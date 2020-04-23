angular.module("siidfApp").service("radarCatalogosService", function ($http, config) { 
	
	
	this.comboCatalogos = function () {
		return $http.get(config.baseUrl + "/comboCatalogos");
	};
	
	this.comboTipoBusqueda = function (tipoCatalogo) {
		return $http.get(config.baseUrl + "/comboTipoBusqueda",{params:{"tipoCatalogo": tipoCatalogo}});
	};
	
	this.comboValor = function (tipoBusqueda) {
		return $http.get(config.baseUrl + "/comboValor",{params:{"tipoBusqueda": tipoBusqueda}});
	};
	
	this.consulta = function (catalogo,tipoBusqueda,valor,archivoTipoUT,tipoRadarUT,estatusUT) {
		return $http.get(config.baseUrl + "/consulta",{params:{"catalogo":catalogo, "tipoBusqueda": tipoBusqueda, "valor": valor, "archivoTipoUT": archivoTipoUT, "tipoRadarUT": tipoRadarUT, "estatusUT": estatusUT}});
	};
	
	this.comboEstatusUT = function () {
		return $http.get(config.baseUrl + "/comboEstatusUT");
	};
	// UT
	this.comboEstatus = function () {
		return $http.get(config.baseUrl + "/comboEstatus");
	};
	
	this.comboDelegacionUT = function () {
		return $http.get(config.baseUrl + "/comboDelegacionUT");
	};
	
	this.sectorUT = function (idEstado) {
		return $http.get(config.baseUrl + "/comboSectorUT",{params:{"idEstado":idEstado}});
	};
	
	this.comboZonasCP = function(){
		return $http.get(config.baseUrl + "/comboZonasCP");
	};
	this.comboEstadoUT = function(){
		return $http.get(config.baseUrl + "/comboEstadoUT");
	};
	
	this.comboEstado = function(){
		return $http.get(config.baseUrl + "/comboEstadoCP");
	};
	
	this.getMunicipio = function(idEstado){
		return $http.get(config.baseUrl + "/getMunicipio",{params:{"idEstado":idEstado}});
	};
	
	this.guardarNewUT = function(unidadTerritorialVO){
		var json =  angular.toJson(unidadTerritorialVO);
		return $http.get(config.baseUrl + "/guardarNewUT",{params:{"unidadTerritorialVO": json}});
	};
	
	this.guardarNewCP = function(centroRepartoVO){
		var json =  angular.toJson(centroRepartoVO);
		return $http.get(config.baseUrl + "/guardarNewCP",{params:{"centroRepartoVO": json}});
	};
	
	this.buscarUnidadTerritorialPorUT = function(valorId){
		return $http.get(config.baseUrl + "/buscarUnidadTerritorialPorUT",{params:{"valorId": valorId}});
	};
	
	this.buscarCentroRepartoPorCP = function(valorId){
		return $http.get(config.baseUrl + "/buscarUnidadTerritorialPorCP",{params:{"valorId": valorId}});
	};
	
	this.guardarUpdateUT = function(unidadTerritorialVO){
		var json =  angular.toJson(unidadTerritorialVO);
		return $http.get(config.baseUrl + "/guardarUpdateUT",{params:{"unidadTerritorialVO": json}});
	};
	this.guardarUpdateCP = function(centroRepartoVO){
		var json =  angular.toJson(centroRepartoVO);
		return $http.get(config.baseUrl + "/guardarUpdateCP",{params:{"centroRepartoVO": json}});
	};
	
	this.comboArchivoTipo = function(){
		return $http.get(config.baseUrl + "/comboArchivoTipo");
	};
	
	// Archivo tipo UT Busqueda
	this.comboArchivoTipoUTBusq = function(){
		return $http.get(config.baseUrl + "/comboArchivoTipoUTBusq");
	};
	
	// Archivo tipo UT
	this.comboArchivoTipoUT = function(){
		return $http.get(config.baseUrl + "/comboArchivoTipoUT");
	};
	
	this.comboTipoRadar = function(){
		return $http.get(config.baseUrl + "/comboTipoRadar");
	};
	// Archivo tipo Radar UT Busqueda
	this.comboTipoRadarUTBusq = function(){
		return $http.get(config.baseUrl + "/comboTipoRadarUTBusq");
	};
	
	// Archivo tipo Radar UT
	this.comboTipoRadarUT = function(){
		return $http.get(config.baseUrl + "/comboTipoRadarUT");
	};
	
	this.guardarUTup = function(unidadTerritorialVO){
		var json =  angular.toJson(unidadTerritorialVO);
		return $http.get(config.baseUrl + "/guardarUTup",{params:{"unidadTerritorialVO": json}});
	};
	
	
	this.actualizarEstatus = function (accion,idUT) {
		return $http.get(config.baseUrl + "/cambiarEstatusUT",{params:{"accion":accion, "idUT": idUT}});
	};
	
});