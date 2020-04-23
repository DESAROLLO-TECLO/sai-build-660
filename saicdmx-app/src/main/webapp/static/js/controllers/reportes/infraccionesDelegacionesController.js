angular.module("siidfApp").controller("infraccionesDelegacionesController", function($scope, ModalService,ReporteDinamicosServices,reporteService) {
	//$scope.parametrosBusquedaExcelTmp={};
	$scope.listaConceptos=[{conceptoId:0,componenteId:0,conceptoNombre:'Seleccione'}];
	$scope.parametroBusquedaVO={DelegacionVO:""};
	$scope.fechaInicio="";
	$scope.fechaFin="";
	
	
	CatalogoDelegaciones = function (){
		$scope.listaDelegaciones=[];
		ReporteDinamicosServices.Catalogos("Delegaciones").success(function(data){
				$scope.listaDelegaciones=data;
				$scope.requiredConcepto=true;
				$scope.hideSeleccione=true;
				$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.listaDelegaciones = {};
		});
	};

	/*Funciones para trabar con un solo service comiensa metodo inamico */
	$scope.consultaInfraccionesDelegaciones=function(parametrosVO){
		if ($scope.form.$invalid) {
            angular.forEach($scope.form.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
              })
            });
        // ' showAviso('Formulario incompleto');
		}else{
			var articulos ="";		
			angular.forEach($scope.listaDelegaciones, function(art, key){
				angular.forEach(parametrosVO.DelegacionVO, function(id, key){
					if(parseInt(id) === art.id) {
						articulos=articulos + art.nombre +", ";
					}
				});
				
			});
			var objectParametros={
					id:parametrosVO.DelegacionVO,
					articulo:articulos,
					fechaInicio:parametrosVO.fechaInicio==undefined ? "" : parametrosVO.fechaInicio ,
					fehaFin:parametrosVO.fechaFin==undefined ? "" : parametrosVO.fechaFin
			};		
			$scope.ResultadoConsulta = [];
			ReporteDinamicosServices.consultaInfraccionesDelegaciones(objectParametros).success(function(data){
				 $scope.mensaje ="";
				if(data.length===5000){
					 $scope.mensaje = "El reporte contiene más de 5,000 resultados, si requiere de la información completa por favor solicítela a soporte," +
	    	  		   " de lo contrario modifique los parámetros de búsqueda.";
			      }
					$scope.ResultadoConsulta	= data;
					}).error(function(data){
						showAviso(data.message);
						$scope.listaBitacoraCambios=[];
					});
		}   
	};

	$scope.descargaExcel = function(){
		ReporteDinamicosServices.descargarExcel("descargarExcelInfraccionesGral","InfraccionesporDelegaciones.xls").success(function(data, status, headers) {			
			var  filename  = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
			save (file , filename);
			
	}).error(function(data) {
		showAviso(data.message);
		$scope.mostrarTabla= false;
	});
	}
	
	/*funciones utilitarias del modulo */
$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {		
		$('.selectpicker').selectpicker();
		$('.selectpicker').selectpicker('refresh');
		
		if($scope.listaDelegaciones.length > 4 ){
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-searchbox').show();
		}else{
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-searchbox').hide();
		}
		
		if($scope.listaDelegaciones.length > 1 ){
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-actionsbox').show();
		}else{
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-actionsbox').hide();
		}
		
	});


function save(file, fileName) {
	 var url = window.URL || window.webkitURL;
	 var blobUrl = url.createObjectURL(file);
	 var a         = document.createElement('a');
	 a.href        = blobUrl; 
	 a.target      = '_blank';
	 a.download    = fileName;
	 document.body.appendChild(a);
	 a.click();   
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
/*aL CARGAR LA PAGINA*/
CatalogoDelegaciones();
	
});