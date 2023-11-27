package com.example.junglebook.repository;


import com.example.junglebook.data.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
Optional<WishList> findById(Long WishList_id);

//팝니다 Entity 판매상품ID 필요 (@Query 필요한지 추후 확인)
List<SellBook> findLikeProducts(@Param("id") Long id);
}
