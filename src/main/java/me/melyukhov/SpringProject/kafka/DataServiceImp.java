package me.melyukhov.SpringProject.kafka;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.melyukhov.SpringProject.Common.Command;
import me.melyukhov.SpringProject.Common.Message;
import me.melyukhov.SpringProject.DataBase.Data;
import me.melyukhov.SpringProject.Service.DataService;

@Component
public class DataServiceImp implements IDataService{

	@Autowired
	private DataService dataRepository;
	
	@Autowired 
	private BeanKafka kafka;
	
	@Override
	public Data save(Data data){
		Data we = dataRepository.save(data);
		kafka.send(new Message(we, Command.SAVE));
		return we;
	}

	@Override
	public Optional<Data> findById(int id) {
		return dataRepository.findById(id);
	}

	@Override
	public void deleteById(int id) {
		dataRepository.deleteById(id);
		kafka.delete(id);
	}

	@Override
	public Iterable<Data> findAll() {
		return dataRepository.findAll();
	}

}
