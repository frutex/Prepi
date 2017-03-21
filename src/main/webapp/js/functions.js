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

angular.module('ExPrep').controller(
		'ScriptCtrl',
		[
				'$http',
				'$scope',
				'RequestFactory',
				'AuthService',
				function($http, $scope, RequestFactory, AuthService) {

					$scope.authenticated = false;
					$scope.checkAuth = function() {
						token = AuthService.getUserToken();
						if (AuthService.isAuthenticated && token) {
							$scope.authenticated = true;
							
							
							
							RequestFactory.checkAuthToken($scope.loginData.username,
									token).success(
									function(data) {
										if (data.successfull) {
											//alert($scope.loginData.username);
											//Nothing needs to be done here, the user is authenticated
										} else {
											$scope.authenticated = false;
											alert(data.token);
											//$scope.$apply;
										}
									});
				
						}
					}

					$scope.loginData = {
						username : "",
						password : ""
					}

					$scope.login = function() {
						RequestFactory.login($scope.loginData.username,
								$scope.loginData.password).success(
								function(data) {
									if (data.successfull) {
										AuthService.login(data);
										$scope.checkAuth();
										//$scope.$apply();
									} else {
										alert(data.token);
									}
								})
					}

					$scope.logout = function() {
						AuthService.logout();
						$scope.authenticated = false;
						//$scope.$apply();
					}

				} ]);
