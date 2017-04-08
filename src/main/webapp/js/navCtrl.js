angular.module('ExPrep').controller(
		'NavCtrl',
		[ '$http', '$scope', 'RequestFactory', 'AuthService',
				function($http, $scope, RequestFactory, AuthService) {

					$scope.logout = function() {
						AuthService.logout();
						window.location.href = "login.html";
					}

				} ]);
