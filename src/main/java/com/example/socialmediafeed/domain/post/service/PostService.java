package com.example.socialmediafeed.domain.post.service;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.entity.TypeStatus;
import com.example.socialmediafeed.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public void createDummyPosts() {
        createDummies();
    }

    private List<Post> createDummies() {
        List<Post> dummyPosts = new ArrayList<>();

        Post postFBa = Post.builder()
                .contentId("fb1")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_1")
                .content("good #dev #java")
                .viewCount(2)
                .likeCount(0)
                .shareCount(3)
                .createdAt(LocalDateTime.parse("2023-10-01 14:14:14"))
                .updatedAt(LocalDateTime.parse("2023-10-01 14:15:15"))
                .build();

        Post postFBb = Post.builder()
                .contentId("fb2")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_2")
                .content("좋아 #java #spring")
                .viewCount(12)
                .likeCount(10)
                .shareCount(13)
                .createdAt(LocalDateTime.parse("2023-10-04 17:17:17"))
                .updatedAt(LocalDateTime.parse("2023-10-04 17:18:18"))
                .build();

        Post postFBc = Post.builder()
                .contentId("fb3")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_3")
                .content("쏘쏘 #sns #spring")
                .viewCount(102)
                .likeCount(77)
                .shareCount(32)
                .createdAt(LocalDateTime.parse("2023-10-11 11:11:11"))
                .updatedAt(LocalDateTime.parse("2023-10-11 11:12:12"))
                .build();

        Post postFBd = Post.builder()
                .contentId("fb4")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_4")
                .content("좋아 #sns #hope")
                .viewCount(0)
                .likeCount(0)
                .shareCount(0)
                .createdAt(LocalDateTime.parse("2023-10-18 01:14:14"))
                .updatedAt(LocalDateTime.parse("2023-10-18 01:18:18"))
                .build();

        Post postFBe = Post.builder()
                .contentId("fb2")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_5")
                .content("좋아 #dev #hope")
                .viewCount(827)
                .likeCount(155)
                .shareCount(101)
                .createdAt(LocalDateTime.parse("2023-10-25 09:23:24"))
                .updatedAt(LocalDateTime.parse("2023-10-25 09:33:35"))
                .build();

        Post postTWa = Post.builder()
                .contentId("tw1")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_1")
                .content("better #hope #sns")
                .viewCount(77)
                .likeCount(30)
                .shareCount(55)
                .createdAt(LocalDateTime.parse("2023-10-01 21:14:14"))
                .updatedAt(LocalDateTime.parse("2023-10-01 21:40:15"))
                .build();

        Post postTWb = Post.builder()
                .contentId("tw2")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_2")
                .content("나쁘진 않아 #sns #spring")
                .viewCount(123)
                .likeCount(103)
                .shareCount(131)
                .createdAt(LocalDateTime.parse("2023-10-06 17:00:17"))
                .updatedAt(LocalDateTime.parse("2023-10-06 17:44:18"))
                .build();

        Post postTWc = Post.builder()
                .contentId("tw3")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_3")
                .content("백엔드개발 #java #spring")
                .viewCount(13302)
                .likeCount(7147)
                .shareCount(3242)
                .createdAt(LocalDateTime.parse("2023-10-13 23:11:11"))
                .updatedAt(LocalDateTime.parse("2023-10-13 23:22:22"))
                .build();

        Post postTWd = Post.builder()
                .contentId("tw4")
                .type(TypeStatus.FACEBOOK)
                .title("트윗 피드_4")
                .content("좋아 #java #dev")
                .viewCount(11)
                .likeCount(2)
                .shareCount(4)
                .createdAt(LocalDateTime.parse("2023-10-20 23:54:14"))
                .updatedAt(LocalDateTime.parse("2023-10-21 00:12:12"))
                .build();

        Post postTWe = Post.builder()
                .contentId("tw5")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_4")
                .content("잘좀해봐 #dev #hope")
                .viewCount(427)
                .likeCount(135)
                .shareCount(2)
                .createdAt(LocalDateTime.parse("2023-10-25 19:43:24"))
                .updatedAt(LocalDateTime.parse("2023-10-25 19:53:35"))
                .build();

        Post postIGa = Post.builder()
                .contentId("ig1")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_1")
                .content("괜찮 #dev #spring")
                .viewCount(21)
                .likeCount(3)
                .shareCount(1)
                .createdAt(LocalDateTime.parse("2023-10-02 04:24:14"))
                .updatedAt(LocalDateTime.parse("2023-10-02 04:25:15"))
                .build();

        Post postIGb = Post.builder()
                .contentId("ig2")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_2")
                .content("best #java #sns")
                .viewCount(912)
                .likeCount(190)
                .shareCount(103)
                .createdAt(LocalDateTime.parse("2023-10-07 07:17:12"))
                .updatedAt(LocalDateTime.parse("2023-10-07 07:28:12"))
                .build();

        Post postIGc = Post.builder()
                .contentId("ig3")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_3")
                .content("나쁘진않아 #hope #spring")
                .viewCount(333)
                .likeCount(222)
                .shareCount(111)
                .createdAt(LocalDateTime.parse("2023-10-13 23:59:11"))
                .updatedAt(LocalDateTime.parse("2023-10-14 00:11:12"))
                .build();

        Post postIGd = Post.builder()
                .contentId("ig4")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_4")
                .content("훌륭하닷 #sns #dev")
                .viewCount(200)
                .likeCount(0)
                .shareCount(0)
                .createdAt(LocalDateTime.parse("2023-10-17 21:44:14"))
                .updatedAt(LocalDateTime.parse("2023-10-17 21:48:18"))
                .build();

        Post postIGe = Post.builder()
                .contentId("ig5")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_5")
                .content("와우 #java #hope")
                .viewCount(827)
                .likeCount(155)
                .shareCount(101)
                .createdAt(LocalDateTime.parse("2023-10-25 09:23:24"))
                .updatedAt(LocalDateTime.parse("2023-10-25 09:33:35"))
                .build();

        Post postTHa = Post.builder()
                .contentId("th1")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_1")
                .content("자연스러워 #spring #dev")
                .viewCount(3)
                .likeCount(2)
                .shareCount(1)
                .createdAt(LocalDateTime.parse("2023-10-03 09:24:14"))
                .updatedAt(LocalDateTime.parse("2023-10-03 09:25:15"))
                .build();

        Post postTHb = Post.builder()
                .contentId("th2")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_2")
                .content("best #java #sns")
                .viewCount(12345)
                .likeCount(1234)
                .shareCount(123)
                .createdAt(LocalDateTime.parse("2023-10-09 17:17:12"))
                .updatedAt(LocalDateTime.parse("2023-10-09 17:28:12"))
                .build();

        Post postTHc = Post.builder()
                .contentId("th3")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_3")
                .content("진짜최악 #hope #spring")
                .viewCount(9622)
                .likeCount(2)
                .shareCount(0)
                .createdAt(LocalDateTime.parse("2023-10-15 20:19:11"))
                .updatedAt(LocalDateTime.parse("2023-10-15 20:21:12"))
                .build();

        Post postTHd = Post.builder()
                .contentId("th4")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_4")
                .content("여행가자 #dev #sns")
                .viewCount(62)
                .likeCount(55)
                .shareCount(43)
                .createdAt(LocalDateTime.parse("2023-10-20 01:44:14"))
                .updatedAt(LocalDateTime.parse("2023-10-20 01:48:18"))
                .build();

        Post postTHe = Post.builder()
                .contentId("th5")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_5")
                .content("와우 #java #hope")
                .viewCount(12)
                .likeCount(3)
                .shareCount(2)
                .createdAt(LocalDateTime.parse("2023-10-24 19:23:24"))
                .updatedAt(LocalDateTime.parse("2023-10-24 19:33:35"))
                .build();

        dummyPosts.addAll(postRepository.saveAll(List.of(
                postFBa, postFBb, postFBc, postFBd, postFBe, postTWa, postTWb, postTWc, postTWd, postTWe,
                postIGa, postIGb, postIGc, postIGd, postIGe, postTHa, postTHb, postTHc, postTHd, postTHe
        )));

        return dummyPosts;
    }
}
