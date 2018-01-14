package ru.job4j.stock;

import java.util.*;

public class StockBook {


    /**
     * Map<Double,Integer> Price|Volume
     * ask - BUY order
     * bid - SELL order
     */

    String bookname;
    Map<Double, Integer> bidladder;
    Map<Double, Integer> askladder;




    public StockBook(String bookname) {
        this.bookname = bookname;
        bidladder = new TreeMap<>(Comparator.reverseOrder());
        askladder = new TreeMap<>(Comparator.naturalOrder());

    }

    /**
     * Алгоритм
     * Входящий ордер может быть двух видов Sell/Buy соответственно bidladdder таблица и aslladder таблица.
     * Для начала проверяем противоположную таблицу для sell -> bidladder и наоборот.
     * Если выполняется условие bid>=ask то вносим соответвующие изменения в таблицы изменяя объем котировки, если условие
     * не соблюдается, то вносим в соответствующую таблицу данные о цене и объеме либо суммируем объемы
     * Алгоритм по шагам для SELL (для Buy будет обратный процесс)
     * 1. Проверям флаг операции, если SELL то ->
     * 2. Если таблица ASK котировок пустая - то сразу заносим данные в BID таблицу и завершаем работу метода
     * 3. Если данные в ASK таблице есть, то
     * 4. Создаем копию таблицы ASK для того чтобы при переборе уровней цен можно было работать с исходной таблицей
     * 5. Проверяем цену по условию sell price >= buy price
     * 6. Проверяем объем.
     * 6.1. Если объем ордера меньше, чем в таблице BUY - редактируем объем в таблице и выходим из цикла
     * 6.2. Если объем оредра больше, чем в таблице BUY - редактируем объем оредра и удаляем запись из таблицы,
     * проверям следующию запись.
     * 6.3. Если объем оредра равен объему в таблице - удаляем запись из таблицы и выходим из цикла
     *
     * @param order
     */
    public void build(Order order) {
        //logic (bid>=ask) sell price >= buy price
        int orderVolume = order.getVolume();

        if (order.getOperation() == Order.Operation.SELL) { // sell order
            if (askladder.isEmpty()) { //таблица buy пустая
                bidladder.put(order.getPrice(), orderVolume);
                return;
            }
            Map<Double, Integer> copyAskQ = new TreeMap<>(askladder);

            for (Map.Entry<Double, Integer> ladder: copyAskQ.entrySet()) {
                if (ladder.getKey() <= order.getPrice()) { //цена из таблицы BUY <= цены Sell оредра
                    //сравнение объемов
                    if (orderVolume < ladder.getValue()) { //объем ореда < объема в таблице
                        askladder.put(ladder.getKey(), ladder.getValue() - orderVolume);
                        return;
                    } else if (orderVolume > ladder.getValue()) { //объем ореда > объема в таблице
                        orderVolume = orderVolume - ladder.getValue(); //обновляем объем оредра
                        askladder.remove(ladder.getKey()); //удаляем из таблицы запись с нулевым объемом
                    } else { //объем ореда = объему в таблице
                        askladder.remove(ladder.getKey());
                        return;

                    }
                } else {
                    bidladder.put(order.getPrice(), ladder.getValue() + orderVolume);
                }


            }

        } else { // buy order
            if (bidladder.isEmpty()) {
                askladder.put(order.getPrice(), orderVolume);
                return;
            }
            Map<Double, Integer> copyBidQ = new TreeMap<>(bidladder);

            for (Map.Entry<Double, Integer> ladder: copyBidQ.entrySet()) {

                if (ladder.getKey() >= order.getPrice()) { //цена из таблицы SELL >= цены BUY оредра
                    //сравнение объемов
                    if (orderVolume < ladder.getValue()) { //объем ореда < объема в таблице
                        bidladder.put(ladder.getKey(), ladder.getValue() - orderVolume);
                        return;
                    } else if (orderVolume > ladder.getValue()) { //объем ореда > объема в таблице
                        orderVolume = orderVolume - ladder.getValue(); //обновляем объем оредра
                        bidladder.remove(ladder.getKey()); //удаляем из таблицы запись с нулевым объемом
                    } else { //объем ореда = объему в таблице
                        bidladder.remove(ladder.getKey());
                        return;

                    }
                } else {
                    askladder.put(order.getPrice(), ladder.getValue() + orderVolume);
                }

            }


        }

    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Order book: $ %s \n", bookname));
        stringBuilder.append(String.format("%-15s - %-15s \n", "BID", "ASK"));

        Iterator<Map.Entry<Double, Integer>> iterBid = bidladder.entrySet().iterator();
        Iterator<Map.Entry<Double, Integer>> iterAsk = askladder.entrySet().iterator();

        while (iterBid.hasNext() || iterAsk.hasNext()) {
            String bid, ask;
            if (iterBid.hasNext()) {
                Map.Entry<Double, Integer> entry = iterBid.next();
                bid = String.format("%d@%.2f", entry.getValue(), entry.getKey());
            } else {
                bid = "--------";
            }
            if (iterAsk.hasNext()) {
                Map.Entry<Double, Integer> entry = iterAsk.next();
                ask = String.format("%d@%.2f", entry.getValue(), entry.getKey());
            } else {
                ask = "--------";
            }
            stringBuilder.append(String.format("%-15s - %-15s \n", bid, ask));
        }

        return stringBuilder.toString();
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
