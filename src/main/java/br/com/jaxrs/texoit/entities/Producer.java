package br.com.jaxrs.texoit.entities;

public class Producer {
	
	public Producer() {}
	
	public Producer(String name) {
		super();
		this.name = name;
	}
	
	private String name;
	private int interval = 0;
	private Integer previousWin;
	private Integer followWin;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public Integer getPreviousWin() {
		return previousWin;
	}
	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
	}
	public Integer getFollowWin() {
		return followWin;
	}
	public void setFollowWin(Integer followWin) {
		this.followWin = followWin;
	}
}
