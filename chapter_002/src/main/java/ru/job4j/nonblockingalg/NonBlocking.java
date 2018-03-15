package ru.job4j.nonblockingalg;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

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

    class OplimisticException extends RuntimeException {
        public OplimisticException(String message) {
            super(message);
        }
    }

    public boolean add(Model model) {
        modelcash.put(index++, model);
        return true;
    }

    public  boolean update(int indx, Model model) throws OplimisticException {

        BiFunction<Integer, Model, Model> bf = new BiFunction<Integer, Model, Model>() {
            @Override
            public Model apply(Integer key, Model oldModel) {
                if (model.getVersion() == oldModel.getVersion()) {
                    model.versionUpd();
                    return model;
                }
                throw  new OplimisticException("Conflict of version during update");
                //return null;

            }
        };
        modelcash.computeIfPresent(indx, bf);
        if (modelcash.get(indx).getVersion() != model.getVersion()) {
            throw new OplimisticException("Conflict of version during update");

        }
        //model.versionUpd();
        //modelcash.computeIfPresent(indx, (key, value) -> model);

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
