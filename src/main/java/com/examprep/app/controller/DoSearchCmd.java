package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.KlausurFrage;
import com.examprep.app.bean.Modul;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;
import com.examprep.app.util.JSONRespCreator;
import com.examprep.app.util.LevelCalc;

public class DoSearchCmd extends AbstractCmdServlet {

	public DoSearchCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	@Override
	public void execute() throws Exception {

		String dname = "";
		dname = request.getParameter("dozent");
		String mname = "";
		mname = request.getParameter("modul");
		String hs = "";
		hs = request.getParameter("hochschule");
		String keywords = request.getParameter("keywords");

		String res = "";
		try {

			Dozent d = null;
			Modul m = null;
			Hochschule h = null;

			if (isFilled(dname)) {
				d = PersistenceQuery.getDozentByName(dname.split(",")[1].trim(), dname.split(",")[0].trim());
			}

			if (isFilled(mname)) {
				m = PersistenceQuery.getModulByName(mname).get(0);
			}

			if (isFilled(hs)) {
				h = PersistenceQuery.getHochschuleByName(hs).get(0);
			}

			List<KlausurFrage> kList = PersistenceQuery.getKlausurFrage(d, m, h, keywords);

			if(kList.size() != 0){
				res = JSONRespCreator.createWobj(true, JSONConverter.toJSONF(kList));
			} else {
				res = JSONRespCreator.createWobj(false, JSONConverter.toJSONF(kList));
			}
			

		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.noResultFound());
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}

	private boolean isFilled(String input) {
		if (!(input.equalsIgnoreCase("") || input.equalsIgnoreCase("null") || input == null
				|| input.equalsIgnoreCase("undefined"))) {
			return true;
		} else {
			return false;
		}
	}

}
