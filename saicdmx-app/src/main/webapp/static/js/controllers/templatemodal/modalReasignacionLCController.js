angular.module('siidfApp').controller('modalReasignacionLCController', function($scope, lineaCapturaService) {
	$scope.generarPDFPagoReasignacion = function(RespuestaWSVO){
		var reasignacionVO = {"folioInfraccion" : RespuestaWSVO.folio,
							  "descuento" : RespuestaWSVO.descuento,
							  "lineaCaptura" : RespuestaWSVO.lineacaptura,
							  "importe" : RespuestaWSVO.importe,
							  "recargos" : RespuestaWSVO.recargos,
							  "total" : RespuestaWSVO.total,
							  "vigencia" : RespuestaWSVO.vigencia,
							  "placaVehiculo" : ""};
		
		lineaCapturaService.generarPDFPago(reasignacionVO).success(function(data,status,headers){
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});
			lineaCapturaService.downloadfile(file, filename);
		}).error(function(data) {
			$scope.showAviso(data.message, 'templateModalError');
		});
	}
});