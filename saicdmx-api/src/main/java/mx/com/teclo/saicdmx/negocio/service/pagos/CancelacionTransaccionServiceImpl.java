package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.DetalleCargosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.DetallePagoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.PagoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.TransaccionesCanceladasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.TransaccionesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.TransaccionesValidacionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.DetalleCargosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.DetallePagosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.PagoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionesCanceladasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionesValidacionDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias.GarantiaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones.InfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.TipoInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ConsultaTransaccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosTransaccionVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

@Service("cancelacionTransaccionServices")
public class CancelacionTransaccionServiceImpl implements CancelacionTransaccionService{

	private static final String OPERACION_REALIZADA="20";
	private static final Integer DES_IGUAL=0;
	private static final String ACTIVO="A";
	private static final String CANCELADO="C";
	private static final Integer ID_PROCESO_GARANTIA_PAGADA=2;
	private static final Integer ID_PROCESO_FARANTIA_PAGO_TRAN_CANCELADA=5;
	
	@Autowired
	private  TransaccionesCanceladasDAO transaccionesCanceladas;
	
	@Autowired
//	@Qualifier("consultaCentroPagosService")
	private ConsultaCentroPagosService consultaCentroPagosServices;
	
	@Autowired
	private TransaccionesValidacionDAO transaccionesValidcionDAO;
	
	@Autowired
	private TransaccionesDAO transaccionesDAO;
	
	@Autowired
	private PagoDAO pagoDAO;
	
	@Autowired 
	private DetalleCargosDAO detCargoDAO;
	
	@Autowired 
	private DetallePagoDAO detPagoDAO;
	
	@Autowired
	private InfraccionMyBatisDAO infraccionDAO;
	
	private RutinasTiempoImpl rutinasTiempo= new RutinasTiempoImpl();
	
	@Autowired
	private CajaDAO cajasDTO;
	
	@Autowired
	private GarantiaMyBatisDAO garantiaDAO;
	
	public ConsultaTransaccionesVO cancelacionTransaccion(ConsultaTransaccionesVO transaccionCancelacion,Long empId,Long cajaId){
		TransaccionesValidacionDTO banderasValidacion=new TransaccionesValidacionDTO();
		
		ConsultaTransaccionesVO cancelacionVO=
				consultaCentroPagosServices.cancelacionTransaccion(transaccionCancelacion.getTranImporte().toString(), 
						transaccionCancelacion.getNumOperacion(), transaccionCancelacion.getTranNumAutoriza());
		
		cancelacionVO.setInfracNum(transaccionCancelacion.getInfracNum());
		cancelacionVO.setTranId(transaccionCancelacion.getTranId());
		banderasValidacion.setTranId(transaccionCancelacion.getTranId());
		banderasValidacion.setEmpId(empId);
		
		TransaccionesCanceladasDTO transaccionCancelacionDTO=getObjetcDTO(cancelacionVO);
		transaccionCancelacionDTO.setFechaCreacion(new Date());
		transaccionCancelacionDTO.setCreadoPor(empId);
		transaccionCancelacionDTO.setInfracNum(transaccionCancelacion.getInfracNum());
		transaccionCancelacionDTO.setTranId(transaccionCancelacion.getTranId());
		
		
		if(cancelacionVO.getCdError()==null && cancelacionVO.getNbError()==null){
			
			cacelacionPagoGarantia(transaccionCancelacion.getInfracNum(),empId);
			getBanderasValidadadas(banderasValidacion);
			cancelacionTransaccionAndPagos(transaccionCancelacion,transaccionCancelacionDTO,banderasValidacion,empId,cajaId);
			updateInfraccion(transaccionCancelacion.getInfracNum(), "N", empId.toString());
			
		}else{
			
			if(cancelacionVO.getCdError().equals(OPERACION_REALIZADA) && cancelacionVO.getNbError() != null){
				
				List<DatosTransaccionVO> listCentroPagos=
						consultaCentroPagosServices.obtenerTransaccionesCentroPagos(
								rutinasTiempo.convertirStringDate(transaccionCancelacion.getTranFecha()), 
								transaccionCancelacion.getTranReferencia());
				
				DatosTransaccionVO transaccionEspecifica=getElementoMayor(listCentroPagos, "centroPagos");
				
				getObjetcDTOofDatosCentroPagos(transaccionEspecifica,transaccionCancelacionDTO);//se convierte a DTO PARA GUARDAR EN BASE DE DATOS
				
				getBanderasValidadadas(banderasValidacion);
				cancelacionTransaccionAndPagos(transaccionCancelacion,transaccionCancelacionDTO,banderasValidacion,empId,cajaId);
				
				if(transaccionEspecifica.getTipoOperacion().equals("VENTA") && transaccionEspecifica.getTranReferencia().equals("A")){
					
				    updatePagoGarantia(empId,transaccionCancelacion.getInfracNum());
					updateInfraccion(transaccionCancelacion.getInfracNum(), "S", empId.toString());
					
				}else{
					updateInfraccion(transaccionCancelacion.getInfracNum(), "N", empId.toString());
					cacelacionPagoGarantia(transaccionCancelacion.getInfracNum(),empId);
				}
				
				DatosTransaccionVOTOConsultaTransaccionesVO(transaccionEspecifica,cancelacionVO);
			}
			
		}
		
		return cancelacionVO;
	}
	
	
	public void guardarCancelacion(TransaccionesCanceladasDTO transaccionCancelacionDTO){
		transaccionesCanceladas.guardarTransaccionCancelada(transaccionCancelacionDTO);
	}
	
	
	public void guardarValidacion(TransaccionesValidacionDTO banderasValidacion){
		transaccionesValidcionDAO.saveTransaccionValidacionDTO(banderasValidacion);
	}
	
	public void updatePagoGarantia(Long empId,String infracNum){
		List<String> garantiaId = garantiaDAO.obtenerGarantiaIdPorInfraccion(infracNum);
		if(garantiaId.size()>0){//si esta infraccion tiene garantia se actualiza como cancelada el pago
			List<String> estatusPagoGarantia=garantiaDAO.obtenerObservacionGarantiaProcesoEstatus(garantiaId.get(0),ID_PROCESO_GARANTIA_PAGADA);
			garantiaDAO.guardarGarantiaPagada(3,empId,infracNum);//se actualiza la garantia como pagada
			 
			 if(estatusPagoGarantia.size()==0)//si no existe en la tabla estatus de garantias se registra
				 garantiaDAO.guardarProcesoEstatusGarantiaPagada(garantiaId.get(0),empId,"Garantia Pagada");
			 else
				 garantiaDAO.updateGarantiaProcesoEstatus(garantiaId.get(0),ID_PROCESO_GARANTIA_PAGADA,empId);
		}
	}
	
	public void cacelacionPagoGarantia(String infracNum,Long empId){
		List<String> garantiaId = garantiaDAO.obtenerGarantiaIdPorInfraccion(infracNum);
		
		if(garantiaId.size()>0){//si esta infraccion tiene garantia se actualiza como cancelada el pago
			List<String> estatusCanceladaGarantia=garantiaDAO.obtenerObservacionGarantiaProcesoEstatus(garantiaId.get(0),ID_PROCESO_FARANTIA_PAGO_TRAN_CANCELADA);
			garantiaDAO.guardarGarantiaPagadaNoPagada(empId,infracNum);//se actualiza la garantia como pagada
			 
			 if(estatusCanceladaGarantia.size()==0)//si no existe el estatus en la tabla se registra sino se actualiza fecha y empId
				 garantiaDAO.guardarProcesoEstatusGarantiaNoPagada(garantiaId.get(0),empId,"Transaccion Cancelada");
			 else
				 garantiaDAO.updateGarantiaProcesoEstatus(garantiaId.get(0), ID_PROCESO_FARANTIA_PAGO_TRAN_CANCELADA,empId);
		}
		
	}
	
	public void cancelacionTransaccionAndPagos(ConsultaTransaccionesVO transaccionCancelacion,
			TransaccionesCanceladasDTO transaccionCancelacionDTO,TransaccionesValidacionDTO banderasValidacion,
			Long empId,Long cajaId){
		
		Date fechaActual = new Date();
		TransaccionDTO transaccionDTO = new TransaccionDTO();
		PagoDTO pagoDTO = new PagoDTO();
		List<DetallePagosDTO> listDetPagoDTO=new ArrayList<>();
		List<DetalleCargosDTO> listDetCargosDTO=new ArrayList<>();
		CajaDTO cajaDTO = new CajaDTO();
		cajaDTO=cajasDTO.buscarCajaPorId(cajaId);
		
		guardarCancelacion(transaccionCancelacionDTO);
		guardarValidacion(banderasValidacion);
		
		transaccionDTO=transaccionesDAO.getTransaccionByTranID(transaccionCancelacion.getTranId(),cajaDTO);
		transaccionDTO.setTranStatus(CANCELADO);
		transaccionDTO.setTranFech_mod(fechaActual);
		transaccionesDAO.updateTransaccionDTO(transaccionDTO);
			
		pagoDTO=pagoDAO.getPagoByNumInfraccionAndTranId(transaccionCancelacion.getInfracNum(), transaccionCancelacion.getTranId());
		
		if(pagoDTO != null){
			
			pagoDTO.setFechaCancelacion(fechaActual);
			pagoDTO.setPagFechMod(fechaActual);
			pagoDTO.setModificadoPor(empId);
			pagoDTO.setPagStatus(CANCELADO);
			pagoDAO.updatePagoDTO(pagoDTO);
			
			listDetPagoDTO=detPagoDAO.getDetallePagoDTO(pagoDTO.getPagosId().getPagId(), pagoDTO.getPagosId().getCajaId());
			for(DetallePagosDTO detPagosDTO: listDetPagoDTO){
				detPagosDTO.setDetPagStatus(CANCELADO);
				detPagosDTO.setModificadoPor(empId);
				detPagosDTO.setUltimaModificacion(new Date());
				detPagoDAO.updateDetallePago(detPagosDTO);
			}
			
			
			listDetCargosDTO=detCargoDAO.getDetalleCargoDTO(pagoDTO.getPagosId().getPagId(), pagoDTO.getPagosId().getCajaId());
			for(DetalleCargosDTO detCargosDTO: listDetCargosDTO){
				detCargosDTO.setDetPagoStatus(CANCELADO);
				detCargosDTO.setModificadoPor(empId);
				detCargosDTO.setUltimaModificacion(new Date());
				detCargoDAO.updateDetalleCargos(detCargosDTO);
			}
			
		}
		
	}
	
	public TransaccionesCanceladasDTO getObjetcDTO(ConsultaTransaccionesVO cancelacionVO){
		
		TransaccionesCanceladasDTO canceladaDTO= new TransaccionesCanceladasDTO();
		
		canceladaDTO.setTranId(cancelacionVO.getTranId());
		canceladaDTO.setBanAfil(cancelacionVO.getBanAfil());
		canceladaDTO.setTranRespuesta(cancelacionVO.getTranRespuesta());
		canceladaDTO.setInfracNum(cancelacionVO.getInfracNum());
		canceladaDTO.setNumOperacion(cancelacionVO.getNumOperacion());
		canceladaDTO.setTranFecha(rutinasTiempo.convertirStringDate(cancelacionVO.getTranFecha(),"dd/MM/yyyy HH:mm:ss"));
		canceladaDTO.setTranImporte(cancelacionVO.getTranImporte());
		canceladaDTO.setTranNombre(cancelacionVO.getTranNombre());
		canceladaDTO.setNumAutorizacion(cancelacionVO.getTranNumAutoriza());
		canceladaDTO.setTranReferencia(cancelacionVO.getTranReferencia());
		canceladaDTO.setTranRespuesta(cancelacionVO.getTranRespuesta());
		canceladaDTO.setTranTarjeta(cancelacionVO.getTranTarjeta());
		canceladaDTO.setTranVoucher(cancelacionVO.getVoucher());
		
		return canceladaDTO;
	}
	
	public void DatosTransaccionVOTOConsultaTransaccionesVO(DatosTransaccionVO datosTransaccionVO,ConsultaTransaccionesVO consultaTransaccionesVO){
		consultaTransaccionesVO.setBanAfil(datosTransaccionVO.getTranBanAfil());
		consultaTransaccionesVO.setBanNombre(datosTransaccionVO.getBanNombre());
		consultaTransaccionesVO.setNomAfil(datosTransaccionVO.getTraNomAfiliacion());
		consultaTransaccionesVO.setNumOperacion(datosTransaccionVO.getTranOrden());
		consultaTransaccionesVO.setTipoOperacion(datosTransaccionVO.getTipoOperacion());
		consultaTransaccionesVO.setTranFecha(datosTransaccionVO.getTranFecha());
		consultaTransaccionesVO.setTranImporte(datosTransaccionVO.getTranImporte());
		consultaTransaccionesVO.setTranNombre(datosTransaccionVO.getTranNombre());
		consultaTransaccionesVO.setTranNumAutoriza(datosTransaccionVO.getTranNumAutorizacion());
		consultaTransaccionesVO.setTranReferencia(datosTransaccionVO.getTranReferencia());
		consultaTransaccionesVO.setTranRespuesta(datosTransaccionVO.getTranRespuesta());
		consultaTransaccionesVO.setTranTarjeta(datosTransaccionVO.getTranTarjeta());
		consultaTransaccionesVO.setVoucher(datosTransaccionVO.getTranLineaBanco());
		
	}
	
	public void getObjetcDTOofDatosCentroPagos(DatosTransaccionVO cancelacionVO,TransaccionesCanceladasDTO canceladaDTO){
		
		canceladaDTO.setTranNomAfil(cancelacionVO.getTraNomAfiliacion());
		canceladaDTO.setBanAfil(cancelacionVO.getTranBanAfil());
		canceladaDTO.setTranRespuesta(cancelacionVO.getTranRespuesta());
		canceladaDTO.setNumOperacion(cancelacionVO.getTranOrden());
		canceladaDTO.setTranFecha(rutinasTiempo.convertirStringDate(cancelacionVO.getTranFecha(),"dd/MM/yyyy HH:mm:ss"));
		canceladaDTO.setTranImporte(cancelacionVO.getTranImporte());
		canceladaDTO.setTranNombre(cancelacionVO.getTranNombre());
		canceladaDTO.setNumAutorizacion(cancelacionVO.getTranNumAutorizacion());
		canceladaDTO.setTranReferencia(cancelacionVO.getTranReferencia());
		canceladaDTO.setTranRespuesta(cancelacionVO.getTranRespuesta());
		canceladaDTO.setTranTarjeta(cancelacionVO.getTranTarjeta());
		canceladaDTO.setTranVoucher(cancelacionVO.getTranLineaBanco());
	}
	
	
	
	public void getBanderasValidadadas(TransaccionesValidacionDTO banderasValidacion){
		banderasValidacion.setMontoDetCargosIgual(DES_IGUAL);
		banderasValidacion.setMontoDetPagoIgual(DES_IGUAL);
		banderasValidacion.setMontoPagoIgual(DES_IGUAL);
		banderasValidacion.setActivo(ACTIVO);
		banderasValidacion.setCajaId(0L);
		banderasValidacion.setTranImporteActualizado(DES_IGUAL);
		banderasValidacion.setTranLineaBancoActualizado(DES_IGUAL);
		banderasValidacion.setTranNombreActualizado(DES_IGUAL);
		banderasValidacion.setTranOrdenActualizado(DES_IGUAL);
		banderasValidacion.setTranRespuestaActualizado(DES_IGUAL);
		banderasValidacion.setNumAutorizacionActualizado(DES_IGUAL);
		banderasValidacion.setCajaId(0L);
		banderasValidacion.setTranTarjetaActualizado(DES_IGUAL);
		banderasValidacion.setTipoOperacion("CANCELACION");
		banderasValidacion.setFechaValidacion(new Date());
	}
	
	 public DatosTransaccionVO getElementoMayor(List<DatosTransaccionVO> lista,String tipoElemento){//obtiene elemento con numOperacion mayor
			
		 DatosTransaccionVO elementoMayor=new DatosTransaccionVO();
			int elemento2=lista.size()-1;
			int indexMayor=0;
			int elemento1=0;
			if(tipoElemento.equals("local")){
				
				for(DatosTransaccionVO ele:lista){
					if(lista.get(elemento1).getTranID().intValue() > lista.get(elemento2).getTranID().intValue()){
						elemento2--;
						indexMayor=elemento1;
					}else if(lista.get(elemento1).getTranID().intValue() < lista.get(elemento2).getTranID().intValue()){
						elemento1++;
						indexMayor=elemento2;
					}else if(lista.get(elemento1).getTranID().intValue() == lista.get(elemento2).getTranID().intValue()){
						indexMayor=elemento2;
					}
				}
				
			}else{
				
				for(DatosTransaccionVO ele:lista){
					if(ele.getCdError()==null){
						
						if(Integer.parseInt(lista.get(elemento1).getTranOrden()) > Integer.parseInt(lista.get(elemento2).getTranOrden())){
							elemento2--;
							indexMayor=elemento1;
						}else if(Integer.parseInt(lista.get(elemento1).getTranOrden()) < Integer.parseInt(lista.get(elemento2).getTranOrden())){
							elemento1++;
							indexMayor=elemento2;
						}else if(lista.get(elemento1).getTranOrden().equals(lista.get(elemento2).getTranOrden())){
							indexMayor=elemento2;
						}
						
					}
				}
				
			}
			elementoMayor=lista.get(indexMayor);
			return elementoMayor;
		}
	 
	 public void updateInfraccion(String infracNum,String InfracPagada,String empId){
		 String tabla="";
		 TipoInfraccionVO tipoInfraccion= infraccionDAO.getInfraccionAndTipoInfraccion(infracNum);
		 tabla=tipoInfraccion.getNombreTabla();
		 switch(tabla){
		 	case "INFRACCIONES":
		 		if(InfracPagada=="S")
		 			infraccionDAO.updateInfraccionesPagada(tipoInfraccion.getNumInfraccion(), empId);
		 		else
		 			infraccionDAO.updateInfraccionesNoPagada(tipoInfraccion.getNumInfraccion(), empId);
			 break;
		 	case "RADAR":
		 		if(InfracPagada=="S")
		 			infraccionDAO.updateInfraccionesRadarPagada(tipoInfraccion.getNumInfraccion(), empId);
		 		else
		 			infraccionDAO.updateInfraccioneRadarNoPagada(tipoInfraccion.getNumInfraccion(), empId);	
		 	break;
		 	case "DIGITALIZACION":
		 		if(InfracPagada=="S")
		 			infraccionDAO.updateInfraccionesDigitalizacionPagada(tipoInfraccion.getNumInfraccion(), empId);
		 		else
		 			infraccionDAO.updateInfraccionesDigitalizacionNoPagada(tipoInfraccion.getNumInfraccion(), empId);
		 	break;
		 }
	 }
	
}
