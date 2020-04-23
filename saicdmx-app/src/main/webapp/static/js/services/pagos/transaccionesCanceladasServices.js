angular.module("siidfApp").service("transaccionesCanceladasServices", function($http, config) {
	
	this.consultaTransaccionesCanceladas=function(){
		return $http.get(config.baseUrl + "/pagos/consultaTransaccionesCanceladas");
	}
	
	this.consultaTransaccionesCanceladasByParametro=function(tipoBusqueda,valorBuscado){
		return $http.get(config.baseUrl + "/pagos/consultaTransaccionesCanceladasByParametro",
				{
					params : {"tipoBusqueda":tipoBusqueda,"parametro" : valorBuscado}	
		 		});
	}
	
	this.consultaTransaccionesCanceladasFechas=function(fechaInicio,fechaFin,tipoBusqueda){
		return $http.get(config.baseUrl + "/pagos/consultaTransaccionesCanceladasFechas",
				{
					params : {"fechaInicio":fechaInicio,
						"fechaFin":fechaFin,"tipoBusqueda":tipoBusqueda}	
		 		});
	}	
});