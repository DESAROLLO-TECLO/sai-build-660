package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudSectorVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.RespuestaBusquedaSectoresVO;

@Mapper
public interface SectorMyBatisDAO {

	String ABC_SECTORES = "{CALL ABC_SECTORES ("
			+"#{sectorId},"
			+"#{sectorCodigo},"
			+"#{sectorNombre},"
			+"#{estadoId},"
			+"#{delegacionId},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	String GET_SECTORES_POR_DELEGACION = "SELECT "
			+ "S.SEC_ID AS SECTORID, "
			+ "S.SEC_COD AS SECTORCODIGO, "
			+ "S.EDO_ID AS ESTADOID, "
			+ "S.SEC_NOMBRE AS SECTORNOMBRE, "
			+ "S.DEL_ID AS DELEGACIONID, "
			+ "S.SEC_STATUS AS SECTORSTATUS "
			+ "FROM SECTORES S "
			+ "WHERE S.DEL_ID = #{delId} "
			+ "AND S.EDO_ID = #{edoId} ";
	
	@Select(value = ABC_SECTORES)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudSector(CrudSectorVO crudSectorVO) throws PersistenceException;
	
	@Select(value = GET_SECTORES_POR_DELEGACION)
	public List<RespuestaBusquedaSectoresVO> buscarSectoresPorDelegacion(@Param("delId") String delId, @Param("edoId") String edoId);
}
