package entity;

public class Cart {
	private int aid;
	private int pid;
	private int amount;
	public Cart(int aid, int pid, int amount) {
		super();
		this.aid = aid;
		this.pid = pid;
		this.amount = amount;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Cart [aid=" + aid + ", pid=" + pid + ", amount=" + amount + "]";
	}
	
	
}
