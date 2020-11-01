package com.company.ftx.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FtxClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtxClientApplication.class, args);
		System.out.println("Hello in Ftx exchange client!");
		System.out.println("After system connected to websocket, you can enter a command like this:");
		System.out.println("subscribe ticker BTC-PERP");
		System.out.println("where \"subscribe\" - is the operation, " +
				"\"ticker\" - is the channel and \"BTC-PERP\" is the market");
		System.out.println("or you can enter just \"test\" it will be equivalent to: \"subscribe ticker BTC/USD\"");
	}

}
