package Ex1;

//import java.util.Stack;

public class checkcComplex {
	public static void main (String [] args) {
//		String s="((()))";
//		Stack <String> st= new Stack <String> ();
//		int i=0;
//		while (i<s.length()) {
//			if (s.charAt(i)=='(') st.push("(");
//			if (s.charAt(i)==')') st.pop();
//			i++;
//		}
//		System.out.println(st.isEmpty());

		
		
		
//		function f= new Polynom ("2x");
//		ComplexFunction cf= new ComplexFunction (f);
//		//System.out.println(cf.toString());
//		cf.plus(new Polynom("5x+2x^2"));
//		//System.out.println(cf.toString());
//		//System.out.println(cf.f(2));
//		cf.plus(new Monom ("3x^3"));
//		System.out.println(cf.toString());
//		//System.out.println(cf.f(2));
		
//		ComplexFunction cf= new ComplexFunction (new Polynom ("2x"));
//		cf.plus(new Polynom ("3x^5+4x"));
//		System.out.println(cf.toString());
//		function init=cf.initFromString(cf.toString());
//		System.out.println(init.toString());
		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "5+2x-3.3x+0.1x^5";
		String[] s3 = {"x+3","x-2", "x-4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		System.out.println("p3: "+p3);
		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
		//System.out.println("cf: "+cf);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x+1"),cf3);
		//System.out.println("cf4: "+cf4);
		cf4.plus(new Monom("2"));
		cf.div(p1);
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		System.out.println("cf5: "+ cf5);
		System.out.println("cf6: "+ cf6);
		
//		function cf2= cf.copy();
//		System.out.println(cf2);
		
	
	}

}
