package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 전략
    Long id;

    @Column
    String name;

    @Column
    String price;

    public void patch(Coffee coffee) {
        if (coffee.name != null)
            this.name = coffee.name;
        if (coffee.price != null)
            this.price = coffee.price;
    }
}
