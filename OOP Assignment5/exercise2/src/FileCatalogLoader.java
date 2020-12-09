import java.io.*;
import java.util.StringTokenizer;

public class FileCatalogLoader implements CatalogLoader{
    public FileCatalogLoader(){}

    private Product readProduct(String line) throws DataFormatException{
        String[] strings = new String[0];
        try {
            StringTokenizer st = new StringTokenizer(line,"_",false);
            strings = new String[4];
            int i = 0;
            while(st.hasMoreTokens()){
                strings[i] = st.nextToken();
                i++;
            }
            if(i!=3||!(strings[3].contains("."))){
                throw new DataFormatException();
            }
        } catch (DataFormatException e) {
        }
        return new Product(strings[1],strings[2],Double.parseDouble(strings[3]));
    }

    private Coffee readCoffee(String line) throws DataFormatException{
        String[] strings = new String[0];
        try {
            StringTokenizer st = new StringTokenizer(line,"_",false);
            strings = new String[10];
            int i = 0;
            while(st.hasMoreTokens()){
                strings[i] = st.nextToken();
                i++;
            }
            if(i!=9||!(strings[3].contains("."))){
                throw new DataFormatException();
            }
        } catch (DataFormatException e) {
        }
        return new Coffee(strings[1],strings[2],Double.parseDouble(strings[3]),strings[4]
                          ,strings[5],strings[6],strings[7],strings[8],strings[9]);
    }

    private CoffeeBrewer readCoffeeBrewer(String line) throws DataFormatException{
        String[] strings = new String[0];
        try {
            StringTokenizer st = new StringTokenizer(line,"_",false);
            strings = new String[7];
            int i = 0;
            while(st.hasMoreTokens()){
                strings[i] = st.nextToken();
                i++;
            }
            if(i!=6||!(strings[3].contains("."))){
                throw new DataFormatException();
            }
        } catch (DataFormatException e) {
        }
        return new CoffeeBrewer(strings[1],strings[2],Double.parseDouble(strings[3]),strings[4]
                ,strings[5],Integer.parseInt(strings[6]));
    }

    public Catalog loadCatalog(String fileName) throws FileNotFoundException,
            IOException, DataFormatException {
        Catalog catalog = null;
        try {
            catalog = new Catalog();
            FileReader fileIn = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fileIn);
            String line;
            while((line = br.readLine())!=null){
                if(line.startsWith("Product")){
                    catalog.addProduct(readProduct(line));
                }
                if(line.startsWith("Coffee")){
                    catalog.addProduct(readCoffee(line));
                }
                if(line.startsWith("Brewer")){
                    catalog.addProduct(readCoffeeBrewer(line));
                }
            }
            fileIn.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
        return catalog;
    }


}
