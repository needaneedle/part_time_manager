package partTimer_Manager;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PaysDetail extends JFrame {
	
	
	WorkerDAO workerDAO = new WorkerDAO();
	int wage ;
	
	public PaysDetail(JTable trow) {
		
		setTitle("급여내역 상세보기");
		
		JPanel conpan = new JPanel(null);
		conpan.setPreferredSize(new Dimension(300,400));
		
		
		int index = trow.getSelectedRow();	
		String m = ""+trow.getValueAt(index, 0);
		int d = Integer.parseInt(""+trow.getValueAt(index, 1));
		String n = ""+trow.getValueAt(index, 2);
		
		int wage = workerDAO.getwage(d);
		float ex = Float.parseFloat(""+trow.getValueAt(index, 4));
		float basicpay = (Float.parseFloat(""+trow.getValueAt(index, 3)) - ex)*wage;
		int extrapay = (int) ((ex*1.5)*wage);
		
		
		JLabel dltt = new JLabel(n + "님의 " +m+" 급여 상세 내역");
		JLabel dlwage = new JLabel("- 시급 : " + wage+ " 원");
		JLabel dlbs = new JLabel("- 기본 근무 수당: " + (int) basicpay + " 원");
		JLabel dlex = new JLabel("- 추가 근무 수당(1.5배) : "+ (int) extrapay+" 원");
		JLabel dltotal = new JLabel(">>> "+m+" 총 급여 : " +((int)basicpay+extrapay)+ " 원");
		
		
		
		JButton btnok =  new JButton("확인");
		
		btnok.addActionListener(e-> {
			dispose();
		});
		
		btnok.setBounds(100, 270, 80, 40);
		dltt.setBounds(40, 50, 200, 22);
		dlwage.setBounds(50, 90, 200, 22);
		dlbs.setBounds(50, 130, 200, 22);
		dlex.setBounds(50, 170, 200, 22);
		dltotal.setBounds(50, 210, 200, 22);
		
		
		conpan.add(btnok);
		conpan.add(dltt);
		conpan.add(dlwage);
		conpan.add(dlbs);
		conpan.add(dlex);
		conpan.add(dltotal);
		
		add(conpan);
		setSize(300,400);
		setLocationRelativeTo(null);
        setResizable(false);
		setVisible(true);
	}


}
