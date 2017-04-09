package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.CryptoHelpClass;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONRespCreator;

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

			List<Nutzer> nutzerList = PersistenceQuery.getNutzerByName(name);
			if (nutzerList.size() > 1) {
				res = JSONRespCreator.createWstring(false, ErrorMessages.getTooManyUsersError());
				this.sendJsonResult(res);
			} else {
				Nutzer nutzer = nutzerList.get(0);
				password = cryp.generateHashPassword(name, password);
				Boolean successfull = nutzer.getPassword().matches(password);
				if (successfull) {
					res = JSONRespCreator.createWstring(successfull, cryp.generateUserToken(nutzer));

				} else {
					res = JSONRespCreator.createWstring(false, ErrorMessages.getLoginFailedError());

				}
				this.sendJsonResult(res);
			}
		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());
			e.printStackTrace();
		}

	}

}