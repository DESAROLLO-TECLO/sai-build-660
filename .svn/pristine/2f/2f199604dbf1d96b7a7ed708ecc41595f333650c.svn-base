angular.module('siidfApp').controller('altaArticuloController', function($scope, $location, catalogoService, ModalService, $controller) {
	
	$controller('modalController',{$scope : $scope });
	

	$scope.crudArticulo = {};
	$scope.crudArticulo.artDescuento = "S";
	$scope.altaArticulo = function(crudArticulo) {
		if($scope.frmAltaArticulo.$invalid){
			requiredFields();
		}else{	
		crudArticulo.operacion = "A";
		crudArticulo.fraccion = crudArticulo.artFraccionInt;
		if (crudArticulo.parrafoTipo != undefined && crudArticulo.parrafoTipo == "ultimo") {
			crudArticulo.artParrafo = "ÚLTIMO";
		} else if (crudArticulo.parrafoTipo != undefined && crudArticulo.parrafoTipo == "penultimo") {
			crudArticulo.artParrafo = "PENULTIMO";			
		} else if ($scope.crudArticulo.artParrafoOrd) {
			crudArticulo.artParrafo = $scope.crudArticulo.artParrafoOrd.toUpperCase(); 
		}
		if (crudArticulo.fraccion) {
			crudArticulo.artFraccion = romanize(crudArticulo.artFraccionInt);
		} else {
			crudArticulo.fraccion = 0;
			crudArticulo.artFraccion = "-----";
		}
		if (!crudArticulo.parrafo) {
			crudArticulo.parrafo = 0;
			crudArticulo.artParrafo = "-----";
		}
		if (!crudArticulo.artInciso) {
			crudArticulo.inciso = 0;
			crudArticulo.artInciso = "-----";
		} else {
			crudArticulo.inciso = crudArticulo.artInciso;
		}
		if (crudArticulo.artDescuento.toUpperCase() === "N") {
			crudArticulo.artPorcenDesc = 0;
		}
		catalogoService.enviarCrud("/articulos", crudArticulo).success(function(data) {
			$scope.crudArticulo = {};
			$scope.frmAltaArticulo.$setPristine();
			$scope.respuestaEnvioCrud = data;	
			$scope.showAviso($scope.respuestaEnvioCrud.mensaje); 
			$scope.crudArticulo.artDescuento = "S";
		}).error(function(data) {
			$scope.error = data;
			$scope.showAviso($scope.error.message);
		});
		}
	};
	
	buscarProgramas = function() {
		catalogoService.buscarProgramas().success(function(data) {
			$scope.programas = data;
		}).error(function(data) {			
			$scope.error = data;
		});
	}
	
	buscarCategorias = function() {
		catalogoService.buscarCategorias().success(function(data) {
			$scope.categorias = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	buscarCausales = function() {
		catalogoService.buscarCausales().success(function(data) {
			$scope.causales = data;
		}).error(function(data) {
			$scope.error = data;
		});
	}
	
	romanize = function(num) {
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
	
	$scope.formatParrafo = function(numero) {
		var Unidad = ["", "primero", "segundo", "tercero", "cuarto", "quinto", "sexto", "septimo", "octavo", "noveno"];
	    var Decena = ["", "decimo", "vigesimo", "trigesimo", "cuadragesimo", "quincuagesimo", "sexagesimo", "septuagesimo", "octogesimo", "nonagesimo"];
	    var Centena = ["", "centesimo", "ducentesimo", "tricentesimo", " cuadringentesimo", " quingentesimo", " sexcentesimo", " septingentesimo", " octingentesimo", " noningentesimo"];        
	    var N = parseInt(numero);
	    var u = parseInt(N % 10);
	    var d = parseInt((N / 10) % 10);
	    var c = parseInt(N / 100);
	        if (N >= 100) {
	        	$scope.crudArticulo.artParrafoOrd = Centena[c] + " " + Decena[d] + " " + Unidad[u];
	        } else {
	            if (N >= 10) {
	            	$scope.crudArticulo.artParrafoOrd = Decena[d] + " " + Unidad[u];
	            } else {
	            	$scope.crudArticulo.artParrafoOrd = Unidad[N];
	            }
	        }
	}
	
	requiredFields = function(){
		angular.forEach($scope.frmAltaArticulo.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
            $scope.showAviso("Formulario Incompleto");
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
		
		$scope.regresarMod = function(){
			$location.path("/catalogos");
		};
		
 	buscarProgramas();
	buscarCategorias();
	buscarCausales();
});