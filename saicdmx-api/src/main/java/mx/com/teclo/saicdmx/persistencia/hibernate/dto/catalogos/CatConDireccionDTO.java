package mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CAT_CON_DIRECCION")
public class CatConDireccionDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4069821965950532083L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CON_DIRECCION_ID", unique = true, nullable = false)
    private Long catConDireccionId;
	
	@Column(name = "CON_DIRECCION_DESC")
	private String catConDireccionDesc;

	public Long getCatConDireccionId() {
		return catConDireccionId;
	}

	public void setCatConDireccionId(Long catConDireccionId) {
		this.catConDireccionId = catConDireccionId;
	}

	public String getCatConDireccionDesc() {
		return catConDireccionDesc;
	}

	public void setCatConDireccionDesc(String catConDireccionDesc) {
		this.catConDireccionDesc = catConDireccionDesc;
	}
	
}
