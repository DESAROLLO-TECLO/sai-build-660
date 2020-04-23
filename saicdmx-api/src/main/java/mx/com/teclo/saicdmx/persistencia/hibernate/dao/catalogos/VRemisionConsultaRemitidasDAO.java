package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VRemisionConsultaRemitidasDTO;

public interface VRemisionConsultaRemitidasDAO extends BaseDao<VRemisionConsultaRemitidasDTO> {

	String obtenerInfoIngresoIngresoPorPlaca(String placa);

	String obtenerInfoIngresoIngresoPorInfraccion(String numeroInfraccion);

}
