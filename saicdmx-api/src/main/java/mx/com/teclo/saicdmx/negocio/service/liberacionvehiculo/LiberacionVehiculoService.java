package mx.com.teclo.saicdmx.negocio.service.liberacionvehiculo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.usuarios.UsuariosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ConsultaSalidaDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ModificacionSalidaDepositoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;

public interface LiberacionVehiculoService {
	
	/***
	 * @author Jesus Gutierrez
	 * @param tipoBusqueda
	 * @param valor
	 * @param depositoId
	 * @return
	 */
	public List<ConsultaSalidaDepositoVO> buscaDepositoSalida(String tipoBusqueda, String valor, Long depositoId);
	
	/***
	 * @author Jesus Gutierrez
	 * @param infracNum
	 * @return
	 */
	public ModificacionSalidaDepositoVO consultaParaLiberacionVehiculoModificacion(String infracNum);
	
	/***
	 * @author Jesus Gutierrez
	 * @param infracNumControl
	 * @param usuObject
	 * @param usuarioId
	 * @return
	 * @throws BusinessException
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	public Map guardarSalidaVehiculo(ModificacionSalidaDepositoVO infracNumControl, UsuariosDTO usuObject, Long usuarioId) throws BusinessException, ParseException;
}
