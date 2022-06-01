package hotel.entity;
import java.sql.Date;

public class SearchForm {
	private long hotelId;
	private long roomTypeId;
	private Date checkedInDate;
	private Date checkedOutDate;
	public long getHotelId() {
		return hotelId;
	}
	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
	public long getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(long roomTypeId) {
		this.roomTypeId = roomTypeId;
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
	public SearchForm(long hotelId, long roomTypeId, Date checkedInDate, Date checkedOutDate) {
		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
		this.checkedInDate = checkedInDate;
		this.checkedOutDate = checkedOutDate;
	}
	public SearchForm() {
	}
	
}
