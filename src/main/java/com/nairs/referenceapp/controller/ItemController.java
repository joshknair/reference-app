package com.nairs.referenceapp.controller;

import com.nairs.referenceapp.domain.Item;
import com.nairs.referenceapp.service.ItemService;
import io.micrometer.core.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/items/v1", produces = "application/json;charset=UTF-8")
public class ItemController {
    ItemService itemService;

    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    @Timed(value = "api.items.v1", percentiles = {.95, .99})
    public ResponseEntity<Item> getItemDetailsWithPrice(@PathVariable String id) {
        Item item = itemService.getItemDetails(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
