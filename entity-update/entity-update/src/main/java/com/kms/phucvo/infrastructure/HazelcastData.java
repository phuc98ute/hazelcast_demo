package com.kms.phucvo.infrastructure;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class HazelcastData implements Serializable {

    @Serial
    private static final long serialVersionUID = -4223298844561715168L;
    String name;
    String age;
    String address;
    String additionalData;
}
