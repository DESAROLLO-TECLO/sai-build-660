package mx.com.teclo.saicdmx.persistencia.vo.parteinformativo;

import java.io.Serializable;

public class ParteInformativoInfracsPorDocsVO implements Serializable {
	private static final long serialVersionUID = 4168624616345253836L;

	private String infracNum;
	private String infracPlaca;
	
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
