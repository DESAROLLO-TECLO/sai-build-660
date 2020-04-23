package mx.com.teclo.saicdmx.negocio.service.salidas;


import java.io.Serializable;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.engine.jdbc.NonContextualLobCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.bitacora.cambios.ingresos.BitTrBitacIngresos;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.persistencia.dao.ingresosMovDao.ingresoMovDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.ingresos.IngresosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.salidas.SalidaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.salidas.SalidaImgDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresoMov.IngresoMovDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos.IngresosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.ImgSalidasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.SalidasDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.salidas.SalidaVehiculoBusquedaMybatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaTrasladoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaVehiculoSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.FilesSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarTrasladoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.InfoPlacaEmpVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.SalidaVehiculoBusqVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ValidarInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.busquedaCatSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.resultCatVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclo.saicdmx.util.enumerados.SalidasEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class SalidaVehiculoServiceImpl  implements SalidaVehiculoService {
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private SalidaVehiculoBusquedaMybatisDAO SalidaVehiculo;
	
	@Autowired
	private SalidaDAO salidaDAO;
	
	@Autowired
	private ingresoMovDAO ingresosMovDAO;
	
	@Autowired
	private SalidaImgDAO salidaImgDAO;
	
	@Autowired
	private IngresosDAO ingresosDAO;
	
	@Autowired
	private ingresoMovDAO ingresoHistDAO;
	
	@Autowired
	private CajaDAO cajaDAO;
	private CajaDTO cajaDTO;
	
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@Autowired
	private BitTrBitacIngresos bitTrBitacIngresos;
	
	@Override
	public List<FilterValuesVO> filterTipoBusq() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		
		for (int i = 1; i < 7; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);
			if (i == 1) {
				filter.setDescripcion("No. Placa Vehicular");
				filter.setCodigoString("NUM_PLACA");
			} else if (i == 2) {
				filter.setDescripcion("No. de Infracción");
				filter.setCodigoString("NUM_INFRAC");
			}else if (i == 3) {
				filter.setDescripcion("Infracción Impresa");
				filter.setCodigoString("NUM_INFRAC_IMPRESA");
			}else if (i == 4) {
				filter.setDescripcion("No. Documento");
				filter.setCodigoString("NUM_DOCTO");
			}else if (i == 5) {
				filter.setDescripcion("No. Resguardo");
				filter.setCodigoString("NUM_RESGUARDO");
			}else if (i == 6) {
				filter.setDescripcion("NCI");
				filter.setCodigoString("NUM_CTRL");
			}

			filterValues.add(filter);
		}
		return filterValues;
	
	}

	@Override
	public List<SalidaVehiculoBusqVO> getConsultaSalidasVehiculosBean(String tipoBusqueda, String valorBusq) {		
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empFirmado = empleadoService.getEmpleadoById(usuarioFirmadoVO.getId());
		String depId = SalidaVehiculo.busquedaDepId(empFirmado.getEmpId());
		List<SalidaVehiculoBusqVO> busquedaVehiculo = new ArrayList<>();
		 switch(tipoBusqueda) {
		 case "NUM_PLACA": 
			 busquedaVehiculo = SalidaVehiculo.busquedaVehiculoNumPlaca(valorBusq,depId);
		     break;
		 case "NUM_INFRAC": 
			 busquedaVehiculo = SalidaVehiculo.busquedaVehiculoInfrac(valorBusq,depId);
		     break;
		 case "NUM_INFRAC_IMPRESA": 
			 busquedaVehiculo = SalidaVehiculo.busquedaVehiculoInfracImpr(valorBusq,depId);
		     break;
		 case "NUM_DOCTO": 
			 busquedaVehiculo = SalidaVehiculo.busquedaVehiculoDoct(valorBusq,depId);
		     break;
		 case "NUM_RESGUARDO": 
			busquedaVehiculo = SalidaVehiculo.busquedaVehiculoResguardo(valorBusq,depId);
			 break;
		 case "NUM_CTRL": 
			 busquedaVehiculo = SalidaVehiculo.busquedaVehiculoNumCtrl(valorBusq,depId);
		     break;
		 }
		
		return busquedaVehiculo;
		
	}

	public List<FilterValuesVO> filterTipoSalida() {
		return SalidaVehiculo.comboTipoSalida();
	
	}


	@Override
	public boolean isInfraccionTraslado(String infracNum) {
			return SalidaVehiculo.busquedaTransaladoPr(infracNum);
				
		}

	@Override
	public SalidaVehiculoBusqVO infoInfraccion(String numInfrac) {
		return SalidaVehiculo.infoInfraccion(numInfrac);
	}
	
	@Override
	public List<FilterValuesVO> comboAdjunDestino() {
		return SalidaVehiculo.comboAdjunDestino();
	}

	@Override
	public List<FilterValuesVO> comboFaseCompacta() {
		return SalidaVehiculo.comboFaseCompacta();
	}

	@Override
	public ValidarInfraccionVO validaPlaca(String infracNum) {
		
		return  SalidaVehiculo.validarPlaca(infracNum);
	}

	@Override
	public List<FilterValuesVO> comboDepDestino(String depOrigen) {
		
		return SalidaVehiculo.comboDepDestino(depOrigen);
	}

	@Override
	public InfoPlacaEmpVO validarPlacaOficial(String placaOficial) {
		return SalidaVehiculo.validarInfoPlaca(placaOficial);
	}
	
	@Transactional
	@Override
	public SalidasDTO insertToSalida(GuardarSalidaVO convertVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empFirmado = empleadoService.getEmpleadoById(usuarioFirmadoVO.getId());
		SalidasDTO salidaDTO = new SalidasDTO();
		convertVO.setCreadoPor(empFirmado.getEmpId());
		if (!convertVO.getNumPlacaOficial().isEmpty()) {
			EmpleadosDTO empPlaca = empleadoService.getEmpleadoByPlaca(convertVO.getNumPlacaOficial());
			convertVO.setIdAutoriza(empPlaca.getEmpId());
		}
		if (convertVO.getMovTpo().equals(SalidasEnum.ACCION_TPO_SAL_MOVDEP)) {
			convertVO.setMovEstatus(SalidasEnum.STATUS_TRASLADO);
		}
		
		ResponseConverter.copiarPropriedades(salidaDTO, convertVO);
		salidaDTO.setStMovimiento("T");
		salidaDTO.setActivo(1L);
	
		Serializable idGenerado = salidaDAO.guardarRegistroToSalida(salidaDTO);
		
		return salidaDTO;
	}
	
	@Transactional
	@Override
	public void insertToImgSalida(List<FilesSalidaVO> filesVO, GuardarSalidaVO convertVO, Long idMovimiento) {

		for (FilesSalidaVO i : filesVO) {
				if (validarImagenSalida(i.getFiletype())) {
					guardarRegistroImg(i.getBase64(), convertVO, idMovimiento);
				} 
			}
	}
	
	@Transactional
	@Override
	public void insertToImgSalidaIngreso(List<FilesSalidaVO> filesVO, GuardarTrasladoVO convertVO, Long idMovimiento) {

		for (FilesSalidaVO i : filesVO) {
				if (validarImagenSalida(i.getFiletype())) {
					guardarRegistroImgIngreso(i.getBase64(), convertVO, idMovimiento);
				} 
			}
	}
	public Long getIdSalida (String infraccion){
		
		return SalidaVehiculo.getIdSalida(infraccion);
		
	}

	private boolean validarImagenSalida(String imagen) {
		String[] formatos = { "image/bmp", "image/gif", "image/jpg", "image/tif", "image/png", "image/jpeg" };
		for (String f : formatos) {
			if (imagen.contains(f)) {
				return true;
			}
		}
		return false;
	}
	public static Blob arrayBytesToBlob2(String file) {
		byte bt[] = Base64.decodeBase64(file);
		Blob blob = NonContextualLobCreator.INSTANCE.wrap(NonContextualLobCreator.INSTANCE.createBlob(bt));
		return blob;
	}
	
	 public boolean guardarRegistroImg(String file,  GuardarSalidaVO convertVO, Long idMovimiento) {
			 Blob imgBlob = arrayBytesToBlob2(file);
			 salidaImgDAO.insertarImgSalidas(convertVO, imgBlob, idMovimiento);
		 return true;
		 }
	 
	 public boolean guardarRegistroImgIngreso(String file,  GuardarTrasladoVO convertVO, Long idMovimiento) {
		 Blob imgBlob = arrayBytesToBlob2(file);
		 salidaImgDAO.insertarImgSalidasIngreso(convertVO, imgBlob, idMovimiento);
	 return true;
	 }

	 @Override
	public List<FilterValuesVO> filterComboTipoBusq() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		for (int i = 1; i < 7; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);
			if (i == 1) {
				filter.setDescripcion("No. de Placa Vehicular");
				filter.setCodigoString("NUM_PLACA_VEHICULO");
			} else if (i == 2) {
				filter.setDescripcion("No. de Infracción");
				filter.setCodigoString("INFRAC_NUM_SALIDA");
			}else if (i == 3) {
				filter.setDescripcion("Infracción Impresa");
				filter.setCodigoString("INFRAC_IMPRESA");	
			}else if (i == 4) {
				filter.setDescripcion("No. Documento");
				filter.setCodigoString("FOLIO_DOC_SALIDA");
			}else if (i == 5) {
				filter.setDescripcion("NCI");
				filter.setCodigoString("NUM_CTRL");
			}else if (i == 6) {
				filter.setDescripcion("Tipo Salida");
				filter.setCodigoString("tpoSalida");
			}
			filterValues.add(filter);
		}
		return filterValues;
	}

	@Override
	public List<FilterValuesVO> filtroComboTipoOrden() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		for (int i = 1; i < 6; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);
			if (i == 1) {
				filter.setDescripcion("NCI");
				filter.setCodigoString("NUM_CTRL");
			} else if (i == 2) {
				filter.setDescripcion("No. de Placa");
				filter.setCodigoString("NUM_PLACA_VEHICULO");
			}else if (i == 3) {
				filter.setDescripcion("No. Infracción");
				filter.setCodigoString("INFRAC_NUM_SALIDA");
			}else if (i == 4) {
				filter.setDescripcion("Marca");
				filter.setCodigoString("NOM_MARCA");
			}else if (i == 5) {
				filter.setDescripcion("Modelo");
				filter.setCodigoString("NOM_MODELO");
			}
			
			
			filterValues.add(filter);
	}
		return filterValues;
		}

	@Override
	public List<FilterValuesVO> filtroComboTipoSalida() {
		List<FilterValuesVO> filterValues = SalidaVehiculo.comboTipoSalida();
		FilterValuesVO filter = new FilterValuesVO();
		filter.setCodigoString("todos");
		filter.setDescripcion("Todos");
		filterValues.add(0, filter);
		return filterValues;
	}

	@Override
	public List<ConsultaVehiculoSalidaVO> busquedaVehiculoConsulta( String tipoBusq,  String valorCombo, String tipoBusqSalida, String fechaInicio, String fechaFin, String tipoBusqEspecific) {
		 List<ConsultaVehiculoSalidaVO> listaConsultaVehiculo = new ArrayList<>();  
		 cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		if (tipoBusq.equals("tpoSalida")) {
			  if(tipoBusqSalida.equals("todos")){
				  listaConsultaVehiculo = SalidaVehiculo.busquedaTodosVehiculo(fechaInicio, fechaFin, cajaDTO.getDeposito().getDepId());
			  }else{
				  listaConsultaVehiculo = SalidaVehiculo.busquedaEspecialVehiculo(tipoBusqSalida, fechaInicio, fechaFin, tipoBusqEspecific, cajaDTO.getDeposito().getDepId());
			  }			  
		  }else{
			  switch (tipoBusq) {
			
		        case "NUM_PLACA_VEHICULO":
		        	 listaConsultaVehiculo = SalidaVehiculo.busquedaNumPlacaVehiculo(valorCombo, cajaDTO.getDeposito().getDepId());
		        break;
		 
		        case "INFRAC_NUM_SALIDA":
		        	 listaConsultaVehiculo = SalidaVehiculo.busquedaInfracNumSalidaVehiculo(valorCombo, cajaDTO.getDeposito().getDepId());
		        break;
		        
		        case "INFRAC_IMPRESA":
		        	 listaConsultaVehiculo = SalidaVehiculo.busquedaInfraccImpresaVehiculo(valorCombo, cajaDTO.getDeposito().getDepId());
		        break;
		        case "NUM_CTRL":
		        	 listaConsultaVehiculo = SalidaVehiculo.busquedaNumCtrlVehiculo(valorCombo, cajaDTO.getDeposito().getDepId());
		        break;
		        case "FOLIO_DOC_SALIDA":
		        	 listaConsultaVehiculo = SalidaVehiculo.busquedaFolioDocSalidaVehiculo(valorCombo, cajaDTO.getDeposito().getDepId());
		        break;   
		 
		 }		

		  }
		return listaConsultaVehiculo;
	}

	
	@Override
	public List<FilterValuesVO> tipoBusqCombo() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		for (int i = 0; i < 5; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);
			 if (i == 0) {
				filter.setDescripcion("No. de Placa");
				filter.setCodigoString("PLACA");
			}else if (i == 1) {
				filter.setDescripcion("No. de Infracción");
				filter.setCodigoString("INFRACCION");
			}else if (i == 2) {
				filter.setDescripcion("Infracción Impresa");
				filter.setCodigoString("IMPRESA");
			}else if (i == 3) {
				filter.setDescripcion("No. Documento");
				filter.setCodigoString("NUMDOCTO");
			}else if (i == 4) {
				filter.setDescripcion("NCI");
				filter.setCodigoString("NCI");
			}
			
			
			
			filterValues.add(filter);
	}
		return filterValues;
	}

	@Override
	public List<FilterValuesVO> depOrigenCombo() {
	
		return  SalidaVehiculo.depositoTraslados();
	}

	@Override
	public List<ConsultaTrasladoVO> busquedaVehiculoConsultaTraslado(String tipoBusq, String datoBusq, String depId) {
		 List<ConsultaTrasladoVO> listResultTraslado = new ArrayList<>();
		
		 switch (tipoBusq) {
	     			
	 		        case "INFRAC_PLACA":
	 		        	listResultTraslado = SalidaVehiculo.busquedaTrsldNumPlaca(datoBusq, depId);
	 		        break;
	 		 
	 		        case "INFRAC_NUM_MOVS":
	 		        	listResultTraslado = SalidaVehiculo.busquedaTrsldInfracNumMov(datoBusq, depId);
	 		        break;
	 		        
	 		        case "INFRAC_IMPRESA":
	 		        	listResultTraslado = SalidaVehiculo.busquedaTrsldInfraccImpresa(datoBusq, depId);
	 		        break;
	 		        case "INFRAC_NUM_CTRL":
	 		        	listResultTraslado = SalidaVehiculo.busquedaTrsldNumCtrl(datoBusq, depId);
	 		        break;
	 		        case "NUM_DOCTO":
	 		        	listResultTraslado = SalidaVehiculo.busquedaTrsldDocto(datoBusq, depId);
	 		        break;   
	 		 
	 		 	}
        
		return listResultTraslado;
	}

	@Override
	public ConsultaTrasladoVO buscarInfoTrasladoVeh(String numInfraccion) {
		
		return SalidaVehiculo.buscarInfoTrasladoVeh(numInfraccion);
	}
	
	@Override
	public String calcularTiempoTrslado(String tiempo) throws ParseException {
		String calculo = "";
		Calendar fechaSalida = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //fechaSalida.setTime(formatter.parse("13/07/2015 20:15:00"));//
        fechaSalida.setTime(formatter.parse(tiempo));
        Calendar fechaActual = Calendar.getInstance();
        System.out.println("Fecha Actual:" + fechaActual.getTime());
        System.out.println("Fecha Salida:" + fechaSalida.getTime());
        int diasAnyo = fechaSalida.isLenient() ? 366 : 365;
        long anios = fechaActual.get(Calendar.YEAR) - fechaSalida.get(Calendar.YEAR);
        long dias = fechaActual.get(Calendar.DAY_OF_YEAR) - fechaSalida.get(Calendar.DAY_OF_YEAR)
                + (anios * diasAnyo);
        long horas = (fechaActual.get(Calendar.HOUR_OF_DAY) - fechaSalida.get(Calendar.HOUR_OF_DAY));
        long minutos = fechaActual.get(Calendar.MINUTE) - fechaSalida.get(Calendar.MINUTE);
        if (minutos < 0) {

            horas = horas - 1;
            minutos = minutos + 60;
        }
        if (horas < 0) {
            dias -= 1;
            horas += 24;
        }
        calculo = dias + " dia(s) " + horas + " hr(s) " + minutos + " min ";
        
        return calculo;
	}
	

	@Override
	public boolean buscarUserValido() {
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
 		return (cajaDTO != null ? true : false);
	
	}

	@Override
	public String busquedaDepDestino(String tipoBusqueda, String datoBusq) {
		return SalidaVehiculo.busquedaDepDestino(tipoBusqueda, datoBusq);
	}

	@Override
	public void actualizarTablaIngresos(GuardarSalidaVO convertVO) {
	 SalidaVehiculo.actualizarTablaIngresos(convertVO);
		
	}



	@Override
	public List<ImgSalidasDTO> getExpediente(String idSalida, String tipo, String nombreComp) {
		 
		return salidaImgDAO.getExpediente(idSalida, tipo, nombreComp);
		
	}

	@Override
	public String getNumInfraccionByIdSalida(Long idSalidas) {
		return SalidaVehiculo.getNumInfraccionBySalida(idSalidas);
	}

	
	/*INICIA CATALOGO SALIDAS*/
	
	@Override
	public List<FilterValuesVO> filterComboCatSalidas() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		for (int i = 1; i < 3; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);
			if (i == 1) {
				filter.setDescripcion("Fases de Compactación");
			} else if (i == 2) {
				filter.setDescripcion("Destinos de Adjudicación");
			}
			
			filterValues.add(filter);
		}
		return filterValues;
	}

	/*INICIA CATALOGO SALIDAS*/
	
	@Override
	public List<FilterValuesVO> filtroComboCatCompactacion() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		for (int i = 1; i < 3; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);
			if (i == 1) {
				filter.setDescripcion("Todos");
				filter.setCodigoString("todo");
			} else if (i == 2) {
				filter.setDescripcion("Por Fase");
				filter.setCodigoString("fase");
			}
			filterValues.add(filter);
		}
		return filterValues;
	}

	@Override
	public List<FilterValuesVO> filtroComboCatAdjudicacion() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		for (int i = 1; i < 3; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);
			if (i == 1) {
				filter.setDescripcion("Todos");
				filter.setCodigoString("todo");
			} else if (i == 2) {
				filter.setDescripcion("Por Destino");
				filter.setCodigoString("destino");
			}
		
			filterValues.add(filter);
		}
		return filterValues;
	}

	@Override
	public List<resultCatVO> getListCatSalidas(busquedaCatSalidaVO convertVO) {
		List<resultCatVO> results = new ArrayList<resultCatVO>();
		if(convertVO.getIdCat().equals("1")){
			results = SalidaVehiculo.getCatCompacta(convertVO);
		}else{
			results = SalidaVehiculo.getCatAdjudica(convertVO);
		}
		return results;
	}

	@Override
	public resultCatVO getListCatSalidasByIdCat(Long idCat, Long tipoCat) {
		resultCatVO results = new resultCatVO();
		if(tipoCat == 1){
			results = SalidaVehiculo.getCatCompactaByIdCat(idCat);
		}else{
			results = SalidaVehiculo.getCatAdjudicaByIdCat(idCat);
		}
		return results;
	}

	@Override
	public List<FilterValuesVO> filtroComboActiveInactive() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		for (int i = 1; i < 3; i++) {
			FilterValuesVO filter = new FilterValuesVO();
			filter.setCodigo(i);
			if (i == 1) {
				filter.setDescripcion("Activo");
				filter.setCodigoString("Activo");
			} else if (i == 2) {
				filter.setDescripcion("Inactivo");
				filter.setCodigoString("Inactivo");
			}
			filterValues.add(filter);
		}
		return filterValues;
	}

	@Override
	public boolean getResultsUpdate(resultCatVO convertVO, Long tipoCat) {
		if(convertVO.getEstatus().equals("Activo")){
			convertVO.setEstatus("A");
		}else{
			convertVO.setEstatus("I");
		}
		if(tipoCat == 1){
			SalidaVehiculo.updateCatCompactaByIdCat(convertVO);
		}else{
			SalidaVehiculo.updateCatAdjudicaByIdCat(convertVO);
		}
		return true;
	}

	@Override
	public boolean getResultsInsertSalida(resultCatVO convertVO, Long tipoCat) {
		if(convertVO.getEstatus().equals("Activo")){
			convertVO.setEstatus("A");
		}else{
			convertVO.setEstatus("I");
		}
		if(tipoCat == 1){
			SalidaVehiculo.insertCatCompactaByIdCat(convertVO);
		}else{
			SalidaVehiculo.insertCatAdjudicaByIdCat(convertVO);
		}
		return true;
	}

	@Transactional
	@Override
	public void guardarSalida(GuardarSalidaVO convertVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		
		SalidasDTO salidaDTO = new SalidasDTO();
			
		convertVO.setCreadoPor(usuarioFirmadoVO.getId());
		
		if (!convertVO.getNumPlacaOficial().isEmpty()) {
			EmpleadosDTO empPlaca = empleadoService.getEmpleadoByPlaca(convertVO.getNumPlacaOficial());
			convertVO.setIdAutoriza(empPlaca.getEmpId());
		}
		if (convertVO.getMovTpo().equals(SalidasEnum.ACCION_TPO_SAL_MOVDEP)) {
			convertVO.setMovEstatus(SalidasEnum.STATUS_TRASLADO);
		}
		
		IngresosDTO ingreso = ingresosDAO.buscaIngresoByInfraccion(convertVO.getNuminfrac());
		IngresoMovDTO ingresoHist = ResponseConverter.copiarPropiedadesFull(ingreso,IngresoMovDTO.class);
		ingresoHistDAO.save(ingresoHist);
		
		ResponseConverter.copiarPropriedades(salidaDTO, convertVO);
		salidaDTO.setNuminfraccion(ingreso);
		if(convertVO.getMovTpo() == 5){
			salidaDTO.setStMovimiento("T");
			
		}else{
			salidaDTO.setStMovimiento("S");
		}
		
		salidaDTO.setActivo(1L);
		salidaDTO = salidaDAO.guardarRegistroToSalida(salidaDTO);
		
		if(salidaDTO.getIdMovimiento() != null){
			if(convertVO.getMovTpo() == 5){
				ingreso.setStMovimiento("T");
			}else{
				ingreso.setStMovimiento("S");
			}
			ingreso.setFhMovimiento(new Date());
			ingreso.setIngrStatus("E");
			ingreso.setModificadoPor(usuarioFirmadoVO.getId());
			ingreso.setUltimaModificacion(new Date());
			ingreso.setUsuarioAutoriza(salidaDTO.getIdAutoriza());
			ingreso = ingresosDAO.update(ingreso);
			convertVO.setMensaje("Movimiento registrado correctamente");
		}
		
			
	
	
		
	}

	
	@Transactional
	@Override
	public void updateIngresos(GuardarTrasladoVO convertVO) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		String tipoBusq = "INFRACCION";
		boolean isIngreso = true;
		EmpleadosDTO empFirmado = empleadoService.getEmpleadoById(usuarioFirmadoVO.getId());
		convertVO.setCreadoPor(empFirmado.getEmpId());
		Long idDepDestino =  cajaDTO.getDeposito().getDepId();
		IngresosDTO ingreso = ingresosDAO.buscaTraslado(tipoBusq, convertVO.getInfracNum(), cajaDTO.getDeposito().getDepId(), isIngreso);
		ingreso.setStMovimiento("D");
		ingreso.setIngrStatus("A");
		ingreso.setModificadoPor(usuarioFirmadoVO.getId());
		ingreso.setUltimaModificacion(new Date());
		ingreso.setDepId(idDepDestino);
		
		ingreso.getInfraccion().setDepId(idDepDestino);
		ingreso = ingresosDAO.saveOrUpdate(ingreso);
		
		SalidasDTO movimiento = salidaDAO.buscaTrasladoUpdate(convertVO.getInfracNum(), cajaDTO.getDeposito().getDepId());
		
		movimiento.setStMovimiento("D");
		movimiento.setActivo(0L);
		movimiento.setMediotransp(convertVO.getMedioTrasporte());
		movimiento.setNoeconomico(convertVO.getNoEconomico());
		movimiento.setObservaciones(convertVO.getObservaciones());
		movimiento.setFchUltimaModificacion(new Date());
		movimiento = salidaDAO.saveOrUpdate(movimiento);
		
		convertVO.setMensaje("Movimiento registrado correctamente");
		
		
	}




	@Override
	public String obtenerRutaLocalMovimiento(String infracNum, String tipo, String idSalida) {
	
		return SalidaVehiculo.obtenerRutaLocalExpedienteMovimiento(infracNum, tipo, idSalida);
	}

	@Override
	public Long getIdMovVeh(String numinfrac) {
		
		return SalidaVehiculo.getIdMovVeh(numinfrac);
	}

	@Override
	public void insertToBit(String infracNum, String oldStatus) {
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				11L,	
				14L, 
				 oldStatus, 
				"A", 
				usuarioFirmadoVO.getId(),	
				infracNum,
				"",  
				ParametrosBitacoraEnum.ORIGEN_W.getParametro() // Origen W / H
			);		
	}

	@Override
	public String getOldStatus(String infracNum) {
		// TODO Auto-generated method stub
		return SalidaVehiculo.getIngrEstatusOld(infracNum);
	}

	
	@Override
	public void insertIntoBitac(GuardarSalidaVO orginalVO, GuardarSalidaVO nuevoVO) throws ParseException {
		
		bitacoraCambiosService.guardarListaBitacoraCambios(bitTrBitacIngresos.guardarNuevoSalidas(orginalVO,nuevoVO) );
	}

	@Override
	public GuardarSalidaVO getOriginalIngreso(String numinfrac) {
		return	SalidaVehiculo.getOriginalIngreso(numinfrac);
	}

}


	
