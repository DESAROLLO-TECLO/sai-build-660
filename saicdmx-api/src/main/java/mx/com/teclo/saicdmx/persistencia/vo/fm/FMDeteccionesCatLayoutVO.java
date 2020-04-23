package mx.com.teclo.saicdmx.persistencia.vo.fm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FMDeteccionesCatLayoutVO {
	private String[] obligatorios;
	private String[] opcionaes;
	private Map<String, List<String>> catalogosMap;
	
	
	public String[] getObligatorios() {
		return obligatorios;
	}
	public void setObligatorios(String[] obligatorios) {
		this.obligatorios = obligatorios;
	}
	public String[] getOpcionaes() {
		return opcionaes;
	}
	public void setOpcionaes(String[] opcionaes) {
		this.opcionaes = opcionaes;
	}
	public Map<String, List<String>> getCatalogosMap() {
		return catalogosMap;
	}
	public void setCatalogosMap(Map<String, List<String>> catalogosMap) {
		this.catalogosMap = catalogosMap;
	}
	
	
}
