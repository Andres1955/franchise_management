package com.franchise.management.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("branches")
public class Branch {
    @Id
    private Long id;
    private String name;
    private Long franchiseId;

    @Builder.Default
    private Set<Product> products = new HashSet<>();
}
