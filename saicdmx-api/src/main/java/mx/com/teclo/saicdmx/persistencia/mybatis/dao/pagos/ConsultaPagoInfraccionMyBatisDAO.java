package mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.InfraccionDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.InfraccionPorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagadoVO;

@Mapper
public interface ConsultaPagoInfraccionMyBatisDAO {
	
	/***********************************************   OBTIENE INFRACCIONES EN DEPOSITO *********************************************/
	
	static final String V_PAGOS_BUSQ_INFRACCION = "SELECT INFRACCION,PLACA,FECHA,NCI,STATUS,MARCA,MODELO,COLOR,INFRACTOR,"+
												  "DEP_ID as depId,DEPOSITO,IMPRESA,DOCUMENTO,VALOR_BUSQUEDA,TIPO_BUSQUEDA as tipoBusqueda "+
												  "FROM V_PAGOS_BUSQ_INFRACCION WHERE  (INFRACCION NOT LIKE '02%')       "+  
												  "AND DEP_ID = #{deposito}";

	String GET_V_INFRACCIONES_IMPRESA 	       	= V_PAGOS_BUSQ_INFRACCION  + " AND IMPRESA = #{valor} ";
	String GET_V_INFRACCIONES_INFRACCION        = V_PAGOS_BUSQ_INFRACCION  + " AND INFRACCION = #{valor} ";
	String GET_V_INFRACCIONES_PLACA 	       	= V_PAGOS_BUSQ_INFRACCION  + " AND PLACA = #{valor} ";
	String GET_V_INFRACCIONES_NCI 		       	= V_PAGOS_BUSQ_INFRACCION  + " AND NCI = #{valor} ";

	
	@Select(value = GET_V_INFRACCIONES_INFRACCION)
	public List<InfraccionDepositoVO> buscarInfraccionesPagoInfraccion(@Param("valor") String valor,  @Param("deposito") String deposito);

	@Select(value = GET_V_INFRACCIONES_IMPRESA)
	public List<InfraccionDepositoVO> buscarInfraccionesPagoImpresa(@Param("valor") String valor,  @Param("deposito") String deposito);

	@Select(value = GET_V_INFRACCIONES_PLACA)
	public List<InfraccionDepositoVO> buscarInfraccionesPagoPlaca(@Param("valor") String valor,  @Param("deposito") String deposito);

	@Select(value = GET_V_INFRACCIONES_NCI)
	public List<InfraccionDepositoVO> buscarInfraccionesPagoNCI(@Param("valor") String valor,  @Param("deposito") String deposito);
	
	
	
	/***********************************************   OBTIENE INFRACCIONES POR PAGAR *********************************************/

	static final String V_INFRACCIONES_POR_PAGAR = "SELECT T_INFRACCION as infracNum,T_INFRAC_MOSTRAR ,T_PLACA as infracPlaca, T_FECHA as infracFecha,					  "+
													"T_MOTIVO as infracMotivacion,T_SANSION as infracSancion, T_MONTO as infracMonto, T_CANDADO as infracCandado,		  "+
													"T_REDUCCION as infracDescuento, T_ACTUALIZACION as infracActualizacion,T_RECARGOS as infracRecargo,                  "+
													"T_DERECHO_PISO as infracDerechoPiso ,T_ARRASTRE as infracArrastre,T_TOTAL as infracTotalPagar,T_NCI as infracNumCtrl,"+
													"T_GRUA_COD  FROM TABLE ( CAST (FN_PAGOS_INFRACCIONES_CALCULO(#{nci} , #{empleadoCaja} ) AS T_PAGOS_INFRACCIONES ))";
	 
	
	@Select(value = V_INFRACCIONES_POR_PAGAR)
	public List<InfraccionPorPagarVO> buscarInfraccionesPorPagar(@Param("nci") String nci,  @Param("empleadoCaja") Integer empleadoCaja);

	
	/****************************************   OBTIENE INFRACCIONES PAGADAS POR DIA ************************************************/
	
	 static final String V_INFRAC_PAGADAS_DIA = "SELECT INFRAC_NUM as infracNum, INFRAC_NUM_CTRL as infracNumCtrl, INFRAC_VEH_PLACA as infracPlaca, " 				+
			 									"INFRAC_FECHA as infracFecha, INFRAC_MOTIVACION as infracMotivacion, INFRAC_SANCION as infracSancion,"				+
			 									"INFRAC_MONTO as infracMonto, INFRAC_DIAS_PISO as infracDiasPiso, INFRAC_ACTUALIZACION as infracActualizacion, "	+
			 									"INFRAC_DESCUENTO as infracDescuento, INFRAC_RECARGO as infracRecargo,INFRAC_PISO as infracDerechoPiso, "			+
			 									"INFRAC_ARRASTRE as infracArrastre, INFRAC_CANDADO as infracCandado, INFRAC_TOTAL_PAGADO as infracTotalPagar, "		+
									            "INFRAC_FECHA_PAGO as infracFechaPago,INFRAC_PAGO_CON_MONTO as pagoConMonto , INFRAC_CON_VOUCHER as pagoConVoucher FROM V_INFRAC_PAGADAS_DIA ";
	 
	 
	 String V_INFRAC_PAGADAS_DIA_PLACA_SIN_FECHA	  =  V_INFRAC_PAGADAS_DIA + " WHERE INFRAC_VEH_PLACA = #{valor}  AND INFRAC_FECHA_PAGO = TRUNC(SYSDATE,'DD') ";
	 String V_INFRAC_PAGADAS_DIA_PLACA_CON_FECHA	  =  V_INFRAC_PAGADAS_DIA + " WHERE INFRAC_VEH_PLACA = #{valor}  AND INFRAC_FECHA_PAGO = TO_DATE(#{fecha},'DD/MM/YYYY') ";

	 String V_INFRAC_PAGADAS_DIA_INFRACCION_SIN_FECHA =  V_INFRAC_PAGADAS_DIA + " WHERE INFRAC_NUM = #{valor}  AND INFRAC_FECHA_PAGO = TRUNC(SYSDATE,'DD') ";
	 String V_INFRAC_PAGADAS_DIA_INFRACCION_CON_FECHA =  V_INFRAC_PAGADAS_DIA + " WHERE INFRAC_NUM = #{valor}  AND INFRAC_FECHA_PAGO = TO_DATE(#{fecha},'DD/MM/YYYY') ";
	 
	 String V_INFRAC_PAGADAS_DIA_NCI_SIN_FECHA		  =  V_INFRAC_PAGADAS_DIA + " WHERE INFRAC_NUM_CTRL = #{valor}  AND INFRAC_FECHA_PAGO = TRUNC(SYSDATE,'DD') ";
	 String V_INFRAC_PAGADAS_DIA_NCI_CON_FECHA		  =  V_INFRAC_PAGADAS_DIA + " WHERE INFRAC_NUM_CTRL = #{valor}  AND INFRAC_FECHA_PAGO = TO_DATE(#{fecha},'DD/MM/YYYY') ";
 
    
    @Select(value = V_INFRAC_PAGADAS_DIA_PLACA_SIN_FECHA)
	public List<InfraccionPorPagarVO> buscarInfraccionesPagadasPorDiaPlacaSinFecha(@Param("valor") String valor );
    @Select(value = V_INFRAC_PAGADAS_DIA_PLACA_CON_FECHA)
	public List<InfraccionPorPagarVO> buscarInfraccionesPagadasPorDiaPlacaConFecha(@Param("valor") String valor, @Param("fecha") String fecha );
	@Select(value = V_INFRAC_PAGADAS_DIA_INFRACCION_SIN_FECHA)
	public List<InfraccionPorPagarVO> buscarInfraccionesPagadasPorDiaInfraSinFecha(@Param("valor") String valor );
    @Select(value = V_INFRAC_PAGADAS_DIA_INFRACCION_CON_FECHA)
	public List<InfraccionPorPagarVO> buscarInfraccionesPagadasPorDiaInfraConFecha(@Param("valor") String valor, @Param("fecha") String fecha );
	@Select(value = V_INFRAC_PAGADAS_DIA_NCI_SIN_FECHA)
	public List<InfraccionPorPagarVO> buscarInfraccionesPagadasPorDiaNCISinFecha(@Param("valor") String valor );
    @Select(value = V_INFRAC_PAGADAS_DIA_NCI_CON_FECHA)
	public List<InfraccionPorPagarVO> buscarInfraccionesPagadasPorDiaNCIConFecha(@Param("valor") String valor, @Param("fecha") String fecha );

	
	
    
	/****************************************   OBTIENE INFRACCIONES PAGADAS   ************************************************/

    static final String V_INFRAC_PAGADAS =  "SELECT INFRACCION as infracNum,PLACA as infracPlaca, PAGO_FECHA as pagoFecha, NCI as infracNumCtrl,"	+
    										"MARCA as vehiculoMarca, MODELO as vehiculoModelo,COLOR as vehiculoColor,INFRACTOR as infractorNombre, "+ 
    										"TIPO_DE_PAGO as tipoPago, IMPRESA as infracImpresa,DOCUMENTO as infracDocumento,PAGO_CON_MONTO as pagoConMonto,"					+
    										"TIENE_VOUCHER as pagoTieneVoucher FROM V_INFRAC_PAGADAS ";
    
    
    String  GET_V_INFRAC_PAGADAS_IMPRESA 	 = V_INFRAC_PAGADAS  + " WHERE IMPRESA 		= #{valor} ";
	String  GET_V_INFRAC_PAGADAS_INFRACCION  = V_INFRAC_PAGADAS  + " WHERE INFRACCION 	= #{valor} ";
	String  GET_V_INFRAC_PAGADAS_PLACA 	     = V_INFRAC_PAGADAS  + " WHERE PLACA 		= #{valor} ";
	String  GET_V_INFRAC_PAGADASS_NCI 		 = V_INFRAC_PAGADAS  + " WHERE NCI 			= #{valor} ";
	String  GET_V_INFRAC_PAGADASS_DOCUMENTO  = V_INFRAC_PAGADAS  + " WHERE DOCUMENTO 	= #{valor} ";

 
	@Select(value = GET_V_INFRAC_PAGADAS_IMPRESA)
	public List<PagadoVO> buscarInfraccionesPagadasImpresa( @Param("valor") String valor );

	@Select(value = GET_V_INFRAC_PAGADAS_INFRACCION)
	public List<PagadoVO> buscarInfraccionesPagadasInfraccion( @Param("valor") String valor );

	@Select(value = GET_V_INFRAC_PAGADAS_PLACA)
	public List<PagadoVO> buscarInfraccionesPagadasPlaca( @Param("valor") String valor );

	@Select(value = GET_V_INFRAC_PAGADASS_NCI)
	public List<PagadoVO> buscarInfraccionesPagadasNCI( @Param("valor") String valor );

	@Select(value = GET_V_INFRAC_PAGADASS_DOCUMENTO)
	public List<PagadoVO> buscarInfraccionesPagadasDocumento( @Param("valor") String valor );

/***********************************************   OBTIENE INFRACCIONES EN DEPOSITO ACTA ADMINISTRATIVA *********************************************/
	
	static final String V_PAGOS_BUSQ_INFRACCION_ACTA = "SELECT INFRACCION,PLACA,FECHA,NCI,STATUS,MARCA,MODELO,COLOR,INFRACTOR,"+
														  "DEP_ID as depId,DEPOSITO,IMPRESA,DOCUMENTO,VALOR_BUSQUEDA,TIPO_BUSQUEDA as tipoBusqueda "+
														  "FROM V_PAGOS_BUSQ_INFRACCION WHERE  (INFRACCION  LIKE '02%')"+  
														  "AND DEP_ID = #{deposito}";

	String GET_V_INFRACCIONES_IMPRESA_ACTA	       	= V_PAGOS_BUSQ_INFRACCION_ACTA  + " AND IMPRESA = #{valor} ";
	String GET_V_INFRACCIONES_INFRACCION_ACTA       = V_PAGOS_BUSQ_INFRACCION_ACTA  + " AND INFRACCION = #{valor} ";
	String GET_V_INFRACCIONES_PLACA_ACTA 	       	= V_PAGOS_BUSQ_INFRACCION_ACTA  + " AND PLACA = #{valor} ";
	String GET_V_INFRACCIONES_NCI_ACTA		       	= V_PAGOS_BUSQ_INFRACCION_ACTA  + " AND NCI = #{valor} ";

	
	@Select(value = GET_V_INFRACCIONES_INFRACCION_ACTA)
	public List<InfraccionDepositoVO> buscarInfraccionesActaInfraccion(@Param("valor") String valor,  @Param("deposito") String deposito);

	@Select(value = GET_V_INFRACCIONES_IMPRESA_ACTA)
	public List<InfraccionDepositoVO> buscarInfraccionesActaImpresa(@Param("valor") String valor,  @Param("deposito") String deposito);

	@Select(value = GET_V_INFRACCIONES_PLACA_ACTA)
	public List<InfraccionDepositoVO> buscarInfraccionesActaPlaca(@Param("valor") String valor,  @Param("deposito") String deposito);

	@Select(value = GET_V_INFRACCIONES_NCI_ACTA)
	public List<InfraccionDepositoVO> buscarInfraccionesActaNCI(@Param("valor") String valor,  @Param("deposito") String deposito);
	
	
	/******************************************** OBTIENE INFRACCION EN DEPOSITO PPOR NUMERO DE PLACA SIN IMPORTAR SI ESTA PAGADA O NO ****************************/
	
	static final String GET_INFRACCION_INGRESADA_DEPOSITO = " SELECT i.infrac_num as infraccion,infrac_placa as placa,infrac_num_ctrl as nci, I.Dep_Id as depId"
														  + " FROM infracciones i JOIN ingresos ingr ON i.infrac_num = ingr.infrac_num"
														  + " WHERE     Fn_Existe_Ingreso (i.infrac_num, 'I') > 0 AND i.estatus_cancelacion IS NULL"
														  + " AND i.infrac_m_fecha_hora >=  TO_DATE('01/01/2009', 'DD/MM/YYYY')"
														  + " AND infrac_placa= #{placa}";
          
          
	
	@Select(value = GET_INFRACCION_INGRESADA_DEPOSITO)
	public List<InfraccionDepositoVO> buscarInfraccionIngresada(@Param("placa") String placa);
	
}
