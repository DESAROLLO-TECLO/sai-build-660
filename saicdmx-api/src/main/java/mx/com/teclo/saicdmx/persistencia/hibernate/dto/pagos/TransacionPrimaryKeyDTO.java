package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;

@Embeddable
public class TransacionPrimaryKeyDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "TRAN_ID ", nullable = false)
	private Long tranId;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "CAJA_ID", referencedColumnName = "CAJA_ID",nullable = false)
	private CajaDTO cajaDTO;

	
	public CajaDTO getCajaDTO() {
		return cajaDTO;
	}

	public void setCajaDTO(CajaDTO cajaDTO) {
		this.cajaDTO = cajaDTO;
	}

	public Long getTranId() {
		return tranId;
	}
	
	public void setTranId(Long tranId) {
		this.tranId = tranId;
	}


}
