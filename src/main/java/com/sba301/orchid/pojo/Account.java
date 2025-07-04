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
@Document(collection = "accounts")
public class Account {

    @Id
    private String accountId;  // MongoDB dùng String, _id dạng ObjectId

    private String accountName;

    private String email;

    private String password;

    @DBRef
    private Role role;  // Tham chiếu sang document Role

    @DBRef
    private List<Order> orders;  // Tham chiếu danh sách Order

}
