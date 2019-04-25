package net.eaustria.serviceapplication;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class EmployeeTest {
    @Test
    public void test_employee_defaultConstructor() {
        assertTrue(ReflectionUtils.hasParameterlessPublicConstructor(Employee.class));
    }

    @Test
    public void test_employee_correctGetter() throws NoSuchMethodException {
        assertTrue(ReflectionUtils.isMethodAvailable(Employee.class, String.class, "getName"));
        assertTrue(ReflectionUtils.isMethodAvailable(Employee.class, String.class, "getLongitude"));
        assertTrue(ReflectionUtils.isMethodAvailable(Employee.class, String.class, "getLatitude"));
    }

    @Test
    public void test_employee_correctSetter() throws NoSuchMethodException {
        assertTrue(ReflectionUtils.isMethodAvailable(Employee.class, null, "setName", String.class));
        assertTrue(ReflectionUtils.isMethodAvailable(Employee.class, null, "setLongitude", String.class));
        assertTrue(ReflectionUtils.isMethodAvailable(Employee.class, null, "setLatitude", String.class));
    }

    @Test
    public void test_employee_correctToString() {
        Employee emp = new Employee();
        emp.setName("TEST");

        assertEquals("TEST", emp.toString());
    }
}
