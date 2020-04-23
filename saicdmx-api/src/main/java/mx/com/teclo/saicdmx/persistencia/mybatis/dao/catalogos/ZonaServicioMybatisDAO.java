package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudZonaServicioVO;

@Mapper
public interface ZonaServicioMybatisDAO {

	String ABC_ZONA_SERVICIO = "{CALL ABC_ZONA_SERVICIO ("
			+"#{zonaId},"
			+"#{zonaCod},"
			+"#{zonaNombre},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_ZONA_SERVICIO)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudZonaServicio(CrudZonaServicioVO zonaServicioVO) throws PersistenceException;
}
