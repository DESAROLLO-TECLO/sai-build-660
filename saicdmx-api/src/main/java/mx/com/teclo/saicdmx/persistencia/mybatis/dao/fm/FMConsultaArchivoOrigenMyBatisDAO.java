package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;

import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaArchivoDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaArchivoOrigenVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoEstatusProcesoArchivoVO;


@Mapper
public interface FMConsultaArchivoOrigenMyBatisDAO {

//	String CONSULTA_ORIGEN_ARCHIVO_TODO = "SELECT DISTINCT" + "	ID_ARCHIVO AS id_Archivo,"
//			+ "	NB_ARCHIVO AS nb_Archivo, " + "	NB_ORIGINAL AS nb_Original, "
//			+ "ID_TIPO_ARCHIVO as id_Tipo_Archivo, "
//			+ "	ID_TIPO_DETECCION AS id_Tipo_Deteccion, " + "	ST_PROCESO AS st_Proceso, "
//			+ "	CANTIDAD_CARGADOS AS cantidad_Cargado, " + "	FH_INI_CARGA AS fh_Ini_Carga, "
//			+ "	FH_FIN_CARGA AS fh_Fin_Carga," + "	ST_ACTIVO AS st_Activo, "
//			+ "	CANTIDAD_REPORTADOS AS cantidad_Reportado, "
//			+ "	CANTIDAD_INACTIVOS AS cantidad_Inactivo, "
//			+ "	CANTIDAD_PROCESADOS AS cantidad_Procesado, "
//			+ "	ST_COMPLEMENTADO AS st_Complementado " + "   FROM TAI028D_FC_ARCHIVO Archivo ";

//
//	 String CONSULTA_CAT_ESTAUS_PROCESO =
//	 "SELECT "
//	 + " ID_PROC_ARCH_ORIG AS value,"
//	 + " NB_PROC_ARCH_ORIG AS label"
//	 + " FROM TAI042C_FM_PROC_ARCH_ORIG "
//	 + " WHERE ST_ACTIVO = 1 ";
	 
//	 
//		String BUSCA_ARCHIVO_TIPO = 
//				"SELECT "
//				+ "	TAI033.ID_TIPO_ARCHIVO_FCIVICA value, "
//				+ "	TAI033.NB_TIPO_ARCHIVO_FCIVICA AS label "
//				+ "FROM TAI033C_FC_TIPO_ARCH_FCIVICA TAI033 "
//			+ "WHERE "
//			+ "	TAI033.ST_ACTIVO = 1";
		

	@Select(CONSULTA_ORIGEN_ARCHIVO+ORDER)
	public List<FMConsultaArchivoOrigenVO> consultaOrigenArchivoTodo(
			@Param("queryPorcetaje") String queryPorcetaje) throws PersistenceException;
	
	@Select(CONSULTA_ARCH_ORIGEN_PORCENTAJE)
	public Map<String,String> consultaPorcentaje()throws PersistenceException;
	
	String CONSULTA_ARCH_ORIGEN_PORCENTAJE="SELECT CD_LLAVE_P_CONFIG,CD_VALOR_P_CONFIG FROM TAI041P_CONFIGURACION WHERE CD_LLAVE_P_CONFIG='PORCENTAJE_FOTOCIVICAS'";
	

	String CONSULTA_ORIGEN_ARCHIVO_DETECCION = " select" + " ID_FC_DETECCION as id_fc_deteccion ,"
			+ " ID_ARCHIVO  as id_archivo ," + " DECODE(ST_ACTIVO,0,'NO',1,'SI') as st_activo ," + " DECODE(ST_DUPLICADO,0,'NO',1,'SI') as st_duplicado ,"
			+ " DECODE(ST_PROCESADO,0,'NO',1,'SI') as st_procesado ," + " DECODE(ST_CANCELADO,0,'NO','SI') as st_cancelado ,"
			+ " DECODE(ST_LIBERADO,0,'NO',1,'SI')	as st_liberado ," + " CD_PLACA	as cd_placa ," + " CD_FECHAHORA	as cd_fechahora ,"
			+ " CD_TDSKUID	as cd_tdskuid ," + " CD_UT 	as cd_ut ,"
			+ " NB_UT_CALLE	as nb_ut_calle, " + " NB_UT_ENTRECALLE	as nb_ut_entrecalle ,"
			+ " NB_UT_YCALLE	as nb_ut_ycalle ," + " CD_UT_SEC_COD  as  cd_ut_sec_cod ,"
			+ " ID_UT_DELEGACION	as id_ut_delegacion ," + " CD_UT_CODIGO	as cd_ut_codigo, "
			+ " NB_UT_COLONIA	as nb_ut_colonia ," + " TX_UT_SENTIDO 	as tx_ut_sentido ,"
			+ " NU_VELOCIDADDETECTADA 	as nu_velocidaddetectada ," + " NU_PUNTOSDESCNT 	as nu_puntosdescnt ,"
			+ " CD_OFICIALPLACA 	as  cd_oficialplaca ," + " ID_ARTID 	as  id_artid ,"
			+ " DECODE(ID_ORIGENPLACA,0,'CDMX',1,'FORANEA') as  id_origenplaca ," + " NB_CALLE	as  nb_nombre, "
			+ " NB_APELLIDOPAT 	as   nb_apellidopat ," + " NB_APELLIDOMAT	as  nb_apellidomat ,"
			+ " NB_CALLE 	as  nb_calle ," + " NU_NUMEXT 	as   nu_numext ," + " NU_NUMINT 	as  nu_numint ,"
			+ " NB_COLONIA 	as  nb_colonia ," + " NU_CP	as  nu_cp ," + " ID_ENTIDAD 	as  id_entidad ,"
			+ " ID_DELEGACION 	as  id_delegacion ," + " NU_TELEFONO 	as  nu_telefono ,"
			+ " TX_CORREO_ELECT 	as  tx_correo_elect ," 
			+ " DECODE(ID_TIPO_DETECCION,1,'Radar Velocidad',2,'Radar Velocidad',3,'Carril Confinado',4,'Fotomulta',5,'Fotomulta',6,'Fotomulta',7,'Radar Velocidad',8,'Carril Confinado') as id_tipo_deteccion,"
			+ " NU_LICENCIA 	as  nu_licencia ," + " CD_TIPO_LICENCI	as  cd_tipo_licenci ,"
			+ "  DECODE(ID_TIPO_PERSONA,1,'FORÁNEAS',2,'FISÍCAS',3,'MORALES') as id_tipo_persona, "
			+ " FH_VIG_LICENCIA 	as  fh_vig_licencia ," + " NU_VIN 	as  nu_vin ," + " NB_MARCA 	as  nb_marca ,"
			+ " NB_SUBMAR	as  nb_submar, " + " NB_MODELO 	as  nb_modelo ," + " NU_NUMSERIE 	as  nu_numserie ,"
			+ " NU_NUMMOTOR 	as  nu_nummotor ," + " ST_SOLICITA_LC 	as st_solicita_lc ,"
			+ " NU_PORCENT_DESC 	as nu_porcent_desc ," + " ST_REGISTRADO 	as st_registrado ,"
			+ " NU_UMAS 	as nu_umas ," + " DECODE(ST_ACEPTADA_SSP,0,'NO',1,'SI') as st_aceptada_ssp ,"
			+ " ID_MOT_DESCARTE_SSP 	as id_mot_descarte_ssp ," + " ID_USR_CREACION 	as id_usr_creacion ,"
			+ " FH_CREACION	as fh_creacion ," + " ID_USR_MODIFICA	as id_usr_modifica ,"
			+ " FH_MODIFICACION  	as fh_modificacion ," + " TX_OBSERVACIONES 	as tx_observaciones ,"
			+ " CD_SERIE_DISPOSITIVO 	as cd_serie_dispositivo, "
			+ " NU_VEL_MAX_PERMITIDA 	as nu_vel_max_permitida ," + " ID_TIPO_DE_VIA 	as id_tipo_de_via ,"
			+ " NU_PORCNTAJ_VEL_EXCDIDO 	as nu_porcntaj_vel_excdido, " + " ID_TIPO_VEHICULO 	as id_tipo_vehiculo ,"
			+ " CD_EXT_SERIE_CAPTURA 	as cd_ext_serie_captura from TAI027D_FC_DETECCIONES"
			+ " WHERE ID_ARCHIVO=#{idArchivo}";
	

	
	
	//////////////// 7777

	// " SELECT "
	// + " CD_TDSKUID as cd_tdskuid ,ID_ARCHIVO as id_archivo,ST_ACTIVO as
	// st_activo,"
	// + " ST_DUPLICADO as st_duplicado ,ST_PROCESADO as st_procesado,"
	// + " ST_CANCELADO st_cancelado ,ST_LIBERADO as st_liberado,CD_PLACA as
	// cd_placa,"
	// + " NB_MARCA as nb_marca,NB_SUBMAR as nb_submar,NB_MODELO as nb_modelo
	// ,CD_FECHAHORA as cd_fechahora,"
	// + " ID_TIPO_DETECCION as id_tipo_deteccion ,NB_NOMBRE as nb_nombre
	// ,NB_APELLIDOPAT as nb_apellidopat ,NB_APELLIDOMAT as nb_apellidomat,
	// NB_COLONIA as nb_colonia,NU_CP as nu_cp,"
	// + " ID_TIPO_PERSONA as id_tipo_persona FROM TAI027D_FC_DETECCIONES "
	// + " WHERE ID_ARCHIVO=#{idArchivo}";
	
	
	
  

	String CONSULTA_ORIGEN_ARCHIVO = "${queryPorcetaje} ";
	
	
	String ORDER="ORDER BY FH_INI_CARGA DESC";
	

	String CONSULTA_CONDICION_ESTATUS = " WHERE " + "  CANTIDAD_REPORTADOS !=0  " + "  AND "
	        + "ST_COMPLEMENTADO=0 AND"
			+ "  ST_PROCESO IN(#{estProCancelado},#{estProPorComplementar})" + "  AND "

			+ "  TRUNC(TO_DATE(#{fechaInicio},'DD/MM/YYYY')) <= TRUNC(FH_INI_CARGA)"
			+ "  AND  TRUNC(TO_DATE(#{fechaFin},'DD/MM/YYYY')) >= TRUNC(FH_FIN_CARGA)" + "  AND"
			+ "  ID_TIPO_ARCHIVO in(#{tipoFisica},#{tipoMoral},#{tipoForanea})" + "  ORDER BY FH_INI_CARGA DESC ";

	String CONSULTA_CONDICION_COMPLEMENTADO = " WHERE " + "  ST_COMPLEMENTADO=1" + "  AND "
			+ "  TRUNC(TO_DATE(#{fechaInicio},'DD/MM/YYYY')) <= TRUNC(FH_INI_CARGA)"
			+ "  AND  TRUNC(TO_DATE(#{fechaFin},'DD/MM/YYYY')) >= TRUNC(FH_FIN_CARGA)" + "  AND"
			+ "  ID_TIPO_ARCHIVO in(#{tipoFisica},#{tipoMoral},#{tipoForanea})" + " 	 ORDER BY FH_INI_CARGA DESC ";

	@Select(CONSULTA_ORIGEN_ARCHIVO+CONSULTA_CONDICION_ESTATUS)
	public List<FMConsultaArchivoOrigenVO> consultaOrigenArchivoEstatus(@Param("fechaInicio") String fechaInicio,
			@Param("fechaFin") String fechaFin, @Param("estProCancelado") Integer estProCancelado,
			
			@Param("estProPorComplementar") Integer estProPorComplementar, 
				@Param("tipoMoral") Integer tipoMoral,
				@Param("tipoFisica") Integer tipoFisica,
				@Param("tipoForanea") Integer tipoForanea,
				@Param("queryPorcetaje") String queryPorcetaje)
			throws PersistenceException;

	@Select(CONSULTA_ORIGEN_ARCHIVO+CONSULTA_CONDICION_COMPLEMENTADO)
	public List<FMConsultaArchivoOrigenVO> consultaOrigenArchivoComplementado(@Param("fechaInicio") String fechaInicio,
			@Param("fechaFin") String fechaFin,
			// @Param("estProCancelado") Integer estProCancelado,
			// @Param("estProComplementado") Integer estProComplementado,
			// @Param("estProPorComplementar") Integer estProPorComplementar,
			// @Param("estProSinProcesar") Integer estProSinProcesar ,
			@Param("tipoMoral") Integer tipoMoral,
			@Param("tipoFisica") Integer tipoFisica,
			@Param("tipoForanea") Integer tipoForanea,
			@Param("queryPorcetaje") String queryPorcetaje) throws PersistenceException;

	@Select(CONSULTA_ORIGEN_ARCHIVO_DETECCION)
	public List<FMConsultaArchivoDeteccionesVO> consultaOrigenArchivoDeteccion(@Param("idArchivo") Integer idArchivo)
			throws PersistenceException;

	String VALIDA_ARCHIVO_PROCESO = "SELECT CANTIDAD_PROCESADOS FROM TAI028D_FC_ARCHIVO WHERE id_archivo = #{idArchivo}";

	@Select(VALIDA_ARCHIVO_PROCESO)
	public Integer validarArchivoProceso(@Param("idArchivo") Integer idArchivo);

	String ACTUALIZA_ARCHIVO_CANCELA = "update TAI028D_FC_ARCHIVO set ST_PROCESO =5,ST_CANCELADO=2,ID_USR_MODIFICA= #{usuario},FH_MODIFICACION= SYSDATE,"
			+ "TX_COMENTARIO= CONCAT(tx_comentario ,'CANCELADO POR USUARIO') where ID_ARCHIVO= #{idArchivo}";

	String ACTUALIZA_DETECCION_CANCELA = "update TAI027D_FC_DETECCIONES SET ST_CANCELADO=2,ID_USR_MODIFICA= #{usuario},FH_MODIFICACION= SYSDATE,TX_OBSERVACIONES=CONCAT(tx_observaciones,'CANCELADA POR USUARIO') WHERE ID_ARCHIVO= #{idArchivo} and ST_CANCELADO=0";
	
	String ACTUALIZA_DETECCION_CANCELA_05 = "update TAI005D_FM_DETECCIONES SET ST_CANCELADO=2,CD_MODIFICADOPOR= #{usuario},FH_MODIFICACION= SYSDATE,TX_OBS_CANCELACION=CONCAT(tx_obs_cancelacion,'CANCELADA POR USUARIO') WHERE ID_ARCHIVO_FC= #{idArchivo} and ST_CANCELADO=0";

	String CONSULTA_ARCHIVO_ID = "SELECT ID_ARCHIVO AS id_Archivo,NB_ARCHIVO AS nb_Archivo,NB_ORIGINAL AS nb_Original FROM TAI028D_FC_ARCHIVO WHERE ID_ARCHIVO=#{idArchivo}";

	@Update(ACTUALIZA_ARCHIVO_CANCELA)
	public Integer actualizaArchivoCancela(@Param("idArchivo") Integer idArchivo,
			@Param("usuario") String usuario);

	@Update(ACTUALIZA_DETECCION_CANCELA)
	public Integer actualizaDeteccionCancela(@Param("idArchivo") Integer idArchivo,
			@Param("usuario") String usuario);
	
	@Update(ACTUALIZA_DETECCION_CANCELA_05)
	public Integer actualizaDeteccionCancela05(@Param("idArchivo") Integer idArchivo,
			@Param("usuario") String usuario);

	@Select(CONSULTA_ARCHIVO_ID)
	public List<FMConsultaArchivoOrigenVO> consultaArchivoId(@Param("idArchivo") Integer idArchivo);

	String CONSULTA_ARCHIVO_SIN_DETECCIONES = "	WHERE CANTIDAD_REPORTADOS=0 AND TRUNC(TO_DATE(#{fechaInicio},'DD/MM/YYYY')) <= TRUNC(FH_CREACION) AND  TRUNC(TO_DATE(#{fechaFin},'DD/MM/YYYY')) >=TRUNC(FH_CREACION)";

	@Select(CONSULTA_ORIGEN_ARCHIVO+CONSULTA_ARCHIVO_SIN_DETECCIONES)
	public List<FMConsultaArchivoOrigenVO> consultaArchivoSinDetecciones(@Param("fechaInicio") String fechaInicio,
			@Param("fechaFin") String fechaFin,
			@Param("queryPorcetaje") String queryPorcetaje) throws PersistenceException;

	
	
	

	 String CONSULTA_CAT_ESTAUS_PROCESO ="SELECT "
	 + " ID_PROC_ARCH_ORIG AS value,"
	 + " NB_PROC_ARCH_ORIG AS label"
	 + " FROM TAI042C_FM_PROC_ARCH_ORIG "
	 + " WHERE ST_ACTIVO = 1 ";
	
	
	 @Select(CONSULTA_CAT_ESTAUS_PROCESO)
	 public List<FMTipoEstatusProcesoArchivoVO>consultaCatalogoEstatusArchivo() throws PersistenceException;
	
	 
	 
	 
		String BUSCA_ARCHIVO_TIPO = 
				"SELECT "
				+ "	TAI033.ID_TIPO_ARCHIVO_FCIVICA value, "
				+ "	TAI033.NB_TIPO_ARCHIVO_FCIVICA AS label "
				+ "FROM TAI033C_FC_TIPO_ARCH_FCIVICA TAI033 "
			+ "WHERE "
			+ "	TAI033.ST_ACTIVO = 1";
		
		
	 @Select(BUSCA_ARCHIVO_TIPO)
	 public List<FMTipoEstatusProcesoArchivoVO>
	 consultaCatalogoArchivoTipo() throws PersistenceException;
	

}
