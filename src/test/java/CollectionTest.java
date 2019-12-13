import org.testng.annotations.Test;

import java.util.*;

public class CollectionTest {
    //ArrayList
    /*
        Contains Duplicates
        Maintains Insertion Order
     */
    @Test
    public static void arrayListTest() {
        List<String> TopCompanies = new ArrayList<String>();
        System.out.println(TopCompanies.isEmpty());

        TopCompanies.add("Google");
        TopCompanies.add("AmaZon");
        TopCompanies.add("Wipro");
        TopCompanies.add("oracle");
        System.out.println(TopCompanies.isEmpty());

        for (String topCompany : TopCompanies) {

        }
        for (int i = 0; i < TopCompanies.size(); i++) {
            System.out.println(TopCompanies.get(i));
        }
        Iterator<String> comIte = TopCompanies.iterator();
        while (comIte.hasNext()) {
            System.out.println(comIte.next());

        }
        //   TopCompanies.forEach((name)->{
        //     System.out.println(name);
        //});
    }
    @Test
    public static void linkedListTest(){
        List<String>   humanSpeci = new LinkedList<String>();
        humanSpeci.add("Homo Sapiens");
        humanSpeci.add("Homo Erectus");
        humanSpeci.add("Homo Nearth ");
        humanSpeci.add("Homo Asian");

        Iterator<String> humanIterator=humanSpeci.iterator();
        while(humanIterator.hasNext()) {
            System.out.println(humanIterator.next());
        }
    }

}





