package br.ueg.pcb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.edu.aee.UniArch.domain.Restrictions;
import br.edu.aee.UniArch.exception.SuperException;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.persistence.dao.GenericDAO;
import br.edu.aee.UniArch.structure.service.GenericService;
import br.ueg.pcb.enums.TipoDeBuscaAcademicoEnum;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.UegAcademico;

@Service
@Qualifier
public class AcademicoService extends GenericService<Academico, Long> {
	/**
	 * @param keyType tipo de busca CPF ou matricula
	 * @param keyValue valor a ser procurado
	 * @return retorna um boolean que indica se a chave de busca existe no 
	 */
	public boolean existsUegAcademico(String keyType, String keyValue ){
		TipoDeBuscaAcademicoEnum typeSearch = TipoDeBuscaAcademicoEnum.getTipoDeBuscaAcademico(keyType); 
		//UegAcademicoDao  uaDAO = (UegAcademicoDao) SpringFactory.getBean(UegAcademicoDao.class);
		GenericDAO uaDAO = (GenericDAO) SpringFactory.loadDAO(UegAcademico.class);
		
		UegAcademico ua = new UegAcademico();
		Restrictions rest;
		if(typeSearch == TipoDeBuscaAcademicoEnum.CPF){
			ua.setCpf(keyValue);
			rest = new Restrictions("cpf",keyValue);
		}else{
			ua.setPk(keyValue);
			rest = new Restrictions("id", keyValue);
		}
		try {		
			List<UegAcademico> uga2 = (List<UegAcademico>) uaDAO.listByClass(UegAcademico.class, rest);
			return uga2!=null && uga2.size()>0;
			
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
