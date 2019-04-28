package com.torajim.autocomplete.service;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class AutoCompleteDoc {
    private String word;
    private long score;
}