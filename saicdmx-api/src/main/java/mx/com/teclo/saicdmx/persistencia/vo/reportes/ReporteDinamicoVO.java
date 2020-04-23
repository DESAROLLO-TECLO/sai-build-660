package mx.com.teclo.saicdmx.persistencia.vo.reportes;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.admireporte.TipoReporteVO;

public class ReporteDinamicoVO {

	List<TipoReporteVO> tipoReporte;
	List<ReportesTaqLiteVO> reportes;

	public List<TipoReporteVO> getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(List<TipoReporteVO> tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public List<ReportesTaqLiteVO> getReportes() {
		return reportes;
	}

	public void setReportes(List<ReportesTaqLiteVO> reportes) {
		this.reportes = reportes;
	}

}
