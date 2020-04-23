package mx.com.teclo.saicdmx.negocio.service.atencionCiudadana;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.NonContextualLobCreator;

import javax.servlet.ServletContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.saicdmx.negocio.service.catalogos.CatalogoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.atencionCiudadana.TramitesAtencionCiudadanaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.atencionCiudadana.ExpedienteTramiteDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.atencionCiudadana.ExpedienteTramiteEstatusDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CatMedioSolicitudDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CatTipoDocumentoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CatTipoTramiteDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.atencionCiudadana.AtencionCiudadanaTramitesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.atencionCiudadana.ExpedienteTramiteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.atencionCiudadana.ExpedienteTramiteEstatusDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatMedioSolicitudDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoDocumentoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoTramiteDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.atencionCiudadana.TramitesACMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.atencionCiudadana.AtencionCudadanaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegDetTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACTramiteDetalleVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.AtenCiudaCamposRequeridosVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ExpedienteACVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ExpedienteTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.TipoDocumentoVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.TipoTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteGeneralFVO;
import mx.com.teclo.saicdmx.util.comun.LetraCapitalUtil;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

@Service
public class AtencionCiudadanaServiceImpl implements AtencionCiudadanaService {
	
private static final Logger logger = Logger
			.getLogger(AtencionCiudadanaServiceImpl.class);
	
@Autowired
CatTipoDocumentoDAO catTipoDocumentoDAO;
@Autowired
CatTipoTramiteDAO catTipoTramiteDAO;
@Autowired
CatMedioSolicitudDAO catMedioSolicitudDAO;
@Autowired
AtencionCudadanaMyBatisDAO atencionCiudadadanaDAO;
@Autowired
private ServletContext context;
@Autowired
private  TramitesAtencionCiudadanaDAO  tramitesAtencionCiudadanaDAO;
@Autowired
private TramitesACMyBatisDAO tramitesACMyBatisDAO;
@Autowired
private CatalogoService catalogoService;
@Autowired
private ExpedienteTramiteDAO expedienteTramiteDAOHibernate;
@Autowired
private ExpedienteTramiteEstatusDAO expedienteTramiteEstatusDAOHibernate;

private LetraCapitalUtil letraCapitalUtil=new LetraCapitalUtil() ;

@SuppressWarnings("rawtypes")
private Map reporteConsulta;
 


	@Override
	@Transactional
	public List<CatTipoDocumentoDTO> getCatalogoDocumento() {
		// TODO Auto-generated method stub
		return catTipoDocumentoDAO.catalogoDocumento();
	}

	@Override
	@Transactional
	public List<CatTipoTramiteDTO> getCatalogoTramite() {
		// TODO Auto-generated method stub
		return catTipoTramiteDAO.catalogoTramite();
	}
	
	@Override
	@Transactional
	public List<CatMedioSolicitudDTO> getCatalogoSolicitud() {
		// TODO Auto-generated method stub
		return catMedioSolicitudDAO.catalogoMedioSolicitud();
	}

	@Override
	//@Transactional
	public List<String> insertarTramite(ACTramiteVO tramiteVO) {
		
		List<String> listRespuesta = new ArrayList<String>();

		String idNexTramit=atencionCiudadadanaDAO.buscarTramiteID();
		tramiteVO.setIdacTramite(idNexTramit);
		Boolean registrarTramite=atencionCiudadadanaDAO.insertarTramite(tramiteVO);
		String respTramite=registrarTramite.toString();
		listRespuesta.add(idNexTramit);
		listRespuesta.add(respTramite);
		
		return listRespuesta;
	}
	
	@Override
	
	public List<String> consultaInfraccion(String placa, String infraccion) {
		return atencionCiudadadanaDAO.buscaInfraccion(placa,infraccion);
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ByteArrayOutputStream generaReporteTramite(String idTramite) {
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		String rutaArchivo;
		String rutalogoSSP;
		String rutalogoTeclo;
		InputStream inputStreamLogoSSP = null;
		InputStream inputStreamLogoTeclo  = null;
		int k=0;
        int j=0;
        Map parametros = new HashMap();
		
		ACTramiteVO listaTramite=atencionCiudadadanaDAO.seleccionarTramite(idTramite);
		listaTramite.setNbOficial(letraCapitalUtil.covertirNombreMayusYMinus(listaTramite.getNbOficial(),false));
		listaTramite.setApellidoPaternoOficial(letraCapitalUtil.covertirNombreMayusYMinus(listaTramite.getApellidoPaternoOficial(),true));
		listaTramite.setApellidoMaternoOficial(letraCapitalUtil.covertirNombreMayusYMinus(listaTramite.getApellidoMaternoOficial(),true));
		List<CatTipoTramiteDTO> listCatTipoTramiteDTO = catTipoTramiteDAO.getTramitePorListId(listaTramite.getIdTramite());
		List<TipoTramiteVO> listTipoTramiteVO = ResponseConverter.converterLista(new ArrayList<>(), listCatTipoTramiteDTO, TipoTramiteVO.class);
		listaTramite.setListaTipoTramite(listTipoTramiteVO);
		List<CatTipoDocumentoDTO> listaTipoDocumentoDTO = catTipoDocumentoDAO.getcatalogoDocumentoID(listaTramite.getIdDocumento());
		List<TipoDocumentoVO> listTipoDocumentoVO = ResponseConverter.converterLista(new ArrayList<>(), listaTipoDocumentoDTO, TipoDocumentoVO.class);
		listaTramite.setListaTipoDocuemento(listTipoDocumentoVO);
		listaTramite.setcTramite("");
		listaTramite.setcDocumento("");
		
		for (TipoTramiteVO actual:listaTramite.getListaTipoTramite())
		{
			listaTramite.setcTramite(listaTramite.getcTramite()+actual.getNbTramite());
			
			if((k+1)!=listaTramite.getListaTipoTramite().size())
			{
				listaTramite.setcTramite(listaTramite.getcTramite()+", ");
			}
			k++;
			
		}
		
		for (TipoDocumentoVO actual:listaTramite.getListaTipoDocuemento())
		{
			listaTramite.setcDocumento(listaTramite.getcDocumento()+actual.getNbDocumento());
			
			if((j+1)!=listaTramite.getListaTipoDocuemento().size())
			{
				listaTramite.setcDocumento(listaTramite.getcDocumento()+", ");
			}
			
		}
		
		
		
		rutaArchivo = context.getRealPath("/WEB-INF/jasper/atencionciudadana/reporteTramites.jasper");
		rutalogoSSP ="WEB-INF/imagenes/logo_CDMX_SSC.png";
		rutalogoTeclo = "WEB-INF/imagenes/logoAC.png";
		try {
			File fileLogoSSP = new ClassPathResource(rutalogoSSP).getFile();
			File fileLogoTeclo  = new ClassPathResource(rutalogoTeclo).getFile();
			
			inputStreamLogoSSP = new FileInputStream(fileLogoSSP);
			inputStreamLogoTeclo  = new FileInputStream(fileLogoTeclo);
		} catch (IOException e1) {
			
		}
		RutinasTiempoImpl rutina=new RutinasTiempoImpl();
		Date stringDate=new Date();
		

	
	
        
        try {
        	parametros.put( "idTramite",listaTramite.getIdacTramite());
    		parametros.put( "fhAlta",listaTramite.getFhAlta());
    		parametros.put( "tipoTramite",listaTramite.getcTramite());
    		parametros.put( "nbNombre",listaTramite.getNbCiudadano()!=null?listaTramite.getNbCiudadano():"" );
    		parametros.put( "nbEmpresa",listaTramite.getNbEmpresa()!=null?listaTramite.getNbEmpresa():"" );
    		parametros.put( "nbPaterno", listaTramite.getNbPaterno()!=null?listaTramite.getNbPaterno():"");
    		parametros.put( "nbMaterno",listaTramite.getNbMaterno()!=null?listaTramite.getNbMaterno():"");
    		parametros.put( "nuTelefono",listaTramite.getNuTelefono());
    		parametros.put( "txCorreo", listaTramite.getTxCorreo());
    		
    		parametros.put( "nbCalle", listaTramite.getTxCalle()!=null?listaTramite.getTxCalle():"Calle desconocida");
    		parametros.put( "nuInt",listaTramite.getNuInt()!=null?listaTramite.getNuInt():"S/N");
    		parametros.put( "nuExt", listaTramite.getNuExt()!=null? listaTramite.getNuExt():"S/N");
    		parametros.put( "nbColonia", listaTramite.getTxColonia()!=null?listaTramite.getTxColonia():"Colonia desconocida");
    		parametros.put( "nbDelegacion",!listaTramite.getcDelegacion().equals(" ")?listaTramite.getcDelegacion():"Delegación desconocida");
    		parametros.put( "nbEstado", !listaTramite.getcEdo().equals(" ")? listaTramite.getcEdo():"Estado desconocido");
    		parametros.put( "nbMarca",listaTramite.getcMarca().equals("OTRO")?listaTramite.getNbMarcaOtro():listaTramite.getcMarca());
    		parametros.put( "nbModelo", listaTramite.getcModelo().equals("OTRO")?listaTramite.getNbModeloOtro():listaTramite.getcModelo());
    		parametros.put( "nbTipo",listaTramite.getcVehiculo()!=null?listaTramite.getcVehiculo():"desconocido");
    		parametros.put( "nbColor",listaTramite.getcColor()!=null?listaTramite.getcColor():"desconocido");
    		parametros.put( "cdPlaca",listaTramite.getCdPlaca());
    		parametros.put( "nbDocumentoOtro",listaTramite.getNbDocOtro()!=null?listaTramite.getNbDocOtro():null);
    		parametros.put( "txCc", listaTramite.getTxCc()!=null?listaTramite.getTxCc():"SSC");
    		parametros.put( "nbHechos",listaTramite.getTxHechos());
    		parametros.put( "tipoDocumento", listaTramite.getcDocumento());
    		parametros.put("folioInfraccion", listaTramite.getFoliosDeInfraccion()!=null?listaTramite.getFoliosDeInfraccion():" ");
    		parametros.put( "nbOficial", listaTramite.getNbOficial()+" "+listaTramite.getApellidoPaternoOficial()+" "+listaTramite.getApellidoMaternoOficial());
            parametros.put( "logotipoCdmxSsp", inputStreamLogoSSP);
    		parametros.put( "logotipoTeclo", inputStreamLogoTeclo);
    		
			reporte.write(JasperRunManager.runReportToPdf(rutaArchivo, parametros,new JREmptyDataSource()));
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reporte;

		
	}
	
	@Transactional
	@Override
	public ExpedienteTramiteVO guardarExpedineteTramite(ExpedienteTramiteVO expedienteTramiteVO, Long empId) {
		ExpedienteTramiteDTO expedienteDTO = new ExpedienteTramiteDTO();
		ExpedienteTramiteEstatusDTO estatusExpediente = new ExpedienteTramiteEstatusDTO();
				
		expedienteDTO.setFolioTramite(expedienteTramiteVO.getFolioTramite());
		expedienteDTO.setNbArchivo(expedienteTramiteVO.getFolioTramite()+"_"+expedienteTramiteVO.getTipoExp()+".pdf");
		expedienteDTO.setArchivo(arrayBytesToBlob(expedienteTramiteVO.getArchivo()));
		expedienteDTO.setTipoExp(expedienteTramiteVO.getTipoExp());
		expedienteDTO.setStActivo(1);
		
		expedienteDTO.setCreadoPor(empId);
		expedienteDTO.setFhCreacion(new Date());
		expedienteDTO.setModificadoPor(empId);
		expedienteDTO.setFhModificacion(new Date());
		expedienteTramiteDAOHibernate.saveOrUpdate(expedienteDTO);
		
		estatusExpediente.setFolioTramite(expedienteTramiteVO.getFolioTramite());
		estatusExpediente.setStExpediente(true);
		estatusExpediente.setModificadoPor(empId);
		estatusExpediente.setFhModificacion(new Date());
		expedienteTramiteEstatusDAOHibernate.saveOrUpdate(estatusExpediente);
		
		return expedienteTramiteVO;
	}
	
	public static Blob arrayBytesToBlob(byte[] archivo){
		Blob blob = NonContextualLobCreator.INSTANCE.wrap(NonContextualLobCreator.INSTANCE.createBlob(archivo));		
		return blob;
	}
	
	@Transactional
	@Override
	public List<ACTramiteConsultaVO> consultaDefaultModificacion(Boolean op) {
		Map<String, String> parametros = catalogoService.getParametrosBD(2, null);
		int nuDias = Integer.parseInt(parametros.get("DIAS_CON_DEFAULT_AC"));
		List<AtencionCiudadanaTramitesDTO> listDTO = tramitesAtencionCiudadanaDAO.consultaDefaultModificacion(nuDias, op);
		List<ACTramiteConsultaVO> listVO = ResponseConverter.converterLista(new ArrayList<>(), listDTO, ACTramiteConsultaVO.class);
		for(ACTramiteConsultaVO objeto : listVO) {
			List<CatTipoTramiteDTO> listCatTipoTramiteDTO = catTipoTramiteDAO.getTramitePorListId(objeto.getTipoTramite());
			List<TipoTramiteVO> listTipoTramiteVO = ResponseConverter.converterLista(new ArrayList<>(), listCatTipoTramiteDTO, TipoTramiteVO.class);
			
			List<CatTipoDocumentoDTO> listaTipoDocumentoDTO = catTipoDocumentoDAO.getcatalogoDocumentoID(objeto.getIdTipoDoc());
			List<TipoDocumentoVO> listTipoDocumentoVO = ResponseConverter.converterLista(new ArrayList<>(), listaTipoDocumentoDTO, TipoDocumentoVO.class);
			
			objeto.setNbCiudadano(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbCiudadano()));
			objeto.setNbCPaterno(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbCPaterno()));
			objeto.setNbMaterno(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbMaterno()));
			objeto.setTxCCorreo(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getTxCCorreo()));
			objeto.setTxCCalle(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getTxCCalle()));
			objeto.setTxCColonia(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getTxCColonia()));
			objeto.setNuCInt(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNuCInt()));
			objeto.setNuCExt(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNuCExt()));
			objeto.setNuCTelefono(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNuCTelefono()));
			objeto.setNbEmpresa(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbEmpresa()));
			
			objeto.setListaTipoTramite(listTipoTramiteVO);
			objeto.setListaTipoDocumento(listTipoDocumentoVO);
		}
		
		if(!listVO.isEmpty()) {
			Date fecha = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha); // Configuramos la fecha que se recibe
			calendar.add(Calendar.DAY_OF_YEAR, (nuDias *(-1)));//con los nuevos días añadidos
			
			Format formatter = new SimpleDateFormat("dd/MM/YYYY");
			
			
			this.reporteConsulta = generaReporteConsulta(listVO, formatter.format(calendar.getTime()), formatter.format(fecha), 1, "", 0, null, null, null, null, null, null, false);
		}
		return listVO;
	}
	
	@Override
	@Transactional
	public List<ACTramiteConsultaVO> buscarTramitesHistorico(String fechaInicio, String fechaFin,
			Integer paramBusqueda, String valorBusqueda, Integer Atendido,
			String avanzadoNombre,String avanzadoAP, String avanzadoAM,
			String avanzadoTel, String avanzadoCorreo,
            String avanzadoEmpresa, Boolean busquedaAvanzada) { 
		if(paramBusqueda==4)
		{
			valorBusqueda=tramitesAtencionCiudadanaDAO.encriptarCampo(valorBusqueda);
		}
		
		if(busquedaAvanzada)
		{
			if(!avanzadoNombre.equals(""))
			{
				avanzadoNombre=tramitesAtencionCiudadanaDAO.encriptarCampo(avanzadoNombre);
			}
			if(!avanzadoAP.equals(""))
			{
				avanzadoAP=tramitesAtencionCiudadanaDAO.encriptarCampo(avanzadoAP);
			}
			if(!avanzadoAM.equals(""))
			{
				avanzadoAM=tramitesAtencionCiudadanaDAO.encriptarCampo(avanzadoAM);
			}
			if(!avanzadoCorreo.equals(""))
			{
				avanzadoCorreo=tramitesAtencionCiudadanaDAO.encriptarCampo(avanzadoCorreo);
			}
			if(!avanzadoEmpresa.equals(""))
			{
				avanzadoEmpresa=tramitesAtencionCiudadanaDAO.encriptarCampo(avanzadoEmpresa);
			}
		}
		
		Map<String, String> parametros= obtenerParametrosAyuda();
		List<AtencionCiudadanaTramitesDTO> tramites = tramitesAtencionCiudadanaDAO.obtieneTramite(fechaInicio, fechaFin, paramBusqueda, valorBusqueda, Atendido,
				avanzadoNombre,avanzadoAP, avanzadoAM,
			    avanzadoTel, avanzadoCorreo,
	            avanzadoEmpresa, busquedaAvanzada,Integer.parseInt(parametros.get("CANTIDAD_REGISTROS_MOSTRAR_AC")));
		
		//tramites.removeAll(Collections.singleton(null));
		List<ACTramiteConsultaVO> listVO = ResponseConverter.converterLista(new ArrayList<>(), tramites, ACTramiteConsultaVO.class);
	
		
		for(ACTramiteConsultaVO objeto : listVO) {
			List<CatTipoTramiteDTO> listCatTipoTramiteDTO = catTipoTramiteDAO.getTramitePorListId(objeto.getTipoTramite());
			List<TipoTramiteVO> listTipoTramiteVO = ResponseConverter.converterLista(new ArrayList<>(), listCatTipoTramiteDTO, TipoTramiteVO.class);
			objeto.setListaTipoTramite(listTipoTramiteVO);
			List<CatTipoDocumentoDTO> listaTipoDocumentoDTO = catTipoDocumentoDAO.getcatalogoDocumentoID(objeto.getIdTipoDoc());
			List<TipoDocumentoVO> listTipoDocumentoVO = ResponseConverter.converterLista(new ArrayList<>(), listaTipoDocumentoDTO, TipoDocumentoVO.class);
			
			objeto.setNbCiudadano(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbCiudadano()));
			objeto.setNbCPaterno(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbCPaterno()));
			objeto.setNbMaterno(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbMaterno()));
			objeto.setTxCCorreo(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getTxCCorreo()));
			objeto.setTxCCalle(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getTxCCalle()));
			objeto.setTxCColonia(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getTxCColonia()));
			objeto.setNuCInt(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNuCInt()));
			objeto.setNuCExt(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNuCExt()));
			objeto.setNuCTelefono(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNuCTelefono()));
			objeto.setNbEmpresa(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbEmpresa()));
			
		
			
			objeto.setListaTipoDocumento(listTipoDocumentoVO);
			
			
		}
		
		 if(busquedaAvanzada&&!avanzadoTel.equals(""))
      	  {
		List<ACTramiteConsultaVO> copiaFiltroTel=new ArrayList<ACTramiteConsultaVO>(listVO);
		int tamañoLista=listVO.size();
		  for(int i=0;i<tamañoLista;i++)
		    {
                String tel4digitos= !copiaFiltroTel.get(i).getNuCTelefono().equals("")? copiaFiltroTel.get(i).getNuCTelefono().substring(10, 14):"";
	             if(!avanzadoTel.equals(tel4digitos))
	             {
	            	 ACTramiteConsultaVO actual=copiaFiltroTel.get(i);
	            	 listVO.remove(actual);
	             }
	       		
	       	  }
		}
		
		
		
		if(!listVO.isEmpty())
		{
		reporteConsulta = generaReporteConsulta(listVO, fechaInicio, fechaFin,paramBusqueda,valorBusqueda,Atendido,avanzadoNombre,avanzadoAP, avanzadoAM,
			    avanzadoTel, avanzadoCorreo,
	            avanzadoEmpresa, busquedaAvanzada);
		}
		return listVO;
	}
	
	  @Override
	  @SuppressWarnings("rawtypes")
	  public Map getReporteExcelConsulta() {
	  	return this.reporteConsulta;
	  }
	  
	  @Override
	  public byte[] obtenerBytesExpediente(String folioExpediente){
		  byte[] blobAsBytes=null;
		  ExpedienteACVO expedienteACVO=new ExpedienteACVO();
		  expedienteACVO=tramitesACMyBatisDAO.getBlobPDFExpediente(folioExpediente);
		  //byte[] blobAsBytes=(byte[])tramitesACMyBatisDAO.getBlobPDFExpediente(folioExpediente);
		/*  try{
			  
			int blobLength = (int) expedienteACVO.getLbExpediente().length();  
			  blobAsBytes = expedienteACVO.getLbExpediente().getBytes(1, blobLength);
		  }catch(SQLException e){
			    e.printStackTrace();
	      }*/
		 
		  return expedienteACVO.getLbExpediente();
	  }
	
	

	
	/***Crea el reporte excel de la consulta realizada
	 * @author David Guerra
	 * @param aCTramiteVO
	 * @param fechaInicio
	 * @param fechaFin
	 * @return Map
	 */
	@SuppressWarnings({"unchecked", "rawtypes" })
	public Map generaReporteConsulta(List<ACTramiteConsultaVO>  aCTramiteVO,
			String fechaInicio, String fechaFin,Integer paramBusqueda,
			String valorBusqueda, Integer atendido, String avanzadoNombre,String avanzadoAP, String avanzadoAM,
			String avanzadoTel, String avanzadoCorreo,
            String avanzadoEmpresa, Boolean busquedaAvanzada) {
		
		Map mapReporte = new HashMap();
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		
		titulos.add("Folio Trámite");
		titulos.add("Tipo Trámite");
		titulos.add("Fecha Támite");
		titulos.add("Placa/Permiso");
		titulos.add("Nombre Ciudadano");
		titulos.add("Apellido Paterno Ciudadano");
		titulos.add("Apellido Materno Ciudadano");
		titulos.add("Nombre Empresa");
		titulos.add("Placa Oficial");
		titulos.add("Nombre Oficial");
		titulos.add("Apellido Paterno Oficial");
		titulos.add("Apellido Materno Oficial");
		titulos.add("Tiene Expediente");
		titulos.add("Hechos");
		
		
		encabezadoTitulo.add(titulos);
			
		String fechaForTitulo = "";
	    String nombreParamtroBusqueda="";
	    String valorAtendido="";
		
		propiedadesReporte.setNombreReporte("Consulta General AC");
		propiedadesReporte.setExtencionArchvio(".xls");
		propiedadesReporte.setTituloExcel("Consulta General Trámites (Atención Ciudadana)");
		List<String> subtitulos = new ArrayList<String>();
		
		if(!fechaInicio.equals("") && !fechaFin.equals("")){
			subtitulos.add("Fecha de Inicio: " + fechaInicio);
			subtitulos.add("Fecha de Fin: " + fechaFin);
		}else{
			RutinasTiempoImpl rutinastiempo = new RutinasTiempoImpl();
			fechaForTitulo = rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date());
			subtitulos.add("Fecha de Fin: " + fechaForTitulo);
		}
		
		if(!busquedaAvanzada)
		{
	      if(paramBusqueda!=1)
          {
	    	
        	  if(paramBusqueda==2)
        	  {
        		  nombreParamtroBusqueda="ID TRÁMITE";
        	  }else if(paramBusqueda==3)
        	  {
        		  nombreParamtroBusqueda="PLACA VEHICULAR";
        	  }else if(paramBusqueda==4)
        	  {
        		  nombreParamtroBusqueda="APELLIDO PATERNO CIUDADANO";
        		  valorBusqueda=tramitesAtencionCiudadanaDAO.desencriptarCampo(valorBusqueda);
        	  }else if(paramBusqueda==5)
        	  {
        		  nombreParamtroBusqueda="APELLIDO PATERNO EMPLEADO";
        	  }
      		subtitulos.add("Búsqueda: " + nombreParamtroBusqueda+": "+valorBusqueda);
          }
		}
		else
		{
			subtitulos.add("Búsqueda Avanzada:" );
	       	  if(!avanzadoNombre.equals(""))
        	  {
	       		subtitulos.add("Nombre del Ciudadano: "+ tramitesAtencionCiudadanaDAO.desencriptarCampo(avanzadoNombre));
        	  }
        	  
        	  if(!avanzadoAP.equals(""))
        	  {
        		  subtitulos.add("Apellido Paterno: "+ tramitesAtencionCiudadanaDAO.desencriptarCampo(avanzadoAP));
        	  }
        	  
        	  if(!avanzadoAM.equals(""))
        	  {
        		  subtitulos.add("Apellido Materno: "+  tramitesAtencionCiudadanaDAO.desencriptarCampo(avanzadoAM));
        	  }
        	  
        	  if(!avanzadoTel.equals(""))
        	  {
        		
        		  subtitulos.add("Últimos 4 Dígitos Teléfono: "+ avanzadoTel);
        	  }
        	  
        	  if(!avanzadoCorreo.equals(""))
        	  {
        		  subtitulos.add("Correo: "+ tramitesAtencionCiudadanaDAO.desencriptarCampo(avanzadoCorreo));
        	  }
        	  
        	  if(!avanzadoEmpresa.equals(""))
        	  {
        		  subtitulos.add("Nombre de la Empresa: "+ tramitesAtencionCiudadanaDAO.desencriptarCampo(avanzadoEmpresa));
        	  }
		}
          
          if(atendido!=0)
          {
        	  if(atendido==1)
        	  {
        		  valorAtendido="Atendido";
        	  }else if(atendido==2)
        	  {
        		  valorAtendido="Pendiente";
        	  }
        	  subtitulos.add("Estatus trámite: " + valorAtendido);
          }
		

		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(ACTramiteConsultaVO actual : aCTramiteVO){
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			String fechaEmision=formatoFecha.format(actual.getFhAlta());
			
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(actual.getIdACTramite());
			 
			String listaTemporal="";
			int k=0;
             for(TipoTramiteVO actualTramite:actual.getListaTipoTramite())
             {
            	
            	 listaTemporal+=actualTramite.getNbTramite();
            	 
            	 if((k+1)!=actual.getListaTipoTramite().size())
            			 {
            		 listaTemporal+=", ";
            			 }
            	 k++;
             }
     
			listaContenido1.add(listaTemporal);
			listaContenido1.add(fechaEmision);
			listaContenido1.add(actual.getCdCPlaca());
			listaContenido1.add(actual.getNbCiudadano());
			listaContenido1.add(actual.getNbCPaterno());
			listaContenido1.add(actual.getNbMaterno());
			listaContenido1.add(actual.getNbEmpresa());
			listaContenido1.add(actual.getEmpId().getEmpPlaca());
			listaContenido1.add(letraCapitalUtil.covertirNombreMayusYMinus(actual.getEmpId().getEmpNombre(),false));	
			listaContenido1.add(letraCapitalUtil.covertirNombreMayusYMinus(actual.getEmpId().getEmpApePaterno(),true));
			listaContenido1.add(letraCapitalUtil.covertirNombreMayusYMinus(actual.getEmpId().getEmpApeMaterno(),true));
			listaContenido1.add(actual.isStExpediente() == true ? "Sí" : "No");
			listaContenido1.add(actual.getTxHechos());
			contenido1.add(listaContenido1);
		}
		
		contenido.add(contenido1);
		
		propiedadesReporte.setSubtitulos(subtitulos);
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
			mapReporte.put("nombre", propiedadesReporte.getNombreReporte());
			mapReporte.put("reporte", reporte);
		} catch (IOException e) {
 			e.printStackTrace();
		}
	
 		return mapReporte;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ACTramiteConsultaVO> buscarTramitesParaModificar(String fechaInicio, String fechaFin, Integer paramBusqueda,
			String valorBusqueda, Integer Atendido) {
		Map<String, String> parametros= obtenerParametrosAyuda();
		List<AtencionCiudadanaTramitesDTO> tramites = tramitesAtencionCiudadanaDAO.obtieneTramite(fechaInicio, fechaFin, paramBusqueda, valorBusqueda, Atendido,""
				,"","","","","",false,Integer.parseInt(parametros.get("CANTIDAD_REGISTROS_MOSTRAR_AC")));
		List<ACTramiteConsultaVO> listVO = ResponseConverter.converterLista(new ArrayList<>(), tramites, ACTramiteConsultaVO.class);
		for(ACTramiteConsultaVO objeto : listVO) {
			List<CatTipoTramiteDTO> listCatTipoTramiteDTO = catTipoTramiteDAO.getTramitePorListId(objeto.getTipoTramite());
			List<TipoTramiteVO> listTipoTramiteVO = ResponseConverter.converterLista(new ArrayList<>(), listCatTipoTramiteDTO, TipoTramiteVO.class);
			List<CatTipoDocumentoDTO> listaTipoDocumentoDTO = catTipoDocumentoDAO.getcatalogoDocumentoID(objeto.getIdTipoDoc());
			List<TipoDocumentoVO> listTipoDocumentoVO = ResponseConverter.converterLista(new ArrayList<>(), listaTipoDocumentoDTO, TipoDocumentoVO.class);
			objeto.setListaTipoTramite(listTipoTramiteVO);
			objeto.setListaTipoDocumento(listTipoDocumentoVO);
			
			objeto.setNbCiudadano(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbCiudadano()));
			objeto.setNbCPaterno(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbCPaterno()));
			objeto.setNbMaterno(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbMaterno()));
			objeto.setTxCCorreo(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getTxCCorreo()));
			objeto.setTxCCalle(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getTxCCalle()));
			objeto.setTxCColonia(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getTxCColonia()));
			objeto.setNuCInt(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNuCInt()));
			objeto.setNuCExt(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNuCExt()));
			objeto.setNuCTelefono(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNuCTelefono()));
			objeto.setNbEmpresa(tramitesAtencionCiudadanaDAO.desencriptarCampo(objeto.getNbEmpresa()));
			
		}
		return listVO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	//@Transactional
	public ByteArrayOutputStream vistaPrevia(ACTramiteVO altaTramiteVO) {
		ByteArrayOutputStream reporte =  new ByteArrayOutputStream();
		String rutaArchivo;
		String rutalogoSSP;
		String rutalogoTeclo;
		InputStream inputStreamLogoSSP = null;
		InputStream inputStreamLogoTeclo  = null;
		Map parametros = new HashMap();
		
	
		
		
		rutaArchivo = context.getRealPath("/WEB-INF/jasper/atencionciudadana/reporteTramites.jasper");
		rutalogoSSP ="WEB-INF/imagenes/logo_CDMX_SSC.png";
		rutalogoTeclo = "WEB-INF/imagenes/logoAC.png";
		try {
			File fileLogoSSP = new ClassPathResource(rutalogoSSP).getFile();
			File fileLogoTeclo  = new ClassPathResource(rutalogoTeclo).getFile();
			
			inputStreamLogoSSP = new FileInputStream(fileLogoSSP);
			inputStreamLogoTeclo  = new FileInputStream(fileLogoTeclo);
		} catch (IOException e1) {
			
		}
		RutinasTiempoImpl rutina=new RutinasTiempoImpl();
		Date stringDate=new Date();
		

	
	
        
        try {
        	parametros.put( "idTramite",altaTramiteVO.getIdacTramite()!=null?altaTramiteVO.getIdacTramite():"Vista Previa");
        	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        	altaTramiteVO.setNbOficial(letraCapitalUtil.covertirNombreMayusYMinus(altaTramiteVO.getNbOficial(),false));
        	altaTramiteVO.setApellidoPaternoOficial(letraCapitalUtil.covertirNombreMayusYMinus(altaTramiteVO.getApellidoPaternoOficial(),true));
        	altaTramiteVO.setApellidoMaternoOficial(letraCapitalUtil.covertirNombreMayusYMinus(altaTramiteVO.getApellidoMaternoOficial(),true));
        	Date fecha =formateador.parse(altaTramiteVO.getFhAlta());
    		parametros.put( "fhAlta",rutina.getStringDateFromFormta("dd/MM/yyyy HH:mm",fecha));
    		parametros.put( "tipoTramite",altaTramiteVO.getcTramite());
    		parametros.put( "nbNombre",altaTramiteVO.getNbCiudadano()!=null?altaTramiteVO.getNbCiudadano():"");
    		parametros.put( "nbEmpresa",altaTramiteVO.getNbEmpresa()!=null?altaTramiteVO.getNbEmpresa():"" );
    		parametros.put( "nbPaterno", altaTramiteVO.getNbPaterno()!=null?altaTramiteVO.getNbPaterno():"");
    		parametros.put( "nbMaterno",altaTramiteVO.getNbMaterno()!=null?altaTramiteVO.getNbMaterno():"");
    		parametros.put( "nuTelefono",altaTramiteVO.getNuTelefono());
    		parametros.put( "txCorreo", altaTramiteVO.getTxCorreo());
    		
    		parametros.put( "nbCalle", altaTramiteVO.getTxCalle()!=null?altaTramiteVO.getTxCalle():"Calle desconocida");
    		parametros.put( "nuInt",altaTramiteVO.getNuInt()!=null?altaTramiteVO.getNuInt():"S/N");
    		parametros.put( "nuExt", altaTramiteVO.getNuExt()!=null? altaTramiteVO.getNuExt():"S/N");
    		parametros.put( "nbColonia", altaTramiteVO.getTxColonia()!=null?altaTramiteVO.getTxColonia():"Colonia desconocida");
    		parametros.put( "nbDelegacion",altaTramiteVO.getcDelegacion()!=null?altaTramiteVO.getcDelegacion():"Delegación desconocida");
    		parametros.put( "nbEstado", altaTramiteVO.getcEdo()!=null? altaTramiteVO.getcEdo():"Estado desconocido");
    		parametros.put( "nbMarca",altaTramiteVO.getcMarca().equals("OTRO")?altaTramiteVO.getNbMarcaOtro():altaTramiteVO.getcMarca());
    		parametros.put( "nbTipo",altaTramiteVO.getcVehiculo()!=null?altaTramiteVO.getcVehiculo():"desconocido");
    		parametros.put( "nbModelo", altaTramiteVO.getcModelo().equals("OTRO")?altaTramiteVO.getNbModeloOtro():altaTramiteVO.getcModelo());
    		parametros.put( "nbColor",altaTramiteVO.getcColor()!=null?altaTramiteVO.getcColor():"desconocido");
    		parametros.put( "cdPlaca",altaTramiteVO.getCdPlaca());
    		parametros.put( "nbDocumentoOtro",altaTramiteVO.getNbDocOtro()!=null?altaTramiteVO.getNbDocOtro():null);
    		parametros.put( "txCc", altaTramiteVO.getTxCc()!=null?altaTramiteVO.getTxCc():"SSC");
    		parametros.put( "nbHechos",altaTramiteVO.getTxHechos());
    		parametros.put( "tipoDocumento", altaTramiteVO.getcDocumento());
    		parametros.put("folioInfraccion", altaTramiteVO.getFoliosDeInfraccion()!=null?altaTramiteVO.getFoliosDeInfraccion():" ");
    		parametros.put( "nbOficial", altaTramiteVO.getNbOficial());
    		
    		parametros.put( "logotipoCdmxSsp", inputStreamLogoSSP);
    		parametros.put( "logotipoTeclo", inputStreamLogoTeclo);
			reporte.write(JasperRunManager.runReportToPdf(rutaArchivo, parametros,new JREmptyDataSource()));
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return reporte;

		
	}

	@Transactional
	@Override
	public ACTramiteConsultaVO modificaTramite(ACTramiteConsultaVO modficacaTramiteVO, Long empId) {
		AtencionCiudadanaTramitesDTO tramiteDTO = new AtencionCiudadanaTramitesDTO();
		ResponseConverter.copiarPropriedades(tramiteDTO, modficacaTramiteVO);
		
		tramiteDTO.setNbCiudadano(tramitesAtencionCiudadanaDAO.encriptarCampo(modficacaTramiteVO.getNbCiudadano()));
		tramiteDTO.setNbCPaterno(tramitesAtencionCiudadanaDAO.encriptarCampo(modficacaTramiteVO.getNbCPaterno()));
		tramiteDTO.setNbMaterno(tramitesAtencionCiudadanaDAO.encriptarCampo(modficacaTramiteVO.getNbMaterno()));
		tramiteDTO.setTxCCorreo(tramitesAtencionCiudadanaDAO.encriptarCampo(modficacaTramiteVO.getTxCCorreo()));
		tramiteDTO.setTxCCalle(tramitesAtencionCiudadanaDAO.encriptarCampo(modficacaTramiteVO.getTxCCalle()));
		tramiteDTO.setTxCColonia(tramitesAtencionCiudadanaDAO.encriptarCampo(modficacaTramiteVO.getTxCColonia()));
		tramiteDTO.setNuCInt(tramitesAtencionCiudadanaDAO.encriptarCampo(modficacaTramiteVO.getNuCInt()));
		tramiteDTO.setNuCExt(tramitesAtencionCiudadanaDAO.encriptarCampo(modficacaTramiteVO.getNuCExt()));
		tramiteDTO.setNuCTelefono(tramitesAtencionCiudadanaDAO.encriptarCampo(modficacaTramiteVO.getNuCTelefono()));
		tramiteDTO.setNbEmpresa(tramitesAtencionCiudadanaDAO.encriptarCampo(modficacaTramiteVO.getNbEmpresa()));
		
		tramiteDTO.setModificadoPor(empId);
		tramiteDTO.setfhModificacion(new Date());
		tramitesAtencionCiudadanaDAO.saveOrUpdate(tramiteDTO);
		return modficacaTramiteVO;
	}

	
		
	@Override
	public AtenCiudaCamposRequeridosVO obtenerJsonCamposRequeridos()
	{
		String cadenaJson=tramitesACMyBatisDAO.getJsonCamposRequeridos();
		Gson gson = new Gson();
        AtenCiudaCamposRequeridosVO atenCiudaCamposRequeridosVO=gson.fromJson(cadenaJson, AtenCiudaCamposRequeridosVO.class);;
		
		return atenCiudaCamposRequeridosVO;
	}
	
	@Override
	public Map<String, String> obtenerParametrosAyuda()
	{
		
		List<Map<String, String>> listaParametros=tramitesACMyBatisDAO.getInformacionTooltipAyuda();
		Map<String, String> parametros = new HashMap<String, String>();
		for(Map<String, String> registro : listaParametros) {
		parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		
		}
		
		return parametros;
	}

	@Transactional
	@Override
	public ExpedienteTramiteVO descargarExpedineteTramite(String folioTramite, String tipoExp) {
		ExpedienteTramiteVO expedienteVO = new ExpedienteTramiteVO();
		ExpedienteTramiteDTO expedienteDTO = expedienteTramiteDAOHibernate.buscarExpedintePorFolioTipo(folioTramite, tipoExp);
		Map<String, String> parametros = catalogoService.getParametrosBD(0, null);
		if(expedienteDTO!=null) {
			expedienteVO.setFolioTramite(expedienteDTO.getFolioTramite());
			expedienteVO.setNbArchivo(expedienteDTO.getNbArchivo());
			expedienteVO.setTipoExp(expedienteDTO.getTipoExp());
			expedienteVO.setArchivo(blobToArrayBytes(expedienteDTO.getArchivo()));
			if(expedienteVO.getArchivo()!=null && expedienteVO.getArchivo().length > 0) {
				expedienteVO.setExisteEnBD(true);
			}else {
				expedienteVO.setExisteEnBD(false);
				String ruta = tramitesACMyBatisDAO.getRutaExpediente(parametros.get("CONS_RUTA_EXPEDIENTE"), folioTramite, expedienteDTO.getIdExpediente());
				expedienteVO.setRuraExpediente(parametros.get("URL_STORAGE_EXPAC") + ruta);
			}
		}
		return expedienteVO;
	}
	
	private byte[] blobToArrayBytes(Blob blob){
		byte[] data=null;
		if(blob!=null){
			try {
				data = blob.getBytes(1, (int)blob.length());
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}		     
		return data;
	}

	@Transactional
	@Override
	public ExpedienteTramiteVO remplazarExpedineteTramite(ExpedienteTramiteVO expedienteTramiteVO, Long empId) {
		ExpedienteTramiteDTO expedienteDTO = new ExpedienteTramiteDTO();
		ExpedienteTramiteEstatusDTO estatusExpediente = new ExpedienteTramiteEstatusDTO();
				
		expedienteDTO = expedienteTramiteDAOHibernate.buscarExpedintePorFolioTipo(expedienteTramiteVO.getFolioTramite(), "TODO");
		if(expedienteDTO!=null) {
			expedienteDTO.setArchivo(arrayBytesToBlob(expedienteTramiteVO.getArchivo()));
			expedienteDTO.setModificadoPor(empId);
			expedienteDTO.setFhModificacion(new Date());
			expedienteTramiteDAOHibernate.saveOrUpdate(expedienteDTO);
			
			estatusExpediente.setFolioTramite(expedienteTramiteVO.getFolioTramite());
			estatusExpediente.setStExpediente(true);
			estatusExpediente.setModificadoPor(empId);
			estatusExpediente.setFhModificacion(new Date());
			expedienteTramiteEstatusDAOHibernate.saveOrUpdate(estatusExpediente);
		}else {
			return null;
		}
		return expedienteTramiteVO;
	}

	@Transactional
	@Override
	public Boolean nuevoSeguimientoTramite(ACSegTramiteVO acSegTramite, String[] foliosDeInfraccion) {
		Boolean exito=false;
		ACTramiteDetalleVO acTramiteDetalle = null;
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		try {	
			String sigSegDetalleString = atencionCiudadadanaDAO.obtenerSiguinteValorSeguimientoDetalle(
					parametros.get("Q_SEC_AC_TRAMITE_DET"));
			Long sigSegDetalle = Long.valueOf(sigSegDetalleString);
			
			if(foliosDeInfraccion.length>0) {
				acTramiteDetalle = new ACTramiteDetalleVO();
				acTramiteDetalle.setIdTramiteDetalle(sigSegDetalle);
				acTramiteDetalle.setIdAcTramite(acSegTramite.getIdAcTramite());
				acTramiteDetalle.setIdTipoTramite(acSegTramite.getIdTipoTramite());
				acTramiteDetalle.setCtSolicitados(foliosDeInfraccion.length);
				acTramiteDetalle.setCtAtendidos(0);
				acTramiteDetalle.setStActivo(1);
				acTramiteDetalle.setIdUsrCreacion(acSegTramite.getIdUsrCreacion());
				acTramiteDetalle.setIdUsrModifica(acSegTramite.getIdUsrModifica());
				acTramiteDetalle.setStSegTramite(1);
				acTramiteDetalle.setTxComentarioTram("");
				exito = this.nuevoACTramiteDetalle(acTramiteDetalle);
			}
			if(exito) {
				for(int x = 0; x<foliosDeInfraccion.length; x++) {
					acSegTramite.setIdTramiteDetalle(sigSegDetalle);
					acSegTramite.setCdValor(foliosDeInfraccion[x]);
					exito = atencionCiudadadanaDAO.altaSeguimientoTramite(acSegTramite);
				}
			}
			
			
		}catch (Exception e) {
			exito=false;
			System.err.println("AtencionCiudadanaServiceImpl - nuevoSeguimientoTramite "+ e);
		}
		return exito;
	}
	
	@Transactional
	@Override
	public Boolean modificarSeguimientoTramite(ACSegTramiteVO acSegTramite, String idAcTramite, Integer ctSolicitados, 
			Long idUsrModifica, String[] foliosInfraccionNuevos, String[] foliosInfraccionQuitar) {
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		Boolean cambios = false;
		
		if(foliosInfraccionQuitar!=null) {
			for(int x=0; x<foliosInfraccionQuitar.length; x++) {
				atencionCiudadadanaDAO.desactivaSeguimientoTramite(
						parametros.get("Q_DESACTIVA_TAI052D"), idAcTramite, 
						foliosInfraccionQuitar[x], 
						1, idUsrModifica);
				cambios = true;
			}
		}
		
		if(foliosInfraccionNuevos!=null) {
			for(int x = 0; x<foliosInfraccionNuevos.length; x++) {
				acSegTramite.setCdValor(foliosInfraccionNuevos[x]);
				atencionCiudadadanaDAO.altaSeguimientoTramite(acSegTramite);
				cambios = true;
			}
		}
		
		if(cambios) {
			return atencionCiudadadanaDAO.actualizarACTramiteDetalle(
					parametros.get("Q_MODIFICA_TAI055D"), idAcTramite, ctSolicitados, 
					0, 1, idUsrModifica, "");
		}
		return cambios;
	}

	@Transactional
	@Override
	public Boolean desactivaSeguimientoTramite(
			Long idSegTramite, Integer stSegTramite, 
			Long idUsrModifica) {
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		return atencionCiudadadanaDAO.desactivaSeguimientoTramite(
				parametros.get("Q_DESACTIVA_TAI052D"), "", "", 
				stSegTramite, idUsrModifica);
	}

	@Transactional
	@Override
	public List<ACSegTramiteConsultaVO> getListaSeguimientosTramite(int tipoBusqueda, String valor, Integer idTipoTramite,
			Integer stSegTramite, int tipoFecha, String fhInicio, String fhFin) {
		List<ACSegTramiteConsultaVO> listSeguimientoTramite = new ArrayList<ACSegTramiteConsultaVO>();
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		String consulta = parametros.get("Q_BUSQUEDA_TODOS_P1");
		
		switch(tipoFecha) {
		case 1:
			consulta = consulta + parametros.get("Q_BUSQUEDA_FHCREACION_P2");
			break;
		case 2:
			consulta = consulta + parametros.get("Q_BUSQUEDA_FHMODIFICA_P2");
			break;
		}
		listSeguimientoTramite = atencionCiudadadanaDAO.getListaSeguimientoTramite(consulta, 
				tipoBusqueda, valor, idTipoTramite, stSegTramite, fhInicio, fhFin);
		return listSeguimientoTramite;
	}

	@Transactional
	@Override
	public Boolean nuevoACTramiteDetalle(ACTramiteDetalleVO acTramiteDetalle) {
		return atencionCiudadadanaDAO.nuevoACTramiteDetalle(acTramiteDetalle);
	}

	@Transactional
	@Override
	public Boolean actualizarACTramiteDetalle(
			String idAcTramite, 
			Integer ctSolicitados, 
			Integer ctAtendidos,
			Integer stSegTramite,
			Long idUsrModifica, 
			String txComentarioTram) {
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		return atencionCiudadadanaDAO.actualizarACTramiteDetalle(
				parametros.get("Q_MODIFICA_TAI055D"), idAcTramite, ctSolicitados, ctAtendidos,  
				stSegTramite, idUsrModifica, txComentarioTram);
	}

	@Transactional
	@Override
	public Boolean realizarCambioDePersona(String idAcTramite, Long idUsrModifica) {
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		return atencionCiudadadanaDAO.realizarCambioDePersona(parametros.get("Q_MOD_TIPO_PERSONA"), idAcTramite, idUsrModifica);
	}
	
	
	@Transactional
	@Override
	public Boolean actualizarACTramiteDetalleFhMod(
			String idAcTramite, Long idUsrModifica, String txComentario) {
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		return atencionCiudadadanaDAO.actualizarACTramiteDetalleFhMod(
				parametros.get("Q_MODIFICA_TAI055D2"), idAcTramite, idUsrModifica, txComentario);
	}
	
	@Transactional
	@Override
	public Boolean actualizarACTramiteSeguimientoFhMod(
			String idAcTramite, Long idUsrModifica) {
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		return atencionCiudadadanaDAO.actualizarACTramiteSeguimiento(
				parametros.get("Q_MODIFICA_TAI052D2"), idAcTramite, idUsrModifica);
	}
	
	@Transactional
	@Override
	public Boolean actualizarACTramiteFhMod(
			String idAcTramite, Long idUsrModifica) {
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		return atencionCiudadadanaDAO.actualizarACTramiteSeguimiento(
				parametros.get("Q_MODIFICA_TAI045D"), idAcTramite, idUsrModifica);
	}

	@Transactional
	@Override
	public List<ACSegDetTramiteConsultaVO> getListInfCambioDePersona(String placaVehicular, String infracNum) {
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		return atencionCiudadadanaDAO.getListInfCambioDePersona(
				parametros.get("Q_BUSCA_INF_CAMBIOTP"), placaVehicular, infracNum);
	}

	@Transactional
	@Override
	public List<ACSegDetTramiteConsultaVO> getInfCambioDePersona(String listInfracciones) {
		List<ACSegDetTramiteConsultaVO> listInfraccionesSeg = new ArrayList<ACSegDetTramiteConsultaVO>();
		Map<String, String> parametros = catalogoService.getParametrosBD(1, null);
		String[] infracciones = listInfracciones.split(",");
		for(int x=0;x<infracciones.length;x++) {
			ACSegDetTramiteConsultaVO acSegDetTramiteConsulta = atencionCiudadadanaDAO.getInfCambioDePersona(
					parametros.get("Q_BUSCA_INF_CAMBIOTP"), null, infracciones[x]);
			if(acSegDetTramiteConsulta!=null)
				listInfraccionesSeg.add(acSegDetTramiteConsulta);
		}
		return listInfraccionesSeg;
	}
	
	@Transactional
	@Override
	public ByteArrayOutputStream generaReporteExcelSeguimientoTramite(
			int tipoBusqueda, String valor, Integer idTipoTramite, Integer stSegTramite, 
			int tipoFecha, String fhInicio, String fhFin, 
			List<ACSegTramiteConsultaVO> acSegTramiteConsultaVO) {
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		propiedadesReporte.setNombreReporte("Consulta Seguimiento Cambio Tipo de Persona");
		propiedadesReporte.setExtencionArchvio(".xls");
		propiedadesReporte.setTituloExcel("Consulta General Seguimiento Trámites (Cambio Tipo de Persona)");

		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
				
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		List<String> subtitulos = new ArrayList<String>();
		
		switch(tipoBusqueda) {
			case 1: 
				subtitulos.add("Folio Trámite: " + valor);
			break;
			case 2: 
				subtitulos.add("Folio de Infracción: " + valor);
			break;
			case 3: 
				subtitulos.add("Vehículo Placa: " + valor);
			break;
		}
//		if(idTipoTramite!=99)  //no se ceunta con el nombre del tramite segun el id
//			subtitulos.add("Tipo de Tramite " + nbTipoTramite);
//		if(stSegTramite!=99)  //no se cuenta con el nombre del estatus segun el id
//			subtitulos.add("Estatus Tramite " + nbStSegTramite);
		
		switch(tipoFecha) {
			case 1:
				subtitulos.add("Fecha Creación Inicial: " + fhInicio);
				subtitulos.add("Fecha Creación Inicial: " + fhFin);
			break;
			case 2:
				subtitulos.add("Fecha Modificación Inicial: " + fhInicio);
				subtitulos.add("Fecha Modificación Inicial: " + fhFin);
			break;
		}
		
		propiedadesReporte.setSubtitulos(subtitulos);
		
		titulos.add("Folio Trámite");
		titulos.add("Vehículo Placa");
		titulos.add("Numero Infracciones");
		titulos.add("Infracciones");
		titulos.add("Tipo Trámite");
		titulos.add("Nuevo Origen Placa");
		titulos.add("Nuevo Tipo de Persona");
		titulos.add("Nuevo Estado");
		titulos.add("Fecha Creación");
		titulos.add("Usuario que Creeó");
		titulos.add("Fecha Modificación");
		titulos.add("Usuario que Modificó");
		
		encabezadoTitulo.add(titulos);
		
		RutinasTiempoImpl rutinastiempo = new RutinasTiempoImpl();
		propiedadesReporte.setFechaI(rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()));
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(ACSegTramiteConsultaVO seguimientoTramite : acSegTramiteConsultaVO){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(seguimientoTramite.getIdAcTramite());
			listaContenido1.add(seguimientoTramite.getCdCPlaca());
			listaContenido1.add(seguimientoTramite.getCtSolicitados().toString());
			listaContenido1.add(seguimientoTramite.getListInfracciones());
			listaContenido1.add(seguimientoTramite.getNbTipoTramite());
			listaContenido1.add(seguimientoTramite.getNbNuevoOrigenPlaca());
			listaContenido1.add(seguimientoTramite.getNbNuevoTipoPersona());
			listaContenido1.add(seguimientoTramite.getNbNuevoEdo());
			listaContenido1.add(seguimientoTramite.getFhCreacion());
			listaContenido1.add(seguimientoTramite.getNbOficial());
			listaContenido1.add(seguimientoTramite.getFhModificacion());
			listaContenido1.add(seguimientoTramite.getNbOficialModifica());
			
			contenido1.add(listaContenido1);
		}
		
		contenido.add(contenido1);
		
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
 			e.printStackTrace();
		}
	
 		return reporte;
	}
	
	
	@Transactional
	@Override
	public ByteArrayOutputStream generaReporteExcelSegTramiteInfracciones(
			String placaVehicular, String infracNum, String listInfracciones,
			List<ACSegDetTramiteConsultaVO> listACSegDetTramiteConsulta, 
			String idAcTramite) {
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		propiedadesReporte.setNombreReporte("Consulta Seguimiento Infracciones Cambio Tipo de Persona");
		propiedadesReporte.setExtencionArchvio(".xls");
		propiedadesReporte.setTituloExcel("Consulta General Seguimiento Infracciones (Cambio Tipo de Persona)");

		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
				
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		List<String> subtitulos = new ArrayList<String>();
		
		subtitulos.add("Folio del Trámite: " + idAcTramite);
		if(placaVehicular!=null)
			subtitulos.add("Vehículo Placa: " + placaVehicular);
		if(infracNum!=null)
			subtitulos.add("Folio de Infracción: " + infracNum);
		
		propiedadesReporte.setSubtitulos(subtitulos);
		
		titulos.add("Folio Trámite");
		titulos.add("Infracción");
		titulos.add("Vehículo Placa");
		titulos.add("Tipo de Persona");
		titulos.add("Origen Placa");
		titulos.add("Estado");
		
		encabezadoTitulo.add(titulos);
		
		RutinasTiempoImpl rutinastiempo = new RutinasTiempoImpl();
		propiedadesReporte.setFechaI(rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()));
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(ACSegDetTramiteConsultaVO acSegDetTramiteConsulta : listACSegDetTramiteConsulta){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(idAcTramite);
			listaContenido1.add(acSegDetTramiteConsulta.getInfracNum());
			listaContenido1.add(acSegDetTramiteConsulta.getInfracPlaca());
			listaContenido1.add(acSegDetTramiteConsulta.getNbTipoPersona());
			listaContenido1.add(acSegDetTramiteConsulta.getNbOrigenPlaca());
			listaContenido1.add(acSegDetTramiteConsulta.getNbEstado());
			contenido1.add(listaContenido1);
		}
		
		contenido.add(contenido1);
		
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
 			e.printStackTrace();
		}
 		return reporte;
	}
}
