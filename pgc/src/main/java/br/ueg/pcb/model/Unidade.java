package br.ueg.pcb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.aee.UniArch.annotation.SearchableField;
import br.edu.aee.UniArch.annotation.VisibleEntityName;
import br.edu.aee.UniArch.structure.interfaces.ISessionParameterEntity;
import br.edu.aee.UniArch.structure.model.Entity;

/**
 * @author Guiliano
 *
 */
@SuppressWarnings("serial")
@VisibleEntityName(value="Unidade")
@javax.persistence.Entity
@Table(name="ueg_unidade")
public class Unidade extends Entity<String>  implements ISessionParameterEntity{
		
	@Id
	@SearchableField(description="id_unid")
	@Column(name = "id_unid", length=10, nullable = false)
	private String pk;
	
	@SearchableField(description="Nome Unidade")
	@Column(name = "nome_unid", length=100, nullable = false)
	private String nome;
	
	@SearchableField(description="Sigla Unidade")
	@Column(name = "sigla_unid", length=20, nullable = true)
	private String sigla;
	
	@SearchableField(description="Diretor Unidade")
	@Column(name = "diretor_unid", length=100, nullable = true)
	private String diretor;
	
	@Column(name = "end_logradouro_unid", length=500, nullable = true)
	private String enderecoLogradouro;
	
	@Column(name = "end_bairro_unid", length=100, nullable = true)
	private String enderecoBairro;
	
	@Column(name = "end_cep_unid", length=10, nullable = true)
	private String enderecoCep;
	
	@Column(name = "end_cidade_unid", length=100, nullable = true)
	private String enderecoCidade;
	
	@Column(name = "telefone_unid", length=50, nullable = true)
	private String telefone;
	
	@Column(name = "email_unid", length=100, nullable = true)
	private String email;
	
	@Column(name = "website_unid", length=200, nullable = true)
	private String website;
	
	@Override
	public String getPk() {
		return this.pk;
	}

	@Override
	public void setPk(String pk) {
		this.pk = pk;
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
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the diretor
	 */
	public String getDiretor() {
		return diretor;
	}

	/**
	 * @param diretor the diretor to set
	 */
	public void setDiretor(String diretor) {
		this.diretor = diretor;
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
	 * @return the enderecoCep
	 */
	public String getEnderecoCep() {
		return enderecoCep;
	}

	/**
	 * @param enderecoCep the enderecoCep to set
	 */
	public void setEnderecoCep(String enderecoCep) {
		this.enderecoCep = enderecoCep;
	}

	/**
	 * @return the enderecoCidade
	 */
	public String getEnderecoCidade() {
		return enderecoCidade;
	}

	/**
	 * @param enderecoCidade the enderecoCidade to set
	 */
	public void setEnderecoCidade(String enderecoCidade) {
		this.enderecoCidade = enderecoCidade;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return this.getNome();
	}

	@Override
	public String getTooltiptext() {
		// TODO Auto-generated method stub
		return "Unidade Universitária do aluno";
	}

}
