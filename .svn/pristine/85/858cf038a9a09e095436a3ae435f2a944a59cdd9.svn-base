package mx.com.teclo.saicdmx.negocio.service.fm;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.fm.DeteccionBatchFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMBitProcesoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionCP;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionCPV2;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLoteProcesoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLoteResumenVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;

public interface DeteccionesService {
	public FMLoteProcesoVO buscarAntiguoLoteEnProceso();
	public FMLoteProcesoVO buscarLoteEnProceso();
	public Boolean actualizarCambiarCP(int[] ids, String[] newcp, Long usuario);
	public Boolean actualizarCambiarRADCP(int[] ids, String[] newcp, Long usuario);
	public List<FMBitProcesoVO> buscarBitacoraLoteEnProceso(Long idLote);
	public DeteccionBatchFinanzasVO cargarLote(Long idLote);
	public void cancelarArchivo(DeteccionBatchFinanzasVO objetoVO, String motivo) throws BusinessException;
	public FMLoteResumenVO buscarLoteCancelado(Long idLote);
	//public List<FMDeteccionCP> buscarDeteccionesFmValidas(Long idLote);
	//public List<FMDeteccionCP> buscarDeteccionesFmInvalidas(Long idLote);
	public List<FMDeteccionCP> buscarAllDeteccionesFmByCP(String cp, Long idLote);
	public List<FMDeteccionCP> buscarAllDeteccionesRADByCP(String cp, Long idLote);
	public List<FMDeteccionCPV2> buscarDeteccionesFmValidas(Long idLote);
	public List<FMDeteccionCPV2> buscarDeteccionesRADValidas(Long idLote);
	public List<FMDeteccionCPV2> buscarDeteccionesFmInvalidas(Long idLote);
	public Boolean actualizarFmLoteReasignadoCP(Long idLote, Integer estatusProcesoId, Long idUsuario, Integer enProceso);
	public Boolean habilitaFmCambioCP(Long idCp, Long lote,Long idUsuario, String cp);
	public Boolean deshabilitaFmCambioCP(Long idCp, Long lote, Long idUsuario);
}
