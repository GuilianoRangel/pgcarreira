package br.ueg.pcb.viewnousecase;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.databind.AnnotateDataBinder;

import br.edu.aee.UniArch.annotation.AttributeView;
import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.structure.controller.GenericController;
import br.edu.aee.UniArch.structure.interfaces.ISuperEntity;
import br.edu.aee.UniArch.structure.view.ZK.CRUDViewZK;

@SuppressWarnings("serial")
public abstract class SuperViewZKPGC<CONTROL extends GenericController<ENTITY, PK>, ENTITY extends ISuperEntity<PK>, PK extends Serializable> extends
		CRUDViewZK<CONTROL, ENTITY, PK> {

	@AttributeView(attributeName = "casoDeUsoCenario", isEntityField = false)
	private String casoDeUsoCenario = "CadastrarAcademicoBusca";
	protected ActionReturn<?, ?> lastActionReturn = null;
	protected AnnotateDataBinder binderForm;

	public SuperViewZKPGC() {
		this("CadastrarAcademicoBusca");
	}
	
	public SuperViewZKPGC(String scenarioAction) {
		super(scenarioAction);
	}
	
	/**
	 * @return the casoDeUsoCenario
	 */
	public String getCasoDeUsoCenario() {
		//return casoDeUsoCenario;
		return this.getScenarioAction();
	}

	/**
	 * @param casoDeUsoCenario the casoDeUsoCenario to set
	 */
	public void setCasoDeUsoCenario(String casoDeUsoCenario) {
		this.setScenarioAction(casoDeUsoCenario);
		this.casoDeUsoCenario = casoDeUsoCenario;
	}

	/**
	 * @param actionReturn
	 */
	protected void redirectByActionReturn(ActionReturn<?, ?> actionReturn) {
		String nextUseCase = (String) actionReturn.getExtra(ActionReturn.NEXT_USE_CASE);
		if(nextUseCase!=null && !nextUseCase.equals("")){			
			Executions.sendRedirect(nextUseCase);
		}
	}

	/** Sempre redireciona para o próximo usecase quando actionReturn for reportar Falha
	 * @param actionReturn
	 */
	@SuppressWarnings("unused")
	protected void redirectNexUseCaseFailure(ActionReturn<?, ?> actionReturn) {
		if(!actionReturn.isSuccess()){
			this.redirectByActionReturn(actionReturn);
		}
	}

	protected void showPendentMessages() {
		if(this.lastActionReturn!=null && !this.lastActionReturn.getErrorMessages().isEmpty()){
			showMessage(this.lastActionReturn);
			this.lastActionReturn = null;
		}
	}

	/** atualiza o 
	 * @return
	 */
	public ActionReturn<String, ENTITY> recordWithoutRedirect() {
		ActionReturn<String, ENTITY> actionReturn =   super.record();
		this.lastActionReturn = actionReturn;
		return actionReturn;
	}

	/**
	 * Obtém o nome do arquivo zul ao qual o componente está assossiado. Por
	 * padrão, o nome adotado para os arquivos zul dos componentes devem ter o
	 * mesmo nome da classe que o representa. Caso decida utilizar um nome
	 * diferente, este método deve ser sobreescrito na classe que herdar esta.
	 * 
	 * @return Reader
	 * @throws UnsupportedEncodingException
	 */
	protected Reader getZulReader(String zulfile)
			throws UnsupportedEncodingException {
				String name = "/academico/".concat(zulfile);
				InputStream resourceAsStream = this.component.getDesktop()
						.getWebApp().getResourceAsStream(name);
				return new InputStreamReader(resourceAsStream, "UTF-8");
			}



	/**
	 * remove o componente ativo
	 */
	public void cleanActiveComponent() {
		if(this.getActiveComponent()==this.component){
			this.setActiveComponent(null);
		}
		if (this.getActiveComponent() != null) {
			this.getActiveComponent().setParent(null);
			this.getActiveComponent().detach();
			this.setActiveComponent(null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp)  {
		comp.setAttribute(this.getUseCase(), this, true);
		super.doAfterCompose(comp);				
	}

}