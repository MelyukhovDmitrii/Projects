package me.melyukhov.SpringProject.kafka;

import java.util.Optional;

import me.melyukhov.SpringProject.DataBase.Data;

public interface IDataService {
	
	public Data save(Data data);
	
	public Optional<Data> findById(int id);
	
	public void deleteById(int id);
	
	public Iterable<Data> findAll();
}
