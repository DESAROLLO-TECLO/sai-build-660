angular.module('siidfApp').controller('consultaInfraccionController',function($scope, $filter, $controller, consultaInfraccionService,catPagoInfraccionService, ModalService,utileriaService) {
		$scope.order='pagoFecha';
  		$scope.infraccionDepositoVO = {}; 
		$scope.listaInfraccionesPagadas = {};
	    $controller('modalController',{$scope : $scope });

	    /* Modal Error */
		$scope.showError = function(messageTo) {
			ModalService.showModal({
			     templateUrl : 'views/templatemodal/templateModalError.html',
			     controller : 'mensajeModalController',
			     inputs : {	message : messageTo	}
							}).then(function(modal) {
						modal.element.modal();
				 });
		};

		// CONSULTA INFRACCIONES PAGADAS  

		$scope.consultaInfraccionesPagadas = function(	infraccionDepositoVO) {
		
			if($scope.infraccionesDeposito.$invalid) {
 	             angular.forEach($scope.infraccionesDeposito.$error, function (field) {
 
	            	angular.forEach(field, function(errorField){
	            		errorField.$setDirty(); 
	            	})
	            });
 			}
			else{
			
 			consultaInfraccionService.consultaInfraccionesPagadas( infraccionDepositoVO)
					.success( function(data) {
						var listDates= ['pagoFecha'];
						data = utileriaService.orderData(listDates,[],[],data);				
 					    $scope.listaInfraccionesPagadas = data;
							
					}).error(function(data) {
						$scope.showAviso (data.message);
						$scope.modalDetalleInfraccionPagada();
						$scope.listaInfraccionesPagadas={};
					});
			}
		}

		// CONSULTA EL CATALOGO DE TIPOS DE BUSQUEDA DE INFRACCIONES
		tiposBusquedaInfraccionesPago = function() {

			catPagoInfraccionService.tipoBusquedaInfracciones()
					.success( function(data) {
								$scope.catTiposBusqueda = data;
								$scope.infraccionDepositoVO.tipoParametro = $scope.catTiposBusqueda[0].codigoString;
								$scope.error = false;
							}).error(function(data) {
						$scope.error = data;
						$scope.catTiposBusqueda = {};
					});
		}

		//REALIZA LA PETICION Y DESCARGA DEL VOUCHER DE LA INFRACCION.
		 $scope.descargaVaucher = function (infracNum ,tipovoucher ) {
//			 var tipovoucher = 'reporteVoucher';
			 consultaInfraccionService.downloadVoucher( infracNum,tipovoucher ).success(
						function(data, status, headers) {
							var filename = headers('filename');
							console.log(filename);

							var contentType = headers('content-type');
							var file = new Blob([ data ], {
								type : 'application/pdf;base64,'
							});
							
							save(file, filename);
							$scope.error = false;
							
						}).error(function(data) {

					$scope.error = data;
 					
				});

			}
			
		 function save(file, fileName) {
			 console.log(fileName);
				var url = window.URL || window.webkitURL;
				var blobUrl = url.createObjectURL(file);
				var a = document.createElement('a');
				a.href = blobUrl;
				a.target = '_blank';
				a.download = fileName;
				document.body.appendChild(a);
				a.click();		
			}
		 
		 
		 $scope.actualizarApiCartoDB = function( ) {
 
	 	  consultaInfraccionService.actualizarApiCartoDB().success( function(data) {
				 console.log(data)
			 }).error(function(data,status,headers ,config ,statusText ) { 
				 console.log(data)
				 console.log(status)
				 console.log(headers)
				 console.log(config)
				 console.log(statusText)

				 console.log("error")

			 });
				 
		}
		 
		 // MODAL DE DETALLES DE INFRACCIÓN
		  
		    $scope.modalDetalleInfraccionPagada = function(index) {
		    	
				ModalService.showModal({
					templateUrl: 'views/templatemodal/templateModalConsultaPago.html',
		        	controller: 'modalControllerConsultaPagoDetalles',
		        	inputs:{ value: $scope.listaInfraccionesPagadas, value2: index },
		        	scope: $scope
				}).then(function(modal) {
			        modal.element.modal();
				});
	      }
		
		// METODO EJECUTADO AUTOMATICAMENTE PARA CARGAR TIPOS DE BUSQUEDA
		 
		 
		tiposBusquedaInfraccionesPago();

	});