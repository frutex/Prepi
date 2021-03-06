package com.examprep.app.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Credibility;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;
import com.examprep.app.util.JSONRespCreator;
import com.examprep.app.util.UserTokenMachine;

public class DoLikeCmd extends AbstractCmdServlet {

	public DoLikeCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	@Override
	public void execute() throws Exception {

		String fID = request.getParameter("questionID");
		String nutzer = UserTokenMachine.getUserFromToken(request.getParameter("token"));

		String res = "";

		try {
			Credibility cred = null;
			cred = PersistenceQuery.createCredibility(PersistenceQuery.getFrageById(fID),
					PersistenceQuery.getNutzerByName(nutzer).get(0));

			if (cred != null) {
				res = JSONRespCreator.createWobj(true, JSONConverter.toJSONL(cred));
			} else {
				res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());
			}
		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}

}
