package br.ueg.pcb.model.assist;

import javax.persistence.DiscriminatorValue;

import br.edu.aee.UniArch.annotation.VisibleEntityName;

@SuppressWarnings("serial")
@VisibleEntityName(value="sexo")
@javax.persistence.Entity
@DiscriminatorValue("sexo")
public class Sexo extends EntityTabelaBasica {

}
