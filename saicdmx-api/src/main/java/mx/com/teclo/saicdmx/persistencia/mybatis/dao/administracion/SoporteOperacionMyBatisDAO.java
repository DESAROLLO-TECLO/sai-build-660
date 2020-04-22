package mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.EjecutaSoporteOperacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.SoporteEmbargoConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.SoporteEmbargoVO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.SoporteFoliosVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.ConsultaUsersVO;
import mx.com.teclo.saicdmx.persistencia.vo.empleados.EmpleadosPorPlacaVO;
import mx.com.teclo.saicdmx.persistencia.vo.ingresos.IngresosVO;

@Mapper
public interface SoporteOperacionMyBatisDAO {

	String GET_AUTORIZACION_OFICIAL = "SELECT oficial_placa AS EMPPLACA, oficial_nombre AS EMPNOMBRE "
                    				+ "FROM V_SOPORTE_BUSCA_OFICIAL "
                				    + "WHERE oficial_placa = #{placa}";
	
	String GET_AUTORIZACION_OFICIAL_ASIGNAR_FOLIOS = "SELECT oficial_placa AS EMPPLACA, oficial_nombre AS EMPNOMBRE "
								                   + "FROM V_SOPORTE_BUSCA_OFICIAL_HH "
								                   + "WHERE oficial_placa = #{placa}";
	
	String GET_USUARIO_HANDHELD = "SELECT emp_id, perfil_id ,emp_ape_paterno ||' '|| "
                					+ "emp_ape_materno||' '|| emp_nombre AS emp_nombre, emp_placa "
                					+ "FROM V_USUARIOS_CONSULTA "
                					+ "WHERE emp_placa = #{placa} "
                					+ "AND codigo_aplicacion = #{cd_aplicacion} "
                					+ "AND estatus='A'";
	
	String GET_FOLIOS_EMPLEADO = "SELECT cantidad, folio_clave as folioClave "
                				+ "FROM V_SOPORTE_FOLIOS_USUARIOS "
                				+ "WHERE emp_id= #{empleadoId}";
	
	String 	EXECUTE_SOPORTE_OPERACION = "BEGIN SP_ADMIN_SOPORTE ("
										+ "#{usuarioId}, "
										+ "#{usuarioAutoriza}, "
										+ "#{infraccionNum}, "
										+ "#{fechaHora}, "
										+ "#{infraccionPreImpresa}, "
										+ "#{infraccionNumNueva}, "
										+ "#{nciNuevo}, "
										+ "#{infraccionPlaca}, "
										+ "#{lstCausaIngreso}, "
										+ "#{lstTipoIngreso}, "
										+ "#{numOficio}, "
										+ "#{oficialPlaca}, "
										+ "#{resguardo}, "
										+ "#{folioInicial}, "
										+ "#{folioFinal}, "
										+ "#{reciboTotal}, "
										+ "#{reciboUtilizados}, "
										+ "#{reciboCancelados}, "
										+ "#{tipoSoporte}, "
										+ "#{operacionEfectuada}, "
										+ "#{empIdFolios}, "
										+ "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
										+ "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
	
	/*OPERACIONES SECUNDARIAS*/
	String  GET_INGRESO_BY_NUMERO_INFRACCION = "SELECT infrac_num AS INFRACCION, infrac_m_fecha_hora AS FECHA "
                							 + "FROM V_SOPORTE_BUSCAINFRAC_13 "
                							 + "WHERE infrac_num = #{infraccion}";
	
	String GET_INGRESO_DETALLE_BY_NUMERO_INFRACCION = "SELECT infrac_num AS INFRACCION, dep_nombre AS DEPOSITO, "
										               + "ingr_resguardo AS RESGUARDO, ingr_fecha AS FECHA, "
										               + "ingr_salida AS FECHASALIDA "
										               + "FROM V_SOPORTE_BUSCAINGRESO "
										               + "WHERE infrac_num = #{infraccion}";
	
	String GET_EMBARGO_BY_PLACA_VEHICULO = "SELECT infrac_num_ctrl AS INFRACCONTROL, "
										+ "infrac_num AS INFRACCION, "
										+ "TO_CHAR(INFRAC_M_FECHA_HORA, 'YYYY-MM-DD HH24:MI') AS FECHAHORA "
						                + "FROM V_SOPORTE_BUSCA_EMBARGO "
						                + "WHERE infrac_placa = #{placa} "
						                + "ORDER BY infrac_m_fecha_hora";
	
	String GET_PAGO_DE_INFRACCION = "SELECT infrac_pagada "
					                + "FROM V_SOPORTE_BUSCAPAGO "
					                + "WHERE infrac_num = #{infraccion}";
	
	String GET_EMBARGOS = "SELECT infraccion AS FOLIO, fecha_infraccion AS FECHAINFRACCION, pago AS PAGO, "
			            + "fecha_ingreso AS FECHAINGRESO, fecha_salida AS FECHASALIDA, deposito AS DEPOSITO "
			            + "FROM V_SOPORTE_BUSCA_02 "
			            + "WHERE "
			            + "( CASE " 
							+ "WHEN (#{tipoBusqueda} = 1) AND placa = #{valor} "
							+ "THEN 1 " 
							+ "WHEN (#{tipoBusqueda} = 2) AND infraccion = #{valor} "
							+ "THEN 1 " 
							+ "WHEN (#{tipoBusqueda} = 3) AND docto = #{valor} "
							+ "THEN 1 " 
							+ "WHEN #{tipoBusqueda} = 0 "  
				            + "THEN 1 "  
						+ "END ) = 1";
	
	String  GET_INGRESO_BY_NUMERO_INFRACCION_FECHA_FORMAT = " SELECT TO_CHAR(TO_DATE (infrac_m_fecha_hora, 'DD/MM/YY')) "
			 + "FROM V_SOPORTE_BUSCAINFRAC_13 "
			 + "WHERE infrac_num = #{infraccion}";	
	
	@Select(GET_AUTORIZACION_OFICIAL)
	public EmpleadosPorPlacaVO buscaAutorizacionOficial(@Param("placa") String placa);
	
	@Select(GET_AUTORIZACION_OFICIAL_ASIGNAR_FOLIOS)
	public EmpleadosPorPlacaVO buscaAutorizacionOficialAsignarFolios(@Param("placa") String placa);
	
	@Select(GET_USUARIO_HANDHELD)
	public ConsultaUsersVO buscaUsuarioHH(@Param("placa") String placa, @Param("cd_aplicacion") String cdAplicacion);
	
	@Select(GET_FOLIOS_EMPLEADO)
	public List<SoporteFoliosVO> buscaFoliosEmpleado (@Param("empleadoId") Long empleadoId);
	
	@Select(value = EXECUTE_SOPORTE_OPERACION)
	@Options(statementType = StatementType.CALLABLE)	
	public EjecutaSoporteOperacionVO EjecutarSoporteOperacion(EjecutaSoporteOperacionVO SoporteOperacionVO) throws PersistenceException;

	/*OPERACIONES SECUNDARIAS*/
	@SuppressWarnings("rawtypes")
	@Select(value = GET_INGRESO_BY_NUMERO_INFRACCION)
	public Map buscaIngresoPorInfraccion(@Param("infraccion") String infraccion);
	
	@SuppressWarnings("rawtypes")
	@Select(value = GET_INGRESO_DETALLE_BY_NUMERO_INFRACCION)
	public Map buscaIngresoDetallePorInfraccion(@Param("infraccion") String infraccion);
	
	@Select(value = GET_EMBARGO_BY_PLACA_VEHICULO)
	public List<SoporteEmbargoVO> buscaEmbargoPorPlaca(@Param("placa") String placa);
	
	@Select(GET_PAGO_DE_INFRACCION)
	public String buscaPagoDeInfraccion(@Param("infraccion") String infraccion);
	
	@Select(GET_EMBARGOS)
	public List<SoporteEmbargoConsultaVO> buscaEmbargos(@Param("tipoBusqueda") Integer tipoBusqueda, @Param("valor") String valor);
	
	@SuppressWarnings("rawtypes")
	@Select(value = GET_INGRESO_BY_NUMERO_INFRACCION_FECHA_FORMAT)
	public String buscaIngresoPorInfraccionFecha(@Param("infraccion") String infraccion);
	
	@Select("SELECT DEP_ID as depId,"
		 + "INGR_RESGUARDO as ingrResguardo,"
		 + "CAUSA_ID as causaId,"
		 + "VTIPO_COD as vTipoCod,"
		 + "T_INGR_ID as tIngrId,"
		 + "INGR_ASN as ingrAsn,"
		 + "INGR_NUM_CTRL as ingrNumCtrl,"
		 + "SELLADO as sellado,"
		 + "CAJUELA as cajuela,"
		 + "INGR_FECHA as ingrFecha,"
		 + "INGR_SALIDA as ingrSalida,"
		 + "INGR_SERIE as ingrSerie,"
		 + "INGR_STATUS as ingrStatus,"
		 + "VEH_TIPO as vehtipo,"
		 + "INGR_OBSERVA as ingrObserva,"
		 + "TIPO_GRUA as tipoGrua,"
		 + "PROGRAMA as programa,"
		 + "TIPO_ENTRADA as tipoEntrada,"
		 + "GRUA_COD as gruaCod,"
		 + "INFRAC_DOCTO as infracDocto,"
		 + "ESTATUS_CANCELACION as estatusCancelacion,"
		 + "AUTORIZACION_CANCELACION as autorizacionCancelacion,"
		 + "FECHA_CANCELACION as fechaCancelacion,"
		 + "USUARIO_CANCELA as usuarioCancela,"
		 + "USUARIO_AUTORIZA as usuarioAutoriza,"
		 + "MODIFICADO_POR as modificadoPor"
		 + " FROM INGRESOS "
		 + "WHERE INFRAC_NUM = #{infrac_num} AND ROWNUM = 1")
	public IngresosVO getInformacionIngreso(@Param("infrac_num") String infrac_num);
	
}
