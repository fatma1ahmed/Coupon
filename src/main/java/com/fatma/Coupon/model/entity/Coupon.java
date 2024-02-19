package com.fatma.Coupon.model.entity;

import com.fatma.Coupon.model.dto.CouponRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    @NotNull(message = "must be enter toDate")
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

    private boolean valid;

    public Coupon(long numberOfUsers, double discount) {
        this.numberOfUsers=numberOfUsers;
        this.discount=discount;
    }

    public static Coupon toEntity(CouponRequest dto){
        return Coupon.builder()
                .id(dto.getId())
                .fromDate(dto.getFromDate())
                .toDate(dto.getToDate())
                .numberOfUsers(dto.getNumberOfUsers())
                .discount(dto.getDiscount())
                .build();
    }

}
