package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Dozent;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.JSONConverter;

public class GetDozentenForHochschuleCmd extends AbstractCmdServlet {

	public GetDozentenForHochschuleCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	@Override
	public void execute() throws Exception {

		String hochschule = request.getParameter("hochschule");
		String res = "";

		try {
			List<Dozent> dozenten = PersistenceQuery.getAllDozentenEinerHochschule(hochschule);

			int i = 0;
			res = "{\"successfull\":" + "true" + ",\"data\":" + JSONConverter.toJSOND(dozenten) + "}";

		} catch (Exception e) {
			res = "{\"successfull\":" + "false" + ",\"data\":\""
					+ "An Internal Error Occured, please contact the HelpDesk for further assistance." + "\"}";
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}

}
