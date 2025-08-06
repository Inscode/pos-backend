package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.request.AddItemDTO;
import com.aasait.pos.backend.dto.request.AddOrderDTO;

import java.util.List;

public interface StockkeepeService {
    void saveItem(AddItemDTO addItemDTO);

    List<String> getItemDetails();

    void saveMultipleOrders(List<AddOrderDTO> orders);
}
