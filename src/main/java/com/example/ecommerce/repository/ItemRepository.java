package com.example.ecommerce.repository;

import com.example.ecommerce.model.Item;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.JpaRepository;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
