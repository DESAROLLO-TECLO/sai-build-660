package mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccionForReasignacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.DetalleDeReasignacionesInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.PorcentajeDescuentoLCVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;

@Mapper
public interface ReasignacionLCMyBatisDAO {
	
	String GET_INFRACCIONRADAR_BY_FOLIO = 
		"SELECT "
		+ "FOLIO_INFRACCION as FOLIO, "
		+ "FECHA_INFRACCION as FECHAINFRACCION, " 
		+ "FECHA_EMISION as FECHAEMISION, "
		+ "DIAS_SANCION as DIAS, " 
		+ "SALARIO_MINIMO as SALARIOMINIMO, " 
		+ "TOTAL as IMPORTE, " 
		+ "DERECHOS as DERECHOS " 
		+ "FROM    V_WS_SF_LINEAC_REASIGNA " 
		+ "WHERE FOLIO_INFRACCION = #{folio}";
	
	String GET_INFRACCION_BY_FOLIO = 
		"SELECT "
		+ "FOLIO_INFRACCION as FOLIO, "
		+ "FECHA_INFRACCION as FECHAINFRACCION, " 
		+ "FECHA_EMISION as FECHAEMISION, "
		+ "DIAS_SANCION as DIAS, " 
		+ "SALARIO_MINIMO as SALARIOMINIMO, " 
		+ "TOTAL as IMPORTE, " 
		+ "DERECHOS as DERECHOS " 
		+ "FROM    V_WS_SF_LINEAC_REASIGNA_ALL " 
		+ "WHERE FOLIO_INFRACCION = #{folio}";

	String GET_INFRACCION_DIG_BY_FOLIO = 
		"SELECT "
		+ "FOLIO_INFRACCION as FOLIO, "
		+ "FECHA_INFRACCION as FECHAINFRACCION, " 
		+ "FECHA_EMISION as FECHAEMISION, "
		+ "DIAS_SANCION as DIAS, " 
		+ "SALARIO_MINIMO as SALARIOMINIMO, " 
		+ "TOTAL as IMPORTE, " 
		+ "DERECHOS as DERECHOS " 
		+ "FROM    V_WS_SF_LINEAC_REASIGNA_DIG " 
		+ "WHERE FOLIO_INFRACCION = #{folio}";
	
	String GET_MOTIVO_CONDONACION = 
		"SELECT "
		+ "	CASE "
		+ "		WHEN count(*) > 0 "
		+ "			THEN 1 "
		+ "		ELSE 0 "
		+ "	END AS CONDONADA "
		+ "FROM "
		+ "	INFRACCIONES_RADAR i "
		+ "	JOIN PLAN_DESCUENTOS d ON i.art_id = d.art_id "
		+ "WHERE "
		+ "	i.infrac_num = #{infraccion} "
		+ "	AND d.porcen_descuento_1 = #{porcondonacion}";

	String GET_MOTIVO_CONDONACION_INFRAC = 
		"SELECT "
		+ "	CASE "
		+ "		WHEN count(*) > 0 "
		+ "			THEN 1 "
		+ "		ELSE 0 "
		+ "	END AS CONDONADA "
		+ "FROM "
		+ "	INFRACCIONES i "
		+ "	JOIN PLAN_DESCUENTOS d ON i.art_id = d.art_id "
		+ "WHERE "
		+ "	i.infrac_num = #{infraccion} "
		+ "	AND d.porcen_descuento_1 = #{porcondonacion}";

	String GET_MOTIVO_CONDONACION_INFRAC_DIG = 
		"SELECT "
		+ "	CASE "
		+ "		WHEN count(*) > 0 "
		+ "			THEN 1 "
		+ "		ELSE 0 "
		+ "	END AS CONDONADA "
		+ "FROM "
		+ "	INFRACCIONES_DIGITALIZACION i "
		+ "	JOIN PLAN_DESCUENTOS d ON i.art_id = d.art_id "
		+ "WHERE "
		+ "	i.infrac_num = #{infraccion} "
		+ "	AND d.porcen_descuento_1 = #{porcondonacion}";
	
	String GET_PORCENTAJE_DESCUENTO = 
		"SELECT "
		+ "	COUNT(r.estatus_resultado) AS TOTALREASIGNACIONES, "
		+ "	CASE "
		+ "		WHEN COUNT(estatus_resultado) >= 1 "
		+ "			THEN d.porcen_descuento_2 "
		+ "		ELSE d.porcen_descuento_1 "
		+ "	END AS PORCENTAJECORRESPONDE, "
		+ "	porcen_descuento_1 AS PORCENDESCUENTO1, "
		+ "	porcen_descuento_2 AS PORCENDESCUENTO2 "
		+"FROM INFRACCIONES_RADAR i "
		+"	JOIN PLAN_DESCUENTOS d ON d.art_id = i.art_id "
		+"	LEFT JOIN INFRACCIONES_LC_REASIGNA r ON i.infrac_num = r.infrac_num AND r.estatus_consumo = #{estatus} "
		+"WHERE "
		+ "	i.infrac_num = #{infraccion} "
		+"GROUP BY "
		+ "	r.infrac_num, "
		+ "	r.estatus_resultado, "
		+ "	d.porcen_descuento_1, "
		+ "	d.porcen_descuento_2";

	String GET_PORCENTAJE_DESCUENTO_INFRAC = 
		"SELECT "
		+ "	COUNT(r.estatus_resultado) AS TOTALREASIGNACIONES, "
		+ "	CASE "
		+ "		WHEN COUNT(estatus_resultado) >= 1 "
		+ "			THEN d.porcen_descuento_2 "
		+ "		ELSE d.porcen_descuento_1 "
		+ "	END AS PORCENTAJECORRESPONDE, "
		+ "	porcen_descuento_1 AS PORCENDESCUENTO1, "
		+ "	porcen_descuento_2 AS PORCENDESCUENTO2 "
		+"FROM INFRACCIONES i "
		+"	JOIN PLAN_DESCUENTOS d ON d.art_id = i.art_id "
		+"	LEFT JOIN INFRACCIONES_LC_REASIGNA r ON i.infrac_num = r.infrac_num AND r.estatus_consumo = #{estatus} "
		+"WHERE "
		+ "	i.infrac_num = #{infraccion} "
		+"GROUP BY "
		+ "	r.infrac_num, "
		+ "	r.estatus_resultado, "
		+ "	d.porcen_descuento_1, "
		+ "	d.porcen_descuento_2";

	String GET_PORCENTAJE_DESCUENTO_INFRAC_DIG = 
		"SELECT "
		+ "	COUNT(r.estatus_resultado) AS TOTALREASIGNACIONES, "
		+ "	CASE "
		+ "		WHEN COUNT(estatus_resultado) >= 1 "
		+ "			THEN d.porcen_descuento_2 "
		+ "		ELSE d.porcen_descuento_1 "
		+ "	END AS PORCENTAJECORRESPONDE, "
		+ "	porcen_descuento_1 AS PORCENDESCUENTO1, "
		+ "	porcen_descuento_2 AS PORCENDESCUENTO2 "
		+"FROM INFRACCIONES_DIGITALIZACION i "
		+"	JOIN PLAN_DESCUENTOS d ON d.art_id = i.art_id "
		+"	LEFT JOIN INFRACCIONES_LC_REASIGNA r ON i.infrac_num = r.infrac_num AND r.estatus_consumo = #{estatus} "
		+"WHERE "
		+ "	i.infrac_num = #{infraccion} "
		+"GROUP BY "
		+ "	r.infrac_num, "
		+ "	r.estatus_resultado, "
		+ "	d.porcen_descuento_1, "
		+ "	d.porcen_descuento_2";
	
	String GET_DETALLE_REASIGNACIONES_BY_INFRACCION = "SELECT "
			+ "reasigna.infrac_num AS FOLIOINFRACCION, "
			+ "reasigna.estatus_resultado AS ESTATUS, "
			+ "reasigna.linea_captura AS LINEACAPTURA, "
			+ "to_char(reasigna.fecha_creacion,'dd/mm/yyyy') AS FECHAREASIGNA, "
			+ "to_char(reasigna.vigencia,'dd/mm/yyyy') AS VIGENCIA, "
			+ "reasigna.importe AS IMPORTE, "
			+ "reasigna.recargos AS RECARGOS, "
			+ "reasigna.descuento AS DESCUENTO, "
			+ "reasigna.total AS TOTAL, " 
			+ "emp.emp_placa AS PLACAEMPLEADO, "
			+ "emp.emp_nombre || ' ' || emp.emp_ape_paterno || ' ' || emp.emp_ape_materno AS NOMBREEMPLEADO "
			+ "FROM INFRACCIONES_LC_REASIGNA reasigna "
			+ "LEFT JOIN USUARIOS usr on usr.usu_id =  reasigna.creado_por "
			+ "LEFT JOIN EMPLEADOS emp on emp.emp_id = usr.emp_id "
			+ "WHERE reasigna.infrac_num = #{infraccion} and reasigna.estatus_consumo = #{estatus} " 
			+ "ORDER BY reasigna.fecha_creacion DESC";
	
	String SP_RADAR_LC_REASIGNA = "BEGIN SP_RADAR_LC_REASIGNA ("
			+ "#{usuario}, "
			+ "#{folio}, "
			+ "#{fecha_infraccion}, "
			+ "#{lineacaptura}, "
			+ "#{vigencia}, "
			+ "#{salario_minimo}, "
			+ "#{num_dias}, "
			+ "#{importe}, "
			+ "#{recargos}, "
			+ "#{descuento}, "
			+ "#{total}, "
			+ "#{estatusConsumo}, "
			+ "#{estatusResultado}, "
			+ "#{resultado, jdbcType=INTEGER, javaType=java.lang.Integer, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	String SP_INFRACCIONES_LC_REASIGNA = "BEGIN SP_INFRAC_LC_REASIGNA_ALL ("
			+ "#{usuario}, "
			+ "#{folio}, "
			+ "#{fecha_emision}," 
			+ "#{fecha_infraccion}, "
			+ "#{lineacaptura}, "
			+ "#{vigencia}, "
			+ "#{salario_minimo}, "
			+ "#{num_dias}, "
			+ "#{importe}, "
			+ "#{recargos}, "
			+ "#{descuento}, "
			+ "#{total}, "
			+ "#{estatusConsumo}, "
			+ "#{estatusResultado}, "
			+ "#{resultado, jdbcType=INTEGER, javaType=java.lang.Integer, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	String OLD_SP_RADAR_LC_REASIGNA = "SELECT INFRAC_LINEA_CAP as lineacaptura, INFRAC_LINEA_CAP_VIGENCIA as vigencia, "
			+ "INFRAC_LINEA_CAP_MONTO as total, AUTORIZADO_POR as usuario "
			+ "FROM INFRACCIONES_RADAR WHERE INFRAC_NUM = #{folio}";
	
	String OLD_SP_INFRAC_LC_REASIGNA = "SELECT INFRAC_LINEA_CAP as lineacaptura, INFRAC_LINEA_CAP_VIGENCIA as vigencia, "
			+ "INFRAC_LINEA_CAP_MONTO as total, AUTORIZADO_POR as usuario "
			+ "FROM INFRACCIONES WHERE INFRAC_NUM = #{folio}";
	
	String OLD_SP_INFRAC_DIG_LC_REASIGNA = "SELECT INFRAC_LINEA_CAP as lineacaptura, INFRAC_LINEA_CAP_VIGENCIA as vigencia, "
			+ "INFRAC_LINEA_CAP_MONTO as total, AUTORIZADO_POR as usuario "
			+ "FROM INFRACCIONES_DIGITALIZACION WHERE INFRAC_NUM = #{folio}";
	
	String SQL_FECHA_OLD = 
		"SELECT "
		+ "	FECHA_EMISION "
		+ "FROM INFRACCIONES_RADAR "
		+ "WHERE "
		+ "	INFRAC_NUM = #{infracNum}";
	
	String SQL_FECHA_OLD_INFRAC = 
		"SELECT "
		+ "	FECHA_EMISION "
		+ "FROM INFRACCIONES "
		+ "WHERE "
		+ "	INFRAC_NUM = #{infracNum}";
	
	String SQL_USUARIO_OLD = "SELECT MODIFICADO_POR FROM INFRACCIONES_RADAR "
			+ "WHERE INFRAC_NUM = #{infracNum}";

	String SQL_AUTORIZADO_POR_OLD = "SELECT AUTORIZADO_POR FROM INFRACCIONES_RADAR "
			+ "WHERE INFRAC_NUM = #{infracNum}";
	
	String PARAMETROS_QUERYS_CONDONACIONES="SELECT CD_LLAVE_P_CONFIG,CD_VALOR_P_CONFIG FROM TAI041P_CONFIGURACION";
	
	String CONS_PORCENTAJE_DESCUENTO_RAD = "${consPorcentajeDescRad}";
	String CONS_PORCENTAJE_DESCUENTO_INFRAC = "${consPorcentajeDescInfrac}";
	String CONS_PORCENTAJE_DESCUENTO_DIG = "${consPorcentajeDescDig}";
	String CONS_CONDONACION = "${consCondonacion}";
	
	@Update("UPDATE INFRACCIONES_RADAR "
			   + "SET fecha_emision = #{fechareasigna}, "
		 	   + "ultima_modificacion = sysdate, " 
			   + "modificado_por = #{usuario}, "
			   + "Autorizado_por = #{usuario} "
		       + "WHERE infrac_num = #{folioinfraccion}")
	public void UpdateFechaEmForLC(@Param("folioinfraccion") String infraccion, @Param("fechareasigna") Date fechareasigna, 
			@Param("usuario") Long usuario);
	
	
	
	/***
	 * @author José Castillo
	 * @param folio
	 * @return
	 */
	@Select(GET_INFRACCIONRADAR_BY_FOLIO)
	public ConsultaInfraccionForReasignacionVO buscaInfraccionRadarByFolio(@Param("folio") String folio);
	
	@Select(GET_INFRACCION_BY_FOLIO)
	public ConsultaInfraccionForReasignacionVO buscaInfraccionByFolio(@Param("folio") String folio);

	@Select(GET_INFRACCION_DIG_BY_FOLIO)
	public ConsultaInfraccionForReasignacionVO buscaInfraccionDigByFolio(@Param("folio") String folio);
	
	/***
	 * @author José Castillo
	 * @param infraccion
	 * @param porcondonacion
	 * @return
	 */
	@Select(GET_MOTIVO_CONDONACION)
	public Integer ConsultaMotivoCondonacion(
		@Param("infraccion") String infraccion, 
		@Param("porcondonacion") Integer porcondonacion);
	
	@Select(GET_MOTIVO_CONDONACION_INFRAC)
	public Integer ConsultaMotivoCondonacionInfrac(
		@Param("infraccion") String infraccion, 
		@Param("porcondonacion") Integer porcondonacion);
	
	@Select(GET_MOTIVO_CONDONACION_INFRAC_DIG)
	public Integer ConsultaMotivoCondonacionInfracDig(
		@Param("infraccion") String infraccion, 
		@Param("porcondonacion") Integer porcondonacion);
	
	/***
	 * @author Jesus Gutierrez
	 * @param infraccion
	 * @param estatus 0:OK
	 * @return
	 */
	@Select(GET_PORCENTAJE_DESCUENTO)
	public PorcentajeDescuentoLCVO ConsultarPorcentajeDescuento(
		@Param("infraccion") String infraccion, 
		@Param("estatus") Integer estatus);

	@Select(GET_PORCENTAJE_DESCUENTO_INFRAC)
	public PorcentajeDescuentoLCVO ConsultarPorcentajeDescuentoInfrac(
		@Param("infraccion") String infraccion, 
		@Param("estatus") Integer estatus);

	@Select(GET_PORCENTAJE_DESCUENTO_INFRAC_DIG)
	public PorcentajeDescuentoLCVO ConsultarPorcentajeDescuentoInfracDig(
		@Param("infraccion") String infraccion, 
		@Param("estatus") Integer estatus);
	
	/***
	 * @author Jesus Gutierrez
	 * @param infraccion
	 * @param estatus
	 * @return
	 */
	@Select(GET_DETALLE_REASIGNACIONES_BY_INFRACCION)
	public List<DetalleDeReasignacionesInfraccionVO> ConsultaDetalleReasignacionesByInfraccion(@Param("infraccion") String infraccion, @Param("estatus") Integer estatus);
	
	@Select(value = SP_RADAR_LC_REASIGNA)
	@Options(statementType = StatementType.CALLABLE)	
	public RespuestaWSReasignaLineaCapturaVO ReasignarLineaDeCaptura(RespuestaWSReasignaLineaCapturaVO respuestaWSReasignaLineaCapturaVO) throws PersistenceException;

	@Select(value = SP_INFRACCIONES_LC_REASIGNA)
	@Options(statementType = StatementType.CALLABLE)	
	public RespuestaWSReasignaLineaCapturaVO ReasignarLineaDeCapturaInfrac(RespuestaWSReasignaLineaCapturaVO respuestaWSReasignaLineaCapturaVO) throws PersistenceException;
	
	/***
	 * @author José Castillo
	 * @return Fecha anterior
	 */
	@Select(SQL_FECHA_OLD)
	public Date sqlFechaOld(@Param("infracNum")String infracNum);
	
	@Select(SQL_FECHA_OLD_INFRAC)
	public Date sqlFechaOldInfrac(@Param("infracNum")String infracNum);
	/***
	 * @author Fernando Castillo
	 * @return Usuario anterior
	 */
	@Select(SQL_USUARIO_OLD)
	public String sqlUsuarioOld(@Param("infracNum")String infracNum);
	
	@Select(SQL_AUTORIZADO_POR_OLD)
	public String sqlAutoriadoPorOld(@Param("infracNum")String infracNum);
	/***
	 * @author Fernando Castillo
	 * @return RespuestaWSReasignaLineaCapturaVO anterior
	 */
	@Select(OLD_SP_RADAR_LC_REASIGNA)
	public RespuestaWSReasignaLineaCapturaVO OldReasignarLineaDeCaptura(@Param("folio") String folio);
	
	@Select(OLD_SP_INFRAC_LC_REASIGNA)
	public RespuestaWSReasignaLineaCapturaVO OldReasignarLineaDeCapturaInfrac(@Param("folio") String folio);
	
	@Select(OLD_SP_INFRAC_DIG_LC_REASIGNA)
	public RespuestaWSReasignaLineaCapturaVO OldReasignarLineaDeCapturaInfracDig(@Param("folio") String folio);
	
	@Select(PARAMETROS_QUERYS_CONDONACIONES)
	public List<Map<String, String>> buscarQuerys();
	
	@Select(CONS_PORCENTAJE_DESCUENTO_RAD)
	public PorcentajeDescuentoLCVO consPorcentajeDescRad(@Param("consPorcentajeDescRad") String consPorcentajeDescRad);
	
	@Select(CONS_PORCENTAJE_DESCUENTO_INFRAC)
	public PorcentajeDescuentoLCVO consPorcentajeDescInfrac(@Param("consPorcentajeDescInfrac") String consPorcentajeDescInfrac);
	
	@Select(CONS_PORCENTAJE_DESCUENTO_DIG)
	public PorcentajeDescuentoLCVO consPorcentajeDescDig(@Param("consPorcentajeDescDig") String consPorcentajeDescDig);
	
	@Select(CONS_CONDONACION)
	public Integer consCondonacion(@Param("consCondonacion") String consCondonacion);
}
