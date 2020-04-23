package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CAT_OBSERVE_QUE")
public class CatObserveQueDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298734431067756354L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "OBSERVE_ID", unique = true, nullable = false)
    private Long observeId;
	
	@Column(name = "OBSERVE_DESC")
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
