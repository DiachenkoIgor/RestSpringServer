package server.Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IgorPc on 11/20/2018.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Musician {
    private String name;
    private String instrument;
    private double salary;

    public Musician() {
    }

    public Musician(String name, String instrument, double salary) {
        this.name = name;
        this.instrument = instrument;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
