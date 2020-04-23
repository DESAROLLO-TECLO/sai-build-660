package mx.com.teclo.saicdmx.persistencia.vo.radares;

import java.util.ArrayList;
import java.util.List;

public class ListaDeteccionesErroneas {
	
//	private List<DeteccionesIncorrectasVO> deteccionesErroeas;
	private ArrayList<DeteccionesIncorrectasVO> deteccionesErroeas;
	//

	public ArrayList<DeteccionesIncorrectasVO> getDeteccionesErroeas() {
		return deteccionesErroeas;
	}

	public ListaDeteccionesErroneas() {
		super();
	}

	public ListaDeteccionesErroneas(ArrayList<DeteccionesIncorrectasVO> deteccionesErroeas) {
		super();
		this.deteccionesErroeas = deteccionesErroeas;
	}

	public void setDeteccionesErroeas(ArrayList<DeteccionesIncorrectasVO> deteccionesErroeas) {
		this.deteccionesErroeas = deteccionesErroeas;
	}


}
