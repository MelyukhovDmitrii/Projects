package me.melyukhov.SpringProject.kafka;

import me.melyukhov.SpringProject.Common.Message;

interface IBeanKafka{
	public void listener(Message message);
	
	public void send(Message message);
	
	public void delete(int id);
	
}
