package test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.Answer;
import org.mockito.invocation.InvocationOnMock;

import java.sql.*;
import java.util.*;

import services.*;
import model.*;
import controler.DBConnect;


public class CustomerServiceTest {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @BeforeEach
    void init() {
        conn = mock(Connection.class);
        ps = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
    }

    @Test
    void getAllCustomers_returnsList() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true, true, false);
            when(rs.getInt("id")).thenReturn(1, 2);
            when(rs.getString("account_number")).thenReturn("ACC1", "ACC2");
            when(rs.getString("name")).thenReturn("Alice", "Bob");
            when(rs.getString("address")).thenReturn("A", "B");
            when(rs.getString("telephone")).thenReturn("077", "071");
            when(rs.getString("email")).thenReturn("a@x", "b@y");

            customerService svc = new customerService();
            var out = svc.getAllCustomers();
            assertEquals(2, out.size());
            assertEquals("ACC1", out.get(0).getAccountNumber());
        }
    }

    @Test
    void getNextAccountNumber_parsesNumericSuffix() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            when(rs.getString(1)).thenReturn("ACC0099");

            customerService svc = new customerService();
            String next = svc.getNextAccountNumber();
            assertNotNull(next);
            assertTrue(next.startsWith("ACC"));
        }
    }

    @Test
    void addCustomer_returnsTrue_whenInsertSucceeds() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            customer c = new customer();
            c.setAccountNumber("ACC1"); c.setName("N"); c.setAddress("A"); c.setTelephone("T"); c.setEmail("E");

            customerService svc = new customerService();
            assertTrue(svc.addCustomer(c));
        }
    }

    @Test
    void updateCustomer_returnsTrue_whenUpdateSucceeds() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            customer c = new customer();
            c.setId(9); c.setAccountNumber("ACC1"); c.setName("N"); c.setAddress("A"); c.setTelephone("T"); c.setEmail("E");

            customerService svc = new customerService();
            assertTrue(svc.updateCustomer(c));
        }
    }

    @Test
    void deleteCustomer_returnsTrue_whenDeleteSucceeds() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            customerService svc = new customerService();
            assertTrue(svc.deleteCustomer(3));
        }
    }
}
