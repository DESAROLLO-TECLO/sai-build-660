package mx.com.teclo.saicdmx.persistencia.hibernate.dto.semovi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICULO_ROBADO")
public class SemoviVehiculoRobadoDTO implements Serializable{

		private static final long serialVersionUID = -3097599492876542994L;
		
		@Id
		@Column(name = "ID_ROBO")
		private Long idRobo;
		@Column(name = "TURNO_DGANT")
		private String turnoDgant;
		@Column(name = "EXPEDIENTE")
		private String expediente;
		@Column(name = "FECHA_ROBO")
		private Date fechaRobo;
		@Column(name = "ID_ESTATUS")
		private long idEstatus;
		@Column(name = "PLACA_VEHICULO")
		private String placaVehiculo;
		@Column(name = "NUM_MOTOR")
		private String numMotor;
		@Column(name = "NUM_SERIE")
		private String numSerie;	
		@Column(name = "MAR_ID")
		private Long idMar;
		@Column(name = "MOD_ID")
		private Long idMod;
		@Column(name = "MOD_ANIO_ID")
		private Long idAnio;
		@Column(name = "COLOR_ID")
		private Long idColor;
		
		@Column(name = "CREADO_POR")
		private Long creadoPor;
		@Column(name = "FECHA_CREACION")
		private Date fechaCreacion;
		@Column(name = "MODIFICADO_POR")
		private Long modificadoPor;
		@Column(name = "ULTIMA_MODIFICACION")
		private Date ultimaModificacion;
		@Column(name =  "ACTIVO")
		private Long activo;
		
		public Long getIdRobo() {
			return idRobo;
		}
		public void setIdRobo(Long idRobo) {
			this.idRobo = idRobo;
		}
		public String getTurnoDgant() {
			return turnoDgant;
		}
		public void setTurnoDgant(String turnoDgant) {
			this.turnoDgant = turnoDgant;
		}
		public String getExpediente() {
			return expediente;
		}
		public void setExpediente(String expediente) {
			this.expediente = expediente;
		}
		public Date getFechaRobo() {
			return fechaRobo;
		}
		public void setFechaRobo(Date fechaRobo) {
			this.fechaRobo = fechaRobo;
		}

		public String getPlacaVehiculo() {
			return placaVehiculo;
		}
		public void setPlacaVehiculo(String placaVehiculo) {
			this.placaVehiculo = placaVehiculo;
		}
		public String getNumMotor() {
			return numMotor;
		}
		public void setNumMotor(String numMotor) {
			this.numMotor = numMotor;
		}
		public String getNumSerie() {
			return numSerie;
		}
		public void setNumSerie(String numSerie) {
			this.numSerie = numSerie;
		}
		public Long getIdMar() {
			return idMar;
		}
		public void setIdMar(Long idMar) {
			this.idMar = idMar;
		}
		public Long getIdMod() {
			return idMod;
		}
		public void setIdMod(Long idMod) {
			this.idMod = idMod;
		}
		public Long getIdAnio() {
			return idAnio;
		}
		public void setIdAnio(Long idAnio) {
			this.idAnio = idAnio;
		}
		public Long getIdColor() {
			return idColor;
		}
		public void setIdColor(Long idColor) {
			this.idColor = idColor;
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
		public long getIdEstatus() {
			return idEstatus;
		}
		public void setIdEstatus(long idEstatus) {
			this.idEstatus = idEstatus;
		}
		public Long getActivo() {
			return activo;
		}
		public void setActivo(Long activo) {
			this.activo = activo;
		}
		
	
	
}
