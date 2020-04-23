angular.module("siidfApp").service("msCatalogoService", function ($http, config) {	
	
	this.busquedaTipoFechasAll = function(){
		return $http.get(config.baseUrl + "/ms/catalogos/tipoFechasAll");
	};
	
	this.busquedaTipoFechasOpcion = function(opciones){
		//Espera una lista de numeros separados por comas (1,2,3....n)
		return $http.get(config.baseUrl + "/catalogos/tipoFechasOpcion",
		{
			params:{
				"opcion": opciones
			}
		});
	};
	
});
