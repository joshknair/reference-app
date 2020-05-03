package com.nairs.referenceapp.service;

import com.nairs.referenceapp.domain.Item;
import com.nairs.referenceapp.domain.Price;
import com.nairs.referenceapp.dto.ItemPrice;
import com.nairs.referenceapp.repository.ItemPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    ItemPriceRepository itemPriceRepository;

    PriceService(ItemPriceRepository itemPriceRepository) {
        this.itemPriceRepository = itemPriceRepository;
    }

    public Optional<Price> getCurrentPrice(String id) {
        List<ItemPrice> prices = itemPriceRepository.findByItemId(id);
        if (prices.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toPrice(prices.get(0)));
    }

    private Price toPrice(ItemPrice itemPrice) {
        Item item = new Item(itemPrice.getItemId());
        Price price = new Price();
        price.setAmount(itemPrice.getPriceAmount());
        price.setEffectiveDateTime(itemPrice.getEffectiveDateTime());
        price.setItem(item);
        return price;
    }
}
