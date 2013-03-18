package com.avaya.ept.ups.rest.keepalive;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("serial")
public class KeepAliveServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeepAliveServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("Handling keep alive request...");
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
