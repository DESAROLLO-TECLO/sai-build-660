package mx.com.teclo.saicdmx.negocio.service.semovi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarparametrosweb.ParametrosMyBatisDAO;

@Service
public class ParametroServiceImpl implements ParametroService{
	
	@Autowired
	private ParametrosMyBatisDAO parametrosDAO;
	
	
	    // URL del webservice de l�nea de captura por batch de Secretar�a de Finanzas
	public  String getUrlWebServiceLineaCaptura() {
	        return parametrosDAO.getUrlWebServiceLineaCaptura();
	    }
	    // URL del webservice de SETRAVI

    public  String getUrlWebServiceSETRAVI() {
	        return parametrosDAO.getUrlWebServiceSETRAVI();
	    }
	    // URL del webservice de reasignacion de linea de captura
    public  String getUrlWebServiceReasignarLineaCaptura() {
	        return parametrosDAO.getUrlWebServiceReasignarLineaCaptura();
	    }
	    // Path de almacenamiento de archivos de radares

	public  String getDirRadares() {
	        return parametrosDAO.getDirRadares();
	    }

	    // Path de almacenamiento de archivos de radares
	public  String getDirTempUpload() {
	        return parametrosDAO.getDirTempUpload();
	    }
	    // Parametro usuario de webservice batch
	 public  String getWebserviceUsuario() {
	        return parametrosDAO.getWebserviceUsuario();
	    }
	    // Parametro password de webservice batch
     public  String getWebservicePassword() {
	        return parametrosDAO.getWebservicePassword();
	    }
	    // Parametro clave de webservice batch
	 public  String getWebserviceClave() {
	        return parametrosDAO.getWebserviceClave();
	    }
	    // Parametro dias salario de webservice batch
	public  String getWebserviceDiasSalarios() {
	        return parametrosDAO.getWebserviceDiasSalarios();
	    }
	    // Parametro derechos de webservice batch
    public  String getWebserviceDerechos() {
	        return parametrosDAO.getWebserviceDerechos();
	    }
	    
	    // Parametro derechos de webservice batch
    public  String getRutaZip() {
	        return parametrosDAO.getRutaZip();
	    }
	    
	    // Parametro derechos de webservice batch
    public  String getRutaSemovi() {
	        return parametrosDAO.getRutaSemovi();
	    }

       // Parametro proxy linea de captura
	@Override
	public String getURLProxyLc() {
			return parametrosDAO.getURLProxyLc();
		}
  
	}



