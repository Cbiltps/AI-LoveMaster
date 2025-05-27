package com.cbltps.loveaiagent.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * Description: 终端操作工具类
 * User: lichengxiang
 * Date: 2025-05-24
 * Time: 23:28
 */
public class TerminalOperationTool {
    @Tool(description = "Execute a command in the terminal")
    public String executeTerminalCommand(@ToolParam(description = "Command to execute in the terminal") String command) {
        StringBuilder output = new StringBuilder();
        try {
//            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command); // Windows 系统使用此代码
            Process process = Runtime.getRuntime().exec(command); // Linux 系统使用此代码
//            Process process = builder.start(); // Windows 系统使用此代码
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                output.append("Command execution failed with exit code: ").append(exitCode);
            }
        } catch (IOException | InterruptedException e) {
            output.append("Error executing command: ").append(e.getMessage());
        }
        return output.toString();
    }
}
