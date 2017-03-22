package com.examprep.app.controller;

import java.io.IOException;

public interface CmdServletIF {
	void execute() throws Exception;
	abstract void sendJsonResult(String jsonResult) throws IOException;
}