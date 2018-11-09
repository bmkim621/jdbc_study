package jdbc_study.jdbc;

import java.util.List;

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

}
