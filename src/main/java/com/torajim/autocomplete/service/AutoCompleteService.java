package com.torajim.autocomplete.service;

import com.torajim.autocomplete.repository.AutoCompleteDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoCompleteService {
    public List<String> doAutoComplete(final String input) {
        return AutoCompleteDao.getStrings(input);
    }
}
