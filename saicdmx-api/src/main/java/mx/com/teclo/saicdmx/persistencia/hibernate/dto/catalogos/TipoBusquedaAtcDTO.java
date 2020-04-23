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
@Table(name="TAI048C_TPBUS_TRAMITE")
public class TipoBusquedaAtcDTO implements Serializable {
	
	private static final long serialVersionUID = 4646106535586083018L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID_PARAMETRO", unique = true, nullable = false)
	private Integer idParametro;
	
	@Column(name = "CD_PARAMETRO")
	private String parametroCod;
	
	@Column(name = "TX_DESCRIPCION")
	private String parametroDesc;
	
	@Column(name = "CD_ORDEN")
	private String cdOrden;
	
	@Column(name = "ST_ACTIVO")
	private Integer stActivo;
	
	@Column(name = "ID_USR_CREACION")
	private Long creadoPor;
	
	@Column(name = "FH_CREACION")
	private Date fechaCreacion;
	
	@Column(name = "ID_USR_MODIFICA")
	private Long modificadoPor;
	
	@Column(name = "FH_MODIFICACION")
	private Date ultimaModificacion;

	public Integer getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getParametroCod() {
		return parametroCod;
	}

	public void setParametroCod(String parametroCod) {
		this.parametroCod = parametroCod;
	}

	public String getParametroDesc() {
		return parametroDesc;
	}

	public void setParametroDesc(String parametroDesc) {
		this.parametroDesc = parametroDesc;
	}

	public String getCdOrden() {
		return cdOrden;
	}

	public void setCdOrden(String cdOrden) {
		this.cdOrden = cdOrden;
	}

	public Integer getStActivo() {
		return stActivo;
	}

	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
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
