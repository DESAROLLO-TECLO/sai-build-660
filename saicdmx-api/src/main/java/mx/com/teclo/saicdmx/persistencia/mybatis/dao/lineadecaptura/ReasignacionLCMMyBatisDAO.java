package mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.BitReasignacionLineaCapturaVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccionForReasignacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.InfraccionLCMVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaMasivaVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;

@Mapper
public interface ReasignacionLCMMyBatisDAO {
	
	String INSERT_FOLIO_REASIGNACION = "INSERT INTO INFRACCIONES_LC_REASIGNA_DTL( "
			  						 + "RAD_INFRAC_REASIG_ID, INFRAC_NUM, REASIGNADO, "
			  						 + "CREADO_POR, FECHA_CREACION, "
			  						 + "MODIFICADO_POR, ULTIMA_MODIFICACION) "
			  						 + "VALUES("
			  						 + "#{ idLote }, "
			  						 + "#{ infracNum }, "
			  						 + "0, "
			  						 + "#{ userId }, "
			  						 + "SYSDATE, "
			  						 + "#{ userId }, "
			  						 + "SYSDATE)";
	
	String ACTUALIZAR_FOLIO = "UPDATE INFRACCIONES_LC_REASIGNA_DTL "
							+ "SET REASIGNADO = #{ reasignado }, "
							+ "MODIFICADO_POR = #{ userId }, "
							+ "ULTIMA_MODIFICACION = SYSDATE "
							+ "WHERE INFRAC_NUM = #{ infracNum }";
	
	String INSERT_LOTE_FOLIOS = 
		"INSERT INTO INFRACCIONES_LC_REASIGNA_LOTE( "
		+ "	ID, NOMBRE, REASIGNADO, CANCELADO, CREADO_POR, CANTIDAD_PROCESADOS, CANTIDAD_CANCELADOS, "
		+ "	FECHA_CREACION, FECHA_REASIGNACION, MODIFICADO_POR, ULTIMA_MODIFICACION, FECHA_EMISION, "
		+ "	TIPO_DESCUENTO "
		+ ") VALUES( "	
		+ "(SELECT NVL(MAX(ID),0) + 1 FROM INFRACCIONES_LC_REASIGNA_LOTE), "
		+ "#{ nombre }, "
		+ "0, "
		+ "0, "
		+ "#{ userId }, "
		+ "0, "
		+ "0, "
		+ "SYSDATE, "
		+ "null, "
		+ "#{ userId }, "
		+ "null, "
		+ "#{ fEmision }, "
		+ "#{ tipoDescuento })";
	
	String CANCELAR_LOTE_FOLIOS = "UPDATE INFRACCIONES_LC_REASIGNA_LOTE "
			 					+ "SET CANCELADO = 1, "
			 					+ "CANTIDAD_CANCELADOS = (SELECT COUNT(*) FROM INFRACCIONES_LC_REASIGNA_DTL WHERE REASIGNADO != 1), "
			 					+ "ULTIMA_MODIFICACION =  SYSDATE "
			 					+ "WHERE ID = (SELECT ID FROM INFRACCIONES_LC_REASIGNA_LOTE WHERE ROWNUM < = 1 AND REASIGNADO = 0 AND CANCELADO = 0 AND CANTIDAD_CANCELADOS = 0)";
	
	String GET_FOLIOS_PENDIENTES = 
		"SELECT COUNT(*) "
 		+ "FROM INFRACCIONES_LC_REASIGNA_DTL "
 		+ "WHERE "
 		+ "	REASIGNADO = 0";
	
	String GET_FOLIOS_PENDIENTES_LOTE = 
		"SELECT COUNT(*) "
 		+ "FROM INFRACCIONES_LC_REASIGNA_DTL "
 		+ "WHERE "
 		+ "	RAD_INFRAC_REASIG_ID = #{idLote}"
 		+ "	AND REASIGNADO = 0";
	
	String OBTENER_PROCESO_ACTUAL = 
		"SELECT "
		+ "	PROCESANDO "
		+ "FROM INFRACCIONES_LC_REASIGNA_LOTE "
		+ "WHERE "
		+ "	REASIGNADO = 0 "
		+ "	AND CANCELADO = 0 "
		+ "	AND CANTIDAD_CANCELADOS = 0";
	
	String VACIAR_FOLIOS = "DELETE FROM INFRACCIONES_LC_REASIGNA_DTL";
	
	/*QUERYS PROCESO REASIGNADO*/
	
	String SQL_SP_RADAR_REASIGNA_MASIVA = 
		"BEGIN SP_RADAR_LC_REASIGNA_MASIVA ("
		+ "	#{usuario}, "
		+ "	#{folio}, "
		+ "	#{fecha_infraccion}, "
		+ "	#{lineacaptura}, "
		+ "	#{vigencia}, "
		+ "	#{salario_minimo}, "
		+ "	#{num_dias}, "
		+ "	#{importe}, "
		+ "	#{recargos}, "
		+ "	#{descuento}, "	
		+ "	#{total}, "
		+ "	#{estatusConsumo}, "
		+ "	#{estatusResultado}, "
		+ "	#{lote}, "
		+ "	#{resultado, jdbcType=INTEGER, javaType=java.lang.Integer, mode=INOUT}, "
		+ "	#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}"
		+ "); end;";
	
	String SQL_SP_RADAR_REASIGNA_MASIVA_ALL = 
			"BEGIN SP_RADAR_LC_REASIG_MASIVA_ALL ("
			+ "	#{usuario}, "
			+ "	#{folio}, "
			+ "	#{fecha_emision}, "
			+ "	#{fecha_infraccion}, "
			+ "	#{lineacaptura}, "
			+ "	#{vigencia}, "
			+ "	#{salario_minimo}, "
			+ "	#{num_dias}, "
			+ "	#{importe}, "
			+ "	#{recargos}, "
			+ "	#{descuento}, "	
			+ "	#{total}, "
			+ "	#{estatusConsumo}, "
			+ "	#{estatusResultado}, "
			+ "	#{lote}, "
			+ "	#{resultado, jdbcType=INTEGER, javaType=java.lang.Integer, mode=INOUT}, "
			+ "	#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}"
			+ "); end;";
	
	String SQL_SP_RADAR_LC_REASIGNA_FECHAEM = "UPDATE infracciones_radar "
										 	+ "SET fecha_emision = TRUNC(TO_DATE(#{fecha},'DD/MM/YY HH24:MI')), "
										 	+ "ultima_modificacion = SYSDATE, "
										 	+ "modificado_por = #{userId}, "
										 	+ "Autorizado_por = #{userId} "
										 	+ "WHERE infrac_num = #{infracNum}";
	
	String SQL_ACTUAL_LOTE = 
		"SELECT ID "
		+ "FROM INFRACCIONES_LC_REASIGNA_LOTE "
		+ "WHERE ROWNUM < = 1 "
		+ "AND REASIGNADO = 0 "
		+ "AND CANCELADO = 0 "
		+ "AND CANTIDAD_CANCELADOS = 0 "
		+ "ORDER BY ID DESC";
	
	String SQL_CLEAR = "DELETE FROM INFRACCIONES_LC_REASIGNA_DTL "
					 + "WHERE REASIGNADO = 1 OR REASIGNADO = 2";
	
	String SQL_PROCESO = 
		"UPDATE INFRACCIONES_LC_REASIGNA_LOTE "
		+ "	SET PROCESANDO = #{procesando} "
		+ "WHERE ID = #{id}";
	
	String SQL_INEXISTENTE = "SELECT COUNT(*) FROM INFRACCIONES_LC_REASIGNA_DTL WHERE REASIGNADO = 2";
	
	String SQL_QUITAR_INEX = "DELETE FROM INFRACCIONES_LC_REASIGNA_DTL WHERE REASIGNADO = 2";
	
	String SQL_ACTUALIZAR_LOTE_CON_INEXISTENTES = "UPDATE INFRACCIONES_LC_REASIGNA_LOTE "
												+ "SET REASIGNADO = 1, "
												+ "CANTIDAD_CANCELADOS = #{cancelados}, "
												+ "FECHA_REASIGNACION = SYSDATE, "
												+ "ULTIMA_MODIFICACION = SYSDATE "
												+ "WHERE ID = #{id}";
	
	String SQL_ACTUALIZAR_LOTE_SIN_INEXISTENTES = "UPDATE INFRACCIONES_LC_REASIGNA_LOTE "
												+ "SET REASIGNADO = 1, "
												+ "FECHA_REASIGNACION = SYSDATE, "
												+ "ULTIMA_MODIFICACION = SYSDATE "
												+ "WHERE ID = #{id}";
	
	String SQL_OBTENER_FOLIOS = "SELECT INFRAC_NUM AS INFRACNUM "
			  				  + "FROM INFRACCIONES_LC_REASIGNA_DTL "
			  				  + "WHERE REASIGNADO = 0";
	
	String SQL_MARCAR_INEXISTENTE = 
		"UPDATE INFRACCIONES_LC_REASIGNA_DTL "
		+ "SET REASIGNADO = 2 "
		+ "WHERE INFRAC_NUM = #{infracNum}";
	
	String SQL_REGISTRAR_INEXISTENTE = 
		"INSERT INTO INFRACCIONES_LC_REASIGNA "
		+ "("
		+ "	INFRAC_NUM, ESTATUS_CONSUMO, ESTATUS_RESULTADO, LINEA_CAPTURA, "
		+ "	VIGENCIA, SALARIO_MINIMO, NUMERO_DIAS, IMPORTE, RECARGOS, DESCUENTO, "
		+ "	TOTAL, FECHA_REASIGNACION,  CREADO_POR, TIPO_PROCESO, REASIGNA_LOTE_ID "
		+ ") VALUES ( "
		+ "	#{infracNum}, 720, 'Infracción no existe o se encuentra en proceso de impugnación', null, "
		+ "	null, null, null, null, null, null, "
		+ "	null, null, #{userId}, 1, #{lote} "
		+ ")";
	
	String SQL_VALIDAR_REG_INEX = 
		"SELECT "
		+ "	COUNT(*) "
		+ "FROM INFRACCIONES_LC_REASIGNA "
		+ "WHERE "
		+ "	INFRAC_NUM = #{infracNum} "
		+ "	AND TIPO_PROCESO = 1 "
		+ "	AND REASIGNA_LOTE_ID = #{id}";
	
	String SQL_ACTUAL_LOTE_FEMISION = 
		"SELECT "
		+ "	FECHA_EMISION "
		+ "FROM INFRACCIONES_LC_REASIGNA_LOTE "
		+ "WHERE "
		+ "	ID = #{id}";
	
	String SQL_ACTUAL_LOTE_TIPO_DESCUENTO = 
		"SELECT "
		+ "	TIPO_DESCUENTO "
		+ "FROM INFRACCIONES_LC_REASIGNA_LOTE "
		+ "WHERE "
		+ "	ID = #{id}";
	
	String SQL_INFRACCANCEL = "SELECT INFRAC_NUM FROM INFRACCIONES_LC_REASIGNA_DTL WHERE REASIGNADO = 2";
	
	String SQL_LOTE_AGREGADO = 
		"SELECT ID "
		+ "FROM INFRACCIONES_LC_REASIGNA_LOTE "
		+ "WHERE "
		+ "	ROWNUM < = 1"
		+ "	AND REASIGNADO = 0 "
		+ "	AND CANCELADO = 0 "
		+ "	AND PROCESANDO = 0 "
		+ "	AND CREADO_POR = #{userId} "
		+ "	AND NOMBRE = #{ nombre } "
		//+ "	AND FECHA_CREACION = SYSDATE "
		+ "	AND TO_DATE(TO_CHAR(FECHA_CREACION,'DD/MM/YY'),'DD/MM/YY') = TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YY'),'DD/MM/YY')"
		+ "	AND FECHA_EMISION = #{ fEmision } "
		+ " AND TIPO_DESCUENTO = #{ tipoDescuento } "
		+ "ORDER BY ID DESC";
	
	String SQL_OBTENER_FOLIOS_LOTE = 
		"SELECT INFRAC_NUM AS INFRACNUM "
		+ "FROM INFRACCIONES_LC_REASIGNA_DTL "
		+ "WHERE "
		+ "	REASIGNADO = 0"
 		+ "	AND RAD_INFRAC_REASIG_ID = #{idLote}";
	/*FIN QUERYS PROCESO REASIGNADO*/
	
	String GUARDA_BITACORA_PARAMETROS_WS_LC =
		"INSERT INTO TAI014B_LC_REASIGNA_WS("
//		+ "ID_LC_REASIGNA_WS,TX_URL_WEBSERVICE,NU_FOLIO,FH_INFRACCION,"+
//		"FH_EMISION,NU_DIAS,NU_SALARIO_MINIMO,NU_IMPORTE,NU_DERECHOS,NU_APLICA_CONDONACION,"+
//		"TX_CADENA_XML,ST_ACTIVO,"+
//		"ID_USR_CREACION,FH_CREACION"
		+ "	ID_LC_REASIGNA_WS, TX_URL_WEBSERVICE, NU_FOLIO, FH_INFRACCION, FH_EMISION, "
		+ "	NU_DIAS, NU_SALARIO_MINIMO, NU_IMPORTE, NU_DERECHOS, NU_APLICA_CONDONACION, "
		+ "	TX_CADENA_XML_PETICION, "
		+ "	NU_FOLIO_RES, FH_INFRACCION_RES, NU_SALARIO_MINIMO_RES, NU_DIAS_RES, NU_IMPORTE_RES, "
		+ "	NU_DESCUENTO_RES, NU_RECARGOS_RES, NU_TOTAL_RES, FH_VIGENCIA_RES, TX_LINEACAPTURA_RES, "
		+ "	TX_LINEACAPTURACB_RES, ST_ERROR_RES, TX_ERROR_DESCRIPCION_RES, TX_CADENA_XML_RES, "
		+ "	ST_ACTIVO, ID_USR_CREACION, FH_CREACION, ID_USR_MODIFICA, FH_MODIFICACION, ID_ORIGEN"
		+ ") VALUES ("
		+ "	SQAI_REG_TAI014.NEXTVAL, #{urlWebservice}, #{folio}, TO_DATE(#{fechaInfraccion},'yyyy/mm/dd'), TO_DATE(#{fechaEmision},'yyyy/mm/dd'), "
		+ "	#{dias}, #{salarioMinimo}, #{importe}, #{derechos}, #{aplicaCondonacion}, "
		+ "	#{cadenaXML}, "
		+ "	#{folioRes}, TO_DATE(#{fechaInfraccionRes},'yyyy/mm/dd'), #{salarioMinimoRes}, #{diasRes}, #{importeRes}, "
		+ "	#{descuentoRes}, #{recargosRes}, #{totalRes}, TO_DATE(#{vigenciaRes},'yyyy/mm/dd'), #{lineacapturaRes}, "
		+ "	#{lineacapturaCBRes}, #{errorRes}, #{errorDescripcionRes}, #{cadenaXMLRes}, "
		+ "	1, #{idUsuario}, sysdate, #{idUsuario}, sysdate, #{idOrigen}"
		+ ")";
	
	/***
	 * @author Jesus Ponce
	 * @param infracNum
	 * @param userId
	 * @return Integer
	 */
	@Insert(INSERT_FOLIO_REASIGNACION)
	public Integer insertarFolio(
			@Param("infracNum") String infracNum, 
			@Param("userId") Long userId,
			@Param("idLote") Integer idLote);
	
	/***
	 * @author Jesus Ponce
	 * @param reasignado
	 * @param infracNum
	 * @param userId
	 * @return Integer
	 */
	@Update(ACTUALIZAR_FOLIO)
	public Integer actualizarFolio(
			@Param("reasignado") Integer reasignado,
			@Param("infracNum") String infracNum, 
			@Param("userId") Long userId);
	
	/***
	 * @author Jesus Ponce
	 * @param nombre
	 * @param userId
	 * @return Integer
	 */
	@Insert(INSERT_LOTE_FOLIOS)
	public Integer crearLoteFolios(
			@Param("nombre") String nombre, 
			@Param("userId") Long userId, 
			@Param("fEmision") Date fEmision,
			@Param("tipoDescuento")Integer tipoDescuento);
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Update(CANCELAR_LOTE_FOLIOS)
	public Integer cancelarLoteFolios();
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Select(GET_FOLIOS_PENDIENTES)
	public Integer validarFoliosPendientes();
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Select(OBTENER_PROCESO_ACTUAL)
	public Integer validarProcesoActual();
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Delete(VACIAR_FOLIOS)
	public Integer vaciarFolios();
	
	/*FUNCIONES PARA PROCESO REASIGNADO*/
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Select(SQL_SP_RADAR_REASIGNA_MASIVA)
	@Options(statementType = StatementType.CALLABLE)
	public RespuestaWSReasignaLineaCapturaMasivaVO sqlSpRadarReasignaMasiva(RespuestaWSReasignaLineaCapturaMasivaVO respuestaWSReasignaLineaCapturaMasivaVO) throws PersistenceException;
	
	@Select(SQL_SP_RADAR_REASIGNA_MASIVA_ALL)
	@Options(statementType = StatementType.CALLABLE)
	public RespuestaWSReasignaLineaCapturaMasivaVO sqlSpRadarReasignaMasivaAll(RespuestaWSReasignaLineaCapturaMasivaVO respuestaWSReasignaLineaCapturaMasivaVO) throws PersistenceException;
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Update(SQL_SP_RADAR_LC_REASIGNA_FECHAEM)
	public Integer sqlSpRadarLcReasignaFechaEm(
			@Param("fecha")String fecha,
			@Param("userId")Long userId,
			@Param("infracNum")String infracNum);
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Select(SQL_ACTUAL_LOTE)
	public Integer sqlActualLote();
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Delete(SQL_CLEAR)
	public Integer sqlClear();
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Update(SQL_PROCESO)
	public Integer sqlProceso(
			@Param("procesando") Integer procesando, 
			@Param("id") Integer id);
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Select(SQL_INEXISTENTE)
	public Integer sqlInexistente();
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Delete(SQL_QUITAR_INEX)
	public Integer sqlQuitarInex();
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Update(SQL_ACTUALIZAR_LOTE_CON_INEXISTENTES)
	public Integer sqlActualizarLoteConInexistentes(
			@Param("cancelados")Integer cancelados, 
			@Param("id")Integer id);
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Update(SQL_ACTUALIZAR_LOTE_SIN_INEXISTENTES)
	public Integer sqlActualizarLoteSinInexistentes(
			@Param("id")Integer id);
	
	/***
	 * @author Jesus Ponce
	 * @return List<InfraccionLCMVO>
	 */
	@Select(SQL_OBTENER_FOLIOS)
	public List<InfraccionLCMVO> sqlObtenerFolios();
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Update(SQL_MARCAR_INEXISTENTE)
	public Integer sqlMarcarInexistente(
			@Param("infracNum")String infracNum);
	
	/***
	 * @author Jesus Ponce
	 * @return Integer
	 */
	@Insert(SQL_REGISTRAR_INEXISTENTE)
	public Integer sqlRegistrarInexistente(
			@Param("infracNum")String infracNum, 
			@Param("userId")Long userId, 
			@Param("lote")Integer lote);
	
	/***
	 * @author Jesus Ponce
	 * @return List<InfraccionLCMVO>
	 */
	@Select(SQL_VALIDAR_REG_INEX)
	public Integer sqlValdarRegInex(
			@Param("infracNum")String infracNum, 
			@Param("id")Integer id);
	
	/***
	 * @author Jose Castillo
	 * @return Sting
	 */
	@Select(SQL_ACTUAL_LOTE_FEMISION)
	public String sqlActualLoteFEmision(
			@Param("id") Integer id);
	
	/***
	 * @author Jose Castillo
	 * @return Integer
	 */
	@Select(SQL_ACTUAL_LOTE_TIPO_DESCUENTO)
	public Integer sqlActualLoteTipoDescuento(
			@Param("id") Integer id);
	
	/***
	 * @author Jose Castillo
	 * @return ArrayList<String>
	 */
	@Select(SQL_INFRACCANCEL)
	public ArrayList<String> infracCanceladas();
	
	/***
	 * @author Jose Castillo
	 * @return Long
	 */
	@Select(SQL_LOTE_AGREGADO)
	public Integer consultaLoteCreado(
			@Param("nombre") String nombre, 
			@Param("userId") Long userId, 
			@Param("fEmision") Date fEmision,
			@Param("tipoDescuento") Integer tipoDescuento);
	/***
	 * @author Jose Castillo
	 * @return Integer
	 */
	@Select(GET_FOLIOS_PENDIENTES_LOTE)
	public Integer validarFoliosPendientesLote(
			@Param("idLote") Integer idLote);
	
	/***
	 * @author Jose Castillo
	 * @return List<InfraccionLCMVO>
	 */
	@Select(SQL_OBTENER_FOLIOS_LOTE)
	public List<InfraccionLCMVO> sqlObtenerFoliosLote(
			@Param("idLote") Integer idLote);
	/*FIN FUNCIONES PARA PROCESO REASIGNADO*/
	
	/**
	 * @author Jose Castillo
	 * @param BitReasignacionLineaCapturaVO
	 */
	@Select(GUARDA_BITACORA_PARAMETROS_WS_LC)
	public void guardaBitacoraLcReasignaWS(BitReasignacionLineaCapturaVO peticionWS);
}
