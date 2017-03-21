package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.examprep.app.bean.Nutzer;
import com.examprep.app.dao.NutzerDAO;
import com.examprep.app.util.CryptoHelpClass;

// class to get user data
public class CheckAuthTokenCmd extends AbstractCmdServlet {

	public CheckAuthTokenCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	private CryptoHelpClass cryp = new CryptoHelpClass();

	public void execute() throws Exception {

		String name = request.getParameter("token").split("\\|")[0];
		String token = "";
		if (!name.equals(null)) {
			token = request.getParameter("token").split("\\|")[1];
		}
		String res = "";

		// HttpSession session = request.getSession(true);
		try {

			if (!name.equals(null)) {

				List<Nutzer> nutzerList = NutzerDAO.getNutzerByName(name);
				if (nutzerList.size() > 1) {
					res = "{\"successfull\":false,\"response\":\"Too many users with your Email found, please contact the HelpDesk.\"}";
					this.sendJsonResult(res);
				} else {
					Nutzer nutzer = nutzerList.get(0);
					String genToken = cryp.generateUserToken(nutzer, cryp.token);
					
					if (genToken.split("\\|")[1].matches(token)) {
						res = "{\"successfull\":true,\"token\":\"" + token + "\"}";
					} else {
						res = "{\"successfull\":false,\"token\":\"Authentication token is wrong. You are being logged out. \"}";

					}
				}

			} else {
				//
				int i = 0;
				res = "{\"successfull\":false,\"token\":\"Authentication token is wrong. You are being logged out. \"}";

			}
		} catch (Exception e) {
			res = "{\"successfull\":false,\"token\":\"Internal Error trying to check the Authentication Token, please contact the HelpDesk.\"}";

			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);

		}

	}

}