package me.melyukhov.SpringProject.Common;

import me.melyukhov.SpringProject.DataBase.Data;

public class Message {
	
	public Data data;
	public Command command;
	
	public Message(Data data, Command command) {
		this.data = data;
		this.command = command;
	}
	
	public Message() {}
}
