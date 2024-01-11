package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellerSumDTO {

    private String sellerName;

    private Double total;

    public SellerSumDTO (Seller entity, Double total) {
        sellerName = entity.getName();
        this.total = total;
    }

}
