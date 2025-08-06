package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    List<OrderDTO> updateOrders(List<OrderDTO> orderDTOs);
}
