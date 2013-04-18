package br.ueg.pcb.model.assist;

import javax.persistence.DiscriminatorValue;

import br.edu.aee.UniArch.annotation.VisibleEntityName;


@SuppressWarnings("serial")
@VisibleEntityName(value="EstadoCivil")
@javax.persistence.Entity
@DiscriminatorValue("estado_civil")
public class EstadoCivil extends EntityTabelaBasica {

}
