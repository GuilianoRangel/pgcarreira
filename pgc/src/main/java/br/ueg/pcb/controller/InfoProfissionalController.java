package br.ueg.pcb.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.annotation.UseCase;
import br.edu.aee.UniArch.structure.controller.GenericController;
import br.edu.aee.UniArch.utils.ConfigurationProperties;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.InfoProfissional;

@Controller
@Scope("session")
@UseCase(value = "CADASTRO_ACADEMICO_INFOPROFISSIONAL", order = 2)
public class InfoProfissionalController  extends GenericController<InfoProfissional, Long>{
		

}
