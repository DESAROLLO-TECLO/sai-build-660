package mx.com.teclo.ms.persistencia.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.ms.persistencia.vo.FechasIniFinVO;

@Mapper
public interface CatalogoMsMyBatisDAO {

	String STRINGGENERICO = "${query}";
	
	
	@Select(STRINGGENERICO)
	public FechasIniFinVO getFechaInicialFinal(
			@Param("query") String query);
	
}
