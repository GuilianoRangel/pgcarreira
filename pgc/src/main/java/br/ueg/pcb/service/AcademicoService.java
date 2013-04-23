package br.ueg.pcb.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.edu.aee.UniArch.exception.SuperException;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.service.GenericService;
import br.ueg.pcb.dao.UegAcademicoDao;
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
		UegAcademicoDao  uaDAO = (UegAcademicoDao) SpringFactory.getBean(UegAcademicoDao.class);		
		UegAcademico ua = new UegAcademico();
		if(typeSearch == TipoDeBuscaAcademicoEnum.CPF){
			ua.setCpf(keyValue);
		}else{
			ua.setPk(keyValue);
		}
		try {
			return uaDAO.exists(typeSearch, ua);
			
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
