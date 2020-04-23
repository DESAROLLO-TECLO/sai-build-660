package mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaGetVaucherFVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.recibo.ReciboPagoVO;

@Mapper
public interface GarantiaPagosProcedureMyBatisDAO {

	String procedureGetPagoVaucher = "begin Proc_Linea_Banco_Nueva (#{infracNum}, "
		
			+ "#{resultado,jdbcType=VARCHAR, javaType=java.lang.String, mode=OUT}, "
			+ "#{voucher, jdbcType=VARCHAR, javaType=java.lang.String, mode=OUT}, "
			+ "#{voucherTipo, jdbcType=VARCHAR, javaType=java.lang.String, mode=OUT}, "
			+ "#{voucherPagoOrigen, jdbcType=VARCHAR, javaType=java.lang.String, mode=OUT}) ; end;";
	
	@Select(value= procedureGetPagoVaucher)
	@Options(statementType = StatementType.CALLABLE)
	public VSSPGarantiaGetVaucherFVO getVoucher(VSSPGarantiaGetVaucherFVO garantiaGetVaucherFVO) throws PersistenceException;

	
	
	String procedureGetPagoRecibo  =  "SELECT INFRACTOR as infracNombre,CALLE as infrCalle, NUM_EXTERIOR as infrNumext, NUM_INTERIOR as infrNumInt, "
    		+ "		COLONIA as infrCol, DELEGACION as infrDel, V_ESTADO as infrEdo, RFC as infracRFC, CAJA_COD as pagosCajaCod, "
    		+ "		FECHA_PAGO as pagosPagFecha, PARTIDA as pagosPartidaNo, ID_PAGO as idPago, INFRACCION as infracNum, "
    		+ "		FECHA_INFRAC as infracFecha, PLACA as infracPlacas, ARTICULO as artNum, FRACCION as artFraccion, PARRAFO, "
    		+ "		INCISO as artInciso, FUNDAMENTACION, MAYOR_TRES_TONELADAS as pagosCapacidadArr, NUM_DIAS_PISO as diasPiso, MONTO as pagosMonto, "
    		+ "		REDUCCION as pagosReduccion, ACTUALIZACION as pagosActualizacion,  RECARGOS as pagosRecargos, TOTAL_INFRAC as pagosTotal,"
    		+ "		DERECHO_PISO as derechoPiso, ARRASTRE as pagosMontoDer, TOTAL as pagosTotalPagos, FORMA_PAGO as formaPago, NUM_TARJETA as transTarjeta,"
    		+ "		BANCO as banco, NUM_AUTORIZACION as transReferencia, AGENTE as infracAgente, DEPOSITO as infracDeposito, FORMA_PAGO_2 as OEformaPago2,"
    		+ "		CANDADO as candado "						
    		+ "		FROM 	TABLE ( CAST ( FN_INFRACCION_RECIBO_TP(  #{pagCajaId} , #{pagId} ) AS T_INFRACCIONES_RECIBO_TP))";
	
	
	
	@Select(value= procedureGetPagoRecibo)
	@Options(statementType = StatementType.CALLABLE)
	public ReciboPagoVO getReciboPago(@Param("pagCajaId")Long pagCajaId ,@Param("pagId")Long pagId ) throws PersistenceException;

	
	
}
