package Ex1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		try (JsonWriter writer = new JsonWriter(new FileWriter(""+this.file1+".json"))) {
            writer.beginObject();                   // {        
            writer.name("functions");                // "messages" : 
            writer.beginArray();                    // [
            Iterator<function> it=this.arr.iterator();
            while(it.hasNext()) {
            	writer.value(it.next().toString()); 
            }
            writer.endArray();                      // ]
            writer.endObject();                     // 
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	public ArrayList<function> fromFile() {
		 try (JsonReader reader = new JsonReader(new FileReader(""+this.file1+".json"))) {
			 	function f = new ComplexFunction(new Monom("0"));
	            reader.beginObject();

	            while (reader.hasNext()) {

	                String name = reader.nextName();

	                if (name.equals("functions")) {

	                    // read array
	                    reader.beginArray();

	                    while (reader.hasNext()) {
	                        this.arr.add(f.initFromString(reader.nextString()));
	                    }

	                    reader.endArray();

	                } else {
	                    reader.skipValue(); //avoid some unhandle events
	                }
	            }

	            reader.endObject();

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 return arr;
	    }
	}
	

