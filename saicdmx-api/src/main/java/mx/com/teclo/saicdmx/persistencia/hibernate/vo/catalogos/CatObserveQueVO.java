package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;

public class CatObserveQueVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1559622507456480583L;

    private Long observeId;
	private String observeDes;

	public Long getObserveId() {
		return observeId;
	}

	public void setObserveId(Long observeId) {
		this.observeId = observeId;
	}

	public String getObserveDes() {
		return observeDes;
	}

	public void setObserveDes(String observeDes) {
		this.observeDes = observeDes;
	}

}
