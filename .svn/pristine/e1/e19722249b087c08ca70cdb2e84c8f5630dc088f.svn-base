angular.module('siidfApp').controller('updIngresoDepositoController', function($scope, $filter,$location, remisionaDepositoService, controlSuministrosService,catalogoService, valoresupd, ModalService, liberacionVehiculoService) {
	
$scope.valorUpdIngreso = valoresupd.data;

$scope.viewOficialData = false;
$scope.viewMessagePlaca = false;
$scope.viewFormulario = true;
$scope.viewResultado = false;

$scope.forms = {};

$scope.ingresoInfracVO = {};

	$scope.consultarPlaca = 	function() {
		if($scope.ingresoInfracVO.infrac_placa_empl != undefined){
		remisionaDepositoService.buscarOficialPorPlaca($scope.ingresoInfracVO.infrac_placa_empl)
		.success(function(data) {
		  $scope.datosAuxiliar = data;
		  $scope.error = false;
		  $scope.viewOficialData = true;
		  
			$scope.validColor = "#444";
			$scope.validMensaje = "La placa es valida";
			
		}).error(function(data) {
		
			$scope.viewOficialData = false;
		    $scope.validColor = "red";
			$scope.validMensaje = "La placa no existe";
		});
		}else{
			$scope.viewOficialData = false;
			$scope.validColor = "red";
			$scope.validMensaje = "La placa oficial es requerida";
		}
		$scope.viewMessagePlaca = true;
		$scope.viewRegresar = false;
	};
	
	$scope.changePlaca = function(){
		$scope.viewOficialData = false;
		$scope.viewMessagePlaca = true;
		$scope.validColor = "red";
		$scope.validMensaje = "Validar placa.";
	}
	
// Catalogos de la pantalla //
	
	listOperativo = function() {	
		catalogoService.listaOperativo().success(function(data) {
			$scope.listOperativo = data;
			$scope.ingresoInfracVO.operativo = data[0].oper_cod;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listOperativo  = {};
		});
	};
	
	listMedioTrans = function() {	
		catalogoService.listaMediosTrasp().success(function(data) {
			$scope.listMedios = data;
			$scope.ingresoInfracVO.t_ingr_id = '01';
		}).error(function(data) {
			$scope.listMedios  = {};
		});
	};
	
	listCausa = function() {	
//   listaCausaIngresoByTraslado -- con eso se busca la causa de ingres por traslado con id 09
		catalogoService.listaCausaIngreso().success(function(data) {
			$scope.listCausa = data;
			$scope.ingresoInfracVO.causa_id = data[0].causa_cod;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listCausa  = {};
		});
	};
	
	
	listDeposito = function() {	
		catalogoService.listaDeposito($scope.valorUpdIngreso.dep_id).success(function(data) {
			$scope.listDeposito = data;
			$scope.ingresoInfracVO.deposito = data[0].dep_cod;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listDeposito  = {};
		});
	};
	
	listDepositoOrigen = function() {	
		catalogoService.listaDepositoOrigen().success(function(data) {
			$scope.listDepositoOrigen = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listDepositoOrigen  = {};
		});
	};
	
	listInventario = function() {
		catalogoService.listaInventario().success(function(data) {
			$scope.listInventario = data;
			$scope.firstCheck = data[0].comp_cod;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listInventario  = {};
		});
	};

	listTipoV = function() {	
		catalogoService.buscarTiposVehiculo().success(function(data) {
			$scope.listTipoV = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listTipoV  = {};
		});
	};
	
	listMarca = function() {	
		catalogoService.buscarVehiculosMarcas().success(function(data) {
			$scope.listMarca = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listMarca  = {};
		});
	};
	
	listModel = function() {	
		$scope.listModelo = [
				{"vmod_cod" : $scope.datosVehiculo.vmod_cod,
				"vmod_nombre" : $scope.datosVehiculo.vmod_nombre}
					];
	};
	
	listColor = function() {	
		catalogoService.buscarVehiculosColor().success(function(data) {
			$scope.listColor = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listColor  = {};
		});
	};
	
	cambiarOrigenv = function(val){
		$scope.vorigen = val;
	  }
	
// INICIA Complemanto de datos de la ingracion seleccionada //
	
	datosArticuloSancion = function() {	
		remisionaDepositoService.valorArticuloSancion($scope.valorUpdIngreso.infrac_num).success(function(data) {
			$scope.datosArticuloSancion = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.datosArticuloSancion  = {};
		});
	};
	
	datosVehiculoTipoColorMarca = function() {	
		remisionaDepositoService.valorVehiculo($scope.valorUpdIngreso.infrac_num).success(function(data) {
			$scope.datosVehiculo = data;
			$scope.error = false;
			listModel();
		}).error(function(data) {
			$scope.error = data;
			$scope.datosVehiculo  = {};
		});
	};
	
	datosSectorAgrupaArrastreEmpGrua = function() {	
		remisionaDepositoService.valorSecAgrupArrasEmp($scope.valorUpdIngreso.infrac_num).success(function(data) {
			$scope.datosSecAgrupArrasEmp = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.datosSecAgrupArrasEmp  = {};
		});
	};
	
	
	datosGruaConce = function() {	
		remisionaDepositoService.valorGruaConce($scope.valorUpdIngreso.infrac_num).success(function(dataGrua) {
			$scope.datosGrua = dataGrua;
			$scope.error = false;
		}).error(function(dataGrua) {
			$scope.error = dataGrua;
			$scope.datosGrua = {};
		});
	};

// FIN Complemanto de datos de la ingracion seleccionada //

	
  $scope.imChanged = function(){
	  $scope.valoresInventario = $scope.ingresoInfracVO.valorInv.listInventario.join('-');
  }
  
  $scope.cambioCuentaConPlaca = function(){
	  $scope.ingresoInfracVO.infrac_placa = "";
  }
  
  $scope.cambioMedioTransporte = function (){
	  $scope.ingresoInfracVO.grua_cod = $scope.valorUpdIngreso.grua_cod;
	  if($scope.ingresoInfracVO.t_ingr_id != 1){
		  $scope.ingresoInfracVO.grua_cod = "";
	  }
  }
 
  requiredFields = function(){
		angular.forEach($scope.forms.formIngreso.$error, function (field) {
          	angular.forEach(field, function(errorField){
          	errorField.$setDirty();
          })
		});
	}
	  
  $scope.registroIngreso = function() {  
	  if($scope.forms.formIngreso.$invalid){
		  requiredFields();
		  $scope.showAviso("Formulario Incompleto", 'templateModalAviso');
	  }else{
		  if($scope.validColor == "red"){
				$scope.showAviso("Validar placa del oficial", 'templateModalError');
				return;
			}
		  var articulosInfraccion = $scope.datosArticuloSancion.art_numero + ' - ' + 
							$scope.datosArticuloSancion.art_fraccion + ' - ' + 
							$scope.datosArticuloSancion.art_parrafo + ' - ' + 
							$scope.datosArticuloSancion.art_inciso;

			var infraccionCompletaVO = {
					 "infrac_num" : $scope.valorUpdIngreso.infrac_num,
					 "infrac_placa" : $scope.valorUpdIngreso.infrac_placa,
					 "infrac_num_ctrl" : $scope.valorUpdIngreso.infrac_num_ctrl,
					 "infrac_impresa" : $scope.valorUpdIngreso.infrac_impresa,
					 "infrac_num_arrastre" : $scope.valorUpdIngreso.infrac_num_arrastre,
					 "vorigen" : $scope.valorUpdIngreso.vorigen,
					 "infrac_ley_transporte" : $scope.valorUpdIngreso.infrac_ley_transporte,
					 "infrac_con_placa" : $scope.valorUpdIngreso.infrac_con_placa,
					 "emp_id" : $scope.valorUpdIngreso.emp_id,
					 "emp_placa" : $scope.valorUpdIngreso.emp_placa,
					 "dep_id" : $scope.valorUpdIngreso.dep_id,
					 "infrac_m_en_la_calle" : $scope.valorUpdIngreso.infrac_m_en_la_calle,
					 "infrac_m_colonia" : $scope.valorUpdIngreso.infrac_m_colonia,
					 "infrac_m_fecha_hora" : $scope.valorUpdIngreso.infrac_m_fecha_hora,
					 "tipo_l_cod" : $scope.valorUpdIngreso.tipo_l_cod,
					 "tipo_l_id" : $scope.valorUpdIngreso.tipo_l_id,
					 "del_nombre" : $scope.valorUpdIngreso.del_nombre,
					 "articulos" : articulosInfraccion,
									  
					 "num_resguardo" : $scope.ingresoInfracVO.num_resguardo,
					 "deposito" : $scope.ingresoInfracVO.deposito,
					 "num_serie_vehiculo" : $scope.ingresoInfracVO.num_serie_vehiculo,
					 "operativo" : $scope.ingresoInfracVO.operativo,
					 "documento" : $scope.ingresoInfracVO.documento,
//					 "placa_pre" : $scope.ingresoInfracVO.placa_pre,
					 "placa_pre" : $scope.valorUpdIngreso.infrac_impresa,
					 "t_ingr_id" : $scope.ingresoInfracVO.t_ingr_id,
					 "causa_id" : $scope.ingresoInfracVO.causa_id,
					 "depo_origen" : $scope.ingresoInfracVO.depo_origen,
					 "v_sellado" : $scope.ingresoInfracVO.v_sellado,
					 "v_cajuela" : $scope.ingresoInfracVO.v_cajuela,
					 "infrac_placa_empl" : $scope.ingresoInfracVO.infrac_placa_empl,
					 "infrac_observacion" : $scope.ingresoInfracVO.infrac_observacion,
					 "infrac_parametros_inv" : $scope.valoresInventario,
					 
					 "nombre_comp" : $scope.datosAuxiliar.nombre_comp,
					 "agrp_nombre" : $scope.datosAuxiliar.agrp_nombre,
					 "emp_ape_paterno" : $scope.datosAuxiliar.emp_ape_paterno,
					 "emp_ape_materno" : $scope.datosAuxiliar.emp_ape_materno,
					 "emp_nombre" : $scope.datosAuxiliar.emp_nombre,
					
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
					 "agrp_id" : $scope.datosSecAgrupArrasEmp.agrp_id,
					 "agrp_cod" : $scope.datosSecAgrupArrasEmp.agrp_cod,
					 "emp_cod" : $scope.datosSecAgrupArrasEmp.emp_cod,
					 "grua_id" : $scope.datosSecAgrupArrasEmp.grua_id,
					 "dep_usuario" : $scope.datosSecAgrupArrasEmp.dep_usuario,
					 
					 "grua_cod" : $scope.datosGrua.grua_cod,
					 "conse_prefijo" : $scope.datosGrua.conse_prefijo}
			
		     remisionaDepositoService.altaIngresoInfraccion(infraccionCompletaVO).success(function(data) {
				$scope.showAviso(data.mensaje, 'templateModalAviso');
				$scope.respuesta = data;
				$scope.viewFormulario = false;
				$scope.viewResultado = true;
			}).error(function(data) {
				$scope.showAviso(data.message, 'templateModalError');
			});
	  	}
	}
	
  	$scope.tab = function(event,next){
		var elemento = event.srcElement ? event.srcElement : event.target;
		var id = elemento.id;
		$("#"+next).focus();
	}
  	
  	$scope.nuevaRemision = function(){
  		window.history.back();
  	}
  
  	//Imprimir Recibo resguardo
	$scope.imprimeRecibo_res = function(){		
		remisionaDepositoService.ImprimirResguardoPDF($scope.valorUpdIngreso.infrac_num).success(function(data, status, headers) {
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});

			save(file, filename);
		}).error(function(result) {
			$scope.showAviso(result.message, 'templateModalError');
		});
	};
	
	//Imprimir recibo Arrastre
	$scope.imprimeRecibo_arra = function(){		
		remisionaDepositoService.ImprimirArrastrePDF($scope.valorUpdIngreso.infrac_num)
		.success(function(data, status, headers) {
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});

			save(file, filename);
		}).error(function(result) {
			$scope.showAviso(result.message, 'templateModalError');
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


	/* Modal Aviso templateModalAviso*/
	$scope.showAviso = function(messageTo, template) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/'+template+'.html',
        controller: 'mensajeModalController',
        inputs:{ message: messageTo}
      }).then(function(modal) {
        modal.element.modal();
      });
	};
	
	validarDeposito = function(){
		$scope.valorUpdIngreso.dep_id = remisionaDepositoService.getDeposito();
		$scope.ingresoInfracVO.infrac_con_placa = $scope.valorUpdIngreso.infrac_con_placa;
		$scope.ingresoInfracVO.infrac_ley_transporte = $scope.valorUpdIngreso.infrac_ley_transporte;
		$scope.ingresoInfracVO.infrac_placa = $scope.valorUpdIngreso.infrac_placa;
		$scope.ingresoInfracVO.grua_cod = $scope.valorUpdIngreso.grua_cod;
		
		
		if($scope.valorUpdIngreso.dep_id == 0){
			liberacionVehiculoService.validarDeposito().success(function(data){
				$scope.valorUpdIngreso.dep_id = data;
				remisionaDepositoService.setDeposito(data)
				listOperativo();
				listMedioTrans();
				listCausa();
				listDeposito();
				listInventario();
				listColor();
				listMarca();
				listTipoV();
				datosArticuloSancion();
				datosVehiculoTipoColorMarca();
				datosSectorAgrupaArrastreEmpGrua();
				datosGruaConce();
				cambiarOrigenv($scope.valorUpdIngreso.vorigen);
			}).error(function(data){
				remisionaDepositoService.setDeposito(0)
			});
		}else{
			listOperativo();
			listMedioTrans();
			listCausa();
			listDeposito();
			listInventario();
			datosArticuloSancion();
			datosSectorAgrupaArrastreEmpGrua();
			datosGruaConce();
			listColor();
			listMarca();
			listTipoV();
			datosVehiculoTipoColorMarca();
			cambiarOrigenv($scope.valorUpdIngreso.vorigen);
		}
	}
	
	validarDeposito();
	
});