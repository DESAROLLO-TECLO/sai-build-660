package mx.com.teclo.saicdmx.negocio.service.fm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.fm.FMDeteccionesMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radararchivo.RadarArchivoMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.DeteccionBatchFinanzasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMBitProcesoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionCP;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionCPV2;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLoteProcesoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMLoteResumenVO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.ArchivoBatchFinanzasVO;
import mx.com.teclo.saicdmx.util.enumerados.ArchivoBatchEstatusEnum;
import mx.com.teclo.saicdmx.util.enumerados.ArchivosEnum;
import mx.com.teclo.saicdmx.util.enumerados.EstatusProcesoLote;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;

@Service("deteccionesService")
public class DeteccionesServiceImpl implements DeteccionesService{

	@Autowired
	FMDeteccionesMyBatisDAO fmDeteccionesMyBatisDao;
	
	@Autowired
	RadarArchivoMyBatisDAO radarArchivoMyBatisDAO;
	
	public FMLoteProcesoVO buscarAntiguoLoteEnProceso(){
		return fmDeteccionesMyBatisDao.buscaAntiguoLoteEnProceso();
	}
	
	public FMLoteProcesoVO buscarLoteEnProceso(){
		return fmDeteccionesMyBatisDao.buscaLoteEnProceso();
	}
	
	public DeteccionBatchFinanzasVO cargarLote(Long idLote){
		return fmDeteccionesMyBatisDao.cargarFmLoteDeteccion(idLote);
	}
	
	public void cancelarArchivo(DeteccionBatchFinanzasVO objetoVO, String motivo) throws BusinessException {
		Integer estatus = EstatusProcesoLote.CANCELADO.getEstatusProceso();
		
		Integer deteccionesCanceladas = fmDeteccionesMyBatisDao.buscarCantidadDetecciones(objetoVO.getArchivoId(), 0);
		Integer deteccionesProcesadas = fmDeteccionesMyBatisDao.buscarCantidadDetecciones(objetoVO.getArchivoId(), 1);
		Integer totalDeteccionesCanceladas = deteccionesCanceladas + deteccionesProcesadas;
		
		fmDeteccionesMyBatisDao.cancelaLote(objetoVO.getArchivoId(),new Long(estatus), 
														objetoVO.getModificadoPorId(),
														totalDeteccionesCanceladas, 0, motivo);
		
		radarArchivoMyBatisDAO.cancelaRadarArchivoTotal(objetoVO.getArchivoId(),
				                                    new Long(estatus),
				                                    objetoVO.getModificadoPorId(),
				                                    deteccionesCanceladas, deteccionesProcesadas, motivo);
		
		fmDeteccionesMyBatisDao.reutilizarDetecciones(objetoVO.getArchivoId());
		
		fmDeteccionesMyBatisDao.actualizaDetallesLote(objetoVO.getArchivoId());
		
		fmDeteccionesMyBatisDao.insertaBitacoraProceso(objetoVO.getArchivoId(), objetoVO.getModificadoPorId(), ArchivoBatchEstatusEnum.PROCESO_CANCELADO.getEstatusProceso());
		
		fmDeteccionesMyBatisDao.insertaHistoricoCompleto(objetoVO.getArchivoId());
		fmDeteccionesMyBatisDao.borrarDetecciones(objetoVO.getArchivoId());
		fmDeteccionesMyBatisDao.taiBitCancela(3, objetoVO.getArchivoId(), objetoVO.getModificadoPorId());
	}
	
	public FMLoteResumenVO buscarLoteCancelado(Long idLote){
		return fmDeteccionesMyBatisDao.buscarLoteCancelado(idLote);
	}
	
	public List<FMBitProcesoVO> buscarBitacoraLoteEnProceso(Long idLote){
		return fmDeteccionesMyBatisDao.buscaBitacoraLoteEnProceso(idLote);
	}
	
	/*public List<FMDeteccionCP> buscarDeteccionesFmValidas(Long idLote){
		return fmDeteccionesMyBatisDao.buscaDeteccionesCPValidas(idLote);
	}*/
	public List<FMDeteccionCPV2> buscarDeteccionesFmValidas(Long idLote){
		return fmDeteccionesMyBatisDao.buscaDeteccionesCPValidas(idLote);
	}
	
	public List<FMDeteccionCPV2> buscarDeteccionesRADValidas(Long idLote){
		return fmDeteccionesMyBatisDao.buscaDeteccionesRADCPValidas(idLote);
	}
	
	public List<FMDeteccionCP> buscarAllDeteccionesFmByCP(String CP, Long idLote){
		return fmDeteccionesMyBatisDao.buscaAllDeteccionesByCP(CP,idLote);
	}
	
	public List<FMDeteccionCP> buscarAllDeteccionesRADByCP(String CP, Long idLote){
		return fmDeteccionesMyBatisDao.buscaAllDeteccionesRADByCP(CP,idLote);
	}
	/*public List<FMDeteccionCP> buscarDeteccionesFmInvalidas(Long idLote){
		return fmDeteccionesMyBatisDao.buscaDeteccionesCPInvalidas(idLote);
	}*/
	public List<FMDeteccionCPV2> buscarDeteccionesFmInvalidas(Long idLote){
		return fmDeteccionesMyBatisDao.buscaDeteccionesCPInvalidas(idLote);
	}
	
	public Boolean actualizarFmLoteReasignadoCP(Long idLote, Integer estatusProcesoId, Long idUsuario, Integer enProceso){
		boolean Comprobarproceso = fmDeteccionesMyBatisDao.validarProcesoCancelado(idLote);
		if(Comprobarproceso){
			return false;
		}
		fmDeteccionesMyBatisDao.insertaBitacoraProceso(idLote, idUsuario,estatusProcesoId);
		fmDeteccionesMyBatisDao.actualizarFmLoteReasignadoCP(idLote,estatusProcesoId,idUsuario,enProceso);
		return true;
		
	}
	
	public Boolean actualizarCambiarCP(Long idLote, Integer estatusProcesoId, Long idUsuario, Integer enProceso){
		boolean Comprobarproceso = fmDeteccionesMyBatisDao.validarProcesoCancelado(idLote);
		if(Comprobarproceso){
			return false;
		}
		fmDeteccionesMyBatisDao.insertaBitacoraProceso(idLote, idUsuario,estatusProcesoId);
		fmDeteccionesMyBatisDao.actualizarFmLoteReasignadoCP(idLote,estatusProcesoId,idUsuario,enProceso);
		return true;
		
	}
	
	
	
	public Boolean habilitaFmCambioCP(Long idCp, Long lote,Long idUsuario, String cp){
		//fmDeteccionesMyBatisDao.taiBitModificaCP(5, lote, idUsuario, cp, idCp);
		fmDeteccionesMyBatisDao.habilitaFm(lote, idUsuario, idCp, cp);
		return true;
	}
	
	public Boolean deshabilitaFmCambioCP(Long idCp, Long lote, Long idUsuario){
		fmDeteccionesMyBatisDao.deshabilitaFm(lote, idUsuario, idCp);
		return true;
	}

	@Override
	public Boolean actualizarCambiarCP(int[] ids, String[] newcp, Long usuario) {
		for(int i=0; i<ids.length; i++){
			System.out.println(ids[i]);
			System.out.println(newcp[i]);
			System.out.println("id usuario: "+usuario);
			
			fmDeteccionesMyBatisDao.cambiaCodigoPostal(Long.valueOf(ids[i]), newcp[i], usuario);
		}
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public Boolean actualizarCambiarRADCP(int[] ids, String[] newcp, Long usuario) {
		for(int i=0; i<ids.length; i++){
			System.out.println(ids[i]);
			System.out.println(newcp[i]);
			System.out.println("id usuario: "+usuario);
			
			fmDeteccionesMyBatisDao.cambiaCodigoPostalRAD(Long.valueOf(ids[i]), newcp[i], usuario);
		}
		// TODO Auto-generated method stub
		return true;
	}
}
