package com.fatma.Coupon.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponResponse {
    @NotNull(message = "must be enter numberOfUsers")
    @Min(value = 1,message = "The minimum number of users should be 1")
    @Max(value = 100,message = "numberOfUsers must not exceed 100 users")
    private long numberOfUsers;
    @NotNull
    @Min(value = 1,message = "The minimum discount value must be 1")
    @Max(value = 100,message = "The discount percentage must not exceed 100")
    private double discount;
}
