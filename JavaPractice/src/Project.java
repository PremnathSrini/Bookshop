import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

@SuppressWarnings({ "serial", "unused" })
public class Project extends JFrame {

	private JPanel contentPane;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbid;
	private JTable table;
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project frame = new Project();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void Connect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/bookshop","root","");
			System.out.println("CONNECTED SUCCESSFULY!!!");
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void table_load()
	{
		try 
		{
			pst=con.prepareStatement("Select * from books");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	/**
	 * Create the frame.
	 */
	public Project() {
		setBackground(Color.WHITE);
		Connect();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 511);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOKSHOP");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(332, 21, 177, 40);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(57, 85, 372, 211);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BOOK NAME");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(32, 37, 118, 29);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("EDITION");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(32, 85, 118, 29);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("PRICE");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(32, 135, 118, 29);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setBounds(172, 37, 177, 26);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(172, 88, 177, 26);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(172, 138, 177, 26);
		panel.add(txtprice);
		
		JButton btnsave = new JButton("SAVE");
		btnsave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String bname,edition,price;
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				
				try
				{
					pst=con.prepareStatement("insert into books(bname,edition,price)values(?,?,?)");
					pst.setString(1,bname);
					pst.setString(2,edition);
					pst.setString(3,price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Added Succesfully!!!");
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
		btnsave.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnsave.setBounds(67, 310, 96, 40);
		contentPane.add(btnsave);
		
		JButton btnExit = new JButton("VIEW");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load();
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnExit.setBounds(197, 310, 96, 40);
		contentPane.add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				txtbid.setText("");
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnClear.setBounds(320, 310, 96, 40);
		contentPane.add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(57, 378, 372, 83);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("BOOK ID");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(29, 31, 118, 29);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try
				{
					String id=txtbid.getText();
					
					pst=con.prepareStatement("select bname,edition,price from books where id=?");
					pst.setString(1, id);
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
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(170, 31, 177, 26);
		panel_1.add(txtbid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(458, 96, 392, 277);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,bid;
				bname=txtbname.getText();
				edition=txtedition.getText();
				price=txtprice.getText();
				bid=txtbid.getText();
				
				try
				{
					pst=con.prepareStatement("update books set bname=?,edition=?,price=? where id=?");
					pst.setString(1,bname);
					pst.setString(2,edition);
					pst.setString(3,price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Updated!!!");
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
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnUpdate.setBounds(535, 401, 109, 40);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String bid=txtbid.getText();
				
				try
				{
					pst=con.prepareStatement("delete from books where id=?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Deleted!!!");
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
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDelete.setBounds(687, 401, 109, 40);
		contentPane.add(btnDelete);
	}
}
