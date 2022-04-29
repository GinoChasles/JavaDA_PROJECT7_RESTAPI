package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BidListServiceImplTest {

    @InjectMocks
    private BidListServiceImpl bidListService;

    @Mock
    private BidListRepository bidListRepository;

    private BidList bidList;
    private List<BidList> bidListList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        bidList = new BidList(1, "Test", "Test", 10);
        bidListList.add(bidList);
    }

    @Test
    public void findAllTest() {
        when(bidListRepository.findAll()).thenReturn(bidListList);

        List<BidList> expected = bidListService.findAll();
        assertThat(expected.size()).isEqualTo(1);
        assertThat(expected.get(0).getBidListId()).isEqualTo(1);
    }

    @Test
    public void getBidListByIdTest() {
        when(bidListRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bidList));
        BidList result = bidListService.getBidListById(1);

        assertThat(result.getAccount()).isEqualTo(bidList.getAccount());
    }

    @Test
    public void updateTest() {
        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidList));
        BidList bidListToUpdate = bidList;
        bidListToUpdate.setAccount("TestUpdate");
        when(bidListRepository.save(any(BidList.class))).thenReturn(bidListToUpdate);

        List<BidList> bidListList1 = new ArrayList<>();
        bidListList1.add(bidListToUpdate);
        when(bidListRepository.findAll()).thenReturn(bidListList1);

        List<BidList> bidListList = bidListService.update(bidList.getBidListId(), bidListToUpdate);
        assertThat(bidListList.get(0).getAccount()).isEqualTo("TestUpdate");
    }

    @Test
    public void insertTest() {
        when(bidListRepository.save(any(BidList.class))).thenReturn(bidList);
        when(bidListRepository.findAll()).thenReturn(bidListList);
        List<BidList> result = bidListService.insert(bidList);

        assertThat(result.get(0).getAccount()).isEqualTo("Test");
    }

    @Test
    public void deleteTest() {
        when(bidListRepository.findById(anyInt())).thenReturn(Optional.of(bidList));
        List<BidList> bidListList1 = new ArrayList<>();
        List<BidList> result = bidListService.delete(bidList.getBidListId());
        assertTrue(result.isEmpty());
        Mockito.verify(bidListRepository, times(1)).delete(any(BidList.class));
    }
}
