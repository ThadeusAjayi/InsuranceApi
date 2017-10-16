package com.example.thadeus.insuranceapiconnect.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Thadeus on 10/16/2017.
 */

public class Users implements Parcelable{

    private String name;

    private String email;

    private String address;

    private String phone;

    protected Users(Parcel in) {
        name = in.readString();
        email = in.readString();
        address = in.readString();
        phone = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Users(String name, String email, String address, String phone) {

        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(phone);
    }
}
