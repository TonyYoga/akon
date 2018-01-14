package ru.job4j.stock;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockLadderBuilder {

    static Map<String, StockBook> stockLadder(List<Order> orderList) {
        Map<String, StockBook> resultBook = new HashMap<>();
        for (Order o: orderList) {
            if (o == null || !o.isAvailable()) {
                continue; //skip first null element with 0 index and deleted orders
            }

            if (!resultBook.containsKey(o.getBookname())) {
                resultBook.put(o.getBookname(), new StockBook(o.getBookname()));
            }
            resultBook.get(o.getBookname()).build(o); //обрабатываем каждый ордер
        }

        return resultBook;
    }




    public static void main(String[] args) {
        //String filename = "/home/user/projects/akon/file/order.xml";
        String filename = "/home/user/Downloads/orders.xml";
        XMLparser xmLparser = new XMLparser(filename);
        Long start, end;
        start = System.nanoTime();
        List<Order> orderList = xmLparser.historyParser();
        Map<String, StockBook> bookList = stockLadder(orderList);

        for (Map.Entry<String, StockBook> books: bookList.entrySet()) {
            System.out.println(books.getValue());
        }

        end = System.nanoTime(); //check time
        System.out.println((end - start) * 0.000000001);
        System.out.println();


    }


}
