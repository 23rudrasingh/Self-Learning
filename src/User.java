import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/***************** 		This class is used to handle user related issues Like Login and sign up 	****************/
class User{
	
	//public enum type {Admin,Customer};
	private int type;
	private ArrayList<String> admin= new ArrayList<String>();
	private ArrayList<String> user= new ArrayList<String>();
	FileHandler fh;
	Scanner inp = new Scanner(System.in);
	private String cUser;

	public User() {
		fh = new FileHandler();
		admin = fh.adminUsers(0);
		user = fh.adminUsers(1);
	}
	
	String adminUseres(){
		String content = "";
		Iterator<String> itr =admin.iterator();
		while(itr.hasNext()){
			content += itr.next() + "\n";
		}
		return content;
	}
	
	String useres(){
		String content = "";
		Iterator<String> itr =user.iterator();
		while(itr.hasNext()){
			content += itr.next() + "\n";
		}
		return content;
	}
	
	boolean authentication(){
		try{
			type = inp.nextInt();
		}catch(Exception e){
			e.printStackTrace();
		}
				
		if(type == 1){ 
			cUser = uid();//if user type is Admin
			if(admin.contains(cUser))
				return true;
		}
		else if(type == 2){	
			cUser = uid();//if user type is Customer
			if(user.contains(cUser))
				return true;
		}
		else if(type == 3)
			return signUp();
		return false;
	}
	
	String uid(){
		System.out.print("|* User Id :  ");
		return inp.next();
	}
	
	boolean signUp(){
			
		System.out.println(" ---------------------------------");
		System.out.println("|*	 Press 1 for admin	 *|");
		System.out.println("|*	 Press 2 for Customer	 *|");
		System.out.println(" ---------------------------------");
		try{
			type = inp.nextInt();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		cUser = uid();
		inp.close();
		if(type == 1){ 		//if user type is Admin
			admin.add(cUser);
			return true;
		}
		else if(type == 2){		//if user type is Customer
			user.add(cUser);
			return true;
		}
		else
			return false;
		
	}
	
	public int getType(){
		return this.type;
	}
	
	public String getCuser(){
		return cUser;
	}
	
	public ArrayList<String> getUser() {
		return user;
	}


	public void setUser(ArrayList<String> user) {
		this.user = user;
	}
}
/********************* 		End of User class		**************/