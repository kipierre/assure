package org.cephas.acdia.service;

import org.cephas.acdia.exception.ResourceNotFoundException;
import org.cephas.acdia.model.FilePost;
import org.cephas.acdia.model.Post;
import org.cephas.acdia.repository.FilePostRepository;
import org.cephas.acdia.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by admin on 23-05-19.
 */

@Service
@Transactional
public class FilePostService {
    @Autowired
    private FilePostRepository filePostRepository;
    @Autowired
    private PostRepository postRepository;

    public Optional<FilePost> findById(Long Id) {
        return filePostRepository.findById(Id);
    }

    public List<FilePost> findAllByPostId(long postId) {
        return filePostRepository.findAllByPostId (postId);
    }

    public List<FilePost> getAllFilePosts() {
        return filePostRepository.findAll();
    }

    public Optional<FilePost> getFilePosById(Long filePost) {
        if (!filePostRepository.existsById(filePost)) {
            throw new ResourceNotFoundException("filePost with id " + filePost + " not found");
        }
        return filePostRepository.findById(filePost);
    }

    public FilePost savefile(Long postId, FilePost filePost) {
        Set<FilePost> filePosts = new LinkedHashSet<>();
        return postRepository.findById(postId).map(post -> {
            filePost.setPost(post);
            FilePost filePost1 = filePostRepository.save(filePost);
            filePosts.add(filePost1);
            post.setFilePosts(filePosts);
            return filePost1;
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }


    public void saveFilePost(FilePost filePost) {
        filePostRepository.save(filePost);
    }

    public void deleteById(long id) {
        filePostRepository.deleteById(id );
    }

}
