package mx.com.teclo.saicdmx.persistencia.mybatis.dao.liberacionvehiculo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ConsultaSalidaDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ModificacionSalidaDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ProcesoDeSalidaAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ProcesoDeSalidaVO;

@Mapper
public interface LiberacionVehiculoMyBatisDAO {
	
	String GET_SALIDA_DEPOSITO = "SELECT * FROM TABLE(CAST(FN_INFRACCIONES_2_TP( " +
			 					"#{moduloBusqueda}, " + 
								"#{operacionBusqueda}, " + 
								"#{tipoBusqueda}, " + 
								"#{datoBusqueda}, " + 
								"#{fechaIniBusqueda}, " + 
								"#{fechaFinBusqueda}, " + 
								"#{eventoBusqueda}, " + 
								"#{depositoBusqueda})" + " AS T_INFRACCIONES_2 ))" + "ORDER BY T_INFRACCION";
	
	String GET_DATOS_LIBERACION_VEHICULO_MODIFICACION = "SELECT i.infrac_num_ctrl AS INFRACNUMCTRL, "
													  + "i.DEP_ID AS DEPOSITOID, "
													  + "d.dep_nombre AS NOMBREDEPOSITO, "
													  + "d.dep_usuario AS USUARIODEPOSITO, "
													  + "ingr.ingr_resguardo AS NOMBRERESGUARDO, "
													  + "ingr.ingr_serie AS INGRESOSERIE, "
													  + "ingr.vtipo_cod AS CODINTERNOVEHICULO "
													  + "FROM infracciones i, "
													  + "ingresos ingr, depositos d "
													  + "WHERE i.infrac_num = ingr.infrac_num AND i.dep_id = d.dep_id "
													  + "AND i.infrac_num = #{infracNum}";
	
	String SP_PROC_SALIDA = "BEGIN PROC_SALIDA ("
			+ "#{depositoId}, "
			+ "#{nombreresguardo}, "
			+ "#{usuarioId}, "
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.Integer, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	String SP_PROC_SALIDA_ADMIN = "BEGIN PROC_SALIDA_EXCEED_ADMIN ("
			+ "#{usuarioDeposito}, "
			+ "#{infracnumctrl}, "
			+ "#{numeroPedido}, "
			+ "#{vehiculoTipo}, "
			+ "#{usuario}, "
			+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.Integer, mode=INOUT}, "
			+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	
	@Select(GET_SALIDA_DEPOSITO)
	public List<ConsultaSalidaDepositoVO> consultaSalidaDeposito(@Param("moduloBusqueda") String moduloBusqueda,
			@Param("operacionBusqueda") String operacionBusqueda,
			@Param("tipoBusqueda") String tipoBusqueda,
			@Param("datoBusqueda") String datoBusqueda,
			@Param("fechaIniBusqueda") String fechaIniBusqueda,
			@Param("fechaFinBusqueda") String fechaFinBusqueda,
			@Param("eventoBusqueda") String eventoBusqueda,
			@Param("depositoBusqueda") String depositoBusqueda);
	
	@Select(GET_DATOS_LIBERACION_VEHICULO_MODIFICACION)
	public ModificacionSalidaDepositoVO consultaParaLiberacionVehiculoModificacion(@Param("infracNum") String infracNum);
	
	@Select(value = SP_PROC_SALIDA)
	@Options(statementType = StatementType.CALLABLE)
	public ProcesoDeSalidaVO ejecutarProcesoDeSalida(ProcesoDeSalidaVO object) throws PersistenceException;
	
	@Select(value = SP_PROC_SALIDA_ADMIN)
	@Options(statementType = StatementType.CALLABLE)
	public ProcesoDeSalidaAdminVO ejecutarProcesoDeSalidaAdmin(ProcesoDeSalidaAdminVO object) throws PersistenceException;

	@Select("SELECT INGR_SALIDA AS fechaIngreso, " + 
			"        INGR_STATUS AS statusIngr " + 
			"    FROM INGRESOS WHERE INGR_NUM_CTRL = #{numCtrl} ")
	public ProcesoDeSalidaVO getOldVOProcesoSalida(@Param("numCtrl")String infracnumctrl);
}
