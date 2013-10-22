package br.ueg.pcb.model.assist;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.edu.aee.UniArch.annotation.UseCase;
import br.edu.aee.UniArch.annotation.VisibleEntityName;
import br.edu.aee.UniArch.structure.interfaces.ILocalizableEntity;

@SuppressWarnings("serial")
@DiscriminatorValue("tipovinculo")
@Entity
@VisibleEntityName("Vinculo")
public class TipoVinculo extends EntityTabelaBasica implements ILocalizableEntity {

	@Override
	public Map<String, String> getLocalizableFields() {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("Descrição", this.getDescricao());
		map.put("Código", String.valueOf(this.getPk()));
		return map;
	}
	

	
	
	
}
