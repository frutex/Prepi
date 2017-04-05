package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.JSONConverter;
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
			int cred = (int) (Math.random() * 200);
			int level = LevelCalc.calculateLevel(cred);

			List<KlausurFrage> fragen = PersistenceQuery.getAllQuestions();

			String fRes = JSONConverter.toJSONF(fragen);
			String nRes = JSONConverter.toJSONN(nutzer);

			String returnData = "[" + nRes + "," + fRes + "," + "{\"Credibility\":\"" + cred + "\",\"Level\":\"" + level
					+ "\"}]";

			res = "{\"successfull\":" + "true" + ",\"data\":" + returnData + "}";

		} catch (Exception e) {
			res = "{\"successfull\":" + "false" + ",\"data\":\""
					+ "An Internal Error Occured, please contact the HelpDesk for further assistance." + "\"}";
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}