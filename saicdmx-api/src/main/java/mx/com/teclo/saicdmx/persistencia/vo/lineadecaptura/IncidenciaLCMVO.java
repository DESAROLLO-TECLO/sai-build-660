package mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura;

public class IncidenciaLCMVO {
	private String infracNum;
	private int linea;
	private boolean isDuplicated;

	public String getInfracNum() {
		return infracNum;
	}

	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public boolean isDuplicated() {
		return isDuplicated;
	}

	public void setDuplicated(boolean isDuplicated) {
		this.isDuplicated = isDuplicated;
	}

	@Override
	public String toString(){
		String duplicate = isDuplicated ? " <==DUPLICADO EN LINEA "+linea: linea!=0 ? " EN LINEA "+linea : "";
		return infracNum+duplicate;
	}
}
