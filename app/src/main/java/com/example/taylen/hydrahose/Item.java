package com.example.taylen.hydrahose;

public class Item {


    protected String _id;
    protected String dimensions;
    protected String name;
    protected String part_number;
    protected String psi;

    public Item(String _id, String dimensions, String name, String part_number, String psi) {
        this._id = _id;
        this.dimensions = dimensions;
        this.name = name;
        this.part_number = part_number;
        this.psi = psi;
    }

    public Item() {
        // Empty constructor
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPart_number() {
        return part_number;
    }

    public void setPart_number(String part_number) {
        this.part_number = part_number;
    }

    public String getPsi() {
        return psi;
    }

    public void setPsi(String psi) {
        this.psi = psi;
    }

    @Override
    public String toString() {
        return "Item{" +
                "_id='" + _id + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", name='" + name + '\'' +
                ", part_number='" + part_number + '\'' +
                ", psi='" + psi + '\'' +
                '}';
    }
}
