package br.ueg.pcb.model.assist;

import javax.persistence.Cacheable;
import javax.persistence.DiscriminatorValue;

import br.edu.aee.UniArch.annotation.VisibleEntityName;

@SuppressWarnings("serial")
@VisibleEntityName(value="sexo")
@javax.persistence.Entity
@Cacheable(value=false)
@DiscriminatorValue("sexo")
public class Sexo extends EntityTabelaBasica {

}
