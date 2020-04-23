package mx.com.teclo.saicdmx.persistencia.mybatis.dao.cajas;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasDepositosSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasNuevoSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasUsuariosSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.FNRenglonesCortePartidasVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.FnRenglonesCorteInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.FnRenglonesCortePartidasTarVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.ProcInformeCorteSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VBuscarCorteCaja;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaConTieneOperacionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaSinCorteActVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaSinCorteHistVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VConsultaUsuariosCajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VFoliosCajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;

@Mapper
public interface CajaMyBatisDAO {
	
	/* **********************                HISTORICO                ********************************* */
	
	String CONDICION = "WHERE (#{filtroEmpPlaca} IS NULL OR EMP_PLACA = #{filtroEmpPlaca}) AND "
			+ "(#{filtroCajaCod} IS NULL OR CAJA_COD = #{filtroCajaCod}) AND "
			+ "(#{filtroPerfil} IS NULL OR PERFIL_ID = #{filtroPerfil}) AND "
			+ "( (#{filtroFechaI} IS NULL AND #{filtroFechaF} IS NULL) OR (FECHA BETWEEN TO_DATE(#{filtroFechaI}, 'dd/mm/yyyy') AND TO_DATE(#{filtroFechaF}, 'dd/mm/yyyy')) ) ";
	
	String GET_V_CAJAS_SINCORTE_HIST_DETALLE = "SELECT CAJA_ID AS CAJAID, "
			+ "CAJA_COD AS CAJACOD, "
			+ "EMP_ID AS EMPID, "
			+ "EMP_PLACA AS EMPPLACA, "
			+ "EMP_TIPO AS EMPTIPO, "
			+ "EMP_NOMBRE_COMP AS EMPNOMBRECOMP, "
			+ "INFRAC_NUM AS INFRACNUM, "
			+ "PAGO_TOTAL AS PAGOTOTAL, "
			+ "TO_CHAR(FECHA,'DD/MM/YYYY') AS FECHA, "
			+ "PERFIL_ID AS PERFILID, "
			+ "PERFIL_NOMBRE AS PERFILNOMBRE "
			+ "FROM V_CAJAS_SINCORTE_HIST "
			+ CONDICION;
	
	String GET_V_CAJAS_SINCORTE_HIST_TOTAL = "SELECT COUNT(*) AS TOTALCOUNT, "
			+ "CAJA_ID AS CAJAID, "
			+ "CAJA_COD AS CAJACOD, "
			+ "EMP_ID AS EMPID, "
			+ "EMP_PLACA AS EMPPLACA, "
			+ "EMP_TIPO AS EMPTIPO, "
			+ "EMP_NOMBRE_COMP AS EMPNOMBRECOMP, "
			+ "TO_CHAR(FECHA,'DD/MM/YYYY') AS FECHA, "
			+ "PERFIL_ID AS PERFILID, "
			+ "PERFIL_NOMBRE AS PERFILNOMBRE "
			+ "FROM V_CAJAS_SINCORTE_HIST "
			+ CONDICION
			+ "GROUP BY CAJA_ID, CAJA_COD, EMP_ID, "
			+ "EMP_PLACA, EMP_TIPO, EMP_NOMBRE_COMP, FECHA, PERFIL_ID, PERFIL_NOMBRE";
	
	String GET_V_CAJAS_SINCORTE_HIST_DETALLE_BY_PARAMS = "SELECT INFRAC_NUM AS INFRACNUM, PAGO_TOTAL AS PAGOTOTAL "
    + "FROM V_CAJAS_SINCORTE_HIST "
    + "WHERE CAJA_ID = #{caja} "
    + " AND EMP_ID = #{emp} "
    + " AND TO_CHAR(FECHA,'DD/MM/YYYY') = #{fecha}";
	
	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_HIST FILTRADO
	 * @author Kevin Ojeda
	 * @param String filtroEmpPlaca
	 * @param String filtroCajaCod
	 * @param String filtroPerfil
	 * @param String filtroFechaI
	 * @param String filtroFechaF
	 * @return List<VCajaSinCorteHistVO>
	 */
	@Select(GET_V_CAJAS_SINCORTE_HIST_DETALLE)
	public List<VCajaSinCorteHistVO> getVCajaSinCorteHistVODetalle(
			@Param("filtroEmpPlaca") String filtroEmpPlaca,
			@Param("filtroCajaCod") String filtroCajaCod,
			@Param("filtroPerfil") String filtroPerfil,
			@Param("filtroFechaI") String filtroFechaI,
			@Param("filtroFechaF") String filtroFechaF);
	
	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_HIST FILTRADO Y AGRUPADO
	 * @author Kevin Ojeda
	 * @param String filtroEmpPlaca
	 * @param String filtroCajaCod
	 * @param String filtroPerfil
	 * @param String filtroFechaI
	 * @param String filtroFechaF
	 * @return List<VCajaSinCorteHistVO>
	 */
	@Select(GET_V_CAJAS_SINCORTE_HIST_TOTAL)
	public List<VCajaSinCorteHistVO> getVCajaSinCorteHistVOTotalPagos(
			@Param("filtroEmpPlaca") String filtroEmpPlaca,
			@Param("filtroCajaCod") String filtroCajaCod,
			@Param("filtroPerfil") String filtroPerfil,
			@Param("filtroFechaI") String filtroFechaI,
			@Param("filtroFechaF") String filtroFechaF);
	
	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_HIST FILTRADO Y AGRUPADO
	 * @author Kevin Ojeda
	 * @param String caja
	 * @param String emp
	 * @param String fecha
	 * @return List<VCajaSinCorteHistVO>
	 */
	@Select(GET_V_CAJAS_SINCORTE_HIST_DETALLE_BY_PARAMS)
	public List<VCajaSinCorteHistVO> getVCajaSinCorteHistVODetalleByParams(
			@Param("caja") String caja,
			@Param("emp") String emp,
			@Param("fecha") String fecha);
	
	/* **********************                HISTORICO                ********************************* */
	
	/* **********************                 ACTUAL                  ********************************* */
	
	String GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL = "SELECT "
			+ "COUNT(*) AS TOTALCOUNT, "
			+ "CAJA_ID AS CAJAID, "
			+ "CAJA_COD AS CAJACOD, "
			+ "EMP_ID AS EMPID, "
            + "EMP_PLACA AS EMPPLACA, "
            + "EMP_TIPO AS EMPTIPO, "
            + "EMP_NOMBRE_COMP AS EMPNOMBRECOMP, "
            + "TO_CHAR(sysdate,'dd/mm/yyyy') FECHA, "
            + "PERFIL_ID AS PERFILID, "
            + "PERFIL_NOMBRE AS PERFILNOMBRE "
            + "FROM V_CAJAS_SINCORTE_ACT "
            + "WHERE FECHA = TRUNC(SYSDATE,'DD') AND CD_APLICACION = #{cd_aplicacion} ";
	
	String GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE = "SELECT "
			+ "CAJA_ID AS CAJAID, "
			+ "CAJA_COD AS CAJACOD, "
			+ "EMP_ID AS EMPID, "
            + "EMP_PLACA AS EMPPLACA, "
            + "EMP_TIPO AS EMPTIPO, "
            + "EMP_NOMBRE_COMP AS EMPNOMBRECOMP, "
            + "INFRAC_NUM AS INFRACNUM, "
            + "PAGO_TOTAL AS PAGOTOTAL, "
            + "FECHA_COMPLETA AS FECHA, "
            + "PERFIL_ID AS PERFILID, "
            + "PERFIL_NOMBRE AS PERFILNOMBRE "
            + "FROM V_CAJAS_SINCORTE_ACT "
            + "WHERE FECHA = TRUNC(SYSDATE,'DD') AND CD_APLICACION = #{cd_aplicacion} ";
            
     String CONDITIONED_BY_CAJA_COD = "AND CAJA_COD = #{parametroBusqueda} ";
     
     String CONDITIONED_BY_EMP_PLACA = "AND EMP_PLACA = #{parametroBusqueda} ";
     
     String CONDITIONED_BY_PERFIL_ID = "AND PERFIL_ID = #{parametroBusqueda} ";
            
     String GROUPED_BY = "GROUP BY CAJA_ID,CAJA_COD,EMP_ID, EMP_PLACA, EMP_TIPO, EMP_NOMBRE_COMP, PERFIL_ID, PERFIL_NOMBRE";
     
     String GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL_COD_GROUPED_BY = 
    		 GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL + GROUPED_BY;
     
     String GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL_CONDITIONED_BY_CAJA_COD_GROUPED_BY = 
    		 GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL + CONDITIONED_BY_CAJA_COD + GROUPED_BY;
     
     String GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL_CONDITIONED_BY_EMP_PLACA_GROUPED_BY = 
    		 GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL + CONDITIONED_BY_EMP_PLACA + GROUPED_BY;
     
     String GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL_CONDITIONED_BY_PERFIL_ID_GROUPED_BY = 
    		 GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL + CONDITIONED_BY_PERFIL_ID + GROUPED_BY;
     
     
     String GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE_CONDITIONED_BY_CAJA_COD = 
    		 GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE + CONDITIONED_BY_CAJA_COD;
     
     String GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE_CONDITIONED_BY_EMP_PLACA = 
    		 GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE + CONDITIONED_BY_EMP_PLACA ;
     
     String GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE_CONDITIONED_BY_PERFIL_ID = 
    		 GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE + CONDITIONED_BY_PERFIL_ID;
     
     
     String GET_V_CAJAS_SINCORTE_HIST_ACTUAL_BY_PARAMS = "SELECT INFRAC_NUM AS INFRACNUM, PAGO_TOTAL AS PAGOTOTAL "
    		    + "FROM V_CAJAS_SINCORTE_ACT "
    		    + "WHERE CAJA_ID = #{caja} "
    		    + " AND EMP_ID = #{emp} "
    		    + " AND FECHA = TRUNC(SYSDATE,'DD')";
     
     /**
 	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT AGRUPADO
 	 * @author Kevin Ojeda
 	 * @return List<VCajaSinCorteHistVO>
 	 */
 	@Select(GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL_COD_GROUPED_BY)
 	public List<VCajaSinCorteActVO> getVCajaSinCorteActVOTotalPagos(@Param("cd_aplicacion") String cd_aplicacion);
 	
 	 /**
 	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT AGRUPADO Y FILTRADO POR NUMERO DE CAJA
 	 * @author Kevin Ojeda
 	 * @return List<VCajaSinCorteHistVO>
 	 */
 	@Select(GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL_CONDITIONED_BY_CAJA_COD_GROUPED_BY)
 	public List<VCajaSinCorteActVO> getVCajaSinCorteActVOTotalPagosByCajaCod(@Param("parametroBusqueda") String parametroBusqueda, @Param("cd_aplicacion") String cd_aplicacion);
 	
 	/**
 	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT AGRUPADO Y FILTRADO POR EMPLEADO_PLACA
 	 * @author Kevin Ojeda
 	 * @return List<VCajaSinCorteHistVO>
 	 */
 	@Select(GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL_CONDITIONED_BY_EMP_PLACA_GROUPED_BY)
 	public List<VCajaSinCorteActVO> getVCajaSinCorteActVOTotalPagosByEmpPlaca(@Param("parametroBusqueda") String parametroBusqueda, @Param("cd_aplicacion") String cd_aplicacion);
 	
 	/**
 	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT AGRUPADO Y FILTRADO POR TIPO DE CAJA
 	 * @author Kevin Ojeda
 	 * @return List<VCajaSinCorteHistVO>
 	 */
 	@Select(GET_V_CAJAS_SINCORTE_ACTUAL_TOTAL_CONDITIONED_BY_PERFIL_ID_GROUPED_BY)
 	public List<VCajaSinCorteActVO> getVCajaSinCorteActVOTotalPagosByPerfilId(@Param("parametroBusqueda") String parametroBusqueda, @Param("cd_aplicacion") String cd_aplicacion);
 	
 	/**
	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT FILTRADO Y AGRUPADO
	 * @author Kevin Ojeda
	 * @param String caja
	 * @param String emp
	 * @param String fecha
	 * @return List<VCajaSinCorteHistVO>
	 */
	@Select(GET_V_CAJAS_SINCORTE_HIST_ACTUAL_BY_PARAMS)
	public List<VCajaSinCorteActVO> getVCajaSinCorteActVODetalleByParams(
			@Param("caja") String caja,
			@Param("emp") String emp);
	
	/**
 	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT
 	 * @author Kevin Ojeda
 	 * @return List<VCajaSinCorteHistVO>
 	 */
 	@Select(GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE)
 	public List<VCajaSinCorteActVO> getVCajaSinCorteActVODetallePagos(@Param("cd_aplicacion") String cd_aplicacion);
 	
 	 /**
 	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT FILTRADO POR NUMERO DE CAJA
 	 * @author Kevin Ojeda
 	 * @return List<VCajaSinCorteHistVO>
 	 */
 	@Select(GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE_CONDITIONED_BY_CAJA_COD)
 	public List<VCajaSinCorteActVO> getVCajaSinCorteActVODetallePagosByCajaCod(@Param("parametroBusqueda") String parametroBusqueda, @Param("cd_aplicacion") String cd_aplicacion);
 	
 	/**
 	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT FILTRADO POR EMPLEADO_PLACA
 	 * @author Kevin Ojeda
 	 * @return List<VCajaSinCorteHistVO>
 	 */
 	@Select(GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE_CONDITIONED_BY_EMP_PLACA)
 	public List<VCajaSinCorteActVO> getVCajaSinCorteActVODetallePagosByEmpPlaca(@Param("parametroBusqueda") String parametroBusqueda, @Param("cd_aplicacion") String cd_aplicacion);
 	
 	/**
 	 * RETORNA UNA LISTA DE REGISTROS DE LA VISTA V_CAJAS_SINCORTE_ACT FILTRADO POR TIPO DE CAJA
 	 * @author Kevin Ojeda
 	 * @return List<VCajaSinCorteHistVO>
 	 */
 	@Select(GET_V_CAJAS_SINCORTE_ACTUAL_DETALLE_CONDITIONED_BY_PERFIL_ID)
 	public List<VCajaSinCorteActVO> getVCajaSinCorteActVODetallePagosByPerfilId(@Param("parametroBusqueda") String parametroBusqueda, @Param("cd_aplicacion") String cd_aplicacion);
 	
	/* **********************                 ACTUAL                  ********************************* */
 	
 	/* **********************               CORTE NUEVO               ********************************* */
 	
 	String CONSULTA_CAJA_USUARIO = "SELECT "
 			+ "EMP_ID empId, "
			+ "EMP_PLACA as empPlaca, "
			+ "EMP_TIPO as empTipo, "
			+ "EMP_NOMBRE as empNombre, "
			+ "CAJA_ID as cajaIdD, "
			+ "CAJA_ESTATUS as cajaEstatus, "
			+ "CAJA_COD as cajaCod, "
			+ "DEP_ID as depId, "
			+ "DEPOSITO as deposito, "
			+ "PERFIL_ID as perfilId, "
			+ "PERFIL_NOMBRE as perfilNombre, "
			+ "DERECHO_COBRO as derechoCobro ";
 	
 	String CONSULTA_CAJA_USUARIO_BY_EMP_ID = CONSULTA_CAJA_USUARIO + "FROM V_CAJAS_CONSULTA WHERE EMP_ID = #{param}";
 	
 	String CONSULTA_CAJA_USUARIO_BY_CAJA_COD = CONSULTA_CAJA_USUARIO + "FROM V_CAJAS_CONSULTA WHERE CAJA_COD = #{param} AND CODIGO_APLICACION = #{cd_aplicacion}";
 	
 	String CONSULTA_CAJA_USUARIO_BY_EMP_PLACA = CONSULTA_CAJA_USUARIO + "FROM V_CAJAS_CONSULTA WHERE EMP_PLACA = #{param} AND CODIGO_APLICACION = #{cd_aplicacion}";
 	
 	String PROC_INFORME_CORTE_2 = "BEGIN PROC_INFORME_CORTE_2("
	+ "#{p_caja_id}, "
    + "#{p_num_corte, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{p_total_efectivo, jdbcType=NUMERIC, javaType=java.math.BigDecimal, mode=INOUT}, "
    + "#{p_total_cheques, jdbcType=NUMERIC, javaType=java.math.BigDecimal, mode=INOUT}, "
    + "#{p_total_tar_credito, jdbcType=NUMERIC, javaType=java.math.BigDecimal, mode=INOUT}, "
    + "#{p_total_otros, jdbcType=NUMERIC, javaType=java.math.BigDecimal, mode=INOUT}, "
    + "#{p_total_corte, jdbcType=NUMERIC, javaType=java.math.BigDecimal, mode=INOUT}, "
    + "#{p_partida_inicial, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "                
    + "#{p_partida_final, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "                
    + "#{p_num_operaciones, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "                
    + "#{P_RECIBO_FOL_INI, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "          
    + "#{P_RECIBO_FOL_FIN, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "
    + "#{P_RECIBO_TOTAL, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_RECIBO_UTILIZADOS, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_RECIBO_CANCELADOS, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_RECIBO_INUTILIZADOS, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_RECIBO_FALTANTES, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_B1000, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_B500, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_B200, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_B100, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_B50, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_B20, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_M20, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_M10, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_M5, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_M2, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_M1, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_MC50, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_MC20, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_MC10, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{P_EFE_MC5, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "
    + "#{p_resultado, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "               
    + "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "          
    + "#{P_TIPO_CORTE}, "
    + "#{PTXT_FECHA_CORTE}); "
    + "END;";
 	
 	String SP_PROC_GUARDAR_CORTE = "BEGIN Proc_Guardar_Corte_22("
 			+ "#{p_caja_id}, "
 			+ "#{p_num_corte}, "
 			+ "#{P_RECIBO_FOL_INI}, "
 			+ "#{P_RECIBO_FOL_FIN}, " 
 			+ "#{P_RECIBO_TOTAL}, "     
 			+ "#{P_RECIBO_UTILIZADOS}, "     
 			+ "#{P_RECIBO_CANCELADOS}, "     
 			+ "#{P_RECIBO_INUTILIZADOS}, "    
 			+ "#{P_RECIBO_FALTANTES}, "
 			+ "#{P_EFE_B1000}, "
 			+ "#{P_EFE_B500}, "
 			+ "#{P_EFE_B200}, "
 			+ "#{P_EFE_B100}, "
 			+ "#{P_EFE_B50}, "
 			+ "#{P_EFE_B20}, "
 			+ "#{P_EFE_M20}, "
 			+ "#{P_EFE_M10}, "
 			+ "#{P_EFE_M5}, "
 			+ "#{P_EFE_M2}, "
 			+ "#{P_EFE_M1}, "
 			+ "#{P_EFE_MC50}, "
 			+ "#{P_EFE_MC20}, "
 			+ "#{P_EFE_MC10}, "
 			+ "#{P_EFE_MC5}, "    
 			+ "#{empId}, "
 		    + "#{p_resultado, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "               
 		    + "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); "   
 		    + "END;";
 	
 	
 	String FN_REGLONES_CORTE_PARTIDAS = "SELECT "
 			+ "T_RENGLON AS t_RENGLON, "
 			+ "T_PARTIDA AS t_PARTIDA, "
 			+ "T_PISO AS t_PISO, "
 			+ "T_ARRASTRE AS t_ARRASTRE, " 
 			+ "T_CANDADO AS t_CANDADO, " 
 			+ "T_SUMA_PISO AS t_SUMA_PISO, "
 			+ "T_SUMA_ARRASTRE AS t_SUMA_ARRASTRE, "
 			+ "T_SUMA_CANDADO AS t_SUMA_CANDADO, "
 			+ "T_SUMA_MONTOS AS t_SUMA_MONTOS, "
 			+ "T_PARTIDAS AS t_PARTIDAS, "
 			+ "T_STATUS_TXT AS t_STATUS_TXT "
 			+ "FROM TABLE("
 				+ "CAST("
 					+ "FN_REGLON_CORTE_PARTIDAS_2_TP("
 						+ "#{cajaId},"
 						+ "#{numCaja}"
 					+ ") "
 				+ "AS T_CORTE_PARTIDAS_2)"
 			+ ") ";
 	
 	String FN_REGLONES_CORTE_INFRACCION = "SELECT "
 			+ "T_RENGLON AS t_RENGLON, "
 			+ "T_INFRACCION AS t_INFRACCION, "
 			+ "T_MONTO AS t_MONTO, "
 			+ "T_SUMA_MONTOS AS t_SUMA_MONTOS, "
 			+ "T_PARTIDAS AS t_PARTIDAS, "
 			+ "T_STATUS_TXT AS t_STATUS_TXT "
 			+ "FROM TABLE( "
 				+ "CAST("
 					+ "FN_REGLON_CORTE_INFRAC_2_TP(#{cajaId}, #{numCaja}) AS T_CORTE_INFRACCIONES_2))";
 	
 	String FN_REGLONES_CORTE_PARTIDAS_TAR = "SELECT "
 			+ "T_RENGLON AS t_RENGLON, "
 			+ "T_PARTIDA AS t_PARTIDA, "
 			+ "T_NCI AS t_NCI, "
 			+ "T_AUTORIZACION AS t_AUTORIZACION, "
 			+ "T_MONTO AS t_MONTO, "
 			+ "T_SUMA_MONTOS AS t_SUMA_MONTOS, "
 			+ "T_NUM_PARTIDAS AS t_NUM_PARTIDAS "
 			+ "FROM TABLE( CAST( FN_REGLONES_CORTE_PARTIDAS_TAR(#{cajaId}, #{numCaja}) "
 			+ "AS T_CORTE_PARTIDAS_TARJETA)) ";
 	
 	@Select(CONSULTA_CAJA_USUARIO_BY_EMP_ID)
 	public List<VCajaConsultaVO> getConsultaCajaByUsuarioByEmpId(@Param("param") String param);
 	
 	@Select(CONSULTA_CAJA_USUARIO_BY_CAJA_COD)
 	public List<VCajaConsultaVO> getConsultaCajaByUsuarioByCajaCod(@Param("param") String param, @Param("cd_aplicacion") String cdAplicacion);
 	
 	@Select(CONSULTA_CAJA_USUARIO_BY_EMP_PLACA)
 	public List<VCajaConsultaVO> getConsultaCajaByUsuarioByEmpPlaca(@Param("param") String param, @Param("cd_aplicacion") String cdAplicacion);
 	
 	/**
	 * Ejecuta SP Proc_Informe_Corte_2
	 * @author Kevin Ojeda
	 * @param ProcInformeCorteSPVO procInformeCorteSPVO
	 * @return ProcInformeCorteSPVO
	 * @throws PersistenceException
	 */
	@Select(value = PROC_INFORME_CORTE_2)
	@Options(statementType = StatementType.CALLABLE)
	public ProcInformeCorteSPVO procInformeCorte(ProcInformeCorteSPVO procInformeCorteSPVO) throws PersistenceException;
	
	/**
	 * Ejecuta SP Proc_Guardar_Corte_22
	 * @author Kevin Ojeda
	 * @param ProcInformeCorteSPVO procInformeCorteSPVO
	 * @return ProcInformeCorteSPVO
	 * @throws PersistenceException
	 */
	@Select(value = SP_PROC_GUARDAR_CORTE)
	@Options(statementType = StatementType.CALLABLE)
	public ProcInformeCorteSPVO procGuardarCorte(ProcInformeCorteSPVO procInformeCorteSPVO) throws PersistenceException;
	
	/**
	 * RETORNA LAS COLUMNAS PERTENCIENTES A LA FUNCION FN_REGLONES_CORTE_PARTIDAS_2_TP CON ARGUMENTOS CAJAID Y NUMCAJA
	 * @author Kevin Ojeda
	 * @param fNRenglonesCortePartidasVO
	 * @return List<FNRenglonesCortePartidasVO>
	 * @throws PersistenceException
	 */
	@Select(value = FN_REGLONES_CORTE_PARTIDAS)
	@Options(statementType = StatementType.CALLABLE)
	public List<FNRenglonesCortePartidasVO> renglonesCortePartida(FNRenglonesCortePartidasVO fNRenglonesCortePartidasVO) throws PersistenceException;
	
	/**
	 * RETORNA LAS COLUMNAS PERTENCIENTES A LA FUNCION T_CORTE_INFRACCIONES CON ARGUMENTOS CAJAID Y NUMCAJA
	 * @author Kevin Ojeda
	 * @param FnRenglonesCorteInfraccionVO
	 * @return List<FnRenglonesCorteInfraccionVO>
	 * @throws PersistenceException
	 */
	@Select(value = FN_REGLONES_CORTE_INFRACCION)
	@Options(statementType = StatementType.CALLABLE)
	public List<FnRenglonesCorteInfraccionVO> renglonesCorteInfraccion(FnRenglonesCorteInfraccionVO fnRenglonesCorteInfraccionVO) throws PersistenceException;
	
	/**
	 * RETORNA LAS COLUMNAS PERTENCIENTES A LA FUNCION FN_REGLONES_CORTE_PARTIDAS_TAR CON ARGUMENTOS CAJAID Y NUMCAJA
	 * @author Kevin Ojeda
	 * @param FnRenglonesCortePartidasTarVO
	 * @return List<FnRenglonesCortePartidasTarVO>
	 * @throws PersistenceException
	 */
	@Select(value = FN_REGLONES_CORTE_PARTIDAS_TAR)
	@Options(statementType = StatementType.CALLABLE)
	public List<FnRenglonesCortePartidasTarVO> renglonesCortePartidaTarjeta(FnRenglonesCortePartidasTarVO FnRenglonesCortePartidasTarVO) throws PersistenceException;
 	
 	/* **********************               CORTE NUEVO               ********************************* */
	
	/* **********************             CORTE CONSULTA              ********************************* */
	
	String V_CORTES_CAJA_BUSQ = "SELECT "
			+ "CAJA_ID||'|'||TO_CHAR(FECHA,'dd/mm/yyyy')||'|'||CAJA_COD AS codigoString, ";
	
	String V_CORTES_CAJA_BUSQ_BY_EMP_ID = 
			V_CORTES_CAJA_BUSQ + "FECHA_CAJA AS descripcion FROM V_CORTES_CAJA_BUSQ WHERE emp_id = #{param} ORDER BY FECHA DESC";
	
	String V_CORTES_CAJA_BUSQ_BY_CAJA_ID = 
			V_CORTES_CAJA_BUSQ + "FECHA_USUARIO AS descripcion FROM V_CORTES_CAJA_BUSQ WHERE caja_id = #{param} ORDER BY FECHA DESC";
	
	@Select(value = V_CORTES_CAJA_BUSQ_BY_EMP_ID)
	@Options(statementType = StatementType.CALLABLE)
	public List<FilterValuesVO>buscarCajaPorEmpId(@Param(value="param")String param);
	
	@Select(value = V_CORTES_CAJA_BUSQ_BY_CAJA_ID)
	@Options(statementType = StatementType.CALLABLE)
	public List<FilterValuesVO>buscarCajaPorCajaId(@Param(value="param")String param);

	/* **********************             CORTE CONSULTA              ********************************* */
	
	/* **********************             ADMINISTRACION              ********************************* */
	
 	String CONSULTA_CAJA_BY_CAJACOD_EMPPLACA = CONSULTA_CAJA_USUARIO + ", TIENE_OPERACIONES as tieneOperaciones FROM V_CAJAS_CONSULTA WHERE "
 			+ "(#{cajaCod} IS NULL OR CAJA_COD = #{cajaCod}) "
 			+ "AND (#{empPlaca} IS NULL OR EMP_PLACA = #{empPlaca}) AND CODIGO_APLICACION = #{cd_aplicacion} ";

 	String CONSULTA_CAJA_BY_DEPOSITOS = CONSULTA_CAJA_BY_CAJACOD_EMPPLACA	
 			+ "AND (#{depId} IS NULL OR DEP_ID = #{depId}) ";

 	String CONSULTA_CAJA_BY_TODOOSDEPOSITOS = CONSULTA_CAJA_BY_CAJACOD_EMPPLACA	
 			+ " AND DEP_ID !=0 ORDER BY DEP_ID";
 	
 	String V_FOLIOS_CAJA_BY_CAJA_ID = "SELECT "+
 			"CAJA_ID AS cajaId, "+
 			"CAJA_COD AS cajaCod, "+
 			"ULTIMO_FOLIO_SISTEMA AS ultimoFolioSistema, "+
 			"ULTIMO_FOLIO_CAJA AS ultimoFolioCaja, "+
 			"TIPO_FOLIO AS tipoFolio "+
 			"from V_FOLIOS_CAJA "+
 			"WHERE CAJA_ID = #{cajaId} ";
 	
 	String SP_ADMIN_CAJAS_NUEVO ="BEGIN SP_ADMIN_CAJAS_NUEVO("
 			+ "#{p_operacion},"
 			+ "#{p_dep_id},"
 			+ "#{p_caja_id},"
 			+ "#{p_caja_cod},"
 			+ "#{p_modificado_por},"
 			+ "#{p_resultado, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "               
 		    + "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); "
 			+ "END;";
 	
 	String V_CONSULTA_USUARIOS_CAJA = "SELECT EMP_ID AS empId, "
 			+ "EMP_PLACA AS empPlaca, "
 			+ "EMP_NOMBRE AS empNombre, "
 			+ "DEP_NOMBRE AS depNombre, "
 			+ "CAJA_ID AS cajaId, "
 			+ "CAJA_COD AS cajaCod, "
 			+ "PERFIL_NOMBRE AS perfilNombre, "
 			+ "PERFIL_ID AS perfilId, "
 			+ "TIPO_EMPLEADO AS tipoEmpleado, "
 			+ "AUTORIZADA_P_COBRO AS autorizadaPCobro, "
 			+ "TIENE_OPERACIONES AS tieneOperaciones, "
 			+ "DERECHO_COBRO AS derechoCobro "
 			+ "FROM V_CONSULTA_USUARIOS_CAJA WHERE EMP_PLACA = #{empPlaca}";
 	
 	String SP_ADMIN_CAJAS_USUARIOS = "BEGIN SP_ADMIN_CAJAS_USUARIOS ("
 			+ "#{p_operacion}, "
 			+ "#{p_caja_id}, "
 			+ "#{p_caja_emp_id}, "
 			+ "#{p_caja_emp_perfil_id}, "
 			+ "#{p_emp_id}, "
 			+ "#{p_emp_caja_id}, "
 			+ "#{p_emp_perfil_id}, "
 			+ "#{p_emp_puede_cobrar}, "
 			+ "#{p_modificado_por}, "
 			+ "#{p_resultado, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "               
 		    + "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT} "
 			+ "); end;";

 	String SP_ADMIN_CAJAS_DEPOSITOS = "BEGIN SP_ADMIN_CAJAS_DEPOSITOS("
 			+ "#{p_operacion}, "
 			+ "#{p_caja_id}, "
 			+ "#{p_caja_emp_id}, "
 			+ "#{p_dep_id}, "
 			+ "#{p_modificado_por}, "
 			+ "#{cd_aplicacion}, "
 			+ "#{p_resultado, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "               
 			+ "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT} "
 			+ "); end;";
 	
 	String SP_ADMIN_CAJAS_MODIF = "BEGIN SP_ADMIN_CAJAS_MODIF("
 			+ "#{p_operacion}, "
			+ "#{p_caja_id}, "
			+ "#{p_caja_emp_id}, "
			+ "#{p_modificado_por}, "
			+ "#{cd_aplicacion}, "
			+ "#{p_resultado, jdbcType=NUMERIC, javaType=java.lang.Long, mode=INOUT}, "               
 			+ "#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT} "
 			+ "); end;";
 	/**
 	 * RETORNA REGISTROS DE LA VISTA V_CAJAS_CONSULTA FILTRADOS POR CAJA_COD, EMP_PLACA Y DEP_ID 
 	 * @author Kevin Ojeda
 	 * @param vCajaConsultaConTieneOperacionesVO
 	 * @return List<VCajaConsultaConTieneOperacionesVO>
 	 * @throws PersistenceException
 	 */
 	@Select(value = CONSULTA_CAJA_BY_DEPOSITOS)
 	@Options(statementType = StatementType.CALLABLE)
 	public List<VCajaConsultaConTieneOperacionesVO> buscarCajasPorCajaCodEmpPlacaAndDepId(VCajaConsultaConTieneOperacionesVO vCajaConsultaConTieneOperacionesVO) throws PersistenceException;

 	/**
 	 * RETORNA REGISTROS DE LA VISTA V_CAJAS_CONSULTA FILTRADOS POR CAJA_COD, EMP_PLACA Y TODOS LOS DEPOSITOS 
 	 * @author Kevin Ojeda
 	 * @param vCajaConsultaConTieneOperacionesVO
 	 * @return List<VCajaConsultaConTieneOperacionesVO>
 	 * @throws PersistenceException
 	 */
 	@Select(value = CONSULTA_CAJA_BY_TODOOSDEPOSITOS)
 	@Options(statementType = StatementType.CALLABLE)
 	public List<VCajaConsultaConTieneOperacionesVO> buscarCajasPorCajaCodEmpPlacaAndAllDepId(VCajaConsultaConTieneOperacionesVO vCajaConsultaConTieneOperacionesVO) throws PersistenceException;

 	/**
 	 * RETORNA REGISTROS DE LA VISTA V_FOLIOS_CAJA FILTRADOS POR CAJA_ID
 	 * @author Kevin Ojeda
 	 * @param vCajaConsultaConTieneOperacionesVO
 	 * @return List<VCajaConsultaConTieneOperacionesVO>
 	 * @throws PersistenceException
 	 */
 	@Select(value = V_FOLIOS_CAJA_BY_CAJA_ID)
 	@Options(statementType = StatementType.CALLABLE)
 	public List<VFoliosCajaVO> buscarFoliosByCajaId
 		(VFoliosCajaVO VFoliosCajaVO) throws PersistenceException;

 	/**
	 * Ejecuta SP SP_ADMIN_CAJAS_NUEVO
	 * @author Kevin Ojeda
	 * @param ProcInformeCorteSPVO procInformeCorteSPVO
	 * @return ProcInformeCorteSPVO
	 * @throws PersistenceException
	 */
	@Select(value = SP_ADMIN_CAJAS_NUEVO)
	@Options(statementType = StatementType.CALLABLE)
	public AdminCajasNuevoSPVO procAdminCajasNuevoSPVO(AdminCajasNuevoSPVO AdminCajasNuevoSPVO) throws PersistenceException;

 	/**
 	 * RETORNA REGISTROS DE LA VISTA V_CONSULTA_USUARIOS_CAJA FILTRADOS POR EMP_PLACA
	 * @author Kevin Ojeda
	 * @param VConsultaUsuariosCajaVO vConsultaUsuariosCajaVO
	 * @return VConsultaUsuariosCajaVO
	 * @throws PersistenceException
	 */
	@Select(value = V_CONSULTA_USUARIOS_CAJA)
	@Options(statementType = StatementType.CALLABLE)
	public List<VConsultaUsuariosCajaVO> vConsultaUsuariosCajaByEmpPlaca(VConsultaUsuariosCajaVO vConsultaUsuariosCajaVO) throws PersistenceException;

 	/**
	 * Ejecuta SP SP_ADMIN_CAJAS_USUARIOS
	 * @author Kevin Ojeda
	 * @param AdminCajasUsuariosSPVO adminCajasUsuariosSPVO
	 * @return AdminCajasUsuariosSPVO
	 * @throws PersistenceException
	 */
	@Select(value = SP_ADMIN_CAJAS_USUARIOS)
	@Options(statementType = StatementType.CALLABLE)
	public AdminCajasUsuariosSPVO procAdminCajasUsuarios(AdminCajasUsuariosSPVO adminCajasUsuariosSPVO) throws PersistenceException;
	
 	/**
	 * Ejecuta SP SP_ADMIN_CAJAS_DEPOSITOS
	 * @author Kevin Ojeda
	 * @param AdminCajasDepositosSPVO adminCajasDepositosSPVO
	 * @return AdminCajasDepositosSPVO
	 * @throws PersistenceException
	 */
	@Select(value = SP_ADMIN_CAJAS_DEPOSITOS)
	@Options(statementType = StatementType.CALLABLE)
	public AdminCajasDepositosSPVO procAdminCajasDepositos(AdminCajasDepositosSPVO adminCajasDepositosSPVO) throws PersistenceException;
	
 	/**
	 * Ejecuta SP SP_ADMIN_CAJAS_MODIF
	 * @author Kevin Ojeda
	 * @param AdminCajasDepositosSPVO adminCajasDepositosSPVO
	 * @return AdminCajasDepositosSPVO
	 * @throws PersistenceException
	 */
	@Select(value = SP_ADMIN_CAJAS_MODIF)
	@Options(statementType = StatementType.CALLABLE)
	public AdminCajasModifSPVO procAdminCajasModif(AdminCajasModifSPVO adminCajasModifSPVO) throws PersistenceException;
	
	@Select("SELECT CAJA_ID AS cajaIdD, EMP_ID AS modificadoPor, CAJA_NUM_CORTE AS cajaNumCorte FROM CAJAS WHERE CAJA_ID = #{cajaId} ")
	public VCajaConsultaVO getNumCorte(@Param("cajaId") Integer p_caja_id);
	
	/**
	 * trae el ultimo folio utilizado en la tabla transacciones
	 * @author Fernando Castillo
	 * @param cajaCode
	 * @return Long
	 */
	@Select("SELECT ULTIMO_FOLIO_SISTEMA from V_FOLIOS_CAJA WHERE TIPO_FOLIO='TRANSACCION' AND CAJA_ID = #{cajaId} ")
	Long getVFolioTransaccion(@Param("cajaId") Long cajaId);
	
	/**
	 * trae el ultimo folio utilizado en la tabla corte
	 * @author Fernando Castillo
	 * @param cajaCode
	 * @return Long
	 */
	@Select("SELECT ULTIMO_FOLIO_SISTEMA from V_FOLIOS_CAJA WHERE TIPO_FOLIO='CORTE' AND CAJA_ID = #{cajaId} ")
	Long getVfolioCorte(@Param("cajaId") Long cajaId);
	
	/**
	 * trae el ultimo folio utilizado en la tabla pago
	 * @author Fernando Castillo
	 * @param cajaCode
	 * @return Long
	 */
	@Select("SELECT ULTIMO_FOLIO_SISTEMA from V_FOLIOS_CAJA WHERE TIPO_FOLIO='PAGO' AND CAJA_ID = #{cajaId} ")
	Long getVFolioPago(@Param("cajaId") Long cajaId);

	/**
	 * selecciona el maximo registro mas 1 en a tabla PARAMETROS
	 * @author Fernando Castillo
	 * @return Long
	 */
	@Select("SELECT MAX(param_cajas_ficticias)+1 FROM PARAMETROS")
	Long getVParamCajaFicticia();
	
	/**
	 * obtiene el maximo elemento mas uno de CAJAS
	 * @author Fernando Castillo
	 * @return Long
	 */
	@Select("SELECT NVL(MAX(CAJA_ID),0) + 1 FROM CAJAS")
	Long getVCajaId();

	String GET_V_FOLIOS_CAJA = "SELECT "
			+"ULTIMO_FOLIO_SISTEMA AS ultimoFolioSistema,"
			+"ULTIMO_FOLIO_CAJA AS ultimoFolioCaja "
			+"FROM V_FOLIOS_CAJA "
			+"WHERE CAJA_ID=#{cajaId} "
			+"AND TIPO_FOLIO=#{tipoOperacion}";
	
	@Select(value = GET_V_FOLIOS_CAJA)
	VFoliosCajaVO getFoliosCaja(@Param("cajaId")Long cajaId, @Param("tipoOperacion")String tipoOperacion); 
	
	
	String ACTUALIZA_PARAM_CAJAS_FICTICIAS = "UPDATE PARAMETROS SET PARAM_CAJAS_FICTICIAS = #{vParamCajaFicticia}";
	@Update(ACTUALIZA_PARAM_CAJAS_FICTICIAS)
	void actualizaParamCajasFicticias(@Param("vParamCajaFicticia")Long vParamCajaFicticia); 
	
	/**
	 * Los datos de la caja por medio del ID
	 * @author Fernando Castillo
	 * @param cajaId
	 * @return VCajaConsultaVO
	 */
	@Select("SELECT DEP_ID as depId,CAJA_COD as cajaCod,CAJA_NOMBRE as cajaNombre,CAJA_NUM_PAGO as cajaNumPago,CAJA_NUM_TRAN as cajaNumTran,"
			+ "CAJA_NUM_CORTE as cajaNumCorte,CAJA_STATUS as cajaEstatus,EMP_ID as empId FROM CAJAS WHERE CAJA_ID = #{cajaId} ")
	VCajaConsultaVO getCajaById(@Param("cajaId") Long cajaId);

	@Select("SELECT e.emp_id as empId, c.caja_id as cajaId, corte_id as corteId "
			+ "FROM EMPLEADOS e, CAJAS c, CAJA_CORTE cc "
			+ "WHERE     e.emp_id = c.emp_id "
			+ "AND c.caja_id = cc.caja_id "
			+ "AND e.emp_tip_id IN (1, 3, 4) "
			+ "AND e.emp_status = 'A' "
			+ "AND emp_placa = #{placaOficial} "        
	        + "AND TRUNC (corte_fecha) = #{fechaHora} ")
	public VBuscarCorteCaja getCorteId(@Param("fechaHora") String fecha,
			@Param("placaOficial") String placaOficial);                

}
