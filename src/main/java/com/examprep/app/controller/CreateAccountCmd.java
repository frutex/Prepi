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
import com.examprep.app.util.JSONConverter;

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
			Nutzer nutzer = PersistenceQuery.createNutzer(nachname, vorname, email, password,
					PersistenceQuery.getAllHochschulen().get(0));

			if (nutzer.getN_id() != 0) {
				res = "{\"successfull\":" + "true" + ",\"data\":" + JSONConverter.toJSONN(nutzer) + "}";
			} else {

				res = "{\"successfull\":" + "false" + ",\"data\":"
						+ "An Unexpected Error Occured, please contact the Help." + "}";
			}

		} catch (Exception e) {
			res = "{\"successfull\":" + "false" + ",\"data\":\""
					+ "An Internal Error Occured, please contact the HelpDesk for further assistance." + "\"}";
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}
