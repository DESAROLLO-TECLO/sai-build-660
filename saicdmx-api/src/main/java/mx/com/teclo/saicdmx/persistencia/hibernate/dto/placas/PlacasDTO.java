package mx.com.teclo.saicdmx.persistencia.hibernate.dto.placas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	
	@Entity
	@Table(name="TAI006_FM_VALIDA_MATRICULA")
	public class PlacasDTO implements Serializable{

		private static final long serialVersionUID = 8210820947982004389L;


		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "ID", unique = true, nullable = false)
		private Long placaId;
		
		@Column(name = "PLACA")
		private String placaCodigo;
		
		@Column(name = "ACTIVO")
		private String placaStatus;
		
		@Column(name = "CD_CREADOPOR")
		private Long creadoPor;
		
		@Column(name = "FH_CREACION")
		private Date fechaCreacion;
		
		@Column(name = "CD_MODIFICADOPOR")
		private Long modificadoPor;
		
		
		@Column(name = "FH_MODIFICACION")
		private Date ultimaModificacion;
		
		@Column(name="OBSERVACIONES")
		private String observaciones;


		public Long getPlacaId() {
			return placaId;
		}


		public void setPlacaId(Long placaId) {
			this.placaId = placaId;
		}


		public String getPlacaCodigo() {
			return placaCodigo;
		}


		public void setPlacaCodigo(String placaCodigo) {
			this.placaCodigo = placaCodigo;
		}


		public String getPlacaStatus() {
			return placaStatus;
		}


		public void setPlacaStatus(String placaStatus) {
			this.placaStatus = placaStatus;
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

		
		public String getObservaciones() {
			return observaciones;
		}


		public void setObservaciones(String observaciones) {
			this.observaciones = observaciones;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
		
	}


