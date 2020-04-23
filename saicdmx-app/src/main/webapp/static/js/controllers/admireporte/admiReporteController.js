angular
		.module('siidfApp')
		.controller(
				'admiReporteController',
				function($scope, ModalService, admiReporteService, $filter, $parse) {
					$scope.arrayParametros = [];
					$scope.paramConfig = false;
					$scope.configuracionParam = [];
					$scope.paramValue = [];
					$scope.listAuxiliar = [];
					$scope.selectCatPer = [];
					$scope.admiPropiedadVO = {};
					$scope.parametroVO = {};
//					$scope.parametro = {};
					$scope.arrayParam22 = {};
					$scope.paramVO = {};
					$scope.reporteVO={};


					var cadena;
					$scope.validarScript = function(reporteVO, userForm) {
						cadena = "SELECT  " + $scope.reporteVO.scriptSelect
								+ "  FROM   " + $scope.reporteVO.scriptFrom
								+ "  WHERE  " + $scope.reporteVO.scriptWhere;

						admiReporteService
								.guardarReporte(cadena)
								.success(
										function(data) {

											/*Arreglo de parametros desde API*/
											$scope.arrayParametros = data;
											
											/**/
											$scope.params = angular.copy($scope.arrayParametros);
											
											angular.forEach($scope.params, function(param, key){
												param.catalogoPropiedades = angular.copy($scope.propiedades);
											})
											

											angular
													.forEach(
															$scope.arrayParametros,
															function(param, key) {

																$scope.configuracionParam
																		.push({
																			idConfig : key + 1,
																			nbParam : param,
																			tipoParametro : $scope.parametros,
																			tipoPropiedades : $scope.propiedades,
																			tipoComponente : $scope.componentes
																		});
															});

											console
													.log("tamaño del arreglo de las propiedades "
															+ $scope.tamanno);

										}).error(function(data) {
									$scope.arrayParametros = [];

								});

					}
					
					$scope.saveConfParam = function(reporteVO, userForm){
						
						admiReporteService.guardarReporteBd(reporteVO).success(
								function(data) {
								showAviso("Registro Guardado Correctamente");
								}).error(function(data) {
									console.log('Error');
								});
						
						
//						admiReporteService.guardarConfigParam($scope.params).success(function (data){
//							ShowAviso("Registro de parametros Guardado Correctamente");
//						}).error(function(data){
//							console.log('Error');
//						});
						
					}

					$scope.guardarReportConfParam = function(parametroVO, userFormP){
						
						for(var t in parametroVO.propiedad){
							for(var l in parametroVO.propiedad[t]){
								parametroVO.propiedad[t][l] = parametroVO.propiedad[t][l].idPropiedad;
							}
							
						}
						
						for(var k in parametroVO.tipoComponente){
							parametroVO.tipoComponente[k]=parametroVO.tipoComponente[k].idComponente;
							
						}
						for(var n in parametroVO.tipoParametro){
							parametroVO.tipoParametro[n] = parametroVO.tipoParametro[n].idTipoParametro;
							
						}


						console.log($scope.parametroVO);

						
					}

					$scope.guardarParam = function(arrayParam22, paramValue22) {
						var arrayParamValue = [];

						for (var a = 0; a < arrayParam22.length; a++) {
							arrayParamValue.push({
								"param" : arrayParam22[a].nbParametro,
								"value" : paramValue22[a]
							});
						}
						console.log($scope.admiPropiedadVO);
						console.log(arrayParamValue);
						admiReporteService.crearQuery(arrayParamValue, cadena)
								.success(function(data) {
									$scope.respuesta = data;
									$scope.paramConfig = true;
									ModalService.closeModal();
								}).error(function(data) {
									$scope.respuesta = data;
								});

					}
					
					
					

					// CATALOGOS

					obtenerTipoReportes = function() {
						admiReporteService.listTiposReportes().success(

						function(data) {
							$scope.tiposReportes = data;

						}).error(function(data) {
							$scope.tiposReportes = {};
						});

					}

					obtenerFormatosDescargas = function() {
						admiReporteService.listFormatoDescarga().success(

						function(data) {
							$scope.formatos = data;

						}).error(function(data) {
							$scope.formatos = {};
						});
					}

					obtenerComponentes = function() {
						admiReporteService.listComponentes().success(

						function(data) {
							$scope.componentes = data;
						}).error(function(data) {
							$scope.componentes = {};
						})
					}

					obtenerParametros = function() {
						admiReporteService.listParametros().success(
								function(data) {
									$scope.parametros = data;
								}).error(function(data) {
							$scope.parametros = {};
						})
					}

					obtenerPropiedades = function() {
						admiReporteService.listPropiedades().success(
								function(data) {
									$scope.propiedades = data;
								}).error(function(data) {
							$scope.propiedades = {};
						})
					}
					console.log($scope.propiedades);
					obtenerTipotitulo = function() {
						admiReporteService.listTipoTitulo().success(
								function(data) {
									$scope.tipoTitulo = data;
								}).error(function(data) {
							$scope.tipoTitulo = {};
						})
					}

					obtenerTipoReportes();
					obtenerFormatosDescargas();
					obtenerComponentes();
					obtenerParametros();
					obtenerPropiedades();
					obtenerTipotitulo();

				});