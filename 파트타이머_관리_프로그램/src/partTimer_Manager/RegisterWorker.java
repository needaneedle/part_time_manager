package partTimer_Manager;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class RegisterWorker extends JFrame {
	WorkerDAO wDAO = new WorkerDAO();
	String newId;
	
	public RegisterWorker(Object nId) {
		newId = ""+(Integer.parseInt((String) nId)+1);
		
		
				
	}
	
	
	void regForm(){
		setTitle("�űԵ��");

		
		
		JPanel formPan = new JPanel(null); 
		JLabel topTitle = new JLabel("�űԵ��");
		
		JLabel lName = new JLabel("�̸�");
		JTextField nName = new JTextField("",10); 
		JLabel lId = new JLabel("���");
		JTextField nId = new JTextField(newId,10); 

		JButton regi = new JButton("�űԵ��");
		JButton rest = new JButton("�ʱ�ȭ");

		JLabel lAge = new JLabel("����");
		JTextField aAge = new JTextField("",10);  
		JLabel lWage = new JLabel("�޿�");
		JTextField aWage = new JTextField("",10); 
		JLabel lMemo = new JLabel("�޸�");
		JTextArea aMemo =new JTextArea(3,18);
		

		topTitle.setBounds(110, 40, 80, 22);
		
		lId.setBounds(50, 80, 80, 22);
		nId.setBounds(110, 80, 120, 22);


		lName.setBounds(50, 120, 80, 22);
		nName.setBounds(110, 120, 120, 22);
		
		lAge.setBounds(50, 160, 80, 22);
		aAge.setBounds(110, 160, 120, 22);
		
		lWage.setBounds(50, 200, 80, 22);
		aWage.setBounds(110, 200, 120, 22);

		lMemo.setBounds(50, 250, 80, 22);
		aMemo.setBounds(110, 250, 120, 60);
		
		Border lineBorder = BorderFactory.createLineBorder(Color.gray,1);
		aMemo.setBorder(lineBorder);
		aMemo.setText("");
		
		regi.setBounds(180, 350, 90, 22);
		rest.setBounds(20, 350, 75, 22);
		
		nId.setEnabled(false);
		
		formPan.add(topTitle);
		formPan.add(lName);
		formPan.add(nName);
		formPan.add(lId);
		formPan.add(nId);
		formPan.add(lAge);
		formPan.add(aAge);
		formPan.add(lWage);
		formPan.add(aWage);		
		formPan.add(lMemo);
		formPan.add(aMemo);
		formPan.add(regi);
		formPan.add(rest);
		
		regi.addActionListener(e->{
			int c =0;

			try {
			
				if(nName.getText().equals("") || aAge.getText().equals("") || aWage.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "�̸�, ����, �޿��� �ʼ� �׸� �Դϴ�.");
					
					
				} else if(nName.getText().length() > 3) {
					JOptionPane.showMessageDialog(null, "�̸��� 4�� ���Ϸ� �ۼ� �����մϴ�.");
					
				} else {
					String d = nId.getText();
					String n = nName.getText();
					int a = Integer.parseInt(aAge.getText());
					int wa = Integer.parseInt(aWage.getText());
					String m = aMemo.getText(); 
					c = wDAO.workerInsert(new Worker(d, n, a, wa, m));
					if(c > 0) {
						
						JOptionPane.showMessageDialog(null, "����� �Ϸ�Ǿ����ϴ�.");
						dispose();
					} else {
						
						JOptionPane.showMessageDialog(null, "��Ͽ� �����߽��ϴ�.");
					}
					
				}
			} catch(NumberFormatException err) {
				JOptionPane.showMessageDialog(null, "����, �޿��� ���ڸ� �Է��� �����մϴ�.");
				
			}

			
		});
		
		rest.addActionListener(e -> {
			nId.setText("");
			nName.setText("");
			aAge.setText("");
			aWage.setText("");
			aMemo.setText("");
		});

		
		this.add(formPan);
		
		
		
		setSize(300,480);
		setLocationRelativeTo(null);
        setResizable(false);
		setVisible(true);
		

		

	}
	


//	public static void main(String[] args) {
//		new RegisterWorker();
//
//	}

}
