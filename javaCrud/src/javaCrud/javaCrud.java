package javaCrud;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class javaCrud {

	private JFrame frame;
	private JTextField txtprice;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTable table;
	private JTable table_1;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javaCrud window = new javaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public javaCrud() {
		initialize();
		Connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public void Connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/ javacrud","root","");
		}
		catch(ClassNotFoundException ex)
		{
			
		}
		catch(SQLException ex)
		{
			
		}
	}
	public void table_load()
	{
		try
		{
			pst=con.prepareStatement("select * from  book");
			rs=pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 828, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBookShop = new JLabel("Book Shop");
		lblBookShop.setBounds(325, 0, 169, 55);
		lblBookShop.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblBookShop);
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 68, 436, 206);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBookName.setBounds(35, 41, 99, 37);
		panel.add(lblBookName);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEdition.setBounds(35, 91, 76, 41);
		panel.add(lblEdition);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrice.setBounds(35, 141, 86, 41);
		panel.add(lblPrice);
		
		txtprice = new JTextField();
		txtprice.setBounds(176, 148, 248, 29);
		panel.add(txtprice);
		txtprice.setColumns(10);
		
		txtbname = new JTextField();
		txtbname.setColumns(10);
		txtbname.setBounds(176, 46, 248, 29);
		panel.add(txtbname);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(176, 101, 248, 29);
		panel.add(txtedition);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(185, 287, 128, 60);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSave = new JButton("Update");
		btnSave.setBounds(487, 361, 128, 60);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String bname,edition,price,bid;
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				bid=txtbid.getText();
				try
				{
					pst=con.prepareStatement("update book set name=?,edition=?,price=? where id=?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Update!!!!!");
					//table.load();
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbid.setText("");
					txtbname.requestFocus();
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		frame.getContentPane().add(btnSave);
		
		JButton btnClear = new JButton(" Clear");
		btnClear.setBounds(325, 287, 128, 60);
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				
			}
		});
		frame.getContentPane().add(btnClear);
		
		table = new JTable();
		table.setBounds(510, 328, 251, -225);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(465, 68, 333, 284);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(45, 360, 430, 72);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBookId = new JLabel("Book Id");
		lblBookId.setBounds(29, 24, 76, 20);
		lblBookId.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblBookId);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try
				{
					String id=txtbid.getText();
					pst=con.prepareStatement("select name,edition,price from book where id = ? ");
					pst.setString(1,id);
					ResultSet rs=pst.executeQuery();
					if(rs.next()==true)
					{
						String name=rs.getString(1);
						String edition=rs.getString(2);
						String price=rs.getString(3);
						
						txtbname.setText(name);
						txtedition.setText(edition);
						txtprice.setText(price);
						
					}
					else
					{
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				} 
				

			}
		});
		txtbid.setBounds(129, 23, 253, 22);
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bname,edition,price;
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				try
				{
					pst=con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnSave_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSave_1.setBounds(45, 287, 128, 60);
		frame.getContentPane().add(btnSave_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				bid=txtbid.getText();
				try
				{
					pst=con.prepareStatement("delete from book where id=?");
					
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
					//table.load();
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbid.setText("");
					txtbname.requestFocus();
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setBounds(655, 361, 128, 60);
		frame.getContentPane().add(btnDelete);
	}
}
