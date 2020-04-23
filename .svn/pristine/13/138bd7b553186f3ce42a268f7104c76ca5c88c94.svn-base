angular.module('siidfApp').controller('detalleVehiculoRobadoController', function($scope,$route, $timeout, idRobo, opcion, $location, catalogoService, autosRobadosService, showAlert,growl) {
	
	
	$scope.getValue = idRobo;
	$scope.getExp = opcion;	
	$scope.parameters = {};
	$scope.dataCopy = {};
	
	
	
	$scope.initA = function(){
		 
	if($scope.getExp != "false"){
		$scope.optionsview = "Modificar Reporte de Robo";
		autosRobadosService.consultarVehDetalle($scope.getValue).success(
				function(data) {
				$scope.parameters = {};
				if(data != '' || data != null){
					$scope.datas = data;
					 $scope.dataCopy = angular.copy($scope.datas);
					$scope.parameters.turnoDgant = data.turnoDgant;
					$scope.parameters.expediente = data.expediente;
					$scope.parameters.fecha = data.fechaRobo;
					$scope.parameters.placaVehiculo = data.placaVehiculo ;
					$scope.parameters.numSerie = data.numSerie;
					$scope.parameters.numMotor = data.numMotor;
					$scope.comboEstatus();
				}else{
					showAlert.aviso("No se encontro registros");
				}
			}).error(function(data) {						
			$scope.listHisto = {};
			showAlert.aviso(data.message);
			
		});
	}else{
		$scope.parameters = {};

			autosRobadosService.consultarExpediente($scope.getValue).success(
					function(data9) {
						$scope.parameters.expediente = data9[0].expediente;
						$scope.getValue = data9[0].expediente;
						$scope.optionsview =  "Alta de Reporte de Robo con Expediente : "+ $scope.getValue;
						$scope.comboEstatus();
				}).error(function(data9) {						
				showAlert.aviso();
				
			});
			


	}	
	};
	

	
	$scope.comboEstatus = function(){
		autosRobadosService.buscarVehiculosEstatus().success(
				function(data7) {
					if($scope.getExp != "false"){
						$scope.comboEstatus = data7;					
						$scope.parameters.estatus = $scope.dataCopy.idEstatus;
					}else{
						$scope.comboEstatus = data7;					
						$scope.parameters.estatus = data7[0].codigo;
					}	
					$scope.comboMarca();
				}).error(function(data7) {					
			$scope.error = data7;
			showAlert.aviso(data7.message);			
		});
	};
	
	$scope.comboMarca = function(){
		catalogoService.buscarVehiculosMarcas().success(
				function(data3) {
					if($scope.getExp != "false"){
						$scope.comboMarca = data3;		
						$scope.parameters.marca = $scope.dataCopy.idMar;
						$scope.comboTipo($scope.parameters.marca);
						
					}else{
						$scope.comboMarca = data3;		
						$scope.parameters.marca = data3[0].vMarId;
						$scope.comboTipo($scope.parameters.marca);
					}
					
					}).error(function(data3) {					
			$scope.error = data3;
			showAlert.aviso(data3.message);
			
		});
	};
	
	
	$scope.comboTipo = function(id){	
		autosRobadosService.buscarTipo(id).success(
				function(data5) {
					if($scope.getExp != "false"){
						$scope.comboTipos = data5;				
						$scope.parameters.tipo = $scope.dataCopy.idMod;
					}else{
						$scope.comboTipos = data5;				
						$scope.parameters.tipo = data5[0].codigo;
					}	
					$scope.comboModelo();
				}).error(function(data5) {					
			$scope.error = data5;
			showAlert.aviso(data5.message);
			
		});
	};
	
	$scope.comboModelo = function(){	
		autosRobadosService.buscarModelo().success(
				function(data4) {	
					if($scope.getExp != "false"){
						$scope.comboModelo = data4;
						$scope.parameters.modelo = $scope.dataCopy.idAnio;
					}else{
						$scope.comboModelo = data4;
						$scope.parameters.modelo = data4[0].codigo;
					}	
					$scope.comboColor();
				}).error(function(data4) {					
			$scope.error = data4;
			showAlert.aviso(data4.message);			
		});
	};
	

	
	$scope.comboColor = function(){
		catalogoService.buscarVehiculosColor().success(
				function(data6) {
					if($scope.getExp != "false"){
						$scope.comboColor = data6;					
						$scope.parameters.color = $scope.dataCopy.idColor;
					}else{
						$scope.comboColor = data6;					
						$scope.parameters.color = data6[0].vColorId;
					}
					
				}).error(function(data6) {					
			$scope.error = data6;
			showAlert.aviso(data6.message);
			
		});
	};
	

	
	
	requiredFields = function(){
		angular.forEach($scope.formDetalleRobo.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	
	
	$scope.closeModal = function(){	
		 $timeout(function() {
			 $location.path('/vehRobadosAlta');
			  },500);
		
		};
		
		$scope.refreshh = function(){	
			 $timeout(function() {
				 $location.path('/detalleRoboVehiculo/'+$scope.getValue+'/true');
				  },500);
			
			};
	
	$scope.validarRegRobo = function(){
		if($scope.formDetalleRobo.$invalid){
			requiredFields();
//			if(($scope.parameters.placaVehiculo == '' || $scope.parameters.placaVehiculo == undefined) && ( $scope.parameters.numSerie == '' || $scope.parameters.numSerie == undefined )
//					 && ($scope.parameters.numMotor == '' || $scope.parameters.numMotor == undefined )){
//				growl.info("Al menos debe capturar la Placa Vehicular o Número de Serie o Número de Motor");
//			}
//			growl.info("Formulario Incompleto");
			if(($scope.parameters.placaVehiculo == '' || $scope.parameters.placaVehiculo == undefined) && ( $scope.parameters.numSerie == '' || $scope.parameters.numSerie == undefined )
					 && ($scope.parameters.numMotor == '' || $scope.parameters.numMotor == undefined )){
				showAlert.aviso("Al menos debe capturar la Placa Vehicular o Número de Serie o Número de Motor");
			}else{
				showAlert.aviso("Formulario Incompleto");	
			}
			
			
		}else{
			var expVerif = $scope.parameters.expediente;
			var turnoGgantVerif = $scope.parameters.turnoDgant;
			autosRobadosService.verificarExiste(expVerif,turnoGgantVerif).success(
					function(data8) {
						$scope.getValue = data8;
						showAlert.confirmacion("El reporte ya existe, ¿ Desea actualizarlo ? ",function(){ $scope.refreshh() });		
					}).error(function(data8) {						
						$scope.guardarVO();	
			});	
			
		}
		
	};
	
	$scope.guardarVO = function(){	
			var vehiculoRobadoVO = {
					"turnoDgant" : $scope.parameters.turnoDgant ,
					"expediente" : $scope.parameters.expediente ,
					"fechaRobo" : $scope.parameters.fecha,
					"placaVehiculo" : $scope.parameters.placaVehiculo,
					"numSerie" : $scope.parameters.numSerie,
					"numMotor" : $scope.parameters.numMotor,
					"idMar" : $scope.parameters.marca,
					"idMod" : $scope.parameters.tipo,
					"idColor" : $scope.parameters.color,
					"idAnio" : $scope.parameters.modelo,
					"idEstatus" : $scope.parameters.estatus
				 };
			autosRobadosService.altaVehiculoRobado(vehiculoRobadoVO).success(
					function(data1) {
						$scope.result = data1;
						if($scope.result == true){
							showAlert.aviso("El reporte de robo se registró correctamente",function(){ $scope.closeModal() });
						}else{							
							showAlert.aviso("El reporte de robo no se registró correctamente");
						}						
					}).error(function(data1) {						
				$scope.error = data1;
				showAlert.aviso(data1.message);				
			});					
	};
	
	$scope.updateVO = function(){
		
		if($scope.formDetalleRobo.$invalid){
			requiredFields();
//			if(($scope.parameters.placaVehiculo == '' || $scope.parameters.placaVehiculo == undefined) && ( $scope.parameters.numSerie == '' || $scope.parameters.numSerie == undefined )
//					 && ($scope.parameters.numMotor == '' || $scope.parameters.numMotor == undefined )){
//				growl.info("Al menos debe capturar la Placa Vehicular o Número de Serie o Número de Motor");
//			}
//				growl.info("Formulario Incompleto");
				if(($scope.parameters.placaVehiculo == '' || $scope.parameters.placaVehiculo == undefined) && ( $scope.parameters.numSerie == '' || $scope.parameters.numSerie == undefined )
						 && ($scope.parameters.numMotor == '' || $scope.parameters.numMotor == undefined )){
					showAlert.aviso("Al menos debe capturar la Placa Vehicular o Número de Serie o Número de Motor");
				}else{
					showAlert.aviso("Formulario Incompleto");	
				}
			
		}else{
			var vehiculoRobadoVO = {
					"idRobo" : $scope.getValue,
					"turnoDgant" : $scope.parameters.turnoDgant ,
					"expediente" : $scope.parameters.expediente ,
					"fechaRobo" : $scope.parameters.fecha,
					"placaVehiculo" : $scope.parameters.placaVehiculo,
					"numSerie" : $scope.parameters.numSerie,
					"numMotor" : $scope.parameters.numMotor,
					"idMar" : $scope.parameters.marca,
					"idMod" : $scope.parameters.tipo,
					"idColor" : $scope.parameters.color,
					"idAnio" : $scope.parameters.modelo,
					"idEstatus" : $scope.parameters.estatus
				 };
			autosRobadosService.updateVehiculoRobado(vehiculoRobadoVO).success(
					function(data2) {
						$scope.result = data2;
						if($scope.result == true){	
							showAlert.aviso("El reporte de robo se actualizó correctamente",  $scope.closeModal );
						}else{
							showAlert.aviso("El reporte de robo no se actualizó correctamente");
						}
						
					}).error(function(data2) {						
				$scope.error = data2;
				showAlert.aviso(data2.message);
				
			});
				
		}
	};

	
	$scope.$on('$locationChangeSuccess', function(event, current, previous) {
		  $scope.proximoController = $route.current.$$route.controller;
		  if($scope.proximoController != 'vehRobadosAltaController'  ){
			  emptyParams = null;
			  autosRobadosService.setParametro(emptyParams);
		  }
	});
	
	
	
//	 $timeout(function() {
		 
		 $scope.initA();
		
			
//		  },1000);
	
	
	

});