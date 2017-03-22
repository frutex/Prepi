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
					
					$scope.showDiv = "dashboard";
					$scope.isShown = function(div){
						if(div == $scope.showDiv){
							return true;
						} else {
							return false;
						}
					}

				} ]);
