package com.emrekentli.adoptme.domain.platform.post.impl;

import com.emrekentli.adoptme.domain.platform.post.api.Gender;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {
    @Query("SELECT p FROM Post p " +
            "WHERE (:id IS NULL OR p.ownerId = :id) " +
            "AND (:gender IS NULL OR p.gender = :gender) " +
            "AND (:title IS NULL OR LOWER(p.title) LIKE CONCAT('%', LOWER(:title), '%')) " +
            "AND (:description IS NULL OR p.description LIKE CONCAT('%', :description, '%')) " +
            "AND (:animalTypeId IS NULL OR p.animalTypeId = :animalTypeId) " +
            "AND (:breedId IS NULL OR p.animalBreedId = :breedId) " +
            "AND (:cityId IS NULL OR p.cityId = :cityId) " +
            "AND (:districtId IS NULL OR p.districtId = :districtId) " +
            "AND (:verified IS NULL OR p.verified = :verified) " +
            "AND (:status IS NULL OR p.status = :status)")
    List<Post> filter(@Param("id") String id,
                      @Param("gender") Gender gender,
                      @Param("title") String title,
                      @Param("description") String description,
                      @Param("animalTypeId") String animalTypeId,
                      @Param("breedId") String breedId,
                      @Param("cityId") String cityId,
                      @Param("districtId") String districtId,
                      @Param("verified") Boolean verified,
                      @Param("status") Boolean status);
    List<Post> findAllByAnimalTypeId(String animalTypeId);

    List<Post> findAllByOwnerId(String ownerId);

    @Query("SELECT p FROM Post p WHERE p.animalTypeId NOT IN (:animalTypeIds)")
    List<Post> findAllByAnimalTypeIdsNotIn(@Param("animalTypeIds") List<String> animalTypeIds);
}
