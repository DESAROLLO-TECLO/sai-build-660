package mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ConInfraccionVO;

@Mapper
public interface  BuscarConInfraccionMyBatisDAO {

	String GET_CON_INFRACCION = "SELECT * FROM TABLE(CAST(FN_INFRACCIONES_2_TP('INGRESO','INGRESO',#{opcion},#{valor},'','','','') AS T_INFRACCIONES_2 ))ORDER BY T_INFRACCION";
		
	String GET_CONSULTA_REMISIONES = "SELECT * FROM TABLE(CAST(FN_INFRACCIONES_2_TP('INGRESO','CONSULTA',#{opcion},#{valor},'','','',#{dep_emp}) AS T_INFRACCIONES_2 ))ORDER BY T_INFRACCION";
		
	String GET_DEP_EMP = "SELECT DEP_ID FROM DEPOSITOS_EMPLEADOS WHERE emp_id = #{emp_id}"; 

	
	
	@Select(GET_CON_INFRACCION)
	public List<ConInfraccionVO> buscarConInfraccion(@Param("opcion") String opcion, @Param("valor") String valor);

	@Select(GET_CONSULTA_REMISIONES)
	public List<ConInfraccionVO> buscarRemisiones(@Param("opcion") String opcion, @Param("valor") String valor, @Param("dep_emp") String dep_emp);

	@Select(GET_DEP_EMP)
	public String buscarDepEmpId(@Param("emp_id")  Long emp_id);


}