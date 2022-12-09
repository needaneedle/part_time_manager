package partTimer_Manager;


public class WorkDT {
	String wid;
	String wname;
	String wstart;
	String wend;
	String wdate;
	String wnum;
	String wtime;
	
	
	
	public String getWnum() {
		return wnum;
	}

	public void setWnum(String wnum) {
		this.wnum = wnum;
	}


	
	public String getWtime() {
		return wtime;
	}

	public void setWtime(String wtime) {
		this.wtime = wtime;
	}

	public WorkDT(String wd) {
		wdate = wd;
	}
	
	public WorkDT(String wn,String d, String n,String ws, String we,String wd) {
		wid = d;
		wname =n;
		wstart = ws;
		wend = we;
		wdate = wd;
		wnum = wn;
	}

	public WorkDT(String d, String n, String wd) {
		wid = d;
		wname =n;
		wdate = wd;
		
	}
	public WorkDT( String d, String n) {
		wid = d;
		wname =n;
	}
	
	public WorkDT(String d, String n, String wt, String wd) {
		wid = d;
		wname =n;
		wtime = wt;
		wdate = wd;
	}

	

	public String getWstart() {
		return wstart;
	}
	public void setWstart(String wstart) {
		this.wstart = wstart;
	}
	public String getWend() {
		return wend;
	}
	public void setWend(String wend) {
		this.wend = wend;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	
	
	
}
