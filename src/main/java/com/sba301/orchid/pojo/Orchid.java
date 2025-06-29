package com.sba301.orchid.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orchids")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orchid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orchid_id")
    private Integer orchidId;

    @Column(name = "is_natural")
    private Boolean isNatural = true;

    @Column(name = "orchid_description", columnDefinition = "TEXT")
    private String orchidDescription;

    @Column(name = "orchid_name", nullable = false)
    private String orchidName;

    @Column(name = "orchid_url", length = 500)
    private String orchidUrl;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "orchid", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OrderDetail> orderDetails;
}
