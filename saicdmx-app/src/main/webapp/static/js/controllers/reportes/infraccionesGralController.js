angular.module("siidfApp").controller("infraccionesGralController", function($scope, ModalService,ReporteDinamicosServices) {
   $scope.parametroBusqueda = {fechaInicio : "",fechaFin:""};
   
$scope.consultaInfraccionesGral = function(fechaInicio ,fechaFin){
	if ($scope.form.$invalid) {
        angular.forEach($scope.form.$error, function (field) {
          angular.forEach(field, function(errorField){
        	  errorField.$setDirty();
          })
        });
        //showAviso('Formulario incompleto');
	}else{
	 $scope.ResultadoConsulta=[];
	ReporteDinamicosServices.consultaInfraccionesGral(fechaInicio,fechaFin).success(function(data){
		$scope.mensaje ="";
			 if(data.length===5000){
				 $scope.mensaje = "El reporte contiene más de 5,000 resultados, si requiere de la información completa por favor solicítela a soporte," +
  	  		   " de lo contrario modifique los parámetros de búsqueda.";
		      }
		  $scope.ResultadoConsulta=data;
		
	}).error(function(data){
		$scope.ResultadoConsulta=[];
		showAviso(data.message);
		$scope.error=data;
	});
	}
};

showAviso = function(messageTo) {
	ModalService.showModal({
        templateUrl: 'views/templatemodal/templateModalAviso.html',
        controller: 'mensajeModalController',
        inputs:{ message: messageTo}
      	}).then(function(modal) {
      		modal.element.modal();
    });
};

$scope.descargaExcel = function(){
	ReporteDinamicosServices.descargarExcel("descargarExcelInfraccionesGral","InfraccionesGeneral.xls").success(function(data, status, headers) {			
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
});
