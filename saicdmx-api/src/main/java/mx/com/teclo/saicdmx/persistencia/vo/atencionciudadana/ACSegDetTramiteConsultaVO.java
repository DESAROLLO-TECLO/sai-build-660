package mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ACSegDetTramiteConsultaVO implements Serializable {

	private static final long serialVersionUID = 872243366917120241L;
	
	private String infracNum;
	private String infracPlaca;
	private Integer idTipoPersona;
	private String nbTipoPersona;
	private Integer origenPlaca;
	private String nbOrigenPlaca;
	private Integer idEstado;
	private String nbEstado;
	
	public String getInfracNum() {
		return infracNum;
	}
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}
	public String getInfracPlaca() {
		return infracPlaca;
	}
	public void setInfracPlaca(String infracPlaca) {
		this.infracPlaca = infracPlaca;
	}
	public Integer getIdTipoPersona() {
		return idTipoPersona;
	}
	public void setIdTipoPersona(Integer idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}
	public String getNbTipoPersona() {
		return nbTipoPersona;
	}
	public void setNbTipoPersona(String nbTipoPersona) {
		this.nbTipoPersona = nbTipoPersona;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public String getNbEstado() {
		return nbEstado;
	}
	public void setNbEstado(String nbEstado) {
		this.nbEstado = nbEstado;
	}
	public Integer getOrigenPlaca() {
		return origenPlaca;
	}
	public void setOrigenPlaca(Integer origenPlaca) {
		this.origenPlaca = origenPlaca;
	}
	public String getNbOrigenPlaca() {
		return nbOrigenPlaca;
	}
	public void setNbOrigenPlaca(String nbOrigenPlaca) {
		this.nbOrigenPlaca = nbOrigenPlaca;
	}
}
