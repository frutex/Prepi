package com.examprep.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.CryptoHelpClass;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;
import com.examprep.app.util.JSONRespCreator;

public class ModifyAccountCmd extends AbstractCmdServlet {

	public ModifyAccountCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws Exception {
		String res = "";
		try {

			String vorname = request.getParameter("vorname");
			String nachname = request.getParameter("nachname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String hochschule = request.getParameter("hochschule");

			// Get the old account:
			String crypPassword = "";
			if (password != "" && password != "null" && password != null) {
				CryptoHelpClass cryp = new CryptoHelpClass();
				crypPassword = cryp.generateHashPassword(email, password);
			}

			Nutzer nu = PersistenceQuery.getOneNutzerByName(email);

			nu.setVorname(vorname);
			nu.setName(nachname);
			nu.setEmail(email);
			if (crypPassword != "") {
				nu.setPassword(password);
			}

			// Get Hochschule
			Hochschule hs = PersistenceQuery.getHochschuleByName(hochschule).get(0);

			nu.setHochschule(hs);

			Nutzer newNu = PersistenceQuery.update(nu);

			res = JSONRespCreator.createWobj(true, JSONConverter.toJSONN(newNu));

		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());
		} finally {
			this.sendJsonResult(res);
		}
	}

}
