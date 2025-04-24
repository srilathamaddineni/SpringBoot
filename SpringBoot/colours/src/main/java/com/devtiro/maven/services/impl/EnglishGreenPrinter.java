package com.devtiro.maven.services.impl;

import com.devtiro.maven.services.GreenPrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishGreenPrinter implements GreenPrinter {
    @Override
    public String print()
    {
        return "Green";
    }

}
