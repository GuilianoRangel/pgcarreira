package br.ueg.pcb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.aee.UniArch.annotation.SearchableField;
import br.edu.aee.UniArch.annotation.VisibleEntityName;
import br.edu.aee.UniArch.structure.model.SingleEntity;

@SuppressWarnings("serial")
@VisibleEntityName(value="Academico")
@Entity
@Table(name="info_profissional")
public class InfoProfissional  extends SingleEntity  {
	@SearchableField(description="Acadêmico")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pk_acad_inpr", updatable=false, nullable=false)
	private Academico Academico;
	
	@SearchableField(description="Cargo ocupado")
	@Column(name = "cargo_inpr", length=100, nullable = false)
	private String cargo;
	
	@SearchableField(description="Empresa")
	@Column(name = "empresa_inpr", length=100, nullable = false)
	private String empresa;
}
