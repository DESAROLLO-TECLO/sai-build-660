angular.module("siidfApp").service("admiReporteService", function($http, config) {
	
	
	
	this.listTiposReportes = function() {
		return $http.get(config.baseUrl + "/adminReporteController/listaTipoReportes");
	};
	
	this.listFormatoDescarga = function(){
		return $http.get(config.baseUrl + "/adminReporteController/listaFormatoDescarga");
	}
	
	this.listComponentes = function(){
		return $http.get(config.baseUrl + "/adminReporteController/listaComponentes");
	}
	
	this.listParametros = function() {
		return $http.get(config.baseUrl + "/adminReporteController/listaParametros");
	}
	
	this.listPropiedades = function(){
		return $http.get(config.baseUrl + "/adminReporteController/listaPropiedades");
	}
	
	this.listTipoTitulo = function(){
		return $http.get(config.baseUrl + "/adminReporteController/listaTipoTitulo");
	}
	
	this.guardarReporte = function(cadena){
		return $http.get(config.baseUrl + "/adminReporteController/validarReporte", {params : {'cadena' : cadena}});
		
	}
	
	this.crearQuery = function(arrayParamValue, cadena){
		return $http.get(config.baseUrl + "/adminReporteController/creaQuery", {params : {'arrayParamValue' : arrayParamValue, 'cadena' : cadena}});
	}
	
	this.guardarReporteBd = function(reporteVO){
		return $http.post(config.baseUrl + "/adminReporteController/guardarReporteBd", reporteVO);
	}
	this.guardarConfigParam = function(params){
		return $http.post(config.baseUrl + "/adminReporteController/guardarReporteBd", params);
	}
	
});