package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.util.Set;

public class EstadoVO {
	
	private Long edoId;
	
	private String edoCod;
	
	private String edoNombre;
	
	private String edoStatus;
	
	private Set<DelegacionVO> delegaciones;

	/**
	 * @return the edoId
	 */
	public Long getEdoId() {
		return edoId;
	}

	/**
	 * @param edoId the edoId to set
	 */
	public void setEdoId(Long edoId) {
		this.edoId = edoId;
	}

	/**
	 * @return the edoCod
	 */
	public String getEdoCod() {
		return edoCod;
	}

	/**
	 * @param edoCod the edoCod to set
	 */
	public void setEdoCod(String edoCod) {
		this.edoCod = edoCod;
	}

	/**
	 * @return the edoNombre
	 */
	public String getEdoNombre() {
		return edoNombre;
	}

	/**
	 * @param edoNombre the edoNombre to set
	 */
	public void setEdoNombre(String edoNombre) {
		this.edoNombre = edoNombre;
	}

	/**
	 * @return the delegaciones
	 */
	public Set<DelegacionVO> getDelegaciones() {
		return delegaciones;
	}

	/**
	 * @param delegaciones the delegaciones to set
	 */
	public void setDelegaciones(Set<DelegacionVO> delegaciones) {
		this.delegaciones = delegaciones;
	}
	
	public String getEdoStatus() {
		return edoStatus;
	}

	public void setEdoStatus(String edoStatus) {
		this.edoStatus = edoStatus;
	}

	public String getStatusDesc() {
		if (this.getEdoStatus() !=  null) {
			return this.getEdoStatus().equals("A") ? "Activo" : "Cancelado";
		}
		return null;
	}

}
