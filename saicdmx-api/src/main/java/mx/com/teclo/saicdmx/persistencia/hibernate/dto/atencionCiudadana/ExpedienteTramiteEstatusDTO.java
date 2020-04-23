package mx.com.teclo.saicdmx.persistencia.hibernate.dto.atencionCiudadana;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoTramiteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;

@Entity
@Table(name = "TAI045D_AC_TRAMITES")
public class ExpedienteTramiteEstatusDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8916393154519209502L;
	
	@Id
	@Column(name = "ID_AC_TRAMITE", unique = true, nullable = false)
	private String folioTramite;
	@Column(name = "ST_EXPEDIENTE")
	private boolean stExpediente;
	@Column(name = "ID_USR_MODIFICA")
	private Long modificadoPor;
	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;
	
	public String getFolioTramite() {
		return folioTramite;
	}
	public void setFolioTramite(String folioTramite) {
		this.folioTramite = folioTramite;
	}
	public boolean isStExpediente() {
		return stExpediente;
	}
	public void setStExpediente(boolean stExpediente) {
		this.stExpediente = stExpediente;
	}
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	public Date getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
}
