package com.example.safesound;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacts implements Parcelable {
    public String contactID;
    public String name;
    public String phone;

    public Contacts() {
    }

    public Contacts(String contactID,String name, String phone){
        this.contactID=contactID;
        this.name=name;
        this.phone=phone;
    }

    protected Contacts(Parcel in) {
        contactID = in.readString();
        name = in.readString();
        phone = in.readString();
    }

    public String getContactName(){
        return name;
    }

    public String getContactPhone(){
        return phone;
    }


    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contactID);
        dest.writeString(name);
        dest.writeString(phone);
    }
}
