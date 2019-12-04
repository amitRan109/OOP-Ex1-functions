package Ex1;

public class checkcComplex {
	public static void main (String [] args) {
		ComplexFunction cf= new ComplexFunction (new Polynom ("2x"));
		System.out.println(cf.toString());
		cf.plus(new Polynom("5x+2x^2"));
		System.out.println(cf.toString());
		System.out.println(cf.f(2));
		
//		function cf2= cf.copy();
//		System.out.println(cf2);
//
//		
	}

}
