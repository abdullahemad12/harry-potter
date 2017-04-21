package eg.edu.guc.supermarket.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import eg.edu.guc.supermarket.model.cart.Cart;
import eg.edu.guc.supermarket.model.product.GroceryProduct;
import eg.edu.guc.supermarket.model.supermarket.Supermarket;
import eg.edu.guc.supermarket.model.supermarket.SupermarketListener;
import eg.edu.guc.supermarket.view.SupermarketView;

public class SupermarketGUI implements SupermarketListener, ActionListener {
	private Supermarket supermarket;
	private SupermarketView supermarketView;
	private ArrayList<JButton> btnsProduct;

	public SupermarketGUI() {
		// create a new instance of Supermarket and set the controller as its listener
		supermarket = new Supermarket();
		supermarket.setListener(this);

		// create a new instance of the SupermarketView
		supermarketView = new SupermarketView();

		// initialize the ArrayList of product buttons
		btnsProduct = new ArrayList<>();

		for (final GroceryProduct product : supermarket.getProducts()) {
			// create a JButton for each product in the supermarket
			JButton btnProduct = new JButton();
			// set its text to the product's info
			btnProduct.setText(product.toString());
			// add the controller as its ActionListener
			btnProduct.addActionListener(this);
			// add it to the products buy buttons panel
			supermarketView.addProduct(btnProduct);

			// and also add it to the ArrayList for later use
			btnsProduct.add(btnProduct);
		}

		// present the frame
		supermarketView.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get the JButton that was clicked
		JButton btnProduct = (JButton) e.getSource();
		// get its index within the ArrayList of buttons
		int productIndex = btnsProduct.indexOf(btnProduct);
		// get the corresponding product from the supermarket
		GroceryProduct product = supermarket.getProducts().get(productIndex);
		System.out.print("bkblkb");
		// invoke the buy method on it
		product.buy();
	}

	@Override
	public void onCartUpdated(Cart cart) {
		// create a list of products info
		ArrayList<String> products = new ArrayList<>();
		for (GroceryProduct product : cart.getProducts()) {
			products.add(product.toString());
		}
		// set the list along with the total to the supermarketView's updateCart method
		supermarketView.updateCart(products, cart.getTotal());
	}

	public static void main(String[] args) {
		// initialize the supermarket GUI
		new SupermarketGUI();
	}
}
