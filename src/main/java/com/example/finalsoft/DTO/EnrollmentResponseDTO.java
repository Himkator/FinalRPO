package com.example.finalsoft.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnrollmentResponseDTO {
    Long id;
    UserResponseDTO student;
    CourseResponseDTO course;
    String status;
    int progress;
}
