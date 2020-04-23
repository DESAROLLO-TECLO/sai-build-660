package mx.com.teclo.saicdmx.negocio.service.estadistica;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.estadistica.MapaDAO;

@Service
public class MapaServiceImpl implements MapaService{

	@Autowired
	private MapaDAO mapaDAO;

	@SuppressWarnings("rawtypes")
	@Transactional
	public Map tipoInfraccionPorFecha(String[] tipoInfraccion, String fechaini, String fechafin) {
		return mapaDAO.tipoInfraccionPorFecha(tipoInfraccion, fechaini, fechafin);
	}
	
}
