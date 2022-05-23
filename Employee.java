package com.company;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "employee")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "emp", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Department_Employee> Department_Employees= new ArrayList<Department_Employee>();

    public List<Department_Employee> getDepartment_Employee() {
        return Department_Employees;
    }

    public void setDepartment_Employee(List<Department_Employee> de) {
        this.Department_Employee = de;
    }

    public void addDepartment_Employee(Department_Employee de) {
        this.Department_Employee.add(de);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee color = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
