package pl.pcdevs.systemfinansowy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import javax.annotation.processing.Generated;

@Entity
public class DataGenarate {

    @Id
    private int id;
    private boolean genarated;

    public DataGenarate(int id, boolean genarated) {
        this.id = id;
        this.genarated = genarated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGenarated() {
        return genarated;
    }

    public void setGenarated(boolean genarated) {
        this.genarated = genarated;
    }
}
