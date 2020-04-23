package mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ParteInformativoCDocsInfracsPK implements Serializable{
	private static final long serialVersionUID = 1897622359511054272L;

	@Basic(optional = false)
	@Column(name = "PI_ID")
	private Long piId;
	
	@Column(name = "INFRAC_NUM", length=11)
	private String infracNum;
	
	@Column(name = "INFRAC_PLACA", length=10)
	private String infracPlaca;
	
	public ParteInformativoCDocsInfracsPK() {
		super();
	}
	
	public ParteInformativoCDocsInfracsPK(Long documentoId){
		this.piId = documentoId;
	}

	/**
	 * @return the piId
	 */
	public Long getPiId() {
		return piId;
	}

	/**
	 * @param piId the piId to set
	 */
	public void setPiId(Long piId) {
		this.piId = piId;
	}

	/**
	 * @return the infracNum
	 */
	public String getInfracNum() {
		return infracNum;
	}

	/**
	 * @param infracNum the infracNum to set
	 */
	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	/**
	 * @return the infracPlaca
	 */
	public String getInfracPlaca() {
		return infracPlaca;
	}

	/**
	 * @param infracPlaca the infracPlaca to set
	 */
	public void setInfracPlaca(String infracPlaca) {
		this.infracPlaca = infracPlaca;
	}
}
