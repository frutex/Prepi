package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;
import com.examprep.app.util.JSONRespCreator;
import com.examprep.app.util.UserTokenMachine;

public class GetAllQuestionsCmd extends AbstractCmdServlet {

	public GetAllQuestionsCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {

		String res = "";
		String name = UserTokenMachine.getUserFromToken(request.getParameter("token"));

		try {

			Nutzer nutzer = PersistenceQuery.getOneNutzerByName(name);

			List<KlausurFrage> fList = PersistenceQuery.getAllQuestions();

			res = JSONRespCreator.createWobj(true, JSONConverter.toJSONF(fList, nutzer));

		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}
