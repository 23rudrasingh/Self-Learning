import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/***************** 		This class is used to handle File to keep record and access them 	****************/
class FileHandler{
	
	String user[] = {"adminFile","customerFile"},menu[] = {"menuVeg","menuNonveg"};
	
	boolean writer(String file,String content){
		try{
			/*File fp = new File(file);
			FileWriter fw = new FileWriter(fp.getName(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("\n" + content);
			bw.close();*/ //this can be used to appened a file 
			
			FileOutputStream fout=new FileOutputStream(file);  
		     
		    byte b[]=content.getBytes();//converting string into byte array  
		    fout.write(b);  
		    fout.close();  
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	ArrayList<String> adminUsers(int type){
		ArrayList<String> list =new ArrayList<String>();
		try{
			File file =new File(user[type]);
			FileReader fread =new FileReader(file);
			BufferedReader reader = new BufferedReader(fread);
			String line;
			while((line = reader.readLine()) != null)
				list.add(line);
			reader.close();
			fread.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	LinkedList<String> reader(int type){
		LinkedList<String> list =new LinkedList<String>();
		try{
			File file =new File(menu[type]);
			FileReader fread =new FileReader(file);
			BufferedReader reader = new BufferedReader(fread);
			String line;
			while((line = reader.readLine()) != null)
				list.add(line);
			reader.close();
			fread.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	LinkedList<Item> foodreader(String file){
		LinkedList<Item> list = new LinkedList<Item>();
		Item item;
		StringTokenizer tk;
		int count = 0;
		String name;
		try{
			Scanner read = new Scanner(new File(file));
			while(read.hasNextLine()){
				String line = read.nextLine();
				tk = new StringTokenizer(line);				
				count = tk.countTokens();
				name="";
				if(count > 2){
					while(count-- > 2){
						name += tk.nextToken() + " ";
					}
					item= new Item(name,tk.nextToken(),tk.nextToken());
					list.add(item);
				}				
			}
			read.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return list;
	}
}

/********************* 		End of FileHandler class		**************/

