package cs414.a5.jlarison.pizza.domain;

public class DeliveryOrder extends Order {
	
	private String address;
	private String phoneNumber;
	private boolean delivered;

	public DeliveryOrder(String orderName, int orderId, String address, String phoneNumber) {
		super(orderName, orderId);
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean isDelivered() {
		return delivered;
	}
	
}
