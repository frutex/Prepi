package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Nutzer;
import com.examprep.app.dao.NutzerDAO;
import com.examprep.app.util.CryptoHelpClass;

// class to get user data
public class LoginCmd extends AbstractCmdServlet {

	public LoginCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	private CryptoHelpClass cryp = new CryptoHelpClass();

	public void execute() throws Exception {

		String name = request.getParameter("username");
		String password = request.getParameter("password");

		String res = "";

		String token = "";

		// HttpSession session = request.getSession(true);
		try {

			List<Nutzer> nutzerList = NutzerDAO.getNutzerByName(name);
			if (nutzerList.size() > 1) {
				res = "{\"successfull\":false,\"token\":\"Too many users found, please contact the HelpDesk.\"}";
				this.sendJsonResult(res);
			} else {
				Nutzer nutzer = nutzerList.get(0);
				password = cryp.generateHashPassword(name, password);
				Boolean successfull = nutzer.getPassword().matches(password);
				if (successfull) {

					res = "{\"successfull\":" + successfull + ",\"token\":\"" + cryp.generateUserToken(nutzer, cryp.token) + "\"}";

				} else {
					res = "{\"successfull\":" + successfull + ",\"token\":\"Login Attempt failed. Please try again.\"}";

				}
				this.sendJsonResult(res);
			}
		} catch (Exception e) {
			this.sendJsonResult("{\"successfull\":false,\"token\":\"Internal Error, please contact the HelpDesk.\"}");
			e.printStackTrace();
		}

	}

	
}