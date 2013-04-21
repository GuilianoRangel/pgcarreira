package br.ueg.pcb.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.domain.GenericActionReturn;
import br.edu.aee.UniArch.structure.controller.GenericController;
import br.edu.aee.UniArch.structure.interfaces.IGenericService;
import br.ueg.pcb.model.Academico;

@Controller
@Scope("session")
public class CadastroAcademicoControler extends GenericController<Academico, Long> {
	
	//
	public ActionReturn<String, String> procuraracademico(){
		ActionReturn<String, String> actionReturn = new ActionReturn<String, String>();
		actionReturn.addParameter("resultado", "Sucesso");
		return actionReturn;
	}

}
