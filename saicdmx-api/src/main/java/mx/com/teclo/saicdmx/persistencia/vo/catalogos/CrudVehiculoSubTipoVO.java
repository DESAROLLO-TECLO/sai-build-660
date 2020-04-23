package mx.com.teclo.saicdmx.persistencia.vo.catalogos;

public class CrudVehiculoSubTipoVO extends BaseCrudVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7002280701363134924L;
	
	private Long vSubTipoId;
	private String vSubTipoCodigo;
	private String vSubTipoNombre;
	
	public Long getvSubTipoId() {
		return vSubTipoId;
	}
	public void setvSubTipoId(Long vSubTipoId) {
		this.vSubTipoId = vSubTipoId;
	}
	public String getvSubTipoCodigo() {
		return vSubTipoCodigo;
	}
	public void setvSubTipoCodigo(String vSubTipoCodigo) {
		this.vSubTipoCodigo = vSubTipoCodigo;
	}
	public String getvSubTipoNombre() {
		return vSubTipoNombre;
	}
	public void setvSubTipoNombre(String vSubTipoNombre) {
		this.vSubTipoNombre = vSubTipoNombre;
	}
}
