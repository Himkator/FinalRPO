package com.example.finalsoft.db.entities;

import com.example.finalsoft.db.enums.TeamStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity(name = "teams")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class F1Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String name;
    String country;
    String description;
    @Enumerated(EnumType.STRING)
    TeamStatusEnum status;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    User manager;

    @OneToMany(mappedBy = "f1Team", cascade = CascadeType.ALL)
    List<Course> courseList;

    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;

    @PrePersist
    void onCreate(){
        createdAt = Instant.now();
        updatedAt = Instant.now();
        status = TeamStatusEnum.ACTIVE;
    }

    @PreUpdate
    void onUpdate(){
        updatedAt = Instant.now();
    }
}
