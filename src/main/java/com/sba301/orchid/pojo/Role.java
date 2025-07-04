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
@Document(collection = "roles")
public class Role {

    @Id
    private String roleId;  // MongoDB _id dạng String

    private String roleName;

    @DBRef
    private List<Account> accounts;  // Tham chiếu danh sách Account nếu cần

}
