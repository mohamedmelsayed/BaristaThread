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
public class OrderProcessing {
    private long orderId;

	private States status;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public States getStatus() {
		return status;
	}

	public void setStatus(States status) {
		this.status = status;
	}

}
