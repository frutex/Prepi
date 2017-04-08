// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('ExPrep', [ 'ui.router', 'ui.bootstrap', 'ngMaterial'])
		.constant('AUTH_EVENTS', {
			notAuthenticated : 'auth-not-authenticated',
			notAuthorized : 'auth-not-authorized'
		})

		.constant('USER_ROLES', {
			admin : 'admin_role',
			public : 'public_role'
		})

		/*
		.config([ '$locationProvider', function($locationProvider) {
			$locationProvider.html5Mode({
				enabled : true,
				requireBase : false
			});
		} ])
		*/
		
		.run()