package mx.com.teclo.saicdmx.persistencia.dao.ingresosMovDao;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresoMov.IngresoMovDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos.IngresosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.salidas.SalidasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarSalidaVO;


public interface ingresoMovDAO   extends BaseDao<IngresoMovDTO>{

	boolean guardarNewDato(SalidasDTO salidaDTO, String string,  Long idSalidaImg);



}
