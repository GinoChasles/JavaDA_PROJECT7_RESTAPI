package com.nnk.springboot.service;

import com.nnk.springboot.repositories.RatingRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTest {

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @Test
    public void findAllTest() {

    }

    @Test
    public void getRatingByIdTest() {

    }
    @Test
    public void updateTest() {

    }
    @Test
    public void insertTest() {

    }
    @Test
    public void deleteTest() {

    }
}
