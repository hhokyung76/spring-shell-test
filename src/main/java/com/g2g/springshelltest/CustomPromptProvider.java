package com.g2g.springshelltest;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider
{
    @Override
    public AttributedString getPrompt()
    {
        return new AttributedString(
                "G2G Shell" + ":> ",
                AttributedStyle.DEFAULT.background(AttributedStyle.GREEN));
    }
}