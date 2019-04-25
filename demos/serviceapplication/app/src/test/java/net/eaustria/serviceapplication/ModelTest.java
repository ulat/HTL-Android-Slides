package net.eaustria.serviceapplication;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ModelTest {

    @Test
    public void test_model_getEmployees_somethingSet() throws ParseException {
        Model underTest = new Model(getEmployeeAssertData(), getServiceResourceAssertData());

        assertTrue(underTest.getEmployees() != null);
        assertTrue(underTest.getEmployees().size() > 0);
    }

    @Test
    public void test_model_getEmployees_correctSize() throws ParseException {
        Model underTest = new Model(getEmployeeAssertData(), getServiceResourceAssertData());

        assertEquals(2, underTest.getEmployees().size());
    }

    @Test
    public void test_model_getEmployees_correctData() throws ParseException {
        Model underTest = new Model(getEmployeeAssertData(), getServiceResourceAssertData());

        List<Employee> employeesExpected = getEmployeeAssertData();

        ListTester<Employee> listTester = new ListTester<>();

        listTester.listsEqual(underTest.getEmployees(), employeesExpected);
    }

    @Test
    public void test_model_getServices_somethingSet() throws ParseException {
        Model underTest = new Model(getEmployeeAssertData(), getServiceResourceAssertData());

        assertTrue(underTest.getServices() != null);
        assertTrue(underTest.getServices().size() > 0);
    }

    @Test
    public void test_model_getServices_correctSize() throws ParseException {
        Model underTest = new Model(getEmployeeAssertData(), getServiceResourceAssertData());

        assertEquals(3, underTest.getServices().size());
    }

    @Test
    public void test_model_getServices_correctData() throws ParseException {
        Model underTest = new Model(getEmployeeAssertData(), getServiceResourceAssertData());

        ListTester<Service> listTester = new ListTester<>();

        listTester.listsEqual(underTest.getServices(), getServiceAssertData(true));
    }

    @Test
    public void test_model_deleteService_deletesSomething() throws ParseException {
        Model underTest = new Model(getEmployeeAssertData(), getServiceResourceAssertData());

        underTest.deleteService(0);

        assertEquals(2, underTest.getServices().size());
    }

    @Test
    public void test_model_deleteService_correctData() throws ParseException {
        Model underTest = new Model(getEmployeeAssertData(), getServiceResourceAssertData());

        underTest.deleteService(0);

        ListTester<Service> listTester = new ListTester<>();

        listTester.listsEqual(underTest.getServices(), getServiceAssertData(false));
    }

    private class ListTester<T> {
        private boolean listsEqual(List<T> listA, List<T> listB) {
            for (T cA : listA) {
                if(!isElementInList(cA, listB))
                    return false;
            }

            for(T cB : listB) {
                if(!isElementInList(cB, listA))
                    return false;
            }

            return true;
        }

        private boolean isElementInList(T needle, List<T> list) {
            for (T cElement : list) {
                if (cElement instanceof Employee) {
                    if (employeeEquals((Employee) cElement, (Employee) needle))
                        return true;
                }
                if (cElement instanceof ServiceResource) {
                    if(serviceResourceEquals((ServiceResource) cElement, (ServiceResource) needle)) {
                        return true;
                    }
                }
                if(cElement instanceof Service) {
                    if(serviceEquals((Service) cElement, (Service) needle)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean serviceEquals(Service a, Service b) {
            Employee e1 = a.getEmployee();
            Employee e2 = b.getEmployee();

            if(!employeeEquals(e1, e2)) {
                return false;
            }

            return a.getId() == b.getId() &&
                    a.getName().equals(b.getName()) &&
                    a.getDate().equals(b.getDate()) &&
                    a.getLatitude().equals(b.getLatitude()) &&
                    a.getLongitude().equals(b.getLongitude());
        }

        private boolean serviceResourceEquals(ServiceResource a, ServiceResource b) {
            return a.getId() == b.getId() &&
                    a.getName().equals(b.getName()) &&
                    a.getEmployeeId() == b.getEmployeeId() &&
                    a.getDate().equals(b.getDate()) &&
                    a.getLatitude().equals(b.getLatitude()) &&
                    a.getLongitude().equals(b.getLongitude());
        }

        private boolean employeeEquals(Employee a, Employee b) {
            return a.getId() == b.getId() &&
                    a.getName().equals(b.getName()) &&
                    a.getLatitude().equals(b.getLatitude()) &&
                    a.getLongitude().equals(b.getLongitude());
        }
    }

    private List<ServiceResource> getServiceResourceAssertData() {
        return getServiceResourceAssertData(true);
    }

    private List<Service> getServiceAssertData(boolean withFirst) throws ParseException {
        Employee e1 = new Employee();
        e1.setId(0);
        e1.setName("Hubert Sauerampfer");
        e1.setLatitude("48.405560");
        e1.setLongitude("13.528200");

        Employee e2 = new Employee();
        e2.setId(1);
        e2.setName("Franz Mayer");
        e2.setLatitude("47.972800");
        e2.setLongitude("13.033750");

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        Service s1 = new Service();
        s1.setId(0);
        s1.setName("Putzen");
        s1.setEmployee(e1);
        s1.setDate(sdf.parse("09.03.2019 12:33"));
        s1.setLatitude("48.215802");
        s1.setLongitude("13.627451");

        Service s2 = new Service();
        s2.setId(1);
        s2.setName("Rasenmaehen");
        s2.setDate(sdf.parse("09.04.2019 13:37"));
        s2.setEmployee(e1);
        s2.setLatitude("48.248112");
        s2.setLongitude("13.723441");

        Service s3 = new Service();
        s3.setId(2);
        s3.setName("Heckenschneiden");
        s3.setDate(sdf.parse("22.04.2019 18:00"));
        s3.setEmployee(e2);
        s3.setLatitude("48.310845");
        s3.setLongitude("14.401865");

        List<Service> result = new ArrayList<>();

        if (withFirst) {
            result.add(s1);
        }
        result.add(s2);
        result.add(s3);
        return result;
    }

    private List<ServiceResource> getServiceResourceAssertData(boolean withFirst) {


        ServiceResource s1 = new ServiceResource();
        s1.setId(0);
        s1.setName("Putzen");
        s1.setEmployeeId(0);
        s1.setDate("09.03.2019 12:33");
        s1.setLatitude("48.215802");
        s1.setLongitude("13.627451");

        ServiceResource s2 = new ServiceResource();
        s2.setId(1);
        s2.setName("Rasenmaehen");
        s2.setDate("09.04.2019 13:37");
        s2.setEmployeeId(0);
        s2.setLatitude("48.248112");
        s2.setLongitude("13.723441");

        ServiceResource s3 = new ServiceResource();
        s3.setId(2);
        s3.setName("Heckenschneiden");
        s3.setDate("22.04.2019 18:00");
        s3.setEmployeeId(1);
        s3.setLatitude("48.310845");
        s3.setLongitude("14.401865");

        List<ServiceResource> result = new ArrayList<>();

        if (withFirst) {
            result.add(s1);
        }
        result.add(s2);
        result.add(s3);
        return result;
    }

    private List<Employee> getEmployeeAssertData() {
        Employee e1 = new Employee();
        e1.setId(0);
        e1.setName("Hubert Sauerampfer");
        e1.setLatitude("48.405560");
        e1.setLongitude("13.528200");

        Employee e2 = new Employee();
        e2.setId(1);
        e2.setName("Franz Mayer");
        e2.setLatitude("47.972800");
        e2.setLongitude("13.033750");

        List<Employee> result = new ArrayList<>();

        result.add(e1);
        result.add(e2);

        return result;
    }

}
