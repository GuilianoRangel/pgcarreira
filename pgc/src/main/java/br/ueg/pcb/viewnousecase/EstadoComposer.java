package br.ueg.pcb.viewnousecase;

import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;

import br.edu.aee.UniArch.annotation.AttributeView;
import br.edu.aee.UniArch.structure.controller.GenericController;
import br.edu.aee.UniArch.structure.view.ZK.CRUDViewZK;
import br.ueg.pcb.model.assist.Estado;

/**
 * @author Diego Carlos Rezende - Analista de sistemas 
 *         <diego.rezende@unievangelica.edu.br>
 *
 */

@SuppressWarnings({ "serial" })
@Scope(value="prototype")
@org.springframework.stereotype.Component
public class EstadoComposer extends CRUDViewZK<GenericController<Estado, Long>, Estado, Long> {
	
	@AttributeView(attributeName="descricao" , isEntityField=true)
	private String fldDescricao;

	
	public EstadoComposer() {
		super();
	}
	
	@Override
	public void cleanFields() {
		super.cleanFields();
	}
		


	
	@Override
	public String getUseCase() {
		return "Estado";
	}



	/**
	 * @return the fldDescricao
	 */
	public String getFldDescricao() {
		return fldDescricao;
	}



	/**
	 * @param fldDescricao the fldDescricao to set
	 */
	public void setFldDescricao(String fldDescricao) {
		this.fldDescricao = fldDescricao;
	}


	
}

