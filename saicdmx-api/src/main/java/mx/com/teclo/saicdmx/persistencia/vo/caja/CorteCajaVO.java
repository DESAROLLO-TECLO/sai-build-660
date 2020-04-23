package mx.com.teclo.saicdmx.persistencia.vo.caja;

public class CorteCajaVO {
	
	public CorteCajaVO() {
        billete1000 = "";
        billete500 = "";
        billete20 = "";
        billete100 = "";
        billete50 = "";
        billete20 = "";
        cantbill1000 = "";
        cantbill500 = "";
        cantbill200 = "";
        cantbill100 = "";
        cantbill50 = "";
        cantbill20 = "";
        subtot_billetes = "";
        moneda20 = "";
        moneda10 = "";
        moneda5 = "";
        moneda2 = "";
        moneda1 = "";
        moneda05 = "";
        moneda02 = "";
        moneda01 = "";
        moneda005 = "";
        cantmon20 = "";
        cantmon10 = "";
        cantmon5 = "";
        cantmon2 = "";
        cantmon1 = "";
        cantmon05 = "";
        cantmon02 = "";
        cantmon01 = "";
        cantmon005 = "";
        subtot_moneda = "";
        tar_cred = "";
        tar_cre = "";
        efectivo = "";
        partida_inicial = "";
        partida_final = "";
        oper_efectuadas = "";
        total_recaudado = "";
        total_correcciones = "";
        total_ajustado = "";
        total_concenrado = "";
        sobrante_faltante = "";
        p_caja_id = "";
        p_caja_fecha = "";
        p_caja_code = "";
        p_num_corte = "";
        p_total_efectivo = "";
        p_total_cheques = "";
        p_total_tarjetas = "";
        p_total_otros = "";
        p_total_corte = "";
        p_partida_inicial = "";
        p_partida_final = "";
        p_num_operaciones = "";
        combocajas = "";
        comboFechas = "";
        r_folio_inicial = "";
        r_folio_final = "";
        r_total_folios = "";
        r_utilizados = "";
        r_cancelados = "";
        r_inutilizados = "";
        r_faltantes = "";
    }
	
	/**
     * Variable para Billetes de 1000.
     */
    private String billete1000;
    /**
     * Variable para Billeres de 500.
     */
    private String billete500;
    /**
     * Variable para Billetes de 200.
     */
    private String billete200;
    /**
     * Variable para Billetes de 100.
     */
    private String billete100;
    /**
     * Variable para Billetes de 50.
     */
    private String billete50;
    /**
     * Variable para Billetes de 20.
     */
    private String billete20;
    /**
     * Variable para Cantidad de billetes de 1000.
     */
    private String cantbill1000;
    /**
     * Variable para Cantidad de billetes de 500.
     */
    private String cantbill500;
    /**
     * Variable para Cantidad de billetes de 200.
     */
    private String cantbill200;
    /**
     * Variable para Cantidad de billetes de 100.
     */
    private String cantbill100;
    /**
     * Variable para Cantidad de billetes de 50.
     */
    private String cantbill50;
    /**
     * Variable para Cantidad de billetes de 20/
     */
    private String cantbill20;
    /**
     * Variable para Total de billetes.
     */
    private String subtot_billetes;
    /**
     * Variable para monedas de 20.
     */
    private String moneda20;
    /**
     * Variable para monedas de 10.
     */
    private String moneda10;
    /**
     * Variable para monedas de 5.
     */
    private String moneda5;
    /**
     * Variable para monedas de 2.
     */
    private String moneda2;
    /**
     * Variable para monedas de 1.
     */
    private String moneda1;
    /**
     * Variable para monedas de 05.
     */
    private String moneda05;
    /**
     * Variable para monedas de 02.
     */
    private String moneda02;
    /**
     * Variable para monedas de 01.
     */
    private String moneda01;
    /**
     * Variable para monedas de 005.
     */
    private String moneda005;
    /**
     * Variable para Cantidad de monedas de 20.
     */
    private String cantmon20;
    /**
     * Variable para Cantidad de monedas de 10.
     */
    private String cantmon10;
    /**
     * Variable para Cantidad de monedas de 5.
     */
    private String cantmon5;
    /**
     * Variable para Cantidad de monedas de 2.
     */
    private String cantmon2;
    /**
     * Variable para Cantidad de monedas de 1.
     */
    private String cantmon1;
    /**
     * Variable para Cantidad de monedas de 05.
     */
    private String cantmon05;
    /**
     * Variable para Cantidad de monedas de 02.
     */
    private String cantmon02;
    /**
     * Variable para Cantidad de monedas de 01.
     */
    private String cantmon01;
    /**
     * Variable para Cantidad de monedas de 005.
     */
    private String cantmon005;
    /**
     * Variable para Subtotal de monedas.
     */
    private String subtot_moneda;
    /**
     * Variable para Tarjeta de credito.
     */
    private String tar_cred;
    /**
     * Variable para Tarjeta de credito.
     */
    private String tar_cre;
    /**
     * Variable para Efectivo.
     */
    private String efectivo;
    /**
     * Variable para Partida inicial.
     */
    private String partida_inicial;
    /**
     * Variable para Partida final.
     */
    private String partida_final;
    /**
     * Variable para Operaciones efectuadas.
     */
    private String oper_efectuadas;
    /**
     * Variable para Total recaudado.
     */
    private String total_recaudado;
    /**
     * Variable para Total de correcciones.
     */
    private String total_correcciones;
    /**
     * Variable para Total ajustado.
     */
    private String total_ajustado;
    /**
     * Variable para Total Concentrado.
     */
    private String total_concenrado;
    /**
     * Variable para Sobrante y/o faltante.
     */
    private String sobrante_faltante;
    /**
     * Variable para Identificador unico de caja.
     */
    private String p_caja_id;
    /**
     * Variable para Fecha.
     */
    private String p_caja_fecha;
    /**
     * Variable para Codigo de caja.
     */
    private String p_caja_code;
    /**
     * Variable para Numero de corte.
     */
    private String p_num_corte;
    /**
     * Variable para Total de efectivo.
     */
    private String p_total_efectivo;
    /**
     * Variable para Total Cheques.
     */
    private String p_total_cheques;
    /**
     * Variable para Total de tarjetas.
     */
    private String p_total_tarjetas;
    /**
     * Variable para Total otros.
     */
    private String p_total_otros;
    /**
     * Variable para Total corte.
     */
    private String p_total_corte;
    /**
     * Variable para Partida inicial.
     */
    private String p_partida_inicial;
    /**
     * Variable para Partida final.
     */
    private String p_partida_final;
    /**
     * Variable para Numero de operaciones.
     */
    private String p_num_operaciones;
    /**
     * Variables para cajas.
     */
    private String combocajas;
    /**
     * Variable para fechas.
     */
    private String comboFechas;
    /**
     * Variable para Folio inicial.
     */
    private String r_folio_inicial;
    /**
     * Variable para Folio final.
     */
    private String r_folio_final;
    /**
     * Variable para Total de folios.
     */
    private String r_total_folios;
    /**
     * Variable para Utilizados.
     */
    private String r_utilizados;
    /**
     * Variable para Cancelados.
     */
    private String r_cancelados;
    /**
     * Variable para Inutilizados.
     */
    private String r_inutilizados;
    /**
     * Variable para Faltantes.
     */
    private String r_faltantes;
    /**
     * Variable por si ocurre algÃºn error
     */
    private String errorCodigo;
    /**
     * Variable de mensaje de error.
     */
    private String errorMensaje;
	public String getBillete1000() {
		return billete1000;
	}
	public void setBillete1000(String billete1000) {
		this.billete1000 = billete1000;
	}
	public String getBillete500() {
		return billete500;
	}
	public void setBillete500(String billete500) {
		this.billete500 = billete500;
	}
	public String getBillete200() {
		return billete200;
	}
	public void setBillete200(String billete200) {
		this.billete200 = billete200;
	}
	public String getBillete100() {
		return billete100;
	}
	public void setBillete100(String billete100) {
		this.billete100 = billete100;
	}
	public String getBillete50() {
		return billete50;
	}
	public void setBillete50(String billete50) {
		this.billete50 = billete50;
	}
	public String getBillete20() {
		return billete20;
	}
	public void setBillete20(String billete20) {
		this.billete20 = billete20;
	}
	public String getCantbill1000() {
		return cantbill1000;
	}
	public void setCantbill1000(String cantbill1000) {
		this.cantbill1000 = cantbill1000;
	}
	public String getCantbill500() {
		return cantbill500;
	}
	public void setCantbill500(String cantbill500) {
		this.cantbill500 = cantbill500;
	}
	public String getCantbill200() {
		return cantbill200;
	}
	public void setCantbill200(String cantbill200) {
		this.cantbill200 = cantbill200;
	}
	public String getCantbill100() {
		return cantbill100;
	}
	public void setCantbill100(String cantbill100) {
		this.cantbill100 = cantbill100;
	}
	public String getCantbill50() {
		return cantbill50;
	}
	public void setCantbill50(String cantbill50) {
		this.cantbill50 = cantbill50;
	}
	public String getCantbill20() {
		return cantbill20;
	}
	public void setCantbill20(String cantbill20) {
		this.cantbill20 = cantbill20;
	}
	public String getSubtot_billetes() {
		return subtot_billetes;
	}
	public void setSubtot_billetes(String subtot_billetes) {
		this.subtot_billetes = subtot_billetes;
	}
	public String getMoneda20() {
		return moneda20;
	}
	public void setMoneda20(String moneda20) {
		this.moneda20 = moneda20;
	}
	public String getMoneda10() {
		return moneda10;
	}
	public void setMoneda10(String moneda10) {
		this.moneda10 = moneda10;
	}
	public String getMoneda5() {
		return moneda5;
	}
	public void setMoneda5(String moneda5) {
		this.moneda5 = moneda5;
	}
	public String getMoneda2() {
		return moneda2;
	}
	public void setMoneda2(String moneda2) {
		this.moneda2 = moneda2;
	}
	public String getMoneda1() {
		return moneda1;
	}
	public void setMoneda1(String moneda1) {
		this.moneda1 = moneda1;
	}
	public String getMoneda05() {
		return moneda05;
	}
	public void setMoneda05(String moneda05) {
		this.moneda05 = moneda05;
	}
	public String getMoneda02() {
		return moneda02;
	}
	public void setMoneda02(String moneda02) {
		this.moneda02 = moneda02;
	}
	public String getMoneda01() {
		return moneda01;
	}
	public void setMoneda01(String moneda01) {
		this.moneda01 = moneda01;
	}
	public String getMoneda005() {
		return moneda005;
	}
	public void setMoneda005(String moneda005) {
		this.moneda005 = moneda005;
	}
	public String getCantmon20() {
		return cantmon20;
	}
	public void setCantmon20(String cantmon20) {
		this.cantmon20 = cantmon20;
	}
	public String getCantmon10() {
		return cantmon10;
	}
	public void setCantmon10(String cantmon10) {
		this.cantmon10 = cantmon10;
	}
	public String getCantmon5() {
		return cantmon5;
	}
	public void setCantmon5(String cantmon5) {
		this.cantmon5 = cantmon5;
	}
	public String getCantmon2() {
		return cantmon2;
	}
	public void setCantmon2(String cantmon2) {
		this.cantmon2 = cantmon2;
	}
	public String getCantmon1() {
		return cantmon1;
	}
	public void setCantmon1(String cantmon1) {
		this.cantmon1 = cantmon1;
	}
	public String getCantmon05() {
		return cantmon05;
	}
	public void setCantmon05(String cantmon05) {
		this.cantmon05 = cantmon05;
	}
	public String getCantmon02() {
		return cantmon02;
	}
	public void setCantmon02(String cantmon02) {
		this.cantmon02 = cantmon02;
	}
	public String getCantmon01() {
		return cantmon01;
	}
	public void setCantmon01(String cantmon01) {
		this.cantmon01 = cantmon01;
	}
	public String getCantmon005() {
		return cantmon005;
	}
	public void setCantmon005(String cantmon005) {
		this.cantmon005 = cantmon005;
	}
	public String getSubtot_moneda() {
		return subtot_moneda;
	}
	public void setSubtot_moneda(String subtot_moneda) {
		this.subtot_moneda = subtot_moneda;
	}
	public String getTar_cred() {
		return tar_cred;
	}
	public void setTar_cred(String tar_cred) {
		this.tar_cred = tar_cred;
	}
	public String getTar_cre() {
		return tar_cre;
	}
	public void setTar_cre(String tar_cre) {
		this.tar_cre = tar_cre;
	}
	public String getEfectivo() {
		return efectivo;
	}
	public void setEfectivo(String efectivo) {
		this.efectivo = efectivo;
	}
	public String getPartida_inicial() {
		return partida_inicial;
	}
	public void setPartida_inicial(String partida_inicial) {
		this.partida_inicial = partida_inicial;
	}
	public String getPartida_final() {
		return partida_final;
	}
	public void setPartida_final(String partida_final) {
		this.partida_final = partida_final;
	}
	public String getOper_efectuadas() {
		return oper_efectuadas;
	}
	public void setOper_efectuadas(String oper_efectuadas) {
		this.oper_efectuadas = oper_efectuadas;
	}
	public String getTotal_recaudado() {
		return total_recaudado;
	}
	public void setTotal_recaudado(String total_recaudado) {
		this.total_recaudado = total_recaudado;
	}
	public String getTotal_correcciones() {
		return total_correcciones;
	}
	public void setTotal_correcciones(String total_correcciones) {
		this.total_correcciones = total_correcciones;
	}
	public String getTotal_ajustado() {
		return total_ajustado;
	}
	public void setTotal_ajustado(String total_ajustado) {
		this.total_ajustado = total_ajustado;
	}
	public String getTotal_concenrado() {
		return total_concenrado;
	}
	public void setTotal_concenrado(String total_concenrado) {
		this.total_concenrado = total_concenrado;
	}
	public String getSobrante_faltante() {
		return sobrante_faltante;
	}
	public void setSobrante_faltante(String sobrante_faltante) {
		this.sobrante_faltante = sobrante_faltante;
	}
	public String getP_caja_id() {
		return p_caja_id;
	}
	public void setP_caja_id(String p_caja_id) {
		this.p_caja_id = p_caja_id;
	}
	public String getP_caja_fecha() {
		return p_caja_fecha;
	}
	public void setP_caja_fecha(String p_caja_fecha) {
		this.p_caja_fecha = p_caja_fecha;
	}
	public String getP_caja_code() {
		return p_caja_code;
	}
	public void setP_caja_code(String p_caja_code) {
		this.p_caja_code = p_caja_code;
	}
	public String getP_num_corte() {
		return p_num_corte;
	}
	public void setP_num_corte(String p_num_corte) {
		this.p_num_corte = p_num_corte;
	}
	public String getP_total_efectivo() {
		return p_total_efectivo;
	}
	public void setP_total_efectivo(String p_total_efectivo) {
		this.p_total_efectivo = p_total_efectivo;
	}
	public String getP_total_cheques() {
		return p_total_cheques;
	}
	public void setP_total_cheques(String p_total_cheques) {
		this.p_total_cheques = p_total_cheques;
	}
	public String getP_total_tarjetas() {
		return p_total_tarjetas;
	}
	public void setP_total_tarjetas(String p_total_tarjetas) {
		this.p_total_tarjetas = p_total_tarjetas;
	}
	public String getP_total_otros() {
		return p_total_otros;
	}
	public void setP_total_otros(String p_total_otros) {
		this.p_total_otros = p_total_otros;
	}
	public String getP_total_corte() {
		return p_total_corte;
	}
	public void setP_total_corte(String p_total_corte) {
		this.p_total_corte = p_total_corte;
	}
	public String getP_partida_inicial() {
		return p_partida_inicial;
	}
	public void setP_partida_inicial(String p_partida_inicial) {
		this.p_partida_inicial = p_partida_inicial;
	}
	public String getP_partida_final() {
		return p_partida_final;
	}
	public void setP_partida_final(String p_partida_final) {
		this.p_partida_final = p_partida_final;
	}
	public String getP_num_operaciones() {
		return p_num_operaciones;
	}
	public void setP_num_operaciones(String p_num_operaciones) {
		this.p_num_operaciones = p_num_operaciones;
	}
	public String getCombocajas() {
		return combocajas;
	}
	public void setCombocajas(String combocajas) {
		this.combocajas = combocajas;
	}
	public String getComboFechas() {
		return comboFechas;
	}
	public void setComboFechas(String comboFechas) {
		this.comboFechas = comboFechas;
	}
	public String getR_folio_inicial() {
		return r_folio_inicial;
	}
	public void setR_folio_inicial(String r_folio_inicial) {
		this.r_folio_inicial = r_folio_inicial;
	}
	public String getR_folio_final() {
		return r_folio_final;
	}
	public void setR_folio_final(String r_folio_final) {
		this.r_folio_final = r_folio_final;
	}
	public String getR_total_folios() {
		return r_total_folios;
	}
	public void setR_total_folios(String r_total_folios) {
		this.r_total_folios = r_total_folios;
	}
	public String getR_utilizados() {
		return r_utilizados;
	}
	public void setR_utilizados(String r_utilizados) {
		this.r_utilizados = r_utilizados;
	}
	public String getR_cancelados() {
		return r_cancelados;
	}
	public void setR_cancelados(String r_cancelados) {
		this.r_cancelados = r_cancelados;
	}
	public String getR_inutilizados() {
		return r_inutilizados;
	}
	public void setR_inutilizados(String r_inutilizados) {
		this.r_inutilizados = r_inutilizados;
	}
	public String getR_faltantes() {
		return r_faltantes;
	}
	public void setR_faltantes(String r_faltantes) {
		this.r_faltantes = r_faltantes;
	}
	public String getErrorCodigo() {
		return errorCodigo;
	}
	public void setErrorCodigo(String errorCodigo) {
		this.errorCodigo = errorCodigo;
	}
	public String getErrorMensaje() {
		return errorMensaje;
	}
	public void setErrorMensaje(String errorMensaje) {
		this.errorMensaje = errorMensaje;
	}
    
}
