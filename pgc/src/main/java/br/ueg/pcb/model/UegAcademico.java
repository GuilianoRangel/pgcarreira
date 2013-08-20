package br.ueg.pcb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.aee.UniArch.annotation.SearchableField;
import br.edu.aee.UniArch.annotation.VisibleEntityName;
import br.edu.aee.UniArch.structure.model.Entity;

@SuppressWarnings("serial")
@VisibleEntityName(value="UegAcademico")
@javax.persistence.Entity
@Table(name="ueg_academico")
public class UegAcademico extends Entity<String> {

	@Id
	@SearchableField(description="cpf_acad")
	@Column(name = "cpf_acad", length=12, nullable = false)
	private String cpf;
	
	@SearchableField(description="Nome Acadêmico/Egresso")
	@Column(name = "nome_acad", length=100, nullable = false)
	private String nome;
	
	@Override
	public String getPk() {
		return this.cpf;
	}

	@Override
	public void setPk(String pk) {
		this.cpf = pk;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
