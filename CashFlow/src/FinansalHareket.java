import java.time.LocalDate;

public class FinansalHareket {
     private int id ;
     private String aciklama;
     private double miktar;
     private String tip;
     private LocalDate tarih;
     public FinansalHareket() {
    	 
     }
     
	public FinansalHareket(int id, String aciklama, double miktar, String tip, LocalDate tarih) {
		
		this.id = id;
		this.aciklama = aciklama;
		this.miktar = miktar;
		this.tip = tip;
		this.tarih = tarih;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public double getMiktar() {
		return miktar;
	}

	public void setMiktar(double miktar) {
		this.miktar = miktar;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public LocalDate getTarih() {
		return tarih;
	}

	public void setTarih(LocalDate tarih) {
		this.tarih = tarih;
	}
	

	@Override
	public String toString() {
		return "FinansalHareket [id=" + id + ", aciklama=" + aciklama + ", miktar=" + miktar + ", tip=" + tip
				+ ", tarih=" + tarih + "]";
	}

	public static void main(String[] args) {
		

	}

}
