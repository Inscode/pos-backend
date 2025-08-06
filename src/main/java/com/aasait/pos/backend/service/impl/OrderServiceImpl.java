package com.aasait.pos.backend.service.impl;

import com.aasait.pos.backend.dto.OrderDTO;
import com.aasait.pos.backend.entity.Order;
import com.aasait.pos.backend.repository.OrderRepo;
import com.aasait.pos.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public List<OrderDTO> updateOrders(List<OrderDTO> orderDTOs) {
        List<OrderDTO> updatedList = new ArrayList<>();

        for (OrderDTO dto : orderDTOs) {
            Order order = orderRepo.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Order not found: ID " + dto.getId()));

//            order.setBatchId(dto.getBatchId());
//            order.setStatus(dto.getStatus());
//            order.setUnitPrice(dto.getUnitPrice());
//            order.setSellPrice(dto.getSellPrice());
            order.setTotal(dto.getTotal());

            Order saved = orderRepo.save(order);

            OrderDTO updatedDTO = new OrderDTO();
            updatedDTO.setId(saved.getId());
//            updatedDTO.setBatchId(saved.getBatchId());
//            updatedDTO.setStatus(saved.getStatus());
//            updatedDTO.setUnitPrice(saved.getUnitPrice());
//            updatedDTO.setSellPrice(saved.getSellPrice());
            updatedDTO.setTotal(saved.getTotal());

            updatedList.add(updatedDTO);
        }

        return updatedList;
    }
}
