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
@Table(name="CATEGORIAS")
public class CategoriaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5415113092785378293L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CATEG_ID", unique = true, nullable = false)
	private Long categoriaId;
	
	@Column(name = "CATEG_COD")
	private String categoriaCodigo;
	
	@Column(name = "CATEG_DESCRIP")
	private String categoriaDesc;
	
	@Column(name = "CATEG_STATUS")
	private String categoriaStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modificadoPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaModificacion;
	
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getCategoriaCodigo() {
		return categoriaCodigo;
	}
	public void setCategoriaCodigo(String categoriaCodigo) {
		this.categoriaCodigo = categoriaCodigo;
	}
	public String getCategoriaDesc() {
		return categoriaDesc;
	}
	public void setCategoriaDesc(String categoriaDesc) {
		this.categoriaDesc = categoriaDesc;
	}
	public String getCategoriaStatus() {
		return categoriaStatus;
	}
	public void setCategoriaStatus(String categoriaStatus) {
		this.categoriaStatus = categoriaStatus;
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
	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}
	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}
}
