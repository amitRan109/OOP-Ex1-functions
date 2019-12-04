package Ex1;

//import java.util.Stack;

public class ComplexFunction implements complex_function {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public class Node {

		private function f;
		private Node right; 
		private Node left;
		private Operation op;
		
		/*public Node (function _f, Node _left, Node _right, Operation _op) {
			this. f=_f;
			this.right=_right; 
			this.left=_left;
			this.op=_op;
		}*/
		public Node (function _f) {
			this. f=_f;
			this.right=null; 
			this.left=null;
			this.op=null;
		}
		public Node (Operation _op,Node _left, Node _right) {
			this. f=null;
			this.right=_right; 
			this.left=_left;
			this.op=_op;
		}

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
	
	private Node head;
	
	public Node getHead () { return this.head; }
	
	public ComplexFunction (function f1) { // build constructor
		this.head= new Node (Operation.None,new Node (f1),null);
		
	}
	public ComplexFunction (String op, function f1, function f2) { // build constructor
		op.toLowerCase();
		
		switch (op) {
		case "plus":
			this.head= new Node (Operation.Plus,new Node (f1),new Node (f2));
			break;
		case "mul":
			this.head= new Node (Operation.Times,new Node (f1),new Node (f2));
			break;
		case "div":
			this.head= new Node (Operation.Divid,new Node (f1),new Node (f2));
			break;
		case "max":
			this.head= new Node (Operation.Max,new Node (f1),new Node (f2));
			break;
		case "min":
			this.head= new Node (Operation.Min,new Node (f1),new Node (f2));
			break;
		case "comp":
			this.head= new Node (Operation.Comp,new Node (f1),new Node (f2));
			break;
		default:
			throw new RuntimeException("error");
		}
	}
	
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
				return -1;// ??
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
		else  if (n.getRight()==null ) return getOp(n.getOp())+"("+tsR(n.left)+")";
		else return getOp(n.getOp())+"("+tsR(n.left)+","+tsR(n.right)+")";
	}
	
	private String getOp (Operation op) {
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
		ComplexFunction ans=new ComplexFunction (new Polynom ("0"));
		
		/*Stack <String> st= new Stack <String> ();
		int i=0; int j=0;
		if (s.charAt(j))
		while (j<s.length()) {
			if ()
		}*/
		
		
		/*int i=0; int j=0; 
		if (s.charAt(j)=='-' || s.charAt(j)=='+') {
			j++;
		}
		while (j<s.length()) {
			if (s.charAt(j)=='+' || s.charAt(j)=='-' || s.charAt(j)=='*' || s.charAt(j)=='/') {
				Monom m=new Monom (s.substring(i, j));
				addToList (m);
				i=j;
			}
			j++; 
		}
		if (i!=j) {
			Monom m=new Monom (s.substring(i, j));
			addToList (m);
		}*/	



		return (function) ans;
	}

	@Override
	public function copy() {
		/*ComplexFunction ans = new ComplexFunction ();
		copyR(ans,this.head);
		return ans;*/
		return null;
	}

	@Override
	public void plus(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Plus, temp, cf1.head);
		
	}

	@Override
	public void mul(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Times, temp, cf1.head);
	}

	@Override
	public void div(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Divid, temp, cf1.head);
	}

	@Override
	public void max(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Max, temp, cf1.head);
	}

	@Override
	public void min(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Min, temp, cf1.head);	}

	@Override
	public void comp(function f1) {
		ComplexFunction cf1= new ComplexFunction (f1);
		Node temp = this.head;
		this.head= new Node (Operation.Comp, temp, cf1.head);
	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		return null;
	}

}
