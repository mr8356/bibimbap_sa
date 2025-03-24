package Refactoring.grasp;

public class LowCouplingExample {
	public static void main(String[] args) {
		PaymentUI ui = new PaymentUI();
		ui.pay();
	}
}

class PaymentProcessor {
	public void process() {
		System.out.println("결제 처리 완료");
	}
}

class PaymentUI {
	PaymentProcessor processor = new PaymentProcessor(); // ❌ 강한 결합

	public void pay() {
		processor.process();
	}
}