angular.module("siidfApp").service("bloqueohhService", function($http, config) {
 
	
	this.consultaHandHeldBloqueados = function(bloqueohhVO){
 
 		return $http.get(config.baseUrl + "/handhelds/bloqueados",
		{
			params : {
			"placaOficial"	:  bloqueohhVO.placaOficial != ""  ?  bloqueohhVO.placaOficial.toLowerCase()  : "null",   
			"numeroSeriehh"	:  bloqueohhVO.numeroSeriehh != ""  ? bloqueohhVO.numeroSeriehh  : "null" 
			}
		
 		});
	}
	
	this.desbloquearHandHeld = function(bloqueohhRegistroVO){

 		return $http.put(config.baseUrl + "/handhelds/desbloquear",bloqueohhRegistroVO);
	}
	
	
	
});
