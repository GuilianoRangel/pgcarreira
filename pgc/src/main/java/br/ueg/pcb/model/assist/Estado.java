package br.ueg.pcb.model.assist;

import javax.persistence.DiscriminatorValue;

import br.edu.aee.UniArch.annotation.VisibleEntityName;

@VisibleEntityName(value="Estado")
@SuppressWarnings("serial")
@javax.persistence.Entity
@DiscriminatorValue("estado")
public class Estado extends EntityTabelaBasica {

}
