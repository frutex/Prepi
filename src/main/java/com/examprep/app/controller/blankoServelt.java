package com.examprep.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class blankoServelt extends AbstractCmdServlet  {

	public blankoServelt(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {
		
		

		HttpSession session = request.getSession(true);
		try {
			
			
			this.sendJsonResult("");
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
	}
}