package me.melyukhov.SpringProject.Serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import me.melyukhov.SpringProject.Common.Command;
import me.melyukhov.SpringProject.Common.Message;
import me.melyukhov.SpringProject.DataBase.Data;

public class DataDeserializer implements Deserializer<Message> {
	
	private static String splitter = new String(new char[] { (char) 0 });

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		
	}

	@Override
	public Message deserialize(String topic, byte[] data) {
		
		Message out = new Message();
		String[] in = (new String(data)).split(splitter);

		Command command = Command.valueOf(in[0]);
		
		if(command == Command.DELETE) {
			out.command = command;
			out.data = new Data(Integer.parseInt(in[1]));
		} else {
			out.command = command;
			out.data = new Data(in[1],in[2],in[3],Integer.parseInt(in[4]));
		}
		
		
		return out;
	}

	@Override
	public void close() {	}
}
