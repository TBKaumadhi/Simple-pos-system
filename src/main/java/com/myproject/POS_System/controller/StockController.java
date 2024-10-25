package com.myproject.POS_System.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.myproject.POS_System.dto.StockReqDto;
import com.myproject.POS_System.entity.Stock;
import com.myproject.POS_System.service.StockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stock")
public class StockController {

    @Autowired private StockService stockService;

    @GetMapping
    public ResponseEntity <List <Stock> >  getAllStocks() {
        List <Stock> stocks = stockService.getAllStocks(); 
        return ResponseEntity.status(200).body(stocks);
    }

    @PostMapping
    public ResponseEntity <Stock> createStock (@RequestBody StockReqDto stockDto) {        
      
        Stock createdStock = stockService.createStock(stockDto);
        return ResponseEntity.status(201).body(createdStock);    
  } 
  @PutMapping("/stockId")
    public ResponseEntity <Stock> updateStock (@PathVariable Long stockId, @RequestBody StockReqDto stockDto) {        
      
        Stock updatedStock = stockService.updateStock(stockId, stockDto);
        return ResponseEntity.status(201).body(updatedStock);    
  } 
  @DeleteMapping("/{stockId}")
    public void deleteStock (@PathVariable Long stockId){
        stockService.deleteStock(stockId);
    }
}
    

