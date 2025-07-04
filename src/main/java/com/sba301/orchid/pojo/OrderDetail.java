package com.sba301.orchid.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {

    @DBRef
    private Orchid orchid;  // Tham chiếu Orchid

    private Double price;

    private Integer quantity = 1;
}
