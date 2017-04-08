package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;

public class GetQuestionDetailsCmd extends AbstractCmdServlet{

	public GetQuestionDetailsCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {

		String res = "";
		String qID = request.getParameter("questionID");
		int id = Integer.parseInt(qID);

		try {

			List<KlausurFrage> fList;
			
			fList = PersistenceQuery.getAllQuestions();
				
			res = "{\"successfull\":" + "false" + ",\"data\":" + ErrorMessages.getInternalError() + "}";

			for(int i = 0; i< fList.size(); i++){
				if(fList.get(i).getF_id() == id){
					res = "{\"successfull\":" + "true" + ",\"data\":" + JSONConverter.toJSONFullF(fList.get(i)) + "}";
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
