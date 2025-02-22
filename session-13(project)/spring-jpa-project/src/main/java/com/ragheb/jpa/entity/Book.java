package com.ragheb.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.EntityGraph;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
//@NamedEntityGraph(name = "book-entity-graph", attributeNodes = {@NamedAttributeNode("author")})
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Name is required")
    private String name;

    @Min(value = 100)
    @Max(value = 9000)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @NotNull
    private Author author;

    //@Formula("(select count(*) from books)")
    //private int booksCount;

    //@Transient
    //private double discount;

    @CreatedBy
    private LocalDateTime createdBy;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedBy
    private LocalDateTime lastModifiedAt;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    //@PostLoad
    //public void CalcDiscount() {
        //this.setDiscount(price * 0.25);
    //}
}
