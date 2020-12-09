import java.util.Iterator;

public class XMLSalesFormatter implements SalesFormatter {
	private static XMLSalesFormatter singletonInstance;

	private XMLSalesFormatter() {}

	static public XMLSalesFormatter getSingletonInstance() {
		singletonInstance = new XMLSalesFormatter();
		return singletonInstance;
	}

	public String formatSales(Sales sales) {
		System.out.println("<Sales>");
		Iterator orderIterator = sales.iterator();
		while (orderIterator.hasNext()) {
			Order order = (Order) orderIterator.next();
			double totalCost = 0;
			totalCost = order.getTotalCost();
			System.out.println("<Order total= \"" + totalCost+"\">");
            for(Iterator items = order.iterator(); items.hasNext();){
                OrderItem item = (OrderItem) items.next();
				System.out.println("<OrderItem quantity= \"" + item.getQuantity() +
                        "\"\nprice= \"" +item.getProduct().getPrice()+
                        "\">"+item.getProduct().getCode()+
                        "</OrderItem>\n</Order>");
			}
			System.out.println("</Sales>");
		}
		return (" ");
	}
}
