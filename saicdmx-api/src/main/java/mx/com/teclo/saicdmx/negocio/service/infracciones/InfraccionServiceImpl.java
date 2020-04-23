package mx.com.teclo.saicdmx.negocio.service.infracciones;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.bitacora.cambios.infracciones.BitSpInfraccionSuspension;
import mx.com.teclo.saicdmx.bitacora.cambios.infracciones.BitSpInfraccionesGeneralFMpl;
import mx.com.teclo.saicdmx.bitacora.cambios.infracciones.BitTrBitUpInfrac;
import mx.com.teclo.saicdmx.bitacora.cambios.infraccionesDigitalizacion.BitTrBitUpInfracDigImpl;
import mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar.BitTrBitUpInfracRadar;
import mx.com.teclo.saicdmx.pdf.garantia.GarantiasRecibe;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CatConDireccionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CatObserveQueDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.DelegacionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.EstadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.GarantiasCatDocumentosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.ResponsableVehiculoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.SectorDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.TipoLicenciaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.VehiculoColorDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.VehiculoMarcaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.VehiculoTipoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones.InfraccionDigitalTodoDiaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones.InfraccionesImagenesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones.InfraccionesRadarDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones.IngresosImagenesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDigitalTodoDiaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionRadarDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionesImagenesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.IngresosImagenesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatConDireccionVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.CatObserveQueVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.DelegacionDescAndIdVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.EstadoSinDelegacionesVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.EstadoVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.GarantiasCatDocumentosVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.ResponsableVehiculoDescAndIdVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.SectorSinUnidadTerritorialVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.TipoLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoColorDescAndIdVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoMarcaDescAndIdVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.VehiculoTipoDescAndIdVO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos.ParametrosBDDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones.InfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infraccionexpediente.ExpedienteMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.articulos.ArticuloVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.CargaDigitalizacionWebSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.CargaDigitalizacionWebVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.CountVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionAllDataVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionConsultaReporteRegDFVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionConsultaReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.ModificaEnDepositoSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.ModificaEnDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.SuspensionInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.SuspensionInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VInfraccionesValidaImagenVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;
import mx.com.teclo.saicdmx.util.comun.Infracciones;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

@Service
public class InfraccionServiceImpl implements InfraccionService {

	private static final Logger logger = Logger.getLogger(GarantiasRecibe.class);

	/**
	 * @inheritDoc
	 */
	@Override
	public List<FilterValuesVO> buscarTiposFitroParaBusquedaInfracciones() {
		List<FilterValuesVO> listaFiltros = new ArrayList<FilterValuesVO>();
		FilterValuesVO fi = new FilterValuesVO(0, "VEHICULO_PLACA", "Placa / Permiso");
		listaFiltros.add(fi);
		fi = new FilterValuesVO(0, "IMPRESA", "Boleta Preimpresa");
		listaFiltros.add(fi);
		fi = new FilterValuesVO(0, "INFRACCION", "No. Infracción");
		listaFiltros.add(fi);
		fi = new FilterValuesVO(0, "INFRACTOR_LICENCIA", "Licencia de Conducir");
		listaFiltros.add(fi);
		return listaFiltros;
	}

	@Autowired
	private InfraccionMyBatisDAO infraccionDAO;

	@Autowired
	private InfraccionesImagenesDAO infraccionesImagenesDAO;

	@Autowired
	private IngresosImagenesDAO ingresosImagenesDAO;

	@Autowired
	private InfraccionesRadarDAO infraccionesRadarDAO;

	@Autowired
	private InfraccionDigitalTodoDiaDAO infraccionDigitalTodoDiaDAO;

	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;

	@Autowired
	private ServletContext context;

	@Autowired
	private BitTrBitUpInfrac bitTrBitUpInfrac;

	@Autowired
	BitSpInfraccionSuspension bitSpInfraccionSuspension;

	@Autowired
	BitSpInfraccionesGeneralFMpl bitSpInfraccionesGeneralFMpl;

	@Autowired
	BitSpInfraccionSuspension BitSpInfraccionSuspension;

	@Autowired
	BitTrBitUpInfracRadar bitTrBitUpInfracRadar;

	@Autowired
	BitTrBitUpInfracDigImpl bitTrBitUpInfracDig;

	@Autowired
	private ExpedienteMyBatisDAO expedienteMyBatisDAO;
	
	@Autowired
	ParametrosBDDAO parametrosBDDAO;

	@Override
	public List<VSSPInfracConsGralFVO> buscarInfracciones(String valor, String tipoBusqueda) {
		List<VSSPInfracConsGralFVO> listaInfraccionesDTO = new ArrayList<VSSPInfracConsGralFVO>();
		switch (tipoBusqueda) {
		case "VEHICULO_PLACA":
			listaInfraccionesDTO = infraccionDAO.buscarInfraccionesVehiculoPlaca(valor);
			break;
		case "IMPRESA":
			listaInfraccionesDTO = infraccionDAO.buscarInfraccionesImpresa(valor);
			break;
		case "INFRACCION":
			listaInfraccionesDTO = infraccionDAO.buscarInfraccionesInfraccion(valor);
			break;
		case "INFRACTOR_LICENCIA":
			listaInfraccionesDTO = infraccionDAO.buscarInfraccionesInfractorLicencia(valor);
			break;
		default:
			break;
		}
		return listaInfraccionesDTO;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public VSSPInfracConsGralFVO buscarInfraccionesAllDataByInfracNum(String valor) {
		List<VSSPInfracConsGralFVO> listaInfraccionesDTO = infraccionDAO.buscarInfraccionesAllDataByInfracNum(valor);
		if (CollectionUtils.isNotEmpty(listaInfraccionesDTO))
			return listaInfraccionesDTO.get(0);
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @author dagoberto
	 * @return AltaInfraccionSPVO SP_INFRACCIONES_GENERAL_F_MPL
	 */

	@Override
	public AltaInfraccionSPVO crearInfraccion(AltaInfraccionSPVO altaInfraccionSPVO) {
		infraccionDAO.crearInfraccion(altaInfraccionSPVO);
		if (!altaInfraccionSPVO.getP_resultado().equals("-1")) {
			bitSpInfraccionesGeneralFMpl.altaBitSpInfraccionesGral(altaInfraccionSPVO);
		}
		return altaInfraccionSPVO;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 * @author dagoberto
	 * @return AltaInfraccionSPVO TR_BIT_UP_INFRAC
	 */

	// @Transactional
	@Override
	public AltaInfraccionSPVO modificaInfraccion(AltaInfraccionSPVO altaInfraccionSPVO) throws ParseException {

		VSSPInfracConsGralFVO oldListaInfraccionesVO = buscarInfraccionByTipoInfrac(
				altaInfraccionSPVO.getP_infrac_num_ctrl());
		AltaInfraccionSPVO newInfraccionVO = altaInfraccionSPVO;
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		infraccionDAO.modificaInfraccion(altaInfraccionSPVO);
		newInfraccionVO.setP_infrac_placa(buscarInfraccionBVehiculoPlaca(newInfraccionVO.getP_infrac_num_ctrl()));
		try {
			// oldListaInfraccionesVO = null;
			if (!altaInfraccionSPVO.getP_resultado().equals("-1")) {
				bitacoraCambiosVOs = bitTrBitUpInfrac.guardarCambiosBitacora(newInfraccionVO, oldListaInfraccionesVO);
				bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
			}

		} catch (Exception e) {
			System.out.print("Error al guardar en bitacoras modificaInfraccion");
		}

		return altaInfraccionSPVO;
	}

	public VSSPInfracConsGralFVO buscarInfraccionByTipoInfrac(String ctrl) throws ParseException {
		String infraccion = ctrl.substring(0, 11);
		String tipoFolio = ctrl.substring(0, 2);

		VSSPInfracConsGralFVO oldListaInfraccionesVO = new VSSPInfracConsGralFVO();

		if (tipoFolio.equals("01") || tipoFolio.equals("02") || tipoFolio.equals("04") || tipoFolio.equals("06")) {
			oldListaInfraccionesVO = infraccionDAO.buscarInfraccionesAllDataInfrac(infraccion);

		} else if (tipoFolio.equals("03") || tipoFolio.equals("05") || tipoFolio.equals("07") || tipoFolio.equals("08")
				|| tipoFolio.equals("09")) {

			oldListaInfraccionesVO = infraccionDAO.buscarInfraccionesAllDataInfraR(infraccion);
		} else {

			oldListaInfraccionesVO = infraccionDAO.buscarInfraccionesAllDataInfracD(infraccion);
		}

		return oldListaInfraccionesVO;
	}

	private String buscarInfraccionBVehiculoPlaca(String ctrl) throws ParseException {
		String infraccion = ctrl.substring(0, 11);
		String tipoFolio = ctrl.substring(0, 2);

		String placa = "";

		if (tipoFolio.equals("01") || tipoFolio.equals("02") || tipoFolio.equals("04") || tipoFolio.equals("06")) {
			placa = infraccionDAO.getPlacaVehiculo(infraccion);

		} else if (tipoFolio.equals("03") || tipoFolio.equals("05") || tipoFolio.equals("07") || tipoFolio.equals("08")
				|| tipoFolio.equals("09")) {

			placa = infraccionDAO.getPlacaVehiculoR(infraccion);
		} else {

			placa = infraccionDAO.getPlacaVehiculoD(infraccion);
		}

		return placa;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 * @author dagoberto
	 * @return ModificaEnDepositoSPVO
	 */
	@Override
	public ModificaEnDepositoSPVO modificaInfraccionEnDeposito(ModificaEnDepositoVO modDepVO, Long modificadoPor)
			throws ParseException {
		modDepVO.generaParametrosParaSP();
		modDepVO.getModificaEnDepositoSPVO().setP_modificado_por(modificadoPor);
		ModificaEnDepositoSPVO modificaEnDepositoSPVO = modDepVO.getModificaEnDepositoSPVO();

		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		AltaInfraccionSPVO newInfraccionVO = new AltaInfraccionSPVO(); // nuevo
		ModificaEnDepositoVO modDepVo = modDepVO;
		VSSPInfracConsGralFVO oldListaInfraccionesVO = infraccionDAO
				.getOldVoInfracMod(modDepVO.getpInfracNumCtrl().substring(0, 11));
		infraccionDAO.modificaInfraccionEnDeposito(modificaEnDepositoSPVO);
		newInfraccionVO
				.setP_infrac_placa(infraccionDAO.getPlacaVehiculo(modDepVO.getpInfracNumCtrl().substring(0, 11)));

		if (!modificaEnDepositoSPVO.getP_resultado().equals("-1")) {
			try {
				newInfraccionVO.setP_infrac_tipo_arrastre(modDepVo.getpInfracTipoArrastre());
				newInfraccionVO.setP_infrac_num_ctrl(modDepVo.getpInfracNumCtrl());
				newInfraccionVO.setP_vtipo_id(modDepVo.getpVehiculoTipo() != null
						? Integer.valueOf(modDepVo.getpVehiculoTipo().toString()) : 0);
				newInfraccionVO.setP_motivo_cambio(modDepVo.getpMotivoCambio());
				newInfraccionVO
						.setP_modificado_por(modificadoPor != null ? Integer.valueOf(modificadoPor.toString()) : 0);
				newInfraccionVO.setP_emp_id(modDepVo.getpAutorizadoPor() != null
						? Integer.valueOf(modDepVo.getpAutorizadoPor().toString()) : 0);
				newInfraccionVO.setP_art_id(modDepVo.getpArticuloId().intValue());

				bitacoraCambiosVOs = bitTrBitUpInfrac.guardarCambiosBitacora(newInfraccionVO, oldListaInfraccionesVO);
				bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
			} catch (Exception e) {
				return modificaEnDepositoSPVO;
			}

		}
		return modificaEnDepositoSPVO;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 *             SP_INFRACCION_SUSPENSION
	 */
	@Override
	public SuspensionInfraccionVO suspensionInfraccion(SuspensionInfraccionVO suspensionInfraccionVO, Long emp_id)
			throws ParseException {
		suspensionInfraccionVO.generaParametrosParaSP();
		suspensionInfraccionVO.getSuspensionInfraccionSPVO().setP_emp_id(emp_id);
		SuspensionInfraccionSPVO suspensionInfraccionSPVO = suspensionInfraccionVO.getSuspensionInfraccionSPVO();
		VSSPInfracConsGralFVO oldListaInfraccionesVO = new VSSPInfracConsGralFVO();

		VSSPInfracConsGralFVO newInfraccionVO = new VSSPInfracConsGralFVO();

		// String modificadorPorOld =
		// infraccionDAO.getAutorizaId(suspensionInfraccionVO.getpInfracNum());
		Long modificadorPorOld = infraccionDAO.buscarInfraccionesInfraccion2(suspensionInfraccionVO.getpInfracNum());
		String fechaAutorizaOld = infraccionDAO.getFechaAutoriza(suspensionInfraccionVO.getpInfracNum());

		infraccionDAO.suspensionInfraccion(suspensionInfraccionSPVO);

		if (!suspensionInfraccionSPVO.generaRespuestaVO().getpResultado().equals("-1")
				|| !suspensionInfraccionSPVO.generaRespuestaVO().getpResultado().equals("-2")) {

			try {

				BitSpInfraccionSuspension.bitSuspensionInfraccionsp(emp_id, suspensionInfraccionSPVO);

				String tipoFolio = suspensionInfraccionVO.getpInfracNum().substring(0, 2);
				if (tipoFolio.equals("01") || tipoFolio.equals("02") || tipoFolio.equals("04")
						|| tipoFolio.equals("06")) {
					ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
					oldListaInfraccionesVO.setAutorizaId(new Long(modificadorPorOld).toString());

					newInfraccionVO.setAutorizaId(emp_id.toString());
					newInfraccionVO.setInfraccionNumero(suspensionInfraccionVO.getpInfracNum());
					newInfraccionVO.setModificadoPor(emp_id.intValue());

					bitacoraCambiosVOs = bitTrBitUpInfrac.guardarCambiosBitacora(newInfraccionVO,
							oldListaInfraccionesVO);
					bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);

				} else if (tipoFolio.equals("03") || tipoFolio.equals("05") || tipoFolio.equals("07")
						|| tipoFolio.equals("08") || tipoFolio.equals("09")) {
					RespuestaWSReasignaLineaCapturaVO newInfraccionRadarVO = new RespuestaWSReasignaLineaCapturaVO();
					RespuestaWSReasignaLineaCapturaVO oldInfraccionRadarVO = new RespuestaWSReasignaLineaCapturaVO();

					newInfraccionRadarVO.setUsuario(emp_id.toString());
					oldInfraccionRadarVO.setUsuario(new Long(modificadorPorOld).toString());
					newInfraccionRadarVO.setAutorizaId(emp_id.toString());
					oldInfraccionRadarVO.setAutorizaId(new Long(modificadorPorOld).toString());

					VSSPInfracConsGralFVO infracRadarVO = new VSSPInfracConsGralFVO();
					infracRadarVO.setNumeroControlInterno(
							suspensionInfraccionVO.getSuspensionInfraccionSPVO().getP_infrac_num());
					infracRadarVO.setFechaAutoriza(new SimpleDateFormat("dd/MM/yyyy").parse(fechaAutorizaOld));
					newInfraccionRadarVO.setInfracRadarVO(infracRadarVO);
					oldInfraccionRadarVO.setInfracRadarVO(infracRadarVO);

					bitacoraCambiosService.guardarListaBitacoraCambios(
							bitTrBitUpInfracRadar.guardarCambiosBitacora(newInfraccionRadarVO, oldInfraccionRadarVO));

				} else {
					RespuestaWSReasignaLineaCapturaVO newInfraccionRadarVO = new RespuestaWSReasignaLineaCapturaVO();
					RespuestaWSReasignaLineaCapturaVO oldInfraccionRadarVO = new RespuestaWSReasignaLineaCapturaVO();

					newInfraccionRadarVO.setUsuario(emp_id.toString());
					oldInfraccionRadarVO.setUsuario(new Long(modificadorPorOld).toString());
					newInfraccionRadarVO.setAutorizaId(emp_id.toString());
					oldInfraccionRadarVO.setAutorizaId(new Long(modificadorPorOld).toString());

					VSSPInfracConsGralFVO infracRadarVO = new VSSPInfracConsGralFVO();
					infracRadarVO.setNumeroControlInterno(
							suspensionInfraccionVO.getSuspensionInfraccionSPVO().getP_infrac_num());
					infracRadarVO.setFechaAutoriza(new SimpleDateFormat("dd/MM/yyyy").parse(fechaAutorizaOld));

					newInfraccionRadarVO.setInfracRadarVO(infracRadarVO);
					oldInfraccionRadarVO.setInfracRadarVO(infracRadarVO);

					bitacoraCambiosService.guardarListaBitacoraCambios(
							bitTrBitUpInfracRadar.guardarCambiosBitacora(newInfraccionRadarVO, oldInfraccionRadarVO));
				}
			} catch (Exception e) {
				return suspensionInfraccionSPVO.generaRespuestaVO();
			}

		}

		return suspensionInfraccionSPVO.generaRespuestaVO();
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public InfraccionesImagenesDTO buscaFotoPorNombreArchivo(String nombre) {
		InfraccionesImagenesDTO imgInfraccion = infraccionesImagenesDAO.buscaFotoPorNombreArchivo(nombre);
		return imgInfraccion;
	}

	@Transactional
	@Override
	public IngresosImagenesDTO buscaFotoPorNombreArchivoIngreso(String nombre) {
		IngresosImagenesDTO imgIngreso = ingresosImagenesDAO.buscaFotoPorNombreArchivo(nombre);
		return imgIngreso;
	}

	@Transactional(readOnly = true)
	@Override
	public InfraccionRadarDTO buscarInfraccionPorFolio(String folio) {
		return infraccionesRadarDAO.buscarInfraccionPorFolio(folio);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public List<InfraccionDigitalTodoDiaDTO> buscarInfraccionDigitalTodoDiaDTOPorEstatus(String status) {
		status = status.equals("sinLiberar") ? "0" : status.equals("duplicados") ? "2" : "";
		return infraccionDigitalTodoDiaDAO.buscarPorEstatus(status);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public CountVO countByStatus(String status) {
		status = status.equals("sinLiberar") ? "0" : status.equals("duplicados") ? "2" : "";
		return new CountVO(infraccionDigitalTodoDiaDAO.countByStatus(status));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CargaDigitalizacionWebVO CargaDigitalizacionWeb(CargaDigitalizacionWebVO cargaDigitalizacionWebVO,
			Long emp_id) {
		cargaDigitalizacionWebVO.generaParametrosParaSP();
		cargaDigitalizacionWebVO.getCargaDigitalizacionWebSPVO().setEmpleado_id(emp_id);
		CargaDigitalizacionWebSPVO cargaDigitalizacionWebSPVO = cargaDigitalizacionWebVO
				.getCargaDigitalizacionWebSPVO();
		infraccionDAO.CargaDigitalizacionWeb(cargaDigitalizacionWebSPVO);

		if (cargaDigitalizacionWebSPVO.generaRespuestaVO().getResultOut() == 0) {

			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), // Nombre
																				// de
																				// la
																				// tabla
					5L, // Componente
					48L, // Concepto
					cargaDigitalizacionWebSPVO.generaRespuestaVO().getFoliosRepetidos().toString(), // Valor
																									// Original
																									// del
																									// registro.
					cargaDigitalizacionWebSPVO.generaRespuestaVO().getFoliosLiberados().toString(), // Valor
																									// final
																									// del
																									// registro.
					emp_id, // Id del usuario que realiza el cambio
					infraccionDAO.getToIdRegistro(), // IdRegistro
					"", // Descripcion del registro modificado.
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()); // Origen W
																		// / H
		}

		return cargaDigitalizacionWebSPVO.generaRespuestaVO();
	}

	@Autowired
	private EstadoDAO estadoDAO;

	@Autowired
	private ResponsableVehiculoDAO responsableVehiculoDao;

	@Autowired
	private CatObserveQueDAO catObserveQueDAO;

	@Autowired
	private DelegacionDAO delegacionDAO;

	@Autowired
	private CatConDireccionDAO catConDireccionDAO;

	@Autowired
	private VehiculoTipoDAO vehiculoTipoDAO;

	@Autowired
	private VehiculoMarcaDAO vehiculoMarcaDAO;

	@Autowired
	private VehiculoColorDAO vehiculoColorDAO;

	@Autowired
	private GarantiasCatDocumentosDAO garantiasCatDocumentosDAO;

	@Autowired
	private TipoLicenciaDAO tipoLicenciaDAO;

	@Autowired
	private SectorDAO sectorDAO;

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public InfraccionAllDataVO gatherAllData() {
		InfraccionAllDataVO infraccionAllDataVO = new InfraccionAllDataVO();

		infraccionAllDataVO.setEdoSinDelegacionCatalgo(ResponseConverter.converterLista(new ArrayList<>(),
				estadoDAO.buscarEstadoPorCodigo("DF"), EstadoSinDelegacionesVO.class));

		infraccionAllDataVO.setResponsableVehiculoCatalogo(ResponseConverter.converterLista(new ArrayList<>(),
				responsableVehiculoDao.buscarResponsableVehiculoConEstatusActivo(),
				ResponsableVehiculoDescAndIdVO.class));

		infraccionAllDataVO.setConDireccionCatalogo(ResponseConverter.converterLista(new ArrayList<>(),
				catConDireccionDAO.findAll(), CatConDireccionVO.class));

		infraccionAllDataVO.setDelegacionPorEstadoCatalogo(ResponseConverter.converterLista(new ArrayList<>(),
				delegacionDAO.buscarDelegacionPorEstado(9L), DelegacionDescAndIdVO.class));

		infraccionAllDataVO.setVehiculoTipoCatalogo(ResponseConverter.converterLista(new ArrayList<>(),
				vehiculoTipoDAO.buscarTodosVehiculoTipoActivo(), VehiculoTipoDescAndIdVO.class));

		infraccionAllDataVO.setVehiculoMarcaCatalogo(ResponseConverter.converterLista(new ArrayList<>(),
				vehiculoMarcaDAO.buscarVehiculoMarca("A"), VehiculoMarcaDescAndIdVO.class));

		infraccionAllDataVO.setVehiculoColorCatalogo(ResponseConverter.converterLista(new ArrayList<>(),
				vehiculoColorDAO.buscarColor("A"), VehiculoColorDescAndIdVO.class));

		infraccionAllDataVO.setEstadoCatalogo(
				ResponseConverter.converterLista(new ArrayList<>(), estadoDAO.findAll(), EstadoVO.class));

		infraccionAllDataVO.setGarantiasCatDocumentosCatalogo(ResponseConverter.converterLista(new ArrayList<>(),
				garantiasCatDocumentosDAO.findAll(), GarantiasCatDocumentosVO.class));

		infraccionAllDataVO.setTipoLicenciaCatalogo(ResponseConverter.converterLista(new ArrayList<>(),
				tipoLicenciaDAO.buscarListaTipoLicencia(Infracciones.ESTATUS_ACTIVO), TipoLicenciaVO.class));

		infraccionAllDataVO.setSectorSinUTCatalogo(ResponseConverter.converterLista(new ArrayList<>(),
				sectorDAO.buscarSectores(9L, "A"), SectorSinUnidadTerritorialVO.class));

		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		FilterValuesVO filter = new FilterValuesVO(0, "N", "Sin Arrastre");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "I", "Arrastre por Infracción");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "A", "Arrastre por Abandono");
		filterValues.add(filter);
		infraccionAllDataVO.setTipoArrastreCatalogo(filterValues);

		filterValues = new ArrayList<FilterValuesVO>();
		filter = new FilterValuesVO(0, "G", "Grua|Patrulla|Moto");
		filterValues.add(filter);
		filter = new FilterValuesVO(0, "P", "PIE");
		filterValues.add(filter);
		infraccionAllDataVO.setTipoUnidadCatalogo(filterValues);

		infraccionAllDataVO.setObserveQueCatalogo(
				ResponseConverter.converterLista(new ArrayList<>(), catObserveQueDAO.findAll(), CatObserveQueVO.class));

		return infraccionAllDataVO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VInfraccionesValidaImagenVO vInfraccionesValidaImagen(String infracNum) {
		VInfraccionesValidaImagenVO vInfraccionesValidaImagenVO = new VInfraccionesValidaImagenVO();
		vInfraccionesValidaImagenVO.setNumeroInfraccion(infracNum);

		List<VInfraccionesValidaImagenVO> LvInfraccionesValidaImagenVO = infraccionDAO
				.vInfraccionesValidaImagen(vInfraccionesValidaImagenVO);
		if (CollectionUtils.isEmpty(LvInfraccionesValidaImagenVO))
			return null;
		return LvInfraccionesValidaImagenVO.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ByteArrayOutputStream generaReporteConsulta(String nci) {
		List<InfraccionConsultaReporteVO> infraccionConsultaReporteVO = infraccionDAO.consultaInfraccionReporte(nci);
		InfraccionConsultaReporteVO reportVO;
		if (CollectionUtils.isEmpty(infraccionConsultaReporteVO)) {

		} else {
			reportVO = infraccionConsultaReporteVO.get(0);
			ByteArrayOutputStream reporte = new ByteArrayOutputStream();
			String rutaArchivo;
			Map parametros = new HashMap();
			String infracDia = reportVO.getFecha_infraccion().substring(0, 2);
			String infracMes = reportVO.getFecha_infraccion().substring(3, 5);
			String infracAao = reportVO.getFecha_infraccion().substring(6, 10);
			String infracHora = reportVO.getFecha_infraccion().substring(11, 16);

			//Se obtiene fecha de bd para poder mostrar nuevo formato 2020
			//David Guerra
			List<Map<String, String>> fechaLiberacionNuevoFormato=parametrosBDDAO.getParametrosPorCdConfig("FECHA_LIBERACION_BOLETA_2020");
			String fecha=fechaLiberacionNuevoFormato.get(0).get("CD_VALOR_P_CONFIG").trim();
            String[] fechaSeparada=fecha.split("/");
            Integer NuevoFormDia=Integer.parseInt(fechaSeparada[0]);
            Integer NuevoFormMes=Integer.parseInt(fechaSeparada[1]);
            Integer NuevoFormAnio=Integer.parseInt(fechaSeparada[2]);
            Integer proximoAño=NuevoFormAnio+1;
			
			if ((Integer.parseInt(infracAao) >= 2016) || ((Integer.parseInt(infracDia) >= 15)
					&& (Integer.parseInt(infracMes) >= 12) && (Integer.parseInt(infracAao) >= 2015))) {
				rutaArchivo = context.getRealPath("/WEB-INF/jasper/infraccion/impresionInfraccionRegDF.jasper");
				if ((Integer.parseInt(infracAao) >= 2018) || ((Integer.parseInt(infracDia) >= 1)
						&& (Integer.parseInt(infracMes) >= 2) && (Integer.parseInt(infracAao) >= 2017))) {
					rutaArchivo = context.getRealPath("/WEB-INF/jasper/infraccion/impresionInfraccionRegCDMX.jasper");
				}
				if ((Integer.parseInt(infracAao) >= proximoAño) || ((Integer.parseInt(infracDia) >= NuevoFormDia)
						&& (Integer.parseInt(infracMes) >= NuevoFormMes) && (Integer.parseInt(infracAao) >= NuevoFormAnio))) {
					rutaArchivo = context.getRealPath("/WEB-INF/jasper/infraccion/impresionInfraccionNuevoCDMX2020.jasper");
					
				}
				List<InfraccionConsultaReporteRegDFVO> infraccionConsultaReporteRegDFVO = infraccionDAO
						.consultaInfraccionReporteRegDF(nci);
				if (CollectionUtils.isNotEmpty(infraccionConsultaReporteRegDFVO)) {
					InfraccionConsultaReporteRegDFVO reportRegDFVO = infraccionConsultaReporteRegDFVO.get(0);
					//parrafo 1
					List<Map<String, String>> parrafo1=parametrosBDDAO.getParametrosPorCdConfig("PARRAFO_1_BOLETA_2020");
					String parrafo_1=parrafo1.get(0).get("CD_VALOR_P_CONFIG");
					parametros.put("PARRAFO1",parrafo_1 == null ? "" : parrafo_1);
					//parrafo 2
					List<Map<String, String>> parrafo2=parametrosBDDAO.getParametrosPorCdConfig("PARRAFO_2_BOLETA_2020");
					String parrafo_2=parrafo2.get(0).get("CD_VALOR_P_CONFIG");
					String auxHandHledSerie=reportRegDFVO.getHandheld_serie() == null ? "" : reportRegDFVO.getHandheld_serie();
				    String auxInfracDia=reportRegDFVO.getInfrac_dia() == null ? "" : reportRegDFVO.getInfrac_dia();
					String auxInfracMes=reportRegDFVO.getInfrac_mes() == null ? "" : reportRegDFVO.getInfrac_mes();
					String auxInfracAnio=reportRegDFVO.getInfrac_anio() == null ? "" : reportRegDFVO.getInfrac_anio();
					String[][] reemplazos = {{"$P{EMP_NOMBRE_COMPLETO}",reportRegDFVO.getEmp_nombre_completo() == null ? ""
							: reportRegDFVO.getEmp_nombre_completo()},
							{"$P{EMP_PLACA}",reportRegDFVO.getEmp_placa() == null ? "" : reportRegDFVO.getEmp_placa()}, {"$P{EMP_SECTOR}", reportRegDFVO.getEmp_sector() == null ? "" : reportRegDFVO.getEmp_sector()},
	                        {"$P{EMP_AGRUPAMIENTO}",reportRegDFVO.getEmp_agrupamiento() == null ? "" : reportRegDFVO.getEmp_agrupamiento()}
							,{"$P{INFRAC_HORA}",reportRegDFVO.getInfrac_hora() == null ? "" : reportRegDFVO.getInfrac_hora()},
							{"$P{INFRAC_DIA}",reportRegDFVO.getInfrac_dia() == null ? "" : reportRegDFVO.getInfrac_dia()},
							{"$P{INFRAC_MES}",reportRegDFVO.getInfrac_mes() == null ? "" : reportRegDFVO.getInfrac_mes().trim()},
							{"$P{INFRAC_ANIO}",reportRegDFVO.getInfrac_anio() == null ? "" : reportRegDFVO.getInfrac_anio()},
							{"$P{INFRAC_EN_LA_CALLE}",reportRegDFVO.getInfrac_en_la_calle() == null ? "" : reportRegDFVO.getInfrac_en_la_calle()},
							{"$P{INFRAC_ENTRE_LA_CALLE}",reportRegDFVO.getInfrac_entre_la_calle() == null ? ""
									: reportRegDFVO.getInfrac_entre_la_calle()},
							{"$P{INFRAC_Y_LA_CALLE}",reportRegDFVO.getInfrac_y_la_calle() == null ? "" : reportRegDFVO.getInfrac_y_la_calle()},
							{"$P{INFRAC_FRENTE_AL_NUM}",reportRegDFVO.getInfrac_frente_al_num() == null ? ""
									: reportRegDFVO.getInfrac_frente_al_num()},
							{"$P{INFRAC_CON_DIRECCION}",reportRegDFVO.getInfrac_con_direccion() == null ? ""
									: reportRegDFVO.getInfrac_con_direccion()},
							{"$P{INFRAC_COLONIA}",reportRegDFVO.getInfrac_colonia() == null ? "" : reportRegDFVO.getInfrac_colonia()},
							{"$P{INFRAC_DELEGACION}",reportRegDFVO.getInfrac_delegacion() == null ? "" : reportRegDFVO.getInfrac_delegacion()},
							{"$V{variable1}",auxHandHledSerie.length() == 0 ?
									Integer.parseInt(auxInfracAnio)<=2016 ? 
											auxInfracMes.trim().toString().equals("ENERO") ||
											auxInfracMes.trim().toString().equals("FEBRERO") ||
											auxInfracMes.trim().toString().equals("MARZO") ||
											auxInfracMes.trim().toString().equals("ABRIL") ||
											auxInfracMes.trim().toString().equals("MAYO") ||
											auxInfracMes.trim().toString().equals("JUNIO") ||
											auxInfracMes.trim().toString().equals("JULIO") ||
											auxInfracMes.trim().toString().equals("AGOSTO") ||
											auxInfracMes.trim().toString().equals("SEPTIEMBRE") ||
											auxInfracMes.trim().toString().equals("OCTUBRE") &&
											Integer.parseInt(auxInfracAnio)==2016 ?
													auxInfracMes.trim().toString().equals("OCTUBRE") ?
													Integer.parseInt(auxInfracDia) < 24 ?
														"INTERMEC, modelo CN3":"ZEBRA, modelo MC67"
												:"INTERMEC, modelo CN3"
											:auxInfracMes.trim().toString().equals("NOVIEMBRE")||auxInfracMes.trim().toString().equals("DICIEMBRE") && Integer.parseInt(auxInfracAnio)==2016 ?
												"ZEBRA, modelo MC67":"INTERMEC, modelo CN3"
										:"ZEBRA, modelo MC67"
									:auxHandHledSerie.length() > 11 ?
										"ZEBRA, modelo MC67" :"INTERMEC, modelo CN3"},
							{"$P{HANDHELD_SERIE}",reportRegDFVO.getHandheld_serie() == null ? "" : reportRegDFVO.getHandheld_serie()},
							{"$P{OBSERVE_QUE}",reportRegDFVO.getObserve_que() == null ? "" : reportRegDFVO.getObserve_que()},
							{"$P{VEH_MARCA}",reportRegDFVO.getVeh_marca() == null ? "" : reportRegDFVO.getVeh_marca()},
							{"$P{VEH_MODELO}",reportRegDFVO.getVeh_modelo() == null ? "" : reportRegDFVO.getVeh_modelo()},
							{"$P{VEH_COLOR}",reportRegDFVO.getVeh_color() == null ? "" : reportRegDFVO.getVeh_color()},
							{"$P{VEH_PLACA}",reportRegDFVO.getVeh_placa() == null ? "" : reportRegDFVO.getVeh_placa()},
							{"$P{VEH_PLACA_EXPEDIDA}",reportRegDFVO.getVeh_placa_expedida() == null ? "" : reportRegDFVO.getVeh_placa_expedida()},
							{"$P{ARTICULO}",reportRegDFVO.getArticulo() == null ? "" : reportRegDFVO.getArticulo()},
							{"$P{FRACCION}",reportRegDFVO.getFraccion() == null ? "" : reportRegDFVO.getFraccion()},
							{"$P{INCISO}",reportRegDFVO.getInciso() == null ? "" : reportRegDFVO.getInciso()},
							{"$P{PARRAFO}",reportRegDFVO.getParrafo() == null ? "" : reportRegDFVO.getParrafo()},
							{"$P{INFRAC_OBSERVACIONES}",reportRegDFVO.getInfrac_observaciones() == null ? ""
									: reportRegDFVO.getInfrac_observaciones()}}; 
						for(String[] reemplazar: reemplazos ) {
							parrafo_2 = parrafo_2.replace(reemplazar[0], reemplazar[1]);
							}
							parametros.put("PARRAFO2",
							parrafo_2 == null ? "" : parrafo_2);	
							//parrafo3
					List<Map<String, String>> parrafo3=parametrosBDDAO.getParametrosPorCdConfig("PARRAFO_3_BOLETA_2020");
					String parrafo_3=parrafo3.get(0).get("CD_VALOR_P_CONFIG");
					String[][] reemplazosParrafo3 = {{"$P{INFRAC_DIAS_SANCION}",reportRegDFVO.getInfrac_dias_sancion() == null ? ""
							: reportRegDFVO.getInfrac_dias_sancion()},
							{"$P{INFRACTOR_NOMBRE}",reportRegDFVO.getInfractor_nombre() == null ? "" : reportRegDFVO.getInfractor_nombre()},
							{"$P{INFRACTOR_LIC}",reportRegDFVO.getInfractor_lic() == null ? "" : reportRegDFVO.getInfractor_lic()},
	                        {"$P{INFRACTOR_LIC_TIPO}",reportRegDFVO.getInfractor_lic_tipo() == null ? "" : reportRegDFVO.getInfractor_lic_tipo()},
	                        {"$P{INFRACTOR_LIC_EXPEDIDA}",reportRegDFVO.getInfractor_lic_expedida() == null ? ""
	    							: reportRegDFVO.getInfractor_lic_expedida()},
							{"$P{INFRACTOR_CALLE}",reportRegDFVO.getInfractor_calle() == null ? "" : reportRegDFVO.getInfractor_calle()},
							{"$P{INFRACTOR_NUM_INT}",reportRegDFVO.getInfractor_num_int() == null ? "" : reportRegDFVO.getInfractor_num_int()},
							{"$P{INFRACTOR_NUM_EXT}",reportRegDFVO.getInfractor_num_ext() == null ? "" : reportRegDFVO.getInfractor_num_ext()},
							{"$P{INFRACTOR_COLONIA}",reportRegDFVO.getInfractor_colonia() == null ? "" : reportRegDFVO.getInfractor_colonia()},
							{"$P{INFRACTOR_ESTADO}",reportRegDFVO.getInfractor_estado() == null ? "" : reportRegDFVO.getInfractor_estado()},
							{"$P{INFRACTOR_DELEGACION}",reportRegDFVO.getInfractor_delegacion() == null ? ""
									: reportRegDFVO.getInfractor_delegacion()}};
					for(String[] reemplazar: reemplazosParrafo3 ) {
						parrafo_3 = parrafo_3.replace(reemplazar[0], reemplazar[1]);
						}
						parametros.put("PARRAFO3",
						parrafo_3 == null ? "" : parrafo_3);
						//parrafo4
					    List<Map<String, String>> parrafo4=parametrosBDDAO.getParametrosPorCdConfig("PARRAFO_4_BOLETA_2020");
						String parrafo_4=parrafo4.get(0).get("CD_VALOR_P_CONFIG");
						parametros.put("PARRAFO4",parrafo_4 == null ? "" : parrafo_4);
						//parrafo5 y parrafo6
						String aplicaGarnatia=reportRegDFVO.getGarantia_retenida() == null ? "" : reportRegDFVO.getGarantia_retenida();
						String deposito=reportRegDFVO.getInfrac_deposito() == null ? "" : reportRegDFVO.getInfrac_deposito();
						if(!aplicaGarnatia.equals("NO"))
						{
							 List<Map<String, String>> parrafo5=parametrosBDDAO.getParametrosPorCdConfig("PARRAFO_5_BOLETA_2020");
							 String parrafo_5=parrafo5.get(0).get("CD_VALOR_P_CONFIG");
							 String[][] reemplazosParrafo5 = {{"$P{VEH_PLACA}",reportRegDFVO.getVeh_placa() == null ? "" : reportRegDFVO.getVeh_placa()},
										{"$P{VEH_PLACA_EXPEDIDA}",reportRegDFVO.getVeh_placa_expedida() == null ? "" : reportRegDFVO.getVeh_placa_expedida()},
										{"$P{VEH_ORIGEN}",reportRegDFVO.getVeh_origen() == null ? "" : reportRegDFVO.getVeh_origen()},
				                        {"$P{GARANTIA_RETENIDA}",reportRegDFVO.getGarantia_retenida() == null ? "" : reportRegDFVO.getGarantia_retenida()}};
							 for(String[] reemplazar: reemplazosParrafo5 ) {
									parrafo_5 = parrafo_5.replace(reemplazar[0], reemplazar[1]);
									}
									parametros.put("PARRAFO5",
									parrafo_5 == null ? "" : parrafo_5);
							
//							List<Map<String, String>> parrafo6=parametrosBDDAO.getParametrosPorCdConfig("PARRAFO_6_BOLETA_2020");
//							String parrafo_6=parrafo6.get(0).get("CD_VALOR_P_CONFIG");
//							String[][] reemplazosParrafo6 = {{"$P{INFRAC_DEPOSITO}",deposito.equals("SIN INGRESO")?"NO":"SI"},
//			                        {"$P{INFRAC_DEPOSITO2}",deposito.equals("SIN INGRESO")? "":": "+deposito}};
//								for(String[] reemplazar: reemplazosParrafo6 ) {
//											parrafo_6 = parrafo_6.replace(reemplazar[0], reemplazar[1]);
//											}
//											parametros.put("PARRAFO6",
//											parrafo_6 == null ? "" : parrafo_6);
 
						}
						//parrafo6
						List<Map<String, String>> parrafo6=parametrosBDDAO.getParametrosPorCdConfig("PARRAFO_6_BOLETA_2020");
						String parrafo_6=parrafo6.get(0).get("CD_VALOR_P_CONFIG");
						String[][] reemplazosParrafo6 = {{"$P{INFRAC_DEPOSITO}",deposito.equals("SIN INGRESO")?"NO":"SI"},
		                        {"$P{INFRAC_DEPOSITO2}",deposito.equals("SIN INGRESO")? "":": "+deposito}};
							for(String[] reemplazar: reemplazosParrafo6 ) {
										parrafo_6 = parrafo_6.replace(reemplazar[0], reemplazar[1]);
										}
										parametros.put("PARRAFO6",
										parrafo_6 == null ? "" : parrafo_6);
						
						//parrafo7
						 List<Map<String, String>> parrafo7=parametrosBDDAO.getParametrosPorCdConfig("PARRAFO_7_BOLETA_2020");
						 String parrafo_7=parrafo7.get(0).get("CD_VALOR_P_CONFIG");
						 parametros.put("PARRAFO7",
									parrafo_7 == null ? "" : parrafo_7);
						
						
					parametros.put("GARANTIA_RETENIDA",
							reportRegDFVO.getGarantia_retenida() == null ? "" : reportRegDFVO.getGarantia_retenida());
					parametros.put("SELLO_DIGITAL",
							reportRegDFVO.getSello_digital() == null ? "" : reportRegDFVO.getSello_digital());
					parametros.put("CADENA_ORIGINAL",
							reportRegDFVO.getCadena_original() == null ? "" : reportRegDFVO.getCadena_original());
					parametros.put("INFRAC_NUM",
							reportRegDFVO.getInfrac_num() == null ? "" : reportRegDFVO.getInfrac_num());
					parametros.put("INFRAC_NUM_CTRL",
							reportRegDFVO.getInfrac_num_ctrl() == null ? "" : reportRegDFVO.getInfrac_num_ctrl());
					parametros.put("EMP_ID", reportRegDFVO.getEmp_id() == null ? "" : reportRegDFVO.getEmp_id());
					parametros.put("EMP_NOMBRE_COMPLETO", reportRegDFVO.getEmp_nombre_completo() == null ? ""
							: reportRegDFVO.getEmp_nombre_completo());
					parametros.put("EMP_PLACA",
							reportRegDFVO.getEmp_placa() == null ? "" : reportRegDFVO.getEmp_placa());
					parametros.put("EMP_SECTOR",
							reportRegDFVO.getEmp_sector() == null ? "" : reportRegDFVO.getEmp_sector());
					parametros.put("EMP_AGRUPAMIENTO",
							reportRegDFVO.getEmp_agrupamiento() == null ? "" : reportRegDFVO.getEmp_agrupamiento());
					parametros.put("INFRAC_FECHA",
							reportRegDFVO.getInfrac_fecha() == null ? "" : reportRegDFVO.getInfrac_fecha());
					parametros.put("INFRAC_HORA",
							reportRegDFVO.getInfrac_hora() == null ? "" : reportRegDFVO.getInfrac_hora());
					parametros.put("INFRAC_DIA",
							reportRegDFVO.getInfrac_dia() == null ? "" : reportRegDFVO.getInfrac_dia());
					parametros.put("INFRAC_MES",
							reportRegDFVO.getInfrac_mes() == null ? "" : reportRegDFVO.getInfrac_mes());
					parametros.put("INFRAC_ANIO",
							reportRegDFVO.getInfrac_anio() == null ? "" : reportRegDFVO.getInfrac_anio());
					parametros.put("INFRAC_EN_LA_CALLE",
							reportRegDFVO.getInfrac_en_la_calle() == null ? "" : reportRegDFVO.getInfrac_en_la_calle());
					parametros.put("INFRAC_ENTRE_LA_CALLE", reportRegDFVO.getInfrac_entre_la_calle() == null ? ""
							: reportRegDFVO.getInfrac_entre_la_calle());
					parametros.put("INFRAC_Y_LA_CALLE",
							reportRegDFVO.getInfrac_y_la_calle() == null ? "" : reportRegDFVO.getInfrac_y_la_calle());
					parametros.put("INFRAC_COLONIA",
							reportRegDFVO.getInfrac_colonia() == null ? "" : reportRegDFVO.getInfrac_colonia());
					parametros.put("INFRAC_DELEGACION",
							reportRegDFVO.getInfrac_delegacion() == null ? "" : reportRegDFVO.getInfrac_delegacion());
					parametros.put("INFRAC_CON_DIRECCION", reportRegDFVO.getInfrac_con_direccion() == null ? ""
							: reportRegDFVO.getInfrac_con_direccion());
					parametros.put("INFRAC_FRENTE_AL_NUM", reportRegDFVO.getInfrac_frente_al_num() == null ? ""
							: reportRegDFVO.getInfrac_frente_al_num());
					parametros.put("OBSERVE_QUE",
							reportRegDFVO.getObserve_que() == null ? "" : reportRegDFVO.getObserve_que());
					parametros.put("VEH_MARCA",
							reportRegDFVO.getVeh_marca() == null ? "" : reportRegDFVO.getVeh_marca());
					parametros.put("VEH_MODELO",
							reportRegDFVO.getVeh_modelo() == null ? "" : reportRegDFVO.getVeh_modelo());
					parametros.put("VEH_COLOR",
							reportRegDFVO.getVeh_color() == null ? "" : reportRegDFVO.getVeh_color());
					parametros.put("VEH_PLACA",
							reportRegDFVO.getVeh_placa() == null ? "" : reportRegDFVO.getVeh_placa());
					parametros.put("VEH_PLACA_EXPEDIDA",
							reportRegDFVO.getVeh_placa_expedida() == null ? "" : reportRegDFVO.getVeh_placa_expedida());
					parametros.put("ARTICULO", reportRegDFVO.getArticulo() == null ? "" : reportRegDFVO.getArticulo());
					parametros.put("FRACCION", reportRegDFVO.getFraccion() == null ? "" : reportRegDFVO.getFraccion());
					parametros.put("PARRAFO", reportRegDFVO.getParrafo() == null ? "" : reportRegDFVO.getParrafo());
					parametros.put("INCISO", reportRegDFVO.getInciso() == null ? "" : reportRegDFVO.getInciso());
					parametros.put("INFRACTOR_NOMBRE",
							reportRegDFVO.getInfractor_nombre() == null ? "" : reportRegDFVO.getInfractor_nombre());
					parametros.put("INFRACTOR_LIC",
							reportRegDFVO.getInfractor_lic() == null ? "" : reportRegDFVO.getInfractor_lic());
					parametros.put("INFRACTOR_LIC_TIPO",
							reportRegDFVO.getInfractor_lic_tipo() == null ? "" : reportRegDFVO.getInfractor_lic_tipo());
					parametros.put("INFRACTOR_LIC_EXPEDIDA", reportRegDFVO.getInfractor_lic_expedida() == null ? ""
							: reportRegDFVO.getInfractor_lic_expedida());
					parametros.put("INFRACTOR_CALLE",
							reportRegDFVO.getInfractor_calle() == null ? "" : reportRegDFVO.getInfractor_calle());
					parametros.put("INFRACTOR_NUM_EXT",
							reportRegDFVO.getInfractor_num_ext() == null ? "" : reportRegDFVO.getInfractor_num_ext());
					parametros.put("INFRACTOR_NUM_INT",
							reportRegDFVO.getInfractor_num_int() == null ? "" : reportRegDFVO.getInfractor_num_int());
					parametros.put("INFRACTOR_COLONIA",
							reportRegDFVO.getInfractor_colonia() == null ? "" : reportRegDFVO.getInfractor_colonia());
					parametros.put("INFRACTOR_DELEGACION", reportRegDFVO.getInfractor_delegacion() == null ? ""
							: reportRegDFVO.getInfractor_delegacion());
					parametros.put("INFRACTOR_ESTADO",
							reportRegDFVO.getInfractor_estado() == null ? "" : reportRegDFVO.getInfractor_estado());
					parametros.put("INFRAC_MOTIVACION",
							reportRegDFVO.getInfrac_motivacion() == null ? "" : reportRegDFVO.getInfrac_motivacion());
					parametros.put("INFRAC_DIAS_SANCION", reportRegDFVO.getInfrac_dias_sancion() == null ? ""
							: reportRegDFVO.getInfrac_dias_sancion());
					parametros.put("INFRAC_OBSERVACIONES", reportRegDFVO.getInfrac_observaciones() == null ? ""
							: reportRegDFVO.getInfrac_observaciones());
					parametros.put("INFRAC_EN_DEPOSITO",
							reportRegDFVO.getInfrac_en_deposito() == null ? "" : reportRegDFVO.getInfrac_en_deposito());
					parametros.put("INFRAC_DEPOSITO",
							reportRegDFVO.getInfrac_deposito() == null ? "" : reportRegDFVO.getInfrac_deposito());
					parametros.put("RESPONSABLE_VEH",
							reportRegDFVO.getResponsable_veh() == null ? "" : reportRegDFVO.getResponsable_veh());
					parametros.put("LEYENDA_SALARIO",
							reportRegDFVO.getLeyenda_salario() == null ? "" : reportRegDFVO.getLeyenda_salario());
					parametros.put("VEH_ORIGEN",
							reportRegDFVO.getVeh_origen() == null ? "" : reportRegDFVO.getVeh_origen());
					parametros.put("HANDHELD_SERIE",
							reportRegDFVO.getHandheld_serie() == null ? "" : reportRegDFVO.getHandheld_serie());
				}
			} else {
				reportVO.setNombre((reportVO.getApe_paterno() == null ? "" : reportVO.getApe_paterno() + " ")
						+ (reportVO.getApe_materno() == null ? "" : reportVO.getApe_materno() + " ")
						+ (reportVO.getNombre() == null ? " " : reportVO.getNombre() + " "));

				reportVO.setCalle(reportVO.getCalle() == null ? "" : reportVO.getCalle() + " ");
				reportVO.setNum_exterior(reportVO.getNum_exterior() == null ? "" : reportVO.getNum_exterior() + " ");
				reportVO.setNum_interior(reportVO.getNum_interior() == null ? "" : "Int " + reportVO.getNum_interior());

				reportVO.setColonia(reportVO.getColonia() == null ? "" : reportVO.getColonia());
				if (!reportVO.getCalle().equals("") || !reportVO.getNum_exterior().equals("")
						|| !reportVO.getNum_interior().equals("") && !reportVO.getColonia().equals("")) {
					reportVO.setColonia(", " + reportVO.getColonia());
				}

				reportVO.setDelegacion(reportVO.getDelegacion() == null ? "" : reportVO.getDelegacion());
				if (!reportVO.getCalle().equals("") || !reportVO.getNum_exterior().equals("")
						|| !reportVO.getNum_interior().equals("")
						|| !reportVO.getColonia().equals("") && !reportVO.getDelegacion().equals("")) {
					reportVO.setDelegacion(", " + reportVO.getDelegacion());
				}

				reportVO.setEdo_infractor(reportVO.getEdo_infractor() == null ? "" : reportVO.getEdo_infractor());
				if (!reportVO.getCalle().equals("") || !reportVO.getNum_exterior().equals("")
						|| !reportVO.getNum_interior().equals("")
						|| !reportVO.getColonia().equals("") && !reportVO.getEdo_infractor().equals("")) {
					reportVO.setEdo_infractor(", " + reportVO.getEdo_infractor());
				}

				String dom = reportVO.getCalle() + reportVO.getNum_exterior() + reportVO.getNum_interior()
						+ reportVO.getColonia() + reportVO.getDelegacion() + reportVO.getEdo_infractor();

				String resVehRecibio = "  ";
				String resVehAusente = "  ";
				String resVehNegoARecibir = "  ";

				if (reportVO.getEmp_responsable().equalsIgnoreCase("Recibo"))
					resVehRecibio = " X ";
				else if (reportVO.getEmp_responsable().equalsIgnoreCase("Ausente"))
					resVehAusente = " X ";
				else if (reportVO.getEmp_responsable().equalsIgnoreCase("se nego a recibir"))
					resVehNegoARecibir = " X ";

				parametros.put("infracNum", reportVO.getInfraccion() == null ? "" : reportVO.getInfraccion());
				parametros.put("infracFecha",
						reportVO.getFecha_infraccion() == null ? "" : reportVO.getFecha_infraccion());
				parametros.put("infracNombre", reportVO.getNombre() == null ? "" : reportVO.getNombre());
				parametros.put("infracDomicilio", dom);
				parametros.put("infracLicencia", reportVO.getLicencia() == null ? "" : reportVO.getLicencia());
				parametros.put("licenciaTipo", reportVO.getTipo_licencia() == null ? "" : reportVO.getTipo_licencia());
				parametros.put("servicioPublico",
						reportVO.getServicio_publico() == null ? "" : reportVO.getServicio_publico());
				parametros.put("licenciaExpedida",
						reportVO.getLic_expedida_en() == null ? "" : reportVO.getLic_expedida_en());
				parametros.put("vehiculMarca", reportVO.getMarca() == null ? "" : reportVO.getMarca());
				parametros.put("vehiculoModelo", reportVO.getModelo() == null ? "" : reportVO.getModelo());
				parametros.put("vehiculoColor", reportVO.getColor() == null ? "" : reportVO.getColor());
				parametros.put("vehiculoPlacas", reportVO.getPlaca() == null ? "" : reportVO.getPlaca());
				parametros.put("placaExpedida",
						reportVO.getPlaca_expedida_en() == null ? "" : reportVO.getPlaca_expedida_en());
				parametros.put("infracMotivacion", reportVO.getMotivacion() == null ? "" : reportVO.getMotivacion());
				parametros.put("infracEnLaCalle", reportVO.getEn_la_calle() == null ? "" : reportVO.getEn_la_calle());
				parametros.put("infracEntreLaCalle",
						reportVO.getEntre_la_calle() == null ? "" : reportVO.getEntre_la_calle());
				parametros.put("infracYLaCalle", reportVO.getY_la_calle() == null ? "" : reportVO.getY_la_calle());
				parametros.put("infracMDelegacion",
						reportVO.getDelegacion_infraccion() == null ? "" : reportVO.getDelegacion_infraccion());
				parametros.put("infracMColonia",
						reportVO.getColonia_infraccion() == null ? "" : reportVO.getColonia_infraccion());
				parametros.put("artNum", reportVO.getArticulo() == null ? "" : reportVO.getArticulo());
				parametros.put("artFraccion", reportVO.getFraccion() == null ? "" : reportVO.getFraccion());
				parametros.put("artParrafo", reportVO.getParrafo() == null ? "" : reportVO.getParrafo());
				parametros.put("artInciso", reportVO.getInciso() == null ? "" : reportVO.getInciso());
				parametros.put("sancionNum", reportVO.getArt_sancion() == null ? "" : reportVO.getArt_sancion());
				parametros.put("sancionDias", reportVO.getDias_sal_min() == null ? "" : reportVO.getDias_sal_min());
				parametros.put("polSecAgrp",
						reportVO.getEmp_agrupamiento() == null ? "" : reportVO.getEmp_agrupamiento());
				parametros.put("polPlaca", reportVO.getEmp_placa() == null ? "" : reportVO.getEmp_placa());
				parametros.put("polNombre", reportVO.getEmpleado() == null ? "" : reportVO.getEmpleado());
				parametros.put("resVehRecibio", resVehRecibio);
				parametros.put("resVehAusente", resVehAusente);
				parametros.put("resVehNegoARecibir", resVehNegoARecibir);
				parametros.put("infracNumCtrl", reportVO.getNci() == null ? "" : reportVO.getNci());

				parametros.put("infracdia", infracDia);
				parametros.put("infracmes", infracMes);
				parametros.put("infracaao", infracAao);
				parametros.put("infrachora", infracHora);

				if ((Integer.parseInt(infracDia) < 20) && (Integer.parseInt(infracMes) <= 7)
						&& (Integer.parseInt(infracAao) <= 2007)) {
					rutaArchivo = context.getRealPath("/WEB-INF/jasper/infraccion/impresionInfraccion.jasper");
				} else {
					parametros.put("leyendaSalarioMinimo",
							(Integer.parseInt(infracAao) >= 2015)
									? "Multas en días de unidad de cuenta de la Ciudad de México vigente: "
									: "Multa en días de salario mínimo vigente en el Distrito Federal: ");
					rutaArchivo = context.getRealPath("/WEB-INF/jasper/infraccion/impresionInfraccion_rmt.jasper");

				}
			}
			try {
				reporte.write(JasperRunManager.runReportToPdf(rutaArchivo, parametros, new JREmptyDataSource()));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return reporte;

		}
		return null;
	}

	@Override
	public List<ArticuloVO> buscarExcepcionesEnArticulos(String articulo) {
		List<ArticuloVO> result = new ArrayList<>();
		if (articulo.equals("30"))
			result = infraccionDAO.buscarExcepcionesEnArticulos();
		return result;
	}

	@Override
	public void consultaExisteExpedienteInfraccion(List<VSSPInfracConsGralFVO> listaInfracciones) {
		for (int indice = 0; indice < listaInfracciones.size(); indice++) {
			// String infracNum =
			// listaInfracciones.get(indice).getInfraccionNumero();
			// Integer existeImgExpediente = 0;
			// Integer existeImgIngreso = 0;
			// Integer existeImgExpPago = 0;
			// Integer existeImgHandHeld = 0;
			// Integer existeImgDigA = 0;
			// Integer existeImgDigR = 0;
			Boolean tieneExpediente = false;

			// existeImgExpediente =
			// expedienteMyBatisDAO.getExisteImgExpediente(infracNum);
			// existeImgIngreso =
			// expedienteMyBatisDAO.getExisteImgIngreso(infracNum);
			// existeImgExpPago =
			// expedienteMyBatisDAO.getExisteImgExpPago(infracNum);
			// existeImgHandHeld =
			// expedienteMyBatisDAO.getExisteImgHandHeld(infracNum);
			// existeImgDigA = expedienteMyBatisDAO.getExisteImgDigA(infracNum);
			// existeImgDigR = expedienteMyBatisDAO.getExisteImgDigR(infracNum);

			// if(existeImgExpediente > 0 || existeImgIngreso > 0 ||
			// existeImgExpPago > 0 ||
			// existeImgHandHeld > 0 || existeImgDigA > 0 || existeImgDigR > 0){
			// tieneExpediente = true;
			// }

			listaInfracciones.get(indice).setTieneExpediente(tieneExpediente);
		}
	}

	//@Transactional
	@Override
	public String getPlacaInfraccion(String infraNum, String parametro) {
		// TODO Auto-generated method stub
		String resultadoMensaje = "";

		switch (parametro) {
		case "V_WS_SF_LINEAC_REASIGNA_ALL":
			resultadoMensaje = infraccionDAO.getPlacaVehiculo(infraNum);
			break;
		case "V_WS_SF_LINEAC_REASIGNA_DIG":
			resultadoMensaje = infraccionDAO.getPlacaVehiculoD(infraNum);
			break;
		default:
			resultadoMensaje = "";
			break;
		}
		return resultadoMensaje;
	}
}
