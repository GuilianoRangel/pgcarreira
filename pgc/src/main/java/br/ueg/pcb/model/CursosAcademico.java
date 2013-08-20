package br.ueg.pcb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.aee.UniArch.annotation.SearchableField;
import br.edu.aee.UniArch.annotation.VisibleEntityName;
import br.edu.aee.UniArch.structure.model.CompositeEntity;
import br.ueg.pcb.model.pks.CursosAcademicoPK;

@SuppressWarnings("serial")
@VisibleEntityName(value="Cursos Acadêmicos")
@Entity
@Table(name="ueg_cursos_academico")
public class CursosAcademico extends CompositeEntity<CursosAcademicoPK> {
	
	@SearchableField(description="Ano Conclusão")
	@Column(name = "ano_conclusao_cuac", length=10, nullable = true)
	private String anoConclusao;

	@SearchableField(description="Matricula")
	@Column(name = "matricula_cuac", length=12, nullable = false)
	private String matricula;
	
	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * @return the anoConclusao
	 */
	public String getAnoConclusao() {
		return anoConclusao;
	}

	/**
	 * @param anoConclusao the anoConclusao to set
	 */
	public void setAnoConclusao(String anoConclusao) {
		this.anoConclusao = anoConclusao;
	}
}
