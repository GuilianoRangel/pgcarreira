package br.ueg.pcb.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.annotation.ActionMethod;
import br.edu.aee.UniArch.annotation.UseCase;
import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.domain.GenericActionReturn;
import br.edu.aee.UniArch.enums.AuthenticationTypeEnum;
import br.edu.aee.UniArch.enums.ReturnTypeEnum;
import br.edu.aee.UniArch.enums.StatusEnum;
import br.edu.aee.UniArch.exception.SuperException;
import br.edu.aee.UniArch.structure.controller.GenericController;
import br.edu.aee.UniArch.structure.interfaces.IGenericView;
import br.edu.aee.UniArch.structure.interfaces.IValidator;
import br.edu.aee.UniArch.structure.model.AllocationUser;
import br.edu.aee.UniArch.structure.model.UserPermission;
import br.edu.aee.UniArch.subsystems.sessionparam.SessionParameter;
import br.edu.aee.UniArch.utils.ConfigurationProperties;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.CursosAcademico;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.model.Unidade;
import br.ueg.pcb.model.assist.EntityTabelaBasica;
import br.ueg.pcb.service.AcademicoService;
import br.ueg.pcb.view.SuperViewZKPGC;

@Controller
@Scope("session")
@UseCase(value = "CADASTRO_ACADEMICO_UC", order = 4)
public class CadastroAcademicoControler extends GenericController<Academico, Long> {
	public static final String ACTION_EDIT_ACADEMICO = "AC_EDITAR_ACADEMICO";
	public static final String ACTION_PROCURAR_ACADEMICO = "AC_PROCURAR_ACADEMICO";
	
	@ActionMethod(ACTION_EDIT_ACADEMICO)
	public ActionReturn<String, Academico> edit(){
		ActionReturn<String, Academico> actionReturn = new ActionReturn<String, Academico>();
				UserPermission up = (UserPermission)getView().getUserLogged();
				if(up==null){
					String loginPage = ConfigurationProperties.getInstance().getPropertyOrDefault("SECURITY_LOGIN_PAGE");
					actionReturn.reportFailure(ReturnTypeEnum.ERROR);
					actionReturn.addExtra(ActionReturn.NEXT_USE_CASE,loginPage);
					return actionReturn;
				}
				
				Academico academico = this.getAcademicoByUserPermission(up);
				this.setSelectedAcademico(academico);
				this.setEntityFromView(this.getSelectedAcademico());
				this.setAttributeFromView("senha", "");
				this.setAttributeFromView("confirmaSenha", "");
				
				((SuperViewZKPGC)this.getView()).setCasoDeUsoCenario("EditarAcademico");
				
				actionReturn.addParameter(ActionReturn.ENTITY_PARAMETER, academico);
				
				actionReturn.addExtra(ActionReturn.NEXT_USE_CASE, this.getMessageByKey("view.CadastroAcademico.cadastro2"));
				
				return actionReturn;
	}
	/* (non-Javadoc)
	 * @see br.edu.aee.UniArch.structure.controller.GenericController#record()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@ActionMethod(IValidator.RECORD_ACTION)
	public ActionReturn<String, Academico> record() {
		
		ActionReturn<?,?> actionReturn = super.executeValidateAction(IValidator.SAVE_ACTION);
		if(!actionReturn.isSuccess()){
			return (ActionReturn<String, Academico>) actionReturn;
		}
		
		
		this.resetAttributesOfView();
		Academico academico = this.getEntityFromView();
		
		UserPermission up = saveUserPermission(academico, actionReturn);
		
		if(!actionReturn.isSuccess()	){
			this.getSecurityService().rollback();
			return (ActionReturn<String, Academico>) actionReturn;
		}
		
		
		academico.setPkUserPermission(up.getPk());
		
		
		
		setEntityFromView(academico);
		
		actionReturn = super.record();
		if (actionReturn.isSuccess()){
			actionReturn.addExtra(ActionReturn.NEXT_USE_CASE, this.getMessageByKey("view.Academico.homepage"));
			try {
				this.getSecurityService().commit();
			} catch (SuperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			this.getSecurityService().rollback();
		}
		
		return (ActionReturn<String, Academico>) actionReturn;
	}

	/**
	 * metodo para savar um UserPermissio baseado no academico pasado
	 * @param academico
	 * @return retorna o UserPermission salvo ou null caso ocorra algum problema ao salvar e modifica o ActionReturn com o erro
	 */
	private UserPermission saveUserPermission(Academico academico, ActionReturn<?, ?> actionReturn) {
		Boolean isUpdate = (Boolean) getAttributeFromView(IGenericView.ATTRIBUTE_UPDATE);
		ActionReturn<String, ?>ar2 = new GenericActionReturn();
		
		//this._securityService = (SecurityService) SpringFactory.getBean(SecurityService.class);
		try {
			this.getSecurityService().activeMultiTransaction();
		} catch (SuperException e) {
			actionReturn.reportFailure(ReturnTypeEnum.ERROR,Arrays.asList(this.getMessageByKey("CadastroAcademico.CadastrarAcademico.erroSalvarUserPermission")));
			actionReturn.addExtra("stackTrace", e.getStackTrace());
			return null;
		}
	
		
		//_securityService.setDao(SpringFactory.getBean(SecurityDAO.class));
		
		
		UserPermission up = new UserPermission();;;
		if(isUpdate){
			up.setPk(academico.getPkUserPermission());
			ar2 = this.getSecurityService().findByPk(academico.getPkUserPermission());
			if(!ar2.isSuccess()){
				return null;
			}
			up = (UserPermission) ar2.getParameter(ActionReturn.ENTITY_PARAMETER);
		}
		up.setAuthenticationType(AuthenticationTypeEnum.INTERNAL);
		up.setLogin(academico.getEmail());
		up.setName(academico.getUegAcademico().getNome());
		
		String senha = (String)this.getAttributeFromView("senha");
		if(senha!=null && !senha.isEmpty()){
			up.setPassword(senha);
		}
		up.setStatus(StatusEnum.ACTIVE);
		
		
		
		if(isUpdate){
			ar2 = this.getSecurityService().update(up);
		}else{
			ar2 = this.getSecurityService().save(up);
			
		}
		if(!ar2.isSuccess()){
			actionReturn.reportFailure(ReturnTypeEnum.ERROR,Arrays.asList(this.getMessageByKey("CadastroAcademico.CadastrarAcademico.erroSalvarUserPermission")));
			actionReturn.addExtra("stackTrace", ar2.getErrorMessages());
			return null;
		}else{
			//salva alocação de unidade(requirido pela arquitetura)
			if (!isUpdate && this.getSystem().getHasNoUnit()) {
				AllocationUser allocationUser = new AllocationUser();
				allocationUser.setUser(up);
				allocationUser.setUnit(this.getSecurityService().findNoUnit());
				this.getSecurityService().saveAllocatedUser(allocationUser);
			}
		}
		
//		if(isUpdate){
//			return up;
//		}else{
//			return (UserPermission) ar2.getParameter(ActionReturn.ENTITY_PARAMETER);
//		}
		return up;
	}

	private Academico selectedAcademico;
	//private SecurityService _securityService;
	
	public AcademicoService getAcademicoService(){
		return ((AcademicoService) this.getService());
	}
	
	public String getMessageByKey(String key){
		return ConfigurationProperties.getInstance().getValue(key);
	}
	
	@ActionMethod(ACTION_PROCURAR_ACADEMICO)
	public ActionReturn<String, Object> procuraracademico(){
		ActionReturn<String, Object> actionReturn = new ActionReturn<String, Object>();
		
		String tipoBusca = "CPF";
		String academicoChaveBusca = (String) this.getAttributeFromView("academicoChaveBusca");
				
		UegAcademico ua = getAcademicoService().getUegAcademico(tipoBusca, academicoChaveBusca);
		if(ua==null){
			actionReturn.reportFailure(ReturnTypeEnum.ERROR, Arrays.asList(this.getMessageByKey("CadastroAcademico.procuraracademico.academicoNaoExiste")));
			return actionReturn;
		}
		if(getAcademicoService().existsAcademicoByUegAcademico(ua)){
			String loginPage = ConfigurationProperties.getInstance().getPropertyOrDefault("SECURITY_LOGIN_PAGE");
			actionReturn.reportFailure(ReturnTypeEnum.ERROR, Arrays.asList(this.getMessageByKey("CadastroAcademico.procuraracademico.academicoExiste")));
			actionReturn.addExtra(ActionReturn.NEXT_USE_CASE,loginPage);
			
			return actionReturn;			
		}
		Academico academico = new Academico();
		academico.setUegAcademico(ua);
		this.setSelectedAcademico(academico);
		
		actionReturn.addExtra(ActionReturn.NEXT_USE_CASE, this.getMessageByKey("view.CadastroAcademico.cadastro2"));
		actionReturn.reportSuccess();
		this.setAttributeFromView("casoDeUsoCenario", "CadastrarAcademico");
		this.setAttributeFromView(IGenericView.ATTRIBUTE_UPDATE,false);
		this.setEntityFromView(this.getSelectedAcademico());
		//((AcademicoService) this.getService()).existsUegAcademico(keyType, keyValue)
		return actionReturn;
	}
	
	public List<Unidade> getListUnidadeDoAcademico(){		
		return this.getAcademicoService().getListUnidadeAcademico(this.getSelectedAcademico());
	}
	
	public List<CursosAcademico> getListCursosAcademico(){
		return this.getAcademicoService().getListCursosAcademico(this.getSelectedAcademico());
	}

	/**
	 * @return the selectedAcademico
	 */
	public Academico getSelectedAcademico() {
		return selectedAcademico;
	}

	/**
	 * @param selectedAcademico the selectedAcademico to set
	 */
	public void setSelectedAcademico(Academico selectedAcademico) {
		this.selectedAcademico = selectedAcademico;
	}
	
	public Academico getAcademicoByUserPermission(UserPermission up){
		return this.getAcademicoService().getAcademicoByUserPermission(up);
	}
	
	/** retorna uma lista de entidades de uma classe passada.
	 * @param classe
	 * @return
	 */
	public <T extends EntityTabelaBasica> List<T> getListEntityTabelaBasica(Class<T> classe){
		return this.getAcademicoService().getListEntityTabelaBasica(classe);
	}
	
	/*
	 * Sem javaDoc, verificar super classe
	 
	@Override
	protected List<SessionParameter> getUseCaseSessionParameters() {
		return Arrays.asList(new SessionParameter(Unidade.class));
	}*/
}
