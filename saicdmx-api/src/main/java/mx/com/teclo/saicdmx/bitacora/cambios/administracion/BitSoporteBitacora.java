package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bitacora.SoporteBitacoraDTO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.EjecutaSoporteOperacionVO;

public interface BitSoporteBitacora extends BaseDao<SoporteBitacoraDTO>{

	public void persisteSoporteBitacora(EjecutaSoporteOperacionVO objeto, Long vEmpAutId, Long cambioId, Long componenteId, String fechaSoporte);
}
