angular.module('ExPrep').controller(
		'DashCtrl',
		[
				'$http',
				'$scope',
				'RequestFactory',
				'AuthService',
				function($http, $scope, RequestFactory, AuthService) {

					$scope.startup = function() {
						$scope.checkAuth();
						$scope.loadUserData();
					}

					$scope.checkAuth = function() {
						token = AuthService.getUserToken();
						if (AuthService.isAuthenticated && token) {

							RequestFactory.checkAuthToken().success(
									function(data) {
										if (data.successfull) {

										} else {
											alert(data.token);
											// $scope.$apply;
										}
									});

						} else {
							var loc = window.location.pathname;
							var dir = loc.substring(loc.lastIndexOf('/'),
									loc.length);
							if (dir != "/login.html") {
								if (dir != "/") {
									window.location.href = "./login.html";
								}
							}
						}
					}

					$scope.userData;
					$scope.loadUserData = function() {
						RequestFactory.loadUserData().success(function(data) {
							if (data.successfull) {
								$scope.userData = data.data;
							} else {
								$scope.userData = data;
								alert(data.data);
								// $scope.$apply;
							}
						});
					}

				
				
				} ]
		
);
