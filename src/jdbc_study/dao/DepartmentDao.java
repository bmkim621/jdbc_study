package jdbc_study.dao;

import java.sql.SQLException;
import java.util.List;

import jdbc_study.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	
	//일반적으로 insert하면 return 타입이 숫자 => 성공 0 실패 1
	int insertDepartment(Department department) throws SQLException;
}
