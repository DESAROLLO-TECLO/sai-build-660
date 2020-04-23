angular.module("siidfApp").service("ReporteDinamicosServices", function($http, config) {
//	
//	/*Función para mandar petición y comprobar si 
//	 * hay alguna configuración de búsqueda existente*/
//	this.compruebaPermisosAcceso = function(){
//		return $http.get(config.baseUrl + "/compruebaPermisosAcceso");
//	};
//	
	
	this.ListaReportes = function(){
		return $http.get(config.baseUrl + "/GetListaReportes", {});
	};
/*RptEntradasDeposito       catalogos y funciones */
	this.Catalogos = function(tipo){
		return $http.get(config.baseUrl + "/CatalogosEntradaDeposito", {
			params:{"tipo": tipo}});
		};
		
		this.ConsultaEntroDeposito = function(NoInfraccion,NomDeposito,causaIngreso,tipoIngreso){
			return $http.get(config.baseUrl + "/EntradaDeposito", {
				params:{"NoInfraccion": NoInfraccion,
					    "NomDeposito":NomDeposito,
					    "causaIngreso":causaIngreso,
					    "tipoIngreso":tipoIngreso}});
			};
		
/* Metodos del reporte Infracciones Diarias **/
	this.consultaInfracciones = function (fInicio){
		return $http.get(config.baseUrl + "/ConsultaInfraccionesDiarias" ,{
			params:{"fInicio":fInicio}});
	};
/* Metodos para realizar consulta infracciones diarias Detalle */
	this.consultaInfraccionesDetalle = function (fInicio){
		return $http.get(config.baseUrl + "/ConsultaInfraccionesDiariasDetalle" ,{
			params:{"fInicio":fInicio}});
	};
	
/*Metodo para Generar Reporte Excel */
	this.descargarExcel = function(tipo,nombre){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/"+ tipo ,
	        params: {"nombre":nombre},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
/*Reporte Infracciones GRAL */
	this.consultaInfraccionesGral = function(fInicio,fFin){
		return $http ({method:'GET',url:config.baseUrl + "/consultaInfraccionesGral",
				params:{"fInicio":fInicio,
					    "fFin":fFin}});
	};
	
/*Reporte infracciones Articulo*/
	this.consultaInfraccionesArticulo=function(parametrosBusquedaV){
		parametrosBusquedaVO={
				id: parametrosBusquedaV.id,
				articulo:parametrosBusquedaV.articulo,
				fechaInicio: parametrosBusquedaV.fechaInicio,
				fechaFin : parametrosBusquedaV.fehaFin
		}
		return $http.post(config.baseUrl + "/consultaInfraccionesArticulo",parametrosBusquedaVO);
	};
/*Reporte Infracciones por Delegacion*/
	this.consultaInfraccionesDelegaciones=function(parametrosBusquedaV){
		parametrosBusquedaVO={
				id: parametrosBusquedaV.id,
				articulo:parametrosBusquedaV.articulo,
				fechaInicio: parametrosBusquedaV.fechaInicio,
				fechaFin : parametrosBusquedaV.fehaFin
		}
		return $http.post(config.baseUrl + "/consultaInfraccionesDelegaciones",parametrosBusquedaVO);
	};

	
	/*Reporte infracciones por empleado */
	this.consultaEmpleados = function (PlacasOficial){
		return $http ({method:'GET',url:config.baseUrl + "/consultaEmpleados",
			params:{"PlacasOficial":PlacasOficial}});
    };
	
//	this.consultaEmpleados = function (){
//		return $http.get(config.baseUrl + "/consultaEmpleados",
//				params  {"placa":placa});
//	};
	
	this.consultaInfraccionesEmpleado = function (parametrosBusquedaV){
		parametrosBusquedaVO={
				placasOficial: parametrosBusquedaV.placasOficial,
				fechaInicio: parametrosBusquedaV.fechaInicio,
				fechaFin : parametrosBusquedaV.fehaFin,
				placaBuscadas : parametrosBusquedaV.placaBuscadas
		}
		return $http.post(config.baseUrl + "/consultaInfraccionesEmpleados",parametrosBusquedaVO);
	};
	
	this.descargarExcelEmpleado = function(nombre){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/descargarExcelEmpleado",
	        params: {"nombre":nombre},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
});