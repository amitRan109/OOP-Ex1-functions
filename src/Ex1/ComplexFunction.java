package Ex1;

import java.util.Stack;

public class ComplexFunction implements complex_function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public class Node {

		//***params***
		private function f;
		private Node right; 
		private Node left;
		private Operation op;
		
		//***constructors***
		public Node (function _f) {
			this. f=_f;
			this.left=null;
			this.right=null; 
			this.op=null;
		}
		
		public Node (Operation _op,Node _left, Node _right) {
			this. f=null;
			this.left=_left;
			this.right=_right; 
			this.op=_op;
		}
		
		//***getters and setters***

		public function getF() {
			return f;
		}

		public void setF(function f) {
			this.f = f;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Operation getOp() {
			return op;
		}

		public void setOp(Operation op) {
			this.op = op;
		}
	}
	//***params***
	private Node head;
	
	//**constructors**
	public ComplexFunction (function f1) { // build constructor
		setHead(new Node (Operation.None,new Node (f1),null));
	}
	
	public ComplexFunction (String op, function f1, function f2) { // build constructor
		Operation _op= makeOpFromString(op);
		setHead(new Node (_op,new Node (f1),new Node (f2)));
	}
	
	private Operation makeOpFromString (String s) {
		s.toLowerCase();
		switch (s) {
		case "plus":

			return Operation.Plus;
		case "mul":
			return Operation.Times;
		case "div":
			return Operation.Divid;
		case "max":
			return Operation.Max;
		case "min":
			return Operation.Min;
		case "comp":
			return Operation.Comp;
		default:
			throw new RuntimeException("error");
		}
	}
	//***functions***
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return fRecurs(this.head,x);
	}
	
	private double fRecurs (Node n, double x) {
		if (n.f != null) return n.getF().f(x);
		else {
			switch (n.getOp()) {
			case Plus:
				return fRecurs(n.getLeft(),x)+fRecurs(n.getRight(),x);
			case Times:
				return fRecurs(n.getLeft(),x)*fRecurs(n.getRight(),x);
			case Divid:
				return fRecurs(n.getLeft(),x)/fRecurs(n.getRight(),x);
			case Max:
				return Math.max(fRecurs(n.getLeft(),x), fRecurs(n.getRight(),x));
			case Min:
				return Math.min(fRecurs(n.getLeft(),x), fRecurs(n.getRight(),x));
			case Comp:
				return fRecurs(n.getLeft(),fRecurs(n.getRight(),x));
			case None:
				return fRecurs(n.getLeft(),x);
			case Error:
				return 0;
			default:
				throw new RuntimeException("error");
			}
		}
	}
	
	public String toString () {
		return tsR(this.head);
	}
	
	private String tsR (Node n) {
		if (n==null) return "";
		if (n.getF()!= null) return n.getF().toString();
		else  if (n.getRight()==null ) return  makeFromOp(n.getOp())+tsR(n.getLeft());
		else return  makeFromOp(n.getOp())+"("+tsR(n.getLeft())+","+tsR(n.getRight())+")";
	}
	
	private String makeFromOp (Operation op) {
		switch (op) {
		case Plus:
			return "plus";
		case Times:
			return "mul";
		case Divid:
			return "div";
		case Max:
			return "max";
		case Min:
			return "min";
		case Comp:
			return "comp";
		case None:
			return "";
		case Error:
			return "no";
		default:
			throw new RuntimeException("error");
		}
		
	}
	
	@Override
	public function initFromString(String s) {
		s=s.replace(" ", "");
		return initR (s);
	
	}
	private function initR (String s) {
		try {
			if (s.charAt(0)=='+' || s.charAt(0)=='-' || s.charAt(0)=='x' || Character.isDigit(s.charAt(0))) {
				return new Polynom (s);
				
			}
			String op="";
			int i=0;
			while (Character.isLetter(s.charAt(i))) { // make string from the op
				op+=s.charAt(i);
				i++;
			}
			String left="";
			String right="";
			Stack <String> st= new Stack <String> ();
			i++; // start from the char after "("
			int j=i;
			//find left
			while (s.charAt(i)!=',' || s.charAt(i)==',' && !st.isEmpty()) { //or its not ',' or its ',' but not the last
				if (s.charAt(i)=='(') st.add("(");
				if (s.charAt(i)==')') st.pop();
				i++;
			}
			left=s.substring(j, i);
			i++;
			j=i;
			//find right
			while (s.charAt(i)!=')'||s.charAt(i)==')' && !st.isEmpty()) {
				if (s.charAt(i)=='(') st.add("(");
				if (s.charAt(i)==')') st.pop();
				i++;
			}
			right=s.substring(j, i);
			return new ComplexFunction (op,initR(left),initR(right));
		}
		catch (Exception e) {
			throw new RuntimeException ("error");
		}

	}

	@Override
	public function copy() {
	function ansF = initFromString (toString());
	ComplexFunction ans= new ComplexFunction (ansF);
		return ans;
	}
	
	@Override
	public void plus(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Plus, temp, cf1.getHead());
		
	}

	@Override
	public void mul(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Times, temp, cf1.getHead());
	}

	@Override
	public void div(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Divid, temp, cf1.getHead());
	}

	@Override
	public void max(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Max, temp, cf1.getHead());
	}

	@Override
	public void min(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Min, temp, cf1.getHead());	}

	@Override
	public void comp(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Comp, temp, cf1.getHead());
	}
	
	public boolean equals(Object obj) {
		function second=(function) obj;
		boolean check=true;
		for(double i=-1000;i<=1000;i++) {
			if(this.f(i)!=second.f(i)) {
				check=false;
			}
		}
		return check;	
	}
	
	//**getters**
	@Override
	public function left() {
		ComplexFunction ans=new ComplexFunction (new Monom("0"));
		ans.setHead(this.head.getLeft());
		return ans;
	}

	@Override
	public function right() {
		ComplexFunction ans=new ComplexFunction (new Monom("0"));
		ans.setHead(this.head.getRight());
		return ans;	
	}

	@Override
	public Operation getOp() { return this.getHead().op; }
	
	public Node getHead () { return this.head; }
	
	//**setters**
	public void setHead (Node _head) { this.head=_head; }

}