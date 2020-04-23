package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos.GruaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudGruaVO;

@Mapper
public interface GruaMyBatisDAO {
	
	String GET_GRUAS_POR_CONCESIONARIA = "SELECT "
			+ "G.GRUA_ID AS GRUAID, "
			+ "G.GRUA_COD AS GRUACOD, "
			+ "G.CONSE_ID AS CONSEID, "
			+ "GS.GSTAT_NOMBRE AS GSTATUS, "
			+ "G.GSTAT_ID AS GSTATID, "
			+ "G.GRUA_STATUS AS GRUASTATUS "
			+ "FROM GRUAS G, GRUAS_STATUS GS "
			+ "WHERE G.GSTAT_ID = GS.GSTAT_ID "
			+ "AND G.CONSE_ID = #{valor}";
	
	String ABC_GRUAS = "{CALL ABC_GRUAS ("
			+"#{gruaId},"
			+"#{gruaCod},"
			+"#{conseId},"
			+"#{gStatId},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_GRUAS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudGrua(CrudGruaVO crudGruaVO) throws PersistenceException;
	
	@Select(value = GET_GRUAS_POR_CONCESIONARIA)
	public List<GruaVO> buscarGruasPorConcesionaria(@Param("valor") String valor);
}
