package br.ueg.pcb.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.domain.GenericActionReturn;
import br.edu.aee.UniArch.enums.AuthenticationTypeEnum;
import br.edu.aee.UniArch.enums.ReturnTypeEnum;
import br.edu.aee.UniArch.enums.StatusEnum;
import br.edu.aee.UniArch.exception.SuperException;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.controller.GenericController;
import br.edu.aee.UniArch.structure.interfaces.IGenericView;
import br.edu.aee.UniArch.structure.interfaces.IValidator;
import br.edu.aee.UniArch.structure.model.UserPermission;
import br.edu.aee.UniArch.subsystems.security.SecurityDAO;
import br.edu.aee.UniArch.subsystems.security.SecurityService;
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
public class CadastroAcademicoControler extends GenericController<Academico, Long> {
	
	
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
	public ActionReturn<String, Academico> record() {
		
		ActionReturn<?,?> actionReturn = super.executeValidateAction(IValidator.SAVE_ACTION);
		if(!actionReturn.isSuccess()){
			return (ActionReturn<String, Academico>) actionReturn;
		}
		
		
		this.resetAttributesOfView();
		Academico academico = this.getEntityFromView();
		
		UserPermission up = saveUserPermission(academico, actionReturn);
		
		if(!actionReturn.isSuccess()	){
			securityService.rollback();
			return (ActionReturn<String, Academico>) actionReturn;
		}
		
		
		academico.setPkUserPermission(up.getPk());
		
		
		
		setEntityFromView(academico);
		
		actionReturn = super.record();
		if (actionReturn.isSuccess()){
			actionReturn.addExtra(ActionReturn.NEXT_USE_CASE, this.getMessageByKey("view.Academico.homepage"));
			try {
				securityService.commit();
			} catch (SuperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			securityService.rollback();
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
		
		this.securityService = (SecurityService) SpringFactory.getBean(SecurityService.class);
		this.securityService.activeMultiTransaction();
	
		
		securityService.setDao(SpringFactory.getBean(SecurityDAO.class));
		ActionReturn<String, ?>ar2 = new GenericActionReturn();
		
		UserPermission up = new UserPermission();;;
		if(isUpdate){
			up.setPk(academico.getPkUserPermission());
			ar2 = securityService.findByPk(academico.getPkUserPermission());
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
			ar2 = this.securityService.update(up);
		}else{
			ar2 = this.securityService.save(up);
		}
		if(!ar2.isSuccess()){
			actionReturn.reportFailure(ReturnTypeEnum.ERROR,Arrays.asList(this.getMessageByKey("CadastroAcademico.CadastrarAcademico.erroSalvarUserPermission")));
			actionReturn.addExtra("stackTrace", ar2.getErrorMessages());
			return null;
		}
		
//		if(isUpdate){
//			return up;
//		}else{
//			return (UserPermission) ar2.getParameter(ActionReturn.ENTITY_PARAMETER);
//		}
		return up;
	}

	private Academico selectedAcademico;
	private SecurityService securityService;
	
	public AcademicoService getAcademicoService(){
		return ((AcademicoService) this.getService());
	}
	
	public String getMessageByKey(String key){
		return ConfigurationProperties.getInstance().getValue(key);
	}
	
	public ActionReturn<String, Object> procuraracademico(){
		ActionReturn<String, Object> actionReturn = new ActionReturn<String, Object>();
		
		String tipoBusca = (String) this.getAttributeFromView("tipoBusca");
		String academicoChaveBusca = (String) this.getAttributeFromView("academicoChaveBusca");
				
		UegAcademico ua = getAcademicoService().getUegAcademico(tipoBusca, academicoChaveBusca);
		if(ua==null){
			actionReturn.reportFailure(ReturnTypeEnum.ERROR, Arrays.asList(this.getMessageByKey("CadastroAcademico.procuraracademico.academicoNaoExiste")));
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

}
