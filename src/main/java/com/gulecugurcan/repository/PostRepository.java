package com.gulecugurcan.repository;

import com.gulecugurcan.entity.Post;
import com.gulecugurcan.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    Optional<Post> getPostByPostIdAndDeleteFlagFalse(Long postId);

    @Getter
    @Setter
    class PostQuerySpecification extends AbstractQuerySpecification<Post> {

        String postTitle;
        String username;

        @Override
        public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            Join<Post, User> joinedRoot = root.join("user");
            if (StringUtils.hasText(this.getPostTitle())) {
                predicates.add(criteriaBuilder.equal(root.get("title"), this.getPostTitle()));
            }
            if (StringUtils.hasText(this.getUsername())) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(joinedRoot.get("username"), this.getUsername())));
            }
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("deleteFlag"), false)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
}
