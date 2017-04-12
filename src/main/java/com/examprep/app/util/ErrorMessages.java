package com.examprep.app.util;

public class ErrorMessages {
	private static String internalError = "An Internal Error Occured, please contact the HelpDesk for further assistance.";
	private static String tooManyUsersError = "Error, too many Users with this User-ID found. Please check the spelling or contact the HelpDesk for further assistance.";
	private static String authenticationError = "Authentication token is wrong. You are being logged out.";
	private static String timeoutError = "Your Session has timeouted. Please Login again.";
	private static String loginFailed = "We are sorry, your Login Attempt has failed. Please try again.";
	private static String timeTokenError = "Authentication token is wrong or has expired. You are being logged out.";
	private static String noResult = "No Results for your Search found. Please try another search!";

	public static String getTooManyUsersError() {
		return tooManyUsersError;
	}

	public static String getAuthenticationError() {
		return authenticationError;
	}

	public static String getInternalError() {
		return internalError;
	}

	public static String getTimeoutError() {
		return timeoutError;
	}

	public static String getLoginFailedError() {
		return loginFailed;
	}

	public static String getAuthenticationTimeTokenError() {
		return timeTokenError;
	}

	public static String noResultFound() {
		return noResult;
	}

}
