package com.fatma.Coupon.service;

import com.fatma.Coupon.model.dto.CouponRequest;
import com.fatma.Coupon.model.dto.CouponResponse;
import com.fatma.Coupon.model.entity.Coupon;
import com.fatma.Coupon.repositry.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static com.fatma.Coupon.model.entity.Coupon.toEntity;

@Service
public class CouponService {
    @Autowired
    private CouponRepo couponRepo;


    public ResponseEntity<?> createCoupon(CouponRequest couponRequest) {
        String code=generateCode();
        Coupon coupon=toEntity(couponRequest);
        coupon.setCode(code);
        coupon.setValid(true);
        return new ResponseEntity<>(CouponRequest.fromEntityToDto(couponRepo.save(coupon)),HttpStatus.CREATED);

    }
    public String generateCode(){

        UUID uuid=UUID.randomUUID();
        String code=uuid.toString();
        return code;
    }
    public Coupon getCouponById(long id){
      Optional<Coupon> coupon=couponRepo.findById(id);
      if(coupon.isPresent())
          return coupon.get();

      else
           throw new  RuntimeException("this record with id: " + id + " Not found");
    }
    public Coupon checkCouponExistOrNot(long id){
      return   getCouponById(id);
    }

public ResponseEntity<?> makeCouponNotValidById(long id){
        Coupon coupon=checkCouponExistOrNot(id);
        coupon.setValid(false);
        return new ResponseEntity<>(couponRepo.save(coupon),HttpStatus.ACCEPTED);
}
public ResponseEntity<?> useCoupon(long id){
        Coupon coupon=checkCouponExistOrNot(id);
       if(coupon.isValid()){
           CouponResponse couponResponse=new CouponResponse();
           couponResponse.setDiscount(coupon.getDiscount());
           couponResponse.setNumberOfUsers(coupon.getNumberOfUsers());
           return new ResponseEntity<>(couponResponse,HttpStatus.ACCEPTED);

       }
       else
           return new ResponseEntity<>( "coupon is not valid",HttpStatus.CONFLICT);

}
public ResponseEntity<?> checkDateCoupon(long id){
        Coupon coupon=checkCouponExistOrNot(id);
        LocalDate date1=LocalDate.now();
        if (date1.isAfter(coupon.getToDate())|| date1.isBefore(coupon.getFromDate())){
            coupon.setValid(false);
            couponRepo.save(coupon);
            return new ResponseEntity<>( "coupon is not valid",HttpStatus.CONFLICT);
        }
        else
            return new ResponseEntity<>( "coupon is valid",HttpStatus.FOUND);
}


}
