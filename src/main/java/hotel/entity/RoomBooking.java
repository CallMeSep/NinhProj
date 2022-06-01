package hotel.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RoomBooking")
public class RoomBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "roomId")
    private Room room;
	
	@Column
	private Date checkedInDate;
	
	@Column
	private Date checkedOutDate;
//	@Column
//	private double price = room.getPrice()*Float.floatToIntBits(checkedOutDate.getTime() - checkedInDate.getTime() / (3600 * 24));
//	
//	public double getPrice() {
//		return price;
//	}
//
//
//	public void setPrice(double price) {
//		this.price = price;
//	}


	public void setId(Long id) {
		this.id = id;
	}


	public Room getRoom() {
		return room;
	}

	
	public RoomBooking() {
		super();
	}


	public RoomBooking(Room room, Date checkedInDate, Date checkedOutDate, UserRole appUser) {
		this.room = room;
		this.checkedInDate = checkedInDate;
		this.checkedOutDate = checkedOutDate;
		this.userRole = appUser;
	}


	@ManyToOne
	@JoinColumn(name = "userId")
    private UserRole userRole;
	
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole user) {
		this.userRole = user;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getCheckedInDate() {
		return checkedInDate;
	}

	public void setCheckedInDate(Date checkedInDate) {
		this.checkedInDate = checkedInDate;
	}

	public Date getCheckedOutDate() {
		return checkedOutDate;
	}

	public void setCheckedOutDate(Date checkedOutDate) {
		this.checkedOutDate = checkedOutDate;
	}

	public Long getId() {
		return id;
	}
	
	
}
