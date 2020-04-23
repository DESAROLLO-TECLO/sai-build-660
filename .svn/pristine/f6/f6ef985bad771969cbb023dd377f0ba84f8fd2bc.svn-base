package mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;

@Mapper
public interface PagoInfraccionMyBatisDAO {
	
	/********************************************   OBTIENE EL DETALLE DE EL MONTO A PAGAR POR INFRACCION  ******************************************/

	 String FN_PAGOS_INFRACCION     = "SELECT T_INFRACCION as numInfrac, T_NCI as numCtrl, T_ARRASTRE as arrastre, T_DERECHO_PISO as derechoPiso, " +
             							 "T_MONTO as monto, T_REDUCCION as descuento, T_ACTUALIZACION as actualizacion, T_RECARGOS as recargos, " 	+
             							 "T_TOTAL as totalPago,T_CANDADO as candado ,T_MONTO_ACTUALIZADO as infracMontoActualizado " 				+
             							 "FROM TABLE ( CAST (  FN_PAGOS_INFRACCION_DETALLE ( #{nci}, #{empCaja}) AS T_PAGOS_INFRACCIONES))";
 
	@Select(value = FN_PAGOS_INFRACCION)
	@Options(statementType = StatementType.CALLABLE)
	public PagoVO consultarDetallePago( @Param("nci") String nci,  @Param("empCaja") Long empCaja ) throws PersistenceException;
	
	/***************************************************** GUARDA EL PAGO DE INFRACCION EN EFECTIVO *************************************************/


	String SP_PAGOS_TP_EFECTIVO			= " BEGIN SP_PAGOS_TP(#{tipoPago}, #{cajaId}, #{numCtrl}, #{numInfrac}, #{monto}, #{descuento}, "			 +
										"#{actualizacion}, #{recargos}, #{arrastre}, #{derechoPiso}, #{adicionales}, #{lugarPago}, #{entidad}, " +
										"#{referencia}, #{transaccion}, #{usuario}, #{formaPago}, #{candado},#{conceptoPagoInfraccion}, "		 +
										"#{conceptoPagoArrastre}, #{conceptoPagoPiso}, #{conceptoPagoCandado}, "								 +
										"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "								 +
										"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";

	 

	@Select(value = SP_PAGOS_TP_EFECTIVO)
	@Options(statementType = StatementType.CALLABLE)
	public PagoVO guardarPagoEfectivo( PagoVO pagoVO ) throws PersistenceException;
	
	/****************************************************** GUARDA EL PAGO DE INFRACCION POR TARJETA ************************************************/
 
	/*String SP_PAGOS_TP_TARJETA 			= " BEGIN PROC_TRANSACCIONES ( #{cajaId}, #{totalPago}, #{numInfrac}, #{bancoTarjeta}, #{numTarjeta}, #{nomPropTarjeta}, " 	+
									      "#{transRespuesta}, #{numAutorizacion}, #{nomAfil}, #{banAfil}, #{fechaTrans}, #{refeTrans}, "						   	+
										  "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "								 					+
										  "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "  												  	+ 
										  "#{voucher}, #{numeroOperacion}); end;";*/
	String SP_PAGOS_TP_TARJETA 			= " BEGIN SP_TRANSACCIONES ( #{cajaId}, #{totalPago}, #{numInfrac}, #{bancoTarjeta}, #{numTarjeta}, #{nomPropTarjeta}, " 	+
		      "#{transRespuesta}, #{numAutorizacion}, #{nomAfil}, #{banAfil}, #{fechaTrans}, #{refeTrans}, "						   	+
			  "#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "								 					+
			  "#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "  												  	+ 
			  "#{voucher}, #{numeroOperacion}); end;";

	@Select(value = SP_PAGOS_TP_TARJETA)
	@Options(statementType = StatementType.CALLABLE)
	public PagoVO guardarPagoTarjeta( PagoVO pagoVO ) throws PersistenceException;
	
	/*************************************************************** PENDIENTE ***********************************************************************/

	
	String SP_PAGOS_ACTAS_EMBARGO 	= " BEGIN SP_PAGOS_ACTAS_EMBARGO( #{cajaId}, #{numCtrl}, #{arrastre}, #{derechoPiso}, " +
										"#{formaPago}, #{referencia}, #{transaccion}, #{usuario}, "							+
										"#{resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}, "			+
										"#{mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); end;";
						


	@Select(value = SP_PAGOS_ACTAS_EMBARGO)
	@Options(statementType = StatementType.CALLABLE)
	public PagoVO guardarPagoActaAdministrativa(PagoVO pagoVO) throws PersistenceException;

	/***************************************************** CONSULTA DATOS PARA LA BITACORA *************************************************/
	String SQL_USUARIO_OLD = "SELECT MODIFICADO_POR FROM INFRACCIONES WHERE INFRAC_NUM = #{infracNum} union "
						   + "SELECT MODIFICADO_POR FROM INFRACCIONES_RADAR WHERE INFRAC_NUM = #{infracNum} union "
						   + "SELECT MODIFICADO_POR FROM INFRACCIONES_DIGITALIZACION WHERE INFRAC_NUM = #{infracNum}";
	 
	/***
	 * @author Fernando Castillo
	 * @return Usuario anterior
	 */
	@Select(SQL_USUARIO_OLD)
	public String sqlUsuarioOld(@Param("infracNum")String infracNum);
	
	String SQL_CAJA_NUM_OLD = "SELECT CAJA_NUM_PAGO FROM CAJAS "
			+ "WHERE CAJA_ID = #{cajaId}";
	 
	/***
	 * @author Fernando Castillo
	 * @return CAJA_NUM_PAGO anterior
	 */
	@Select(SQL_CAJA_NUM_OLD)
	public String sqlCajaNumPagoOld(@Param("cajaId")String cajaId);
}
