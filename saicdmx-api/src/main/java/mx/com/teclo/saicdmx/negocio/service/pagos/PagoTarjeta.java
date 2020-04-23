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
 
@Service("pagoTarjeta")
public class PagoTarjeta extends AbstractPagoService {
	private static final Logger logger = LoggerFactory.getLogger(PagoInfraccionServiceImpl.class);
	//private PagoVO pagoVO;
		//private TransaccionVO transaccionVO;

		@Override
		public PagoVO pagar(DatosPagoVO datosPagoVO) {

			PagoVO pagoVO = new PagoVO();
			TransaccionVO transaccionVO =  new TransaccionVO();
			transaccionVO=datosPagoVO.getTransaccionVO();
			logger.info("Iniciando pago con {}, NCI:  {}, MONTO: {}  ", datosPagoVO.getFormaPago(), datosPagoVO.getNci(),datosPagoVO.getImporte());

		logger.info("Iniciando pago con {}, NCI:  {}, MONTO: {}  ", datosPagoVO.getFormaPago(), datosPagoVO.getNci(),datosPagoVO.getImporte());

		//consultarCaja(datosPagoVO.getNci());
		pagoVO = consultarDetallePago(datosPagoVO.getNci(), new Long(datosPagoVO.getCajaId()));
		obtenerDatosTransaccion(datosPagoVO, pagoVO);
		
		if (transaccionVO.getRespuesta().getCdError() != null && transaccionVO.getRespuesta().getNbError() != null) {
			pagoVO = new PagoVO();
			pagoVO.setMensaje("No se pudo realizar la transacción");

			logger.info("Error al realizar la transaccion : [{},{}] ", transaccionVO.getRespuesta().getCdError(),transaccionVO.getRespuesta().getNbError());

		} else {

			if (transaccionVO.getRespuesta().getResponse().equalsIgnoreCase(EnumTransaccionPago.TRANSACCION_APROBADA.getTransaccion())) {

				logger.info("::::::GUARDANDO TRANSACCION:::::::");

				System.out.println("\nCaja: " + pagoVO.getCajaId() + "\n getTotalPago(): " + pagoVO.getTotalPago()
						+ "\n getBancoTarjeta: " + pagoVO.getBancoTarjeta() + "\n getBancoTarjeta: "
						+ pagoVO.getBancoTarjeta() + "\n getNumTarjeta: " + pagoVO.getNumTarjeta()
						+ "\n getNomPropTarjeta: " + pagoVO.getNomPropTarjeta() + "\n getTransRespuesta: "
						+ pagoVO.getTransRespuesta() + "\n getNumAutorizacion: " + pagoVO.getNumAutorizacion()
						+ "\n getNomAfil: " + pagoVO.getNomAfil() + "\n getBanAfil: " + pagoVO.getBanAfil()
						+ "\n getFechaTrans: " + pagoVO.getFechaTrans() + "\n getRefeTrans: " + pagoVO.getRefeTrans()
						+ "\n getVouchers: " + pagoVO.getVoucher() + "\n Numero Operacion: " + pagoVO.getNumeroOperacion() + "\n");
				if(datosPagoVO.isExisteTransaccion()==false){//si no existe la transaccion se guarada en tabal transacciones
					getPagoInfraccionMyBatisDAO().guardarPagoTarjeta(pagoVO);
					pagoVO.setTransaccion(
							pagoVO.getResultado().equals(EnumTransaccionPago.ERROR_TRANSACCION.getTransaccion()) ? EnumTransaccionPago.PAGO_SIN_TRANSACCION.getTransaccion() : pagoVO.getResultado());
				}
				

				
				logger.info("::::::GUARDANDO PAGO:::::::");

				System.out.println("\ngetTipoPago: " + pagoVO.getTipoPago() + "\n getCajaId(): " + pagoVO.getCajaId()
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
						+ "\n getConceptoPagoArrastre: " + pagoVO.getConceptoPagoArrastre() + "\n getConceptoPagoPiso: "
						+ pagoVO.getConceptoPagoPiso() + "\n getConceptoPagoCandado: " + pagoVO.getConceptoPagoCandado()
						+ "\n");
				if(datosPagoVO.isExistePago()==false){//si no existe el pago en local se guarada
					getPagoInfraccionMyBatisDAO().guardarPagoEfectivo(pagoVO);
				}
				
			} else
				pagoVO.setMensaje("No se pudo realizar la transacción.");

			logger.info("Respuesta transaccion MIT : [{},{}] ", transaccionVO.getRespuesta().getCdError(),transaccionVO.getRespuesta().getNbError());
		}

		logger.info("Respuesta transaccion MIT : [{},{}] ", transaccionVO.getRespuesta().getCdError(),transaccionVO.getRespuesta().getNbError());

		logger.info("Finalizando pago en {}, mensaje: {}  ", datosPagoVO.getFormaPago(), pagoVO.getMensaje());

		return pagoVO;
	}

		//private PagoVO obtenerDatosTransaccion(DatosPagoVO datosPagoVO,TransaccionVO transaccionVO,PagoVO pagoVO) {
		private PagoVO obtenerDatosTransaccion(DatosPagoVO datosPagoVO, PagoVO pagoVO) {
			// IMPORTE : 1.00
			// MONEDA : MXN
			// NCI : ?
			// receptor datos del dispositivo
			//PagoVO pagoVO = new PagoVO();
			TransaccionVO transaccionVO = new TransaccionVO();
			transaccionVO = datosPagoVO.getTransaccionVO();
//		    	transaccionVO = operacionesService.realizarCobro(datosPagoVO.getImporte(), "MXN", datosPagoVO.getNci());

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
			pagoVO.setLugarPago(EnumInfraccionLugarPago.DEPOSITO.getLugarPago().toString());
			pagoVO.setEntidad(EnumInfraccionEntidadesPago.TARDEB.getEntidadPago().toString());
			pagoVO.setReferencia(" ");
			pagoVO.setTransaccion(datosPagoVO.getTranId()==null ? "0" : datosPagoVO.getTranId());
			pagoVO.setCajaId(datosPagoVO.getCajaId());
			pagoVO.setUsuario(getUsuarioFirmadoService().getUsuarioFirmadoVO().getId().toString());
			pagoVO.setFormaPago(EnumInfraccionFormaPago.TARJETA_DEBITO.getFormaPago().toString());

			return pagoVO;
		}

}
