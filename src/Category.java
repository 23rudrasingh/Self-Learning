import java.util.Iterator;
import java.util.LinkedList;


/********************** This is Category class which will hold item list *********************/

class Category{
	//String categoryName;
	LinkedList<Item> item = new LinkedList<Item>();
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s ="";
		Iterator<Item> itr=item.iterator();
		while(itr.hasNext()){
			s += itr.next() + "\n";
		}
		return s;
	}
}

class Item{
	private String itemName,price,availabilty;
	public Item(String itemName,String price,String availabilty){
		this.availabilty = availabilty;
		this.itemName = itemName;
		this.price = price;
	}
	
	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public String getAvailabilty() {
		return availabilty;
	} 
	
	public String getItemName() {
		return itemName;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setAvailabilty(String availabilty) {
		this.availabilty = availabilty;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s= itemName + " " + price + " " + availabilty ;
		return s;
	}
}