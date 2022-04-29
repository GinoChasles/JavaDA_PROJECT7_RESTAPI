package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeServiceImplTest {
    @InjectMocks
    private TradeServiceImpl tradeService;

    @Mock
    private TradeRepository tradeRepository;

    private List<Trade> tradeList = new ArrayList<>();
    private Trade trade;

    @BeforeEach
    void setUp() {
        trade = new Trade(1,"10", "11.0", 11.0);
        tradeList.add(trade);
    }

    @Test
    public void findAllTest() {
        when(tradeRepository.findAll()).thenReturn(tradeList);
        List<Trade> result = tradeService.findAll();
        assertThat(result.get(0)).isEqualTo(trade);
    }

    @Test
    public void getCurvePointByIdTest() {
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));
        Trade result = tradeService.getTradeById(trade.getTradeId());
        assertThat(result.getBuyQuantity()).isEqualTo(11.0);
    }
    @Test
    public void updateTest() {
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));
        Trade curvePointToUpdate = trade;
        curvePointToUpdate.setBuyQuantity(15.0);
        when(tradeRepository.save(any(Trade.class))).thenReturn(curvePointToUpdate);

        List<Trade> curvePointList1 = new ArrayList<>();
        curvePointList1.add(curvePointToUpdate);
        when(tradeRepository.findAll()).thenReturn(curvePointList1);

        List<Trade> curvePointList = tradeService.update(trade.getTradeId(), curvePointToUpdate);
        assertThat(curvePointList.get(0).getBuyQuantity()).isEqualTo(15.0);
    }
    @Test
    public void insertTest() {
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);
        when(tradeRepository.findAll()).thenReturn(tradeList);
        List<Trade> result = tradeService.insert(trade);

        assertThat(result.get(0).getBuyQuantity()).isEqualTo(11.0);
    }
    @Test
    public void deleteTest() {
        when(tradeRepository.findById(anyInt())).thenReturn(Optional.of(trade));
        List<Trade> trades = new ArrayList<>();
        List<Trade> result = tradeService.delete(trade.getTradeId());
        assertTrue(result.isEmpty());
        Mockito.verify(tradeRepository, times(1)).delete(any(Trade.class));

    }
}
