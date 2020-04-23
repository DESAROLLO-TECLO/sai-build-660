package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoGruaDTO;

public class GruaVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3623493623232084131L;
	
	private Long gruaId;
	private String gruaCod;
	private Integer gruaSms;
	private Long conseId;
	private Long gStatId;
	private String gStatus;
	private String gruaStatus;
	private TipoGruaDTO tipoGrua;
	private String gruaCobrarArrastre;
	public Long getGruaId() {
		return gruaId;
	}
	public void setGruaId(Long gruaId) {
		this.gruaId = gruaId;
	}
	public String getGruaCod() {
		return gruaCod;
	}
	public void setGruaCod(String gruaCod) {
		this.gruaCod = gruaCod;
	}
	public Integer getGruaSms() {
		return gruaSms;
	}
	public void setGruaSms(Integer gruaSms) {
		this.gruaSms = gruaSms;
	}
	public Long getConseId() {
		return conseId;
	}
	public void setConseId(Long conseId) {
		this.conseId = conseId;
	}
	public Long getgStatId() {
		return gStatId;
	}
	public void setgStatId(Long gStatId) {
		this.gStatId = gStatId;
	}
	public String getGruaStatus() {
		return gruaStatus;
	}
	public void setGruaStatus(String gruaStatus) {
		this.gruaStatus = gruaStatus;
	}
	public TipoGruaDTO getTipoGrua() {
		return tipoGrua;
	}
	public void setTipoGrua(TipoGruaDTO tipoGrua) {
		this.tipoGrua = tipoGrua;
	}
	public String getGruaCobrarArrastre() {
		return gruaCobrarArrastre;
	}
	public void setGruaCobrarArrastre(String gruaCobrarArrastre) {
		this.gruaCobrarArrastre = gruaCobrarArrastre;
	}
	public String getgStatus() {
		return gStatus;
	}
	public void setgStatus(String gStatus) {
		this.gStatus = gStatus;
	}
	public String getStatusDesc() {
		return this.getGruaStatus().equals("A") ? "Activo" : "Cancelado";
	}
}
