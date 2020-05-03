package com.nairs.referenceapp.service;

import com.nairs.referenceapp.domain.Item;
import com.nairs.referenceapp.domain.Price;
import com.nairs.referenceapp.exception.ApplicationException;
import com.nairs.referenceapp.restclients.ItemRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * ItemService - returns item details including price for an item
 */
@Service
public class ItemService {
    Logger logger = LoggerFactory.getLogger(ItemService.class);

    ItemRestClient itemRestClient;
    PriceService priceService;

    public ItemService(ItemRestClient itemRestClient, PriceService priceService) {
        this.itemRestClient = itemRestClient;
        this.priceService = priceService;
    }

    /**
     * Returns item details with price
     * @param id itemId
     * @return Item
     */
    @Cacheable(value = "items")
    public Item getItemDetails(String id) {
        logger.info("Getting item details and price for item:: {}", id);

        //Call item and price services parallelly
        CompletableFuture<Item> itemFuture = CompletableFuture.supplyAsync(() ->  itemRestClient.getItemDetails(id));

        CompletableFuture<Optional<Price>> priceFuture = CompletableFuture.supplyAsync(() -> priceService.getCurrentPrice(id));

        /*
        CompletableFuture<Item> itemWithPriceFuture = itemFuture.thenCombine(priceFuture, (item, priceOptional) -> {
            if (priceOptional.isPresent()) {
                item.setPrice(priceOptional.get());
            }else{
                logger.info("No price found for item:: {}", id);
                item.setPrice(null);
            }
            return item;
        }).exceptionally(ex -> {
            logger.error("Unable to get item details for item:: {} Error::{}", id, ex.toString());
            throw new ApplicationException("Failed to get item and price details", ex);
        });
        */

        //Merge the result from the above two futures
        try {
            Item item = itemFuture.get();
            Optional<Price> priceOptional = priceFuture.get();
            if (priceOptional.isPresent()) {
                item.setPrice(priceOptional.get());
            }else{
                logger.info("No price found for item:: {}", id);
                item.setPrice(null);
            }
            return item;
        }catch (Exception ex) {
            logger.error("Unable to get item details for item:: {} Error::{}", id, ex.toString());
            throw new ApplicationException("Failed to get item and price details", ex);
        }
    }
}
