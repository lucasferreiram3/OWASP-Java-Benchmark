/**
* OWASP Benchmark Project v1.2beta
*
* This file is part of the Open Web Application Security Project (OWASP)
* Benchmark Project. For details, please see
* <a href="https://www.owasp.org/index.php/Benchmark">https://www.owasp.org/index.php/Benchmark</a>.
*
* The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
* of the GNU General Public License as published by the Free Software Foundation, version 2.
*
* The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
* even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* @author Nick Sanidas <a href="https://www.aspectsecurity.com">Aspect Security</a>
* @created 2015
*/

package org.owasp.benchmark.testcode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BenchmarkTest02183")
public class BenchmarkTest02183 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String param = request.getParameter("vector");
		if (param == null) param = "";

		String bar = doSomething(param);
		
		try {
			String sql = "SELECT TOP 1 USERNAME from USERS where USERNAME='foo' and PASSWORD='"+ bar + "'";
	
	        Object results = org.owasp.benchmark.helpers.DatabaseHelper.JDBCtemplate.queryForObject(sql,new Object[]{}, String.class);
			java.io.PrintWriter out = response.getWriter();
			out.write("Your results are: ");
	//		System.out.println("Your results are");
			out.write(org.owasp.esapi.ESAPI.encoder().encodeForHTML(results.toString()));
	//		System.out.println(results.toString());
		} catch (org.springframework.dao.DataAccessException e) {
			if (org.owasp.benchmark.helpers.DatabaseHelper.hideSQLErrors) {
        		response.getWriter().println("Error processing request.");
        		return;
        	}
			else throw new ServletException(e);
		}
	}  // end doPost
	
	private static String doSomething(String param) throws ServletException, IOException {

		// Chain a bunch of propagators in sequence
		String a98238 = param; //assign
		StringBuilder b98238 = new StringBuilder(a98238);  // stick in stringbuilder
		b98238.append(" SafeStuff"); // append some safe content
		b98238.replace(b98238.length()-"Chars".length(),b98238.length(),"Chars"); //replace some of the end content
		java.util.HashMap<String,Object> map98238 = new java.util.HashMap<String,Object>();
		map98238.put("key98238", b98238.toString()); // put in a collection
		String c98238 = (String)map98238.get("key98238"); // get it back out
		String d98238 = c98238.substring(0,c98238.length()-1); // extract most of it
		String e98238 = new String( new sun.misc.BASE64Decoder().decodeBuffer( 
		    new sun.misc.BASE64Encoder().encode( d98238.getBytes() ) )); // B64 encode and decode it
		String f98238 = e98238.split(" ")[0]; // split it on a space
		org.owasp.benchmark.helpers.ThingInterface thing = org.owasp.benchmark.helpers.ThingFactory.createThing();
		String g98238 = "barbarians_at_the_gate";  // This is static so this whole flow is 'safe'
		String bar = thing.doSomething(g98238); // reflection
	
		return bar;	
	}
}