package com.myproject.POS_System.service;
import com.myproject.POS_System.dto.ItemReqDto;
import com.myproject.POS_System.entity.Item;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public  interface  ItemService {
        List<Item> getAllItems();
        Item createItem(ItemReqDto dto ,MultipartFile image);  // Using ItemReqDto directly here
        Item getItemById(Long id);
        Item updateItem( Long id, ItemReqDto dto);
        void deleteItem(Long itemId);

    }
    

