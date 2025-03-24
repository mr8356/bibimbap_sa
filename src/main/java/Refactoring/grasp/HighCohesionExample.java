package Refactoring.grasp;

public class HighCohesionExample {
	public static void main(String[] args) {
		Report report = new Report();
		report.generate();
	}
}

class Report {
	public void generate() {
		System.out.println("보고서 생성");

		// ❌ 로컬 파일 저장
		System.out.println("파일 저장");

		// ❌ 이메일 전송
		System.out.println("이메일 전송");
	}
}