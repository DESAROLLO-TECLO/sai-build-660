package mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.BuscarAuxiliarPorPlacaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialNuevoVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSPSuministroAreasNewImpl implements BitSPSuministroAreasNew{
		
	@Autowired
	private BuscarAuxiliarPorPlacaMyBatisDAO auxiliarPorPlacaDAO;
	
	@Override
	public BitacoraCambiosVO cambiosBitacoraAltaAuxiliar(OficialNuevoVO valoresAux) throws ParseException {
		
		DelegadoAuxiliarVO newAux = auxiliarPorPlacaDAO.buscarAuxiliarPorPlaca(valoresAux.getOficial_placa());
		
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(3L);
		bitacoraCambiosVO.setConceptoId(3L);
		bitacoraCambiosVO.setValorOriginal("");
		bitacoraCambiosVO.setValorFinal(newAux.getOficial_nombre());
		bitacoraCambiosVO.setCreadoPor(valoresAux.getIdUser() > 0 ? Long.parseLong(valoresAux.getIdUser()+"") : 0L);
		bitacoraCambiosVO.setIdRegistro(valoresAux.getResultado());
		bitacoraCambiosVO.setIdRegistroDescripcion("");
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return bitacoraCambiosVO;
	}
}
