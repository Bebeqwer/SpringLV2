package com.sparta.springlv2.posting.repository;

import com.sparta.springlv2.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting,Long> {

    List<Posting> findAllByOrderByCreatedAtDesc();

}