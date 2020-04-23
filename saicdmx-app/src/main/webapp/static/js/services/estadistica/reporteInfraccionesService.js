angular.module("siidfApp").service("reporteInfraccionesService", function($http, config) {
	
//	this.getCreadasPagadas = function () {
//		return $http.get(config.baseUrl + "/infracciones/creadasPagadas");
//	};
	

	 this.getCreadasPagadas = function (filtroBusqueda,tipoClasificacion) {
//		alert(filtroBusqueda);
	     var json =   angular.toJson(filtroBusqueda);
	     return $http({
	         method: 'GET',
	         url: config.baseUrl + "/infracciones/creadasPagadas",
	         params: { filtroBusqueda :json , tipoClasificacion: tipoClasificacion},
	         dataType: "json",
	         contentType: {'Accept' : 'application/json'},
	         responseType: 'application/json'
	     });
	 };
	
	
	this.getIngraccionesDispositivo = function (fechaInicio, fechaFin) {
		return $http.get(config.baseUrl + "/infracciones/infraccionesporDispositivo",
				{params:{"fechaInicio": fechaInicio, "fechaFin": fechaFin}});
	};
	
	
	
	this.getInfraccionesDepositos = function () {
		return $http.get(config.baseUrl + "/infracciones/depositosEstadisticas");
	};
	
	this.getInfraccionesEntradaSalidaDepositos = function (fechaInicio,fechaFin) {
		return $http.get(config.baseUrl + "/infracciones/entradaSalidaDepositosGrafica",
				{params:{"fechaInicio": fechaInicio, "fechaFin": fechaFin}});
	};
	
	this.getInfraccionesEntradaSalidaDepositosFechas = function (fechaInicio,fechaFin) {
		return $http.get(config.baseUrl + "/infracciones/entradaSalidaDepositosGraficaFechas",
				{params:{"fechaInicio": fechaInicio, "fechaFin": fechaFin}});
	};
	
	this.upCarto = function (urlServicio) {
		return $http.post(config.baseUrl + "/infracciones/'"+urlServicio+"'");
	};
	this.upCartoTipoFecha = function (radares, fechaInicio, fechaFin) {
		return $http.get(config.baseUrl + "/infraccionesmapa/",
				{params:{"radares[]": radares, "fechaInicio": fechaInicio, "fechaFin": fechaFin}});
	};
	
	this.tipoInfracciones = function () {
		return $http.get(config.baseUrl + "/tipoInfracciones");
	};
	
 });
