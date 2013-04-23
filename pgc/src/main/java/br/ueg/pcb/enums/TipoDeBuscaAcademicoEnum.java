package br.ueg.pcb.enums;

import br.edu.aee.UniArch.enums.AuthenticationTypeEnum;

public enum TipoDeBuscaAcademicoEnum {
	CPF, 
	MATRICULA;
	
	public static TipoDeBuscaAcademicoEnum getTipoDeBuscaAcademico(String keyType) {
		for (TipoDeBuscaAcademicoEnum tipoDeBuscaAcademico : TipoDeBuscaAcademicoEnum.values()) {
			if (tipoDeBuscaAcademico.toString().equals(keyType.toUpperCase())) {
				return tipoDeBuscaAcademico;
			}
		}
		return null;
	}
}
