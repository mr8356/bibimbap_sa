package Refactoring.solid;

// ISP
//SimplePrinter는 프린트 기능만 필요하지만, scan()과 fax()도 구현해야 합니다.
//ISP를 위반한 구조입니다.
//불필요한 구현을 피할 수 있도록 인터페이스를 나눠보세요.

interface Machine {
	void print();
	void scan();
	void fax();
}

public class PrinterOnly implements Machine {
	public void print() {
		System.out.println("프린트 출력");
	}
	public void scan() {
		throw new UnsupportedOperationException("스캔 불가");
	}
	public void fax() {
		throw new UnsupportedOperationException("팩스 불가");
	}
}