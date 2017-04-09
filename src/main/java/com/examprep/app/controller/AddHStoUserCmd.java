package com.examprep.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;
import com.examprep.app.util.JSONRespCreator;
import com.examprep.app.util.UserTokenMachine;

public class AddHStoUserCmd extends AbstractCmdServlet {

	public AddHStoUserCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws Exception {

		String name = UserTokenMachine.getUserFromToken(request.getParameter("token"));

		String hs = request.getParameter("hochschule");
		String res = "";

		try {

			Nutzer nutzer = PersistenceQuery.getOneNutzerByName(name);

			Hochschule h = PersistenceQuery.getHochschuleByName(hs).get(0);

			nutzer.setHochschule(h);

			Nutzer nuNew = PersistenceQuery.update(nutzer);

			res = JSONRespCreator.createWobj(true, JSONConverter.toJSONN(nuNew));

			// PersistenceQuery.
		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}
	}

}
