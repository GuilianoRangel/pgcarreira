package br.ueg.pcb.view;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Scope;

import br.edu.aee.UniArch.annotation.AttributeView;
import br.edu.aee.UniArch.annotation.Scenario;
import br.edu.aee.UniArch.domain.ActionReturn;
import br.edu.aee.UniArch.structure.interfaces.IValidator;
import br.ueg.pcb.controller.InfoProfissionalController;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.InfoProfissional;
import br.ueg.pcb.model.assist.Mes;
import br.ueg.pcb.model.assist.TipoVinculo;
import br.ueg.pcb.viewnousecase.SuperViewZKPGC;

@SuppressWarnings({ "serial" })
@Scenario(visibleName = "CADASTRO_ACADEMICO_INFOPROFISSIONAL", value = "CADASTRO_ACADEMICO_INFOPROFISSIONAL_SCENARIO", isSingle = true, restrictedAccess = true, showOnMenu = true, actions = {
		IValidator.RECORD_ACTION, IValidator.SAVE_ACTION,
		IValidator.LIST_ACTION})
@Scope(value="session")
@org.springframework.stereotype.Component
public class InfoProfissionalComposer extends SuperViewZKPGC<InfoProfissionalController, InfoProfissional, Long>{
	
	@AttributeView(attributeName="academico", isEntityField=true)
	private Academico fldAcademico;
	
	@AttributeView(attributeName="cargo", isEntityField=true)
	private String fldCargo;
	
	@AttributeView(attributeName="empresa", isEntityField=true)
	private String fldEmpresa;
	
	@AttributeView(attributeName="entradaMes", isEntityField=true)
	private Mes fldEntradaMes;
	
	@AttributeView(attributeName="entradaAno", isEntityField=true)
	private Integer fldEntradaAno;
	
	@AttributeView(attributeName="saidaMes", isEntityField=true)
	private Mes fldSaidaMes;
	
	@AttributeView(attributeName="saidaAno", isEntityField=true)
	private Integer fldSaidaAno;
	
	@AttributeView(attributeName="tipoVinculo", isEntityField=true)
	private TipoVinculo fldTipoVinculo;
	
	@AttributeView(attributeName="localidade", isEntityField=true)
	private String fldLocalidade;
	
	@AttributeView(attributeName="descricao", isEntityField=true)
	private String fldDescricao;
	
	public Collection<Mes> getListMeses() {
		ActionReturn<String,Collection<Mes>> actionReturn = this.getViewController().listByClass(Mes.class);
		if (actionReturn.isSuccess()) {
			return actionReturn.getParameter(ActionReturn.LIST_PARAMETER);
		}
		return new ArrayList<Mes>();
	}
	
	public Collection<TipoVinculo> getListTipoVinculos() {
		ActionReturn<String,Collection<TipoVinculo>> actionReturn = this.getViewController().listByClass(TipoVinculo.class);
		if (actionReturn.isSuccess()) {
			return actionReturn.getParameter(ActionReturn.LIST_PARAMETER);
		}
		return new ArrayList<TipoVinculo>();
	}

	public Academico getFldAcademico() {
		return fldAcademico;
	}

	public void setFldAcademico(Academico fldAcademico) {
		this.fldAcademico = fldAcademico;
	}

	public String getFldCargo() {
		return fldCargo;
	}

	public void setFldCargo(String fldCargo) {
		this.fldCargo = fldCargo;
	}

	public String getFldEmpresa() {
		return fldEmpresa;
	}

	public void setFldEmpresa(String fldEmpresa) {
		this.fldEmpresa = fldEmpresa;
	}

	public Mes getFldEntradaMes() {
		return fldEntradaMes;
	}

	public void setFldEntradaMes(Mes fldEntradaMes) {
		this.fldEntradaMes = fldEntradaMes;
	}

	public Integer getFldEntradaAno() {
		return fldEntradaAno;
	}

	public void setFldEntradaAno(Integer fldEntradaAno) {
		this.fldEntradaAno = fldEntradaAno;
	}

	public Mes getFldSaidaMes() {
		return fldSaidaMes;
	}

	public void setFldSaidaMes(Mes fldSaidaMes) {
		this.fldSaidaMes = fldSaidaMes;
	}

	public Integer getFldSaidaAno() {
		return fldSaidaAno;
	}

	public void setFldSaidaAno(Integer fldSaidaAno) {
		this.fldSaidaAno = fldSaidaAno;
	}

	public TipoVinculo getFldTipoVinculo() {
		return fldTipoVinculo;
	}

	public void setFldTipoVinculo(TipoVinculo fldTipoVinculo) {
		this.fldTipoVinculo = fldTipoVinculo;
	}

	public String getFldLocalidade() {
		return fldLocalidade;
	}

	public void setFldLocalidade(String fldLocalidade) {
		this.fldLocalidade = fldLocalidade;
	}

	public String getFldDescricao() {
		return fldDescricao;
	}

	public void setFldDescricao(String fldDescricao) {
		this.fldDescricao = fldDescricao;
	}

	@Override
	public void cleanFields() {
		this.setFldAcademico(null);
		this.setFldCargo(null);
		this.setFldDescricao(null);
		this.setFldEmpresa(null);
		this.setFldEntradaAno(null);
		this.setFldEntradaMes(null);
		this.setFldLocalidade(null);
		this.setFldSaidaAno(null);
		this.setFldSaidaMes(null);
		this.setFldTipoVinculo(null);
		super.cleanFields();
	}
	
}
