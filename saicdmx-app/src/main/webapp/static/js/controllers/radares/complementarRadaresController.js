angular.module('siidfApp').controller('complementarRadaresController', function($scope,  $window,$location, $filter, radaresService,ModalService) {
	$scope.archivoVO={};
	$scope.mensaje = {};
	$scope.MostrarTabla= false;
	$scope.fileEmpty = false;
	requiredFields = function(){
	
		    
		angular.forEach($scope.formComplementarArchivoRadares.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
                     
            //$scope.showAviso("Es necesario completar el formulario");
		});
	}
	
	$scope.generaDescargaArchivoZIP2 = function(){ 
		console.log('estos son los errores a exportar'+$scope.errores.length);
		console.log($scope.errores);
		if($scope.errores.length>=0){
			radaresService.descargarExcelErrores($scope.errores).success(function(data, status, headers) {
				var  filename  = headers('filename');
				var contentType = headers('content-type');
				var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
				radaresService.downloadfile(file, filename);
				$scope.error = false;
			}).error(function(data) {
				$scope.showAviso(data.message, "templateModalError");
			});
		}
		
	}
	
	$scope.showAviso = function(messageTo) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      }).then(function(modal) {
	        modal.element.modal();
	      });
		};
		$scope.showAvisoA = function(messageTo) {
		      ModalService.showModal({
		        templateUrl: 'views/templatemodal/templateModalAviso.html',
		        controller: 'mensajeModalController',
		        inputs:{ message: messageTo}
		      }).then(function(modal) {
		        modal.element.modal();
		        $window.location.reload();
		      });
			};
	
	$scope.cargarArchivosDeInfracciones = function (archivoVO){
		if ($('#exampleInputFile').get(0).files.length === 0) {
			$scope.fileEmpty = true;
			$scope.showAviso("Formulario Incompleto");
			if($scope.formComplementarArchivoRadares.$invalid){	requiredFields();}
		}else{
			$scope.fileEmpty = false;
			if($scope.formComplementarArchivoRadares.$invalid){	requiredFields();
		}else{
			console.log("tipo: "+$scope.archivoVO);
			var currentdate = new Date(); 
			var datetime = "Last Sync: " + currentdate.getDate() + "/"
			                + (currentdate.getMonth()+1)  + "/" 
			                + currentdate.getFullYear() + " @ "  
			                + currentdate.getHours() + ":"  
			                + currentdate.getMinutes() + ":" 
			                + currentdate.getSeconds();
			console.log(datetime);
		radaresService.cargaArchivoComplementacion(archivoVO, $scope.file).success(function(data){
			if(data.resultado == 1){	
				$scope.sucess = true;				
				$scope.showAvisoA(data.message);
				$scope.error = false;
				$scope.MostrarTabla= false;
				
			}else{
				if(data.resultado == 3){
					$scope.sucess = true;
					$scope.error = false;
					$scope.showAviso(data.message);
					$scope.MostrarTabla= false;
				}else{
					$scope.sucess = true;
					$scope.error = false;
					$scope.errores = data.errores;					
					$('#tablaErrores').modal('show'); 
					$scope.MostrarTabla= true;
					
					console.log('se imprimen los errores');
					console.log($scope.errores);
					$scope.generaErrores($scope.errores);
				}
				
			}
		}).error(function(data){
			var currentdate = new Date(); 
			var datetime = "Last Sync: " + currentdate.getDate() + "/"
			                + (currentdate.getMonth()+1)  + "/" 
			                + currentdate.getFullYear() + " @ "  
			                + currentdate.getHours() + ":"  
			                + currentdate.getMinutes() + ":" 
			                + currentdate.getSeconds();
			console.log(datetime);
			$scope.error = data;
			$scope.sucess = false;
			$scope.MostrarTabla= false;
		});
		}
			}
	};
	
	$scope.addFile = function(archivos){			
		$scope.$apply(function () {			
			if(!archivos.files[0]){
        		$scope.fileEmpty = true;
				$scope.file = null;
			 }else{
				$scope.fileEmpty = false;
		    	$scope.file = archivos.files[0];
        	}
		});

	}
	
	$scope.generaErrores=function(errores){
		console.log('entraste aqui');
		

//		var datas={ 
//				apellidomat:[], apellidopat:[], artid:[], calle:[], colonia:[], cp:[], 	delegacion:[],
//				entidad:[], fecha:[], hora:[], 	marca:[], modelo:[], nombre:[], numext:[], 
//				numint:[], nummotor:[], numserie:[], oficialplaca:[], origenplaca:[], 
//				placa:[], submar:[], tdskuid:[], telefono:[],	tipodeteccion:[],	telefono:[], ut:[]
//				};
		$scope.datas_err=[];
		
		for(var i in errores){
			var datas={ 
					apellidomat:{valor:"",descripcion:"", error:false}, 
					apellidopat:{valor:"",descripcion:"", error:false}, artid:{valor:"",descripcion:"", error:false}, calle:{valor:"",descripcion:"", error:false}, velocidaddetectada:{valor:"",descripcion:"", error:false}, colonia:{valor:"",descripcion:"", error:false}, cp:{valor:"",descripcion:"", error:false}, 	delegacion:{valor:"",descripcion:"", error:false},
					entidad:{valor:"",descripcion:"", error:false}, fecha:{valor:"",descripcion:"", error:false}, hora:{valor:"",descripcion:"", error:false}, 	marca:{valor:"",descripcion:"", error:false}, modelo:{valor:"",descripcion:"", error:false}, nombre:{valor:"",descripcion:"", error:false}, numext:{valor:"",descripcion:"", error:false}, 
					numint:{valor:"",descripcion:"", error:false}, nummotor:{valor:"",descripcion:"", error:false}, numserie:{valor:"",descripcion:"", error:false}, oficialplaca:{valor:"",descripcion:"", error:false}, origenplaca:{valor:"",descripcion:"", error:false}, 
					placa:{valor:"",descripcion:"", error:false}, submar:{valor:"",descripcion:"", error:false}, tdskuid:{valor:"",descripcion:"", error:false}, telefono:{valor:"",descripcion:"", error:false},	tipodeteccion:{valor:"",descripcion:"", error:false},	telefono:{valor:"",descripcion:"", error:false}, ut:{valor:"",descripcion:"", error:false}, 
					};
			
			if(errores[i].APELLIDOMAT=== undefined){
			}else{
				var apemat = errores[i].APELLIDOMAT.split(" -> ");
				var error_apellidomat={valor:apemat[0],descripcion:apemat[1], error:false};
				if(apemat.length>1){error_apellidomat.error=true};
				datas.apellidomat=error_apellidomat;
			}
			
			if(errores[i].APELLIDOPAT=== undefined){
			}else{
			var apepat = errores[i].APELLIDOPAT.split(" -> ");
			var error_apellidopat={valor:apepat[0],descripcion:apepat[1], error:false};
			if(apepat.length>1){error_apellidopat.error=true};
			datas.apellidopat=error_apellidopat;
			var iddett=errores[i].tipodeteccion;
			}
			
			if(errores[i].VELOCIDADDETECTADA=== undefined){
				
			}else{		
			var velo = errores[i].VELOCIDADDETECTADA.split(" -> ");
			var error_velocidaddetectada={valor:velo[0],descripcion:velo[1], error:false};
			if(velo.length>1){error_velocidaddetectada.error=true};
			datas.velocidaddetectada=error_velocidaddetectada;
			}
			
			if(errores[i].CALLE=== undefined){
			}else{
			var call = errores[i].CALLE.split(" -> ");
			var error_calle={valor:call[0],descripcion:call[1], error:false};
			if(call.length>1){error_calle.error=true};
			datas.calle=error_calle;
			}
			
			if(errores[i].COLONIA=== undefined){
			}else{
			var col = errores[i].COLONIA.split(" -> ");
			var error_colonia={valor:col[0],descripcion:col[1], error:false};
			if(col.length>1){error_colonia.error=true};
			datas.colonia=error_colonia;
			}
			
			if(errores[i].CP=== undefined){
			}else{
			var cp = errores[i].CP.split(" -> ");
			var error_cp={valor:cp[0],descripcion:cp[1], error:false};
			if(cp.length>1){error_cp.error=true};
			datas.cp=error_cp;
			}
			
			if(errores[i].DELEGACION=== undefined){
			}else{
			var del = errores[i].DELEGACION.split(" -> ");
			var error_delegacion={valor:del[0],descripcion:del[1], error:false};
			if(del.length>1){error_delegacion.error=true};
			datas.delegacion=error_delegacion;
			}
			
			if(errores[i].ENTIDAD=== undefined){
			}else{
			var enti = errores[i].ENTIDAD.split(" -> ");
			var error_entidad={valor:enti[0],descripcion:enti[1], error:false};
			if(enti.length>1){error_entidad.error=true};
			datas.entidad=error_entidad;
			}
			
			if(errores[i].FECHA=== undefined){
			}else{
			var fech = errores[i].FECHA.split(" -> ");
			var error_fecha={valor:fech[0],descripcion:fech[1], error:false};
			if(fech.length>1){error_fecha.error=true};
			datas.fecha=error_fecha;
			}
			
			if(errores[i].HORA=== undefined){
			}else{
			var hor = errores[i].HORA.split(" -> ");
			var error_hora={valor:hor[0],descripcion:hor[1], error:false};
			if(hor.length>1){error_hora.error=true};
			datas.hora=error_hora;
			}
			
			if(errores[i].MARCA=== undefined){
			}else{
			var mar = errores[i].MARCA.split(" -> ");
			var error_marca={valor:mar[0],descripcion:mar[1], error:false};
			if(mar.length>1){error_marca.error=true};
			datas.marca=error_marca;
			}
			
			if(errores[i].MODELO=== undefined){
			}else{
			var modl = errores[i].MODELO.split(" -> ");
			var error_modelo={valor:modl[0],descripcion:modl[1], error:false};
			if(modl.length>1){error_modelo.error=true};
			datas.modelo=error_modelo;
			}
			
			if(errores[i].NOMBRE=== undefined){
			}else{
			var nom = errores[i].NOMBRE.split(" -> ");
			var error_nombre={valor:nom[0],descripcion:nom[1], error:false};
			if(nom.length>1){error_nombre.error=true};
			datas.nombre=error_nombre;
			}
			
			if(errores[i].NUMEXT=== undefined){
			}else{
			var nume = errores[i].NUMEXT.split(" -> ");
			var error_numext={valor:nume[0],descripcion:nume[1], error:false};
			if(nume.length>1){error_numext.error=true};
			datas.numext=error_numext;
			}
			
			if(errores[i].NUMINT=== undefined){
			}else{
			var numi = errores[i].NUMINT.split(" -> ");
			var error_numint={valor:numi[0],descripcion:numi[1], error:false};
			if(numi.length>1){error_numint.error=true};
			datas.numint=error_numint;
			}
			
			if(errores[i].NUMMOTOR=== undefined){
			}else{
			var numo = errores[i].NUMMOTOR.split(" -> ");
			var error_nummotor={valor:numo[0],descripcion:numo[1], error:false};
			if(numo.length>1){error_nummotor.error=true};
			datas.nummotor=error_nummotor;
			}
			
			if(errores[i].NUMSERIE=== undefined){
			}else{
			var numse = errores[i].NUMSERIE.split(" -> ");
			var error_numserie={valor:numse[0],descripcion:numse[1], error:false};
			if(numse.length>1){error_numserie.error=true};
			datas.numserie=error_numserie;
			}
			
			if(errores[i].OFICIALPLACA=== undefined){
			}else{
			var oficipla = errores[i].OFICIALPLACA.split(" -> ");
			var error_oficialplaca={valor:oficipla[0],descripcion:oficipla[1], error:false};
			if(oficipla.length>1){error_oficialplaca.error=true};
			datas.oficialplaca=error_oficialplaca;
			}
			
			if(errores[i].ORIGENPLACA=== undefined){
			}else{
			var oripla = errores[i].ORIGENPLACA.split(" -> ");
			var error_origenplaca={valor:oripla[0],descripcion:oripla[1], error:false};
			if(oripla.length>1){error_origenplaca.error=true};
			datas.origenplaca=error_origenplaca;
			}
			
			if(errores[i].PLACA=== undefined){
			}else{
			var plac = errores[i].PLACA.split(" -> ");
			var error_placa={valor:plac[0],descripcion:plac[1], error:false};
			if(plac.length>1){error_placa.error=true};
			datas.placa=error_placa;
			}
			
			if(errores[i].SUBMAR=== undefined){
			}else{
			var submar = errores[i].SUBMAR.split(" -> ");
			var error_submar={valor:submar[0],descripcion:submar[1], error:false};
			if(submar.length>1){error_submar.error=true};
			datas.submar=error_submar;
			}
			
			if(errores[i].TDSKUID=== undefined){
			}else{
			var tdsk = errores[i].TDSKUID.split(" -> ");
			var error_tdskuid={valor:tdsk[0],descripcion:tdsk[1], error:false};
			if(tdsk.length>1){error_tdskuid.error=true};
			datas.tdskuid=error_tdskuid;
			}
			
			if(errores[i].TELEFONO=== undefined){
			}else{
			var tel = errores[i].TELEFONO.split(" -> ");
			var error_telefono={valor:tel[0],descripcion:tel[1], error:false};
			if(tel.length>1){error_telefono.error=true};
			datas.telefono=error_telefono;
			}
			
			if(errores[i].TIPODETECCION=== undefined){
			}else{
			var tipdet = errores[i].TIPODETECCION.split(" -> ");
			var error_tipodeteccion={valor:tipdet[0],descripcion:tipdet[1], error:false};
			if(tipdet.length>1){error_tipodeteccion.error=true};
			datas.tipodeteccion=error_tipodeteccion;
			}
			
			
			if(tipdet[0]!=3){
//				console.log('si se considera artid');
				if(errores[i].ARTID=== undefined){
				}else{
				var artid = errores[i].ARTID.split(" -> ");
				var error_artid={valor:artid[0],descripcion:artid[1], error:false};
				if(artid.length>1){error_artid.error=true};
				datas.artid=error_artid;
				}
				
			}else{
//				console.log('no se considera el artid');
			}
			
			if(errores[i].UT=== undefined){
			}else{
			var utt = errores[i].UT.split(" -> ");			
			var error_ut={valor:utt[0],descripcion:utt[1], error:false};
			if(utt.length>1){error_ut.error=true};
			datas.ut=error_ut;
			}
			
			$scope.datas_err.push(datas);
		}
		console.log('imprimes el scope');
		console.log($scope.datas_err);
		$scope.init=0;
	}
	
	$scope.proConsult = function(){
		$window.location.reload();
	}
	
	$scope.buscaResp = function(archivoTipo){
		if(archivoTipo.archivoTipoId == 4 || archivoTipo.archivoTipoId == 8){
			$scope.cargaResp = [
    			{id: 1, responsable: 'SSP'},
    			{id: 0, responsable: 'Concesionario'}
    		];
			$scope.disabledResp = false;
		}else{
			$scope.cargaResp = [
    			{id: 1, responsable: 'SSP'}
    		];
			$scope.disabledResp = true;
		}
		$scope.archivoVO.responsableProceso = $scope.cargaResp[0];
	}
	
	buscaCatArchivoTipoActivos = function() {
		radaresService.buscaCatArchivoTipoActivos().success(function(data) {
			$scope.catArchivoTipo = data;
			$scope.archivoVO.radaresCatArchivoTipoVO = $scope.catArchivoTipo[0];
			$scope.buscaResp($scope.catArchivoTipo[0]);
		}).error(function(data) {
			$scope.error = data;
		});
	};
	
	obtenerAnioSalarioMinimo = function() {
		radaresService.obtenerAnioSalarioMinimo().success(function(data) {
			$scope.aniosVO = data;
			$scope.archivoVO.comboValuesVO = $scope.aniosVO[0];
		}).error(function(data) {
			$scope.error = data;
		});
	};
	
	/****** Inicializador *******/
	buscaCatArchivoTipoActivos();
	obtenerAnioSalarioMinimo();
});