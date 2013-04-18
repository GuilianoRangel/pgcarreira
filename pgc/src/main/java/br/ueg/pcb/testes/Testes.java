package br.ueg.pcb.testes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.ueg.pcb.view.EstadoComposer;

public class Testes {
	/**
	 * @param args
	 */
	public static void main(String[] args) { ApplicationContext appCo = new ClassPathXmlApplicationContext("META-INF/spring.xml");
		EstadoComposer  ec = ((EstadoComposer) appCo.getBean("estadoComposer"));
		try {
			ec.doAfterCompose(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
