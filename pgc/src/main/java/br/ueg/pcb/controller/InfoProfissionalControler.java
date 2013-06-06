package br.ueg.pcb.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.structure.controller.GenericController;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.InfoProfissional;

@Controller
@Scope("session")
public class InfoProfissionalControler  extends GenericController<InfoProfissional, Long>{

}
