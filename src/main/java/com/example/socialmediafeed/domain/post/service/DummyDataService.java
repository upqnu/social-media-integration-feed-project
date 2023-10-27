package com.example.socialmediafeed.domain.post.service;

import com.example.socialmediafeed.domain.post.entity.Post;
import com.example.socialmediafeed.domain.post.entity.TypeStatus;
import com.example.socialmediafeed.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DummyDataService {

    private final PostRepository postRepository;

    public void createDummyPosts() {
        createDummies();
    }

    private List<Post> createDummies() {

        Post postFBa = Post.builder()
                .contentId("fb1")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_1")
                .content("good #dev #java")
                .viewCount(2)
                .likeCount(0)
                .shareCount(3)
                .build();

        Post postFBb = Post.builder()
                .contentId("fb2")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_2")
                .content("좋아 #java #spring")
                .viewCount(12)
                .likeCount(10)
                .shareCount(13)
                .build();

        Post postFBc = Post.builder()
                .contentId("fb3")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_3")
                .content("쏘쏘 #sns #spring")
                .viewCount(102)
                .likeCount(77)
                .shareCount(32)
                .build();

        Post postFBd = Post.builder()
                .contentId("fb4")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_4")
                .content("좋아 #sns #hope")
                .viewCount(0)
                .likeCount(0)
                .shareCount(0)
                .build();

        Post postFBe = Post.builder()
                .contentId("fb2")
                .type(TypeStatus.FACEBOOK)
                .title("페북 피드_5")
                .content("좋아 #dev #hope")
                .viewCount(827)
                .likeCount(155)
                .shareCount(101)
                .build();

        Post postTWa = Post.builder()
                .contentId("tw1")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_1")
                .content("better #hope #sns")
                .viewCount(77)
                .likeCount(30)
                .shareCount(55)
                .build();

        Post postTWb = Post.builder()
                .contentId("tw2")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_2")
                .content("나쁘진 않아 #sns #spring")
                .viewCount(123)
                .likeCount(103)
                .shareCount(131)
                .build();

        Post postTWc = Post.builder()
                .contentId("tw3")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_3")
                .content("백엔드개발 #java #spring")
                .viewCount(13302)
                .likeCount(7147)
                .shareCount(3242)
                .build();

        Post postTWd = Post.builder()
                .contentId("tw4")
                .type(TypeStatus.FACEBOOK)
                .title("트윗 피드_4")
                .content("좋아 #java #dev")
                .viewCount(11)
                .likeCount(2)
                .shareCount(4)
                .build();

        Post postTWe = Post.builder()
                .contentId("tw5")
                .type(TypeStatus.TWITTER)
                .title("트윗 피드_4")
                .content("잘좀해봐 #dev #hope")
                .viewCount(427)
                .likeCount(135)
                .shareCount(2)
                .build();

        Post postIGa = Post.builder()
                .contentId("ig1")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_1")
                .content("괜찮 #dev #spring")
                .viewCount(21)
                .likeCount(3)
                .shareCount(1)
                .build();

        Post postIGb = Post.builder()
                .contentId("ig2")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_2")
                .content("best #java #sns")
                .viewCount(912)
                .likeCount(190)
                .shareCount(103)
                .build();

        Post postIGc = Post.builder()
                .contentId("ig3")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_3")
                .content("나쁘진않아 #hope #spring")
                .viewCount(333)
                .likeCount(222)
                .shareCount(111)
                .build();

        Post postIGd = Post.builder()
                .contentId("ig4")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_4")
                .content("훌륭하닷 #sns #dev")
                .viewCount(200)
                .likeCount(0)
                .shareCount(0)
                .build();

        Post postIGe = Post.builder()
                .contentId("ig5")
                .type(TypeStatus.INSTAGRAM)
                .title("인스타 피드_5")
                .content("와우 #java #hope")
                .viewCount(827)
                .likeCount(155)
                .shareCount(101)
                .build();

        Post postTHa = Post.builder()
                .contentId("th1")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_1")
                .content("자연스러워 #spring #dev")
                .viewCount(3)
                .likeCount(2)
                .shareCount(1)
                .build();

        Post postTHb = Post.builder()
                .contentId("th2")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_2")
                .content("best #java #sns")
                .viewCount(12345)
                .likeCount(1234)
                .shareCount(123)
                .build();

        Post postTHc = Post.builder()
                .contentId("th3")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_3")
                .content("진짜최악 #hope #spring")
                .viewCount(9622)
                .likeCount(2)
                .shareCount(0)
                .build();

        Post postTHd = Post.builder()
                .contentId("th4")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_4")
                .content("여행가자 #dev #sns")
                .viewCount(62)
                .likeCount(55)
                .shareCount(43)
                .build();

        Post postTHe = Post.builder()
                .contentId("th5")
                .type(TypeStatus.THREADS)
                .title("쓰레 피드_5")
                .content("와우 #java #hope")
                .viewCount(12)
                .likeCount(3)
                .shareCount(2)
                .build();

        return postRepository.saveAll(List.of(
                postFBa, postFBb, postFBc, postFBd, postFBe, postTWa, postTWb, postTWc, postTWd, postTWe,
                postIGa, postIGb, postIGc, postIGd, postIGe, postTHa, postTHb, postTHc, postTHd, postTHe
        ));
    }
}
