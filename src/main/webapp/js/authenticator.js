angular.module('ExPrep')

.service('AuthService', function($q, $http, USER_ROLES) {
	var LOCAL_TOKEN_KEY = 'SecretExPrepToken';
	var username = '';
	var isAuthenticated = false;
	var role = '';
	var authToken;

	function loadUserCredentials() {
		var token = window.localStorage.getItem(LOCAL_TOKEN_KEY);
		if (token) {
			useCredentials(token);
		}
		return token;
	}

	function getUserToken() {
		return window.localStorage.getItem(LOCAL_TOKEN_KEY);
	}

	function storeUserCredentials(token) {
		window.localStorage.setItem(LOCAL_TOKEN_KEY, token);
		useCredentials(token);
	}

	function useCredentials(token) {
		username = token.split('|')[0];
		isAuthenticated = true;
		authToken = token;

		if (username == 'admin') {
			role = USER_ROLES.admin
		}
		if (username == 'user') {
			role = USER_ROLES.public
		}

		// Set the token as header for your requests!
		$http.defaults.headers.common['X-Auth-Token'] = token;
	}

	function destroyUserCredentials() {
		window.localStorage.removeItem(LOCAL_TOKEN_KEY);
		authToken = undefined;
		username = '';
		isAuthenticated = false;
		$http.defaults.headers.common['X-Auth-Token'] = undefined;
		
	}

	var login = function(res) {
		return $q(function(resolve, reject) {
			if (res.successfull) {
				storeUserCredentials(name + res.data);
				resolve('Login success.');
			} else {
				reject('Login Failed.');
			}
		});
	};

	var logout = function() {
		destroyUserCredentials();
	};

	var isAuthorized = function(authorizedRoles) {
		if (!angular.isArray(authorizedRoles)) {
			authorizedRoles = [ authorizedRoles ];
		}
		return (isAuthenticated && authorizedRoles.indexOf(role) !== -1);
	};

	loadUserCredentials();

	return {
		getUserToken : getUserToken,
		login : login,
		logout : logout,
		isAuthorized : isAuthorized,
		isAuthenticated : function() {
			return isAuthenticated;
		},
		username : function() {
			return username;
		},
		role : function() {
			return role;
		}
	};
})


.factory('AuthInterceptor', function ($rootScope, $q, AUTH_EVENTS) {
  return {
    responseError: function (response) {
      $rootScope.$broadcast({
        401: AUTH_EVENTS.notAuthenticated,
        403: AUTH_EVENTS.notAuthorized
      }[response.status], response);
      return $q.reject(response);
    }
  };
})

.config(function ($httpProvider) {
  $httpProvider.interceptors.push('AuthInterceptor');
});
