package pl.mizuirokoala.conventer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.mizuirokoala.entity.Hotel;
import pl.mizuirokoala.repository.HotelRepository;

public class HotelConverter implements Converter<String, Hotel> {

	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel convert(String source) {
		long id = Long.parseLong(source);
		Hotel hotel = this.hotelRepository.findOne(id);
		return hotel;
	}
}
