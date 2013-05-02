package br.ueg.pcb.service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.TabExpander;

import org.omg.CORBA.OMGVMCID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.edu.aee.UniArch.domain.Restrictions;
import br.edu.aee.UniArch.enums.RestrictionsTypeEnum;
import br.edu.aee.UniArch.exception.SuperException;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.model.UserPermission;
import br.edu.aee.UniArch.structure.persistence.dao.GenericDAO;
import br.edu.aee.UniArch.structure.service.GenericService;
import br.ueg.pcb.enums.TipoDeBuscaAcademicoEnum;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.CursosAcademico;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.model.Unidade;
import br.ueg.pcb.model.assist.EntityTabelaBasica;
import br.ueg.pcb.model.assist.Sexo;
import br.ueg.pcb.utils.ORMUtils;

@Service
@Qualifier
public class AcademicoService extends GenericService<Academico, Long> {
	/**
	 * @param keyType tipo de busca CPF ou matricula
	 * @param keyValue valor a ser procurado
	 * @return retorna um boolean que indica se a chave de busca existe no 
	 */
	public boolean existsUegAcademico(String keyType, String keyValue ){
		if(this.getUegAcademico(keyType, keyValue)!=null){
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UegAcademico getUegAcademico(String keyType, String keyValue){
		TipoDeBuscaAcademicoEnum typeSearch = TipoDeBuscaAcademicoEnum.getTipoDeBuscaAcademico(keyType); 
		//UegAcademicoDao  uaDAO = (UegAcademicoDao) SpringFactory.getBean(UegAcademicoDao.class);
		GenericDAO uaDAO = (GenericDAO) SpringFactory.loadDAO(UegAcademico.class);
		
		UegAcademico ua = new UegAcademico();
		Restrictions rest;
		if(typeSearch == TipoDeBuscaAcademicoEnum.CPF){
			ua.setCpf(keyValue);
			rest = new Restrictions(RestrictionsTypeEnum.EQUAL,"cpf",(Object)keyValue);
		}else{
			ua.setPk(keyValue);
			rest = new Restrictions(RestrictionsTypeEnum.EQUAL,"id", (Object)keyValue);
		}
		try {		
			List<UegAcademico> uga2 = (List<UegAcademico>) uaDAO.listByClass(UegAcademico.class, rest);
			
			if (uga2!=null && uga2.size()>0){
				return uga2.get(0);
			}else{
				return null;
			}
				
			
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Unidade> getListUnidadeAcademico(Academico academico){
		
		List<CursosAcademico> cursosAcademico=null;
		List<Unidade> unidadeList = new ArrayList<Unidade>();
		
		cursosAcademico = this.getListCursosAcademico(academico);
		
		if(cursosAcademico==null) return null;
		for(CursosAcademico ca: cursosAcademico){
			Unidade unidade =  ORMUtils.initializeAndUnproxy(ca.getPk().getCurso().getUnidade());				
			if(!unidadeList.contains(unidade)){
				unidadeList.add(unidade);
			}
		} 
		return unidadeList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CursosAcademico> getListCursosAcademico(Academico academico){
		if(academico == null) return null;
		
		GenericDAO cursosAcademicoDAO = (GenericDAO) SpringFactory.loadDAO(CursosAcademico.class);
		
		Restrictions rest = new Restrictions(RestrictionsTypeEnum.EQUAL,"pk.uegAcademico.pk",(Object)academico.getUegAcademico().getPk());
		
		try {
			return (List<CursosAcademico>) cursosAcademicoDAO.list(CursosAcademico.class, rest);
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends EntityTabelaBasica> List<T> getListEntityTabelaBasica(Class<T> classe){
		GenericDAO gDAO = (GenericDAO) SpringFactory.getBean("genericDAO");
		List<T> listReturn = new ArrayList<T>(0);
		List<T> listReturnObject = new ArrayList<T>(0);
		 try {
			listReturn = (List<T>)gDAO.listByClass(classe);
			for (T t : listReturn) {
				listReturnObject.add(ORMUtils.initializeAndUnproxy(t));
			}
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listReturnObject;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean existsAcademicoEmail(String email){		
		List<Academico> listAcademico = this.findAcademicoByField("email", email);
		if(listAcademico!=null && listAcademico.size()>0){
			return true;
		}
		return false;
	}
	
	public Academico getAcademicoByUserPermission(UserPermission up){
		List<Academico> listAcademico = this.findAcademicoByField("pkUserPermission",up.getPk());
		if (listAcademico!=null && listAcademico.size()>0){
			return listAcademico.get(0);
		}
		return null;
	}
	
	public boolean existsAcademicoByUegAcademico(UegAcademico ua){
		List<Academico> listAcademico = this.findAcademicoByField("uegAcademico",ua);
		if (listAcademico!=null && listAcademico.size()>0){
			return true;
		}
		return false;
	}
	/** Localiza um academico passando o campo e o valor do campo para procurar a busca é feito apenas por igualdade 
	 * @param field
	 * @param value
	 * @return retorna uma lista de academico ou null caso não seja encontrado
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Academico> findAcademicoByField(String field, Object value){		
		GenericDAO uaDAO = (GenericDAO) SpringFactory.loadDAO(UegAcademico.class);
				
		Restrictions rest;
		rest = new Restrictions(RestrictionsTypeEnum.EQUAL,field, value);
		
		try {		
			List<Academico> academico = (List<Academico>) uaDAO.listByClass(Academico.class, rest);
			
			if (academico!=null && academico.size()>0){
				return academico;
			}else{
				return null;
			}
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
