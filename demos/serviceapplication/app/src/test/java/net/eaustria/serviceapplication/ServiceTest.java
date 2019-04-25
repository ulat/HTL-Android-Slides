package net.eaustria.serviceapplication;

import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ServiceTest {
    @Test
    public void test_service_defaultConstructor() {
        assertTrue(ReflectionUtils.hasParameterlessPublicConstructor(Service.class));
    }

    @Test
    public void test_service_correctGetter() throws NoSuchMethodException {
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, int.class, "getId"));
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, String.class, "getName"));
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, Employee.class, "getEmployee"));
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, Date.class, "getDate"));
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, String.class, "getLongitude"));
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, String.class, "getLatitude"));
    }

    @Test
    public void test_service_correctSetter() throws NoSuchMethodException {
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, null, "setId", int.class));
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, null, "setName", String.class));
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, null, "setEmployee", Employee.class));
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, null, "setDate", Date.class));
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, null, "setLongitude", String.class));
        assertTrue(ReflectionUtils.isMethodAvailable(Service.class, null, "setLatitude", String.class));
    }

    @Test
    public void test_service_correctToString() {
        Service service = new Service();
        service.setName("TEST");

        assertEquals("TEST", service.toString());
    }
}
