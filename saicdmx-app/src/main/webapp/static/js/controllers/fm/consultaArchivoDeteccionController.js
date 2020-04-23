angular.module('siidfApp').controller('consultaArchivoDeteccionController',function($scope, $route, $filter,$location,deteccion,opcion,switchRangoFecha1,periodoFecha1,startDate1,endDate1,estatusProceso1,opcTipoArchivo1,radarArchivoProcesadosService,showAlert,ModalService,catalogoService,
				$location, utileriaService,fmService ) {
	        $scope.deteccionVO=[];
	      
			$scope.deteccion = deteccion.data;
			$scope.opcion2=2;
			
			
			if($scope.deteccion.length>0 ){
				$scope.deteccionVO=$scope.deteccion;
				
			     $scope.mostrarTabla = true;
	
			     var idArchivo=$scope.deteccion.id_archivo;
			        var idArchivo2=$scope.deteccion[0].id_archivo;
			}
//			
//			$scope.$on('$locationChangeSuccess', function(event, current, previous){
//				  $scope.proximoController = $route.current.$$route.controller;
//				  if($scope.proximoController != 'consultaArchivoOrigenController'){
//					  emptyList = [];
//					// infraccionService.setListaInfraccionesCons(emptyList);
//					  emptyParams = null;
//						$location.path('/consultaArchivoOrigen/'+true);
//				  }
//			});
//			
			$scope.regresarBusq = function(){
				
				$location.path('/consultaArchivoOrigen/'+opcion+'/'+true+'/'+switchRangoFecha1+'/'
						+periodoFecha1+'/'+startDate1+'/'+endDate1+'/'+estatusProceso1+'/'+opcTipoArchivo1);
//				$location.path('/consultaArchivoOrigen/'+true);
			
			}
			
			
			
			
			$scope.downloadReporte = function() {
				
				radarArchivoProcesadosService.obtenerReportExcel($scope.deteccion[0].id_archivo)
				.success(function(data, status, headers) {
					var filename = headers('filename');
					var contentType = headers('content-type');
					var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
					save(file , filename);
					$scope.error = false;
				})
				.error(function(data) {
					$scope.error = data;
					$scope.listaBloqueohhVO = {};
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