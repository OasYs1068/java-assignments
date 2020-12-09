import java.util.*;

public class PlainTextSalesFormatter implements SalesFormatter{
		private static PlainTextSalesFormatter singletonInstance;
		private PlainTextSalesFormatter() {}
		
		public static PlainTextSalesFormatter getSingletonInstance() {
			singletonInstance = new PlainTextSalesFormatter();
			return singletonInstance;
		}
		
		public String formatSales(Sales sales) {
            int i = 1;
		    Iterator orderIterator = sales.iterator();
			while(orderIterator.hasNext()){
                Order order = (Order) orderIterator.next();
				double totalCost = 0.0;
				System.out.println("-------------------------");
				System.out.println("Order "+i);
				System.out.println("\n");
				for(Iterator items = order.iterator(); items.hasNext();){
				    OrderItem item = (OrderItem) items.next();
                    System.out.println(item.toString());
					System.out.println("\n");
                    totalCost = order.getTotalCost();
				}
				System.out.println("Total = "+totalCost);
				i++;
			}
			return(" ");
		}
}
