package com.example.concurrency.service;

import com.example.concurrency.entity.Coupon;
import com.example.concurrency.repository.CouponRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmService {

    private CouponRepository couponRepository;

    public void confirm(Long userId){
        long count = couponRepository.count();// 총 쿠폰 발급 개수를 체크합니다.

        if(count > 100){
            return; // 발급된 쿠폰이 100개가 넘으면 발급 방지
        }

        couponRepository.save(new Coupon(userId));
    }
}
