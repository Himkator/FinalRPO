package com.example.finalsoft.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class F1TeamResponseDTO {
    Long id;
    String name;
    String country;
    String description;
    String status;
    UserResponseDTO manager;
}
