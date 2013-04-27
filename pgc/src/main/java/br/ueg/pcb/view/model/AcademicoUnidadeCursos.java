package br.ueg.pcb.view.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.aee.UniArch.structure.model.SingleEntity;
import br.ueg.pcb.model.CursosAcademico;
import br.ueg.pcb.model.Unidade;

public class AcademicoUnidadeCursos extends SingleEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6396281231384402517L;
	
	
	private Unidade unidade;
	private List<CursosAcademico> cursos = new ArrayList<CursosAcademico>(0);
	
	public AcademicoUnidadeCursos(Unidade unidade){
		this.unidade = unidade;
	}

	/**
	 * @return the cursos
	 */
	public List<CursosAcademico> getCursos() {
		return cursos;
	}

	/**
	 * @param cursos the cursos to set
	 */
	public void setCursos(List<CursosAcademico> cursos) {
		this.cursos = cursos;
	}

	/**
	 * @return the unidade
	 */
	public Unidade getUnidade() {
		return unidade;
	}
}
