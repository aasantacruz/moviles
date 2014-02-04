package com.trucha;

public class Order {
	
	private int id,table_id;
	private float total;
	private boolean is_payed;
	
	public Order(int id,int table_id,float total,boolean is_payed){
		this.id = id;
		this.table_id = table_id;
		this.total = total;
		this.is_payed = is_payed;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setTableId(int table_id){
		this.table_id = table_id;
	}
	
	public void setTotal(float total){
		this.total = total;
	}
	
	public void setIsPayed(boolean is_payed){
		this.is_payed = is_payed;
	}

}
