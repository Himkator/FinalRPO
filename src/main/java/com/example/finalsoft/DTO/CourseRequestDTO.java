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
public class CourseRequestDTO {
    String title;
    String description;
    BigDecimal price;
    String level;
    Long f1Team;
}
