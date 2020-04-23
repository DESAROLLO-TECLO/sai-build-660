package mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivodetalle;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ReporteDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ArchivosTotalesVO;

@Mapper
public interface RadarArchivoDetalleMyBatisDAO {
	
	/*String INSERT_RADAR_ARCHIVO_DETALLE_COMPLETO = 
			"INSERT INTO RADAR_ARCHIVO_DETALLE (ID, RADAR_ARCHIVO_ID, PLACA, FECHA, HORA, TDSKUID, UT, VELOCIDAD_DETECTADA, FECHA_CREACION, "+
			"	PLACA_SF,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CALLE,NUM_EXTERIOR,NUM_INTERIOR,COLONIA,CODIGO_POSTAL,ENTIDAD_FEDERATIVA,MUNICIPIO,"+
			"	TELEFONO,MARCA,SUBMARCA,MODELO,SERIE,MOTOR,WS_SETRAVI_CONSULTADO,ERROR_WS_SETRAVI,FECHA_CONSULTA_WS_SETRAVI,ACTIVO,DUPLICADO,ART_ID) "+
			"VALUES (RADAR_ARCHIVO_DETALLE_SEQ.NEXTVAL, #{RADARARCHIVOID}, #{PLACA}, #{FECHA}, #{HORA}, #{TDSKUID}, #{UT}, #{VELOCIDADDETECTADA}, SYSDATE, "+
			"	#{PLACA}, #{NOMBRE}, #{APELLIDOPAT}, #{APELLIDOMAT}, #{CALLE},#{NUMEXT}, #{NUMINT}, #{COLONIA}, #{CP}, #{ENTIDAD},#{DELEGACION}, "+
			" 	#{TELEFONO}, #{MARCA}, #{SUBMAR}, #{MODELO},#{NUMSERIE},#{NUMMOTOR},1,0,SYSDATE,#{ACTIVO},#{DIPLICADO},#{ARTID})";
	*/
	
	String INSERT_RADAR_ARCHIVO_DETALLE_COMPLETO = 
			"INSERT INTO RADAR_ARCHIVO_DETALLE (ID, RADAR_ARCHIVO_ID, PLACA, FECHA, HORA, TDSKUID, UT, VELOCIDAD_DETECTADA, FECHA_CREACION, "+
			"	PLACA_SF,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,CALLE,NUM_EXTERIOR,NUM_INTERIOR,COLONIA,CODIGO_POSTAL,ENTIDAD_FEDERATIVA,MUNICIPIO,"+
			"	TELEFONO,MARCA,SUBMARCA,MODELO,SERIE,MOTOR,WS_SETRAVI_CONSULTADO,ERROR_WS_SETRAVI,FECHA_CONSULTA_WS_SETRAVI,ACTIVO,DUPLICADO,ART_ID, OFICIAL_PLACA, OFICIAL_NOMBRE, ART_NUMERO,"+
			" 	ART_FRACCION, ART_PARRAFO, ART_INCISO, ART_UNIDAD_CUENTA) "+
			"VALUES (RADAR_ARCHIVO_DETALLE_SEQ.NEXTVAL, #{RADARARCHIVOID}, #{PLACA}, #{FECHA}, #{HORA}, #{TDSKUID}, #{UT}, #{VELOCIDADDETECTADA}, SYSDATE, "+
			"	#{PLACA}, #{NOMBRE}, #{APELLIDOPAT}, #{APELLIDOMAT}, #{CALLE},#{NUMEXT}, #{NUMINT}, #{COLONIA}, #{CP}, #{ENTIDAD},#{DELEGACION}, "+
			" 	#{TELEFONO}, #{MARCA}, #{SUBMAR}, #{MODELO},#{NUMSERIE},#{NUMMOTOR},1,0,SYSDATE,#{ACTIVO},#{DUPLICADO},#{ARTID},#{OFICIALPLACA}, #{NOMBREOFICIAL},#{ARTNUMERO},#{ARTFRACCION}, "+
			" 	#{ARTPARRAFO},#{ARTINCISO},#{ARTUNIDADCUENTA})";
	
	String INSERT_RADAR_ARCHIVO_DETALLE_FORANEO = 
			"INSERT INTO RADAR_ARCHIVO_DETALLE (ID, RADAR_ARCHIVO_ID, PLACA, FECHA, HORA, TDSKUID, UT, VELOCIDAD_DETECTADA, FECHA_CREACION,"+
			" 	PLACA_SF,WS_SETRAVI_CONSULTADO,ERROR_WS_SETRAVI,FECHA_CONSULTA_WS_SETRAVI,ACTIVO,DUPLICADO) "+
			"VALUES (RADAR_ARCHIVO_DETALLE_SEQ.NEXTVAL, #{RADARARCHIVOID}, #{PLACA}, #{FECHA}, #{HORA}, #{TDSKUID}, #{UT}, #{VELOCIDADDETECTADA}, SYSDATE, "+
			"	#{PLACA}, 1,0,SYSDATE,#{ACTIVO},#{DIPLICADO})";
	
	String CANTIDAD_PROCESADOS_CANCELADOS=
			"SELECT COUNT(*) as cantidad_canceladas FROM radar_archivo_detalle WHERE   radar_archivo_id = #{radarArchivoId} AND  activo = #{activoInactivo}";
	
	String UPDATE_CANCELA_DETECCIONES =
			"UPDATE RADAR_ARCHIVO_DETALLE "
			+ "	SET "
			+ "	activo = 0, "
			+ "	proceso_cancelado = 1, "
			+ "	complementado = 0 "
			+ "WHERE "
			+ "	radar_archivo_id = #{radarArchivoId}";
	
	String DELETE_DETECIONES_CANCELACION =
			"DELETE	FROM RADAR_ARCHIVO_DETALLE "+
			"WHERE 	RADAR_ARCHIVO_ID = #{radarArchivoId}";
	
	String INSERTAR_FOTOMULTA_DETALLE = "INSERT  INTO RADAR_ARCHIVO_DETALLE " +
	    	"(ID, RADAR_ARCHIVO_ID, PLACA, FECHA, HORA, TDSKUID, UT, VELOCIDAD_DETECTADA, FECHA_CREACION, NOMBRE, "
	    	+ "APELLIDO_PATERNO, APELLIDO_MATERNO, CALLE, NUM_EXTERIOR, NUM_INTERIOR, COLONIA, MUNICIPIO, "
	    	+ "CODIGO_POSTAL, ENTIDAD_FEDERATIVA, MARCA, SUBMARCA, MODELO, TELEFONO, SERIE, MOTOR, ART_ID, CP_MODIFICADO_POR,"
	    	+ "OFICIAL_PLACA, OFICIAL_NOMBRE) " +
	    "VALUES " +
	        "(RADAR_ARCHIVO_DETALLE_SEQ.NEXTVAL, #{loteId}, #{placa}, #{fecha}, #{hora}, #{tdskuid}, #{ut}, "
	        + "#{velocidadDetectada}, SYSDATE, #{nombre}, #{apellidoPaterno}, #{apellidoMaterno}, #{calle}, #{numExterior}, "
	        + "#{numInterior}, #{colonia}, #{municipio}, #{codigoPostal}, #{entidadFederativa}, #{marca}, #{submarca}, "
	        + "#{modelo}, #{telefono}, #{serie}, #{motor}, #{articuloId}, #{modificadoPor}, #{oficialPlaca}, #{oficialNombre})";
	
	
	String GET_TOTALES_ARCHIVO_BY_ESTATUS_PROCESO_LIBERADAS = "select count(id) as ARCHIVOTOTAL,'TOTAL' as ARCHIVOCONCEPTO "+ 
															"from radar_archivo_detalle_hist " +
															"where activo = 1 and radar_archivo_id = #{archivoId} "+
															"group by radar_archivo_id "+
															"union "+
															"select count(id),'RECHAZADAS' from radar_archivo_detalle_hist "+
															"where activo = 0 and radar_archivo_id = #{archivoId} "+
															"group by radar_archivo_id "+
															"union "+
															"select count(id),'COMPLEMENTADAS' from radar_archivo_detalle_hist "+
															"where activo = 1 and complementado = 1 and radar_archivo_id = #{archivoId} "+
															"group by radar_archivo_id "+
															"union "+
															"select count(id),'LIBERADAS' from radar_archivo_detalle_hist "+
															"where activo = 1 and liberado = 1 and radar_archivo_id = #{archivoId} "+
															"group by radar_archivo_id ";
	
	String GET_TOTALES_ARCHIVO_BY_ESTATUS_PROCESO_CANCELADO = "select count(id) as ARCHIVOTOTAL,'TOTAL' as ARCHIVOCONCEPTO "+
																"from radar_archivo_detalle_hist " +
																"where proceso_cancelado = 1 and radar_archivo_id = #{archivoId} "+
																"group by radar_archivo_id "+
																"union "+
																"select count(id),'RECHAZADAS' from radar_archivo_detalle_hist "+
																"where proceso_cancelado = 1 and activo = 0 and radar_archivo_id = #{archivoId} "+
																"group by radar_archivo_id "+
																"union "+
																"select count(id),'COMPLEMENTADAS' from radar_archivo_detalle_hist "+
																"where proceso_cancelado = 1 and complementado = 1 and radar_archivo_id = #{archivoId} "+
																"group by radar_archivo_id "+
																"union "+
																"select count(id),'LIBERADAS' from radar_archivo_detalle_hist "+
																"where proceso_cancelado = 1 and liberado = 1 and radar_archivo_id = #{archivoId} "+
																"group by radar_archivo_id";
	
	String GET_TOTALES_ARCHIVO_BY_OTHER_ESTATUS_PROCESO = "select count(id) as ARCHIVOTOTAL,'TOTAL' as ARCHIVOCONCEPTO " +
															"from radar_archivo_detalle "+
															"where activo = 1 and radar_archivo_id = #{archivoId} "+
															"group by radar_archivo_id "+
															"union "+
															"select count(id),'RECHAZADAS' from radar_archivo_detalle "+
															"where activo = 0 and radar_archivo_id = #{archivoId} "+
															"group by radar_archivo_id "+
															"union "+
															"select count(id),'COMPLEMENTADAS' from radar_archivo_detalle "+
															"where activo = 1 and complementado = 1 and radar_archivo_id = #{archivoId} "+
															"group by radar_archivo_id "+
															"union "+
															"select count(id),'LIBERADAS' from radar_archivo_detalle "+
															"where activo = 1 and liberado = 1 and radar_archivo_id = #{archivoId} "+
															"group by radar_archivo_id";
	
	String BUSCA_INFORMACION_FOTOMULTA = "SELECT tdskuid||'|'|| linea_captura||'|'||" +
			"to_char(fecha_vencimiento,'dd/mm/yyyy')||'|'||" +
			"importe_infraccion||'|'||" +
			"importe_derechos||'|'||" +
			"importe_actualizacion||'|'||" +
			"importe_recargos||'|'||" +
			"importe_descuento||'|'||" +
			"importe_total as registro " +
			"FROM radar_archivo_detalle " +
			"WHERE radar_Archivo_id= #{archivoId} " +
			"AND COMPLEMENTADO=1";
	
	String CANTIDAD_PROCESADOS_CANCELADOS_FM = 
			"SELECT COUNT(*) as cantidad_canceladas "
			+ "FROM RADAR_ARCHIVO_DETALLE "
			+ "WHERE "
			+ "	RADAR_ARCHIVO_ID = #{radarArchivoId} "
			+ "	AND ACTIVO = #{activoInactivo}";
	
	String UPDATE_CANCELA_DETECCIONES_FM =
			"UPDATE RADAR_ARCHIVO_DETALLE "
			+ "SET ACTIVO = 0, PROCESO_CANCELADO = 1, COMPLEMENTADO =0 "
			+ "WHERE RADAR_ARCHIVO_ID = #{radarArchivoId}";
	
	String GET_TOTALES_ARCHIVO_BY_ESTATUS_PROCESO_LIBERADAS_FM = 
		"SELECT count(id) as ARCHIVOTOTAL,'TOTAL' as ARCHIVOCONCEPTO "
		+ "FROM RADAR_ARCHIVO_DETALLE_HIST "
		+ "WHERE activo = 1 AND radar_archivo_id = #{archivoId} "
		+ "GROUP BY radar_archivo_id "
		+ "UNION "
		+ "SELECT count(id),'RECHAZADAS' "
		+ "FROM RADAR_ARCHIVO_DETALLE_HIST "
		+ "WHERE activo = 0 AND radar_archivo_id = #{archivoId} "
		+ "GROUP BY radar_archivo_id "
		+ "UNION "
		+ "SELECT count(id),'COMPLEMENTADAS' "
		+ "FROM RADAR_ARCHIVO_DETALLE_HIST "
		+ "WHERE activo = 1 AND complementado = 1 AND radar_archivo_id = #{archivoId} "
		+ "GROUP BY radar_archivo_id "
		+ "UNION "
		+ "SELECT count(id),'LIBERADAS' "
		+ "FROM RADAR_ARCHIVO_DETALLE_HIST "
		+ "WHERE activo = 1 AND liberado = 1 AND radar_archivo_id = #{archivoId} "
		+ "GROUP BY radar_archivo_id ";
	
	String GET_TOTALES_ARCHIVO_BY_ESTATUS_PROCESO_CANCELADO_FM = 
		"select count(id) as ARCHIVOTOTAL,'TOTAL' as ARCHIVOCONCEPTO "+
		"from RADAR_ARCHIVO_DETALLE_HIST " +
		"where proceso_cancelado = 1 and radar_archivo_id = #{archivoId} "+
		"group by radar_archivo_id "+
		"union "+
		"select count(id),'RECHAZADAS' "+
		"from RADAR_ARCHIVO_DETALLE_HIST "+
		"where proceso_cancelado = 1 and activo = 0 and radar_archivo_id = #{archivoId} "+
		"group by radar_archivo_id "+
		"union "+
		"select count(id),'COMPLEMENTADAS' "+
		"from RADAR_ARCHIVO_DETALLE_HIST "+
		"where proceso_cancelado = 1 and complementado = 1 and radar_archivo_id = #{archivoId} "+
		"group by radar_archivo_id "+
		"union "+
		"select count(id),'LIBERADAS' "+
		"from RADAR_ARCHIVO_DETALLE_HIST "+
		"where proceso_cancelado = 1 and liberado = 1 and radar_archivo_id = #{archivoId} "+
		"group by radar_archivo_id";
	
	String GET_TOTALES_ARCHIVO_BY_OTHER_ESTATUS_PROCESO_FM = 
		"select count(id) as ARCHIVOTOTAL,'TOTAL' as ARCHIVOCONCEPTO " +
		"from RADAR_ARCHIVO_DETALLE "+
		"where activo = 1 and radar_archivo_id = #{archivoId} "+
		"group by radar_archivo_id "+
		"union "+
		"select count(id),'RECHAZADAS' "+
		"from RADAR_ARCHIVO_DETALLE "+
		"where activo = 0 and radar_archivo_id = #{archivoId} "+
		"group by radar_archivo_id "+
		"union "+
		"select count(id),'COMPLEMENTADAS' "+
		"from RADAR_ARCHIVO_DETALLE "+
		"where activo = 1 and complementado = 1 and radar_archivo_id = #{archivoId} "+
		"group by radar_archivo_id "+
		"union "+
		"select count(id),'LIBERADAS' "+
		"from RADAR_ARCHIVO_DETALLE "+
		"where activo = 1 and liberado = 1 and radar_archivo_id = #{archivoId} "+
		"group by radar_archivo_id";
	
	String DELETE_DETECIONES_CANCELACION_FM =
		"DELETE	FROM RADAR_ARCHIVO_DETALLE "+
		"WHERE 	RADAR_ARCHIVO_ID = #{radarArchivoId}";
	
	String UPDATE_COMPLEMENTA_OFICIAL_NOMBRE =
		"UPDATE RADAR_ARCHIVO_DETALLE "
		+ "	SET "
		+ "		OFICIAL_NOMBRE = ( "
		+ "			SELECT NVL(trim(EMP_APE_PATERNO ||' '|| EMP_APE_MATERNO ||' '|| EMP_NOMBRE),null) AS OFICIAL_NOMBRE "
		+ "			FROM EMPLEADOS "
		+ "			WHERE EMP_PLACA = RADAR_ARCHIVO_DETALLE.OFICIAL_PLACA "
		+ "		) "
		+ "	WHERE NVL(OFICIAL_NOMBRE,null) is null AND RADAR_ARCHIVO_ID = #{radarArchivoId}";
	
	String UPDATE_VALIDA_FORMATO_PLACA = 
		"UPDATE RADAR_ARCHIVO_DETALLE "
		+ "	SET "
		+ "		ACTIVO = 0, "
		+ "		OFICIAL_NOMBRE = -3 "
		+ "	WHERE "
		+ "		EXISTS ( "
		+ "			SELECT * "
		+ "			FROM TAI006_FM_VALIDA_MATRICULA "
		+ "			WHERE PKG_ENCRIPCION.DESENCRIPTA(PLACA) = RADAR_ARCHIVO_DETALLE.PLACA "
		+ "		) "
		+ "		and RADAR_ARCHIVO_ID = #{radarArchivoId}";
	
	@SuppressWarnings("rawtypes")
	@Insert(INSERT_RADAR_ARCHIVO_DETALLE_COMPLETO)
	public Integer insertaRadarArchivoDetalleCompleto(Map deteccion);
	
	@SuppressWarnings("rawtypes")
	@Insert(INSERT_RADAR_ARCHIVO_DETALLE_FORANEO)
	public Integer insertaRadarArchivoDetalleForaneo(Map deteccion);
	
	@Select(CANTIDAD_PROCESADOS_CANCELADOS)
	public Integer buscaCantidadDeDetecciones(@Param("radarArchivoId") Long radarArchivoId, @Param("activoInactivo") Integer activoInactivo);
	
	@Update(UPDATE_CANCELA_DETECCIONES)
	public Integer cancelaDeteccionesPorArchivo(@Param("radarArchivoId") Long radarArchivoId);
	
	@Delete(DELETE_DETECIONES_CANCELACION)
	public Integer eliminaDeteccionesCancelacionLote(@Param("radarArchivoId") Long radarArchivoId);
	
	@Insert(INSERTAR_FOTOMULTA_DETALLE)
	public Integer insertarFotomultaDetalle(ReporteDeteccionesVO reporteDeteccionesVO);
	
	/****/
	@Select(GET_TOTALES_ARCHIVO_BY_ESTATUS_PROCESO_LIBERADAS)
	public List<ArchivosTotalesVO> totalesArchivoLiberado(@Param("archivoId") Long archivoId);
	
	@Select(GET_TOTALES_ARCHIVO_BY_ESTATUS_PROCESO_CANCELADO)
	public List<ArchivosTotalesVO> totalesArchivoCancelado(@Param("archivoId") Long archivoId);
	
	@Select(GET_TOTALES_ARCHIVO_BY_OTHER_ESTATUS_PROCESO)
	public List<ArchivosTotalesVO> totalesArchivo(@Param("archivoId") Long archivoId);
	
	@Select(BUSCA_INFORMACION_FOTOMULTA)
	public List<String> buscaInformacionFotoMulta(@Param("archivoId") Long archivoId);
	
	@Select(CANTIDAD_PROCESADOS_CANCELADOS_FM)
	public Integer buscaCantidadDeDeteccionesFM(
		@Param("radarArchivoId") Long radarArchivoId, 
		@Param("activoInactivo") Integer activoInactivo
	);
	
	@Update(UPDATE_CANCELA_DETECCIONES_FM)
	public Integer cancelaDeteccionesPorArchivoFM(
		@Param("radarArchivoId") Long radarArchivoId
	);
	
	@Select(GET_TOTALES_ARCHIVO_BY_ESTATUS_PROCESO_LIBERADAS_FM)
	public List<ArchivosTotalesVO> totalesArchivoLiberadoFM(@Param("archivoId") Long archivoId);
	
	@Select(GET_TOTALES_ARCHIVO_BY_ESTATUS_PROCESO_CANCELADO_FM)
	public List<ArchivosTotalesVO> totalesArchivoCanceladoFM(@Param("archivoId") Long archivoId);
	
	@Select(GET_TOTALES_ARCHIVO_BY_OTHER_ESTATUS_PROCESO_FM)
	public List<ArchivosTotalesVO> totalesArchivoFM(@Param("archivoId") Long archivoId);
	
	@Delete(DELETE_DETECIONES_CANCELACION_FM)
	public Integer eliminaDeteccionesCancelacionLoteFM(@Param("radarArchivoId") Long radarArchivoId);
	
	@Update(UPDATE_COMPLEMENTA_OFICIAL_NOMBRE)
	public Integer complementaOficialNombre(@Param("radarArchivoId") Long radarArchivoId);
	
	@Update(UPDATE_VALIDA_FORMATO_PLACA)
	public Integer validaFormatoPlaca(@Param("radarArchivoId") Long radarArchivoId);
}
