package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

import mx.com.teclo.saicdmx.persistencia.vo.articulos.ArticuloVO;

public class CrudArticulosHistoricoVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7104060531674549690L;
	
	private Long artInfrFinArticulo;
	private Long artInfrFinFraccion;
	private String artInfrFinInciso;
	private String artInfrFinParrafo;
	private String artInfrFinDescripcion;
	private Long artInfrFinSalario;
	private Long artInfrFinAplicadoVeh;
	private String artInfrFinArrastro;
	private Long artInfrFinPuntos;
	private Long artInfrFinCons;
	private String artInfrFinFechaInicio;
	private String artInfrFinFechaTermino;
	private ArticuloVO articulo;
	
	public Long getArtInfrFinArticulo() {
		return artInfrFinArticulo;
	}
	public void setArtInfrFinArticulo(Long artInfrFinArticulo) {
		this.artInfrFinArticulo = artInfrFinArticulo;
	}
	public Long getArtInfrFinFraccion() {
		return artInfrFinFraccion;
	}
	public void setArtInfrFinFraccion(Long artInfrFinFraccion) {
		this.artInfrFinFraccion = artInfrFinFraccion;
	}
	public String getArtInfrFinInciso() {
		return artInfrFinInciso;
	}
	public void setArtInfrFinInciso(String artInfrFinInciso) {
		this.artInfrFinInciso = artInfrFinInciso;
	}
	public String getArtInfrFinParrafo() {
		return artInfrFinParrafo;
	}
	public void setArtInfrFinParrafo(String artInfrFinParrafo) {
		this.artInfrFinParrafo = artInfrFinParrafo;
	}
	public String getArtInfrFinDescripcion() {
		return artInfrFinDescripcion;
	}
	public void setArtInfrFinDescripcion(String artInfrFinDescripcion) {
		this.artInfrFinDescripcion = artInfrFinDescripcion;
	}
	public Long getArtInfrFinSalario() {
		return artInfrFinSalario;
	}
	public void setArtInfrFinSalario(Long artInfrFinSalario) {
		this.artInfrFinSalario = artInfrFinSalario;
	}
	public Long getArtInfrFinAplicadoVeh() {
		return artInfrFinAplicadoVeh;
	}
	public void setArtInfrFinAplicadoVeh(Long artInfrFinAplicadoVeh) {
		this.artInfrFinAplicadoVeh = artInfrFinAplicadoVeh;
	}
	public String getArtInfrFinArrastro() {
		return artInfrFinArrastro;
	}
	public void setArtInfrFinArrastro(String artInfrFinArrastro) {
		this.artInfrFinArrastro = artInfrFinArrastro;
	}
	public Long getArtInfrFinPuntos() {
		return artInfrFinPuntos;
	}
	public void setArtInfrFinPuntos(Long artInfrFinPuntos) {
		this.artInfrFinPuntos = artInfrFinPuntos;
	}
	public Long getArtInfrFinCons() {
		return artInfrFinCons;
	}
	public void setArtInfrFinCons(Long artInfrFinCons) {
		this.artInfrFinCons = artInfrFinCons;
	}
	public String getArtInfrFinFechaInicio() {
		return artInfrFinFechaInicio;
	}
	public void setArtInfrFinFechaInicio(String artInfrFinFechaInicio) {
		this.artInfrFinFechaInicio = artInfrFinFechaInicio;
	}
	public String getArtInfrFinFechaTermino() {
		return artInfrFinFechaTermino;
	}
	public void setArtInfrFinFechaTermino(String artInfrFinFechaTermino) {
		this.artInfrFinFechaTermino = artInfrFinFechaTermino;
	}
	public ArticuloVO getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloVO articulo) {
		this.articulo = articulo;
	}
}
