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

@WebServlet("/BenchmarkTest00186")
public class BenchmarkTest00186 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
		String param = request.getHeader("vector");
		if (param == null) param = "";
		
		
		// Chain a bunch of propagators in sequence
		String a75075 = param; //assign
		StringBuilder b75075 = new StringBuilder(a75075);  // stick in stringbuilder
		b75075.append(" SafeStuff"); // append some safe content
		b75075.replace(b75075.length()-"Chars".length(),b75075.length(),"Chars"); //replace some of the end content
		java.util.HashMap<String,Object> map75075 = new java.util.HashMap<String,Object>();
		map75075.put("key75075", b75075.toString()); // put in a collection
		String c75075 = (String)map75075.get("key75075"); // get it back out
		String d75075 = c75075.substring(0,c75075.length()-1); // extract most of it
		String e75075 = new String( new sun.misc.BASE64Decoder().decodeBuffer( 
		    new sun.misc.BASE64Encoder().encode( d75075.getBytes() ) )); // B64 encode and decode it
		String f75075 = e75075.split(" ")[0]; // split it on a space
		org.owasp.benchmark.helpers.ThingInterface thing = org.owasp.benchmark.helpers.ThingFactory.createThing();
		String g75075 = "barbarians_at_the_gate";  // This is static so this whole flow is 'safe'
		String bar = thing.doSomething(g75075); // reflection
		
		
		try {
			int randNumber = java.security.SecureRandom.getInstance("SHA1PRNG").nextInt(99);
			String rememberMeKey = Integer.toString(randNumber);
			
			String user = "SafeInga";
			String fullClassName = this.getClass().getName();
			String testCaseNumber = fullClassName.substring(fullClassName.lastIndexOf('.')+1+"BenchmarkTest".length());
			user+= testCaseNumber;
			
			String cookieName = "rememberMe" + testCaseNumber;
			
			boolean foundUser = false;
			javax.servlet.http.Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; !foundUser && i < cookies.length; i++) {
					javax.servlet.http.Cookie cookie = cookies[i];
					if (cookieName.equals(cookie.getName())) {
						if (cookie.getValue().equals(request.getSession().getAttribute(cookieName))) {
							foundUser = true;
						}
					}
				}
			}

			
			if (foundUser) {
				response.getWriter().println("Welcome back: " + user + "<br/>");			
			} else {			
				javax.servlet.http.Cookie rememberMe = new javax.servlet.http.Cookie(cookieName, rememberMeKey);
				rememberMe.setSecure(true);
				rememberMe.setPath("/benchmark/" + this.getClass().getSimpleName());
				request.getSession().setAttribute(cookieName, rememberMeKey);
				response.addCookie(rememberMe);
				response.getWriter().println(user + " has been remembered with cookie: " + rememberMe.getName() 
						+ " whose value is: " + rememberMe.getValue() + "<br/>");
			}

	    } catch (java.security.NoSuchAlgorithmException e) {
			System.out.println("Problem executing SecureRandom.nextInt(int) - TestCase");
			throw new ServletException(e);
	    }		
		response.getWriter().println("Weak Randomness Test java.security.SecureRandom.nextInt(int) executed");
	}
}