package br.ueg.pcb.model.pks;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.edu.aee.UniArch.annotation.SearchableField;
import br.edu.aee.UniArch.structure.interfaces.ICompositePK;
import br.ueg.pcb.model.Curso;
import br.ueg.pcb.model.UegAcademico;

@SuppressWarnings("serial")
public class CursosAcademicoPK implements ICompositePK {

	@SearchableField(description="UegAcadêmico")
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="matricula_acad_cuac")
	private UegAcademico uegAcademico;
	
	@SearchableField(description="Curso")
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="id_curs_cuac")//, referencedColumnName = "id_curs_cuac")
	private Curso curso;
	
	@SearchableField(description="AnoIngresso")
	@Column(name = "ano_ingresso_cuac", length=10, nullable = false)
	private String anoIngresso;	
	
	public CursosAcademicoPK(){		
	}

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
	 * @return the curso
	 */
	public Curso getCurso() {
		return curso;
	}

	/**
	 * @param curso the curso to set
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	/**
	 * @return the anoIngresso
	 */
	public String getAnoIngresso() {
		return anoIngresso;
	}

	/**
	 * @param anoIngresso the anoIngresso to set
	 */
	public void setAnoIngresso(String anoIngresso) {
		this.anoIngresso = anoIngresso;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.uegAcademico == null) ? 0 : this.uegAcademico.hashCode());
		result = prime * result + ((this.curso == null) ? 0 : this.curso.hashCode() );
		result = prime * result + ((this.anoIngresso == null) ? 0 : this.anoIngresso.hashCode() );
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CursosAcademicoPK other = (CursosAcademicoPK) obj;
		if (this.uegAcademico == null) {
			if (other.uegAcademico != null)
				return false;
		} else if (!uegAcademico.equals(other.uegAcademico)){
			return false;
		};
		if(curso==null ){
			if(other.curso != null){
				return false;
			}			
		}else if(!curso.equals(other.curso)){
			return false;
		}
		if(anoIngresso==null ){
			if(other.anoIngresso != null){
				return false;
			}			
		}else if(!anoIngresso.equals(other.anoIngresso)){
			return false;
		}
		
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CursosAcademicoPK [UegAcademico=" + uegAcademico + ", Curso=" + curso
				+ ", AnoIngresso="+anoIngresso+"]";
	}
	
}
