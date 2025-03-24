// OCP
//결제 수단이 늘어날 때마다 if문을 수정해야 함
//→ 새로운 기능 추가 시 기존 코드를 건드리는 건 OCP 위반
package Refactoring.solid;

public class PaymentService {
	public void pay(String method) {
		if (method.equals("CARD")) {
			System.out.println("카드 결제 처리");
		} else if (method.equals("KAKAO")) {
			System.out.println("카카오페이 결제 처리");
		} else if (method.equals("TOSS")) {
			System.out.println("토스 결제 처리");
		}
	}
}