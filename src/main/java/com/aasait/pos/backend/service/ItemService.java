package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.ItemDTO;
import com.aasait.pos.backend.dto.OrderDTO;
import com.aasait.pos.backend.entity.Item;
import com.aasait.pos.backend.entity.Order;
import com.aasait.pos.backend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();

        return items.stream().map(item -> {
            ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
            List<OrderDTO> orderDTOs = item.getOrders().stream()
                    .map(order -> modelMapper.map(order, OrderDTO.class))
                    .collect(Collectors.toList());
            itemDTO.setOrders(orderDTOs);
            return itemDTO;
        }).collect(Collectors.toList());
    }
}
