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
@Table(name = "INFRACCIONES_IMAGENES")
public class InfraccionesImagenesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3681463132475974455L;
	@Id
	@Column(name = "INFRAC_NUM", unique = false, nullable = false)
	private String infraccNum;
	@Column(name = "INFRAC_NUM_CTRL")
	private String infraccNumCtrl;
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	@Column(name = "NOMBRE_ARCHIVO")
	private String nombreArchivo;
	@Column(name = "FOTO")
	@Lob
	private Blob foto;
	@Column(name = "BIT_NUM_CICLO")
	private Integer bitNumCiclo;
	@Column(name = "BIT_CICLO_ESTATUS")
	private Integer bitCicloEstatus;

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
	 * @return the bitNumCiclo
	 */
	public Integer getBitNumCiclo() {
		return bitNumCiclo;
	}

	/**
	 * @param bitNumCiclo
	 *            the bitNumCiclo to set
	 */
	public void setBitNumCiclo(Integer bitNumCiclo) {
		this.bitNumCiclo = bitNumCiclo;
	}

	/**
	 * @return the bitCicloEstatus
	 */
	public Integer getBitCicloEstatus() {
		return bitCicloEstatus;
	}

	/**
	 * @param bitCicloEstatus
	 *            the bitCicloEstatus to set
	 */
	public void setBitCicloEstatus(Integer bitCicloEstatus) {
		this.bitCicloEstatus = bitCicloEstatus;
	}

}
