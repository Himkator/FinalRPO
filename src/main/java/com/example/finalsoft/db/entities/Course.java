package com.example.finalsoft.db.entities;

import com.example.finalsoft.db.enums.CourseStatusEnum;
import com.example.finalsoft.db.enums.LevelEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity(name = "courses")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    BigDecimal price;
    @Enumerated(EnumType.STRING)
    LevelEnum level;

    @ManyToOne
    @JoinColumn(name = "team_id")
    F1Team f1Team;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    List<Enrollment> enrollments;

    @Enumerated(EnumType.STRING)
    CourseStatusEnum status;

    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;

    @PrePersist
    void onCreate(){
        createdAt = Instant.now();
        updatedAt = Instant.now();
        status = CourseStatusEnum.ACTIVE;
    }

    @PreUpdate
    void onUpdate(){
        updatedAt = Instant.now();
    }
}
