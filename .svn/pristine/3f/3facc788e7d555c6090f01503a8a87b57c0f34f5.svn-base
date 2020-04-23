angular.module('siidfApp').service('liberacionVehiculoService', function($http, config) {
	
	this.validarDeposito = function () {
		return $http.get(config.baseUrl + "/validarDeposito"); 
	};
	
	this.buscaSalidaDeposito = function(tipoBusqueda, valor, deposito){
		return $http.get(config.baseUrl + "/buscaSalidaDeposito",
		{params:{"tipoBusqueda": tipoBusqueda, "valor": valor, "deposito": deposito}});
	}
	
	this.buscarDetalleDeposito = function (infracNum) {
		return $http.get(config.baseUrl + "/buscaSalidaDepositoModificacion/" + infracNum);
	};
	
	this.guardarSalidaDeposito = function (objectVO){
		var json =  angular.toJson(objectVO);
        
        return $http({
	          method: 'POST',
	          url: config.baseUrl + "/guardarSalidaDeposito",
	          params: {objectVO : json},
	          dataType: "json",
	          contentType: {'Accept' : 'application/json'},
	          responseType: 'application/json'
	      });
	}
});