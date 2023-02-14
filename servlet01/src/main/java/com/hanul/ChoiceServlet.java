package com.hanul;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/choice")
public class ChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//선택화면에서 선택해서 전달한 정보를 화면을 통해 응답하도록 처리
		//주소창을 통해 전달된 파라미터 한글이 깨지지 않게
		request.setCharacterEncoding("utf-8");
		
		//전달된 파라미터 접근
		String gender = request.getParameter("gender");
		
		String[] sports = request.getParameterValues("sports");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<h3>선택정보</h3>");
		out.print("<div>성별: "+ gender +"</div>");
//		out.print("<div>스포츠: "+ sports +"</div>");
		out.print("<div>스포츠: " );
		//sports 
//		for(int idx=0; idx<sports.length; idx++ ) {
//			out.print( sports[idx] );
//		}
		for(String sport : sports) {
			out.print( sport );
		}
		
//		test(sports);
		out.print("<div><a href='choice.html'>선택화면</a></div>");
		out.print("<div><a href='home.html'>홈으로</a></div>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
	
		//디버그하기
		//F6: 명령행 한 행 실행
		//F8: 다음 breakpoint까지 실행
		//F5: 메소드/클래스 내부로 들어가서 실행 
	}
	
	public void test(String[] sports) {
		for(String sport : sports) {
			System.out.println( sport );
		}
	}

}






