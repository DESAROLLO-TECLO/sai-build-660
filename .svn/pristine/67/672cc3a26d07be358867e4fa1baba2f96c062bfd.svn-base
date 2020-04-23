package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.ingresos.IngresosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.ParametrosPagoBloqueoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos.IngresosDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos.ConsultaPagoInfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.InfraccionDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.InfraccionPorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ParametrosPagosBloqueoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaTrasladoVO;
import mx.com.teclo.saicdmx.util.comun.AssemblerParametroPagos;
import mx.com.teclo.siidf.centrodepagos.mit.service.IOperacionesService;

/**
 * Copyright (c) 2016, Teclo Mexicana.
 * Descripcion 					: PagoInfraccionServiceImpl 
 * Historial de Modificaciones 	:
 * Descripcion del Cambio 		: Creacion
 * @author 						: fjmb
 * @version 					: 1.0 
 * Fecha 						: 05/Octubre/2016
 */

@Service
public class ConsultaInfraccionServiceImpl implements ConsultaInfraccionService {

	private static final Logger logger = LoggerFactory.getLogger(ConsultaInfraccionServiceImpl.class);

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private CajaDAO cajaDAO;
	@Autowired
	private ConsultaPagoInfraccionMyBatisDAO consultaPagoInfraccionMyBatisDAO;
	@Autowired
	@Qualifier("pagoEfectivo")
	private PagoService pagoEfectivo;	
	@Autowired
	private ParametrosPagoBloqueoDAO parametrosPagoBloqueoDAO;
		
	private CajaDTO cajaDTO;
	@Autowired
	@Qualifier("operacionesService")
	private IOperacionesService operacionesService;
	
	@Autowired
	private IngresosDAO ingresosDAO;

	
	@Override
	public List<InfraccionDepositoVO> obtenerInfracciones(String parametro, String valor) {

		logger.info("Tipo parametro:" + parametro + " valorParametro:" + valor);
		List<InfraccionDepositoVO> listaPagoInfraccionVO = null;
		
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		
		switch (parametro) {
		case "PLACA":
			listaPagoInfraccionVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagoPlaca(valor,
					cajaDTO.getDeposito().getDepId().toString());
			break;
		case "INFRACCION":
			listaPagoInfraccionVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagoInfraccion(valor,
					cajaDTO.getDeposito().getDepId().toString());
			break;
		case "IMPRESA":
			listaPagoInfraccionVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagoImpresa(valor,
					cajaDTO.getDeposito().getDepId().toString());
			break;
		case "NCI":
			listaPagoInfraccionVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagoNCI(valor,
					cajaDTO.getDeposito().getDepId().toString());
			break;
		default:
			break;
		}

		return listaPagoInfraccionVO;
	}
	

	@Override
	public List<InfraccionDepositoVO> obtenerInfraccionesActa(String parametro, String valor) {

		logger.info("Tipo parametro:" + parametro + " valorParametro:" + valor);
		List<InfraccionDepositoVO> listaPagoInfraccionVO = null;

		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		
		switch (parametro) {
		case "PLACA":
			listaPagoInfraccionVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesActaPlaca(valor,
					cajaDTO.getDeposito().getDepId().toString());
			break;
		case "INFRACCION":
			listaPagoInfraccionVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesActaInfraccion(valor,
					cajaDTO.getDeposito().getDepId().toString());
			break;
		case "IMPRESA":
			listaPagoInfraccionVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesActaImpresa(valor,
					cajaDTO.getDeposito().getDepId().toString());
			break;
		case "NCI":
			listaPagoInfraccionVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesActaNCI(valor,
					cajaDTO.getDeposito().getDepId().toString());
			break;
		default:
			break;
		}

		return listaPagoInfraccionVO;
	}

	@Override
	@Transactional
	public List<InfraccionPorPagarVO> obtenerInfraccionesPorPagar(String nci, String noInfraccion) {

		logger.info("NCI :" + nci + " No Infraccion :" + noInfraccion);
		List<InfraccionPorPagarVO> listaInfraccionPorPagarVO = null;
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		listaInfraccionPorPagarVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPorPagar(nci,
				Integer.parseInt(cajaDTO.getCajaId().toString()));

		for (InfraccionPorPagarVO infraccionPorPagarVO : listaInfraccionPorPagarVO) {
			infraccionPorPagarVO.setInfracTipo(infraccionPorPagarVO.getInfracNum().substring(0, 2));

			if (infraccionPorPagarVO.getInfracTotalPagar() == null) {
				infraccionPorPagarVO.setInfracRecargo("0");
				infraccionPorPagarVO.setInfracTotalPagar("0");
			}
			if (listaInfraccionPorPagarVO.size() == 1)
				infraccionPorPagarVO.setUltimoPago(true);
			
			ParametrosPagosBloqueoVO parametrosPagosBloqueoVO = AssemblerParametroPagos.toParametrosPagosBloqueoVO(parametrosPagoBloqueoDAO.obtenerParametroPago(1));  
			infraccionPorPagarVO.setStBloqueoPago(parametrosPagosBloqueoVO.getStBloqueoPago());
		}

		return listaInfraccionPorPagarVO;
	}

	@Override
	public List<InfraccionPorPagarVO> obtenerInfraccionesPagadasPorDia(String parametro, String valor, String fecha) {
		logger.info("Tipo parametro:" + parametro + " valorParametro:" + valor);

		List<InfraccionPorPagarVO> listaInfraccionPorPagarVO = null;

		switch (parametro) {
		case "PLACA":
			listaInfraccionPorPagarVO = fecha != null
					? consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasPorDiaPlacaConFecha(valor, fecha)
					: consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasPorDiaPlacaSinFecha(valor);
			break;
		case "INFRACCION":
			listaInfraccionPorPagarVO = fecha != null
					? consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasPorDiaInfraConFecha(valor, fecha)
					: consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasPorDiaInfraSinFecha(valor);
			break;
		case "NCI":
			listaInfraccionPorPagarVO = fecha != null
					? consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasPorDiaNCIConFecha(valor, fecha)
					: consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasPorDiaNCISinFecha(valor);
			break;
		default:
			break;
		}

		return listaInfraccionPorPagarVO;
	}

	@Override
	public List<PagadoVO> obtenerInfraccionesPagadas(String parametro, String valor) {

		logger.info("Parametro :" + parametro + " Valor :" + valor);
		List<PagadoVO> listaInfraccionDepositoVO = null;

		switch (parametro) {
		case "PLACA":
			listaInfraccionDepositoVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasPlaca(valor);
			break;
		case "INFRACCION":
			listaInfraccionDepositoVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasInfraccion(valor);
			break;
		case "IMPRESA":
			listaInfraccionDepositoVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasImpresa(valor);
			break;
		case "NCI":
			listaInfraccionDepositoVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasNCI(valor);
			break;
		case "DOCUMENTO":
			listaInfraccionDepositoVO = consultaPagoInfraccionMyBatisDAO.buscarInfraccionesPagadasDocumento(valor);
			break;
		default:
			break;
		}

		return listaInfraccionDepositoVO;
	}

	@Override
	public List<FilterValuesVO> obtenerTipoBusquedaInfraccion() {

		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		filterValues.add(new FilterValuesVO(1, "PLACA", "No. De Placa"));
		filterValues.add(new FilterValuesVO(3, "INFRACCION", "No. Infracción"));
		filterValues.add(new FilterValuesVO(2, "IMPRESA", "Infracción Impresa"));
		filterValues.add(new FilterValuesVO(4, "NCI", "NCI"));

		return filterValues;
	}
	
	@Override
	public List<FilterValuesVO> obtenerTipoBusquedaInfraccionActa() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		filterValues.add(new FilterValuesVO(1, "PLACA", "No. De Placa"));
		filterValues.add(new FilterValuesVO(3, "INFRACCION", "No. Infracción"));
		filterValues.add(new FilterValuesVO(2, "IMPRESA", "Infracción Documento"));
		filterValues.add(new FilterValuesVO(4, "NCI", "NCI"));
		return filterValues;
	}

	@Override
	public List<FilterValuesVO> obtenerTiposPagoInfraccion() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		filterValues.add(new FilterValuesVO(1, "EFECTIVO", "Efectivo"));
		filterValues.add(new FilterValuesVO(99, "DOCUMENTO", "Otras Entidades"));
		filterValues.add(new FilterValuesVO(3, "TARJETA", "Tarjeta Crédito / Débito"));

		return filterValues;
	}

	@Override
	public List<FilterValuesVO> obtenerEntidadesPagoInfraccion() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		filterValues.add(new FilterValuesVO(5, "BANCO", "BANCO"));
		filterValues.add(new FilterValuesVO(4, "INTERNET", "INTERNET"));
		filterValues.add(new FilterValuesVO(6, "SETRAVI", "SETRAVI"));
		filterValues.add(new FilterValuesVO(7, "TES", "TESORERIA"));

		return filterValues;

	}
	@Override
	public List<FilterValuesVO> obtenerTipoBusquedaTransacciones() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		filterValues.add(new FilterValuesVO(1, "TODOS", "Todos"));
		filterValues.add(new FilterValuesVO(2, "FECHAS", "Fechas"));
		filterValues.add(new FilterValuesVO(3, "NUMOPERACION", "No. Operación"));
		filterValues.add(new FilterValuesVO(4, "INFRACCION", "No. Infracción"));
		filterValues.add(new FilterValuesVO(5, "REFERENCIA", "No. Referencia"));

		return filterValues;
	}

	@Override
	public boolean obtenerPerfilCajero(String numPlaca) {
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
 		return (cajaDTO != null ? true : false);
	}


	@Override
	public String obtenerTokenMit() {
		return operacionesService.getTokenEMV();
	}

	@Transactional
	@Override
	public IngresosDTO validarTraslado(String tipoParam,String valor, boolean isIngreso) {
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		IngresosDTO ingreso = ingresosDAO.buscaTraslado(tipoParam,valor, cajaDTO.getDeposito().getDepId(), isIngreso);
		return ingreso;
	}

	@Transactional
	@Override
	public ConsultaTrasladoVO buscaTrasladoParaIngreso(String tipoParam,String valor, boolean isIngreso){
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		IngresosDTO traslado = ingresosDAO.buscaTraslado(tipoParam,valor, cajaDTO.getDeposito().getDepId(), isIngreso);
		ConsultaTrasladoVO objectVo = null;
		if(traslado != null){
			objectVo = new ConsultaTrasladoVO();
			objectVo.setDepositoDestino(traslado.getMovimientos().get(0).getDepNomDestino());
			objectVo.setDepositoOrigen(traslado.getMovimientos().get(0).getDepNomOrigen());
			objectVo.setFechaCreacion(traslado.getMovimientos().get(0).getFechaCreacion());
			objectVo.setFechaSalidaVehiculo(traslado.getFhMovimiento());
			objectVo.setInfracDocto(traslado.getInfracDocto());
			objectVo.setInfracNum(traslado.getInfracNum());
			objectVo.setIngrNumCtrl(traslado.getIngrNumCtrl());
			objectVo.setMarcaVehiculo(traslado.getInfraccion().getvModMar().getVehiculoMarca().getvMarNombre());
			objectVo.setModeloVehiculo(traslado.getInfraccion().getvModMar().getvModNombre());
			objectVo.setNumPlacaVehiculo(traslado.getInfraccion().getInfraccPlaca());
			objectVo.setNumResguardo(traslado.getMovimientos().get(0).getNumResguardo());
			objectVo.setNumSerie(traslado.getMovimientos().get(0).getNumSerie());
			objectVo.setPlacaOficial(traslado.getInfraccion().getInfraccPlaca());
			objectVo.setFolioBoletaPreImpresa(traslado.getInfraccion().getInfraccImpresa());
			objectVo.setIdPlacaOficial(traslado.getMovimientos().get(0).getIdAutoriza());
			objectVo.setCodGrua(traslado.getGruaCod());
			objectVo.setIdDepDestino(traslado.getMovimientos().get(0).getDepDestino());
		}
		
		return objectVo;
	}


	@Override
	public boolean validaDestinoTraslado(String depDestino) {
		cajaDTO.getDeposito().getDepId().toString();
		boolean bandera = false;
		if(cajaDTO.getDeposito().getDepId().toString() == depDestino){
			bandera = true;
		}
		return bandera;
	}
	


	

	 
	 
	

}
