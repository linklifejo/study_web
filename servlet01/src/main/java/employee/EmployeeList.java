package employee;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Command;
import hr.EmployeeDAO;
import hr.EmployeeDTO;

public class EmployeeList implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//비니지스로직
		//:DB에서 사원목록을 조회해온다-사원목록조회메소드 호출
		EmployeeDAO dao = new EmployeeDAO();
		ArrayList<EmployeeDTO> list = dao.employee_list();
		
		// 응답할 화면에 출력할 수 있도록 request에 attribute로 담는다
		request.setAttribute("list", list);		
	}

}
