
angular.module("siidfApp").service("catPagoInfraccionService", function($http, config) {
   
//	this.tipoBusquedaInfracciones= function(){
// 		return $http.get(config.baseUrl + "/pagos/tipoBusqueda");
//	}
	
	this.tipoBusquedaInfracciones= function(){
 		return $http.get(config.baseUrl + "/pagos/tipoBusquedaActa");
	}
	
	this.tipoPagoInfracciones= function(){
 		return $http.get(config.baseUrl + "/pagos/tipoPago");
	}
	this.entidadesPagoInfracciones= function(){
 		return $http.get(config.baseUrl + "/pagos/entidadesPago");
	}
	
	this.obtenerPerfilCajero= function( placa ){
 		return $http.get(config.baseUrl + "/pagos/perfil", {
			params : {
				"placa"	 :  placa  
 			}	
 		});
	}
	this.obtenerTokenMit= function(){
 		return $http.get(config.baseUrl + "/pagos/mit/token",{responseType:'text'});
	}
	this.obtenerTiosBusquedaTransacciones=function(){
		return $http.get(config.baseUrl + "/pagos/tipoBusquedaTransaccion");
	}
	 
});
