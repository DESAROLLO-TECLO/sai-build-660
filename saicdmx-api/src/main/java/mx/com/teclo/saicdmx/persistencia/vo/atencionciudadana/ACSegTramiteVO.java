package mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class ACSegTramiteVO implements Serializable {

	private static final long serialVersionUID = 3281941210167555554L;
	
	private Long idSegTramite;
	private Long idTramiteDetalle;
	private String idAcTramite;
	private Integer idTipoTramite;
	private String nbTramite;
	private Integer idTipoValor;
	private String cdValor;
	private Integer stSegTramite;
	private String nbStSegTramite;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer stActivo;	
	private Long idUsrCreacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private String fhCreacion;
	private Long idUsrModifica;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private String fhModificacion;
	public Long getIdSegTramite() {
		return idSegTramite;
	}
	public void setIdSegTramite(Long idSegTramite) {
		this.idSegTramite = idSegTramite;
	}
	public Long getIdTramiteDetalle() {
		return idTramiteDetalle;
	}
	public void setIdTramiteDetalle(Long idTramiteDetalle) {
		this.idTramiteDetalle = idTramiteDetalle;
	}
	public String getIdAcTramite() {
		return idAcTramite;
	}
	public void setIdAcTramite(String idAcTramite) {
		this.idAcTramite = idAcTramite;
	}
	public Integer getIdTipoTramite() {
		return idTipoTramite;
	}
	public void setIdTipoTramite(Integer idTipoTramite) {
		this.idTipoTramite = idTipoTramite;
	}
	public String getNbTramite() {
		return nbTramite;
	}
	public void setNbTramite(String nbTramite) {
		this.nbTramite = nbTramite;
	}
	public Integer getIdTipoValor() {
		return idTipoValor;
	}
	public void setIdTipoValor(Integer idTipoValor) {
		this.idTipoValor = idTipoValor;
	}
	public String getCdValor() {
		return cdValor;
	}
	public void setCdValor(String cdValor) {
		this.cdValor = cdValor;
	}
	public Integer getStSegTramite() {
		return stSegTramite;
	}
	public void setStSegTramite(Integer stSegTramite) {
		this.stSegTramite = stSegTramite;
	}
	public String getNbStSegTramite() {
		return nbStSegTramite;
	}
	public void setNbStSegTramite(String nbStSegTramite) {
		this.nbStSegTramite = nbStSegTramite;
	}
	public Integer getStActivo() {
		return stActivo;
	}
	public void setStActivo(Integer stActivo) {
		this.stActivo = stActivo;
	}
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}
	public String getFhCreacion() {
		return fhCreacion;
	}
	public void setFhCreacion(String fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	public Long getIdUsrModifica() {
		return idUsrModifica;
	}
	public void setIdUsrModifica(Long idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
	public String getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(String fhModificacion) {
		this.fhModificacion = fhModificacion;
	}	
}
