package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.examprep.app.bean.Nutzer;

import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.CryptoHelpClass;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONRespCreator;
import com.examprep.app.util.UserTokenMachine;

// class to get user data
public class CheckAuthTokenCmd extends AbstractCmdServlet {

	public CheckAuthTokenCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	private CryptoHelpClass cryp = new CryptoHelpClass();

	public void execute() throws Exception {

		String name = UserTokenMachine.getUserFromToken(request.getParameter("token"));
		String token = "";
		if (!name.equals(null)) {
			token = UserTokenMachine.getTokenFromToken(request.getParameter("token"));
		}
		String res = "";

		// HttpSession session = request.getSession(true);
		try {

			if (!name.equals(null)) {

				List<Nutzer> nutzerList = PersistenceQuery.getNutzerByName(name);
				if (nutzerList.size() > 1) {
					res = JSONRespCreator.createWstring(false, ErrorMessages.getTooManyUsersError());
					this.sendJsonResult(res);
				} else {
					Nutzer nutzer = nutzerList.get(0);
					String genToken = cryp.generateUserToken(nutzer);

					if (UserTokenMachine.getTokenFromToken(genToken).matches(token)) {
						if (cryp.checkDurability(request.getParameter("token"))) {
							res = JSONRespCreator.createWstring(true, token);
						} else {
							res = JSONRespCreator.createWstring(false, ErrorMessages.getTimeoutError());
						}

					} else {
						res = JSONRespCreator.createWstring(false, ErrorMessages.getAuthenticationError());

					}
				}

			} else {
				res = JSONRespCreator.createWstring(false, ErrorMessages.getAuthenticationError());

			}
		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());

			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);

		}

	}

}