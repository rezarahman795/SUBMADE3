package com.example.mainactivity.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SerialTV implements Parcelable {

    private int picture,backdropPict;
    private String namaSerial,tglSerial,descSerial,crewSerial,crewDetailSerial;

    public SerialTV(){

    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getBackdropPict() {
        return backdropPict;
    }

    public void setBackdropPict(int backdropPict) {
        this.backdropPict = backdropPict;
    }

    public String getNamaSerial() {
        return namaSerial;
    }

    public void setNamaSerial(String namaSerial) {
        this.namaSerial = namaSerial;
    }

    public String getTglSerial() {
        return tglSerial;
    }

    public void setTglSerial(String tglSerial) {
        this.tglSerial = tglSerial;
    }

    public String getDescSerial() {
        return descSerial;
    }

    public void setDescSerial(String descSerial) {
        this.descSerial = descSerial;
    }

    public String getCrewSerial() {
        return crewSerial;
    }

    public void setCrewSerial(String crewSerial) {
        this.crewSerial = crewSerial;
    }

    public String getCrewDetailSerial() {
        return crewDetailSerial;
    }

    public void setCrewDetailSerial(String crewDetailSerial) {
        this.crewDetailSerial = crewDetailSerial;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(picture);
        dest.writeInt(backdropPict);
        dest.writeString(namaSerial);
        dest.writeString(tglSerial);
        dest.writeString(descSerial);
        dest.writeString(crewSerial);
        dest.writeString(crewDetailSerial);
    }
    protected SerialTV(Parcel in) {
        picture = in.readInt();
        backdropPict = in.readInt();
        namaSerial = in.readString();
        tglSerial = in.readString();
        descSerial = in.readString();
        crewSerial = in.readString();
        crewDetailSerial = in.readString();
    }

    public static final Creator<SerialTV> CREATOR = new Creator<SerialTV>() {
        @Override
        public SerialTV createFromParcel(Parcel in) {
            return new SerialTV(in);
        }

        @Override
        public SerialTV[] newArray(int size) {
            return new SerialTV[size];
        }
    };
}
