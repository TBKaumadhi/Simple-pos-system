package com.myproject.POS_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.myproject.POS_System.dto.ItemReqDto;
import com.myproject.POS_System.entity.Item;
import com.myproject.POS_System.service.ItemService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")                                                                                                     
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.status(200).body(items);
    }

    @PostMapping(consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
   
    public ResponseEntity<Item> createItem(
        @RequestParam("name") String name,
        @RequestParam("description") String description,
        @RequestParam("price") Double price,
        @RequestParam("discount") double discount,
        @RequestParam("categoryId") Long categoryId,
        @RequestParam("image") MultipartFile image) {
        
        // Create ItemReqDto and set values
        ItemReqDto dto = new ItemReqDto();
        dto.setName(name);
        dto.setDescription(description);
        dto.setPrice(price);
        dto.setDiscount(discount);
        dto.setCategoryId(categoryId);

        
            Item createdItem = itemService.createItem(dto, image);
            
                return ResponseEntity.status(201).body(createdItem);
           
        }
    @PutMapping("/{id}")   
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody ItemReqDto dto) {
        
            Item updatedItem = itemService.updateItem(id, dto);
            if (updatedItem == null) {
                return ResponseEntity.status(404).body(null); 
            }
            return ResponseEntity.status(200).body(updatedItem);      

        }

    @DeleteMapping("/{id}")
    public void deleteItem (@PathVariable Long itemId){
        itemService.deleteItem(itemId);
    }
}

