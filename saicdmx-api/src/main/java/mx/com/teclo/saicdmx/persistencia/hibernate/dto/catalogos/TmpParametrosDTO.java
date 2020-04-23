package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TMP_PARAMETROS")
public class TmpParametrosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TmpParametrosKey tmpParametrosKey;

	public TmpParametrosKey getTmpParametrosKey() {
		return tmpParametrosKey;
	}

	public void setTmpParametrosKey(TmpParametrosKey tmpParametrosKey) {
		this.tmpParametrosKey = tmpParametrosKey;
	}
}
