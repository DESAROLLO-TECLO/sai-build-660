package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDTO;

public interface GarantiasMasivaDAO extends BaseDao<GarantiaDTO>{
	
	public List<GarantiaDTO> getGarantiasMasivaSinProcesar(Long empId);

}
