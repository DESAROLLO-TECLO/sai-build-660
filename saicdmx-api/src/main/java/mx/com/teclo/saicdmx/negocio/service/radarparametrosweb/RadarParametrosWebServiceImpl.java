package mx.com.teclo.saicdmx.negocio.service.radarparametrosweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarparametrosweb.RadarParametrosWebMyBatisDAO;

/**
 * 
 * @author UnitisDes0416
 *
 */
@Service
public class RadarParametrosWebServiceImpl implements RadarParametrosWebService {
	
	@Autowired
	private RadarParametrosWebMyBatisDAO radarParametrosWebMyBatisDAO;
	
	public String obtenerRutaRadares(){
		return radarParametrosWebMyBatisDAO.buscaRutaRadares();
	}
}
