angular.module('ExPrep').controller(
		'NavCtrl',
		[ '$http', '$scope', 'RequestFactory', 'AuthService', 'ProgService',
				function($http, $scope, RequestFactory, AuthService, ProgService) {

					$scope.logout = function() {
						AuthService.logout();
						window.location.href = "login.html";
					}

					$scope.searchText = "";

					$scope.newSearch = function() {
						url = "explore.html?keywords=" + $scope.searchText;
						window.location.href = url;
					}
					
					$scope.progActive = function(){
						return ProgService.is();
					}

				} ]);
