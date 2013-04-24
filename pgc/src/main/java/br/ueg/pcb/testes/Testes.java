package br.ueg.pcb.testes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.ueg.pcb.validation.CadastroAcademicoValidation;

public class Testes {
	/**
	 * @param args
	 */
	public static void main(String[] args) { ApplicationContext appCo = new ClassPathXmlApplicationContext("META-INF/spring.xml");
		//UegAcademicoDao2  uaDAO = ((UegAcademicoDao2) appCo.getBean("uegAcademicoDao"));
		
		System.out.println("---------------");
		CadastroAcademicoValidation cv = ((CadastroAcademicoValidation) appCo.getBean("cadastroAcademicoValidation"));
		
	}
}
