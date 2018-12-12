package me.melyukhov.SpringProject.Serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import me.melyukhov.SpringProject.Common.Command;
import me.melyukhov.SpringProject.Common.Message;

public class DataSerializer implements Serializer<Message> {

	private static String splitter = new String(new char[] { (char) 0 });
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public byte[] serialize(String topic, Message message) {
		if(message.command == Command.DELETE) {
			String out = message.command.toString() + splitter + message.data.getId();
			return out.getBytes();
		} else {
			String out = message.command + splitter + message.data.getFullName() + splitter + 
				message.data.getNameOfTheDish()	+ splitter + message.data.getRecipe() + splitter + message.data.getId();
			return out.getBytes();
		}
	}

	@Override
	public void close() {	}

}
