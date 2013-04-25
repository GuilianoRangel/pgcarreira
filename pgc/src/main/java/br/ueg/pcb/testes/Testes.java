package br.ueg.pcb.testes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.interfaces.ISuperEntity;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.model.Unidade;
import br.ueg.pcb.service.AcademicoService;
import br.ueg.pcb.validation.CadastroAcademicoValidation;

public class Testes {
	/**
	 * @param args
	 */
	public static void main(String[] args) { ApplicationContext appCo = new ClassPathXmlApplicationContext("META-INF/spring.xml");
		//UegAcademicoDao2  uaDAO = ((UegAcademicoDao2) appCo.getBean("uegAcademicoDao"));
		
		System.out.println("---------------");
		//CadastroAcademicoValidation cv = ((CadastroAcademicoValidation) appCo.getBean("cadastroAcademicoValidation"));
		SpringFactory sf = SpringFactory.getInstance(appCo);
		AcademicoService as = (AcademicoService) sf.loadService(Academico.class);
		UegAcademico ua = as.getUegAcademico("cpf", "11111111111");
		Academico a = new Academico();
		a.setUegAcademico(ua);
		for(Unidade u: as.getListUnidadeAcademico(a)){
			System.out.print(u.getNome());
			System.out.print(":"+u.getPk());
			System.out.println(":"+u.getSigla());
		}
		
		
		
	}
}
