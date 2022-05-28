package univer.program.entity;

import java.util.List;

import javax.persistence.*;


@Entity
@Table
public class Room {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String roomNum;

	@ManyToOne
	@JoinColumn(name = "roomTypeId")
	private RoomType roomType;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

	@OneToMany(mappedBy = "room")
	private List<RoomBooking> roomBookings ;

	public Room(String roomNum, RoomType roomType, Hotel hotel, List<RoomBooking> roomBookings, int capacity,
			double price) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.hotel = hotel;
		this.roomBookings = roomBookings;
		this.capacity = capacity;
		this.price = price;
		this.xoa=false;
	}

	@Column
	private int capacity;

	@Column
	private double price;
	@Column
	private boolean xoa;
	
    

	public boolean isXoa() {
		return xoa;
	}

	public void setXoa(boolean xoa) {
		this.xoa = xoa;
	}

	public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    



    @org.jetbrains.annotations.Contract(pure = true)
    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public List<RoomBooking> getRoomBookings() {
		return roomBookings;
	}

	public void setRoomBookings(List<RoomBooking> roomBookings) {
		this.roomBookings = roomBookings;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPrice(int price) {
        this.price = price;
    }

//    @Override
//    public String toString() {
//        return String.format("Number: %s,Type: %s,Bed_Cnt: %d,Price: %d", roomNum, roomType.getDescription(), capacity, price);
//    }
}