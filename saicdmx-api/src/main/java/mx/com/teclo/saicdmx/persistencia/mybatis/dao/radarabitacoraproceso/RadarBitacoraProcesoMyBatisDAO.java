package mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarabitacoraproceso;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.com.teclo.saicdmx.persistencia.vo.comuns.ComboValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.radarbitacoraprocesoestatus.RadarBitacoraProcesoComplementacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarDeteccionesCentroReparto;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarDeteccionesCentroRepartoV2;

/**
 * 
 * @author UnitisDes0416
 *
 */
@Mapper
public interface RadarBitacoraProcesoMyBatisDAO {
	
	String INSERT_RADAR_BITACORA_PROCESO = 
			"INSERT INTO RADAR_BITACORA_PROCESO ("
			+ "	RADAR_ARCHIVO_ID, ESTATUS_PROCESO_ID, FECHA, CREADO_POR"
			+ ") VALUES ("
			+ "	#{radarArchivoId}, #{estatusProcesoId}, SYSDATE, #{empleadoId}"
			+ ")";
	
	String UPDATE_RADAR_ARCHIVO = 
			"UPDATE radar_archivo SET estatus_proceso_id = #{estatusProcesoId}, en_proceso = #{enProceso}, modificado_por = #{empleadoId}, "
			+ " ultima_modificacion = systimestamp WHERE id = #{radarArchivoId}";
	
	String PROCESO_CANCELADO = "SELECT cancelado FROM radar_archivo WHERE id = #{radarArchivoId}";
	
	String BUSCA_ESTASTUS_RADAR_ARCHIVO_EN_PROCESO = 
			"SELECT  RBP.ESTATUS_PROCESO_ID AS estatusProcesoId, "+
			"        REP.NOMBRE AS estausNombre, "+
			"        RBP.FECHA AS fechaProceso, "+
			"        REP.CD_ORDEN_PROCESO AS cdOrderProceso "+
			"FROM    RADAR_BITACORA_PROCESO RBP "+
			"        JOIN RADAR_ESTATUS_PROCESO REP "+
			"            ON RBP.ESTATUS_PROCESO_ID = REP.ID "+
			"WHERE   RBP.RADAR_ARCHIVO_ID = #{radarArchivoId} "+
			"ORDER BY RBP.FECHA DESC, REP.CD_ORDEN_PROCESO";
	
//	String BUSCAR_DETECCIONES_CENTRO_REPARTO_VALIDAS = 
//			"SELECT id AS idDeteccion, "
//		    +"		placa AS placa, "
//			+"		 TO_CHAR(to_date(fecha,'dd.MM.YY'),'dd/MM/yyyy') AS fecha, " 
//			+"		 hora AS hora, " 
//			+"		 nvl(apellido_paterno, ' ') || ' ' || nvl(apellido_materno, ' ') || ' ' || nvl(nombre, ' ') AS nombre, " 
//			+"		 nvl(calle,' ')  || ' ' || nvl(num_exterior,' ') || ' ' || nvl(num_interior,' ') || ' ' || nvl(colonia,' ') AS domicilio, " 
//			+"		 nvl(codigo_postal, ' ') AS codigoPostal, " 
//			+"		 trim(to_char(nvl(centro_reparto, 0), '00009')) AS centroReparto, " 
//			+"		 nvl(municipio, ' ') AS municipio, " 
//			+"		 nvl(entidad_federativa, ' ') AS entidadFederativa " 
//			+"		 FROM radar_archivo_detalle   WHERE activo = 1 " 
//			+"		 AND sin_centro_reparto = 0 " 
//			+"		 AND radar_archivo_id =  #{radarArchivoId}  " 
//            +" ORDER BY centroReparto,codigoPostal,placa";
	String BUSCAR_DETECCIONES_CENTRO_REPARTO_VALIDAS = 
			"SELECT  COUNT(id) AS idDeteccion, "
					+ "nvl(codigo_postal, ' ') AS codigoPostal, trim(to_char(nvl(centro_reparto, 0), '00009')) AS centroReparto, "
					+ "nvl(municipio, ' ') AS municipio, nvl(entidad_federativa, ' ') AS entidadFederativa "
					+ "FROM radar_archivo_detalle "
					+ "WHERE activo = 1	AND duplicado=0 AND sin_centro_reparto = 0 AND radar_archivo_id = #{radarArchivoId} "
					+ "GROUP BY centro_reparto,codigo_postal,municipio,entidad_federativa";
	
	
//	String BUSCAR_DETECCIONES_CENTRO_REPARTO_INVALIDAS = 
//			"SELECT id AS idDeteccion, " 
//			+"       placa AS placa, " 
//			+"		 TO_CHAR(to_date(fecha,'dd.MM.YY'),'dd/MM/yyyy') AS fecha, " 
//			+"		 hora AS hora, " 
//			+"		 nvl(apellido_paterno, ' ') || ' ' || nvl(apellido_materno, ' ') || ' ' || nvl(nombre, ' ') AS nombre, " 
//			+"		 nvl(calle,' ')  || ' ' || nvl(num_exterior,' ') || ' ' || nvl(num_interior,' ') || ' ' || nvl(colonia,' ') AS domicilio, " 
//			+"		 nvl(codigo_postal, ' ') as codigoPostal, " 
//			+"		 trim(to_char(nvl(centro_reparto, 0), '00009')) AS centroReparto, " 
//			+"		 nvl(municipio, ' ') as municipio, " 
//			+"		 nvl(entidad_federativa, ' ') as entidadFederativa " 
//			+"		 FROM radar_archivo_detalle   WHERE activo = 0 " 
//			
//			+"		 AND sin_centro_reparto = 1 " 
//					 
//			+"		AND radar_archivo_id = #{radarArchivoId} "  
//			+"		ORDER BY centroReparto,codigoPostal,placa";
	String BUSCAR_DETECCIONES_CENTRO_REPARTO_INVALIDAS = 
			 "SELECT  COUNT(id) AS idDeteccion, nvl(codigo_postal, ' ') AS codigoPostal, "
						+ "trim(to_char(nvl(centro_reparto, 0), '00009')) AS centroReparto, nvl(municipio, ' ') AS municipio, "
						+ "ENTIDAD_FEDERATIVA AS municipio, "
						+ "nvl(entidad_federativa, ' ') AS entidadFederativa "
						+ "FROM radar_archivo_detalle "
						+ "WHERE activo = 0 AND duplicado=0 AND sin_centro_reparto = 1 AND radar_archivo_id =  #{radarArchivoId} "
						+ "GROUP BY centro_reparto,codigo_postal,municipio,entidad_federativa";
	
	
//	String DESHABILITA_DETECCION_UPDATE =
//			"UPDATE radar_archivo_detalle "
//					+ "SET activo = 0, "
//					
//					+"sin_centro_reparto = 1, " + "codigo_postal = 0, "
//					
//					+"centro_reparto = 0, "
//					
//					+"cp_modificado_por = #{id}, "
//					
//					+"fecha_cp_modificado = SYSDATE " + "WHERE id = #{idCP} "
//					+ "AND radar_archivo_id = #{radarArchivoId} ";
	
	String DESHABILITA_DETECCION_UPDATE =
			"UPDATE radar_archivo_detalle " + "SET activo = 0, " + "sin_centro_reparto = 1, "
	+ "codigo_postal = 0, " + "cp_modificado_por = #{id}, "
	+ "fecha_cp_modificado = SYSDATE " + "WHERE codigo_postal = #{idCP} " + "AND radar_archivo_id = #{radarArchivoId} ";
		
//	String HABILITA_DETECCION_UPDATE = 
//			"UPDATE radar_archivo_detalle "
//					+ "SET activo = 1, "
//					
//					+"sin_centro_reparto = 0, " + "codigo_postal = #{cP}, "
//					
//					+"cp_modificado_por = #{id}, "
//				
//					+"fecha_cp_modificado = SYSDATE " + "WHERE id = #{idCP} "
//					+ "AND radar_archivo_id = #{archivoId}";
	String HABILITA_DETECCION_UPDATE = 
			"UPDATE radar_archivo_detalle " + "SET activo = 1, " + "sin_centro_reparto = 0, centro_reparto=0, "
	+ "codigo_postal = #{cP}, " + "cp_modificado_por = #{id}, " + "fecha_cp_modificado = SYSDATE "
	+ "WHERE codigo_postal = 0 AND centro_reparto = #{idCP} AND radar_archivo_id = #{archivoId}";
	
	String INSERT_RADAR_BITACORA_PROCESO_FM = 
			"INSERT INTO TAI013B_FM_BIT_PROCESO (ID_FM_LOTE, ID_ESTATUS_PROCESO, FH_CREACION, CD_CREADO_POR) "+
			"VALUES (#{radarArchivoId}, #{estatusProcesoId}, SYSDATE, #{empleadoId})";

	/**
	 * @author UnitisDes0416
	 * @param radarArchivoId Long
	 * @param estatusProcesoId Long
	 * @param empleadoId Long
	 * @return Integer
	 */
	@Insert(INSERT_RADAR_BITACORA_PROCESO)
	public Integer insertaRadarBitacoraProceso(@Param("radarArchivoId")Long radarArchivoId,  @Param("estatusProcesoId")Long estatusProcesoId,  @Param("empleadoId")Long empleadoId);
	
	/**
	 * @author UnitisDes0416
	 * @param radarArchivoId Long
	 * @return List<RadarBitacoraProcesoComplementacionVO>
	 */
	@Select(BUSCA_ESTASTUS_RADAR_ARCHIVO_EN_PROCESO)
	public List<RadarBitacoraProcesoComplementacionVO> buscarRadarArchivoEnProceso(@Param("radarArchivoId") Long radarArchivoId);

//	@Select(BUSCAR_DETECCIONES_CENTRO_REPARTO_VALIDAS)
//	public List<RadarDeteccionesCentroReparto> obtenerListaDeteccionesValidas(
//			@Param("radarArchivoId") Long radarArchivoId);
//	
//	@Select(BUSCAR_DETECCIONES_CENTRO_REPARTO_INVALIDAS)
//	public List<RadarDeteccionesCentroReparto> obtenerListaDeteccionesInvalidas(
//			@Param("radarArchivoId") Long radarArchivoId);
	
	@Select(BUSCAR_DETECCIONES_CENTRO_REPARTO_VALIDAS)
	public List<RadarDeteccionesCentroRepartoV2> obtenerListaDeteccionesValidas(
			@Param("radarArchivoId") Long radarArchivoId);
	
	@Select(BUSCAR_DETECCIONES_CENTRO_REPARTO_INVALIDAS)
	public List<RadarDeteccionesCentroRepartoV2> obtenerListaDeteccionesInvalidas(
			@Param("radarArchivoId") Long radarArchivoId);
	
	
	@Insert(INSERT_RADAR_BITACORA_PROCESO)
	public void insertaRadarBitacora(@Param("radarArchivoId")Long radarArchivoId,  @Param("estatusProcesoId")int estatusProcesoId,  @Param("empleadoId")Long empleadoId);

	@Select(UPDATE_RADAR_ARCHIVO)
	public void actualizarArchivoProcesoId(
			@Param("radarArchivoId")Long radarArchivoId,  
			@Param("estatusProcesoId")int estatusProcesoId,  
			@Param("empleadoId")Long empleadoId,
			@Param("enProceso")int enProceso);
	
	@Select(PROCESO_CANCELADO)
	public boolean procesoCancelado(@Param("radarArchivoId")Long radarArchivoId);

	@Update(DESHABILITA_DETECCION_UPDATE)
	public boolean deshabilitaDeteccion(
			@Param("idCP")Long idCP,
			@Param("radarArchivoId")Long radarArchivoId,
			@Param("id")Long id);
	
	String CAMBIA_THIS_CP =
			"UPDATE radar_archivo_detalle " + "SET activo = 1, " + "sin_centro_reparto = 1, "
	+ "codigo_postal = #{Cp}, " + "cp_modificado_por = #{idUsu}, "
	+ "fecha_cp_modificado = SYSDATE " + "WHERE id = #{id} " + "AND radar_archivo_id = #{radarArchivoId} ";
	
	String SET_ALL_CR =
			"UPDATE radar_archivo_detalle " + "SET activo = 1, " + "sin_centro_reparto = 1, "
	+ "CENTRO_REPARTO = 0 "
	+ "WHERE radar_archivo_id = #{radarArchivoId} ";
	
	@Update(SET_ALL_CR) 
	public boolean cambiaAllCr(
			@Param("radarArchivoId")Long radarArchivoId);
	
	@Update(CAMBIA_THIS_CP) 
	public boolean cambiaThisCp(
			@Param("radarArchivoId")Long radarArchivoId,
			@Param("Cp")String Cp,
			@Param("id")Long id,
			@Param("idUsu")Long idUsu);
	
	@Update(HABILITA_DETECCION_UPDATE)
	public void HabilitaDeteccion(
			
			@Param("cP") String cP, 
			@Param("id") Long id, 
			@Param("idCP") Long idCP, 
			@Param("archivoId") Long archivoId);
	
	@Select("SELECT  descripcion, SALARIOS_MINIMOS_ID as valor "+
			   "   FROM    V_PERIODOS_SALARIOS_MINIMOS ")
	public List<ComboValuesVO> getAnioSalarioMinimo();
	
	@Insert(INSERT_RADAR_BITACORA_PROCESO_FM)
	public Integer insertaRadarBitacoraProcesoFM(
		@Param("radarArchivoId")Long radarArchivoId, 
		@Param("estatusProcesoId")Long estatusProcesoId, 
		@Param("empleadoId")Long empleadoId
	);

}
