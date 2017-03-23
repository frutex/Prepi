angular.module('ExPrep').controller(
		'DashCtrl',
		[ '$http', '$scope', 'RequestFactory', 'AuthService',
				function($http, $scope, RequestFactory, AuthService) {

			
					$scope.userData;
					$scope.loadUserData = function() {
						token = AuthService.getUserToken();
						
						RequestFactory.loadUserData()
						.success(
								function(data) {
									if (data.successfull) {
										
									} else {
										$scope.userData = data;
										alert(data.message);
										// $scope.$apply;
									}
								});
					}

				} ]);
