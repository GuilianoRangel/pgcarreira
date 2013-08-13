package br.ueg.pcb.webservice.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.ksoap.SoapObject;
import org.ksoap.transport.HttpTransport;
import org.zkoss.web.servlet.dsp.action.ForEach;

public class WsAlunoClient {
	/**
	 * @param CPF
	 * @return
	 */
	static public HashMap<String,String> searchAluno(String CPF){
		HashMap<String, String> map = null;
		SoapObject client = new SoapObject("http://client", "getAluno");
		HttpTransport ht = new HttpTransport("http://webservice.ueg.br/webserverextensao/wsAlunos.php5", "getAluno");
		
		client.addProperty("cpf",CPF);

		try {
			Vector<?> vObj = (Vector<?>)ht.call(client);
			if(vObj!=null){
				map = new HashMap<String,String>();
				for( Iterator<?> iterator = vObj.iterator();iterator.hasNext();){
					SoapObject next = (SoapObject)iterator.next();
					for(int i= 0;i<next.getPropertyCount();i++){
						SoapObject property = (SoapObject)next.getProperty(i);
						
						map.put((String)property.getProperty(0), (String)property.getProperty(1));

					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public static void main(String[] args) {
		HashMap<String,String> searchAluno = WsAlunoClient.searchAluno("04657156136");
		if(searchAluno!=null)
		for (String string : searchAluno.keySet()) {
			System.out.println("Chave:"+string+"\t\t"+searchAluno.get(string));
		} 
			
	}
}
