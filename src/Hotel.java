import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/*****************************************************************************/
/**************		This Application is built for a Hotel		**************/
/*****************************************************************************/

public class Hotel {
	static String cUser;
	static int ctype;
	static Menu menu;
	static User ob;
	public static void main(String[] args) {
		
		do{
			welcomeMsg();
			logIn();			
			ob = new User();
			boolean status = ob.authentication();	
			ctype = ob.getType();
			if(status){
				System.out.println("|*	 Log in Successfull	 *|");
				cUser = ob.getCuser();
				home(ob.getUser());
				System.out.printf("|*	 Mr. %s You Log out Successfully	 *|\n",cUser);
			}
			else if(ctype != 0)
				System.out.println("|*	 Log in failed	 *|" );
		}while(ctype != 0);
		update();
	}
	
	static void welcomeMsg(){
		System.out.println("=====================================================");
		System.out.println("=========	 Welcome To Rajputana	 ============");
		System.out.println("=====================================================");
		
	}
	
	static void logIn(){
		System.out.println(" ------------------------------------------");
		System.out.println("|*	 Press 1 for admin               *|");
		System.out.println("|*	 Press 2 for Customer	         *|");
		System.out.println("|*	 Press 3 for Sign up	         *|");
		System.out.println("|*	 Press 0 for exit from program	 *|");
		System.out.println(" ------------------------------------------");
	}

	public static void home(ArrayList<String> ob){
		menu = new Menu(ob);
		menu.welcome("Mr. " + cUser + " Welcome To Rajputana");
		if(ctype == 1)
			menu.admin();
		else 
			menu.customer();
	}
	
	public static void update(){
		String content = "";
		FileHandler fh = new FileHandler();
		Iterator<String> itr = menu.getVeg().iterator();
		content = save(itr,menu.getFoodVeg(),fh);
		fh.writer(fh.menu[0],content);
		itr = menu.getNonVeg().iterator();
		content = save(itr,menu.getFoodNonVeg(),fh);
		System.out.println(" hi " + content);
		fh.writer(fh.menu[1],content);
		fh.writer(fh.user[0],ob.adminUseres());
		fh.writer(fh.user[1],ob.useres());
		menu.welcome("Thanks for visit Rajputana");
	}
	
	static String save(Iterator<String> itr,Map<String,Category> map,FileHandler fh){
		String name,s = "";
		int c = 0;
		try{
			while(itr.hasNext()){
				name = itr.next();
				System.out.println(c++ + " and " + name);
				fh.writer(name,map.get(name).toString());
				s += name + "\n";
			}
		}catch(Exception e){e.printStackTrace();}
		System.out.println(s);
		return s;
	
	}
}
