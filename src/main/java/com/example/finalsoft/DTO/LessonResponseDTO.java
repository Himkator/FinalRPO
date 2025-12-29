package com.example.finalsoft.DTO;

import com.example.finalsoft.db.entities.Course;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonResponseDTO {
    Long id;
    String title;
    String content;
    int orderNumber;
    CourseResponseDTO course;
    int durationMinutes;
}
