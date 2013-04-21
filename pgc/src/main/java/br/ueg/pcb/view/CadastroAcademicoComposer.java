package br.ueg.pcb.view;

import java.sql.Date;

import org.springframework.context.annotation.Scope;

import br.edu.aee.UniArch.annotation.AttributeView;
import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.controller.GenericController;
import br.edu.aee.UniArch.structure.model.UserPermission;
import br.edu.aee.UniArch.structure.view.ZK.CRUDViewZK;
import br.edu.aee.UniArch.subsystems.security.SecurityController;
import br.ueg.pcb.controller.CadastroAcademicoControler;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.model.assist.Estado;
import br.ueg.pcb.model.assist.EstadoCivil;
import br.ueg.pcb.model.assist.Sexo;

@SuppressWarnings({ "serial" })
@Scope(value="session")
@org.springframework.stereotype.Component
public class CadastroAcademicoComposer extends CRUDViewZK<CadastroAcademicoControler, Academico, Long> {
	
	@AttributeView(attributeName="academicoChaveBusca", isEntityField=false)
	private String academicoChaveBusca;
	
	@AttributeView(attributeName="uegAcademico" , isEntityField=true)
	private UegAcademico fldUegAcademico;
	
	@AttributeView(attributeName="nomeMae" , isEntityField=true)
	private String fldMae;
	
	@AttributeView(attributeName="identidadeNumero" , isEntityField=true)
	private String fldIdentidadeNumero;
	
	@AttributeView(attributeName="identidadeOrgao" , isEntityField=true)
	private String fldIdentidadeOrgao;
	
	@AttributeView(attributeName="identidadeUf" , isEntityField=true)
	private String fldIdentidadeUf;
	
	@AttributeView(attributeName="dataNascimento" , isEntityField=true)
	private Date fldDataNascimento;
	
	@AttributeView(attributeName="dataNascimento" , isEntityField=true)
	private Sexo fldSexo;
	
	@AttributeView(attributeName="estadoCivil" , isEntityField=true)
	private EstadoCivil fldEstadoCivil;
	
	@AttributeView(attributeName="enderecoLogradouro" , isEntityField=true)
	private String fldEnderecoLogradouro;
	
	@AttributeView(attributeName="enderecoNumero" , isEntityField=true)
	private String fldEnderecoNumero;
	
	@AttributeView(attributeName="enderecoComplemento" , isEntityField=true)
	private String fldEnderecoComplemento;
	
	@AttributeView(attributeName="enderecoBairro" , isEntityField=true)
	private String fldEnderecoBairro;
	
	@AttributeView(attributeName="enderecoUF" , isEntityField=true)
	private Estado fldEnderecoUF;
	
	@AttributeView(attributeName="enderecoMunicipio" , isEntityField=true)
	private String fldEnderecoMunicipio;
	
	@AttributeView(attributeName="telefoneFixo" , isEntityField=true)
	private String fldTelefoneFixo;
	
	@AttributeView(attributeName="telefoneCelular" , isEntityField=true)
	private String fldTelefoneCelular;
	
	@AttributeView(attributeName="telefoneRecado" , isEntityField=true)
	private String fldTelefoneRecado;
	
	@AttributeView(attributeName="email" , isEntityField=true)
	private String fldEmail;
	
	@AttributeView(attributeName="pkUserPermission" , isEntityField=true)
	private Long fldPkUserPermission;
	
	@AttributeView(attributeName="autorizaEmailNovidadeUeg" , isEntityField=true)
	private Boolean fldAutorizaEmailNovidadeUeg;	
	
	@AttributeView(attributeName="autorizaEmailOportundiade" , isEntityField=true)
	private Boolean fldAutorizaEmailOportundiade;
	
	@AttributeView(attributeName="autorizaEmailPublicacao" , isEntityField=true)
	private Boolean fldAutorizaEmailPublicacao;
	
	@AttributeView(attributeName="autorizaParceiroVerTelefone" , isEntityField=true)
	private Boolean fldAutorizaParceiroVerTelefone;

	@Override
	protected String getUseCase() {
		return "CadastroAcademico";
	}

	/**
	 * @return the fldUegAcademico
	 */
	public UegAcademico getFldUegAcademico() {
		return fldUegAcademico;
	}

	/**
	 * @param fldUegAcademico the fldUegAcademico to set
	 */
	public void setFldUegAcademico(UegAcademico fldUegAcademico) {
		this.fldUegAcademico = fldUegAcademico;
	}

	/**
	 * @return the fldMae
	 */
	public String getFldMae() {
		return fldMae;
	}

	/**
	 * @param fldMae the fldMae to set
	 */
	public void setFldMae(String fldMae) {
		this.fldMae = fldMae;
	}

	/**
	 * @return the fldIdentidadeNumero
	 */
	public String getFldIdentidadeNumero() {
		return fldIdentidadeNumero;
	}

	/**
	 * @param fldIdentidadeNumero the fldIdentidadeNumero to set
	 */
	public void setFldIdentidadeNumero(String fldIdentidadeNumero) {
		this.fldIdentidadeNumero = fldIdentidadeNumero;
	}

	/**
	 * @return the fldIdentidadeOrgao
	 */
	public String getFldIdentidadeOrgao() {
		return fldIdentidadeOrgao;
	}

	/**
	 * @param fldIdentidadeOrgao the fldIdentidadeOrgao to set
	 */
	public void setFldIdentidadeOrgao(String fldIdentidadeOrgao) {
		this.fldIdentidadeOrgao = fldIdentidadeOrgao;
	}

	/**
	 * @return the fldIdentidadeUf
	 */
	public String getFldIdentidadeUf() {
		return fldIdentidadeUf;
	}

	/**
	 * @param fldIdentidadeUf the fldIdentidadeUf to set
	 */
	public void setFldIdentidadeUf(String fldIdentidadeUf) {
		this.fldIdentidadeUf = fldIdentidadeUf;
	}

	/**
	 * @return the fldDataNascimento
	 */
	public Date getFldDataNascimento() {
		return fldDataNascimento;
	}

	/**
	 * @param fldDataNascimento the fldDataNascimento to set
	 */
	public void setFldDataNascimento(Date fldDataNascimento) {
		this.fldDataNascimento = fldDataNascimento;
	}

	/**
	 * @return the fldSexo
	 */
	public Sexo getFldSexo() {
		return fldSexo;
	}

	/**
	 * @param fldSexo the fldSexo to set
	 */
	public void setFldSexo(Sexo fldSexo) {
		this.fldSexo = fldSexo;
	}

	/**
	 * @return the fldEstadoCivil
	 */
	public EstadoCivil getFldEstadoCivil() {
		return fldEstadoCivil;
	}

	/**
	 * @param fldEstadoCivil the fldEstadoCivil to set
	 */
	public void setFldEstadoCivil(EstadoCivil fldEstadoCivil) {
		this.fldEstadoCivil = fldEstadoCivil;
	}

	/**
	 * @return the fldEnderecoLogradouro
	 */
	public String getFldEnderecoLogradouro() {
		return fldEnderecoLogradouro;
	}

	/**
	 * @param fldEnderecoLogradouro the fldEnderecoLogradouro to set
	 */
	public void setFldEnderecoLogradouro(String fldEnderecoLogradouro) {
		this.fldEnderecoLogradouro = fldEnderecoLogradouro;
	}

	/**
	 * @return the fldEnderecoNumero
	 */
	public String getFldEnderecoNumero() {
		return fldEnderecoNumero;
	}

	/**
	 * @param fldEnderecoNumero the fldEnderecoNumero to set
	 */
	public void setFldEnderecoNumero(String fldEnderecoNumero) {
		this.fldEnderecoNumero = fldEnderecoNumero;
	}

	/**
	 * @return the fldEnderecoComplemento
	 */
	public String getFldEnderecoComplemento() {
		return fldEnderecoComplemento;
	}

	/**
	 * @param fldEnderecoComplemento the fldEnderecoComplemento to set
	 */
	public void setFldEnderecoComplemento(String fldEnderecoComplemento) {
		this.fldEnderecoComplemento = fldEnderecoComplemento;
	}

	/**
	 * @return the fldEnderecoBairro
	 */
	public String getFldEnderecoBairro() {
		return fldEnderecoBairro;
	}

	/**
	 * @param fldEnderecoBairro the fldEnderecoBairro to set
	 */
	public void setFldEnderecoBairro(String fldEnderecoBairro) {
		this.fldEnderecoBairro = fldEnderecoBairro;
	}

	/**
	 * @return the fldEnderecoUF
	 */
	public Estado getFldEnderecoUF() {
		return fldEnderecoUF;
	}

	/**
	 * @param fldEnderecoUF the fldEnderecoUF to set
	 */
	public void setFldEnderecoUF(Estado fldEnderecoUF) {
		this.fldEnderecoUF = fldEnderecoUF;
	}

	/**
	 * @return the fldEnderecoMunicipio
	 */
	public String getFldEnderecoMunicipio() {
		return fldEnderecoMunicipio;
	}

	/**
	 * @param fldEnderecoMunicipio the fldEnderecoMunicipio to set
	 */
	public void setFldEnderecoMunicipio(String fldEnderecoMunicipio) {
		this.fldEnderecoMunicipio = fldEnderecoMunicipio;
	}

	/**
	 * @return the fldTelefoneFixo
	 */
	public String getFldTelefoneFixo() {
		return fldTelefoneFixo;
	}

	/**
	 * @param fldTelefoneFixo the fldTelefoneFixo to set
	 */
	public void setFldTelefoneFixo(String fldTelefoneFixo) {
		this.fldTelefoneFixo = fldTelefoneFixo;
	}

	/**
	 * @return the fldTelefoneCelular
	 */
	public String getFldTelefoneCelular() {
		return fldTelefoneCelular;
	}

	/**
	 * @param fldTelefoneCelular the fldTelefoneCelular to set
	 */
	public void setFldTelefoneCelular(String fldTelefoneCelular) {
		this.fldTelefoneCelular = fldTelefoneCelular;
	}

	/**
	 * @return the fldTelefoneRecado
	 */
	public String getFldTelefoneRecado() {
		return fldTelefoneRecado;
	}

	/**
	 * @param fldTelefoneRecado the fldTelefoneRecado to set
	 */
	public void setFldTelefoneRecado(String fldTelefoneRecado) {
		this.fldTelefoneRecado = fldTelefoneRecado;
	}

	/**
	 * @return the fldEmail
	 */
	public String getFldEmail() {
		return fldEmail;
	}

	/**
	 * @param fldEmail the fldEmail to set
	 */
	public void setFldEmail(String fldEmail) {
		this.fldEmail = fldEmail;
	}

	/**
	 * @return the fldPkUserPermission
	 */
	public Long getFldPkUserPermission() {
		return fldPkUserPermission;
	}

	/**
	 * @param fldPkUserPermission the fldPkUserPermission to set
	 */
	public void setFldPkUserPermission(Long fldPkUserPermission) {
		this.fldPkUserPermission = fldPkUserPermission;
	}

	/**
	 * @return the fldAutorizaEmailNovidadeUeg
	 */
	public Boolean getFldAutorizaEmailNovidadeUeg() {
		return fldAutorizaEmailNovidadeUeg;
	}

	/**
	 * @param fldAutorizaEmailNovidadeUeg the fldAutorizaEmailNovidadeUeg to set
	 */
	public void setFldAutorizaEmailNovidadeUeg(Boolean fldAutorizaEmailNovidadeUeg) {
		this.fldAutorizaEmailNovidadeUeg = fldAutorizaEmailNovidadeUeg;
	}

	/**
	 * @return the fldAutorizaEmailOportundiade
	 */
	public Boolean getFldAutorizaEmailOportundiade() {
		return fldAutorizaEmailOportundiade;
	}

	/**
	 * @param fldAutorizaEmailOportundiade the fldAutorizaEmailOportundiade to set
	 */
	public void setFldAutorizaEmailOportundiade(Boolean fldAutorizaEmailOportundiade) {
		this.fldAutorizaEmailOportundiade = fldAutorizaEmailOportundiade;
	}

	/**
	 * @return the fldAutorizaEmailPublicacao
	 */
	public Boolean getFldAutorizaEmailPublicacao() {
		return fldAutorizaEmailPublicacao;
	}

	/**
	 * @param fldAutorizaEmailPublicacao the fldAutorizaEmailPublicacao to set
	 */
	public void setFldAutorizaEmailPublicacao(Boolean fldAutorizaEmailPublicacao) {
		this.fldAutorizaEmailPublicacao = fldAutorizaEmailPublicacao;
	}

	/**
	 * @return the fldAutorizaParceiroVerTelefone
	 */
	public Boolean getFldAutorizaParceiroVerTelefone() {
		return fldAutorizaParceiroVerTelefone;
	}

	/**
	 * @param fldAutorizaParceiroVerTelefone the fldAutorizaParceiroVerTelefone to set
	 */
	public void setFldAutorizaParceiroVerTelefone(
			Boolean fldAutorizaParceiroVerTelefone) {
		this.fldAutorizaParceiroVerTelefone = fldAutorizaParceiroVerTelefone;
	}
	
	/**
	 * @return the academicoChaveBusca
	 */
	public String getAcademicoChaveBusca() {
		return academicoChaveBusca;
	}

	/**
	 * @param academicoChaveBusca the academicoChaveBusca to set
	 */
	public void setAcademicoChaveBusca(String academicoChaveBusca) {
		this.academicoChaveBusca = academicoChaveBusca;
	}
	
	/**
	 * @see br.edu.aee.UniArch.structure.view.ZK.SuperViewZK#newControlInstance()
	 */
	@Override
	protected CadastroAcademicoControler newControlInstance() {
		return SpringFactory.getBean("cadastroAcademicoControler", CadastroAcademicoControler.class);
	}

	public void procurarAcademico(){
		this.doAction("procuraracademico");
		/*ActionReturn<?,?> actionReturn = getViewController().save();
		if (!actionReturn.isSuccess()) {
			showMessage(actionReturn);
		} else {
			cleanFields();
			showMessage(actionReturn);
			super.binder.loadAll();
		}*/
	}
	

}
