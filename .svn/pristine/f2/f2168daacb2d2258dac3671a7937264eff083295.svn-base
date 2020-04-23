package mx.com.teclo.saicdmx.negocio.service.garantias;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.bitacora.cambios.infracciones.BitTrBitUpInfrac;
import mx.com.teclo.saicdmx.bitacora.cambios.infraccionesDigitalizacion.BitTrBitUpInfracDig;
import mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar.BitTrBitUpInfracRadar;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.negocio.service.pagos.ConsultaCentroPagosService;
import mx.com.teclo.saicdmx.negocio.service.pagos.PagoService;
import mx.com.teclo.saicdmx.pdf.garantia.GarantiaGeneraReporteVaucher;
import mx.com.teclo.saicdmx.pdf.garantia.GarantiaGeneraReporteVoucher;
import mx.com.teclo.saicdmx.pdf.garantia.GarantiaGeneraReporteVoucherHH;
import mx.com.teclo.saicdmx.pdf.garantia.GarantiasEntrega;
import mx.com.teclo.saicdmx.pdf.garantia.GarantiasRecibe;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado.EmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias.GarantiasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias.GarantiasDocumentoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias.GarantiasEstatusProcesoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias.GarantiasProcesoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias.GarantiasVwSinProcesarDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones.InfraccionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.TransaccionesCanceladasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaEstatusProcesoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaProcesoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaVwSinProcesarDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionesCanceladasDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias.GarantiaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias.GarantiaPagosProcedureMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias.GarantiaReporteEntregaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias.GarantiaReporteGralMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias.GarantiaReporteMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones.InfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaDetallePorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaPorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsGralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsultaEntregaFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsultaFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaGetVaucherFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaRecepcionInfoFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteEntregarFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteGeneralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteRecibirFVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaReporteRecibirMasivaFVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enums.EnumGarantiasProceso;
import mx.com.teclo.saicdmx.util.enums.EnumGarantiasTipo;
import mx.com.teclo.siidf.centrodepagos.mit.service.IOperacionesService;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service
public class GarantiaServiceImpl implements GarantiaService {

	public static final Integer procesoIdPagada = 2;
	public static final Integer procesoIdEntregado = 3;
	private static final String ERROR_LINEA_BANCO = "-1";
	private static final String VOUCHER_ANTERIOR = "A";
	private static final String VOUCHER_ORIGEN_WEB = "W";

	@Autowired
	BitTrBitUpInfracRadar bitTrBitUpInfracRadar;
	
	@Autowired
	BitTrBitUpInfracDig bitTrBitUpInfracDig;
	
	@Autowired
	BitTrBitUpInfrac bitTrBitUpInfrac;
	
	@Autowired
	private GarantiaMyBatisDAO garantiaDAO;

	@Autowired
	private GarantiasDAO garantiasHDAO;
	
	//JLGD
	@Autowired
	private GarantiasVwSinProcesarDAO garantiasVwSinProcesarDAO;
	@Autowired
	private EmpleadoDAO empleadoDAO;
	
	@Autowired
	private InfraccionDAO infraccionDAO;

	@Autowired
	private GarantiasProcesoDAO garantiaProcesoDAO;
	
	@Autowired
	private GarantiasDocumentoDAO garantiasDocumentoDAO;

	@Autowired
	private GarantiasEstatusProcesoDAO garantiaEstatusProcesoDAO;

	@Autowired
	private GarantiaReporteMyBatisDAO garantiaReporteMyBatis;
	
	@Autowired
	private GarantiaReporteEntregaMyBatisDAO garantiaReporteEntregaMyBatis;
	
	@Autowired
	private GarantiaPagosProcedureMyBatisDAO garantiaPagosProcedureMyBatis;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private CajaDAO cajaDAO;
	private CajaDTO cajaDTO;
	
	@Autowired
	@Qualifier("operacionesService")
	private IOperacionesService operacionesService;
	
	@Autowired
	@Qualifier("pagoTarjeta")
	private PagoService pagoTarjeta;
	
	@Autowired
	@Qualifier("pagoDocumento")
	private PagoService pagoDocumento;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private GarantiaReporteGralMyBatisDAO garantiaReporteGralMyBatisDAO;
	
	@Autowired
	@Qualifier("consultaCentroPagosService")
	private ConsultaCentroPagosService consultaCentroPagosServices;
	
	@Autowired
	private TransaccionesCanceladasDAO transaccionesCanceladasDAO;
	
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@Autowired
	InfraccionMyBatisDAO infraccionMyBatisDAO;
	
	@Autowired
	private EmpleadoService empleadoService;

	private static final Logger logger = Logger
			.getLogger(GarantiaServiceImpl.class);
	
	//JLGD
	@Override
	@Transactional
	public Boolean generarGarantiasSinProcesar(String empPlaca, Long creadoPor) {
		Boolean flag = true;
		try {
			flag = true;
		
		List<GarantiaVwSinProcesarDTO> infracciones = garantiasVwSinProcesarDAO
				.getGarantiasSinProcesarByOficial(empPlaca);
		GarantiaDTO garantia;
		for (GarantiaVwSinProcesarDTO infraccion : infracciones) {
			garantia = new GarantiaDTO();
			garantia.setCreadoPor(creadoPor);
			garantia.setEmpleadosDTO(empleadoService.getEmpleadoById(infraccion.getEmpId()));
			garantia.setInfraccionDTO(infraccionDAO.findOne(infraccion.getInfracNum()));
			garantia.setGarantiaDocumentoDTO(garantiasDocumentoDAO.findOne(infraccion.getGarantiaTipoId().intValue()));

			EnumGarantiasTipo tipoGarantia = EnumGarantiasTipo
					.getTipoGarantia(infraccion.getGarantiaTipoId().intValue());
			switch (tipoGarantia) {
			case PLACA:
				garantia.setDocumentoFolio(infraccion.getPlaca());
				break;
			case LICENCIA:
				garantia.setDocumentoFolio(infraccion.getLicencia());
				break;
			case TARJETA:
				garantia.setDocumentoFolio(infraccion.getTarjetaCirculacion());
				break;
			case PROMESA_PLACA:
				garantia.setDocumentoFolio(infraccion.getPlaca());
				break;
			case PROMESA_LICENCIA:
				garantia.setDocumentoFolio(infraccion.getLicencia());
				break;
			case PROMESA_TARJETA:
				garantia.setDocumentoFolio(infraccion.getTarjetaCirculacion());
				break;
			default:
				break;
			}

			garantia.setModificadoPor(creadoPor);
			garantia.setGarantiaProcesoDTO(garantiaProcesoDAO.findOne(EnumGarantiasProceso.CREADA.getProcesoID()));
			garantia.setRecibida(false);
			garantia.setPagada(false);
			garantia.setEntregada(false);
			garantia.setIdLote(0l);
			garantiasHDAO.save(garantia);

			// Crear estatus proceso
			GarantiaEstatusProcesoDTO garantiaEstatusDTO = new GarantiaEstatusProcesoDTO();
			garantiaEstatusDTO.setCreadoPor(creadoPor);
			garantiaEstatusDTO.setFechaCreacion(new Date());
			garantiaEstatusDTO.setGarantiaId(garantia.getGarantiaId());
			garantiaEstatusDTO.setProcesoId(EnumGarantiasProceso.CREADA.getProcesoID());
			garantiaEstatusDTO.setModificadoPor(creadoPor);
			garantiaEstatusDTO.setUltimaModificacion(new Date());
			garantiaEstatusProcesoDAO.save(garantiaEstatusDTO);
		}
		
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;

		
	}
	

	@Override
	@Transactional
	public Boolean generarGarantiasSinProcesarPorInf(String infrac_num, Long emp_id) {
		Boolean flag = true;
		try {
			GarantiaVwSinProcesarDTO infraccion = garantiasVwSinProcesarDAO.getGarantiasSinProcesarByInfracNum(infrac_num);
			if(infraccion != null) {
				GarantiaDTO garantia;
				garantia = new GarantiaDTO();
				garantia.setCreadoPor(emp_id);
				garantia.setEmpleadosDTO(empleadoService.getEmpleadoById(infraccion.getEmpId()));
				garantia.setInfraccionDTO(infraccionDAO.findOne(infraccion.getInfracNum()));
				garantia.setGarantiaDocumentoDTO(garantiasDocumentoDAO.findOne(infraccion.getGarantiaTipoId().intValue()));
	
				EnumGarantiasTipo tipoGarantia = EnumGarantiasTipo
						.getTipoGarantia(infraccion.getGarantiaTipoId().intValue());
				switch (tipoGarantia) {
				case PLACA:
					garantia.setDocumentoFolio(infraccion.getPlaca());
					break;
				case LICENCIA:
					garantia.setDocumentoFolio(infraccion.getLicencia());
					break;
				case TARJETA:
					garantia.setDocumentoFolio(infraccion.getTarjetaCirculacion());
					break;
				case PROMESA_PLACA:
					garantia.setDocumentoFolio(infraccion.getPlaca());
					break;
				case PROMESA_LICENCIA:
					garantia.setDocumentoFolio(infraccion.getLicencia());
					break;
				case PROMESA_TARJETA:
					garantia.setDocumentoFolio(infraccion.getTarjetaCirculacion());
					break;
				default:
					break;
				}
	
				garantia.setModificadoPor(emp_id);
				garantia.setGarantiaProcesoDTO(garantiaProcesoDAO.findOne(EnumGarantiasProceso.CREADA.getProcesoID()));
				garantia.setRecibida(false);
				garantia.setPagada(false);
				garantia.setEntregada(false);
				garantiasHDAO.save(garantia);
	
				// Crear estatus proceso
				GarantiaEstatusProcesoDTO garantiaEstatusDTO = new GarantiaEstatusProcesoDTO();
				garantiaEstatusDTO.setCreadoPor(emp_id);
				garantiaEstatusDTO.setFechaCreacion(new Date());
				garantiaEstatusDTO.setGarantiaId(garantia.getGarantiaId());
				garantiaEstatusDTO.setProcesoId(EnumGarantiasProceso.CREADA.getProcesoID());
				garantiaEstatusDTO.setModificadoPor(emp_id);
				garantiaEstatusDTO.setUltimaModificacion(new Date());
				garantiaEstatusProcesoDAO.save(garantiaEstatusDTO);
			}
			else {
				flag = false;
			}
		
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}
	
	public List<VSSPGarantiaConsGralFVO> buscarGarantiasSinProcesar(String placaOfical, boolean op) {
		List<VSSPGarantiaConsGralFVO> listaGarantiasDTO = new ArrayList<VSSPGarantiaConsGralFVO>();
		
		try {
			if(!op)
			listaGarantiasDTO = garantiaDAO.buscarGarantiasSinProcesar(placaOfical,
					EnumGarantiasProceso.CREADA.getProcesoID());
			else
				listaGarantiasDTO = garantiaDAO.buscarGarantiasSinProcesarOp(placaOfical,
						EnumGarantiasProceso.CREADA.getProcesoID());
		} catch (Exception e) {
			listaGarantiasDTO = null;
		}
		
		return listaGarantiasDTO;
	}
	
	@Override
	public Boolean obtenerPerfilCajero()
	{
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
 		return (cajaDTO != null ? true : false);
	}
	@Override
	public List<GarantiaPorPagarVO> buscarGarantiasPorPagar(String valor, String tipo){
		List<GarantiaPorPagarVO> listaGarantiasPorPagar = new ArrayList<GarantiaPorPagarVO>();
		String infracNum, docFolio, infracPlaca = null;
		
		switch(tipo)
		{
			case "N°. Infracción": infracNum = valor; docFolio = null; infracPlaca = null; break;
			case "Folio Documento": infracNum = null; docFolio = valor; infracPlaca = null; break;
			case "Placa Vehículo": infracNum = null; docFolio = null; infracPlaca = valor; break;
			default: infracNum = null; docFolio = null; infracPlaca = null;
		}
		
		listaGarantiasPorPagar = garantiaDAO.buscarGarantiasPorPagar(infracNum, docFolio, infracPlaca);
		return listaGarantiasPorPagar;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<GarantiaDetallePorPagarVO> buscarDetalleGarantiaPorPagar(String infracNum) throws NotFoundException{
		List<GarantiaDetallePorPagarVO> listaDetalleGarantiaPorPagar = new ArrayList<GarantiaDetallePorPagarVO>();
		
		CajaDTO cajero = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		
		Long numCaja = cajero.getCajaId();
		
		Map resultadoArchivo = new HashMap<>(); 
		
		//listaComboArchivoVO = radarCatalogoService.getComboCatSectores();
		String INFRAC_NUM_CTRL = garantiaDAO.buscarNumeroControlInfraccion(infracNum);
		
		//String nCI = "01060032710OBRERA1";
		
		if(numCaja == null){
			throw new NotFoundException("No se encontró un número de caja asociado al empleado.");
		}else if(INFRAC_NUM_CTRL == null){
			throw new NotFoundException("No se encontró el número de control infracción.");
		}else{
			listaDetalleGarantiaPorPagar = garantiaDAO.buscarDetalleGarantiaPorPagar(INFRAC_NUM_CTRL, numCaja.toString());
			if (listaDetalleGarantiaPorPagar == null || listaDetalleGarantiaPorPagar.size() == 0) {
				throw new NotFoundException("No se encontró el detalle del pago.");
			} else {
				return listaDetalleGarantiaPorPagar;
			}
		}
	}

	@Override
	public GarantiaDTO getGarantiaById(Long garantiaId) {
		return garantiasHDAO.findOne(garantiaId);
	
	}
	

	@Override
	public VSSPGarantiaRecepcionInfoFVO getGarantiaByIdInfo(Long garantiaId) {
		GarantiaDTO garantiaDTO = garantiasHDAO.findOne(garantiaId);
		VSSPGarantiaRecepcionInfoFVO objectVo = new VSSPGarantiaRecepcionInfoFVO() ;
		if(garantiaDTO != null){
			objectVo.setDocumentoFolio(garantiaDTO.getDocumentoFolio());
			objectVo.setDocumentoNombre(garantiaDTO.getGarantiaDocumentoDTO().getNombre());
			objectVo.setGarantiaId(garantiaDTO.getGarantiaId());
			objectVo.setInfraccNum(garantiaDTO.getInfraccionDTO().getInfraccNum());
		}
		
		return objectVo;
	}


	@Override
	public GarantiaProcesoDTO busquedaProceso() {

		return garantiaProcesoDAO.findOne(EnumGarantiasProceso.REVISADA.getProcesoID());
	}

	@Override
	public ByteArrayOutputStream generaReporteGarantiaRecibe(Long garantiaId, EmpleadosDTO empFirmado,
			String rutaTotalArchivo, String rutaTotalImagen) throws IOException {
		File rutaLinuxImagen = null;
		File rutaLinuxArchivo = null;
		VSSPGarantiaReporteRecibirFVO listaGarantiasReporteVO = new VSSPGarantiaReporteRecibirFVO();
		ByteArrayOutputStream reporte = null;
		GarantiasRecibe garantiasRecibe = new GarantiasRecibe();

		String nombreEmpleado = empFirmado.getEmpNombre() + " " + empFirmado.getEmpApePaterno() + " "
				+ empFirmado.getEmpApeMaterno();
		
		rutaLinuxImagen = new ClassPathResource(rutaTotalImagen).getFile();
		logger.error("Ruta de la Imagen : "+new ClassPathResource(rutaTotalImagen).toString());
		logger.error("Ruta de imagen con classpath : "+new ClassPathResource(rutaTotalImagen).getFile());
		rutaLinuxArchivo = new ClassPathResource(rutaTotalArchivo).getFile();
		logger.error("Ruta del archivo Jasper : "+ new ClassPathResource(rutaTotalArchivo).toString());
		logger.error("Ruta del archivo Jasper coon classpath existe?  : "+ new ClassPathResource(rutaTotalArchivo).exists());
		logger.error("Ruta del archivo Jasper coon classpath nombre  : "+ new ClassPathResource(rutaTotalArchivo).getFilename());
		listaGarantiasReporteVO = garantiaReporteMyBatis.buscarGarantiasReporteRecibir(garantiaId);

		reporte = garantiasRecibe.generaReporteGarantiasRecibePDF(garantiaId, nombreEmpleado, rutaLinuxArchivo,
				rutaLinuxImagen, listaGarantiasReporteVO);

		return reporte;
	}

	@Override
	public GarantiaEstatusProcesoDTO busquedaEstatusProcesoId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional (readOnly=true)
	@Override
	public List<VSSPGarantiaConsultaEntregaFVO> buscarGarantiaEntrega(String dato, Integer opcionBusqueda) {
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();

		List<VSSPGarantiaConsultaEntregaFVO> garantiaEntregaConsultaVO = new ArrayList<VSSPGarantiaConsultaEntregaFVO>();

		List<GarantiaDTO> garantiaDTO = garantiasHDAO.buscarGarantiasEntrega(opcionBusqueda, dato, procesoIdPagada);
		garantiaEntregaConsultaVO = ResponseConverter.converterLista(new ArrayList<>(), garantiaDTO,
				VSSPGarantiaConsultaEntregaFVO.class);
		GarantiaEstatusProcesoDTO garantiaEstatusProcesoDTO;

		for (int i = 0; i < garantiaEntregaConsultaVO.size(); i++) {
			garantiaEstatusProcesoDTO = garantiaEstatusProcesoDAO.buscaGarantiaEstatusProcesoPagadas(
					garantiaEntregaConsultaVO.get(i).getGarantiaId(), procesoIdPagada);
			garantiaEntregaConsultaVO.get(i).setFechaPago(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",
					garantiaEstatusProcesoDTO.getFechaCreacion()));

		}

		return garantiaEntregaConsultaVO;
	}

	@Transactional
	@Override
	public ResponseEntity<String> guardarGarantiaEntrega(Long garantiaId, EmpleadosDTO empFirmado,
			String observacion) {

		Date fechaActual = new Date();
		GarantiaDTO garantiaDTO = garantiasHDAO.findOne(garantiaId);
		GarantiaEstatusProcesoDTO garantiaEstatusProcesoDTO = new GarantiaEstatusProcesoDTO();

		garantiaDTO.setGarantiaProcesoDTO(garantiaProcesoDAO.findOne(procesoIdEntregado));
		garantiaDTO.setEntregada(Boolean.TRUE);
		garantiaDTO.setUltimaModificacion(fechaActual);
		garantiaDTO.setModificadoPor(empFirmado.getEmpId());

		garantiasHDAO.update(garantiaDTO);

		garantiaEstatusProcesoDTO.setCreadoPor(empFirmado.getEmpId());
		garantiaEstatusProcesoDTO.setFechaCreacion(fechaActual);
		garantiaEstatusProcesoDTO.setGarantiaId(garantiaId);
		garantiaEstatusProcesoDTO.setProcesoId(procesoIdEntregado);
		garantiaEstatusProcesoDTO.setObservaciones(observacion);
		garantiaEstatusProcesoDTO.setModificadoPor(empFirmado.getEmpId());
		garantiaEstatusProcesoDTO.setUltimaModificacion(new Date());

		garantiaEstatusProcesoDAO.save(garantiaEstatusProcesoDTO);

		return new ResponseEntity<String>("1", HttpStatus.OK);
	}

	@Override
	public ByteArrayOutputStream generaReporteGarantiaEntrega(Long garantiaId, String rutaTotalArchivo, String rutaTotalImagen) throws FileNotFoundException {
	
		VSSPGarantiaReporteEntregarFVO listaGarantiasEntregaVO = new VSSPGarantiaReporteEntregarFVO();
		ByteArrayOutputStream reporte = null;
		GarantiasEntrega garantiasEntrega = new GarantiasEntrega();

		listaGarantiasEntregaVO = garantiaReporteEntregaMyBatis.buscarGarantiasReporteEntregar(garantiaId);

		reporte = garantiasEntrega.generaReporteGarantiasEntregaPDF(garantiaId,rutaTotalArchivo, rutaTotalImagen, listaGarantiasEntregaVO);

		return reporte;
	}

	@Override
	public Date RutinaInicio(String fechaInicio) {
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		
		return rutinasTiempoImpl.convertirStringDate(fechaInicio, "dd/MM/yyyy");
	}

	@Override
	public Date RutinaFin(String fechaFin) {
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		return rutinasTiempoImpl.convertirStringDate(fechaFin, "dd/MM/yyyy");
	}

	@Override
	public List<GarantiaProcesoDTO> obtenerGarantiaProceso() {
		
		
		return garantiaProcesoDAO.findAll();
	}

	@Override
	public ByteArrayOutputStream imprimeReporteVoucher(String infracNum) {
		VSSPGarantiaGetVaucherFVO voucher = new VSSPGarantiaGetVaucherFVO();
		voucher.setInfracNum(infracNum);
		
		    garantiaPagosProcedureMyBatis.getVoucher(voucher);
	        ByteArrayOutputStream reporte = null;

	        if (!voucher.getResultado().toString().equals(ERROR_LINEA_BANCO)) {

	            if (voucher.getVoucherTipo().toString().equals(VOUCHER_ANTERIOR)) {
	                reporte = obtenerVoucher2011(voucher.getVoucher().toString(), infracNum);
	            } else {
	                reporte = obtenerVoucherNuevo(voucher.getVoucher().toString(),voucher.getVoucherPagoOrigen().toString(), infracNum);
	            }
	        } 
	        return reporte;
		
		
	}
	
	@Override
	 public ByteArrayOutputStream imprimirVoucherCentroPagos(String numOperacion){//se obtiene el voucher de centro de pagos enviando el numOperacion
	    	ByteArrayOutputStream reporte = null;
	    	String voucher="";
	 
	    	voucher=consultaCentroPagosServices.getVoucherPago(numOperacion);
	    	if(!voucher.isEmpty() && voucher.equalsIgnoreCase("Numero de operacion incorrecto - Reimpresion")==false){
	    		reporte = obtenerVoucherNuevo(voucher,VOUCHER_ORIGEN_WEB, numOperacion);
	    	}
	    	return reporte;
	    }
	@Transactional
	public ByteArrayOutputStream imprimirVoucherCancelacion(String tranId,String numOperacion){
		ByteArrayOutputStream reporte = null;
    	String voucher="";
    	TransaccionesCanceladasDTO canceladaDTO=new TransaccionesCanceladasDTO();
    	
    	canceladaDTO=transaccionesCanceladasDAO.consultaTransaccionCanceladaByTranId(Long.parseLong(tranId),numOperacion);
    	if(canceladaDTO!=null){
    		voucher=canceladaDTO.getTranVoucher();
    	}
    	if(!voucher.isEmpty()){
    		reporte = obtenerVoucherNuevo(voucher,VOUCHER_ORIGEN_WEB, numOperacion);
    	}
    	return reporte;
	}
	
	public ByteArrayOutputStream obtenerVoucher2011(String voucher, String infracNum) {
        ByteArrayOutputStream reporte = null;
        GarantiaGeneraReporteVaucher generaReporteVaucher  = new GarantiaGeneraReporteVaucher();
        String LogoSSP = context.getRealPath("/WEB-INF/imagenes/head_infra.gif"); 
        String LogoPlaca = context.getRealPath("/WEB-INF/imagenes/SSP_PLACA.gif");
        String sRuta = context.getRealPath("/WEB-INF/jasper/garantias/Voucher.jasper");
        String arrVoucher[] = new String[0];
        arrVoucher = voucher.split("@");
        ArrayList arlaux = new ArrayList();
        arlaux.add(0, infracNum);
        try {
            for (int i = 1; i <= arrVoucher.length - 1; i++) {
                int aux = arrVoucher[i].length();
               
                if (arrVoucher[i].trim().length() > 3) {
                   
                    arlaux.add(i, arrVoucher[i].substring(4, aux).trim());
                } else {
                    arlaux.add(i, "");
                }
            }
        } catch (Exception e) {
           
        }


        reporte = generaReporteVaucher.generaReporteVoucher(arlaux,LogoSSP,LogoPlaca,sRuta);

        return reporte;
    }
	
	public ByteArrayOutputStream obtenerVoucherNuevo(String voucher,String voucherOrigen, String infracNum) {

        String arrVoucher[] = new String[0];
        ByteArrayOutputStream reporte = null;
        GarantiaGeneraReporteVoucher generaReporteVoucher = new GarantiaGeneraReporteVoucher();
        GarantiaGeneraReporteVoucherHH garantiaGeneraReporteVoucherHH = new GarantiaGeneraReporteVoucherHH();
        String LogoSSP = context.getRealPath("WEB-INF/imagenes/head_infra.gif");
        String LogoPlaca = context.getRealPath("WEB-INF/imagenes/SSP_PLACA.gif");
        String sRutaDinamico = context.getRealPath("/WEB-INF/jasper/garantias/VoucherDinamico.jasper");
        String sRutaHH = context.getRealPath("/WEB-INF/jasper/garantias/VoucherHH.jasper");
         
        if (voucherOrigen.equals(VOUCHER_ORIGEN_WEB)) {   	
        	 boolean resultado = voucher.contains("\\^");
      	   if(resultado){
          	   arrVoucher = voucher.split("\\^");   
             }
      	   else{
          	   arrVoucher = voucher.split("voucher_cliente");   
             }
           
            String voucherComercio = "";
            String voucherCliente = "";
            if (arrVoucher.length > 0) {
            	voucherComercio = arrVoucher[0];              
                voucherCliente = arrVoucher[1]; 
            }
            reporte = generaReporteVoucher.generaReporteVoucher(voucherComercio, voucherCliente, sRutaDinamico);  
      } else {
            arrVoucher = voucher.split("@");
            ArrayList arlaux = new ArrayList();
            arlaux.add(0, infracNum);
           
                for (int i = 1; i <= arrVoucher.length - 1; i++) {
	                    int aux = arrVoucher[i].length();
	                    if (arrVoucher[i].trim().length() > 3) {
	                        arlaux.add(i, arrVoucher[i].substring(4, aux).trim());
	                    } else {
	                        arlaux.add(i, "");
	                    }
                }
            

            reporte = garantiaGeneraReporteVoucherHH.generaReporteVoucherHH(arlaux,LogoSSP,LogoPlaca,sRutaHH);

        }

        return reporte;

    }

	@Override
	public List<VSSPGarantiaReporteGeneralFVO> consultaReporteGral(String fechaInicio, String fechaFin) {
		
		
		return garantiaReporteGralMyBatisDAO.consultaReporteGeneral(fechaInicio, fechaFin);
	}

	@Override
	public ByteArrayOutputStream generaReporteExcel(List<VSSPGarantiaReporteGeneralFVO> garantiaReporteGeneralVO,
			String fechaInicio, String fechaFin) {
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
		List<String> subtitulos = new ArrayList<String>(); 
		
		titulos.add("Infracción");
		titulos.add("Fecha Infracción");
		titulos.add("Vehículo Placa");
		titulos.add("Oficial Placa");
		titulos.add("Oficial Nombre");
		titulos.add("Documento");
		titulos.add("Documento Folio");
		titulos.add("Proceso Recepción");
		titulos.add("Recibida");
		titulos.add("Depósito");
		titulos.add("Pagada");
		titulos.add("Cancelada");
		titulos.add("Observaciones");
		
		encabezadoTitulo.add(titulos);
		
		String fechaReporte = "";
		if(fechaInicio != null && fechaFin != null){
			propiedadesReporte.setFechaI(fechaInicio);
			propiedadesReporte.setFechaF(fechaFin);
			fechaReporte = fechaInicio.replaceAll("/","") + "-" + fechaFin.replaceAll("/","");
		}else{
			RutinasTiempoImpl rutinastiempo = new RutinasTiempoImpl();
			propiedadesReporte.setFechaI(rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()));
			fechaReporte = rutinastiempo.getStringDateFromFormta("dd/MM/yyyy", new Date()).replaceAll("/", "");
		}
		
		
		propiedadesReporte.setTituloExcel("Garantías "+ fechaInicio+ "-" + fechaFin);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(VSSPGarantiaReporteGeneralFVO garantiaReporteExc : garantiaReporteGeneralVO){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(garantiaReporteExc.getInfrac_num());
			listaContenido1.add(garantiaReporteExc.getInfrac_fecha());
			listaContenido1.add(garantiaReporteExc.getInfrac_placa());
			listaContenido1.add(garantiaReporteExc.getEmp_placa());
			listaContenido1.add(garantiaReporteExc.getEmp_nombre());
			listaContenido1.add(garantiaReporteExc.getDocumento_nombre());
			listaContenido1.add(garantiaReporteExc.getDocumento_folio());
			listaContenido1.add(garantiaReporteExc.getProceso_recepcion());
			listaContenido1.add(garantiaReporteExc.getRecibida());
			listaContenido1.add(garantiaReporteExc.getDeposito());
			listaContenido1.add(garantiaReporteExc.getPagada());
			listaContenido1.add(garantiaReporteExc.getCancelada());
			listaContenido1.add(garantiaReporteExc.getObservaciones());
			
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

	@Override
	public String obtenerTokenMit() {
		return operacionesService.getTokenEMV();
	}
	
	@Override
	
	public PagoVO pagarGarantiaPorDocumento(DatosPagoVO datosPagoVO) {
		CajaDTO cajero = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		datosPagoVO.setCajaId(cajero.getCajaId().toString());
		String vInfracTipo = fnInfracTipo(datosPagoVO.getInfracNum());
		VSSPInfracConsGralFVO ListaInfraccionesVOGral = new VSSPInfracConsGralFVO();
		RespuestaWSReasignaLineaCapturaVO oldInfraccionVODig = new RespuestaWSReasignaLineaCapturaVO();//Parámetro infracciones digitalización
		VSSPInfracConsGralFVO oldListaInfraccionesVO = new VSSPInfracConsGralFVO();//Parámetro infracciones
		VSSPInfracConsGralFVO ListaInfraccionesVODigA = new VSSPInfracConsGralFVO();
		RespuestaWSReasignaLineaCapturaVO oldInfraccionVORad = new RespuestaWSReasignaLineaCapturaVO();//Parámetro infracciones radar
		if(vInfracTipo.equals("IMPRESA")){//Antes bitácora tabla infracciones_digitalización
			ListaInfraccionesVODigA = infraccionMyBatisDAO.datosParaBitacoraDigitalizacion(datosPagoVO.getInfracNum());
			if(ListaInfraccionesVODigA != null){
		        oldInfraccionVODig.setAutorizaId(ListaInfraccionesVODigA.getAutorizaId());
			} 		
		}
		else if(vInfracTipo.equals("01") || vInfracTipo.equals("02")
	                   || vInfracTipo.equals("04") || vInfracTipo.equals("05") || 
	                   vInfracTipo.equals("06")){
				ListaInfraccionesVOGral = new VSSPInfracConsGralFVO();//Antes bitácora tabla infracciones
				ListaInfraccionesVOGral = infraccionMyBatisDAO.datosParaBitacoraInfracciones(datosPagoVO.getInfracNum());
				oldListaInfraccionesVO.setAutorizaId(ListaInfraccionesVOGral.getAutorizaId());
			}
			else if(vInfracTipo.equals("03") || vInfracTipo.equals("07")
		                   || vInfracTipo.equals("08")){
				    ListaInfraccionesVOGral = new VSSPInfracConsGralFVO();//Antes bitácora tabla infracciones radar
				    ListaInfraccionesVOGral = infraccionMyBatisDAO.datosParaBitacoraInfraccionesRadar(datosPagoVO.getInfracNum());
				    oldInfraccionVORad.setAutorizaId(ListaInfraccionesVOGral.getAutorizaId());
				}
			
		PagoVO pagoVO = pagoDocumento.pagar(datosPagoVO);
		
		RespuestaWSReasignaLineaCapturaVO newInfraccionVODig = new RespuestaWSReasignaLineaCapturaVO();//Parametro infracciones digitalización
		AltaInfraccionSPVO newInfraccionVO = new AltaInfraccionSPVO();//Parámetro infracciones
		VSSPInfracConsGralFVO ListaInfraccionesVOD = new VSSPInfracConsGralFVO();
		RespuestaWSReasignaLineaCapturaVO newInfraccionVORad = new RespuestaWSReasignaLineaCapturaVO();//Parametro infracciones radar
		if(vInfracTipo.equals("IMPRESA")){//Después bitácora tabla infracciones_digitalización
			    ListaInfraccionesVOD = infraccionMyBatisDAO.datosParaBitacoraDigitalizacion(datosPagoVO.getInfracNum());
				if(ListaInfraccionesVOD != null){
				    newInfraccionVODig.setAutorizaId(ListaInfraccionesVOD.getAutorizaId());
					newInfraccionVODig.setUsuario(ListaInfraccionesVOD.getModificadoPor() != null ? ListaInfraccionesVOD.getModificadoPor().toString() : null);
					newInfraccionVODig.setInfracRadarVO(ListaInfraccionesVOD);
				}
		}
		else if(vInfracTipo.equals("01") || vInfracTipo.equals("02")
                || vInfracTipo.equals("04") || vInfracTipo.equals("05") || 
                vInfracTipo.equals("06")){
				ListaInfraccionesVOGral = new VSSPInfracConsGralFVO();
				ListaInfraccionesVOGral = infraccionMyBatisDAO.datosParaBitacoraInfracciones(datosPagoVO.getInfracNum());//Después bitácora tabla infracciones
				newInfraccionVO.setAutorizaId(ListaInfraccionesVOGral.getAutorizaId());
				newInfraccionVO.setP_infrac_num_ctrl(datosPagoVO.getInfracNum());
				newInfraccionVO.setP_modificado_por(ListaInfraccionesVOGral.getModificadoPor());
		      }
			  else if(vInfracTipo.equals("03") || vInfracTipo.equals("07")
		                   || vInfracTipo.equals("08")){
				    ListaInfraccionesVOGral = new VSSPInfracConsGralFVO();//Después bitácora tabla infracciones radar
				    ListaInfraccionesVOGral = infraccionMyBatisDAO.datosParaBitacoraInfraccionesRadar(datosPagoVO.getInfracNum());
				    newInfraccionVORad.setAutorizaId(ListaInfraccionesVOGral.getAutorizaId());
				    newInfraccionVORad.setUsuario(ListaInfraccionesVOGral.getModificadoPor() != null ? ListaInfraccionesVOGral.getModificadoPor().toString() : null);
				    newInfraccionVORad.setInfracRadarVO(ListaInfraccionesVOGral);
				   } 
			  
		if(pagoVO.getResultado().equals("1")){
			System.out.println("Dato: "+ pagoVO.getResultado());
			try {
				vInfracTipo = fnInfracTipo(datosPagoVO.getInfracNum());
				if(vInfracTipo.equals("IMPRESA")){
					if(ListaInfraccionesVODigA != null && ListaInfraccionesVOD != null){
				     bitacoraCambiosService.guardarListaBitacoraCambios(bitTrBitUpInfracDig.guardarCambiosBitacora(newInfraccionVODig, oldInfraccionVODig));    	
					}
				}
				else if(vInfracTipo.equals("01") || vInfracTipo.equals("02")
	                   || vInfracTipo.equals("04") || vInfracTipo.equals("05") || 
	                   vInfracTipo.equals("06")){
					     bitacoraCambiosService.guardarListaBitacoraCambios(bitTrBitUpInfrac.guardarCambiosBitacora(newInfraccionVO, oldListaInfraccionesVO));    	
					}
	                else if(vInfracTipo.equals("03") || vInfracTipo.equals("07")
			                   || vInfracTipo.equals("08")){
					     bitacoraCambiosService.guardarListaBitacoraCambios(bitTrBitUpInfracRadar.guardarCambiosBitacora(newInfraccionVORad, oldInfraccionVORad));    	
					     }
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			guardarGarantiaPagada(1, pagoVO.getNumInfrac());
		}
		return pagoVO;
	}
	
	public String fnInfracTipo(String infracNum){
		
		String vInfracTipo = "";
		
		if(infracNum.length() < 11){
			vInfracTipo = "IMPRESA";
		}else{
			vInfracTipo = infracNum.substring(0,2);
		}
				
		return vInfracTipo;	
	}
	
	@Override
	public void guardarGarantiaPagada(Integer tipoPago, String infracNum){
		/*Hacer update a GARANTIAS_ESTATUS_PROCESO donde 
		ubiques la garantia por su garantia_id y cambiar 
		el proceso_id a 2(Simboliza de PAGADO), comentario 
		debe ser= "Garantía Pagada"*/
		List<String> garantiaId = garantiaDAO.obtenerGarantiaIdPorInfraccion(infracNum);
		
		garantiaDAO.guardarProcesoEstatusGarantiaPagada(garantiaId.get(0), usuarioFirmadoService.getUsuarioFirmadoVO().getId(), "Garantia Pagada");
		
		/*Hacer update a GARANTIAS donde ubiques la 
		 * garantia por su numero de infraccion y cambies
		 * el pagada, tipo de pago, */
		garantiaDAO.guardarGarantiaPagada(tipoPago, usuarioFirmadoService.getUsuarioFirmadoVO().getId(), infracNum);
		
	}
	
	@Override
	public PagoVO pagarGarantiaPorTarjeta(DatosPagoVO datosPagoVO) {
		CajaDTO cajero = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		datosPagoVO.setCajaId(cajero.getCajaId().toString());
		PagoVO pagoVO = pagoTarjeta.pagar(datosPagoVO);
		if(pagoVO.getResultado() != null){
			if(pagoVO.getResultado().equals("1"))
			guardarGarantiaPagada(2, pagoVO.getNumInfrac());
		}
		return pagoVO;
	}


	@Override
	public ByteArrayOutputStream generarReporteExcelGarantiasRecepcion(
			List<VSSPGarantiaConsGralFVO> garantiasRecepcionVO) {
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
				
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		
		titulos.add("Infracción");
		titulos.add("Fecha Infracción");
		titulos.add("No. Garantía");
		titulos.add("Tipo Documento");
		titulos.add("Folio Documento");
		titulos.add("Oficial Nombre");
		titulos.add("Oficial Placa");
		
		encabezadoTitulo.add(titulos);
		
		
		String fechaReporte = rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",new Date());
		
		propiedadesReporte.setTituloExcel("Garantías Recepción "+ fechaReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(VSSPGarantiaConsGralFVO garantiaReporteExc : garantiasRecepcionVO){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(garantiaReporteExc.getInfraccionFolio());
			listaContenido1.add(garantiaReporteExc.getFechaInfraccion());
			listaContenido1.add(garantiaReporteExc.getGarantiaId().toString());
			listaContenido1.add(garantiaReporteExc.getDocumentoNombre());
			listaContenido1.add(garantiaReporteExc.getDocumentoFolio());
			listaContenido1.add(garantiaReporteExc.getEmpleadoNombre());
			listaContenido1.add(garantiaReporteExc.getEmpleadoPlaca());

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
	
	@Override
	public ByteArrayOutputStream generarReporteExcelGarantiasEntrega(
			List<VSSPGarantiaConsultaEntregaFVO> garantiasEntregaVO, Boolean op) {
		
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
				
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		
		titulos.add("Infracción");
		titulos.add("Fecha Infracción");
		titulos.add("No. Garantía");
		titulos.add("Folio Documento");
		titulos.add("Tipo Documento");
		titulos.add("Fecha Pago");
		
		encabezadoTitulo.add(titulos);
		
		String fechaReporte = rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",new Date());
		
		propiedadesReporte.setTituloExcel("Garantías Entrega "+ fechaReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(VSSPGarantiaConsultaEntregaFVO garantiaReporteExc : garantiasEntregaVO){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(garantiaReporteExc.getInfraccionDTO().getInfraccNum());
			listaContenido1.add(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy", garantiaReporteExc.getInfraccionDTO().getFechaCreacion()));
			listaContenido1.add(garantiaReporteExc.getGarantiaId().toString());
			listaContenido1.add(garantiaReporteExc.getDocumentoFolio());
			listaContenido1.add(garantiaReporteExc.getGarantiaDocumentoDTO().getNombre());
			listaContenido1.add(garantiaReporteExc.getFechaPago());
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


	@Override
	public ByteArrayOutputStream generarReporteExcelConsulta(List<VSSPGarantiaConsultaFVO> garantiasConsultaVO) {
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
				
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		
		titulos.add("Infracción");
		titulos.add("Fecha Infracción");
		titulos.add("No. Garantía");
		titulos.add("Tipo Documento");
		titulos.add("Folio Documento");
		titulos.add("Proceso Garantía");
		titulos.add("Desposito");
		titulos.add("Recibida");
		titulos.add("Pagada");
		titulos.add("Entregada");
		
		encabezadoTitulo.add(titulos);
		
		String fechaReporte = rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",new Date());
		
		propiedadesReporte.setTituloExcel("Garantías Consulta "+ fechaReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(VSSPGarantiaConsultaFVO garantiaReporteExc : garantiasConsultaVO){
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(garantiaReporteExc.getInfraccNum());
			listaContenido1.add(garantiaReporteExc.getFechaInfraccion());
			listaContenido1.add(garantiaReporteExc.getGarantiaId().toString());
			listaContenido1.add(garantiaReporteExc.getNombreDocumento());
			listaContenido1.add(garantiaReporteExc.getDocumentoFolio());
			listaContenido1.add(garantiaReporteExc.getNombreProceso());
			listaContenido1.add(garantiaReporteExc.getDeposito());
			listaContenido1.add(garantiaReporteExc.getRecibida());
			listaContenido1.add(garantiaReporteExc.getPagada());
			listaContenido1.add(garantiaReporteExc.getEntregada());
			
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
	
	
	@Override
	public ByteArrayOutputStream generaReporteGarantiaRecibeMasiva(Long idGarantiaInd,
			EmpleadosDTO empFirmado, String rutaTotalArchivo, String rutaTotalImagen, Integer bandJasper, Integer idLote) throws FileNotFoundException, IOException {
		
		Integer cantidadesId = null;
		File rutaLinuxImagen = null;
		File rutaLinuxArchivo = null;
		
		List<VSSPGarantiaReporteRecibirMasivaFVO> listaGarantiasReporteVO = new ArrayList<VSSPGarantiaReporteRecibirMasivaFVO>();
		
		VSSPGarantiaReporteRecibirFVO listaGarantiasReportVO = new VSSPGarantiaReporteRecibirFVO();
		ByteArrayOutputStream reporte = null;
		GarantiasRecibe garantiasRecibe = new GarantiasRecibe();
			
		Long garantiaId = idGarantiaInd;
		
		String nombreEmpleado = empFirmado.getEmpNombre() + " " + empFirmado.getEmpApePaterno() + " "
				+ empFirmado.getEmpApeMaterno();
		
			
		rutaLinuxImagen = new ClassPathResource(rutaTotalImagen).getFile();
//		logger.error("Ruta de la Imagen : "+new ClassPathResource(rutaTotalImagen).toString());
//		logger.error("Ruta de imagen con classpath : "+new ClassPathResource(rutaTotalImagen).getFile());
		rutaLinuxArchivo = new ClassPathResource(rutaTotalArchivo).getFile();		
//		logger.error("Ruta del archivo Jasper : "+ new ClassPathResource(rutaTotalArchivo).toString());
//		logger.error("Ruta del archivo Jasper coon classpath existe?  : "+ new ClassPathResource(rutaTotalArchivo).exists());
//		logger.error("Ruta del archivo Jasper coon classpath nombre  : "+ new ClassPathResource(rutaTotalArchivo).getFilename());
		
		if(idLote>0){
			
			listaGarantiasReporteVO = garantiaReporteMyBatis.buscarGarantiasMasivaReporteRecibir(idLote);
			cantidadesId=garantiaReporteMyBatis.cantidadLote(idLote);
		}
		else
		{
			listaGarantiasReportVO = garantiaReporteMyBatis.buscarGarantiasReporteRecibir(garantiaId);
		}	
		
	 reporte = garantiasRecibe.generaReporteGarantiasRecibePDFMasiva(idGarantiaInd, nombreEmpleado, rutaLinuxArchivo,
				rutaLinuxImagen, listaGarantiasReporteVO, bandJasper,listaGarantiasReportVO, cantidadesId);

		return reporte;
		
	}
	
	
	@Override
	public ByteArrayOutputStream generaReporteGarantiaRecibeMasiva2(Long idGarantiaInd,
			EmpleadosDTO empFirmado, String rutaTotalArchivo, String rutaTotalImagen, Integer bandJasper, Integer idLote) throws FileNotFoundException, IOException {
		Integer cantidadesId = null;
		File rutaLinuxImagen = null;
		File rutaLinuxArchivo = null;
		
		List<VSSPGarantiaReporteRecibirMasivaFVO> listaGarantiasReporteVO = new ArrayList<VSSPGarantiaReporteRecibirMasivaFVO>();
		
		VSSPGarantiaReporteRecibirFVO listaGarantiasReportVO = new VSSPGarantiaReporteRecibirFVO();
		ByteArrayOutputStream reporte = null;
		GarantiasRecibe garantiasRecibe = new GarantiasRecibe();
			
		Long garantiaId = idGarantiaInd;
		
		String nombreEmpleado = empFirmado.getEmpNombre() + " " + empFirmado.getEmpApePaterno() + " "
				+ empFirmado.getEmpApeMaterno();
		
			
		rutaLinuxImagen = new ClassPathResource(rutaTotalImagen).getFile();
//		logger.error("Ruta de la Imagen : "+new ClassPathResource(rutaTotalImagen).toString());
//		logger.error("Ruta de imagen con classpath : "+new ClassPathResource(rutaTotalImagen).getFile());
		rutaLinuxArchivo = new ClassPathResource(rutaTotalArchivo).getFile();		
//		logger.error("Ruta del archivo Jasper : "+ new ClassPathResource(rutaTotalArchivo).toString());
//		logger.error("Ruta del archivo Jasper coon classpath existe?  : "+ new ClassPathResource(rutaTotalArchivo).exists());
//		logger.error("Ruta del archivo Jasper coon classpath nombre  : "+ new ClassPathResource(rutaTotalArchivo).getFilename());

		if(idLote>0){
			
			listaGarantiasReporteVO = garantiaReporteMyBatis.buscarGarantiasMasivaReporteRecibir(idLote);
			cantidadesId=garantiaReporteMyBatis.cantidadLote(idLote);
		}
		else
		{
			listaGarantiasReportVO = garantiaReporteMyBatis.buscarGarantiasReporteRecibir(garantiaId);	
		}	
		
	 reporte = garantiasRecibe.generaReporteGarantiasRecibePDFMasiva(idGarantiaInd, nombreEmpleado, rutaLinuxArchivo,
				rutaLinuxImagen, listaGarantiasReporteVO, bandJasper,listaGarantiasReportVO, cantidadesId);

		return reporte;
		
	}
	@Override
	public String obtenerRutaCarpeta(String cdLlave) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, String> getParametrosLP() {
		List<Map<String, String>> listaParametros=garantiaReporteMyBatis.buscarQuerys();
		Map<String, String> parametros = new HashMap<String, String>();
		for(Map<String, String> registro : listaParametros) {
		parametros.put(registro.get("CD_LLAVE_P_CONFIG"), registro.get("CD_VALOR_P_CONFIG"));
		

		}
		return parametros;
	}

}
