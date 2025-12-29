package com.example.finalsoft.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class F1TeamRequestDTO {
    String name;
    String country;
    String description;
    Long manager;
}
