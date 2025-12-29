package com.example.finalsoft.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponseDTO {
    Long id;
    String title;
    String description;
    BigDecimal price;
    String level;
    F1TeamResponseDTO f1Team;
    String status;
}
