package org.cephas.acdia.model;
import org.cephas.acdia.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Date;


/**
 * Created by admin on 08-05-19.
 */
@Entity
@Table(name = "user_profiles")
public class UserProfile extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    @Size(max = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    @Size(max = 100)
    private String address1;

    @Size(max = 100)
    private String address2;

    @Size(max = 100)
    private String street;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String state;

    @Size(max = 100)
    private String country;

    @Column(name = "zip_code")
    @Size(max = 32)
    private String zipCode;



   // @OneToOne(cascade = CascadeType.ALL)
  //  @JoinColumn(name = "user_id", referencedColumnName = "id")
   @OneToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "user_id", nullable = false)
    private User user;
/////////////////////////////////////////////////////////////////
    @Column(name="name", length=100, nullable=false)
    private String name;

    @Column(name="type", length=100, nullable=false)
    private String type;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;
   //////////////////////////////////////////////////////////////////////////
    public UserProfile() {

    }

    public UserProfile(String phoneNumber, Gender gender, Date dateOfBirth,
                       String address1, String address2, String street, String city,
                       String state, String country, String zipCode) {
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address1 = address1;
        this.address2 = address2;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public UserProfile( String phoneNumber, Gender gender, Date dateOfBirth,
                         String address1, String address2, String street, String city,
                         String state, String country, String zipCode, User user) {
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address1 = address1;
        this.address2 = address2;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;

        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
