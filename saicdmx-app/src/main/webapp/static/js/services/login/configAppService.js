angular.module('siidfApp').service('configAppService', function($http, config) {
	
	var app = "/aplicacion";
	var urlConfig = config.baseUrl+app;
	
	/*
	 * @author: Cesar Gomez
	 * @return: data
	*/
	this.configuracionAplicacion = function(data) {
		return $http.get(urlConfig + "/configuraciones");	
	};
	

	
 
});