package pro.sky.cource2.aliev.employeebookwithmap.service;

import org.springframework.stereotype.Service;
import pro.sky.cource2.aliev.employeebookwithmap.exception.EmployeeAlreadyAddedException;
import pro.sky.cource2.aliev.employeebookwithmap.exception.EmployeeNotFoundException;
import pro.sky.cource2.aliev.employeebookwithmap.model.Employee;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee add(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        if (employees.containsKey(fullName)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + fullName + " уже есть в системе!");
        }
        Employee employee = new Employee(firstName, lastName);
        employees.put(fullName, employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        if (!employees.containsKey(fullName)) {
            throw new EmployeeNotFoundException("Сотрудник " + fullName + " не найден в системе!");
        }
        Employee employee = new Employee(firstName, lastName);
        employees.remove(fullName);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        if (!employees.containsKey(fullName)) {
            throw new EmployeeNotFoundException("Сотрудник " + fullName + " не найден в системе!");
        }
        return new Employee(firstName, lastName);
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return employees;
    }
}
