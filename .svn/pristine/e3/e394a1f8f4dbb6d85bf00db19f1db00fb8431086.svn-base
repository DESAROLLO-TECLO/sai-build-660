package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.mit.prepago.services.BusinessException;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.VistaDTO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.infraccionesAnualesVO;

public interface InfraccionesAnualesService {
	
	/***
	 * @author Fernando Octavio
	 * @return
	 * @throws BusinessException
	 */
	public List<VistaDTO> consultaAniosInfraccion() throws BusinessException;
	
	/***
	 * @author Fernando Octavio
	 * @return
	 * @throws BusinessException
	 */
	public List<infraccionesAnualesVO> consultaInfraccionesAnuales(Long anio) throws BusinessException;
	
	/***
	 * @author Fernando Octavio
	 * @return
	 * @throws BusinessException
	 */
	public ByteArrayOutputStream descargaExcelInfraccionesAnuales();
	
	/*infracciones anuales total anual*/
	/***
	 * @author Fernando Octavio
	 * @return
	 * @throws BusinessException
	 */
	public List<infraccionesAnualesVO> consultaInfrraccionesTotalAnual(Long anio) throws BusinessException;
	
}
