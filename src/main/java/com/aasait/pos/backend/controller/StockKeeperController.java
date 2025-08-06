
package com.aasait.pos.backend.controller;

import com.aasait.pos.backend.dto.ApiResponse;
import com.aasait.pos.backend.dto.request.AddItemDTO;
import com.aasait.pos.backend.dto.request.AddOrderDTO;
import com.aasait.pos.backend.dto.request.BulkOrderDTO;
import com.aasait.pos.backend.service.StockkeepeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/stokkeeper")
@CrossOrigin
public class StockKeeperController {

    @Autowired
    private StockkeepeService stockkeepeService;

    @PostMapping(path = "/add-item")
    public ResponseEntity<ApiResponse<List<String>>> addItem(@RequestBody AddItemDTO addItemDTO){
        try {
            stockkeepeService.saveItem(addItemDTO);

            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    "Item saved Successfully",
                    List.of("success"),
                    HttpStatus.CREATED.value()
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            ApiResponse<List<String>> response = new ApiResponse<>(

                    LocalDateTime.now(),
                    "Failed to save item" + e.getMessage(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "item/name-id")
    public ResponseEntity<ApiResponse<List<String>>> getItemNameId() {
        try{
            List<String> itemDetails = stockkeepeService.getItemDetails();

            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    "Items details fetched successfully",
                    itemDetails,
                    HttpStatus.OK.value()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND.value()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    "Internal server error",
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping(path = "/add-order")
    public ResponseEntity<ApiResponse<List<String>>> addOrder(@RequestBody BulkOrderDTO bulkOrderDTO){
        try {
            stockkeepeService.saveMultipleOrders(bulkOrderDTO.getOrders());

            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    "Orders saved Successfully",
                    List.of("success"),
                    HttpStatus.CREATED.value()
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            ApiResponse<List<String>> response = new ApiResponse<>(
                    LocalDateTime.now(),
                    "Failed to save orders: " + e.getMessage(),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
