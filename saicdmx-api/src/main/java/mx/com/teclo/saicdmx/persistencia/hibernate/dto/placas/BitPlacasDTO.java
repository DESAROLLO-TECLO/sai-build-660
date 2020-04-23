package mx.com.teclo.saicdmx.persistencia.hibernate.dto.placas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAI004B_FM_VALIDA_MATRICULA")
public class BitPlacasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1614633775470671559L;

	@Id
	@Column(name = "ID_BIT_VALIDA_MATRI", unique = true, nullable = false)
	private Integer idBitValidaMatri;

	@Column(name = "CD_VALOR_ORIGINAL")
	private String cdValorOriginal;

	@Column(name = "CD_VALOR_FINAL")
	private String cdValorFinal;

	@Column(name = "ID_REGISTRO")
	private String idRegistro;

	@Column(name = "NB_COLUMNA")
	private String nbColumna;

	@Column(name = "NB_TABLA")
	private String nbTabla;

	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name = "CREADO_POR")
	private Integer creadoPor;

	public Integer getIdBitValidaMatri() {
		return idBitValidaMatri;
	}

	public void setIdBitValidaMatri(Integer idBitValidaMatri) {
		this.idBitValidaMatri = idBitValidaMatri;
	}

	public String getCdValorOriginal() {
		return cdValorOriginal;
	}

	public void setCdValorOriginal(String cdValorOriginal) {
		this.cdValorOriginal = cdValorOriginal;
	}

	public String getCdValorFinal() {
		return cdValorFinal;
	}

	public void setCdValorFinal(String cdValorFinal) {
		this.cdValorFinal = cdValorFinal;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getNbColumna() {
		return nbColumna;
	}

	public void setNbColumna(String nbColumna) {
		this.nbColumna = nbColumna;
	}

	public String getNbTabla() {
		return nbTabla;
	}

	public void setNbTabla(String nbTabla) {
		this.nbTabla = nbTabla;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(Integer creadoPor) {
		this.creadoPor = creadoPor;
	}
	
	

}
