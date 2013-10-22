package br.ueg.pcb.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.annotation.UseCase;
import br.edu.aee.UniArch.structure.controller.GenericController;
import br.ueg.pcb.model.assist.TipoVinculo;

@Controller
@Scope("session")
@UseCase(value = "VINCULO_UC", order = 4)

public class TipoVinculoController extends GenericController<TipoVinculo, Long> {

}
