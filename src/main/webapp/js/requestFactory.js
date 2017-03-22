angular.module('ExPrep').factory(
		'RequestFactory',
		[
				'$http',
				'$filter',
				function($http, $filter) {
					function send(url) {
						return $http.post("/prepa/cmd?" + url).success(
								function(data) {
									return data;
								})
					}

					function login(username, password) {
						return send("c=login&username=" + username
								+ "&password=" + password);

					}
					
					function checkAuthToken(username, token) {
						return send("c=checkAuthToken&username=" + username
								+ "&token=" + token);

					}

					return {
						send : send,
						login : login,
						checkAuthToken : checkAuthToken
					};
				} ]);
