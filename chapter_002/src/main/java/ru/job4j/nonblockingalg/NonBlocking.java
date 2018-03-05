package ru.job4j.nonblockingalg;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlocking {
    private ConcurrentHashMap<Integer, Model> modelcash = new ConcurrentHashMap<>();
    private volatile int index = 0;



    static class Model {
        String name;
        Integer version;

        public Model(String name) {
            this.name = name;
            this.version = 0;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            versionUpd();
        }

        public Integer getVersion() {
            return version;
        }

        private void setVersion(Integer version) {
            this.version = version;
        }

        public void versionUpd() {
            setVersion(getVersion() + 1);
        }
    }

    public boolean add(Model model) {
        modelcash.put(index++, model);
        return true;
    }

    public  boolean update(int indx, Model model) {
        if (modelcash.get(indx).getVersion() != model.getVersion()) {
            return false; //здесь выбрасывать исключение

        }
        model.versionUpd();
        modelcash.computeIfPresent(indx, (key, value) -> model);
        return true;
    }

    public boolean delete(int indx) {
        if (!modelcash.containsKey(indx)) {
            return false;
        }
        modelcash.remove(indx);
        return true;
    }

}
