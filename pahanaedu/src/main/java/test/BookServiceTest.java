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


public class BookServiceTest {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private MockitoSession session;

    @BeforeEach
    void init() {
        session = Mockito.mockitoSession().initMocks(this).startMocking();
        conn = mock(Connection.class);
        ps = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
    }

    @AfterEach
    void tearDown() throws Exception {
        session.finishMocking();
    }

    @Test
    void getAllBooks_returnsList_whenRowsExist() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true, true, false);
            when(rs.getInt("id")).thenReturn(1, 2);
            when(rs.getString("title")).thenReturn("A", "B");
            when(rs.getString("author")).thenReturn("X", "Y");
            when(rs.getDouble("price")).thenReturn(10.0, 20.0);
            when(rs.getInt("qty")).thenReturn(5, 7);
            when(rs.getString("category")).thenReturn("Cat1", "Cat2");

            bookService svc = new bookService();
            List<book> out = svc.getAllBooks();
            assertEquals(2, out.size());
            assertEquals("A", out.get(0).getTitle());
            assertEquals("B", out.get(1).getTitle());
        }
    }

    @Test
    void searchBooks_matchesByTitleOrAuthorOrCategory() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("id")).thenReturn(3);
            when(rs.getString("title")).thenReturn("Clean Code");
            when(rs.getString("author")).thenReturn("Robert Martin");
            when(rs.getDouble("price")).thenReturn(45.0);
            when(rs.getInt("qty")).thenReturn(12);
            when(rs.getString("category")).thenReturn("Programming");

            bookService svc = new bookService();
            List<book> out = svc.searchBooks("clean");
            assertEquals(1, out.size());
            assertTrue(out.get(0).getTitle().toLowerCase().contains("clean"));
        }
    }

    @Test
    void searchBooks_returnsEmpty_whenNoRows() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);

            bookService svc = new bookService();
            List<book> out = svc.searchBooks("none");
            assertTrue(out.isEmpty());
        }
    }

    @Test
    void addBook_returnsTrue_whenInsertSucceeds() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            book b = new book();
            b.setTitle("T"); b.setAuthor("A"); b.setPrice(10.0); b.setQty(2); b.setCategory("C");

            bookService svc = new bookService();
            assertTrue(svc.addBook(b));
        }
    }

    @Test
    void updateBook_returnsTrue_whenUpdateSucceeds() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            book b = new book();
            b.setId(5); b.setTitle("T"); b.setAuthor("A"); b.setPrice(15.0); b.setQty(3); b.setCategory("C");

            bookService svc = new bookService();
            assertTrue(svc.updateBook(b));
        }
    }

    @Test
    void deleteBook_returnsTrue_whenDeleteSucceeds() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            bookService svc = new bookService();
            assertTrue(svc.deleteBook(10));
        }
    }
}
