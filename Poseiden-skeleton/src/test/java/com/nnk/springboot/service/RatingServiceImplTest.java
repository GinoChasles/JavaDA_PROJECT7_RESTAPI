package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTest {

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Mock
    private RatingRepository ratingRepository;

    private List<Rating> ratingList = new ArrayList<>();
    private Rating rating;

    @BeforeEach
    void setUp() {
        rating = new Rating(1,"Test","test1","test2",10);
        ratingList.add(rating);
    }

    @Test
    public void findAllTest() {
        when(ratingRepository.findAll()).thenReturn(ratingList);
        List<Rating> result = ratingService.findAll();
        assertThat(result.get(0)).isEqualTo(rating);
    }

    @Test
    public void getCurvePointByIdTest() {
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));
        Rating result = ratingService.getRatingById(rating.getId());
        assertThat(result.getMoodysRating()).isEqualTo("Test");
    }
    @Test
    public void updateTest() {
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));
        Rating rating1 = rating;
        rating1.setMoodysRating("TestMoody");
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating1);

        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating1);
        when(ratingRepository.findAll()).thenReturn(ratings);

        List<Rating> ratingList = ratingService.update(rating.getId(), rating1);
        assertThat(ratingList.get(0).getMoodysRating()).isEqualTo("TestMoody");
    }
    @Test
    public void insertTest() {
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
        when(ratingRepository.findAll()).thenReturn(ratingList);
        List<Rating> result = ratingService.insert(rating);

        assertThat(result.get(0).getMoodysRating()).isEqualTo("Test");
    }
    @Test
    public void deleteTest() {
        when(ratingRepository.findById(anyInt())).thenReturn(Optional.of(rating));
        List<Rating> bidListList1 = new ArrayList<>();
        List<Rating> result = ratingService.delete(rating.getId());
        assertTrue(result.isEmpty());
        Mockito.verify(ratingRepository, times(1)).delete(any(Rating.class));

    }
}
