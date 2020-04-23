package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CATALOGOS_WEB_OPCIONES")
public class CatalogoWebOpcionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7654996333957696113L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_OPCION", unique = true, nullable = false)
	private Long opcionId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CATALOGO")
	private CatalogoWebDTO catalogoWebDTO;
	
	@Column(name = "DESCR_OPCION")
	private String opcionDesc;
	
	@Column(name = "URL")
	private String opcionUrl;
	
	@Column(name = "ESTATUS")
	private Long opcionEstatus;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_MODIFICACION")
	private Date fechaModificacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;

	public Long getOpcionId() {
		return opcionId;
	}

	public void setOpcionId(Long opcionId) {
		this.opcionId = opcionId;
	}

	public CatalogoWebDTO getCatalogoWebDTO() {
		return catalogoWebDTO;
	}

	public void setCatalogoWebDTO(CatalogoWebDTO catalogoWebDTO) {
		this.catalogoWebDTO = catalogoWebDTO;
	}

	public String getOpcionDesc() {
		return opcionDesc;
	}

	public void setOpcionDesc(String opcionDesc) {
		this.opcionDesc = opcionDesc;
	}

	public String getOpcionUrl() {
		return opcionUrl;
	}

	public void setOpcionUrl(String opcionUrl) {
		this.opcionUrl = opcionUrl;
	}

	public Long getOpcionEstatus() {
		return opcionEstatus;
	}

	public void setOpcionEstatus(Long opcionEstatus) {
		this.opcionEstatus = opcionEstatus;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
}
