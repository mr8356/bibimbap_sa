// SRP
package Refactoring.solid;

public class SignUpService {
	public void signUp(String email) {
		System.out.println("회원가입 완료: " + email);
		sendWelcomeEmail(email);
	}

	private void sendWelcomeEmail(String email) {
		System.out.println(email + " 에 환영 이메일 전송!");
	}
}