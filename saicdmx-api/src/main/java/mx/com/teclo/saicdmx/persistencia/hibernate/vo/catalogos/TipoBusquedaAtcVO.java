package mx.com.teclo.saicdmx.persistencia.hibernate.vo.catalogos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class TipoBusquedaAtcVO implements Serializable {
	
	private static final long serialVersionUID = -8440407655888104415L;
	
	private Integer idParametro;
	private String parametroCod;
	private String parametroDesc;
	private String cdOrden;

	public Integer getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Integer idParametro) {
		this.idParametro = idParametro;
	}

	public String getParametroCod() {
		return parametroCod;
	}

	public void setParametroCod(String parametroCod) {
		this.parametroCod = parametroCod;
	}

	public String getParametroDesc() {
		return parametroDesc;
	}

	public void setParametroDesc(String parametroDesc) {
		this.parametroDesc = parametroDesc;
	}

	public String getCdOrden() {
		return cdOrden;
	}

	public void setCdOrden(String cdOrden) {
		this.cdOrden = cdOrden;
	}
}
