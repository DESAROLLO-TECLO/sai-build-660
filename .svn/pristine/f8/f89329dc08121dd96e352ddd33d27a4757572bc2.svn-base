package mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "DIRECTORIOS_DIGITALIZACION_DIA")
public class DirectoriosDigitalizacionDiaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 502475726002257213L;
	@Id
	@Column(name = "FOLIO", unique = false, nullable = false)
	private String folio;
	@Column(name = "ANV_REV")
	private String anversoReverso;
	@Column(name = "RUTA_ARCHIVO")
	private String rutaArchivo;
	@Column(name = "IMAGEN")
	@Lob
	private Blob imagen;
	@Column(name = "BIT_CICLO_ESTATUS")
	private Integer bitCicloEstatus;

	/**
	 * @return the folio
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param folio
	 *            the folio to set
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}

	/**
	 * @return the anversoReverso
	 */
	public String getAnversoReverso() {
		return anversoReverso;
	}

	/**
	 * @param anversoReverso
	 *            the anversoReverso to set
	 */
	public void setAnversoReverso(String anversoReverso) {
		this.anversoReverso = anversoReverso;
	}

	/**
	 * @return the rutaArchivo
	 */
	public String getRutaArchivo() {
		return rutaArchivo;
	}

	/**
	 * @param rutaArchivo
	 *            the rutaArchivo to set
	 */
	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	/**
	 * @return the imagen
	 */
	public Blob getImagen() {
		return imagen;
	}

	/**
	 * @param imagen
	 *            the imagen to set
	 */
	public void setImagen(Blob imagen) {
		this.imagen = imagen;
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
