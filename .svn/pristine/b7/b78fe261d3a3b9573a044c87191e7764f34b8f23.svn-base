package mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.CatalogoExpedienteVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.TipoExpedienteVO;

@Mapper
public interface DigitalizacionMyBatisDAO {

	
	String CONFIRM_INFRACNUM_EXISTE = "SELECT COUNT(INFRAC_NUM) FROM INFRACCIONES WHERE INFRAC_NUM = #{infracNum}";
	
	@Select(value = CONFIRM_INFRACNUM_EXISTE)
	public Integer corroborarNumeroInfraccion(@Param("infracNum") String infracNum);
	
	String GET_CATALOGO_EXPEDIENTE = "SELECT DESCRIPCION, TIPO FROM PAGOS_CAT_EXPEDIENTE WHERE ACTIVO = 1 ORDER BY CAT_TIPO_EXP_ID ASC";
	
	@Select(value = GET_CATALOGO_EXPEDIENTE)
	public List<CatalogoExpedienteVO> obtenerCatalogoExpediente();
	
	String GET_CAPTURAS_EXPEDIENTE = "SELECT TIPO FROM PAGOS_IMAGENES WHERE INFRAC_NUM = #{ infracNum } AND ACTIVO = 1";
	
	@Select(value = GET_CAPTURAS_EXPEDIENTE)
	public List<TipoExpedienteVO> obtenerCapturasExpediente(@Param("infracNum") String infracNum);
	
	String GET_RUTA_LOCAL_EXPEDIENTE = "SELECT PATH || '/' || NOMBRE_ARCHIVO AS PATH FROM V_IMAGENES_EXPEDIENTE WHERE NOMBRE_ARCHIVO LIKE '%'||#{infracNum}|| '_' ||#{idArchivo}|| '_' ||#{tipo}||'%'";
	
	@Select(value = GET_RUTA_LOCAL_EXPEDIENTE)
	public String obtenerRutaLocalExpediente(@Param("infracNum") String infracNum, @Param("idArchivo") Long idArchivo, @Param("tipo") String tipo);
	
	String GET_IMAGEN_EXPEDIENTE = "SELECT ARCHIVO FROM PAGOS_IMAGENES WHERE INFRAC_NUM = #{infracNum} AND TIPO = #{tipo}";
	
	@Select(value = GET_IMAGEN_EXPEDIENTE)
	public byte[] buscarCapturaExpedientePorTipo(@Param("infracNum") String infracNum,@Param("tipo") String tipo);
	
//	Obtener nombre de catálogo del documento
	String GET_NOMBRE_CAT_DOCUMENTO = "SELECT PC.DESCRIPCION AS DOCUMENTO "
									+"FROM PAGOS_IMAGENES PI INNER JOIN PAGOS_CAT_EXPEDIENTE PC ON PI.TIPO = PC.TIPO "
									+"	WHERE PI.INFRAC_NUM = #{infracNum} "
									+"		AND PI.TIPO = #{tipo} "
									+"	    AND PI.ACTIVO = 1 "
									+"	    ORDER BY PC.CAT_TIPO_EXP_ID";
	
	@Select(value = GET_NOMBRE_CAT_DOCUMENTO)
	public String obtenerCatDocumento(@Param("infracNum") String infracNum, @Param("tipo") String tipo);
	
	
//	Consultas de la infracción asociada al parámetro buscado
	static final String CONSULTA_INFRAC_DEPOSITO = "SELECT (PLACA || '|' || NCI || '|' || INFRACCION) as DATA FROM V_PAGOS_BUSQ_DIGITALIZACION WHERE ROWNUM = 1 ";
	
//	CONSULTA DE INFRACCION PARA DIGITALIZAR	
	@Select(CONSULTA_INFRAC_DEPOSITO + "AND INFRACCION = #{infracNum}")
	public String consultaExpedienteInfraccion(@Param("infracNum") String infracNum);
	
	@Select(CONSULTA_INFRAC_DEPOSITO + "AND PLACA = #{infracNum}")
	public String consultaExpedientePlaca(@Param("infracNum") String infracNum);
	
	@Select(CONSULTA_INFRAC_DEPOSITO + "AND IMPRESA = #{infracNum}")
	public String consultaExpedienteImpresa(@Param("infracNum") String infracNum);
	
	@Select(CONSULTA_INFRAC_DEPOSITO + "AND NCI = #{infracNum}")
	public String consultaExpedienteNCI(@Param("infracNum") String infracNum);
	
	@Select("SELECT COUNT(*) FROM TABLE (CAST (FN_PAGOS_INFRACCIONES_CALCULO(#{nci}, #{empPlaca}) AS T_PAGOS_INFRACCIONES))")
	Integer consultaInfraccionesAsociadas(@Param("nci") String nci, @Param("empPlaca") Long empPlaca);

	@Select(" SELECT PATH || '/' || NOMBRE_ARCHIVO AS PATH FROM V_IMAGENES_MOVIMIENTOS WHERE NOMBRE_ARCHIVO LIKE '%'||#{infracNum}|| '_' ||#{tipo}||'%' || '_' ||#{IdMovimiento}||'%' ")
	public String obtenerRutaLocalExpedienteMovimiento(
			@Param("infracNum") String infracNum, @Param("tipo") String tipo, @Param("IdMovimiento")String imgMovId);
}
