package mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.articulos.ArticuloVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.CargaDigitalizacionWebSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionConsultaReporteRegDFVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionConsultaReporteVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.ModificaEnDepositoSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.SuspensionInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.TipoInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VInfraccionesValidaImagenVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
@Mapper
public interface InfraccionMyBatisDAO {
	
	String GET_V_INFRACCIONES = "SELECT "
			+ "INFRACCION AS INFRACCIONNUMERO, "
			+ "VEHICULO_PLACA AS PLACA, "
			+ "INFRAC_FECHA AS FECHA, "
			+ "INFRAC_CALLE AS UBICACION, "
			+ "INFRAC_MOTIVACION AS MOTIVACION, "
			+ "INFRAC_ARTICULO AS ARTICULO, "
			+ "SANCION_DIAS AS SANCION, "
			+ "INFRAC_PAGADA AS PAGADA, "
			+ "DEPOSITO AS DEPOSITO, "
			+ "FECHA_EMISION AS FECHAEMISION "
			+ "FROM V_SSP_INFRAC_CONS_GRAL_F ";
	
	String GET_V_INFRACCIONES_ALL_DATA = "SELECT "
			+"INFRACCION AS INFRACCIONNUMERO,"
			+"IMPRESA AS IMPRESA,"
			+"NCI AS NCI,"
			+"INFRAC_SECTOR AS INFRACSECTOR,"
			+"TIENE_PLACA AS TIENEPLACA,"
			+"VEHICULO_PLACA AS VEHICULOPLACA,"
			+"PLACA_EXPEDIDA AS PLACAEXPEDIDA,"
			+"INFRACTOR_APE_PATERNO AS INFRACTORAPEPATERNO,"
			+"INFRACTOR_APE_MATERNO AS INFRACTORAPEMATERNO,"
			+"INFRACTOR_NOMBRE AS INFRACTORNOMBRE,"
			+"INFRACTOR_CALLE AS INFRACTORCALLE,"
			+"INFRACTOR_NUM_EXT AS INFRACTORNUMEXT,"
			+"INFRACTOR_NUM_INT AS INFRACTORNUMINT,"
			+"INFRACTOR_COLONIA AS INFRACTORCOLONIA,"
			+"INFRACTOR_DELEGACION AS INFRACTORDELEGACION,"
			+"pkg_encripcion.desencripta(infractor_licencia) AS INFRACTORLICENCIA,"
			+"LICENCIA_TIPO AS LICENCIATIPO,"
			+"LICENCIA_EXPEDIDA AS LICENCIAEXPEDIDA,"
			+"TARJETA_CIRCULACION AS tarjetaCirculacion,"
			+"INFRAC_CALLE AS INFRACCALLE,"
			+"INFRAC_ENTRE_CALLE AS INFRACENTRECALLE,"
			+"INFRAC_Y_CALLE AS INFRACYCALLE,"
			+"INFRAC_COLONIA AS INFRACCOLONIA,"
			+"INFRAC_DELEGACION AS INFRACDELEGACION,"
			+"INFRAC_FECHA AS FECHA,"
			+"INFRAC_PAGADA AS PAGADA,"
			+"INFRAC_ARTICULO AS INFRACARTICULO,"
			+"INFRAC_FRACCION AS INFRACFRACCION,"
			+"INFRAC_PARRAFO AS INFRACPARRAFO,"
			+"INFRAC_INCISO AS INFRACINCISO,"
			+"INFRAC_MOTIVACION AS MOTIVACION,"
			+"SANCION_DIAS AS SANCION,"
			+"VEHICULO_MARCA AS VEHICULOMARCA,"
			+"VEHICULO_MODELO AS VEHICULOMODELO,"
			+"VEHICULO_COLOR AS VEHICULOCOLOR,"
			+"VEHICULO_TIPO AS VEHICULOTIPO,"
			+"DEPOSITO AS DEPOSITO,"
			+"GRUA AS GRUA,"
			+"OFICIAL_NOMBRE AS OFICIALNOMBRE,"
			+"OFICIAL_PLACA AS OFICIALPLACA,"
			+"OFICIAL_SECTOR AS OFICIALSECTOR,"
			+"OFICIAL_AGRUPAMIENTO AS OFICIALAGRUPAMIENTO,"
			+"INFRAC_OBSERVACION AS INFRACOBSERVACION,"
			+"INFRAC_L_SERV_PUBLICO AS INFRACLSERVPUBLICO,"
			+"INFRAC_UNITERR AS INFRACUNITERR,"
			+"RESPONSABLE_VEHID as RESPONSABLEVEHID,"
			+"INFRAC_SEC_ID AS INFRACSECID,"
			+"INFRACTOR_EDO AS INFRACTOREDO,"
			+"INFRACTOR_EDO_ID AS INFRACTOREDOID,"
			+"INFRACTOR_DELEG_ID AS INFRACTORDELEGID,"
			+"LIC_EXPEDIDA_ID AS LICEXPEDIDAID,"
			+"VEHICULO_ORIGEN AS VEHICULOORIGEN,"
			+"INFRAC_LEYTRANSP AS INFRACLEYTRANSP,"
			+"INFRAC_UNITERRID AS INFRACUNITERRID,"
			+"PLACA_EXPEDIDAID AS PLACAEXPEDIDAID,"
			+"VEHICULO_MARCAID AS VEHICULOMARCAID,"
			+"VEHICULO_MODELOID AS VEHICULOMODELOID,"
			+"VEHICULO_COLORID AS VEHICULOCOLORID,"
			+"GRUA_ID AS GRUAID,"
			+"OFICIAL_SECTORID AS OFICIALSECTORID,"
			+"OFICIAL_AGRUPAMIENTOID AS OFICIALAGRUPAMIENTOID,"
			+"ARTICULOID AS ARTICULOID,"
			+"EMP_ID AS EMPID,"
			+"INFRACTOR_RFC AS INFRACTORRFC,"
			+"FECHA_EMISION AS FECHAEMISION,"
			+"INFRAC_ARRASTRE AS INFRACARRASTRE,"
			+"INFRAC_TIPO_ARRASTRE AS INFRACTIPOARRASTRE,"
			+"INFRAC_CON_DIRECCION AS INFRACCONDIRECCION,"
			+"INFRAC_FRENTE_AL_NUM AS INFRACFRENTEALNUM,"
			+"INFRAC_OBSERVE_QUE_ID AS INFRACOBSERVEQUEID,"
			+"INFRAC_OBSERVE_QUE_DESC AS INFRACOBSERVEQUEDESC,"
			+"GARANTIA_TIPO_ID AS GARANTIATIPOID,"
			+"GARANTIA_TIPO_NOMBRE AS GARANTIATIPONOMBRE, "
			+"GARANTIA_FOLIO AS GARANTIAFOLIO, "
			+"GARANTIA_PROCESO_ID AS GARANTIAPROCESOID, "
			+"ORIGEN_PLACA AS origenPlaca, "
			+"NB_TIPO_PERSONA AS nbTipoPersona "
			+"FROM V_SSP_INFRAC_CONS_GRAL_F ";
	
	
	
	String GET_TIPO_INFRACCION_NOM_TABLA=" SELECT INFRAC_NUM as numInfraccion,INFRAC_PLACA as placa,INFRAC_NUM_CTRL as numControl,'INFRACCIONES' as nombreTabla from infracciones where INFRAC_NUM=#{infracNumVal} UNION " 
			+ " SELECT INFRAC_NUM as numInfraccion,INFRAC_PLACA as placa,INFRAC_NUM_CTRL as numControl,'RADAR' as nombreTabla from Infracciones_Radar where INFRAC_NUM=#{infracNumVal} UNION "
			+ " SELECT INFRAC_NUM as numInfraccion,INFRAC_PLACA as placa,INFRAC_NUM_CTRL as numControl,'DIGITALIZACION' as nombreTabla from Infracciones_Digitalizacion where INFRAC_NUM=#{infracNumVal}";
	
	String GET_TIPO_INFRACCION_NOM_TABLA_NCI=" SELECT INFRAC_NUM as numInfraccion,INFRAC_PLACA as placa,INFRAC_NUM_CTRL as numControl,'INFRACCIONES' as nombreTabla from infracciones where INFRAC_NUM_CTRL=#{numControl} UNION " 
			+ " SELECT INFRAC_NUM as numInfraccion,INFRAC_PLACA as placa,INFRAC_NUM_CTRL as numControl,'RADAR' as nombreTabla from Infracciones_Radar where INFRAC_NUM_CTRL=#{numControl} UNION "
			+ " SELECT INFRAC_NUM as numInfraccion,INFRAC_PLACA as placa,INFRAC_NUM_CTRL as numControl,'DIGITALIZACION' as nombreTabla from Infracciones_Digitalizacion where INFRAC_NUM_CTRL=#{numControl}";
	
	String GET_MODIFICADO_POR=" SELECT MODIFICADO_POR from infracciones where INFRAC_NUM=#{infracNum} UNION "
			+ " SELECT MODIFICADO_POR from Infracciones_Radar where INFRAC_NUM=#{infracNum} UNION "
			+ " SELECT MODIFICADO_POR from Infracciones_Digitalizacion where INFRAC_NUM=#{infracNum} UNION "
			+ " SELECT MODIFICADO_POR from infracciones where INFRAC_IMPRESA = #{infracNum}";
	
	String GET_NCI=" SELECT INFRAC_NUM_CTRL from infracciones where INFRAC_NUM=#{infracNum} UNION "
			+ " SELECT INFRAC_NUM_CTRL from Infracciones_Radar where INFRAC_NUM=#{infracNum} UNION "
			+ " SELECT INFRAC_NUM_CTRL from Infracciones_Digitalizacion where INFRAC_NUM=#{infracNum} UNION "
			+ " SELECT INFRAC_NUM_CTRL from infracciones where INFRAC_IMPRESA = #{infracNum}";
	
	String UPDATE_INFRACCION_PAGADA="UPDATE INFRACCIONES SET INFRAC_PAGADA='S', "
			+ "ESTATUS_CANCELACION=null, ULTIMA_MODIFICACION=SYSDATE,MODIFICADO_POR=#{empId} "
			+ "WHERE INFRAC_NUM=#{infracNum} AND INFRAC_PAGADA='N'";
	
	String UPDATE_INFRACCION_NO_PAGADA="UPDATE INFRACCIONES SET INFRAC_PAGADA='N', "
			+ "ESTATUS_CANCELACION=null, ULTIMA_MODIFICACION=SYSDATE,MODIFICADO_POR=#{empId} "
			+ "WHERE INFRAC_NUM=#{infracNum} AND INFRAC_PAGADA='S'";
	
	String UPDATE_INFRAC_RADAR_PAGADA="UPDATE INFRACCIONES_RADAR SET INFRAC_PAGADA='S', "
			+ "ESTATUS_CANCELACION=null, ULTIMA_MODIFICACION=SYSDATE ,MODIFICADO_POR=#{empId} "
			+ "WHERE INFRAC_NUM=#{infracNum} AND INFRAC_PAGADA='N'";
	
	String UPDATE_INFRAC_RADAR_NO_PAGADA="UPDATE INFRACCIONES_RADAR SET INFRAC_PAGADA='N', "
			+ "ESTATUS_CANCELACION=null, ULTIMA_MODIFICACION=SYSDATE ,MODIFICADO_POR=#{empId} "
			+ "WHERE INFRAC_NUM=#{infracNum} AND INFRAC_PAGADA='S'";
	
	String UPDATE_INFRAC_DIGITALIZACION_PAGADA="UPDATE INFRACCIONES_DIGITALIZACION SET INFRAC_PAGADA='S', "
			+ "ESTATUS_CANCELACION='null', ULTIMA_MODIFICACION=SYSDATE , MODIFICADO_POR=#{empId} "
					+ "WHERE INFRAC_NUM=#{infracNum} AND INFRAC_PAGADA='N'";
	
	String UPDATE_INFRAC_DIGITALIZACION_NO_PAGADA="UPDATE INFRACCIONES_DIGITALIZACION SET INFRAC_PAGADA='N', "
			+ "ESTATUS_CANCELACION='null', ULTIMA_MODIFICACION=SYSDATE , MODIFICADO_POR=#{empId} "
			+ "WHERE INFRAC_NUM=#{infracNum} AND INFRAC_PAGADA='S'";
	
	String GET_V_INFRACCIONES_VEHICULO_PLACA = GET_V_INFRACCIONES 
			+ "WHERE VEHICULO_PLACA = #{valor} ";
	
	String GET_V_INFRACCIONES_INFRACTOR_LICENCIA = GET_V_INFRACCIONES 
			+ "WHERE INFRACTOR_LICENCIA = pkg_encripcion.encripta(#{valor}) ";
	
	String GET_V_INFRACCIONES_INFRACCION = GET_V_INFRACCIONES 
			+ "WHERE INFRACCION = #{valor} ";
	
	String GET_V_INFRACCIONES_IMPRESA = GET_V_INFRACCIONES 
			+ "WHERE IMPRESA = #{valor} ";
	
	String GET_V_INFRACCIONES_ALL_DATA_INFRAC_NUM = GET_V_INFRACCIONES_ALL_DATA 
			+"WHERE INFRACCION = #{valor} ";
	

	 
	String buscarInfraccionesVehiculoPlacaSQL = GET_V_INFRACCIONES_VEHICULO_PLACA + " AND INFRAC_PAGADA = 'NO'";
	
	String buscarInfraccionesLicenciaSQL 	= GET_V_INFRACCIONES_INFRACTOR_LICENCIA + " AND INFRAC_PAGADA = 'NO'";
	
	String buscarInfraccionSQL 				= GET_V_INFRACCIONES_INFRACCION + " AND INFRAC_PAGADA = 'NO'";
	
	String buscarInfraccionesImpresaSQL 	= GET_V_INFRACCIONES_IMPRESA +" AND INFRAC_PAGADA = 'NO'";
			
	@Select(GET_V_INFRACCIONES_VEHICULO_PLACA)
	public List<VSSPInfracConsGralFVO> buscarInfraccionesVehiculoPlaca(@Param("valor") String valor);
	
	@Select(GET_V_INFRACCIONES_INFRACTOR_LICENCIA)
	public List<VSSPInfracConsGralFVO> buscarInfraccionesInfractorLicencia(@Param("valor") String valor);
	
	@Select(GET_V_INFRACCIONES_INFRACCION)
	public List<VSSPInfracConsGralFVO> buscarInfraccionesInfraccion(@Param("valor") String valor);
	
	@Select(GET_V_INFRACCIONES_IMPRESA)
	public List<VSSPInfracConsGralFVO> buscarInfraccionesImpresa(@Param("valor") String valor);
	
	@Select(buscarInfraccionesVehiculoPlacaSQL)
	public List<VSSPInfracConsGralFVO> buscarInfraccionesNoPagadasVehiculoPlaca(@Param("valor") String valor);
	
	@Select(buscarInfraccionesLicenciaSQL)
	public List<VSSPInfracConsGralFVO> buscarInfraccionesNoPagadasInfractorLicencia(@Param("valor") String valor);
	
	@Select(buscarInfraccionSQL)
	public List<VSSPInfracConsGralFVO> buscarInfraccionesNoPagadasInfraccion(@Param("valor") String valor);
	
	@Select(buscarInfraccionesImpresaSQL)
	public List<VSSPInfracConsGralFVO> buscarInfraccionesNoPagadasImpresa(@Param("valor") String valor);
	
	/**
	 * RETORNA DETALLE COMPLETO DE UNA INFRACCION, BUSCADA POR EL CAMPO INFRAC_NUM
	 * @author Kevin Ojeda
	 * @param String Valor
	 * @return VSSPInfracConsGralFVO
	 */
	@Select(GET_V_INFRACCIONES_ALL_DATA_INFRAC_NUM)
	public List<VSSPInfracConsGralFVO> buscarInfraccionesAllDataByInfracNum(@Param("valor") String valor);
	

	
	/**
	 * INSERCION DE INFRACCION
	 * @author Kevin Ojeda
	 * @param altaInfraccionSPVO altaInfraccionSPVO
	 * @return AltaInfraccionSPVO
	 * @throws PersistenceException
	 */
	@Select(value = SP_INFRACCIONES_GENERAL_F)
	@Options(statementType = StatementType.CALLABLE)
	public AltaInfraccionSPVO crearInfraccion(AltaInfraccionSPVO altaInfraccionSPVO) throws PersistenceException;
	
	/**
	 * MODIFICA UNA INFRACCION
	 * @author Kevin Ojeda
	 * @param AltaInfraccionSPVO modificaInfraccionSPVO
	 * @return AltaInfraccionSPVO-
	 * @throws PersistenceException
	 */
	@Select(value = PROC_INFRACCIONES_GENERAL_F)
	@Options(statementType = StatementType.CALLABLE)
	public AltaInfraccionSPVO modificaInfraccion(AltaInfraccionSPVO modificaInfraccionSPVO) throws PersistenceException;
	
	/**
	 * Ejecuta SP de modificacion en Deposito
	 * @author Kevin Ojeda
	 * @param modificaEnDepositoSPVO
	 * @return ModificaEnDepositoSPVO modificaEnDepositoSPVO
	 * @throws PersistenceException
	 */
//	@Select(value = PROC_INFRACCION_MOD_DEP)
	@Select(value = PROC_INFRAC_MOD_DEP_ART)
	@Options(statementType = StatementType.CALLABLE)
	public ModificaEnDepositoSPVO modificaInfraccionEnDeposito(ModificaEnDepositoSPVO modificaEnDepositoSPVO) throws PersistenceException;
	
	/**
	 * Ejecuta SP de suspension de infraccion
	 * @author Kevin Ojeda
	 * @param SuspensionInfraccionSPVO
	 * @return SuspensionInfraccionSPVO suspensionInfraccionSPVO
	 * @throws PersistenceException
	 */
	@Select(value = SP_INFRACCION_SUSPENSION)
	@Options(statementType = StatementType.CALLABLE)
	public SuspensionInfraccionSPVO suspensionInfraccion(SuspensionInfraccionSPVO suspensionInfraccionSPVO) throws PersistenceException;
	
	/**
	 * Ejecuta SP de carga de digitalización de infraccion
	 * @author Kevin Ojeda
	 * @param SuspensionInfraccionSPVO
	 * @return SuspensionInfraccionSPVO suspensionInfraccionSPVO
	 * @throws PersistenceException
	 */
	@Select(value = SP_CARGA_DIGITALIZACION_WEB)
	@Options(statementType = StatementType.CALLABLE)
	public CargaDigitalizacionWebSPVO CargaDigitalizacionWeb(CargaDigitalizacionWebSPVO cargaDigitalizacionWebSPVO) throws PersistenceException;
	
	String PARAMS = "#{p_infrac_impresa},"
			+"#{p_sec_id},"
			+"#{p_infrac_con_placa},"
			+"#{p_infrac_placa_edo},"
			+"#{p_infrac_placa},"
			+"#{p_infrac_i_paterno},"
			+"#{p_infrac_i_materno},"
			+"#{p_infrac_i_nombre},"
			+"#{p_infrac_i_calle},"
			+"#{p_infrac_i_num_ext},"
			+"#{p_infrac_i_num_int},"
			+"#{p_infrac_i_colonia},"
			+"#{p_infrac_i_edo_id},"
			+"#{p_infrac_i_del_id},"
			+"#{p_infrac_licencia},"
			+"#{p_tipo_l_id},"
			+"#{p_infrac_l_serv_publico},"
			+"#{p_infrac_lic_edo},"
			+"#{p_vmar_id},"
			+"#{p_vmod_id},"
			+"#{p_vcolor_id},"
			+"#{p_vorigen},"
			+"#{p_vtarjeta_circulacion},"
			+"#{p_vtipo_id},"
			+"#{p_art_motivacion},"
			+"#{p_infrac_m_en_la_calle},"
			+"#{p_infrac_m_entre_calle},"
			+"#{p_infrac_m_y_la_calle},"
			+"#{p_infrac_m_colonia},"
			+"#{p_infrac_m_edo_id},"
			+"#{p_infrac_m_del_id},"
			+"#{p_art_id},"
			+"#{p_infrac_ley_transporte},"
			+"#{p_sancion_art_id},"
			+"#{p_infrac_arrastre},"
			+"#{p_infrac_num_arrastre},"
			+"#{p_infrac_tipo_arrastre},"
			+"#{p_grua_id},"
			+"#{p_dep_id},"
			+"#{p_emp_id},"
			+"#{p_emp_sector},"
			+"#{p_emp_agrup},"
			+"#{p_r_veh_id},"
			+"#{p_oper},"
			+"#{p_infrac_num_ctrl},"
			+"#{p_ut_id},"
			+"#{p_m_fecha},"
			+"#{p_modificado_por},"
			+"#{p_resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{P_EMPLEADO_COD},"
			+"#{P_EMP_AGRUPAMIENTO},"
			+"#{P_EMP_PAT},"
			+"#{P_EMP_MAT},"
			+"#{P_EMP_NOMBRE},"
			+"#{P_INFRAC_CAPTURA},"
			+"#{P_INFRAC_APOYO_GRUA},"
			+"#{p_infrac_rfc},"
			+"#{p_fecha_emision},"
			+"#{p_infrac_observacion},"
			+"#{p_motivo_cambio},"
			+"#{p_infrac_con_direccion},"
			+"#{p_infrac_frente_al_num},"
			+"#{p_observe_que},"
			+"#{p_garantia_tipo_id}, "
			+"#{p_garantia_folio} ); END;  ";
	
	String SP_INFRACCIONES_GENERAL_F = "begin SP_INFRACCIONES_GENERAL_F_MPL (" + PARAMS;
	
	String PROC_INFRACCIONES_GENERAL_F = "begin SP_INFRACCIONES_GENERAL_F_MPL (" + PARAMS;
	
	String PROC_INFRACCION_MOD_DEP = "BEGIN PROC_INFRACCION_MOD_DEP "
		+"(#{p_infrac_num_ctrl}, "
		+"#{p_infrac_con_placa}, "
		+"#{p_infrac_placa}, "
		+"#{p_infrac_arrastre}, "
		+"#{p_infrac_tipo_arrastre}, "
		+"#{p_vehictipo}, "
		+"#{p_motivo_cambio}, "
		+"#{p_modificado_por}, "
		+"#{p_autorizado_por}, "
		+"#{p_resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
		+"#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); END; ";
	
	String PROC_INFRAC_MOD_DEP_ART = "BEGIN PROC_INFRAC_MOD_DEP_ART "
			+"(#{p_infrac_num_ctrl}, "
			+"#{p_infrac_con_placa}, "
			+"#{p_infrac_placa}, "
			+"#{p_infrac_arrastre}, "
			+"#{p_infrac_tipo_arrastre}, "
			+"#{p_vehictipo}, "
			+"#{p_motivo_cambio}, "
			+"#{p_modificado_por}, "
			+"#{p_autorizado_por}, "
			+"#{p_articuloid}, "
			+"#{p_resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}); END; ";
	
	String SP_INFRACCION_SUSPENSION = "BEGIN SP_INFRACCION_SUSPENSION("
			+"#{p_infrac_num},"
			+"#{p_infrac_oficio},"
			+"#{p_emp_id},"
			+"#{p_resultado, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT},"
			+"#{p_mensaje, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}"
	+ "); END;";
	
	String SP_CARGA_DIGITALIZACION_WEB = "BEGIN SP_CARGA_DIGITALIZACION_WEB("
			+ "#{empleado_id}, "
			+"#{folios_repetidos, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "
			+"#{folios_liberados, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "
			+"#{folios_procesados, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "
			+"#{result_out, jdbcType=NUMERIC, javaType=java.lang.Integer, mode=INOUT}, "//jdbcType=NUMBER
			+"#{message_out, jdbcType=VARCHAR, javaType=java.lang.String, mode=INOUT}"
			+ "); END;";

	String V_INFRACCIONES_VALIDA_IMAGEN = "SELECT "
			+ "INFRAC_NUM AS numeroInfraccion, "
			+ "ESTATUS AS estatus, "
			+ "FECHA_MODIFICACION AS fechaModificacion, "
			+ "MODIFICADO_POR  AS modificadoPor "
			+ "FROM "
			+ "V_INFRACCIONES_VALIDA_IMAGEN WHERE INFRAC_NUM = #{numeroInfraccion}";
	
	
	/**
	 * RETORNA UNA LISTA DE LA VISTA V_INFRACCIONES_VALIDA_IMAGEN FILTRADO POR INFRAC_NUM
	 * @author Kevin Ojeda
	 * @param vInfraccionesValidaImagenVO
	 * @return List<VInfraccionesValidaImagenVO>
	 * @throws PersistenceException
	 */
	@Select(value = V_INFRACCIONES_VALIDA_IMAGEN)
	@Options(statementType = StatementType.CALLABLE)
	public List<VInfraccionesValidaImagenVO> vInfraccionesValidaImagen(VInfraccionesValidaImagenVO vInfraccionesValidaImagenVO) throws PersistenceException;
	
	String CONSULTA_INFRACCION_REPORTE = 
			"SELECT NCI, ESTATUS, INFRACCION, INFRACCION_IMPRESA, ESTADO, SECTOR, UNIDAD_TERRITORIAL, APE_PATERNO, APE_MATERNO, NOMBRE, CALLE, NUM_EXTERIOR, NUM_INTERIOR, "+"COLONIA, DELEGACION, EDO_INFRACTOR, LICENCIA, TIPO_LICENCIA, LIC_EXPEDIDA_EN, VEHICULO_TIPO, ORIGEN, MARCA, MODELO, COLOR, SERVICIO_PUBLICO, CON_PLACA, PLACA, "+"PLACA_EXPEDIDA_EN, TARJETA_CIRCULACION, EN_LA_CALLE, ENTRE_LA_CALLE, Y_LA_CALLE, FECHA_INFRACCION, COLONIA_INFRACCION, DELEGACION_INFRACCION, ARTICULO, FRACCION, "+"PARRAFO, INCISO, LEY_TRANSPORTE, MOTIVACION, DIAS_SAL_MIN, ART_SANCION, EVENTO_INFRACCION, EVENTO_INMOVILIZACION, EVENTO_LEVANTAMIENTO, EVENTO_ARRASTRE, "+"TIPO_ARRASTRE, NUMERO_ARRASTRE, NUMERO_GRUA, DEPOSITO, EMPLEADO, EMP_SECTOR, EMP_PLACA, EMP_AGRUPAMIENTO, EMP_RESPONSABLE, INFRACTOR, FUNDAMENTACION, MONTO, "+"REDUCCION, ACTUALIZACION, RECARGOS, DERECHO_PISO, ARRASTRE, TOTAL, DEPO_NOMBRE, DEPO_DIRECCION, DEPO_COLONIA, DEPO_DELEGACION,"
					+"DEPO_TELEFONO FROM TABLE ( CAST ( FN_INFRAC_CONSULTA_TP(#{nci}) AS T_INFRACIONES_CONSULTA)) UNION "

			+"SELECT NCI, ESTATUS, INFRACCION, INFRACCION_IMPRESA, ESTADO, SECTOR, UNIDAD_TERRITORIAL, APE_PATERNO, APE_MATERNO, NOMBRE, CALLE, NUM_EXTERIOR, NUM_INTERIOR, "+"COLONIA, DELEGACION, EDO_INFRACTOR, LICENCIA, TIPO_LICENCIA, LIC_EXPEDIDA_EN, VEHICULO_TIPO, ORIGEN, MARCA, MODELO, COLOR, SERVICIO_PUBLICO, CON_PLACA, PLACA, "+"PLACA_EXPEDIDA_EN, TARJETA_CIRCULACION, EN_LA_CALLE, ENTRE_LA_CALLE, Y_LA_CALLE, FECHA_INFRACCION, COLONIA_INFRACCION, DELEGACION_INFRACCION, ARTICULO, FRACCION, "+"PARRAFO, INCISO, LEY_TRANSPORTE, MOTIVACION, DIAS_SAL_MIN, ART_SANCION, EVENTO_INFRACCION, EVENTO_INMOVILIZACION, EVENTO_LEVANTAMIENTO, EVENTO_ARRASTRE, "+"TIPO_ARRASTRE, NUMERO_ARRASTRE, NUMERO_GRUA, DEPOSITO, EMPLEADO, EMP_SECTOR, EMP_PLACA, EMP_AGRUPAMIENTO, EMP_RESPONSABLE, INFRACTOR, FUNDAMENTACION, MONTO, "+"REDUCCION, ACTUALIZACION, RECARGOS, DERECHO_PISO, ARRASTRE, TOTAL, DEPO_NOMBRE, DEPO_DIRECCION, DEPO_COLONIA, DEPO_DELEGACION,"
					+"DEPO_TELEFONO FROM TABLE ( CAST (  Fn_infraccion_consulta_dig_pru(#{nci}) AS T_INFRACIONES_CONSULTA)) UNION "

			+"SELECT  NCI, ESTATUS, INFRACCION, INFRACCION_IMPRESA, ESTADO, SECTOR, UNIDAD_TERRITORIAL, APE_PATERNO, APE_MATERNO, NOMBRE, CALLE, NUM_EXTERIOR, NUM_INTERIOR, "+"COLONIA, DELEGACION, EDO_INFRACTOR, LICENCIA, TIPO_LICENCIA, LIC_EXPEDIDA_EN, VEHICULO_TIPO, ORIGEN, MARCA, MODELO, COLOR, SERVICIO_PUBLICO, CON_PLACA, PLACA, "+"PLACA_EXPEDIDA_EN, TARJETA_CIRCULACION, EN_LA_CALLE, ENTRE_LA_CALLE, Y_LA_CALLE, FECHA_INFRACCION, COLONIA_INFRACCION, DELEGACION_INFRACCION, ARTICULO, FRACCION, "+"PARRAFO, INCISO, LEY_TRANSPORTE, MOTIVACION, DIAS_SAL_MIN, ART_SANCION, EVENTO_INFRACCION, EVENTO_INMOVILIZACION, EVENTO_LEVANTAMIENTO, EVENTO_ARRASTRE, "+"TIPO_ARRASTRE, NUMERO_ARRASTRE, NUMERO_GRUA, DEPOSITO, EMPLEADO, EMP_SECTOR, EMP_PLACA, EMP_AGRUPAMIENTO, EMP_RESPONSABLE, INFRACTOR, FUNDAMENTACION, MONTO, "+"REDUCCION, ACTUALIZACION, RECARGOS, DERECHO_PISO, ARRASTRE, TOTAL, DEPO_NOMBRE, DEPO_DIRECCION, DEPO_COLONIA, DEPO_DELEGACION,"
					+"DEPO_TELEFONO FROM TABLE ( CAST ( Fn_infraccion_consulta_radar(#{nci}) AS T_INFRACIONES_CONSULTA))";
	
	/**
	 * RETORNA UNA LISTA DE PARA GENERAR REPORTE DE CONSULTA DE INFRACCION
	 * @author Kevin Ojeda
	 * @param String nci
	 * @return List<InfraccionConsultaReporteVO>
	 */
	@Select(value = CONSULTA_INFRACCION_REPORTE)
	@Options(statementType = StatementType.CALLABLE)
	public List<InfraccionConsultaReporteVO> consultaInfraccionReporte(@Param(value = "nci") String nci);
	
	
	String CONSULTA_INFRACCION_REPORTE_REG_DF = "SELECT * FROM V_REP_BOLETA_INFRACCION WHERE INFRAC_NUM_CTRL = #{nci}";
	
	/**
	 * RETORNA UNA LISTA DE PARA GENERAR REPORTE DE CONSULTA DE INFRACCION
	 * @author Kevin Ojeda
	 * @param String nci
	 * @return List<InfraccionConsultaReporteRegDFVO>
	 */
	@Select(value = CONSULTA_INFRACCION_REPORTE_REG_DF)
	@Options(statementType = StatementType.CALLABLE)
	public List<InfraccionConsultaReporteRegDFVO> consultaInfraccionReporteRegDF(@Param(value = "nci") String nci);
	
	/**
	 * RETORNA UNA objeto CON EL NUMERO DE INFRACCION Y EL NOMBRE DE LA TABLA DE CONSULTA EN LA QUE SE ENCONTRO EL REGISTRO
	 * @author 
	 * @param String infracNumVal
	 * @return Map
	 */
	@Select(value = GET_TIPO_INFRACCION_NOM_TABLA)
	public TipoInfraccionVO getInfraccionAndTipoInfraccion(@Param(value="infracNumVal") String infracNumVal);
	
	/**
	 * RETORNA UN objeto CON EL INFRAC_NUM_CTRL Y EL NOMBRE DE LA TABLA DE CONSULTA EN LA QUE SE ENCONTRO EL REGISTRO
	 * @author Fernando Castillo
	 * @param String infracNum
	 * @return Map
	 */
	@Select(value = GET_TIPO_INFRACCION_NOM_TABLA_NCI)
	public TipoInfraccionVO getInfraccionAndTipoInfraccionNCI(@Param(value="numControl") String numControl);
	
	/**
	 * RETORNA UN INTEGER MODIFICADO_POR DE LA INFRACCION
	 * @author Fernando Castillo
	 * @param String infracNum
	 * @return INTEGER
	 */
	@Select(value = GET_MODIFICADO_POR)
	public Integer getModificadoPor(@Param(value="infracNum") String infracNum);
	
	/**
	 * RETORNA UN STRING NCI DE LA INFRACCION
	 * @author Fernando Castillo
	 * @param String infracNum
	 * @return INTEGER
	 */
	@Select(value = GET_NCI)
	public String getInfracNumCtrl(@Param(value="infracNum") String infracNum);
	
	/**
	 * Actualiza el status de infrac_pagad con S a la tabla de infracciones
	 * @author 
	 * @param String infracNum
	 * @param String empId
	 */
	@Update(value =UPDATE_INFRACCION_PAGADA)
	public void updateInfraccionesPagada(@Param(value="infracNum") String infracNum,@Param(value="empId") String empId);
	
	/**
	 * Actualiza el status de infrac_pagad con N a la tabla de infracciones
	 * @author 
	 * @param String infracNum
	 * @param String empId
	 */
	@Update(value =UPDATE_INFRACCION_NO_PAGADA)
	public void updateInfraccionesNoPagada(@Param(value="infracNum") String infracNum,@Param(value="empId") String empId);
	
	/**
	 * Actualiza el status de infrac_pagad con S a la tabla de infracciones_radar
	 * @author 
	 * @param String infracNum
	 * @param String empId
	 */
	@Update(value =UPDATE_INFRAC_RADAR_PAGADA)
	public void updateInfraccionesRadarPagada(@Param(value="infracNum") String infracNum,@Param(value="empId") String empId);
	
	/**
	 * Actualiza el status de infrac_pagad con N a la tabla de infracciones_radar
	 * @author 
	 * @param String infracNum
	 * @param String empId
	 */
	@Update(value =UPDATE_INFRAC_RADAR_NO_PAGADA)
	public void updateInfraccioneRadarNoPagada(@Param(value="infracNum") String infracNum,@Param(value="empId") String empId);
	
	/**
	 * Actualiza el status de infrac_pagad con S a la tabla de infracciones_Digitalizacion
	 * @author 
	 * @param String infracNum
	 * @param String empId
	 */
	@Update(value =UPDATE_INFRAC_DIGITALIZACION_PAGADA)
	public void updateInfraccionesDigitalizacionPagada(@Param(value="infracNum") String infracNum,@Param(value="empId") String empId);
	
	/**
	 * Actualiza el status de infrac_pagad con N a la tabla de infracciones_Digitalizacion
	 * @author 
	 * @param String infracNum
	 * @param String empId
	 */
	@Update(value =UPDATE_INFRAC_DIGITALIZACION_NO_PAGADA)
	public void updateInfraccionesDigitalizacionNoPagada(@Param(value="infracNum") String infracNum,@Param(value="empId") String empId);
	
	/**
	 * Encripta los valores que se ha pasado de tipo String
	 * @author dagoberto
	 * @param String valor
	 * 
	 */
	@Select("SELECT PKG_ENCRIPCION.ENCRIPTA(#{valor}) FROM DUAL ")
	public String getDesencriptarValor(@Param(value="valor") String valor);
	
	/**
	 * Se obtiene los valores faltantes para la comparacion en java(representacion del trigger TrBitUpInfracImpl)
	 * @author dagoberto
	 * @param String valor
	 * 
	 */
//	@Select("	SELECT VTIPO_ID as tipoVehiculoId, " + 
//			"        MOTIVO_CAMBIO as motivoCambio, " + 
//			"        AUTORIZADO_POR as autorizaId, " + 
//			"        FECHA_AUTORIZACION as fechaAutoriza, " + 
//			"        INFRAC_M_DEL_ID as infracDelegacionId, " + 
//			"        TIPO_L_ID as licenciaTipoId " + 
//			"  FROM INFRACCIONES WHERE INFRAC_NUM = #{infracNum}"
//			)
//	public VSSPInfracConsGralFVO getTipoVehiculoId( @Param(value="infracNum") String infracNum);
//	
//	
//	@Select("SELECT MOTIVO_CAMBIO FROM INFRACCIONES WHERE INFRAC_NUM = #{infracNum} ")
//	public String getMotivoCambioId(@Param(value="infracNum") String infracNum);
//	
	@Select("SELECT AUTORIZADO_POR FROM INFRACCIONES WHERE INFRAC_NUM = #{infracNum} ")
	public String getAutorizaId(@Param(value="infracNum") String infracNum);
	
	@Select("SELECT FECHA_AUTORIZACION FROM INFRACCIONES WHERE INFRAC_NUM = #{infracNum} ")
	public Date getFechAutoriza(@Param(value="infracNum") String infracNum);
//
//	@Select("select INFRAC_M_DEL_ID from infracciones where INFRAC_NUM = #{infracNum} ")
//	public String getDelegacionInfracId(@Param(value="infracNum") String infracNum);
//	
//	@Select("select TIPO_L_ID from infracciones where INFRAC_NUM = #{infracNum} ")
//	public String getLicTipoId(@Param(value="infracNum") String infracNum);

	@Select("SELECT INFRAC_PLACA FROM INFRACCIONES WHERE INFRAC_NUM = #{infracNum} ")
	public String getPlacaVehiculo(@Param(value="infracNum") String infracNum);
	
	@Select("SELECT INFRAC_PLACA FROM INFRACCIONES_DIGITALIZACION WHERE INFRAC_NUM = #{infracNum} ")
	public String getPlacaVehiculoD(@Param(value="infracNum") String infracNum);
	
	@Select("SELECT INFRAC_PLACA FROM INFRACCIONES_RADAR WHERE INFRAC_NUM = #{infracNum} ")
	public String getPlacaVehiculoR(@Param(value="infracNum") String infracNum);
//	
//	@Select("SELECT INFRAC_TIPO_ARRASTRE FROM INFRACCIONES WHERE INFRAC_NUM = #{infracNum} ")
//	public String getInfracTipoArrastre(@Param(value="infracNum") String infracNum);
	
//	@Select(" SELECT * FROM (SELECT DISTINCT (LOTE) FROM INFRACCIONES_DIGITAL_TODO_DIA ORDER BY LOTE DESC) WHERE ROWNUM = 1 ")
	@Select(" SELECT CARGA_DIGITALIZACION_SEQ.CURRVAL FROM DUAL ")
	public String getToIdRegistro();
	
	@Select(" SELECT DEP_ID FROM INFRACCIONES WHERE INFRAC_NUM = #{infracNum} ")
	public String getIdDep(@Param(value="infracNum") String infracNum);
	
	@Select(" SELECT VTIPO_ID tipoVehiculoId, " + 
			"        f.MOTIVO_CAMBIO motivoCambio, " + 
			"        f.AUTORIZADO_POR autorizaId, " +
			"        f.FECHA_AUTORIZACION fechaAutoriza, " + 
			"        f.INFRAC_M_DEL_ID infracDelegacionId, " + 
			"        f.TIPO_L_ID licenciaTipoId, " + 
			"      vs.INFRACCION AS INFRACCIONNUMERO, " + 
			"			vs.IMPRESA AS IMPRESA, " + 
			"			vs.NCI AS NCI, " + 
			"			vs.INFRAC_SECTOR AS INFRACSECTOR, " + 
			"			vs.TIENE_PLACA AS TIENEPLACA, " + 
			"			vs.VEHICULO_PLACA AS VEHICULOPLACA, " + 
			"			vs.PLACA_EXPEDIDA AS PLACAEXPEDIDA, " + 
			"			vs.INFRACTOR_APE_PATERNO AS INFRACTORAPEPATERNO, " + 
			"			vs.INFRACTOR_APE_MATERNO AS INFRACTORAPEMATERNO, " + 
			"			vs.INFRACTOR_NOMBRE AS INFRACTORNOMBRE, " + 
			"			vs.INFRACTOR_CALLE AS INFRACTORCALLE, " + 
			"			vs.INFRACTOR_NUM_EXT AS INFRACTORNUMEXT, " + 
			"			vs.INFRACTOR_NUM_INT AS INFRACTORNUMINT, " + 
			"			vs.INFRACTOR_COLONIA AS INFRACTORCOLONIA, " + 
			"			vs.INFRACTOR_DELEGACION AS INFRACTORDELEGACION, " + 
			"			pkg_encripcion.desencripta(vs.infractor_licencia) AS INFRACTORLICENCIA, " + 
			"			vs.LICENCIA_TIPO AS LICENCIATIPO, " + 
			"			vs.LICENCIA_EXPEDIDA AS LICENCIAEXPEDIDA, " + 
			"			vs.TARJETA_CIRCULACION AS tarjetaCirculacion, " + 
			"			vs.INFRAC_CALLE AS INFRACCALLE, " + 
			"			vs.INFRAC_ENTRE_CALLE AS INFRACENTRECALLE, " + 
			"			vs.INFRAC_Y_CALLE AS INFRACYCALLE, " + 
			"			vs.INFRAC_COLONIA AS INFRACCOLONIA, " + 
			"			vs.INFRAC_DELEGACION AS INFRACDELEGACION, " + 
			"			vs.INFRAC_FECHA AS FECHA, " + 
			"			vs.INFRAC_PAGADA AS PAGADA, " + 
			"			vs.INFRAC_ARTICULO AS INFRACARTICULO, " + 
			"			vs.INFRAC_FRACCION AS INFRACFRACCION, " + 
			"			vs.INFRAC_PARRAFO AS INFRACPARRAFO, " + 
			"			vs.INFRAC_INCISO AS INFRACINCISO, " + 
			"			vs.INFRAC_MOTIVACION AS MOTIVACION, " + 
			"			vs.SANCION_DIAS AS SANCION, " + 
			"			vs.VEHICULO_MARCA AS VEHICULOMARCA, " + 
			"			vs.VEHICULO_MODELO AS VEHICULOMODELO, " + 
			"			vs.VEHICULO_COLOR AS VEHICULOCOLOR, " + 
			"			vs.VEHICULO_TIPO AS VEHICULOTIPO, " + 
			"			vs.DEPOSITO AS DEPOSITO," + 
			"			vs.GRUA AS GRUA," + 
			"			vs.OFICIAL_NOMBRE AS OFICIALNOMBRE, " + 
			"			vs.OFICIAL_PLACA AS OFICIALPLACA, " + 
			"			vs.OFICIAL_SECTOR AS OFICIALSECTOR, " + 
			"			vs.OFICIAL_AGRUPAMIENTO AS OFICIALAGRUPAMIENTO, " + 
			"			vs.INFRAC_OBSERVACION AS INFRACOBSERVACION, " + 
			"			vs.INFRAC_L_SERV_PUBLICO AS INFRACLSERVPUBLICO, " + 
			"			vs.INFRAC_UNITERR AS INFRACUNITERR, " + 
			"			vs.RESPONSABLE_VEHID as RESPONSABLEVEHID, " + 
			"			vs.INFRAC_SEC_ID AS INFRACSECID, " + 
			"			vs.INFRACTOR_EDO AS INFRACTOREDO, " + 
			"			vs.INFRACTOR_EDO_ID AS INFRACTOREDOID, " + 
			"			vs.INFRACTOR_DELEG_ID AS INFRACTORDELEGID, " + 
			"			vs.LIC_EXPEDIDA_ID AS LICEXPEDIDAID, " + 
			"			vs.VEHICULO_ORIGEN AS VEHICULOORIGEN, " + 
			"			vs.INFRAC_LEYTRANSP AS INFRACLEYTRANSP, " + 
			"			vs.INFRAC_UNITERRID AS INFRACUNITERRID, " + 
			"			vs.PLACA_EXPEDIDAID AS PLACAEXPEDIDAID, " + 
			"			vs.VEHICULO_MARCAID AS VEHICULOMARCAID, " + 
			"			vs.VEHICULO_MODELOID AS VEHICULOMODELOID, " + 
			"			vs.VEHICULO_COLORID AS VEHICULOCOLORID, " + 
			"			vs.GRUA_ID AS GRUAID, " + 
			"			vs.OFICIAL_SECTORID AS OFICIALSECTORID, " + 
			"			vs.OFICIAL_AGRUPAMIENTOID AS OFICIALAGRUPAMIENTOID, " + 
			"			vs.ARTICULOID AS ARTICULOID, " + 
			"			vs.EMP_ID AS EMPID, " + 
			"			vs.INFRACTOR_RFC AS INFRACTORRFC, " + 
			"			vs.FECHA_EMISION AS FECHAEMISION, " + 
			"			vs.INFRAC_ARRASTRE AS INFRACARRASTRE, " + 
			"			vs.INFRAC_TIPO_ARRASTRE AS INFRACTIPOARRASTRE, " + 
			"			vs.INFRAC_CON_DIRECCION AS INFRACCONDIRECCION, " + 
			"			vs.INFRAC_FRENTE_AL_NUM AS INFRACFRENTEALNUM, " + 
			"			vs.INFRAC_OBSERVE_QUE_ID AS INFRACOBSERVEQUEID, " + 
			"			vs.INFRAC_OBSERVE_QUE_DESC AS INFRACOBSERVEQUEDESC, " + 
			"			vs.GARANTIA_TIPO_ID AS GARANTIATIPOID, " + 
			"			vs.GARANTIA_TIPO_NOMBRE AS GARANTIATIPONOMBRE, " + 
			"			vs.GARANTIA_FOLIO AS GARANTIAFOLIO, " + 
			"			vs.GARANTIA_PROCESO_ID AS GARANTIAPROCESOID " + 
			"  FROM V_SSP_INFRAC_CONS_GRAL_F vs  ")
	public VSSPInfracConsGralFVO buscarInfraccionesAllDataByInfracNumV2(@Param("infracNum")String infracNum);
	
	@Select("	SELECT AUTORIZADO_POR as autorizaId,  " + 
			"			       MODIFICADO_POR as modificadoPor " + 
			"			 FROM infracciones WHERE INFRAC_NUM = #{infracNum} ")
	public VSSPInfracConsGralFVO datosParaBitacoraInfracciones(@Param("infracNum")String infracNum);
	
	@Select("	SELECT INFRAC_NUM_CTRL numeroControlInterno,  " +
			"                  AUTORIZADO_POR as autorizaId,  " +
			"			       MODIFICADO_POR as modificadoPor " + 
			"			 FROM INFRACCIONES_DIGITALIZACION WHERE INFRAC_NUM = #{infracNum} ")
	public VSSPInfracConsGralFVO datosParaBitacoraDigitalizacion(@Param("infracNum")String infracNum);
	
	@Select("	SELECT INFRAC_NUM_CTRL numeroControlInterno,  " +
			"                  AUTORIZADO_POR as autorizaId,  " +
			"			       MODIFICADO_POR as modificadoPor " + 
			"			 FROM INFRACCIONES_RADAR WHERE INFRAC_NUM = #{infracNum} ")
	public VSSPInfracConsGralFVO datosParaBitacoraInfraccionesRadar(@Param("infracNum")String infracNum);

	@Select("	SELECT VTIPO_ID as tipoVehiculoId,  " + 
			"			       MOTIVO_CAMBIO as motivoCambio,  " + 
			"			       AUTORIZADO_POR as autorizaId, " + 
			"            FECHA_AUTORIZACION as fechaAutoriza, " + 
			"			      INFRAC_PLACA as vehiculoPlaca, " + 
			"            INFRAC_TIPO_ARRASTRE as infracTipoArrastre, " +
			"			ART_ID as articuloId	" + 
			"			 FROM INFRACCIONES WHERE INFRAC_NUM = #{infracNum} ")
	public VSSPInfracConsGralFVO getOldVoInfracMod(@Param("infracNum")String infracNum);

	
	/* Obtener datos de una infracción sin filtros para el servicio de bitacoras ... */
	@Select("SELECT INFRAC_NUM AS infraccionNumero, " +
			"INFRAC_PLACA AS vehiculoPlaca, " +
			"INFRAC_IMPRESA AS impresa, " +
			"ART_ID AS articuloId, " +
			"INFRAC_M_FECHA_HORA AS fecha, " +
			"INFRAC_LICENCIA AS infractorLicencia, " +
			"VTARJETA_CIRCULACION AS tarjetaCirculacion, " +
			"INFRAC_M_EN_LA_CALLE AS infracCalle, " +
			"INFRAC_M_ENTRE_CALLE AS infracEntreCalle, " +
			"VMAR_ID AS vehiculoMarcaId, " +
			"VMOD_ID AS vehiculoModeloId, " +
			"VCOLOR_ID AS vehiculoColorId, " +
			"INFRAC_I_NOMBRE AS infractorNombre, " +
			"INFRAC_I_PATERNO AS infractorApePaterno, " +
			"INFRAC_I_MATERNO AS infractorApeMaterno, " +
			"SEC_ID AS infracSecId, " +
			"UT_ID AS infracUniterrId, " +
			"INFRAC_I_CALLE AS infractorCalle, " +
			"INFRAC_I_NUM_EXT AS infractorNumExt, " +
			"INFRAC_I_NUM_INT AS infractorNumInt, " +
			"INFRAC_I_COLONIA AS infractorColonia, " +
			"INFRAC_I_EDO_ID AS infractorEdoId, " +
			"INFRAC_I_DEL_ID AS infractorDelegId, " +
			"INFRAC_PLACA_EDO AS placaExpedidaId, " +
			"TIPO_L_ID AS licenciaTipoId, " +
			"INFRAC_LIC_EDO AS licExpedidaId, " +
			"INFRAC_I_RFC AS infractorRfc, " +
			"INFRAC_L_SERV_PUBLICO AS infracLServPublico, " +
			"VORIGEN AS vehiculoOrigen, " +
			"INFRAC_M_Y_LA_CALLE AS infracYCalle, " +
			"INFRAC_M_COLONIA AS infracColonia, " +
			"INFRAC_M_DEL_ID AS infracDelegacionId, " +
			"INFRAC_OBSERVACION AS infracObservacion, " +
			"INFRAC_LEY_TRANSPORTE AS infracLeyTransp, " +
			"R_VEH_ID AS responsableVehId, " +
			"DEP_ID AS idDeposito, " +
			"VTIPO_ID AS tipoVehiculoId, " +
			"INFRAC_ARRASTRE AS infracArrastre, " +
			"INFRAC_TIPO_ARRASTRE AS infracTipoArrastre,  " +
			"AUTORIZADO_POR AS autorizaId, " +
			"FECHA_AUTORIZACION AS fechaAutoriza, " +
			"MOTIVO_CAMBIO AS motivoCambio, " +
			"CON_DIRECCION AS infracConDireccion, " +
			"FRENTE_AL_NUM AS infracFrenteAlNum, " +
			"OBSERVE_QUE AS infracObserveQueId  " +
			"FROM INFRACCIONES  " +
			"WHERE INFRAC_NUM = #{infracNum} ")
	public VSSPInfracConsGralFVO getDatosInfraccionParaBitacora(@Param("infracNum")String infracNum);

	@Select("SELECT INFRAC_NUM AS infraccionNumero, " +
			"INFRAC_PLACA AS vehiculoPlaca, " +
			"AUTORIZADO_POR AS autorizaId, " +
			"FROM INFRACCIONES_DIGITALIZACION  " +
			"WHERE INFRAC_NUM = #{infracNum} ")
	public VSSPInfracConsGralFVO getDatosInfraccionDigitalizacionParaBitacora(@Param("infracNum")String infracNum);

	@Select("SELECT ART as artNumero, FRACCION as artFraccion, INCISO as artInciso, PARRAFO as artParrafo, DESCRIPCION as artMotivacion, ART_ID as artId FROM V_INFRACCION_DEP_MODIFICA")
	public List<ArticuloVO> buscarExcepcionesEnArticulos();
	

	String gralInfracAllTipo = " SELECT VTIPO_ID tipoVehiculoId, " + 
			"        f.MOTIVO_CAMBIO motivoCambio, " + 
			"        f.AUTORIZADO_POR autorizaId, " +
			"        f.FECHA_AUTORIZACION fechaAutoriza, " + 
			"        f.INFRAC_M_DEL_ID infracDelegacionId, " + 
			"        f.TIPO_L_ID licenciaTipoId, " + 
			"      vs.INFRACCION AS INFRACCIONNUMERO, " + 
			"			vs.IMPRESA AS IMPRESA, " + 
			"			vs.NCI AS NCI, " + 
			"			vs.INFRAC_SECTOR AS INFRACSECTOR, " + 
			"			vs.TIENE_PLACA AS TIENEPLACA, " + 
			"			vs.VEHICULO_PLACA AS VEHICULOPLACA, " + 
			"			vs.PLACA_EXPEDIDA AS PLACAEXPEDIDA, " + 
			"			vs.INFRACTOR_APE_PATERNO AS INFRACTORAPEPATERNO, " + 
			"			vs.INFRACTOR_APE_MATERNO AS INFRACTORAPEMATERNO, " + 
			"			vs.INFRACTOR_NOMBRE AS INFRACTORNOMBRE, " + 
			"			vs.INFRACTOR_CALLE AS INFRACTORCALLE, " + 
			"			vs.INFRACTOR_NUM_EXT AS INFRACTORNUMEXT, " + 
			"			vs.INFRACTOR_NUM_INT AS INFRACTORNUMINT, " + 
			"			vs.INFRACTOR_COLONIA AS INFRACTORCOLONIA, " + 
			"			vs.INFRACTOR_DELEGACION AS INFRACTORDELEGACION, " + 
			"			pkg_encripcion.desencripta(vs.infractor_licencia) AS INFRACTORLICENCIA, " + 
			"			vs.LICENCIA_TIPO AS LICENCIATIPO, " + 
			"			vs.LICENCIA_EXPEDIDA AS LICENCIAEXPEDIDA, " + 
			"			vs.TARJETA_CIRCULACION AS tarjetaCirculacion, " + 
			"			vs.INFRAC_CALLE AS INFRACCALLE, " + 
			"			vs.INFRAC_ENTRE_CALLE AS INFRACENTRECALLE, " + 
			"			vs.INFRAC_Y_CALLE AS INFRACYCALLE, " + 
			"			vs.INFRAC_COLONIA AS INFRACCOLONIA, " + 
			"			vs.INFRAC_DELEGACION AS INFRACDELEGACION, " + 
			"			vs.INFRAC_FECHA AS FECHA, " + 
			"			vs.INFRAC_PAGADA AS PAGADA, " + 
			"			vs.INFRAC_ARTICULO AS INFRACARTICULO, " + 
			"			vs.INFRAC_FRACCION AS INFRACFRACCION, " + 
			"			vs.INFRAC_PARRAFO AS INFRACPARRAFO, " + 
			"			vs.INFRAC_INCISO AS INFRACINCISO, " + 
			"			vs.INFRAC_MOTIVACION AS MOTIVACION, " + 
			"			vs.SANCION_DIAS AS SANCION, " + 
			"			vs.VEHICULO_MARCA AS VEHICULOMARCA, " + 
			"			vs.VEHICULO_MODELO AS VEHICULOMODELO, " + 
			"			vs.VEHICULO_COLOR AS VEHICULOCOLOR, " + 
			"			vs.VEHICULO_TIPO AS VEHICULOTIPO, " + 
			"			vs.DEPOSITO AS DEPOSITO," + 
			"			vs.GRUA AS GRUA," + 
			"			vs.OFICIAL_NOMBRE AS OFICIALNOMBRE, " + 
			"			vs.OFICIAL_PLACA AS OFICIALPLACA, " + 
			"			vs.OFICIAL_SECTOR AS OFICIALSECTOR, " + 
			"			vs.OFICIAL_AGRUPAMIENTO AS OFICIALAGRUPAMIENTO, " + 
			"			vs.INFRAC_OBSERVACION AS INFRACOBSERVACION, " + 
			"			vs.INFRAC_L_SERV_PUBLICO AS INFRACLSERVPUBLICO, " + 
			"			vs.INFRAC_UNITERR AS INFRACUNITERR, " + 
			"			vs.RESPONSABLE_VEHID as RESPONSABLEVEHID, " + 
			"			vs.INFRAC_SEC_ID AS INFRACSECID, " + 
			"			vs.INFRACTOR_EDO AS INFRACTOREDO, " + 
			"			vs.INFRACTOR_EDO_ID AS INFRACTOREDOID, " + 
			"			vs.INFRACTOR_DELEG_ID AS INFRACTORDELEGID, " + 
			"			vs.LIC_EXPEDIDA_ID AS LICEXPEDIDAID, " + 
			"			vs.VEHICULO_ORIGEN AS VEHICULOORIGEN, " + 
			"			vs.INFRAC_LEYTRANSP AS INFRACLEYTRANSP, " + 
			"			vs.INFRAC_UNITERRID AS INFRACUNITERRID, " + 
			"			vs.PLACA_EXPEDIDAID AS PLACAEXPEDIDAID, " + 
			"			vs.VEHICULO_MARCAID AS VEHICULOMARCAID, " + 
			"			vs.VEHICULO_MODELOID AS VEHICULOMODELOID, " + 
			"			vs.VEHICULO_COLORID AS VEHICULOCOLORID, " + 
			"			vs.GRUA_ID AS GRUAID, " + 
			"			vs.OFICIAL_SECTORID AS OFICIALSECTORID, " + 
			"			vs.OFICIAL_AGRUPAMIENTOID AS OFICIALAGRUPAMIENTOID, " + 
			"			vs.ARTICULOID AS ARTICULOID, " + 
			"			vs.EMP_ID AS EMPID, " + 
			"			vs.INFRACTOR_RFC AS INFRACTORRFC, " + 
			"			vs.FECHA_EMISION AS FECHAEMISION, " + 
			"			vs.INFRAC_ARRASTRE AS INFRACARRASTRE, " + 
			"			vs.INFRAC_TIPO_ARRASTRE AS INFRACTIPOARRASTRE, " + 
			"			vs.INFRAC_CON_DIRECCION AS INFRACCONDIRECCION, " + 
			"			vs.INFRAC_FRENTE_AL_NUM AS INFRACFRENTEALNUM, " + 
			"			vs.INFRAC_OBSERVE_QUE_ID AS INFRACOBSERVEQUEID, " + 
			"			vs.INFRAC_OBSERVE_QUE_DESC AS INFRACOBSERVEQUEDESC, " + 
			"			vs.GARANTIA_TIPO_ID AS GARANTIATIPOID, " + 
			"			vs.GARANTIA_TIPO_NOMBRE AS GARANTIATIPONOMBRE, " + 
			"			vs.GARANTIA_FOLIO AS GARANTIAFOLIO, " + 
			"			vs.GARANTIA_PROCESO_ID AS GARANTIAPROCESOID " + 
			"  FROM V_SSP_INFRAC_CONS_GRAL_F vs  ";
	
	
	@Select(gralInfracAllTipo + "  inner join INFRACCIONES f on  "+
			   "  vs.INFRACCION = f.infrac_num "+
			   " WHERE f.infrac_num = #{infraccion}  ")
	public VSSPInfracConsGralFVO buscarInfraccionesAllDataInfrac(@Param("infraccion") String infraccion);

	@Select(gralInfracAllTipo + "  inner join INFRACCIONES_RADAR f on  "+
			   "  vs.INFRACCION = f.infrac_num "+
			   " WHERE f.infrac_num = #{infraccion}  ")
	public VSSPInfracConsGralFVO buscarInfraccionesAllDataInfraR(@Param("infraccion")String infraccion);

	@Select(gralInfracAllTipo + "  inner join INFRACCIONES_DIGITALIZACION f on  "+
			   "  vs.INFRACCION = f.infrac_num "+
			   " WHERE f.infrac_num = #{infraccion}  ")
	public VSSPInfracConsGralFVO buscarInfraccionesAllDataInfracD(@Param("infraccion")String infraccion);

	
	@Select("select  emp_id from V_SSP_INFRAC_CONS_GRAL_F where infraccion = #{valor}")
	public Long buscarInfraccionesInfraccion2(@Param("valor")String getpInfracNum);

	@Select("select  INFRAC_FECHA from V_SSP_INFRAC_CONS_GRAL_F where infraccion = #{valor}")
	public String getFechaAutoriza(@Param("valor") String getpInfracNum);

	
}
