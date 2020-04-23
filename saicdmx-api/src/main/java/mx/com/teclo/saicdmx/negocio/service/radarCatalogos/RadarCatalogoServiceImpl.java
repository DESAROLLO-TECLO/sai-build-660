package mx.com.teclo.saicdmx.negocio.service.radarCatalogos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar.BitSpRadarCatCr;
import mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar.BitSpRadarCatUtM;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarCatalogo.RadarCatalogosMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaCPVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaUTVO;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ArchivoTipoAtributes;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class RadarCatalogoServiceImpl implements RadarCatalogoService{

	private ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs;
	@Autowired
	RadarCatalogosMyBatisDAO RadarCatalogos;
	@Autowired
	UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	@Autowired
	private RadarCatalogoService radarCatalogoService;
	@Autowired
	private BitSpRadarCatCr bitSpRadarCatCr;
	@Autowired
	private BitSpRadarCatUtM bitSpRadarCatUtM;
	
	@Override
	public List<FilterValuesVO> getComboCatalgos() {
	List <FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		
		for(int i=0; i<2; i++){
			FilterValuesVO filter = new FilterValuesVO();
			 if(i == 0){
				filter.setCodigoString(ArchivoTipoAtributes.CAT_UT_VALUE);
				filter.setDescripcion(ArchivoTipoAtributes.CAT_UT_LABEL);
			}else if(i == 1){
				filter.setCodigoString(ArchivoTipoAtributes.CAT_CP_VALUE);
				filter.setDescripcion(ArchivoTipoAtributes.CAT_CP_LABEL);
			}
			
			filterValues.add(filter);
		}
		return filterValues;
	}


	@Override
	public List<FilterValuesVO> getComboTipoBusquedaCatUT() {
		
			List <FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		
		for(int i=0; i<4; i++){
			FilterValuesVO filter = new FilterValuesVO();
			if(i == 0){
				filter.setCodigoString(ArchivoTipoAtributes.CAT_FILTRO_TODOS);
				filter.setDescripcion(ArchivoTipoAtributes.CAT_FILTRO_TODOS);
			}else if(i == 1){
				filter.setCodigoString(ArchivoTipoAtributes.CAT_FILTRO_UT);
				filter.setDescripcion(ArchivoTipoAtributes.CAT_FILTRO_UT);
			}else if(i == 2){
				filter.setCodigoString(ArchivoTipoAtributes.CAT_FILTRO_SECTOR);
				filter.setDescripcion(ArchivoTipoAtributes.CAT_FILTRO_SECTOR);
			}else if(i == 3){
				filter.setCodigoString(ArchivoTipoAtributes.CAT_FILTRO_DELEGACION);
				filter.setDescripcion("Delegaci\u00f3n");
			}
			
			filterValues.add(filter);
		}
		return filterValues;
		
    
		
	}
	


	@Override
	public List<FilterValuesVO> getComboTipoBusquedaCatCP() {
List <FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		
		for(int i=0; i<4; i++){
			FilterValuesVO filter = new FilterValuesVO();
			if(i == 0){
				filter.setCodigoString(ArchivoTipoAtributes.CAT_FILTRO_TODOS);
				filter.setDescripcion(ArchivoTipoAtributes.CAT_FILTRO_TODOS);
			}else if(i == 1){
				filter.setCodigoString(ArchivoTipoAtributes.CAT_FILTRO_CP);
				filter.setDescripcion("C\u00f3digo Postal");
			}else if(i == 2){
				filter.setCodigoString(ArchivoTipoAtributes.CAT_FILTRO_EDO);
				filter.setDescripcion(ArchivoTipoAtributes.CAT_FILTRO_EDO);
			}else if(i == 3){
				filter.setCodigoString(ArchivoTipoAtributes.CAT_FILTRO_DELEGACION_ESTADO);
				filter.setDescripcion("Delegaci\u00f3n");
			}
			
			filterValues.add(filter);
	

	}
		return filterValues;
		
	}

	public List<FilterValuesVO> getComboCatEstadosUT() {
		
		return RadarCatalogos.getComboCatEstadosUT();
	}
	
	public List<FilterValuesVO> getComboCatEstados() {
		
		return RadarCatalogos.getComboCatEstados();
	}


	@Override
	public List<FilterValuesVO> getComboCatDelegacionEstado() {
		
		return RadarCatalogos.getComboCatDelegacionEstado();
	}


	@Override
	public List<FilterValuesVO> getComboCatSectores() {
		
		return RadarCatalogos.getComboCatSectores();
	}


	@Override
	public List<FilterValuesVO> getComboCatDelegaciones() {
			
		return	RadarCatalogos.getComboCatDelegacion(ArchivoTipoAtributes.EDO_DF_ID);
			
		 
	}


	@Override
	public List<RadarCatConsultaUTVO> getConsultaUnidadTerritorial(String tipoCatalogo, String valor, Long archivoTipoUT, Long tipoRadarUT, Long estatusUT) {
		
		return RadarCatalogos.getConsultaUnidadTerritorial(tipoCatalogo,valor,archivoTipoUT,tipoRadarUT,estatusUT);
	}


	public RadarCatConsultaUTVO getUnidadTerritorial(String tipoCatalogo, String valor, Long archivoTipoUT, Long tipoRadarUT, Long estatusUT) {
		
		return RadarCatalogos.getUnidadTerritorial(tipoCatalogo,valor,archivoTipoUT,tipoRadarUT,estatusUT);
	}
	
	@Override
	public List<RadarCatConsultaCPVO> getConsultaCodigosPostales(String tipoBusqueda, String valor) {
		String valores[] = new String[0];
		String valorExtra = "";
		if(!valor.isEmpty() && tipoBusqueda.equals(ArchivoTipoAtributes.CAT_FILTRO_DELEGACION_ESTADO)){
			valores = valor.split("\\|");
			valor = valores[0];
			valorExtra = valores[1];
		}
		
		
		return RadarCatalogos.getConsultaCodigoPostal(tipoBusqueda,valor, valorExtra);
	}


	@Override
	public List<FilterValuesVO> getComboEstatus() {
List <FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		
		for(int i=0; i<2; i++){
			FilterValuesVO filter = new FilterValuesVO();
			if(i == 0){
				filter.setCodigoString("1");
				filter.setDescripcion("Activo");
			}else if(i == 1){
				filter.setCodigoString("0");
				filter.setDescripcion("Inactivo");
			}
			
			filterValues.add(filter);
	

	}
		return filterValues;
	}

	// UT
	@Override
	public List<FilterValuesVO> getComboEstatusUT() {
		List <FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		
		for(int i=0; i<3; i++){
			FilterValuesVO filter = new FilterValuesVO();
			if(i == 0){
				filter.setCodigoString("2");
				filter.setDescripcion("TODOS");
			}else if(i == 1){
				filter.setCodigoString("1");
				filter.setDescripcion("ACTIVO");
			}else if(i == 2){
				filter.setCodigoString("0");
				filter.setDescripcion("INACTIVO");
			}
			
			filterValues.add(filter);
	

	}
		return filterValues;
	}

	@Override
	public List<FilterValuesVO> getComboCatSectoresPorDelgac(String idDelegacion) {
		
		return  RadarCatalogos.getComboSectorPorDelegacion(ArchivoTipoAtributes.EDO_DF_ID,idDelegacion);
	}


	@Override
	public List<FilterValuesVO> getComboZonasCP() {
		
		return RadarCatalogos.getComboZonasCP();
	}


	@Override
	public List<FilterValuesVO> getComboCatDelegaciones(String idEstado) {
		//return RadarCatalogos.getComboCatDelegacion(idEstado);
		return RadarCatalogos.getComboRadaCatDelegacion(idEstado);
	}


	@Override
	public RadarCatConsultaUTVO guardarNewUT(RadarCatConsultaUTVO convertVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		convertVO.setCatUTModificadoPor(usuarioFirmadoVO.getId());
		convertVO.setAccion(ArchivoTipoAtributes.ACCION_CAT_NUEVO);
		if(convertVO.getCatUTId() != null){ 
			convertVO.getCatUTId(); 
			} else {
				convertVO.setCatUTId("0");
				}
		if(convertVO.getCatUTtipoRadar() != null){
			convertVO.getCatUTtipoRadar();
		}else{
			convertVO.setCatUTtipoRadar("0");
		}
		
		RadarCatalogos.crudCatUT(convertVO);
		if(convertVO.getResultado().equals("0")){
			bitacoraCambiosService.guardarBitacoraCambios(bitSpRadarCatUtM.guardarNewUTBit(convertVO));
		}
		
		return convertVO;
	}


	@Override
	public RadarCatConsultaCPVO guardarNewCP(RadarCatConsultaCPVO convertVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
		convertVO.setCatCPModificadoPor(usuarioFirmadoVO.getId());
		convertVO.setAccion(ArchivoTipoAtributes.ACCION_CAT_NUEVO);
		if(convertVO.getCatCPId() != null){ 
			convertVO.getCatCPId(); 
			} else {
				convertVO.setCatCPId("0");
				}
		if(convertVO.getCatCPCiudad().isEmpty()){
			convertVO.setCatCPCiudad("NULL");
		}else{
			convertVO.getCatCPCiudad();
		}
		if(convertVO.getCatCPCiudadId().isEmpty()){
			convertVO.setCatCPCiudadId("");
		}else{
			convertVO.getCatCPCiudadId();
		}
		RadarCatalogos.crudCatCR(convertVO);
		if(convertVO.getResultado().equals("0")){
			bitacoraCambiosService.guardarBitacoraCambios(bitSpRadarCatCr.guardarNewCR(convertVO));
		}
		return convertVO;
	}


	@Override
	public RadarCatConsultaUTVO buscarUnidadTerritorialPorUT(String numeroUT) {
		 return RadarCatalogos.buscarUnidadTerritorialPorUT(numeroUT);
	}


	@Override
	public RadarCatConsultaCPVO buscarUnidadTerritorialPorCP(String valorId) {
		
		return  RadarCatalogos.buscarUnidadTerritorialPorCP(valorId);
	}


	@Override
	public RadarCatConsultaUTVO guardarUpdateUT(RadarCatConsultaUTVO convertVO) throws ParseException {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		
		
			convertVO.setCatUTModificadoPor(usuarioFirmadoVO.getId());
			convertVO.setAccion(ArchivoTipoAtributes.ACCION_CAT_MODIF);
			if(convertVO.getCatUTId() != null){ 
				convertVO.getCatUTId(); 
				} else {
					convertVO.setCatUTId("0");
					}
			if(convertVO.getCatUTtipoRadar() != null){
				convertVO.getCatUTtipoRadar();
			}else{
				convertVO.setCatUTtipoRadar("0");
			}
			
			RadarCatConsultaUTVO convertVOEdit  = convertVO;
			RadarCatConsultaUTVO convertVOBd = radarCatalogoService.getUnidadTerritorial("Unidad Territorial",convertVO.getCatUTId(), 0L, 0L, 2L);
			RadarCatalogos.crudCatUT(convertVO); 
			
			convertVOBd.setCatUTEstatus(convertVOBd.getCatUTEstatus().compareTo("ACTIVO") == 0 ? "HABILITADO" : "DESHABILITADO");
			convertVOEdit.setCatUTEstatus(convertVOEdit.getCatUTEstatus().compareTo("1") == 0 ? "HABILITADO" : "DESHABILITADO");
			
				if(convertVO.getResultado().equals("0")){
					bitacoraCambiosService.guardarListaBitacoraCambios(bitSpRadarCatUtM.guardarCambiosBitacoraUdateUT(convertVOEdit, convertVOBd));
				}
	
			return  convertVO;
	
	}
	
	@Override
	public RadarCatConsultaCPVO guardarUpdateCP(RadarCatConsultaCPVO convertVO) throws ParseException {//**************************************
			UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO(); 
			convertVO.setCatCPModificadoPor(usuarioFirmadoVO.getId());
			convertVO.setAccion(ArchivoTipoAtributes.ACCION_CAT_MODIF);
				if(convertVO.getCatCPId() != null){ 
				convertVO.getCatCPId(); 
				} else {
					convertVO.setCatCPId("0");
					}
			if(convertVO.getCatCPCiudad().isEmpty()){
				convertVO.setCatCPCiudad("NULL");
			}else{
				convertVO.getCatCPCiudad();
			}
			if(convertVO.getCatCPCiudadId().isEmpty()){
				convertVO.setCatCPCiudadId("");
			}else{
				convertVO.getCatCPCiudadId();
			}
			RadarCatConsultaCPVO convertVOEdit  = convertVO;
			RadarCatConsultaCPVO convertVOBd = radarCatalogoService.buscarUnidadTerritorialPorCP(convertVO.getCatCPId());
			RadarCatalogos.crudCatCR(convertVO);
			
			convertVOBd.setCatCPEstatus(convertVOBd.getCatCPEstatus().equals("1") ? "HABILITADO" : "DESHABILITADO");
			convertVOEdit.setCatCPEstatus(convertVOEdit.getCatCPEstatus().equals("0") ? "DESHABILITADO" : "HABILITADO");
			convertVOBd.setCatCPEstado(convertVOBd.getCatCPEstadoId() != null ? RadarCatalogos.getNombreEstado(Integer.parseInt(convertVOBd.getCatCPEstadoId())) : "");
			convertVOEdit.setCatCPEstado(convertVOEdit.getCatCPEstadoId() != null ? RadarCatalogos.getNombreEstado(Integer.parseInt(convertVOEdit.getCatCPEstadoId())) : "");				
			convertVOBd.setCatCPMunicipio(convertVOBd.getCatCPMunicipioId() != null ? RadarCatalogos.getNombreDelegacion(Integer.parseInt(convertVOBd.getCatCPMunicipioId()), Integer.parseInt(convertVOBd.getCatCPEstadoId())) : "");
			convertVOEdit.setCatCPMunicipio(convertVOEdit.getCatCPMunicipioId() != null ? RadarCatalogos.getNombreDelegacion(Integer.parseInt(convertVOEdit.getCatCPMunicipioId()), Integer.parseInt(convertVOEdit.getCatCPEstadoId())): "");
			
			if(convertVO.getResultado().equals("0")){
				bitacoraCambiosService.guardarListaBitacoraCambios(bitSpRadarCatCr.guardarCambiosBitacoraUdateCP(convertVOEdit, convertVOBd));
			}
		
		return convertVO;
	}

	public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
	
	@Override
	public List<FilterValuesVO> getComboArchivoTipo() {
		return RadarCatalogos.getComboArchivoTipo();
	}

	// UT Busqueda
	@Override
	public List<FilterValuesVO> getComboArchivoTipoUTBusq() {
		List <FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO filter = new FilterValuesVO();
		filterValues = RadarCatalogos.getComboArchivoTipoUT();		
		filter.setCodigoString("0");
		filter.setDescripcion("TODOS");
		filterValues.add(filter);
		
		return filterValues;
	}
	
	// UT
	@Override
	public List<FilterValuesVO> getComboArchivoTipoUT() {
		List <FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO filter = new FilterValuesVO();
		filter.setCodigoString("0");
		filter.setDescripcion("TODOS");
		filterValues.add(filter);
		filterValues = RadarCatalogos.getComboArchivoTipoUT();		
		return filterValues;
	}

	@Override
	public List<FilterValuesVO> getComboTipoRadar() {
		return RadarCatalogos.getComboTipoRadar();
	}
	
	// UT Busqueda
	@Override
	public List<FilterValuesVO> getComboTipoRadarUTBusq() {
		
		List <FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO filter = new FilterValuesVO();
		filterValues = RadarCatalogos.getComboTipoRadar();
		filter.setCodigoString("0");
		filter.setDescripcion("TODOS");
		filterValues.add(filter);
		return filterValues;
	}
		
	// UT
	@Override
	public List<FilterValuesVO> getComboTipoRadarUT() {
		List <FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO filter = new FilterValuesVO();
		filter.setCodigoString("0");
		filter.setDescripcion("TODOS");
		filterValues.add(filter);
		filterValues = RadarCatalogos.getComboTipoRadar();
		return filterValues;
	}
/*
	@Override
	public boolean comprobacionUT(RadarCatConsultaUTVO convertVO) {
		if(convertVO.getCatUTtipoRadar() != null){
			convertVO.getCatUTtipoRadar();
		}else{
			convertVO.setCatUTtipoRadar("0");
		}
		return RadarCatalogos.comprobacionUT(convertVO);
		
	} */
	
	@Override
	public int comprobacionUT(RadarCatConsultaUTVO convertVO) {
		if(convertVO.getCatUTtipoRadar() != null){
			convertVO.getCatUTtipoRadar();
		}else{
			convertVO.setCatUTtipoRadar("0");
		}
			 
		 String resultado  = RadarCatalogos.comprobacionUT(convertVO);
		 int resp;
		 
		 if (resultado != null){
			 resp = Integer.parseInt(resultado);
		 }else{
			 resp = 0;
		 }
		
		 
		 return resp;
	}
	
	public boolean comprobacionCPAll() {
		int a = RadarCatalogos.consultaOpcionalCp();
		
		return a > 5000 ? true: false;
		
	}
	
	
	public List<RadarCatConsultaCPVO> consultaOpcionalAll(){
	
		return RadarCatalogos.consultaOpcionalAll();
	}
	
	public boolean cambiarEstatus(String accion,int idUT) {
		   int status; 
		if(accion.equalsIgnoreCase("INACTIVO")){
	        	status = 1;
	        }else{
	        	status = 0;
	        }
	     
		String resultado = RadarCatalogos.cambiarEstatus(status, idUT);
		Boolean confirmacion = false;
		
		if (resultado == null){
			confirmacion = true;
		}
		
		return confirmacion;
	}
	
		
	}
	
