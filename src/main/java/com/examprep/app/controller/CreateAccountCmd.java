package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.persistencelayer.daoif.HochschuleDao;
import com.examprep.app.persistencelayer.daoif.NutzerDao;
import com.examprep.app.persistencelayer.daoimpl.HochschuleDaoImpl;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;
import com.examprep.app.util.JSONRespCreator;

public class CreateAccountCmd extends AbstractCmdServlet {

	public CreateAccountCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {

		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String res = "";

		try {

			// TODO: leere Hochschule einpflegen
			Nutzer nutzer = PersistenceQuery.createNutzer(nachname, vorname, email, password, null);

			if (nutzer.getN_id() != 0) {
				res = JSONRespCreator.createWobj(true, JSONConverter.toJSONN(nutzer));

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
