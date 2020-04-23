package mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class ACTramiteDetalleVO implements Serializable{

	private static final long serialVersionUID = -6244812574901892320L;
	
	private Long idTramiteDetalle;
	private String idAcTramite;
	private Integer idTipoTramite;
	private Integer ctSolicitados;
	private Integer ctAtendidos;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer stActivo;
	private Long idUsrCreacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private String fhCreacion;
	private Long idUsrModifica;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private String fhModificacion;
	private Integer stSegTramite;
	private String txComentarioTram;
	

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
	public Integer getCtSolicitados() {
		return ctSolicitados;
	}
	public void setCtSolicitados(Integer ctSolicitados) {
		this.ctSolicitados = ctSolicitados;
	}
	public Integer getCtAtendidos() {
		return ctAtendidos;
	}
	public void setCtAtendidos(Integer ctAtendidos) {
		this.ctAtendidos = ctAtendidos;
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
	public Integer getStSegTramite() {
		return stSegTramite;
	}
	public void setStSegTramite(Integer stSegTramite) {
		this.stSegTramite = stSegTramite;
	}
	public String getTxComentarioTram() {
		return txComentarioTram;
	}
	public void setTxComentarioTram(String txComentarioTram) {
		this.txComentarioTram = txComentarioTram;
	}
}
