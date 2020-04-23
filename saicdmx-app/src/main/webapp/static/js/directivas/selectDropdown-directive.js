angular.module('siidfApp').directive('dropdown', function ($rootScope, $timeout) {
	
	return {
		restrict: "E",
		templateUrl: "views/templatedirectivas/dropdownSelect.html",
		scope: {
			placeholder: "@",
			list: "=",
			selected: "=",
			property: "@",
			isChangeStyle : "@"
		},
		link: function(scope, element, attrs) {
			
			scope.listVisible = false;
			scope.isPlaceholder = true;

			scope.select = function(item) {
				
				scope.isPlaceholder = false;
				scope.selected = item;
				
				if (scope.onChange !== undefined){
					
					scope.onChange(item);
					
				}
				
				scope.changeStyle();
				
			};
			
			scope.changeStyle = function() {
				//	Solo se aplica si en el html se agrega la directiva dropdown con la propiedad is-change-style
				 
				
				if(scope.isChangeStyle) {
					
					var html = $('html');
					var fsDefault = $('html').css('font-size');
					var cdEstilo;
					
					if(scope.selected != undefined) {

						cdEstilo = scope.selected.cdResolucion;
						
						switch(cdEstilo) {
						
							case 'GR' :
								html.css('font-size', '15px');
								$.AdminLTE.layout.fix();
								break;
								
							case 'NM' :
								html.css('font-size', '13px');
								$.AdminLTE.layout.fix();
								break;
								
							case 'PQ' :
								html.css('font-size', '11px');
								$.AdminLTE.layout.fix();
								break;
							
							default :
								html.css('font-size', fsDefault);
								$.AdminLTE.layout.fix();
						}
						
						return;
					
					} else {
					
						scope.selected = {};
//						$timeout(scope.setSelected, 10000);
						
					}
				}
			}
			
//			scope.setSelected = function(){
//				
//				scope.selected = $rootScope.configuracionApp.resolucion;
//			
//			}
			
			scope.isSelected = function(item) {
				
				return item[scope.property] === scope.selected[scope.property];
				
			};
			
			scope.show = function() {
				
				scope.listVisible = true;
				
			};

			$rootScope.$on("documentClicked", function(inner, target) {
				
				var parent = angular.element(target.parent()[0]);
				
				if(parent.hasClass("dropdown-display")){
					
					if (!parent.hasClass("clicked")) {
						
						parent.addClass("clicked");
						
					}
					
				}else{
					
					angular.element(".dropdown-display").removeClass("clicked");
					
					scope.$apply(function () {
						
						scope.listVisible = false;
						
					});
				}
			});
			
			scope.$watch("selected", function(value) {
				
				scope.isPlaceholder = scope.selected[scope.property] === undefined;
				scope.display = scope.selected[scope.property];
				
			});
			
			scope.changeStyle();
			
		}
	}
});