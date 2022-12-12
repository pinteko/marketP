package com.korsuk.cloud.service.cart.entities;

//import com.korsuk.my_market.entities.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "novels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Novel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;

//    @ManyToMany
//    @JoinTable(name = "novel_readers",
//    joinColumns = @JoinColumn(name = "novel_id"),
//    inverseJoinColumns = @JoinColumn(name = "student_id"))
//    private List<StudentEntity> studentEntities;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "price")
    private Double price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Novel(Long id, String title, Author author, Double rating, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
        this.price = price;
    }
}
