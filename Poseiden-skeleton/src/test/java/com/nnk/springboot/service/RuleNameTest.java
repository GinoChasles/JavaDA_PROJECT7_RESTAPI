package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
public class RuleNameTest {

    @InjectMocks
    private RuleNameServiceImpl ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepository;

    private List<RuleName> ruleNames = new ArrayList<>();
    private RuleName ruleName;

    @BeforeEach
    void setUp() {
        ruleName = new RuleName(1,"test", "test1", "test2", "test3", "test4","test5");
        ruleNames.add(ruleName);
    }

    @Test
    public void findAllTest() {
        when(ruleNameRepository.findAll()).thenReturn(ruleNames);
        List<RuleName> result = ruleNameService.findAll();
        assertThat(result.get(0)).isEqualTo(ruleName);
    }

    @Test
    public void getCurvePointByIdTest() {
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));
        RuleName result = ruleNameService.getRuleNamById(ruleName.getId());
        assertThat(result.getName()).isEqualTo("test");
    }
    @Test
    public void updateTest() {
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));
        RuleName curvePointToUpdate = ruleName;
        curvePointToUpdate.setName("testName");
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(curvePointToUpdate);

        List<RuleName> curvePointList1 = new ArrayList<>();
        curvePointList1.add(curvePointToUpdate);
        when(ruleNameRepository.findAll()).thenReturn(curvePointList1);

        List<RuleName> curvePointList = ruleNameService.update(ruleName.getId(), curvePointToUpdate);
        assertThat(curvePointList.get(0).getName()).isEqualTo("testName");
    }
    @Test
    public void insertTest() {
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);
        when(ruleNameRepository.findAll()).thenReturn(ruleNames);
        List<RuleName> result = ruleNameService.insert(ruleName);

        assertThat(result.get(0).getName()).isEqualTo("test");
    }
    @Test
    public void deleteTest() {
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(ruleName));
        List<RuleName> ruleNames1 = new ArrayList<>();
        List<RuleName> result = ruleNameService.delete(ruleName.getId());
        assertTrue(result.isEmpty());
        Mockito.verify(ruleNameRepository, times(1)).delete(any(RuleName.class));

    }
}
