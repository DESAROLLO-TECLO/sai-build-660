package mx.com.teclo.saicdmx.persistencia.mybatis.dao.lineadecaptura;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccReasignacionEstadisticaVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccReasignacionHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.DetalleDeReasignacionesOficialVO;

@Mapper
public interface ConsultaLCMyBatisDAO {
	
	String GET_V_INFRACCIONES_REASIGNACION_HISTORICO = "SELECT nvl(i.LINEA_CAPTURA, ' ') AS LINEACAPTURA, nvl(i.IMPORTE,0) AS IMPORTE, nvl(i.DESCUENTO,0) AS DESCUENTO, nvl(i.TOTAL,0) AS TOTAL, "
			+ "nvl(i.RECARGOS,0) AS RECARGOS, i.INFRAC_NUM AS NUMEROINFRACCION, "
			+ "e.EMP_NOMBRE || ' ' || e.EMP_APE_PATERNO || ' ' || e.EMP_APE_MATERNO AS NOMBREOFICIAL, "
			+ "e.EMP_PLACA AS PLACAOFICIAL, r.INFRAC_PLACA AS PLACAVEHICULO, nvl(TO_CHAR(i.FECHA_REASIGNACION, 'dd/mm/yyyy'), ' ') AS FECHAREASIGNACION, TO_CHAR(i.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHACREACION, "
			+ "nvl(TO_CHAR(i.VIGENCIA, 'dd/mm/yyyy'), ' ') AS VIGENCIA , i.ESTATUS_RESULTADO AS ESTATUSRESULTADO "
			+ "FROM INFRACCIONES_LC_REASIGNA i INNER JOIN EMPLEADOS e "
			+ "ON i.CREADO_POR = e.EMP_ID LEFT JOIN INFRACCIONES_RADAR r ON i.INFRAC_NUM = r.INFRAC_NUM WHERE "
			+ "(((#{fechaInicio} IS NULL AND #{fechaFin} IS NULL) OR (fecha_reasignacion between TO_DATE (#{fechaInicio} , 'dd/mm/yyyy') AND TO_DATE(#{fechaFin} , 'dd/mm/yyyy')))) " 
            + "AND (#{noInfraccion} IS NULL OR i.INFRAC_NUM = #{noInfraccion}) AND (#{placaOficial} IS NULL OR  e.emp_placa = #{placaOficial}) "
            + "AND (#{estatusConsumo} IS NULL OR i.ESTATUS_CONSUMO = #{estatusConsumo}) "
            + "AND (#{placaVehiculo} IS NULL OR r.Infrac_Placa = #{placaVehiculo}) "
            + "AND i.ESTATUS_RESULTADO = 'OK' "
            + "AND ROWNUM <=20000 "
            + "ORDER BY FECHACREACION DESC";
	
	String GET_V_REASIGNACIONES_ESTADISTICA = "SELECT  EMP.EMP_NOMBRE || ' ' || EMP.EMP_APE_PATERNO || ' ' || EMP.EMP_APE_MATERNO AS NOMBREOFICIAL, "
										+ "EMP.EMP_PLACA AS PLACAOFICIAL, "
										+ "COUNT(LC.INFRAC_NUM) AS NUMEROREASIGNACIONES "
										+ "FROM INFRACCIONES_LC_REASIGNA LC "
										+ "LEFT JOIN EMPLEADOS EMP "
										+ "ON LC.CREADO_POR = EMP.EMP_ID WHERE LC.ESTATUS_CONSUMO = 0 AND "
										+ "(((#{fechaInicio} IS NULL AND #{fechaFin} IS NULL) OR (fecha_reasignacion between TO_DATE (#{fechaInicio} , 'dd/mm/yyyy') AND TO_DATE(#{fechaFin} , 'dd/mm/yyyy'))) "
										+ ") AND (#{placaOficial} IS NULL OR EMP.EMP_PLACA = #{placaOficial}) AND (#{nombreOficial} IS NULL OR  UPPER(EMP.EMP_NOMBRE || ' ' || EMP.EMP_APE_PATERNO || ' ' || EMP.EMP_APE_MATERNO) LIKE UPPER('%' || #{nombreOficial} ||  '%')) "
										+ "AND ROWNUM <=40000 "
										+ "GROUP BY EMP.EMP_PLACA, EMP.EMP_NOMBRE || ' ' || EMP.EMP_APE_PATERNO || ' ' || EMP.EMP_APE_MATERNO";
	
	String GET_DETALLE_REASIGNACIONES_BY_OFICIAL = "SELECT reasigna.infrac_num AS FOLIOINFRACCION, reasigna.estatus_resultado AS ESTATUS, "
												 + "reasigna.linea_captura AS LINEACAPTURA, "
												 + "TO_CHAR(reasigna.FECHA_REASIGNACION, 'dd/mm/yyyy') AS FECHAREASIGNA, "
												 + "TO_CHAR(reasigna.FECHA_CREACION, 'DD/MM/YYYY HH24:MI:SS') AS FECHACREACION, "
												 + "TO_CHAR(reasigna.vigencia,'dd/mm/yyyy') VIGENCIA, "
												 + "reasigna.importe AS IMPORTE, reasigna.recargos AS RECARGOS, "
												 + "reasigna.descuento AS DESCUENTO, reasigna.total AS TOTAL, "
												 + "emp.emp_placa, emp.emp_nombre || ' ' || emp.emp_ape_paterno || ' ' || emp.emp_ape_materno AS NOMBREEMPLEADO, "
												 + "r.INFRAC_PLACA AS PLACAVEHICULO "
												 + "FROM infracciones_lc_reasigna reasigna "
												 + "LEFT JOIN usuarios usr ON usr.usu_id =  reasigna.creado_por "
												 + "LEFT JOIN empleados emp ON emp.emp_id = usr.emp_id "
												 + "LEFT JOIN INFRACCIONES_RADAR r ON reasigna.INFRAC_NUM = r.INFRAC_NUM "
												 + "WHERE emp.EMP_PLACA = #{placaOficial} "
												 + "AND reasigna.estatus_consumo = #{estatusConsumo} AND "
												 + "((#{fechaInicio} IS NULL AND #{fechaFin} IS NULL) OR (FECHA_REASIGNACION BETWEEN TO_DATE(#{fechaInicio} ,'dd/MM/yyyy') AND TO_DATE(#{fechaFin},'dd/MM/yyyy'))) "
												 + "ORDER BY reasigna.fecha_creacion DESC";		
	
	@Select(GET_V_INFRACCIONES_REASIGNACION_HISTORICO)
	public List<ConsultaInfraccReasignacionHistoricoVO> buscarHistoricoLC(@Param("fechaInicio") String fechaInicio, 
			@Param("fechaFin") String fechaFin, @Param("noInfraccion") String noInfraccion,
			@Param("placaOficial") String placaOficial, @Param("placaVehiculo") String placaVehiculo,
			@Param("estatusConsumo") Integer estatusConsumo);
	
	@Select(GET_V_REASIGNACIONES_ESTADISTICA)
	public List<ConsultaInfraccReasignacionEstadisticaVO> buscarEstadisticaLC(@Param("fechaInicio") String fechaInicio, 
			@Param("fechaFin") String fechaFin, @Param("placaOficial") String placaOficial, @Param("nombreOficial") String nombreOficial);
	
	@Select(GET_DETALLE_REASIGNACIONES_BY_OFICIAL)
	public List<DetalleDeReasignacionesOficialVO> ConsultaDetalleReasignacionesByOficial(@Param("placaOficial") String infraccion, 
																						 @Param("estatusConsumo") Integer estatus,
																						 @Param("fechaInicio") String fechaInicio,
																						 @Param("fechaFin") String fechaFin);
}