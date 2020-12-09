package coffee;

public class Product {
		protected String code;
		protected String description;
		protected double price;
		
		public Product(String code,String description, double price) {
			this.code = code;
			this.description = description;
			this.price = price;
		}
		
		public String getCode() {
			return code;
		}
		
		public String getDescription() {
			return description;
		}
		
		public double getPrice() {
			return price;
		}
		
		public boolean equals(Object object) {
			if(object instanceof Product) {
				if(!(code==((Product)object).getCode())) {
					return false;
				}
					return true;
			}
			else {
				return false;
			}
		}
		
		public String toString() {
			return(code+"_"+description+"_"+price);
		}
}
