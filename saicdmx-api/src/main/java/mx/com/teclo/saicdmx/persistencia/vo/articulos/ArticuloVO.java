package mx.com.teclo.saicdmx.persistencia.vo.articulos;

import java.io.Serializable;
import java.math.BigDecimal;

public class ArticuloVO implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5048576113810805110L;
	
	private Long artId;
	private Long categId;
	private String artMotivacion;
	private Long progId;
	private Long causalId;
	private String artNumero;
	private String artFraccion;
	private String artParrafo;
	private String artInciso;
	private Integer artDias;
	private String artDescuento;
	private BigDecimal artPorcenDesc;
	private String artTipo;
	private String artStatus;
	private Integer reglamentoId;
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
	public Long getCausalId() {
		return causalId;
	}
	public void setCausalId(Long causalId) {
		this.causalId = causalId;
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
	public Integer getArtDias() {
		return artDias;
	}
	public void setArtDias(Integer artDias) {
		this.artDias = artDias;
	}
	public String getArtDescuento() {
		return artDescuento;
	}
	public void setArtDescuento(String artDescuento) {
		this.artDescuento = artDescuento;
	}
	public BigDecimal getArtPorcenDesc() {
		return artPorcenDesc;
	}
	public void setArtPorcenDesc(BigDecimal artPorcenDesc) {
		this.artPorcenDesc = artPorcenDesc;
	}
	public String getArtTipo() {
		return artTipo;
	}
	public void setArtTipo(String artTipo) {
		this.artTipo = artTipo;
	}
	public String getArtStatus() {
		return artStatus;
	}
	public void setArtStatus(String artStatus) {
		this.artStatus = artStatus;
	}
	public Integer getReglamentoId() {
		return reglamentoId;
	}
	public void setReglamentoId(Integer reglamentoId) {
		this.reglamentoId = reglamentoId;
	}
	public String getStatusDesc() {
		if (this.getArtStatus() != null) {
			return this.getArtStatus().equals("A") ? "Activo" : "Cancelado";
		}
		return null;
	}
}
