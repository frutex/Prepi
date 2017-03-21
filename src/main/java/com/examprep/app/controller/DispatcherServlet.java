package com.examprep.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		CmdServletIF cmd = getCommand(this, request, response);

		try {

			// LOG.debug("DispatcherServlet execute:" + cmd);
			if (null != cmd) {
				cmd.execute();
			}

		} catch (Exception e) {

			e.printStackTrace();
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
		}  else if (command.equalsIgnoreCase("checkAuthToken")) {
			result = new CheckAuthTokenCmd(servlet, request, response);
		}  
		return result;
	}
}