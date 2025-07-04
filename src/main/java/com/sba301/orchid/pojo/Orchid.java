package com.sba301.orchid.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "orchids")
public class Orchid {

    @Id
    private String orchidId;  // MongoDB dùng String _id

    private Boolean isNatural = true;

    private String orchidDescription;

    private String orchidName;

    private String orchidUrl;

    private Double price;

    @DBRef
    private Category category;  // Tham chiếu sang Category, lưu ObjectId

    @DBRef
    private List<OrderDetail> orderDetails;  // Tham chiếu danh sách OrderDetail

}
