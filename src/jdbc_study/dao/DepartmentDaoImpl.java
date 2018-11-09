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
		
		//db에 연결,, mysqljdbcutil에서 connection...java.sql로 import
		Connection conn = null;
		//PreparedStatement => java.util(인터페이스!)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MySQLjdbcUtil.getConnection();
			//sql문장 보낼 준비 하기(prepare)
			pstmt = conn.prepareStatement(sql);
			//확인하기
			LOG.debug(pstmt);
			//insert 하는 경우 executeQuery, 나머지 update/insert/delet => executeUpdate();
			rs = pstmt.executeQuery();
			
			//반복문(db 데이터 읽기,,,,, 테이블에 데이터 없으면 false)
			while(rs.next()) {
				//getInt("필드명") //인덱스보다는 필드명 쓰는게 좋음!
				//인덱스 번호 기준은 ★select★ 했을 때의 절을 기준으로 deptno(인덱스 0), deptname(인덱스 1), floor(인덱스 2)임.
				int deptNo = rs.getInt("deptno");
				String deptName = rs.getString("deptname");
				int floor = rs.getInt("floor");
				
				Department dept = new Department(deptNo, deptName, floor);
				
				//리스트에 넣어준다.
				list.add(dept);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//끝나면 반드시 connection 종료("역순"으로 종료시킴)
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

}
