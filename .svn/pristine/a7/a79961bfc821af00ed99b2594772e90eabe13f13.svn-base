package mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GarantiasInfraccionesDAO {


	
	String GET_GARANTIAS_INFRACCION_DEPOSITO = 
			"SELECT  	ICD.FN_DEPOSITO(#{infraccNum}) "+
			"FROM    	dual";
	
	@Select(value = GET_GARANTIAS_INFRACCION_DEPOSITO)
	String buscaDepositoProInfraccionGarantia(@Param("infraccNum") String infraccNum);
}
