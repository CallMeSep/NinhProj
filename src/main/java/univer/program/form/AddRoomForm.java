package univer.program.form;

import java.sql.Date;

import univer.program.entity.Hotel;
import univer.program.entity.RoomType;

public class AddRoomForm {
	private Long roomId;
	public AddRoomForm(Long roomId, String roomNum, RoomType roomType, Hotel hotel, double price, int capacity) {
		super();
		this.roomId = roomId;
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.hotel = hotel;
		this.price = price;
		this.capacity = capacity;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	private String roomNum;
	private RoomType roomType;
	private Hotel hotel;
	private double price;
	private int capacity;
	
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public AddRoomForm(String roomNum, RoomType roomType, Hotel hotel, double price) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.hotel = hotel;
		this.price = price;
	}
	public AddRoomForm() {
	}
	
}
