package de.aittr.g_31_2_shop.repositories.jpa;

import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional //  позволяет Spring автоматически управлять транзакциями репозитория
public interface JpaProductRepository extends JpaRepository<JpaProduct, Integer> {

    @Modifying// аннотация показывает, что запрос изменяет данные(используется метод execute() )
    @Query(value = "UPDATE `31_2_shop`.`product` SET `is_active` = '0' WHERE (`id` = :id);", nativeQuery = true)
    void deleteById(@Param("id") int id);

    @Modifying
    @Query(value = "UPDATE `31_2_shop`.`product` SET `is_active` = '0' WHERE (`name` =:name);", nativeQuery = true)
    void deleteByName(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE `31_2_shop`.`product` SET `is_active` = '1' WHERE (`id` = :id);", nativeQuery = true)
    void restoreById(@Param("id") int id);


}
