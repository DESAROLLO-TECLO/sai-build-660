package mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.BuscarAuxiliarPorIdMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.controlsuministros.BuscarAuxiliarPorPlacaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.DelegadoAuxiliarVO;
import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialModificacionVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSPSuministroAreasUPDImpl implements BitSPSuministroAreasUPD{

	@Autowired
	private BuscarAuxiliarPorIdMyBatisDAO auxiliarPorIdDAO;
	@Autowired
	private BuscarAuxiliarPorPlacaMyBatisDAO auxiliarPorPlacaDAO;
	
	@Override
	public BitacoraCambiosVO cambiosBitacoraCambioAuxiliar(OficialModificacionVO valores) throws ParseException {
		
		DelegadoAuxiliarVO newAux = auxiliarPorPlacaDAO.buscarAuxiliarPorPlaca(valores.getOficial_placa());
		DelegadoAuxiliarVO oldAux = auxiliarPorIdDAO.buscarAuxiliarPorId(valores.getId_registro());
		
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(3L);
		bitacoraCambiosVO.setConceptoId(4L);
		bitacoraCambiosVO.setValorOriginal(oldAux.getOficial_nombre());
		bitacoraCambiosVO.setValorFinal(newAux.getOficial_nombre());
		bitacoraCambiosVO.setCreadoPor(valores.getIdUser() != null ? valores.getIdUser() : 0L);
		bitacoraCambiosVO.setIdRegistro((valores.getId_registro()+""));
		bitacoraCambiosVO.setIdRegistroDescripcion("");
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return bitacoraCambiosVO;
	}

}
