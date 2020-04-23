package mx.com.teclo.saicdmx.negocio.service.controlsuministros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros.BitSpSuministroAlmacen;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.AltaControlAlmacenMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.ControlAlmacenVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service("controlAlmacenService")
public class ControlAlmacenServiceImpl implements ControlAlmacenService {

	@Autowired
	private AltaControlAlmacenMyBatisDAO altaAlmacenDAO;
	
	@Autowired
	private BitSpSuministroAlmacen bitSpSuministroAlmacen;
	
	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;
	
	@Override
	public ControlAlmacenVO altaAlmacen(ControlAlmacenVO almacenVO){
		altaAlmacenDAO.altaAlmacen(almacenVO);
		
		/* Bitacora de Cambios ...*/
		if(almacenVO != null){
			if(!almacenVO.getResultado().equals("-1")){

				try {
					bitacoraCambiosService.guardarBitacoraCambios(bitSpSuministroAlmacen.bitacoraCambiosAltaAlmacen(almacenVO));
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				
			}
		}
		return almacenVO;
		
	}
	
}
