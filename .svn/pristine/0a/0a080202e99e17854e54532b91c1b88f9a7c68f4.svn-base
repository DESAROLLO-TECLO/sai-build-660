package mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FOTOMULTA_CAT_TIPO_RADAR")
public class FotomultaTipoRadarDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5692850839544888460L;


	@Id
	@Column(name = "CAT_TIPO_RADAR_ID")
	private Integer radarTipoId;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "CODIGO")
	private String codigo;
	
	@Column(name = "ACTIVO")
	private Integer activo;
	
	@Column(name = "CREADOR_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	public Integer getRadarTipoId() {
		return radarTipoId;
	}

	public void setRadarTipoId(Integer radarTipoId) {
		this.radarTipoId = radarTipoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
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

	
	
	
	
}
