package mx.com.teclo.saicdmx.persistencia.vo.reportes;

import mx.com.teclo.saicdmx.persistencia.vo.admireporte.PropiedadesVO;

public class PropiedadesCompVO {

	private Long idPropiedadComp;
	private PropiedadesVO propiedad;
	private ComponentesVO componente;

	public Long getIdPropiedadComp() {
		return idPropiedadComp;
	}

	public void setIdPropiedadComp(Long idPropiedadComp) {
		this.idPropiedadComp = idPropiedadComp;
	}

	public PropiedadesVO getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(PropiedadesVO propiedad) {
		this.propiedad = propiedad;
	}

	public ComponentesVO getComponente() {
		return componente;
	}

	public void setComponente(ComponentesVO componente) {
		this.componente = componente;
	}

}
