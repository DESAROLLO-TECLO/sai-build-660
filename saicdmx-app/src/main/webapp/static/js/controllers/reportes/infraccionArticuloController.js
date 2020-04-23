angular.module("siidfApp").controller("infraccionArticuloController", function($scope, ModalService,ReporteDinamicosServices) {
	$scope.parametrosBusquedaExcelTmp={};
	$scope.listaConceptos=[{conceptoId:0,componenteId:0,conceptoNombre:'Seleccione'}];
	$scope.parametroBusquedaVO={};
	$scope.fechaInicio="";
	$scope.fechaFin="";
	$scope.articuloID;
	
	$scope.ResultadoConsulta=[];
	
	CatalogoArticulos = function (){
		$scope.listaArticulos=[];
		ReporteDinamicosServices.Catalogos("Articulos").success(function(data){
				$scope.listaArticulos=data;
				$scope.requiredConcepto=true;
				$scope.hideSeleccione=true;
				//$scope.parametroBusqueda.ArticuloVO = $scope.listaArticulos[0].id;
				$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.listaArticulos = {};
		});
	};
	
$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
		$('.selectpicker').selectpicker();
		$('.selectpicker').selectpicker('refresh');
		
		if($scope.listaArticulos.length > 4 ){
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-searchbox').show();
		}else{
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-searchbox').hide();
		}
		
		if($scope.listaArticulos.length > 1 ){
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-actionsbox').show();
		}else{
			$('.selectpicker').selectpicker();
			$('.selectpicker').selectpicker('refresh');
			$('.bs-actionsbox').hide();
		}
		
	});
/*funciones para hacer la busqueda de infracciones por articulo o varios articulos * */
$scope.consultaInfraccionesArticulo=function(parametrosVO){
		if ($scope.form.$invalid) {
            angular.forEach($scope.form.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
              })
            });
           // showAviso('Formulario incompleto');
		}else{
			
			var articulos ="";
			
			angular.forEach($scope.listaArticulos, function(art, key){
				angular.forEach(parametrosVO.articuloVO, function(id, key){
					if(parseInt(id) === art.id) {
						articulos=articulos + art.nombre +", ";
					}
				});
				
			});

			var objectParametros={
					id:parametrosVO.articuloVO,
					articulo:articulos,
					fechaInicio:parametrosVO.fechaInicio==undefined ? "" : parametrosVO.fechaInicio ,
					fehaFin:parametrosVO.fechaFin==undefined ? "" : parametrosVO.fechaFin
			};
			
			$scope.ResultadoConsulta = [];
			$scope.mensaje ="";
			
			ReporteDinamicosServices.consultaInfraccionesArticulo(objectParametros).success(function(data){
				
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
	
	copyParametrosBusqueda=function(parametroBusquedaVO){
		angular.copy(parametroBusquedaVO, $scope.parametrosBusquedaExcelTmp);
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
	    
	    $scope.descargaExcel = function(){
	    	ReporteDinamicosServices.descargarExcel("descargarExcelInfraccionesGral","InfraccionesporArticulo.xls").success(function(data, status, headers) {			
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
	    	};
	
	/*Llamadas al cargar la vista */
	CatalogoArticulos();

});