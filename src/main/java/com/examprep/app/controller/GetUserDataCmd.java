package com.examprep.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.NutzerServletMapper;
import com.examprep.app.util.JSONConverter;

// class to get user data
public class GetUserDataCmd extends AbstractCmdServlet {

	public GetUserDataCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {

		String token = request.getParameter("token");
		String name = token.split("\\|")[0];
		String res = "";

		try {
			Nutzer nutzer = NutzerServletMapper.getOneNutzerByName(name);
			String nutzerJSON = JSONConverter.toJSON(nutzer);

			res = "{\"successfull\":" + "true" + ",\"data\":" + nutzerJSON + "}";

		} catch (Exception e) {
			res = "{\"successfull\":" + "false" + ",\"data\":\"" + "An Internal Error Occured, please contact the HelpDesk for further assistance." + "\"}";
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}