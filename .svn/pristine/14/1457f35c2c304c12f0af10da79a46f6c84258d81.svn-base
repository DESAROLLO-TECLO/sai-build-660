package mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VConsulaCanceladasDetalleVO;

@Mapper
public interface ImpugnacionCanceladasDetalleMyBatisDAO {
	
String V_CONSULTA_CANCELACIONES_DETALLE ="SELECT * FROM V_CONSULTA_CANCELACIONES WHERE INFRACCION = #{valor} "; 
			                                        
@Select(V_CONSULTA_CANCELACIONES_DETALLE)
public VConsulaCanceladasDetalleVO obtieneVConsultaCanceladas(@Param("valor") String valor);


}
