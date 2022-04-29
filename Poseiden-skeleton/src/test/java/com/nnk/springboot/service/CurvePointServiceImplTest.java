package com.nnk.springboot.service;

import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class CurvePointServiceImplTest {
@InjectMocks
private CurvePointServiceImpl curvePointService;

@Mock
private CurvePointRepository curvePointRepository;

    @Test
    public void findAllTest() {

    }

    @Test
    public void getCurvePointByIdTest() {

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
