angular.module('ExPrep').factory(
		'RequestFactory',
		[
				'$http',
				'$filter',
				'AuthService',
				function($http, $filter, AuthService) {
					function send(url) {
						return $http.post("/prepa/cmd?" + url + '&token=' + AuthService.getUserToken()).success(
								function(data) {
									return data;
								})
					}

					function login(username, password) {
						return send("c=login&username=" + username
								+ "&password=" + password);

					}
					
					function checkAuthToken() {
						return send("c=checkAuthToken");

					}
					
					function loadUserData() {
						return send("c=loadUserData");

					}

					return {
						send : send,
						login : login,
						checkAuthToken : checkAuthToken,
						loadUserData : loadUserData
					};
				} ]);
