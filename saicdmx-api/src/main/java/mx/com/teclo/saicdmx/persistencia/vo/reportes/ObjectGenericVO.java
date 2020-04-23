package mx.com.teclo.saicdmx.persistencia.vo.reportes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ObjectGenericVO {

	private HashMap<Object, Object> parametros;
	private List<Object> cabeceras;
	private List<Object> values;
	private String msjAvisoMax;

	@JsonIgnore
	private List<LinkedHashMap<Object, Object>> resultSetToArray;

	public ObjectGenericVO(HashMap<Object, Object> parametros, List<Object> cabeceras, List<Object> values,
			List<LinkedHashMap<Object, Object>> resultSetToArray) {
		this.parametros = parametros;
		this.cabeceras = cabeceras;
		this.values = values;
		this.resultSetToArray = resultSetToArray;
	}

	public ObjectGenericVO() {
	}

	public HashMap<Object, Object> getParametros() {
		return parametros;
	}

	public void setParametros(HashMap<Object, Object> parametros) {
		this.parametros = parametros;
	}

	public List<Object> getCabeceras() {
		return cabeceras;
	}

	public void setCabeceras(List<Object> cabeceras) {
		this.cabeceras = cabeceras;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	public List<LinkedHashMap<Object, Object>> getResultSetToArray() {
		return resultSetToArray;
	}

	public void setResultSetToArray(List<LinkedHashMap<Object, Object>> resultSetToArray) {
		this.resultSetToArray = resultSetToArray;
	}

	public String getMsjAvisoMax() {
		return msjAvisoMax;
	}

	public void setMsjAvisoMax(String msjAvisoMax) {
		this.msjAvisoMax = msjAvisoMax;
	}

	@Override
	public String toString() {
		return "ObjectGenericVO = [parametros:{" + parametros + "}," + "cabeceras:{" + cabeceras + "},values:{" + values
				+ "}]";
	}

}
