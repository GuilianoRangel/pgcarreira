package br.ueg.pcb.view;

import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.AnnotateDataBinder;

import br.edu.aee.UniArch.structure.view.ZK.CRUDViewZK;
import br.ueg.pcb.controller.InfoProfissionalControler;
import br.ueg.pcb.model.InfoProfissional;

@SuppressWarnings({ "serial" })
@Scope(value="session")
@org.springframework.stereotype.Component
public class InfoProfissionalComposer extends SuperViewZKPGC<InfoProfissionalControler, InfoProfissional, Long>{
	
	@Override
	public String getUseCase() {
		return "ManterInfoProfissional";
	}

	
}
