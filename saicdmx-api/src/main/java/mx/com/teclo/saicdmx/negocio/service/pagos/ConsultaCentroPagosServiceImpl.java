package mx.com.teclo.saicdmx.negocio.service.pagos;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.api.rest.pagos.CentroPagosServlet;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ConsultaTransaccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosTransaccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.RespuestaCentroPagosVO;
import mx.com.teclo.siidf.centrodepagos.mit.service.IOperacionesService;
import mx.com.teclo.siidf.centrodepagos.mit.vo.Consulta;
import mx.com.teclo.siidf.centrodepagos.mit.vo.ConsultaTransaccionVO;
import mx.com.teclo.siidf.centrodepagos.mit.vo.TransaccionVO;

@Service("consultaCentroPagosService")
public class ConsultaCentroPagosServiceImpl implements ConsultaCentroPagosService {
	
	private static final Logger logger = LoggerFactory.getLogger(CentroPagosServlet.class);
	
	@Autowired
	@Qualifier("operacionesService")
	private IOperacionesService operacionesService;
	
	private static final String PAGO_APROBADO = "VENTA";
	private static final String TYPE_OPERATION_QA = "AGREGADO EN QA";
	private static final String TYPE_OPERATION_PROD = "APPROVED";

	private ConsultaTransaccionVO consultaTransaccionVO;

	public List<RespuestaCentroPagosVO> obtenerRegistroCentroPagos(String fecha) {
		List<RespuestaCentroPagosVO> listaPagos = new ArrayList<RespuestaCentroPagosVO>();
		RespuestaCentroPagosVO transaccion = new RespuestaCentroPagosVO();

		int year = Integer.parseInt(fecha.substring(6, 10));
		int month = Integer.parseInt(fecha.substring(3, 5)) - 1;
		int day = Integer.parseInt(fecha.substring(0, 2));
		Calendar calendar = new GregorianCalendar(year, month, day);

		Date date = calendar.getTime();
		consultaTransaccionVO = operacionesService.consultaTransacciones(date, "");
		if (consultaTransaccionVO.getCdError() == null) {

			if (consultaTransaccionVO.getConsulta().size() > 0) {
				for (Consulta consulta : consultaTransaccionVO.getConsulta()) {
					transaccion = new RespuestaCentroPagosVO();
					transaccion.setImporte(consulta.getTxAmount());
					transaccion.setNumeroAutorizacion(consulta.getAuth());
					transaccion.setOperacionNumero(consulta.getOperation());
					transaccion.setOperacionRespuesta(consulta.getNbResponse());
					transaccion.setOperacionFecha(consulta.getDateBank());
					transaccion.setOperacionFechaHora(consulta.getDateRegister());
					transaccion.setReferencia(consulta.getNbReference());
					transaccion.setTarjetaNombreUsuario(consulta.getCcName());
					transaccion.setTarjetaNumero(consulta.getCcNumber());
					transaccion
							.setPagoAprobado(transaccion.getOperacionRespuesta().toUpperCase().equals(PAGO_APROBADO));
					listaPagos.add(transaccion);
				}

			}
		}
		return listaPagos;
	}

	public List<Consulta> obtenerReferenciasPagosHandheld(String fecha) {

		logger.info("Ejecutando metodo obtenerReferenciasPagosHandheld [fecha] [{}]", fecha);

		List<Consulta> listaPagos = new ArrayList<Consulta>();

		int year = Integer.parseInt(fecha.substring(6, 10));
		int month = Integer.parseInt(fecha.substring(3, 5)) - 1;
		int day = Integer.parseInt(fecha.substring(0, 2));
		Calendar calendar = new GregorianCalendar(year, month, day);
		String reference = "";
		Date date = calendar.getTime();
		String token = operacionesService.getTokenEMV();
		consultaTransaccionVO = operacionesService.consultaTransacciones(date, reference);
		if (consultaTransaccionVO.getCdError() == null) {

			if (consultaTransaccionVO.getConsulta().size() > 0) {
				for (Consulta consulta : consultaTransaccionVO.getConsulta()) {
					logger.info("Transaccion con {Referencia] [{}], [Response][{}], [Operation Type][{}]",consulta.getNbReference(),consulta.getNbResponse(),consulta.getTypeOperation());

					if ((consulta.getNbResponse().toUpperCase().equals(TYPE_OPERATION_QA) || consulta.getNbResponse().toUpperCase().equals(TYPE_OPERATION_PROD)) && consulta.getTypeOperation().toUpperCase().equals( PAGO_APROBADO ) ) {
						listaPagos.add(consulta);
					}
				}

			}
		}

		return listaPagos;
	}
	
	public List<DatosTransaccionVO> obtenerTransaccionesCentroPagos(Date fecha,String referencia) {

		logger.info("Ejecutando metodo obtener Transacciones por referencia y fecha [fecha] [referencia]", fecha,referencia);

		List<DatosTransaccionVO> listaPagos = new ArrayList<DatosTransaccionVO>();
		DatosTransaccionVO transaccionVO=new DatosTransaccionVO();
		consultaTransaccionVO = operacionesService.consultaTransacciones(fecha, referencia);
		if (consultaTransaccionVO.getCdError() == null) {

			if (consultaTransaccionVO.getConsulta().size() > 0) {
				for (Consulta consulta : consultaTransaccionVO.getConsulta()) {
					transaccionVO=new DatosTransaccionVO();
					transaccionVO.setTranOrden(consulta.getOperation());
					transaccionVO.setBanNombre(consulta.getCcType());
					transaccionVO.setTranTarjeta(consulta.getCcNumber());
					transaccionVO.setTranNombre(consulta.getCcName());
					transaccionVO.setTraNomAfiliacion(consulta.getMerchant());
					transaccionVO.setTranBanAfil(consulta.getCcType());
					transaccionVO.setTranNumAutorizacion(consulta.getAuth());
					transaccionVO.setTranFecha(consulta.getDateRegister());
					transaccionVO.setTranLineaBanco(consulta.getVoucher());
					if ((consulta.getNbResponse().equalsIgnoreCase("Transaccion valida")|| consulta.getNbResponse().equalsIgnoreCase("Aprobada")
							|| consulta.getNbResponse().equalsIgnoreCase("approved") || consulta.getNbResponse().equalsIgnoreCase("Aprobado")
							||consulta.getNbResponse().equalsIgnoreCase(TYPE_OPERATION_QA))&& consulta.getTypeOperation().equalsIgnoreCase(PAGO_APROBADO)) {
						transaccionVO.setTranRespuesta("A");
						transaccionVO.setTransaccionAprobada(true);
					}else if(consulta.getTypeOperation().equalsIgnoreCase("CANCELACION")&&(consulta.getNbResponse().equalsIgnoreCase(TYPE_OPERATION_QA)||
							consulta.getNbResponse().equalsIgnoreCase(TYPE_OPERATION_PROD))){
						transaccionVO.setTranRespuesta("A");
						transaccionVO.setTransaccionCancelada(true);
					}else if(consulta.getNbResponse().equalsIgnoreCase("denied")){
						transaccionVO.setTransaccionDenegada(true);
					}
					transaccionVO.setTranReferencia(consulta.getNbReference());
					transaccionVO.setTranImporte(Double.parseDouble(consulta.getTxAmount()));
					transaccionVO.setTipoOperacion(consulta.getTypeOperation());
					listaPagos.add(transaccionVO);
				}
			}
		}else{
			transaccionVO.setNbError(consultaTransaccionVO.getNbError());
			transaccionVO.setCdError(consultaTransaccionVO.getCdError());
			listaPagos.add(transaccionVO);
		}

		return listaPagos;
	}
	
	public ConsultaTransaccionesVO cancelacionTransaccion(String importe,String numOperacion,String numAutorizacion){
		ConsultaTransaccionesVO cancelacionVO = new ConsultaTransaccionesVO();
		TransaccionVO transaccion=operacionesService.cancelacion(importe, numOperacion, numAutorizacion);
		 if(transaccion.getRespuesta().getResponse() != null && transaccion.getRespuesta().getResponse().equals("approved")){
			 cancelacionVO.setTranRespuesta("A");
			 cancelacionVO.setTipoOperacion("CANCELACIÓN");
			 cancelacionVO.setTranReferencia(transaccion.getRespuesta().getReference());
			 cancelacionVO.setNumOperacion(transaccion.getRespuesta().getFolioCpagos());
			 cancelacionVO.setTranNumAutoriza(transaccion.getRespuesta().getAuth());
			 cancelacionVO.setTranTarjeta(transaccion.getRespuesta().getCcNumber());
			 cancelacionVO.setTranNombre(transaccion.getRespuesta().getCcName());
			 cancelacionVO.setTranImporte(Double.parseDouble(transaccion.getRespuesta().getTxAmount())); 
			 cancelacionVO.setNomAfil(transaccion.getRespuesta().getDsTxMerchant());
			 cancelacionVO.setBanAfil(transaccion.getRespuesta().getCcType());
			 cancelacionVO.setTranFecha(transaccion.getRespuesta().getDate());
			 cancelacionVO.setVoucher(transaccion.getRespuesta().getVoucher());
		 }
		 
		 if(transaccion.getRespuesta().getResponse() != null && transaccion.getRespuesta().getResponse().equalsIgnoreCase("denied")){
			 cancelacionVO.setTranRespuesta("D"); 
			 cancelacionVO.setTipoOperacion("DENEGADA");
		 }
		 if(transaccion.getRespuesta().getResponse()!=null&&transaccion.getRespuesta().getResponse().equalsIgnoreCase("error")){
			 cancelacionVO.setTranRespuesta("E");
			 cancelacionVO.setTipoOperacion("ERRROR");
			 cancelacionVO.setCdError(transaccion.getRespuesta().getCdError());
			 cancelacionVO.setNbError(transaccion.getRespuesta().getNbError());
		 }
		 if(transaccion.getRespuesta().getResponse()==null){
			 cancelacionVO.setTranRespuesta("E");
			 cancelacionVO.setTipoOperacion("ERRROR");
			 cancelacionVO.setCdError("E412");
			 cancelacionVO.setNbError("Error de comunicación con el centro de pagos");
		 }
		
		return cancelacionVO;
	}
	
	public String getVoucherPago(String numOperacion){
		return operacionesService.getVoucher(numOperacion);
	}
	
	
	
}
