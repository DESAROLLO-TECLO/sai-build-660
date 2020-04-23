angular.module('siidfApp').controller('corteCajaConsultaController',
	function($scope, $filter, cajaService, catalogoService, ModalService) {
	
	$scope.showScreenBy = "both";
	
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
	
	/*
	 * CORTE DE CAJA (CONSULTA)
	 */
	
	$scope.consulta = {};
	$scope.isTipoBusquedaVisible = false;
	
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
            return;
        }
		$scope.consultaByEmp = {};
		$scope.isCajaPreparada = false;
		$scope.consultaCajaUsuarios(false, $scope.consulta.parametroBusqueda, $scope.consulta.tipoBusqueda);
	}
	
	/*
	 * CORTE DE CAJA NUEVO
	 */
	ceroPesos = "$0.00";

	
	$scope.isCajaPreparada = false;
	$scope.isCorteRealizado = false;
	
	
	
	/**
	 * @param firstSearch bandera que nos indica si es la primer busqueda para conocer si el usuario logueado es cajero
	 * @param param parametro de filtro de busqueda
	 * @param tipoBusqueda paramtro que indica el tipo de filtro(""-> emp_id, caja, placa)
	 */
	$scope.consultaCajaUsuarios = function(firstSearch, param, tipoBusqueda){
		cajaService.consultaCajaUsuarios(param, tipoBusqueda)
		.success(function(data){
			$scope.consultaByEmp = data;
			if(firstSearch)
				$scope.consultaCajaFechaPorParam($scope.consultaByEmp[0].empId,"buscaPorPlaca");
			else{
				$scope.showScreenBy = tipoBusqueda;
				$scope.consultaCajaFechaPorParam(
						tipoBusqueda == "buscaPorPlaca" ? $scope.consultaByEmp[0].empId : $scope.consultaByEmp[0].cajaIdD,
						tipoBusqueda);
			}
			$scope.error = false;
		})
		.error(function(x,y,z){
			if(y==404){
				$scope.consultaByEmp = null;
				$scope.showScreenBy = "undefined";
				if(firstSearch)
					$scope.buscaTipoConsulta();
				else
					$scope.showAviso("No se encontraron registros");
			}
		})
	}
	
	$scope.consultaCajaFechaPorParam = function(param, tipoBusqueda){
		cajaService.consultaCajaFechaPorParam(param, tipoBusqueda)
		.success(function(data){
			$scope.catalogoFechaCaja = data;
		})
		.error(function(data){
			
		})
	}
	
	$scope.consultaCajaUsuarios(true, "", "");
	$scope.cajaPrep = {};
	$scope.consultaCaja = function(){
		var arregloValores = $scope.consulta.fechaCaja.split("|");
		var cajaId=arregloValores[0];
        var fecha= arregloValores[1];  
        var cajaCod= arregloValores[2];
        
		cajaService.preparaCorte(cajaId, "A", fecha)
		.success(function(data){
			$scope.cajaPrep = data;
			$scope.fechaSplit = $scope.consulta.fechaCaja.split('|')[1];
			$scope.cajaSplit = $scope.consulta.fechaCaja.split('|')[2];
			if($scope.cajaPrep.p_resultado==-1){
				$scope.showError($scope.cajaPrep.p_mensaje);
				return;
			}
			$scope.cajaPrep.empId = parseInt($scope.consultaByEmp[0].empId);
			$scope.cajaPrep.caja_cod = parseInt($scope.consultaByEmp[0].cajaCod);
			$scope.cajaPrep.resumenPartidaInicial = remove3firstCharc($scope.cajaPrep.p_partida_inicial);
			$scope.cajaPrep.resumenPartidaFinal = remove3firstCharc($scope.cajaPrep.p_partida_final);
			$scope.cajaPrep.totalDoc = $scope.cajaPrep.p_total_cheques + $scope.cajaPrep.p_total_tar_credito ;
			$scope.isCajaPreparada = true;
			$scope.modificaBillete(1000, $scope.cajaPrep.p_EFE_B1000)
			$scope.modificaBillete(500, $scope.cajaPrep.p_EFE_B500)
			$scope.modificaBillete(200, $scope.cajaPrep.p_EFE_B200)
			$scope.modificaBillete(100, $scope.cajaPrep.p_EFE_B100)
			$scope.modificaBillete(50, $scope.cajaPrep.p_EFE_B50)
			$scope.modificaBillete(20, $scope.cajaPrep.p_EFE_B20)
			$scope.modificaMoneda(20, $scope.cajaPrep.p_EFE_M20)
			$scope.modificaMoneda(10, $scope.cajaPrep.p_EFE_M10)
			$scope.modificaMoneda(5, $scope.cajaPrep.p_EFE_M5)
			$scope.modificaMoneda(2, $scope.cajaPrep.p_EFE_M2)
			$scope.modificaMoneda(1, $scope.cajaPrep.p_EFE_M1)
			$scope.modificaCent(.5, $scope.cajaPrep.p_EFE_MC50)
			$scope.modificaCent(.2, $scope.cajaPrep.p_EFE_MC20)
			$scope.modificaCent(.1, $scope.cajaPrep.p_EFE_MC10)
			$scope.modificaCent(.05, $scope.cajaPrep.p_EFE_MC5)
			$scope.recalcBillSub();
			$scope.recalcMonedaSub();
			$scope.error = false;
		})
		.error(function(data){
			$scope.cajaPrep = null;
			$scope.error = data;
		})
	}
	
	$scope.imprimirCaratula = function(){
		var fecha= $scope.consulta.fechaCaja.split("|")[1];
		$scope.cajaPrep.PTXT_FECHA_CORTE = fecha;
		cajaService.impresionCorteConsulta($scope.cajaPrep).success(function(data,status,headers){
			console.log(data);
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
		var fecha= $scope.consulta.fechaCaja.split("|")[1]; 
		cajaService.imprimirPartidas(
			$scope.cajaPrep.p_caja_id,
			$scope.cajaPrep.p_num_corte,
			fecha)
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
		var fecha= $scope.consulta.fechaCaja.split("|")[1]; 
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
        var fecha= $scope.consulta.fechaCaja.split("|")[1];  
		cajaService.imprimirInfracciones(
			$scope.cajaPrep.p_caja_id,
			$scope.cajaPrep.p_num_corte,
			fecha)
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
	
	
	$scope.modificaBillete = function(denom, cant){
		error = false;
		if (isNaN(cant)) 
		{
		    $scope.showAviso("Este campo exige carácter numérico mayor o igual a cero ");
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
		//$scope.recalcBillSub();
	}
	
	$scope.modificaMoneda = function(denom, cant){
		error = false;
		if (isNaN(cant)) 
		{
		    $scope.showAviso("Este campo exige carácter numérico mayor o igual a cero ");
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
		//$scope.recalcMonedaSub();
	}
	
	$scope.modificaCent = function(denom, cant){
	
		error = false;
		if (isNaN(cant)) 
		{
		    $scope.showAviso("Este campo exige caracter numérico mayor o igual a cero ");
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
		//$scope.recalcMonedaSub();
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
	
});