package employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Command;
import hr.EmployeeDAO;
import hr.EmployeeDTO;

public class EmployeeRegist implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//비지니스로직
		//화면에서 입력한 정보를 DB에 신규저장 - 신규사원등록 메소드 호출
		//화면에서 입력한 정보를 수집한다 
		EmployeeDTO dto = new EmployeeDTO();
		dto.setLast_name( request.getParameter("last_name") );
		dto.setFirst_name( request.getParameter("first_name") );
		dto.setEmail( request.getParameter("email") );
		dto.setPhone_number( request.getParameter("phone_number") );
		dto.setSalary( Integer.parseInt( request.getParameter("salary") ) );
		dto.setJob_id( request.getParameter("job_id") );
		dto.setDepartment_id( Integer.valueOf(request.getParameter("department_id")) );

		EmployeeDAO dao = new EmployeeDAO();
		dao.employee_regist(dto);
	}

}
