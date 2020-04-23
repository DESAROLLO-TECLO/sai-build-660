angular.module("siidfApp").service("infraccionAllDataService", function($http, config) {
	
	var allData;
	
	this.createAllData = function(){
		return $http.get(config.baseUrl + "/getInfraccionAltaCatalogo");
	}
});