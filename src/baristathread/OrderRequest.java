/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baristathread;

/**
 *
 * @author melsayed
 */

public class OrderRequest {

	private long id=0;
	
	private String status;

	private String size  ;
	private String milkType;

	private boolean withMilk ;

	private double price ;

	private String coffeeType;
	private String delivery;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMilkType() {
		return milkType;
	}

	public void setMilkType(String milkType) {
		this.milkType = milkType;
	}

	public boolean getWithMilk() {
		return withMilk;
	}

	public void setWithMilk(boolean withMilk) {
		this.withMilk = withMilk;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCoffeeType() {
		return coffeeType;
	}

	public void setCoffeeType(String coffeeType) {
		this.coffeeType = coffeeType;
	}
	
	
	

}

