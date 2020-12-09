import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

/**
 * This class displays the catalog of the gourmet coffee system.
 *
 * @author author name
 * @version 1.1.0
 * @see Product
 * @see Coffee
 * @see CoffeeBrewer
 * @see Catalog
 * @see CatalogLoader
 * @see FileCatalogLoader
 * @see DataFormatException
 * @see DataField
 */
public class CatalogGUI extends JPanel {

	/* Standar error stream */
	static private PrintWriter  stdErr = new  PrintWriter(System.err, true);

	/* Window width in pixels */
	static private int WIDTH = 420;

	/* Window height in pixels */
	static private int HEIGHT = 320;

	/* Size of the list cell in pixels */
	static private int CELL_SIZE = 50;

	/* Visible rows in list */
	static private int LIST_ROWS = 10;

	/* Rows in status text area */
	static private int STATUS_ROWS = 5;

	/* Rows in status text area */
	static private int STATUS_COLS = 40;

	private JList  catalogList;
	private JPanel productPanel;
	private JTextArea statusTextArea;

	private Catalog  catalog;

	/**
	 * Loads a product catalog and starts the application.
	 *
	 * @param args  String arguments.  Not used.
	 * @throws IOException if there are errors in the loading
	 *                     the catalog.
	 */
	public static void  main(String[]  args) throws IOException {

		String filename = "";

		if (args.length != 1) {
			filename = "catalog.dat";
		} else {
			filename = args[0];
		}
		try {
			Catalog catalog =
				(new FileCatalogLoader()).loadCatalog(filename);

			JFrame frame = new JFrame("Catalog Gourmet Coffee");

			frame.setContentPane(new CatalogGUI(catalog));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(WIDTH, HEIGHT);
			frame.setResizable(true);
			frame.setVisible(true);

		} catch (FileNotFoundException fnfe) {
			stdErr.println("The file does not exist");

			System.exit(1);

		} catch (DataFormatException dfe) {
			stdErr.println("The file contains malformed data: "
			               + dfe.getMessage());

			System.exit(1);
		}
	}

	/**
	 * Instantiates the components and arranges them in a window.
	 *
	 * @param initialCatalog a product catalog.
	 */
	public  CatalogGUI(Catalog initialCatalog) {

		catalog = initialCatalog;

 		// create the components
		catalogList = new JList(catalog.getCodes());
		catalogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		catalogList.setVisibleRowCount(LIST_ROWS);
		catalogList.setFixedCellWidth(CELL_SIZE);
		statusTextArea = new JTextArea(STATUS_ROWS, STATUS_COLS);
		statusTextArea.setEditable(false);

		// create product information panel
		productPanel = new JPanel();
		productPanel.setBorder(
				BorderFactory.createTitledBorder("Product Information"));

		// create panel to hold list and Display button
		JPanel catalogPanel = new JPanel();
		catalogPanel.setBorder(BorderFactory.createTitledBorder("Catalog"));
		catalogPanel.add (
			new JScrollPane(catalogList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		setLayout(new BorderLayout());
		add(catalogPanel, BorderLayout.WEST);
		add(productPanel, BorderLayout.CENTER);
		add(statusTextArea, BorderLayout.SOUTH);

		// start listening for list events
		catalogList.addListSelectionListener(new CatalogListListener());
	}

	/*
	 * Obtains a {@link JPanel} object with the information of a product.
	 *
	 * @param dataFields  an {@link ArrayList} of {@link DataField}
	 *                    with the product information.
	 * @return  a {@link JPanel} with the product information.
	 */
	private JPanel getDataFieldsPanel(ArrayList<DataField> dataFields) {
		JPanel informationPanel = new JPanel();
		informationPanel.setLayout(new GridBagLayout());
		GridBagConstraints c;

		switch (dataFields.size()){
			case 9:
				informationPanel.removeAll();
				JLabel[] labelsCoffee = new JLabel[9];
				labelsCoffee[0] = new JLabel("Code:");
				labelsCoffee[1] = new JLabel("Description:");
				labelsCoffee[2] = new JLabel("Price:");
				labelsCoffee[3] = new JLabel("Origin:");
				labelsCoffee[4] = new JLabel("Roast:");
				labelsCoffee[5] = new JLabel("Flavor:");
				labelsCoffee[6] = new JLabel("Aroma:");
				labelsCoffee[7] = new JLabel("Acidity:");
				labelsCoffee[8] = new JLabel("Body:");

				JTextArea[] textAreasCoffee = new JTextArea[9];

				c = new GridBagConstraints();
				c.gridx = 0;
				c.weightx = 1;
                c.gridwidth = 1;
                c.insets = new Insets(1,1,1,1);
				for(int i = 0;i<9;i++){
					c.gridy = i;
					informationPanel.add(labelsCoffee[i],c);
				}

				c = new GridBagConstraints();
				c.gridx = 2;
				c.weightx = 3;
                c.gridwidth = 3;
                c.insets = new Insets(1,1,1,1);
				c.fill = GridBagConstraints.HORIZONTAL;
				for(int i =0;i<9;i++){
					c.gridy = i;
					textAreasCoffee[i] = new JTextArea("No Data");
					textAreasCoffee[i].setEditable(false);
					textAreasCoffee[i].setVisible(true);
					textAreasCoffee[i].setText(dataFields.get(i).getValue());
					informationPanel.add(textAreasCoffee[i],c);
				}
				break;
			case 6:
				informationPanel.removeAll();
				JLabel[] labelsCoffeeBrewer = new JLabel[6];
				labelsCoffeeBrewer[0] = new JLabel("Code:");
				labelsCoffeeBrewer[1] = new JLabel("Description:");
				labelsCoffeeBrewer[2] = new JLabel("Price:");
				labelsCoffeeBrewer[3] = new JLabel("Model:");
				labelsCoffeeBrewer[4] = new JLabel("Water Supply:");
				labelsCoffeeBrewer[5] = new JLabel("Number of Cups:");

				JTextArea[] textAreasCoffeeBrewer = new JTextArea[6];
				c = new GridBagConstraints();
				c.gridx = 0;
				c.weightx = 1;
				c.gridwidth = 1;
				c.insets = new Insets(1,1,1,1);
				for(int i = 0;i<6;i++){
					c.gridy = i;
					informationPanel.add(labelsCoffeeBrewer[i],c);
				}

				c = new GridBagConstraints();
				c.gridx = 2;
				c.weightx = 3;
				c.gridwidth = 3;
                c.insets = new Insets(1,1,1,1);
				c.fill = GridBagConstraints.HORIZONTAL;
				for(int i =0;i<6;i++){
					c.gridy = i;
					textAreasCoffeeBrewer[i] = new JTextArea("No Data");
					textAreasCoffeeBrewer[i].setEditable(false);
					textAreasCoffeeBrewer[i].setVisible(true);
					textAreasCoffeeBrewer[i].setText(dataFields.get(i).getValue());
					informationPanel.add(textAreasCoffeeBrewer[i],c);
				}
				break;
			case 3:
				informationPanel.removeAll();
				JLabel[] labelsProduct = new JLabel[3];
				labelsProduct[0] = new JLabel("Code:");
				labelsProduct[1] = new JLabel("Description:");
				labelsProduct[2] = new JLabel("Price:");

				JTextArea[] textAreasProduct = new JTextArea[3];
				c = new GridBagConstraints();
				c.gridx = 0;
				c.weightx = 1;
                c.gridwidth = 1;
                c.insets = new Insets(1,1,1,1);
				for(int i = 0;i<3;i++){
					c.gridy = i;
					informationPanel.add(labelsProduct[i],c);
				}

				c = new GridBagConstraints();
				c.gridx = 2;
				c.weightx = 3;
                c.gridwidth = 3;
                c.insets = new Insets(1,1,1,1);
				c.fill = GridBagConstraints.HORIZONTAL;
				for(int i =0;i<3;i++){
					c.gridy = i;
					textAreasProduct[i] = new JTextArea("No Data");
					textAreasProduct[i].setEditable(false);
					textAreasProduct[i].setVisible(true);
					textAreasProduct[i].setText(dataFields.get(i).getValue());
					informationPanel.add(textAreasProduct[i],c);
				}
				break;
		}
		return informationPanel;
	}

	/**
	 * This inner class handles list-selection events.
	 */
	class CatalogListListener implements ListSelectionListener {

		/**
		 * Displays the information of the selected product.
		 *
		 * @param event  the event object.
		 */
		public void valueChanged(ListSelectionEvent event) {

			String code = (String) catalogList.getSelectedValue();
			Product product = catalog.getProduct(code);

			productPanel.removeAll();
			productPanel.setVisible(false);                   // Use this
			productPanel.add(                                 // to update
				getDataFieldsPanel(product.getDataFields())); // the panel
			productPanel.setVisible(true);                    // correctly

			statusTextArea.setText("Product " + code + " has been displayed");
		}
	}
}