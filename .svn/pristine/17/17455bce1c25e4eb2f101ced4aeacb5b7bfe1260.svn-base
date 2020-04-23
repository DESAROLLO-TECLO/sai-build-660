package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes.ComponenteDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes.FormatoDescargaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes.PropiedadesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes.ReporteDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes.TipoParametroDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes.TipoReporteDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes.TipoTituloDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.AplicacionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ComponentesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.FormatoDescargaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ParametrosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.PropiedadesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ReportesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoParametroDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoReportesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoTitulosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.ConfigParamVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.ParamValueVO;
import mx.com.teclo.saicdmx.persistencia.vo.admireporte.ReporteVO;





@Service
public class AdmiReporteServiceImpl implements AdmiReporteService {
	


	@Autowired
	private TipoReporteDAO tipoReporteDAO;
	
	@Autowired
	private FormatoDescargaDAO formatoDescargaDAO;
	
	@Autowired
	private ComponenteDAO componenteDAO;
	
	@Autowired
	private TipoParametroDAO tipoParametroDAO;
	
	@Autowired
	private PropiedadesDAO propiedadesDAO;
	
	@Autowired
	private TipoTituloDAO tipoTituloDAO;
	
	@Autowired
	private ReporteDAO reporteDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Override
	@Transactional
	public List<TipoReportesDTO> obtenerListaTipoReporte(){
		return tipoReporteDAO.listaTipoReporte();
	}

	@Override
	@Transactional
	public List<FormatoDescargaDTO> obtenerListaFormatoDescarga() {
		return formatoDescargaDAO.listaFormatoDescarga();
	}

	@Override
	@Transactional
	public List<ComponentesDTO> obtenerListaComponentes() {
		return componenteDAO.listaComponentes();
	}

	@Override
	@Transactional
	public List<TipoParametroDTO> obtenerListaParametros() {
		return tipoParametroDAO.listaTiposParametro();
	}

	@Override
	@Transactional
	public List<PropiedadesDTO> obtenerPropiedades() {
		return propiedadesDAO.listaPropiedades();
	}

	@Override
	@Transactional
	public List<TipoTitulosDTO> obtenerTipoTitulo() {
		return tipoTituloDAO.listaTipoTitulo();
	}
	
	public Long selectMaximo(){
		return reporteDAO.selectMaximo();
	}

	@Override
	public List<ConfigParamVO> identificacionParametro(String cadena) {

		StringTokenizer st= new StringTokenizer(cadena, "#{}");
		
		int i, k=0, j=0, p=0;
		
		String[] token = new String[st.countTokens()];
		
		while(st.hasMoreTokens()){
			for(i=0; i<token.length; i++){
				token[i] = st.nextToken();
				if(i%2 !=0){
					j++;
				}
			}
		}
		String[] tokenImpar = new String[j];
		for(k=0; k<token.length; k++){
			if(k%2 !=0){
				tokenImpar[p] = token[k];
				p++;
			}
			
		}
		List<String> listaImparArray = new ArrayList<String>();
		listaImparArray=Arrays.asList(tokenImpar);
		System.out.println(listaImparArray);
		
		List<ConfigParamVO> response = new ArrayList<ConfigParamVO>();
		for(String string : listaImparArray){
			response.add(new ConfigParamVO(string));
		}
		return response;

	}

	
	@Override
	public String formarQuery(String query, String cadena) {
		String queryValue = cadena;
		List<ParamValueVO> listJson = converJson(query);
		
		System.out.println(listJson);
		String mensaje=null;
		
		System.out.println(listJson.get(0).getParam());
		
		HashMap<String, String> hashParam = new HashMap<String, String>();
		
		for(int i=0; i<listJson.size(); i++){
			hashParam.put("#{" + listJson.get(i).getParam() + "}", listJson.get(i).getValue());
		}
		for(Entry<String, String> entry : hashParam.entrySet()){
			queryValue=queryValue.replace(entry.getKey(), "'"+entry.getValue()+"'");
		}
		System.out.println(queryValue);
		
		try {
			System.out.println(queryValue);
			mensaje ="Query Correcto";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			mensaje = "error";
		}
		return mensaje;
		 
	}
	
	public List<ParamValueVO> converJson(String query){
		List<ParamValueVO> listJson = new ArrayList<>();
		
		try{
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			listJson = mapper.readValue(query.toString(), new TypeReference<List<ParamValueVO>>(){});
	
		} catch (IOException e){
			e.printStackTrace();
		}
		
		return listJson;
		
	}

	@Override
	@Transactional
	public void saveReporte(ReporteVO rVo) {
		ReportesDTO  rDto= new ReportesDTO();
		ParametrosDTO pDto = new ParametrosDTO();
		AplicacionDTO appDto = reporteDAO.idApp();
		
		Long maximoIdReporte = selectMaximo();
		if(maximoIdReporte !=0){
			
//			TipoTituloDTO tTDto = ResponseConverter.copiarPropiedadesFull(rVo.getIdTipoTitulo().getIdTipoTitulo(), TipoTituloDTO.class);
			
			
			rDto.setTipoReporte(tipoReporteDAO.findOne(rVo.getIdTipoReporte().getIdTipoReporte()));
			rDto.setTipoTitulo(tipoTituloDAO.findOne(rVo.getIdTipoTitulo().getIdTipoTitulo()));
			rDto.setAplicacion(appDto);
			rDto.setCdReporte(rVo.getNbReporte());
			rDto.setNbReporte(rVo.getNbReporte());
			rDto.setTxReporte(rVo.getTxReporte());
			rDto.setUrl(rVo.getTxUrl());
			rDto.setScriptSelect("SELECT " + rVo.getScriptSelect());
			rDto.setScritFrom("FROM " + rVo.getScriptFrom());
			rDto.setScriptWhere("WHERE " + rVo.getScriptWhere());
			rDto.setStActivo(1);
			rDto.setIdUsrCreacion(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			rDto.setFhCreacion(new Date());
			rDto.setIdUsrModifica(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
			rDto.setFhModificacion(new Date());
			reporteDAO.save(rDto);	
//			pDto.s
		}
	}

	@Override
	public void saveConfigparam(ConfigParamVO ConfigParamVO) {
		
		//ParametroDTO paraDto = new ParametroDTO();
		
		
	}

	
}
