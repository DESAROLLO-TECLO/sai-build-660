package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDelegacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.RespuestaBusquedaDelegacionesVO;

@Mapper
public interface DelegacionMyBatisDAO {

	String ABC_DELEGACIONES = "{CALL ABC_DELEGACIONES ("
			+"#{edoId},"
			+"#{delId},"
			+"#{regId},"
			+"#{delCod},"
			+"#{delNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	String GET_DELEGACIONES_POR_ESTADO = "SELECT "
			+ "D.EDO_ID AS EDOID, "
			+ "D.DEL_ID AS DELID, "
			+ "D.REG_ID AS REGID, "
			+ "D.DEL_COD AS DELCOD, "
			+ "R.REG_NOMBRE AS REGNOMBRE, "
			+ "D.DEL_NOMBRE AS DELNOMBRE, "
			+ "D.DEL_STATUS AS DELSTATUS "
			+ "FROM DELEGACIONES D, ESTADOS E, REGIONES R "
			+ "WHERE D.EDO_ID = E.EDO_ID "
			+ "AND D.REG_ID = R.REG_ID "
			+ "AND R.EDO_ID = E.EDO_ID "
			+ "AND D.EDO_ID = #{valor} "
			+ "ORDER BY D.DEL_ID DESC";
	
	@Select(value = GET_DELEGACIONES_POR_ESTADO)
	public List<RespuestaBusquedaDelegacionesVO> buscarDelegacionesPorEstado(@Param("valor") String valor);
	
	@Select(value = ABC_DELEGACIONES)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudDelegacion(CrudDelegacionVO crudDelegacionVO) throws PersistenceException;
}
