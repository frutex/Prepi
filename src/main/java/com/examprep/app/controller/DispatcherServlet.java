package com.examprep.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.util.AuthService;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthService authenticator = new AuthService();

	@Override
	protected void doPost(HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("c").equalsIgnoreCase("login")) {
			try {
				LoginCmd cmd = new LoginCmd(this, request, response);
				// LOG.debug("DispatcherServlet execute:" + cmd);
				if (null != cmd) {
					cmd.execute();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		} else if (request.getParameter("c").equalsIgnoreCase("createAccount")) {
			CmdServletIF cmd = getCommand(this, request, response);

			try {

				// LOG.debug("DispatcherServlet execute:" + cmd);
				if (null != cmd) {
					cmd.execute();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		} else if (request.getParameter("token") != null) {
			if (authenticator.checkAuth(request.getParameter("token"))) {
				CmdServletIF cmd = getCommand(this, request, response);

				try {

					// LOG.debug("DispatcherServlet execute:" + cmd);
					if (null != cmd) {
						cmd.execute();
					}

				} catch (Exception e) {

					e.printStackTrace();
				}
			} else {
				CmdServletIF cmd = getCommand(this, request, response);
				cmd.sendJsonResult(
						"{\"successfull\":false,\"token\":\"Authentication token is wrong. You are being logged out. \"}");
			}
		}

		// response.sendRedirect(result);
	}

	@Override
	protected void doGet(HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public static CmdServletIF getCommand(HttpServlet servlet, HttpServletRequest request,
			HttpServletResponse response) {
		CmdServletIF result = null;

		String command = request.getParameter("c");
		System.out.println(command);

		final String query = request.getQueryString();

		if (command.equalsIgnoreCase("login")) {
			result = new LoginCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("checkAuthToken")) {
			result = new CheckAuthTokenCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("loadUserData")) {
			result = new GetUserDataCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("getHochschulen")) {
			result = new GetHochschulenCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("getModule")) {
			result = new GetModuleCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("getDozenten")) {
			result = new GetDozentenCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("addFrage")) {
			result = new AddFrageCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("getDozentenForHochschule")) {
			result = new GetDozentenForHochschuleCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("getModulForDozent")) {
			result = new GetModuleForDozentCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("createAccount")) {
			result = new CreateAccountCmd(servlet, request, response);
		}else if (command.equalsIgnoreCase("loadQuestionDetails")) {
			result = new GetQuestionDetailsCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("doQuestionLike")) {
			result = new DoLikeCmd(servlet, request, response);
		} else if (command.equalsIgnoreCase("doSearch")) {
			result = new DoSearchCmd(servlet, request, response);
		} 
		return result;
	}
}