angular.module("siidfApp").controller("totalInfraccionesMesController", function($scope, ModalService,totalInfraccionesMensualService) {
	$scope.fechaInicio="";
	$scope.fechaFin="";
	
	
	$scope.conusltaInfracciones = function(fechaInicio,fechaFin){
		if ($scope.form.$invalid) {
			requiredFields();
		}else{
			 $scope.ResultadoConsulta=[];
			totalInfraccionesMensualService.consultaInfracciones(fechaInicio,fechaFin).success(function(data){
				$scope.mensaje ="";
				if(data.length===5000){
					$scope.mensaje = "El reporte contiene más de 5,000 resultados, si requiere de la información completa por favor solicítela a soporte," +
	    	  		   " de lo contrario modifique los parámetros de búsqueda.";
			      }
			  $scope.ResultadoConsulta = data;			  
			}).error(function(data){
				showAviso(data.message);
				$scope.error = data;
				$scope.ResultadoConsulta=[];
			});
		}
	}

/*Funciones de utileria  ,descargar excel ,validar formulario*/
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	/* Metodo para generar Excel */
	$scope.descargaExcel = function(){///////////////////////////////////////////////
		totalInfraccionesMensualService.descargarExcel("TotalInfracionesMensuales.xls").success(function(data, status, headers) {			
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

	showAviso = function(messageTo) {
		ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	    });
	};
});//Fin de clase controller 
