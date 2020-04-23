angular.module('siidfApp').controller('corteCajaNuevoController',
	function($scope, $filter, cajaService, catalogoService, ModalService) {
	
	$scope.showAviso = function(messageTo) {
	   	ModalService.showModal({
	   		templateUrl: 'views/templatemodal/templateModalAviso.html',
	   		controller: 'mensajeModalController',
	   		inputs:{ message: messageTo}
	   	}).then(function(modal) {
	   		modal.element.modal();
	   	});
	};

	$scope.showError = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalError.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
		});
	};
	
	/*
	 * CORTE DE CAJA (CONSULTA)
	 */
	
	$scope.consulta = {};
	$scope.isTipoBusquedaVisible = false;
	$scope.isSaved = false;
	
	$scope.buscaTipoConsulta = function(){
		$scope.isTipoBusquedaVisible = true;
		catalogoService.buscaTipoConsulta()
			.success(function(data){
				$scope.catalogoTipoBusqueda = data;
				$scope.consulta.tipoBusqueda = $scope.catalogoTipoBusqueda[0].codigoString;
				error = false;
			})
			.error(function(data){
				$scope.catalogoTipoBusqueda = {};
				$scope.error = data;
				$scope.showError($scope.error.error);
			})
	}
	
	$scope.buscarCaja = function(){
		if ($scope.busq.$invalid) {
            angular.forEach($scope.busq.$error, function (field) {
              angular.forEach(field, function(errorField){
            	  errorField.$setDirty();
              })
            });
            //$scope.showAviso("Ingrese el dato de búsqueda");
            return;
        }
		$scope.consultaByEmp = {};
		$scope.isCajaPreparada = false;
		$scope.consultaCajaUsuarios(false, $scope.consulta.parametroBusqueda, $scope.consulta.tipoBusqueda);
		limpiar();
	}
	
	/*
	 * CORTE DE CAJA NUEVO
	 */
	ceroPesos = "$0.00";
	
	$scope.totBillDenom = ceroPesos;
	$scope.milBillTot = ceroPesos;
	$scope.quinBillTot = ceroPesos;
	$scope.dosBillTot = ceroPesos;
	$scope.cienBillTot = ceroPesos;
	$scope.cincBillTot = ceroPesos;
	$scope.veinBillTot = ceroPesos;
	
	$scope.totMonDenom = ceroPesos;
	$scope.veinMonTot = ceroPesos;
	$scope.diezMonTot = ceroPesos;
	$scope.cincMonTot = ceroPesos;
	$scope.dosMonTot = ceroPesos;
	$scope.UnoMonTot = ceroPesos;
	$scope.cincCentTot = ceroPesos;
	$scope.veinCentTot = ceroPesos;
	$scope.diezCentTot = ceroPesos;
	$scope.cinCentTot = ceroPesos;
	
	$scope.milBill = 0;
	$scope.quinBill = 0;
	$scope.dosBill = 0;
	$scope.cienBill = 0;
	$scope.cincBill = 0;
	$scope.veinBill = 0;
	
	//$scope.totMonDen = 0;
	$scope.veinMon = 0;
	$scope.diezMon = 0;
	$scope.cincMon = 0;
	$scope.dosMon = 0;
	$scope.UnoMon = 0;
	$scope.cincCent = 0;
	$scope.veinCent = 0;
	$scope.diezCent = 0;
	$scope.cinCent = 0;
	
	$scope.totBill = 0;
	$scope.totMon = 0;
	$scope.isCajaPreparada = false;
	$scope.isCorteRealizado = false;
	
	
	
	$scope.consultaCajaUsuarios = function(firstSearch, param, tipoBusqueda){
		cajaService.consultaCajaUsuarios(param, tipoBusqueda)
		.success(function(data){
			$scope.consultaByEmp = data;
			if($scope.consultaByEmp == null){
				$scope.buscaTipoConsulta()
			}
			$scope.error = false;
		})
		.error(function(x,y,z){
			if(y==404){
				$scope.consultaByEmp = null;
				//$scope.buscaTipoConsulta();
				if(firstSearch)
					$scope.buscaTipoConsulta();
				else
					$scope.showAviso("No se encontraron registros");
			}
		})
	}
	
	$scope.consultaCajaUsuarios(true,"","");
	
	$scope.generaCajaPrep = function(caja){
		if(caja.empId == '0' || caja.empId == null){
			$scope.showAviso("La caja no tiene usuario asignado, no es posible generar el corte");
			return;
		}
		if(caja.cajaEstatus == 'Deshabilitada'){
			$scope.showAviso("La caja esta deshabilitada, no es posible generar el corte");
			return;
		}
		if(caja.deposito == 'SIN DEPOSITO ASIGNADO'){
			$scope.showAviso("La caja no tiene depósito asignado, no es posible generar el corte");
			return;
		}
		cajaService.preparaCorte(caja.cajaIdD, "N", "")
		.success(function(data){
			$scope.cajaPrep = data;
			if($scope.cajaPrep.p_resultado==-1){
				$scope.isCajaPreparada = false;
				$scope.showAviso($scope.cajaPrep.p_mensaje);
				return;
			}
			$scope.cajaPrep.resumenPartidaInicial = remove3firstCharc($scope.cajaPrep.p_partida_inicial);
			$scope.cajaPrep.resumenPartidaFinal = remove3firstCharc($scope.cajaPrep.p_partida_final);
			$scope.cajaPrep.totalDoc = $scope.cajaPrep.p_total_cheques + $scope.cajaPrep.p_total_tar_credito ;
			$scope.isCajaPreparada = true;
			$scope.isSaved = false;
			$scope.error = false;
		})
		.error(function(data){
			$scope.cajaPrep = null;
			$scope.error = data;
		})
	}
	
	$scope.modificaBillete = function(denom, cant){
		error = false;
		if (isNaN(cant)) 
		{
		    $scope.showError("Este campo exige carácter numérico mayor o igual a cero");
		    error = true;
		}
		cant = round(cant, 0);
		tot = denom * cant;
		totDenom = "$" + tot + ".00";
		switch(denom){
			case 1000:
				if(!error){
					$scope.milBillTot = totDenom;
					$scope.milBill = cant;
				}
				else{
					$scope.milBill = 0;
					$scope.milBillTot = "$0.00";
				}
			break;
			case 500:
				if(!error){
					$scope.quinBillTot = totDenom;
					$scope.quinBill = cant;
				}
				else{
					$scope.quinBill = 0;
					$scope.quinBillTot = "$0.00";
				}
			break;
			case 200:
				if(!error){
					$scope.dosBillTot = totDenom;
					$scope.dosBill = cant;
				}
				else{
					$scope.dosBill = 0;
					$scope.dosBillTot = "$0.00";
				}
			break;
			case 100:
				if(!error){
					$scope.cienBillTot = totDenom;
					$scope.cienBill = cant;
				}
				else{
					$scope.cienBill = 0;
					$scope.cienBillTot = "$0.00";
				}
			break;
			case 50:
				if(!error){
					$scope.cincBillTot = totDenom;
					$scope.cincBill = cant;
				}
				else{
					$scope.cincBill = 0;
					$scope.cincBillTot = "$0.00";
				}
			break;
			case 20:
				if(!error){
					$scope.veinBillTot = totDenom;
					$scope.veinBill = cant;
				}
				else{
					$scope.veinBill = 0;
					$scope.veinBillTot = "$0.00";
				}
			break;
		}
		$scope.recalcBillSub();
	}
	
	$scope.recalcBillSub = function(){
		$scope.totBill = 0;
		$scope.totBill += 1000 * ($scope.milBill == null ? 0 : $scope.milBill);
		$scope.totBill += 500 * ($scope.quinBill == null ? 0 : $scope.quinBill);
		$scope.totBill += 200 * ($scope.dosBill == null ? 0 : $scope.dosBill);
		$scope.totBill += 100 * ($scope.cienBill == null ? 0 : $scope.cienBill);
		$scope.totBill += 50 * ($scope.cincBill == null ? 0 : $scope.cincBill);
		$scope.totBill += 20 * ($scope.veinBill == null ? 0 : $scope.veinBill);
		$scope.totBillDenom = "$" + $scope.totBill + ".00";
	}

	$scope.modificaMoneda = function(denom, cant){
		error = false;
		if (isNaN(cant)) 
		{
		    $scope.showAviso("Este campo exige carácter numérico mayor o igual a cero");
		    error = true;
		}
		cant = round(cant, 0);
		tot = denom * cant;
		totDenom = "$" + tot + ".00";
		switch(denom){
			case 20:
				if(!error){
					$scope.veinMonTot = totDenom;
					$scope.veinMon = cant;
				}
				else{
					$scope.veinMon = 0;
					$scope.veinMonTot = "$0.00";
				}
			break;
			case 10:
				if(!error){
					$scope.diezMonTot = totDenom;
					$scope.diezMon = cant;
				}
				else{
					$scope.diezMon = 0;
					$scope.diezMonTot = "$0.00";
				}
			break;
			case 5:
				if(!error){
					$scope.cincMonTot = totDenom;
					$scope.cincMon = cant;
				}
				else{
					$scope.cincMon = 0;
					$scope.cincMonTot = "$0.00";
				}
			break;
			case 2:
				if(!error){
					$scope.dosMonTot = totDenom;
					$scope.dosMon = cant;
				}
				else{
					$scope.dosMon = 0;
					$scope.dosMonTot = "$0.00";
				}
			break;
			case 1:
				if(!error){
					$scope.UnoMonTot = totDenom;
					$scope.UnoMon = cant;
				}
				else{
					$scope.UnoMon = 0;
					$scope.UnoMonTot= "$0.00";
				}
			break;
		}
		$scope.recalcMonedaSub();
	}
	
	$scope.modificaCent = function(denom, cant){
		error = false;
		if (isNaN(cant)) 
		{
		    $scope.showAviso("Este campo exige carácter numérico mayor o igual a cero");
		    error = true;
		}
		cant = round(cant, 0);
		tot = denom * cant;
		tot = Math.round(tot * 100) / 100;
		//totDenom = "$" + tot + ".00";
		totDenom = $scope.generaMontoEnPesos(tot);
		switch(denom){
			case .5:
				if(!error){
					$scope.cincCentTot = totDenom;
					$scope.cincCent = cant;
				}
				else{
					$scope.cincCent = 0;
					$scope.cincCentTot = "$0.00";
				}
			break;
			case .2:
				if(!error){
					$scope.veinCentTot = totDenom;
					$scope.veinCent = cant;
				}
				else{
					$scope.veinCent = 0;
					$scope.veinCentTot = "$0.00";
				}
			break;
			case .1:
				if(!error){
					$scope.diezCentTot = totDenom;
					$scope.diezCent = cant;
				}
				else{
					$scope.diezCent = 0;
					$scope.diezCentTot = "$0.00";
				}
			break;
			case .05:
				if(!error){
					$scope.cinCentTot = totDenom;
					$scope.cinCent = cant;
				}
				else{
					$scope.cinCent = 0;
					$scope.cinCentTot = "$0.00";
				}
			break;
		}
		$scope.recalcMonedaSub();
	}
	
	$scope.recalcMonedaSub = function(){
		$scope.totMon = 0;
		$scope.totMon += 20 * ($scope.veinMon == null ? 0 : $scope.veinMon);
		$scope.totMon += 10 * ($scope.diezMon == null ? 0 : $scope.diezMon);
		$scope.totMon += 5 * ($scope.cincMon == null ? 0 : $scope.cincMon);
		$scope.totMon += 2 * ($scope.dosMon == null ? 0 : $scope.dosMon);
		$scope.totMon += 1 * ($scope.UnoMon == null ? 0 : $scope.UnoMon);
		$scope.totMon += .5 * ($scope.cincCent == null ? 0 : $scope.cincCent);
		$scope.totMon += .2 * ($scope.veinCent == null ? 0 : $scope.veinCent);
		$scope.totMon += .1 * ($scope.diezCent == null ? 0 : $scope.diezCent);
		$scope.totMon += .05 * ($scope.cinCent == null ? 0 : $scope.cinCent);
		$scope.totMon = Math.round($scope.totMon * 100) / 100;
		$scope.totMonDenom = $scope.generaMontoEnPesos($scope.totMon);
	}
	
	$scope.generaMontoEnPesos = function(monto){
		x = monto + "";
		if (x.indexOf('.') > -1){
			var cerosNeeded = 2 - x.substr(x.indexOf(".") + 1).length;
			if(cerosNeeded==1) return "$" + monto + "0";
			return "$" + monto;
		}
		else
			return "$" + monto + ".00";
	}
	
	$scope.generaCorte = function(){
		auxForImporte = $scope.totBill + $scope.totMon + $scope.cajaPrep.totalDoc;
		//alert($scope.totBill + "  " +$scope.totMon + "  " +  $scope.cajaPrep.totalDoc)
		if(auxForImporte != $scope.cajaPrep.p_total_corte){
			$scope.showAviso("Los valores no cuadran, por favor verifique los datos");
			return;
		}
		if(angular.isUndefined($scope.cajaPrep.p_RECIBO_FOL_INI) || $scope.cajaPrep.p_RECIBO_FOL_INI === null || isNaN($scope.cajaPrep.p_RECIBO_FOL_INI) || $scope.cajaPrep.p_RECIBO_FOL_INI === "" ){
			$scope.showAviso("Debe ingresar carácter númerico como folio inicial");
			return;
		}
		if(angular.isUndefined($scope.cajaPrep.p_RECIBO_FOL_FIN) || $scope.cajaPrep.p_RECIBO_FOL_FIN === null || isNaN($scope.cajaPrep.p_RECIBO_FOL_FIN) || $scope.cajaPrep.p_RECIBO_FOL_FIN === "" ){
			$scope.showAviso("Debe ingresar carácter númerico como folio final");
			return;
		}
		if(angular.isUndefined($scope.cajaPrep.p_RECIBO_TOTAL) || $scope.cajaPrep.p_RECIBO_TOTAL === null || isNaN($scope.cajaPrep.p_RECIBO_TOTAL) || $scope.cajaPrep.p_RECIBO_TOTAL === "" || (+$scope.cajaPrep.p_RECIBO_TOTAL) <1)
		{
			$scope.showAviso("Debe ingresar carácter númerico mayor a cero como total de recibos");
			return;
		}
		if(angular.isUndefined($scope.cajaPrep.p_RECIBO_UTILIZADOS) || $scope.cajaPrep.p_RECIBO_UTILIZADOS === null || isNaN($scope.cajaPrep.p_RECIBO_UTILIZADOS) || $scope.cajaPrep.p_RECIBO_UTILIZADOS === "" ){
			$scope.showAviso("Debe ingresar carácter númerico como folios utilizados");
			return;
		}
		if(angular.isUndefined($scope.cajaPrep.p_RECIBO_CANCELADOS) || isNaN($scope.cajaPrep.p_RECIBO_CANCELADOS) ){
			$scope.showAviso("El campo de folios cancelados esperaba carácteres numéricos");
			return;
		}
		if(angular.isUndefined($scope.cajaPrep.p_RECIBO_CANCELADOS) || isNaN($scope.cajaPrep.p_RECIBO_CANCELADOS) ){
			$scope.showAviso("El campo de folios cancelados esperaba carácteres numéricos");
			return;
		}
		if(angular.isUndefined($scope.cajaPrep.p_RECIBO_INUTILIZADOS) || isNaN($scope.cajaPrep.p_RECIBO_INUTILIZADOS) ){
			$scope.showAviso("El Campo de folios inutilizados esperaba carácteres numéricos");
			return;
		}
		if(angular.isUndefined($scope.cajaPrep.p_RECIBO_FALTANTES) || isNaN($scope.cajaPrep.p_RECIBO_FALTANTES) ){
			$scope.showAviso("El campo de folios faltantes esperaba carácteres numéricos");
			return;
		}
		auxForFolios = (+$scope.cajaPrep.p_RECIBO_UTILIZADOS) + (+$scope.cajaPrep.p_RECIBO_CANCELADOS) + (+$scope.cajaPrep.p_RECIBO_INUTILIZADOS) + (+$scope.cajaPrep.p_RECIBO_FALTANTES) ;
		if(auxForFolios != $scope.cajaPrep.p_RECIBO_TOTAL){
			$scope.showAviso("La suma de los folios utilizados, cancelados, inutilizados y faltantes no coinciden con el total");
			return;
		}
		
		$scope.cajaPrep.p_EFE_B1000 = $scope.milBill;
		$scope.cajaPrep.p_EFE_B500 = $scope.quinBill;
		$scope.cajaPrep.p_EFE_B200 = $scope.dosBill;
		$scope.cajaPrep.p_EFE_B100 = $scope.cienBill;
		$scope.cajaPrep.p_EFE_B50 = $scope.cincBill;
		$scope.cajaPrep.p_EFE_B20 = $scope.veinBill;
		
		$scope.cajaPrep.p_EFE_M20 = $scope.veinMon;
		$scope.cajaPrep.p_EFE_M10 = $scope.diezMon;
		$scope.cajaPrep.p_EFE_M5 = $scope.cincMon;
		$scope.cajaPrep.p_EFE_M2 = $scope.dosMon;
		$scope.cajaPrep.p_EFE_M1 = $scope.UnoMon;
		$scope.cajaPrep.p_EFE_MC50 = $scope.cincCent;
		$scope.cajaPrep.p_EFE_MC20 = $scope.veinCent;
		$scope.cajaPrep.p_EFE_MC10 = $scope.diezCent;
		$scope.cajaPrep.p_EFE_MC5 = $scope.cinCent;
		$scope.cajaPrep.caja_cod = $scope.consultaByEmp[0].cajaCod;
		
		$scope.cajaPrep.p_RECIBO_TOTAL = $scope.cajaPrep.p_RECIBO_TOTAL != "" ? $scope.cajaPrep.p_RECIBO_TOTAL : "0";
		$scope.cajaPrep.p_RECIBO_UTILIZADOS = $scope.cajaPrep.p_RECIBO_UTILIZADOS != "" ? $scope.cajaPrep.p_RECIBO_UTILIZADOS :"0";
		$scope.cajaPrep.p_RECIBO_CANCELADOS = $scope.cajaPrep.p_RECIBO_CANCELADOS != "" ? $scope.cajaPrep.p_RECIBO_CANCELADOS : "0";
		$scope.cajaPrep.p_RECIBO_INUTILIZADOS = $scope.cajaPrep.p_RECIBO_INUTILIZADOS != "" ? $scope.cajaPrep.p_RECIBO_INUTILIZADOS : "0";
		$scope.cajaPrep.p_RECIBO_FALTANTES = $scope.cajaPrep.p_RECIBO_FALTANTES != "" ? $scope.cajaPrep.p_RECIBO_FALTANTES : "0";
		
		cajaService.guardaCorte($scope.cajaPrep)
			.success(function(data){
				$scope.cajaGuarda = data;
				if($scope.cajaGuarda.p_resultado == 0){ 
					$scope.isCorteRealizado = true;
					$scope.isSaved = true;
					$scope.error = false;
//					$scope.showAviso($scope.cajaGuarda.p_mensaje)
					$scope.showAviso("Corte efectuado correctamente")
				}else{
					$scope.showError($scope.cajaGuarda.p_mensaje)
					$scope.isCorteRealizado = false;
					$scope.error = true;
				}
			})
			.error(function(data){
				$scope.cajaGuardad = {};
				$scope.error = data;
			})
	};
	
	$scope.imprimirCaratula = function(){
		cajaService.impresionCorteEfectuado($scope.cajaPrep).success(function(data,status,headers){
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});
			cajaService.downloadfile(file, filename);
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	$scope.imprimirPartidas = function(){
		cajaService.imprimirPartidas(
				String($scope.consultaByEmp[0].cajaIdD),
				$scope.cajaPrep.p_num_corte, 
				"")
		.success(function(data,status,headers){
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});
			cajaService.downloadfile(file, filename);
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	$scope.imprimirPartidas = function(){
		cajaService.imprimirPartidas(
			String($scope.consultaByEmp[0].cajaIdD),
			$scope.cajaPrep.p_num_corte,
			"")
		.success(function(data,status,headers){
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});
			cajaService.downloadfile(file, filename);
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	$scope.imprimirPartidastTar = function(){
		var fecha= ""; 
		cajaService.imprimirPartidastTar(
			$scope.cajaPrep.p_caja_id,
			$scope.cajaPrep.p_num_corte,
			fecha,
			$scope.consultaByEmp[0].cajaCod)
		.success(function(data,status,headers){
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});
			cajaService.downloadfile(file, filename);
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	$scope.imprimirInfracciones = function(){
		cajaService.imprimirInfracciones(
				String($scope.consultaByEmp[0].cajaIdD), 
				$scope.cajaPrep.p_num_corte, 
				"")
		.success(function(data,status,headers){
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});
			cajaService.downloadfile(file, filename);
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	function round(value, ndec){
	    var n = 10;
	    for(var i = 1; i < ndec; i++){
	        n *=10;
	    }

	    if(!ndec || ndec <= 0)
	        return Math.round(value);
	    else
	        return Math.round(value * n) / n;
	}
	
	function remove3firstCharc(c){
		c = c.toString();
		return c.substring(3, c.length);
		
	}
	
	limpiar=function(){
		ceroPesos = "$0.00";
		
		$scope.totBillDenom = ceroPesos;
		$scope.milBillTot = ceroPesos;
		$scope.quinBillTot = ceroPesos;
		$scope.dosBillTot = ceroPesos;
		$scope.cienBillTot = ceroPesos;
		$scope.cincBillTot = ceroPesos;
		$scope.veinBillTot = ceroPesos;
		
		$scope.totMonDenom = ceroPesos;
		$scope.veinMonTot = ceroPesos;
		$scope.diezMonTot = ceroPesos;
		$scope.cincMonTot = ceroPesos;
		$scope.dosMonTot = ceroPesos;
		$scope.UnoMonTot = ceroPesos;
		$scope.cincCentTot = ceroPesos;
		$scope.veinCentTot = ceroPesos;
		$scope.diezCentTot = ceroPesos;
		$scope.cinCentTot = ceroPesos;
		
		$scope.milBill = 0;
		$scope.quinBill = 0;
		$scope.dosBill = 0;
		$scope.cienBill = 0;
		$scope.cincBill = 0;
		$scope.veinBill = 0;
		
		//$scope.totMonDen = 0;
		$scope.veinMon = 0;
		$scope.diezMon = 0;
		$scope.cincMon = 0;
		$scope.dosMon = 0;
		$scope.UnoMon = 0;
		$scope.cincCent = 0;
		$scope.veinCent = 0;
		$scope.diezCent = 0;
		$scope.cinCent = 0;
		
		$scope.totBill = 0;
		$scope.totMon = 0;
		$scope.isCajaPreparada = false;
		$scope.isCorteRealizado = false;
		
	}
	
});