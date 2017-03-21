package com.examprep.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// class to get user data
public class getUserDataCmd extends AbstractCmdServlet  {

	public getUserDataCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {
		
		
		String name = request.getParameter("name");
		String vorName = request.getParameter("vorname");;
		String telnr = request.getParameter("telnr");
		String adresse = request.getParameter("adresse");
		String firma = request.getParameter("unternehmen");

		HttpSession session = request.getSession(true);
		try {
			
			String res = "";
			this.sendJsonResult(res);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
	}
}