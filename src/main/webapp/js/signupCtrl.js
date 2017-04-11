angular
		.module('ExPrep')
		.controller(
				'SignupCtrl',
				[
						'$http',
						'$scope',
						'RequestFactory',
						function($http, $scope, RequestFactory) {

							$scope.haserror = "has-error";

							$scope.data = {

								"Name" : "",
								"Vorname" : "",
								"Email" : "",
								"EmailWied" : "",
								"Pass" : "",
								"PassWied" : "",
							}

							$scope.checkmail = function() {
								if ($scope.data.Email.trim() == $scope.data.EmailWied
										.trim()) {
									return true;
								} else {
									return false;
								}
							}

							$scope.checkpass = function() {
								if ($scope.data.Pass.trim() == $scope.data.PassWied
										.trim()) {
									return true;
								} else {
									return false;
								}
							}

							$scope.submit = function() {

								if (!$scope.checkmail()) {
									alert("Fehler! Die angegebenen Emailadressen stimmen nicht überein.")
								} else {
									if (!$scope.checkpass()) {
										alert("Fehler! Die angegebenen Passwörter stimmen nicht überein.")
									} else {
										// Email und Passwörter stimmen
										// überein
										RequestFactory
												.createAccount($scope.data)
												.then(
														function(data) {
															if (data.data.successfull) {
																alert("Registrierung erfolgreich.")
																window.location.href = "./login.html";
															} else {
																alert(data.data.data);
															}
														});

									}
								}
							}

						} ]);
