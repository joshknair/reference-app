package com.nairs.referenceapp.dto;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(value = "item_price")
@Data
public class ItemPrice {

    @PrimaryKey
    private ItemPriceKey key;

    @Column("item_id")
    private String itemId;

    @Column("effective_datetime")
    private LocalDateTime effectiveDateTime;

    @Column("price_amount")
    private BigDecimal priceAmount;

    @Data
    public class ItemPriceKey {
        @Column("item_id")
        private String itemId;

        @Column("effective_datetime")
        private LocalDateTime effectiveDatetime;
    }
}

