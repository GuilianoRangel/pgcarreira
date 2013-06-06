package br.ueg.pcb.model.assist;

import javax.persistence.DiscriminatorValue;

import br.edu.aee.UniArch.annotation.VisibleEntityName;

@VisibleEntityName(value="Estado")
@SuppressWarnings("serial")
@javax.persistence.Entity
@DiscriminatorValue("tipovinculoprofissional")
public class TipoVinculoProfissional extends EntityTabelaBasica {

}
