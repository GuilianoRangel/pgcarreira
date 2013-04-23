package br.ueg.pcb.testes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.edu.aee.UniArch.exception.SuperException;
import br.ueg.pcb.dao.UegAcademicoDao;
import br.ueg.pcb.enums.TipoDeBuscaAcademicoEnum;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.validation.CadastroAcademicoValidation;
import br.ueg.pcb.view.EstadoComposer;

public class Testes {
	/**
	 * @param args
	 */
	public static void main(String[] args) { ApplicationContext appCo = new ClassPathXmlApplicationContext("META-INF/spring.xml");
		UegAcademicoDao  uaDAO = ((UegAcademicoDao) appCo.getBean("uegAcademicoDao"));
		UegAcademico ua = new UegAcademico();
		ua.setCpf("11111111111");
		try {
			uaDAO.exists(TipoDeBuscaAcademicoEnum.CPF, ua);
			System.out.println(ua);
			System.out.println(ua.getNome());
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------");
		CadastroAcademicoValidation cv = ((CadastroAcademicoValidation) appCo.getBean("cadastroAcademicoValidation"));
		
	}
}
