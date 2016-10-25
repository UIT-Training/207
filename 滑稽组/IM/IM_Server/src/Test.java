
/**
 * 回调
 * @author linCQ
 *
 */
interface A{
	void speak();
}

class B implements A{
	@Override
	public void speak() {
		
		System.out.println("8点吃饭");
	}
}


class C{
	
	public void tell(A a){
		System.out.println("几点吃饭？");
		
		a.speak();
	}
	
}


public class Test {

	public static void main(String[] args) {
		C c=new C();
//		B b=new B();
//		new B();
		c.tell(new B());
	}
}
