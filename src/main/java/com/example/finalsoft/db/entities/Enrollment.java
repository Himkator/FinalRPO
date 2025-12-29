package com.example.finalsoft.db.entities;

import com.example.finalsoft.db.enums.EnrollmentStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity(name = "enrollments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    User student;
    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;
    @Enumerated(EnumType.STRING)
    EnrollmentStatusEnum status;
    int progress;

    Instant createdAt;
    Instant updatedAt;
    Instant completedAt;
    Instant cancelledAt;

    @PrePersist
    void onCreate(){
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    void onUpdate(){
        updatedAt = Instant.now();
    }
}
