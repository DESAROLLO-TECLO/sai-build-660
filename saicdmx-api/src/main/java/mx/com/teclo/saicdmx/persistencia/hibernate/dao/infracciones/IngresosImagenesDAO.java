package mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.IngresosImagenesDTO;

public interface IngresosImagenesDAO  extends BaseDao<IngresosImagenesDTO>{
	public IngresosImagenesDTO buscaFotoPorNombreArchivo(String nombre);
}
