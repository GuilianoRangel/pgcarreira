package br.ueg.pcb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.edu.aee.UniArch.exception.ErrorException;
import br.edu.aee.UniArch.exception.SuperException;
import br.edu.aee.UniArch.structure.persistence.dao.GenericDAO;
import br.ueg.pcb.enums.TipoDeBuscaAcademicoEnum;
import br.ueg.pcb.model.UegAcademico;

@Qualifier(value="uegAcademicoDao")
@Repository
@org.springframework.stereotype.Component
public class UegAcademicoDao extends GenericDAO<UegAcademico, String> {
	public boolean exists(TipoDeBuscaAcademicoEnum tipoBusca, UegAcademico uegAcademico) throws SuperException {
		openTransition();
		try {
			Criteria criteria = getSession().createCriteria(UegAcademico.class);
			if(tipoBusca == TipoDeBuscaAcademicoEnum.CPF){
				criteria.add(Restrictions.eq("cpf", uegAcademico.getCpf()));
			}else{
				criteria.add(Restrictions.eq("pk", uegAcademico.getPk()));
			}
			
			List<UegAcademico> list = criteria.list();
			this.commit();
			if (!list.isEmpty()) {
				UegAcademico academicoFound = list.get(0);
				uegAcademico.setCpf(academicoFound.getCpf());
				uegAcademico.setNome(academicoFound.getNome());
				uegAcademico.setPk(academicoFound.getPk());
				return true;
			}
			return false;
		} catch (HibernateException e) {
			this.rollback();
			//TODO tratar
			e.printStackTrace();
			throw new ErrorException("VERIFICAR DIREITO", e);
		}
	}
}
