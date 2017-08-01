import java.util.*;

class Menu{
	private LinkedList<String> veg = new LinkedList<String>();
	private LinkedList<String> nonVeg = new LinkedList<String>();
	private ArrayList<String> user= new ArrayList<String>();
	private Map<String,Category> foodVeg = new HashMap<String,Category>();
	private Map<String,Category> foodNonVeg = new HashMap<String,Category>();
	
	FileHandler fh;
	Scanner inp = new Scanner(System.in);
	
	public LinkedList<String> getVeg() {
		return veg;
	}

	public LinkedList<String> getNonVeg() {
		return nonVeg;
	}

	public Map<String, Category> getFoodVeg() {
		return foodVeg;
	}

	public Map<String, Category> getFoodNonVeg() {
		return foodNonVeg;
	}

	public Menu(ArrayList<String> ob){
		this.user = ob;
		fh = new FileHandler();
		veg = fh.reader(0);
		nonVeg = fh.reader(1);
		reader();
	}
	
	public void reader(){
		
		Iterator<String> itr;
		itr = veg.iterator();
		String name;
		while(itr.hasNext()){
			Category obj = new Category();
			name = itr.next();
			obj.item = fh.foodreader(name);
			foodVeg.put(name,obj);
		}
		itr = nonVeg.iterator();
		while(itr.hasNext()){
			Category obj = new Category();
			name = itr.next();
			obj.item = fh.foodreader(name);
			foodNonVeg.put(name,obj);
		}
		
		//incomplete();
		
	}
	
	void welcome(String name){
		System.out.println(" -----------------------------------------");
		System.out.printf("|*	%s 	 *|\n",name);
		System.out.println(" -----------------------------------------");
	}
	
	void show(){
		System.out.println("Veg Category : \n\t\t" + veg );
		System.out.println("and Non-Veg Category : \n\t\t" + nonVeg);
		boolean flag = true;
		do{
			selectionMenu();
			//opt = inp.next();
			flag = viewItem();
		}while(flag);
		
	}
	
	void foodMenu(){
		System.out.println(" -------------------------------------------------");
		System.out.println("|*	 Press 1 for Veg category                *|");
		System.out.println("|*	 Press 2 for Non-Veg category	         *|");
		System.out.println("|*	 Press anything go to back in Main menu	 *|");
		System.out.println(" -------------------------------------------------");
	}
	
	void newEntry(){
		int opt;
		foodMenu();
		try{
			 opt = inp.nextInt();
		
			switch(opt){
			case 1:
			case 2: addCategory(opt);
				break;
			default : break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	void addCategory(int type){
		System.out.println(" -------------------------------------------------");
		System.out.println("|*	 Press 1 for add new category	         *|");
		System.out.println("|*	 Press 2 for add in existing category	 *|");
		System.out.println("|*	 Press anything go to back in Main menu	 *|");
		System.out.println(" -------------------------------------------------");
		try{
			int opt = inp.nextInt();
			switch(opt){
				case 2: addExisting(type);
					break;
				case 1: newCategory(type);
					break;
				default : break;
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void selectionMenu(){
		System.out.println(" ---------------------------------------------------");
		System.out.println("|*	 Enter name of category to see Item details*|");
		System.out.println("|*	 Enter 0 for back to Main men              *|");
		System.out.println(" ---------------------------------------------------");
	}
	
	boolean verifyExistance(int type,String name){
		boolean flag = true;
			if(type == 1 ){
				if(!veg.contains(name)){
					flag  = false;
				}
			}
			else{
				if(!(nonVeg.contains(name))){
					flag  = false;
				}
			}		
		return flag;
	}
	
	
	void addExisting(int type){
		boolean flag = true;
		selectionMenu();
		
		Iterator<String> itr;
		if(type == 1)
			itr = veg.iterator();
		else
			itr = nonVeg.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		
		String category;
		try{
			category = inp.next();
			flag = verifyExistance(type,category);	
			boolean wishflag = false;
			if(!flag){
				System.out.println("|*	 Category Entered by you is not available! wann create new press y/n *|");
				char wish;
				try{
					wish = inp.next().charAt(0);
					if(wish == 'y'){
						wishflag = true;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(wishflag){
				Category ob = addCategory(type,category,flag);
				ob.item.add(addItem());
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void incomplete(){
		System.out.println(" -----------------------------------");
		System.out.println("|*	 Under Construction Sorry! *|");
		System.out.println(" -----------------------------------");
	}
	
	void newCategory(int type){
		String category;
		System.out.println("Enter the Category name");
		try{
			category = inp.next();
			Category ob = addCategory(type,category,verifyExistance(type,category));
			ob.item.add(addItem());			
		}catch(Exception e){
			e.printStackTrace();
		}
		welcome("Item added Successfully");
		//incomplete();
	}
		
	Category addCategory(int type,String category,boolean flag){
		if(type == 1){
			if(!flag){
				veg.add(category);
				Category ob = new Category();
				foodVeg.put(category, ob);
				return ob;
			}
			return foodVeg.get(category);
		}
		else{
			if(!flag){
				nonVeg.add(category);
				Category ob = new Category();
				foodNonVeg.put(category, ob);
				return ob;
			}
			return foodNonVeg.get(category);
		}
	}
	
	void viewUser(){
		System.out.print("User of Hotel are : \n\t\t " +user + "\n");
	}
	
	boolean admin(){
		int option;
		try{
			do{
				adminMenu();
				option = inp.nextInt();
				switch(option){
					case 1:show();
						break;
					case 2:newEntry();
						break;
					case 4:viewUser();
						break;
					case 3:update();
						break;
				}
			}while(option != 0);
		}catch(Exception e){}
		return true;
	}
	
	void remove(){
		
		incomplete();
	}
	
	void update(){
		incomplete();
	}
			 
	void adminMenu(){
		System.out.println(" -----------------------------------------");
		System.out.println("|*	 Press 1 for view Menu Category	 *|");
		System.out.println("|*	 Press 2 for add a item	         *|");
		System.out.println("|*	 Press 3 for Update Menu    	 *|");
		System.out.println("|*	 Press 4 for view Customer       *|");
		System.out.println("|*	 Press 0 for Log out             *|");
		System.out.println(" -----------------------------------------");
	}
	

	Item addItem(){
		Item ob;
		String name,price,time;
		System.out.println("Enter item name");
		inp = new Scanner(System.in);
		name = inp.nextLine();
		System.out.println("Enter item price as 20rs ");
		price = inp.nextLine();
		System.out.println("Enter item availability as NA/15min");
		time = inp.nextLine();
		ob = new Item(name,price,time);
		return ob;
	}
	boolean customer(){
		int option;
		do{
			customerMenu();
			option = inp.nextInt();
			switch(option){
				case 1:show();
					/*System.out.println(" -----------------------------------------");
					System.out.println("|*	 Enter Category	to view item   *|");
					System.out.println("|*	 Press 0 for back to main menu *|");
					System.out.println(" -----------------------------------------");*/
					//viewItem();
					break;
				case 2:order();
					break;
			}
		}while(option != 0);
		return true;
	}
	
	public boolean viewItem(){
		String name = inp.next();
		//2Category ob;
		boolean flag;
		if(name.equals("0")){
			return false;
		}
		flag = foodVeg.containsKey(name);
		if(flag){
			System.out.println(" Item under " + name +"\n" + foodVeg.get(name));
		}
		else if(flag = foodNonVeg.containsKey(name)){
			System.out.println(" Item under " + name +"\n" + foodNonVeg.get(name));
		}
		else{
			System.out.println(name + " category is not in this hotel");
		}
		return flag;
	}
	
	void order(){
		incomplete();
	}
		
	void customerMenu(){
		System.out.println(" -----------------------------------------");
		System.out.println("|*	 Press 1 for view Menu Category	 *|");
		System.out.println("|*	 Press 2 for Order               *|");
		System.out.println("|*	 Press 0 for Logout              *|");
		System.out.println(" -----------------------------------------");
	}
		
	
}
