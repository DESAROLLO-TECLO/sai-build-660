angular.module("siidfApp").service("semoviService", function ($http, config) {	
		
		
		this.obtenerArchivosActivos = function () {
			return $http.get(config.baseUrl + "/semoviController/buscaCatTipoArchivosActivos");
		};
		
		this.validaArchivoExiste = function (fechaArchivo,codigoArchivo) {
			return $http.get(config.baseUrl + "/semoviController/validaArchivoExiste",
					{params:{"fechaArchivo": fechaArchivo, "codigoArchivo": codigoArchivo}});
		};
		
		this.generaArchivoPuntoInfraccion = function (fechaArchivo) {
			return $http.get(config.baseUrl + "/semoviController/generaArchivoPuntoInfraccion",
					{params:{"fechaArchivo": fechaArchivo}});
		};
		
		this.descargaExcel = function (nombreArchivo,codigo,fechaArchivo) {

			return $http({
		        method: 'GET',
		        url: config.baseUrl + "/semoviController/descargaExcel",
		        params: {"nombreArchivo": nombreArchivo, "codigo": codigo ,"fechaArchivo":fechaArchivo},
		        dataType: "json",
		        header :{ "Content-type": "application/json",
		        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
		        },
		        responseType: 'arraybuffer'
		    });
		
		
		};
		
		this.cargarArchivosSemovi = function (file,fechaArchivo,tipoArchivo) {
	
			var fd = new FormData();
		    fd.append("file", file);
		    fd.append("fechaArchivo", angular.toJson(fechaArchivo));
		    fd.append("tipoArchivo", angular.toJson(tipoArchivo));
			 return $http({
			        method: 'POST',
			        url: config.baseUrl + "/semoviController/cargarArchivosSemovi",
			        headers	:{'Content-type':undefined},
					data: fd,
					transformRequest : angular.identy
			    });
			 
		};
		
		this.consultar = function (tipoBusqueda,fechaInicio,fechaFin) {
			return $http.get(config.baseUrl + "/semoviController/consultar",
					{params:{"tipoBusqueda": tipoBusqueda, "fechaInicio": fechaInicio,"fechaFin": fechaFin}});
		};
		
		this.buscaCatTipoArchivos = function () {
			return $http.get(config.baseUrl + "/semoviController/buscaCatTipoArchivos");
		};
		
//*******************************************************************
//		INICIO VEHICULOS ROBADO
//		CONSULTA
	/*	
		this.buscarOpcionesVehRob = function () {
			return $http.get(config.baseUrl + "/semoviController/buscarOpciones");
		};
	
	
		this.buscarVehiculoRobadoConsulta = function (opcion, valor) {
			return $http.get(config.baseUrl + "/semoviController/consultaVehRobados",
					{params:{"opcion": opcion, "valor": valor}});
		};
		
		this.buscarVehiculoRobado = function (valor) {
			return $http.get(config.baseUrl + "/semoviController/consultarVehRobados",
					{params:{"valor": valor}});
		};
		
		this.altaExpediente = function(expedienteNombre){
			return $http.get(config.baseUrl + "/semoviController/altaExpediente",
					{params:{"expedienteNombre": expedienteNombre}});
		};
		
		this.buscarVehiculoRobadoHist = function(id){
			return $http.get(config.baseUrl + "/semoviController/consultarVehRobadosHist",
					{params:{"id": id}});
		};
		
		this.consultarVehDetalle = function(id){
			return $http.get(config.baseUrl + "/semoviController/consultarVehDetalle",
					{params:{"id": id}});
		};
		
		this.buscarModelo = function () {
			return $http.get(config.baseUrl + "/semoviController/buscarModelo");
		};
		this.buscarTipo = function (id) {
			return $http.get(config.baseUrl + "/semoviController/buscarTipo",
					{params:{"id": id}});
			
		};
		
		this.altaVehiculoRobado = function(vehiculoRobadoVO){
			var json =  angular.toJson(vehiculoRobadoVO);
			return $http.get(config.baseUrl + "/semoviController/guardarVehiculoRobado",{params:{"vehiculoRobadoVO": json}});
		};
		
		this.updateVehiculoRobado = function(vehiculoRobadoVO){
			var json =  angular.toJson(vehiculoRobadoVO);
			return $http.get(config.baseUrl + "/semoviController/updateVehiculoRobado",{params:{"vehiculoRobadoVO": json}});
		};
		
		this.buscarVehiculosEstatus = function () {
			return $http.get(config.baseUrl + "/semoviController/buscarVehiculosEstatus");
		};
		
		this.cancelar = function (idRobo) {
			return $http.get(config.baseUrl + "/semoviController/cancelarRegistroRob",
					{params:{"idRobo": idRobo}});
			
		};
		
		
		this.obtenerReporteExcel = function() {		
			return $http({
				method : 'GET',
				url : config.baseUrl + "/semoviController/getReport",
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
			return $http.get(config.baseUrl + "/semoviController/verifiReporteExist",
					{params:{"exp": exp, "turno":turno}});
			
		};
		
		this.updateExpediente = function (expNew, expOld) {
			return $http.get(config.baseUrl + "/semoviController/updateExistExp",
					{params:{"expNew": expNew, "expOld": expOld}});
			
		};
		
		
		var parametroRobo = null;
	   
		this.setParametro =  function(parametro){
			parametroRobo = parametro;
		};
			
		this.getParametro = function() {
			return parametroRobo;
		};
		
		
		
		*/
		

});
