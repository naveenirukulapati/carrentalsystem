package com.car.rental.app.dto;

//@Entity
//@Table(name = "ord")
public class Ord {

	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String sec;
	private String trans;
	private double amt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSec() {
		return sec;
	}
	public void setSec(String sec) {
		this.sec = sec;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	
}
