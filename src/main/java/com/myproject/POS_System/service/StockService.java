package com.myproject.POS_System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproject.POS_System.dto.StockReqDto;
import com.myproject.POS_System.entity.Stock;

@Service
public interface StockService {

     List <Stock> getAllStocks();

     Stock createStock(StockReqDto stockDto);
     Stock updateStock(Long stockId,StockReqDto dto);

     Stock getStockById (Long stockId);

     void deleteStock(Long stockId);
        
}