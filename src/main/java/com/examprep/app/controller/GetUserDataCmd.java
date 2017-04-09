package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;
import com.examprep.app.util.JSONRespCreator;
import com.examprep.app.util.LevelCalc;
import com.examprep.app.util.UserTokenMachine;

// class to get user data
public class GetUserDataCmd extends AbstractCmdServlet {

	public GetUserDataCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {

		String name = UserTokenMachine.getUserFromToken(request.getParameter("token"));
		String res = "";

		try {
			Nutzer nutzer = PersistenceQuery.getOneNutzerByName(name);
			String nutzerJSON = JSONConverter.toJSONN(nutzer);

			// ToDo: Reale Daten LAden
			// int cred = (int) (Math.random() * 100);
			int cred = PersistenceQuery.getAllLikesForRegisteredQuestionsForOneUser(name);
			int level = LevelCalc.calculateLevel(cred);

			List<KlausurFrage> fragen = PersistenceQuery.getAllQuestionsFromOneUser(name);

			String fRes = JSONConverter.toJSONF(fragen, nutzer);
			String nRes = JSONConverter.toJSONN(nutzer);

			String returnData = "[" + nRes + "," + fRes + "," + "{\"Credibility\":\"" + cred + "\",\"Level\":\"" + level
					+ "\"}]";

			res = JSONRespCreator.createWobj(false, returnData);

		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}