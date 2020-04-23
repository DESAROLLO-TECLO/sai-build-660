angular.module('siidfApp').controller('garantiaEntregaController',
				function($scope, $filter, garantiaSinProcesarService, ModalService, infraccion, utileriaService) {

					$scope.numinfrac = infraccion;
					$scope.dato = "";
					$scope.banderaView = false;
					$scope.mostrarDescarga = false;
					$scope.tablaShow = false;
					$scope.fileNameImg = "././static/dist/img/pdf_logo.png";
					$scope.data = {};
					$scope.forms = {};
					$scope.viewHelpers = {viewForm : false};
					$scope.parametroBusqueda = {opcionBusqueda: "1"};
					$scope.parametroModal = {};


					  
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
							angular.forEach($scope.forms.garantiaEntrega.$error, function (field) {
					            	angular.forEach(field, function(errorField){
					            	errorField.$setDirty();
					            	//alert(errorField.$name);
					            })
							});
						};
					
					$scope.buscarPorOpcionEntrega = function() {
						$scope.ocultar= false;
						
						if($scope.forms.garantiaEntrega.$invalid){
							requiredFields();
						}else{
							$scope.opcion = $scope.parametroBusqueda.opcionBusqueda;
							$scope.dato = $scope.parametroBusqueda.dato;
							garantiaSinProcesarService.buscarPorOpcionEntrega($scope.dato, $scope.opcion)
									.success(
											function(data) {
												var isLleno = isEmpty(data);
												if (isLleno == false) {
													$scope.tablaShow = true;
													var listDates= ['fechaPago'];
													data = utileriaService.orderData(listDates,[],[],data);
													$scope.garantiaEntragaResult = data;
													angular.forEach(data,function(
																			value,
																			key) {
																		$scope.garantiaEntragaResul = value;
																	});

												} else {	
													$scope.showAviso("No se encontraron registros");
													$scope.tablaShow = false;
													$scope.ocultar = false;

												}

											}).error(function(data) {
										$scope.error = data;
										$scope.garantiaEntragaResult = {};
										$scope.tablaShow = false;
										$scope.ocultar = false;
									});
						}
					}
					
					$scope.buscarGarantiaEntregaId = function(garantiaId) {
							var opcion = 4;
							garantiaSinProcesarService.buscarPorOpcionEntrega(garantiaId,opcion)
									.success(
											function(data) {
												var isLleno = isEmpty(data);
												if (isLleno == false) {
													$scope.garantiaEntragaResult = data;
													$scope.ocultar= true;
													$scope.banderaView = false;
													$scope.mostrarDescarga = false;
													$scope.parametroBusqueda.observacion = "";
													$scope.forms.garantiaEntrega.$setPristine();
													angular.forEach(data,function(
																			value,
																			key) {
																		$scope.garantiaEntragaResul = value;
																	});

												} else {	
													$scope.showAviso("No se encontraron registros");
													$scope.tablaShow = false;
													$scope.ocultar = false;

												}

											}).error(function(data) {
										$scope.error = data;
										$scope.garantiaEntragaResult = {};
									
									});
						}
					

					$scope.guardarGarantiaEntregaId = function() {
						
						if($scope.forms.garantiaEntrega.$invalid){
							requiredFields();
						}else{
							var observacion = $scope.parametroBusqueda.observacion;
							garantiaSinProcesarService.guardarGarantiaEntregaId($scope.garantiaEntragaResul.garantiaId,observacion)
									.success(
											function(result) {
												if (result == 1) {

													$("#modalInfoEntrega")
															.modal("show");
													$scope.resultado = result;
													$scope.banderaView = true;
													$scope.mostrarDescarga = true;
													$scope.error = false;
												} else {
													$scope.banderaView = false;
													$scope.mostrarDescarga = false;
													$scope.resultado = result;
													$scope.error = false;
												}
											}).error(function(result) {
										$scope.error = result;
										$scope.resultado = {};
									});	
						}
					}

					$scope.generarExcelEntrega = function(){
						garantiaSinProcesarService.generarExcelEntrega($scope.dato, $scope.opcion).success(function(data, status, headers) {			
							var  filename  = headers('filename');
					        var contentType = headers('content-type');
					     	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
					     	save (file , filename);
					    	$scope.error = false;
						}).error(function(data) {			
							$scope.error = data;
							$scope.reporte = {};
						});
					};
					
					$scope.buscarPDF = function(garantiaId) {
						$scope.tablaShow = false;
						$scope.forms.garantiaEntrega.dato.$pristine=true;
						$scope.parametroBusqueda.dato="";
						$scope.forms.garantiaEntrega.dato.$dirty= false;
						garantiaSinProcesarService.buscarPDFEntrega(garantiaId)
								.success(function(data, status, headers) {
									var filename = headers('filename');
									var contentType = headers('content-type');
									var file = new Blob([ data ], {
										type : 'application/pdf;base64,'
									});

									save(file, filename);
									$('body').removeAttr('padding-right');
								/*	*/
									$scope.error = false;
								}).error(function(result) {
									$scope.error = result;
									$scope.resultado = {};
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
					
					consultaAutomatica = function(){
						$scope.parametroBusqueda.dato = $scope.numinfrac;
//						var opcion = 3;
						$scope.opcion = 3;
						if($scope.numinfrac!=null){
							garantiaSinProcesarService.buscarPorOpcionEntrega($scope.numinfrac, $scope.opcion).success(
								function(data) {
									var isLleno = isEmpty(data);
									if (isLleno == false) {
										$scope.tablaShow = true;
										$scope.garantiaEntragaResult = data;
										$scope.ocultar= true;
										$scope.banderaView = false;
										$scope.mostrarDescarga = false;
										$scope.parametroBusqueda.observacion = "";
										$("#modalInfoEntrega").modal();
										angular.forEach(data,function(
											value,
											key) {
												$scope.garantiaEntragaResul = value;
											});
										} else {
											$scope.showAviso("No se encontraron registros");
											$scope.tablaShow = false;
											$scope.ocultar = false;
										}
									}).error(function(data) {
										$scope.error = data;
										$scope.garantiaEntragaResult = {};
										$scope.tablaShow = false;
										$scope.ocultar = false;
							});
						}
					};
					
					$scope.limpiaValor = function(){
						$scope.parametroBusqueda.dato = "";
						$scope.forms.garantiaEntrega.dato.$invalid = true;
						$scope.forms.garantiaEntrega.dato.$dirty = false;
					};
					
					/*SE VALIDA EL TIPO DE PERFIL*/
					validacionUsuario();
					/*SI LA PETICIÓN VIENE DESDE PAGO, SE REALIZA AUTOMATICAMENTE LA CONSULTA*/
					consultaAutomatica();

				});