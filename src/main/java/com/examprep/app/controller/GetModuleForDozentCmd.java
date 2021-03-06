package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Modul;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.persistencelayer.daoif.DozentDao;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;
import com.examprep.app.util.JSONRespCreator;

public class GetModuleForDozentCmd extends AbstractCmdServlet {

	public GetModuleForDozentCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	@Override
	public void execute() throws Exception {

		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String res = "";

		try {

			Dozent d = PersistenceQuery.getDozentByName(vorname, nachname);
			List<Modul> mList = PersistenceQuery.getAllModuleVonDozent(d);

			res = JSONRespCreator.createWobj(true, JSONConverter.toJSONM(mList));

		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}
