package com.myproject.POS_System.service.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.POS_System.Repository.ItemRepository;
import com.myproject.POS_System.Repository.StockRepository;
import com.myproject.POS_System.dto.ItemReqDto;
import com.myproject.POS_System.entity.Category;
import com.myproject.POS_System.entity.Item;
import com.myproject.POS_System.entity.Stock;
import com.myproject.POS_System.service.CategoryService;
import com.myproject.POS_System.service.ItemService;
import com.myproject.POS_System.service.StockService;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired 
    private ItemRepository itemRepository;

    @Autowired 
    private CategoryService categoryService;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item createItem(ItemReqDto dto, MultipartFile image) {
        Item item = new Item();
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setDescription(dto.getDescription());
        item.setDiscount(dto.getDiscount());

        // Save the image and get the URL
        String imageUrl = saveImage(image);
        item.setImgUrl(imageUrl);

        if (item.getIsActive() == null) {
            item.setIsActive(true); 
        }

        // Find category by categoryId and assign it to the item
        Category category = categoryService.getCategoryById(dto.getCategoryId());
        item.setCategory(category);
        return itemRepository.save(item);
    }

    private String saveImage(MultipartFile image) {
        
        String imagePath = "D:/cmjd/Cmjd/POS-System/src/main/resources/static/images/";
        
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        
        File destinationFile = new File(imagePath + fileName);

        try {
            
            image.transferTo(destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "images/" + fileName; 
    }


    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item updateItem(Long id, ItemReqDto dto) {

       Item existingItem = itemRepository.findById(id).orElse(null);

       if (existingItem == null) {
        return null; 
    }

       existingItem.setName(dto.getName());
       existingItem.setPrice(dto.getPrice());      
       existingItem.setDiscount(dto.getDiscount());
       existingItem.setDescription(dto.getDescription());
       existingItem.setImgUrl(dto.getImgUrl());
        if (existingItem.getIsActive() == null) {
            existingItem.setIsActive(true); 
        }
        
        Category category = categoryService.getCategoryById(dto.getCategoryId());
        existingItem.setCategory(category);
        
        return itemRepository.save(existingItem);
    }

    @Override
    public void deleteItem(Long itemId) {           
        itemRepository.deleteById(itemId);
        Item item = itemRepository.findById(itemId).orElse(null);
        Stock stock = stockRepository.findById(item.getStock().getId()).orElse(null);
        if(stock.getAvailable_stock() < 1){
            item.setIsActive(false);
        }     
        itemRepository.save(item);
    }
    
}



