package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.siidf.centrodepagos.mit.vo.TransaccionVO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.DetalleCargosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.DetallePagoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.PagoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.TransaccionesDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.TransaccionesValidacionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.PagoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionesValidacionDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias.GarantiaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones.InfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos.ConsultaPagoInfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos.PagoInfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.TipoInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ConsultaTransaccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosTransaccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.InfraccionDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionEntidadesPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionFormaPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionLugarPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumInfraccionTipoPago;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumTransaccionPago;
import mx.com.teclo.siidf.centrodepagos.mit.vo.Respuesta;
import mx.com.teclo.siidf.centrodepagos.mit.vo.Tarjeta;

@Service("transaccionesServices")
public class ConsultaTransaccionesServiceImpl implements ConsultaTransaccionesService{
	
	private static final Integer ACTUALIZADO=1;
	private static final Integer NO_ACTUALIZADO=0;
	private static final Integer MONTO_IGUAL=1;
	private static final Integer MONTO_DES_IGUAL=0;
	private static final Integer ID_PROCESO_GARANTIA_PAGADA=2;

	@Autowired
	private TransaccionesDAO transacciones;
	
	@Autowired
	private TransaccionesValidacionDAO transaccionValidacion;
	
	@Autowired
	private PagoDAO pagos;
	
	@Autowired
	private DetallePagoDAO detallePagoDAO;
	
	@Autowired 
	private DetalleCargosDAO detalleCargosDAO;
	
	@Autowired
	@Qualifier("consultaCentroPagosService")
	private ConsultaCentroPagosService consultaCentroPagosServices;
	
	@Autowired
	private CajaDAO cajaDAO;
	
	@Autowired
	private PagoInfraccionMyBatisDAO pagoInfraccionMyBatisDAO;
	
	@Autowired
	private InfraccionMyBatisDAO infraccionDAO;
	
	@Autowired
	private ConsultaPagoInfraccionMyBatisDAO ConsultaPagoInfraccionDAO;
	
	@Autowired
	@Qualifier("pagoTarjeta")
	private PagoService pagoTarjeta;
	
	@Autowired
	private CancelacionTransaccionService cancelacionTransaccionesService;
	
	@Autowired
	private GarantiaMyBatisDAO garantiaDAO;
	
	@Transactional
	public List<ConsultaTransaccionesVO> consultaTransacciones(Long empId){
		
		CajaDTO cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.buscarCajaEmpleado(empId);
		
		return transacciones.getAllTransacciones(cajaDTO.getCajaId().toString());
	}
	
	
	public List<ConsultaTransaccionesVO> consultaTransaccionesByNumInfraccion(String valorBusqueda,Long empId){
		List<DatosTransaccionVO> listaByNumInfraccion=new ArrayList<>();
		List<ConsultaTransaccionesVO> listprocesadaByNumInfraccion=new ArrayList<>();
		CajaDTO cajaDTO = new CajaDTO();
		PagoDTO pagoDTO = new PagoDTO();
		cajaDTO = cajaDAO.buscarCajaEmpleado(empId);
		listaByNumInfraccion=transacciones.getTransaccionNumInfrac(valorBusqueda,cajaDTO.getCajaId().toString());
		for(DatosTransaccionVO datsoTransaccionVO:listaByNumInfraccion){
			pagoDTO=pagos.getPagoByNumInfraccionAndTranId(datsoTransaccionVO.getInfracNum(),datsoTransaccionVO.getTranID());
			listprocesadaByNumInfraccion.add(validarBusquedaEspecifica( datsoTransaccionVO,empId,cajaDTO,pagoDTO));
		}
		return listprocesadaByNumInfraccion;
	}
	
	
	public List<ConsultaTransaccionesVO> consultaTransaccionesByNumControl(String valorBusqueda,Long empId){
		List<DatosTransaccionVO> listaByNumReferencia=new ArrayList<>();
		List<ConsultaTransaccionesVO> listprocesadaByNumReferencia=new ArrayList<>();
		CajaDTO cajaDTO = new CajaDTO();
		PagoDTO pagoDTO = new PagoDTO();
		cajaDTO = cajaDAO.buscarCajaEmpleado(empId);
		listaByNumReferencia=transacciones.getTransaccionesNumReferencia(valorBusqueda,cajaDTO.getCajaId().toString());
		for(DatosTransaccionVO datsoTransaccionVO:listaByNumReferencia){
			pagoDTO= pagos.getPagoByNumControlAndTranId(valorBusqueda,datsoTransaccionVO.getTranID());
			listprocesadaByNumReferencia.add(validarBusquedaEspecifica( datsoTransaccionVO,empId,cajaDTO,pagoDTO));
		}
		return listprocesadaByNumReferencia;
	}
	
	
	public List<ConsultaTransaccionesVO> consultaTransaccionesCentroPagos(String tipoBusqueda,String valorBusqueda,String fechaTransaccion,Long empId){
		
		RutinasTiempoImpl rutinasTiempo=new RutinasTiempoImpl();
		TipoInfraccionVO  datosInfra=new TipoInfraccionVO();
		CajaDTO cajaDTO =cajaDAO.buscarCajaEmpleado(empId);
		List<ConsultaTransaccionesVO> lisTransaciones=new ArrayList<>();
		List<DatosTransaccionVO> listaTransLocal=new ArrayList<>();
		DatosTransaccionVO centroPagos=new DatosTransaccionVO();
		TransaccionesValidacionDTO banderasValidadas=new TransaccionesValidacionDTO();
		PagoDTO pagoDTO = new PagoDTO();
		PagoVO pagoVO = new PagoVO();
		boolean noExisteTransaccion=false;
		 DatosPagoVO datosPagoVO = new DatosPagoVO();
		 Long tranId=0L;
		 Long depId=cajaDTO.getDepEmp().getDepId().getDepId();
		if(tipoBusqueda.equals("INFRACCION")){
			datosInfra=infraccionDAO.getInfraccionAndTipoInfraccion(valorBusqueda);
			listaTransLocal=transacciones.getTransaccionNumInfrac(valorBusqueda,cajaDTO.getCajaId().toString());
		}else{
			datosInfra=infraccionDAO.getInfraccionAndTipoInfraccionNCI(valorBusqueda);
			listaTransLocal=transacciones.getTransaccionesNumReferencia(valorBusqueda,cajaDTO.getCajaId().toString());
		}
		
		if(listaTransLocal.size()>0){
			for(DatosTransaccionVO tarnLocal:listaTransLocal){
				lisTransaciones.add(getTransaccionSinComparar(tarnLocal));
			}
		}else{
			Date fecha=rutinasTiempo.convertirStringDate(fechaTransaccion);
			List<DatosTransaccionVO> lisCentroPagos=consultaCentroPagosServices.obtenerTransaccionesCentroPagos(fecha, datosInfra.getNumControl());
			 centroPagos=getElementoMayor(lisCentroPagos,"CENTROPAGOS");
			 centroPagos.setInfracNum(datosInfra.getNumInfraccion());
			 centroPagos.setCajaID(cajaDTO.getCajaId());
			 
			 if(centroPagos.getCdError() == null && centroPagos.getNbError() == null && verificaInfraccionDeposito(datosInfra.getPlaca(),depId)){
				 centroPagos.setTipoOperacion(centroPagos.getTipoOperacion().equals("CANCELACION") ? "CANCELACIÓN": centroPagos.getTipoOperacion());
				 
				 if(centroPagos.getTranRespuesta().equals("A") && centroPagos.getTipoOperacion().equals("VENTA")){
					 pagoDTO=getPagoByNumInfraccion(datosInfra.getNumInfraccion());
					 if(pagoDTO != null){//existe el pago se relaciona el tran id con la nueva transaccion
						 TransaccionDTO transaccionDTO=new TransaccionDTO();
						 centroPagos.setTranID(pagoDTO.getTranId());
						 transaccionDTO=getTransaccionDTO(centroPagos, cajaDTO);
						 transaccionDTO.getTransaccionId().setCajaDTO(cajaDTO);
						 tranId=guardarTransaccion(transaccionDTO);
						 comparaMontosPagados(centroPagos,cajaDTO,banderasValidadas,pagoDTO,empId.toString(),noExisteTransaccion);
					 }else{//no existe el pago
						 datosPagoVO.setExistePago(false);
						 datosPagoVO.setExisteTransaccion(false);
						 getDatosParaPago(datosPagoVO,centroPagos);
						 pagoVO= pagarTarjeta(datosPagoVO);
						 tranId=Long.parseLong(pagoVO.getTransaccion());
						 banderasValidadas.setMontoPagoIgual(MONTO_DES_IGUAL);
						 banderasValidadas.setMontoDetPagoIgual(MONTO_DES_IGUAL);
						 banderasValidadas.setMontoDetCargosIgual(MONTO_DES_IGUAL);
					 }
					 centroPagos.setTranID(tranId);
					 
					 banderasValidadas.setTranImporteActualizado(NO_ACTUALIZADO);
					 banderasValidadas.setTranNombreActualizado(NO_ACTUALIZADO);
					 banderasValidadas.setNumAutorizacionActualizado(NO_ACTUALIZADO);
					 banderasValidadas.setTranTarjetaActualizado(NO_ACTUALIZADO);
					 banderasValidadas.setTranRespuestaActualizado(NO_ACTUALIZADO);
					 banderasValidadas.setTranLineaBancoActualizado(NO_ACTUALIZADO);
					 banderasValidadas.setTranOrdenActualizado(NO_ACTUALIZADO);
					 banderasValidadas.setFechaValidacion(new Date());
					 banderasValidadas.setActivo("A");
					 banderasValidadas.setEmpId(empId);
					 banderasValidadas.setCajaId(cajaDTO.getCajaId());
					 banderasValidadas.setTranId(tranId);
					 banderasValidadas.setTipoOperacion(centroPagos.getTipoOperacion());
					 registraValidacion(banderasValidadas);
					 centroPagos.setFechaValidacion(getFechaValidacion(tranId,centroPagos.getTipoOperacion(),centroPagos.getCajaID()));
				 }
				 lisTransaciones.add(getTransaccionSinComparar(centroPagos));
			 }
		}
		
		 return lisTransaciones;
	}
	
	 @Transactional
	 public List<ConsultaTransaccionesVO> consultaTransaccionesFechas(String fechaInicio,String fechaFin,String tipoBusqueda,Long empId){
		 
		 List<ConsultaTransaccionesVO> listaTransacciones = new ArrayList<ConsultaTransaccionesVO>();
		 CajaDTO cajaDTO = new CajaDTO();
		 cajaDTO = cajaDAO.buscarCajaEmpleado(empId);
		 switch(tipoBusqueda){
		 case "FECHAINICIO":
			 listaTransacciones=transacciones.getTransaccionesFechaInicio(fechaInicio, cajaDTO.getCajaId().toString());
			 break;
		 case "FECHAFIN":
			 listaTransacciones=transacciones.getTransaccionesFechaFin(fechaFin, cajaDTO.getCajaId().toString());
			 break;
		 case "FECHAS":
			 listaTransacciones=transacciones.getTransaccionesFechas(fechaInicio, fechaFin, cajaDTO.getCajaId().toString());
			 break;
		 }
		 return listaTransacciones;
	 }
	 
	 
	 public ConsultaTransaccionesVO validacionManual(ConsultaTransaccionesVO transaccionVO,Long empId){
		 PagoDTO pagoDTO = new PagoDTO();
		 pagoDTO=pagos.getPagoByNumInfraccionAndTranId(transaccionVO.getInfracNum(),transaccionVO.getTranId());
		 CajaDTO cajaDTO = new CajaDTO();
		 cajaDTO = cajaDAO.buscarCajaEmpleado(empId);
		 DatosTransaccionVO datosTransaccionVO=converterVOtoDatosTransaccionVO(transaccionVO,cajaDTO.getCajaId());
		 return validarBusquedaEspecifica(datosTransaccionVO,empId,cajaDTO,pagoDTO);
	 }
	 
	 public PagoDTO getPagoByNumInfraccion(String numInfrac){
		return pagos.getPagoByNumInfraccion(numInfrac);
	 }
	 
	 
	 public TransaccionDTO getTransaccionByTranId(Long tranId,CajaDTO cajaDTO){
		return  transacciones.getTransaccionByTranID(tranId,cajaDTO);
	 }
	 
	 
	 public void  updateTransaccion(TransaccionDTO transaccionDTO){
		 transacciones.updateTransaccionDTO(transaccionDTO);
	 }
	 
	 
	 public void registraValidacion(TransaccionesValidacionDTO banderasValidacion){
		 transaccionValidacion.saveTransaccionValidacionDTO(banderasValidacion);
	 }
	 
	 
	 public String getFechaValidacion(Long tranId,String tipoOperacion,Long cajaId){
		 return transaccionValidacion.fechaValidacion(tranId,tipoOperacion,cajaId);
	 }
	 
	 @Transactional
	 public Long guardarTransaccion(TransaccionDTO transaccionDTO){
		 
		 Long tranId=(Long)transacciones.save(transaccionDTO);
		 transacciones.flush();
		 return tranId;
	 }
	 
	 public boolean verificaInfraccionDeposito(String placa,Long depId){
		 boolean respuesta=false;
		 List<InfraccionDepositoVO> infraccionEnDeposito= ConsultaPagoInfraccionDAO.buscarInfraccionIngresada(placa);
		 
			 for(InfraccionDepositoVO infrc:infraccionEnDeposito){
				 if(infrc.getDepId().equals(depId)){
					 respuesta=true;
				 }
			 }
			return respuesta;
	 }
	 
	 public ConsultaTransaccionesVO validarBusquedaEspecifica(DatosTransaccionVO transaccionLocal,Long empId,CajaDTO cajaDTO, PagoDTO pagoDTO){
		 String numReferencia="";
		 RutinasTiempoImpl rutinasTiempo=new RutinasTiempoImpl();
		 ConsultaTransaccionesVO tarnsaccionProcesada= new ConsultaTransaccionesVO();
		 List<DatosTransaccionVO> listaCentroPagos=new ArrayList<>();
		 DatosTransaccionVO centroPagosElementEspecifico=new DatosTransaccionVO();
		 
				 numReferencia=transaccionLocal.getTranReferencia();
				 Date fecha=rutinasTiempo.convertirStringDate(transaccionLocal.getTranFecha());
				 
				 listaCentroPagos=consultaCentroPagosServices.obtenerTransaccionesCentroPagos(fecha,numReferencia);
				 centroPagosElementEspecifico=getElementoEspecifico(listaCentroPagos, transaccionLocal.getTranOrden(), transaccionLocal.getTranNumAutorizacion());
				
				 if(centroPagosElementEspecifico.getNbError() == null && centroPagosElementEspecifico.getCdError()==null){
					 
					 tarnsaccionProcesada=validarDatosLocalVSCentroPagos(empId,centroPagosElementEspecifico,transaccionLocal,cajaDTO,pagoDTO);
				 
					}else{
						transaccionLocal.setCdError(centroPagosElementEspecifico.getCdError());
						transaccionLocal.setNbError(centroPagosElementEspecifico.getNbError());
						tarnsaccionProcesada=getTransaccionSinComparar(transaccionLocal);
					}
		 
		 return tarnsaccionProcesada;
	 }
	 
	 public DatosTransaccionVO getElementoEspecifico(List<DatosTransaccionVO> lista,String numOperacion,String numAutoriza){
			
		 DatosTransaccionVO elementoEspecifico=new DatosTransaccionVO();
		 
			for(DatosTransaccionVO ele:lista){
				
				if(numOperacion==null && ele.getCdError()==null){
					if(numAutoriza.equals(ele.getTranNumAutorizacion())){
						elementoEspecifico=ele;
					}
				}else if(ele.getCdError()==null){
					if(numOperacion.equals(ele.getTranOrden())){
						elementoEspecifico=ele;
					}
				}else{
					elementoEspecifico=ele;
				}
			}
			
			return elementoEspecifico;
		}
	 
	 public DatosTransaccionVO getElementoMayor(List<DatosTransaccionVO> lista,String tipoElemento){//obtiene elemento con numOperacion mayor
			
		 DatosTransaccionVO elementoMayor=new DatosTransaccionVO();
			int elemento2=lista.size()-1;
			int indexMayor=0;
			int elemento1=0;
			boolean isErrorCentroPagos=false;
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
					if(ele.getCdError()==null && ele.getNbError()==null){
						
						if(Integer.parseInt(lista.get(elemento1).getTranOrden()) > Integer.parseInt(lista.get(elemento2).getTranOrden())){
							elemento2--;
							indexMayor=elemento1;
						}else if(Integer.parseInt(lista.get(elemento1).getTranOrden()) < Integer.parseInt(lista.get(elemento2).getTranOrden())){
							elemento1++;
							indexMayor=elemento2;
						}else if(lista.get(elemento1).getTranOrden().equals(lista.get(elemento2).getTranOrden())){
							indexMayor=elemento2;
						}
						
					}else{
						isErrorCentroPagos=true;
						elementoMayor=ele;
					}
				}
				
			}
			if(isErrorCentroPagos==false){
				elementoMayor=lista.get(indexMayor);
			}
			
			return elementoMayor;
		}
	 
	 public ConsultaTransaccionesVO getTransaccionSinComparar(DatosTransaccionVO transaccion){
		 
		 ConsultaTransaccionesVO trasaccionSinComparar=new ConsultaTransaccionesVO();
		 
			 trasaccionSinComparar.setBanNombre(transaccion.getBanNombre());
			 trasaccionSinComparar.setFechaValidacion(transaccion.getFechaValidacion());
			 trasaccionSinComparar.setInfracNum(transaccion.getInfracNum());
			 trasaccionSinComparar.setNumOperacion(transaccion.getTranOrden());
			 trasaccionSinComparar.setTranFecha(transaccion.getTranFecha());
			 trasaccionSinComparar.setTranId(transaccion.getTranID());
			 trasaccionSinComparar.setTranImporte(transaccion.getTranImporte());
			 trasaccionSinComparar.setTranNombre(transaccion.getTranNombre());
			 trasaccionSinComparar.setTranNumAutoriza(transaccion.getTranNumAutorizacion());
			 trasaccionSinComparar.setTranReferencia(transaccion.getTranReferencia());
			 trasaccionSinComparar.setTranRespuesta(transaccion.getTranRespuesta());
			 trasaccionSinComparar.setTranTarjeta(transaccion.getTranTarjeta());
			 trasaccionSinComparar.setVoucher(transaccion.getTranLineaBanco());
			 trasaccionSinComparar.setCdError(transaccion.getCdError());
			 trasaccionSinComparar.setNbError(transaccion.getNbError());
			 trasaccionSinComparar.setTipoOperacion(transaccion.getTipoOperacion());
			 
		 return trasaccionSinComparar;
	 }
	 
	 public ConsultaTransaccionesVO validarDatosLocalVSCentroPagos(Long empId,DatosTransaccionVO centrPagos,DatosTransaccionVO tranLocal,CajaDTO cajaDTO,PagoDTO pagoDTO){
		 
		
		 ConsultaTransaccionesVO tranActualizada= new ConsultaTransaccionesVO();
		 TransaccionesValidacionDTO banderasValidadas = new TransaccionesValidacionDTO();
		 boolean existeTransaccion=true;
		 banderasValidadas.setTranId(tranLocal.getTranID());
		 
		 
		 
		 if(centrPagos.getTipoOperacion().equalsIgnoreCase("CANCELACION") && centrPagos.getTranRespuesta().equalsIgnoreCase("A")){
			 tranActualizada=cancelacionTransaccionesService.cancelacionTransaccion(getTransaccionSinComparar(tranLocal), empId, cajaDTO.getCajaId());
			 tranActualizada.setTranRespuesta("C");
		 }
		 if(centrPagos.getTipoOperacion().equalsIgnoreCase("VENTA") && centrPagos.getTranRespuesta().equalsIgnoreCase("A")){
			 
			 banderasValidadas.setActivo("A");
			 banderasValidadas.setCajaId(cajaDTO.getCajaId());
			 banderasValidadas.setEmpId(empId);
			 banderasValidadas.setTranId(tranLocal.getTranID());
			 banderasValidadas.setTipoOperacion(centrPagos.getTipoOperacion());
			 centrPagos.setTranID(tranLocal.getTranID());
			 centrPagos.setInfracNum(tranLocal.getInfracNum());
			 
			
			 guardarPagoGarantia(empId,tranLocal.getInfracNum());
			 tranActualizada=actualizaTablaTransaccionesBD(empId,cajaDTO,centrPagos,tranLocal,banderasValidadas);
			 comparaMontosPagados(centrPagos,cajaDTO,banderasValidadas,pagoDTO,empId.toString(),existeTransaccion);
			 banderasValidadas.setFechaValidacion(new Date());
			 registraValidacion(banderasValidadas);
			 tranActualizada.setTipoOperacion(centrPagos.getTipoOperacion());
			 tranActualizada.setFechaValidacion(getFechaValidacion(tranLocal.getTranID(),"VENTA",cajaDTO.getCajaId()));
		 }
		 if(centrPagos.getTipoOperacion().equalsIgnoreCase("VENTA") && centrPagos.getTranRespuesta().equalsIgnoreCase("DENIED")){
			 tranActualizada=getTransaccionSinComparar(centrPagos);
			 tranActualizada.setTranRespuesta("D");
		 }
		 return tranActualizada;
	 }
	 
	 public void guardarPagoGarantia(Long empId,String infracNum){
		 List<String> garantiaId = garantiaDAO.obtenerGarantiaIdPorInfraccion(infracNum);
		 if(garantiaId.size()>0){//si esta infraccion tiene garantia se actualiza como pagada
			 List<String> estatusPagoGarantia=garantiaDAO.obtenerObservacionGarantiaProcesoEstatus(garantiaId.get(0),ID_PROCESO_GARANTIA_PAGADA);
			 garantiaDAO.guardarGarantiaPagada(3,empId,infracNum);//se actualiza la garantia como pagada
			 
			 if(estatusPagoGarantia.size()==0)//si no existe en la tabla estatus de garantias se registra
				 garantiaDAO.guardarProcesoEstatusGarantiaPagada(garantiaId.get(0),empId,"Garantia Pagada");
			 else
				 garantiaDAO.updateGarantiaProcesoEstatus(garantiaId.get(0), ID_PROCESO_GARANTIA_PAGADA, empId);
		 }
		 
	 }
	 
	 public void comparaMontosPagados(DatosTransaccionVO centrPagos,CajaDTO cajaDTO,TransaccionesValidacionDTO banderasValidadas,
			 PagoDTO pagoDTO,String empId,boolean existeTransaccion){
		 
		 PagoVO pagoVO = new PagoVO();
		 DatosPagoVO datosPagoVO = new DatosPagoVO();
		
		 if(pagoDTO==null){//no existe pago
			 pagoVO=consultarDetallePago(centrPagos.getTranReferencia(),cajaDTO.getCajaId());
			 Double totalPago=Double.parseDouble(pagoVO.getTotalPago());
			 datosPagoVO.setExistePago(false);
			 datosPagoVO.setExisteTransaccion(existeTransaccion);
				if(totalPago.equals(centrPagos.getTranImporte())){
					getDatosParaPago(datosPagoVO,centrPagos);
					pagarTarjeta(datosPagoVO);
				}else
					updateInfraccion(centrPagos.getInfracNum(), "S", empId);
				
				banderasValidadas.setMontoPagoIgual(MONTO_DES_IGUAL);
				banderasValidadas.setMontoDetPagoIgual(MONTO_DES_IGUAL);
				banderasValidadas.setMontoDetCargosIgual(MONTO_DES_IGUAL);
		}else {//si existe el pago se procede a comparar los importes
			
			banderasValidadas.setMontoPagoIgual(centrPagos.getTranImporte().doubleValue() == pagoDTO.getPagTotal().doubleValue() ? MONTO_IGUAL : MONTO_DES_IGUAL);
			banderasValidadas.setMontoDetPagoIgual(centrPagos.getTranImporte().doubleValue() == detallePagoDAO.getSumPagValorDetallePago(cajaDTO.getCajaId(),pagoDTO.getPagosId().getPagId().toString()).doubleValue() ? MONTO_IGUAL : MONTO_DES_IGUAL);
			banderasValidadas.setMontoDetCargosIgual(centrPagos.getTranImporte().doubleValue() == detalleCargosDAO.getSumPagValorDetalleCargos(cajaDTO.getCajaId(),pagoDTO.getPagosId().getPagId().toString()).doubleValue() ? MONTO_IGUAL : MONTO_DES_IGUAL);
			
		}
	 }
	 
	 public ConsultaTransaccionesVO actualizaTablaTransaccionesBD(Long empId,CajaDTO cajaDTO,DatosTransaccionVO centrPagos,DatosTransaccionVO tranLocal
			 ,TransaccionesValidacionDTO banderasValidadas){
		 ConsultaTransaccionesVO tranActualizada= new ConsultaTransaccionesVO();
		 TransaccionDTO transaccionDTO = new TransaccionDTO();
		 String numTarjeta="";

			 int longitudNumTarjeta=tranLocal.getTranTarjeta().length();
			 if (longitudNumTarjeta != 4) {//se obtienen los ultimos 4 digitos de la tarjeta para compraralos con los obtenidos de centro de pagos
					numTarjeta = tranLocal.getTranTarjeta().substring(longitudNumTarjeta - 4, longitudNumTarjeta);
			 }else{
				 numTarjeta=tranLocal.getTranTarjeta();
			 }
			 transaccionDTO=getTransaccionByTranId(tranLocal.getTranID(),cajaDTO);
			 
				if (tranLocal.getTranImporte().doubleValue() == centrPagos.getTranImporte().doubleValue()){
					banderasValidadas.setTranImporteActualizado(NO_ACTUALIZADO);
				} else {
					if(tranLocal.getTranImporte().doubleValue() > centrPagos.getTranImporte().doubleValue()){
						banderasValidadas.setTranImporteActualizado(NO_ACTUALIZADO);
					}else{
						banderasValidadas.setTranImporteActualizado(ACTUALIZADO);
						transaccionDTO.setTranImporte(centrPagos.getTranImporte().longValue());
					}
				}
				if (tranLocal.getTranNombre() != null && tranLocal.getTranNombre().equalsIgnoreCase(centrPagos.getTranNombre())) {
					banderasValidadas.setTranNombreActualizado(NO_ACTUALIZADO);
				} else {
					banderasValidadas.setTranNombreActualizado(ACTUALIZADO);
					transaccionDTO.setTranNombre(centrPagos.getTranNombre());
				}
				if (tranLocal.getTranNumAutorizacion() != null && tranLocal.getTranNumAutorizacion().equals(centrPagos.getTranNumAutorizacion())) {
					banderasValidadas.setNumAutorizacionActualizado(NO_ACTUALIZADO);
				} else {
					banderasValidadas.setNumAutorizacionActualizado(ACTUALIZADO);
					transaccionDTO.setTranNumAutoriza(centrPagos.getTranNumAutorizacion());
				}
				if (numTarjeta != null  && numTarjeta.equalsIgnoreCase(centrPagos.getTranTarjeta())) {//error
					banderasValidadas.setTranTarjetaActualizado(NO_ACTUALIZADO);
				} else {
					banderasValidadas.setTranTarjetaActualizado(ACTUALIZADO);
					transaccionDTO.setTranTarjeta(centrPagos.getTranTarjeta());
				}
				if (tranLocal.getTranOrden() != null && tranLocal.getTranOrden().equals(centrPagos.getTranOrden())) {
					banderasValidadas.setTranOrdenActualizado(NO_ACTUALIZADO);
				} else {
					banderasValidadas.setTranOrdenActualizado(ACTUALIZADO);
					transaccionDTO.setTranOrden(centrPagos.getTranOrden());
				}
				if (tranLocal.getTranRespuesta() != null && tranLocal.getTranRespuesta().equalsIgnoreCase(centrPagos.getTranRespuesta())) {
					banderasValidadas.setTranRespuestaActualizado(NO_ACTUALIZADO);
				} else {
					banderasValidadas.setTranRespuestaActualizado(ACTUALIZADO);
					transaccionDTO.setTranRespuesta(centrPagos.getTranRespuesta());
				}
				banderasValidadas.setTranLineaBancoActualizado(NO_ACTUALIZADO);
				updateTransaccion(transaccionDTO);//actualizamos en base de datos

				 tranActualizada.setBanNombre(tranLocal.getBanNombre());
				 tranActualizada.setInfracNum(tranLocal.getInfracNum());
				 tranActualizada.setTranId(tranLocal.getTranID());
				 tranActualizada.setTranReferencia(tranLocal.getTranReferencia());
				 tranActualizada.setTranFecha(tranLocal.getTranFecha());
				 tranActualizada.setTranImporte(centrPagos.getTranImporte().doubleValue() == tranLocal.getTranImporte().doubleValue() ? tranLocal.getTranImporte() : centrPagos.getTranImporte());
				 tranActualizada.setTranNombre(centrPagos.getTranNombre().equals(tranLocal.getTranNombre()) ? tranLocal.getTranNombre() : centrPagos.getTranNombre());
				 tranActualizada.setTranNumAutoriza(centrPagos.getTranNumAutorizacion().equals(tranLocal.getTranNumAutorizacion()) ? tranLocal.getTranNumAutorizacion() : centrPagos.getTranNumAutorizacion());
				 tranActualizada.setTranTarjeta(centrPagos.getTranTarjeta().equals(numTarjeta) ? tranLocal.getTranTarjeta() : centrPagos.getTranTarjeta());
				 tranActualizada.setVoucher(centrPagos.getTranLineaBanco().equals(tranLocal.getTranLineaBanco()) ? tranLocal.getTranLineaBanco() : centrPagos.getTranLineaBanco());
				 tranActualizada.setNumOperacion(centrPagos.getTranOrden().equals(tranLocal.getTranOrden()) ? tranLocal.getTranOrden() : centrPagos.getTranOrden());
				 tranActualizada.setTranRespuesta(centrPagos.getTranRespuesta().equals(tranLocal.getTranRespuesta()) ? tranLocal.getTranRespuesta() : centrPagos.getTranRespuesta());
		
		 return tranActualizada;
	 }
	 
     
	 
	 public PagoVO consultarDetallePago(String nci, Long cajaId){
			return pagoInfraccionMyBatisDAO.consultarDetallePago(nci, cajaId);
	 }
	 
	 public void getDatosParaPago(DatosPagoVO datosPagoVO,DatosTransaccionVO datosTransaccion){
		 TransaccionVO transaccionVO = new TransaccionVO();
		 Respuesta  respuesta = new Respuesta();
		 Tarjeta tarjeta = new Tarjeta();
		 datosPagoVO.setImporte(datosTransaccion.getTranImporte().toString());
		 datosPagoVO.setNci(datosTransaccion.getTranReferencia());
		 datosPagoVO.setInfracNum(datosTransaccion.getInfracNum());
		 datosPagoVO.setFormaPago(EnumInfraccionFormaPago.TARJETA_DEBITO.getFormaPago().toString());
		 datosPagoVO.setTipoPago(EnumInfraccionTipoPago.CON_INFRACCION.getTipoPago().toString());
		 datosPagoVO.setEntidadPago(EnumInfraccionEntidadesPago.TARDEB.getEntidadPago().toString());
		 datosPagoVO.setLugarPago(EnumInfraccionLugarPago.DEPOSITO.getLugarPago().toString());
		 datosPagoVO.setFormaPagoDocumentos(EnumInfraccionLugarPago.DEPOSITO.getLugarPago().toString());
		 respuesta.setAuth(datosTransaccion.getTranNumAutorizacion());
		 respuesta.setCcType(datosTransaccion.getTranBanAfil());
		 respuesta.setResponse(EnumTransaccionPago.TRANSACCION_APROBADA.getTransaccion());
		 respuesta.setFolioCpagos(datosTransaccion.getTranOrden());
		 respuesta.setDate(datosTransaccion.getTranFecha());
		 respuesta.setReference(datosTransaccion.getTranReferencia());
		 respuesta.setVoucher(datosTransaccion.getTranLineaBanco());
		 respuesta.setDsTxMerchant(datosTransaccion.getTranBanAfil());
		 transaccionVO.setRespuesta(respuesta);
		 tarjeta.setCcName(datosTransaccion.getTranNombre());
		 tarjeta.setCcNumber(datosTransaccion.getTranTarjeta());
		 tarjeta.setExpirationMonth("");
		 tarjeta.setExpirationYear("");
		 transaccionVO.setTarjeta(tarjeta);
		 datosPagoVO.setTransaccionVO(transaccionVO);
		 datosPagoVO.setTranId(datosTransaccion.getTranID()==null ? "0" : datosTransaccion.getTranID().toString());

	 }
	 
	 public PagoVO pagarTarjeta(DatosPagoVO datosPagoVO){
		 return pagoTarjeta.pagar(datosPagoVO);
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
	 
	 
	 public DatosTransaccionVO converterVOtoDatosTransaccionVO(ConsultaTransaccionesVO transaccionesVO,Long cajaId){
		 
		 DatosTransaccionVO datosVO = new DatosTransaccionVO();
		 
		 datosVO.setTranID(transaccionesVO.getTranId());
		 datosVO.setTranOrden(transaccionesVO.getNumOperacion());
		 datosVO.setTranReferencia(transaccionesVO.getTranReferencia());
		 datosVO.setInfracNum(transaccionesVO.getInfracNum());
		 datosVO.setTranImporte(transaccionesVO.getTranImporte());
		 datosVO.setBanNombre(transaccionesVO.getBanNombre());
		 datosVO.setTranTarjeta(transaccionesVO.getTranTarjeta());
		 datosVO.setTranNombre(transaccionesVO.getTranNombre());
		 datosVO.setTranRespuesta(transaccionesVO.getTranRespuesta());
		 datosVO.setTranNumAutorizacion(transaccionesVO.getTranNumAutoriza());
		 datosVO.setTranFecha(transaccionesVO.getTranFecha());
		 datosVO.setFechaValidacion(transaccionesVO.getFechaValidacion());
		 datosVO.setTranLineaBanco(transaccionesVO.getVoucher());
		 datosVO.setCajaID(cajaId);
		 datosVO.setTipoOperacion(transaccionesVO.getTipoOperacion());
		 return datosVO;
	 }
	 
	 public  TransaccionDTO getTransaccionDTO(DatosTransaccionVO centroPagos,CajaDTO cajaId){
		 TransaccionDTO transaccion = new TransaccionDTO();
		 transaccion.getTransaccionId().setTranId(centroPagos.getTranID());
		 transaccion.setBanId(99L);
		 transaccion.setBanNombre(centroPagos.getBanNombre());
		 transaccion.getTransaccionId().setCajaDTO(cajaId);
		 transaccion.setInfracNum(centroPagos.getInfracNum());
		 transaccion.setTranBanAfil(centroPagos.getBanNombre());
		 transaccion.setTranFech_mod(new Date());
		 transaccion.setTranFecha(getFechaHoras(centroPagos.getTranFecha()));
		 transaccion.setTranImporte(centroPagos.getTranImporte().longValue());
		 transaccion.setTranLineaBanco(centroPagos.getTranLineaBanco());
		 transaccion.setTranNomAfil(centroPagos.getTraNomAfiliacion());
		 transaccion.setTranNombre(centroPagos.getTranNombre());
		 transaccion.setTranNumAutoriza(centroPagos.getTranNumAutorizacion());
		 transaccion.setTranOrden(centroPagos.getTranOrden());
		 transaccion.setTranReferencia(centroPagos.getTranReferencia());
		 transaccion.setTranRespuesta(centroPagos.getTranRespuesta());
		 transaccion.setTranStatus("A");
		 transaccion.setTranTarjeta(centroPagos.getTranTarjeta());
		 return transaccion;
	 }
	 
	 public Date getFechaHoras(String fecha){
		 Date fechaTransaccion=new Date();
		 try{
			 DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				 fechaTransaccion = fechaHora.parse(fecha); 
		 }catch(Exception e){
			 
		 }
		 return fechaTransaccion;
	 }
	 
	 ////consulta de transacciones PARA CANCELACIÓN
	 
	 @Transactional
	 @Override
	 public List<ConsultaTransaccionesVO> consultaAllTransaccionesCancelacion(){
		 
		 return getListaConsultaTransaccionesVO(transacciones.getTransaccionALLForCancelacion());
	 }

	 
	@Override
	public List<ConsultaTransaccionesVO> consultaTransaccionesByNumOperacionCancelacion(String numOperacion) {
		
		return getListaConsultaTransaccionesVO(transacciones.getTransaccionForCancelacionByNumOpera(numOperacion));
	}

	
	@Override
	public List<ConsultaTransaccionesVO> consultaTransaccionesByNumInfraccionCancelacion(String numInfraccion) {
		return getListaConsultaTransaccionesVO(transacciones.getTransaccionForCancelacionByNumInfrac(numInfraccion));
	}

	 
	@Override
	public List<ConsultaTransaccionesVO> consultaTransaccionesByNumReferenciaCancelacion(String numReferencia) {
		return getListaConsultaTransaccionesVO(transacciones.getTransaccionForCancelacionByNumRefe(numReferencia));
	}
	 
	public List<ConsultaTransaccionesVO> getListaConsultaTransaccionesVO(List<TransaccionDTO> listDTO){
		List<ConsultaTransaccionesVO> liataCoversion= new ArrayList<>();
		ConsultaTransaccionesVO consultaVO=null;
		PagoDTO pagosDTO= new PagoDTO();
		for(TransaccionDTO  transaccionDTO : listDTO){
			pagosDTO= getPagoByNumInfraccion(transaccionDTO.getInfracNum());
			  consultaVO = new ConsultaTransaccionesVO();
			  consultaVO.setBanAfil(transaccionDTO.getTranBanAfil());
			  consultaVO.setBanNombre(transaccionDTO.getBanNombre());
			  consultaVO.setInfracNum(transaccionDTO.getInfracNum());
			  consultaVO.setNomAfil(transaccionDTO.getTranNomAfil());
			  consultaVO.setNumOperacion(transaccionDTO.getTranOrden());
			  consultaVO.setTranFecha(transaccionDTO.getTranFecha().toString());
			  consultaVO.setTranId(transaccionDTO.getTransaccionId().getTranId());
			  consultaVO.setTranImporte(transaccionDTO.getTranImporte().doubleValue());
			  consultaVO.setTranNombre(transaccionDTO.getTranNombre());
			  consultaVO.setTranNumAutoriza(transaccionDTO.getTranNumAutoriza());
			  consultaVO.setTranReferencia(transaccionDTO.getTranReferencia());
			  consultaVO.setTranRespuesta(transaccionDTO.getTranRespuesta());
			  consultaVO.setTranTarjeta(transaccionDTO.getTranTarjeta());
			  consultaVO.setCajaId(transaccionDTO.getTransaccionId().getCajaDTO().getCajaId());
			  consultaVO.setTienePago(pagosDTO != null ? true : false);
			  liataCoversion.add(consultaVO);
			  
		}
			
			return liataCoversion;
	}
}
