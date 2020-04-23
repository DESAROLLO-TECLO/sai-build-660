package mx.com.teclo.saicdmx.negocio.service.controlsuministros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros.BitSpSuministroNuevo;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.AltaSuministroAreasMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.SuministroAreasVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service("suministroAreasService")
public class SuministroAreasServiceImpl implements SuministroAreasService {

	@Autowired
	private AltaSuministroAreasMyBatisDAO altaSuministroDAO;
	
	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;

	@Autowired
	private BitSpSuministroNuevo bitSpSuministroNuevo;
	
	@Override
	public SuministroAreasVO altaSuministro(SuministroAreasVO suministroVO){
		altaSuministroDAO.altaSuministro(suministroVO);
		
		/* 	Bitacora de Cambios ... */
		if(suministroVO != null){
			if(!suministroVO.getResultado().equals("-1")){
				try {
					bitacoraCambiosService.guardarBitacoraCambios(bitSpSuministroNuevo.bitacoraCambiosAltaSuministro(suministroVO));
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return suministroVO;
	}
	
}
