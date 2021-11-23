package com.Kelvin.Blog.service;

import com.Kelvin.Blog.data.dto.PostDto;
import com.Kelvin.Blog.data.model.Post;
import com.Kelvin.Blog.data.model.User;
import com.Kelvin.Blog.data.repository.PostRepository;
import com.Kelvin.Blog.data.repository.UserRepository;
import com.Kelvin.Blog.service.mapper.PostMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


class UserServiceImplMockTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    PostMapper postMapper;

    @InjectMocks
    UserServiceImpl userServiceImpl;
    @InjectMocks
    PostServiceImpl postServiceImpl;

    @BeforeEach
    void setup(){
        postServiceImpl = new PostServiceImpl();
        userServiceImpl = new UserServiceImpl(userRepository, postServiceImpl, postMapper);
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void canCreateAndSaveUser(){
        User user1 = User.builder()
                .firstName("Steve")
                .lastName("Jobs")
                .email("Stevejobs@yahoo.com")
                .password("123$%^")
                .phoneNumber("08163091749").build();

        when(userRepository.save(user1)).thenReturn(user1);
        userServiceImpl.createAndSaveUser(user1);
        verify(userRepository, times(1)).save(user1);
    }
    @Test
    void userCanCreatePost(){
        User user1 = User.builder()
                .userId(1L)
                .firstName("Steve")
                .lastName("Jobs")
                .email("Stevejobs@yahoo.com")
                .password("123$%^")
                .phoneNumber("08163091749").build();

        PostDto postDto = new PostDto();
        postDto.setPostTitle("Chemistry Sociology");
        postDto.setPostBody("The sociology of science");
        postDto.setPostAuthorName(user1.getFullName());
        postDto.setImageUrls(List.of("www.java.com","www.divine.com"));


        Post post1 = new Post(postDto.getPostTitle(),postDto.getPostBody(),
                postDto.getImageUrls(),postDto.getPostAuthorName());


        when(userRepository.save(user1)).thenReturn(user1);
        userServiceImpl.createAndSaveUser(user1);

        verify(userRepository, times(1)).save(user1);
        when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user1));
        when(postRepository.save(post1)).thenReturn(post1);
        userServiceImpl.createPost(user1.getUserId(),postDto);
        verify(userRepository,times(1)).findById(user1.getUserId());
        verify(postRepository,times(1)).save(post1);
    }

    @Test
    void userCanUpdatePostViaPostId(){


        User user1 = User.builder()
                .firstName("Kelvin")
                .lastName("Okoro")
                .email("kelvin@yahoo.com")
                .password("1234")
                .phoneNumber("08163091749").build();

        User user2 = User.builder()
                .firstName("John")
                .lastName("Bush")
                .email("okoro@yahoo.com")
                .password("1234")
                .phoneNumber("08175878390").build();

        User user3 = User.builder()
                .firstName("Tolani")
                .lastName("Eze")
                .email("nike@yahoo.com")
                .password("1234")
                .phoneNumber("08163565267").build();


        Post post1 = new Post("Chemistry","The study of chemistry of Life",
                List.of("www.image.com","www.css.com"),user1.getFullName());

        Post post2 = new Post("Biology Science","Bio comes from the world Life," +
                "Logos comes from the word study. Therefore Biology is the Study of Life",
                List.of("www.college.com","www.Buss.com"),user2.getFullName());

        Post post3 = new Post("Chemistry technology","Bio comes from the world Life,Logos comes from the" +
                "word study. Therefore Biology is the Study of Life",null,user3.getFullName());




        PostDto postDto = new PostDto();
        postDto.setPostTitle("Chemistry Sociology");
        postDto.setPostBody("The sociology of science");
        postDto.setPostAuthorName(user1.getFullName());
        postDto.setImageUrls(List.of("www.java.com","www.divine.com"));



        when(userRepository.save(user1)).thenReturn(user1);
        userServiceImpl.createAndSaveUser(user1);

        verify(userRepository, times(1)).save(user1);
        when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user1));
        when(postRepository.save(post1)).thenReturn(post1);
        userServiceImpl.createPost(user1.getUserId(),postDto);
        verify(userRepository,times(1)).findById(user1.getUserId());
        verify(postRepository,times(1)).save(post1);



    }


}