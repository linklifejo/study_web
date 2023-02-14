package hr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hr/emp_regist")
public class EmpRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//비지니스로직
		//화면에서 입력한 정보를 가져와 DB에 저장한다
		//1.화면에서 입력한 정보를 DTO에 담는다
		EmployeeDTO dto = new EmployeeDTO();
		dto.setLast_name( request.getParameter("last_name") );
		dto.setFirst_name( request.getParameter("first_name") );
		dto.setEmail( request.getParameter("email") );
		dto.setPhone_number( request.getParameter("phone_number") );
		dto.setSalary( Integer.parseInt( request.getParameter("salary") ) );
		dto.setJob_id( request.getParameter("job_id") );
		dto.setDepartment_id( Integer.valueOf(request.getParameter("department_id")) );
	
		//2.DB를 연결해서 사원정보를 신규저장하는 메소드를 호출한다
		EmployeeDAO dao = new EmployeeDAO();
		dao.employee_regist(dto);
		
		//프리젠테이션로직: 응답할 화면
//		RequestDispatcher rd = request.getRequestDispatcher("emp_list");
//		rd.forward(request, response);
		response.sendRedirect("emp_list");
	
	}

}
