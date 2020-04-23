package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaVwSinProcesarDTO;

public interface GarantiasVwSinProcesarDAO extends BaseDao<GarantiaVwSinProcesarDTO> {
	
    List<GarantiaVwSinProcesarDTO> getGarantiasSinProcesar(String placaOficial);
	
	List<GarantiaVwSinProcesarDTO> getGarantiasSinProcesar();
	
	List<GarantiaVwSinProcesarDTO> getGarantiasSinProcesarByOficial(String placaOficial); 

	GarantiaVwSinProcesarDTO getGarantiasSinProcesarByInfracNum(String infrac_num);
}
