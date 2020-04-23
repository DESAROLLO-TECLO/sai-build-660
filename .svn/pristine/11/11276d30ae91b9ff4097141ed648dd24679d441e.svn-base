package mx.com.teclo.saicdmx.negocio.service.pagos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionEntidadesPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionFormaPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionLugarPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionTipoPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumTransaccionPago;
import mx.com.teclo.siidf.centrodepagos.mit.vo.TransaccionVO;

@Service("pagoDocumento")
public class PagoDocumento extends AbstractPagoService {

	private static final Logger logger = LoggerFactory.getLogger(PagoInfraccionServiceImpl.class);
	private PagoVO pagoVO;
//	@Autowired
//	@Qualifier("operacionesService")
//	private IOperacionesService operacionesService;
	private TransaccionVO transaccionVO;

	@Override
	public PagoVO pagar(DatosPagoVO datosPagoVO) {

		logger.info("Iniciando pago con {}, NCI:  {}, MONTO: {}  ", datosPagoVO.getFormaPago(), datosPagoVO.getNci(),
				datosPagoVO.getImporte());

		//consultarCaja(datosPagoVO.getNci());
		pagoVO = consultarDetallePago(datosPagoVO.getNci(), new Long(datosPagoVO.getCajaId()));
		obtenerDatosDocumento(datosPagoVO);

		if (datosPagoVO.getFormaPagoDocumentos().equals(EnumInfraccionFormaPago.TARJETA_DEBITO.getFormaPago().toString())) {

			obtenerDatosTransaccion(datosPagoVO);

			if (transaccionVO.getRespuesta().getCdError() != null && transaccionVO.getRespuesta().getNbError() != null) {
				pagoVO = new PagoVO();
				pagoVO.setMensaje("No se pudo realizar la transacción");
				pagoVO.setResultado("-1");

				logger.info("Error al realizar la transaccion : [{},{}] ", transaccionVO.getRespuesta().getCdError(),
						    transaccionVO.getRespuesta().getNbError());

			} else {

				if (transaccionVO.getRespuesta().getResponse().equalsIgnoreCase(EnumTransaccionPago.TRANSACCION_APROBADA.getTransaccion())) {

					logger.info("::::::GUARDANDO TRANSACCION:::::::");

					logger.info("Caja: " + pagoVO.getCajaId() + "\n getTotalPago(): " + pagoVO.getTotalPago()
							+ "\n getBancoTarjeta: " + pagoVO.getBancoTarjeta() + "\n getBancoTarjeta: "
							+ pagoVO.getBancoTarjeta() + "\n getNumTarjeta: " + pagoVO.getNumTarjeta()
							+ "\n getNomPropTarjeta: " + pagoVO.getNomPropTarjeta() + "\n getTransRespuesta: "
							+ pagoVO.getTransRespuesta() + "\n getNumAutorizacion: " + pagoVO.getNumAutorizacion()
							+ "\n getNomAfil: " + pagoVO.getNomAfil() + "\n getBanAfil: " + pagoVO.getBanAfil()
							+ "\n getFechaTrans: " + pagoVO.getFechaTrans() + "\n getRefeTrans: "
							+ pagoVO.getRefeTrans() + "\n getVouchers: " + pagoVO.getVoucher() +"\n Numero Operacion: " + pagoVO.getNumeroOperacion() + "\n");

					getPagoInfraccionMyBatisDAO().guardarPagoTarjeta(pagoVO);

					pagoVO.setTransaccion( pagoVO.getResultado().equals(EnumTransaccionPago.ERROR_TRANSACCION.getTransaccion())
									? EnumTransaccionPago.PAGO_SIN_TRANSACCION.getTransaccion() : pagoVO.getResultado());

					pagoVO.setFormaPago(EnumInfraccionFormaPago.TARJETA_DEBITO.getFormaPago().toString());

					logger.info("::::::GUARDANDO PAGO CON TARJETA:::::::");

					logger.info("getTipoPago: " + pagoVO.getTipoPago() + "\n getCajaId(): " + pagoVO.getCajaId()
							+ "\n getNumCtrl: " + pagoVO.getNumCtrl() + "\n getNumInfrac: " + pagoVO.getNumInfrac()
							+ "\n getMonto: " + pagoVO.getMonto() + "\n getDescuento: " + pagoVO.getDescuento()
							+ "\n getActualizacion: " + pagoVO.getActualizacion() + "\n getRecargos: "
							+ pagoVO.getRecargos() + "\n getArrastre: " + pagoVO.getArrastre() + "\n getDerechoPiso: "
							+ pagoVO.getDerechoPiso() + "\n getAdicionales: " + pagoVO.getAdicionales()
							+ "\n getLugarPago: " + pagoVO.getLugarPago() + "\n getEntidad: " + pagoVO.getEntidad()
							+ "\n getReferencia: " + pagoVO.getReferencia() + "\n getTransaccion: "
							+ pagoVO.getTransaccion() + "\n getUsuario: " + pagoVO.getUsuario() + "\n getFormaPago: "
							+ pagoVO.getFormaPago() + "\n getCandado: " + pagoVO.getCandado()
							+ "\n getConceptoPagoInfraccion: " + pagoVO.getConceptoPagoInfraccion()
							+ "\n getConceptoPagoArrastre: " + pagoVO.getConceptoPagoArrastre()
							+ "\n getConceptoPagoPiso: " + pagoVO.getConceptoPagoPiso() + "\n getConceptoPagoCandado: "
							+ pagoVO.getConceptoPagoCandado() + "\n");

					getPagoInfraccionMyBatisDAO().guardarPagoEfectivo(pagoVO);

				}

				logger.info("Respuesta transaccion MIT : [{},{}] ", transaccionVO.getRespuesta().getCdError(), transaccionVO.getRespuesta().getNbError());
			}
		} else if (datosPagoVO.getFormaPagoDocumentos().equals(EnumInfraccionFormaPago.EFECTIVO.getFormaPago().toString())) {
			
			pagoVO.setFormaPago(EnumInfraccionFormaPago.EFECTIVO.getFormaPago().toString());
			
			logger.info("::::::GUARDANDO PAGO CON EFECTIVO:::::::");

			logger.info("getTipoPago: " + pagoVO.getTipoPago() + "\n getCajaId(): " + pagoVO.getCajaId()
					+ "\n getNumCtrl: " + pagoVO.getNumCtrl() + "\n getNumInfrac: " + pagoVO.getNumInfrac()
					+ "\n getMonto: " + pagoVO.getMonto() + "\n getDescuento: " + pagoVO.getDescuento()
					+ "\n getActualizacion: " + pagoVO.getActualizacion() + "\n getRecargos: " + pagoVO.getRecargos()
					+ "\n getArrastre: " + pagoVO.getArrastre() + "\n getDerechoPiso: " + pagoVO.getDerechoPiso()
					+ "\n getAdicionales: " + pagoVO.getAdicionales() + "\n getLugarPago: " + pagoVO.getLugarPago()
					+ "\n getEntidad: " + pagoVO.getEntidad() + "\n getReferencia: " + pagoVO.getReferencia()
					+ "\n getTransaccion: " + pagoVO.getTransaccion() + "\n getUsuario: " + pagoVO.getUsuario()
					+ "\n getFormaPago: " + pagoVO.getFormaPago() + "\n getCandado: " + pagoVO.getCandado()
					+ "\n getConceptoPagoInfraccion: " + pagoVO.getConceptoPagoInfraccion()
					+ "\n getConceptoPagoArrastre: " + pagoVO.getConceptoPagoArrastre() + "\n getConceptoPagoPiso: "
					+ pagoVO.getConceptoPagoPiso() + "\n getConceptoPagoCandado: " + pagoVO.getConceptoPagoCandado()
					+ "\n");

			
			getPagoInfraccionMyBatisDAO().guardarPagoEfectivo(pagoVO);
			logger.info("Finalizando pago en {}, mensaje: {}  ", datosPagoVO.getFormaPago(), pagoVO.getMensaje());
			
		} else {

			logger.info("::::::GUARDANDO PAGO SOLO DOCUMENTO:::::::");

			logger.info("getTipoPago: " + pagoVO.getTipoPago() + "\n getCajaId(): " + pagoVO.getCajaId()
					+ "\n getNumCtrl: " + pagoVO.getNumCtrl() + "\n getNumInfrac: " + pagoVO.getNumInfrac()
					+ "\n getMonto: " + pagoVO.getMonto() + "\n getDescuento: " + pagoVO.getDescuento()
					+ "\n getActualizacion: " + pagoVO.getActualizacion() + "\n getRecargos: " + pagoVO.getRecargos()
					+ "\n getArrastre: " + pagoVO.getArrastre() + "\n getDerechoPiso: " + pagoVO.getDerechoPiso()
					+ "\n getAdicionales: " + pagoVO.getAdicionales() + "\n getLugarPago: " + pagoVO.getLugarPago()
					+ "\n getEntidad: " + pagoVO.getEntidad() + "\n getReferencia: " + pagoVO.getReferencia()
					+ "\n getTransaccion: " + pagoVO.getTransaccion() + "\n getUsuario: " + pagoVO.getUsuario()
					+ "\n getFormaPago: " + pagoVO.getFormaPago() + "\n getCandado: " + pagoVO.getCandado()
					+ "\n getConceptoPagoInfraccion: " + pagoVO.getConceptoPagoInfraccion()
					+ "\n getConceptoPagoArrastre: " + pagoVO.getConceptoPagoArrastre() + "\n getConceptoPagoPiso: "
					+ pagoVO.getConceptoPagoPiso() + "\n getConceptoPagoCandado: " + pagoVO.getConceptoPagoCandado()
					+ "\n");
			
			getPagoInfraccionMyBatisDAO().guardarPagoEfectivo(pagoVO);

			logger.info("Finalizando pago en {}, mensaje: {}  ", datosPagoVO.getFormaPago(), pagoVO.getMensaje());
		}

		return pagoVO;
	}

	private PagoVO obtenerDatosDocumento(DatosPagoVO datosPagoVO) {

		validarCamposDocumento(datosPagoVO);

		String conceptoArrastre = datosPagoVO.getDocumentoVO().getArrastreTipo() + "|"
				+ datosPagoVO.getDocumentoVO().getArrastreSinCobro() + "|"
				+ datosPagoVO.getDocumentoVO().getArrastreMonto() + "|"
				+ datosPagoVO.getDocumentoVO().getArrastreReferencia() + "|"
				+ datosPagoVO.getDocumentoVO().getArrastreEntidad();

		String conceptoInfraccion =  datosPagoVO.getDocumentoVO().getInfraccionTipo() + "|"
				+ datosPagoVO.getDocumentoVO().getInfraccionSinCobro() + "|"
				+ datosPagoVO.getDocumentoVO().getInfraccionMonto() + "|"
				+ datosPagoVO.getDocumentoVO().getInfraccionReferencia() + "|"
				+ datosPagoVO.getDocumentoVO().getInfraccionEntidad();

		String conceptoCandado = datosPagoVO.getDocumentoVO().getCandadoTipo() + "|"
				+ datosPagoVO.getDocumentoVO().getCandadoSinCobro() + "|"
				+ datosPagoVO.getDocumentoVO().getCandadoMonto() + "|"
				+ datosPagoVO.getDocumentoVO().getCandadoReferencia() + "|"
				+ datosPagoVO.getDocumentoVO().getCandadoEntidad();

		String conceptoPiso =  datosPagoVO.getDocumentoVO().getPisoTipo() + "|"
				+ datosPagoVO.getDocumentoVO().getPisoSinCobro() + "|" + datosPagoVO.getDocumentoVO().getPisoMonto()
				+ "|" + datosPagoVO.getDocumentoVO().getPisoReferencia() + "|"
				+ datosPagoVO.getDocumentoVO().getPisoEntidad();

		pagoVO.setConceptoPagoInfraccion(conceptoInfraccion == null ? " " : conceptoInfraccion);
		pagoVO.setConceptoPagoArrastre(conceptoArrastre == null ? " " : conceptoArrastre);
		pagoVO.setConceptoPagoPiso(conceptoPiso == null ? " " : conceptoPiso);
		pagoVO.setConceptoPagoCandado(conceptoCandado == null ? " " : conceptoCandado);

		pagoVO.setTipoPago(EnumInfraccionTipoPago.CON_INFRACCION.getTipoPago().toString());
		pagoVO.setAdicionales(" ");
		pagoVO.setLugarPago(EnumInfraccionLugarPago.PREVIO.getLugarPago().toString());
		//pagoVO.setEntidad(EnumInfraccionEntidadesPago.BANCO.getEntidadPago().toString());
		pagoVO.setEntidad(datosPagoVO.getDocumentoVO().getInfraccionEntidad());
		pagoVO.setReferencia(" ");
		pagoVO.setTransaccion("0");
		pagoVO.setCajaId(datosPagoVO.getCajaId());
		pagoVO.setUsuario(getUsuarioFirmadoService().getUsuarioFirmadoVO().getId().toString());
		pagoVO.setFormaPago(EnumInfraccionFormaPago.DOCUMENTO.getFormaPago().toString());

		return pagoVO;

	}

	private void validarCamposDocumento(DatosPagoVO datosPagoVO) {

		if (datosPagoVO.getDocumentoVO().getArrastreEntidad() == null)
			datosPagoVO.getDocumentoVO().setArrastreEntidad("0");
		if (datosPagoVO.getDocumentoVO().getArrastreMonto() == null)
			datosPagoVO.getDocumentoVO().setArrastreMonto(" ");
		if (datosPagoVO.getDocumentoVO().getArrastreReferencia() == null)
			datosPagoVO.getDocumentoVO().setArrastreReferencia(" ");
		if (datosPagoVO.getDocumentoVO().getArrastreSinCobro() == null)
			datosPagoVO.getDocumentoVO().setArrastreSinCobro(" ");
		if (datosPagoVO.getDocumentoVO().getArrastreTipo() == null)
			datosPagoVO.getDocumentoVO().setArrastreTipo(" ");

		if (datosPagoVO.getDocumentoVO().getPisoEntidad() == null)
			datosPagoVO.getDocumentoVO().setPisoEntidad("0");
		if (datosPagoVO.getDocumentoVO().getPisoMonto() == null)
			datosPagoVO.getDocumentoVO().setPisoMonto(" ");
		if (datosPagoVO.getDocumentoVO().getPisoReferencia() == null)
			datosPagoVO.getDocumentoVO().setPisoReferencia(" ");
		if (datosPagoVO.getDocumentoVO().getPisoSinCobro() == null)
			datosPagoVO.getDocumentoVO().setPisoSinCobro(" ");
		if (datosPagoVO.getDocumentoVO().getPisoTipo() == null)
			datosPagoVO.getDocumentoVO().setPisoTipo(" ");

		if (datosPagoVO.getDocumentoVO().getInfraccionEntidad() == null)
			datosPagoVO.getDocumentoVO().setInfraccionEntidad("0");
		if (datosPagoVO.getDocumentoVO().getInfraccionMonto() == null)
			datosPagoVO.getDocumentoVO().setInfraccionMonto(" ");
		if (datosPagoVO.getDocumentoVO().getInfraccionReferencia() == null)
			datosPagoVO.getDocumentoVO().setInfraccionReferencia(" ");
		if (datosPagoVO.getDocumentoVO().getInfraccionSinCobro() == null)
			datosPagoVO.getDocumentoVO().setInfraccionSinCobro(" ");
		if (datosPagoVO.getDocumentoVO().getInfraccionTipo() == null)
			datosPagoVO.getDocumentoVO().setInfraccionTipo(" ");

		if (datosPagoVO.getDocumentoVO().getCandadoEntidad() == null)
			datosPagoVO.getDocumentoVO().setCandadoEntidad("0");
		if (datosPagoVO.getDocumentoVO().getCandadoMonto() == null)
			datosPagoVO.getDocumentoVO().setCandadoMonto(" ");
		if (datosPagoVO.getDocumentoVO().getCandadoReferencia() == null)
			datosPagoVO.getDocumentoVO().setCandadoReferencia(" ");
		if (datosPagoVO.getDocumentoVO().getCandadoSinCobro() == null)
			datosPagoVO.getDocumentoVO().setCandadoSinCobro(" ");
		if (datosPagoVO.getDocumentoVO().getCandadoTipo() == null)
			datosPagoVO.getDocumentoVO().setCandadoTipo(" ");

	}

	private PagoVO obtenerDatosTransaccion(DatosPagoVO datosPagoVO) {
		// IMPORTE : 1.00
		// MONEDA : MXN
		// NCI : ?
		// receptor datos del dispositivo
		transaccionVO = datosPagoVO.getTransaccionVO();
//		transaccionVO = operacionesService.realizarCobro(datosPagoVO.getImporte(), "MXN", datosPagoVO.getNci());
		
		pagoVO.setTotalPago(datosPagoVO.getImporte());
		pagoVO.setBancoTarjeta(transaccionVO.getRespuesta().getCcType());
		pagoVO.setTransRespuesta(transaccionVO.getRespuesta().getResponse());
		pagoVO.setNumAutorizacion(transaccionVO.getRespuesta().getAuth());
		pagoVO.setNumeroOperacion( transaccionVO.getRespuesta().getFolioCpagos());

		// getDsTxMerchant() Nombre de la afiliacion.
		// getMerchant() ID de la afiliacion del centro de pagos.
		pagoVO.setNomAfil(transaccionVO.getRespuesta().getDsTxMerchant());
		// Banco de la tarjeta.
		pagoVO.setBanAfil(transaccionVO.getRespuesta().getCcType());
		pagoVO.setFechaTrans(transaccionVO.getRespuesta().getDate());
		pagoVO.setRefeTrans(transaccionVO.getRespuesta().getReference());
		pagoVO.setVoucher(transaccionVO.getRespuesta().getVoucher());
		// StrTrack numero de tarjeta.
		pagoVO.setStrTrack(transaccionVO.getTarjeta().getCcNumber());
		pagoVO.setSalidaProceso(transaccionVO.getRespuesta().getResponse());
		pagoVO.setNumTarjeta(transaccionVO.getTarjeta().getCcNumber());
		pagoVO.setNomPropTarjeta(transaccionVO.getTarjeta().getCcName());

		pagoVO.setTipoPago(EnumInfraccionTipoPago.CON_INFRACCION.getTipoPago().toString());
		pagoVO.setAdicionales(" ");
		pagoVO.setLugarPago(EnumInfraccionLugarPago.PREVIO.getLugarPago().toString());
		//pagoVO.setEntidad(EnumInfraccionEntidadesPago.BANCO.getEntidadPago().toString());
		pagoVO.setEntidad(datosPagoVO.getDocumentoVO().getInfraccionEntidad());
		pagoVO.setReferencia(" ");
		pagoVO.setTransaccion("0");
		pagoVO.setCajaId(datosPagoVO.getCajaId());
		pagoVO.setUsuario(getUsuarioFirmadoService().getUsuarioFirmadoVO().getId().toString());
		pagoVO.setFormaPago(EnumInfraccionFormaPago.TARJETA_DEBITO.getFormaPago().toString());

		return pagoVO;
	}

}
