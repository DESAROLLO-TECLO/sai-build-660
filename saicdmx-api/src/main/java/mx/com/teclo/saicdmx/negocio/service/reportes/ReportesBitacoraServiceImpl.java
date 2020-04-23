package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.reportes.ReporteBitacoraAnterior;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.CatalogoDinamicoVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.ParametrosBusquedaReporteBitacoraVO;
import mx.com.teclo.saicdmx.persistencia.vo.reportes.TcaBitacoraCambiosVO;

@Service
public class ReportesBitacoraServiceImpl implements ReportesBitacoraService{

	@Autowired 
	private ReporteBitacoraAnterior bitacoraAnterior;
	
	private ByteArrayOutputStream  reporte;
	
	@Autowired
	private GenerarReporteExcelBitacora ExcelBitacora;
	

	@Override
	public List<CatalogoDinamicoVO> consultaComponentesBitacora(){
		return bitacoraAnterior.obtenerListaComponentes() ;
	}
	
	@Override
	public List<CatalogoDinamicoVO> consultaConceptosPorConponenteId(long componenteId){
		return bitacoraAnterior.obtenerListaConceptos(componenteId);
	}
	
	@Override
	public List<TcaBitacoraCambiosVO> consultaBitacoraCambios(ParametrosBusquedaReporteBitacoraVO parametrosBusquedaVO) {
		String ListaConceptosId="";
		for(int x = 0; x<parametrosBusquedaVO.getConceptosId().size();x++){
			if(x + 1 == parametrosBusquedaVO.getConceptosId().size()){
				ListaConceptosId=ListaConceptosId+parametrosBusquedaVO.getConceptosId().get(x);
			}else{
				ListaConceptosId=ListaConceptosId+parametrosBusquedaVO.getConceptosId().get(x)+",";
			}
		}
		
		List<TcaBitacoraCambiosVO> cambiosBitacora = bitacoraAnterior.consultaCambiosBitacora(parametrosBusquedaVO.getConponenteId(),
				                                                                              ListaConceptosId,
				                                                                              parametrosBusquedaVO.getFechaInicio(),
				                                                                              parametrosBusquedaVO.getFehaFin());
		if(!cambiosBitacora.isEmpty()){
			this.reporte = ExcelBitacora.generarReporteExcel(cambiosBitacora,"Bitácora Cambios",
					                                         parametrosBusquedaVO.getFechaInicio(),
					                                         parametrosBusquedaVO.getFehaFin(),
					                                         parametrosBusquedaVO.getComponente(),
					                                         parametrosBusquedaVO.getConcepto());
		}
		return cambiosBitacora;
	}
	

	/** Metodos para Reporte Bitacora Anterior**/
	
	@Override
	public List<CatalogoDinamicoVO> obtenerListaComponentes() {
		List<CatalogoDinamicoVO> ListaComponentes = bitacoraAnterior.CatalogoComponentesAnterior();
		return ListaComponentes;
	}
	
	@Override
	public List<CatalogoDinamicoVO> obtenerListaConceptos(int componenteId){
		List<CatalogoDinamicoVO> ListaConceptos = bitacoraAnterior.CatalogoConceptosAnterior(componenteId);
		return ListaConceptos;
	}

	@Override
	public List<TcaBitacoraCambiosVO> consultaBitacoraCambiosAnterior(ParametrosBusquedaReporteBitacoraVO parametrosBusquedaVO) {
		String ListaConceptosId="";
		for(int x = 0; x<parametrosBusquedaVO.getConceptosId().size();x++){
			if(x + 1 == parametrosBusquedaVO.getConceptosId().size()){
				ListaConceptosId=ListaConceptosId+parametrosBusquedaVO.getConceptosId().get(x);
			}else{
				ListaConceptosId=ListaConceptosId+parametrosBusquedaVO.getConceptosId().get(x)+",";
			}
		}
		List<TcaBitacoraCambiosVO> cambiosBitacora = bitacoraAnterior.consultaCambiosBitAnterior(parametrosBusquedaVO.getConponenteId(),
				                                                                                 ListaConceptosId,
				                                                                                 parametrosBusquedaVO.getFechaInicio(),
				                                                                                 parametrosBusquedaVO.getFehaFin());
		if(!cambiosBitacora.isEmpty()){
			this.reporte = ExcelBitacora.generarReporteExcel(cambiosBitacora,"Bitácora Cambios Histórico",
					                                                          parametrosBusquedaVO.getFechaInicio(),
					                                                          parametrosBusquedaVO.getFehaFin(),
					                                                          parametrosBusquedaVO.getComponente(),
										                                      parametrosBusquedaVO.getConcepto());
		}
		return cambiosBitacora;
	}

	@Override
	public ByteArrayOutputStream DescargarExcelBitacora() {
		return this.reporte;
	}





	
}
