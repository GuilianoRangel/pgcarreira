package br.ueg.pcb.view;

import org.springframework.context.annotation.Scope;

import br.edu.aee.UniArch.annotation.AttributeView;
import br.edu.aee.UniArch.annotation.Scenario;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.interfaces.IValidator;
import br.ueg.pcb.controller.VinculoControler;
import br.ueg.pcb.model.Vinculo;

@SuppressWarnings("serial")
@Scope(value="prototype")
@Scenario(visibleName = "VINCULO", value = "_SCENARIO", isSingle = true, restrictedAccess = true, showOnMenu = true, actions = {
		IValidator.RECORD_ACTION, IValidator.SAVE_ACTION,
		IValidator.LIST_ACTION})
@org.springframework.stereotype.Component


public class VinculoComposer extends SuperViewZKPGC<VinculoControler, Vinculo, Long>{
	
	@AttributeView(isEntityField = true, attributeName = "descricao")
	private String fldDescricao;

	public String getFldDescricao() {
		return fldDescricao;
	}

	public void setFldDescricao(String fldDescricao) {
		this.fldDescricao = fldDescricao;
	}
	
	public Class<?> getEntityClass() {
		return Vinculo.class;
	}
	
	@Override
	public String getUseCase() {
		return "VINCULO_UC";
	}
	/**
	 * @see br.edu.aee.UniArch.structure.view.ZK.SuperViewZK#newControlInstance()
	 */
	@Override
	protected VinculoControler newControlInstance() {
		return SpringFactory.getBean("vinculoControler", VinculoControler.class);
	}
}
