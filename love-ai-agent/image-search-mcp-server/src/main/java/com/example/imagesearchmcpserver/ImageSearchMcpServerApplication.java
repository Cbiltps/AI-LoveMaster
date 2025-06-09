package com.example.imagesearchmcpserver;

import com.example.imagesearchmcpserver.tools.ImageSearchTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImageSearchMcpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageSearchMcpServerApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider imageSearchTools(ImageSearchTool imageSearchTool) {
		return MethodToolCallbackProvider.builder()
				// 传入需要注册的工具
				.toolObjects(imageSearchTool)
				.build();
	}
}
