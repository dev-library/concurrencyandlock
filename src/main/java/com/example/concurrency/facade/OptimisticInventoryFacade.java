package com.example.concurrency.facade;

import com.example.concurrency.service.OptimisticInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OptimisticInventoryFacade {

    // 파사드 클래스의 역할은 낙관적 락 서비스가 기각되었을때 호출이 반영될때까지 지속적으로 재시도하도록 만드는것
    private OptimisticInventoryService optimisticInventoryService;

    public void decrease(Long id, Long count) throws InterruptedException {

        // 성공할떄까지 반복적으로 호출시도를 해야함.
        while(true) {
            try {
                optimisticInventoryService.decrease(id, count);// 서비스의 decrease 호출
                break; // 위 구문에 의한 호출이 버전 정합성이 맞아서 받아들여졌다면 반복 호출 해제
            } catch (Exception e) {
                // 낙관적 락에 의해서 버저닝 정합성이 맞지 않아 예외가 발생했다면
                Thread.sleep(10);// 0.1초뒤에 다시 시도
            }
        }

    }


}
