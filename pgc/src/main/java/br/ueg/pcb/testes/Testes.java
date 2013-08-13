package br.ueg.pcb.testes;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.SessionScope;

import br.edu.aee.UniArch.domain.Order;
import br.edu.aee.UniArch.domain.Restrictions;
import br.edu.aee.UniArch.exception.SuperException;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.interfaces.ISuperEntity;
import br.edu.aee.UniArch.structure.persistence.dao.GenericDAO;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.model.Unidade;
import br.ueg.pcb.model.assist.Sexo;
import br.ueg.pcb.service.AcademicoService;
import br.ueg.pcb.validation.CadastroAcademicoValidation;

public class Testes {
	/**
	 * @param args
	 */
	@SuppressWarnings({ "unchecked", "resource", "rawtypes" })
	public static void main(String[] args) { 
		
		ApplicationContext appCo = new ClassPathXmlApplicationContext("META-INF/spring.xml");
		((AbstractRefreshableApplicationContext) appCo).getBeanFactory().registerScope("session", new SessionScope());
		//UegAcademicoDao2  uaDAO = ((UegAcademicoDao2) appCo.getBean("uegAcademicoDao"));
		
//		System.out.println("---------------");
//		//CadastroAcademicoValidation cv = ((CadastroAcademicoValidation) appCo.getBean("cadastroAcademicoValidation"));
//		SpringFactory sf = SpringFactory.getInstance(appCo);
//		AcademicoService as = (AcademicoService) sf.loadService(Academico.class);
//		UegAcademico ua = as.getUegAcademico("cpf", "11111111111");
//		Academico a = new Academico();
//		a.setUegAcademico(ua);
//		for(Unidade u: as.getListUnidadeAcademico(a)){
//			System.out.print(u.getNome());
//			System.out.print(":"+u.getPk());
//			System.out.println(":"+u.getSigla());
//		}
		GenericDAO gDAO = (GenericDAO) appCo.getBean("genericDAO");
		try {
			for(Sexo s: (List<Sexo>)gDAO.listByClass(Sexo.class, new Restrictions[0],new Order[0])){
				System.out.println(s);
			}
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
