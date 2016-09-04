package ua.goit.java.hibernate.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dish {
    @Id
    @Column(name = "id")      // на какую колонку
    @GeneratedValue(generator = "increment")    // автоинкремент
    @GenericGenerator(name = "increment", strategy = "increment")  // автоинкремент
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private DishCategory category;
    @Column(name = "price")
    private Float price;
    @Column(name = "weight")
    private Float weight;
    @Column(name = "measure")
    @Enumerated(EnumType.STRING)
    private Measures measure;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "dish_ingredients",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )

    private List<Ingredient> ingredients;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Measures getMeasure() {
        return measure;
    }

    public void setMeasure(Measures measure) {
        this.measure = measure;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;

        Dish dish = (Dish) o;

        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        if (category != dish.category) return false;
        if (price != null ? !price.equals(dish.price) : dish.price != null) return false;
        if (weight != null ? !weight.equals(dish.weight) : dish.weight != null) return false;
        return measure == dish.measure;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", weight=" + weight +
                ", measure=" + measure +
                ", ingredients=" + ingredients +
                '}';
    }
}
