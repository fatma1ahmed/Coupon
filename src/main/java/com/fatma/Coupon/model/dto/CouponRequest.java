package com.fatma.Coupon.model.dto;

import com.fatma.Coupon.model.entity.Coupon;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponRequest {
    private long id;
    private LocalDate toDate;
    @NotNull(message = "must be enter fromDate")
    private LocalDate fromDate;
    @NotNull(message = "must be enter numberOfUsers")
    @Min(value = 1,message = "The minimum number of users should be 1")
    @Max(value = 100,message = "numberOfUsers must not exceed 100 users")
    private long numberOfUsers;
    @NotNull
    @Min(value = 1,message = "The minimum discount value must be 1")
    @Max(value = 100,message = "The discount percentage must not exceed 100")
    private double discount;

    @AssertTrue(message = " fromDate must be before toDate")
    public boolean isFromDateBeforeToDate(){
        return fromDate.isBefore(toDate);

    }

    public static CouponRequest fromEntityToDto(Coupon entity){
        return CouponRequest.builder()
                .id(entity.getId())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .numberOfUsers(entity.getNumberOfUsers())
                .discount(entity.getDiscount())
                .build();
    }
}
