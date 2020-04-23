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
@Table(name="VEHICULO_SUBTIPO")
public class VehiculoSubTipoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 120907899469256630L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "VSUBT_ID", unique = true, nullable = false)
	private Long vSubTipoId;
	
	@Column(name = "VSUBT_COD")
	private String vSubTipoCodigo;
	
	@Column(name = "VSUBT_NOMBRE")
	private String vSubTipoNombre;
	
	@Column(name = "VSUBT_STATUS")
	private String vSubTipoStatus;
	
	@Column(name = "CREADO_POR")
	private Long creadoPor;
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "MODIFICADO_POR")
	private Long modPor;
	
	@Column(name = "ULTIMA_MODIFICACION")
	private Date ultimaMod;

	public Long getvSubTipoId() {
		return vSubTipoId;
	}

	public void setvSubTipoId(Long vSubTipoId) {
		this.vSubTipoId = vSubTipoId;
	}

	public String getvSubTipoCodigo() {
		return vSubTipoCodigo;
	}

	public void setvSubTipoCodigo(String vSubTipoCodigo) {
		this.vSubTipoCodigo = vSubTipoCodigo;
	}

	public String getvSubTipoNombre() {
		return vSubTipoNombre;
	}

	public void setvSubTipoNombre(String vSubTipoNombre) {
		this.vSubTipoNombre = vSubTipoNombre;
	}

	public String getvSubTipoStatus() {
		return vSubTipoStatus;
	}

	public void setvSubTipoStatus(String vSubTipoStatus) {
		this.vSubTipoStatus = vSubTipoStatus;
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

	public Long getModPor() {
		return modPor;
	}

	public void setModPor(Long modPor) {
		this.modPor = modPor;
	}

	public Date getUltimaMod() {
		return ultimaMod;
	}

	public void setUltimaMod(Date ultimaMod) {
		this.ultimaMod = ultimaMod;
	}
	
}
