angular.module('siidfApp').controller('consultaRemisionController', function($scope, $filter,$q,remisionaDepositoService,catalogoService,ModalService) {

	$scope.consultaRemiVO   = {};

    
	ListaOpciones = function() {	
		catalogoService.OpcionesConsultaRemision().success(function(data) {
			$scope.opcionInfraccion = data;
			$scope.consultaRemiVO.opcion = data[0].valor; 
		}).error(function(data) {
			$scope.opcionInfraccion  = {};
		});
	};
	
	$scope.consultarOpcion = function() {
		if($scope.formConsulta.$invalid){
			requiredFields();
		}else{
			remisionaDepositoService.buscarRemisiones($scope.consultaRemiVO.opcion, $scope.consultaRemiVO.valor).success(function(data) {
				$scope.datosInfra = data;
		    }).error(function(data) {
				$scope.datosInfra =[];
				showAviso(data.message, 'templateModalAviso');
			});
		}
	};
	
	$scope.changeFilters = function(){
		$scope.datosInfra = [];
	}
	
	requiredFields = function(){
		angular.forEach($scope.formConsulta.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	showAviso = function(messageTo, template) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/' + template + '.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
	
	$scope.imprimir = function(infrac_num, imp) {
		if(angular.isDefined(imp)){
			if (imp == 'R'){
				 imprimeRecibo_res(infrac_num);
			}else{
				 imprimeRecibo_arra(infrac_num);
			}
		}
	}; 
	
	imprimeRecibo_res = function(infrac_num) {
		remisionaDepositoService.ImprimirResguardoPDF(infrac_num)
		.success(function(data, status, headers) {
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});

			save(file, filename);
			$scope.error = false;
		}).error(function(result) {
			showAviso(data.message, 'templateModalError');
		});
	
	};
	
	// ---------------------Imprimir recibo Arrastre-----------------------
	imprimeRecibo_arra = function(infrac_num){
		remisionaDepositoService.ImprimirArrastrePDF(infrac_num)
		.success(function(data, status, headers) {
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});

			save(file, filename);
		}).error(function(result) {
			showAviso(data.message, 'templateModalError');
		});
	};
	
	
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
	
	ListaOpciones();
	
	
	/*remisionaDepositoService.mostarDatosPorNCI(infrac_num_ctrl).success(function(data) {
		$scope.datosInfraccion = data;
		$scope.error = false;
		
		if($scope.datosInfraccion != undefined){
		
			remisionaDepositoService.valorArticuloSancion($scope.datosInfraccion.infrac_num).success(function(dataArt) {
				
					$scope.errorArt = false;
					
					remisionaDepositoService.valorVehiculo($scope.datosInfraccion.infrac_num).success(function(dataV) {
						//	$scope.datosVehiculo = dataV;
							$scope.errorV = false;
							
							remisionaDepositoService.valorSecAgrupArrasEmp($scope.datosInfraccion.infrac_num).success(function(dataSec) {
								//	$scope.datosSecAgrupArrasEmp = dataSec;
									$scope.errorSec = false;
									
									$scope.datosArticuloSancion = dataArt;
									$scope.datosVehiculo = dataV;
									$scope.datosSecAgrupArrasEmp = dataSec;
									
									var infraccionCompletaReporteVO = {
											 "infrac_num" : $scope.datosInfraccion.infrac_num,
											 "infrac_placa" : $scope.datosInfraccion.infrac_placa,
											 "infrac_num_ctrl" : $scope.datosInfraccion.infrac_num_ctrl,
											 "infrac_impresa" : $scope.datosInfraccion.infrac_impresa,
											 "infrac_num_arrastre" : $scope.datosInfraccion.infrac_num_arrastre,
											 "vorigen" : $scope.datosInfraccion.vorigen,
											 "infrac_ley_transporte" : $scope.datosInfraccion.infrac_ley_transporte,
											 "infrac_con_placa" : $scope.datosInfraccion.infrac_con_placa,
											 "emp_id" : $scope.datosInfraccion.emp_id,
											 "emp_placa" : $scope.datosInfraccion.emp_placa,
											 "grua_cod" : $scope.datosInfraccion.grua_cod,
											 "dep_id" : $scope.datosInfraccion.dep_id,
											 "conse_prefijo" : $scope.datosInfraccion.conse_prefijo,
											 "infrac_m_en_la_calle" : $scope.datosInfraccion.infrac_m_en_la_calle,
											 "infrac_m_entre_calle" : $scope.datosInfraccion.infrac_m_entre_calle,
											 "infrac_m_y_la_calle" : $scope.datosInfraccion.infrac_m_y_la_calle,
											 "infrac_m_colonia" : $scope.datosInfraccion.infrac_m_colonia,
											 "infrac_m_fecha_hora" : $scope.datosInfraccion.infrac_m_fecha_hora,
											 "tipo_l_cod" : $scope.datosInfraccion.tipo_l_cod,
											 "tipo_l_id" : $scope.datosInfraccion.tipo_l_id,
											 "del_nombre" : $scope.datosInfraccion.del_nombre,		
											 
											 "infrac_observacion" : $scope.datosInfraccion.infrac_observacion,
											 "causa_id" : $scope.datosInfraccion.causa_id,
											 "vtipo_cod" : $scope.datosInfraccion.vtipo_cod,
											// "infrac_tipo_ingre_nom" : $scope.nombreTipoIngreso,
											 "asn" : $scope.datosInfraccion.asn,
											 "v_sellado" : $scope.datosInfraccion.v_sellado,
											 "v_cajuela" : $scope.datosInfraccion.v_cajuela,
											 
											 "art_id" : $scope.datosArticuloSancion.art_id,
											 "sancion_art_id" : $scope.datosArticuloSancion.sancion_art_id,
											 "art_numero" : $scope.datosArticuloSancion.art_numero,
											 "art_fraccion" : $scope.datosArticuloSancion.art_fraccion,
											 "art_parrafo" : $scope.datosArticuloSancion.art_parrafo,
											 "art_inciso" : $scope.datosArticuloSancion.art_inciso,
											 "art_motivacion" : $scope.datosArticuloSancion.art_motivacion,
											 
											 "vtipo_cod" : $scope.datosVehiculo.vtipo_cod,
											 "vtipo_nombre" : $scope.datosVehiculo.vtipo_nombre,
											 "vcolor_cod" : $scope.datosVehiculo.vcolor_cod,
											 "vcolor_nombre" : $scope.datosVehiculo.vcolor_nombre,
											 "vmar_cod" : $scope.datosVehiculo.vmar_cod,
											 "vmar_nombre" : $scope.datosVehiculo.vmar_nombre,
											 "vmod_cod" : $scope.datosVehiculo.vmod_cod,
											 "vmod_nombre" : $scope.datosVehiculo.vmod_nombre,
											
											 "sec_cod" : $scope.datosSecAgrupArrasEmp.sec_cod,
											 "sec_nombre" : $scope.datosSecAgrupArrasEmp.sec_nombre,
											 "agrp_id" : $scope.datosSecAgrupArrasEmp.agrp_id,
											 "agrp_cod" : $scope.datosSecAgrupArrasEmp.agrp_cod,
											 "agrp_nombre" : $scope.datosSecAgrupArrasEmp.agrp_nombre, 
											 "emp_cod" : $scope.datosSecAgrupArrasEmp.emp_cod,
											 "grua_id" : $scope.datosSecAgrupArrasEmp.grua_id,
											 "dep_usuario" : $scope.datosSecAgrupArrasEmp.dep_usuario
													
									}
									if (imp == 'R'){
										 $scope.imprimeRecibo_res(infraccionCompletaReporteVO);
									}else{
										 $scope.imprimeRecibo_arra(infraccionCompletaReporteVO);
									}
									
							
									
								}).error(function(dataSec) {
									$scope.errorSec = dataSec;
								//	$scope.datosSecAgrupArrasEmp  = {};
								});
							
						}).error(function(dataV) {
							$scope.errorV = dataV;
						//	$scope.datosVehiculo  = {};
						});
					
				}).error(function(dataArt) {
					$scope.errorArt = dataArt;

				//	$scope.datosArticuloSancion  = {};
				});
			
//			datosArticuloSancion($scope.datosInfraccion.infrac_num).then(function(dataArt) {
//	            $scope.datosArticuloSancion = dataArt;
//	        });	
			
		} // termina si es diferente de null
		
	}).error(function(data) {
		$scope.error = data;
		$scope.datosInfraccion  = {};
	});*/
});