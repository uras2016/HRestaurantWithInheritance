package ua.goit.java.hibernate.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prepared_dishes")
public class PreparedDish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee cooker;

    @OneToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Column(name = "date")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getCooker() {
        return cooker;
    }

    public void setCooker(Employee cooker) {
        this.cooker = cooker;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PreparedDish{" +
                "id=" + id +
                ", cooker=" + cooker +
                ", dish=" + dish +
                ", date=" + date +
                '}';
    }
}
