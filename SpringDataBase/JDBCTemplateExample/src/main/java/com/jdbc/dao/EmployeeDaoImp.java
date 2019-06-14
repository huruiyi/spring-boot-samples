package com.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.jdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImp implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getEmployeeCount() {
        String sql = "select count(*) from employee";
        return 1;
    }

    public int insertEmployee(Employee employee) {
        String inserQuery = "insert into employee (EmpId, Name, Age) values (?, ?, ?) ";
        Object[] params = new Object[]{employee.getEmpId(),
                employee.getName(), employee.getAge()};
        int[] types = new int[]{Types.INTEGER, Types.VARCHAR, Types.INTEGER};
        return jdbcTemplate.update(inserQuery, params, types);
    }

    public Employee getEmployeeById(int empId) {
        String query = "select * from Employee where EmpId = ?";
        // using RowMapper anonymous class, we can create a separate RowMapper
        // for reuse
        Employee employee = jdbcTemplate.queryForObject(query,
                new Object[]{empId}, new RowMapper<Employee>() {
                    public Employee mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Employee employee = new Employee(rs.getInt("EmpId"), rs
                                .getString("Name"), rs.getInt("Age"));
                        return employee;
                    }
                });
        return employee;
    }

    public int deleteEmployeeById(int empId) {
        String delQuery = "delete from employee where EmpId = ?";
        return jdbcTemplate.update(delQuery, new Object[]{empId});
    }

    public void createEmployee() {
        jdbcTemplate.execute("create table employee (EmpId integer, Name char(30), Age integer)");
    }
}
