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
import com.examprep.app.util.UserTokenMachine;

public class GetQuestionDetailsCmd extends AbstractCmdServlet{

	public GetQuestionDetailsCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {

		String res = "";
		String qID = request.getParameter("questionID");
		
		String name = UserTokenMachine.getUserFromToken(request.getParameter("token"));
		

		try {
			
			Nutzer nutzer = PersistenceQuery.getOneNutzerByName(name);
			int id = Integer.parseInt(qID);

			List<KlausurFrage> fList;
			
			fList = PersistenceQuery.getAllQuestions();
				
			res = "{\"successfull\":" + "false" + ",\"data\":" + ErrorMessages.getInternalError() + "}";

			for(int i = 0; i< fList.size(); i++){
				if(fList.get(i).getF_id() == id){
					res = "{\"successfull\":" + "true" + ",\"data\":" + JSONConverter.toJSONFullF(fList.get(i), nutzer) + "}";
				}
			}
			
		} catch (Exception e) {
			res = "{\"successfull\":" + "false" + ",\"data\":\""
					+ ErrorMessages.getInternalError() + "\"}";
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}
