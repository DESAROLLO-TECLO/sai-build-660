package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudGruaVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2713786288516075248L;
	
	private Long gruaId;
	private String gruaCod;
	private Long conseId;
	private Long gStatId;
	
	public Long getGruaId() {
		return gruaId;
	}
	public void setGruaId(Long gruaId) {
		this.gruaId = gruaId;
	}
	public String getGruaCod() {
		return gruaCod;
	}
	public void setGruaCod(String gruaCod) {
		this.gruaCod = gruaCod;
	}
	public Long getConseId() {
		return conseId;
	}
	public void setConseId(Long conseId) {
		this.conseId = conseId;
	}
	public Long getgStatId() {
		return gStatId;
	}
	public void setgStatId(Long gStatId) {
		this.gStatId = gStatId;
	}
}
