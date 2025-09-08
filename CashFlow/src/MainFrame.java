import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {
	private JTextField aciklamaJF;
       private JTextField miktarJF;
       private JComboBox<String> tipcx;
       private JButton eklebtn;
       private JTable hareketlerTable;
       private JScrollPane tableSP;
       private HareketDAO hareketDAO;
       private DefaultTableModel tableModel;
       private JButton silbtn;
	
	
       public MainFrame() {
    	   setTitle("Finans Takip Uygulaması");
    	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	   setSize(800,600);
    	   setLocationRelativeTo(null);
    	   hareketDAO = new HareketDAO();
    	   JPanel mainPanel = new JPanel(new BorderLayout());
    	   JPanel formPanel = new JPanel(new GridLayout(4,2,5,5));
    	   aciklamaJF = new JTextField(20);
    	   miktarJF = new JTextField(20);
    	   tipcx = new JComboBox<String>(new String[] {"Gelir","Gider"});
    	   eklebtn = new JButton("Ekle");
    	   silbtn= new JButton("Sil");
    	   
    	   formPanel.add(new JLabel("Açıklama"));
    	   formPanel.add(aciklamaJF);
    	   formPanel.add(new JLabel("Miktar"));
    	   formPanel.add(miktarJF);
    	   formPanel.add(new JLabel("Tip"));
    	   formPanel.add(tipcx);
    	   formPanel.add(new JLabel(""));
    	   JPanel buttonpanel = new JPanel();
    	   buttonpanel.add(eklebtn);
    	   buttonpanel.add(silbtn);
    	   formPanel.add(buttonpanel);
    	   mainPanel.add(formPanel,BorderLayout.NORTH);
    	   
    	   
    	   String[]columnNames = {"ID","Açıklama","Miktar","Tip","Tarih"};
    	   tableModel = new DefaultTableModel(columnNames, 0); 
    	   hareketlerTable = new JTable(tableModel);
    	   tableSP = new JScrollPane(hareketlerTable);
    	   mainPanel.add(tableSP, BorderLayout.CENTER);
    	   
    	   eklebtn .addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String aciklama = aciklamaJF.getText();
					double miktar = Double.parseDouble(miktarJF.getText());
					String tip = (String)tipcx.getSelectedItem();
					LocalDate tarih = LocalDate.now();
					if (aciklama.isEmpty() || miktar <=0) {
						JOptionPane.showMessageDialog(MainFrame.this, "Lütfen geçerli bir açıklama ve miktar girin","Hata",JOptionPane.ERROR_MESSAGE);
						return ;
					}
					FinansalHareket yeniHareket = new FinansalHareket(0,aciklama,miktar,tip,tarih);
					hareketDAO.hareketEkle(yeniHareket);
					aciklamaJF.setText("");
					miktarJF.setText("");
					tabloyuYenile();
				} catch (NumberFormatException ex) {
				  JOptionPane.showMessageDialog(MainFrame.this, "Miktar geçerli bir sayı olmalıdır." , "Hata", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		 	
		});
    	   silbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int seciliSatir = hareketlerTable.getSelectedRow();
				if(seciliSatir >=0) {
					int hareketId = (int) tableModel.getValueAt(seciliSatir,0);
					HareketDAO.hareketSil(hareketId);
					tabloyuYenile();
				}else {
					JOptionPane.showMessageDialog(MainFrame.this,"Lütfen silinecek bir hareket seçin","Uyarı", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
    	   
    	   add(mainPanel);
    	   setVisible(true);
    	   tabloyuYenile();
       }
       
	private void tabloyuYenile() {
		 tableModel.setRowCount(0);
		 List<FinansalHareket> hareketler = hareketDAO.tumHareketleriGetir();
		 for (FinansalHareket hareket : hareketler) {
		        Object[] rowData = {
		            hareket.getId(),
		            hareket.getAciklama(),
		            hareket.getMiktar(),
		            hareket.getTip(),
		            hareket.getTarih()
		        };
		        tableModel.addRow(rowData);
		 }
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainFrame();
				
			}
		});

	}

}
