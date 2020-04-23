package mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportesweb;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.reportesweb.ReportesWebVO;

@Mapper
public interface ReportesWebMybatisDAO {
	
	  String GET_REPORTES_BY_PERFIL = 	"SELECT  REPORTES.REPORTE_ID as reporteId, "+
	  										"REPORTES.NOMBRE_REPORTE as nombreReporte "+
	  										"FROM REPORTES_WEB_PERFIL reportePerfil, "+
  											"REPORTES_WEB reportes "+
  											"WHERE REPORTEPERFIL.REPORTE_ID = REPORTES.REPORTE_ID "+
  											"AND REPORTES.VALIDO = 1 "+
  											"AND REPORTEPERFIL.PERFIL_ID = #{perfilId}";
	  
	  
	  @Select(GET_REPORTES_BY_PERFIL)
      List<ReportesWebVO> busquedaPorPerfil(@Param("perfilId")Long perfilId);
}
