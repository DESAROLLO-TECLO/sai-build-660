angular.module("siidfApp").config(function ($httpProvider) {
	$httpProvider.interceptors.push("requestInterceptor");
	$httpProvider.interceptors.push("errorInterceptor");
});