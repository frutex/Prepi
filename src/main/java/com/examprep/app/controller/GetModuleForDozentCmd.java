package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Modul;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.JSONConverter;

public class GetModuleForDozentCmd extends AbstractCmdServlet {

	public GetModuleForDozentCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	@Override
	public void execute() throws Exception {

		String dozent = request.getParameter("dozent");
		String res = "";

		try {

			List<Modul> mList = PersistenceQuery.getAllModule();

			res = "{\"successfull\":" + "true" + ",\"data\":" + JSONConverter.toJSONM(mList) + "}";

		} catch (Exception e) {
			res = "{\"successfull\":" + "false" + ",\"data\":\""
					+ "An Internal Error Occured, please contact the HelpDesk for further assistance." + "\"}";
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}
