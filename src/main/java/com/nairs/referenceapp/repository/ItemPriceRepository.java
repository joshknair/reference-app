package com.nairs.referenceapp.repository;

import com.nairs.referenceapp.dto.ItemPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPriceRepository extends CrudRepository<ItemPrice, ItemPrice.ItemPriceKey> {
    List<ItemPrice> findByItemId(String itemId);
}
