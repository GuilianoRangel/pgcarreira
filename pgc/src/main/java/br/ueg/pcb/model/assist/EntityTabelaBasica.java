package br.ueg.pcb.model.assist;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import br.edu.aee.UniArch.annotation.SearchableField;
import br.edu.aee.UniArch.structure.model.Entity;
import br.edu.aee.UniArch.structure.model.SingleEntity;

@javax.persistence.Entity
@SuppressWarnings("serial")
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tabela", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("  ")
@Table(name = "tabela_basica")
public class EntityTabelaBasica extends Entity<Long> {

	/**
	 * Chave primária simples: Long auto generated
	 */
	@SearchableField(description="CODIGO")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_item")
	private Long pk;
	
	@Column(name = "descricao", nullable = false, length = 200)
	private String descricao;

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String vDescricao) {
		this.descricao = vDescricao;
	}

	@Override
	public String toString() {
		return this.getDescricao();
	}
	
	

	/**
	 * @see br.edu.aee.UniArch.structure.interfaces.ISuperEntity#getPk()
	 */
	@Override
	public Long getPk() {
		return this.pk;
	}

	/**
	 * @see br.edu.aee.UniArch.structure.interfaces.ISuperEntity#setPk(java.io.Serializable)
	 */
	@Override
	public void setPk(Long pk) {
		this.pk = pk;
	}

}
