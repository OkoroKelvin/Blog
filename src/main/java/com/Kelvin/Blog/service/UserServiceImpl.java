package com.Kelvin.Blog.service;

import com.Kelvin.Blog.data.dto.PostDto;
import com.Kelvin.Blog.data.model.Post;
import com.Kelvin.Blog.data.model.User;
import com.Kelvin.Blog.data.repository.UserRepository;
import com.Kelvin.Blog.service.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    PostService postServiceImpl;
    PostMapper postMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PostService postServiceImpl,PostMapper postMapper) {
        this.userRepository = userRepository;
        this.postServiceImpl = postServiceImpl;
        this.postMapper= postMapper;
    }

    @Override
    public User createAndSaveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Post createPost(Long userId, PostDto postDto) {
        Optional<User> foundUserRepo = userRepository.findById(userId);
        if(foundUserRepo.isPresent()){
            Post post = new Post(postDto.getPostTitle(),
                    postDto.getPostBody(),postDto.getImageUrls(),
                    foundUserRepo.get().getFullName());
            foundUserRepo.get().setPosts(List.of(post));
            return postServiceImpl.save(post);
        }
        throw new IllegalArgumentException("This user with Id " +userId+" is not registered");
    }
}
