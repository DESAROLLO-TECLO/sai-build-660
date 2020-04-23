angular.module('siidfApp').controller('garantiaReporteController',function($scope, $filter, garantiaSinProcesarService, ModalService, showAlert,utileriaService) {	
						$scope.order='infrac_fechaOrder';
						$scope.fileNameImg = "././static/dist/img/excel.png"; 
						$scope.descShow = false;
						$scope.fechas = {};
												
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
								angular.forEach($scope.reporteGarantia.$error, function (field) {
						            	angular.forEach(field, function(errorField){
						            	errorField.$setDirty();
						            })
						            $scope.showAviso("Formulario Incompleto");
								});
							};
							
						$scope.buscarReporteGral = function() {
							if($scope.reporteGarantia.$invalid){
								requiredFields();
							}else{
								
							garantiaSinProcesarService.buscarReporteGral($scope.fechas.fechaInicio, $scope.fechas.fechaFin).success(function(data) {
										var listDates= ['infrac_fecha'];
										data = utileriaService.orderData(listDates,[],[],data);				
										$scope.garantiaConsultaResult = data;
										$scope.descShow = true;
										if(data.length>=20000){
											$scope.showAviso("Su consulta superó los 20,000 registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');	
										}
									}).error(function(data) {
										$scope.garantiaConsultaResult = -1;
										showAlert.aviso(data.message);
										$scope.descShow = false;
								
							});
							}
						}
						
						$scope.descargaDeExcel = function() {
								garantiaSinProcesarService.descargaDeExcel($scope.fechas.fechaInicio, $scope.fechas.fechaFin).success(function(data,status, headers) {
								var filename = headers('filename');
								var contentType = headers('content-type');
								var file = new Blob([ data ], {
									type : 'application/vnd.ms-excel;base64'
								});
								save(file, filename);
								$scope.error = false;
							}).error(function(data) {
								$scope.error = data;
								$scope.garantiaConsultaResult = {};
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
								for ( var key in obj) {	if (obj.hasOwnProperty(key))return false;}
								return true;
						}
					 
					
					
				});