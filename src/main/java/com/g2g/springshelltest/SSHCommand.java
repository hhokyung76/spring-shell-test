package com.g2g.springshelltest;

import ch.obermuhlner.scriptengine.java.JavaScriptEngine;
import ch.obermuhlner.scriptengine.java.constructor.DefaultConstructorStrategy;
import com.google.common.base.Charsets;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@ShellComponent
public class SSHCommand
{

    @ShellMethod(value = "connect to remote server")
    public void ssh(@ShellOption(value = "-s") String remoteServer)
    {
        log.info(String.format("Logged to machine '%s'", remoteServer));
    }


    @ShellMethod(value = "echo ")
    public void echo(@ShellOption(value = "-s") String strVal)
    {
        System.out.println(strVal);
    }


    @ShellMethod(value = "run script")
    public void run_script(@ShellOption(value = "-p") String scriptPath)
    {
        log.info(String.format("Logged to machine '%s'", scriptPath));
        runScript(scriptPath);
    }

    private void runScript(String path) {
        try {
            File file = new File(path);

            String content = com.google.common.io.Files.asCharSource(file, Charsets.UTF_8)
                    .read();

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("java");
            JavaScriptEngine javaScriptEngine = (JavaScriptEngine) engine;

            javaScriptEngine.setConstructorStrategy(DefaultConstructorStrategy.byMatchingArguments("Hello", 42));

            Object result = engine.eval(content);
            System.out.println("Result: " + result);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SSHCommand cmd = new SSHCommand();
        cmd.runScript("/Users/hklee/data/test1.sct");
    }

}

//  run_script -p /Users/hklee/data/test2.sct