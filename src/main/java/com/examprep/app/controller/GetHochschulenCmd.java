package com.examprep.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Nutzer;
import com.examprep.app.dao.HochschuleDAO;
import com.examprep.app.dao.NutzerDAO;
import com.examprep.app.util.JSONConverter;

public class GetHochschulenCmd extends AbstractCmdServlet{


	public GetHochschulenCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {

		
		String res = "";

		try {
			
		
			res = "{\"successfull\":" + "true" + ",\"data\":" + "TMP" + "}";

		} catch (Exception e) {
			res = "{\"successfull\":" + "false" + ",\"data\":\"" + "An Internal Error Occured, please contact the HelpDesk for further assistance." + "\"}";
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}
