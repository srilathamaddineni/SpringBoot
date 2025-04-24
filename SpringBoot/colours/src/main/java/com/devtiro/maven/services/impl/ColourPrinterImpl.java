package com.devtiro.maven.services.impl;

import com.devtiro.maven.services.BluePrinter;
import com.devtiro.maven.services.ColourPrinter;
import com.devtiro.maven.services.GreenPrinter;
import com.devtiro.maven.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class ColourPrinterImpl implements ColourPrinter {
    private RedPrinter redPrinter;
    private BluePrinter bluePrinter;
    private GreenPrinter greenPrinter;

    public ColourPrinterImpl(BluePrinter bluePrinter,GreenPrinter greenPrinter,RedPrinter redPrinter)
    {
        this.redPrinter=redPrinter;
        this.bluePrinter=bluePrinter;
        this.greenPrinter=greenPrinter;
    }
    @Override
    public String print()
    {
        return String.join(",", redPrinter.print(), bluePrinter.print(), greenPrinter.print());
    }

}
