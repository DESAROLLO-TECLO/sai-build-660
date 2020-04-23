package mx.com.teclo.saicdmx.negocio.service.radararchivo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.security.spec.RSAPublicKeySpec;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar.BitRadarProcesarArchivoServlet;
import mx.com.teclo.saicdmx.keyloader.certificados.KeyLoaderEnumeration;
import mx.com.teclo.saicdmx.keyloader.certificados.KeyLoaderFactory;
import mx.com.teclo.saicdmx.keyloader.certificados.PrivateKeyLoader;
import mx.com.teclo.saicdmx.keyloader.certificados.PublicKeyLoader;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.certificados.CertificadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadoFirmaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMLotesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultadetecciones.FotomultaDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultalotes.FotomultaLotesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarabitacoraproceso.RadarBitacoraProcesoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivo.RadarArchivoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivodetalle.RadarArchivoDetalleMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivodetallehist.RadarArchivoDetalleHistMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarasignafoliotemp.RadarAsignaFolioTempMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.CertificadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.EmpleadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesCatLayoutVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesLayoutVO;
import mx.com.teclo.saicdmx.persistencia.vo.radarbitacoraprocesoestatus.RadarBitacoraProcesoComplementacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.CargaArchivoComplementracionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoEnComplementacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarDeteccionesCentroRepartoV2;
import mx.com.teclo.saicdmx.util.archivradares.ArchivoRadaresUitil;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enumerados.ArchivoTipoProcesoEnum;
import mx.com.teclo.saicdmx.util.enumerados.ArchivosEnum;
import mx.com.teclo.saicdmx.util.enumerados.ArchivosNumberEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;


/**
 * 
 * @author UnitisDes0416
 *
 */
@Service
public class RadarArchivoServiceImpl implements RadarArchivoService {
	@Autowired
	private RadarArchivoMyBatisDAO radarArchivoMyBatisDAO;
	
	@Autowired
	private FMLotesMyBatisDAO fmLotesMyBatisDAO;
	
	@Autowired
	private RadarArchivoDetalleMyBatisDAO radarArchivoDetalleMyBatisDAO;
	
	@Autowired
	private RadarBitacoraProcesoMyBatisDAO radarBitacoraProcesoMyBatisDAO;
	
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private FotomultaLotesMyBatisDAO fotomultaLotesMyBatisDAO;
	
	@Autowired
	private FotomultaDeteccionesMyBatisDAO fotomultaDeteccionesMyBatisDAO;
	
	@Autowired
	private RadarAsignaFolioTempMyBatisDAO radarAsignaFolioTempMyBatisDAO;
	
	@Autowired
	private RadarArchivoDetalleHistMyBatisDAO radarArchivoDetalleHistMyBatisDAO;
	
	private String idTaiBitacora;
	
	@Autowired
	BitRadarProcesarArchivoServlet BitRadarProcesarArchivoServlet;
	
	// --- Certificado
	@Autowired
	CertificadoDAO certificadoDAO;
	
	@Autowired
	private EmpleadoService empleadoService;
	 
	
	/**
	 * @author UnitisDes0416
	 * @return
	 */
	public boolean verificarArchivosEnCurso(){
		Boolean result = true;
		Integer cantidad = radarArchivoMyBatisDAO.buscaArchivoEnProceso(); 
//		System.out.println("Esta cantidad: "+cantidad);
		if(cantidad == 0){
			result =  false;
			
			/**INI JLGD COMENTA ESTE SEGMENTO DE CODIGO HASTA PREGUNTAR SU FUNCIONALIDAD**/
//			if(radarArchivoMyBatisDAO.getArchivoId()!=null) {
//				long idArchivo = radarArchivoMyBatisDAO.getArchivoId().longValue();//Inicia guardado de bitácora
//				String fileName = radarArchivoMyBatisDAO.getNombreArchivo();
//				Integer isComplement = radarArchivoMyBatisDAO.isArchivoComplementado(idArchivo);
//				Integer isCancelled = radarArchivoMyBatisDAO.isArchivoCancelado(idArchivo);
//				idTaiBitacora = radarArchivoMyBatisDAO.taiBitacoraCambios(String.valueOf(idArchivo));
//				RadarArchivoEstatusVO radarArchivoEstatusVO = new RadarArchivoEstatusVO();
//				radarArchivoEstatusVO.setArchivoId(idArchivo); radarArchivoEstatusVO.setArchivoNombre(fileName);
//				radarArchivoEstatusVO.setEstatusProcesoId(isComplement); radarArchivoEstatusVO.setCancelado(isCancelled);
//				radarArchivoEstatusVO.setIdTaiBitacora(idTaiBitacora);
//				
//				if(isComplement == 1 && StringUtils.isEmpty(idTaiBitacora)){
//					bitacoraCambiosService.guardarBitacoraCambios(BitRadarProcesarArchivoServlet.complementacionCompletoBit(radarArchivoEstatusVO));
//				}
//				else{
//					if(isCancelled == 1 && StringUtils.isEmpty(idTaiBitacora)){
//						bitacoraCambiosService.guardarBitacoraCambios(BitRadarProcesarArchivoServlet.cancelarComplementacionBit(radarArchivoEstatusVO));
//					}
//				}//Termina guardado de bitácora
//			}
			/**FIN JLGD**/
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean cargaArchivoComplementacionRadares(MultipartFile file, CargaArchivoComplementracionVO cargaArchivoComplementracionVO, String rutaArchivo) throws IOException{
		boolean bandera = false;
		File path = new File(rutaArchivo + file.getOriginalFilename());
		
		if(!path.exists()){
			creaDirectoriosPorRuta(rutaArchivo);
		    FileOutputStream fos = new FileOutputStream(path); 
		    fos.write(file.getBytes());
		    fos.close();
			bandera = true;
		}else{
			FileOutputStream fos = new FileOutputStream(path); 
		    fos.write(file.getBytes());
		    fos.close();
			bandera = true;
		}
		return bandera;
		
	}
	
	

	public boolean creaDirectoriosPorRuta(String path) {

		File directorio = new File(path);
		if (!directorio.exists()) {
			return directorio.mkdirs();
		} else {
			return true;
		}
	}	
	
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	public Map validaArchivoRadar(String fileName, String ruraRadares, CargaArchivoComplementracionVO radaresCatArchivoTipoVO) {
		ArchivoRadaresUitil archivoRadaresUitil = new ArchivoRadaresUitil();
		Map resultado = new HashMap<>();
		FMDeteccionesCatLayoutVO catLayoutVO=null;
		
		if(radaresCatArchivoTipoVO.getRadaresCatArchivoTipoVO().getArchivoTipoId() == ArchivosNumberEnum.RADAR_SSP.getConstante()){
			catLayoutVO=obtenerRestricciones(ArchivosNumberEnum.FM_RADAR_VELOCIDAD_Jenoptik.getConstante(), ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());
			resultado = archivoRadaresUitil.validacionProcesoCompletoSSP(fileName, ruraRadares, radaresCatArchivoTipoVO, catLayoutVO);
			resultado.put("origenPlaca", ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());
			resultado.put("esCarrilConfinado", false);
		}else if(radaresCatArchivoTipoVO.getRadaresCatArchivoTipoVO().getArchivoTipoId() == ArchivosNumberEnum.RADAR_SSP_FORANEO.getConstante()){
			catLayoutVO=obtenerRestricciones(ArchivosNumberEnum.FM_RADAR_VELOCIDAD_Jenoptik.getConstante(),ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());
			resultado = archivoRadaresUitil.validacionProcesoForaneo(fileName, ruraRadares, radaresCatArchivoTipoVO, catLayoutVO);
			resultado.put("origenPlaca", ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());
			resultado.put("esCarrilConfinado", false);
		}
		else if(radaresCatArchivoTipoVO.getRadaresCatArchivoTipoVO().getArchivoTipoId() == ArchivosNumberEnum.RADAR_CONCESIONADO.getConstante()){
			catLayoutVO=obtenerRestricciones(ArchivosNumberEnum.FM_RADAR_VELOCIDAD_CONSESIONADO_Redflex.getConstante(),ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());
			resultado = archivoRadaresUitil.validacionProcesoCompletoSSPConcesionado(fileName,ruraRadares, radaresCatArchivoTipoVO, catLayoutVO);
			resultado.put("origenPlaca", ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());
			resultado.put("esCarrilConfinado", false);
		}else if(radaresCatArchivoTipoVO.getRadaresCatArchivoTipoVO().getArchivoTipoId() == ArchivosNumberEnum.RADAR_CONCESIONADO_FORANEO.getConstante()){
			catLayoutVO=obtenerRestricciones(ArchivosNumberEnum.FM_RADAR_VELOCIDAD_CONSESIONADO_Redflex.getConstante(),ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());
			resultado = archivoRadaresUitil.validacionProcesoCompletoSSPForaneoConcesionado(fileName,ruraRadares, radaresCatArchivoTipoVO, catLayoutVO);
			resultado.put("origenPlaca", ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());
			resultado.put("esCarrilConfinado", false);
		}
		else if(radaresCatArchivoTipoVO.getRadaresCatArchivoTipoVO().getArchivoTipoId() == ArchivosNumberEnum.FOTOMULTA.getConstante()){
			catLayoutVO=obtenerRestricciones(ArchivosNumberEnum.FM_FOTOMULTA_Bosch.getConstante(),ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());
			resultado = archivoRadaresUitil.validacionProcesoCompletoFotomulta(fileName,ruraRadares, radaresCatArchivoTipoVO, catLayoutVO);
			resultado.put("origenPlaca", ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());
			resultado.put("esCarrilConfinado", false);
		}else if(radaresCatArchivoTipoVO.getRadaresCatArchivoTipoVO().getArchivoTipoId() == ArchivosNumberEnum.FOTOMULTA_FORANEO.getConstante()){
			catLayoutVO=obtenerRestricciones(ArchivosNumberEnum.FM_FOTOMULTA_Bosch.getConstante(), ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());
			resultado = archivoRadaresUitil.validacionProcesoCompletoFotomultaForanea(fileName,ruraRadares, radaresCatArchivoTipoVO, catLayoutVO);
			resultado.put("origenPlaca", ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());
			resultado.put("esCarrilConfinado", false);
		}
		else if(radaresCatArchivoTipoVO.getRadaresCatArchivoTipoVO().getArchivoTipoId() == ArchivosNumberEnum.CARRIL_CONFINADO.getConstante()){
			catLayoutVO=obtenerRestricciones(ArchivosNumberEnum.FM_CARRIL_CONFINADO_Jenoptik.getConstante(), ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());
			resultado = archivoRadaresUitil.validacionProcesoCarrilConfinado(fileName, ruraRadares, radaresCatArchivoTipoVO, catLayoutVO);
			resultado.put("origenPlaca", ArchivosNumberEnum.FM_ORIGEN_CDM.getConstante());
			resultado.put("esCarrilConfinado", true);
		}else if(radaresCatArchivoTipoVO.getRadaresCatArchivoTipoVO().getArchivoTipoId() == ArchivosNumberEnum.CARRIL_CONFINADO_FORANEO.getConstante()){
			catLayoutVO=obtenerRestricciones(ArchivosNumberEnum.FM_CARRIL_CONFINADO_Jenoptik.getConstante(), ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());
			resultado = archivoRadaresUitil.validacionProcesoCarrilConfinadoForaneo(fileName, ruraRadares, radaresCatArchivoTipoVO, catLayoutVO);
			resultado.put("origenPlaca", ArchivosNumberEnum.FM_ORIGEN_FORANEO.getConstante());
			resultado.put("esCarrilConfinado", true);
		}
		return resultado;
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes"})
//	public Map asignarArticulo(Map mapaArchivo){
//		
//		List<Map> detecciones = (List<Map>) mapaArchivo.get("detecciones");
//		List<Map> duplicados = (List<Map>) mapaArchivo.get("duplicados");
//		Map newMapaArchivo = new HashMap();
//		//HashMap deteccionesValidas = (HashMap) detecciones.get(0);
//		
//		//{resultado=true, duplicados=[], errores=[], detecciones
//		
//		//Asignar su artId a las detecciones
//		for (Map map : detecciones) {
//			Integer artId = 0;
//			String artNumero, artFraccion, artParrafo, artInciso, artUnidadCuenta, fecha;
//			
//			artNumero = String.valueOf(map.get("ARTNUMERO"));
//			artFraccion = String.valueOf(map.get("ARTFRACCION"));
//			artParrafo = String.valueOf(map.get("ARTPARRAFO"));
//			artInciso = String.valueOf(map.get("ARTINCISO"));
//			artUnidadCuenta = String.valueOf(map.get("ARTUNIDADCUENTA"));
//			fecha = String.valueOf(map.get("FECHA"));
//			
//			artFraccion = String.valueOf(radarArchivoMyBatisDAO.castingConcesionado(artFraccion, "F"));
//			artParrafo = String.valueOf(radarArchivoMyBatisDAO.castingConcesionado(artParrafo, "P"));
//			artInciso = artInciso.equals("-") ? "0" : artInciso.toUpperCase();
//			
//			
//			artId = radarArchivoMyBatisDAO.asignarArticulo(artNumero, artFraccion, artParrafo, artInciso, artUnidadCuenta, fecha);
//			
//			map.put("ARTID", artId);
//		}
//		
//		//Asignar su artId a las duplicadas
//				for (Map map : duplicados) {
//					Integer artId = 0;
//					String artNumero, artFraccion, artParrafo, artInciso, artUnidadCuenta, fecha;
//					
//					artNumero = String.valueOf(map.get("ARTNUMERO"));
//					artFraccion = String.valueOf(map.get("ARTFRACCION"));
//					artParrafo = String.valueOf(map.get("ARTPARRAFO"));
//					artInciso = String.valueOf(map.get("ARTINCISO"));
//					artUnidadCuenta = String.valueOf(map.get("ARTUNIDADCUENTA"));
//					fecha = String.valueOf(map.get("FECHA"));
//					
//					artFraccion = String.valueOf(radarArchivoMyBatisDAO.castingConcesionado(artFraccion, "F"));
//					artParrafo = String.valueOf(radarArchivoMyBatisDAO.castingConcesionado(artParrafo, "P"));
//					artInciso = artInciso.equals("-") ? "0" : artInciso.toUpperCase();
//					
//					
//					artId = radarArchivoMyBatisDAO.asignarArticulo(artNumero, artFraccion, artParrafo, artInciso, artUnidadCuenta, fecha);
//					
//					map.put("ARTID", artId);
//				}
//		
//		newMapaArchivo.put("detecciones", detecciones);
//		newMapaArchivo.put("duplicados", duplicados);
//		newMapaArchivo.put("errores", mapaArchivo.get("errores"));
//		newMapaArchivo.put("resultado", mapaArchivo.get("resultado"));
//		
//		return newMapaArchivo;
//	}
	
	

	/**
	 * {@inheritDoc}
	 * Para cualquier tipo de archivo su carga a bd sera completo
	 */
	@SuppressWarnings("rawtypes")
	public void cargaArchivoRadar(String nombreArchivo, Integer procesoTipo, List<Map> detecciones, List<Map> duplicados,Long empleadoId, CargaArchivoComplementracionVO cargaArchivoComplementracionVO, Integer origenPlaca, boolean esCarrilConfinado) {
		
		ArchivoTipoProcesoEnum archivosEnum = ArchivoTipoProcesoEnum.fromInteger(procesoTipo == 1 ? 1 : 2);
		
		cargaProcesoCompleto(nombreArchivo, archivosEnum.getLongConstante(), empleadoId, detecciones, duplicados, cargaArchivoComplementracionVO, origenPlaca, esCarrilConfinado);

	}
	
	private void insertaRadarDetalleCompleto(List<Map> detecciones, Long selectNextVal, boolean isDuplicado, boolean esCarrilConfinado) {
		for (Map deteccionValida : detecciones) {
			deteccionValida.put("RADARARCHIVOID", selectNextVal);
			if(isDuplicado) {
				deteccionValida.put("ACTIVO", ArchivosNumberEnum.REGISTRO_INACTIVO.getConstante());
				deteccionValida.put("DUPLICADO", ArchivosNumberEnum.REGISTRO_DUPLICADO.getConstante());
				
			}else {
				deteccionValida.put("ACTIVO", ArchivosNumberEnum.REGISTRO_ACTIVO.getConstante());
				deteccionValida.put("DUPLICADO", ArchivosNumberEnum.REGISTRO_NODUPLICADO.getConstante());
				if(deteccionValida.get("OFICIALPLACA").toString().trim().isEmpty()){
					deteccionValida.put("ACTIVO", ArchivosNumberEnum.REGISTRO_INACTIVO.getConstante());
				}
			}
			//validamos la incercion de datos obligatorios para la CDMX y discriminamos los no requeridos para Foraneos
			//	que requieran de catálogo
			if(deteccionValida.get("ENTIDAD")!=null) {
//				System.out.println("Conversin Entiidad y delegacion");
				String ENTIDAD = deteccionValida.get("ENTIDAD").toString();
				String DELEGACION = deteccionValida.get("DELEGACION").toString();
//				System.out.println("incercion Entiidad y delegacion");
				deteccionValida.put("ENTIDAD",this.esNumeroEntero(ENTIDAD) ? fmLotesMyBatisDAO.buscarEstadoPorId(Long.parseLong(ENTIDAD)):ENTIDAD );
				deteccionValida.put("DELEGACION", this.esNumeroEntero(DELEGACION) ? fmLotesMyBatisDAO.buscarDelegacionPorId(Long.parseLong(DELEGACION), this.esNumeroEntero(ENTIDAD)?Long.parseLong(ENTIDAD):Long.parseLong("99")):DELEGACION);
			}
//			System.out.println("incercion de artid");
			String newFech = deteccionValida.get("FECHA").toString();
//			Comentado por JLGD
//			if(deteccionValida.get("TIPODETECCION").toString().equals("3")) {
//				newFech = deteccionValida.get("FECHA").toString().replace(".", "/");
//			}
			if(esCarrilConfinado){
				deteccionValida.put("ARTID", deteccionValida.get("ARTID") != null ? deteccionValida.get("ARTID") :getArticuloIdByFecha(newFech));	
			}
			radarArchivoDetalleMyBatisDAO.insertaRadarArchivoDetalleCompleto(deteccionValida);
		}
	}
	
	private boolean esNumeroEntero(String cadena){
		cadena=cadena==null?"":cadena;
		boolean isRight=true;
		try {
			Integer.parseInt(cadena);
			isRight=true;
		} catch (Exception e) {
			isRight=false;
		}
		return isRight;
	}
	
	/**
	 * @author UnitisDes0416
	 * @param nombreArchivo String
	 * @param procesoTipo Long
	 * @param empleadoId Long
	 * @param detecciones List<Map>
	 * @param duplicados List<Map>
	 * @param cargaArchivoComplementracionVO CargaArchivoComplementracionVO
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void cargaProcesoCompleto(String nombreArchivo, Long procesoTipo, Long empleadoId, List<Map> detecciones, List<Map> duplicados, CargaArchivoComplementracionVO cargaArchivoComplementracionVO, Integer origenPlaca,boolean esCarrilConfinado) {
		RadarArchivoVO radarArchivoVO = new RadarArchivoVO();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		
		Long selectNextVal = radarArchivoMyBatisDAO.obtenerSiguienteSecuencia();
		Integer archivoTipo = cargaArchivoComplementracionVO.getRadaresCatArchivoTipoVO().getArchivoTipoId();
		String anioSalarioMin = radarArchivoMyBatisDAO.getAnioSalarioMin(cargaArchivoComplementracionVO.getComboValuesVO().getValor());		
		String idFmTipoDeteccion="";
		for (Map deteccionValida : detecciones) {
			//System.out.println("obtenemos id dispositivo FM");
			idFmTipoDeteccion=deteccionValida.get("TIPODETECCION").toString();
			break;
		}
		//System.out.println("Asignamos datos al VO para almacenar en BD");
		radarArchivoVO.setRadarArchivoId(selectNextVal);
		radarArchivoVO.setArchivoNombre(nombreArchivo);
		radarArchivoVO.setArchivoTipoId(new Long (archivoTipo.longValue()));
		radarArchivoVO.setArchivoTipoProcesoId(procesoTipo);
		radarArchivoVO.setEstatusProcesoId(ArchivosNumberEnum.ARCHIVO_LISTO_PARA_COMPLEMENTAR.getLongConstante());
		//radarArchivoVO.setEnProceso(ArchivosNumberEnum.EN_PROCESO.getConstante());
		radarArchivoVO.setEnProceso(0);
		radarArchivoVO.setOrigenLote(0);
		radarArchivoVO.setIdTipoDeteccion(Long.valueOf(idFmTipoDeteccion));
		radarArchivoVO.setAnioSalario(Integer.parseInt(anioSalarioMin));
		radarArchivoVO.setFechaEmision(rutinasTiempoImpl.convertirStringDate(cargaArchivoComplementracionVO.getFechaEmision(),"dd/MM/yyyy"));
		radarArchivoVO.setEmpleadoId(empleadoId);
		radarArchivoVO.setFehcaUltimaModificacion(new Date());
		radarArchivoVO.setIdSalarioMin(Long.valueOf((cargaArchivoComplementracionVO.getComboValuesVO().getValor())));
		radarArchivoVO.setResponsableProceso(cargaArchivoComplementracionVO.getResponsableProceso().getId());
		if(origenPlaca == 1){
			radarArchivoVO.setFechaImposicion(radarArchivoVO.getFechaEmision());
		}
		//System.out.println("Insertamos datos en BD (Radar Archivo y Radar Archivo Total)");
		radarArchivoMyBatisDAO.insertaRadarArchivo(radarArchivoVO);
		radarArchivoMyBatisDAO.insertaRadarArchivoTotal(radarArchivoVO);
		
		//System.out.println("Insertamos todas las detecciones validas y duplicadas del Lote en RadarArchivoDetalle");
		insertaRadarDetalleCompleto(detecciones, selectNextVal, false, esCarrilConfinado);
		insertaRadarDetalleCompleto(duplicados, selectNextVal, true, esCarrilConfinado);
		complementaOficialNombre(selectNextVal);
		validaFormatoPlaca(selectNextVal);
		//System.out.println("Insertamos estatus listoParaComplementar en la bitacora de proceso");
		radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProceso(selectNextVal, ArchivosNumberEnum.ARCHIVO_LISTO_PARA_COMPLEMENTAR.getLongConstante(), empleadoId);
		radarArchivoMyBatisDAO.setArchivoEnProceso(1, selectNextVal);
		radarArchivoMyBatisDAO.setArchivoTotalEnProceso(1, selectNextVal);
	}
	
	/**
	 * @author UnitisDes0416
	 * @param nombreArchivo String
	 * @param procesoTipo Long
	 * @param empleadoId Long
	 * @param detecciones List<Map>
	 * @param duplicados List<Map>
	 * @param cargaArchivoComplementracionVO CargaArchivoComplementracionVO
	 */
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private void cargaProcesoForaneo(String nombreArchivo, Long procesoTipo, Long empleadoId, List<Map> detecciones, List<Map> duplicados, CargaArchivoComplementracionVO cargaArchivoComplementracionVO) {
//		RadarArchivoVO radarArchivoVO = new RadarArchivoVO();
//		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
//		Long selectNextVal = radarArchivoMyBatisDAO.obtenerSiguienteSecuencia();
//		
//		radarArchivoVO.setRadarArchivoId(selectNextVal);
//		radarArchivoVO.setArchivoNombre(nombreArchivo);
//		radarArchivoVO.setArchivoTipoId(cargaArchivoComplementracionVO.getRadaresCatArchivoTipoVO().getArchivoTipoId());
//		radarArchivoVO.setArchivoTipoProcesoId(procesoTipo);
//		radarArchivoVO.setAnioSalario(Integer.parseInt(cargaArchivoComplementracionVO.getComboValuesVO().getValor()));
//		radarArchivoVO.setFechaEmision(rutinasTiempoImpl.convertirStringDate(cargaArchivoComplementracionVO.getFechaEmision(),"dd/MM/yyyy"));
//		radarArchivoVO.setEmpleadoId(empleadoId);
//		radarArchivoVO.setFehcaUltimaModificacion(new Date());
//
//		for (Map deteccionValida : detecciones) {
//			deteccionValida.put("RADARARCHIVOID", selectNextVal);
//			deteccionValida.put("ACTIVO", ArchivosNumberEnum.REGISTRO_ACTIVO.getConstante());
//			deteccionValida.put("DUPLICADO", ArchivosNumberEnum.REGISTRO_NODUPLICADO.getConstante());
//			
//			radarArchivoDetalleMyBatisDAO.insertaRadarArchivoDetalleForaneo(deteccionValida);
//		}
//		
//		for (Map deteccionDuplicada : duplicados) {
//			deteccionDuplicada.put("RADARARCHIVOID", selectNextVal);
//			deteccionDuplicada.put("ACTIVO", ArchivosNumberEnum.REGISTRO_INACTIVO.getConstante());
//			deteccionDuplicada.put("DUPLICADO", ArchivosNumberEnum.REGISTRO_DUPLICADO.getConstante());
//			
//			radarArchivoDetalleMyBatisDAO.insertaRadarArchivoDetalleForaneo(deteccionDuplicada);
//		}
//		
//		radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProceso(selectNextVal, ArchivosNumberEnum.ARCHIVO_LISTO_PARA_COMPLEMENTAR.getLongConstante(), empleadoId);
//	}
	
	/**
	 * @author UnitisDes0416
	 * @param fecha String
	 * @return Integer
	 */
	private Integer getArticuloIdByFecha(String fecha) {
		try {
			SimpleDateFormat infracFormat = new SimpleDateFormat("dd.MM.yy");
			SimpleDateFormat baseFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaInfrac = infracFormat.parse(fecha);
			Date fechaBase = baseFormat
					.parse(ArchivosEnum.REGLAMENTO_FECHA_DISTRITO_FEDERAL.getConstante());
			
			if (fechaInfrac.before(fechaBase)) {
				return ArchivosNumberEnum.REG_METROPOLITANO_ARTICULO_ID.getConstante();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ArchivosNumberEnum.REG_DISTRITO_FEDERAL_ARTICULO_ID.getConstante();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public RadarArchivoEnComplementacionVO buscarRadarArchivoEnProceso(String Query){
		return radarArchivoMyBatisDAO.buscarRadarArchivoEnProceso(Query);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<RadarBitacoraProcesoComplementacionVO> buscaEststusRadarArchivoEnProceso(Long radarArchivoId){
		return radarBitacoraProcesoMyBatisDAO.buscarRadarArchivoEnProceso(radarArchivoId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void cancelaLoteEnProceso(Long radarArchivoId, String motivoCancelacion, Long empleadoId){
		
			String archivoTipo = radarArchivoMyBatisDAO.buscaArchivoTipo(radarArchivoId);
			
			Integer cantidadCancelados = radarArchivoDetalleMyBatisDAO.buscaCantidadDeDetecciones(radarArchivoId, ArchivosNumberEnum.INACTIVO.getConstante());
			Integer cantidadProcesados = radarArchivoDetalleMyBatisDAO.buscaCantidadDeDetecciones(radarArchivoId, ArchivosNumberEnum.ACTIVO.getConstante());
			
			if(archivoTipo.equalsIgnoreCase(ArchivosEnum.FOTO_MUTLTA.getConstante())){
				fotomultaLotesMyBatisDAO.updateFotoMultaLotesCancelado(radarArchivoId, ArchivosNumberEnum.DETECCIONES_CANCELADAS.getConstante(), cantidadCancelados + cantidadProcesados);
				
				fotomultaDeteccionesMyBatisDAO.updateDeteccionesLoteCancelado(radarArchivoId);
			}
			
			radarAsignaFolioTempMyBatisDAO.InsertFoliosCancelados(radarArchivoId);
			
			radarArchivoMyBatisDAO.cancelaRadarArchivo(radarArchivoId, ArchivosNumberEnum.ESTATUS_PROCESO_CANCELADO.getLongConstante(), empleadoId, ArchivosNumberEnum.DETECCIONES_CANCELADAS.getConstante(), cantidadProcesados + cantidadCancelados, motivoCancelacion);
			
			radarArchivoDetalleMyBatisDAO.cancelaDeteccionesPorArchivo(radarArchivoId);
			
			radarArchivoDetalleHistMyBatisDAO.insertaRadarArchivoDetalleCompleto(radarArchivoId);
			
			radarArchivoDetalleMyBatisDAO.eliminaDeteccionesCancelacionLote(radarArchivoId);
			
			radarBitacoraProcesoMyBatisDAO.insertaRadarBitacoraProceso(radarArchivoId, ArchivosNumberEnum.ESTATUS_PROCESO_CANCELADO.getLongConstante(), empleadoId);
	}

	@Override
	public List<RadarDeteccionesCentroRepartoV2> buscarListaDeteccionesValidas(Long radarArchivoId) {
		
		return radarBitacoraProcesoMyBatisDAO.obtenerListaDeteccionesValidas(radarArchivoId);
	}
	

	@Override
	public List<RadarDeteccionesCentroRepartoV2> buscarListaDeteccionesInvalidas(Long radarArchivoId) {
		
		return radarBitacoraProcesoMyBatisDAO.obtenerListaDeteccionesInvalidas(radarArchivoId);
	}
	
	/*actualizarArchivoProcesoId remplaza a setEstatusProcesoId*/
	public  Boolean actualizarArchivoProcesoId(Long radarArchivoId, int estatusProcesoId, Long idEmpleado, int enproceso) {
		 
		boolean Comprobarproceso = radarBitacoraProcesoMyBatisDAO.procesoCancelado(radarArchivoId);
		if(Comprobarproceso){
			return false;
		}
		radarBitacoraProcesoMyBatisDAO.insertaRadarBitacora(radarArchivoId, estatusProcesoId,idEmpleado);
		radarBitacoraProcesoMyBatisDAO.actualizarArchivoProcesoId(radarArchivoId,estatusProcesoId,idEmpleado,enproceso);
		return true;
		
	}

	@Override
	public Boolean updateAllCR(Long radarArchivoId) {
		radarBitacoraProcesoMyBatisDAO.cambiaAllCr(radarArchivoId);
		return true;
	}
	
	@Override
	public Boolean updateDetecciones(Long idCP, Long radarArchivoId, Long id) {
		radarBitacoraProcesoMyBatisDAO.deshabilitaDeteccion(idCP,radarArchivoId, id);
		return true;
	}
	
	@Override
	public Boolean updateThisCP(Long radarArchivoId, String Cp, Long id, Long idUsu) {
		radarBitacoraProcesoMyBatisDAO.cambiaThisCp(radarArchivoId, Cp, id, idUsu);
		return true;
	}

	@Override
	public Boolean cambiaCPDeteccion(Long idCP, String cP, Long id , Long archivoId) {
		radarBitacoraProcesoMyBatisDAO.HabilitaDeteccion(cP, id,idCP, archivoId);
		return true;
	}

//	@Override
//	public List<ComboValuesVO> obtenerAniosSalarioMinimo() {
//		
//		return radarBitacoraProcesoMyBatisDAO.getAnioSalarioMinimo();
//	}
	
	public void complementaOficialNombre(Long selectNextVal){
		radarArchivoDetalleMyBatisDAO.complementaOficialNombre(selectNextVal);
	}
	
	public void validaFormatoPlaca(Long selectNextVal){
		radarArchivoDetalleMyBatisDAO.validaFormatoPlaca(selectNextVal);
	}
	
	private FMDeteccionesCatLayoutVO obtenerRestricciones(Integer idDeteccion,Integer idOrigenPlaca){
		FMDeteccionesCatLayoutVO catLayoutVO=new FMDeteccionesCatLayoutVO();
		catLayoutVO.setCatalogosMap(new HashMap<String, List<String>>());
		FMDeteccionesLayoutVO deteccionesLayoudVO = null;
		deteccionesLayoudVO = fmLotesMyBatisDAO.consultaFMDeteccionesLayout(idDeteccion.longValue(),idOrigenPlaca.longValue());
		if (deteccionesLayoudVO!=null){
			catLayoutVO.setObligatorios(this.cadenaToArrayCadena(deteccionesLayoudVO.getCdCamposObligatorios(), null));
			catLayoutVO.setOpcionaes(this.cadenaToArrayCadena(deteccionesLayoudVO.getCdCamposOpcionales(), null));
			catLayoutVO.getCatalogosMap().put("CAT_ENTIDADES", Arrays.asList(this.cadenaToArrayCadena(deteccionesLayoudVO.getCdCatEntidades(),null)));
			catLayoutVO.getCatalogosMap().put("CAT_DELEGACIONES", Arrays.asList(this.cadenaToArrayCadena(deteccionesLayoudVO.getCdCatDelegaciones(),null)));
			catLayoutVO.getCatalogosMap().put("CAT_ARTICULOS", Arrays.asList(this.cadenaToArrayCadena(deteccionesLayoudVO.getCdCatArticulos(),null)));
//			catLayoutVO.getCatalogosMap().put("CAT_ART_03", Arrays.asList(this.cadenaToArrayCadena(deteccionesLayoudVO.getCdCatArt03(),null)));
//			catLayoutVO.getCatalogosMap().put("CAT_ART_08",Arrays.asList(this.cadenaToArrayCadena(deteccionesLayoudVO.getCdCatArt08(),null)));
		}	
		return catLayoutVO;
	}
	
	/**
	 * @param cadena , if cadena es null, retorna array null
	 * @param caracterSplit , por defaul tiene asignado el caracter coma (,)
	 * @return
	 */
	private String[] cadenaToArrayCadena(String cadena, String caracterSplit) {
		String[] arreglo = null;
		cadena=cadena==null?"":cadena;
		//if (cadena != null) {
			caracterSplit = caracterSplit == null ? "," : caracterSplit;
			arreglo = cadena.split(caracterSplit);
			for (int i = 0; i < arreglo.length; i++) {
				arreglo[i] = arreglo[i].trim();
			}
		//}
		return arreglo;
	}
	
	@Override
	public Boolean validarUsuarioActivo(String placaOficial){
		Integer Existe = radarArchivoMyBatisDAO.validarUsuarioActivo(placaOficial);
		
		if(Existe == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public static byte[] blobToArrayBytes(Blob blob) {
		byte[] data = null;
		if (blob != null) {
			try {
				data = blob.getBytes(1, (int) blob.length());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public static EmpleadoVO getEmpleadoVO(EmpleadosDTO empleado) {
		EmpleadoVO empleadoVO = new EmpleadoVO();

		empleadoVO.setAgrpId(empleado.getAgrupamiento().getAgrupacionId());
		empleadoVO.setCreadoPor(empleado.getCreadoPor());
		empleadoVO.setEmpApeMaterno(empleado.getEmpApeMaterno());
		empleadoVO.setEmpApePaterno(empleado.getEmpApePaterno());
		empleadoVO.setEmpCod(empleado.getEmpCod());
		empleadoVO.setEmpId(empleado.getEmpId());
		empleadoVO.setEmpNombre(empleado.getEmpNombre());
		empleadoVO.setEmpPlaca(empleado.getEmpPlaca());
		empleadoVO.setEmpRFC(empleado.getEmpRFC());
		empleadoVO.setEmpPwd(empleado.getEmpPwd());
		empleadoVO.setEmpStatus(empleado.getEmpStatus());
		empleadoVO.setEmpTipId(empleado.getEmpTip().getEmpTipId());
		empleadoVO.setFechaCreacion(empleado.getFechaCreacion());
		empleadoVO.setModificadoPor(empleado.getModificadoPor());
		empleadoVO.setSecId(empleado.getSector().getSecId());
		empleadoVO.setUltimaModificacion(empleado.getUltimaModificacion());

		return empleadoVO;
	}
	
	public static CertificadoVO getCertificadoVO(EmpleadoFirmaDTO certificadoDTO) {
		CertificadoVO certificadoVO = new CertificadoVO();

		certificadoVO.setCertId(certificadoDTO.getCertId());
		certificadoVO.setCertEmitidoPara(certificadoDTO.getCertEmitidoPara());
		certificadoVO.setCertEmitidoPor(certificadoDTO.getCertEmitidoPor());
		certificadoVO.setCertNombre(certificadoDTO.getCertNombre());
		certificadoVO.setKeyNombre(certificadoDTO.getKeyNombre());
		certificadoVO.setCertValidoDesde(certificadoDTO.getCertValidoDesde());
		certificadoVO.setCertValidoHasta(certificadoDTO.getCertValidoHasta());
		certificadoVO.setCertArchivo(blobToArrayBytes(certificadoDTO.getCertArchivo()));
		certificadoVO.setKeyArchivo(blobToArrayBytes(certificadoDTO.getKeyArchivo()));
		certificadoVO.setEmpleadoVO(getEmpleadoVO(certificadoDTO.getEmpleadoDTO()));
		certificadoVO.setEstatusCertificado(certificadoDTO.getEstatus());
		certificadoVO.setValidado(certificadoDTO.getValidado());

		return certificadoVO;

	}
	
	@Override
	@Transactional
	public Map<String, Object> validarPassCert(String placaOficial, String passPrivateKey) {
		EmpleadosDTO empleadoDTO = empleadoService.getEmpleadoByPlaca(placaOficial);
		Map<String, Object> response = new HashMap<String, Object>();

		CertificadoVO certificadoVO = getCertificadoVO(
				certificadoDAO.obtieneCertificadoPorPlaca(empleadoDTO.getEmpId()));
		InputStream isCertificado = new ByteArrayInputStream(certificadoVO.getCertArchivo());
		InputStream isLlave = new ByteArrayInputStream(certificadoVO.getKeyArchivo());

		PrivateKey key = null;
		X509Certificate cert = null;
		try {
			cert = KeyLoaderFactory.createInstance(KeyLoaderEnumeration.PUBLIC_KEY_LOADER, isCertificado).getKey();

			key = KeyLoaderFactory.createInstance(KeyLoaderEnumeration.PRIVATE_KEY_LOADER, isLlave, passPrivateKey)
					.getKey();

			RSAPublicKeySpec rsaPrivateKey = PrivateKeyLoader.getPublicKeySpec(key);
			RSAPublicKeySpec rsaPublicKey = PublicKeyLoader.getPublicKeySpec(cert);

			if ((rsaPrivateKey.getModulus().equals(rsaPublicKey.getModulus()))
					&& rsaPrivateKey.getPublicExponent().equals(rsaPublicKey.getPublicExponent())) {
				
				//certificadoDAO.updateStatusCertificado(empleadoDTO.getEmpId());
				// Se comenta esta linea para impedir afectar el registro de el certificado
				response.put("respuesta", Boolean.TRUE);
				response.put("message", "El certificado fue validado correctamente.");

			} else {
				response.put("respuesta", Boolean.FALSE);
				response.put("message", "La llave no le corresponde al certificado");
			}

		} catch (GeneralSecurityException e) {
			response.put("respuesta", Boolean.FALSE);
			response.put("message", "La contraseña de la llave privada  no es correcta");

		}

		return response;
	}

	@Override
	@Transactional
	public Boolean insertaParametrosFirma(Long radarArchivoId, Long empId, String placaOficial, String empRFC, String pwd){
		
		Integer Existe = radarArchivoMyBatisDAO.insertaParametrosFirmaRadar(radarArchivoId, empId, placaOficial, empRFC, pwd);
		
		return true;
	}
}
