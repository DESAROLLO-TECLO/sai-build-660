angular.module('siidfApp').controller('sinInfraccionController', function($scope, $filter,$location, remisionaDepositoService,catalogoService, infraccionService, altaInfraccionService, infraccionAllDataService, liberacionVehiculoService, ModalService) {

	var dateCurrent = moment();
	var dateAfter   = moment().add(+5, 'm');
	
	$scope.dateTimePickerOptions = {
			format: 'DD/MM/YYYY HH:mm:ss',
			maxDate: dateAfter
			};
	
	$scope.viewOficialData = false;
	$scope.viewMessagePlaca = false;
	$scope.flagToShowFraccion =  true;
	$scope.flagToShowIncisoParrafo = true;
	$scope.flagToShowSancion = true;
	$scope.flagToShowArticulo = true;
	$scope.p_captura = '';

	$scope.ingresoSinInfracVO = {DG:{}};
	$scope.listModelo  = [];
	$scope.idMarca = '';
	$scope.idModelo = '';
	$scope.idColor = '';
	$scope.validColor = '';
	
	$scope.validar = {access: false, viewFormulario: true, viewResultado: false, viewFormInfraccion: false, 
			disabledFImpresa: false, disabledDocumento : true};
	
	$scope.forms = {};
	$scope.request = {};
	$scope.ctrlvalue = {};
	
	
	
	$scope.consultarPlaca = function() {
		if($scope.ingresoSinInfracVO.infrac_placa_empl != undefined){
		remisionaDepositoService.buscarOficialPorPlaca($scope.ingresoSinInfracVO.infrac_placa_empl)
		.success(function(data) {
		  $scope.datosAuxiliar = data;
		  $scope.error = false;
		  $scope.viewOficialData = true;
		  
			$scope.validColor = "#444";
			$scope.validMensaje = "La placa es valida.";
			
		}).error(function(data) {
			$scope.viewOficialData = false;
		    $scope.validColor = "red";
			$scope.validMensaje = "La placa no existe.";
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
			$scope.ingresoSinInfracVO.operativo = data[0].oper_cod;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listOperativo  = {};
		});
	};
	
	listMedioTrans = function() {	
		catalogoService.listaMediosTrasp().success(function(data) {
			$scope.listMedios = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.listMedios  = {};
		});
	};
	
	listCausa = function() {	
		catalogoService.listaCausaIngresoSin().success(function(data) {
			$scope.listCausa = data;
		}).error(function(data) {
			$scope.listCausa  = [];
		});
	};
	
	listDeposito = function() {	
		catalogoService.listaDeposito($scope.dep_id).success(function(data) {
			$scope.listDeposito = data;
			$scope.ingresoSinInfracVO.deposito = data[0];
		}).error(function(data) {
			$scope.error = data;
			$scope.listDeposito  = {};
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
	
	$scope.imChanged = function(){
		$scope.valoresInventario = $scope.ingresoSinInfracVO.valorInv.listInventario.join('-');
	}
	
	
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
	
	$scope.listModel = function() {	
	
//		if(angular.isDefined($scope.ingresoSinInfracVO.vmar_cod) && $scope.ingresoSinInfracVO.vmar_cod != null){
//			$scope.idMarca = $.grep($scope.listMarca, function (option) {
//		        return option.vMarCod == $scope.ingresoSinInfracVO.vmar_cod;
//		    })[0].vMarId;
			
			catalogoService.buscarVehiculosModeloPorMarca($scope.ingresoSinInfracVO.vmar_cod.vMarId).success(function(data) {
				$scope.listModelo = data;
			}).error(function(data) {
				$scope.error = data;
				$scope.listModelo  = [];
			});
//		}else{
//			$scope.listModelo  = [];
//		}
		
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

	
	$scope.verificaIngreso = function() {
		$scope.validar.viewFormInfraccion = false;
		if ($scope.ingresoSinInfracVO.causa_id == '02' || $scope.ingresoSinInfracVO.causa_id == '06'){
			$scope.showAviso('Para esta causa de ingreso se requiere dar de alta una infracción', 'templateModalAviso');
			$scope.validar.viewFormInfraccion = true;
			$scope.validar.disabledDocumento = true;
			$scope.validar.disabledFImpresa = false;
		}else if($scope.ingresoSinInfracVO.causa_id == '03' || $scope.ingresoSinInfracVO.causa_id == '04' 
					|| $scope.ingresoSinInfracVO.causa_id == '07'){
			$scope.validar.disabledFImpresa = true;
			$scope.validar.disabledDocumento = false;
			$scope.ingresoSinInfracVO.DG.infrac_impresa = "";
		}else{
			$scope.validar.disabledFImpresa = false;
			$scope.validar.disabledDocumento = true;
		}
	};
	
	$scope.tab = function(event,next){
		var elemento = event.srcElement ? event.srcElement : event.target;
		var id = elemento.id;
		if(id == "DGTipoUnidad" && $('#DGGrua').prop('disabled'))
			next = "calle";
		$("#"+next).focus();
	}

	$scope.nuevaRemision = function(){
		$scope.DG = {};
//		$scope.DG.unidadesTerritoriales = {};
//		$scope.DG.sectores = {};
//		$scope.DG.empleado = {};
		$scope.DG.grua = {};
		$scope.showBuscaCodigo = false;
		$scope.FI.fraccion = {};
//		$scope.FI.articulo = "";
		$scope.UBI={};
		$scope.DV.tarjetaCirculacion = "";
		$scope.nuevaInfraccionVO = {};
		$scope.ingresoSinInfracVO = {DG:{}};
//		$scope.forms.altaInfraccion.$setPristine();
		$scope.validar.viewFormulario = true;
		$scope.validar.access = true;
		$scope.validar.viewFormInfraccion = false;
		$scope.validar.viewResultado = false;
		$scope.viewMessagePlaca = false;
		$scope.viewOficialData = false;
		$scope.bringAllData();
		listDeposito();
		$scope.ingresoSinInfracVO.operativo = $scope.listOperativo[0].oper_cod;
		$scope.ingresoSinInfracVO.infrac_con_placa = 'S';
		$scope.DV.cuentaConPlaca = 'S';
		$scope.DV.servpublico = '';
		$("#nvehiculo").focus();
	}
	
	// Imprimir Recibo resguardo
	$scope.imprimeRecibo_res = function(){
		var infracNum =  null;
		if ($scope.ingresoSinInfracVO.causa_id == '02' || $scope.ingresoSinInfracVO.causa_id == '06'){
			infracNum = $scope.datosInfraccion.infrac_num;
		}else if($scope.ingresoSinInfracVO.causa_id == '03' || $scope.ingresoSinInfracVO.causa_id == '04'
				|| $scope.ingresoSinInfracVO.causa_id == '07'){
			infracNum = $scope.request.infrac_num;
		}
		remisionaDepositoService.ImprimirResguardoPDF(infracNum).success(function(data, status, headers) {
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});

			save(file, filename);
			$scope.error = false;
		}).error(function(result) {
			$scope.error = result;
			$scope.resultado = {};
		});
	};
	
	//Imprimir recibo Arrastre
	$scope.imprimeRecibo_arra = function(){
		var infracNum =  null;
		if ($scope.ingresoSinInfracVO.causa_id == '02' || $scope.ingresoSinInfracVO.causa_id == '06'){
			infracNum = $scope.datosInfraccion.infrac_num;
		}else if($scope.ingresoSinInfracVO.causa_id == '03' || $scope.ingresoSinInfracVO.causa_id == '04'
				|| $scope.ingresoSinInfracVO.causa_id == '07'){
			infracNum = $scope.request.infrac_num;
		}
		remisionaDepositoService.ImprimirArrastrePDF(infracNum).success(function(data, status, headers) {
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});

			save(file, filename);
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
	
	/* Modal Aviso */
	$scope.showAviso = function(messageTo, template) {
      ModalService.showModal({
        templateUrl: 'views/templatemodal/'+template+'.html',
        controller: 'mensajeModalController',
        inputs:{ message: messageTo}
      }).then(function(modal) {
        modal.element.modal();
      });
	};
	
	///////////////////////// INicia seccion de infracccion 
	
	$scope.nuevaInfraccionVO = {};
	
	$scope.resetForm = function(){
	//	$scope.altaInfraccion.$setPristine();
		$scope.DG = angular.copy($scope.originalDG);
		$scope.UBI = angular.copy($scope.originalUBI);
		$scope.DV = angular.copy($scope.originalDV);
		$scope.DI = angular.copy($scope.originalDI);
		$scope.FI = angular.copy($scope.orginalFI);
	}
	
	/*******************************************************************
	 * ****************         BringAllData(AD)        ****************
	 * *****************************************************************/
	$scope.bringAllData = function(){
		infraccionAllDataService.createAllData()
		.success(function(data){
			$scope.infraccionAllData = data;
			$scope.error = false;
			//buscarDistritoFederal();
			fillDataSelect();
		})
		.error(function(data){
			$scope.infraccionAllData = {};
			$scope.error = data;
		})
	}
	
	fillDataSelect = function(){
		$scope.DG.estadosDF = $scope.infraccionAllData.edoSinDelegacionCatalgo;
		$scope.DF = $scope.infraccionAllData.edoSinDelegacionCatalgo[0];
		$scope.DG.estado = $scope.DF;
		$scope.DG.sectores = $scope.infraccionAllData.sectorSinUTCatalogo;
		$scope.DG.CatalogoresponsableVehiculo = $scope.infraccionAllData.responsableVehiculoCatalogo;
		$scope.DG.CatalogoTipoArrastre = $scope.infraccionAllData.tipoArrastreCatalogo;
		$scope.DG.CatalogoTipoUnidad = $scope.infraccionAllData.tipoUnidadCatalogo;
		$scope.originalDG = $scope.DG;
		$scope.UBI.catalogoObserveQue = $scope.infraccionAllData.observeQueCatalogo;
		$scope.UBI.catalogoconDirecciones = $scope.infraccionAllData.conDireccionCatalogo;
		$scope.UBI.catalogoDelegaciones = $scope.infraccionAllData.delegacionPorEstadoCatalogo;
		$scope.originalUBI = $scope.UBI;
		$scope.DV.catalogoTiposVehiculo = $scope.infraccionAllData.vehiculoTipoCatalogo;
		$scope.DV.catalogoVehiculosMarcas = $scope.infraccionAllData.vehiculoMarcaCatalogo;
		$scope.DV.catalogoVehiculosColor = $scope.infraccionAllData.vehiculoColorCatalogo;
		$scope.DV.catalogoEstados = $scope.infraccionAllData.estadoCatalogo;
		$scope.DV.estado = $scope.DV.catalogoEstados[8];
		$scope.DV.catalogoGarantiasDocumentos = $scope.infraccionAllData.garantiasCatDocumentosCatalogo;
		$scope.DV.garantiaDocumento = null;
		$scope.originalDV = $scope.DV;
		$scope.DI = {};
		$scope.DI.catalogoTipoLicencia = $scope.infraccionAllData.tipoLicenciaCatalogo;
		$scope.originalDI = $scope.DI;
		$scope.FI = {};
		$scope.orginalFI = {};
		$scope.resetForm();
	}
	$scope.bringAllData();
	/*******************************************************************
	 * ****************         BringAllData(AD)        ****************
	 * *****************************************************************/
	
	
	/*******************************************************************
	 * ****************         Generales(DG)           ****************
	 * *****************************************************************/

	$scope.DG = {};
	$scope.DG.sectores = {};
	$scope.DG.unidadesTerritoriales = {};
	$scope.DG.disabledBuscaCodigo = true;
	$scope.showBuscaCodigo = false;
	$scope.DG.grua = {};
	$scope.DG.grua.valido = false;
	$scope.DG.grua.codigoColor = "black";
	
	$scope.DG.empleado = {};
	$scope.DG.empleado.valido = false;
	$scope.DG.empleado.placaColor = "black";
	
	
	$scope.updateFIFecha = function(){
		var fecha_anterior = ($scope.FI.fechaInfraccion)? moment($scope.FI.fechaInfraccion,"DD/MM/YYYY HH:mm:ss").format("DD/MM/YYYY") : null;
		if($scope.DG.fecha){	
			$scope.FI.fechaInfraccion = $filter('formatDate')($scope.DG.fecha,"DD/MM/YYYY HH:mm:ss");
			$scope.flagToShowArticulo = false;
			if($filter('formatDate')($scope.DG.fecha,"DD/MM/YYYY") != fecha_anterior)
				$scope.buscarArticulosActivos();
		}else{
			$scope.FI.fechaInfraccion = null;
			$scope.flagToShowArticulo = true;
			$scope.FI.articulo = "";
			$scope.FI.fraccion = "";
			$scope.FI.articuloFinal = "";
			$scope.FI.articuloSancion = "";
			$scope.flagToShowFraccion =  true;
			$scope.flagToShowIncisoParrafo = true;
			$scope.flagToShowSancion = true;
		}
	}
	

	$scope.actualizarSectoresDelDF = function() {
		$scope.DG.sectores = $scope.infraccionAllData.sectorSinUTCatalogo;
	};

	$scope.actualizarUnidadTerritorialPorSector = function() {
		catalogoService.buscarUnidadTerritorialPorSector(
				$scope.DG.Sector.secId).success(function(data) {
			$scope.DG.unidadesTerritoriales = data;
			// alert($scope.DG.unidadesTerritoriales[0].utCod);
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.DG.unidadesTerritoriales = {};
		});
	};

	$scope.actualizarBuscarCodigo = function() {
		if ($scope.DG.tipoUnidad == null) {
			$scope.showBuscaCodigo = false;
			$scope.DG.disabledBuscaCodigo = true;
		} else if ($scope.DG.tipoUnidad.codigoString == "G") {
			$scope.DG.grua = {};
			$scope.DG.grua.gruaCod = "";
			$scope.DG.grua.valido = false;
			$scope.showBuscaCodigo = true;
			$scope.DG.disabledBuscaCodigo = false;
		} else {
			$scope.DG.grua.gruaCod = "PIE";
			$scope.showBuscaCodigo = true;
			$scope.DG.disabledBuscaCodigo = true;
			$scope.buscarGruaPorCodigo();
		}
	};

	$scope.buscarGruaPorCodigo = function() {
		catalogoService.buscarGruaPorCodigo($scope.DG.grua.gruaCod)
				.success(function(data) {
					$scope.DG.grua = data;
					$scope.DG.grua.valido = true;
					$scope.DG.grua.codigoColor = "green";
					$scope.error = false;
				}).error(function(data) {
					$scope.error = data;
					codigo = $scope.DG.grua.gruaCod;
					$scope.DG.grua = {};
					$scope.DG.grua.gruaCod = codigo;
					$scope.DG.grua.valido = false;
					$scope.DG.grua.codigoColor = "red";
				});
	};
	
	$scope.typeCodigoDeGrua = function (){
		$scope.DG.grua.valido = false;
		$scope.DG.grua.codigoColor = "red";
	};
	
	$scope.buscarPlacaPorCodigo = function() {
		if($scope.DG.empleado!= null && $scope.DG.empleado.empPlaca != null && $scope.DG.empleado.empPlaca != "")
			catalogoService.empleadoPorPlaca($scope.DG.empleado.empPlaca)
				.success(function(data){
					$scope.DG.empleado = data;
					$scope.DG.empleado.valido = true;
					$scope.DG.empleado.placaColor = "green";
					$scope.error = false;
				}).error(function(data){
					$scope.error = data;
					placa = $scope.DG.empleado.empPlaca;
					$scope.DG.empleado = {};
					$scope.DG.empleado.empPlaca = placa;
					$scope.DG.empleado.valido = false;
					$scope.DG.empleado.placaColor = "red";
				});
		else
			$scope.showAviso("Se Requiere Ingresar la Placa");
	};
	
	$scope.typePlacaDeEmpleado = function (){
		$scope.DG.empleado.valido = false;
		$scope.DG.empleado.placaColor = "red";
	}

	
	buscarTodosLosEstados = function() {
		catalogoService.buscarTodosLosEstados().success(function(data) {
			$scope.estados = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.estados = {};
		});
	};
	
	/*******************************************************************
	 * ****************         Generales(DG)           ****************
	 * *****************************************************************/
	

	
	/*******************************************************************
	 * ***********     Ubicación de la Infracción(UBI)   ***************
	 * *****************************************************************/
	
	$scope.UBI={};
	
	 /******************************************************************
	 * ***********     Ubicación de la Infracción(UBI)   ***************
	 * *****************************************************************/
	
	
	
	 /******************************************************************
	 * ****************     Datos del Vehículo(DV)   *******************
	 * *****************************************************************/
	
	$scope.DV = {};
	$scope.DV.catalogoVehiculosModelo={};
	$scope.DV.sectorPublico = 'N';
	$scope.DV.origen ='N';
	$scope.ingresoSinInfracVO.vorigen = 'N';
	$scope.DV.cuentaConPlaca ='S';
	$scope.ingresoSinInfracVO.infrac_con_placa = 'S';
	$scope.DV.placaDisabled = false;
	$scope.DV.showSelectGarantiaCatDoc = false;
	
	$scope.actualizarVehiculosModeloPorMarca = function() {
		catalogoService.buscarVehiculosModeloPorMarca($scope.DV.vehiculoMarca.vMarId).success(function(data) {
			$scope.DV.catalogoVehiculosModelo = data;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.DV.catalogoVehiculosModelo = {};
		});
	};
	
	$scope.verificaEstadoExpedicion = function(){
		$scope.DV.garantiaDocumento = null;
		$scope.DV.garantiaFolio = null;
		if($scope.DV.estado.edoCod != 'DF' && $scope.DV.estado.edoCod != 'MET'){
			$scope.showAviso("La placa fue expedida en otro estado, por lo tanto se debe registrar una garantía.");
			$scope.DV.showSelectGarantiaCatDoc = true;
		}
		else
			$scope.DV.showSelectGarantiaCatDoc = false;
	}
	
	 /******************************************************************
	 * ****************     Datos del Vehículo(DV)   *******************
	 * *****************************************************************/
	
	
	/*******************************************************************
	 * ****************    Datos del Infractor (DI)   ******************
	 * *****************************************************************/
	
	$scope.actualizarDelegacionPorEstado = function() {
		catalogoService.delegacionPorEstado($scope.DI.direccionEstado.edoId)
			.success(function(data){
				$scope.DI.catalogoDelegaciones = data;
				//$scope.DI.delegacion = $scope.DI.catalogoDelegaciones[0];
				$scope.error = false;
			}).error(function(data){
				$scope.DI.catalogoDelegaciones = {};
				$scope.error = true;
			});
	};
	
	
	/*******************************************************************
	 * ****************    Datos del Infractor (DI)   ******************
	 * *****************************************************************/
	
	
	/*******************************************************************
	 * *************   Fundamentos de la Infracción(FI)  ***************
	 * *****************************************************************/
	
	
	$scope.buscarArticulosActivos = function(){
		catalogoService.buscarArticulosActivos($scope.FI.fechaInfraccion)
		.success(function(data) {
			$scope.FI.catalogoArticulos = data;
			$scope.FI.violacionLeyTransporte = 'S';
			
		}).error(function(data) {
			$scope.FI.catalogoArticulos = {};
		});
	};
	
	$scope.actualizarFraccionesPorArticulos = function(){
		if($scope.FI.articulo != null){
			$scope.flagToShowFraccion = false;
			catalogoService.buscarFraccionesActivasPorArticulo($scope.FI.fechaInfraccion, $scope.FI.articulo.artInfrFinArticulo)
			.success(function(data){
				$scope.FI.catalogoFracciones = data;
				$scope.actualizarIncisoParrafoPorFraccion();
			}).error(function(data){
				$scope.FI.catalogoFracciones = {};
				$scope.FI.fraccion = {};
			});
		}
		else{
			$scope.flagToShowFraccion = true;
			$scope.FI.catalogoFracciones = null;
			$scope.FI.fraccion = null;
			$scope.actualizarIncisoParrafoPorFraccion();
		}
	};
	
	$scope.actualizarIncisoParrafoPorFraccion = function(){
		$scope.FI.articulo.artInfrFinArticulo;
		$scope.FI.fraccion.artInfrFinFraccion;
	
		if($scope.FI.fraccion != null){
			$scope.flagToShowIncisoParrafo = false;
			catalogoService.buscarParrafoIncisoPorFraccion
					($scope.FI.fechaInfraccion, $scope.FI.articulo.artInfrFinArticulo , $scope.FI.fraccion.artInfrFinFraccion)
				.success(function(data){
					$scope.FI.catalogoIncisoParrafo = data;
					$scope.actualizarSancionesPorFraccion()
				}).error(function(data){
					$scope.FI.catalogoIncisoParrafo = {};
					$scope.FI.articuloFinal = {};
				})
		}else{
			$scope.flagToShowIncisoParrafo = true;
			$scope.FI.catalogoIncisoParrafo = null;
			$scope.FI.articuloFinal = null;
			$scope.actualizarSancionesPorFraccion();
		}
	}
	
	$scope.actualizarSancionesPorFraccion = function(){
		if($scope.FI.articuloFinal != null){
			$scope.flagToShowSancion = false;
			catalogoService.buscarSancionesPorFraccion($scope.FI.articuloFinal.articulo.artId)
			.success(function(data){
				$scope.FI.catalogoSancion =  data;
				$scope.FI.articuloSancion = $scope.FI.catalogoSancion[0];
			}).error(function(data){
				$scope.FI.catalogoSancion = {};
			})
		}else{
			$scope.flagToShowSancion = true;
			$scope.FI.catalogoSancion =  null;
			$scope.FI.articuloSancion = null;
		}
	}
	
	/*******************************************************************
	 * *************   Fundamentos de la Infracción(FI)  ***************
	 * *****************************************************************/
	
	$scope.probando = function(){
		if($scope.DG.tipoArrastre.codigoString == 'N')
			$scope.nuevaInfraccionVO.infracArrastre = 'N'; else $scope.nuevaInfraccionVO.infracArrastre = 'S'; //$scope.DG.tipoArrastre.codigoString;
		$scope.nuevaInfraccionVO.infracNumArrastre = null;
		if($scope.nuevaInfraccionVO.infracArrastre == 'N')
			$scope.nuevaInfraccionVO.infracTipoArrastre = null; else $scope.nuevaInfraccionVO.infracTipoArrastre = $scope.DG.tipoArrastre.codigoString;
		
		alert($scope.nuevaInfraccionVO.infracArrastre)
		alert($scope.nuevaInfraccionVO.infracTipoArrastre)
		
	}
	
	$scope.validateForm = function(){
		error = false;
		$scope.incompleteDG = {};
		if($scope.DG.fecha == null || $scope.DG.fecha == ""){
			error=true;
			$scope.incompleteDG.fecha = true;
		}
		
		if($scope.DG.Sector == null){
			error=true;
			$scope.incompleteDG.Sector = true;
		}
		
		if(error){
			$scope.showAviso("Formulario Incompleto");
		}
	}
	
	//// Registra Ingreso
	$scope.registraIngreso = function(ingresoSinInfracVO) {
		
		if($scope.forms.altaInfraccion.$invalid){
			requiredFields();
			$scope.showAviso("Formulario Incompleto", 'templateModalAviso');
		}else{
			if($scope.validColor == "red"){
				$scope.showAviso("Validar placa del oficial", 'templateModalError');
				return;
			}
			//Obtiene el ID del tipo de vehiculo selecionado 
//			$scope.idTipoV = $.grep($scope.listTipoV, function (option) {
//		        return option.vipoCod == $scope.ingresoSinInfracVO.vtipo_cod;
//		    })[0].vTipoId;
//			
//			if(angular.isDefined($scope.ingresoSinInfracVO.vmar_cod) && $scope.ingresoSinInfracVO.vmar_cod != null){
//				//Obtiene el ID del tipo de vehiculo selecionado 
//				$scope.idMarca = $.grep($scope.listMarca, function (option) {
//			        return option.vMarCod == $scope.ingresoSinInfracVO.vmar_cod;
//			    })[0].vMarId;
//			}
//			
//			if(angular.isDefined($scope.ingresoSinInfracVO.vmod_cod) && $scope.ingresoSinInfracVO.vmod_cod != null){
//				//Obtiene el ID del tipo de vehiculo selecionado 
//				$scope.idModelo = $.grep($scope.listModelo, function (option) {
//			        return option.vModCod == $scope.ingresoSinInfracVO.vmod_cod;
//			    })[0].vModId;
//			}
//			
//			if(angular.isDefined($scope.ingresoSinInfracVO.vcolor_cod) && $scope.ingresoSinInfracVO.vcolor_cod != null){
//				//Obtiene el ID del tipo de vehiculo selecionado 
//				$scope.idColor = $.grep($scope.listColor, function (option) {
//			        return option.vColorCod == $scope.ingresoSinInfracVO.vcolor_cod;
//			    })[0].vColorId;
//			}
			
			// Crea Infraccion si la causa de ingreso es ESCOLTADO
			if ($scope.ingresoSinInfracVO.causa_id == '02' || $scope.ingresoSinInfracVO.causa_id == '06'){
				$scope.nuevaInfraccionVO.infracImpresa = $scope.DG.infrac_impresa;
				$scope.nuevaInfraccionVO.secId = $scope.DG.Sector.secId;
				$scope.nuevaInfraccionVO.infracConPlaca = $scope.DV.cuentaConPlaca;
				$scope.nuevaInfraccionVO.infracPlacaEdo = $scope.DV.estado.edoId;
//				$scope.nuevaInfraccionVO.infracPlaca = $scope.DV.placa;
				$scope.nuevaInfraccionVO.infracPlaca = $scope.ingresoSinInfracVO.infrac_placa;
				$scope.nuevaInfraccionVO.infracIPaterno = $scope.DI.apellidoPaterno;
				$scope.nuevaInfraccionVO.infracIMaterno = $scope.DI.apellidoMaterno;
				$scope.nuevaInfraccionVO.infracINombre = $scope.DI.nombre;
				$scope.nuevaInfraccionVO.infracICalle = $scope.DI.calle;
				$scope.nuevaInfraccionVO.infracINumExt = $scope.DI.numeroExterior;
				$scope.nuevaInfraccionVO.infracINumInt = $scope.DI.numeroInterior;
				$scope.nuevaInfraccionVO.infracIColonia = $scope.DI.colonia;
				$scope.nuevaInfraccionVO.p_infrac_l_serv_publico = $scope.DV.servpublico;
				
				if($scope.DI.direccionEstado!=null)
					$scope.nuevaInfraccionVO.infracIedoId = $scope.DI.direccionEstado.edoId;
				else
					$scope.nuevaInfraccionVO.infracIedoId = null;
				
				if($scope.DI.delegacion!=null)
					$scope.nuevaInfraccionVO.infracIDelId = $scope.DI.delegacion.delId.delId;
				else
					$scope.nuevaInfraccionVO.infracIDelId = null;
				
				$scope.nuevaInfraccionVO.infracLicencia = $scope.DI.licencia;
				$scope.nuevaInfraccionVO.tipoLId = $scope.DI.tipoLicencia != null ? $scope.DI.tipoLicencia.tipoLId : null;
				$scope.nuevaInfraccionVO.infracLServPublico = $scope.DV.sectorPublico;
				$scope.nuevaInfraccionVO.infracLicEdo = $scope.DI.expedidaEnEstado != null ?  $scope.DI.expedidaEnEstado.edoId : null;
				
//				$scope.nuevaInfraccionVO.vMarId = $scope.idMarca;
				$scope.nuevaInfraccionVO.vMarId = $scope.ingresoSinInfracVO.vmar_cod != null ? $scope.ingresoSinInfracVO.vmar_cod.vMarId : null;
//				$scope.nuevaInfraccionVO.vModId = $scope.idModelo;
				$scope.nuevaInfraccionVO.vModId = $scope.ingresoSinInfracVO.vmod_cod != null ? $scope.ingresoSinInfracVO.vmod_cod.vModId.vModId : null;
//				$scope.nuevaInfraccionVO.vColorId = $scope.idColor;
				$scope.nuevaInfraccionVO.vColorId = $scope.ingresoSinInfracVO.vcolor_cod != null ? $scope.ingresoSinInfracVO.vcolor_cod.vColorId : null;
							
				$scope.nuevaInfraccionVO.vOrigen = $scope.DV.origen;
				$scope.nuevaInfraccionVO.vTarjetaCirculacion = $scope.DV.tarjetaCirculacion;
				
				$scope.nuevaInfraccionVO.vTipoId = $scope.ingresoSinInfracVO.vtipo_cod.vTipoId;
								
				$scope.nuevaInfraccionVO.infracMEnLaCalle = $scope.UBI.calle;
				$scope.nuevaInfraccionVO.infracMEntreCalle = $scope.UBI.entreCalle1;
				$scope.nuevaInfraccionVO.infracMYLaCalle = $scope.UBI.entreCalle2;
				$scope.nuevaInfraccionVO.infracMColonia = $scope.UBI.colonia;
				$scope.nuevaInfraccionVO.infracMEdoId = $scope.DG.estado.edoId;
				$scope.nuevaInfraccionVO.infracMDelId = $scope.UBI.delegacion.delId.delId;
				
				$scope.nuevaInfraccionVO.artId = $scope.FI.articuloFinal.articulo.artId;
				$scope.nuevaInfraccionVO.sancionArtId = $scope.FI.articuloSancion.articuloSancion.artId;
				$scope.nuevaInfraccionVO.infracLeyTransporte = $scope.FI.violacionLeyTransporte;
				$scope.nuevaInfraccionVO.artMotivacion = $scope.FI.articuloFinal.artInfrFinDescripcion;
				
				if($scope.DG.tipoArrastre == null || $scope.DG.tipoArrastre.codigoString == 'N' )
					$scope.nuevaInfraccionVO.infracArrastre = 'N'; else $scope.nuevaInfraccionVO.infracArrastre = 'S'; //$scope.DG.tipoArrastre.codigoString;
				$scope.nuevaInfraccionVO.infracNumArrastre = null;
				if($scope.nuevaInfraccionVO.infracArrastre == 'N')
					$scope.nuevaInfraccionVO.infracTipoArrastre = null; else $scope.nuevaInfraccionVO.infracTipoArrastre = $scope.DG.tipoArrastre.codigoString;
				$scope.nuevaInfraccionVO.gruaId = $scope.DG.grua.gruaId;
				$scope.nuevaInfraccionVO.empleadoId = $scope.datosAuxiliar.emp_id;
				$scope.nuevaInfraccionVO.emp_Sector = $scope.datosAuxiliar.sec_id;
				$scope.nuevaInfraccionVO.empAgrup = $scope.datosAuxiliar.agrp_id;
				if($scope.DG.ResponsableVehiculo == null || $scope.DG.ResponsableVehiculo.rVehId == 0)
					$scope.nuevaInfraccionVO.rVehId = 99; else $scope.nuevaInfraccionVO.rVehId = $scope.DG.ResponsableVehiculo.rVehId;
				$scope.nuevaInfraccionVO.oper = "A";
				$scope.nuevaInfraccionVO.infracNumCtrl = null;
				if($scope.DG.UnidadT == null || $scope.DG.UnidadT.utId.utId == 0)
					$scope.nuevaInfraccionVO.utId = null; else $scope.nuevaInfraccionVO.utId = $scope.DG.UnidadT.utId.utId;
				$scope.nuevaInfraccionVO.fecha = $filter('formatDate')($scope.DG.fecha,"DD/MM/YYYY HH:mm:ss");
				$scope.nuevaInfraccionVO.modificadoPor = 1;
				$scope.nuevaInfraccionVO.resultado = "PENDIENTE";
				$scope.nuevaInfraccionVO.mensaje = "PENDIENTE";
				$scope.nuevaInfraccionVO.empleadoCod = null;
				$scope.nuevaInfraccionVO.empAgrupamiento = null;
				$scope.nuevaInfraccionVO.empPat = null;
				$scope.nuevaInfraccionVO.empMaterno = null;
				$scope.nuevaInfraccionVO.empNombre = null;
				$scope.nuevaInfraccionVO.infracCaptura = null;
				$scope.nuevaInfraccionVO.infracApoyoGrua = null;
				$scope.nuevaInfraccionVO.infracRfc = $scope.DI.rfc;
				$scope.nuevaInfraccionVO.fechaEmision = null;
				$scope.nuevaInfraccionVO.infracObservacion = $scope.FI.motivacionCompleta;
				$scope.nuevaInfraccionVO.motivoCambio = null;
				if($scope.UBI.conDireccion == null)
					$scope.nuevaInfraccionVO.infracConDireccion = "SIN DATO"; 
				else 
					$scope.nuevaInfraccionVO.infracConDireccion = $scope.UBI.conDireccion.catConDireccionDesc;
				
				if($scope.UBI.numero == null || $scope.UBI.numero == "")
					$scope.nuevaInfraccionVO.infracFrenteAlNumero  = "SIN DATO"; else $scope.nuevaInfraccionVO.infracFrenteAlNumero = $scope.UBI.numero;
				
				if($scope.UBI.observeQue == null)
					$scope.nuevaInfraccionVO.observeQue = "0"; 
				else 
					$scope.nuevaInfraccionVO.observeQue = $scope.UBI.observeQue.observeId;
				
				if($scope.DV.garantiaDocumento!=null){
					$scope.nuevaInfraccionVO.garantiaTipoId = $scope.DV.garantiaDocumento.garantiaCatDocumentoId;
					$scope.nuevaInfraccionVO.garantiaFolio = $scope.DV.garantiaFolio;
				}
				else{
					$scope.nuevaInfraccionVO.garantiaTipoId = null;
					$scope.nuevaInfraccionVO.garantiaFolio = null;
				}
	
				altaInfraccionService.altaInfraccion($scope.nuevaInfraccionVO).success(function(data){
					if(data.p_resultado == '-1'){
						$scope.showAviso(data.p_mensaje, 'templateModalError');
						return;
					}else{
						$scope.infracNueva = data;
					}
					
					$scope.ctrlvalue.nci = data.p_resultado;
					// Obtiene los datos de la infraccion generada para el alta el ingreso
					remisionaDepositoService.buscarInfraporNCI(data.p_resultado).success(function(dataInfra) {
						$scope.datosInfraccion = dataInfra;
						
						remisionaDepositoService.valorArticuloSancion($scope.datosInfraccion.infrac_num).success(function(dataArt) {
							$scope.datosArticuloSancion = dataArt;
							
							remisionaDepositoService.valorVehiculo($scope.datosInfraccion.infrac_num).success(function(dataV) {
								$scope.datosVehiculo = dataV;
						
								remisionaDepositoService.valorSecAgrupArrasEmp($scope.datosInfraccion.infrac_num).success(function(dataSecA) {
									$scope.datosSecAgrupArrasEmp = dataSecA;
									
									remisionaDepositoService.valorGruaConce($scope.datosInfraccion.infrac_num).success(function(dataGrua) {
										$scope.datosGrua = dataGrua;
	
										var articulosInfraccion = $scope.datosArticuloSancion.art_numero + ' - ' + 
												$scope.datosArticuloSancion.art_fraccion + ' - ' + 
												$scope.datosArticuloSancion.art_parrafo + ' - ' + 
												$scope.datosArticuloSancion.art_inciso;
										
										var sinInfraccionCompletaVO = {
											"dep_usuario" : $scope.datosSecAgrupArrasEmp.dep_usuario,	
											"infrac_num_ctrl" : $scope.datosInfraccion.infrac_num_ctrl,	
											//"tipo_l_id" : $scope.datosInfraccion.tipo_l_id,
											"causa_id" : $scope.ingresoSinInfracVO.causa_id,
											"conse_prefijo" : $scope.datosGrua.conse_prefijo,
											"deposito" : $scope.ingresoSinInfracVO.deposito.dep_cod,
											//deposito origen
											"grua_cod" : $scope.datosGrua.grua_cod,
											"infrac_num_arrastre" : $scope.datosInfraccion.infrac_num_arrastre,
											"infrac_num" : $scope.datosInfraccion.infrac_num,
											"infrac_placa_empl" : $scope.ingresoSinInfracVO.infrac_placa_empl,
											"sec_cod" : $scope.datosSecAgrupArrasEmp.sec_cod,
//											"placa_pre" : $scope.ingresoSinInfracVO.placa_pre,
											"placa_pre" : $scope.ingresoSinInfracVO.DG.infrac_impresa,
											"articulos" : articulosInfraccion,
											"emp_cod" : $scope.datosSecAgrupArrasEmp.emp_cod,
											"vtipo_cod" : $scope.datosVehiculo.vtipo_cod,
											"infrac_placa" : $scope.datosInfraccion.infrac_placa,
											"vcolor_cod" : $scope.datosVehiculo.vcolor_cod,
											"vmar_cod" : $scope.datosVehiculo.vmar_cod,
											"vmod_cod" : $scope.datosVehiculo.vmod_cod,
											"vorigen" : $scope.datosInfraccion.vorigen,
											"num_serie_vehiculo" : $scope.ingresoSinInfracVO.num_serie_vehiculo,
											
											/**************************/
											"dep_id" : $scope.dep_id,
											"t_ingr_id" : $scope.ingresoSinInfracVO.t_ingr_id,	//INT
											"v_sellado" : $scope.ingresoSinInfracVO.v_sellado,
											"v_cajuela" : $scope.ingresoSinInfracVO.v_cajuela,
											"infrac_parametros_inv" : $scope.valoresInventario,
											"infrac_observacion" : $scope.ingresoSinInfracVO.infrac_observacion,
											"documento": $scope.ingresoSinInfracVO.documento,
											"p_captura": ''
										};
										
								        /*var sinInfraccionCompletaVO = {
											"dep_id" : $scope.dep_id,
											"infrac_num_ctrl" : $scope.datosInfraccion.infrac_num_ctrl,	
											"t_ingr_id" : $scope.ingresoSinInfracVO.t_ingr_id,	//INT
											"causa_id" : $scope.ingresoSinInfracVO.causa_id, //INT
											"infrac_num" : $scope.datosInfraccion.infrac_num,
											//resguardo
											//asn
											"v_sellado" : $scope.ingresoSinInfracVO.v_sellado,
											"v_cajuela" : $scope.ingresoSinInfracVO.v_cajuela,
											"infrac_parametros_inv" : $scope.valoresInventario,
											//usu_id
											"vtipo_cod" : $scope.datosVehiculo.vtipo_cod,
											"num_serie_vehiculo" : $scope.ingresoSinInfracVO.num_serie_vehiculo,
											"infrac_observacion" : $scope.ingresoSinInfracVO.infrac_observacion,
											"documento": $scope.ingresoSinInfracVO.documento,
											"p_captura": ''
										}*/
										
								        // Entra a Admon Infra y crea Numero de Resguardo 
										remisionaDepositoService.altaIngresoSinInfraccion(sinInfraccionCompletaVO).success(function(data) {
									    	// Obtiene el numero de resguardo y asn y realiza el ingreso												 
											$scope.showAviso(data.mensaje, 'templateModalAviso');
											$scope.request = data;
											$scope.validar.viewFormulario = false;
											$scope.validar.viewResultado = true;
										}).error(function(data) {
											$scope.showAviso(data.message, 'templateModalError');
										});
									}).error(function(dataGrua) {
										$scope.error = dataGrua;
										$scope.datosGrua = {};
									});
								}).error(function(dataSecA) {
									$scope.error = dataSecA;
									$scope.datosSecAgrupArrasEmp  = {};
								});
							}).error(function(dataV) {
								$scope.error = dataV;
								$scope.datosVehiculo  = {};
							});
						}).error(function(dataArt) {
							$scope.error = dataArt;
							$scope.datosArticuloSancion  = {};
						});
					}).error(function(dataInfra) {
						$scope.error = dataInfra;
						$scope.datosInfraccion  = {};
						$scope.showAviso(dataInfra.message, 'templateModalAviso');
					});
				}).error(function(data){
				
				});
				
				}else{ // si la causa es diferente registra ingreso
					var codigoGrua = '0'
					if($scope.ingresoSinInfracVO.t_ingr_id == '01'){
						codigoGrua = $scope.ingresoSinInfracVO.grua_cod_combo;
					}else{
						codigoGrua = $scope.ingresoSinInfracVO.grua_cod;
					}
					
					var sinInfraccionCompletaVO = {
						"dep_id" : $scope.dep_id,
						"deposito" : $scope.ingresoSinInfracVO.deposito.dep_cod,
						"dep_usuario" : $scope.ingresoSinInfracVO.deposito.dep_usuario,
						"num_serie_vehiculo" : $scope.ingresoSinInfracVO.num_serie_vehiculo,
						"infrac_con_placa" : $scope.ingresoSinInfracVO.infrac_con_placa,
						"infrac_placa" : $scope.ingresoSinInfracVO.infrac_placa,
						"vorigen" : $scope.ingresoSinInfracVO.vorigen,
						"vtipo_cod" : $scope.ingresoSinInfracVO.vtipo_cod.vipoCod,
						"vmar_cod" : $scope.ingresoSinInfracVO.vmar_cod== null ? null :$scope.ingresoSinInfracVO.vmar_cod.vMarCod,
						"vmod_cod" : $scope.ingresoSinInfracVO.vmod_cod==null?null: $scope.ingresoSinInfracVO.vmod_cod.vModCod,
						"vcolor_cod" : $scope.ingresoSinInfracVO.vcolor_cod==null?null:$scope.ingresoSinInfracVO.vcolor_cod.vColorCod,
						"documento" : $scope.ingresoSinInfracVO.documento,
						"causa_id" : $scope.ingresoSinInfracVO.causa_id,
						"t_ingr_id" : $scope.ingresoSinInfracVO.t_ingr_id,
						"grua_cod" : codigoGrua,
						"infrac_placa_empl" : $scope.ingresoSinInfracVO.infrac_placa_empl,
						"articulos": ' ',
						"infrac_ley_transporte" : $scope.FI.violacionLeyTransporte,
						"v_sellado" : $scope.ingresoSinInfracVO.v_sellado,
						"v_cajuela" : $scope.ingresoSinInfracVO.v_cajuela,
						"infrac_parametros_inv" : $scope.valoresInventario, 
						"infrac_observacion" : $scope.ingresoSinInfracVO.infrac_observacion 
					 }
					 
				     remisionaDepositoService.altaIngresoSinInfraccion(sinInfraccionCompletaVO).success(function(data) {
				    	 	$scope.request = data;
				    	 	if(data.resultado != '-1'){
			    	 			$scope.showAviso('Actualización Exitosa', 'templateModalAviso');
			    	 			$scope.request.mensaje = "Actualización Exitosa";
			    	 			$scope.validar.viewFormulario = false;
								$scope.validar.viewResultado = true;
			    	 		}else{
			    	 			$scope.showAviso(data.mensaje, 'templateModalAviso');
			    	 		}
						}).error(function(data) {
							$scope.ingresoSinInfra = {};
							$scope.showAviso(data.message, 'templateModalError');
						});
					
					}
				}
			};
	
	
	$scope.romanize = function(num) {
	    if (!+num)
	        return num;
	    var digits = String(+num).split(""),
	        key = ["","C","CC","CCC","CD","D","DC","DCC","DCCC","CM",
	               "","X","XX","XXX","XL","L","LX","LXX","LXXX","XC",
	               "","I","II","III","IV","V","VI","VII","VIII","IX"],
	        roman = "",
	        i = 3;
	    while (i--)
	        roman = (key[+digits.pop() + (i * 10)] || "") + roman;
	    return Array(+digits.join("") + 1).join("M") + roman;
	}
	
	
	requiredFields = function(){
		angular.forEach($scope.forms.altaInfraccion.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	listNoGruas = function(){
		catalogoService.listaNoGruas().success(function(data){
			$scope.listaGruas = data;
		}).error(function(data){
			$scope.listaGruas = {};
		})
	}
	
	
	validarDeposito = function(){
		$scope.dep_id = remisionaDepositoService.getDeposito();
		if($scope.dep_id == 0){
			liberacionVehiculoService.validarDeposito().success(function(data){
				$scope.validar.access = true;
				$scope.dep_id = data;
				remisionaDepositoService.setDeposito(data);
				listOperativo();
				listMedioTrans();
				listNoGruas();
				listCausa();
				listDeposito();
				listInventario();
				listTipoV();
				listMarca();
				listColor();
			}).error(function(data){
				remisionaDepositoService.setDeposito(0);
				$scope.validar.message = data.message;
			});
		}else{
			$scope.validar.access = true;
			listOperativo();
			listMedioTrans();
			listNoGruas();
			listCausa();
			listDeposito();
			listInventario();
			listTipoV();
			listMarca();
			listColor();
		}
	}
	
	  $scope.cambioCuentaConPlaca = function(val){
		  $scope.ingresoSinInfracVO.infrac_placa = "";
		  $scope.DV.placa = "";
		  $scope.DV.cuentaConPlaca = val;
		  $scope.ingresoSinInfracVO.infrac_con_placa = val;
	  }
	  
	  $scope.cambiarOrigenv = function(val){
		  $scope.ingresoSinInfracVO.vorigen = val;
		  $scope.DV.origen = val;
	  }
	  
	  $scope.cambioCuentaConGrua = function(){
		  if($scope.ingresoSinInfracVO.t_ingr_id == '01'){
			  $scope.ingresoSinInfracVO.grua_cod = "";
		  }
	  }
	  
	  $scope.cambioNoGrua = function(){
		  if($scope.ingresoSinInfracVO.grua_id != 168){
			  $scope.ingresoSinInfracVO.grua_cod = "";
		  }
	  }	
	  
	  validarDeposito();
});