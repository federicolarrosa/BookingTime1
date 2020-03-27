package com.example.bookingtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

public class reservaHoraMesa extends AppCompatActivity  implements View.OnClickListener{
    ToggleButton M01,M02,M03;
    Button M1,M2,M3,M4,M5,M6,M7,M8,M9,M10,M11,M12,M13,M14,M15;
    Spinner horaRes,personRes;
    TextView horaSel,personSel;

    public ToggleButton getM01() {
        return M01;
    }

    public void setM01(ToggleButton m01) {
        M01 = m01;
    }

    public ToggleButton getM02() {
        return M02;
    }

    public void setM02(ToggleButton m02) {
        M02 = m02;
    }

    public ToggleButton getM03() {
        return M03;
    }

    public void setM03(ToggleButton m03) {
        M03 = m03;
    }

    public Button getM1() {
        return M1;
    }

    public void setM1(Button m1) {
        M1 = m1;
    }

    public Button getM2() {
        return M2;
    }

    public void setM2(Button m2) {
        M2 = m2;
    }

    public Button getM3() {
        return M3;
    }

    public void setM3(Button m3) {
        M3 = m3;
    }

    public Button getM4() {
        return M4;
    }

    public void setM4(Button m4) {
        M4 = m4;
    }

    public Button getM5() {
        return M5;
    }

    public void setM5(Button m5) {
        M5 = m5;
    }

    public Button getM6() {
        return M6;
    }

    public void setM6(Button m6) {
        M6 = m6;
    }

    public Button getM7() {
        return M7;
    }

    public void setM7(Button m7) {
        M7 = m7;
    }

    public Button getM8() {
        return M8;
    }

    public void setM8(Button m8) {
        M8 = m8;
    }

    public Button getM9() {
        return M9;
    }

    public void setM9(Button m9) {
        M9 = m9;
    }

    public Button getM10() {
        return M10;
    }

    public void setM10(Button m10) {
        M10 = m10;
    }

    public Button getM11() {
        return M11;
    }

    public void setM11(Button m11) {
        M11 = m11;
    }

    public Button getM12() {
        return M12;
    }

    public void setM12(Button m12) {
        M12 = m12;
    }

    public Button getM13() {
        return M13;
    }

    public void setM13(Button m13) {
        M13 = m13;
    }

    public Button getM14() {
        return M14;
    }

    public void setM14(Button m14) {
        M14 = m14;
    }

    public Button getM15() {
        return M15;
    }

    public void setM15(Button m15) {
        M15 = m15;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_hora_mesa);

        M01=findViewById(R.id.M01);
        M02=findViewById(R.id.M02);
        M03=findViewById(R.id.M03);
        M1=findViewById(R.id.M1);
        M2=findViewById(R.id.M2);
        M3=findViewById(R.id.M3);
        M4=findViewById(R.id.M4);
        M5=findViewById(R.id.M5);
        M6=findViewById(R.id.M6);
        M7=findViewById(R.id.M7);
        M8=findViewById(R.id.M8);
        M9=findViewById(R.id.M9);
        M10=findViewById(R.id.M10);
        M11=findViewById(R.id.M11);
        M12=findViewById(R.id.M12);
        M13=findViewById(R.id.M13);
        M14=findViewById(R.id.M14);
        M15=findViewById(R.id.M15);
        horaRes=findViewById(R.id.horaRes);
        personRes=findViewById(R.id.personRes);
        horaSel=findViewById(R.id.horaSel);
        personSel=findViewById(R.id.personSel);

        M01.setOnClickListener(this);
        M02.setOnClickListener(this);
        M03.setOnClickListener(this);
        M1.setOnClickListener(this);
        M2.setOnClickListener(this);
        M3.setOnClickListener(this);
        M4.setOnClickListener(this);
        M5.setOnClickListener(this);
        M6.setOnClickListener(this);
        M7.setOnClickListener(this);
        M8.setOnClickListener(this);
        M9.setOnClickListener(this);
        M10.setOnClickListener(this);
        M11.setOnClickListener(this);
        M12.setOnClickListener(this);
        M13.setOnClickListener(this);
        M14.setOnClickListener(this);
        M15.setOnClickListener(this);


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Hora,android.R.layout.simple_spinner_item);
        horaRes.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.Personas,android.R.layout.simple_spinner_item);
        personRes.setAdapter(adapter1);

     horaRes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

             horaSel.setText("Hora: "+adapter.getItem(i).toString());

         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });
     personRes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

             personSel.setText("Personas: "+adapter1.getItem(i).toString());


         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.M01:
                Intent intent01 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent01.putExtra("MesaHora",horaSel.getText().toString());
                intent01.putExtra("MesaPerson",personSel.getText().toString());
                intent01.putExtra("Mesa",M01.getText().toString());
                intent01.putExtra("id","M01");
                this.startActivity(intent01);
                M01.setEnabled(false);
                M01.setText("Reservado");
                break;

            case R.id.M02:
                Intent intent02 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent02.putExtra("MesaHora",horaSel.getText().toString());
                intent02.putExtra("MesaPerson",personSel.getText().toString());
                intent02.putExtra("Mesa",M02.getText().toString());
                intent02.putExtra("id","M02");
                this.startActivity(intent02);
                M02.setEnabled(false);
                M02.setText("Reservado");
                break;

            case R.id.M03:
                Intent intent03 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent03.putExtra("MesaHora",horaSel.getText().toString());
                intent03.putExtra("MesaPerson",personSel.getText().toString());
                intent03.putExtra("Mesa",M03.getText().toString());
                intent03.putExtra("id","M03");
                this.startActivity(intent03);
                M03.setEnabled(false);
                M03.setText("Reservado");
                break;

            case R.id.M1:
                Intent intent1 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent1.putExtra("MesaHora",horaSel.getText().toString());
                intent1.putExtra("MesaPerson",personSel.getText().toString());
                intent1.putExtra("Mesa",M1.getText().toString());
                intent1.putExtra("id","M1");
                this.startActivity(intent1);
                M1.setEnabled(false);
                M1.setText("Reservado");
                break;

            case R.id.M2:
                Intent intent2 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent2.putExtra("MesaHora",horaSel.getText().toString());
                intent2.putExtra("MesaPerson",personSel.getText().toString());
                intent2.putExtra("Mesa",M2.getText().toString());
                intent2.putExtra("id","M2");
                this.startActivity(intent2);
                M2.setEnabled(false);
                M2.setText("Reservado");
                break;

            case R.id.M3:
                Intent intent3 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent3.putExtra("MesaHora",horaSel.getText().toString());
                intent3.putExtra("MesaPerson",personSel.getText().toString());
                intent3.putExtra("Mesa",M3.getText().toString());
                intent3.putExtra("id","M3");
                this.startActivity(intent3);
                M3.setEnabled(false);
                M3.setText("Reservado");
                break;

            case R.id.M4:
                Intent intent4 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent4.putExtra("MesaHora",horaSel.getText().toString());
                intent4.putExtra("MesaPerson",personSel.getText().toString());
                intent4.putExtra("Mesa",M4.getText().toString());
                intent4.putExtra("id","M4");
                this.startActivity(intent4);
                M4.setEnabled(false);
                M4.setText("Reservado");
                break;

            case R.id.M5:
                Intent intent5 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent5.putExtra("MesaHora",horaSel.getText().toString());
                intent5.putExtra("MesaPerson",personSel.getText().toString());
                intent5.putExtra("Mesa",M5.getText().toString());
                intent5.putExtra("id","M5");
                this.startActivity(intent5);
                M5.setEnabled(false);
                M5.setText("Reservado");
                break;

            case R.id.M6:
                Intent intent6 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent6.putExtra("MesaHora",horaSel.getText().toString());
                intent6.putExtra("MesaPerson",personSel.getText().toString());
                intent6.putExtra("Mesa",M6.getText().toString());
                intent6.putExtra("id","M6");
                this.startActivity(intent6);
                M6.setEnabled(false);
                M6.setText("Reservado");
                break;

            case R.id.M7:
                Intent intent7 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent7.putExtra("MesaHora",horaSel.getText().toString());
                intent7.putExtra("MesaPerson",personSel.getText().toString());
                intent7.putExtra("Mesa",M7.getText().toString());
                intent7.putExtra("id","M7");
                this.startActivity(intent7);
                M7.setEnabled(false);
                M7.setText("Reservado");
                break;

            case R.id.M8:
                Intent intent8 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent8.putExtra("MesaHora",horaSel.getText().toString());
                intent8.putExtra("MesaPerson",personSel.getText().toString());
                intent8.putExtra("Mesa",M8.getText().toString());
                intent8.putExtra("id","M8");
                this.startActivity(intent8);
                M8.setEnabled(false);
                M8.setText("Reservado");
                break;

            case R.id.M9:
                Intent intent9 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent9.putExtra("MesaHora",horaSel.getText().toString());
                intent9.putExtra("MesaPerson",personSel.getText().toString());
                intent9.putExtra("Mesa",M9.getText().toString());
                intent9.putExtra("id","M9");
                this.startActivity(intent9);
                M9.setEnabled(false);
                M9.setText("Reservado");
                break;

            case R.id.M10:
                Intent intent10 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent10.putExtra("MesaHora",horaSel.getText().toString());
                intent10.putExtra("MesaPerson",personSel.getText().toString());
                intent10.putExtra("Mesa",M10.getText().toString());
                intent10.putExtra("id","M10");
                this.startActivity(intent10);
                M10.setEnabled(false);
                M10.setText("Reservado");
                break;

            case R.id.M11:
                Intent intent11 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent11.putExtra("MesaHora",horaSel.getText().toString());
                intent11.putExtra("MesaPerson",personSel.getText().toString());
                intent11.putExtra("Mesa",M11.getText().toString());
                intent11.putExtra("id","M11");
                this.startActivity(intent11);
                M11.setEnabled(false);
                M11.setText("Reservado");
                break;

            case R.id.M12:
                Intent intent12 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent12.putExtra("MesaHora",horaSel.getText().toString());
                intent12.putExtra("MesaPerson",personSel.getText().toString());
                intent12.putExtra("Mesa",M12.getText().toString());
                intent12.putExtra("id","M12");
                this.startActivity(intent12);
                M12.setEnabled(false);
                M12.setText("Reservado");
                break;

            case R.id.M13:
                Intent intent13 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent13.putExtra("MesaHora",horaSel.getText().toString());
                intent13.putExtra("MesaPerson",personSel.getText().toString());
                intent13.putExtra("Mesa",M13.getText().toString());
                intent13.putExtra("id","M13");
                this.startActivity(intent13);
                M13.setEnabled(false);
                M13.setText("Reservado");
                break;

            case R.id.M14:
                Intent intent14 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent14.putExtra("MesaHora",horaSel.getText().toString());
                intent14.putExtra("MesaPerson",personSel.getText().toString());
                intent14.putExtra("Mesa",M14.getText().toString());
                intent14.putExtra("id","M14");
                this.startActivity(intent14);
                M14.setEnabled(false);
                M14.setText("Reservado");
                break;

            case R.id.M15:
                Intent intent15 = new Intent(reservaHoraMesa.this, preMenu.class);
                intent15.putExtra("MesaHora",horaSel.getText().toString());
                intent15.putExtra("MesaPerson",personSel.getText().toString());
                intent15.putExtra("Mesa",M15.getText().toString());
                intent15.putExtra("id","M15");
                this.startActivity(intent15);
                M15.setEnabled(false);
                M15.setText("Reservado");
                break;
        }
    }

}
