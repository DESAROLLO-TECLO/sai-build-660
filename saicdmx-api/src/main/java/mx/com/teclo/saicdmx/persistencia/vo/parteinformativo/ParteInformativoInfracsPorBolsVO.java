package mx.com.teclo.saicdmx.persistencia.vo.parteinformativo;

import java.io.Serializable;

public class ParteInformativoInfracsPorBolsVO implements Serializable {
	private static final long serialVersionUID = 4168624616345253836L;

	private String infracNum;
	
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
}
