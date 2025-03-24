// LSP

package Refactoring.solid;

class Car {
	public void turnOnEngine() {
		System.out.println("엔진 시동");
	}
}
public class Bicycle extends Car {
	@Override
	public void turnOnEngine() {
		throw new UnsupportedOperationException("자전거는 엔진이 없습니다!");
	}
}
