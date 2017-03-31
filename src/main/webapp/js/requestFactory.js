angular.module('ExPrep').factory(
		'RequestFactory',
		[
				'$http',
				'$filter',
				'AuthService',
				function($http, $filter, AuthService) {
					function send(url) {
						return $http.post(
								"/prepa/cmd?" + url + '&token='
										+ AuthService.getUserToken()).then(
								function(data) {
									return data;
								})
					}

					function login(username, password) {
						return send("c=login&username=" + username
								+ "&password=" + password);

					}

					function getHochschulen() {
						return send("c=getHochschulen");

					}

					function checkAuthToken() {
						return send("c=checkAuthToken");

					}

					function loadUserData() {
						return send("c=loadUserData");

					}

					function getModule() {
						return send("c=getModule");

					}

					function getDozenten() {
						return send("c=getDozenten");

					}

					function addFrage(data) {
						return send("c=addFrage" + "&titel=" + data.Titel
								+ "&hochschule=" + data.Hochschule.Name
								+ "&modul=" + data.Modul.Name
								+ "&dozentVorname=" + data.Dozent.Vorname
								+ "&dozentNachname=" + data.Dozent.Nachname
								+ "&beschreibung=" + data.Beschreibung
								+ "&datum=" + data.Datum);

					}

					function getDozentenForHochschule(hochschule) {
						return send("c=getDozentenForHochschule"
								+ "&hochschule=" + hochschule);

					}

					function getModulForDozent(dozent) {
						return send("c=getModulForDozent" + "&vorname="
								+ dozent.Vorname + "&nachname="
								+ dozent.Nachname);

					}

					return {
						send : send,
						login : login,
						checkAuthToken : checkAuthToken,
						loadUserData : loadUserData,
						getHochschulen : getHochschulen,
						getModule : getModule,
						getDozenten : getDozenten,
						addFrage : addFrage,
						getDozentenForHochschule : getDozentenForHochschule,
						getModulForDozent : getModulForDozent,

					};
				} ]);
