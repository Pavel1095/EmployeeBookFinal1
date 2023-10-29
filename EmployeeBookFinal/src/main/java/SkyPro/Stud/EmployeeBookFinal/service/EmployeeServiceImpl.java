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
    public Employee addEmployeeFull(String firstName, String lastName, int department, double salary) {
        if (employees.size() == EMPLOYEES_SIZE) {
            throw new EmployeeStorageIsFullException();
        }

        String capitalizedFirstName = StringUtils.capitalize(firstName);
        String capitalizedLastName = StringUtils.capitalize(lastName);
        Employee employee = new Employee(
                capitalizedFirstName,
                capitalizedLastName,
                department,
                salary);

        String key = getKey(capitalizedFirstName, capitalizedLastName);

        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }

        employees.put(key, employee);

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        return employees.remove(getKey(firstName, lastName));
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        String fullNameKey = getKey(firstName, lastName);
        if (!employees.containsKey(fullNameKey)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(fullNameKey);
    }

    private static String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public Collection<Employee> findAll() {
        return employees.values();
    }
}
