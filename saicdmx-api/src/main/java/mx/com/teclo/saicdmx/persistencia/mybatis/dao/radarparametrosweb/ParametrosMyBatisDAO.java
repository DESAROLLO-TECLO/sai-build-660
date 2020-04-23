package mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarparametrosweb;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ParametrosMyBatisDAO {
	
	String CONSULTA = " FROM RADAR_PARAMETROS_WEB WHERE PARAMETRO_ID=1";
	
    // URL del webservice de  linea de captura por batch de Secretaria de Finanzas
  String  getUrlWebServiceLineaCaptura="SELECT URL_WEBSERVICE_BATCH" + CONSULTA;
    
    // URL del webservice de SETRAVI

  String getUrlWebServiceSETRAVI=" SELECT URL_WEBSERVICE_SETRAVI"+CONSULTA;
    
    // URL del webservice de reasignacion de linea de captura
  String getUrlWebServiceReasignarLineaCaptura="SELECT URL_WEBSERVICE_LINEA_CAPTURA"+CONSULTA;
    
    // Path de almacenamiento de archivos de radares

  String getDirRadares = "SELECT DIR_CARGA_ARCHIVOS_FINAL"+CONSULTA;

    // Path de almacenamiento de archivos de radares
  String getDirTempUpload="SELECT DIR_CARGA_ARCHIVOS_TEMPORAL"+CONSULTA;
    
    // Parametro usuario de webservice batch
   String getWebserviceUsuario="SELECT WEBSERVICE_BATCH_USUARIO"+CONSULTA;
    // Parametro password de webservice batch
   String getWebservicePassword = "SELECT WEBSERVICE_BATCH_PASSWORD"+CONSULTA;
    
    // Parametro clave de webservice batch
   String getWebserviceClave = "SELECT WEBSERVICE_BATCH_CLAVE"+CONSULTA;
    
    // Parametro dias salario de webservice batch
  String getWebserviceDiasSalarios=" SELECT WEBSERVICE_BATCH_DIAS_SALARIO"+CONSULTA;
    
    // Parametro derechos de webservice batch
  String getWebserviceDerechos="SELECT WEBSERVICE_BATCH_DERECHOS"+CONSULTA;
        
    // Parametro derechos de webservice batch
  String getRutaZip="SELECT RUTA_ARCHIVOS_ZIP"+CONSULTA;
  
    // Parametro derechos de webservice batch
  String getRutaSemovi= "SELECT RUTA_ARCHIVOS_SEMOVI "+CONSULTA;
	
   // Parametro url Proxy LC
  String getURLProxyLc="SELECT PROXY_WEBSERVICE_LC "+CONSULTA;
  	
	@Select(getUrlWebServiceLineaCaptura)
	public String getUrlWebServiceLineaCaptura();

	@Select(getUrlWebServiceSETRAVI)
	public String getUrlWebServiceSETRAVI();
		
	@Select(getUrlWebServiceReasignarLineaCaptura)
	public String getUrlWebServiceReasignarLineaCaptura();
	
	@Select(getDirRadares)
	public String getDirRadares();
	
	@Select(getDirTempUpload)
	public String getDirTempUpload();
	
	@Select(getWebserviceUsuario)
	public String getWebserviceUsuario();
	
	@Select(getWebservicePassword)
	public String getWebservicePassword();
	
	@Select(getWebserviceClave)
	public String getWebserviceClave();

	@Select(getWebserviceDiasSalarios)
	public String getWebserviceDiasSalarios();
		
	@Select(getWebserviceDerechos)
	public String getWebserviceDerechos();

	@Select(getRutaZip)
	public String getRutaZip();

	@Select(getRutaSemovi)
	public String getRutaSemovi();
    
	@Select(getURLProxyLc)
	public String getURLProxyLc();
	 
}
