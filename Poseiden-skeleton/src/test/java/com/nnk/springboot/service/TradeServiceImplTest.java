package com.nnk.springboot.service;

import com.nnk.springboot.repositories.TradeRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TradeServiceImplTest {
    @InjectMocks
    private TradeServiceImplTest tradeService;

    @Mock
    private TradeRepository tradeRepository;

    @Test
    public void findAllTest() {

    }

    @Test
    public void getTradeByIdTest() {

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
