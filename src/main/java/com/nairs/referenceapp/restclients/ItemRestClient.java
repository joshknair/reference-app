package com.nairs.referenceapp.restclients;

import com.nairs.referenceapp.domain.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${clients.items.baseUrl}", name="itemRestClient")
public interface ItemRestClient {
    @RequestMapping(method = RequestMethod.GET, value = "/items/v1/{id}")
    Item getItemDetails(@PathVariable("id") String id);
}
