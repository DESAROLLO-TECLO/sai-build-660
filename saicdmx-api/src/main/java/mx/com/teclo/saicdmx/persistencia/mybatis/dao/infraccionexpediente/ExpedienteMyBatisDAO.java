package mx.com.teclo.saicdmx.persistencia.mybatis.dao.infraccionexpediente;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionValidaImagenSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VConsultaExpediente;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VDirectorioDigitalizacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VImagenesHandHeldVO;
import mx.com.teclo.saicdmx.persistencia.vo.vconsultaexpediente.VImagenesIngresoVO;

@Mapper
public interface ExpedienteMyBatisDAO {
	
	String GET_V_CONSULTA_EXPEDIENTE = "SELECT NOMBRE_ARCHIVO AS NOMBREARCHIVO, TIPO AS TIPO "
			+ "FROM V_CONSULTA_EXPEDIENTE where infrac_num = #{valor} order by TIPO, NOMBRE_ARCHIVO";
	
	String GET_V_IMAGENES_INGRESO = "SELECT PATH AS PATH, NOMBRE_ARCHIVO AS NOMBRE_ARCHIVO "
			+ "FROM V_IMAGENES_INGRESO WHERE INFRAC_NUM = #{valor}";
	
	String GET_V_DIRECTORIO_DIGITALIZACIONES_A = "SELECT RUTA_ARCHIVO AS PATH "
			+ "FROM V_DIRECTORIOS_DIGITALIZACION WHERE ANV_REV = 'A' AND FOLIO = #{valor}";
	
	String GET_V_DIRECTORIO_DIGITALIZACIONES_R = "SELECT RUTA_ARCHIVO AS PATH "
			+ "FROM V_DIRECTORIOS_DIGITALIZACION WHERE ANV_REV = 'R' AND FOLIO = #{valor}";
	
	String GET_V_IMAGENES_HANDHELD = "SELECT PATH AS PATH, NOMBRE_ARCHIVO AS NOMBREARCHIVO "
			+ "FROM V_IMAGENES_HANDHELD WHERE INFRAC_NUM = #{valor}";
	
	String GET_V_DIRECTORIO_DIGITALIZACIONES = "SELECT "
			+ "SUBSTR(RUTA_ARCHIVO,1,1) UNIDAD, "
			+ "REPLACE(SUBSTR(RUTA_ARCHIVO,4),'\','/') PATH "
			+ "FROM V_DIRECTORIOS_DIGITALIZACION "
            + "WHERE ANV_REV = #{anvRev} AND FOLIO = #{valor}";
	
	String SP_INFRACCION_VALIDA_IMAGEN = "BEGIN SP_INFRACCION_VALIDA_IMAGEN (  "
			+ "#{p_emp_id}, "
			+ "#{p_infrac_num}, "
			+ "#{p_estatus}, "
			+ "#{p_resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+ "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT});"
			+ "end; ";
	
	String GET_V_RUTA_IMG_HANDHELD = "SELECT RUTA_IMG_HANDHELD FROM RADAR_PARAMETROS_WEB WHERE PARAMETRO_ID = 1";
	
	String EXISTEN_IMG_EXPEDIENTE 	= "SELECT COUNT(*) FROM V_CONSULTA_EXPEDIENTE WHERE INFRAC_NUM = #{infracNum}";
	
	String EXISTEN_IMG_INGRESO 		= "SELECT COUNT(*) FROM V_IMAGENES_INGRESO WHERE INFRAC_NUM = #{infracNum}";
	
	String EXISTEN_IMG_EXP_PAGO 	= "SELECT COUNT(*) FROM PAGOS_IMAGENES WHERE INFRAC_NUM = #{infracNum} AND ACTIVO = 1";
	
	String EXISTEN_IMG_HANDHELD 	= "SELECT COUNT(*) FROM V_IMAGENES_HANDHELD WHERE INFRAC_NUM = #{infracNum}";
	
	String EXISTEN_IMG_DIG_A 		= "SELECT COUNT(*) FROM V_DIRECTORIOS_DIGITALIZACION WHERE FOLIO = #{infracNum} AND ANV_REV = 'A'";
	
	String EXISTEN_IMG_DIG_R 		= "SELECT COUNT(*) FROM V_DIRECTORIOS_DIGITALIZACION WHERE FOLIO = #{infracNum} AND ANV_REV = 'R'";
	
	@Select(GET_V_CONSULTA_EXPEDIENTE)
	public List<VConsultaExpediente> getVConsultaExpediente(@Param("valor") String valor);
	
	@Select(GET_V_IMAGENES_INGRESO)
	public List<VImagenesIngresoVO> getVImagenesIngresoVO(@Param("valor") String valor);
	
	@Select(GET_V_DIRECTORIO_DIGITALIZACIONES_A)
	public List<VDirectorioDigitalizacionVO> getVDirectorioDigitalizacionVOA(@Param("valor") String valor);
	
	@Select(GET_V_DIRECTORIO_DIGITALIZACIONES_R)
	public List<VDirectorioDigitalizacionVO> getVDirectorioDigitalizacionVOR(@Param("valor") String valor);
	
	@Select(GET_V_DIRECTORIO_DIGITALIZACIONES)
	public List<VDirectorioDigitalizacionVO> getVDirectorioDigitalizacionVO(@Param("anvRev") String anvRev, @Param("valor") String valor);
	
	@Select(GET_V_IMAGENES_HANDHELD)
	public List<VImagenesHandHeldVO> getVImagenesHandHeldVO(@Param("valor") String valor);
	
	/**
	 * Ejecuta SP SP_INFRACCION_VALIDA_IMAGEN
	 * @author Kevin Ojeda
	 * @param InfraccionValidaImagenSPVO infraccionValidaImagenSPVO
	 * @return InfraccionValidaImagenSPVO
	 * @throws PersistenceException
	 */
	@Select(value = SP_INFRACCION_VALIDA_IMAGEN)
	@Options(statementType = StatementType.CALLABLE)
	public InfraccionValidaImagenSPVO InfraccionValidaImagenSPVO
		(InfraccionValidaImagenSPVO infraccionValidaImagenSPVO) throws PersistenceException;
	
	@Select(GET_V_RUTA_IMG_HANDHELD)
	public String getVRutaImgHandheld();
	
	@Select(EXISTEN_IMG_EXPEDIENTE)
	public Integer getExisteImgExpediente(
		@Param("infracNum") String infracNum);
	@Select(EXISTEN_IMG_INGRESO)
	public Integer getExisteImgIngreso(
		@Param("infracNum") String infracNum);
	@Select(EXISTEN_IMG_EXP_PAGO)
	public Integer getExisteImgExpPago(
		@Param("infracNum") String infracNum);
	@Select(EXISTEN_IMG_HANDHELD)
	public Integer getExisteImgHandHeld(
		@Param("infracNum") String infracNum);
	@Select(EXISTEN_IMG_DIG_A)
	public Integer getExisteImgDigA(
		@Param("infracNum") String infracNum);
	@Select(EXISTEN_IMG_DIG_R)
	public Integer getExisteImgDigR(
		@Param("infracNum") String infracNum);
}
