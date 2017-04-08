package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Hochschule;
import com.examprep.app.bean.Nutzer;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.persistencelayer.daoif.HochschuleDao;
import com.examprep.app.persistencelayer.daoimpl.HochschuleDaoImpl;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;

public class GetHochschulenCmd extends AbstractCmdServlet {

	public GetHochschulenCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {

		String res = "";

		try {

			List<Hochschule> hsList = PersistenceQuery.getAllHochschulen();

			res = "{\"successfull\":" + "true" + ",\"data\":" + JSONConverter.toJSONH(hsList) + "}";

		} catch (Exception e) {
			res = "{\"successfull\":" + "false" + ",\"data\":\""
					+ ErrorMessages.getInternalError() + "\"}";
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}
