package br.ueg.pcb.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.aee.UniArch.annotation.SearchableField;
import br.edu.aee.UniArch.annotation.VisibleEntityName;
import br.edu.aee.UniArch.structure.model.SingleEntity;
import br.ueg.pcb.model.assist.Estado;
import br.ueg.pcb.model.assist.EstadoCivil;
import br.ueg.pcb.model.assist.Sexo;

@SuppressWarnings("serial")
@VisibleEntityName(value="Academico")
@Entity
@Table(name="academico")
public class Academico extends SingleEntity {
	@SearchableField(description="UegAcadêmico")
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="matricula_acad")
	private UegAcademico uegAcademico;
	
	@SearchableField(description="Nome da mãe")
	@Column(name = "mae_acad", length=100, nullable = false)
	private String nomeMae;
	
	@SearchableField(description="Identidade número")
	@Column(name = "identidade_numero_acad", length=20, nullable = true)
	private String identidadeNumero;
	
	@SearchableField(description="Identidade orgão expedidor")
	@Column(name = "identidade_orgao_acad", length=20, nullable = true)
	private String identidadeOrgao;
	
	@SearchableField(description="Identidade UF")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "identidade_uf_acad", insertable = true, updatable = true, nullable=true)
	private Estado identidadeUf;
	
	@SearchableField(description="Data de nascimento")
	@Column(name = "data_nascimento_acad", nullable = false)
	private Date dataNascimento;
	
	@SearchableField(description="Sexo")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "sexo_acad", insertable = true, updatable = true, nullable=false)
	private Sexo sexo; 
	
	@SearchableField(description="Estado cívil")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "estado_civil_acad", insertable = true, updatable = true, nullable=true)
	private EstadoCivil estadoCivil;
	
	@SearchableField(description="Endereço logradouro")
	@Column(name = "end_logradouro_acad", length=500, nullable = false)
	private String enderecoLogradouro;
	
	@Column(name = "end_numero_acad", length=20, nullable = true)
	private String enderecoNumero;
	
	@Column(name = "end_complemento_acad", length=40, nullable = true)
	private String enderecoComplemento;
	
	@SearchableField(description="Endereço bairro")
	@Column(name = "end_bairro_acad", length=100, nullable = false)
	private String enderecoBairro;	
	
	@SearchableField(description="Endereço UF")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_uf_acad", insertable = true, updatable = true, nullable=false)
	private Estado enderecoUF;
	
	@Column(name = "end_pais_acad", length=50, nullable=false)
	private String enderecoPais;
	
	@Column(name = "end_municipio_acad", length=100, nullable = false)
	private String enderecoMunicipio;
	
	@Column(name = "telefone_fixo_acad", length=50, nullable = true)
	private String telefoneFixo;
	
	@Column(name = "telefone_celular_acad", length=50, nullable = true)
	private String telefoneCelular;
	
	@Column(name = "telefone_recado_acad", length=50, nullable = true)
	private String telefoneRecado;
	
	@Column(name = "email_acad", length=100 ,nullable = false)
	private String email;
	
	@Column(name = "user_acad", nullable = false)
	private Long pkUserPermission;
	
	@Column(name = "autoriza_email_novidade_ueg_acad", nullable = false)
	private Boolean autorizaEmailNovidadeUeg;
	
	@Column(name = "autoriza_email_oportunidade_acad", nullable = false)
	private Boolean autorizaEmailOportundiade;
	
	@Column(name = "autoriza_email_publicacao_acad", nullable = false)
	private Boolean autorizaEmailPublicacao;
	
	@Column(name = "autoriza_parcerio_ver_telefone_acad", nullable = false)
	private Boolean autorizaParceiroVerTelefone;

	/**
	 * @return the uegAcademico
	 */
	public UegAcademico getUegAcademico() {
		return uegAcademico;
	}

	/**
	 * @param uegAcademico the uegAcademico to set
	 */
	public void setUegAcademico(UegAcademico uegAcademico) {
		this.uegAcademico = uegAcademico;
	}

	/**
	 * @return the nomeMae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * @param nomeMae the nomeMae to set
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * @return the identidadeNumero
	 */
	public String getIdentidadeNumero() {
		return identidadeNumero;
	}

	/**
	 * @param identidadeNumero the identidadeNumero to set
	 */
	public void setIdentidadeNumero(String identidadeNumero) {
		this.identidadeNumero = identidadeNumero;
	}

	/**
	 * @return the identidadeOrgao
	 */
	public String getIdentidadeOrgao() {
		return identidadeOrgao;
	}

	/**
	 * @param identidadeOrgao the identidadeOrgao to set
	 */
	public void setIdentidadeOrgao(String identidadeOrgao) {
		this.identidadeOrgao = identidadeOrgao;
	}

	/**
	 * @return the identidadeUf
	 */
	public Estado getIdentidadeUf() {
		return identidadeUf;
	}

	/**
	 * @param identidadeUf the identidadeUf to set
	 */
	public void setIdentidadeUf(Estado identidadeUf) {
		this.identidadeUf = identidadeUf;
	}

	/**
	 * @return the dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return the sexo
	 */
	public Sexo getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the estadoCivil
	 */
	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * @param estadoCivil the estadoCivil to set
	 */
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the enderecoLogradouro
	 */
	public String getEnderecoLogradouro() {
		return enderecoLogradouro;
	}

	/**
	 * @param enderecoLogradouro the enderecoLogradouro to set
	 */
	public void setEnderecoLogradouro(String enderecoLogradouro) {
		this.enderecoLogradouro = enderecoLogradouro;
	}

	/**
	 * @return the enderecoNumero
	 */
	public String getEnderecoNumero() {
		return enderecoNumero;
	}

	/**
	 * @param enderecoNumero the enderecoNumero to set
	 */
	public void setEnderecoNumero(String enderecoNumero) {
		this.enderecoNumero = enderecoNumero;
	}

	/**
	 * @return the enderecoComplemento
	 */
	public String getEnderecoComplemento() {
		return enderecoComplemento;
	}

	/**
	 * @param enderecoComplemento the enderecoComplemento to set
	 */
	public void setEnderecoComplemento(String enderecoComplemento) {
		this.enderecoComplemento = enderecoComplemento;
	}

	/**
	 * @return the enderecoBairro
	 */
	public String getEnderecoBairro() {
		return enderecoBairro;
	}

	/**
	 * @param enderecoBairro the enderecoBairro to set
	 */
	public void setEnderecoBairro(String enderecoBairro) {
		this.enderecoBairro = enderecoBairro;
	}

	/**
	 * @return the enderecoUF
	 */
	public Estado getEnderecoUF() {
		return enderecoUF;
	}

	/**
	 * @param enderecoUF the enderecoUF to set
	 */
	public void setEnderecoUF(Estado enderecoUF) {
		this.enderecoUF = enderecoUF;
	}

	/**
	 * @return the enderecoMunicipio
	 */
	public String getEnderecoMunicipio() {
		return enderecoMunicipio;
	}

	/**
	 * @param enderecoMunicipio the enderecoMunicipio to set
	 */
	public void setEnderecoMunicipio(String enderecoMunicipio) {
		this.enderecoMunicipio = enderecoMunicipio;
	}

	/**
	 * @return the enderecoPais
	 */
	public String getEnderecoPais() {
		return enderecoPais;
	}

	/**
	 * @param enderecoPais the enderecoPais to set
	 */
	public void setEnderecoPais(String enderecoPais) {
		this.enderecoPais = enderecoPais;
	}

	/**
	 * @return the telefoneFixo
	 */
	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	/**
	 * @param telefoneFixo the telefoneFixo to set
	 */
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	/**
	 * @return the telefoneCelular
	 */
	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	/**
	 * @param telefoneCelular the telefoneCelular to set
	 */
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	/**
	 * @return the telefoneRecado
	 */
	public String getTelefoneRecado() {
		return telefoneRecado;
	}

	/**
	 * @param telefoneRecado the telefoneRecado to set
	 */
	public void setTelefoneRecado(String telefoneRecado) {
		this.telefoneRecado = telefoneRecado;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the pkUserPermission
	 */
	public Long getPkUserPermission() {
		return pkUserPermission;
	}

	/**
	 * @param pkUserPermission the pkUserPermission to set
	 */
	public void setPkUserPermission(Long pkUserPermission) {
		this.pkUserPermission = pkUserPermission;
	}

	/**
	 * @return the autorizaEmailNovidadeUeg
	 */
	public Boolean getAutorizaEmailNovidadeUeg() {
		return autorizaEmailNovidadeUeg;
	}

	/**
	 * @param autorizaEmailNovidadeUeg the autorizaEmailNovidadeUeg to set
	 */
	public void setAutorizaEmailNovidadeUeg(Boolean autorizaEmailNovidadeUeg) {
		this.autorizaEmailNovidadeUeg = autorizaEmailNovidadeUeg;
	}

	/**
	 * @return the autorizaEmailOportundiade
	 */
	public Boolean getAutorizaEmailOportundiade() {
		return autorizaEmailOportundiade;
	}

	/**
	 * @param autorizaEmailOportundiade the autorizaEmailOportundiade to set
	 */
	public void setAutorizaEmailOportundiade(Boolean autorizaEmailOportundiade) {
		this.autorizaEmailOportundiade = autorizaEmailOportundiade;
	}

	/**
	 * @return the autorizaEmailPublicacao
	 */
	public Boolean getAutorizaEmailPublicacao() {
		return autorizaEmailPublicacao;
	}

	/**
	 * @param autorizaEmailPublicacao the autorizaEmailPublicacao to set
	 */
	public void setAutorizaEmailPublicacao(Boolean autorizaEmailPublicacao) {
		this.autorizaEmailPublicacao = autorizaEmailPublicacao;
	}

	/**
	 * @return the autorizaParceiroVerTelefone
	 */
	public Boolean getAutorizaParceiroVerTelefone() {
		return autorizaParceiroVerTelefone;
	}

	/**
	 * @param autorizaParceiroVerTelefone the autorizaParceiroVerTelefone to set
	 */
	public void setAutorizaParceiroVerTelefone(Boolean autorizaParceiroVerTelefone) {
		this.autorizaParceiroVerTelefone = autorizaParceiroVerTelefone;
	}
	
}
