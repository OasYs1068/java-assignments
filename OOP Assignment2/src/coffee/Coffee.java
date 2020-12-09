package coffee;

public class Coffee extends Product{
		private String origin;
		private String roast;
		private String flavor;
		private String aroma;
		private String acidity;
		private String body;
		
		public Coffee(String initialCode,
					              String initialDescription,
					              double initialPrice,
					              String initialOrigin,
					              String initialRoast,
					              String initialFlavor,
					              String initialAroma,
					              String initialAcidity,
					              String initialBody) {
			super(initialCode, initialDescription, initialPrice);
			origin = initialOrigin;
			roast = initialRoast;
			flavor = initialFlavor;
			aroma = initialAroma;
			acidity = initialAcidity;
			body = initialBody;
		}
		
		public String getOrigin() {
			return origin;
		}
		public String getRoast() {
			return roast;
		}
		public String getFlavor() {
			return flavor;
		}
		public String getAroma() {
			return aroma;
		}
		public String getAcidity() {
			return acidity;
		}
		public String getBody() {
			return body;
		}
		
		public String toString() {
			return(code+"_"+description+"_"+price+"_"+origin
					    +"_"+roast+"_"+flavor+"_"+aroma+"_"+acidity
					    +"_"+body);
		}
}
