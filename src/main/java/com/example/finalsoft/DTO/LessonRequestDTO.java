package com.example.finalsoft.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonRequestDTO {
    String title;
    String content;
    int orderNumber;
    Long course;
    int durationMinutes;
}
