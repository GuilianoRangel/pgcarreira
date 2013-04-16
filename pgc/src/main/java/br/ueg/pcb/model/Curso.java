package br.ueg.pcb.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.aee.UniArch.annotation.SearchableField;
import br.edu.aee.UniArch.annotation.VisibleEntityName;
import br.edu.aee.UniArch.structure.model.Entity;


@SuppressWarnings("serial")
@VisibleEntityName(key="Curso")
@javax.persistence.Entity
@Table(name="ueg_curso")
public class Curso extends Entity<String> {

	@Id
	@SearchableField(description="id_curs")
	@Column(name = "id_curs", length=10, nullable = false)
	private String pk;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Unidade unidade;
	
	@SearchableField(description="Nome Curso")
	@Column(name = "nome_curs", length=100, nullable = false)
	private String nome;
	
	@Column(name = "modalidade_curs", length=40, nullable = true)
	private String modalidade;
	
	@Column(name = "regime_curs", length=40, nullable = true)
	private String regime;
	
	@Column(name = "sistema_curs", length=40, nullable = true)
	private String sistema;
	
	@Column(name = "integralizacao_curs", length=40, nullable = true)
	private String integralizacao;
	
	@Column(name = "carga_horaria_total_curs", length=20, nullable = true)
	private String cargaHorariaTotal;
	
	@Column(name = "turno_curs", length=20, nullable = true)
	private String turno;
	
	@Column(name = "inicio_virgencia_curs", length=20, nullable = true)
	private String inicioVirgencia;
	
	@Column(name = "coordenador_curs", length=100, nullable = true)
	private String coordenador;
	
	
	@Override
	public String getPk() {
		return this.pk;
	}

	@Override
	public void setPk(String pk) {
		this.pk = pk;
	}

	/**
	 * @return the unidade
	 */
	public Unidade getUnidade() {
		return unidade;
	}

	/**
	 * @param unidade the unidade to set
	 */
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
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

	/**
	 * @return the modalidade
	 */
	public String getModalidade() {
		return modalidade;
	}

	/**
	 * @param modalidade the modalidade to set
	 */
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	/**
	 * @return the regime
	 */
	public String getRegime() {
		return regime;
	}

	/**
	 * @param regime the regime to set
	 */
	public void setRegime(String regime) {
		this.regime = regime;
	}

	/**
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return the integralizacao
	 */
	public String getIntegralizacao() {
		return integralizacao;
	}

	/**
	 * @param integralizacao the integralizacao to set
	 */
	public void setIntegralizacao(String integralizacao) {
		this.integralizacao = integralizacao;
	}

	/**
	 * @return the cargaHorariaTotal
	 */
	public String getCargaHorariaTotal() {
		return cargaHorariaTotal;
	}

	/**
	 * @param cargaHorariaTotal the cargaHorariaTotal to set
	 */
	public void setCargaHorariaTotal(String cargaHorariaTotal) {
		this.cargaHorariaTotal = cargaHorariaTotal;
	}

	/**
	 * @return the turno
	 */
	public String getTurno() {
		return turno;
	}

	/**
	 * @param turno the turno to set
	 */
	public void setTurno(String turno) {
		this.turno = turno;
	}

	/**
	 * @return the inicioVirgencia
	 */
	public String getInicioVirgencia() {
		return inicioVirgencia;
	}

	/**
	 * @param inicioVirgencia the inicioVirgencia to set
	 */
	public void setInicioVirgencia(String inicioVirgencia) {
		this.inicioVirgencia = inicioVirgencia;
	}

	/**
	 * @return the coordenador
	 */
	public String getCoordenador() {
		return coordenador;
	}

	/**
	 * @param coordenador the coordenador to set
	 */
	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}

}
