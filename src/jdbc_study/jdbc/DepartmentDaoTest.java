package jdbc_study.jdbc;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.DepartmentDaoImpl;
import jdbc_study.dto.Department;

public class DepartmentDaoTest {
	static DepartmentDao dao;
	
	//테스트 수행하기 전 한번 호출
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MySQLjdbcUtilTest.LOG.debug("setUpBeforeClass()");
		dao = new DepartmentDaoImpl();
	}

	//테스트 완료 후 마지막에 호출
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		MySQLjdbcUtilTest.LOG.debug("tearDownAfterClass()");
		dao = null;
	}

	//select 테스트
	@Test
	public void testSelectDepartmentByAll() {
		List<Department> list = dao.selectDepartmentByAll();
		//console창으로 확인해보기
		for(Department dept : list) {
			MySQLjdbcUtilTest.LOG.debug(dept);
		}
		
		//확인, 리스트 크기가 0과 같지 않아야 데이터가 제대로 있다. 0이면 데이터가 없겠지.....
		Assert.assertNotEquals(0, list.size());
	}

	//insert 테스트
	@Test
	public void testInsertDepartment() {
		Department newDept = new Department(4, "자바개발", 15);
		try {
			int res = dao.insertDepartment(newDept);
			//확인, 1개 추가했으니까(4,"자바개발",15) 기댓값:1, 정상적으로 추가되면 1, res(실행결과값) 만약 1=1이면? 추가됐음을 의미
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());	//Dupulicate(중복) => 에러코드 1062
			e.printStackTrace();
			if(e.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 부서입니다.");
			}
		}
	}
	
	//delete 테스트
	@Test
	public void testDeleteDepartment() {
		
	}
	
	//update 테스트
	@Test
	public void testUpdateDepartment() {
		
	}
	
	//dempNo로 select 테스트
	@Test
	public void testSelectDepartment() {
		
	}
}
