package x.b.n;

public class Cat {
	Integer a;
	Integer b;
	public Cat() {}
	public Cat(Integer a,Integer b) {this.a=a;this.b=b;}
	
	public Integer cal(Integer a) {
		System.out.println("calc:"+a);
		return a;
	}
	@Override
	public String toString() {
		return "Cat [a=" + a + ", b=" + b + "]";
	}
	
	
	
}
