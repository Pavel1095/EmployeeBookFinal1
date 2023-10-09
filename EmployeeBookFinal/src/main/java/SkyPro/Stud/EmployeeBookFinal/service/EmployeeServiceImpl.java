package SkyPro.Stud.EmployeeBookFinal.service;

import SkyPro.Stud.EmployeeBookFinal.dto.Employee;
import SkyPro.Stud.EmployeeBookFinal.exception.EmployeeAlreadyAddedException;
import SkyPro.Stud.EmployeeBookFinal.exception.EmployeeNotFoundException;
import SkyPro.Stud.EmployeeBookFinal.exception.EmployeeStorageIsFullException;
import SkyPro.Stud.EmployeeBookFinal.util.EmployeeNameValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    private static final int EMPLOYEES_SIZE = 3;
    private final EmployeeNameValidator employeeValidationService;

    public EmployeeServiceImpl(EmployeeNameValidator employeeValidationService) {
        this.employeeValidationService = employeeValidationService;
        this.employees = new HashMap<>();
    }


    @Override
    public Employee addEmployee(String firstName, String lastName) {
        return null;
    }

    @Override
    public Employee addEmployeeFull(String firstName, String lastName, int department, double salary){
        if (employees.size() == EMPLOYEES_SIZE){
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee (
                StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName),
                department,
                salary);

        if (employees.containsKey(employee)){
            throw new EmployeeAlreadyAddedException();
        }
            return employee;
    }

    @Override
    public Employee remoteEmployee(String firstName, String lastName){
        Employee employee = getEmployee(firstName, lastName);
        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee getEmployee(String firstName, String lastName){
        String fullNameKey = firstName + " " + lastName;
        if (!employees.containsKey(fullNameKey)){
            throw new EmployeeNotFoundException();
        }
        return employees.get(fullNameKey);
    }

    public Collection<Employee> findAll(){
        return employees.values();
    }
}
