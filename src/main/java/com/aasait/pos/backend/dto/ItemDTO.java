package com.aasait.pos.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemDTO {
    private Long id;
    private String itemName;
    private List<OrderDTO> orders;
}
