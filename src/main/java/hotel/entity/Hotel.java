package hotel.entity;

import javax.persistence.*;


import java.util.Base64;
import java.util.Set;

@Entity
@Table
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    

    private String name;
    private String country;
    private String city;
    private String address;
    @Column
    private boolean xoa;

    
	public boolean isXoa() {
		return xoa;
	}

	public void setXoa(boolean xoa) {
		this.xoa = xoa;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Room> rooms;

    public String getPhone_number() {
        return phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    private String phone_number;

    public byte[] getImage() {
        return image;
    }

    public String getBase64Image() {
        return Base64.getEncoder().encodeToString(image);
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    private byte[] image;

    public Hotel() {}
    public Hotel(String name, String country, String city, String address, String phone_number) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phone_number = phone_number;
        this.xoa=false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setData(String name, String country, String city, String address, String phone_number) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phone_number = phone_number;
    }
}

