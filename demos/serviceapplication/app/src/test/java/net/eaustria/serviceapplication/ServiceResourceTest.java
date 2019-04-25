package net.eaustria.serviceapplication;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ServiceResourceTest {
    @Test
    public void test_serviceResource_defaultConstructor() {
        assertTrue(ReflectionUtils.hasParameterlessPublicConstructor(ServiceResource.class));
    }

    @Test
    public void test_serviceResource_correctGetter() throws NoSuchMethodException {
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, int.class, "getId"));
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, String.class, "getName"));
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, int.class, "getEmployeeId"));
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, String.class, "getDate"));
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, String.class, "getLongitude"));
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, String.class, "getLatitude"));
    }

    @Test
    public void test_serviceResource_correctSetter() throws NoSuchMethodException {
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, null, "setId", int.class));
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, null, "setName", String.class));
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, null, "setEmployeeId", int.class));
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, null, "setDate", String.class));
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, null, "setLongitude", String.class));
        assertTrue(ReflectionUtils.isMethodAvailable(ServiceResource.class, null, "setLatitude", String.class));
    }

}
