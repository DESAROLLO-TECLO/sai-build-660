package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.RegionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudRegionVO;

@Mapper
public interface RegionMyBatisDAO {

	String ABC_GRUAS_STATUS = "{CALL ABC_REGIONES ("
			+"#{estadoId},"
			+"#{regionId},"
			+"#{regionCodigo},"
			+"#{regionNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	String GET_REGIONES_POR_ESTADO = "SELECT "
			+ "R.REG_ID AS REGIONID, "
			+ "R.REG_COD AS REGIONCODIGO, "
			+ "R.REG_NOMBRE AS REGIONNOMBRE, "
			+ "R.REG_STATUS AS REGIONSTATUS, "
			+ "E.EDO_ID AS ESTADOID "
			+ "FROM REGIONES R, ESTADOS E "
			+ "WHERE E.EDO_ID = R.EDO_ID "
			+ "AND R.EDO_ID = #{valor}";
	
	@Select(value = ABC_GRUAS_STATUS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudRegion(CrudRegionVO crudRegionVO) throws PersistenceException;
	
	@Select(value = GET_REGIONES_POR_ESTADO)
	public List<RegionVO> buscarRegionesPorEstado(@Param("valor") String valor);
}
