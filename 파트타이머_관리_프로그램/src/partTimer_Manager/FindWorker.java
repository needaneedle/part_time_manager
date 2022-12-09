package partTimer_Manager;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FindWorker extends JFrame {
	
	WorkerDAO findWorkerDAO = new WorkerDAO();
	Worker rewk;
	String wname;
	String wid;
	
	DefaultTableModel ftmodel;
	JTable findtbl;
	String[] title = {"���","�̸�","����","�޿�(�ñ�)","�޸�"};
	String tbl = "workertbl";
	Container fc = this.getContentPane();
	
	
	JLabel lName = new JLabel("�̸�");
	JTextField aName = new JTextField("",10); 
	JLabel lId = new JLabel("���");
	JTextField aId = new JTextField("",10); 
	JButton search = new JButton("��ȸ");
	JButton rest = new JButton("�ʱ�ȭ");
	
	

	public FindWorker(String wid, String wname) {
		this.wid = wid;
		this.wname = wname;

		openform(wid,wname);

	}


	private void openform(String wid, String wname) {
		
		setTitle("����˻�");
		this.setSize(600,400);
		setLocationRelativeTo(null);
        setResizable(false);

		JPanel formPan = new JPanel(null);
		formPan.setPreferredSize(new Dimension(600, 100));
		
		//�� ��ġ
		lName.setBounds(45, 50, 80, 22);
		aName.setBounds(85, 50, 100, 22);
		formPan.add(lName);
		formPan.add(aName);

		lId.setBounds(200, 50, 80, 22);
		aId.setBounds(240, 50, 100, 22);
		formPan.add(lId);
		formPan.add(aId);
		
		search.setBounds(360, 50, 60, 22);
		rest.setBounds(430, 50, 75, 22);
		
		aName.setText(this.wname);
		aId.setText(this.wid);
		
		formPan.add(search);
		formPan.add(rest);
		
		
		// ���̺� 
		JPanel tblPan = new JPanel(null);
		tblPan.setPreferredSize(new Dimension(600,300));

		ftmodel = tblList(aId.getText(), aName.getText());
		findtbl = new JTable(ftmodel);
		
		JScrollPane scroll = new JScrollPane(findtbl);
		scroll.setBounds(45,20, 480, 220);
		tblPan.add(scroll);
		
		
		search.addActionListener(e -> {
			ftmodel = tblList(aId.getText(), aName.getText());
			findtbl.setModel(ftmodel);
		});
		
		rest.addActionListener(e -> {
			
			aName.setText("");
			aId.setText("");

			ftmodel = tblList("", "");
			findtbl.setModel(ftmodel);
		});
		
		findtbl.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					JTable t = (JTable) e.getSource();
					int index = t.getSelectedRow();

					String d = ""+t.getValueAt(index, 0);
					String n = ""+t.getValueAt(index, 1);
					int a = Integer.parseInt(""+ t.getValueAt(index, 2));
					int wa = Integer.parseInt(""+t.getValueAt(index, 3));
					String m = ""+t.getValueAt(index, 4); 

					rewk = new Worker(d, n, a, wa, m);
					
					dispose();
				}
			}
		});

		
		this.add(tblPan,"Center");
		this.add(formPan, "North");

		setVisible(true);
	}
	
	
	private DefaultTableModel tblList(String d, String n) {
		
		if (d.equals("") && n.equals("")) { //�ʵ尡 ��� �������
			ftmodel = findWorkerDAO.makeAllTbl(tbl, title);
		} else if(!(d.equals("")) && !(n.equals(""))) { // ��� �ʵ尡 �ԷµǾ�����.
			ftmodel = findWorkerDAO.workerSearch(tbl,new Worker(d,n), 1, title);
		}else if(!(d.equals("")) && n.equals("")) { // ���̵� ����
			ftmodel = findWorkerDAO.workerSearch(tbl,new Worker(d,""), 2, title);
		}else if(d.equals("") && !(n.equals(""))) { //�̸�������
			ftmodel = findWorkerDAO.workerSearch(tbl,new Worker("",n), 3, title);
		}
		
		return ftmodel;
	}
	
	
	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}
	public Worker getWorker() {
		return rewk;

	}
	

//	public static void main(String[] args) {
//		new FindWorker();
//		
//	}
	
}

