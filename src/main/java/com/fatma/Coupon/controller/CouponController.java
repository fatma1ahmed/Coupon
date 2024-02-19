package com.fatma.Coupon.controller;

import com.fatma.Coupon.model.dto.CouponRequest;
import com.fatma.Coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping("/createCoupon")
    public ResponseEntity<?> createCoupon(@RequestBody CouponRequest couponRequest) {
        return couponService.createCoupon(couponRequest);
    }
    @GetMapping("/NotValidCoupon/{id}")
    public ResponseEntity<?> makeCouponValidById(@PathVariable long id) throws Exception {
        return couponService.makeCouponNotValidById(id);
    }
    @GetMapping("/useCoupon/{id}")
    public ResponseEntity<?> useCoupon(@PathVariable long id){
        return couponService.useCoupon(id);
    }
    @GetMapping("/checkDateCoupon/{id}")
    public ResponseEntity<?> checkDateCoupon(@PathVariable long id){
        return couponService.checkDateCoupon(id);
    }
    }
