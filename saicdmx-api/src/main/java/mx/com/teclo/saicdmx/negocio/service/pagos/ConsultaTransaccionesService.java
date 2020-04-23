package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.ConsultaTransaccionesVO;


public interface ConsultaTransaccionesService {
	
 public List<ConsultaTransaccionesVO> consultaTransacciones(Long empId);
  
 public List<ConsultaTransaccionesVO> consultaTransaccionesByNumInfraccion(String valorBusqueda,Long empId);
  
 public List<ConsultaTransaccionesVO> consultaTransaccionesByNumControl(String valorBusqueda,Long empId);
  
 public List<ConsultaTransaccionesVO> consultaTransaccionesCentroPagos(String tipoBusqueda,String valorBusqueda,String fechaTransaccion,Long empId);
  
 public List<ConsultaTransaccionesVO> consultaTransaccionesFechas(String fechaInicio,String FechaFin,String tipoBusqueda,Long empId);
  
 public  ConsultaTransaccionesVO validacionManual(ConsultaTransaccionesVO transaccionVO,Long empId);
 
 public List<ConsultaTransaccionesVO> consultaAllTransaccionesCancelacion();
 
 public List<ConsultaTransaccionesVO> consultaTransaccionesByNumOperacionCancelacion(String numOperacion);
 
 public List<ConsultaTransaccionesVO> consultaTransaccionesByNumInfraccionCancelacion(String numInfraccion);
 
 public List<ConsultaTransaccionesVO> consultaTransaccionesByNumReferenciaCancelacion(String numReferencia);
 
}
