package br.ueg.pcb.model;

import java.sql.Date;

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
@VisibleEntityName(key="Academico")
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
	private Date datanascimento;
	
	@SearchableField(description="Sexo")
	@Column(name = "sexo_acad", nullable = false)
	private Sexo sexo; 
	
	@SearchableField(description="Estado cívil")
	@Column(name = "estado_civil_acad", nullable = true)
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
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_uf_acad", insertable = true, updatable = true, nullable=false)
	private Estado enderecoUF;
	
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
	
}
