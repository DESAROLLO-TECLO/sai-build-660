package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DEPOSITOS")
public class DepositosDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5692457374775928430L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "DEP_ID", unique = true, nullable = false)
	private Long depId;
	
	@Column(name = "DEP_COD")
	private String depCod;
	
	@Column(name = "DEP_NOMBRE")
	private String depNombre;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="ZONA_ID", referencedColumnName="ZONA_ID", insertable=false, updatable=false)
	private ZonasDTO zonaDTO;

	@Column(name = "DEP_DIRECCION")
	private String depDireccion;
	
	@Column(name = "DEP_COLONIA")
	private String depColonia;
	
	@Column(name = "DEP_TELEFONO")
	private String depTelefono;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="EDO_ID", referencedColumnName="EDO_ID", insertable=false, updatable=false)
	private EstadoDTO estado;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({
        @JoinColumn(name="EDO_ID", referencedColumnName="EDO_ID", insertable=false, updatable=false),
        @JoinColumn(name="DEL_ID", referencedColumnName="DEL_ID", insertable=false, updatable=false)
    })
	private DelegacionDTO delegacion;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({
        @JoinColumn(name="EDO_ID", referencedColumnName="EDO_ID"),
        @JoinColumn(name="REG_ID", referencedColumnName="REG_ID")
    })
	private RegionDTO region;
	
	@Column(name = "DEP_SUPERFICIE")
	private Long depSuperficie;
	
	@Column(name = "DEP_CAPACIDAD")
	private Long depCapacidad;
	
	@Column(name = "DEP_STATUS")
	private String depEstatus;
	
	@Column(name = "DEP_USUARIO")
	private String depUsuario;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;

	public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public String getDepCod() {
		return depCod;
	}

	public void setDepCod(String depCod) {
		this.depCod = depCod;
	}

	public String getDepNombre() {
		return depNombre;
	}

	public void setDepNombre(String depNombre) {
		this.depNombre = depNombre;
	}

	public ZonasDTO getZonaDTO() {
		return zonaDTO;
	}

	public void setZonaDTO(ZonasDTO zonaDTO) {
		this.zonaDTO = zonaDTO;
	}

	public String getDepDireccion() {
		return depDireccion;
	}

	public void setDepDireccion(String depDireccion) {
		this.depDireccion = depDireccion;
	}

	public String getDepColonia() {
		return depColonia;
	}

	public void setDepColonia(String depColonia) {
		this.depColonia = depColonia;
	}

	public String getDepTelefono() {
		return depTelefono;
	}

	public void setDepTelefono(String depTelefono) {
		this.depTelefono = depTelefono;
	}

	public EstadoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}

	public DelegacionDTO getDelegacion() {
		return delegacion;
	}

	public void setDelegacion(DelegacionDTO delegacion) {
		this.delegacion = delegacion;
	}

	public RegionDTO getRegion() {
		return region;
	}

	public void setRegion(RegionDTO region) {
		this.region = region;
	}

	public Long getDepSuperficie() {
		return depSuperficie;
	}

	public void setDepSuperficie(Long depSuperficie) {
		this.depSuperficie = depSuperficie;
	}

	public Long getDepCapacidad() {
		return depCapacidad;
	}

	public void setDepCapacidad(Long depCapacidad) {
		this.depCapacidad = depCapacidad;
	}

	public String getDepEstatus() {
		return depEstatus;
	}

	public void setDepEstatus(String depEstatus) {
		this.depEstatus = depEstatus;
	}

	public String getDepUsuario() {
		return depUsuario;
	}

	public void setDepUsuario(String depUsuario) {
		this.depUsuario = depUsuario;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public Date getUltimaMod() {
		return ultimaMod;
	}

	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}
	
}
