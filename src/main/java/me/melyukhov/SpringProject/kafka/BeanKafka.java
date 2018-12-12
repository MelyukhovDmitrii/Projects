package me.melyukhov.SpringProject.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import me.melyukhov.SpringProject.Common.Command;
import me.melyukhov.SpringProject.Common.Message;
import me.melyukhov.SpringProject.DataBase.Data;
import me.melyukhov.SpringProject.Service.DataService;

@Component
public class BeanKafka implements IBeanKafka {
	
	private static final String TOPIC = "Project1";
	
	@Autowired
	private DataService dataService;
	
	private final KafkaTemplate<Integer, Message> template;
	
	@Autowired
	public BeanKafka(KafkaTemplate<Integer, Message> template) {
		this.template = template;
	}
	
	@KafkaListener(topics= {TOPIC})
	public void listener(Message message) {
		if(message.command == Command.DELETE) {
			dataService.deleteById(message.data.getId());
		} else {
			dataService.save(message.data);
		}
	}
	
	public void send(Message message) {
		template.send(TOPIC, message);
	}
	
	public void delete(int id) {
		Message message = new Message(new Data(id), Command.DELETE);
		template.send(TOPIC, message);
	}
}
