package com.hanul;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Calculator;

//클래스 import 선언문: ctrl + shift + o
//주석처리: 라인주석: ctrl + /, ctrl + shift + c
//코드라인복사 붙여넣기: ctrl + alt + 방향키
//코드라인이동 : alt + 방향키 
//코드라인삭제 : ctrl + d

@WebServlet({"/hello", "/first"})
//@WebServlet({"*.do"})
//@WebServlet({"/*"})
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Calculator c= new Calculator();
		int result = c.calculate(10, 20, "+");
		PrintWriter writer = response.getWriter();
		writer.print("<html>");
		writer.print("<body>");
		writer.print("<h3>Hello Servlet</h3>");
		writer.print("<div>여기는 Hello</div");
		writer.print("<div>연산결과: " + result + "</div>");
		writer.print("</body>");
		writer.print("</html>");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
