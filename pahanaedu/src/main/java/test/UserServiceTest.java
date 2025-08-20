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


public class UserServiceTest {
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
    void getAllUsers_returnsList() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("id")).thenReturn(1);
            when(rs.getString("name")).thenReturn("Admin");
            when(rs.getString("email")).thenReturn("admin@x");
            when(rs.getString("role")).thenReturn("ADMIN");

            userService svc = new userService();
            List<user> out = svc.getAllUsers();
            assertEquals(1, out.size());
            assertEquals("Admin", out.get(0).getName());
        }
    }

    @Test
    void registerUser_insertsHashedPassword() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            user u = new user();
            u.setName("X"); u.setEmail("x@y"); u.setTelephone("077"); u.setPassword("secret"); u.setRole("ADMIN");

            userService svc = new userService();
            assertDoesNotThrow(() -> svc.registerUser(u));
            verify(ps, atLeastOnce()).setString(eq(4), anyString()); // password param set
        }
    }

    @Test
    void loginUser_returnsUser_whenCredentialsMatch() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            when(rs.getInt("id")).thenReturn(2);
            when(rs.getString("name")).thenReturn("X");
            when(rs.getString("email")).thenReturn("x@y");
            when(rs.getString("role")).thenReturn("ADMIN");

            userService svc = new userService();
            user out = svc.loginUser("x@y", "secret");
            assertNotNull(out);
            assertEquals("x@y", out.getEmail());
        }
    }

    @Test
    void updateUser_returnsTrue_whenUpdateSucceeds() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            user u = new user();
            u.setId(5); u.setName("Y"); u.setEmail("y@y"); u.setTelephone("071"); u.setRole("STAFF");

            userService svc = new userService();
            assertTrue(svc.updateUsers(u));
        }
    }

    @Test
    void deleteUser_returnsTrue_whenDeleteSucceeds() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            userService svc = new userService();
            assertTrue(svc.deleteUser(1));
        }
    }
}
