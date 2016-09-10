package ua.goit.java.hibernate.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Cook extends Employee{

    @OneToMany(fetch = FetchType.EAGER) // lazy - by default
    @JoinColumn(name = "employee_id")
    @Fetch(FetchMode.SUBSELECT)  // каким образом будут вытащены данные
    private List<PreparedDish> preparedDishes;

    public List<PreparedDish> getPreparedDishes() {
        return preparedDishes;
    }

    public void setPreparedDishes(List<PreparedDish> preparedDishes) {
        this.preparedDishes = preparedDishes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cook {\n");
        sb.append("   ID = ").append(getId()).append("\n");
        sb.append("   name = ").append(getName()).append("\n");
        sb.append("   surname = ").append(getSurname()).append("\n");
        sb.append("   telephone = ").append(getTelephone()).append("\n");
        sb.append("   position = ").append(getPosition()).append("\n");
        sb.append("   salary = ").append(getSalary()).append("\n");
        sb.append("   orders = {\n");
        preparedDishes.forEach(dish -> sb.append("   ").append(dish).append("\n"));
        sb.append("   }\n");
        sb.append("}\n");
        return sb.toString();
    }
}
