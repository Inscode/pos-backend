package com.aasait.pos.backend.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class BulkOrderDTO {

    private List<AddOrderDTO> orders;

}
