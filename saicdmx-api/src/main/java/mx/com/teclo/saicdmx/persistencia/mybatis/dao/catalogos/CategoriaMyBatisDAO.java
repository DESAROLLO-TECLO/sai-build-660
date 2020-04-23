package mx.com.teclo.saicdmx.persistencia.mybatis.dao.catalogos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCategoriaVO;

@Mapper
public interface CategoriaMyBatisDAO {

	String ABC_CATEGORIAS = "{CALL ABC_CATEGORIAS ("
			+"#{categoriaId},"
			+"#{categoriaCodigo},"
			+"#{categoriaDesc},"
			+"#{modificadoPor},"
			+"#{operacion},"
			+"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT})}";
	
	@Select(value = ABC_CATEGORIAS)
	@Options(statementType = StatementType.CALLABLE)
	public void callCrudCategoria(CrudCategoriaVO crudCategoriaVO) throws PersistenceException;
}
