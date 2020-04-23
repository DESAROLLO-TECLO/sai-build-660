package mx.com.teclo.saicdmx.persistencia.vo.fm;

public class FMDeteccionesLayoutVO {
	
	private Long idTipoDeteccion;
	private Long idOrigenPlaca;
	private Boolean stActivo;
	private String cdCamposObligatorios;
	private String cdCamposOpcionales;
	private String cdCatEntidades;
	private String cdCatDelegaciones;
	private String cdCatArticulos;
	
	public Long getIdTipoDeteccion() {
		return idTipoDeteccion;
	}
	public void setIdTipoDeteccion(Long idTipoDeteccion) {
		this.idTipoDeteccion = idTipoDeteccion;
	}
	public Long getIdOrigenPlaca() {
		return idOrigenPlaca;
	}
	public void setIdOrigenPlaca(Long idOrigenPlaca) {
		this.idOrigenPlaca = idOrigenPlaca;
	}
	public Boolean getStActivo() {
		return stActivo;
	}
	public void setStActivo(Boolean stActivo) {
		this.stActivo = stActivo;
	}
	public String getCdCamposObligatorios() {
		return cdCamposObligatorios;
	}
	public void setCdCamposObligatorios(String cdCamposObligatorios) {
		this.cdCamposObligatorios = cdCamposObligatorios;
	}
	public String getCdCamposOpcionales() {
		return cdCamposOpcionales;
	}
	public void setCdCamposOpcionales(String cdCamposOpcionales) {
		this.cdCamposOpcionales = cdCamposOpcionales;
	}
	public String getCdCatEntidades() {
		return cdCatEntidades;
	}
	public void setCdCatEntidades(String cdCatEntidades) {
		this.cdCatEntidades = cdCatEntidades;
	}
	public String getCdCatDelegaciones() {
		return cdCatDelegaciones;
	}
	public void setCdCatDelegaciones(String cdCatDelegaciones) {
		this.cdCatDelegaciones = cdCatDelegaciones;
	}
	public String getCdCatArticulos() {
		return cdCatArticulos;
	}
	public void setCdCatArticulos(String cdCatArticulos) {
		this.cdCatArticulos = cdCatArticulos;
	}
}
