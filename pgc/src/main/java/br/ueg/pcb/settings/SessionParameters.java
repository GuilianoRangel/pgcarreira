package br.ueg.pcb.settings;

import java.util.Arrays;

import org.springframework.stereotype.Controller;

import br.edu.aee.UniArch.structure.interfaces.ISuperEntity;
import br.edu.aee.UniArch.subsystems.sessionparam.SessionParameter;
import br.edu.aee.UniArch.subsystems.sessionparam.SystemSessionParameters;
import br.ueg.pcb.model.Unidade;

/**
 * Implementação do P.S.S. (Sistema de parâmetro de sessão)
 * para a aplicação.
 * Define quais são os parâmetros de sessão aceitos pela aplicação
 * 
 * @author Diego Carlos Rezende - Analista de sistemas 
 *         <diego.rezende@unievangelica.edu.br>
 *
 */
@Controller
public class SessionParameters extends SystemSessionParameters {
	
	/**
	 * Bloco estático que define quais são os parâmetros de 
	 * sessão que a aplicação suporta.
	 */
	static {
		sessionParameters.addAll(Arrays.asList(
				new SessionParameter( Unidade.class)) );//, 
				/*new SessionParameter(Switch.class), 
				new SessionParameter(Port.class)));*/
	}

}
