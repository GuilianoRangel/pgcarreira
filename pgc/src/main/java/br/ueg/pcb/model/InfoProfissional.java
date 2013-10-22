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
import br.ueg.pcb.model.assist.Mes;
import br.ueg.pcb.model.assist.TipoVinculo;

@SuppressWarnings("serial")
@VisibleEntityName(value="Academico")
@Entity
@Table(name="info_profissional")
public class InfoProfissional  extends SingleEntity  {

	@SearchableField(description="Acadêmico")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pk_acad_inpr", updatable=false, nullable=false)
	private Academico academico;
	
	@SearchableField(description="Cargo ocupado")
	@Column(name = "cargo_inpr", length=100, nullable = false)
	private String cargo;
	
	@SearchableField(description="Empresa")
	@Column(name = "empresa_inpr", length=100, nullable = false)
	private String empresa;
	
	@SearchableField(description="Mês de Entrada")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entrada_mes_inpr", updatable=true, nullable=false)
	private Mes entradaMes;
	
	@SearchableField(description="Ano de Entrada")
	@Column(name = "entrada_ano_inpr", nullable = false)
	private Integer entradaAno;
	
	@SearchableField(description="Mês de Saída")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="saida_mes_inpr", updatable=true, nullable=true)
	private Mes saidaMes;
	
	@SearchableField(description="Ano de Saida")
	@Column(name = "saida_ano_inpr", nullable = true)
	private Integer saidaAno;
	
	@SearchableField(description="Tipo de Vínculo")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipovinculo_inpr", updatable=true, nullable=false)
	private TipoVinculo tipoVinculo;
	
	@SearchableField(description="Localidade")
	@Column(name = "localidade_inpr", nullable = true, length=100)
	private String localidade;
	
	@SearchableField(description="Descrição")
	@Column(name = "descricao_inpr", nullable = true, length=2000)
	private String descricao;

	public Academico getAcademico() {
		return academico;
	}

	public void setAcademico(Academico academico) {
		this.academico = academico;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Mes getEntradaMes() {
		return entradaMes;
	}

	public void setEntradaMes(Mes entradaMes) {
		this.entradaMes = entradaMes;
	}

	public Integer getEntradaAno() {
		return entradaAno;
	}

	public void setEntradaAno(Integer entradaAno) {
		this.entradaAno = entradaAno;
	}

	public Mes getSaidaMes() {
		return saidaMes;
	}

	public void setSaidaMes(Mes saidaMes) {
		this.saidaMes = saidaMes;
	}

	public Integer getSaidaAno() {
		return saidaAno;
	}

	public void setSaidaAno(Integer saidaAno) {
		this.saidaAno = saidaAno;
	}

	public TipoVinculo getTipoVinculo() {
		return tipoVinculo;
	}

	public void setTipoVinculo(TipoVinculo tipoVinculo) {
		this.tipoVinculo = tipoVinculo;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
