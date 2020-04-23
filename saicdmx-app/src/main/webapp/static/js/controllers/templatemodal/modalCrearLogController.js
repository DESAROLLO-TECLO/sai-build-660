angular.module('siidfApp').controller('modalCrearLogController',
		function($scope, logsService, close, growl, showAlert) {
			$scope.altaLog = function(log) {

				if ($scope.form.$invalid) {
					showAlert.requiredFields($scope.form);
					growl.error('Formulario incompleto');
				}else{

					var logVO = ({
						rutaArchivo : log.rutaArchivo,
						logTipoArchivos : log.tipoExtensiones,
						logNombre : log.logNombre,
						logDescripcion : log.logDescripcion,
					});

					logsService.crear(logVO).success(function(data) {
						cerrar();
						if (data.resultadoPrincipal == "-1") {
							showError(data.mensaje);
						} else {
							showAvisoWAction(data.mensaje);
						}
					}).error(function(data) {

					});
				}
			}

			cerrar = function(){
				$('.modal').hide();
				$('.modal-backdrop').remove();
			};
		});