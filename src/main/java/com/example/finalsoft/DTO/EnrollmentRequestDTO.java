package com.example.finalsoft.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnrollmentRequestDTO {
    Long id;
    Long student;
    Long course;
    String status;
    int progress;
}
