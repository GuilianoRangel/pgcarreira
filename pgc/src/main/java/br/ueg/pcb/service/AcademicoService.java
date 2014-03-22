package br.ueg.pcb.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.edu.aee.UniArch.domain.Order;
import br.edu.aee.UniArch.domain.Restrictions;
import br.edu.aee.UniArch.enums.RestrictionsTypeEnum;
import br.edu.aee.UniArch.exception.SuperException;
import br.edu.aee.UniArch.settings.SpringFactory;
import br.edu.aee.UniArch.structure.model.User;
import br.edu.aee.UniArch.structure.persistence.dao.GenericDAO;
import br.edu.aee.UniArch.structure.service.GenericService;
import br.ueg.pcb.enums.TipoDeBuscaAcademicoEnum;
import br.ueg.pcb.model.Academico;
import br.ueg.pcb.model.Curso;
import br.ueg.pcb.model.CursosAcademico;
import br.ueg.pcb.model.UegAcademico;
import br.ueg.pcb.model.Unidade;
import br.ueg.pcb.model.assist.EntityTabelaBasica;
import br.ueg.pcb.model.pks.CursosAcademicoPK;
import br.ueg.pcb.utils.ORMUtils;
import br.ueg.pcb.webservice.client.WsAlunoClient;

@Service
@Qualifier
public class AcademicoService extends GenericService<Academico, Long> {
	/**
	 * @param keyType tipo de busca CPF ou matricula
	 * @param keyValue valor a ser procurado
	 * @return retorna um boolean que indica se a chave de busca existe no 
	 */
	public boolean existsUegAcademico(String keyType, String keyValue ){
		if(this.getUegAcademico(keyType, keyValue)!=null){
			return true;
		}else{
			return false;
		} 
	}
	/**
	 * Mapeia o usuário no banco de dados da aplicação, atualizando o cadastro de
	 * unidade, curso, academico
	 * @param CPF
	 * @return true caso do usuário foi ou está mapeado no banco de dados, 
	 */
	public boolean mapUegAcademico(String CPF){
		//HashMap<String,String> searchAluno = WsAlunoClient.searchAluno(CPF);
		ArrayList<HashMap<String,String>> searchAluno = WsAlunoClient.searchAluno(CPF);
		
		
		
		if(searchAluno!=null){	
			for (Iterator iterator = searchAluno.iterator(); iterator.hasNext(); ) {    
				HashMap<String,String> cursoInfo = (HashMap<String,String>) iterator.next();
				try {
					UegAcademico uegAcademico = new UegAcademico();
					Curso curso = new Curso();
					CursosAcademico cursosAcademico = new CursosAcademico();
					Unidade unidade = new Unidade();
					
					this.prepareHashMapWebServiceAluno(cursoInfo);
					BeanUtils.populate(uegAcademico, cursoInfo);	
					
					this.prepareHashMapWebServiceUnidade(cursoInfo);
					BeanUtils.populate(unidade, cursoInfo);
					
					this.prepareHashMapWebServiceCurso(cursoInfo);
					
					BeanUtils.populate(curso, cursoInfo);
					if(cursoInfo.get("situacao").equalsIgnoreCase("formando")){
						cursosAcademico.setAnoConclusao(cursoInfo.get("ano_semestre_conclusao_ou_ultimo_semestre_cursado"));
					};
					cursosAcademico.setMatricula(cursoInfo.get("matricula"));
					
					CursosAcademicoPK pk = new CursosAcademicoPK();
					
					pk.setAnoIngresso(cursoInfo.get("ano_semestre_inicio"));					
					pk.setCurso(curso);
					pk.setUegAcademico(uegAcademico);
					cursosAcademico.setPk(pk);
					
					saveUpdateUegAcademico(uegAcademico);
					
					unidade = saveUpdateUnidade(unidade);
					curso.setUnidade(unidade);
					saveUpdateCurso(curso);
					
					saveUpdateCursosAcademico(cursosAcademico);
					
					
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SuperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;
			//System.out.println(uegAcademico);
		}
			
		return false;
	}
	/**
	 * @param uegAcademico
	 * @throws SuperException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void saveUpdateUegAcademico(UegAcademico uegAcademico)
			throws SuperException {
		GenericDAO uaDAO = (GenericDAO) SpringFactory.loadDAO(UegAcademico.class);
		UegAcademico uegAcademico2 = (UegAcademico) uaDAO.findEntityByPk(uegAcademico);
		if(uegAcademico2==null){
			uaDAO.save(uegAcademico);
		}else{
			uaDAO.update(uegAcademico);
		}
	}
	
	/**
	 * @param unidade
	 * @throws SuperException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Unidade saveUpdateUnidade(Unidade unidade)
			throws SuperException {
		GenericDAO uaDAO = (GenericDAO) SpringFactory.loadDAO(Unidade.class);
		Unidade unidade2 = (Unidade) uaDAO.findEntityByPk(unidade);
		if(unidade2==null){
			uaDAO.save(unidade);
		}else{
			uaDAO.update(unidade);
		}
		unidade2 = (Unidade) uaDAO.findEntityByPk(unidade);
		return unidade2;
	}
	
	/**
	 * @param curso
	 * @throws SuperException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void saveUpdateCurso(Curso curso)
			throws SuperException {
		GenericDAO uaDAO = (GenericDAO) SpringFactory.loadDAO(Curso.class);
		Curso curso2 = (Curso) uaDAO.findEntityByPk(curso);
		if(curso2==null){
			uaDAO.save(curso);
		}else{
			uaDAO.update(curso);
		}
	}
	
	/**
	 * @param cursosAcademico
	 * @throws SuperException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void saveUpdateCursosAcademico(CursosAcademico cursosAcademico)
			throws SuperException {
		GenericDAO uaDAO = (GenericDAO) SpringFactory.loadDAO(CursosAcademico.class);
		CursosAcademico cursosAcademico2 = (CursosAcademico) uaDAO.findEntityByPk(cursosAcademico);
		if(cursosAcademico2==null){
			uaDAO.save(cursosAcademico);
		}else{
			uaDAO.update(cursosAcademico);
		}
	}
	
	private void prepareHashMapWebServiceAluno(Map<String,String> map){
		map.put("nome", map.get("aluno"));
	}
	
	private void prepareHashMapWebServiceUnidade(Map<String,String> map){
		map.put("pk",map.get("id_unidade"));
		map.put("nome", map.get("unidade"));
		map.put("telefone", map.get("telefones_unidade"));
		map.put("email", map.get("email_unidade"));
		map.put("enderecoCidade", map.get("cidade"));
	}
	
	private void prepareHashMapWebServiceCurso(Map<String,String> map){
		map.remove("unidade");
		map.remove("pk");
		map.put("pk", map.get("id_curso"));
		map.put("nome", map.get("curso"));
		map.put("cargaHorariaTotal", map.get("cht"));		
	}
	
	/**
	 * @param keyType - tipo de busca(CPF(caso seja cpf faz a busca no web service e mapea) ou matricula
	 * @param keyValue - chave de busca(o número CPF ou a o número da matricula)
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UegAcademico getUegAcademico(String keyType, String keyValue){

		TipoDeBuscaAcademicoEnum typeSearch = TipoDeBuscaAcademicoEnum.getTipoDeBuscaAcademico(keyType);
		
		if(typeSearch == TipoDeBuscaAcademicoEnum.CPF){
			if(!this.mapUegAcademico(keyValue)){
				return null;
			}
		}
		
		//UegAcademicoDao  uaDAO = (UegAcademicoDao) SpringFactory.getBean(UegAcademicoDao.class);
		GenericDAO uaDAO = (GenericDAO) SpringFactory.loadDAO(UegAcademico.class);
		
		UegAcademico ua = new UegAcademico();
		Restrictions[] restrictions =  new Restrictions[1];

		if(typeSearch == TipoDeBuscaAcademicoEnum.CPF){
			ua.setCpf(keyValue);
			restrictions[0]  = new Restrictions(RestrictionsTypeEnum.EQUAL,"cpf",(Object)keyValue);
		}else{
			ua.setPk(keyValue);
			restrictions[0]  = new Restrictions(RestrictionsTypeEnum.EQUAL,"id", (Object)keyValue);
		}
		try {		
			List<UegAcademico> uga2 = (List<UegAcademico>) uaDAO.listByClass(UegAcademico.class, restrictions , new Order[0]);
			
			if (uga2!=null && uga2.size()>0){
				return uga2.get(0);
			}else{
				return null;
			}
				
			
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Unidade> getListUnidadeAcademico(Academico academico){
		
		List<CursosAcademico> cursosAcademico=null;
		List<Unidade> unidadeList = new ArrayList<Unidade>();
		
		cursosAcademico = this.getListCursosAcademico(academico);
		
		if(cursosAcademico==null) return null;
		for(CursosAcademico ca: cursosAcademico){
			Unidade unidade =  ORMUtils.initializeAndUnproxy(ca.getPk().getCurso().getUnidade());				
			if(!unidadeList.contains(unidade)){
				unidadeList.add(unidade);
			}
		} 
		return unidadeList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CursosAcademico> getListCursosAcademico(Academico academico){
		if(academico == null) return null;
		
		GenericDAO cursosAcademicoDAO = (GenericDAO) SpringFactory.loadDAO(CursosAcademico.class);
		
		Restrictions[] restrictions =  new Restrictions[1];
		restrictions[0] = new Restrictions(RestrictionsTypeEnum.EQUAL,"pk.uegAcademico.cpf",(Object)academico.getUegAcademico().getPk());
		
		try {
			return (List<CursosAcademico>) cursosAcademicoDAO.list(CursosAcademico.class, restrictions, new Order[0]);
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends EntityTabelaBasica> List<T> getListEntityTabelaBasica(Class<T> classe){
		GenericDAO gDAO = (GenericDAO) SpringFactory.getBean("genericDAO");
		List<T> listReturn = new ArrayList<T>(0);
		List<T> listReturnObject = new ArrayList<T>(0);
		 try {
			listReturn = (List<T>)gDAO.listByClass(classe, new Restrictions[0], new Order[0]);
			for (T t : listReturn) {
				listReturnObject.add(ORMUtils.initializeAndUnproxy(t));
			}
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listReturnObject;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean existsAcademicoEmail(String email){		
		List<Academico> listAcademico = this.findAcademicoByField("email", email);
		if(listAcademico!=null && listAcademico.size()>0){
			return true;
		}
		return false;
	}
	
	public Academico getAcademicoByUserPermission(User up){
		List<Academico> listAcademico = this.findAcademicoByField("pkUserPermission",up.getPk());
		if (listAcademico!=null && listAcademico.size()>0){
			Academico academico = listAcademico.get(0);
			UegAcademico ua = this.getUegAcademico("matricula", academico.getUegAcademico().getPk());
			academico.setUegAcademico(ua);
			return academico;
		}
		return null;
	}
	
	public boolean existsAcademicoByUegAcademico(UegAcademico ua){
		List<Academico> listAcademico = this.findAcademicoByField("uegAcademico",ua);
		if (listAcademico!=null && listAcademico.size()>0){
			return true;
		}
		return false;
	}
	/** Localiza um academico passando o campo e o valor do campo para procurar a busca é feito apenas por igualdade 
	 * @param field
	 * @param value
	 * @return retorna uma lista de academico ou null caso não seja encontrado
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Academico> findAcademicoByField(String field, Object value){		
		GenericDAO uaDAO = (GenericDAO) SpringFactory.loadDAO(UegAcademico.class);
				
		Restrictions[] restrictions =  new Restrictions[1];
		restrictions[0] = new Restrictions(RestrictionsTypeEnum.EQUAL,field, value);
		
		try {		
			List<Academico> academico = (List<Academico>) uaDAO.listByClass(Academico.class, restrictions, new Order[0]);
			
			if (academico!=null && academico.size()>0){
				return academico;
			}else{
				return null;
			}
		} catch (SuperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
