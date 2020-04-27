package mx.com.teclo.saicdmx.persistencia.vo.evaluaciones;

import java.io.Serializable;
import java.util.List;

public class EvaluacionUsuarioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3549144283830852880L;
	private EvaluacionVO evaluacion;
	private List<UsuarioVO> usuarios;
	public EvaluacionVO getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(EvaluacionVO evaluacion) {
		this.evaluacion = evaluacion;
	}
	public List<UsuarioVO> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioVO> usuarios) {
		this.usuarios = usuarios;
	}
}
