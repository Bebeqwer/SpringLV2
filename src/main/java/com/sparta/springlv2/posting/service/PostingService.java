package com.sparta.springlv2.posting.service;

import com.sparta.springlv2.posting.dto.PostingRequestDto;
import com.sparta.springlv2.posting.dto.PostingResponseDto;
import com.sparta.springlv2.posting.entity.Posting;
import com.sparta.springlv2.posting.repository.PostingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostingService {

    private final PostingRepository postingRepository;


    public PostingService(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }


    public PostingResponseDto createPosting(PostingRequestDto requestDto){
        Posting posting = new Posting(requestDto);

        Posting savePosting = postingRepository.save(posting);

        PostingResponseDto postingResponseDto = new PostingResponseDto(savePosting);

        return postingResponseDto;





    }

    public List<PostingResponseDto> getPostings(){

        return postingRepository.findAllByOrderByCreatedAtDesc().stream().map(PostingResponseDto::new).toList();
    }

    public PostingResponseDto getPosting(Long id){
        Posting posting = findPosting(id);
        PostingResponseDto postingResponseDto = new PostingResponseDto(posting);
        return postingResponseDto;
    }

    @Transactional
    public PostingResponseDto updatePosting(Long id , PostingRequestDto requestDto){

        Posting posting = findPosting(id);
        if(posting.getPassword().equals(requestDto.getPassword())){
            posting.update(requestDto);

            return new PostingResponseDto(posting);
        }else{
            throw new IllegalArgumentException("비밀번호가 다릅니다");
        }



    }



    public boolean deletePosting(Long id,PostingRequestDto requestDto){
        Posting posting = findPosting(id);
        if(posting.getPassword().equals(requestDto.getPassword())){
            postingRepository.delete(posting);

        }else{
            throw new IllegalArgumentException("비밀번호가 다릅니다");
        }
        return true;
    }

    private Posting findPosting(Long id){
        return postingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 포스팅은 존재하지 않습니다."));
    }
}