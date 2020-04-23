package mx.com.teclo.saicdmx.negocio.service.lineadecaptura;

import java.io.IOException;
import java.net.ProtocolException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.IncidenciaLCMVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.NotFoundException;

public interface ReasignacionLCMasivaService {
	
	/***
	 * Valida que no haya folios pendientes, si es asi, se manda directo a la
	 * pagina de reasignado, sino, se manda a la pagina de carga de archivo.
	 * @author Jesus Ponce
	 * @return Map<String, Integer>
	 */
	public Map<String,Integer> validarLotePendiente();
	
	/***
	 * Sube el archivo y lo registra en BD
	 * @author Jesus Ponce
	 * @param String[]
	 * @param userId
	 * @param name
	 * @return List<IncidenciaLCMVO>
	 */
	public Map<String, Object> cargarArchivoReasignar(String[] infracs, Long userId, String name, String fEmision, Integer tipoDescuento);
	//List<IncidenciaLCMVO>
	/***
	 * Se encarga de empezar con el proceso de reasignado de folios.
	 * @author Jesus Ponce
	 * @return Map<String, Boolean>
	 * @throws BusinessException 
	 * @throws ParseException 
	 * @throws NotFoundException 
	 */
	public Map<String, Object> reasignarLoteFolio(Long userId)throws ProtocolException, IOException, ParserConfigurationException, SAXException, BusinessException, ParseException, NotFoundException;
	
	/***
	 * Se encarga de cancelar el lote de Folios.
	 * @author Jesus Ponce
	 * @return Boolean
	 */
	public Boolean cancelarLoteFolio();
}
