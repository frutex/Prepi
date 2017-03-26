angular.module('ExPrep').controller(
		'DashCtrl',
		[ '$http', '$scope', 'RequestFactory', 'AuthService',
				function($http, $scope, RequestFactory, AuthService) {

			
					$scope.userData;
					$scope.loadUserData = function() {
						RequestFactory.loadUserData()
						.success(
								function(data) {
									if (data.successfull) {
										$scope.userData = data.data;
									} else {
										$scope.userData = data;
										alert(data.data);
										// $scope.$apply;
									}
								});
					}
					
					

				} ]);
