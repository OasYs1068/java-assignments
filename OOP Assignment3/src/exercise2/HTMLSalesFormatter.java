import java.util.Iterator;

public class HTMLSalesFormatter implements SalesFormatter{
		private static HTMLSalesFormatter singletonInstance;
		private HTMLSalesFormatter() {}
		static public HTMLSalesFormatter getSingletonInstance() {
			singletonInstance = new HTMLSalesFormatter();
			return singletonInstance;
		}
		
		public String formatSales(Sales sales) {
		    System.out.println("<html>\n\t<body>\n\t\t<center><h2>Orders</h2></center>");
			Iterator orderIterator = sales.iterator();
			while(orderIterator.hasNext()){
				Order order = (Order) orderIterator.next();
				double totalCost = 0;
				totalCost = order.getTotalCost();
				System.out.println("<hr>\n<h4>Total = "+totalCost);
                for(Iterator items = order.iterator(); items.hasNext();){
                    OrderItem item = (OrderItem) items.next();
					System.out.println("\t<p>");
					System.out.println("\t\t<b>code:</b> "+item.getProduct().getCode()+"<br>");
					System.out.println("\t\t<b>quantity:</b> "+item.getQuantity()+"<br>");
					System.out.println("\t\t<b>Price:</b> "+item.getProduct().getPrice()+"<br>");
					System.out.println("</p>");

				}
				System.out.println("\t</body>\n</html>");
			}
			return(" ");
		}
}
