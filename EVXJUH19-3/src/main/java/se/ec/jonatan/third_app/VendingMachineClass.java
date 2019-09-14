package se.ec.jonatan.third_app;

import java.util.*;

public class VendingMachineClass implements VendingMachine {
	protected int[] insertableValues;
	private int change;
	private Product soda;
	private Product chips;
	private Product chocolate;
	protected Product[] products;
	protected boolean keepBuying;
	protected ArrayList<Product> boughtProducts;
	
	public VendingMachineClass() {
		insertableValues = new int[] {1, 5, 10, 20, 50, 100, 500, 1000};
		change = 0;
		soda = new FoodItem("Coke", "20");
		chips = new FoodItem("OLW", "60");
		chocolate = new FoodItem("Marabou", "140");
		products = new Product[3];
		products[0] = soda;
		products[1] = chips;
		products[2] = chocolate;
		keepBuying = true;
		boughtProducts = new ArrayList<Product>();
	}
	
	public void addCurrency(int amount) {
		boolean contains = false;
		for(int i : insertableValues) {
            if(i==amount) {
            	contains = true;
            }
        }
		if(contains) {
			change += amount;
			System.out.println("\n----- " + getBalance() + " kr inserted -----\n");
		}
		else {
			System.out.println("Can only add the mentioned values!");
		}
	}

	public int getBalance() {
		return change;
	}
	
	public Product request(int productNumber) {
		if(change<Integer.parseInt(products[productNumber].cost)) {
			System.out.println("You haven't inserted enough money for that item");
		}
		else { 
			change -= Integer.parseInt(products[productNumber].cost);
			System.out.println("\nYou buy a " + products[productNumber].name);
			boughtProducts.add(products[productNumber]);
			System.out.println("Remaining balance is: " + getBalance() + " kr\n");
		}
		return products[productNumber];
	}

	public int endSession() {
		int inReturn = change;
		change = 0;
		keepBuying = false;
		return inReturn;
	}

	public String getDescription(int productNumber) {
		int i=0;
		while(i<products.length) {
			System.out.println(i+1 + ": " + products[i].examine());
			i++;
		}
		return products[productNumber].examine();
	}

	public String[] getProducts() {
		String[] stringArr = new String[products.length];
		for(int i=0; i<1; i++) {
			stringArr[i] = getDescription(i);
		}
		return stringArr;
	}

}