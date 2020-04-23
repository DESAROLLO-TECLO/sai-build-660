package mx.com.teclo.saicdmx.negocio.service.controlsuministros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros.BitSPSuministroDev;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.AltaDevolucionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DevolucionesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;


@Service("devolucionesService")
public class DevolucionesServiceImpl implements DevolucionesService{

	@Autowired
	private AltaDevolucionesMyBatisDAO altaDevolucionDAO;
	
	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;

	@Autowired
	private BitSPSuministroDev bitSPSuministroDev;
	
	@Override
	public DevolucionesVO altaDevolucion(DevolucionesVO devolucionVO){
		altaDevolucionDAO.altaDevolucion(devolucionVO);		
		/*Bitacora de cambios*/
		if(!devolucionVO.getResultado().equals("-1")){
			try{
				bitacoraCambiosService.guardarBitacoraCambios(bitSPSuministroDev.guardarCambiosBitacoraAltaDev(devolucionVO));
			}catch(Exception e){ System.err.println(e.getMessage());}
		}
		return devolucionVO;
		
	}
}
