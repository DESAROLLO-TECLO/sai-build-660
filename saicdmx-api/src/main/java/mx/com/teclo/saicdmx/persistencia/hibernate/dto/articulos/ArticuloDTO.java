package mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ARTICULOS")
public class ArticuloDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5296348770854465690L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ART_ID", unique = true, nullable = false)
	private Long artId;
	@Column(name = "CATEG_ID")
	private Long categId;
	@Column(name = "ART_MOTIVACION")
	private String artMotivacion;
	@Column(name = "PROG_ID")
	private Long progId;
	@Column(name = "CAUSAL_ID")
	private Long causalId;
	@Column(name = "ART_NUMERO")
	private String artNumero;
	@Column(name = "ART_FRACCION")
	private String artFraccion;
	@Column(name = "ART_PARRAFO")
	private String artParrafo;
	@Column(name = "ART_INCISO")
	private String artInciso;
	@Column(name = "ART_DIAS")
	private Integer artDias;
	@Column(name = "ART_DESCUENTO")
	private String artDescuento;
	@Column(name = "ART_PORCEN_DESC")
	private BigDecimal artPorcenDesc;
	@Column(name = "ART_TIPO")
	private String artTipo;
	@Column(name = "ART_STATUS")
	private String artStatus;
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	/*@Column(name = "REGLAMENTO_ID")
	private Integer reglamentoId;
	
	@OneToMany(mappedBy = "sancionId.articulo")
	private Set<SancionesArticulosDTO> sancionesArticulos;
	@OneToMany(mappedBy = "articulo")
	private Set<ArtCatObsDTO> artCatObs;*/
	/**
	 * @return the artId
	 */
	public Long getArtId() {
		return artId;
	}
	/**
	 * @param artId the artId to set
	 */
	public void setArtId(Long artId) {
		this.artId = artId;
	}
	/**
	 * @return the categId
	 */
	public Long getCategId() {
		return categId;
	}
	/**
	 * @param categId the categId to set
	 */
	public void setCategId(Long categId) {
		this.categId = categId;
	}
	/**
	 * @return the artMotivacion
	 */
	public String getArtMotivacion() {
		return artMotivacion;
	}
	/**
	 * @param artMotivacion the artMotivacion to set
	 */
	public void setArtMotivacion(String artMotivacion) {
		this.artMotivacion = artMotivacion;
	}
	/**
	 * @return the progId
	 */
	public Long getProgId() {
		return progId;
	}
	/**
	 * @param progId the progId to set
	 */
	public void setProgId(Long progId) {
		this.progId = progId;
	}
	/**
	 * @return the causalId
	 */
	public Long getCausalId() {
		return causalId;
	}
	/**
	 * @param causalId the causalId to set
	 */
	public void setCausalId(Long causalId) {
		this.causalId = causalId;
	}
	/**
	 * @return the artNumero
	 */
	public String getArtNumero() {
		return artNumero;
	}
	/**
	 * @param artNumero the artNumero to set
	 */
	public void setArtNumero(String artNumero) {
		this.artNumero = artNumero;
	}
	/**
	 * @return the artFraccion
	 */
	public String getArtFraccion() {
		return artFraccion;
	}
	/**
	 * @param artFraccion the artFraccion to set
	 */
	public void setArtFraccion(String artFraccion) {
		this.artFraccion = artFraccion;
	}
	/**
	 * @return the artParrafo
	 */
	public String getArtParrafo() {
		return artParrafo;
	}
	/**
	 * @param artParrafo the artParrafo to set
	 */
	public void setArtParrafo(String artParrafo) {
		this.artParrafo = artParrafo;
	}
	/**
	 * @return the artInciso
	 */
	public String getArtInciso() {
		return artInciso;
	}
	/**
	 * @param artInciso the artInciso to set
	 */
	public void setArtInciso(String artInciso) {
		this.artInciso = artInciso;
	}
	/**
	 * @return the artDias
	 */
	public Integer getArtDias() {
		return artDias;
	}
	/**
	 * @param artDias the artDias to set
	 */
	public void setArtDias(Integer artDias) {
		this.artDias = artDias;
	}
	/**
	 * @return the artDescuento
	 */
	public String getArtDescuento() {
		return artDescuento;
	}
	/**
	 * @param artDescuento the artDescuento to set
	 */
	public void setArtDescuento(String artDescuento) {
		this.artDescuento = artDescuento;
	}
	/**
	 * @return the artPorcenDesc
	 */
	public BigDecimal getArtPorcenDesc() {
		return artPorcenDesc;
	}
	/**
	 * @param artPorcenDesc the artPorcenDesc to set
	 */
	public void setArtPorcenDesc(BigDecimal artPorcenDesc) {
		this.artPorcenDesc = artPorcenDesc;
	}
	/**
	 * @return the artTipo
	 */
	public String getArtTipo() {
		return artTipo;
	}
	/**
	 * @param artTipo the artTipo to set
	 */
	public void setArtTipo(String artTipo) {
		this.artTipo = artTipo;
	}
	/**
	 * @return the artStatus
	 */
	public String getArtStatus() {
		return artStatus;
	}
	/**
	 * @param artStatus the artStatus to set
	 */
	public void setArtStatus(String artStatus) {
		this.artStatus = artStatus;
	}
	/**
	 * @return the creadoPor
	 */
	public Long getCreadoPor() {
		return creadoPor;
	}
	/**
	 * @param creadoPor the creadoPor to set
	 */
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the modificadoPor
	 */
	public Long getModificadoPor() {
		return modificadoPor;
	}
	/**
	 * @param modificadoPor the modificadoPor to set
	 */
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
	/* *
	 * @return the reglamentoId
	 * /
	public Integer getReglamentoId() {
		return reglamentoId;
	}
	/**
	 * @param reglamentoId the reglamentoId to set
	 * /
	public void setReglamentoId(Integer reglamentoId) {
		this.reglamentoId = reglamentoId;
	}
	/**
	 * @return the artCatObs
	 * /
	public Set<ArtCatObsDTO> getArtCatObs() {
		return artCatObs;
	}
	/**
	 * @param artCatObs the artCatObs to set
	 * /
	public void setArtCatObs(Set<ArtCatObsDTO> artCatObs) {
		this.artCatObs = artCatObs;
	}
	
	public Set<SancionesArticulosDTO> getSancionesArticulos() 
	{
		return sancionesArticulos;
	}
	
	public void setSancionesArticulos(Set<SancionesArticulosDTO> sancionesArticulos) 
	{
		this.sancionesArticulos = sancionesArticulos;
	}*/
}
