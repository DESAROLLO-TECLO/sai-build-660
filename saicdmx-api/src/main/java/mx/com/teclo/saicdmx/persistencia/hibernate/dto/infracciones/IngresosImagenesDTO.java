package mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "INGRESOS_IMAGENES")
public class IngresosImagenesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6253147516348625588L;
	@Column(name = "INFRAC_NUM")
	private String infraccNum;
	@Column(name = "INFRAC_NUM_CTRL")
	private String infraccNumCtrl;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Id
	@Column(name = "NOMBRE_ARCHIVO", unique = true, nullable = false)
	private String nombreArchivo;
	@Column(name = "FOTO")
	@Lob
	private Blob foto;
	@Column(name = "ID_MOVIMIENTO")
	private Integer idMovimiento;
	@Column(name = "ID_SALIDA")
	private Integer idSalida;

	/**
	 * @return the infraccNum
	 */
	public String getInfraccNum() {
		return infraccNum;
	}

	/**
	 * @param infraccNum
	 *            the infraccNum to set
	 */
	public void setInfraccNum(String infraccNum) {
		this.infraccNum = infraccNum;
	}

	/**
	 * @return the infraccNumCtrl
	 */
	public String getInfraccNumCtrl() {
		return infraccNumCtrl;
	}

	/**
	 * @param infraccNumCtrl
	 *            the infraccNumCtrl to set
	 */
	public void setInfraccNumCtrl(String infraccNumCtrl) {
		this.infraccNumCtrl = infraccNumCtrl;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo
	 *            the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the foto
	 */
	public Blob getFoto() {
		return foto;
	}

	/**
	 * @param foto
	 *            the foto to set
	 */
	public void setFoto(Blob foto) {
		this.foto = foto;
	}

	/**
	 * @return the idMovimiento
	 */
	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	/**
	 * @param idMovimiento
	 *            the idMovimiento to set
	 */
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	/**
	 * @return the idSalida
	 */
	public Integer getIdSalida() {
		return idSalida;
	}

	/**
	 * @param idSalida
	 *            the idSalida to set
	 */
	public void setIdSalida(Integer idSalida) {
		this.idSalida = idSalida;
	}

}
