package ru.job4j.xmlOptimization;

import java.io.File;
import java.security.KeyStore;
import java.util.List;

/**
 * Genetation of XML from DB
 *  @author  Konkin Anton
 *  @since   1.0
 */
public class StoreXML {
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void setTarget(File target) {
        this.target = target;
    }

    public File getTarget() {
        return this.target;
    }

    public boolean save(List<KeyStore.Entry> list) {
        return true;
    }
}
