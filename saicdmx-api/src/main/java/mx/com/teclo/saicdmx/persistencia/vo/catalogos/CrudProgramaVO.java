package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudProgramaVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -33370856434264432L;
	
	private Long programaId;
	private String programaCodigo;
	private String programaNombre;
	
	public Long getProgramaId() {
		return programaId;
	}
	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}
	public String getProgramaCodigo() {
		return programaCodigo;
	}
	public void setProgramaCodigo(String programaCodigo) {
		this.programaCodigo = programaCodigo;
	}
	public String getProgramaNombre() {
		return programaNombre;
	}
	public void setProgramaNombre(String programaNombre) {
		this.programaNombre = programaNombre;
	}
}
