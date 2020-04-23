angular.module("siidfApp").service("impugnaService", function ($http, config) {	
	
	var listaImpugnacion = [];
	var listaImpugnacionCancel = [];
	var rowsPerPage = 0;
	
	this.setListaImpugnacion = function(lista) {
		listaImpugnacion = lista;
	};
	
	this.getListaImpugnacion = function() {
		return listaImpugnacion;
	}

	this.setListaImpugnacionCancel = function(listaCancel) {
		listaImpugnacionCancel = listaCancel;
	};
	
	this.getListaImpugnacionCancel = function() {
		return listaImpugnacionCancel;
	}

	this.setRowsPerPage = function(rows) {
		rowsPerPage = rows;
	};
	
	this.getRowsPerPage = function() {
		return rowsPerPage;
	}
	
	
	this.buscarImpugnaciones = function (tipoBusqueda, valor) {
		return $http.get(config.baseUrl + "/buscarImpugnaciones", 
				{params:{"tipoBusqueda": tipoBusqueda, "valor": valor}});
	};
	
	
	this.updateImpugna = function (impugnacionVO) {
		return $http.put(config.baseUrl + "/actualizaInformacion",impugnacionVO);
		
	};	
	
	this.modificaInfracc = function (parametros) {
		return $http.put(config.baseUrl + "/callImpugnaInfracc",parametros);
		
	};	
	
	this.buscarImpugnacionPorId = function (id) {
		return $http.get(config.baseUrl + "/buscarPorId", 
				{params:{"id": id}});
	};
	
	this.saveImpugna = function (impugnacionVO) {
		return $http.post(config.baseUrl + "/saveImpugnacion",impugnacionVO);
		
	};	
	
	this.obtenerInfracciones = function (id) {
		return $http.get(config.baseUrl + "/infraccionImpugna",
				{params:{ "id": id}});
	};
	
	/*** Impugnaciones Canceladas ***/
	
	this.buscarCanceladas = function (tipoBusqueda, valor) {
		return $http.get(config.baseUrl + "/buscarCanceladas", 
				{params:{"tipoBusqueda": tipoBusqueda, "valor": valor}});
	};
	
	this.obtenerDetalleCanceladas = function (valor) {
		return $http.get(config.baseUrl + "/detalleCanceladas",
				{params:{ "valor": valor}});
	};
	
	this.imprimirReporte = function (nci) {
		 	  return $http({
	        method: 'GET',
	        url: config.baseUrl + "/imprimirDetalleCancel",
	        params: { "nci" : nci },
	        dataType: "json",
	        contentType: {'Accept' : 'application/json'},
	        responseType: 'arraybuffer'
	    });
	};


            
});