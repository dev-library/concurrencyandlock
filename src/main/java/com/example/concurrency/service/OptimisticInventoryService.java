package com.example.concurrency.service;

import com.example.concurrency.entity.Inventory;
import com.example.concurrency.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OptimisticInventoryService {

    private InventoryRepository inventoryRepository;

    @Transactional
    public void decrease(Long id, Long count){ // 아이템번호와 감소시킬 수량을 적으면
        Inventory inventory = inventoryRepository.findByIdOptimistic(id);// 낙관적 락을 활용한 상태.

        inventory.decrease(count); //감소시킨다음

        inventoryRepository.saveAndFlush(inventory); // 디비에 반영
    }
}
