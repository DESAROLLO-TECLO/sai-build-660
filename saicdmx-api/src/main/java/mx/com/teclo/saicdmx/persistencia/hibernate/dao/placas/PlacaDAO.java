package mx.com.teclo.saicdmx.persistencia.hibernate.dao.placas;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.placas.PlacasDTO;

public interface PlacaDAO extends BaseDao<PlacasDTO>{


	public PlacasDTO actualizarPlaca(String placaCodigo);
	
	

 	public  PlacasDTO altaPlaca(String placaCodigo);
 	
 
 	public  List<PlacasDTO> consultaPlaca( String placaCodigo);


	public PlacasDTO find(Long placaID);

}