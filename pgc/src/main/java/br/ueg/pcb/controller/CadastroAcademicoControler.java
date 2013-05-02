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
import br.edu.aee.UniArch.structure.interfaces.IGenericService;
import br.edu.aee.UniArch.structure.model.UserPermission;
import br.edu.aee.UniArch.structure.persistence.dao.GenericDAO;
import br.edu.aee.UniArch.subsystems.security.SecurityDAO;
import br.edu.aee.UniArch.utils.ConfigurationProperties;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.CursosAcademico;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.model.Unidade;
import br.ueg.pcb.model.assist.EntityTabelaBasica;
import br.ueg.pcb.service.AcademicoService;
import br.ueg.pcb.view.CadastroAcademicoComposer;

@Controller
@Scope("session")
public class CadastroAcademicoControler extends GenericController<Academico, Long> {
	
	/* (non-Javadoc)
	 * @see br.edu.aee.UniArch.structure.controller.GenericController#record()
	 */
	@Override
	public ActionReturn<String, Academico> record() {
		UserPermission up = new UserPermission();
		this.resetAttributesOfView();
		Academico academico = this.getEntityFromView();
		up.setAuthenticationType(AuthenticationTypeEnum.INTERNAL);
		up.setLogin(academico.getEmail());
		up.setName(academico.getUegAcademico().getNome());
		up.setPassword((String)this.getAttributeFromView("senha"));
		up.setStatus(StatusEnum.ACTIVE);
		
		
		GenericDAO userPermissionDAO = SpringFactory.getBean(SecurityDAO.class);
		try {
			userPermissionDAO.save(up);
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		academico.setPkUserPermission(up.getPk());
		
		setEntityFromView(academico);
		
		ActionReturn<String, Academico> actionReturn = super.record();
		if (actionReturn.isSuccess()){
			actionReturn.addExtra("nextUseCase", this.getMessageByKey("view.Academico.homepage"));
		}
		
		return actionReturn;
	}

	private Academico selectedAcademico;
	
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
		
		actionReturn.addExtra("nextUseCase", this.getMessageByKey("view.CadastroAcademico.cadastro2"));
		actionReturn.reportSuccess();
		this.setAttributeFromView("casoDeUsoCenario", "CadastrarAcademico");
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
