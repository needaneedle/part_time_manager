package partTimer_Manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarCon extends JFrame implements ActionListener{

	Calendar cal; 
	int year, month, date;
	String selectdate;
	JPanel pane = new JPanel();
	
		//?œ„?— ë²„íŠ¼ ì¶”ê?
		JButton btn1 = new JButton("??");  //?´? „ë²„íŠ¼
		JButton btn2 = new JButton("?–¶"); //?‹¤?Œë²„íŠ¼
		
		//?œ„?— ?¼ë²¨ì¶”ê°?
		JLabel yearlb = new JLabel("?…„");
		JLabel monthlb = new JLabel("?›”");
		
		//?…„?›” ì¶”ê?
		JComboBox<Integer> yearCombo = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
		JComboBox<Integer> monthCombo = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
		
		//?Œ¨?„ì¶”ê?
		JPanel pane2 = new JPanel(new BorderLayout());
			JPanel title = new JPanel(new GridLayout(1, 7));
				String titleStr[] = {"?¼", "?›”", "?™”", "?ˆ˜", "ëª?", "ê¸?", "?† "};
			JPanel datePane = new JPanel(new GridLayout(0, 7));

	//?™”ë©´ë””??¸
	public CalendarCon() {
		//------?…„?„ ?›” êµ¬í•˜ê¸?------------
		cal = Calendar.getInstance(); //?˜„?¬?‚ ì§?
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH)+1;
		date = cal.get(Calendar.DATE);
		
		//?…„
		for(int i=year-22; i<=year+1; i++){
			yearModel.addElement(i);
		}
		
		yearCombo.setModel(yearModel);
		yearCombo.setSelectedItem(year);
		
		//?›”
		for(int i=1; i<=12; i++) {
			monthModel.addElement(i);
		}
		monthCombo.setModel(monthModel);
		monthCombo.setSelectedItem(month);
		
		//?›”?™”?ˆ˜ëª©ê¸ˆ?† ?¼
		for(int i=0; i<titleStr.length; i++){
			JLabel lbl = new JLabel(titleStr[i], JLabel.CENTER);
			if(i == 0){
				lbl.setForeground(Color.red);
			}else if(i == 6){
				lbl.setForeground(Color.blue);
			}
			title.add(lbl);
		}
		//?‚ ì§? ì¶œë ¥
		day(year, month);
		
		//----------------------------
		setTitle("?‚ ì§œì„ ?ƒ");
		pane.add(btn1);
		pane.add(yearCombo);
		pane.add(yearlb);
		pane.add(monthCombo);
		pane.add(monthlb);
		pane.add(btn2);
//		pane.setBackground(Color.CYAN);
		add(BorderLayout.NORTH, pane);
		pane2.add(title,"North");
		pane2.add(datePane);
		add(BorderLayout.CENTER, pane2);
				
		//ê°ì¢… ëª…ë ¹?–´
        setVisible(true);
        setSize(400,300);
		setLocationRelativeTo(null);
        setResizable(false);

        
        //----------ê¸°ëŠ¥êµ¬í˜„----------
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        yearCombo.addActionListener(this);
        monthCombo.addActionListener(this);
	}

	//ê¸°ëŠ¥êµ¬í˜„
	public void actionPerformed(ActionEvent e) {
		Object eventObj = e.getSource();
		if(eventObj instanceof JComboBox) {
			datePane.setVisible(false);	//ë³´ì—¬ì§??Š” ?Œ¨?„?„ ?ˆ¨?‚¨?‹¤.
			datePane.removeAll();	//?¼ë²? ì§??š°ê¸?
			day((Integer)yearCombo.getSelectedItem(), (Integer)monthCombo.getSelectedItem());
			datePane.setVisible(true);	//?Œ¨?„ ?¬ì¶œë ¥
		}else if(eventObj instanceof JButton) {
			JButton eventBtn = (JButton) eventObj;
			int yy = (Integer)yearCombo.getSelectedItem();
			int mm = (Integer)monthCombo.getSelectedItem();
			if(eventBtn.equals(btn1)){	//? „?‹¬
				if(mm==1){
					yy--; mm=12;
				}else{
					mm--;
				}				
			}else if(eventBtn.equals(btn2)){	//?‹¤?Œ?‹¬
				if(mm==12){
					yy++; mm=1;
				}else{
					mm++;
				}
			}
			yearCombo.setSelectedItem(yy);
			monthCombo.setSelectedItem(mm);
		}	
	}
	
	//?‚ ì§œì¶œ? ¥
	public void day(int year, int month) {
		Calendar date = Calendar.getInstance();//?˜¤?Š˜?‚ ì§? + ?‹œê°?
		date.set(year, month-1, 1);
		int week = date.get(Calendar.DAY_OF_WEEK);
		int lastDay = date.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//ê³µë°±ì¶œë ¥
		for(int space=1; space<week; space++) {
			datePane.add(new JLabel("\t"));
		}
		
		//?‚ ì§? ì¶œë ¥
		for (int day = 1; day <= lastDay; day++) {
			JLabel lbl = new JLabel(String.valueOf(day), JLabel.CENTER);
			cal.set(year, month-1, day);
			int Week = cal.get(Calendar.DAY_OF_WEEK);
			if(Week==1){
				lbl.setForeground(Color.red);				
			}else if(Week==7){
				lbl.setForeground(Color.BLUE);
			}
			lbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() ==2) {
						String y = ""+yearCombo.getSelectedItem();
						String m = (int)monthCombo.getSelectedItem() < 10? "0"+monthCombo.getSelectedItem() : ""+monthCombo.getSelectedItem();
						String d = lbl.getText();

						selectdate = y+"-"+m+"-"+d;
						dispose();
					}
				}
			});
			datePane.add(lbl);
			
		}
	}
	
	String getSelectdate() {
		return selectdate;
	}

}