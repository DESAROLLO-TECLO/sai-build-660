package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.ExpedienteDTO; 

public interface ExpedientesDAO extends BaseDao<ExpedienteDTO>{

//	public ExpedienteDTO eliminarExpediente(String infracNum, String tipo);
	
	public ExpedienteDTO getExpedientePorTipo(String infracNum, String tipo);
	
	public Long getIdArchivo();
	
	public String obtenerNumControl(String infracNum);
	
	public List<ExpedienteDTO> getTodoExpediente(String infracNum);
}
