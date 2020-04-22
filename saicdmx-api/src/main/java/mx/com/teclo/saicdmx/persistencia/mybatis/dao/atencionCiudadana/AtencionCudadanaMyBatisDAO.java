package mx.com.teclo.saicdmx.persistencia.mybatis.dao.atencionCiudadana;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.exceptions.PersistenceException;

import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegDetTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegTramiteConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACSegTramiteVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACTramiteDetalleVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ACTramiteVO;

@Mapper
public interface AtencionCudadanaMyBatisDAO {

	String SELECT_ID_TRAMITE = "SELECT 'A'||TO_NUMBER(TO_CHAR(SYSDATE,'yy'))||trim(to_char(SQAI045D_AC_TRAMITE.NEXTVAL, '00000009')) FROM dual";

	String INSERTAR_TRAMITE = "INSERT INTO  TAI045D_AC_TRAMITES " + "( "
			+ "ID_AC_TRAMITE ,ID_TIPO_TRAMITE ,FH_ALTA,NB_CIUDADANO ,NB_C_PATERNO ,NB_C_MATERNO ,NU_C_TELEFONO,"
			+ " TX_C_CORREO ,TX_C_CALLE ,TX_C_COLONIA ,NU_C_INT,NU_C_EXT,ID_C_DELEGACION ,NU_C_CP ,ID_C_EDO ,CD_C_PLACA ,"
			+ "ID_MARCA ,ID_MODELO ,ID_COLOR ,ID_TIPO_VEHICULO ,TX_HECHOS ,TX_CC ,ID_TIPO_DOC  ,ST_ATENDIDO ,"
			+ "NB_MARCA_OTRO ,NB_MODELO_OTRO ,NB_DOC_OTRO ,ID_EMP ,FH_CREACION ,FH_MODIFICACION ,ID_USR_CREACION ,ID_USR_MODIFICA,"
			+ "ST_EXPEDIENTE,NB_EMPRESA,CD_SOLICITANTE,ID_MEDIO_SOLICITUD,TX_INFRACCION,CD_NUEVO_ORIGEN_PLACA,ID_NUEVO_TIPO_PERSONA,ID_NUEVO_EDO" + ") VALUES ("
			+ "	 #{idacTramite}, #{idTramite}, TO_DATE (#{fhAlta},' DD / MM / YYYY HH24: MI: SS '), PKG_ENCRIPCION.ENCRIPTA(#{nbCiudadano}), PKG_ENCRIPCION.ENCRIPTA(#{nbPaterno}), PKG_ENCRIPCION.ENCRIPTA(#{nbMaterno}), PKG_ENCRIPCION.ENCRIPTA(#{nuTelefono}),"
			+ " PKG_ENCRIPCION.ENCRIPTA(#{txCorreo}), PKG_ENCRIPCION.ENCRIPTA(#{txCalle}), PKG_ENCRIPCION.ENCRIPTA(#{txColonia}), PKG_ENCRIPCION.ENCRIPTA(#{nuInt}), PKG_ENCRIPCION.ENCRIPTA(#{nuExt}), #{idDelegacion}, #{nuCp}, #{idEdo}, #{cdPlaca},"
			+ " #{idMarca}, #{idModelo}, #{idColor}, #{tipoVehiculo}, #{txHechos}, #{txCc}, #{idDocumento}, #{stAtendido},"
			+ "#{nbMarcaOtro}, #{nbModeloOtro}, #{nbDocOtro},#{idEmp}, sysdate, sysdate,#{idEmp},#{idEmp},#{stExpediente},PKG_ENCRIPCION.ENCRIPTA(#{nbEmpresa}),#{cdTipoPersona},#{idMedioSolicitud},#{foliosDeInfraccion},#{nuevoOrigenTipo},#{nuevoTipo},#{nuevoIdEstadoTipo})";

	String SELECT_TRAMITE = " SELECT ID_AC_TRAMITE as idacTramite," + " ID_TIPO_TRAMITE  as idTramite,"
			+ " TO_CHAR(fh_alta,'dd/mm/yyyy HH24:MI')as fhAlta,PKG_ENCRIPCION.DESENCRIPTA(NB_CIUDADANO) as nbCiudadano,PKG_ENCRIPCION.DESENCRIPTA(NB_C_PATERNO) nbPaterno,PKG_ENCRIPCION.DESENCRIPTA(NB_C_MATERNO) nbMaterno,PKG_ENCRIPCION.DESENCRIPTA(NU_C_TELEFONO) as nuTelefono,"
			+ " PKG_ENCRIPCION.DESENCRIPTA(TX_C_CORREO) as txCorreo,PKG_ENCRIPCION.DESENCRIPTA(TX_C_CALLE) as txCalle,PKG_ENCRIPCION.DESENCRIPTA(TX_C_COLONIA) as txColonia,PKG_ENCRIPCION.DESENCRIPTA(NU_C_INT) as nuInt,PKG_ENCRIPCION.DESENCRIPTA(NU_C_EXT) as nuExt,"
			+ "Fn_Delegacion (ID_C_EDO,ID_C_DELEGACION) as cDelegacion,"
			+ "Fn_Estado (ID_C_EDO) as cEstado ,CD_C_PLACA as cdPlaca, Fn_Marca (ID_MARCA) as cMarca , Fn_Modelo (ID_MARCA, ID_MODELO) as cModelo,"
			+ " Fn_Color (ID_COLOR) as cColor,(SELECT  VTIPO_NOMBRE FROM VEHICULO_TIPO WHERE VTIPO_ID=TAI045D_AC_TRAMITES.ID_TIPO_VEHICULO)as cVehiculo ,"
			+ " TX_HECHOS as txHechos,TX_CC as txCc, ID_TIPO_DOC AS idDocumento ,"
			+ " ST_ATENDIDO as stAtendido ,NB_MARCA_OTRO as nbMarcaOtro,NB_MODELO_OTRO as nbModeloOtro, NB_DOC_OTRO nbDocOtro,"
			+ " EMP_NOMBRE as nbOficial, EMP_APE_PATERNO as apellidoPaternoOficial, EMP_APE_MATERNO as apellidoMaternoOficial,"
			+ " FH_CREACION as fhCreacion,FH_MODIFICACION as fhModificacion, PKG_ENCRIPCION.DESENCRIPTA(NB_EMPRESA) as nbEmpresa,ID_MEDIO_SOLICITUD as idMedioSolicitud,"
			+ " TX_INFRACCION as foliosDeInfraccion, CD_NUEVO_ORIGEN_PLACA as nuevoOrigenTipo, ID_NUEVO_TIPO_PERSONA as nuevoTipo, ID_NUEVO_EDO as nuevoIdEstadoTipo"
			+" FROM TAI045D_AC_TRAMITES inner join EMPLEADOS ON TAI045D_AC_TRAMITES.ID_EMP=EMPLEADOS.EMP_ID AND " 
			+ "TAI045D_AC_TRAMITES.ID_AC_TRAMITE=#{idTramite}";
	
	String SELECT_INFRACCION="SELECT DECODE (COUNT(*), 1, 'SI', 0, 'NO') AS RESPUESTA"
			+ " FROM V_SSP_INFRAC_CONS_GRAL_F"
			+ " WHERE INFRACCION=#{infraccion} AND VEHICULO_PLACA=#{placa}";

	String ALTA_AC_SEG_TRAMITE = "insert into TAI052D_AC_SEG_TRAMITE (" + 
			"ID_SEG_TRAMITE, "+
			"ID_AC_TRAMITE, " +
			"ID_TRAMITE_DETALLE, " + 
			"ID_TIPO_TRAMITE, " + 
			"ID_TIPO_VALOR, " + 
			"CD_VALOR, " + 
			"ST_ACTIVO, " + 
			"ID_USR_CREACION, " + 
			"FH_CREACION, " + 
			"ID_USR_MODIFICA, " + 
			"FH_MODIFICACION, " + 
			"ST_SEG_TRAMITE) " + 
			"values( " + 
			"SQAI052D_AC_SEG_TRAMITE.nextval, " + 
			"#{idAcTramite}, " +
			"#{idTramiteDetalle}, "+
			"#{idTipoTramite}, " + 
			"#{idTipoValor}, " + 
			"#{cdValor}, " + 
			"#{stActivo}, " + 
			"#{idUsrCreacion}, " + 
			"sysdate, " + 
			"#{idUsrModifica}, " + 
			"sysdate, " + 
			"#{stSegTramite})";
	
	String ALTA_AC_TRAMITE_DETALLE = "insert into TAI055D_AC_TRAMITE_DETALLE ("
			+ "ID_TRAMITE_DETALLE, ID_AC_TRAMITE, ID_TIPO_TRAMITE, CT_SOLICITADOS, CT_ATENDIDOS, " 
			+ "ST_ACTIVO, ID_USR_CREACION, FH_CREACION, ID_USR_MODIFICA, FH_MODIFICACION, ST_SEG_TRAMITE, TX_COMENTARIO_TRAM) " 
			+ "values(" 
			+ "#{idTramiteDetalle}, #{idAcTramite}, #{idTipoTramite}, #{ctSolicitados}, #{ctAtendidos}, #{stActivo}, " 
			+ "#{idUsrCreacion}, sysdate, #{idUsrModifica}, sysdate, #{stSegTramite}, #{txComentarioTram})";
	
	String CONS_PERSONALIZADA = "${consulta}";
	
	@Insert(INSERTAR_TRAMITE)
	public Boolean insertarTramite(ACTramiteVO tramiteVO) throws PersistenceException;

	@Select(SELECT_INFRACCION)
	public List<String> buscaInfraccion(@Param("placa") String placa,@Param("infraccion") String infraccion);
	
	@Select(SELECT_ID_TRAMITE)
	public String buscarTramiteID();

	@Select(SELECT_TRAMITE)
	public ACTramiteVO seleccionarTramite(@Param("idTramite") String idTramite) throws PersistenceException;
	
	@Insert(ALTA_AC_SEG_TRAMITE)
	public Boolean altaSeguimientoTramite( ACSegTramiteVO AcSegTramite);
	
	@Update(CONS_PERSONALIZADA)
	public Boolean modificaSeguimientoTramite(@Param("consulta") String consulta, 
			@Param("idAcTramite") String idAcTramite,
			@Param("cdValor") String cdValor,
			@Param("stSegTramite") Integer stSegTramite, 
			@Param("idUsrModifica") Long idUsrModifica);
	
	@Update(CONS_PERSONALIZADA)
	public Boolean desactivaSeguimientoTramite(@Param("consulta") String consulta, 
			@Param("idAcTramite") String idAcTramite,
			@Param("cdValor") String cdValor,
			@Param("stSegTramite") Integer stSegTramite, 
			@Param("idUsrModifica") Long idUsrModifica);
	
	@Select(CONS_PERSONALIZADA)
	public List<ACSegTramiteConsultaVO> getListaSeguimientoTramite(
			@Param("consulta") String consulta,
			@Param("tipoBusq") Integer tipoBusq,
			@Param("valor") String valor, 
			@Param("idTipoTramite") Integer idTipoTramite,
			@Param("stSegTramite") Integer stSegTramite, 
			@Param("fhInicio") String fhInicio, 
			@Param("fhFin") String fhFin);
	
	@Insert(ALTA_AC_TRAMITE_DETALLE)
	public Boolean nuevoACTramiteDetalle(ACTramiteDetalleVO acTramiteDetalle);
	
	@Update(CONS_PERSONALIZADA)
	public Boolean actualizarACTramiteDetalle(
			@Param("consulta") String consulta,
			@Param("idAcTramite") String idAcTramite,
			@Param("ctSolicitados")Integer ctSolicitados, 
			@Param("ctAtendidos")Integer ctAtendidos, 
			@Param("stSegTramite") Integer stSegTramite, 
			@Param("idUsrModifica") Long idUsrModifica, 
			@Param("txComentarioTram") String txComentarioTram);
	
	@Update(CONS_PERSONALIZADA)
	public Boolean actualizarACTramiteDetalleFhMod(
			@Param("consulta") String consulta,
			@Param("idAcTramite") String idAcTramite, 
			@Param("idUsrModifica") Long idUsrModifica, 
			@Param("txComentario") String txComentario);
	
	@Update(CONS_PERSONALIZADA)
	public Boolean actualizarACTramiteSeguimiento(
			@Param("consulta") String consulta,
			@Param("idAcTramite") String idAcTramite, 
			@Param("idUsrModifica") Long idUsrModifica);
	
	@Select(CONS_PERSONALIZADA)
	public String obtenerSiguinteValorSeguimientoDetalle(
			@Param("consulta") String consulta);
	
	@Update(CONS_PERSONALIZADA)
	public Boolean realizarCambioDePersona(
			@Param("consulta") String consulta, 
			@Param("idAcTramite") String idAcTramite,
			@Param("idUsrModifica") Long idUsrModifica);
	
	@Select(CONS_PERSONALIZADA)
	public List<ACSegDetTramiteConsultaVO> getListInfCambioDePersona(
			@Param("consulta") String consulta,
			@Param("placaVehicular") String placaVehicular,
			@Param("infracNum") String infracNum);
	
	@Select(CONS_PERSONALIZADA)
	public ACSegDetTramiteConsultaVO getInfCambioDePersona(
			@Param("consulta") String consulta,
			@Param("placaVehicular") String placaVehicular,
			@Param("infracNum") String infracNum);
}
