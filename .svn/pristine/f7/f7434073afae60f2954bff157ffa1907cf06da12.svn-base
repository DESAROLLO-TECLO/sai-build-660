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

@Service("pagoEfectivo")
public class PagoEfectivo extends AbstractPagoService {
	
	private static final Logger logger = LoggerFactory.getLogger(PagoInfraccionServiceImpl.class);

	private PagoVO pagoVO;

	@Override
	public PagoVO pagar(DatosPagoVO datosPagoVO) {
		
		logger.info("Iniciando pago con {}, NCI:  {}, MONTO: {}  ", datosPagoVO.getFormaPago(), datosPagoVO.getNci()  ,datosPagoVO.getImporte());

		//consultarCaja(datosPagoVO.getNci());
		
		pagoVO = consultarDetallePago(datosPagoVO.getNci(),  new Long(datosPagoVO.getCajaId()));
		pagoVO.setTipoPago(EnumInfraccionTipoPago.CON_INFRACCION.getTipoPago().toString());
		pagoVO.setAdicionales(" ");
		pagoVO.setLugarPago(EnumInfraccionLugarPago.DEPOSITO.getLugarPago().toString());
		pagoVO.setEntidad(EnumInfraccionEntidadesPago.EFE.getEntidadPago().toString());
		pagoVO.setReferencia(" ");
		pagoVO.setTransaccion("0");
		pagoVO.setCajaId(datosPagoVO.getCajaId());
		pagoVO.setUsuario(getUsuarioFirmadoService().getUsuarioFirmadoVO().getId().toString());
		pagoVO.setFormaPago(EnumInfraccionFormaPago.EFECTIVO.getFormaPago().toString());
		logger.info("::::::GUARDANDO PAGO:::::::");

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
		
		logger.info("Finalizando pago en {}, mensaje: {}  ", datosPagoVO.getFormaPago(), pagoVO.getMensaje());

 		return pagoVO;
 	}

	 

}
