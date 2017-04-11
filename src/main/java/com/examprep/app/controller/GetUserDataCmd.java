package com.examprep.app.controller;

import java.time.Duration;
import java.time.Instant;
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
			Instant start = Instant.now();
			Nutzer nutzer = PersistenceQuery.getOneNutzerByName(name);
			String nutzerJSON = JSONConverter.toJSONN(nutzer);
			
			Instant end = Instant.now();
			System.out.println("Zeit für getOneNutzerByName: " + Duration.between(start, end));
			
			// ToDo: Reale Daten LAden
			// int cred = (int) (Math.random() * 100);
			
			start = end;
			

			int cred = PersistenceQuery.getAllLikesForRegisteredQuestionsForOneUser(name);
			
			end = Instant.now();
			System.out.println("Zeit für getAllLikesForRegisteredQuestionsForOneUser: " + Duration.between(start, end));
			start = end;
			
			int level = LevelCalc.calculateLevel(cred);
			
			int progress = LevelCalc.calculateProgress(cred);

			List<KlausurFrage> fragen = PersistenceQuery.getAllQuestionsFromOneUser(name);
			end = Instant.now();
			System.out.println("Zeit für Level + getAllQuestionsFromOneUser: " + Duration.between(start, end));

			start = end;
			String fRes = JSONConverter.toJSONF(fragen, nutzer);
			end = Instant.now();
			System.out.println("Zeit für Converting der Frage: " + Duration.between(start, end));
			start= end;
			String nRes = JSONConverter.toJSONN(nutzer);

			String returnData = "[" + nRes + "," + fRes + "," + "{\"Credibility\":\"" + cred + "\",\"Level\":\"" + level
					+ "\",\"Progress\":\"" + progress + "\"}]";

			res = JSONRespCreator.createWobj(true, returnData);
			end = Instant.now();
			System.out.println("Zeit für Converting: " + Duration.between(start, end));

		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}