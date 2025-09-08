import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManagerConn {
	// Veritabanı bağlantı URL'si
    private static final String DB_URL ="jdbc:sqlserver://FURKANTOPBAS;databaseName=finans_takip;sendStringParametersAsUnicode=false;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Driver'ı yüklüyoruz.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Bağlantı kuruyoruz.
            conn = DriverManager.getConnection(DB_URL);
            
            System.out.println("Bağlantı başarıyla kuruldu.");
            
        } catch (SQLException e) {
            System.err.println("Veritabanı bağlantı hatası!");
            System.err.println("Hata Mesajı: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("SQL Server JDBC Driver bulunamadı!");
            e.printStackTrace();
        }
        return conn;
    }
	
}
