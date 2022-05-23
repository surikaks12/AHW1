package com.company;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department_employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department_Employee {

    public Department_Employee(Department dep, Employee emp) {
        this.dep = dep;
        this.emp = emp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "d_id")
    private Department dep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e_id")
    private Employee emp;

    @Override
    public String toString() {
        return "Department_Employee{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department_Employee that = (Department_Employee) o;
        return Objects.equals(id, that.id) && Objects.equals(dep, that.dep) && Objects.equals(emp, that.emp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dep, emp);
    }

}
