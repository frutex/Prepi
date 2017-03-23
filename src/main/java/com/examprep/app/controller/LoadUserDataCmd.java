package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.dao.KlausurFrageDAO;
import com.examprep.app.dao.NutzerDAO;
import com.examprep.app.util.CryptoHelpClass;
import com.j256.ormlite.dao.ForeignCollection;

// class to get user data
public class LoadUserDataCmd extends AbstractCmdServlet {

	public LoadUserDataCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	private CryptoHelpClass cryp = new CryptoHelpClass();

	public void execute() throws Exception {

		String token = request.getParameter("token");
		String username = token.split("\\|")[0];
		
		Nutzer nutzer = NutzerDAO.getOneNutzerByName(username);
		ForeignCollection<KlausurFrage> fragen = nutzer.getKlausurfragen();
		
		int i = 0;

	}

	
}