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


public class BillServiceTest {
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
    void getAllBills_returnsList() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("id")).thenReturn(1);
            when(rs.getString("customer_name")).thenReturn("Alice");
            when(rs.getTimestamp("bill_date")).thenReturn(new java.sql.Timestamp(System.currentTimeMillis()));
            when(rs.getDouble("total_amount")).thenReturn(100.0);
            when(rs.getString("books_purchased")).thenReturn("1x Clean Code");

            billService svc = new billService();
            var out = svc.getAllBills();
            assertEquals(1, out.size());
            assertEquals("Alice", out.get(0).getCustomerName());
        }
    }

    @Test
    void addBill_executesInsert() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            Bill b = new Bill();
            b.setCustomerName("X"); b.setTotalAmount(50.0); b.setBooksPurchased("book1");
            billService svc = new billService();
            assertDoesNotThrow(() -> svc.addBill(b));
        }
    }

    @Test
    void deleteBill_executesDelete() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeUpdate()).thenReturn(1);

            billService svc = new billService();
            assertDoesNotThrow(() -> svc.deleteBill(9));
        }
    }

    @Test
    void getSalesView_returnsAggregatedRows() throws Exception {
        try (MockedStatic<DBConnect> mocked = Mockito.mockStatic(DBConnect.class)) {
            mocked.when(DBConnect::getConnection).thenReturn(conn);
            when(conn.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true, true, false);
            when(rs.getString(1)).thenReturn("2025-08-18", "2025-08-19");
            when(rs.getInt(2)).thenReturn(2, 3);

            billService svc = new billService();
            var out = svc.getSalesView();
            assertEquals(2, out.size());
        }
    }
}
