package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//DAO(Data Access Object)
public class EmployeeDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public void connect() {
		try {
			//1.드라이버로딩:드라이버관리자가 생성됨
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.DB에 연결
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "0000");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	//사원정보를 신규저장
	public void employee_regist(EmployeeDTO dto) {
		//DB연결
		connect();
		//쿼리문작성
		String sql 
		= "insert into employees"
		+ "(employee_id, last_name, first_name, email, phone_number, salary"
		+ ", job_id, department_id, hire_date) "
		+ "values (employees_seq.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		//쿼리문실행
		//1.조회: executeQuery()
		//2.DML: executeUpdate()
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getLast_name());
			ps.setString(2, dto.getFirst_name());
			ps.setString(3, dto.getEmail());
			ps.setString(4, dto.getPhone_number());
			ps.setInt(5, dto.getSalary());
			ps.setString(6, dto.getJob_id());
			ps.setInt(7, dto.getDepartment_id());
			int count = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			//자원회수
			disconnect();
		}
	}
	
	
	//선택한 사원정보를 조회
	public EmployeeDTO employee_info(int employee_id) {
		EmployeeDTO dto = null;
		//DB연결
		connect();
		//쿼리문선언
		try {
			String sql 
				= "select last_name ||' ' || first_name name, department_name, e.* "
				+ "from departments d right outer join employees e "
				+ "on e.department_id=d.department_id "
				+ "where employee_id = ?";
			//쿼리문실행
			ps = conn.prepareStatement(sql); //쿼리문 준비
			ps.setInt(1, employee_id);   //준비된 쿼리문에 ? 가 있으면 ?에 값을 담는다
			rs = ps.executeQuery(); //준비된 쿼리문 실행하기: sqldeveloper에서의 ctrl+enter
			if( rs.next() ) {
				dto = new EmployeeDTO();
				//조회결과값을 dto에 담기
				dto.setDepartment_name( rs.getString("department_name"));
				dto.setEmployee_id( rs.getInt("employee_id") );
//				dto.setName( rs.getString("last_name") 
//							+ " "+ rs.getString("first_name") );
				dto.setName( rs.getString("name") );
				dto.setDepartment_id( rs.getInt("department_id") );
				dto.setJob_id( rs.getString("job_id") );
				dto.setSalary( rs.getInt("salary") );
				dto.setHire_date( rs.getDate("hire_date") );				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			//자원회수
			disconnect();
		}
		//사원정보반환
		return dto;
	}
	
	//전체 부서목록 조회
	public ArrayList<DepartmentDTO> department_list() {
		connect();
		
		ArrayList<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
		try {
			String sql = "select * from departments";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				DepartmentDTO dto = new DepartmentDTO();
				dto.setDepartment_id( rs.getInt("department_id") );
				dto.setDepartment_name( rs.getString("department_name") );
				list.add(dto);
			}			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			disconnect();
		}
		return list;
	}
	
	//선택한 사원정보 삭제
	public void employee_delete(int id) {
		connect();
		String sql = "delete from employees where employee_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			//select: executeQuery()
			//insert/update/delete: executeUpdate()
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			disconnect();
		}
	}
	
	
	//전체 사원정보를 조회
	public ArrayList<EmployeeDTO> employee_list() {		
		connect();
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		try {
			String sql 
			= "select * from employees "
			+ "order by employee_id desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				EmployeeDTO dto = new EmployeeDTO();
				dto.setEmployee_id( rs.getInt("employee_id") );
				dto.setName( rs.getString("last_name") + " " 
								+ rs.getString("first_name") );
				dto.setDepartment_id( rs.getInt("department_id") );
				dto.setSalary( rs.getInt("salary") );
				dto.setHire_date( rs.getDate("hire_date") );
				list.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			disconnect();
		}
		
		return list;
	}

	//자원회수
	private void disconnect() {
		if( rs!=null ) {
			try{ rs.close(); }catch(Exception e) {}
		}
		if( ps!=null ) {
			try{ ps.close(); }catch(Exception e) {}
		}
		if( conn!=null ) {
			try{ conn.close(); }catch(Exception e) {}
		}
	}
}
