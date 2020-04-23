package mx.com.teclo.saicdmx.negocio.service.pagos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionEntidadesPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionFormaPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionLugarPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionTipoPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumTransaccionPago;
import mx.com.teclo.siidf.centrodepagos.mit.vo.TransaccionVO; 

@Service("pagoActaAdministrativa")
public class PagoActaAdministrativa extends AbstractPagoService {

	private static final Logger logger = LoggerFactory.getLogger(PagoActaAdministrativa.class);
	private PagoVO pagoVO;
	private TransaccionVO transaccionVO;
	  
	@Override
	public PagoVO pagar(DatosPagoVO datosPagoVO) {

		logger.info("Iniciando pago con {}, NCI:  {}, MONTO: {}  ", datosPagoVO.getFormaPago(), datosPagoVO.getNci(),	datosPagoVO.getImporte());

		//consultarCaja(datosPagoVO.getNci());
		pagoVO = consultarDetallePago(datosPagoVO.getNci(), new Long(datosPagoVO.getCajaId()));
		
		obtenerDatosActa(datosPagoVO);
		if (datosPagoVO.getFormaPago().equals(EnumInfraccionFormaPago.TARJETA_DEBITO.getFormaPago().toString())) {

			obtenerDatosTransaccion(datosPagoVO);

			if (transaccionVO.getRespuesta().getCdError() != null&& transaccionVO.getRespuesta().getNbError() != null) {
				pagoVO = new PagoVO();
				pagoVO.setMensaje("No se pudo realizar la transacción.");
				pagoVO.setResultado("-1");

				logger.info("Error al realizar la transaccion : [{},{}] ", transaccionVO.getRespuesta().getCdError(),
						    transaccionVO.getRespuesta().getNbError());

			} else {

				if (transaccionVO.getRespuesta().getResponse().equalsIgnoreCase(EnumTransaccionPago.TRANSACCION_APROBADA.getTransaccion())) {

					logger.info("::::::GUARDANDO TRANSACCION:::::::");

					logger.info("Num Infrac : " + pagoVO.getNumInfrac()
							+"Caja: " + pagoVO.getCajaId() + "\n getTotalPago(): " + pagoVO.getTotalPago()
							+ "\n getBancoTarjeta: " + pagoVO.getBancoTarjeta() + "\n getNumTarjeta: "
							+ pagoVO.getNumTarjeta() + "\n getNumTarjeta: " + pagoVO.getNumTarjeta()
							+ "\n getNomPropTarjeta: " + pagoVO.getNomPropTarjeta() + "\n getTransRespuesta: "
							+ pagoVO.getTransRespuesta() + "\n getNumAutorizacion: " + pagoVO.getNumAutorizacion()
							+ "\n getNomAfil: " + pagoVO.getNomAfil() + "\n getBanAfil: " + pagoVO.getBanAfil()
							+ "\n getFechaTrans: " + pagoVO.getFechaTrans() + "\n getRefeTrans: "
							+ pagoVO.getRefeTrans() + "\n getVouchers: " + pagoVO.getVoucher() + "\n Numero Operacion: " + pagoVO.getNumeroOperacion() + "\n");

					getPagoInfraccionMyBatisDAO().guardarPagoTarjeta(pagoVO);

					pagoVO.setTransaccion( pagoVO.getResultado().equals(EnumTransaccionPago.ERROR_TRANSACCION.getTransaccion())
									? EnumTransaccionPago.PAGO_SIN_TRANSACCION.getTransaccion() : pagoVO.getResultado());

					pagoVO.setFormaPago(EnumInfraccionFormaPago.TARJETA_DEBITO.getFormaPago().toString());

					logger.info("::::::GUARDANDO PAGO CON TARJETA:::::::");

					logger.info("\n getCajaId(): " + pagoVO.getCajaId()	+ "\n getNumCtrl: " + pagoVO.getNumCtrl() 
							+ "\n getNumInfrac: " +pagoVO.getNumInfrac()+"\n getArrastre: " + pagoVO.getArrastre() + "\n getDerechoPiso: "	+ pagoVO.getDerechoPiso() 
							+ "\n getReferencia: " + pagoVO.getReferencia() + "\n getTransaccion: "
							+ pagoVO.getTransaccion() + "\n getUsuario: " + pagoVO.getUsuario() + "\n getFormaPago: "
							+ pagoVO.getFormaPago() );

					getPagoInfraccionMyBatisDAO().guardarPagoActaAdministrativa(pagoVO);

				}

				logger.info("Respuesta transaccion MIT : [{},{}] ", transaccionVO.getRespuesta().getCdError(), transaccionVO.getRespuesta().getNbError());
			}
		} else if (datosPagoVO.getFormaPago().equals(EnumInfraccionFormaPago.EFECTIVO.getFormaPago().toString())) {
			pagoVO.setFormaPago(EnumInfraccionFormaPago.EFECTIVO.getFormaPago().toString());

			logger.info("::::::GUARDANDO PAGO CON EFECTIVO:::::::");


			logger.info("\n getCajaId(): " + pagoVO.getCajaId()	+ "\n getNumCtrl: " + pagoVO.getNumCtrl() 
					+ "\n getNumInfrac: " +"\n getArrastre: " + pagoVO.getArrastre() + "\n getDerechoPiso: "	+ pagoVO.getDerechoPiso() 
					+ "\n getReferencia: " + pagoVO.getReferencia() + "\n getTransaccion: "
					+ pagoVO.getTransaccion() + "\n getUsuario: " + pagoVO.getUsuario() + "\n getFormaPago: "
					+ pagoVO.getFormaPago() );

			pagoVO.setFormaPago(EnumInfraccionFormaPago.EFECTIVO.getFormaPago().toString());
			getPagoInfraccionMyBatisDAO().guardarPagoActaAdministrativa(pagoVO);
			logger.info("Finalizando pago en {}, mensaje: {}  ", datosPagoVO.getFormaPago(), pagoVO.getMensaje());
			
		}

		return pagoVO;
	}

	private PagoVO obtenerDatosActa(DatosPagoVO datosPagoVO) {	
		pagoVO.setReferencia(datosPagoVO.getDocumentoActa());
		pagoVO.setTransaccion("0");
		pagoVO.setCajaId(datosPagoVO.getCajaId());
  		pagoVO.setUsuario(getUsuarioFirmadoService().getUsuarioFirmadoVO().getId().toString());

		return pagoVO;

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
		pagoVO.setFormaPago(EnumInfraccionFormaPago.TARJETA_DEBITO.getFormaPago().toString());

		return pagoVO;
	}

}
