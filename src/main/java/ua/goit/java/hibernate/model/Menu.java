package ua.goit.java.hibernate.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "id")      // на какую колонку
    @GeneratedValue(generator = "increment")    // автоинкремент
    @GenericGenerator(name = "increment", strategy = "increment")  // автоинкремент
    private Long id;


    @Column(name = "name")
    private String name;

    /*1. fetch-“join” = Disable the lazy loading, always load all the collections and entities.
      2. fetch-“select” (default) = Lazy load all the collections and entities.
      3. batch-size=”N” = Fetching up to ‘N’ collections or entities, *Not record*.
      4. fetch-“subselect” = Group its collection into a sub select statement.*/
    @OneToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "menu_list",
            joinColumns = @JoinColumn(name = "menu"),
            inverseJoinColumns = @JoinColumn (name = "dish_id")
    )
    private List<Dish> dishes;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        return name != null ? name.equals(menu.name) : menu.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
