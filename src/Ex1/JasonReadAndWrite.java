package Ex1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class JasonReadAndWrite {
	private ArrayList<function> arr;
	private String file1;

	public JasonReadAndWrite(String file, ArrayList<function> list) {
		this.file1=file;
		this.arr=list;
	}
	public void addToFile() {
		try {
		 
			File fout =new File(this.file1);
			FileOutputStream fos=new FileOutputStream(fout);
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
			for(int i=0;i<arr.size();i++) {
				bw.write(this.arr.get(i).toString());
				bw.newLine();
			}
			bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	public ArrayList<function> fromFile() {
		 BufferedReader reader;
		try{
			 	reader=new  BufferedReader(new FileReader(this.file1));
			 	function f = new ComplexFunction(new Monom("6"));
			 	String line=reader.readLine();
	            while (line!=null) {
	                //System.out.println(line);
	            	this.arr.add(f.initFromString(line));
	                line=reader.readLine();             
                    }
	                    reader.close();;
	            
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 return arr;
	    }
	}
	

