package mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.com.teclo.saicdmx.persistencia.vo.empleados.EmpleadosVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosValidaVO;

@Mapper
public interface CentroPagosProcedureMyBatisDAO {
	
	/****Insert */
	
	
	String REGISTRO_CETRO_PAGOS_VALIDA = "INSERT INTO CTRO_PAGOS_VALIDA "
            + " (NCI,INFRAC_NUM,EMP_ID,EMP_PLACA,EMP_NOMBRE,EMP_ADSCRIPCION, "
            + " EXISTE_INFRACCION,EXISTE_PAGO,EXISTE_DETALLE_PAGO, "
            + " EXISTE_DETALLE_CARGO,EXISTE_TRANSACCION,EXISTE_TRANSAC_INFRAC, "
            + " ESTATUS_PROCESO,fecha_pago_ctro_pagos,pago_origen) "
            + " VALUES (#{nci},#{infracNum},#{empId},#{empPlaca},#{empNombre},#{empAdscripcion},"
            + " #{existeInfraccion},#{existePago},#{existeDetallePagos},"
            + " #{existeDetalleCargos},#{existePagoTransaccion},#{existeInfracTransaccion},"
            + " #{estatusProceso}, to_date(#{fechaPagoCtroPagos},'dd/mm/yyyy'),#{pagoOrigen}) ";
	
	@Insert(REGISTRO_CETRO_PAGOS_VALIDA)
	public boolean crearRegistroCtroPagosValida(CentroPagosValidaVO centroPagosValidaVO);
	  
	String ACTUALIZA_CENTRO_PAGOS = "UPDATE CTRO_PAGOS_VALIDA "
            + " SET INFRAC_NUM=#{infracNum},EMP_ID=#{empId},EMP_PLACA=#{empPlaca},EMP_NOMBRE=#{empNombre},EMP_ADSCRIPCION=#{empAdscripcion}, "
            + " EXISTE_INFRACCION=#{existeInfraccion},EXISTE_PAGO=#{existePago},EXISTE_DETALLE_PAGO=#{existeDetallePagos}, "
            + " EXISTE_DETALLE_CARGO=#{existeDetalleCargos},EXISTE_TRANSACCION=#{existePagoTransaccion},EXISTE_TRANSAC_INFRAC=#{existeInfracTransaccion}, "
            + " ESTATUS_PROCESO=#{estatusProceso} "
            + " WHERE nci=#{nci} ";
	
	@Update(ACTUALIZA_CENTRO_PAGOS)
    public boolean actualizarRegistroCtroPagosValida(CentroPagosValidaVO centroPagosValidaVO);
    
    String REGISTRO_CETRO_PAGOS_HIST = " INSERT INTO CTRO_PAGOS_HIST  (CTRO_PAGOS_VALIDA_ID) VALUES (#{id})";
	
    @Insert(REGISTRO_CETRO_PAGOS_HIST)
    public void crearRegistroCtroPagosHist(CentroPagosValidaVO centroPagosValidaVO);
    
    String INSERTA_CTRO_PAGOS_ULTIMA_CONSULTA = " INSERT INTO CTRO_PAGOS_ULTIMA_CONSULTA (FECHA_ULTIMA_CONSULTA) VALUES (SYSDATE) ";
    
    @Insert(INSERTA_CTRO_PAGOS_ULTIMA_CONSULTA)
    public void crearUltimaConsultaCtroPagos();    
    
//CONSULTAS
    
    String EXISTE_INFRACCION =" SELECT count(*) as existe FROM infracciones WHERE infrac_num_ctrl=#{nci} ";
	@Select(EXISTE_INFRACCION)
    public boolean existeInfraccion(@Param("nci") String   nci);
	

//	String PARAMETROS_TARJETA =" SELECT param_bs_company as company , param_bs_branch as branch "
//			+ " param_bs_country as country,param_bs_user as user, param_bs_pwd as password, "
//			+ " param_tx_operationtype as operationType, param_URL as url,param_usr_transaction as usrTransaction "
//			+ " FROM  V_CONSULTA_PARAMETROS_TARJETA WHERE PARAM_STATUS=1 ";
//	
//	@Select(PARAMETROS_TARJETA)
//	public BeanReceptor getBeanReceptor();
//	
	
	String CONSULTA_CTRO_PAGOS_ULTIMA_CONSULTA=  " SELECT to_char(fecha_ultima_consulta,'dd/mm/yyyy HH24:MI') fecha "
	 		+ "  FROM CTRO_PAGOS_ULTIMA_CONSULTA ";

	@Select(CONSULTA_CTRO_PAGOS_ULTIMA_CONSULTA)
	public String getCtroPagosUltimaConsulta();
	
	
	 
	String EXISTE_DETALLE_PAGO = "SELECT count(*) as existe FROM detalle_pago WHERE pag_id=#{pagId} AND caja_id=#{cajaId} ";
	@Select(EXISTE_DETALLE_PAGO)
    public boolean existeDetallePagos(@Param("pagId") String  pagId, @Param("cajaId")String cajaId);
	
	
	String EXISTE_DETALLE_CARGO = " SELECT count(*) as existe FROM detalle_cargos WHERE pag_id=#{pagId} AND caja_id=#{cajaId} ";
	@Select(EXISTE_DETALLE_CARGO)
	public boolean existeDetalleCargos(@Param("pagId") String  pagId, @Param("cajaId")String cajaId);

	
	String EXISTE_NCI = " SELECT count(*) as existe FROM ctro_pagos_valida WHERE nci=#{nci} ";
	@Select(EXISTE_NCI)
	public boolean existeNCIEnCtroPagosValida( @Param("nci") String   nci);
	
        
	String PAGO_DETALLE_TRANSACCION= " SELECT caja_id as cajaId,infrac_num as infracNum, infrac_num_ctrl as nci,pag_id,existe_pago as pagoId,existe_detalle_pago as existeDetallePagos, "
			+ " existe_pag_transaccion as existePagoTransaccion,existe_infrac_transaccion  as existeInfracTransaccion FROM V_PAGOS_DETALLE_TRANSAC WHERE infrac_num_ctrl = #{nci} ";
	
	@Select(PAGO_DETALLE_TRANSACCION)
	public CentroPagosValidaVO getPagoDetalleTransaccion( @Param("nci") String   nci );

	
	String EMPLEADO_POR_NCI = "SELECT e.emp_id as empId, emp_placa as empPlaca, emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno   as empNombre "
			+ "FROM infracciones i join empleados e ON e.emp_id = i.emp_id WHERE infrac_num_ctrl=#{nci}";
	
	@Select(EMPLEADO_POR_NCI)
    public EmpleadosVO getEmpleadoPorNci( @Param("nci") String   nci);

	
	String CENTRO_PAGO_ID = " SELECT CTRO_PAGOS_VALIDA_ID FROM ctro_pagos_valida WHERE nci=#{nci} ";
	
	@Select(CENTRO_PAGO_ID)
	public String getCtroPagosId( @Param("nci") String   nci );
	 
	String REFERENCIAS_EN_CURSO =" SELECT nci FROM ctro_pagos_valida  WHERE estatus_proceso=0 ";
	@Select(REFERENCIAS_EN_CURSO)
    public List<String> obtenerTodasReferenciasEnCurso();
	
	 
	String EMPLEADO_FOLIO_INFRACCIONES = " SELECT e.emp_id as empId, emp_placa as empPlaca, emp_nombre || ' ' || emp_ape_paterno || ' ' || emp_ape_materno    as empNombre "
			+ " FROM folios_infracciones f JOIN empleados e ON e.emp_id = f.emp_id WHERE folio_tipo = 'I' AND infrac_folio = #{folioInfraccion}"
			+ " and   f.ULTIMA_MODIFICACION = (  SELECT max(ULTIMA_MODIFICACION)  FROM folios_infracciones  WHERE folio_tipo = 'I' AND infrac_folio = #{folioInfraccion}) ";
	
	@Select(EMPLEADO_FOLIO_INFRACCIONES)
	public EmpleadosVO getEmpleadoInfoFoliosInfracciones(@Param("folioInfraccion") String  folioInfraccion );
	
	 
}
