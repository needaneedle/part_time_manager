package partTimer_Manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class AdminFrame extends JFrame {
	WorkerDAO workerDAO = new WorkerDAO();
	Container c = this.getContentPane();
	JPanel topMenuPanel = new JPanel();
	JPanel centerPanel = new JPanel();

	JPanel formPan;
	JPanel tablePan;
	DefaultTableModel model;

	JLabel titles = new JLabel("");
	JLabel lName = new JLabel("�̸�");
	JTextField aName;
	JLabel lId = new JLabel("���");
	JTextField aId;
	JButton wsearch;
	JButton search;
	JButton rest;
	JButton save;
	JButton regi = new JButton("�űԵ��");

	JTable workertable;
	String tbl;

	JFormattedTextField aStart;
	JFormattedTextField aEnd;
	JFormattedTextField aDate;

	Calendar cal;
	
	String wn;


	public AdminFrame() {
		setTitle("������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		centerPanel.setLayout(new BorderLayout());
		setSize(690, 600);
		setLocationRelativeTo(null);
        setResizable(false);
		
		admenu();
		c.add(centerPanel, "Center");
		setVisible(true);

	}

	private void admenu() {

		JButton btn1 = new JButton("��������");
		JButton btn2 = new JButton("������");
		JButton btn3 = new JButton("�޿���ȸ");
		JButton btnre = new JButton("<- ���ư���");
		JButton btnexit = new JButton("����");
		btn1.addActionListener(e -> {
			btn1();
		});
		btn2.addActionListener(e -> {
			btn2();
		});
		btn3.addActionListener(e -> {
			btn3();
		});
		btnre.addActionListener(e -> {
			new BaseFrame();
			this.setVisible(false);
		});
		btnexit.addActionListener(e -> {
			System.exit(0);
		});

		topMenuPanel.add(btnre);
		topMenuPanel.add(btn1);
		topMenuPanel.add(btn2);
		topMenuPanel.add(btn3);
		topMenuPanel.add(btnexit);

		c.add(topMenuPanel, "North");
	}

	// (�̸� ��� ��) + (�˻� �ʱ�ȭ ��ư)
	JPanel makeform() {
		formPan = new JPanel();
		formPan.setLayout(null);
		formPan.setPreferredSize(new Dimension(690, 200));

		rest = new JButton("�ʱ�ȭ");
		save = new JButton("����");
		wsearch = new JButton("�����ȸ");
		search = new JButton("��ȸ");

		aId = new JTextField("", 10);
		aName = new JTextField("", 10);

		// ����
		titles.setBounds(45, 15, 80, 22);
		// �� ��ġ
		lName.setBounds(60, 50, 80, 22);
		aName.setBounds(100, 50, 100, 22);
		lId.setBounds(220, 50, 80, 22);
		aId.setBounds(260, 50, 100, 22);

		// ��ư ��ġ
		wsearch.setBounds(400, 50, 85, 22);
		rest.setBounds(440, 150, 75, 22);

		formPan.add(titles);
		formPan.add(lName);
		formPan.add(aName);
		formPan.add(lId);
		formPan.add(aId);
		formPan.add(wsearch);
		formPan.add(rest);

		aId.setEnabled(false);

		return formPan;

	}

	void btn1() {

		String[] title = { "���", "�̸�", "����", "�޿�(�ñ�)", "�޸�" };
		tbl = "workertbl";

		centerPanel.removeAll();
		centerPanel.setLayout(new BorderLayout());

		formPan = makeform();
		aName.setEnabled(true);
		titles.setText("��������");

		JLabel lAge = new JLabel("����");
		JTextField aAge = new JTextField(10);
		JLabel lWage = new JLabel("�޿�");
		JTextField aWage = new JTextField(10);
		JLabel lMemo = new JLabel("�޸�");
		JTextArea aMemo = new JTextArea(3, 18);

		lAge.setBounds(60, 90, 80, 22);
		aAge.setBounds(100, 90, 100, 22);
		lWage.setBounds(220, 90, 80, 22);
		aWage.setBounds(260, 90, 100, 22);
		lMemo.setBounds(60, 130, 80, 22);
		aMemo.setBounds(100, 130, 260, 50);

		formPan.add(lAge);
		formPan.add(aAge);
		formPan.add(lWage);
		formPan.add(aWage);
		formPan.add(lMemo);
		formPan.add(aMemo);

		// ��ư ��ġ
		save.setBounds(525, 150, 60, 22);
		regi.setBounds(500, 50, 85, 22);

		formPan.add(save);
		formPan.add(regi);

		// ���̺�
		tablePan = new JPanel(null);
		model = workerDAO.makeAllTbl(tbl, title);
		workertable = new JTable(model);

		JScrollPane scroll = new JScrollPane(workertable);
		scroll.setBounds(60, 30, 560, 220);
		tablePan.add(scroll);

		// ���� ������ �ؽ�Ʈ �ʵ忡 ���� �ѷ���
		workertable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable) e.getSource();
				int index = t.getSelectedRow();

				aId.setText("" + t.getValueAt(index, 0));
				aName.setText("" + t.getValueAt(index, 1));
				aAge.setText("" + t.getValueAt(index, 2));
				aWage.setText("" + t.getValueAt(index, 3));
				aMemo.setText(t.getValueAt(index, 4) == null ? " " : "" + t.getValueAt(index, 4));
				

			}
		});

		// btn �̺�Ʈ ��ư �̺�Ʈ event
		wsearch.addActionListener(e -> {
			FindWorker fw = new FindWorker(aId.getText(), aName.getText());
			fw.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					Worker wk = fw.getWorker();

					aId.setText(wk.getWorkerId());
					aName.setText(wk.getName());
					aAge.setText("" + wk.getAge());
					aWage.setText("" + wk.getWage());
					aMemo.setText(wk.getMemo());

				}
			});
		});

		regi.addActionListener(e -> {
			int l = workertable.getRowCount() - 1;
			String d = "" + workertable.getValueAt(l, 0);
			System.out.println(d);
			RegisterWorker rw = new RegisterWorker(d);
			rw.regForm();
			rw.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosed(WindowEvent e) {
					model = workerDAO.makeAllTbl(tbl, title);
					workertable.setModel(model);

				}
			});

		});

		rest.addActionListener(e -> {
			System.out.println(1);
			btn1();

		});

		save.addActionListener(e -> {
			String d = aId.getText();
			String n = aName.getText();
			int a = Integer.parseInt(aAge.getText().equals("") ? "0" : aAge.getText());
			int wa = Integer.parseInt(aWage.getText().equals("") ? "0" : aWage.getText());
			String m = aMemo.getText();

			if (n.equals("") || a == 0 || wa == 0) {
				JOptionPane.showMessageDialog(null, "�׸� ���� �Է��ϼ���");
			} else {
				if (workerDAO.updateWorker(new Worker(d, n, a, wa, m)) > 0) {
					JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
					model = workerDAO.makeAllTbl(tbl, title);
					workertable.setModel(model);
				} else {
					JOptionPane.showMessageDialog(null, "�۾��� �����߽��ϴ�.");
				}
			}
		});

		centerPanel.add(formPan, "North");
		centerPanel.add(tablePan);
		centerPanel.validate();

	}

	void btn2() {
		String[] title = { "��ȣ", "��¥", "���", "�̸�", "��ٽð�", "��ٽð�" };
		String tbl = "hourstbl";

		centerPanel.removeAll();
		formPan = makeform();

		titles.setText("������");

		JLabel wStart = new JLabel("���");
		JLabel wEnd = new JLabel("���");
		JLabel wDate = new JLabel("��¥");
		JButton sdate = new JButton("��¥����");

		try {
			MaskFormatter dformat = new MaskFormatter("####-##-##");
			MaskFormatter tformat = new MaskFormatter("##:##:##");
			aStart = new JFormattedTextField(tformat);
			aEnd = new JFormattedTextField(tformat);
			aDate = new JFormattedTextField(dformat);

			aStart.setBounds(100, 150, 100, 22);
			aDate.setBounds(100, 100, 100, 22);
			aEnd.setBounds(260, 150, 100, 22);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		wDate.setBounds(60, 100, 80, 22);
		aDate.setBounds(100, 100, 100, 22);
		wStart.setBounds(60, 150, 80, 22);
		aStart.setBounds(100, 150, 100, 22);
		wEnd.setBounds(220, 150, 80, 22);
		aEnd.setBounds(260, 150, 100, 22);

		formPan.add(wStart);
		formPan.add(aStart);
		formPan.add(wEnd);
		formPan.add(aEnd);
		formPan.add(wDate);
		formPan.add(aDate);

		// ���� ��ư ��ġ
		save.setBounds(540, 150, 60, 22);
		search.setBounds(540, 50, 60, 22);
		wsearch.setBounds(440, 50, 85, 22);
		sdate.setBounds(220, 100, 85, 22);

		formPan.add(save);
		formPan.add(search);
		formPan.add(rest);
		formPan.add(sdate);

		aName.setEnabled(false);

		// ���̺�
		tablePan = new JPanel(null);
		model = workerDAO.makeAllTbl(tbl, title);
		workertable = new JTable(model);
		workertable.getColumnModel().getColumn(0).setPreferredWidth(15);

		JScrollPane scroll = new JScrollPane(workertable);
		scroll.setBounds(60, 30, 560, 220);
		tablePan.add(scroll);

		// ���� ������ �ؽ�Ʈ �ʵ忡 ���� �ѷ���
		workertable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				JTable t = (JTable) e.getSource();
				int index = t.getSelectedRow();

				aId.setText("" + t.getValueAt(index, 2));
				aName.setText("" + t.getValueAt(index, 3));
				aStart.setText("" + t.getValueAt(index, 4));
				aEnd.setText("" + t.getValueAt(index, 5));
				aDate.setText("" + t.getValueAt(index, 1));
				wn = ""+t.getValueAt(index, 0);
				System.out.println(wn);
			}
		});

		// ��ư �̺�Ʈ
		search.addActionListener(e -> {
			String wi = aId.getText();
			String wn = aName.getText();
			String wd = aDate.getText().equals("    -  -  ") ? "" : aDate.getText();

			if (!(wi.equals("")) && !(wn.equals("")) && !(wd.equals(""))) {
				model = workerDAO.workhoursSearch(title, 3, new WorkDT(wi, wn, wd));
			} else if (!(wi.equals("")) && !(wn.equals("")) && wd.equals("")) {
				model = workerDAO.workhoursSearch(title, 2, new WorkDT(wi, wn));
			} else if (wi.equals("") && wn.equals("") && !(wd.equals(""))) {
				model = workerDAO.workhoursSearch(title, 1, new WorkDT(wd));
			} else {
				model = workerDAO.makeAllTbl(tbl, title);
			}
			workertable.setModel(model);
			
		});

		wsearch.addActionListener(e -> {
			FindWorker fw = new FindWorker(aId.getText(), aName.getText());
			fw.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					Worker wk = fw.getWorker();

					aId.setText(wk.getWorkerId());
					aName.setText(wk.getName());

				}
			});

		});

		// �ʱ�ȭ
		rest.addActionListener(e -> {
			System.out.println(2);
			btn2();
		});

		// �����ư �̺�Ʈ
		save.addActionListener(e -> {
		
			String d = aId.getText();
			String n = aName.getText();
			String ws = aStart.getText().equals("  :  :  ") ? "" : aStart.getText();
			String we = aEnd.getText().equals("  :  :  ") ? "" : aEnd.getText();
			String wd = aDate.getText().equals("    -  -  ") ? "" : aDate.getText();
			if (ws.equals("") || we.equals("") || wd.equals("")) {
				JOptionPane.showMessageDialog(null, "�׸� ���� �Է��ϼ���");
			} else {
				if (workerDAO.updateWorkhours(new WorkDT(wn,d, n, ws, we, wd)) > 0) {
					JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
					model = workerDAO.makeAllTbl(tbl, title);
					workertable.setModel(model);
				} else {
					JOptionPane.showMessageDialog(null, "�۾��� �����߽��ϴ�.");
				}
			}
			workerDAO.timestmp(); 
		});

		sdate.addActionListener(e -> {
			CalendarCon date = new CalendarCon();
			date.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					aDate.setText(date.getSelectdate());

				}
			});

		});

		centerPanel.add(formPan, "North");
		centerPanel.add(tablePan);
		centerPanel.validate();

	}

	public void btn3() {
		String[] title = {"��¥(��)", "���", "�̸�", "�� �ٹ��ð�","�ʰ��ٹ�","�� �޿�"};
		centerPanel.removeAll();
		
		formPan = makeform();
		formPan.setPreferredSize(new Dimension(690,130));
		titles.setText("�޿�����");
		
		JPanel dataPan = new JPanel(null); 
		JLabel wDate = new JLabel("��¥(��)");
		
		cal = Calendar.getInstance(); 
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH)+1;
		 
		JComboBox<Integer> yearbox = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> years = new DefaultComboBoxModel<Integer>();
		JComboBox<Integer> monthbox = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> months = new DefaultComboBoxModel<Integer>();
		
		for(int i=y-22; i<=y+1; i++){ years.addElement(i); }
		yearbox.setModel(years);
		yearbox.setSelectedItem(y);
		
		for(int i=1; i<=12; i++) { months.addElement(i); }
		monthbox.setModel(months);
		monthbox.setSelectedItem(m);
		
		yearbox.setBounds(60, 0, 60, 23);
		monthbox.setBounds(125, 0, 40, 23);
		dataPan.setBounds(60, 100, 300, 25);
		wDate.setBounds(0, 0, 80, 22);
		
		dataPan.add(yearbox);
		dataPan.add(monthbox);


		//��ư ��ġ
		rest.setBounds(530, 100, 75, 22);
		search.setBounds(540, 50, 60, 22);
		wsearch.setBounds(440, 50, 85, 22);

		rest.addActionListener(e -> {
			btn3();
		});



		dataPan.add(wDate);
		formPan.add(dataPan);

		formPan.add(search);
		formPan.add(rest);
		
		aName.setEnabled(false);


		//���̺� 
		tablePan = new JPanel(null);
		model = new DefaultTableModel(title,0);
		workertable = new JTable(model);
		//�÷�������

		JScrollPane scroll = new JScrollPane(workertable);
		scroll.setBounds(60, 30, 560, 330);
		tablePan.add(scroll);

		// ���� ������ �ؽ�Ʈ �ʵ忡 ���� �ѷ��� 
		workertable.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable) e.getSource();
				int index = t.getSelectedRow();	
				if (e.getClickCount() ==1) {

			System.out.println(index);
					aId.setText(""+t.getValueAt(index, 1));
					aName.setText(""+t.getValueAt(index, 2));
				} else if(e.getClickCount() ==2) {
					new PaysDetail(t);
				}

			}
		});
		
		
		wsearch.addActionListener(e->{
			FindWorker fw = new FindWorker( aId.getText(),aName.getText());
			fw.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					Worker wk = fw.getWorker();
					
					aId.setText(wk.getWorkerId());
					aName.setText(wk.getName());
				}

			});
			});
			
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String wy = ""+yearbox.getSelectedItem();
				String wm = (int)monthbox.getSelectedItem() < 10? "0"+monthbox.getSelectedItem() : ""+monthbox.getSelectedItem();
				String wtm = wy+"-"+wm+"%";
				
				String d = aId.getText();
				
				if(d.equals("")) {
					model = workerDAO.calcpay(title, wtm, d,1);
				} else {
					model = workerDAO.calcpay(title, wtm, d,2);
				}
				workertable.setModel(model);
			}
		});


		centerPanel.add(formPan, "North");
		centerPanel.add(tablePan);
		centerPanel.validate();
		
		
		}
	}
