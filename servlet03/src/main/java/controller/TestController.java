package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.EmployeeInfoCommand;
import command.EmployeeListCommand;
import command.FirstCommand;

@WebServlet("*.do")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String path = request.getContextPath();
		uri = uri.substring( path.length() );
	
		if( uri.equals("/emp_info.do") ) { //선택한 사원정보 조회
			//비지니스로직
			new EmployeeInfoCommand().execute(request, response);
			
			//응답화면연결
			request.getRequestDispatcher("employee/info.jsp").forward(request, response);
			
		}else if( uri.equals("/emp_list.do") ) { //사원목록조회
			//비지니스로직
			//DB에서 사원목록을 조회해온다
			new EmployeeListCommand().execute(request, response);
			
			//응답화면연결
			request.getRequestDispatcher("employee/list.jsp").forward(request, response);
			
		}else if( uri.equals("/test.do") ) { //조회테스트
			//비지니스로직
			//DB에서 데이터를 조회해온다: DB연결 - 조회메소드호출
			new FirstCommand().execute(request, response);
			
			//응답할 화면연결
			RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
			rd.forward(request, response);
		}
	
	}

}
