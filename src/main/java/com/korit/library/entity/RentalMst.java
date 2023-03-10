package com.korit.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentalMst {
    private int rentalId;
    private int userId;
    private LocalDate rentalDate;
    private LocalDate fixedDate;
}
