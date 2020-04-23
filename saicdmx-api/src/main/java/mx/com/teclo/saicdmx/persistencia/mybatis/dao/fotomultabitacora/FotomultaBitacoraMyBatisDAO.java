package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fotomultabitacora;

import java.sql.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface FotomultaBitacoraMyBatisDAO {
	
	String INSERT_FOTOMULTA_BITACORA = "INSERT INTO FOTOMULTA_BITACORA_WS( "
  + "REQUEST_LOTE_ID, REQUEST_USUARIO, FECHA_CONSULTA, ESTATUS_EJECUCION, FOTOMULTA_CAT_PROCESO_WS_ID, EN_PROCESO, REQUEST_FOLIO) "
  + "VALUES( "
  + "	#{loteId}, #{usuario}, #{fecha}, #{estatus}, #{proceso_id}, #{en_proceso}, #{folio})";
	
	String UPDATE_FOTOMULTA_BITACORA = "UPDATE FOTOMULTA_BITACORA_WS "
	+ "SET RESPONSE_ERROR = #{responseError}, RESPONSE_RESULTADO = #{responseResultado}, EN_PROCESO = 0, ESTATUS_EJECUCION = #{estatusEjecucion}, REQUEST_USUARIO = #{usuario} "
    + "WHERE FOTOMULTA_CAT_PROCESO_WS_ID = #{proceso_id} "
    + "AND EN_PROCESO = 1 AND "
    + "(CASE"
    + "		WHEN (#{lote_id} IS NOT NULL) AND REQUEST_LOTE_ID = #{lote_id}"
    + "		THEN 1"
    + "		WHEN (#{folio} IS NOT NULL) AND REQUEST_FOLIO = #{folio}"
    + "		THEN 1"
    + "END) = 1";
	
	String BUSCA_ESTATUS_EJECUCION_FOTOMULTA_BITACORA = "SELECT  FBWS.ESTATUS_EJECUCION "+
			"FROM FOTOMULTA_BITACORA_WS FBWS "+
			"WHERE FBWS.REQUEST_LOTE_ID = #{archivoId} "+
			"AND FBWS.FOTOMULTA_CAT_PROCESO_WS_ID = #{tipoProcesows} "+
			"AND ROWNUM  = 1 "+
			"ORDER BY FOTOMULTA_BITACORA_WS_ID DESC";
	
	
	@Insert(INSERT_FOTOMULTA_BITACORA)
	public Integer insertFotomultaBitacora(@Param("loteId") Long loteId, @Param("usuario")String usuario, @Param("fecha") Date fecha,
											@Param("estatus") Integer estatus, @Param("proceso_id") Long proceso_id, 
											@Param("en_proceso") Integer en_proceso, @Param("folio") String folio);

	@Update(UPDATE_FOTOMULTA_BITACORA)
	public Integer updateFotomultaBitacora(@Param("responseError") String responseError, @Param("responseResultado") String responseResultado,
											@Param("estatusEjecucion") Integer estatusEjecucion, @Param("usuario") String usuario,
											@Param("proceso_id") Long proceso_id, @Param("lote_id") Long lote_id,
											@Param("folio") String folio);
	
	@Select(BUSCA_ESTATUS_EJECUCION_FOTOMULTA_BITACORA)
	public Integer buscaEstatusEjecucionFotomultaBitacora(@Param("archivoId") Long archivoId, @Param("tipoProcesows") Integer tipoProcesows);
}