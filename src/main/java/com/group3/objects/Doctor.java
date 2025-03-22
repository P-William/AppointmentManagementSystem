package com.group3.objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Doctor {
    @Setter(AccessLevel.NONE)
    private Long doctorId;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private List<String> specialties;
}
