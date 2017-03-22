package com.examprep.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCmdServlet implements CmdServletIF{

	private HttpServlet servlet;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public AbstractCmdServlet(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.servlet = servlet;
		this.response = response;

	}
	
	public void sendJsonResult(String jsonResult) throws IOException {
	    response.setContentType("application/json;charset=utf-8");
	    response.setCharacterEncoding("utf-8");
	    if (null != jsonResult) {
	      PrintWriter writer = response.getWriter();
	      writer.print(jsonResult);
	      writer.close();
	    }
	  }
	
	protected void sendJsonResult(Boolean jsonResult) throws IOException {
	    response.setContentType("application/json;charset=utf-8");
	    response.setCharacterEncoding("utf-8");
	    if (null != jsonResult) {
	      PrintWriter writer = response.getWriter();
	      writer.print(jsonResult.toString());
	      writer.close();
	    }
	  }

	
}
