import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HareketDAO {
     public void hareketEkle(FinansalHareket hareket) {
      String sql = "INSERT INTO hareketler  VALUES (?, ?, ?, ?)";
      try(Connection conn = DBManagerConn.getConnection();
    		  PreparedStatement pstmt = conn.prepareStatement(sql)) {
    	  pstmt.setString(1, hareket.getAciklama());
    	  pstmt.setDouble(2, hareket.getMiktar());
    	  pstmt.setString(3, hareket.getTip());
          pstmt.setDate(4, Date.valueOf(hareket.getTarih()));
          int satirEtkilendi = pstmt.executeUpdate();
          if(satirEtkilendi >0) {
        	  System.out.println("Finansal hareket başarıyla eklendi.");
          }
	} catch (SQLException e) {
		System.out.println("Veritabanına ekleme başarısız  "+e.getMessage());
		e.printStackTrace();
	}
     }
     public static void hareketSil(int hareketId) {
    	 String sql = "Delete from hareketler where id = ?";
    	 try(Connection conn = DBManagerConn.getConnection();
    			 PreparedStatement pstmt = conn.prepareStatement(sql)){
    		 pstmt.setInt(1, hareketId);
    		 int satirEtkilendi = pstmt.executeUpdate();
    		 if(satirEtkilendi >0) {
    			 System.out.println("Finansal hareket başarıyla silindi.");
    		 }
    	 }catch (SQLException e) {
    		 System.out.println("Veritabanında silme hatası  "+e.getMessage());
    		 e.printStackTrace();
    	 }
     }
     public List<FinansalHareket>tumHareketleriGetir(){
    	 List<FinansalHareket> hareketListesi = new ArrayList<FinansalHareket>();
    	 String sql = "SELECT id,aciklama,miktar,tip,tarih from hareketler ORDER BY TARİH DESC";
    	 try (Connection conn = DBManagerConn.getConnection();
    			 Statement stmt = conn.createStatement();
    			 ResultSet rs = stmt.executeQuery(sql)) {
    		 while(rs.next()) {
    			 FinansalHareket hareket = new FinansalHareket();
    			 hareket.setId(rs.getInt("id"));
    			 hareket.setAciklama(rs.getString("aciklama"));
    			 hareket.setMiktar(rs.getDouble("miktar"));
    			 hareket.setTip(rs.getString("tip"));
    			 hareket.setTarih(rs.getDate("tarih").toLocalDate());
    			 hareketListesi.add(hareket);
    			 
    		 }
			
		} catch (SQLException e) {
			System.out.println("Veri çekme başarısız  "+e.getMessage());
			e.printStackTrace();
		}
		return hareketListesi;
    	 
     }
	public static void main(String[] args) {
		

	}

}
