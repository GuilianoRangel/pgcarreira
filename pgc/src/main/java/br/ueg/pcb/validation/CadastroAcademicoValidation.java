package br.ueg.pcb.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.annotation.UseCase;
import br.edu.aee.UniArch.annotation.ValidatorMethod;
import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.domain.GenericActionReturn;
import br.edu.aee.UniArch.enums.ReturnTypeEnum;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.subsystems.validation.SuperValidator;
import br.edu.aee.UniArch.utils.ConfigurationProperties;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.service.AcademicoService;


@Controller
@UseCase(value="CadastroAcademico")
public class CadastroAcademicoValidation extends SuperValidator {
	
	@ValidatorMethod(action="procuraracademico", order=0)//validação das ações
	public ActionReturn<?, ?> validateProcuraracademico(String action, String attributeName) {
		ActionReturn<?, ?> actionReturn = new GenericActionReturn();
		List<String> erroList = new ArrayList<String>();
		if (attributeName == null) {
			
			ConfigurationProperties mensagens = ConfigurationProperties.getInstance();
			
			String tipoBusca = (String)getAttributeValue("tipoBusca");
			if(tipoBusca==null || tipoBusca.equals("")){
				erroList.add(mensagens.getValue("CadastroAcademico.procuraracademico.tipoBusca.incorreto"));
			}
			
			onBlurValidateFieldAcademicoChaveBusca("academicoChaveBusca",actionReturn);
			if(!actionReturn.isSuccess()){
				erroList.add(actionReturn.getErrorMessages().get(0));
			}
			
			
			
			//validar se o aluno existe
			AcademicoService academicoService = (AcademicoService) SpringFactory.loadService(Academico.class); 
			if(erroList.isEmpty() && !academicoService.existsUegAcademico(tipoBusca, (String) getAttributeValue("academicoChaveBusca"))){
				erroList.add(mensagens.getValue("CadastroAcademico.procuraracademico.academicoNaoExiste"));
			}
			
			if(!erroList.isEmpty()){
				actionReturn.reportFailure(ReturnTypeEnum.ERROR, erroList);
			}
		} else {
			
		}
		return actionReturn;
	}
	@ValidatorMethod(action="CadastrarAcademicoBusca", order=0)//validação de atributos com onblur(por isso usa o nome do cenário
	public ActionReturn<?, ?> validateCadastrarAcademicoBuscaFieldsOnBlur(String action, String attributeName){
		ActionReturn<?, ?> actionReturn = new GenericActionReturn();
		
		onBlurValidateFieldAcademicoChaveBusca(attributeName, actionReturn);
		if(!actionReturn.isSuccess()) return actionReturn;
		
		
		return actionReturn;
	}
	/**
	 * Valida o campo academicoChaveDeBusca ao sair do campo
	 * @param attributeName
	 * @param actionReturn
	 */
	private void onBlurValidateFieldAcademicoChaveBusca(String attributeName,
			ActionReturn<?, ?> actionReturn) {
		//valida atributo academicoChave de busca.
		if (attributeName != null && attributeName.equals("academicoChaveBusca")) {
			String value = (String) getValue();
			value = value!=null ? value :(String) getAttributeValue(attributeName);
			if(value==null || value.equals("")){
				actionReturn.reportFailure(
					ReturnTypeEnum.ERROR,  
					Arrays.asList(
							ConfigurationProperties.getInstance().getValue("CadastroAcademico.CadastrarAcademico.academicoChaveBusca.obrigatorio")
							)
				);
			}
		}
	}

	/* (non-Javadoc)
	 * @see br.edu.aee.UniArch.subsystems.validation.SuperValidator#validateAttributeNotMandatory(java.lang.String, java.lang.String)
	 */
	@Override
	protected ActionReturn<?, ?> validateAttributeNotMandatory(String action,
			String attributeName) {
		ActionReturn<?, ?> actionReturn = new GenericActionReturn();
		//valida o atributo 
		if ( action.equals("procuraracademico") && attributeName!=null &&  attributeName.equals("academicoChaveBusca")  ) {
			String unfilledFieldMessage = this.generateEmptyMessageForAttribute(attributeName, this.getValue());
			if (unfilledFieldMessage != null) {
				actionReturn.reportFailure(ReturnTypeEnum.WARNING, Arrays.asList(unfilledFieldMessage));
			}
		}
		return actionReturn;
	}
	
}
