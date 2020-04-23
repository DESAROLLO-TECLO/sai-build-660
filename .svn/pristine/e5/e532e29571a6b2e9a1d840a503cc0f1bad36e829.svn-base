package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DelId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7159030421391871895L;

	@Basic(optional = false)
	@Column(name = "EDO_ID", nullable = false)
	private Long edoId;
	
	@Basic(optional = false)
	@Column(name = "DEL_ID", nullable = false)
	private Long delId;
	
	
	public Long getEdoId() {
		return edoId;
	}
	
	public void setEdoId(Long edoId) {
		this.edoId = edoId;
	}
	/**
	 * @return the delId
	 */
	public Long getDelId() {
		return delId;
	}
	/**
	 * @param delId the delId to set
	 */
	public void setDelId(Long delId) {
		this.delId = delId;
	}
}
