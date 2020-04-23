angular.module("siidfApp").controller("infraccionesAnualesController", function($scope, ModalService,infraccionesAnualesService,reporteService) {
	$scope.anioBuscar="";
	$scope.resultInfraccionesAnio=[];

	showAviso = function(messageTo) {
		ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	    });
	};
	consultaAnios = function (){
		$scope.listaAnio=[];
		infraccionesAnualesService.consultaAnios().success(function(data){
			    $scope.listaAnio=data; //.push("Seleccione el Año");
				//$scope.listaAnio=$scope.listaAnio.concat(data);
				$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.listaAnio = {};
		});
	};

   $scope.consultaInfraccionesMensuales = function (anioBuscar){
	   if ($scope.form.$invalid) {
           angular.forEach($scope.form.$error, function (field) {
             angular.forEach(field, function(errorField){
           	  errorField.$setDirty();
             })
           });
           //showAviso('Formulario incompleto');
		}else{
			$scope.ResultadoConsulta=[];
			infraccionesAnualesService.consultaInfraccionesAnual(anioBuscar).success(function(data){
				$scope.mensaje ="";
				 if(data.length===5000){
			    	  $scope.mensaje = "El reporte contiene más de 5,000 resultados, si requiere de la información completa por favor solicítela a soporte," +
			    	  		   " de lo contrario modifique los parámetros de búsqueda.";
			      }
			    $scope.ResultadoConsulta=data;
				$scope.error = false;
		}).error(function(data){
			showAviso(data.message);
			$scope.error = data;
			$scope.ResultadoConsulta = {};
		});
	 }  
   }////fin clase buscar 
	
   /*Funciones PAra bajar el Excel del reporte */
   $scope.descargaExcel = function(){///////////////////////////////////////////////
	   infraccionesAnualesService.descargarExcel("InfraccionesAnuales(Total Mensual).xls").success(function(data, status, headers) {			
			var  filename  = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
			save (file , filename);
			
	}).error(function(data) {
		showAviso(data.message);
		$scope.mostrarTabla= false;
	});
}
	function save(file, fileName) {
		 var url = window.URL || window.webkitURL;
 	 var blobUrl = url.createObjectURL(file);
 	 var a         = document.createElement('a');
 	 a.href        = blobUrl; 
		 a.target      = '_blank';
		 a.download    = fileName;
		 document.body.appendChild(a);
		 a.click();   
		}
	/* Funcion al Cargar la pagina , trae los años de los cuales se peude generar el reporte */
	consultaAnios();

});