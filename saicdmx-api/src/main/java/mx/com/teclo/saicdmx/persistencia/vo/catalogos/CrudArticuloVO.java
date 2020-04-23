package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudArticuloVO extends BaseCrudVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6767188380988209609L;
	
	private Long artId;
	private Long categId;
	private String artMotivacion;
	private Long progId;
	private Long causalId;
	private String artNumero;
	private String artFraccion;
	private String artParrafo;
	private String parrafo;
	private String artInciso;
	private Long artDias;
	private String artDescuento;
	private Long artPorcenDesc;
	private String artTipo;
	private String artEstatus;
	private String fraccion;
	private String inciso;
	

	public Long getCausalId() {
		return causalId;
	}
	public void setCausalId(Long causalId) {
		this.causalId = causalId;
	}
	public String getArtTipo() {
		return artTipo;
	}
	public void setArtTipo(String artTipo) {
		this.artTipo = artTipo;
	}
	public Long getArtId() {
		return artId;
	}
	public void setArtId(Long artId) {
		this.artId = artId;
	}
	public Long getCategId() {
		return categId;
	}
	public void setCategId(Long categId) {
		this.categId = categId;
	}
	public String getArtMotivacion() {
		return artMotivacion;
	}
	public void setArtMotivacion(String artMotivacion) {
		this.artMotivacion = artMotivacion;
	}
	public Long getProgId() {
		return progId;
	}
	public void setProgId(Long progId) {
		this.progId = progId;
	}
	public String getArtNumero() {
		return artNumero;
	}
	public void setArtNumero(String artNumero) {
		this.artNumero = artNumero;
	}
	public String getArtFraccion() {
		return artFraccion;
	}
	public void setArtFraccion(String artFraccion) {
		this.artFraccion = artFraccion;
	}
	public String getArtParrafo() {
		return artParrafo;
	}
	public void setArtParrafo(String artParrafo) {
		this.artParrafo = artParrafo;
	}
	public String getArtInciso() {
		return artInciso;
	}
	public void setArtInciso(String artInciso) {
		this.artInciso = artInciso;
	}
	public Long getArtDias() {
		return artDias;
	}
	public void setArtDias(Long artDias) {
		this.artDias = artDias;
	}
	public String getArtDescuento() {
		return artDescuento;
	}
	public void setArtDescuento(String artDescuento) {
		this.artDescuento = artDescuento;
	}
	public Long getArtPorcenDesc() {
		return artPorcenDesc;
	}
	public void setArtPorcenDesc(Long artPorcenDesc) {
		this.artPorcenDesc = artPorcenDesc;
	}
	public String getArtEstatus() {
		return artEstatus;
	}
	public void setArtEstatus(String artEstatus) {
		this.artEstatus = artEstatus;
	}
	public String getFraccion() {
		return fraccion;
	}
	public void setFraccion(String fraccion) {
		this.fraccion = fraccion;
	}
	public String getParrafo() {
		return parrafo;
	}
	public void setParrafo(String parrafo) {
		this.parrafo = parrafo;
	}
	public String getInciso() {
		return inciso;
	}
	public void setInciso(String inciso) {
		this.inciso = inciso;
	}
}
