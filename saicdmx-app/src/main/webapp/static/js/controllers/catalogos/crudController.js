angular.module('siidfApp').controller('crudController', function($scope, $route, catalogoService,ModalService, growl) {
	
	$scope.crud = {};
	$scope.nombreServicioBusqueda = {};
	$scope.nombreServicioActualizacion = {};
	$scope.selectedOption = {};
	$scope.formsgeneral = {};
	
	$scope.objectMap = {};
	
	$scope.configCrud = function(nombreServicioBusqueda, nombreServicioActualizacion) {				
		if ((!$scope.selectedOption.concesionaria && $scope.opcion.opcionUrl === 'admon_cat_grua_grua.jsp') ||
			(!$scope.selectedOption.estado && $scope.opcion.opcionUrl === 'admon_cat_agrupamiento_region.jsp') ||
		    (!$scope.selectedOption.estado && $scope.opcion.opcionUrl === 'admon_cat_agrupamiento_delegacion.jsp') ||		    
		    (!$scope.selectedOption.delegacion && $scope.opcion.opcionUrl === 'admon_cat_agrupamiento_sector.jsp') ||
		    (!$scope.selectedOption.marca && $scope.opcion.opcionUrl === 'admon_cat_vehiculos_modelo.jsp') ||
		    (!$scope.selectedOption.region && $scope.opcion.opcionUrl === 'admon_cat_deposito_deposito.jsp') ||
		    (!$scope.selectedOption.estado && $scope.opcion.opcionUrl === 'admon_cat_deposito_region.jsp')) {				
			return;
		}
		//alert();
		$scope.nombreServicioBusqueda = nombreServicioBusqueda;
		$scope.nombreServicioActualizacion = nombreServicioActualizacion;
		$scope.datos = undefined;
		$scope.setPreselectedDependencyForNewRecord();
		catalogoService.buscarDatosCrud($scope.nombreServicioBusqueda).success(function(data) {
			if(data.length > 0){
			$scope.datos = data;
//			$scope.setPreselectedDependencyForNewRecord();
			}else{$scope.showAviso("No se encontraron registros");}
		}).error(function(data) {
			if(data.message == undefined){
				$scope.showAviso("No se encontraron registros");
			}else{
			$scope.error = data;
			$scope.datos = undefined;
			$scope.showAviso(data.message);
			}
		});
	};
	
	$scope.setPreselectedDependencyForNewRecord = function(){
		if ($scope.opcion.opcionUrl === 'admon_cat_grua_grua.jsp'){
			$scope.preselectedDependency = $scope.selectedOption.concesionaria;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_agrupamiento_region.jsp'){
			$scope.preselectedDependency = $scope.selectedOption.estado;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_agrupamiento_delegacion.jsp'){
			$scope.preselectedDependency = $scope.selectedOption.estado;
			$scope.preselectedDependency.listEdoD = [];
			$scope.preselectedDependency.listEdoD[0] = $scope.selectedOption.estado;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_agrupamiento_unidades.jsp'){
			$scope.preselectedDependency = $scope.selectedOption.sector;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_agrupamiento_sector.jsp'){
			$scope.preselectedDependency = {};
			$scope.preselectedDependency.listEdo = [];
			$scope.preselectedDependency.listEdo[0] = $scope.selectedOption.estado;
			$scope.preselectedDependency.edo = $scope.selectedOption.estado;
			$scope.preselectedDependency.del = $scope.selectedOption.delegacion;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_vehiculos_modelo.jsp'){
			$scope.preselectedDependency = $scope.selectedOption.marca;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_deposito_deposito.jsp'){
			$scope.preselectedDependency = $scope.selectedOption.region;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_deposito_region.jsp'){
			$scope.preselectedDependency = $scope.selectedOption.estado;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_vehiculos_tipo.jsp'){
			$scope.preselectedDependency = $scope.selectedOption.subTipo;
			return;
		}
	}
	
	$scope.getPreselectedDependencyForNewRecord = function(){
		if ($scope.opcion.opcionUrl === 'admon_cat_grua_grua.jsp'){
			$scope.crud.conseId = $scope.preselectedDependency.concesionariaId;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_agrupamiento_region.jsp'){
			$scope.crud.estadoId = $scope.preselectedDependency.edoId;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_agrupamiento_delegacion.jsp'){
			$scope.crud.edoId = $scope.preselectedDependency.edoId;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_agrupamiento_unidades.jsp'){
			$scope.crud.utId = {};
			$scope.crud.utId.secId = $scope.preselectedDependency.secId
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_agrupamiento_sector.jsp'){
			$scope.crud.estadoId = $scope.preselectedDependency.edo.edoId;
			$scope.crud.delegacionId = $scope.preselectedDependency.del.delId;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_vehiculos_modelo.jsp'){
			$scope.crud.vehiculoMarca = {};
			$scope.crud.vehiculoMarca.vMarId = $scope.preselectedDependency.vMarId;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_deposito_deposito.jsp'){
			$scope.crud.regionId = $scope.preselectedDependency.regionId;
			return;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_deposito_region.jsp'){
			$scope.crud.estadoDTO = {};
			$scope.crud.estadoDTO.edoId = $scope.preselectedDependency.edoId;
		}
		if ($scope.opcion.opcionUrl === 'admon_cat_vehiculos_tipo.jsp'){
			$scope.crud.vSubtipo = {}; 
			$scope.crud.vSubtipo.vSubTipoId = $scope.preselectedDependency.vSubTipoId;
			return;
		}
	}
	//It´s new 30/03/2017
	$scope.closeModal = function(){
		$('#datosCrud').modal('hide')
		 $("body").css({ 'padding-right': '0px' }); 
	}
	//validacion formulario
	requiredFields = function(){
		angular.forEach($scope.formsgeneral.formCrud.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	};
	
	$scope.showAviso = function(messageTo) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      }).then(function(modal) {
	        modal.element.modal();
	      });
		};
		 
	enviarCrud = function(nombreServicio, crud) {
		catalogoService.enviarCrud(nombreServicio, crud).success(function(data) {
			$scope.respuestaEnvioCrud = data;
			var actualizacionExitosa = $scope.respuestaEnvioCrud.resultado >= 0;
			if (actualizacionExitosa) {
				if ($scope.crud.operacion === "A") {
					$scope.crud = {};
				} else if ($scope.crud.operacion === "E") {
					$scope.crud.statusDesc = "Cancelado";
				} else {
					$scope.crud.statusDesc = "Activo";
				}
			}
			$scope.closeModal();
			$scope.showAviso($scope.respuestaEnvioCrud.mensaje);
			$scope.limpiarRespuesta();
			$scope.configCrud($scope.nombreServicioBusqueda, $scope.nombreServicioActualizacion);
					
			}).error(function(data) {
			$scope.error = data;
		});
	};
	
	$scope.generarExcel = function(nombreServicio) {
		catalogoService.generarExcel(nombreServicio).success(function(data, status, headers) {			
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
	
	$scope.setCrud = function(crud) {
		$scope.crud = angular.copy(crud);	
	};
	
	$scope.limpiarRespuesta = function() {
		$scope.respuestaEnvioCrud = null;
		$scope.crud = {};
		
		if($scope.formsgeneral.formCrud !== undefined) {
			$scope.formsgeneral.formCrud.$setPristine();
		}
	};
	
	$scope.desactivarCampo = function(crud) {
		if($scope.formsgeneral.formCrud.$invalid){
			requiredFields();
		}else{ crud.operacion = "E"; enviarCrud($scope.nombreServicioActualizacion, crud);}
	};
	
	$scope.actualizar = function(crud) {
		
		if($scope.formsgeneral.formCrud.$invalid){
			requiredFields();
		}else{	crud.operacion = "M"; enviarCrud($scope.nombreServicioActualizacion, crud);
		}
	};
	
	$scope.alta = function(crud) {
		if($scope.formsgeneral.formCrud.$invalid){
			requiredFields();
			growl.error('Formulario incompleto');
		}else{
		crud.operacion = "A";
		enviarCrud($scope.nombreServicioActualizacion, crud);
		}
	};
	
	$scope.buscarCatalogosWeb = function() {
		catalogoService.buscarCatalogosWeb().success(function(data) {
			$scope.catalogos = data;
		}).error(function(data) {
			$scope.error = data;			
		});
	}
	
	$scope.actualizarOpciones = function(catalogoId) {
		if (catalogoId == null) {
			$scope.opciones = {};
			return;
		}
		catalogoService.buscarCatalogosWebOpcionesPorCatalogo(catalogoId).success(function(data) {
			$scope.opciones = data;
		}).error(function(data) {
			$scope.opciones = {};
		});
		$scope.selectedOption = {};
	}
	
	//configura catalog de gruas
	$scope.buscarConcesionarias = function() {
		catalogoService.buscarConcesionarias().success(function(data) {
			$scope.concesionarias = data;
			buscarGruaStatus();
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	buscarGruaStatus = function() {
		catalogoService.buscarGruaStatus().success(function(data) {
			$scope.gruasEstatus = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	//configura catalogo de region
	$scope.buscarEstados = function() {
		catalogoService.buscarEstadosTodos().success(function(data) {
			$scope.estados = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	//configura catalogo de delegacion
	$scope.buscarRegionesPorEstado = function(estadoId) {
		if (estadoId) {
			catalogoService.buscarRegionesPorEstado(estadoId).success(function(data) {
				$scope.regiones = data;
				$scope.crud.edoId = estadoId;
			}).error(function(data) {
				$scope.error = data;
			});
		}		
	}
	
	//configura catalogo de unidad territoriales
	$scope.buscarSectores = function() {
		catalogoService.buscarSectores().success(function(data) {
			$scope.sectores = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	//configura catalogo de sector
	$scope.buscarDelegacionPorEstado = function(estadoId) {
		if (estadoId) {
			catalogoService.buscarDelegacionPorEstado(estadoId).success(function(data) {
				$scope.delegaciones = data;
			}).error(function(data) {
				$scope.error = data;
			});
		} else {
			$scope.delegaciones = {};
		}
	}
	
	//configura catalogo region deposito
	$scope.buscarRegionDepositoPorEstado = function(estadoId) {
		catalogoService.buscarRegionDepositoPorEstado(estadoId).success(function(data) {
			$scope.regionesDeposito = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	//configura catalogo deposito
	$scope.regionesDF = function() {
		catalogoService.buscarRegionesDF().success(function(data) {
			$scope.regiones = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	//configura catalogo deposito
	$scope.buscarZonas = function() {
		catalogoService.buscarZonas().success(function(data) {
			$scope.zonas = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	//configura catalogo de depositos
	$scope.buscarEstadoDF = function() {
		catalogoService.buscarEstadoPorCodigo("DF").success(function(data) {
			$scope.estadoDF = data;			
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	//configura catalogo de modelo
	$scope.buscarMarcas = function() {
		catalogoService.buscarVehiculosMarcasTodos().success(function(data) {
			$scope.marcas = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	//configura catalogo de tipo
	$scope.buscarSubTipos = function() {
		catalogoService.buscarSubTipos().success(function(data) {
			$scope.subTipos = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	$scope.limpiarSelectedOption = function() {
		$scope.selectedOption = {};
		
		if($scope.formsgeneral.formCrud !== undefined){
			$scope.formsgeneral.formCrud.$setPristine();
		}
	}
	
	$scope.$on('$locationChangeSuccess', function(event, current, previous) {
    	//$scope.objectMap.listaResultados = catalogoService.getMapSources().articulos;
    	$scope.objectMap.catalogoWeb = $scope.catalogoWeb;
    	$scope.objectMap.opcion = $scope.opcion;
    	$scope.objectMap.listaCatalago =  $scope.catalogos;
    	$scope.objectMap.listaOpciones =  $scope.opciones;
    	
    	$scope.proximoController = $route.current.$$route.controller;
 
    	if( $scope.proximoController == 'detalleArticuloController' ){
    		catalogoService.setMapObjects($scope.objectMap);
    	}
    	if( $scope.proximoController == 'altaArticuloController' ){
    		catalogoService.setMapObjects($scope.objectMap);
    	}
    	if( $scope.proximoController == 'articuloHistoricoController' ){
    		catalogoService.setMapObjects($scope.objectMap);
    	}
    	else{
     		$scope.objectMap.listaResultados = [];
     		catalogoService.setMapObjects($scope.objectMap);
    	}
    });
	
	verificarLista = function (){
		 var mapParameters = catalogoService.getMapObjects();
	 	   if (angular.isDefined(mapParameters.opcion.opcionUrl)) {
	 		   //$scope.datos =  mapParameters.listaResultados;
	 		   $scope.catalogoWeb = mapParameters.catalogoWeb;
	 		   $scope.opcion = mapParameters.opcion;
	 		   $scope.catalogos =  mapParameters.listaCatalago;
	 		   $scope.opciones =  mapParameters.listaOpciones;
	 	   }else{
	 		  $scope.buscarCatalogosWeb();
	 	   }
	}	
	
	verificarLista();
});