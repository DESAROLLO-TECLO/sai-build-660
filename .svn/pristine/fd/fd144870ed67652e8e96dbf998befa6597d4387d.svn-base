angular.module('siidfApp').controller('consultaLogsController', function($scope,logsService,ModalService) {
			$scope.mostrarTabla= false;
			
			consultaPorPerfil = function() {
				logsService.consultaPorPerfil().success(function(data) {
					$scope.data = data;
					/*$scope.data.splice(0, 0, {
						"logId" : 0,
						"logNombre" : "--Opciones--",
						"logDescripcion" : "",
						"logEstatus" : "",
						"rutaArchivos" : ""
					});
					$scope.data.selectedOption = data[0];*/
				}).error(function(data) {
					$scope.data = {};
				});
			}

			$scope.obtenerInfoArchivo = function(id) {
				$scope.mostrarTabla= false;
				if ($scope.form.$invalid){
					requiredFields();
				}else{
					logsService.consultaArchivosPorLog(id).success(function(data) {
						$scope.mostrarTabla= true;
						$scope.archivoLog = data;
				}).error(function(data) {
					showAviso(data.message);
					$scope.archivoLog = {};
				});
			  }
			}

			$scope.descargaArchivo = function(id, nombre) {

				logsService.descargaArchivo(id, nombre).success(
						function(data, status, headers) {

							var filename = headers('filename');
							var contentType = headers('content-type');
							var file = new Blob([ data ], {
								type : ' text/plain'
							});
							save(file, filename);
							$scope.error = false;

						}).error(function(data) {

					$scope.error = data;

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
			
			requiredFields = function(){
				angular.forEach($scope.form.$error, function (field) {
		            	angular.forEach(field, function(errorField){
		            	errorField.$setDirty();
		            })
				});
			};

					showAviso = function(messageTo) {
						ModalService
								.showModal(
										{
											templateUrl : 'views/templatemodal/templateModalAviso.html',
											controller : 'mensajeModalController',
											inputs : {
												message : messageTo
											}
										}).then(function(modal) {
									modal.element.modal();
								});
					};
			consultaPorPerfil();

		});