package com.example.socialmediafeed.domain.post.service;

import com.example.socialmediafeed.domain.hashtag.entity.Hashtag;
import com.example.socialmediafeed.domain.hashtag.repository.HashtagRepository;
import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.entity.TypeStatus;
import com.example.socialmediafeed.domain.post.repository.PostRepository;
import com.example.socialmediafeed.domain.posthashtag.entity.PostHashtag;
import com.example.socialmediafeed.domain.posthashtag.repository.PostHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DummyDataService {

    private final PostRepository postRepository;
    private final PostHashtagRepository postHashtagRepository;
    private final HashtagRepository hashtagRepository;

    public void createDummyPosts() {
        createDummies();
    }

    private void createDummies() {

        Post postFBa = Post.builder()
                .contentId("fb1")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_1")
                .content("good #dev #java")
                .viewCount(2)
                .likeCount(0)
                .shareCount(3)
                .build();
        postFBa.setCreatedAt(LocalDateTime.of(2023, 10, 29, 0, 0, 0));

        Post postFBb = Post.builder()
                .contentId("fb2")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_2")
                .content("좋아 #java #spring")
                .viewCount(12)
                .likeCount(10)
                .shareCount(13)
                .build();
        postFBb.setCreatedAt(LocalDateTime.of(2023, 10, 29, 0, 50, 0));

        Post postFBc = Post.builder()
                .contentId("fb3")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_3")
                .content("쏘쏘 #sns #spring")
                .viewCount(102)
                .likeCount(77)
                .shareCount(32)
                .build();
        postFBc.setCreatedAt(LocalDateTime.of(2023, 10, 29, 0, 55, 0));


        Post postFBd = Post.builder()
                .contentId("fb4")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_4")
                .content("좋아 #sns #hope")
                .viewCount(0)
                .likeCount(0)
                .shareCount(0)
                .build();
        postFBd.setCreatedAt(LocalDateTime.of(2023, 10, 29, 1, 0, 0));


        Post postFBe = Post.builder()
                .contentId("fb2")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_5")
                .content("좋아 #dev #hope")
                .viewCount(827)
                .likeCount(155)
                .shareCount(101)
                .build();
        postFBe.setCreatedAt(LocalDateTime.of(2023, 10, 29, 1, 30, 0));


        Post postTWa = Post.builder()
                .contentId("tw1")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_1")
                .content("better #hope #sns")
                .viewCount(77)
                .likeCount(30)
                .shareCount(55)
                .build();
        postTWa.setCreatedAt(LocalDateTime.of(2023, 10, 29, 2, 30, 0));


        Post postTWb = Post.builder()
                .contentId("tw2")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_2")
                .content("나쁘진 않아 #sns #spring")
                .viewCount(123)
                .likeCount(103)
                .shareCount(131)
                .build();
        postTWb.setCreatedAt(LocalDateTime.of(2023, 10, 30, 2, 30, 0));

        Post postTWc = Post.builder()
                .contentId("tw3")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_3")
                .content("백엔드개발 #java #spring")
                .viewCount(13302)
                .likeCount(7147)
                .shareCount(3242)
                .build();
        postTWc.setCreatedAt(LocalDateTime.of(2023, 10, 31, 2, 30, 0));

        Post postTWd = Post.builder()
                .contentId("tw4")
                .type(TypeStatus.FACEBOOK)
                .title("트윗 피드_4")
                .content("좋아 #java #dev")
                .viewCount(11)
                .likeCount(2)
                .shareCount(4)
                .build();
        postTWd.setCreatedAt(LocalDateTime.of(2023, 10, 31, 3, 30, 0));


        Post postTWe = Post.builder()
                .contentId("tw5")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_4")
                .content("잘좀해봐 #dev #hope")
                .viewCount(427)
                .likeCount(135)
                .shareCount(2)
                .build();
        postTWe.setCreatedAt(LocalDateTime.of(2023, 10, 31, 5, 30, 0));

        Post postIGa = Post.builder()
                .contentId("ig1")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_1")
                .content("괜찮 #dev #spring")
                .viewCount(21)
                .likeCount(3)
                .shareCount(1)
                .build();
        postIGa.setCreatedAt(LocalDateTime.of(2023, 10, 31, 5, 30, 0));


        Post postIGb = Post.builder()
                .contentId("ig2")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_2")
                .content("best #java #sns")
                .viewCount(912)
                .likeCount(190)
                .shareCount(103)
                .build();
        postIGb.setCreatedAt(LocalDateTime.of(2023, 10, 25, 5, 30, 0));


        Post postIGc = Post.builder()
                .contentId("ig3")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_3")
                .content("나쁘진않아 #hope #spring")
                .viewCount(333)
                .likeCount(222)
                .shareCount(111)
                .build();
        postIGc.setCreatedAt(LocalDateTime.of(2023, 10, 26, 5, 30, 0));

        Post postIGd = Post.builder()
                .contentId("ig4")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_4")
                .content("훌륭하닷 #sns #dev")
                .viewCount(200)
                .likeCount(0)
                .shareCount(0)
                .build();
        postIGd.setCreatedAt(LocalDateTime.of(2023, 10, 27, 5, 30, 0));

        Post postIGe = Post.builder()
                .contentId("ig5")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_5")
                .content("와우 #java #hope")
                .viewCount(827)
                .likeCount(155)
                .shareCount(101)
                .build();
        postIGe.setCreatedAt(LocalDateTime.of(2023, 10, 28, 5, 30, 0));

        Post postTHa = Post.builder()
                .contentId("th1")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_1")
                .content("자연스러워 #spring #dev")
                .viewCount(3)
                .likeCount(2)
                .shareCount(1)
                .build();
        postTHa.setCreatedAt(LocalDateTime.of(2023, 10, 29, 5, 30, 0));


        Post postTHb = Post.builder()
                .contentId("th2")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_2")
                .content("best #java #sns")
                .viewCount(12345)
                .likeCount(1234)
                .shareCount(123)
                .build();
        postTHb.setCreatedAt(LocalDateTime.of(2023, 10, 25, 5, 30, 0));

        Post postTHc = Post.builder()
                .contentId("th3")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_3")
                .content("진짜최악 #hope #spring")
                .viewCount(9622)
                .likeCount(2)
                .shareCount(0)
                .build();
        postTHc.setCreatedAt(LocalDateTime.of(2023, 10, 25, 6, 30, 0));

        Post postTHd = Post.builder()
                .contentId("th4")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_4")
                .content("여행가자 #dev #sns")
                .viewCount(62)
                .likeCount(55)
                .shareCount(43)
                .build();
        postTHd.setCreatedAt(LocalDateTime.of(2023, 10, 25, 7, 30, 0));

        Post postTHe = Post.builder()
                .contentId("th5")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_5")
                .content("와우 #java #hope")
                .viewCount(12)
                .likeCount(3)
                .shareCount(2)
                .build();
        postTHe.setCreatedAt(LocalDateTime.of(2023, 10, 25, 8, 30, 0));

        Hashtag hashtag1 = new Hashtag("단아샵");
        Hashtag hashtag2 = new Hashtag("행복해");

        PostHashtag postHashtag1 = PostHashtag.builder().hashtag(hashtag1).post(postFBa).build();
        PostHashtag postHashtag2 = PostHashtag.builder().hashtag(hashtag1).post(postFBb).build();
        PostHashtag postHashtag3 = PostHashtag.builder().hashtag(hashtag1).post(postFBc).build();
        PostHashtag postHashtag4 = PostHashtag.builder().hashtag(hashtag2).post(postFBd).build();
        PostHashtag postHashtag5 = PostHashtag.builder().hashtag(hashtag2).post(postFBe).build();
        PostHashtag postHashtag6 = PostHashtag.builder().hashtag(hashtag2).post(postFBa).build();
        PostHashtag postHashtag7 = PostHashtag.builder().hashtag(hashtag2).post(postTWa).build();
        PostHashtag postHashtag8 = PostHashtag.builder().hashtag(hashtag2).post(postTWb).build();

        hashtagRepository.saveAll(List.of(
                hashtag1, hashtag2
        ));
        postRepository.saveAll(List.of(
                postFBa, postFBb, postFBc, postFBd, postFBe, postTWa, postTWb, postTWc, postTWd, postTWe,
                postIGa, postIGb, postIGc, postIGd, postIGe, postTHa, postTHb, postTHc, postTHd, postTHe
        ));
        postHashtagRepository.saveAll(List.of(
                postHashtag1, postHashtag2, postHashtag3, postHashtag4, postHashtag5, postHashtag6, postHashtag7, postHashtag8
        ));

    }
}
