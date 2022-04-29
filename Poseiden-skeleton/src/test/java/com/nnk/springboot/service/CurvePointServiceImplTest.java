package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
public class CurvePointServiceImplTest {

    @InjectMocks
    private CurvePointServiceImpl curvePointService;

    @Mock
    private CurvePointRepository curvePointRepository;

    private List<CurvePoint> curvePointList = new ArrayList<>();
    private CurvePoint curvePoint;

    @BeforeEach
    void setUp() {
        curvePoint = new CurvePoint(1,10, 11.0, 12.0);
        curvePointList.add(curvePoint);
    }

    @Test
    public void findAllTest() {
        when(curvePointRepository.findAll()).thenReturn(curvePointList);
        List<CurvePoint> result = curvePointService.findAll();
        assertThat(result.get(0)).isEqualTo(curvePoint);
    }

    @Test
    public void getCurvePointByIdTest() {
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));
        CurvePoint result = curvePointService.getCurvePointById(curvePoint.getId());
        assertThat(result.getTerm()).isEqualTo(11.0);
    }
    @Test
    public void updateTest() {
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));
        CurvePoint curvePointToUpdate = curvePoint;
        curvePointToUpdate.setTerm(15.0);
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePointToUpdate);

        List<CurvePoint> curvePointList1 = new ArrayList<>();
        curvePointList1.add(curvePointToUpdate);
        when(curvePointRepository.findAll()).thenReturn(curvePointList1);

        List<CurvePoint> curvePointList = curvePointService.update(curvePoint.getId(), curvePointToUpdate);
        assertThat(curvePointList.get(0).getTerm()).isEqualTo(15.0);
    }
    @Test
    public void insertTest() {
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);
        when(curvePointRepository.findAll()).thenReturn(curvePointList);
        List<CurvePoint> result = curvePointService.insert(curvePoint);

        assertThat(result.get(0).getTerm()).isEqualTo(11.0);
    }
    @Test
    public void deleteTest() {
        when(curvePointRepository.findById(anyInt())).thenReturn(Optional.of(curvePoint));
        List<CurvePoint> bidListList1 = new ArrayList<>();
        List<CurvePoint> result = curvePointService.delete(curvePoint.getId());
        assertTrue(result.isEmpty());
        Mockito.verify(curvePointRepository, times(1)).delete(any(CurvePoint.class));

    }
}
