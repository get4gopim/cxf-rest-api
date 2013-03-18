package com.avaya.ept.ups.rest.keepalive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("serial")
public class KeepAliveServletTest extends KeepAliveServlet {

	private static final Logger LOGGER = Logger.getLogger(KeepAliveServletTest.class);
	
	@Test
	public void test() {
		LOGGER.info("test()...");
        try {
            KeepAliveServlet servlet = new KeepAliveServlet();
            
            HttpServletRequest req = new MyHttpServletRequest();
            MyHttpServletResponse resp = new MyHttpServletResponse();
            servlet.doGet(req, resp);
            Assert.assertTrue(resp.status == 200);
            servlet.doPost(req, resp);
            Assert.assertTrue(resp.status == 200);
        } catch (Throwable e) {
            LOGGER.error("Test Error: " + e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
	}
	
	private class MyHttpServletRequest implements HttpServletRequest {

	    public String getAuthType() {

	        return null;
	    }

	    public String getContextPath() {

	        return null;
	    }

	    public Cookie[] getCookies() {

	        return null;
	    }

	    public long getDateHeader(String name) {

	        return 0;
	    }

	    public String getHeader(String name) {

	        return null;
	    }

	    public Enumeration getHeaderNames() {

	        return null;
	    }

	    public Enumeration getHeaders(String name) {

	        return null;
	    }

	    public int getIntHeader(String name) {

	        return 0;
	    }

	    public String getMethod() {

	        return null;
	    }

	    public String getPathInfo() {

	        return null;
	    }

	    public String getPathTranslated() {

	        return null;
	    }

	    public String getQueryString() {

	        return null;
	    }

	    public String getRemoteUser() {

	        return null;
	    }

	    public String getRequestURI() {

	        return null;
	    }

	    public StringBuffer getRequestURL() {

	        return null;
	    }

	    public String getRequestedSessionId() {

	        return null;
	    }

	    public String getServletPath() {

	        return null;
	    }

	    public HttpSession getSession() {

	        return null;
	    }

	    public HttpSession getSession(boolean create) {

	        return null;
	    }

	    public Principal getUserPrincipal() {

	        return null;
	    }

	    public boolean isRequestedSessionIdFromCookie() {

	        return false;
	    }

	    public boolean isRequestedSessionIdFromURL() {

	        return false;
	    }

	    public boolean isRequestedSessionIdFromUrl() {

	        return false;
	    }

	    public boolean isRequestedSessionIdValid() {

	        return false;
	    }

	    public boolean isUserInRole(String role) {

	        return false;
	    }

	    public Object getAttribute(String name) {

	        return null;
	    }


	    public Enumeration getAttributeNames() {

	        return null;
	    }

	    public String getCharacterEncoding() {

	        return null;
	    }

	    public int getContentLength() {

	        return 0;
	    }

	    public String getContentType() {

	        return null;
	    }

	    public ServletInputStream getInputStream() throws IOException {

	        return null;
	    }

	    public String getLocalAddr() {

	        return null;
	    }

	    public String getLocalName() {

	        return null;
	    }

	    public int getLocalPort() {

	        return 0;
	    }

	    public Locale getLocale() {

	        return null;
	    }

	    public Enumeration getLocales() {

	        return null;
	    }

	    public String getParameter(String name) {

	        return null;
	    }

	    public Map getParameterMap() {

	        return null;
	    }

	    public Enumeration getParameterNames() {

	        return null;
	    }

	    public String[] getParameterValues(String name) {

	        return null;
	    }

	    public String getProtocol() {

	        return null;
	    }

	    public BufferedReader getReader() throws IOException {

	        return null;
	    }

	    public String getRealPath(String path) {

	        return null;
	    }

	    public String getRemoteAddr() {

	        return null;
	    }

	    public String getRemoteHost() {

	        return null;
	    }

	    public int getRemotePort() {

	        return 0;
	    }

	    public RequestDispatcher getRequestDispatcher(String path) {

	        return null;
	    }

	    public String getScheme() {

	        return null;
	    }

	    public String getServerName() {

	        return null;
	    }

	    public int getServerPort() {

	        return 0;
	    }

	    public boolean isSecure() {

	        return false;
	    }

	    public void removeAttribute(String name) {

	        
	    }

	    public void setAttribute(String name, Object o) {

	        
	    }

	    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

	        
	    }

		@Override
		public AsyncContext getAsyncContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DispatcherType getDispatcherType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ServletContext getServletContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isAsyncStarted() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isAsyncSupported() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public AsyncContext startAsync() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean authenticate(HttpServletResponse arg0)
				throws IOException, ServletException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Part getPart(String arg0) throws IOException, ServletException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection<Part> getParts() throws IOException, ServletException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void login(String arg0, String arg1) throws ServletException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void logout() throws ServletException {
			// TODO Auto-generated method stub
			
		}
	   
	    
	}
	
	private class MyHttpServletResponse implements HttpServletResponse {
        int status;

        public void addCookie(Cookie cookie) {

            
        }

        public void addDateHeader(String name, long date) {

            
        }

        public void addHeader(String name, String value) {

            
        }

        public void addIntHeader(String name, int value) {

            
        }

        public boolean containsHeader(String name) {

            return false;
        }

        public String encodeRedirectURL(String url) {

            return null;
        }

        public String encodeRedirectUrl(String url) {

            return null;
        }

        public String encodeURL(String url) {

            return null;
        }

        public String encodeUrl(String url) {

            return null;
        }

        public void sendError(int sc) throws IOException {

            
        }

        public void sendError(int sc, String msg) throws IOException {

            
        }

        public void sendRedirect(String location) throws IOException {

            
        }

        public void setDateHeader(String name, long date) {

            
        }

        public void setHeader(String name, String value) {

            
        }

        public void setIntHeader(String name, int value) {

            
        }

        public void setStatus(int sc) {
            status = sc;
        }

        public void setStatus(int sc, String sm) {

            
        }

        public void flushBuffer() throws IOException {

            
        }

        public int getBufferSize() {

            return 0;
        }

        public String getCharacterEncoding() {

            return null;
        }

        public String getContentType() {

            return null;
        }

        public Locale getLocale() {

            return null;
        }

        public ServletOutputStream getOutputStream() throws IOException {

            return null;
        }

        public PrintWriter getWriter() throws IOException {

            return null;
        }

        public boolean isCommitted() {

            return false;
        }

        public void reset() {

            
        }

        public void resetBuffer() {

            
        }

        public void setBufferSize(int size) {

            
        }

        public void setCharacterEncoding(String charset) {

            
        }

        public void setContentLength(int len) {

            
        }

        public void setContentType(String type) {

            
        }

        public void setLocale(Locale loc) {

            
        }

		@Override
		public String getHeader(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection<String> getHeaderNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection<String> getHeaders(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getStatus() {
			// TODO Auto-generated method stub
			return 0;
		}

        
        
    }


}



