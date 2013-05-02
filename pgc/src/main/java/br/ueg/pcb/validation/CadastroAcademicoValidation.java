package br.ueg.pcb.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.Validator;

import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.annotation.UseCase;
import br.edu.aee.UniArch.annotation.ValidatorMethod;
import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.domain.GenericActionReturn;
import br.edu.aee.UniArch.enums.ReturnTypeEnum;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.interfaces.IValidator;
import br.edu.aee.UniArch.subsystems.validation.SuperValidator;
import br.edu.aee.UniArch.utils.ConfigurationProperties;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.service.AcademicoService;
import br.ueg.pcb.utils.CheckValidEMail;


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
			AcademicoService academicoService = getAcademicoService(); 
			if(erroList.isEmpty() && !academicoService.existsUegAcademico(tipoBusca, (String) getAttributeValue("academicoChaveBusca"))){
				erroList.add(mensagens.getValue("CadastroAcademico.procuraracademico.academicoNaoExiste"));
			}
			
			//TODO passar para metodo
			UegAcademico ua = academicoService.getUegAcademico(tipoBusca, getValueOrAttributeValue("academicoChaveBusca"));
			if(ua!=null){
				if(academicoService.existsAcademicoByUegAcademico(ua)){
					erroList.add(mensagens.getValue("CadastroAcademico.procuraracademico.academicoExiste"));
				}
			}
			
			if(!erroList.isEmpty()){
				actionReturn.reportFailure(ReturnTypeEnum.ERROR, erroList);
			}
		} else {
			
		}
		return actionReturn;
	}

	/**
	 * @return
	 */
	private AcademicoService getAcademicoService() {
		AcademicoService academicoService = (AcademicoService) SpringFactory.loadService(Academico.class);
		return academicoService;
	}
	
	@ValidatorMethod(action=IValidator.SAVE_ACTION, order=0)
	public ActionReturn<?, ?> validateCadastrarAcademico(String Action, String attributeName){
		ActionReturn<?, ?> actionReturn = new GenericActionReturn();
		
		onBlurValidateFieldEmailExists("email",actionReturn);
		
		return actionReturn;
	}
	
	/** metodo para validas os campos da tela de busca quando perde o foco
	 * @param action
	 * @param attributeName
	 * @return
	 */
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
			String value = getValueOrAttributeValue(attributeName);
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
	/** metodo para validas os campos da tela de cadastro quando perde o foco
	 * @param action
	 * @param attributeName
	 * @return
	 */
	@ValidatorMethod(action="CadastrarAcademico", order=0)//validação de atributos com onblur(por isso usa o nome do cenário
	public ActionReturn<?, ?> validateCadastrarAcademicoFieldsOnBlur(String action, String attributeName){
		ActionReturn<?, ?> actionReturn = new GenericActionReturn();
		
		//valida campos obrigatórios
		if (this.getAllMandatoryFields().contains(attributeName)) {
			String unfilledFieldMessage = this.generateEmptyMessageForAttribute(attributeName, this.getValue());
			if (unfilledFieldMessage != null) {
				actionReturn.reportFailure(ReturnTypeEnum.WARNING, Arrays.asList(unfilledFieldMessage));
			}
			
		}		
		if(!actionReturn.isSuccess()) return actionReturn;
		
		//valida formato do email
		this.onBlurValidateFieldEmailFormat(attributeName, actionReturn);
		if(!actionReturn.isSuccess()) return actionReturn;
		
		//valida confirmação da senha
		onBlurValidateFieldConfirmaSenha(attributeName,actionReturn);
		if(!actionReturn.isSuccess()) return actionReturn;

		//validar se o email já existe
		onBlurValidateFieldEmailExists(attributeName,actionReturn);
		
		
		return actionReturn;
	}
	
	/**
	 * Valida o campo email ao sair do campo
	 * @param attributeName
	 * @param actionReturn
	 */
	private void onBlurValidateFieldEmailExists(String attributeName,
			ActionReturn<?, ?> actionReturn) {
		//valida atributo academicoChave de busca.
		if (attributeName != null && attributeName.equalsIgnoreCase("email")) {
			String value = getValueOrAttributeValue(attributeName);
			String unfilledFieldMessage = this.generateEmptyMessageForAttribute(attributeName, this.getValue());
			//valida o formato do e-mail apenas se estiver preenchido
			if (unfilledFieldMessage ==null){
				AcademicoService academicoService = getAcademicoService();
				if(attributeName.equalsIgnoreCase("email") && academicoService.existsAcademicoEmail(value)){
					actionReturn.reportFailure(ReturnTypeEnum.WARNING,Arrays.asList(
							ConfigurationProperties.getInstance().getValue("CadastroAcademico.CadastrarAcademico.email.existe")
							));					
				}
			}
		}
	}
	
	/**
	 * Valida o campo email ao sair do campo
	 * @param attributeName
	 * @param actionReturn
	 */
	private void onBlurValidateFieldEmailFormat(String attributeName,
			ActionReturn<?, ?> actionReturn) {
		//valida atributo academicoChave de busca.
		if (attributeName != null && attributeName.equalsIgnoreCase("email")) {
			String value = getValueOrAttributeValue(attributeName);
			String unfilledFieldMessage = this.generateEmptyMessageForAttribute(attributeName, this.getValue());
			//valida o formato do e-mail apenas se estiver preenchido
			if (unfilledFieldMessage ==null){
				if(attributeName.equalsIgnoreCase("email") && !CheckValidEMail.validar(value)){
					actionReturn.reportFailure(ReturnTypeEnum.WARNING,Arrays.asList(
							ConfigurationProperties.getInstance().getValue("CadastroAcademico.CadastrarAcademico.email.formato_invalido")
							));					
				}
			}else{
				actionReturn.reportFailure(ReturnTypeEnum.WARNING, Arrays.asList(unfilledFieldMessage));
			}
		}
	}

	/**
	 * @param attributeName
	 * @return
	 */
	private String getValueOrAttributeValue(String attributeName) {
		String value = (String) getValue();
		value = value!=null ? value :(String) getAttributeValue(attributeName);
		return value;
	}
	
	/** valida o campo confirma senha para ver se é igual ao campo senha 
	 * @param attributeName
	 * @param actionReturn
	 */
	private void onBlurValidateFieldConfirmaSenha(String attributeName, ActionReturn<?, ?> actionReturn){
		if(attributeName.equalsIgnoreCase("confirmaSenha")){
			String senha = (String) getAttributeParameter("senha");
			senha=senha!=null?senha:"";
			String confirmaSenha = getValueOrAttributeValue(attributeName);
			confirmaSenha=confirmaSenha!=null?confirmaSenha:"";
			if (!senha.equals(confirmaSenha)){
				actionReturn.reportFailure(ReturnTypeEnum.WARNING,Arrays.asList(
						ConfigurationProperties.getInstance().getValue("CadastroAcademico.CadastrarAcademico.senha_confirma_diferente_senh")
						));		
			}
		}
	}
}
