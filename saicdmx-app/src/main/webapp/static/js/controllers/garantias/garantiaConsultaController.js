angular
		.module('siidfApp')
		.controller(
				'garantiaConsultaController',
				function($scope, $filter, garantiaSinProcesarService, ModalService,utileriaService) {	
					$scope.order='fechaInfraccionOrder';
					$scope.opcionParametro = '1';//0
					//$scope.opcionTipoProceso = '-1';
					$scope.opcionTipoFecha = '1';//0
					$scope.listGaranId=[];
					
					
	$scope.showAviso = function(messageTo) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      }).then(function(modal) {
	        modal.element.modal();
	      });
		};
		
		requiredFields = function(){
			angular.forEach($scope.garantiaConsulta.$error, function (field) {
			      	angular.forEach(field, function(errorField){
	            	errorField.$setDirty();
	            	//alert(errorField.$name);
	            })

			});
		};
		
	$scope.buscarPorOpcionParametroA = function(opcionParametro, parametro){
		$scope.paramAA = opcionParametro;
		$scope.paramAB = parametro;
		if($scope.garantiaConsulta.$invalid){
			requiredFields();
		}else{
		garantiaSinProcesarService.buscarPorOpcionParametroA(opcionParametro, parametro).success(
				function(datos) {
					var isLleno = isEmpty(datos);
					
					if(isLleno == false){
					var listDates= ['fechaInfraccion'];
					datos = utileriaService.orderData(listDates,[],[],datos);	
					$scope.listaInfracciones = datos;
					$scope.garantiaConsultaResult = datos;
					if($scope.garantiaConsultaResult[0].idLote !=0){
						garantiaSinProcesarService.bucarCantGaranLote($scope.garantiaConsultaResult[0].idLote).success(function(data){
							$scope.listaGarantiasLote = data;
							
							for(var e=0; e<$scope.listaGarantiasLote.length; e++){
								$scope.listGaranId.push($scope.listaGarantiasLote[e].garantiaId);
							}
						})
					
						$scope.banderaMasiva=true;
					}
					else{
						$scope.banderaMasiva=false;
					}
					}else{
					$scope.garantiaConsultaResult = -1;
					$scope.showAviso("No se encontraron registros");
					}
					}).error(function(datos) {
				$scope.garantiaConsultaResult = -1;
				$scope.error = datos;
				$scope.datos = {};
				$scope.showAviso(datos.message);
					
				});
		}
	}

	$scope.buscarPorOpcionParametroB = function(opcionTipoFecha,opcionTipoProceso,fechaInicio, fechaFin){
		if(opcionTipoProceso == null){
			opcionTipoProceso = -1;}
		if($scope.garantiaConsulta.$invalid){
			requiredFields();
		}else{
			$scope.paramBA = opcionTipoFecha;
			$scope.paramBB = opcionTipoProceso;
			$scope.paramBC = fechaInicio;
			$scope.paramBD = fechaFin;
			garantiaSinProcesarService
				.buscarPorOpcionParametroB(opcionTipoFecha,opcionTipoProceso,fechaInicio, fechaFin).success(
						function(datos) {
							var isLleno = isEmpty(datos);
							if(isLleno == false){					
							var listDates= ['fechaInfraccion'];
							datos = utileriaService.orderData(listDates,[],[],datos);				
							$scope.garantiaConsultaResult = datos;
							
							}else{
								
								$scope.garantiaConsultaResult = -1;
								$scope.fIni = false;
								$scope.fFn = false;
								$scope.tipoFecha = false;
								$scope.showAviso("No se encontraron registros");
							}
							
						}).error(function(datos) {
						$scope.garantiaConsultaResult = -1;
						$scope.error = datos;
						$scope.datos = {};
						$scope.showAviso(datos.message);
							
						});
			}
	}
	
	
	
	
	$scope.descargaPDFEntregada = function(garantiaId) {
		garantiaSinProcesarService.buscarPDFEntrega(garantiaId)
				.success(function(data, status, headers) {
					var filename = headers('filename');
					var contentType = headers('content-type');
					var file = new Blob([ data ], {
						type : 'application/pdf;base64,'
					});

					save(file, filename);
					$scope.error = false;
				}).error(function(result) {
					$scope.error = result;
					$scope.resultado = {};
				});

	}
			 
	$scope.descargaPDFRecibida = function (garantiaID) {
	 garantiaSinProcesarService.buscarPDF(garantiaID).success(
			 function(data, status, headers) {
				 var filename = headers('filename');
				 var contentType = headers('content-type');
				 var file = new Blob([ data ], {
					 type : 'application/pdf;base64,'
						 });
				 save(file, filename);
				 $scope.error = false;
				 }).error(function(data) {
					 $scope.error = data;
					 $scope.obtPDF = {};
				});
			}
	
	
	$scope.descargaPDFRecibidaMasiva = function( idLote, idGarantiaInd) {

				
	garantiaSinProcesarService.bucarCantGaranLote(idLote).success(function(data){
					$scope.listaGarantiasLote = data;
					
					for(var e=0; e<$scope.listaGarantiasLote.length; e++){
						$scope.listGaranId.push($scope.listaGarantiasLote[e].garantiaId);
					}
					
					
		garantiaSinProcesarService.buscarPDFMasiva(idGarantiaInd, $scope.listGaranId.length, idLote).success(function(data, status, headers) {
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});

			save(file, filename);
			$scope.error = false;

		}).error(function(data) {

			$scope.error = data;
			$scope.obtPDF = {};

		});
		
	}).error(function(data){
		$scope.error=data;
	});
	
		$scope.listGaranId=[];
	}
	
	
			
			function save(file, fileName) {
				var url = window.URL || window.webkitURL;
				var blobUrl = url.createObjectURL(file);
				var a = document.createElement('a');
				a.href = blobUrl;
				a.target = '_blank';
				a.download = fileName;
				document.body.appendChild(a);
				a.click();	
			}
						
function obtenerTipoProceso(){
	garantiaSinProcesarService.buscarTipoProceso().success(
			function(data) {
				$scope.opcionTipoProcesoList = data;
				$scope.error = false;
			
			}).error(function(data){
				$scope.error = data;
				
			});
	
}

 $scope.getVal=function(){
       
        if($scope.opcion == 2){
        	$scope.consultaPorParametro = true;
        	$scope.consultaPorFecha = false;
        	$scope.opcionTipoProceso = '-1';
        	$scope.opcionTipoFecha = '1';//0
			$scope.fechaInicio = '';
			$scope.fechaFin = '';
			$scope.opBusq = true;
			$scope.opBusqA=false;
			$scope.garantiaConsultaResult = -1;
			$scope.parametroRequired = true;
            $scope.parametroReqB = false;
            $scope.garantiaConsulta.$setPristine();
        }
        if($scope.opcion == 1){
        	obtenerTipoProceso()
        $scope.consultaPorFecha = true;
    	$scope.consultaPorParametro = false;
    	$scope.parametro = "";
    	$scope.opcionParametro = '1';//0
    	$scope.opBusq = false;
    	$scope.opBusqA=true;
    	$scope.garantiaConsultaResult = -1;
    	$scope.tipoFecha = false;
		$scope.fIni = false;
		$scope.fFn = false;
		$scope.parametroRequired = false;
        $scope.parametroReqB = true;
        $scope.garantiaConsulta.$setPristine();
        }
        
      }
 
	function isEmpty(obj) {
		
		for ( var key in obj) {
			if (obj.hasOwnProperty(key))
				
			return false;
		}
		return true;
	}
	
	
	$scope.descargaVaucher = function (infracNum) {
		var tipovoucher = 'reporteVoucher';
		garantiaSinProcesarService.descargarVaucher(infracNum,tipovoucher).success(
				function(data, status, headers) {
				var filename = headers('filename');
				var contentType = headers('content-type');
				var file = new Blob([ data ], {
					type : 'application/pdf;base64,'
				});
				save(file, filename);
				$scope.error = false;
						
		}).error(function(data) {
			$scope.showAviso("No se logro generar el reporte");
			$scope.error = data;
			$scope.obtPDF = {};
		});
	 }
	 
	 $scope.descargarExcel = function() {
		 if ($scope.consultaPorParametro) {
			 generarExcelConsultaA($scope.paramAA, $scope.paramAB);
		 } else {
			 generarExcelConsultaB($scope.paramBA, $scope.paramBB, $scope.paramBC, $scope.paramBD);
		 }
	 };
	 
	 generarExcelConsultaA = function(opcionParametro, parametro) {
			garantiaSinProcesarService
					.generarExcelConsultaA(opcionParametro, parametro)
					.success(
							function(data, status, headers) {
								var filename = headers('filename');
								var contentType = headers('content-type');
								var file = new Blob(
										[ data ],
										{
											type : 'application/vnd.ms-excel;base64,'
										});
								save(file, filename);
								$scope.error = false;
							}).error(function(data) {
						$scope.error = data;
						$scope.reporte = {};
					});
		};

		generarExcelConsultaB = function(opcionTipoFecha,opcionTipoProceso, fechaInicio, fechaFin) {
			garantiaSinProcesarService.generarExcelConsultaB(opcionTipoFecha,opcionTipoProceso, fechaInicio, fechaFin).success(
				function(data, status, headers) {
					var filename = headers('filename');
					var contentType = headers('content-type');
					var file = new Blob(
								[ data ],
								{
									type : 'application/vnd.ms-excel;base64,'
								});
					save(file, filename);
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					$scope.reporte = {};
			});
		};
		
});