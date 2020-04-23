angular.module("siidfApp").service("parteInformativoService", function ($http, config) {	
	
	var mapObjects ={listaResultados: [], tipoBusqueda: "0", valor: "", filterValues : [], rowsPerPage : 0};
	
	this.setMapObjects = function(object) {
		mapObjects = object;
 	};
	
	this.getMapObjects = function() {
		return mapObjects;
	}
	
	//Consulta Documentos
	this.buscarPIDocumentos = function (tipoBusqueda, valor) {
		return $http.get(config.baseUrl + "/documentosPI", 
				{params:{"tipoBusqueda": tipoBusqueda, "valor": valor}});
	};
	
	this.filtroPIDocumentos = function () {
		return $http.get(config.baseUrl + "/filterValuesDocumentos");
	};
	
	//Consulta Boletas
	this.buscarPIBoletas = function (tipoBusqueda, valor) {
		return $http.get(config.baseUrl + "/boletasPI", 
				{params:{"tipoBusqueda": tipoBusqueda, "valor": valor}});
	};
	
	this.filtroPIBoletas = function () {
		return $http.get(config.baseUrl + "/filterValuesBoletas");
	};
	
	
	//Modificacion Documentos
	this.buscarDocumentoPorId = function (id) {
		return $http.get(config.baseUrl + "/consultarDocumento/" + id);
	};
	
	this.buscarInfraccionesPorDocumento = function(id){
		return $http.get(config.baseUrl + "/consultarInfraccionesPorDocumento",
				{params:{"documentoId" : id}});
	};
	
	this.actualizarDocumento = function (documentoVO, addInfracciones, deleteInfracciones) {
        var json =  angular.toJson(documentoVO);
        var json2 = angular.toJson(addInfracciones);
        var json3 = angular.toJson(deleteInfracciones);
        return $http({
	          method: 'PUT',
	          url: config.baseUrl + "/actualizaDocumento",
	          params: {documentoVO : json, addInfracciones: json2, deleteInfracciones: json3},
	          dataType: "json",
	          contentType: {'Accept' : 'application/json'},
	          responseType: 'application/json'
	      });
	};
	
	//Modificacion Boletas
	this.buscarBoletaPorId = function (id) {
		return $http.get(config.baseUrl + "/consultarBoleta/" + id);
	};
	
	this.opcionesSituaciones = function () {
		return $http.get(config.baseUrl + "/opcionesBoletas");
	};
	
	this.buscarInfraccionesPorBoleta = function(id){
		return $http.get(config.baseUrl + "/consultarInfraccionesPorBoleta",
				{params:{"boletaId" : id}});
	};
	
	this.actualizarBoleta = function (boletaVO, addInfracciones, deleteInfracciones) {
        var json =  angular.toJson(boletaVO);
        var json2 = angular.toJson(addInfracciones);
        var json3 = angular.toJson(deleteInfracciones);
        return $http({
	          method: 'PUT',
	          url: config.baseUrl + "/actualizaBoleta",
	          params: {boletaVO : json, addInfracciones: json2, deleteInfracciones: json3},
	          dataType: "json",
	          contentType: {'Accept' : 'application/json'},
	          responseType: 'application/json'
	      });
	};
	
	//Creacion Documentos
	this.crearDocumento = function(documentoVO, addInfracciones){
		var json =  angular.toJson(documentoVO);
        var json2 = angular.toJson(addInfracciones);
        
        return $http({
	          method: 'POST',
	          url: config.baseUrl + "/creaDocumento",
	          params: {documentoVO : json, addInfracciones: json2},
	          dataType: "json",
	          contentType: {'Accept' : 'application/json'},
	          responseType: 'application/json'
	      });
	};
	
	//Creacion Boletas
	this.crearBoleta = function(boletaVO, addInfracciones){
		var json =  angular.toJson(boletaVO);
        var json2 = angular.toJson(addInfracciones);
        
        return $http({
	          method: 'POST',
	          url: config.baseUrl + "/creaBoleta",
	          params: {boletaVO : json, addInfracciones: json2},
	          dataType: "json",
	          contentType: {'Accept' : 'application/json'},
	          responseType: 'application/json'
	      });
	};

	//Modificacion y Creacion Documentos
	this.buscarEmpleadoPorPlaca = function (placa) {
		return $http.get(config.baseUrl + "/consultarEmpleadoPorPlaca", 
				{params:{"placa": placa}});
	};
});