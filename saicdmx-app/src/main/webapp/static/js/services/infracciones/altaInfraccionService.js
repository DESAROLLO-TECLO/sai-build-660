angular.module("siidfApp").service("altaInfraccionService", function($http, config){
	
	this.altaInfraccion = function(altaInfraccionVO){//R
		return $http.post(config.baseUrl + "/altaInfraccion", altaInfraccionVO);
	}
	
	this.modificaInfraccion = function(altaInfraccionVO){//R
		return $http.post(config.baseUrl + "/modificaInfraccion", altaInfraccionVO);
	}
});	