package mx.com.teclo.saicdmx.persistencia.mybatis.dao.atencionCiudadana;




import java.sql.Blob;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.EstatusSeguimientoTramiteACVO;
import mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana.ExpedienteACVO;



@Mapper
public interface TramitesACMyBatisDAO {
	
	//result property="blobData" javaType="_byte[]" column="blob_Data" jdbcType="BLOB"/>
	
	
	String GET_BLOB_EXPEDIENTE = "SELECT ID_AC_TRAMITE as acTramite, "
			+ "ID_TIPO_TRAMITE as idTramite, "
			+ "FH_ALTA as fhAlta, "
			+ "LB_EXPEDIENTE as lbExpediente, "
			+ "ST_ATENDIDO as stAtendido, "
			+ "ST_EXPEDIENTE as tieneExpediente "
		    + "FROM TAI045D_AC_TRAMITES "
		    + "WHERE ID_AC_TRAMITE = #{folioTramite}";
	
	String GET_CAMPOS_REQUERIDOS = "SELECT CD_VALOR_P_CONFIG "
		    + "FROM TAI041P_CONFIGURACION "
		    + "WHERE CD_LLAVE_P_CONFIG ='CAMPOS_REQUERIDOS_AC'";
	
	String GET_INFORMACION_AYUDA="SELECT CD_LLAVE_P_CONFIG,CD_VALOR_P_CONFIG FROM TAI041P_CONFIGURACION "
			+"WHERE CD_MODULO_APP='SAI-ATENCION CIUDADANA'";

	String CONS_PERSONALIZADA = "${consPersonalizada}";
	
	
	/*@Results({
        @Result(property="lbExpediente",column="LB_EXPEDIENTE" ,jdbcType=JdbcType.BLOB, javaType=byte.class),
         })*/
	@Select(GET_BLOB_EXPEDIENTE)
	public ExpedienteACVO getBlobPDFExpediente(@Param("folioTramite") String folioTramite);
	
	@Select(GET_CAMPOS_REQUERIDOS)
	public String getJsonCamposRequeridos();
	
	@Select(GET_INFORMACION_AYUDA)
	public List<Map<String, String>> getInformacionTooltipAyuda();
	
	@Select(CONS_PERSONALIZADA)
	String getRutaExpediente(@Param("consPersonalizada") String consPersonalizada, @Param("folioTramite") String folioTramite, @Param("idExpediente") Long idExpediente);
			
	@Select(CONS_PERSONALIZADA)
	public List<EstatusSeguimientoTramiteACVO> consStSegTramite(@Param("consPersonalizada") String consPersonalizada);
}
