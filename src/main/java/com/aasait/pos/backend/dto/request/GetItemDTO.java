package com.aasait.pos.backend.dto.request;

import lombok.Data;

@Data
public class GetItemDTO {
    private Long id;
    private String itemName;
}
