package com.sparta.springlv2.posting.service;

import com.sparta.springlv2.auth.jwt.JwtUtil;
import com.sparta.springlv2.posting.dto.Message;
import com.sparta.springlv2.posting.dto.PostingRequestDto;
import com.sparta.springlv2.posting.dto.PostingResponseDto;
import com.sparta.springlv2.posting.dto.StatusEnum;
import com.sparta.springlv2.posting.entity.Posting;
import com.sparta.springlv2.posting.repository.PostingRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpHeaders;


import java.net.http.HttpRequest;
import java.util.List;

@Service
public class PostingService {

    private HttpServletRequest req;
    private final PostingRepository postingRepository;
    private final JwtUtil jwtUtil;




    public PostingService(PostingRepository postingRepository,JwtUtil jwtUtil,HttpServletRequest req) {
        this.jwtUtil = jwtUtil;
        this.postingRepository = postingRepository;
        this.req = req;


    }


    public PostingResponseDto createPosting(PostingRequestDto requestDto) {
        Posting posting = new Posting(requestDto);

        Posting savePosting = postingRepository.save(posting);

        PostingResponseDto postingResponseDto = new PostingResponseDto(savePosting);

        return postingResponseDto;


    }

    public List<PostingResponseDto> getPostings() {

        return postingRepository.findAllByOrderByCreatedAtDesc().stream().map(PostingResponseDto::new).toList();
    }

    public PostingResponseDto getPosting(Long id) {
        Posting posting = findPosting(id);
        PostingResponseDto postingResponseDto = new PostingResponseDto(posting);
        return postingResponseDto;
    }

    @Transactional
    public PostingResponseDto updatePosting(Long id, PostingRequestDto requestDto) {
        Claims info = getClaim(req);


        Posting posting = findPosting(id);
        if (info.getSubject().equals(requestDto.getUsername())) {
            posting.update(requestDto);

            return new PostingResponseDto(posting);
        } else {
            throw new IllegalArgumentException("비밀번호가 다릅니다");
        }


    }


    public Long deletePosting(Long id, PostingRequestDto requestDto) {
        Claims info = getClaim(req);
        Posting posting = findPosting(id);


        if (info.getSubject().equals(requestDto.getUsername())) {
            postingRepository.delete(posting);



        } else {
            throw new IllegalArgumentException("비밀번호가 다릅니다");
        }
        return id;
    }

    private Posting findPosting(Long id) {
        return postingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 포스팅은 존재하지 않습니다."));
    }

    private Claims getClaim(HttpServletRequest req) {
        String tokenFromRequest = jwtUtil.getTokenFromRequest(req);
        tokenFromRequest = jwtUtil.substringToken(tokenFromRequest);

        if (!jwtUtil.validateToken(tokenFromRequest)){
            throw new IllegalArgumentException("토큰이 잘못되었습니다");
        }

        Claims info = jwtUtil.getUserInfoFromToken(tokenFromRequest);
        return info;
    }
}