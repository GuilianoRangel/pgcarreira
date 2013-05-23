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
			UegAcademico ua = academicoService.getUegAcademico(tipoBusca, (String)getValueOrAttributeValue("academicoChaveBusca"));
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
	
	/* (non-Javadoc)
	 * @see br.edu.aee.UniArch.subsystems.validation.SuperValidator#validateSave(java.lang.String, java.lang.String)
	 */
	@Override
	@ValidatorMethod(action = "SAVE_ACTION", order = 0)
	public ActionReturn<?, ?> validateSave(String action, String attributeName) {
		ActionReturn<?, ?> actionReturn = super.validateSave(action, attributeName);
		
		//valida se o e-mail já existe.
		onBlurValidateFieldEmailExists(actionReturn,(String) this.getAttributeValue("email"),(Long) getAttributeValue("pk"));
		if(!actionReturn.isSuccess()){ 
			return actionReturn; 
		}
		
		//valida campo completo e numero
		if(!validateComplementNumero(action)){
			actionReturn.reportFailure(ReturnTypeEnum.WARNING,Arrays.asList(
					ConfigurationProperties.getInstance().getValue("CadastroAcademico.CadastrarAcademico.complemento.numero.vazio")
					));	
		}
		
		//valida senha
		String confirmaSenha = (String) getAttributeParameter("confirmaSenha"); 		confirmaSenha=confirmaSenha!=null?confirmaSenha:"";		
		String senha = (String) getAttributeParameter("senha");		senha=senha!=null?senha:"";
		onBlurValidateFieldConfirmaSenha(actionReturn,confirmaSenha,senha);
		if(!actionReturn.isSuccess()) return actionReturn;
		
		return actionReturn;
	}
	
	

	private boolean validateComplementNumero(String action){
		String comp = (String)getAttributeValue("enderecoComplemento");
		String numero =(String) getAttributeValue("enderecoNumero");
		if( 
				(comp!=null && comp.isEmpty()) && 
				(numero!=null && numero.isEmpty()) 
			){
			return false;
		}
			
		return true;
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
			String value = (String)getValueOrAttributeValue(attributeName);
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
		if(attributeName!=null && !attributeName.isEmpty()){
			//valida campos obrigatórios
			if (this.getAllMandatoryFields().contains(attributeName)) {
				String unfilledFieldMessage = this.generateEmptyMessageForAttribute(attributeName, this.getValue());
				if (unfilledFieldMessage != null) {
					actionReturn.reportFailure(ReturnTypeEnum.WARNING, Arrays.asList(unfilledFieldMessage));
				}
				
			}		
			if(!actionReturn.isSuccess()) return actionReturn;
			
	
			//validações do e-mail
			if (attributeName.equalsIgnoreCase("email")) {
				//valida formato do email
				this.onBlurValidateFieldEmailFormat(actionReturn,(String)getValueOrAttributeValue(attributeName));
				if(!actionReturn.isSuccess()) return actionReturn;
			
				//validar se o email já existe
				onBlurValidateFieldEmailExists(actionReturn, (String)getValueOrAttributeValue(attributeName),(Long) getAttributeParameter("pk"));
				if(!actionReturn.isSuccess()) return actionReturn;
			}
			
			//valida confirmação da senha
			if(attributeName.equalsIgnoreCase("confirmaSenha")){
				String confirmaSenha = (String)getValueOrAttributeValue(attributeName); 
				confirmaSenha=confirmaSenha!=null?confirmaSenha:"";
				
				String senha = (String) getAttributeParameter("senha");
				senha=senha!=null?senha:"";
				onBlurValidateFieldConfirmaSenha(actionReturn,confirmaSenha,senha);
				if(!actionReturn.isSuccess()) return actionReturn;
			}
	
			
		}
		
		
		
		return actionReturn;
	}
	
	/**
	 * Valida o campo email ao sair do campo
	 * @param attributeName
	 * @param actionReturn
	 */
	private void onBlurValidateFieldEmailExists(ActionReturn<?, ?> actionReturn, String value, Long academicoPk) {
		//valida atributo academicoChave de busca.
			String unfilledFieldMessage = this.generateEmptyMessageForAttribute("email", value);
			boolean exist = false;
			//valida o formato do e-mail apenas se estiver preenchido
			if (unfilledFieldMessage ==null){
				AcademicoService academicoService = getAcademicoService();
				if( academicoService.existsAcademicoEmail(value)){
					exist=true;
					if(academicoPk!=null){
						ActionReturn<String, Academico> ar = academicoService.findByPk(academicoPk);
						if(!ar.isSuccess() || !ar.getParameter(ActionReturn.ENTITY_PARAMETER).getEmail().equalsIgnoreCase(value)){
							exist=true;
						}else{
							exist=false;
						}
					}
				}
				if(exist){
					actionReturn.reportFailure(ReturnTypeEnum.WARNING,Arrays.asList(
							ConfigurationProperties.getInstance().getValue("CadastroAcademico.CadastrarAcademico.email.existe")
							));	
				}
			}
	}
	
	/**
	 * Valida o campo email ao sair do campo
	 * @param attributeName
	 * @param actionReturn
	 */
	private void onBlurValidateFieldEmailFormat(ActionReturn<?, ?> actionReturn, String value) {			
			String unfilledFieldMessage = this.generateEmptyMessageForAttribute("email", value);
			//valida o formato do e-mail apenas se estiver preenchido
			if (unfilledFieldMessage ==null){
				if(!CheckValidEMail.validar(value)){
					actionReturn.reportFailure(ReturnTypeEnum.WARNING,Arrays.asList(
							ConfigurationProperties.getInstance().getValue("CadastroAcademico.CadastrarAcademico.email.formato_invalido")
							));					
				}
			}else{
				actionReturn.reportFailure(ReturnTypeEnum.WARNING, Arrays.asList(unfilledFieldMessage));
			}
	}

	/**
	 * @param attributeName
	 * @return
	 */
	private Object getValueOrAttributeValue(String attributeName) {
		Object value =  getValue();
		value = value!=null ? value :getAttributeValue(attributeName) ;
		return value;
	}
	
	/** valida o campo confirma senha para ver se é igual ao campo senha 
	 * @param attributeName
	 * @param actionReturn
	 */
	private void onBlurValidateFieldConfirmaSenha(ActionReturn<?, ?> actionReturn, String confirmaSenha, String senha){
		
		if (!senha.equals(confirmaSenha)){
			actionReturn.reportFailure(ReturnTypeEnum.WARNING,Arrays.asList(
					ConfigurationProperties.getInstance().getValue("CadastroAcademico.CadastrarAcademico.senha_confirma_diferente_senha")
					));		
		}
	}

}
