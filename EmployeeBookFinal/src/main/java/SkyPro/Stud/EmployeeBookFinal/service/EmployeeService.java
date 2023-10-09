package SkyPro.Stud.EmployeeBookFinal.service;

import SkyPro.Stud.EmployeeBookFinal.dto.Employee;

import java.util.Collection;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);

    Employee addEmployeeFull(String firstName, String lastName, int department, double salary);

    Employee remoteEmployee(String firstName, String lastName);

    Employee getEmployee(String firstName, String lastName);

    Collection<Employee> findAll();
}
