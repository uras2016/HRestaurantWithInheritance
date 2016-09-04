package ua.goit.java.hibernate.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "employee")      // обьясняем на какую таблицу мапить
public class Employee {
    @Id
    @Column(name = "id")      // на какую колонку
    @GeneratedValue(generator = "increment")    // автоинкремент
    @GenericGenerator(name = "increment", strategy = "increment")  // автоинкремент
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;
    @Column(name = "telephone")
    private int telephone;
    @Enumerated(EnumType.STRING)     // ORDINAL - по порядковому номеру
    @Column(name = "position")
    private Position position;
    @Column(name = "salary")
    private Float salary;
    @Column(name = "birthday")
    private String birthday;


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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (telephone != employee.telephone) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;
        if (position != employee.position) return false;
        if (salary != null ? !salary.equals(employee.salary) : employee.salary != null) return false;
        return birthday != null ? birthday.equals(employee.birthday) : employee.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + telephone;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", telephone=" + telephone +
                ", position=" + position +
                ", salary=" + salary +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
