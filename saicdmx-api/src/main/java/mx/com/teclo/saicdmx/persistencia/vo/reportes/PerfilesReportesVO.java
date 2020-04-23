package mx.com.teclo.saicdmx.persistencia.vo.reportes;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.PerfilesAdminVO;

public class PerfilesReportesVO {

	private Long idPerfilReporte;
	private PerfilesAdminVO perfil;
	private ReportesTaqVO reporte;
	private Integer stActivo;
	private PerfilReporteIdVO id;

	public Long getIdPerfilReporte() {
		return idPerfilReporte;
	}

	public void setIdPerfilReporte(Long idPerfilReporte) {
		this.idPerfilReporte = idPerfilReporte;
	}

	public PerfilesAdminVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilesAdminVO perfil) {
		this.perfil = perfil;
	}

	public ReportesTaqVO getReporte() {
		return reporte;
	}

	public void setReporte(ReportesTaqVO reporte) {
		this.reporte = reporte;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}

	public PerfilReporteIdVO getId() {
		return id;
	}

	public void setId(PerfilReporteIdVO id) {
		this.id = id;
	}

}
