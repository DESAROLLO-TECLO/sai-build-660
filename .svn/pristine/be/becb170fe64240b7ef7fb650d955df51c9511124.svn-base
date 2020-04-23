package mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.mapping.StatementType;

import mx.com.teclo.saicdmx.persistencia.vo.fm.DeteccionBatchFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FCConsultaDeteccionesSinProcVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FCConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMBitProcesoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesAgrupacionMes;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesDetalleHistoricoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesSPDetalleMesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionCP;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionCPV2;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLoteProcesoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLoteResumenVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMMarcaDispositivoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMOrigenPlacaFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoArchivoFCVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTipoFotocivicaVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMTiposDeteccionesVO;

@Mapper
public interface FMDeteccionesMyBatisDAO {

	String ALLMARCADISPOSITIVO= "SELECT  ID_MARCA_DISPOSITIVO_DET "
			+ "FROM TAI007C_FM_TIPO_DETECCIONES   WHERE ID_ARCHIVO_TIPO_FORA = #{idTipDeteccion}";
	@Select(value = ALLMARCADISPOSITIVO)
	public List<String> getMarcas(@Param("idTipDeteccion") Integer idTipDeteccion) throws PersistenceException;
	
	String TIPODETECCION = "SELECT DISTINCT(ID_ARCHIVO_TIPO_FORA) AS idArchivoTipoFora, NB_DISPOSITIVO_DETECCION AS nbDispositivoDeteccion"
			+ " FROM TAI007C_FM_TIPO_DETECCIONES";

	@Select(value = TIPODETECCION)
	@Options(statementType = StatementType.CALLABLE)
	public List<FMTiposDeteccionesVO> buscarTipoDeteccion() throws PersistenceException;
	
//	String TIPODETECCIONFC = 
//			"SELECT "
//			+ "	DISTINCT(ID_ARCHIVO_TIPO_FORA) AS idArchivoTipoFora, "
//			+ "	NB_DISPOSITIVO_DETECCION AS nbDispositivoDeteccion, "
//			+ "	ID_TIPO_DETECCION AS idTipoDeteccion "
//			+ "FROM TAI007C_FM_TIPO_DETECCIONES "
//			+ "WHERE ST_ACTIVO = 1 "
//			+ "AND ID_TIPO_DETECCION <> 99";
	String TIPODETECCIONFCOPCION =
			"SELECT "
			+ "	ID_TIPO_FOTOCIVICA AS idTipoFotocivica, "
			+ "	CD_TIPO_FOTOCIVICA AS cdTipoFotocivica, "
			+ "	NB_TIPO_FOTOCIVICA AS nbTipoFotocivica, "
			+ "	TX_TIPO_FOTOCIVICA AS txTipoFotocivica, "
			+ "	CD_TIPO_FOLIO_INFRACCION AS cdTipoFolioInfraccion "
			+ "FROM TAI036C_FC_TIPO_FOTOCIVICA "
			+ "WHERE "
			+ "(case when #{opcion} = -1 and ID_TIPO_FOTOCIVICA = ID_TIPO_FOTOCIVICA then 1 " 
			+ "		 when #{opcion} = 0 and (ID_TIPO_FOTOCIVICA = 1 or ID_TIPO_FOTOCIVICA = 2) then 1 " 
			+ "      when #{opcion} = 1 and ID_TIPO_FOTOCIVICA = 3 then 1 " 
			+ "end) = 1 "
			+ "	AND ID_TIPO_FOTOCIVICA != 99 "
			+ "ORDER BY ID_TIPO_FOTOCIVICA asc";
		
		@Select(value = TIPODETECCIONFCOPCION)
		@Options(statementType = StatementType.CALLABLE)
		public List<FMTipoFotocivicaVO> buscarTipoDeteccionFcOpcion(
			@Param("opcion") Integer opcion) throws PersistenceException;
	
	String TIPODETECCIONFC =
		"SELECT "
		+ "	ID_TIPO_FOTOCIVICA AS idTipoFotocivica, "
		+ "	CD_TIPO_FOTOCIVICA AS cdTipoFotocivica, "
		+ "	NB_TIPO_FOTOCIVICA AS nbTipoFotocivica, "
		+ "	TX_TIPO_FOTOCIVICA AS txTipoFotocivica, "
		+ "	CD_TIPO_FOLIO_INFRACCION AS cdTipoFolioInfraccion "
		+ "FROM TAI036C_FC_TIPO_FOTOCIVICA "
		+ "WHERE "
		+ "	ST_ACTIVO = 1 "
		//+ "	--AND ID_TIPO_FOTOCIVICA != 99 "
		+ "	AND ID_TIPO_FOTOCIVICA != CASE "
		+ "		WHEN #{todos} <> 1 "
		+ "			THEN 99 "
		+ "		ELSE "
		+ "			0 "
		+ "		END "
		+ "ORDER BY ID_TIPO_FOTOCIVICA asc";
	
	@Select(value = TIPODETECCIONFC)
	@Options(statementType = StatementType.CALLABLE)
	public List<FMTipoFotocivicaVO> buscarTipoDeteccionFc(
		@Param("todos") Integer todos) throws PersistenceException;
	
	String TIPORADAR = "SELECT " 
			+ "	ID_MARCA_DISPOSITIVO_DET AS idMarcaDispositivoDet, "
			+ "	NB_DISPOSITIVO AS nbMarcaDispositivo " 
			+ "FROM TAI009C_FM_MARCA_DISPOSITIVO "
			+ "WHERE ID_MARCA_DISPOSITIVO_DET " 
			+ "IN(" 
			+ "		SELECT ID_MARCA_DISPOSITIVO_DET "
			+ "		FROM TAI007C_FM_TIPO_DETECCIONES " 
			+ "		WHERE ID_ARCHIVO_TIPO_FORA = #{idMarca}" 
			+ ")";

	@Select(value = TIPORADAR)
	@Options(statementType = StatementType.CALLABLE)
	public List<FMMarcaDispositivoVO> ListaTipoRadar(
		@Param("idMarca") Integer idMarca
	) throws PersistenceException;

	String IDTIPODETECCION = 
		"SELECT "
		+ "	ID_TIPO_FOTOCIVICA AS idTipoFotocivica, "
		+ "	ID_ARCHIVO_TIPO_CDMX AS idArchivoTipoCDMX, "
		+ "	ID_ARCHIVO_TIPO_FORA AS idArchivoTipoFora "
		+ "FROM TAI036C_FC_TIPO_FOTOCIVICA "
		+ "WHERE ID_TIPO_FOTOCIVICA = #{tipoDeteccion}";

	@Select(value = IDTIPODETECCION)
	@Options(statementType = StatementType.CALLABLE)
	public FMTipoFotocivicaVO consultaIdTipoDeteccion(
			@Param("tipoDeteccion") Integer tipoDeteccionFora) throws PersistenceException;
	
	
	String IDTIPODETECCIONALL = 
	"SELECT    ID_TIPO_DETECCION AS idTipoDeteccion, ID_ARCHIVO_TIPO_CDMX AS idArchivoTipoCDMX, "
	+ "ID_ARCHIVO_TIPO_FORA AS idArchivoTipoFora " + 
	"FROM TAI007C_FM_TIPO_DETECCIONES   " + 
	"WHERE ID_ARCHIVO_TIPO_FORA = #{tipoDeteccion}  AND ID_MARCA_DISPOSITIVO_DET = " + 
	"    CASE    " + 
	"			 	WHEN #{idMarca} = 0  " + 
	"			 	THEN ID_MARCA_DISPOSITIVO_DET " + 
	"			 	ELSE #{idMarca}  " + 
	"			 	END";
	

	@Select(value = IDTIPODETECCIONALL)
	@Options(statementType = StatementType.CALLABLE)
	public List<FMTiposDeteccionesVO> consultaIdTipoDeteccionAll(@Param("tipoDeteccion") Integer tipoDeteccion,
			@Param("idMarca") Integer idMarca) throws PersistenceException;
	
	

	String BUSCA_OLD_LOTE_EN_PROCESO = "SELECT ra.NOMBRE as loteNombre, ra.id as radarArchivoId, ra.fecha_emision as fechaEmision, rep.nombre as estatusProceso, "
			+ "CASE WHEN ra.en_proceso = 1 THEN 'Si' WHEN ra.en_proceso = 0 THEN 'No' END as enProceso, "
			+ "(SELECT COUNT(rad.id) FROM RADAR_ARCHIVO_DETALLE rad WHERE rad.activo = 1) as totalValidas, "
			+ "(SELECT COUNT(rad.id) FROM RADAR_ARCHIVO_DETALLE rad WHERE rad.activo = 0) as totalInvalidas "
			+ "FROM RADAR_ARCHIVO ra JOIN RADAR_ESTATUS_PROCESO rep ON (ra.estatus_proceso_id = rep.id) "
			+ "WHERE ra.en_proceso = 1";

	@Select(value = BUSCA_OLD_LOTE_EN_PROCESO)
	public FMLoteProcesoVO buscaAntiguoLoteEnProceso();

	String BUSCA_LOTE_EN_PROCESO = "SELECT tai010.NOMBRE as loteNombre, tai010.id as radarArchivoId, tai010.fecha_emision as fechaEmision, rep.nombre as estatusProceso, tai010.estatus_proceso_id as idEstatusProceso, "
			 + "CASE WHEN tai010.en_proceso = 1 THEN 'Si' WHEN tai010.en_proceso = 0 THEN 'No' END as enProceso, " 
			 + "(SELECT COUNT(tai011.id) FROM TAI011D_FM_DETALLE tai011 WHERE tai011.activo = 1 and tai011.radar_archivo_id = tai010.id) as totalValidas, " 
			 + "(SELECT COUNT(tai011.id) FROM TAI011D_FM_DETALLE tai011 WHERE tai011.activo = 0 and tai011.radar_archivo_id = tai010.id) as totalInvalidas " 
			 + "FROM TAI010D_FM_LOTE tai010 JOIN RADAR_ESTATUS_PROCESO rep ON (tai010.estatus_proceso_id = rep.id) "
			 + "WHERE tai010.en_proceso = 1";
	
	@Select(value = BUSCA_LOTE_EN_PROCESO)
	public FMLoteProcesoVO buscaLoteEnProceso();
	
	
	String UPDATE_CODIGO_POSTAL = "update tai011d_fm_detalle "
			+ "set codigo_postal = #{newcp}, centro_reparto = 0, CP_MODIFICADO_POR = #{usuario} where id = #{id}";
	
	@Update(value = UPDATE_CODIGO_POSTAL)
	public void cambiaCodigoPostal(@Param("id") Long id, @Param("newcp") String newcp, @Param("usuario") Long usuario);
	

	String UPDATE_CODIGO_POSTAL_RAD = "update RADAR_ARCHIVO_DETALLE "
			+ "set codigo_postal = #{newcp}, centro_reparto = 0, CP_MODIFICADO_POR = #{usuario} where id = #{id}";
	
	@Update(value = UPDATE_CODIGO_POSTAL_RAD)
	public void cambiaCodigoPostalRAD(@Param("id") Long id, @Param("newcp") String newcp, @Param("usuario") Long usuario);
	
	
	String BUSCA_BIT_LOTE_EN_PROCESO = "SELECT rep.nombre as estatusNombre, t013.id_estatus_proceso as estatusProcesoId, "
			+ "t013.fh_creacion as fechaProceso FROM TAI013B_FM_BIT_PROCESO t013 "
			+ "JOIN RADAR_ESTATUS_PROCESO rep ON (t013.id_estatus_proceso = rep.id) "
			+ "WHERE ID_FM_LOTE = #{idLote} ORDER BY FH_CREACION ASC";

	@Select(value = BUSCA_BIT_LOTE_EN_PROCESO)
	public List<FMBitProcesoVO> buscaBitacoraLoteEnProceso(@Param("idLote") Long idLote);

	String GET_LOTE_BY_ID = "SELECT a.id AS archivoId, "

			+ "a.nombre AS fileName, " + "a.fecha_emision AS fechaEmision, "
			+ "a.estatus_proceso_id AS estatusProcesoId, " + "ep.nombre AS estatusProceso, "
			+ "a.en_proceso AS enProceso, " + "a.modificado_por AS modificadoPorId, "
			+ "a.ultima_modificacion AS ultimaModificacion, " + "a.anio_salario_minimo as anioEjercicio, "
			+ "a.archivo_tipo as archivotipo, " + "a.archivo_tipo_proceso AS archivoTipoProceso "
			+ "FROM tai010d_fm_lote a " + "INNER JOIN radar_estatus_proceso ep ON (ep.id = a.estatus_proceso_id) "
			+ "WHERE a.id= #{archivoId}";

	@Select(value = GET_LOTE_BY_ID)
	public DeteccionBatchFinanzasVO cargarFmLoteDeteccion(@Param("archivoId") Long idLote);

	String CANTIDAD_DETECCIONES = "SELECT COUNT(*) as cantidad_canceladas FROM tai011d_FM_DETALLE WHERE   radar_archivo_id = #{radarArchivoId} AND  activo = #{activo}";

	@Select(value = CANTIDAD_DETECCIONES)
	public Integer buscarCantidadDetecciones(@Param("radarArchivoId") Long idLote, @Param("activo") Integer activo);

	String UPDATE_CANCELA_DETECCIONES = "UPDATE TAI011D_FM_DETALLE "
			+ "SET 	activo = 0, proceso_cancelado = 1, complementado =0 "
			+ "WHERE 	radar_archivo_id = #{radarArchivoId}";

	@Update(value = UPDATE_CANCELA_DETECCIONES)
	public void actualizaDetallesLote(@Param("radarArchivoId") Long idLote);

	String CANCELA_LOTE = "UPDATE TAI010D_FM_LOTE " + "SET 	ESTATUS_PROCESO_ID = #{estatusProceId}, "
			+ "EN_PROCESO = 0, CANCELADO = 1, "
			+ "		MODIFICADO_POR = #{empleadoId}, CANTIDAD_CANCELADOS = #{cantidadCancelados}, "
			+ "		CANTIDAD_PROCESADOS = #{cantidadProcesados}, ULTIMA_MODIFICACION = SYSDATE, "
			+ "		MOTIVO_CANCELACION= #{motivoCancelacion} " + "WHERE 	ID = #{archivoRadarId}";

	String INSERT_TAI011D_FM_DETALLE_CANCELACION = "INSERT INTO TAI012D_FM_DETALLE_HIST( "
			+ "	ID,RADAR_ARCHIVO_ID,PLACA,FECHA,HORA,TDSKUID,UT,VELOCIDAD_DETECTADA,FECHA_CREACION,PLACA_SF,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,"
			+ "	CALLE,NUM_EXTERIOR,NUM_INTERIOR,COLONIA,MUNICIPIO,CODIGO_POSTAL,ENTIDAD_FEDERATIVA,MARCA,SUBMARCA,MODELO,TELEFONO,SERIE,MOTOR,"
			+ "	WS_SETRAVI_CONSULTADO,ERROR_WS_SETRAVI,FECHA_CONSULTA_WS_SETRAVI,CENTRO_REPARTO,CP_MODIFICADO_POR,FECHA_CP_MODIFICADO,INFRAC_NUM,"
			+ "	DIAS,PORCENTAJE_DESCUENTO,FECHA_IMPOSICION,FECHA_VENCIMIENTO,IMPORTE_DESCUENTO,IMPORTE_INFRACCION,IMPORTE_DERECHOS,IMPORTE_ACTUALIZACION,"
			+ "	IMPORTE_RECARGOS,IMPORTE_CEROS,IMPORTE_TOTAL,LINEA_CAPTURA,CLAVE_PAGO,COMPLEMENTADO_LC,FECHA_COMPLEMENTADO_LC,UT_CALLE,UT_ENTRE_CALLE,"
			+ "	UT_Y_CALLE,UT_SECTOR,UT_DELEGACION,UT_COLONIA,UT_SECTOR_ID,UT_DELEGACION_ID,COMPLEMENTADO_UT,FECHA_COMPLEMENTADO_UT,COMPLEMENTADO,FECHA_COMPLEMENTADO,"
			+ "	ACTIVO,DUPLICADO,SIN_DATOS_SETRAVI,SIN_CENTRO_REPARTO,SIN_LC,SIN_UT,PROCESO_CANCELADO,ART_ID,OFICIAL_PLACA,OFICIAL_NOMBRE,LIBERADO,FECHA_LIBERADO, ART_NUMERO, ART_FRACCION, ART_PARRAFO, ART_INCISO, ART_UNIDAD_CUENTA) "
			+ "SELECT	ID,RADAR_ARCHIVO_ID,PLACA,FECHA,HORA,TDSKUID,UT,VELOCIDAD_DETECTADA,FECHA_CREACION,PLACA_SF,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,"
			+ "		CALLE,NUM_EXTERIOR,NUM_INTERIOR,COLONIA,MUNICIPIO,CODIGO_POSTAL,ENTIDAD_FEDERATIVA,MARCA,SUBMARCA,MODELO,TELEFONO,SERIE,MOTOR,"
			+ "		WS_SETRAVI_CONSULTADO,ERROR_WS_SETRAVI,FECHA_CONSULTA_WS_SETRAVI,CENTRO_REPARTO,CP_MODIFICADO_POR,FECHA_CP_MODIFICADO,INFRAC_NUM,"
			+ "		DIAS,PORCENTAJE_DESCUENTO,FECHA_IMPOSICION,FECHA_VENCIMIENTO,IMPORTE_DESCUENTO,IMPORTE_INFRACCION,IMPORTE_DERECHOS,IMPORTE_ACTUALIZACION,"
			+ "		IMPORTE_RECARGOS,IMPORTE_CEROS,IMPORTE_TOTAL,LINEA_CAPTURA,CLAVE_PAGO,COMPLEMENTADO_LC,FECHA_COMPLEMENTADO_LC,UT_CALLE,UT_ENTRE_CALLE,"
			+ "		UT_Y_CALLE,UT_SECTOR,UT_DELEGACION, UT_COLONIA,UT_SECTOR_ID,UT_DELEGACION_ID,COMPLEMENTADO_UT,FECHA_COMPLEMENTADO_UT,COMPLEMENTADO,FECHA_COMPLEMENTADO,"
			+ "		ACTIVO,DUPLICADO,SIN_DATOS_SETRAVI,SIN_CENTRO_REPARTO,SIN_LC,SIN_UT,PROCESO_CANCELADO,ART_ID,OFICIAL_PLACA,OFICIAL_NOMBRE,LIBERADO,FECHA_LIBERADO, ART_NUMERO, ART_FRACCION, ART_PARRAFO, ART_INCISO, ART_UNIDAD_CUENTA "
			+ "FROM	TAI011D_FM_DETALLE " + "WHERE 	RADAR_ARCHIVO_ID = #{radarArchivoId}";

	@Insert(INSERT_TAI011D_FM_DETALLE_CANCELACION)
	public Integer insertaHistoricoCompleto(@Param("radarArchivoId") Long radarArchivoId);

	@Update(value = CANCELA_LOTE)
	public void cancelaLote(@Param("archivoRadarId") Long radarArchivoId, @Param("estatusProceId") long estatusProceId,
			@Param("empleadoId") Long empleadoId, @Param("cantidadCancelados") Integer cantidadCancelados,
			@Param("cantidadProcesados") Integer cantidadProcesados,
			@Param("motivoCancelacion") String motivoCancelacion);

	String DELETE_DETECIONES_CANCELACION = "DELETE	FROM TAI011D_FM_DETALLE "
			+ "WHERE 	RADAR_ARCHIVO_ID = #{radarArchivoId}";

	@Delete(value = DELETE_DETECIONES_CANCELACION)
	public void borrarDetecciones(@Param("radarArchivoId") Long idLote);

	String BUSCAR_LOTE_CANCELADO = "SELECT " + "tai010.id AS archivoId, "
			+ "TO_CHAR(tai010.fecha_emision, 'dd/MM/yyyy') AS fechaEmision, "
			+ "tai010.cantidad_cancelados AS archivoTotal, " + "rep.nombre AS estatusProceso "
			+ "FROM tai010d_fm_lote tai010 " + "INNER JOIN radar_estatus_proceso rep "
			+ "ON (rep.id = tai010.estatus_proceso_id) " + "WHERE tai010.en_proceso = 0 and tai010.id = #{archivoId} "
			+ "ORDER BY tai010.id, fechaEmision DESC";

	@Select(BUSCAR_LOTE_CANCELADO)
	public FMLoteResumenVO buscarLoteCancelado(@Param("archivoId") Long idLote);

	String REUTILIZA_DETECCIONES = "UPDATE TAI005D_FM_DETECCIONES SET ID_LOTE = 0, ST_PROCESADO = 0, FH_MODIFICACION = SYSDATE WHERE ID_LOTE = #{idLote}";

	@Update(REUTILIZA_DETECCIONES)
	public void reutilizarDetecciones(@Param("idLote") Long idLote);

	String VALIDA_LOTE_CANCELADO = "SELECT cancelado FROM tai010d_fm_lote WHERE id = #{idLote}";

	@Select(VALIDA_LOTE_CANCELADO)
	public boolean validarProcesoCancelado(@Param("idLote") Long idLote);

	String BUSCAR_ALL_DETECCIONES_BY_CP = "SELECT id AS idDeteccion,  placa AS placa, \r\n" + 
			"			 	TO_CHAR(to_date(fecha,'dd.MM.YY'),'dd/MM/yyyy') AS fecha, hora AS hora, \r\n" + 
			"			 	nvl(apellido_paterno, ' ') || ' ' || nvl(apellido_materno, ' ') || ' ' || nvl(nombre, ' ') AS nombre, \r\n" + 
			"			 	nvl(calle,' ')  || ' ' || nvl(num_exterior,' ') || ' ' || nvl(num_interior,' ') || ' ' || nvl(colonia,' ') AS domicilio, \r\n" + 
			"			 	nvl(codigo_postal, ' ') AS codigoPostal, \r\n" + 
			"			 	trim(to_char(nvl(centro_reparto, 0), '00009')) AS centroReparto, \r\n" + 
			"			 	nvl(municipio, ' ') AS municipio,   	nvl(entidad_federativa, ' ') AS entidadFederativa \r\n" + 
			"			 	FROM tai011d_fm_detalle   WHERE activo = 1\r\n" + 
			"			 	AND codigo_postal =   #{CP}   AND RADAR_ARCHIVO_ID = #{idLote}	ORDER BY centroReparto,codigoPostal,placa";
	@Select(BUSCAR_ALL_DETECCIONES_BY_CP)
	public List<FMDeteccionCP> buscaAllDeteccionesByCP(@Param("CP") String CP, @Param("idLote") Long idLote);
	
	String BUSCAR_ALL_DETECCIONES_RAD_BY_CP = 
			"SELECT "
			+ "	unique id AS idDeteccion, "
			+ "	placa AS placa, "
			+ "	TO_CHAR(to_date(fecha,'dd.MM.YY'),'dd/MM/yyyy') AS fecha, "
			+ "	hora AS hora, "
			+ "	nvl(apellido_paterno, ' ') || ' ' || nvl(apellido_materno, ' ') || ' ' || nvl(nombre, ' ') AS nombre, "
			+ "	nvl(calle,' ')  || ' ' || nvl(num_exterior,' ') || ' ' || nvl(num_interior,' ') || ' ' || nvl(colonia,' ') AS domicilio, "
			+ "	nvl(codigo_postal, ' ') AS codigoPostal, "
			+ "	trim(to_char(nvl(centro_reparto, 0), '00009')) AS centroReparto, "
			+ " nvl(municipio, ' ') AS municipio, "
			+ "	nvl(entidad_federativa, ' ') AS entidadFederativa "
			+ "FROM RADAR_ARCHIVO_DETALLE "
			+ "WHERE "
			+ "	duplicado = 0 "
			+ "	AND codigo_postal = #{CP} "
			+ "	AND (CP_MODIFICADO_POR IS NULL OR CP_MODIFICADO_POR != -3) "
			+ "	AND RADAR_ARCHIVO_ID = #{idLote}";
	@Select(BUSCAR_ALL_DETECCIONES_RAD_BY_CP)
	public List<FMDeteccionCP> buscaAllDeteccionesRADByCP(@Param("CP") String CP, @Param("idLote") Long idLote);
	
	/*
	String BUSCAR_DETECCIONES_CR_VALIDAS = "SELECT id AS idDeteccion, " + "	placa AS placa, "
			+ "	TO_CHAR(to_date(fecha,'dd.MM.YY'),'dd/MM/yyyy') AS fecha, " + "	hora AS hora, "
			+ "	nvl(apellido_paterno, ' ') || ' ' || nvl(apellido_materno, ' ') || ' ' || nvl(nombre, ' ') AS nombre, "
			+ "	nvl(calle,' ')  || ' ' || nvl(num_exterior,' ') || ' ' || nvl(num_interior,' ') || ' ' || nvl(colonia,' ') AS domicilio, "
			+ "	nvl(codigo_postal, ' ') AS codigoPostal, "
			+ "	trim(to_char(nvl(centro_reparto, 0), '00009')) AS centroReparto, "
			+ "	nvl(municipio, ' ') AS municipio, " + "	nvl(entidad_federativa, ' ') AS entidadFederativa "
			+ "	FROM tai011d_fm_detalle   WHERE activo = 1 " + "	AND sin_centro_reparto = 0 "
			+ "	AND radar_archivo_id =  #{idLote}  " + "	ORDER BY centroReparto,codigoPostal,placa";
	@Select(BUSCAR_DETECCIONES_CR_VALIDAS)
	public List<FMDeteccionCP> buscaDeteccionesCPValidas(@Param("idLote") Long idLote);
	*/
	String BUSCAR_DETECCIONES_CR_VALIDAS = "SELECT  COUNT(id) AS idDeteccion, "
			+ "nvl(codigo_postal, ' ') AS codigoPostal, trim(to_char(nvl(centro_reparto, 0), '00009')) AS centroReparto, "
			+ "nvl(municipio, ' ') AS municipio, nvl(entidad_federativa, ' ') AS entidadFederativa "
			+ "FROM TAI011D_FM_DETALLE "
			+ "WHERE activo = 1	AND sin_centro_reparto = 0 AND radar_archivo_id = #{idLote} "
			+ "GROUP BY centro_reparto,codigo_postal,municipio,entidad_federativa";
	@Select(BUSCAR_DETECCIONES_CR_VALIDAS)
	public List<FMDeteccionCPV2> buscaDeteccionesCPValidas(@Param("idLote") Long idLote);
	
	String BUSCAR_DETECCIONES_RAD_CR_VALIDAS = "SELECT  COUNT(id) AS idDeteccion, "
			+ "nvl(codigo_postal, ' ') AS codigoPostal, "
			+ "nvl(entidad_federativa, ' ') AS entidadFederativa "
			+ "FROM RADAR_ARCHIVO_DETALLE "
			+ "WHERE duplicado = 0 AND radar_archivo_id = #{idLote} "
			+ "GROUP BY codigo_postal,entidad_federativa";
	@Select(BUSCAR_DETECCIONES_RAD_CR_VALIDAS)
	public List<FMDeteccionCPV2> buscaDeteccionesRADCPValidas(@Param("idLote") Long idLote);

	/*String BUSCAR_DETECCIONES_CR_INVALIDAS = "SELECT id AS idDeteccion, " + "	placa AS placa, "
			+ "	TO_CHAR(to_date(fecha,'dd.MM.YY'),'dd/MM/yyyy') AS fecha, " + "	hora AS hora, "
			+ "	nvl(apellido_paterno, ' ') || ' ' || nvl(apellido_materno, ' ') || ' ' || nvl(nombre, ' ') AS nombre, "
			+ "	nvl(calle,' ')  || ' ' || nvl(num_exterior,' ') || ' ' || nvl(num_interior,' ') || ' ' || nvl(colonia,' ') AS domicilio, "
			+ "	nvl(codigo_postal, ' ') as codigoPostal, "
			+ "	trim(to_char(nvl(centro_reparto, 0), '00009')) AS centroReparto, "
			+ "	nvl(municipio, ' ') as municipio, " + "	nvl(entidad_federativa, ' ') as entidadFederativa "
			+ "	FROM tai011d_fm_detalle   WHERE activo = 0 " + "	AND sin_centro_reparto = 1 "
			+ "	AND radar_archivo_id = #{idLote} " + "	ORDER BY centroReparto,codigoPostal,placa";
	@Select(BUSCAR_DETECCIONES_CR_INVALIDAS)
	public List<FMDeteccionCP> buscaDeteccionesCPInvalidas(@Param("idLote") Long idLote);
	*/
	String BUSCAR_DETECCIONES_CR_INVALIDAS = "SELECT  COUNT(id) AS idDeteccion, nvl(codigo_postal, ' ') AS codigoPostal, "
			+ "trim(to_char(nvl(centro_reparto, 0), '00009')) AS centroReparto, nvl(municipio, ' ') AS municipio, "
			+ "ENTIDAD_FEDERATIVA AS municipio, nvl(entidad_federativa, ' ') AS entidadFederativa "
			+ "FROM TAI011D_FM_DETALLE "
			+ "WHERE activo = 0	AND sin_centro_reparto = 1 AND radar_archivo_id = #{idLote} "
			+ "GROUP BY centro_reparto,codigo_postal,municipio,entidad_federativa";

	@Select(BUSCAR_DETECCIONES_CR_INVALIDAS)
	public List<FMDeteccionCPV2> buscaDeteccionesCPInvalidas(@Param("idLote") Long idLote);

	String ACTUALIZA_LOTE_CP = "UPDATE tai010d_fm_lote SET estatus_proceso_id = #{estatusProcesoId}, en_proceso = #{enProceso}, modificado_por = #{idUsuario}, "
			+ " ultima_modificacion = systimestamp WHERE id = #{idLote}";

	@Select(ACTUALIZA_LOTE_CP)
	public void actualizarFmLoteReasignadoCP(@Param("idLote") Long idLote,
			@Param("estatusProcesoId") int estatusProcesoId, @Param("idUsuario") Long idUsuario,
			@Param("enProceso") int enProceso);

	String INSERTA_BIT_PROCESO = "INSERT into tai013b_fm_bit_proceso values(#{idLote}, #{estatusProcesoId}, SYSDATE, #{idUsuario})";

	@Insert(INSERTA_BIT_PROCESO)
	public void insertaBitacoraProceso(@Param("idLote") Long idLote, @Param("idUsuario") Long idUsuario,
			@Param("estatusProcesoId") Integer estatusProcesoId);

	
	@Insert(TAI_BIT_CANCELACION)
	public void taiBitCancela(@Param("concepto") Integer concepto, @Param("idLote") Long idLote,
			@Param("idUsuario") Long idUsuario);
	/*
	 String HABILITA_FM = "UPDATE tai011d_fm_detalle " + "SET activo = 1, " + "sin_centro_reparto = 0, "
			+ "codigo_postal = #{cp}, " + "cp_modificado_por = #{idUsuario}, " + "fecha_cp_modificado = SYSDATE "
			+ "WHERE id = #{idCp} " + "AND radar_archivo_id = #{idLote}";

	@Select(HABILITA_FM)
	public void habilitaFm(@Param("idLote") Long idLote, @Param("idUsuario") Long idUsuario, @Param("idCp") Long idCp,
			@Param("cp") String cp);
	 */
	String HABILITA_FM = "UPDATE tai011d_fm_detalle " + "SET activo = 1, " + "sin_centro_reparto = 0, centro_reparto=0, "
			+ "codigo_postal = #{cp}, " + "cp_modificado_por = #{idUsuario}, " + "fecha_cp_modificado = SYSDATE "
			+ "WHERE codigo_postal = 0 AND centro_reparto = #{idCp} AND radar_archivo_id = #{idLote}";

	@Select(HABILITA_FM)
	public void habilitaFm(@Param("idLote") Long idLote, @Param("idUsuario") Long idUsuario, @Param("idCp") Long idCp,
			@Param("cp") String cp);

	/*
	String DESHABILITA_FM = "UPDATE tai011d_fm_detalle " + "SET activo = 0, " + "sin_centro_reparto = 1, "
			+ "codigo_postal = 0, " + "centro_reparto = 0, " + "cp_modificado_por = #{idUsuario}, "
			+ "fecha_cp_modificado = SYSDATE " + "WHERE id = #{idCp} " + "AND radar_archivo_id = #{idLote} ";
	@Select(DESHABILITA_FM)
	public void deshabilitaFm(@Param("idLote") Long idLote, @Param("idUsuario") Long idUsuario,
			@Param("idCp") Long idCp);
	*/
	String DESHABILITA_FM = "UPDATE tai011d_fm_detalle " + "SET activo = 0, " + "sin_centro_reparto = 1, "
			+ "codigo_postal = 0, " + "cp_modificado_por = #{idUsuario}, "
			+ "fecha_cp_modificado = SYSDATE " + "WHERE codigo_postal = #{idCp} " + "AND radar_archivo_id = #{idLote} ";
	@Select(DESHABILITA_FM)
	public void deshabilitaFm(@Param("idLote") Long idLote, @Param("idUsuario") Long idUsuario,
			@Param("idCp") Long idCp);
	

//	String consultaDetecciones = "SELECT (SELECT COUNT(*) FROM TAI005D_FM_DETECCIONES TAI005 " 
//		                         +" WHERE TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION "
//		                         +" AND TAI005.ST_ACTIVO = 1 AND ST_PROCESADO = 0 AND TAI005.ST_CANCELADO = 0 AND TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')) >=#{fechaInicio}"
//		                         +" AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0  THEN #{origenPlaca}"
//		                         +"     								   ELSE TAI005.ID_ORIGENPLACA END) AS TOTAL,"
//		                         +" TAI009.NB_DISPOSITIVO AS RADAR,NB_DISPOSITIVO_DETECCION AS TIPO,TAI007.ID_ARCHIVO_TIPO_FORA AS MARCA,"
//		                         +" TAI009.ID_MARCA_DISPOSITIVO_DET AS ID FROM TAI007C_FM_TIPO_DETECCIONES TAI007 " 
//		                         +" LEFT JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET) "
//		                         +" WHERE TAI007.ID_TIPO_DETECCION IN(SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES "
//		                         +" WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0 "
//		                         +"                                       THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END"
//		                         +" AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0 THEN #{tipoDeteccion} ELSE  ID_ARCHIVO_TIPO_FORA END)"
//		                         +" GROUP BY TAI007.ID_TIPO_DETECCION, TAI009.NB_DISPOSITIVO, NB_DISPOSITIVO_DETECCION,TAI007.ID_ARCHIVO_TIPO_FORA ,TAI009.ID_MARCA_DISPOSITIVO_DET "
//		                         +" ORDER BY TAI007.ID_ARCHIVO_TIPO_FORA ASC,TAI009.ID_MARCA_DISPOSITIVO_DET ASC";
//	
//	@Select(value = consultaDetecciones)
//	@Options(statementType = StatementType.CALLABLE)
//	public List<FMConsultaDeteccionesVO> consultaDetecciones(@Param("tipoDeteccion") int tipoDeteccion,
//			                                                 @Param("tipoRadar") int tipoRadar, 
//			                                                 @Param("origenPlaca") int origenPlaca,
//			                                                 @Param("fechaInicio") String fechaInicio)throws PersistenceException;
	
	String consultaDeteccionesFecha2 = "SELECT (SELECT COUNT(*) FROM TAI005D_FM_DETECCIONES TAI005 " 
            +" WHERE TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION "
            +" AND TAI005.ST_ACTIVO = 1 AND ST_PROCESADO = 0 AND TAI005.ST_CANCELADO = 0 AND TAI005.FH_CREACION >=#{fechaInicio}"                       /*** CAMPO DE FECHA **/
            +" AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0  THEN #{origenPlaca}"
            +"     								   ELSE TAI005.ID_ORIGENPLACA END) AS TOTAL,"
            +" TAI009.NB_DISPOSITIVO AS RADAR,NB_DISPOSITIVO_DETECCION AS TIPO,TAI007.ID_ARCHIVO_TIPO_FORA AS MARCA,"
            +" TAI009.ID_MARCA_DISPOSITIVO_DET AS ID FROM TAI007C_FM_TIPO_DETECCIONES TAI007 " 
            +" LEFT JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET) "
            +" WHERE TAI007.ID_TIPO_DETECCION IN(SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES "
            +" WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0 "
            +"                                       THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END"
            +" AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0 THEN #{tipoDeteccion} ELSE  ID_ARCHIVO_TIPO_FORA END)"
            +" GROUP BY TAI007.ID_TIPO_DETECCION, TAI009.NB_DISPOSITIVO, NB_DISPOSITIVO_DETECCION,TAI007.ID_ARCHIVO_TIPO_FORA ,TAI009.ID_MARCA_DISPOSITIVO_DET "
            +" ORDER BY NB_DISPOSITIVO_DETECCION ASC";

@Select(value = consultaDeteccionesFecha2)
@Options(statementType = StatementType.CALLABLE)
public List<FMConsultaDeteccionesVO> consultaDeteccionesFecha2(@Param("tipoDeteccion") int tipoDeteccion,
                                                               @Param("tipoRadar") int tipoRadar, 
                                                               @Param("origenPlaca") int origenPlaca,
                                                               @Param("fechaInicio") String fechaInicio)throws PersistenceException;

	/* Consulta detecciones historicas ,desde dos meses atras al principipo */

//	String consultaDeteccionesHistoricasTipoFecha1 = "SELECT (SELECT COUNT(*) FROM TAI005D_FM_DETECCIONES TAI005 " 
//		                         +" WHERE TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION "
//		                         +" AND TAI005.ST_ACTIVO = 1 AND TAI005.ST_PROCESADO = 0  AND TAI005.ST_CANCELADO = 0 "
//		                         +" AND TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')) <#{fechaInicio}" 
//		                         +" AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0  THEN #{origenPlaca}"
//		                         +"     								   ELSE TAI005.ID_ORIGENPLACA END) AS TOTAL,"
//		                         +" TAI009.NB_DISPOSITIVO AS RADAR,NB_DISPOSITIVO_DETECCION AS TIPO,TAI007.ID_ARCHIVO_TIPO_FORA AS MARCA,"
//		                         +" TAI009.ID_MARCA_DISPOSITIVO_DET AS ID "
//		                         +" FROM TAI007C_FM_TIPO_DETECCIONES TAI007 " 
//		                         +" LEFT JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET) "
//		                         +" WHERE TAI007.ID_TIPO_DETECCION IN(SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES "
//		                         +" WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0 "
//		                         +"                                       THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END"
//		                         +" AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0 THEN #{tipoDeteccion} ELSE  ID_ARCHIVO_TIPO_FORA END)"
//		                         +" GROUP BY TAI007.ID_TIPO_DETECCION, TAI009.NB_DISPOSITIVO, NB_DISPOSITIVO_DETECCION,TAI007.ID_ARCHIVO_TIPO_FORA ,TAI009.ID_MARCA_DISPOSITIVO_DET "
//		                         +"ORDER BY TAI007.ID_ARCHIVO_TIPO_FORA ASC,TAI009.ID_MARCA_DISPOSITIVO_DET ASC";
//	
//	@Select(value = consultaDeteccionesHistoricasTipoFecha1)
//	@Options(statementType = StatementType.CALLABLE)
//	public List<FMConsultaDeteccionesVO> consultaDeteccionesHistoricas(@Param("tipoDeteccion") int tipoDeteccion,
//																	   @Param("tipoRadar") int tipoRadar, 
//																	   @Param("origenPlaca") int origenPlaca,
//																	   @Param("fechaInicio") String fechaInicio) throws PersistenceException;
	
	

	
	String consultaDeteccionesHistoricasTipoFecha2 = "SELECT (SELECT COUNT(*) FROM TAI005D_FM_DETECCIONES TAI005 "   
            +" WHERE TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION "
            +" AND TAI005.ST_ACTIVO = 1 AND ST_PROCESADO = 0 AND TAI005.ST_CANCELADO = 0 AND TAI005.FH_CREACION  < #{fechaInicio}"   
            +" AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0  THEN #{origenPlaca}"
            +"     								   ELSE TAI005.ID_ORIGENPLACA END) AS TOTAL,"
            +" TAI009.NB_DISPOSITIVO AS RADAR,NB_DISPOSITIVO_DETECCION AS TIPO,TAI007.ID_ARCHIVO_TIPO_FORA AS MARCA,"
            +" TAI009.ID_MARCA_DISPOSITIVO_DET AS ID FROM TAI007C_FM_TIPO_DETECCIONES TAI007 " 
            +" LEFT JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET) "
            +" WHERE TAI007.ID_TIPO_DETECCION IN(SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES "
            +" WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0 "
            +"                                       THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END"
            +" AND ID_ARCHIVO_TIPO_FORA =CASE WHEN #{tipoDeteccion} <> 0 THEN #{tipoDeteccion} ELSE  ID_ARCHIVO_TIPO_FORA END)"
        
            +" GROUP BY TAI007.ID_TIPO_DETECCION, TAI009.NB_DISPOSITIVO, NB_DISPOSITIVO_DETECCION,TAI007.ID_ARCHIVO_TIPO_FORA ,TAI009.ID_MARCA_DISPOSITIVO_DET "
            +" ORDER BY NB_DISPOSITIVO_DETECCION ASC ";

@Select(value = consultaDeteccionesHistoricasTipoFecha2)
@Options(statementType = StatementType.CALLABLE)
public List<FMConsultaDeteccionesVO> consultaDeteccionesHistoricasFecha2(@Param("tipoDeteccion") int tipoDeteccion,
												   @Param("tipoRadar") int tipoRadar, 
												   @Param("origenPlaca") int origenPlaca,
												   @Param("fechaInicio") String fechaInicio) throws PersistenceException;

	/*
	 * Cosnulta detalle historico seleccionando radar especifico Fernando
	 * Octavio Sanchez Chavez
	 */

	String consultaDeteccionesHistoricasDetalle = "SELECT COUNT(CD_PLACA) AS TOTAL ,"   
			+" TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD')AS DIA,"
			+ " TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM')AS MES , "
			+ " TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy')AS ANIO, "
			+ " TAI009.NB_DISPOSITIVO  as RADAR , " 
			+ " TAI009.ID_MARCA_DISPOSITIVO_DET AS ID_RADAR,"
			+"  TAI007.ID_ARCHIVO_TIPO_FORA AS tipoDeteccion , "
			+"  TAI007.NB_DISPOSITIVO_DETECCION AS nbDeteccion"
			+"   FROM TAI005D_FM_DETECCIONES TAI005 "
			+ "    JOIN TAI007C_FM_TIPO_DETECCIONES TAI007 ON (TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION) "
			+ "    JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET)  "
			+ "     WHERE TAI007.ID_TIPO_DETECCION IN (SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES   "
			+ "                                               WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0   "
			+ "                                               THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END   " 
			+ " AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0   "
			+ "                                 THEN #{tipoDeteccion} ELSE ID_ARCHIVO_TIPO_FORA END )  "
			+ " AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
			+ "                                  THEN #{origenPlaca} ELSE TAI005.ID_ORIGENPLACA END   "
			+ " AND TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')) <#{fechaInicio} " 
			+ " AND TAI005.ST_CANCELADO=0 and TAI005.ST_ACTIVO=1 AND TAI005.ST_PROCESADO = 0"
			+ " GROUP BY  TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD'), "
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM'),"
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy'),"
			+ "           TAI009.NB_DISPOSITIVO ,"
			+ " 		  TAI009.ID_MARCA_DISPOSITIVO_DET,"
			+ "           TAI007.ID_ARCHIVO_TIPO_FORA, TAI007.NB_DISPOSITIVO_DETECCION," 
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'/yyyy') "
			+ " ORDER BY  TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'/yyyy')ASC ,"
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM'),"
			+"            TAI007.NB_DISPOSITIVO_DETECCION ASC";
	
	@Select(value = consultaDeteccionesHistoricasDetalle)
	@Options(statementType = StatementType.CALLABLE)
	List<FMConsultaDeteccionesDetalleHistoricoVO> consultaDeteccionesDetalleHistorica(
			@Param("tipoDeteccion") int tipoDeteccion, @Param("tipoRadar") int tipoRadar,
			@Param("origenPlaca") int origenPlaca, @Param("fechaInicio") String fechaInicio) throws PersistenceException;
	

	String TAI_BIT_MOD_CP = "INSERT INTO TAI_BITACORA_CAMBIOS(COMPONENTE_ID, CONCEPTO_ID, VALOR_ORIGINAL, VALOR_FINAL, FECHA_CREACION, CREADO_POR, ID_REGISTRO, ORIGEN, FECHA_ENVIO_SF, ID_REGISTRO_DESCRIPCION) "
						  + "VALUES(39, #{concepto}, (SELECT CODIGO_POSTAL FROM tai011d_fm_detalle WHERE id = #{idCp}), #{newCp}, SYSDATE, #{idUsuario}, #{idLote}, 'W', null, null)";
	
	String consultaDeteccionesHistoricasDetalleFecha2 = "SELECT COUNT(TAI005.CD_PLACA) AS TOTAL ,"   
			+" TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY')),'DD')AS DIA,"
			+ " TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY')),'MM')AS MES , "
			+ " TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY')),'yyyy')AS ANIO, "
			+ " TAI009.NB_DISPOSITIVO  as RADAR , " 
			+ " TAI009.ID_MARCA_DISPOSITIVO_DET AS ID_RADAR,"
			+"  TAI007.ID_ARCHIVO_TIPO_FORA AS tipoDeteccion , "
			+"  TAI007.NB_DISPOSITIVO_DETECCION AS nbDeteccion"
			+"   FROM TAI005D_FM_DETECCIONES TAI005 "
			+ "    JOIN TAI007C_FM_TIPO_DETECCIONES TAI007 ON (TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION) "
			+ "    JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET)  "
			+ "     WHERE TAI007.ID_TIPO_DETECCION IN (SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES   "
			+ "                                               WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0   "
			+ "                                               THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END   " 
			+ " AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0   "
			+ "                                 THEN #{tipoDeteccion} ELSE ID_ARCHIVO_TIPO_FORA END )  "
			+ " AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
			+ "                                  THEN #{origenPlaca} ELSE TAI005.ID_ORIGENPLACA END   "
			+ " AND TAI005.FH_CREACION <#{fechaInicio} "             
			 +" AND TAI005.ST_CANCELADO=0 AND TAI005.ST_ACTIVO=1 AND TAI005.ST_PROCESADO = 0"
			+ " GROUP BY  TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY')),'DD'), "
			+ "           TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY')),'MM'),"
			+ "           TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY')),'yyyy'),"
			+ "           TAI009.NB_DISPOSITIVO ,"
			+ " 		  TAI009.ID_MARCA_DISPOSITIVO_DET,"
			+"            TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'/MM/yyyy')"
			+" ORDER BY   TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'/MM/yyyy')";

	@Select(value = consultaDeteccionesHistoricasDetalleFecha2)
	@Options(statementType = StatementType.CALLABLE)
	List<FMConsultaDeteccionesDetalleHistoricoVO> consultaDeteccionesDetalleHistoricaFecha2(
			@Param("tipoDeteccion") int tipoDeteccion, @Param("tipoRadar") int tipoRadar,
			@Param("origenPlaca") int origenPlaca, @Param("fechaInicio") String fechaInicio) throws PersistenceException;
	
	
	String TAI_BIT_CANCELACION = "INSERT INTO TAI_BITACORA_CAMBIOS(COMPONENTE_ID, CONCEPTO_ID, VALOR_ORIGINAL, VALOR_FINAL, FECHA_CREACION, CREADO_POR, ID_REGISTRO, ORIGEN, FECHA_ENVIO_SF, ID_REGISTRO_DESCRIPCION) "
			  				   + "VALUES(39, #{concepto}, null, (SELECT NOMBRE FROM tai010d_fm_lote WHERE id = #{idLote}), SYSDATE, #{idUsuario}, #{idLote}, 'W', null, null)";
	
	
	/** 
	 * consulta para traer el detalle por dia ,de las detecciones 
	 * */
	String consultaDeteccionesHistoricasDetalleDiario = "SELECT COUNT(CD_PLACA) AS TOTAL ,"
			+" TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD')AS DIA,"
			+ " TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM')AS MES , "
			+ " TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy')AS ANIO, "
			+"  TO_CHAR(TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS'))) AS FECHACREACION,"
			+ " TAI009.NB_DISPOSITIVO  as RADAR , " 
			+ " TAI009.ID_MARCA_DISPOSITIVO_DET AS ID_RADAR,"
			+"  TAI007.NB_DISPOSITIVO_DETECCION AS nbDeteccion " 
			+"   FROM TAI005D_FM_DETECCIONES TAI005 "
			+ "    JOIN TAI007C_FM_TIPO_DETECCIONES TAI007 ON (TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION) "
			+ "    JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET)  "
			+ "     WHERE TAI007.ID_TIPO_DETECCION IN (SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES   "
			+ "                                               WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0   "
			+ "                                               THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END   " 
			+ " AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0   "
			+ "                                 THEN #{tipoDeteccion} ELSE ID_ARCHIVO_TIPO_FORA END )  "
			+ " AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
			+ "                                  THEN #{origenPlaca} ELSE TAI005.ID_ORIGENPLACA END   "
			+ " AND TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')) BETWEEN #{fechaInicio} AND  #{fechaFin} "
			 +" AND TAI005.ST_CANCELADO=0 AND TAI005.ST_ACTIVO=1 AND TAI005.ST_PROCESADO = 0"
			+ " GROUP BY  TO_CHAR(TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS'))) ,"
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD'), "
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM'),"
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy'),"
			+ "           TAI009.NB_DISPOSITIVO ,"
			+ " 		  TAI009.ID_MARCA_DISPOSITIVO_DET,"
			+ "           TAI007.ID_TIPO_DETECCION ,TAI007.NB_DISPOSITIVO_DETECCION" 
			+ " ORDER BY  TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD') ASC";
	

	@Select(value = consultaDeteccionesHistoricasDetalleDiario)
	@Options(statementType = StatementType.CALLABLE)
	List<FMConsultaDeteccionesAgrupacionMes> consultaDeteccionesDetalleDiario(@Param("tipoDeteccion") int tipoDeteccion, 
			  																	   @Param("tipoRadar") int tipoRadar,
			  																	   @Param("origenPlaca") int origenPlaca, 
			  																	   @Param("fechaInicio") String fechaInicio,
			  																	   @Param("fechaFin")String fechaFin) throws PersistenceException;
	
	
	String consultaDeteccionesHistoricasDetalleDiarioFecha2 = "SELECT COUNT(CD_PLACA) AS TOTAL ,"
			+"  TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY HH24:MI:SS')),'DD')AS DIA,"
			+ " TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY HH24:MI:SS')),'MM')AS MES , "
			+ " TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY HH24:MI:SS')),'yyyy')AS ANIO, "
			+"  TO_CHAR(TRUNC(TO_DATE(TAI005.FH_CREACION,'DD-MM-YYYY HH24:MI:SS'))) AS FECHACREACION,"
			+ " TAI009.NB_DISPOSITIVO  as RADAR , " 
			+ " TAI009.ID_MARCA_DISPOSITIVO_DET AS ID_RADAR,"
			+"  TAI007.NB_DISPOSITIVO_DETECCION AS nbDeteccion " 
			+"   FROM TAI005D_FM_DETECCIONES TAI005 "
			+ "    JOIN TAI007C_FM_TIPO_DETECCIONES TAI007 ON (TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION) "
			+ "    JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET)  "
			+ "     WHERE TAI007.ID_TIPO_DETECCION IN (SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES   "
			+ "                                               WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0   "
			+ "                                               THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END   " 
			+ " AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0   "
			+ "                                 THEN #{tipoDeteccion} ELSE ID_ARCHIVO_TIPO_FORA END )  "
			+ " AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
			+ "                                  THEN #{origenPlaca} ELSE TAI005.ID_ORIGENPLACA END   "
			+ "  AND   TAI005.FH_CREACION BETWEEN #{fechaInicio} AND  #{fechaFin} "
			 +" AND TAI005.ST_CANCELADO=0 AND TAI005.ST_ACTIVO=1 AND TAI005.ST_PROCESADO = 0"
			+ " GROUP BY  TO_CHAR(TRUNC(TO_DATE(TAI005.FH_CREACION,'DD-MM-YYYY HH24:MI:SS'))) ,"
			+ "           TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY HH24:MI:SS')),'DD'), "
			+ "           TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY HH24:MI:SS')),'MM'),"
			+ "           TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY HH24:MI:SS')),'yyyy'),"
			+ "           TAI009.NB_DISPOSITIVO ,"
			+ " 		  TAI009.ID_MARCA_DISPOSITIVO_DET,"
			+ "           TAI007.ID_TIPO_DETECCION ,TAI007.NB_DISPOSITIVO_DETECCION" 
			+ " ORDER BY  TO_CHAR(TRUNC(to_date(TAI005.FH_CREACION,'DD/MM/YYYY HH24:MI:SS')),'DD') ASC ";
			

	@Select(value = consultaDeteccionesHistoricasDetalleDiarioFecha2)
	@Options(statementType = StatementType.CALLABLE)
	List<FMConsultaDeteccionesAgrupacionMes> consultaDeteccionesDetalleDiarioFecha2(@Param("tipoDeteccion") int tipoDeteccion, 
			  																	   @Param("tipoRadar") int tipoRadar,
			  																	   @Param("origenPlaca") int origenPlaca, 
			  																	   @Param("fechaInicio") String fechaInicio,
			  																	   @Param("fechaFin")String fechaFin) throws PersistenceException;
	
//	/*Consulta detecciones detalle por año agrupa por años */
//	String consultaDeteccionesActualesDetalles= "SELECT COUNT(CD_PLACA) AS TOTAL ,"   
//			+" TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD')AS DIA,"
//			+ " TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM')AS MES , "
//			+ " TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy')AS ANIO, "
//			+ " TAI009.NB_DISPOSITIVO  as RADAR , " 
//			+ " TAI009.ID_MARCA_DISPOSITIVO_DET AS ID_RADAR,"
//			+"  TAI007.ID_ARCHIVO_TIPO_FORA AS tipoDeteccion , "
//			+"  TAI007.NB_DISPOSITIVO_DETECCION AS nbDeteccion"
//			+"   FROM TAI005D_FM_DETECCIONES TAI005 "
//			+ "    JOIN TAI007C_FM_TIPO_DETECCIONES TAI007 ON (TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION) "
//			+ "    JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET)  "
//			+ "     WHERE TAI007.ID_TIPO_DETECCION IN (SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES   "
//			+ "                                               WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0   "
//			+ "                                               THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END   " 
//			+ " AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0   "
//			+ "                                 THEN #{tipoDeteccion} ELSE ID_ARCHIVO_TIPO_FORA END )  "
//			+ " AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
//			+ "                                  THEN #{origenPlaca} ELSE TAI005.ID_ORIGENPLACA END   "
//			+ " AND TRUNC(TO_DATE(TAI005.CD_FECHAHORA,'DD-MM-YYYY HH24:MI:SS')) >= #{fechaInicio} " 
//			+ " AND TAI005.ST_CANCELADO=0 AND TAI005.ST_ACTIVO=1 AND TAI005.ST_PROCESADO = 0"
//			+ " GROUP BY  TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD'), "
//			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM'),"
//			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy'),"
//			+ "           TAI009.NB_DISPOSITIVO ,"
//			+ " 		  TAI009.ID_MARCA_DISPOSITIVO_DET,"
//			+ "           TAI007.ID_ARCHIVO_TIPO_FORA, TAI007.NB_DISPOSITIVO_DETECCION," 
//			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'/yyyy') "
//			+ " ORDER BY  TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'/yyyy')ASC ,"
//			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM'),"
//			+"            TAI007.NB_DISPOSITIVO_DETECCION ASC";
//
//	@Select(value = consultaDeteccionesActualesDetalles)
//	@Options(statementType = StatementType.CALLABLE)
//	List<FMConsultaDeteccionesDetalleHistoricoVO> consultaDeteccionesDetalleActuales(
//			@Param("tipoDeteccion") int tipoDeteccion, @Param("tipoRadar") int tipoRadar,
//			@Param("origenPlaca") int origenPlaca, @Param("fechaInicio") String fechaInicio) throws PersistenceException;
	
	
	String consultaDeteccionesDetalleActualFecha2 = "SELECT COUNT(CD_PLACA) AS TOTAL ,"   
			+" TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD')AS DIA,"
			+ " TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM')AS MES , "
			+ " TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy')AS ANIO, "
			+ " TAI009.NB_DISPOSITIVO  as RADAR , " 
			+ " TAI009.ID_MARCA_DISPOSITIVO_DET AS ID_RADAR,"
			+"  TAI007.ID_ARCHIVO_TIPO_FORA AS tipoDeteccion , "
			+"  TAI007.NB_DISPOSITIVO_DETECCION AS nbDeteccion"
			+"   FROM TAI005D_FM_DETECCIONES TAI005 "
			+ "    JOIN TAI007C_FM_TIPO_DETECCIONES TAI007 ON (TAI005.ID_TIPO_DETECCION = TAI007.ID_TIPO_DETECCION) "
			+ "    JOIN TAI009C_FM_MARCA_DISPOSITIVO TAI009 ON (TAI009.ID_MARCA_DISPOSITIVO_DET = TAI007.ID_MARCA_DISPOSITIVO_DET)  "
			+ "     WHERE TAI007.ID_TIPO_DETECCION IN (SELECT ID_TIPO_DETECCION FROM TAI007C_FM_TIPO_DETECCIONES   "
			+ "                                               WHERE ID_MARCA_DISPOSITIVO_DET = CASE WHEN #{tipoRadar} <> 0   "
			+ "                                               THEN #{tipoRadar} ELSE ID_MARCA_DISPOSITIVO_DET END   " 
			+ " AND ID_ARCHIVO_TIPO_FORA = CASE WHEN #{tipoDeteccion} <> 0   "
			+ "                                 THEN #{tipoDeteccion} ELSE ID_ARCHIVO_TIPO_FORA END )  "
			+ " AND TAI005.ID_ORIGENPLACA = CASE WHEN #{origenPlaca} = 1 OR #{origenPlaca} = 0 "
			+ "                                  THEN #{origenPlaca} ELSE TAI005.ID_ORIGENPLACA END   "
			+ " AND TAI005.FH_CREACION >=#{fechaInicio} " 
			+ " AND TAI005.ST_CANCELADO=0 AND TAI005.ST_ACTIVO=1 AND TAI005.ST_PROCESADO = 0"
			+ " GROUP BY  TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'DD'), "
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM'),"
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'yyyy'),"
			+ "           TAI009.NB_DISPOSITIVO ,"
			+ " 		  TAI009.ID_MARCA_DISPOSITIVO_DET,"
			+ "           TAI007.ID_ARCHIVO_TIPO_FORA, TAI007.NB_DISPOSITIVO_DETECCION," 
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'/yyyy') "
			+ " ORDER BY  TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'/yyyy')ASC ,"
			+ "           TO_CHAR(TRUNC(to_date(TAI005.CD_FECHAHORA,'DD/MM/YYYY HH24:MI:SS')),'MM'),"
			+"            TAI007.NB_DISPOSITIVO_DETECCION ASC";

	@Select(value = consultaDeteccionesDetalleActualFecha2)
	@Options(statementType = StatementType.CALLABLE)
	List<FMConsultaDeteccionesDetalleHistoricoVO> consultaDeteccionesDetalleActualFecha2(
		@Param("tipoDeteccion") int tipoDeteccion, 
		@Param("tipoRadar") int tipoRadar,
		@Param("origenPlaca") int origenPlaca, 
		@Param("fechaInicio") String fechaInicio
	) throws PersistenceException;
	
	/**
	 * 
	 * fin consulta detalle diarios de detecciones */
	
	String CONSULTA_ORIGEN_PLACA = 
		"SELECT "
		+ "	ID_ORIGEN_PLACA_VEHICULAR AS idOrigenPlacaVehicular, "
		+ "	NB_ORIGEN_PLACA_VEHICULAR AS nbOrigenPlacaVehicular "
		+ "FROM TAI032C_FC_ORIGEN_PLACA_VEHIC "
		+ "WHERE "
		+ "	ST_ACTIVO = 1 "
		//+ "	--AND ID_ORIGEN_PLACA_VEHICULAR != 2 "
		+ "	AND ID_ORIGEN_PLACA_VEHICULAR != CASE "
		+ "		WHEN #{todos} <> 1 "
		+ "			THEN 2 "
		+ "		ELSE -1 "
		+ "	END "
		+ "ORDER BY ID_ORIGEN_PLACA_VEHICULAR ASC";

	@Select(CONSULTA_ORIGEN_PLACA)
	public List<FMOrigenPlacaFCVO> consultaOrigenPlacaFC(
			@Param("todos") Integer todos);
	
	String CONSULTA_TIPO_ARCHIVO_FC =
		"SELECT "
		+ "	ID_TIPO_ARCHIVO_FCIVICA AS idTipoArchivoFCivica, "
		+ "	NB_TIPO_ARCHIVO_FCIVICA AS nbTipoArchivoFCivica "
		+ "FROM TAI033C_FC_TIPO_ARCH_FCIVICA "
		+ "WHERE "
		+ "	ST_ACTIVO = 1 "
		+ "	AND ID_ORIGEN_PLACA = CASE "
		+ "		WHEN #{origenPlaca} = 0 "
		+ "			THEN 0 "
		+ "		WHEN #{origenPlaca} = 1 "
		+ "			THEN 1 "
		+ "		ELSE ID_ORIGEN_PLACA "
		+ "	END";
	
	@Select(CONSULTA_TIPO_ARCHIVO_FC)
	public List<FMTipoArchivoFCVO> consultaTipoArchivoFC(
			@Param("origenPlaca") Integer origenPlaca);
	
	String BUSCA_ARCHIVO_FOTOCIVICAS = 
		"SELECT "
		+ "	TAI033.ID_TIPO_ARCHIVO_FCIVICA AS idTipoArchivoFCivica, "
		+ "	TAI033.NB_TIPO_ARCHIVO_FCIVICA AS nbTipoArchivoFCivica "
		+ "FROM TAI033C_FC_TIPO_ARCH_FCIVICA TAI033 "
		+ "WHERE "
		+ "	TAI033.ST_ACTIVO = 1 "
		+ "	AND TAI033.ID_TIPO_ARCHIVO_FCIVICA != 99";
	
	@Select(BUSCA_ARCHIVO_FOTOCIVICAS)
	public List<FMTipoArchivoFCVO> buscarArchivoFC();
	
	String PARAMETROS_QUERYS = 
		"SELECT "
		+ "	CD_LLAVE_P_CONFIG, "
		+ "	CD_VALOR_P_CONFIG "
		+ "FROM TAI041P_CONFIGURACION "
		+ "WHERE "
		+ "	ST_ACTIVO = 1";
	@Select(PARAMETROS_QUERYS)
	public List<Map<String, String>> buscarQuerys();
	
	String PARAMETROS_QUERYS_COND = 
		"SELECT "
		+ "	CD_LLAVE_P_CONFIG, "
		+ "	CD_VALOR_P_CONFIG "
		+ "FROM TAI041P_CONFIGURACION "
		+ "WHERE "
		+ "	ST_ACTIVO = 1 "
		+ "	AND CD_LLAVE_P_CONFIG = #{nbConfig}";
	
	@Select(PARAMETROS_QUERYS_COND)
	public List<Map<String, String>> buscarParametrosPorNbConfig(
		@Param("nbConfig") String nbConfig);
	
	String CONS_PERSONALIZADA = "${consPersonalizada}";
	
	@Select(CONS_PERSONALIZADA)
	public Integer countConsDeteccionesFC(
		@Param("consPersonalizada") String consPersonalizada);
	
	@Select(CONS_PERSONALIZADA)
	public List<FCConsultaDeteccionesVO> consDeteccionesFC(
		@Param("consPersonalizada") String consPersonalizada);
	
	/* Consulta detecciones historicas ,desde dos meses atras al principipo */
	@Select(value = CONS_PERSONALIZADA)
	@Options(statementType = StatementType.CALLABLE)
	public List<FCConsultaDeteccionesSinProcVO> consultaDetecciones(
		@Param("consPersonalizada") String consPersonalizada)throws PersistenceException;
	
	/* Consulta detecciones historicas ,desde dos meses atras al principipo */
	@Select(value = CONS_PERSONALIZADA)
	@Options(statementType = StatementType.CALLABLE)
	public List<FCConsultaDeteccionesSinProcVO> consultaDeteccionesHistoricas(
		@Param("consPersonalizada") String consPersonalizada)throws PersistenceException;

	/*Consulta detecciones detalle por mes agrupa por años, mes */
	@Select(value = CONS_PERSONALIZADA)
	@Options(statementType = StatementType.CALLABLE)
	List<FMConsultaDeteccionesSPDetalleMesVO> consultaDeteccionesDetalleActuales(
		@Param("consPersonalizada") String consPersonalizada) throws PersistenceException;
	
	@Select(value = CONS_PERSONALIZADA)
	@Options(statementType = StatementType.CALLABLE)
	public String consParamFechaDetHist(
		@Param("consPersonalizada") String consPersonalizada)throws PersistenceException;
	
	String CONSULTA_TIPO_ARCHIVO_FC_TODO =
		"SELECT "
		+ "	ID_TIPO_ARCHIVO_FCIVICA AS idTipoArchivoFCivica, "
		+ "	NB_TIPO_ARCHIVO_FCIVICA AS nbTipoArchivoFCivica "
		+ "FROM TAI033C_FC_TIPO_ARCH_FCIVICA "
		+ "WHERE "
		+ "	ST_ACTIVO = (CASE "
		+ "		WHEN #{opcion} = 0 "
		+ "			THEN ST_ACTIVO "
		+ "		WHEN #{opcion} = 1 "
		+ "			THEN 1 "
		+ "	END)";
	
	@Select(CONSULTA_TIPO_ARCHIVO_FC_TODO)
	public List<FMTipoArchivoFCVO> consultaTipoArchivoFCTodo(
		@Param("opcion") Integer opcion);
}
