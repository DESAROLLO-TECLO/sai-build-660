angular.module("siidfApp").service("administracionPlacasService", function($http, config) {
 
	/* PLACAS */
	
	var listaPlacas = [];
	var listaParams = {};
	//var listaPerfiles = [];administracionPlacasService
	var activo = 0;
	var rowsperpage = 0;
	
	this.setListaPermisos = function(lista){
		listaPermisos = lista;
	}
	
	this.getListaPermisos = function(){
		return listaPermisos;
	}
	
	this.setListaPerfiles = function(lista) {
		listaPerfiles = lista;
	};
	
	this.getListaPerfiles = function() {
		return listaPerfiles;
	}
	
	this.setListaPlacas = function(lista) {
		listaPlacas = lista;
	};
	
	this.getListaPlacas = function() {
		return listaPlacas;
	}
	
	this.setListaParams = function(listaP) {
		listaParams = listaP;
	};
	
	this.getListaParams = function() {
		return listaParams;
	}
	
	this.getActivo = function() {
		return status;
	}
	
	this.setActivo = function(estado) {
		status = estado;
	};
	
	this.setRowsPerPage = function(rows) {
		rowsperpage = rows;
	};
	
	this.getRowsPerPage = function() {
		return rowsperpage;
	}
	
	
	
	this.buscarPlacas = function (tipoBusqueda, valor ,isAdmin) {
		return $http.get(config.baseUrl + "/administracionPlacasController/buscarPlacas",
				{params:{"tipoBusqueda": tipoBusqueda,"valor": valor, "isAdmin" : isAdmin}});
	};
	
	this.buscaExistencia = function (tipoBusqueda, valor ,isAdmin) {
		return $http.get(config.baseUrl + "/administracionPlacasController/buscaExistencia",
				{params:{"tipoBusqueda": tipoBusqueda,"valor": valor, "isAdmin" : isAdmin}});
	};
	
	
	
	this.buscarPlacasRangoFecha = function (tipoBusqueda, fInicio ,fFin,isAdmin) {
		return $http.get(config.baseUrl + "/administracionPlacasController/buscarPlacasFecha",
				{params:{"tipoBusqueda": tipoBusqueda, "fInicio":fInicio, "fFin":fFin, "isAdmin" : isAdmin}});
	};
	
	this.buscarPlacasPeriodoFecha = function (tipoBusqueda, periodoFecha ,isAdmin) {
		return $http.get(config.baseUrl + "/administracionPlacasController/buscarPlacasFechaRango",
				{params:{"tipoBusqueda": tipoBusqueda, "periodoFecha":periodoFecha,"isAdmin" : isAdmin}});
	};
	
	
	
	
		
	this.actualizaPlaca = function (placaId,placaCodigo,observaciones) {
		return $http.get(config.baseUrl + "/administracionPlacasController/actualizaPlaca",
				{params:{"placaId": placaId,"placaCodigo": placaCodigo,"observaciones":observaciones }});
	};	
	
	this.guardaPlaca = function (placaCodigo,observaciones) {
		return $http.get(config.baseUrl + "/administracionPlacasController/guardaPlaca",
				{params:{"placaCodigo": placaCodigo,"observaciones":observaciones}});
	};	
	

	
	
	this.actualizarEstatus = function (accion,placaId) {
		return $http.get(config.baseUrl + "/administracionPlacasController/cambiarEstatusPlaca",{params:{"accion":accion, "placaId": placaId}});
	};
	
	

	
	
	this.obtenerReportExcel = function(tipoBusqueda,valor,periodoFecha,fInicio,fFin,isAdmin) {
		return $http({
			method : 'GET',
			url : config.baseUrl + "/administracionPlacasController/reportePlacas",
			
		params:{"tipoBusqueda": tipoBusqueda,"valor":valor, "periodoFecha":periodoFecha,"fInicio":fInicio,"fFin":fFin,"isAdmin" : isAdmin},

			dataType : "json",
			header : {
				"Content-type" : "application/json",
				"Accept" : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType : 'arraybuffer'
		});
	}
	
	this.getOcultarActualizar = function () {
		return $http.get(config.baseUrl + "/administracionPlacasController/getOcultarActualizar");
	};
	
	
	

	
});
