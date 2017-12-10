package ru.job4j;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> baseSimpleArray = new SimpleArray<>(10);

    @Override
    public boolean add(T model) {
        if (baseSimpleArray.index < baseSimpleArray.size()) {
            baseSimpleArray.add(model);
        } else {
            SimpleArray<T> tmpArray;
            tmpArray = baseSimpleArray;
            baseSimpleArray  = new SimpleArray<>(baseSimpleArray.size() + 5);
            for (int i = 0; i < tmpArray.size(); i++) {
                baseSimpleArray.add(tmpArray.get(i));
            }
            baseSimpleArray.add(model);
        }
        return true;
    }

    @Override
    /* не совсем понятно по условию возвращаемое значение. Я считаю, что boolean тип более информативный
    * но тем неменее. Если исход операции успешный - то возвращаю старое значение, если элемент найден не будет,
    * то возращаю элемент, который был передан в метод
     */
    public T update(T model) {
        for (int index = 0; index < baseSimpleArray.size(); index++) {
            if (baseSimpleArray.get(index).getId().equals(model.getId())) {
                T modelOld = baseSimpleArray.get(index);
                baseSimpleArray.update(index, model);
                return modelOld;
            }
        }
        return model;
    }

    @Override
    public boolean delete(String id) {
        for (int index = 0; index < baseSimpleArray.size(); index++) {
            if (baseSimpleArray.get(index).getId().equals(id)) {
                baseSimpleArray.delete(index);
                return true;
            }
        }
        return false;
    }

}