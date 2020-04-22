package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.RespuestaBusquedaDepositoVO;

@Mapper
public interface DepositoMyBatisDAO {
	
	String ABC_DEPOSITOS = "{CALL ABC_DEPOSITOS ("
			+"#{depositoId},"
			+"#{depositoCodigo},"
			+"#{depositoNombre},"
			+"#{zonaId},"
			+"#{depositoDireccion},"
			+"#{depositoColonia},"
			+"#{depositoTelefono},"
			+"#{estadoId},"
			+"#{delegacionId},"
			+"#{regionId},"
			+"#{depositoSuperficie},"
			+"#{depositoCapacidad},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	String GET_DEPOSITOS_POR_REGION = "SELECT "
			+ "D.DEP_COD AS DEPOSITOCODIGO, "
			+ "D.DEP_NOMBRE AS DEPOSITONOMBRE, "
			+ "D.DEP_ID AS DEPOSITOID, "
			+ "D.ZONA_ID AS ZONAID, "
			+ "D.DEL_ID AS DELEGACIONID, "
			+ "D.DEP_DIRECCION AS DEPOSITODIRECCION, "
			+ "D.DEP_COLONIA AS DEPOSITOCOLONIA, "
			+ "D.DEP_TELEFONO AS DEPOSITOTELEFONO, "
			+ "D.DEP_CAPACIDAD AS DEPOSITOCAPACIDAD, "
			+ "D.DEP_SUPERFICIE AS DEPOSITOSUPERFICIE, "
			+ "D.DEP_STATUS AS DEPOSITOSTATUS, "
			+ "D.EDO_ID AS ESTADOID, "
			+ "D.REG_ID AS REGIONID, "
			+ "Z.ZONA_NOMBRE AS ZONANOMBRE, "
			+ "DE.DEL_NOMBRE AS DELEGACIONNOMBRE "
			+ "FROM DEPOSITOS D, ZONAS Z, DELEGACIONES DE "
			+ "WHERE D.ZONA_ID = Z.ZONA_ID "
			+ "AND D.DEL_ID = DE.DEL_ID "
			+ "AND D.EDO_ID = DE.EDO_ID "
			+ "AND D.REG_ID = #{valor} "
			+ "order by D.ZONA_ID asc ";
	
	@Select(value = ABC_DEPOSITOS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudDeposito(CrudDepositoVO crudDepositoVO) throws PersistenceException;
	
	@Select(value = GET_DEPOSITOS_POR_REGION)
	public List<RespuestaBusquedaDepositoVO> buscarDepositosPorRegion(@Param("valor") String valor);
	
}
