package partTimer_Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.table.DefaultTableModel;

public class WorkerDAO {

	Connection conn;
	ArrayList<Worker> workers = new ArrayList<>();
//	Vector<Worker> workers = new Vector<>();
	DefaultTableModel tblModel;
	String sql;
	Object[] rows;
	Statement stmt;

	public WorkerDAO() {

	}

	public Connection makeConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버로딩성공");

			String url = "jdbc:mysql://localhost:3306/workerdb?serverTimezone=Asia/Seoul&jdbcCompliantTruncation=false";
			String userId = "root";
			String pass = "1234";

			conn = DriverManager.getConnection(url, userId, pass);
			System.out.println("연결성공");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패");

		} catch (SQLException e) {
			System.out.println("연결 실패");
		}
		return conn;
	}

	// 전체 리스트 가져오기
	public DefaultTableModel makeAllTbl(String tbl, String[] tt) {
		conn = makeConnection();
		Object[] rows = new Object[tt.length];
		sql = "select * from " + tbl + " ;";

		tblModel = new DefaultTableModel(tt, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				for (int i = 0; i < tt.length; i++) {
					rows[i] = rs.getObject(i + 1);
				}
				tblModel.addRow(rows);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return tblModel;
	}

	public DefaultTableModel makeAllhoursTbl(String tbl, String[] tt) {
		conn = makeConnection();
		Object[] rows = new Object[tt.length];
		sql = "select * from " + tbl + " ;";

		tblModel = new DefaultTableModel(tt, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				for (int i = 0; i < tt.length; i++) {
					rows[i] = rs.getObject(i + 1);
				}
				tblModel.addRow(rows);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return tblModel;
	}

	// 검색
	public DefaultTableModel workerSearch(String tbl, Worker w, int c, String[] tt) {
		Object[] rows = new Object[tt.length];
		conn = makeConnection();
		if (c == 1) {
			sql = "select * from " + tbl + " where wname = '" + w.getName() + "' and wid = " + w.getWorkerId() + " ;";
		} else if (c == 2) {
			sql = "select * from " + tbl + " where wid = " + w.getWorkerId() + " ;";
		} else if (c == 3) {
			sql = "select * from " + tbl + " where wname = '" + w.getName() + "';";
		}

		tblModel = new DefaultTableModel(tt, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				for (int i = 0; i < tt.length; i++) {
					rows[i] = rs.getObject(i + 1);
				}
				tblModel.addRow(rows);
			}

			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tblModel;
	}

	public DefaultTableModel workhoursSearch(String[] tt, int check, WorkDT wd) {
		conn = makeConnection();
		Object[] rows = new Object[tt.length];
		if (check == 1) {
			sql = "select * from hourstbl where wday = '" + wd.getWdate() + "';";
		} else if (check == 2) {
			sql = "select * from hourstbl where wid = '" + wd.getWid() + "' and wname = '" + wd.getWname() + "' ;";
		} else if (check == 3) {
			sql = "select * from hourstbl where wday = '" + wd.getWdate() + "' and wid = '" + wd.getWid()
					+ "' and wname = '" + wd.getWname() + "' ;";
		}

		tblModel = new DefaultTableModel(tt, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				for (int i = 0; i < tt.length; i++) {
					rows[i] = rs.getObject(i + 1);
				}
				tblModel.addRow(rows);
			}
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tblModel;
	}
	
	

	int updateWorker(Worker w) {
		conn = makeConnection();

		sql = "update workertbl set wname = ?, wage = ?, wwage = ?, wmemo = ? where wid = ?;";

		int check = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(5, w.getWorkerId());
			pstmt.setString(1, w.getName());
			pstmt.setInt(2, w.getAge());
			pstmt.setInt(3, w.getWage());
			pstmt.setString(4, w.getMemo());

			check = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return check;
	}

	int workerInsert(Worker w) {

		int check = 0;

		conn = makeConnection();
		sql = "insert into workertbl values(null,?,?,?,?);";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, w.getWorkerId());
			pstmt.setString(1, w.getName());
			pstmt.setInt(2, w.getAge());
			pstmt.setInt(3, w.getWage());
			pstmt.setString(4, w.getMemo());

			check = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return check;
	}

	// 출근 되어있는지 확인...

	int checkstartWork(WorkDT wdt) {
		int check = 0;
		conn = makeConnection();
		sql = "select * from hourstbl where wday = ? and wId = ?;";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wdt.getWdate());
			pstmt.setString(2, wdt.getWid());
			ResultSet rs = pstmt.executeQuery();
			check = rs.next() ? rs.getRow() : 0;

			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	// 출근~
	int startWork(WorkDT wdt) {

		int check = 0;
		conn = makeConnection();
		sql = "insert into hourstbl (wday, wId,wname, wstart) values(?,?,?,?);";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wdt.getWdate());
			pstmt.setString(2, wdt.getWid());
			pstmt.setString(3, wdt.getWname());
			pstmt.setString(4, wdt.getWtime());

			check = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return check;
	}

	int endWork(WorkDT wdt) {

		conn = makeConnection();
		sql = "update hourstbl set wend = ? where wday = ? and wid = ?;"
				+ "";

		int check = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wdt.getWtime());
			pstmt.setString(2, wdt.getWdate());
			pstmt.setString(3, wdt.getWid());

			check = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		timestmp();
		return check;
	}
// 관리자 출퇴근시간 수정 
	int updateWorkhours(WorkDT wdt) {
		conn = makeConnection();
		sql = "update hourstbl set wday = ?, wstart= ?, wend= ? where hid = ? and wid = ?;";
			

		int check = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wdt.getWdate());
			pstmt.setString(2, wdt.getWstart());
			pstmt.setString(3, wdt.getWend());
			pstmt.setString(4, wdt.getWnum());
			pstmt.setString(5, wdt.getWid());

			check = pstmt.executeUpdate();
			

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		timestmp();
		return check;
	}
	

	
	// 급여 
	
	
	public DefaultTableModel calcpay(String[] tt, String ym, String d,int c) {
		conn = makeConnection();
		Object[] rows = new Object[tt.length];
		if(c == 1) {
			sql = "select date_format(hourstbl.wday, '%Y-%m'), wid, wname, sum(wtotal), sum(wextra), format(round(((sum(wextra)*1.5)+(sum(wtotal)-sum(wextra)))*(select wwage from workertbl where workertbl.wid = hourstbl.wid)), 0) from hourstbl where wday like '"+ym+"' group by wid;";
		}else {		
			sql = "select date_format(hourstbl.wday, '%Y-%m'), wid, wname, sum(wtotal), sum(wextra), format(round(((sum(wextra)*1.5)+(sum(wtotal)-sum(wextra)))*(select wwage from workertbl where workertbl.wid = hourstbl.wid)), 0) from hourstbl where wday like '"+ym+"' and wid = "+d+";";

		}
		tblModel = new DefaultTableModel(tt, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				for (int i = 0; i < tt.length; i++) {
					rows[i] = rs.getObject(i + 1);
				}
				tblModel.addRow(rows);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return tblModel;
	}
	
	//근무시간 추가 
	void timestmp() {
		conn = makeConnection();
		sql = "update hourstbl set wtotal = TIMESTAMPDIFF(minute, wstart,wend)/60, wextra = greatest(wtotal-8, 0);";
		try {
			stmt = conn.createStatement();
			System.out.println(stmt.executeUpdate(sql));
			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
	public int getwage(int d) {
		conn = makeConnection();
		sql = "select wwage from workertbl where wid = "+d+";";
		int wwage = 0;
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) { 
				wwage = rs.getInt(1);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return wwage;
	}
	
	
}
