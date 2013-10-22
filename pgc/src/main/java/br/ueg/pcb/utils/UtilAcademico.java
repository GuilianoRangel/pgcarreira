package br.ueg.pcb.utils;

import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.enums.ReturnTypeEnum;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.interfaces.ISuperView;
import br.edu.aee.UniArch.structure.model.UserPermission;
import br.edu.aee.UniArch.utils.ConfigurationProperties;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.service.AcademicoService;

public class UtilAcademico {
	/**
	 * Metodo retorna um ActionReturn com o Parametro ActionReturn.ENTITY_PARAMTER com o Academico Logado, caso não tenha um academico logado 
	 * ele retorna um ActionReturn com reportFailure e dados para o composer
	 * redirecionar para a página de login.
	 * @param view
	 * @return
	 */
	public static ActionReturn<String, Academico> getAcademicoLogged(ISuperView view){
		ActionReturn<String, Academico> actionReturn = new ActionReturn<String, Academico>();
		UserPermission up = (UserPermission)view.getUserLogged();
		if(up==null){
			String loginPage = ConfigurationProperties.getInstance().getPropertyOrDefault("SECURITY_LOGIN_PAGE");
			actionReturn.reportFailure(ReturnTypeEnum.ERROR);
			actionReturn.addExtra(ActionReturn.NEXT_USE_CASE,loginPage);
			return actionReturn;
		}		
		Academico academico = getAcademicoService().getAcademicoByUserPermission(up);
		actionReturn.addParameter(ActionReturn.ENTITY_PARAMETER, academico);
		
		return actionReturn;
	}
	
	public static AcademicoService getAcademicoService(){
		return SpringFactory.getBean("academicoService",AcademicoService.class);
	}
}
