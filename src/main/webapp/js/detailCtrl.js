angular
		.module('ExPrep')
		.controller(
				'DetailCtrl',
				[
						'$http',
						'$scope',
						'RequestFactory',
						'AuthService',
						function($http, $scope, RequestFactory, AuthService) {

							$scope.startup = function() {
								$scope.checkAuth();
								$scope.loadQuestionDetails();
							}

							$scope.checkAuth = function() {
								token = AuthService.getUserToken();
								if (AuthService.isAuthenticated && token) {

									RequestFactory
											.checkAuthToken()
											.then(
													function(data) {
														if (data.data.successfull) {

														} else {
															alert(data.data.token);
															AuthService
																	.logout();
															window.location.href = "./login.html";
														}
													});

								} else {
									var loc = window.location.pathname;
									var dir = loc.substring(loc
											.lastIndexOf('/'), loc.length);
									if (dir != "/login.html") {
										if (dir != "/") {
											window.location.href = "./login.html";
										}
									}
								}
							}

							$scope.questionDetails;

							$scope.loadQuestionDetails = function() {
								var loc = window.location.search;
								var param = loc.substring(
										loc.lastIndexOf("=") + 1, loc.length);

								RequestFactory
										.loadQuestionDetails(param)
										.then(
												function(data) {
													if (data.data.successfull) {
														$scope.questionDetails = data.data.data;
													} else {
														alert(data.data.data);

													}
												});
							}
							
							$scope.doQuestionLike = function() {
								
								RequestFactory
										.doQuestionLike($scope.questionDetails[0].Fragendetails.FragenID)
										.then(
												function(data) {
													if (data.data.successfull) {
														$scope.loadQuestionDetails();
													} else {
														alert(data.data.data);

													}
												});
							}

						} ]);
