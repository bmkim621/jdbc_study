package jdbc_study.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_study.dto.Department;
import jdbc_study.jdbc.MySQLjdbcUtil;

public class DepartmentDaoImpl implements DepartmentDao {
	//로그 남기기
	Logger LOG = LogManager.getLogger();
	
	@Override
	public List<Department> selectDepartmentByAll() {
		//DB에 select한 결과를 ArrayList로
		List<Department> list = new ArrayList<>();
		String sql = "select deptno, deptname, floor from department";
		try(Connection conn = MySQLjdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			//확인하기
			LOG.debug(pstmt);
			while(rs.next()) {
				list.add(getDepartment(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		
		return new Department(deptNo, deptName, floor);
	}

	//추가
	@Override
	public int insertDepartment(Department department) throws SQLException {
		String sql = "insert into department values(?, ?, ?)";
		int res = 0;
		
		//try resource
		try(Connection conn = MySQLjdbcUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			//첫번째 매개변수 1(0부터 시작하는 거 아님)
			//첫번째 매개변수 1 : 부서번호, 2: 부서명, 3: 층
			//여기에 입력한 것이 ?, ?, ?로 넘어감
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			//?,?,?에 값이 들어갔는지 확인해보기
			LOG.debug(pstmt);
			
			//return타입 => int(select빼고 나머지 executeUpdate로)
			res = pstmt.executeUpdate();
			//테스트해보기 => departmentdaotest 파일로 이동
		}
		
		return res;
	}

	//삭제
	@Override
	public int deleteDepartment(Department department) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	//변경
	@Override
	public int updateDepartment(Department department) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	//부서번호로 검색
	@Override
	public Department selectDaDepartment(Department department) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
