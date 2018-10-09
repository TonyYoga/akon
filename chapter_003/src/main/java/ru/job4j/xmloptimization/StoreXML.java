package ru.job4j.xmloptimization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

/**
 * Genetation of XML from DB
 *  @author  Konkin Anton
 *  @since   1.0
 */
public class StoreXML {
    private File target;

    StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Field> list) {
        //save array<Field> to xml file
        Base base = new Base(list);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Base.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(base, target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @XmlRootElement
    public static class Base {
        private List<Field> field;

        public Base() {
        }

        public Base(List<Field> fields) {
            this.field = fields;
        }

        public List<Field> getField() {
            return field;
        }

        public void setField(List<Field> field) {
            this.field = field;
        }
    }
    @XmlRootElement
    public static class Field {

        private int value;

        public Field() {
        }

        public Field(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }


}
