package com.myproject.POS_System.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.POS_System.Repository.StockRepository;
import com.myproject.POS_System.dto.StockReqDto;
import com.myproject.POS_System.entity.Stock;
import com.myproject.POS_System.entity.Item;
import com.myproject.POS_System.service.ItemService;
import com.myproject.POS_System.service.StockService;

@Service
public class StockServiceImpl implements StockService {

    @Autowired StockRepository stockRepository;
    @Autowired private ItemService itemService;

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock createStock(StockReqDto dto) {
        Stock stock = new Stock();
        Item item = itemService.getItemById(dto.getItemId());
        stock.setItem(item);
        
        if (item == null) {
            throw new RuntimeException("Item with ID " + dto.getItemId() + " not found");
        }

        stock.setAvailable_stock(dto.getAvailableStock());
        
        item.setStock(stock);
        
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Long stockId, StockReqDto dto) {
        Stock existingStock = stockRepository.findById(stockId).orElse(null);
        Item item = itemService.getItemById(dto.getItemId());
        existingStock.setItem(item);
        
        if (item == null) {
            throw new RuntimeException("Item with ID " + dto.getItemId() + " not found");
        }

        existingStock.setAvailable_stock(dto.getAvailableStock());
        item.setStock(existingStock);
        
        return stockRepository.save(existingStock);
    }

    @Override
    public Stock getStockById(Long stockId) {
        return stockRepository.findById(stockId).orElse(null);
    
    }

    @Override
    public void deleteStock(Long stockId) {
        stockRepository.deleteById(stockId);
    }

}
