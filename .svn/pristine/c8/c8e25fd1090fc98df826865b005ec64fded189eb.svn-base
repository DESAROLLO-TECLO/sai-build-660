package mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosValidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.TotalesCentroPagosVO;

@Mapper
public interface CentroPagosMyBatisDAO {
	
	 
	/***************************************************** ULTIMA CONSULTA AL CENTRO DE PAGOS***********************************************/


	String ULTIMA_CONSULTA_CP	= "SELECT to_char(fecha_ultima_consulta,'dd/mm/yyyy  HH24:MI:SS') fecha FROM CTRO_PAGOS_ULTIMA_CONSULTA ";

	@Select( ULTIMA_CONSULTA_CP)
 	public String ultimaConsultaCentroPagos() throws PersistenceException;

	/***************************************************** CONSULTA TOTALES POR RANGO DE FECHA ***********************************************/

	
	String TOTAL_PAGOS_TIPO_CP = "SELECT nvl(sum(cantidad),0) cantidad, "
								+ "CASE  WHEN ESTATUS_PROCESO =  1 AND PAGO_ORIGEN='W' THEN 'WEB_COMPLETO' "
								+ "		 WHEN ESTATUS_PROCESO =  0 AND PAGO_ORIGEN='W' THEN 'WEB_INCOMPLETO' "
								+ " 	 WHEN ESTATUS_PROCESO =  1 AND PAGO_ORIGEN='H' THEN 'HH_COMPLETO' "
								+ "    	 WHEN ESTATUS_PROCESO =  0 AND PAGO_ORIGEN='H' THEN 'HH_INCOMPLETO'  END AS conceptoPago "
								+ "FROM V_CTRO_PAGOS_REP_TOTALES  WHERE pago_fecha  BETWEEN TO_DATE ( #{fechaInicio} ,'dd/mm/yyyy') "
								+ "AND TO_DATE ( #{fechaFin} ,'dd/mm/yyyy') GROUP BY  pago_origen , estatus_proceso ";
	 	
	@Select( TOTAL_PAGOS_TIPO_CP)
 	public List< TotalesCentroPagosVO>  totalCentroPagosPorTipoYFecha(@Param("fechaInicio") String fechaInicio,  @Param("fechaFin") String fechaFin) throws PersistenceException;
	
	
	/***************************************************** CONSULTA TOTALES POR RANGO DE FECHA ***********************************************/
	String TOTAL_PAGOS_RANGO_FECHA = "SELECT TO_CHAR(cpv1.fecha_pago_ctro_pagos,'dd/mm/yyyy')   as fecha, count(cpv1.CTRO_PAGOS_VALIDA_ID) as total, "
			+ "  count(cpv3.CTRO_PAGOS_VALIDA_ID) as webCompleto, count(cpv4.CTRO_PAGOS_VALIDA_ID) as webIncompleto, "
			+ " count(cpv5.CTRO_PAGOS_VALIDA_ID) as hhCompleto, count(cpv6.CTRO_PAGOS_VALIDA_ID) as hhIncompleto "
			+ "FROM    CTRO_PAGOS_VALIDA cpv1 "
			+ "left join CTRO_PAGOS_VALIDA cpv2 on cpv1.CTRO_PAGOS_VALIDA_ID = cpv2.CTRO_PAGOS_VALIDA_ID "
			+ "left join CTRO_PAGOS_VALIDA cpv3 on cpv1.CTRO_PAGOS_VALIDA_ID = cpv3.CTRO_PAGOS_VALIDA_ID and cpv3.ESTATUS_PROCESO = 1 and cpv3.PAGO_ORIGEN = 'W' "
			+ "left join CTRO_PAGOS_VALIDA cpv4 on cpv1.CTRO_PAGOS_VALIDA_ID = cpv4.CTRO_PAGOS_VALIDA_ID and cpv4.ESTATUS_PROCESO = 0 and cpv4.PAGO_ORIGEN = 'W' "
			+ "left join CTRO_PAGOS_VALIDA cpv5 on cpv1.CTRO_PAGOS_VALIDA_ID = cpv5.CTRO_PAGOS_VALIDA_ID and cpv5.ESTATUS_PROCESO = 1 and cpv5.PAGO_ORIGEN = 'H' "
			+ "left join CTRO_PAGOS_VALIDA cpv6 on cpv1.CTRO_PAGOS_VALIDA_ID = cpv6.CTRO_PAGOS_VALIDA_ID and cpv6.ESTATUS_PROCESO = 0 and cpv6.PAGO_ORIGEN = 'H' "
			+ "WHERE cpv1.fecha_pago_ctro_pagos between to_date (#{fechaInicio},'dd/mm/yyyy')  AND to_date (#{fechaFin},'dd/mm/yyyy') "
			+ "GROUP BY cpv1.fecha_pago_ctro_pagos "
			+ "ORDER BY cpv1.fecha_pago_ctro_pagos desc ";
	
	
	@Select(value = TOTAL_PAGOS_RANGO_FECHA)
	public List<CentroPagosVO> consultaTotalesRangoFecha(@Param("fechaInicio") String fechaInicio,  @Param("fechaFin") String fechaFin) throws PersistenceException;
	
	
	
	/***************************************************** CONSULTA TOTALES POR RANGO DE FECHA ***********************************************/
	 
	String PAGOS_INCORRECTOS_X_PERIODO = " SELECT nci,nvl (infrac_num,' ')  as infracNum, nvl(EMP_PLACA,' ')  as empPlaca, "
			+ " nvl(EMP_NOMBRE,' ')  as empNombre, EXISTE_INFRACCION as existeInfraccion, EXISTE_PAGO as existePago, "
			+ " EXISTE_DETALLE_PAGO as existeDetallePagos, EXISTE_DETALLE_CARGO as existeDetalleCargos, EXISTE_TRANSACCION as existePagoTransaccion, "
			+ " EXISTE_TRANSAC_INFRAC as existeInfracTransaccion, ESTATUS_PROCESO as estatusProceso, TO_CHAR(FECHA_CREACION, 'dd/mm/yyyy')  as fechaCreacion, "
			+ " TO_CHAR(ULTIMA_MODIFICACION,'dd/mm/yyyy')  as fechaModificacion, to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy')  as fechaPagoCtroPagos "
			+ " FROM CTRO_PAGOS_VALIDA where estatus_proceso=0 AND fecha_pago_ctro_pagos BETWEEN TO_DATE (#{fechaInicio}, 'dd/mm/yyyy') "
			+ " AND TO_DATE (#{fechaFin},'dd/mm/yyyy')  ORDER BY fecha_pago_ctro_pagos;";
	
	@Select(value = PAGOS_INCORRECTOS_X_PERIODO)
	public List<CentroPagosValidaVO> getAllCtroPagosValidaInCorrectosPorPeriodo(@Param("fechaInicio") String fechaInicio,  @Param("fechaFin") String fechaFin) throws PersistenceException;
	
 	String PAGOS_INCORRECTOS_X_FECHA_TIPO_PAGO = " SELECT nci,nvl (infrac_num,' ') as infracNum, nvl(EMP_PLACA,' ')   as empPlaca, "
			+ "  nvl(EMP_NOMBRE,' ') as empNombre, EXISTE_INFRACCION as existeInfraccion, EXISTE_PAGO as existePago, "
			+ "  EXISTE_DETALLE_PAGO as existeDetallePagos, EXISTE_DETALLE_CARGO as existeDetalleCargos, EXISTE_TRANSACCION as existePagoTransaccion, "
			+ "  EXISTE_TRANSAC_INFRAC as existeInfracTransaccion, ESTATUS_PROCESO  as estatusProceso,  TO_CHAR(FECHA_CREACION,'dd/mm/yyyy') as fechaCreacion, "
			+ "  TO_CHAR(ULTIMA_MODIFICACION,'dd/mm/yyyy')   as fechaModificacion, to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy')   as fechaPagoCtroPagos "
			+ "  FROM CTRO_PAGOS_VALIDA where estatus_proceso=0  AND fecha_pago_ctro_pagos=TO_DATE (#{fechaInicio},'dd/mm/yyyy') "
			+ "  AND pago_origen = #{tipo}  ORDER BY fecha_pago_ctro_pagos";
	

	@Select(value = PAGOS_INCORRECTOS_X_FECHA_TIPO_PAGO)
	public List<CentroPagosValidaVO> getAllCtroPagosValidaInCorrectosPorFechaTipoPago(@Param("fechaInicio") String fechaInicio,  @Param("tipo") String tipo ) throws PersistenceException;
	
	
 	String PAGOS_INCORRECTOS_X_PERIODO_TIPO_PAGO = "   SELECT nci,nvl (infrac_num,' ')   as infracNum, nvl(EMP_PLACA,' ')   as empPlaca, "
			+ " nvl(EMP_NOMBRE,' ') as empNombre, EXISTE_INFRACCION as existeInfraccion, EXISTE_PAGO as existePago, "
			+ " EXISTE_DETALLE_PAGO as existeDetallePagos, EXISTE_DETALLE_CARGO as existeDetalleCargos,EXISTE_TRANSACCION as existePagoTransaccion, "
			+ " EXISTE_TRANSAC_INFRAC as existeInfracTransaccion, ESTATUS_PROCESO  as estatusProceso, TO_CHAR(FECHA_CREACION,'dd/mm/yyyy') as fechaCreacion, "
			+ " TO_CHAR(ULTIMA_MODIFICACION,'dd/mm/yyyy')  as fechaModificacion, to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy') as fechaPagoCtroPagos"
			+ " FROM CTRO_PAGOS_VALIDA where estatus_proceso = 0  AND fecha_pago_ctro_pagos BETWEEN TO_DATE (#{fechaInicio},'dd/mm/yyyy') "
			+ " AND TO_DATE (#{fechaFin},'dd/mm/yyyy')  AND pago_origen= #{tipo} ORDER BY fecha_pago_ctro_pagos,pago_origen";
	

	@Select(value = PAGOS_INCORRECTOS_X_PERIODO_TIPO_PAGO)
	public List<CentroPagosValidaVO> getAllCtroPagosValidaInCorrectosPorPeriodoTipo(@Param("fechaInicio") String fechaInicio,  @Param("fechaFin") String fechaFin, @Param("tipo") String tipo) throws PersistenceException;
	
	
 	String PAGOS_CORRECTOS_X_PERIODO_TIPO_PAGO = " SELECT nci,infrac_num as infracNum, EMP_PLACA as empPlaca, EMP_NOMBRE as empNombre, "
					+ "	to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy') as fechaPagoCtroPagos "
					+ " FROM CTRO_PAGOS_VALIDA where estatus_proceso = 1 AND fecha_pago_ctro_pagos BETWEEN TO_DATE (#{fechaInicio},'dd/mm/yyyy') "
					+ " AND TO_DATE (#{fechaFin},'dd/mm/yyyy') AND pago_origen=#{tipo} ORDER BY fecha_pago_ctro_pagos, pago_origen ";
	
	
	@Select(value = PAGOS_CORRECTOS_X_PERIODO_TIPO_PAGO)
	public List<CentroPagosValidaVO> getAllCtroPagosValidaCorrectosPorPeriodoTipoPago(@Param("fechaInicio") String fechaInicio,  @Param("fechaFin") String fechaFin, @Param("tipo") String tipo) throws PersistenceException;
	
 
		String PAGOS_CORRECTOS_X_PERIODO = "SELECT nci,infrac_num as infracNum, EMP_PLACA as empPlaca, EMP_NOMBRE  as empNombre, "
				+ " to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy')  as fechaPagoCtroPagos "
				+ " FROM CTRO_PAGOS_VALIDA where estatus_proceso = 1 AND fecha_pago_ctro_pagos BETWEEN TO_DATE (#{fechaInicio},'dd/mm/yyyy') "
				+ " AND TO_DATE (#{fechaFin},'dd/mm/yyyy') ORDER BY fecha_pago_ctro_pagos ";
		
		@Select(value = PAGOS_CORRECTOS_X_PERIODO)
		public List<CentroPagosValidaVO> getAllCtroPagosValidaCorrectosPorPeriodo(@Param("fechaInicio") String fechaInicio,  @Param("fechaFin") String fechaFin) throws PersistenceException;
		
	
 		String PAGOS_CORRECTOS_X_FECHA_TIPO_PAGO = " SELECT nci,infrac_num as infracNum, EMP_PLACA  as empPlaca, EMP_NOMBRE as empNombre, "
 				+ "	to_char(fecha_pago_ctro_pagos,'dd/mm/yyyy')   as fechaPagoCtroPagos "
				+ " FROM CTRO_PAGOS_VALIDA where estatus_proceso=1 AND fecha_pago_ctro_pagos = TO_DATE (#{fechaInicio},'dd/mm/yyyy') "
				+ " AND pago_origen=#{tipo}   ORDER BY fecha_pago_ctro_pagos, pago_origen ";
		
		@Select(value = PAGOS_CORRECTOS_X_FECHA_TIPO_PAGO)
		public List<CentroPagosValidaVO> getAllCtroPagosValidaCorrectosPorFechaTipoPago(@Param("fechaInicio") String fechaInicio,  @Param("tipo") String tipo ) throws PersistenceException;
		
}	

