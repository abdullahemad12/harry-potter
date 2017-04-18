package eg.edu.guc.supermarket.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class SupermarketView extends JFrame {
	private JPanel pnlProducts;
	private JTextArea txtCart;

	public SupermarketView() {
		// set the windows title
		setTitle("Supermarket");
		// change the default close operation of the JFrame to exit the application instead of hiding the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// set the location and size of the JFrame
		setBounds(50, 50, 800, 600);

		// create a panel to hold the products buy buttons
		pnlProducts = new JPanel();
		// set it to use the GridLayout with 3 columns in width
		pnlProducts.setLayout(new GridLayout(0, 3));
		// add it in the center of the JFrame
		add(pnlProducts, BorderLayout.CENTER);

		// create a text area to hold the text of the cart
		txtCart = new JTextArea();
		// force it to have a width of 200 and the same height of the JFrame
		txtCart.setPreferredSize(new Dimension(200, getHeight()));
		// prevent its contents from being edited
		txtCart.setEditable(false);
		// use a monospaced font to make it look cooler 
		txtCart.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		// add it to the right of the JFrame
		add(txtCart, BorderLayout.EAST);

		// initialize the cart's text by setting its total to zero
		updateCart(new ArrayList<String>(), 0);
	}

	// added product JButtons are simply added to the products buy buttons panel
	public void addProduct(JButton product) {
		pnlProducts.add(product);
	}

	// updates the cart's text with the list of products info and the new total
	public void updateCart(ArrayList<String> products, double total) {
		String cart = "";
		cart += "Cart:\n";
		cart += "'''''\n";
		for (String product : products) {
			cart += "- " + product + "\n";
		}
		if (!products.isEmpty()) {
			cart += "~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
		}
		cart += String.format("\nTotal: %21s", String.format("$%.2f", total));
		txtCart.setText(cart);
	}
}
