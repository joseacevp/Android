package com.example.bundelparcelables;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Contacto implements Parcelable {

        String mNombre;
        ArrayList<Contacto> mFamiliares;
        String mTelefono;
        int mNumeroHijos;
        Date mFechaNacimiento;
        boolean mCasado;

        Contacto(String Nombre, String Telefono, int nHijos, Date fecha, boolean Casado){
            mNombre=Nombre;
            mTelefono=Telefono;
            mNumeroHijos=nHijos;
            mCasado=Casado;
            mFechaNacimiento=fecha;
            mFamiliares=new ArrayList<Contacto>();

        }

    protected Contacto(Parcel in) {
        mNombre = in.readString();
        mFamiliares = in.createTypedArrayList(Contacto.CREATOR);
        mTelefono = in.readString();
        mNumeroHijos = in.readInt();
        mCasado = in.readByte() != 0;
        mFechaNacimiento = new Date(in.readLong());//gestiona la fecha de nacimiento
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    void addFamiliar(Contacto c){
            mFamiliares.add(c);
        }

    //solo tiene que devolver 0
    @Override
    public int describeContents() {
        return 0;
    }

    //almacena los atributos en el paquete o parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mNombre);
        parcel.writeTypedList(mFamiliares);
        parcel.writeString(mTelefono);
        parcel.writeInt(mNumeroHijos);
        parcel.writeByte((byte) (mCasado ? 1 : 0));
        parcel.writeLong(mFechaNacimiento.getTime());//pasa a long la fecha antes de enviarla
    }
}
