package com.examprep.app.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examprep.app.bean.Dozent;
import com.examprep.app.bean.Hochschule;
import com.examprep.app.persistencelayer.PersistenceQuery;
import com.examprep.app.util.ErrorMessages;
import com.examprep.app.util.JSONConverter;
import com.examprep.app.util.JSONRespCreator;

public class GetDozentenCmd extends AbstractCmdServlet {

	public GetDozentenCmd(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		super(servlet, request, response);
	}

	public void execute() throws Exception {

		String res = "";

		try {

			List<Dozent> dList;

			dList = PersistenceQuery.getAllDozenten();

			res = JSONRespCreator.createWobj(true, JSONConverter.toJSOND(dList));

		} catch (Exception e) {
			res = JSONRespCreator.createWstring(false, ErrorMessages.getInternalError());
			e.printStackTrace();
		} finally {
			this.sendJsonResult(res);
		}

	}
}
