package com.examprep.app.controller;

import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;

public class AddFrageCmd extends AbstractCmdServlet{

	public AddFrageCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws Exception {
		
		String nutzer = request.getParameter("token").split("\\|")[0];
		String titel = request.getParameter("titel");
		String hochschule = request.getParameter("hochschule");
		String dozent = request.getParameter("dozent");
		String modul = request.getParameter("modul");
		String beschreibung = request.getParameter("beschreibung");
		String datum = request.getParameter("datum");
		
		//Date da = new Date(Integer.parseInt(datum.split(".")[2]), datum.split(".")[1], datum.split(".")[0])
		@SuppressWarnings("deprecation")
		Date dat = new Date(datum);
	
		Nutzer n = PersistenceQuery.getNutzerByName(nutzer).get(0);
	//	Dozent d = PersistenceQuery.get
		Hochschule h = PersistenceQuery.searchForHochschulenByName(hochschule).get(0);
		Modul m = PersistenceQuery.searchModulByName(modul).get(0);
		
		//PersistenceQuery.createKlausurfrage( beschreibung, dat.getYear(), hochschule, dozent, modul, nutzer)
		
		
	}
	
	
}
