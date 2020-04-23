package mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarArchivoProcesado;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RadarArchivoEnvioMyBatisDAO {
	
	
	String UPDATE_ESTATUS_PREREENVIO = "update TAI031D_FC_PARAMETROS_FIRMA set ID_ESTATUS_PREREENVIO = (select ESTATUS_PROCESO_ID from RADAR_ARCHIVO where id = #{idArchivo}) where ID_LOTE = #{idArchivo}";
	
	String UPDATE_ESTATUS_RADAR_ARCHIVO = "update "
			+ "RADAR_ARCHIVO set "
			+ "ESTATUS_PROCESO_ID = #{estatusProceso}, "
			+ "NU_INTENTOS_ENVIO = 0, "
			+ "MODIFICADO_POR = #{empId}, "
			+ "ULTIMA_MODIFICACION = sysdate, "
			+ "EN_PROCESO = 1, "
			+ "ST_ENVIADO = -1 "
			+ "where ID = #{idArchivo}";
	
	String INSERT_BITACORA_RADAR_ARCHIVO = ""
			+ "insert into RADAR_BITACORA_PROCESO "
			+ "(RADAR_ARCHIVO_ID, ESTATUS_PROCESO_ID, FECHA, CREADO_POR) values "
			+ "(#{idArchivo}, #{estatusProceso}, sysdate, #{empId})";
	
	
	@Select(UPDATE_ESTATUS_PREREENVIO)
	public Integer guardaEstatusReenvio(
		@Param("idArchivo") Long idArchivo);
			
	@Select(UPDATE_ESTATUS_RADAR_ARCHIVO)
	public Integer updateEstatusRadarArchivo(
			@Param("idArchivo") Long idArchivo,
			@Param("estatusProceso") Integer estatusProceso,
			@Param("empId") Long empId);
	
	@Select(INSERT_BITACORA_RADAR_ARCHIVO)
	public Integer insertarBitacoraRadarArchvio(
			@Param("idArchivo") Long idArchivo,
			@Param("estatusProceso") Integer estatusProceso,
			@Param("empId") Long empId);
}
