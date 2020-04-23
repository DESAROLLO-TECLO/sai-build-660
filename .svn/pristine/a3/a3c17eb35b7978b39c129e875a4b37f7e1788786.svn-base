package mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.InfraccionImpugnacionInfracsVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VImpugnacionConsultaVO;
/**
 * 
 * @author javier07
 *
 */
@Mapper
public interface ImpugnacionMyBatisDAO {

	String V_IMPUGNACION_CONSULTA = "SELECT IMPUGNACION_ID AS IMPUGNACIONID, "
			+ "INFRAC_NUM AS INFRACNUM, ESTATUS AS ESTATUS,STAT AS STAT "
			+ "FROM V_IMPUGNACION_CONSULTA_INFRACS "
			+ "where impugnacion_id = #{valor} order by infrac_num";

	
	@Select(V_IMPUGNACION_CONSULTA)
	public List<VImpugnacionConsultaVO> obtieneInfraccionImpugna(@Param("valor") String valor);

	String V_IMPUGNACION_INFRAC = "SELECT IMPUGNACION_ID as IMPUGNACIONID,INFRAC_NUM as INFRACNUM,ESTATUS,MODIFICADO_POR AS MODIFICADOPOR,"
			+ "VALIDO,MOTIVO_INVALIDO AS MOTIVOINVALIDO"
			+ " FROM INFRACCION_IMPUGNACION_INFRACS WHERE INFRAC_NUM = #{infraccion}"
			+ "and IMPUGNACION_ID = #{referencia_id} and VALIDO = 1";

	/**
	 * RETORNA UN OBJETO DE LA INFRACCION ENVIADA CON LA REFERENCIA DE LA IMPUGNACION
	 * @author Fernando Castillo
	 * @param String infracNum, referencia_id
	 * @return InfraccionImpugnacionInfracsVO
	 */
	@Select(V_IMPUGNACION_INFRAC)
	public InfraccionImpugnacionInfracsVO obtieneInfraccionImpugnaInfrac(@Param("infraccion") String infraccion,
																	   @Param("referencia_id") String referencia_id);


}
