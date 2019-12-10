package Ex1;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import com.google.gson.Gson;

public class Functions_GUI implements functions{
		ArrayList<function> list;
		
	public Functions_GUI() {
		this.list = new ArrayList<function>();}
		public function get(int index) {
			return this.list.get(index);
			
		}
	
	@Override
	public boolean add(function arg0) {
		return list.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return list.addAll(arg0);
	}

	@Override
	public void clear() {
		 list.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return list.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return list.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return list.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return list.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return list.retainAll(arg0);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return list.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {
		JasonReadAndWrite j=new JasonReadAndWrite(file,this.list);
		this.list = j.fromFile();

	}

	@Override
	public void saveToFile(String file) throws IOException {
		JasonReadAndWrite j=new JasonReadAndWrite(file,this.list);
		j.addToFile();
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
	StdDraw.setCanvasSize(width,height);
	StdDraw.setPenColor(Color.blue);
	StdDraw.setXscale(rx.get_min(),rx.get_max());
	StdDraw.setYscale(ry.get_min(),ry.get_max());
	StdDraw.setPenRadius(0.005);
	StdDraw.line(0, ry.get_min(), 0, ry.get_max());
	StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
	StdDraw.setPenColor(Color.LIGHT_GRAY);
	StdDraw.setPenRadius(0.002);
	for(double i=rx.get_min();i<rx.get_max();i++) {
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		StdDraw.line(i, ry.get_min(), i, ry.get_max());
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(i+0.1, -0.5, Double.toString(i));
		
	}
	for(double j=ry.get_min();j<ry.get_max();j++) {
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		StdDraw.line(rx.get_min(), j, rx.get_max(), j);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(0.3, j, Double.toString(j));
	}
	StdDraw.show();
	Random randomGenerator = new Random();
	StdDraw.setPenRadius(0.1);
	for(int j=0;j<list.size();j++) {
		int red = randomGenerator.nextInt(256);
		int green = randomGenerator.nextInt(256);
		int blue = randomGenerator.nextInt(256);
		Color randomColour = new Color(red,green,blue);
		StdDraw.setPenColor(randomColour);
		double r=Math.abs(rx.get_max()-rx.get_min())/resolution;
		for(double k=rx.get_min();k<rx.get_max();k+=r) {
		StdDraw.setPenRadius(0.01);
		double p = this.list.get(j).f(k);
		//StdDraw.point(k, p);
		StdDraw.line(k, p, k+r, this.list.get(j).f(k+r));
		}}
	}

	
	
	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();
	
		try 
		{
			//Option 2: from JSON file to Object
			FileReader reader = new FileReader(json_file);
			Parameters p = gson.fromJson(reader,Parameters.class);
			Range rx = new Range(p.Range_X[0], p.Range_X[1]);
			Range ry = new Range(p.Range_Y[0], p.Range_Y[1]);
			drawFunctions(p.Width, p.Height, rx, ry,p.Resolution);
		} 
		catch (FileNotFoundException e) {
			Range rx = new Range(-10, 10);
			Range ry = new Range(-10, 10);
			drawFunctions(1000,600,rx, ry,800);
			e.printStackTrace();
		}
		
	}
	
	
	
}
