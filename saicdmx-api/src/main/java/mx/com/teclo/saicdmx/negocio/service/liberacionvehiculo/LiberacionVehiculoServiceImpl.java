package mx.com.teclo.saicdmx.negocio.service.liberacionvehiculo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.ingresos.BitTrBitacIngresos;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.usuarios.UsuariosDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.liberacionvehiculo.LiberacionVehiculoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ConsultaSalidaDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ModificacionSalidaDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ProcesoDeSalidaAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ProcesoDeSalidaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class LiberacionVehiculoServiceImpl implements LiberacionVehiculoService{
	
	@Autowired
	private LiberacionVehiculoMyBatisDAO liberacionVehiculoMyBatisDAO;

	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@Autowired
	private BitTrBitacIngresos bitTrBitacIngresos;
	
	@Override
	public List<ConsultaSalidaDepositoVO> buscaDepositoSalida(String tipoBusqueda, String valor, Long depositoId) {
		List<ConsultaSalidaDepositoVO> lista = new ArrayList<ConsultaSalidaDepositoVO>();
		String moduloBusqueda = "SALIDA";
        String operacionBusqueda = "";
        String fechaIniBusqueda = "";
        String fechaFinBusqueda = "";
        String eventoBusqueda = "";

        lista = liberacionVehiculoMyBatisDAO.consultaSalidaDeposito(moduloBusqueda, operacionBusqueda, 
																		tipoBusqueda, valor, 
																		fechaIniBusqueda, fechaFinBusqueda, 
																		eventoBusqueda, depositoId.toString());
		return lista;
	}

	/***
	 * {@inheritDoc}
	 */
	@Override
	public ModificacionSalidaDepositoVO consultaParaLiberacionVehiculoModificacion(String infracNum) {
		ModificacionSalidaDepositoVO object = liberacionVehiculoMyBatisDAO.consultaParaLiberacionVehiculoModificacion(infracNum);
		return object;
	}

	/***
	 * {@inheritDoc}
	 * @throws ParseException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map guardarSalidaVehiculo(ModificacionSalidaDepositoVO objectoModificacion, UsuariosDTO usuarioDTO, Long usuarioId) throws BusinessException, ParseException {
		Map mapa = new HashMap();
		ProcesoDeSalidaVO original  = liberacionVehiculoMyBatisDAO.getOldVOProcesoSalida(objectoModificacion.getInfracnumctrl());
		
		ProcesoDeSalidaVO salidaVO = new ProcesoDeSalidaVO();
		salidaVO.setDepositoId(objectoModificacion.getDepositoId());
		salidaVO.setNombreresguardo(objectoModificacion.getNombreresguardo());
		salidaVO.setUsuarioId(usuarioId);
		liberacionVehiculoMyBatisDAO.ejecutarProcesoDeSalida(salidaVO);
		SimpleDateFormat formata  = new SimpleDateFormat("dd/MM/yy");
		String fechaIngreso = formata.format(new Date());
		salidaVO.setFechaIngreso(fechaIngreso);
		salidaVO.setInfracNum(objectoModificacion.getInfracnumctrl().substring(0, 11));
		salidaVO.setStatusIngr("E");
		
		String usu_login = "";
		if(usuarioDTO != null){
			usu_login = usuarioDTO.getUsuarioLogin();
		}
		
		ProcesoDeSalidaAdminVO salidaAdminVO = new ProcesoDeSalidaAdminVO();
		salidaAdminVO.setUsuarioDeposito(objectoModificacion.getUsuarioDeposito());
		salidaAdminVO.setInfracnumctrl(objectoModificacion.getInfracnumctrl());
		salidaAdminVO.setNumeroPedido(objectoModificacion.getNombreresguardo());
		salidaAdminVO.setVehiculoTipo(objectoModificacion.getCodInternoVehiculo());
		salidaAdminVO.setUsuario(usu_login);
		liberacionVehiculoMyBatisDAO.ejecutarProcesoDeSalidaAdmin(salidaAdminVO);
		
		if(!salidaVO.getResultado().equals(-1) && !salidaAdminVO.getResultado().equals(-1)) {
			bitacoraCambiosService.guardarListaBitacoraCambios(bitTrBitacIngresos.guardarCambiosModifica(original, salidaVO));	
		}
		
		
		mapa.put("salidaVO", salidaVO);
		mapa.put("salidaAdminVO", salidaAdminVO);
		
		return mapa;
	}
}
