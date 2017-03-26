angular.module('ExPrep').controller(
		'AddQuestionCtrl',
		[ '$http', '$scope', 'RequestFactory', 'AuthService',
				function($http, $scope, RequestFactory, AuthService) {

					$scope.hochschulen;
					$scope.dozenten;
					$scope.startup = function(){
						RequestFactory.getHochschulen()
						.success(
								function(data) {
									if (data.successfull) {
										$scope.hochschulen = data.data;
									} else {
										alert(data.data);
										// $scope.$apply;
									}
								});
					}
					
					$scope.addQuestion = function(){
						window.location.href = "./addQuestion.html";
					}

				} ]);
