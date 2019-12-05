package Ex1Testing;

import java.io.IOException;
import java.util.function.Function;

import Ex1.Functions_GUI;
import Ex1.Polynom;
import Ex1.Range;

public class JasonTester {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Functions_GUI f=new Functions_GUI();
		f.add(new Polynom("3x^4+2"));
		f.add(new Polynom("8x+3x^5"));
		f.add(new Polynom("4x^5"));
		f.add(new Polynom("x^3"));
		f.add(new Polynom("8x^4+2x"));
		f.saveToFile("JasonTest");
		/*Range rx=new Range(-10,10);
		Range ry=new Range(-10,10);
		int rez=1000;
		f.drawFunctions(1000, 700, rx, ry, rez);*/
		/*int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		f.drawFunctions(w,h,rx,ry,res);*/
	}

}
