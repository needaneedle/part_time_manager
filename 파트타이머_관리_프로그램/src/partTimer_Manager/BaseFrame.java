package partTimer_Manager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class BaseFrame extends JFrame {
		
	
	BaseFrame(){
		
		setTitle("��ƮŸ�̸� ���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,500);
		setLocationRelativeTo(null);
        setResizable(false);
		show_main();

		setVisible(true);

	}
	
	private void show_main() {
		this.setLayout(null);
		JPanel mainPan = new JPanel(null);
		JButton btn_manager = new JButton("������");
		JButton btn_part = new JButton("�Ƹ�����Ʈ");
		JLabel title = new JLabel("��ƮŸ�̸� ���� ���α׷�");
		btn_manager.addActionListener(e -> {
				AdminFrame ad = new AdminFrame();
				ad.setVisible(true);
				setVisible(false);
		
		});
		
		btn_part.addActionListener(e -> {
			new WorkerFrame();
			setVisible(false);
		});
		
		mainPan.setPreferredSize(new Dimension(300,400));
		title.setBounds(70, 50, 150, 22);
		btn_manager.setBounds(70, 150, 150, 100);
		btn_part.setBounds(70, 40, 150, 100);
		mainPan.setBounds(0, 70, 300, 300);
		
		mainPan.add(btn_part);
		mainPan.add(btn_manager);
		this.add(title);



		this.add(mainPan, "Center");
	}

	public static void main(String[] args) {
		new BaseFrame();

	}

}
