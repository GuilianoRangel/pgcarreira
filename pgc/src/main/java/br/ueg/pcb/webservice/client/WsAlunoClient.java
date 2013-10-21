package br.ueg.pcb.webservice.client;

import java.io.IOException;
import java.util.ArrayList;
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
	static public ArrayList<HashMap<String,String>> searchAluno(String CPF){
		ArrayList<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> map = null;
		
		SoapObject client = new SoapObject("http://client", "getAluno");
		HttpTransport ht = new HttpTransport("http://webservice.ueg.br/webserverextensao/wsAlunos.php5", "getAluno");
		
		client.addProperty("cpf",CPF);

		try {
			Vector<?> vObj = (Vector<?>)ht.call(client);
			if(vObj!=null){				
				for( Iterator<?> iterator = vObj.iterator();iterator.hasNext();){
					map = new HashMap<String,String>();
					SoapObject next = (SoapObject)iterator.next();
					for(int i= 0;i<next.getPropertyCount();i++){
						SoapObject property = (SoapObject)next.getProperty(i);						
						map.put((String)property.getProperty(0), new String(((String)property.getProperty(1)).getBytes("ISO-8859-1"),"UTF-8"));
					}
					result.add(map);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		//ArrayList<HashMap<String,String>> searchAluno = WsAlunoClient.searchAluno("04657156136");//02945206196
		ArrayList<HashMap<String,String>> searchAluno = WsAlunoClient.searchAluno("01525541145");
		if(searchAluno!=null)
		for (Iterator iterator = searchAluno.iterator(); iterator.hasNext(); ) {    
			HashMap<String,String> c = (HashMap<String,String>) iterator.next();
			System.out.println("key\n");
			for (String string : c.keySet()) {
				System.out.println("Chave:"+string+"\t\t"+c.get(string));
			} 
		}  
		
			
	}
}
