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
@Document(collection = "categories")
public class Category {

    @Id
    private String categoryId;  // MongoDB dùng String

    private String categoryName;

    @DBRef
    private List<Orchid> orchids;  // Tham chiếu sang Orchid, lưu danh sách ID

}
