package partTimer_Manager;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class WorkerFrame extends JFrame {
		
	
	
	WorkerDAO workerDAO = new WorkerDAO();
	Container c = this.getContentPane();

	JPanel formPan;
	JPanel tablePan;
	DefaultTableModel model;
	
	LocalDateTime datetime;
	
	
	JLabel lName = new JLabel("이름");
	JTextField aName = new JTextField("",10); 
	JLabel lId = new JLabel("사번");
	JTextField aId = new JTextField("",10); 


	JButton wstart = new JButton("출근");
	JButton wend = new JButton("퇴근");
	JButton	find = new JButton("사원검색");
	JButton	back = new JButton("<");
	
	JLabel now = new JLabel();
	
	JTable workertable;	
	String tbl;
	
	public WorkerFrame() {
		
		setTitle("아르바이트 근태");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel formpan = new JPanel(null);
		formpan.setPreferredSize(new Dimension(300,300));
		


		JLabel infott = new JLabel("※출근/퇴근 하시려면 [사원검색]을 해주세요");
		
		

		
		find.setBounds(100, 150, 95, 22);
		back.setBounds(3, 3, 50, 30);
		
		lName.setBounds(70, 80, 95, 22);
		lId.setBounds(70, 110, 95, 22);
		aName.setBounds(110, 80, 100, 22);
		aId.setBounds(110, 110, 100, 22);
		
		infott.setBounds(20,40,260,25);
		
		
		
		now.setBounds(92, 200, 115, 22);
		
		TimerThread ltime = new TimerThread(now);
		
		
		
		find.addActionListener(e -> {

			FindWorker fw = new FindWorker( aId.getText(),aName.getText());
			fw.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					Worker wk = fw.getWorker();
					
					infott.setText("☆출근/퇴근 버튼을 눌러주세요★");
					infott.setLocation(50,30);
					aId.setText(wk.getWorkerId());
					aName.setText(wk.getName());
					aId.setEnabled(false);
					aName.setEnabled(false);
					startend();
				}
			});
				
			
			
		});
		
		back.addActionListener(e -> {
			new BaseFrame();
			this.setVisible(false);
		});
		

		formpan.add(now);
		formpan.add(lName);
		formpan.add(lId);
		formpan.add(aName);
		formpan.add(aId);

		formpan.add(find);
		formpan.add(back);
		formpan.add(infott);
		add(formpan, "North");
		
		setSize(300,300);
		setLocationRelativeTo(null);
        setResizable(false);
		setVisible(true);
		
		ltime.start();
	}
	
	

	private void startend() {
		setSize(300	,600);
		setLocationRelativeTo(null);
        setResizable(false);
		JPanel btnpan = new JPanel(null);
		btnpan.setPreferredSize(new Dimension(300,300));
		JLabel infobt = new JLabel("※ 퇴근 실패시");
		JLabel infobt2 = new JLabel("출근기록 없을 시 퇴근처리 불가(관리자문의)");
		
		
		
		wstart.setBounds(70, 10, 150, 60);
		wend.setBounds(70, 90, 150, 60);
		infobt.setBounds(20, 180, 200, 22);
		infobt2.setBounds(20, 210, 300, 22);
		
		btnpan.add(wstart);
		btnpan.add(wend);
		btnpan.add(infobt);
		btnpan.add(infobt2);
		
		
		wstart.addActionListener(e -> {
			
			String[] dt = now.getText().split(" ");
			String id =aId.getText(); 
			String name = aName.getText(); 
			WorkDT wdt = new WorkDT(id, name, dt[1], dt[0]);
			
			if(workerDAO.checkstartWork(wdt) > 0) {
				JOptionPane.showMessageDialog(null, "이미 출근 되어있습니다.");
			} else {
				if(workerDAO.startWork(wdt) == 1) {
					JOptionPane.showMessageDialog(null, name + "님 출근완료!\n" + now.getText());
				}
			}
		});
		
		wend.addActionListener(e->{
			String[] dt = now.getText().split(" ");
			String id =aId.getText(); 
			String name = aName.getText(); 
			WorkDT wdt = new WorkDT(id, name, dt[1], dt[0]);
			
			
			if(workerDAO.checkstartWork(wdt) == 0) {
				JOptionPane.showMessageDialog(null, "퇴근 실패. (출근기록없음)");
			} else {
				if(workerDAO.endWork(wdt) == 1) {
					JOptionPane.showMessageDialog(null, name + "님 퇴근완료!\n" + now.getText());
					
				}
			}
		});
		
		
		add(btnpan);
		

	}
	
	
	// 시간 스레
	class TimerThread extends Thread {
		JLabel dt;
		public TimerThread(JLabel datime) {
			dt = datime;
		}
		
		@Override
		public void run() {

			while(true) {
				datetime = LocalDateTime.now();
				dt.setText(datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}

}
