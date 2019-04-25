package net.eaustria.serviceapplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static boolean isFieldAvailable(Class<?> test, Class<?> type, String fieldName) throws NoSuchFieldException {
        Field declaredField = test.getDeclaredField(fieldName);

        return declaredField != null && declaredField.getType() == type;
    }

    public static boolean isMethodAvailable(Class<?> test, Class<?> returnType, String methodName, Class<?>... parameter) throws NoSuchMethodException {
        Method testMethod = test.getDeclaredMethod(methodName, parameter);

        if (testMethod == null)
            return false;

        if (returnType != null)
            if (!testMethod.getReturnType().equals(returnType))
                return false;

        return true;
    }

    public static boolean throwsException(Class<?> test, Class<?> exception, String method, Class<?>... parameter) throws NoSuchMethodException {
        Method testMethod = test.getDeclaredMethod(method, parameter);

        for (Class<?> exceptionType : testMethod.getExceptionTypes()) {
            if (exceptionType.equals(exception))
                return true;
        }

        return false;
    }

    public static boolean hasParameterlessPublicConstructor(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            // In Java 7-, use getParameterTypes and check the length of the array returned
            if (constructor.getParameterCount() == 0) {
                return true;
            }
        }
        return false;
    }
}
