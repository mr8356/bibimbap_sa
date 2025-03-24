package Refactoring.grasp;

public class CreatorExample {
	public static void main(String[] args) {
		Cart cart = new Cart();
		Product product = new Product("커피");
		cart.add(product);
	}
}

class Product {
	String name;

	public Product(String name) {
		this.name = name;
	}
}

class Cart {
	public void add(Product p) {
		System.out.println(p.name + " 장바구니에 추가됨");
	}
}