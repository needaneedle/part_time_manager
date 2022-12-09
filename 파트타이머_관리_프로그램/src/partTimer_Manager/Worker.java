package partTimer_Manager;

public class Worker {
	String name;
	String workerId;
	int age;
	int wage;
	String memo;
	
	public Worker(String d,String n,int a,int wa,String mo) {
		
		workerId = d;
		name = n;
		age = a;
		wage = wa;
		memo = mo;
	}
	public Worker(String d,String n) {
		workerId = d;
		name = n;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getWage() {
		return wage;
	}
	public void setWage(int wage) {
		this.wage = wage;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}


}
