package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudArticuloVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudArticulosHistoricoVO;

@Mapper
public interface ArticuloMyBatisDAO {

	String ABC_ARTICULOS = "{CALL ABC_ARTICULOS ("
			+"#{artId},"
			+"#{categId},"
			+"#{artMotivacion},"
			+"#{progId},"
			+"#{causalId},"
			+"#{artNumero},"
			+"#{artFraccion},"
			+"#{artParrafo},"
			+"#{artInciso},"
			+"#{artDias},"
			+"#{artDescuento},"
			+"#{artPorcenDesc},"
			+"#{artTipo},"
//			+"#{fraccion},"
//			+"#{parrafo},"
//			+"#{inciso},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	String PROC_ARTICULOS_HISTORICOS = "{CALL PROC_ARTICULOS_HISTORICOS ("
			+"#{articulo.artId}, "
			+"#{artInfrFinCons}, "
			+"#{artInfrFinArticulo}, "
			+"#{artInfrFinFraccion}, "
			+"#{artInfrFinParrafo}, "
			+"#{artInfrFinInciso}, "
			+"#{artInfrFinDescripcion}, "
			+"#{artInfrFinSalario}, "
			+"#{artInfrFinAplicadoVeh}, "
			+"#{artInfrFinArrastro}, "
			+"#{artInfrFinPuntos}, "
			+"#{artInfrFinFechaInicio}, "
			+"#{artInfrFinFechaTermino}, "
			+"#{modificadoPor}, "
			+"#{operacion}, "
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})} ";
	
	@Select(value = ABC_ARTICULOS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudArticulo(CrudArticuloVO crudArticuloVO) throws PersistenceException;
	
	@Select(value = PROC_ARTICULOS_HISTORICOS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudArticulosHistoricos(CrudArticulosHistoricoVO crudArticuloVO) throws PersistenceException;
}
