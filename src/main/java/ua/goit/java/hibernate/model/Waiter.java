package ua.goit.java.hibernate.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Waiter extends Employee{

    @OneToMany(fetch = FetchType.EAGER) // lazy - by default
    @JoinColumn(name = "employee_id")
    @Fetch(FetchMode.SELECT)  // каким образом будут вытащены данные
    private List<Orders> orders;



    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

//    @Override
//    public String toString() {
//        return "Waiter{" +
//                "orders=" + orders +
//                '}';
//    }

    /*@Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
               *//* ", waiter=" + waiter +*//*
                ", name=" + getName()+
                ", surname=" + getSurname() +
                ", telephone=" + getTelephone() +
                ", position=" + getPosition() +
                ", salary=" + getSalary() +
//                ", salary=" + orders.toString() +
                '}';
    }*/

     @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Waiter {\n");
        sb.append("   ID = ").append(getId()).append("\n");
        sb.append("   name = ").append(getName()).append("\n");
        sb.append("   surname = ").append(getSurname()).append("\n");
        sb.append("   telephone = ").append(getTelephone()).append("\n");
        sb.append("   position = ").append(getPosition()).append("\n");
        sb.append("   salary = ").append(getSalary()).append("\n");
        sb.append("   orders = {\n");
        orders.forEach(order -> sb.append("   ").append(order).append("\n"));
        sb.append("   }\n");
        sb.append("}\n");
        return sb.toString();
    }
}
