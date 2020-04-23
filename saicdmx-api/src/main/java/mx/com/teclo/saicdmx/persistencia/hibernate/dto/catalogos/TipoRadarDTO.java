package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FOTOMULTA_CAT_TIPO_RADAR")
public class TipoRadarDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -609178404255904992L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CAT_TIPO_RADAR_ID", unique = true, nullable = false)
	private Long tipoRadarId;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "CODIGO")
	private String codigo;
	
	@Column(name = "ACTIVO")
	private Long activo;
	
	@Column(name = "CREADOR_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	/**
	 * @return the tipoRadarId
	 */
	public Long getTipoRadarId() {
		return tipoRadarId;
	}

	/**
	 * @param tipoRadarId the tipoRadarId to set
	 */
	public void setTipoRadarId(Long tipoRadarId) {
		this.tipoRadarId = tipoRadarId;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the activo
	 */
	public Long getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Long activo) {
		this.activo = activo;
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
	
	
}
