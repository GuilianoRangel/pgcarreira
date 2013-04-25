package br.ueg.pcb.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.domain.GenericActionReturn;
import br.edu.aee.UniArch.enums.ReturnTypeEnum;
import br.edu.aee.UniArch.structure.controller.GenericController;
import br.edu.aee.UniArch.structure.interfaces.IGenericService;
import br.edu.aee.UniArch.utils.ConfigurationProperties;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.model.Unidade;
import br.ueg.pcb.service.AcademicoService;

@Controller
@Scope("session")
public class CadastroAcademicoControler extends GenericController<Academico, Long> {
	
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
		
		actionReturn.addParameter("nextUseCase", this.getMessageByKey("view.CadastroAcademico.cadastro2"));
		actionReturn.reportSuccess();
		//((AcademicoService) this.getService()).existsUegAcademico(keyType, keyValue)
		return actionReturn;
	}
	
	public List<Unidade> getListUnidadeDoAcademico(){		
		return this.getAcademicoService().getListUnidadeAcademico(this.getSelectedAcademico());
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

}
