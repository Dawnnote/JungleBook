package com.example.junglebook.repository;


import com.example.junglebook.data.entity.SellBookPost;
import com.example.junglebook.data.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {
    Optional<WishList> findById(int wishListId);
    @Query("SELECT p FROM WishList l JOIN l.SellBookPost p WHERE l.user.id=:id ORDER BY p.uploadDate DESC")
    List<SellBookPost> findWishListSellBook(@Param("id") int id);

//Optional<WishList> findById(Long WishList_id);
//
////팝니다 Entity 판매상품ID 필요 (@Query 필요한지 추후 확인)
//List<SellBook> findLikeProducts(@Param("id") Long id);
}
