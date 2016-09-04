package ua.goit.java.hibernate.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;


    @OneToOne
    @JoinColumn(name = "ingredient")
    private Ingredient ingredient;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "measure")
    @Enumerated(EnumType.STRING)
    private Measures measure;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Measures getMeasure() {
        return measure;
    }

    public void setMeasure(Measures measure) {
        this.measure = measure;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warehouse)) return false;

        Warehouse warehouse = (Warehouse) o;

        if (ingredient != null ? !ingredient.equals(warehouse.ingredient) : warehouse.ingredient != null) return false;
        if (quantity != null ? !quantity.equals(warehouse.quantity) : warehouse.quantity != null) return false;
        return measure == warehouse.measure;

    }

    @Override
    public int hashCode() {
        int result = ingredient != null ? ingredient.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", quantity=" + quantity +
                ", measure=" + measure +
                '}';
    }
}
