package com.nnk.springboot.service;

import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RuleNameTest {

    @InjectMocks
    private RuleNameServiceImpl ruleNameService;

    @Mock
    private RuleNameRepository ruleNameRepository;

    @Test
    public void findAllTest() {

    }

    @Test
    public void getRuleNameByIdTest() {

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
