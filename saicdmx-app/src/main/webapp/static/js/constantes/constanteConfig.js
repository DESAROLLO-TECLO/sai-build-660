angular.module('siidfApp').constant("constante", {
	urlWs : "/sspcdmxsai_v652r1_pro_api",
	appletClass : 'application.App',
});

angular.module('siidfApp')
	.factory('config', [ '$http', '$location', 'constante', '$rootScope', 
	                     function($http, $location, constante, $rootScope) {

	var protocol = $location.protocol() + "://";
	var host = location.host;
	var url = protocol + host + constante.urlWs;
	var absUrl = $location.absUrl();
	
	let contextApp = absUrl.split("/")[3];
	
	$rootScope.applet_route = url;
	$rootScope.codebase = constante.appletClass;

	return {
		baseUrl : url,
		absUrl : absUrl,
		contextApp:contextApp
	}
} ]);