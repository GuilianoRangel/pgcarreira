package br.ueg.pcb.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.domain.GenericActionReturn;
import br.edu.aee.UniArch.structure.controller.GenericController;
import br.edu.aee.UniArch.structure.interfaces.IGenericService;
import br.edu.aee.UniArch.utils.ConfigurationProperties;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.service.AcademicoService;

@Controller
@Scope("session")
public class CadastroAcademicoControler extends GenericController<Academico, Long> {
	
	//
	public ActionReturn<String, Object> procuraracademico(){
		ActionReturn<String, Object> actionReturn = new ActionReturn<String, Object>();
		actionReturn.addParameter("nextUseCase", ConfigurationProperties.getInstance().getValue("view.CadastroAcademico.cadastro2"));
		actionReturn.reportSuccess();
		//((AcademicoService) this.getService()).existsUegAcademico(keyType, keyValue)
		return actionReturn;
	}
	
	public ActionReturn<String, Object> buscaracademico(){
		ActionReturn<String, Object> actionReturn = new ActionReturn<String, Object>();
		
		
		return actionReturn;
	}

}
