package mx.com.teclo.saicdmx.persistencia.vo.parteinformativo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParteInformativoBoletaSancionConsultaVO implements Serializable {

	private static final long serialVersionUID = -4612743315182739971L;

	private Long id;
	private String noConsecutivo;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm", timezone="America/Mexico_City")
	private Date fecha;
	private String oficialNombre;
	private String oficialPlaca;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the noConsecutivo
	 */
	public String getNoConsecutivo() {
		return noConsecutivo;
	}
	/**
	 * @param noConsecutivo the noConsecutivo to set
	 */
	public void setNoConsecutivo(String noConsecutivo) {
		this.noConsecutivo = noConsecutivo;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the oficialNombre
	 */
	public String getOficialNombre() {
		return oficialNombre;
	}
	/**
	 * @param oficialNombre the oficialNombre to set
	 */
	public void setOficialNombre(String oficialNombre) {
		this.oficialNombre = oficialNombre;
	}
	/**
	 * @return the oficialPlaca
	 */
	public String getOficialPlaca() {
		return oficialPlaca;
	}
	/**
	 * @param oficialPlaca the oficialPlaca to set
	 */
	public void setOficialPlaca(String oficialPlaca) {
		this.oficialPlaca = oficialPlaca;
	}
}
