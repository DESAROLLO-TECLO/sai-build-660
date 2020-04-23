package mx.com.teclo.saicdmx.negocio.service.grua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.GruaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GruaDTO;
import mx.com.teclo.saicdmx.util.comun.Infracciones;
import mx.com.teclo.saicdmx.util.comun.Remisiones;

@Service
public class GruaServiceImpl implements GruaService {
	
	//Comentado 12/09/2017 Validar que funcionalidad utiliza el tipo de ingreso
	
	@Autowired
	private GruaDAO gruaDAO;

//	@Transactional
//	@Override
//	public boolean validarNumeroEconomico(String codigoGrua, long tipoIngreso) {
//		boolean numeroEconomicoOK = false;
//
//		GruaDTO grua = gruaDAO.buscarGrua("A", codigoGrua);
//
//		if (grua != null) {
//			if (grua.getTipoGrua().getTipoIngreso().gettIngrId() == tipoIngreso) {
//				numeroEconomicoOK = true;
//			}
//		}
//		return numeroEconomicoOK;
//	}
//	
//	@Transactional
//	@Override
//	public int validarGrua(String numeroEconomico, Long tipoIngreso) {
//		int resultado = Remisiones.GRUA_NO_ENCONTRADA;
//		GruaDTO gruaDTO = gruaDAO.buscarGrua(Infracciones.ESTATUS_ACTIVO, numeroEconomico);
//		if (gruaDTO != null) {
//			if (gruaDTO.getTipoGrua().getTipoIngreso().gettIngrId() == tipoIngreso) {
//				resultado = Remisiones.GRUA_ENCONTRADA;
//			} else {
//				resultado = Remisiones.GRUA_NO_CORRESPONDE;
//			}
//		}
//		return resultado;
//	}
}
