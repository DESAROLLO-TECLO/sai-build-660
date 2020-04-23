package mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InventarioKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "COMP_ID")
	private Long compId;
	
	@Column(name = "INFRAC_NUM")
	private String infracNum;

	public Long getCompId() {
		return compId;
	}

	public void setCompId(Long compId) {
		this.compId = compId;
	}

	public String getInfracNum() {
		return infracNum;
	}

	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}
}
