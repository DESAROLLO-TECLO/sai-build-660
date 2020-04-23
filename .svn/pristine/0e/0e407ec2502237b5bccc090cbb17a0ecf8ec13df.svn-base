package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionesCanceladasDTO;

public interface TransaccionesCanceladasDAO extends BaseDao<TransaccionesCanceladasDTO>{
	
	
	public List<TransaccionesCanceladasDTO> consultaAllTransaccionesCanceladas();
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasInfraccion(String numInfraccion);
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasNumOperacion(String numOperacion);
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasReferencia(String numReferencia);
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasFechaInico(String fechaInicio);
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasFechasFin(String fechaFin);
	
	public List<TransaccionesCanceladasDTO> consultaTransaccionesCanceladasFechas(String fechaInicio,String fechaFin);
	
	public TransaccionesCanceladasDTO consultaTransaccionCanceladaByTranId(Long tranId,String numOperacion);
	
	public void guardarTransaccionCancelada(TransaccionesCanceladasDTO transacionCancelada);
	
}
