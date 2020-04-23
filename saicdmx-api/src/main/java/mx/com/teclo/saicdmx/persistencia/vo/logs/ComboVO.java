package mx.com.teclo.saicdmx.persistencia.vo.logs;

/**
 * Almacena informaci�n de objetos de tipo combo, que se encuentran en la
 * aplicaci�n.
 *
 * @author TMM Logistic S.A.
 * @see www.tmm.com.mx
 */
public class ComboVO {

    public ComboVO() {
    }
    private String comboEtiqueta;
    private String comboValor;
    private String comboValorExtra;

    public String getComboEtiqueta() {
        return comboEtiqueta;
    }

    public void setComboEtiqueta(String comboEtiqueta) {
        this.comboEtiqueta = comboEtiqueta;
    }

    public String getComboValor() {
        return comboValor;
    }

    public void setComboValor(String comboValor) {
        this.comboValor = comboValor;
    }

    public String getComboValorExtra() {
        return comboValorExtra;
    }

    public void setComboValorExtra(String comboValorExtra) {
        this.comboValorExtra = comboValorExtra;
    }
}
