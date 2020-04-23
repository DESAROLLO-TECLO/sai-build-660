angular.module('siidfApp').controller(
		'garantiaRecepcionController',
		function($scope, $filter, garantiaSinProcesarService,ModalService, utileriaService) {
			$scope.data = {};
			$scope.forms = {};
			$scope.viewHelpers = {viewForm : false};
			$scope.modalForm = {
					busquedaParametro : 'infracPlaca'
			};
			$scope.opcionRecp = true;
			$scope.fileNameImg = "././static/dist/img/pdf_logo.png";
			$scope.valor="";
			$scope.checkFiltroGar = false;
		
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
					angular.forEach($scope.forms.garantiaRecepcion.$error, function (field) {
			            	angular.forEach(field, function(errorField){
			            	errorField.$setDirty();
			            })
					});
				};
				
				
			
			$scope.buscarGarantiasRecepcion = function (valor, op) {
				$scope.ocultar = false;
				$scope.valor = valor;
				if($scope.forms.garantiaRecepcion.$invalid){
					requiredFields();
				}else{
					$scope.checkFiltroGar = op; 
					garantiaSinProcesarService.buscarGarantiasRecepcion(valor, op).success(
						function(data) {
							var isLleno = isEmpty(data);
							if(isLleno == false){
							
							if(data.length >= 20000){
								$scope.showAviso("Su consulta supera los 20,000 registros. En caso de requerir el reporte completo, solicitelo a soporte.", 'templateModalAviso');
							}
							var listDates= ['fechaInfraccion'];
							data = utileriaService.orderData(listDates,[],[],data);
							$scope.garantias = data;
							$scope.error = false;
							}else{
								$scope.garantias = -1;
								$scope.garantias = data;
								if(data.length < 1){
								$scope.showAviso("No se encontraron registros");
								}
							}
						}).error(function(data) {
							$scope.error = data;
					$scope.showAviso(data.message);
					$scope.garantias = -1;
					$scope.garantias = {};
				});
					}
			}

			$scope.buscarGarantiasRecepcionId = function(garantiaId) {
				$scope.banderaView = false;
				$scope.mostrarDescarga = false;
				garantiaSinProcesarService.buscarGarantiasRecepcionId(
						garantiaId).success(function(data) {								
					$scope.garantiasFolio = data;
					$scope.error = false;
					$scope.ocultar = true;
					$scope.modalForm.observacion = "";
					$scope.forms.garantiaRecepcion.$setPristine();
				}).error(function(data) {
					$scope.error = data;
					$scope.garantiasFolio = {};
					$scope.ocultar = false;
				});

			}

			$scope.GuardarGarantiasRecepcionId = function(garantiaId, recibir) {
				
			  if($scope.forms.garantiaRecepcion.$invalid){
				  		requiredFields();
				} else {
					garantiaSinProcesarService.GuardarGarantiasRecepcionId(
							garantiaId, recibir,  $scope.modalForm.observacion).success(
							function(data) {
								$("#modalRecepcionDatos").modal("show");
								$scope.banderaView = true;
								$scope.mostrarDescarga = true;
								if (data != false) {
									$scope.garantiasDetalleOb = data;
									$scope.error = false;
								} else {
									$scope.garantiasDetalleOb = data;
									$scope.error = false;
								}

							}).error(function(data) {

						$scope.error = data;
						$scope.garantiasDetalleOb = {};
						
					});
				}
			}

			$scope.buscarPDF = function buscarPDFs(garantiaID,valor) {			
				this.buscarGarantiasRecepcion (valor, false);
				$scope.opcionRecp = true;
				$scope.banderaView = false;
				$scope.mostrarDescarga = false;
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
			
			function isEmpty(obj) {
				
				for ( var key in obj) {
					if (obj.hasOwnProperty(key))
						
					return false;
				}
				return true;
			}
			
			validacionUsuario = function(){
				garantiaSinProcesarService.validarUsuario().success(function(data){
				
					$scope.viewHelpers.viewForm = data;
					$scope.data.message = !data;
					$scope.data.msg = "¡Cuidado! No Puedes realizar esta operación, verifica tu perfil";
				
				}).error(function(data){
					
				});
			}
			
			$scope.close = function(valor){
			this.buscarGarantiasRecepcion (valor, false);
			};
			
			$scope.generarExcel = function(op) {
				garantiaSinProcesarService.generarExcel($scope.valor, op).success(function(data, status, headers) {			
					var  filename  = headers('filename');
			        var contentType = headers('content-type');
			     	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
			     	save (file , filename);
			    	$scope.error = false;
				}).error(function(data) {			
					$scope.error = data;
					$scope.reporte = {};
				});
			}
			
			validacionUsuario();
			
			$scope.cambiarFiltro = function(op){
				$scope.buscarGarantiasRecepcion($scope.valor, op);
			};

		});