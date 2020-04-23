angular.module("siidfApp").service("controlSuministrosService", function ($http, config) {	
	
	// Almacen
	this.altaAlmacen = function (almacenVO) {
		return $http.post(config.baseUrl + "/controlAlmacen", almacenVO);
	};
	
	//Suministros
	this.altaSuministro = function (suministroVO) {
		return $http.post(config.baseUrl + "/suministroAreas", suministroVO);
	};
	
	//Devoluciones
	this.altaDevolucion = function (devolucionVO) {
		return $http.post(config.baseUrl + "/devoluciones", devolucionVO);
	};
		
	//Modificacion Auxiliar
	this.buscarAuxiliarPorId = function (id) {
		//return auxiliarVO;
		return $http.get(config.baseUrl + "/modificarAuxiliar/" + id);
	};
	
	this.buscarAuxiliarPorPlaca = function(oficial_placa){
		return $http.get(config.baseUrl + "/consultarPlaca", {
			params : {
				"oficial_placa" : oficial_placa
			}
	});
	};
	// CAmbiar auxiliar
	this.cambiarAuxiliar = function (valores) {
		return $http.post(config.baseUrl + "/cambiarAuxiliar", valores);
	};

	//buscar Auxiliar alta
	this.buscarAuxiliarAreaRegion = function (id,id2) {
		//return auxiliarVO;
		return $http.get(config.baseUrl + "/altaAuxiliar/" + id +"/"+ id2 );
	};
	
	// Alta de auxiliar
	this.altaAuxiliar = function (valoresAux) {
		return $http.post(config.baseUrl + "/nuevoAuxiliar", valoresAux);
	};
	// Quitar auxiliar
	this.eliminarAuxiliar = function(valoresAuxBaja){
		return $http.post(config.baseUrl + "/eliminarAuxiliar", valoresAuxBaja);
	};
	
});