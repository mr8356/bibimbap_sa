package Refactoring.grasp;

public class ExpertExample {
	public static void main(String[] args) {
		Order order = new Order(15000, 2);
		UI ui = new UI();
		ui.showTotal(order);
	}
}

class Order {
	int pricePerItem;
	int quantity;

	public Order(int pricePerItem, int quantity) {
		this.pricePerItem = pricePerItem;
		this.quantity = quantity;
	}
}

class UI {
	public void showTotal(Order order) {
		// ❌ 계산 책임을 UI가 가짐
		int total = order.pricePerItem * order.quantity;
		System.out.println("총 금액: " + total + "원");
	}
}