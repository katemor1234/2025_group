package ie.mtu.mtu_2025_demintia;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseConnection {

    private static final String DB_DIR = "db";
    private static final String DB_PATH = DB_DIR + "/users.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    private static Connection conn;

    private DatabaseConnection() {}

    public static synchronized Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            new File(DB_DIR).mkdirs();
            conn = DriverManager.getConnection(URL);
            try (Statement st = conn.createStatement()) {
                st.execute("PRAGMA foreign_keys = ON");
            } catch (SQLException ignore) {}
        }
        return conn;
    }

    public static void initSchema() {
        new File(DB_DIR).mkdirs();
        try (Connection c = getConnection(); Statement st = c.createStatement()) {
            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS users(
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  username TEXT UNIQUE NOT NULL,
                  password_hash TEXT NOT NULL
                )
            """);

            try (PreparedStatement ps = c.prepareStatement(
                    "INSERT OR IGNORE INTO users(username, password_hash) VALUES (?, ?)")) {
                ps.setString(1, "admin");
                ps.setString(2, hashPassword("admin123"));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String hashPassword(String plain) {
        if (plain == null) plain = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(plain.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    public static synchronized void close() {
        if (conn != null) {
            try { conn.close(); } catch (SQLException ignore) {}
            conn = null;
        }
    }
}