angular.module("siidfApp").service("autosRobadosService", function ($http, config) {	
		
	
		this.buscarOpcionesVehRob = function () {
			return $http.get(config.baseUrl + "/autoRobadoController/buscarOpciones");
		};
	
	
		this.buscarVehiculoRobadoConsulta = function (opcion, valor) {
			return $http.get(config.baseUrl + "/autoRobadoController/consultaVehRobados",
					{params:{"opcion": opcion, "valor": valor}});
		};
		
		this.buscarVehiculoRobado = function (valor) {
			return $http.get(config.baseUrl + "/autoRobadoController/consultarVehRobados",
					{params:{"valor": valor}});
		};
		
		this.altaExpediente = function(expedienteNombre){
			return $http.get(config.baseUrl + "/autoRobadoController/altaExpediente",
					{params:{"expedienteNombre": expedienteNombre}});
		};
		
		this.buscarVehiculoRobadoHist = function(id){
			return $http.get(config.baseUrl + "/autoRobadoController/consultarVehRobadosHist",
					{params:{"id": id}});
		};
		
		this.consultarVehDetalle = function(id){
			return $http.get(config.baseUrl + "/autoRobadoController/consultarVehDetalle",
					{params:{"id": id}});
		};
		
		this.buscarModelo = function () {
			return $http.get(config.baseUrl + "/autoRobadoController/buscarModelo");
		};
		this.buscarTipo = function (id) {
			return $http.get(config.baseUrl + "/autoRobadoController/buscarTipo",
					{params:{"id": id}});
			
		};
		
		this.altaVehiculoRobado = function(vehiculoRobadoVO){
			var json =  angular.toJson(vehiculoRobadoVO);
			return $http.get(config.baseUrl + "/autoRobadoController/guardarVehiculoRobado",{params:{"vehiculoRobadoVO": json}});
		};
		
		this.updateVehiculoRobado = function(vehiculoRobadoVO){
			var json =  angular.toJson(vehiculoRobadoVO);
			return $http.get(config.baseUrl + "/autoRobadoController/updateVehiculoRobado",{params:{"vehiculoRobadoVO": json}});
		};
		
		this.buscarVehiculosEstatus = function () {
			return $http.get(config.baseUrl + "/autoRobadoController/buscarVehiculosEstatus");
		};
		
		this.cancelar = function (idRobo) {
			return $http.get(config.baseUrl + "/autoRobadoController/cancelarRegistroRob",
					{params:{"idRobo": idRobo}});
			
		};
		
		
		this.obtenerReporteExcel = function() {		
			return $http({
				method : 'GET',
				url : config.baseUrl + "/autoRobadoController/getReport",
				params : { },
				dataType : "json",
				header : {
					"Content-type" : "application/json",
					"Accept" : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
				},
				responseType : 'arraybuffer'
			});
		}
		
		this.verificarExiste = function (exp, turno) {
			return $http.get(config.baseUrl + "/autoRobadoController/verifiReporteExist",
					{params:{"exp": exp, "turno":turno}});
			
		};
		
		this.updateExpediente = function (expNew, expOld) {
			return $http.get(config.baseUrl + "/autoRobadoController/updateExistExp",
					{params:{"expNew": expNew, "expOld": expOld}});
			
		};
		
		this.consultarExpediente = function (idExp) {
			return $http({
				method : 'GET',
				url : config.baseUrl + "/autoRobadoController/consultarExpediente",
				params : {"idExp": idExp },
				dataType : "json"
			});
				
		};
		
		
		
		var parametroRobo = null;
	   
		this.setParametro =  function(parametro){
			parametroRobo = parametro;
		};
			
		this.getParametro = function() {
			return parametroRobo;
		};
		
		
		
		
		

});
