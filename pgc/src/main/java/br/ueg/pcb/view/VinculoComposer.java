package br.ueg.pcb.view;

import org.springframework.context.annotation.Scope;

import br.edu.aee.UniArch.annotation.AttributeView;
import br.edu.aee.UniArch.annotation.Scenario;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.interfaces.IValidator;
import br.ueg.pcb.controller.TipoVinculoController;
import br.ueg.pcb.model.assist.TipoVinculo;
import br.ueg.pcb.viewnousecase.SuperViewZKPGC;

@SuppressWarnings("serial")
@Scope(value="prototype")
@Scenario(visibleName = "VINCULO", value = "_SCENARIO", isSingle = true, restrictedAccess = true, showOnMenu = true, actions = {
		IValidator.RECORD_ACTION, IValidator.SAVE_ACTION,
		IValidator.LIST_ACTION})
@org.springframework.stereotype.Component


public class VinculoComposer extends SuperViewZKPGC<TipoVinculoController, TipoVinculo, Long>{
	
	@AttributeView(isEntityField = true, attributeName = "descricao")
	private String fldDescricao;

	public String getFldDescricao() {
		return fldDescricao;
	}

	public void setFldDescricao(String fldDescricao) {
		this.fldDescricao = fldDescricao;
	}
	
	public Class<?> getEntityClass() {
		return TipoVinculo.class;
	}

	@Override
	public void cleanFields() {
		this.setFldDescricao(null);
		super.cleanFields();
	}
	
	/*@Override
	public String getUseCase() {
		return "VINCULO_UC";
	}*/
	/**
	 * @see br.edu.aee.UniArch.structure.view.ZK.SuperViewZK#newControlInstance()
	 */
	/*@Override
	protected VinculoController newControlInstance() {
		return SpringFactory.getBean("vinculoController", VinculoController.class);
	}*/
}
