package com.devtiro.maven.services.impl;

import com.devtiro.maven.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishRedPrinter implements RedPrinter {
    @Override
    public String print()
    {
         return "Red";
    }
}
