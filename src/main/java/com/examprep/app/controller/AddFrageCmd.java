package com.examprep.app.controller;

import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.UserTokenMachine;

public class AddFrageCmd extends AbstractCmdServlet {

	public AddFrageCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute() throws Exception {

		String nutzer = UserTokenMachine.getUserFromToken(request.getParameter("token"));
		String titel = request.getParameter("titel");
		String hochschule = request.getParameter("hochschule");
		String dVorname = request.getParameter("dozentVorname");
		String dNachname = request.getParameter("dozentNachname");
		String modul = request.getParameter("modul");
		String beschreibung = request.getParameter("beschreibung");
		String datum = request.getParameter("datum");


		// Date da = new Date(Integer.parseInt(datum.split(".")[2]),
		// datum.split(".")[1], datum.split(".")[0])
		@SuppressWarnings("deprecation")
		//Date dat = new Date(datum);

		Nutzer n = PersistenceQuery.getNutzerByName(nutzer).get(0);
		Dozent d = PersistenceQuery.getDozentByName(dVorname, dNachname);
		Hochschule h = PersistenceQuery.getHochschuleByName(hochschule).get(0);
		Modul m = PersistenceQuery.getModulByName(modul).get(0);


		KlausurFrage frage = PersistenceQuery.createKlausurfrage(beschreibung, Integer.parseInt(datum), titel, h, d, m, n);

		try {
			if (frage != null) {
				this.sendJsonResult(true);
			}

		} catch (Exception e) {
			this.sendJsonResult(false);
		}

	}

}
