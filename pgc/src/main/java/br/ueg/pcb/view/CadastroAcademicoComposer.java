package br.ueg.pcb.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Executions;

import br.edu.aee.UniArch.annotation.AttributeView;
import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.interfaces.ISecurityView;
import br.edu.aee.UniArch.structure.model.UserPermission;
import br.edu.aee.UniArch.structure.view.ZK.CRUDViewZK;
import br.edu.aee.UniArch.utils.ConfigurationProperties;
import br.ueg.pcb.controller.CadastroAcademicoControler;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.CursosAcademico;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.model.Unidade;
import br.ueg.pcb.model.assist.Estado;
import br.ueg.pcb.model.assist.EstadoCivil;
import br.ueg.pcb.model.assist.Sexo;
import br.ueg.pcb.view.model.AcademicoUnidadeCursos;

@SuppressWarnings({ "serial" })
@Scope(value="session")
@org.springframework.stereotype.Component
public class CadastroAcademicoComposer extends CRUDViewZK<CadastroAcademicoControler, Academico, Long> {
	
	@AttributeView(attributeName="tipoBusca", isEntityField=false)
	private String tipoBusca;
	
	@AttributeView(attributeName="academicoChaveBusca", isEntityField=false)
	private String academicoChaveBusca;
	
	@AttributeView(attributeName="casoDeUsoCenario",isEntityField=false)
	private String casoDeUsoCenario="CadastrarAcademicoBusca";
	
	@AttributeView(attributeName="uegAcademico" , isEntityField=true)
	private UegAcademico fldUegAcademico;
	
	@AttributeView(attributeName="nomeMae" , isEntityField=true)
	private String fldMae;
	
	@AttributeView(attributeName="identidadeNumero" , isEntityField=true)
	private String fldIdentidadeNumero;
	
	@AttributeView(attributeName="identidadeOrgao" , isEntityField=true)
	private String fldIdentidadeOrgao;
	
	@AttributeView(attributeName="identidadeUf" , isEntityField=true)
	private Estado fldIdentidadeUf;
	
	@AttributeView(attributeName="dataNascimento" , isEntityField=true)
	private Date fldDataNascimento;
	
	@AttributeView(attributeName="sexo" , isEntityField=true)
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
	
	@AttributeView(attributeName="enderecoPais" , isEntityField=true)
	private String fldEnderecoPais;
	
	@AttributeView(attributeName="enderecoMunicipio" , isEntityField=true)
	private String fldEnderecoMunicipio;
	
	@AttributeView(attributeName="telefoneFixo" , isEntityField=true)
	private String fldTelefoneFixo;
	
	@AttributeView(attributeName="telefoneCelular" , isEntityField=true)
	private String fldTelefoneCelular;
	
	@AttributeView(attributeName="telefoneRecado" , isEntityField=true)
	private String fldTelefoneRecado;
	
	@AttributeView(attributeName="email" , isEntityField=true)
	private String email;
	
	@AttributeView(attributeName="pkUserPermission" , isEntityField=true)
	private Long fldPkUserPermission;
	
	@AttributeView(attributeName="autorizaEmailNovidadeUeg" , isEntityField=true)
	private Boolean fldAutorizaEmailNovidadeUeg;	
	
	@AttributeView(attributeName="autorizaEmailOportundiade" , isEntityField=true)
	private Boolean fldAutorizaEmailOportunidade;
	
	@AttributeView(attributeName="autorizaEmailPublicacao" , isEntityField=true)
	private Boolean fldAutorizaEmailPublicacao;
	
	@AttributeView(attributeName="autorizaParceiroVerTelefone" , isEntityField=true)
	private Boolean fldAutorizaParceiroVerTelefone;
	
	@AttributeView(attributeName="senha" , isEntityField=false)
	private String fldSenha;
	
	@AttributeView(attributeName="confirmaSenha" , isEntityField=false)
	private String fldConfirmaSenha;

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
	public Estado getFldIdentidadeUf() {
		return fldIdentidadeUf;
	}

	/**
	 * @param fldIdentidadeUf the fldIdentidadeUf to set
	 */
	public void setFldIdentidadeUf(Estado fldIdentidadeUf) {
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
	 * @return the fldEnderecoPais
	 */
	public String getFldEnderecoPais() {
		return fldEnderecoPais;
	}

	/**
	 * @param fldEnderecoPais the fldEnderecoPais to set
	 */
	public void setFldEnderecoPais(String fldEnderecoPais) {
		this.fldEnderecoPais = fldEnderecoPais;
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
	public String getEmail() {
		return email;
	}

	/**
	 * @param fldEmail the fldEmail to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	public Boolean getFldAutorizaEmailOportunidade() {
		return fldAutorizaEmailOportunidade;
	}

	/**
	 * @param fldAutorizaEmailOportundiade the fldAutorizaEmailOportundiade to set
	 */
	public void setFldAutorizaEmailOportunidade(Boolean fldAutorizaEmailOportunidade) {
		this.fldAutorizaEmailOportunidade = fldAutorizaEmailOportunidade;
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
	 * @return the tipoBusca
	 */
	public String getTipoBusca() {
		return tipoBusca;
	}

	/**
	 * @param tipoBusca the tipoBusca to set
	 */
	public void setTipoBusca(String tipoBusca) {
		this.tipoBusca = tipoBusca;
	}

	/**
	 * @return the fldSenha
	 */
	public String getFldSenha() {
		return fldSenha;
	}

	/**
	 * @param fldSenha the fldSenha to set
	 */
	public void setFldSenha(String fldSenha) {
		this.fldSenha = fldSenha;
	}

	/**
	 * @return the fldConfirmaSenha
	 */
	public String getFldConfirmaSenha() {
		return fldConfirmaSenha;
	}

	/**
	 * @param fldConfirmaSenha the fldConfirmaSenha to set
	 */
	public void setFldConfirmaSenha(String fldConfirmaSenha) {
		this.fldConfirmaSenha = fldConfirmaSenha;
	}

	/**
	 * @return the casoDeUsoCenario
	 */
	public String getCasoDeUsoCenario() {
		return casoDeUsoCenario;
	}

	/**
	 * @param casoDeUsoCenario the casoDeUsoCenario to set
	 */
	public void setCasoDeUsoCenario(String casoDeUsoCenario) {
		this.casoDeUsoCenario = casoDeUsoCenario;
	}

	/**
	 * @see br.edu.aee.UniArch.structure.view.ZK.SuperViewZK#newControlInstance()
	 */
	@Override
	protected CadastroAcademicoControler newControlInstance() {
		return SpringFactory.getBean("cadastroAcademicoControler", CadastroAcademicoControler.class);
	}
	

	
	/**
	 * @param actionReturn
	 */
	private void redirectNexUseCase(ActionReturn<?, ?> actionReturn) {
		if(actionReturn.isSuccess()){
			String nextUseCase = (String) actionReturn.getExtra("nextUseCase");
			if(nextUseCase!=null && !nextUseCase.equals("")){
				getViewController().setEntityFromView(this.getViewController().getSelectedAcademico());
				Executions.sendRedirect(nextUseCase);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void procurarAcademico(){
		setShowSuccessMessage(false);
		ActionReturn<String, Object> actionReturn =  (ActionReturn<String, Object>) this.doAction("procuraracademico");
		redirectNexUseCase(actionReturn);
		setShowSuccessMessage(true);
	}
	
	
	/* (non-Javadoc)
	 * @see br.edu.aee.UniArch.structure.view.ZK.CRUDViewZK#edit()
	 */
	@Override
	public void edit() {
		//TODO ver questao de verificar sessao, procurar mecanimsmo para o controlador saber o usuário logado.
		UserPermission up = (UserPermission)this.session.getAttribute(ISecurityView.USER_KEY);
		if(up==null){
			String sucessPage = ConfigurationProperties.getInstance().getPropertyOrDefault("SECURITY_LOGIN_PAGE");
			Executions.sendRedirect(sucessPage);
		}
		
		Academico academico = this.getViewController().getAcademicoByUserPermission(up);
		System.out.println(academico.getEnderecoUF());
		this.getViewController().setSelectedAcademico(academico);
		this.setSelectedEntity(academico);
		
		this.setCasoDeUsoCenario("EditarAcademico");
		super.edit();
		Executions.sendRedirect( ConfigurationProperties.getInstance().getValue("view.CadastroAcademico.cadastro2"));
	}

	/* (non-Javadoc)
	 * @see br.edu.aee.UniArch.structure.view.ZK.CRUDViewZK#record()
	 */
	@Override
	public ActionReturn<String, Academico> record() {
		ActionReturn<String, Academico> actionReturn =   super.record();
		redirectNexUseCase(actionReturn);
		return actionReturn;
	}

	public List<Unidade> getListUnidade(){		
		return getCadastroAcademicoControler().getListUnidadeDoAcademico();
	}

	/** metodo para retornar o controler tipado
	 * @return
	 */
	private CadastroAcademicoControler getCadastroAcademicoControler() {
		return (CadastroAcademicoControler)this.getViewController();
	}
	
	public List<AcademicoUnidadeCursos> getListCursosAcademico(){
		List<CursosAcademico> cursosAcademicos = this.getCadastroAcademicoControler().getListCursosAcademico();
		if (cursosAcademicos==null) return null;
		
		Unidade unidade = new Unidade();
		unidade.setPk("0");//so para controlar o primeiro diferencte
		AcademicoUnidadeCursos ant = new AcademicoUnidadeCursos(unidade);
		List<AcademicoUnidadeCursos> resultList =new ArrayList<AcademicoUnidadeCursos>();	
		
		for(CursosAcademico cursoAcademico: cursosAcademicos){
			if(ant.getUnidade()!=null && ant.getUnidade().getPk().equals(cursoAcademico.getPk().getCurso().getUnidade().getPk())){
				ant.getCursos().add(cursoAcademico);				
			}else {	
				AcademicoUnidadeCursos cursosAcademico = new AcademicoUnidadeCursos(cursoAcademico.getPk().getCurso().getUnidade());
				cursosAcademico.getCursos().add(cursoAcademico);
				resultList.add(cursosAcademico);
				ant = cursosAcademico;
			}		
		}
		
		return resultList;
	}
	
	public List<Sexo> getListSexo(){
		return this.getCadastroAcademicoControler().getListEntityTabelaBasica(Sexo.class);
	}
	
	public List<EstadoCivil> getListEstadoCivil(){
		return this.getCadastroAcademicoControler().getListEntityTabelaBasica(EstadoCivil.class);
	}
	public List<Estado> getListUF(){
		return this.getCadastroAcademicoControler().getListEntityTabelaBasica(Estado.class);
	}

}
