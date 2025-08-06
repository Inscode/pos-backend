package com.aasait.pos.backend.service.IMPL;

import com.aasait.pos.backend.dto.request.AddItemDTO;
import com.aasait.pos.backend.dto.request.AddOrderDTO;
import com.aasait.pos.backend.entity.Item;
import com.aasait.pos.backend.entity.Order;
import com.aasait.pos.backend.repository.ItemRepo;
import com.aasait.pos.backend.repository.OrderRepo;
import com.aasait.pos.backend.service.StockkeepeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StockkeepeServiceIMPL implements StockkeepeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public void saveItem(AddItemDTO addItemDTO) {

        String itemName = addItemDTO.getItemName().trim().toLowerCase();

        if (itemName.isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }

        if (itemRepo.existsByItemNameIgnoreCase(itemName)) {
            throw new RuntimeException("Item " + addItemDTO.getItemName() + " is already registered");

        }

        Item item = modelMapper.map(addItemDTO, Item.class);
        item.setItemName(itemName);

        itemRepo.save(item);

    }

    @Override
    public List<String> getItemDetails() {

        List<Item> itemsList = itemRepo.findAll();

        if (itemsList.isEmpty()) {
            throw new NoSuchElementException("No items found in the system");
        }

        return itemsList.stream()
                .map(item -> item.getId() + " - " + item.getItemName())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveMultipleOrders(List<AddOrderDTO> orderDTOList) {
        String batchId = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<Order> orders = new ArrayList<>();

        for (AddOrderDTO dto : orderDTOList) {
            Item item = itemRepo.findById(Long.valueOf(dto.getItemId()))
                    .orElseThrow(() -> new NoSuchElementException("Item not found with ID: " + dto.getItemId()));

            Order order = Order.builder()
                    .item(item)
                    .quantity(dto.getQuantity())
                    .unitPrice(dto.getUnitPrice())
                    .sellPrice(dto.getSellPrice())
                    .batchId(batchId)
                    .build();

            orders.add(order);
        }

        orderRepo.saveAll(orders);
    }


}