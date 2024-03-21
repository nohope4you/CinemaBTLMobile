package com.example.oucinema;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.oucinema.model.Phim;
import android.content.ContentResolver;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ManageAddFilm extends AppCompatActivity {
    DBHelper dbHelper;
    EditText etTenPhim, etMoTa, etTheLoai, etThoiLuong, etNgayPhatHanh, etDaoDien, etLinkTrailer;
    Button btnThemFilm,btnSuaFilm,btnXoaFilm,btnHinhAnh;
    ImageView e ;
    private static String urlString;
    private static final int PICK_IMAGE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_film);
        dbHelper = new DBHelper(ManageAddFilm.this);
        String user_name = getIntent().getStringExtra("user_name");
        String user_id = getIntent().getStringExtra("user_id");
        if(user_id !=null)
            Log.d("test","user id from addfilm "+user_id);
        else
            Log.d("test","error ");

        // Lấy dữ liệu từ Intent
        int itemId = getIntent().getIntExtra("item_id", -1);
        String itemName = getIntent().getStringExtra("item_name");
        String itemMoTa = getIntent().getStringExtra("item_moTa");
        String itemTheLoai = getIntent().getStringExtra("item_theLoai");
        int itemThoiLuong = getIntent().getIntExtra("item_thoiLuong", -1);
        String itemNgayPhatHanh = getIntent().getStringExtra("item_ngayPhatHanh");
        String itemDaoDien = getIntent().getStringExtra("item_daoDien");
        String itemHinhAnh = getIntent().getStringExtra("item_hinhAnh");


        // Nơi gọi biến
        e = findViewById(R.id.imageView13);
        ImageView btnReturn = findViewById(R.id.turn_back_managefilm);
        etTenPhim = findViewById(R.id.Thongtinphimtenphim);
        etMoTa = findViewById(R.id.Thongtinphimmota);
        etTheLoai = findViewById(R.id.Thongtinphimtheloai);
        etThoiLuong = findViewById(R.id.Thongtinphimthoiluong);
        etNgayPhatHanh = findViewById(R.id.Thongtinphimngayphathanh);
        etDaoDien = findViewById(R.id.Thongtinphimdaodien);
        etLinkTrailer = findViewById(R.id.Thongtinphimlinktrailer);
        btnThemFilm = findViewById(R.id.btn_themphim);
        btnSuaFilm=findViewById(R.id.btn_suaphim);
        btnXoaFilm = findViewById(R.id.btn_xoaphim);
        btnHinhAnh = findViewById(R.id.Thongtinphimnuthinhanh);



        // Update dữ liệu
//        EditText textViewName = findViewById(R.id.Thongtinphimtenphim);
        Log.d("ID phim: ", String.valueOf(itemId));
        if (itemId == -1) {
            etTenPhim.getText().clear();
            etMoTa.getText().clear();
            etTheLoai.getText().clear();
            etThoiLuong.getText().clear();
            etNgayPhatHanh.getText().clear();
            etDaoDien.getText().clear();
            etLinkTrailer.getText().clear();
            btnXoaFilm.setEnabled(false);
            btnXoaFilm.setTextColor(Color.parseColor("#C7C8CC"));

        } else {
            etTenPhim.setText(itemName);
            etMoTa.setText(itemMoTa);
            etTheLoai.setText(itemTheLoai);
            etThoiLuong.setText(String.valueOf(itemThoiLuong));
            etNgayPhatHanh.setText(itemNgayPhatHanh);
            etDaoDien.setText(itemDaoDien);
            Log.d("test url","test "+itemHinhAnh);
            Log.d("test url","testurl "+Uri.parse(itemHinhAnh));

            try {
                InputStream inputStream = getContentResolver().openInputStream(Uri.parse("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxISEhUTEhIVFhUXFxgXGBcWFxUXFRYaGBcYGhgYGBgYHSogGBolHRUYITEhJSkrLi4uGB8zODMsNygtLisBCgoKDg0OGxAQGy0mICUtLS8tLTUtLS0tLS0tLS0tLS0tLS0tLS8tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIAOEA4QMBIgACEQEDEQH/xAAcAAABBQEBAQAAAAAAAAAAAAAEAAIDBQYBBwj/xABKEAABAwIDBAcEBgcFBwUBAAABAgMRACEEEjEFQVFhBhMiMnGBkaGxwfAHFCNCUtEVM2JykqLhFlOC4vEkVFVzk5SyQ0Vjs8I0/8QAGgEAAgMBAQAAAAAAAAAAAAAAAgMAAQQFBv/EADkRAAEDAgQCCQIFAwQDAAAAAAEAAhEDIQQSMUFRYQUTIjJxgZGx8BShM1LB0fFCguFicrLjI5LC/9oADAMBAAIRAxEAPwDDupj5+edRpvarvZL6EKJWYkRMT4z7KPxew0ODO3AnenunxFcipVDHQRbjsvYFwBgqiZueWg8Pm/matsEm9A/U1NqKFwCImL6gEewjWig4AI0Hv8aPwWhhtZWXWZ1CO6LD8/OgukuM7KWhuOZXjEAehJ8xUatoBIIRrGu4fnVU8CqeOs86trDqhqHYJjIE+XvvVlsmOty7lEj1/wBaps0eIo7BKUFZ4MAC8GJAAF/GhISw6yN2s2IQsaqBB5xEH2+yq6KIxDxVA3DT41BFObYK3gFxITYrsU6K5FEhhcilFdinJQSQACSdALk+FRQhRxSinUqikJsUXs3CdYoz3UiT8BQ1H7Key50/iA9k/nVOMCytrbiU7aSQlAAESJMcNw9L+dVbjVvIH1APxo7bL05f3R7AB8KBxLlyOBj0sPdSAjJB1TG1bjUkVCjvfO4QaIpzTISoSG+uRV3g8Kw22l17tFQJjWBJAsOMb6ixXSIQUtMJSnS8D+VI+NJNY5oa0nnoEMk6BVNaLo+fsFjfnn1SI9xrM9cSdB4D4UbsvaXVkyJSoQQNbaH30x5BCY2xlaDNLUcFEetx8fSqHF2M8DNWTWKSZynsq9h3T876r8XvpYWgxBCH+rtf3lKhctKqynis2R3FEZZirDD41TIltUKPgQBpobE0BXHDApxAOqMsG6TjkklRJJuSbkk7zTWnkSM2aN8AE+U0OT886eE0OYqCXK4GCacBLDuYgElCxlVa9jvquorZiIV6eup9tWG0NmggqQIIGYjiIm3MVTHEWJlW1hAmVTDjwvRju0lrQpCoCTFkgAWIPju40HSoi0EyQqLATKbSqRxEbwbTb3U2jlXCbFKKkDZOgPoabUlSE2rPZW2FYdKg2hOZVsykypItp/D7TrVdFKhcA4QQqdTDhBC4TNzSiu0qKVIXIqVhgqCiCJSM0byAb5eMankCd1R0RhUrH2iDdBBsRIHHLvTx3XvrVE8FCFJh8Al5BJdShQMAGLgid5FAYzALbVBUCTefHfVu3gw4DBCU2UU7wJhUHfG6dxG+akfwaSlKb5kykc5WMoPkVmkSA65tw4JZ72qqU4IIbSvMJUSAnfCbZuQmQP3Twq0wWyojOLm+X8I1vzjdR+zcIkqUtSQQAEom4SExBG4qMT5k/esQyZWo8En2kD41ecwmsFiVTbTGtUjiLg8R7Rb8vWr3aA1qndHZPIg+Rsfblq9EypaCoFopmvj7x+dT1Cu1xUS3tT2XSLijlPBUTIHHUgfHwqu0NtDcee74VMyasCSha4wr/wDs+x/vbf8AGn865VLXaHqD+Y+g/ZB1b/zH0CfFQYjdRMVG6iRTDotLhZBcPP3n8hUqLkDzPgLn2CoV28vn86kYOvkPj8PbQbJINoVxs0Xk1oEK+0A/ZE/PnVDs/UVYt4oDM4fIe4e6qK0FUmKQErWBoFKA8iajpyiSSTqb+tKKahhT4LDgypWg+b0WWkmJRHDT2xQuDeAlKtDRgM2BzXF+ABm/pVEpTgZTyQLe4aUHtBnQga6xRigQZ1G/y30O5jh90SeelUhaDMhVxTSip3XyuxAncahok6CmxSinUqikJsVYsvKWQtQQVAiFAoQ5YawO94x50IyUhQKklSd4Byk+cGPSrDAuTm6sBsWzdpckTbS3G5EUD9NPnv8ALoHDkj04Pegovc5eeoIifLQTrXcVhjmkQLC0zfKNCNTc351GysA2MyNbKMeN8qt39KID1iVTukpgwVAkWMAixEW3VmulEEIhoKDcKnWwIgAcAOEyZ1OtC4JXbWOKT7CDTsFiApKgEgRcxpPO9jpuA8aFDmRwE6aHwNj7DRtFoT2DsEIfHCqdSe1H4uz62Htg+VXO0BBIqnxQppuEx92oNKqaunYg9on8Xa9bkeRkeVRk1aRmkLp+7+7/APpVTsa1Bw5D3yfjRTCbTUbqhYFJXKfXKNOT4pRTq7FUmwusNtE/a6R+1r/hvRTmxGykuNuDKkEm8jTTiCbC/GgloBp82jnNLcwkyCR7JDqbi+RZJC4ED/WuOOE60orkUcAJ8BNilFPipMME505+7mGbXSb6cqtUdFBFdQojQxRGPS2HFhoktyYKu9HOKHqAyoBIT1vKNiTUVOroFRWGplKt10c2Cwy4wcYR17xPVMm85UlZUocQBvsDG+hcQ3gcI6UEKxuLUo5MM0AoJvIz7kwCJKvGKx/XMLi1oJtNtwJkzoGiDLiYsYmFhf0hQaSJ0+54D9/RU+xejb+JulOVvetdkRy3q8vUUT0p2AjCJZyKU5nBKl/dPdy5YsAZO81UdIdt4jGullx8BplDjuIawxhhCGgSGysXeWTlSSOyCoQN9bjA7VW86jBvMhvDYjDrSxmkPKUyEypSfuSk5gnUZL3MBVSvWY5j3REFxaPyxMybkxLogCBOhlc9vSjnVJI7PDxtf15XXn0UWEZE9ubwpKJsbWWsbhwGp5C5t9udFXMKwh1SgolWVQToiR2b79DPiKzxrax7KrZYZE+3z0grtNLaglpkI4YrKlBi5Mm4kgHwtOgG4J/aop09lYEkpzJVukpVm9IST5GqhswQYmCDB0MbqkTiCFlVrlRINwcwIMjwUfWoWXULOCN+sAFKjYkEpXeOYUBu1B4G4sYpzjoWJFBNup6tSFA7lJI+6qwVPIp9qU1ChRGlWG7Ig2Ea6vMOYHqOPl876rXzRPW01DHWrSkEJKiBJ7t+IqaIXWCqXDYcpHkbj41GDWtw/R1IPaBUd82FuQ+NOxKWkJUhJbmDZMa+W+kDENJAbdZcwJgLNMsk3P8ArRcU6K5FahZaw0ALkUq7FKrVqSKUU+KtcJsq7vWf+m04oxpnTKQmQY17U7wOdLc4NElMc4N1VPSirN3ZZSCTmFkhPZ7ziolCeQOa/wCzpegFpgwdQY8xVhwOijSHaKOKVPiuRVq0yKUU+KUVakJkUoqVphazCEEngkFR9BV3geiGLcv1YbTxcJR7IKvZS6lVlITUIHifhQPe2n3yB89VX7F2SvEuZEFKYSVKUqyUpEXPrV+3shvZqXMXjSkpbI6pKTPWrjsQDv4A6QSbCaFxO0sDs1nEj64h7EONKaCG+0AopVAJTMXiSSPCstgWcWptp/HErwmDwwfZCu46VWYbJ3nPlSZmAiN4JyuLqoLiYp6AQQ5/JkjUmG6WnNeCuDjeknFxp0TII19+f2VF0j23iF436w+4tLpFw2opUylSTDKFHuQlUExYqVYmZge6SrQ2pjDNjDoVZZSSX3OTrxuRM9lISL6VPsgjqMXjnDnfSpCW80HK4+pZU8R+IBCsvBRncKB6ObOQ+8OuWEMp7bzijACAbidSpR7IAvJruBtANPWtEUgAeGYDNAboYzDLIJzGBBBJ4Nybbr1b6Jej4ZwnXuJ7b5ChI0bSfs/UyrzTwq66c4d8Iwr+GRmfaxSMqdyg4laVAkaJsJPCTQnR3pKrHulOFb6vCswFOqHaWfuobTokQJkyYiwJFbRKptuHvuB7zXiMTXqsxrq+Ib2rktnSR2QYMi0AjXLrBsOm1rTSys04+6xmz9mrQvF4B54vOP4dONC1f3pKm15eCAppqBuFqw+taf6Udr4rA49rFsIBQ2wjDrUsSlSnFOOlvUGyUpMjSgcH0p2Tj7PpOCfP3hl6lR8QI/iCddTXfZQrUBny5mkNnLByuDQHS0aTEmBHnZaOjsfTozTfME2+6pqVaTE9DnsudhbeIbOhbUCT5Ex6E1Q4nDONmFoUk8FApPtptKtTq9wg+/pr9l6CnUZU7hn5w1+yhpRTsp4Usp4U2CmZSmRXadXIqKoReLxrjiO2smItxA4ganmaqwk5j8/OlExSiqAiwSDRE2sFyK5TqUVacm0qdFKohWkfCkrD2VIzCFpTGU8R2bDS43H2EtIytrgk50FPiEjKk+afcaX1ZE6qbSRooKULCwBN48ae22SMo01+eVz60gAFZyRA+aaeiepybqVZqQgRIzHvKjxk/wCI0CtonOlCFlbgJypkqMmZcUROWROXebmLAEuICVdk24kDXfao3JIjrUoB1kwT56nwtVZY0+b7KN5fPIev7Kmx2zXGUpKsozTYKBUI/EAez50K2QCCU5gCJSSRI3iRcUZjsMlGjqVjkFA6XNxETaxoOKY3S/tC2NuL+0LYbWdwOHwacWjBKebmFgKJLZ07YUo2m0jiONZXFfSU21dGx2kTopeUz4fZ/GrPo5tYMqU24MzDoyOIIkQRExvsYI3jyql6R9Fgw59XKs2GeGbDOG4G/qyr8SdRxSTzFZaNGg1/V4hpduCXOu3cRIEt+7b7FcWvhahqmm6oRmHYJNs35XeOkjlxhEudPdqqGVCMLh07oSVKH8xHsqmxz2IxP/8AXinnh+DN1bX8CY+FC7JS4FfV3IDqBaTZxG4pO/5/aq2GzVnVQHPX2U8020HxTY1vAgXI2IcZdfkV1MH0dgerD8pceDjJB3BAhsjTTnpCxnSHChtwFIASoWAsAU9lXwV/iq8xu33VbIZYBlsOLacnUFCg60QeBDikx/8AGOFc2qhL7C0RlxDJzKQTe3eyHekjtCOVZljFQ040dFZVDktBIB/hWseYrrMpGtTp5xLqbwb+gPkHZgdJbHNeY6Vptp4hz6fcfJHjo4ciHA2tEgEBRNYpaUrSlRCVgBQ3KggieYI1qETpXsWzfolw7+EacDziXVoS4FQFJhaEqyqTaYM3BFjRL/0OBDiHsO93ShfVLuJSoFSAuLpOgJEiLzMjSMXSBMa+k7ffRc80XgLUdEtjjB4RpgAZgmVnitV1nnew5AVeNE6D1pPbLcWB2wg5gYgmwvBgiSYAiYgnvUzEYBbcr65WXO1aATlCkheabXBV3QndwrwR6JxlWaj4zG5E3k38B67+K6RrMaIAsFhPp3fCcDhmye04+XBxytoUk+10eteW9G8EkpU4pIIPZSFJKhzV88DWh+ljbv6Q2j1TBltqGG40UqftF+Ga06QgGjf0e0gIbTIMQkDUgd5ZHnrxUONesLDhcGzDtN4vtzJ5Sf1WroOi2riXYipo3TxNh6DbiQqzAtFhWbDOusK3hpxQn95J7w5GrZ76SMbhglLxZxKTPfayLtxCFZd/CuL2elIJUsgASTawGpqm2Psz6w4cS4DkmGUnUhIso/OprI2nTrEvxDQ4DjqTsA7vDjrEC+i6/SGCw7w1lBgDydQIAAN3ECx4C0knZaX+3+IFzszCARJkoEDnJrSdEukoxOGdxb+Dw7TKOyjKhOZ1Q1CZAEaJnjPA1gv0ccdi0YFiyQcz7muRKYm5/D/5EDjWl6R7QQcmHw4y4ZgBDcaKKZBXz5eZ31lr4SictNtMBxuSC7ss21cRmftyvFwuZ9Ix+J6ui9xa3vEkG/AQB4b34AKqx+KLrinCEpzHupACUjcBHAVHh8uYZ5yyM0axvimRSitYAAgLvBoAgK16R7JGHcASoFKhIAUFFPEGPI+dVEUS/iFrCQoyEiAIAA04DW2tQRUbIEFLpNe1gDzJ4/OSbFKKdFKKKUabSp0UqkqLTtnRRgBWgF/MxpRiQBx5TbT5FBtthIE2ITmVJvYwEjhcgRxnhaVLhtcyVEHziR7TSw5Y3NnRSPEHfNp8ONjQ4eWkaKjWWzCo421HPSupjMAq0zB4EWPtHupjqZ7KSUuWUjKYS5NpT+1FuNoudBcRuraBohNsrStKVB1ayOxlcMqSLkXtImd1uN6qoojGvFapUADABAEXFrjcagomCGgLYxuVsfP0XIrQ7HebxTJwGJJCVEdSsGFNOAjLlO4zceY0MVn6VDVpio2Jg6g7gjQjw/wgr0G1mFjv45qDbmCIJaxAKMQyeytFlH8LiJ1QoDTjI1FQI2g6RCwAR95HdV+0J7vga2GLw36Tw4AUE45gEtqMQ6mLoVxBtPAwd5ry13b6kEoWxlWklKgTEEGCCCJBB3VeHFau0sAGZuotadxMdl320gESc1HpChhyRijD9CYJDhsbA3jw8YsC9s4JTn2qDDqN8xI8fxCnfRdszC4rHdXikhSVNrKESUhS7Wteyc5Eb0im4LZOLxyAtammMOSRmcUEpUUkAhKUy46ZG4ESN1V2Jw5wOLGVZhJCkOlMGDYOBMmCDcTvArr0GOZTNJzhmi0agcJ38Nlw+k6rMTVFekwhkwXGzS7SeVhc7wJAOv0RsRZSwhoW6kqYUcqllPVdlBKE3OZISq2mYVbYPFEkpINovCwkzMQVAcLjdNYPoN0oL+LdacR1bjjKHVAGULcb+zWtB1hSA1Y3GQ661unHgjtKmJEkAmOZjdzrCSB+/wAss7qbmktfYixHz91NisTkIASokzcJWUiI1KEkzewi9V21szrXUqSCHXG2rhSc6CrM8Mirj7JLmuvACrTC4hLgzJmJIBIImN4m8c9/hWA+k7p43glpZbGfEBtahGjK1pCELUeORThy806A05gLjDRdIcYuSvI9srYw+OxXUDMEuuIYSmYHaInwGgjWrHBNrAKlklxVlnhwQOQ/OsvsnCOPPJQ2pKXFEkKUrKAQCZKjppqd5q+xj+LwsDF4ZUHuuCMirblplC/I03GUHusyCYE3vA0tw34krsdDdIUaDYrktF8pjsydSTcl0GLiA21pReKzOfZ5jk7y7ntb0t+fePLLxojau2uqaAQPtVdlCQNN2aPQAcapHOkLQHYQpR5wlPsJrQdBsDJVtPEgKyHLhkEWU6BZcb0N6+O+RfF1bqLc9VvZGjd3OOg89zsBwBI6WN6RpO/8eFcHVX2kXDR46W8xMkq82fgf0bg+on/asQAvEK1KEkEJZncYN/E8RVXFTvPKWoqUSSSSSdSTrTKQxpEucZcTJPE/sNANICfhcM3D0xTb/J+fJTIpRRLOFKr6DnT0YEzc25b6NPLmjdBxXIqwdwNuyT50FFRUHB2iZFdiuxSiorhNiu12KVRUr7Bv5+0odlsZ16doghKUpHASLc1GnpdzNC1z1yieaUzb+IelU0U5KiNCRYjyOopZZw+fPaBshNIfPnwQNlcMJBbWCoAtw4J4KAkDj90xyrrmMZU0tKxJMLaiOwTZYPmm/E331TKWTv3AHmEgAT6D0psVDTnU7yq6ncn5b55wuKMmTcm5J1NNinxXAKYnJy2VAAkEBWhOhjhUcUZjcWp3JMAISEAAAWHgPfwoUJqhO6FmYjtC6vOhuCK8QFzlQ0M6lTGmgJ4G88ga856Q7WbxeKxDq0JCHljIvLC20oBShRjcoXUDy4V6H0zf+o7MGHTZ7GHKeISUjrPIJIT4rNeZPYcEAC0CB+VdDoPB/VGriTYdxp3sZLh/dpM6XXj+lukB9SC0AgbEWI/Y3/nQro4heHf6hzRULRwMC5B8L/4auulGy+va7I+0RdPPinz94FU+x8SFhOHdspJlhZ1SrXqyeBFhytwrWsLJF9RY+I+fbScUKlHESRDvseY5EbeK9FgBSxOCyAyy4uZIBvlPNpmDwylYZnEufV28SyspdwygMyTCkpVoeY0H8W6vYugf0hMY1vI+pDOISO0FEJQ4PxoJPqndzGnm2OwIZfK4+wxILbg3IUrRXITv3X5VicRhy2tSFapJB8jWqmynXaR5jkCbjyPvK4XSDalFzXO1jK7mW9139zI82uGy+kum/TvD4HDFbTjbrypS0hKkqE/iXlNkj22HMfOmOeW7medUVuOLUcx1J1WfVQoI1q8DsTrXkNKH2bKEZ/2lL7ZR6mOQFOhmHF+ZJ5D/ACQPNZcPQfic0a2A8XH2DQ4+Q1Nkf0O2VkbLqxdwQBwR/U38AKE20+84E4RtSiCc60hSslrJUoaQNb8RWsfXlFtTZI57vT4VnNrvpwyFNt/r3u0tW9KZgeywHifHm0nPrYjMBLthtO3kBfyXqq9Glh8CaTjDLZuJAuY5uNhwBPAKl2r1OVDLbSApqc7wzZ3Cb5SM2WAZuBOg3V6c4lt/Z2CxDCcqENhlSBMIUmZ14qCrm5lJrzLCYUJAkeA4f1refRXjUleJ2e4ew8kutclDvgc7BQ/5Zrd01gnUMKyuCSaZl3MGzjwtM8vfyeB6QaMb1gaGg7DQDgPlzw2gilFT4nDqQpSFCCklJ8QYqOK5Mr3KNaulNpA1HlUgTGYC1gRyJn+lV6VEaGKnaxRAiJ+d9RJLDsimgIt7T+dCONtaSZ9RTHnCozAqOKisMIvKaozwta1NipIrkVaYmRSp8UqipSxXIp8UVgkamJI0qlbjAlQ4djMdYinP4UiIk++jQec6T505dRJ6x0qnKaUVZO4fOZ08taFeYKfDjUTGvB8UPFWXR5DX1hBeUEoBm+hI7oJ3CeNrUBFKKF7czSNJVvbmaW6SifpH2I+9ixiHH8K2wEhtnrXijdmUe6U5iSd+kcKzX6DR/wAQ2f8A9z/lrbbB2g7IYKQ624cpac7SSDrEzA38OVVOyNnMHaeIODQE4RoZHEkBbbr3akIzg5QCd34eChR4TpPFYOkaLXNDWtkdkERIF7ggk6WdJm4uV47G9FCnWAdfNpe/p881mMT0bbX/AO4bPB4/Wf8ALV/s5LYQA9j8AVixUnEDtAaE9ixq+6VIbLaMM202HcUrqkkIRKURLzgt91E+ahVjjUYbDMLc6hvI02VAZEaITZNxqYA86yYvpfEYlrDUiTpDQDw1M2JsPArRgqdTCPd1LtrzJHHTiNVTMbDGJbc6l1jEJSIWlpanDeYEJTqYPpWI6W9D8YHUqbwmJWFJAJDLqjKbSYTvEe2tP0I6OsPYNDzqVpccK1Hq3XWwqFqAUUpVEmDoAIi1R4rG7FacW2t7FZkKKFALxJEpMKAOe9wb06lXrUq72UZeWyCMhOljo8b7+SPE4ypiMOBXDRMEHQiOUHn6nish0e6HY1WIRnweJSlPaJUy8kGBYXTvMe2t9hujuIQCBhnrkrUeqcupRknT5ECh+h+Aw+LYW4oO2ecQk/WMSCUCCgqHWWVCvZVz/ZTCGYL9rH/aH7GAfx8CD50nF9IVDULahgtsex/2c0zo6tUoUoYwHMSZJM+3L5Kq8bs8tDM84wyog9WjEOpZJ55T2tdbVk2+j6SpTjm0dnqWoyT9Z/y+Qq16FYZtraOLwzyQ6oA9Wt0BasqFWEqGpQoG34a2213sPh2w4rDNlGdCVqAbHVpUcvWEZDmAJTIkWJvamtx2JwVfJSglwaQcuocAREknW1yL7JGKqPx9PNVs1pNhtxn5xXnX6DR/xDZ//c/5al2JsDEfW2XMNjMCtxtQUlCMQVKUB3gYRoRIPjW/21stLjDqGUNtulJCFBDdlcJKbA6TumRWe6KKQnZoOFbDbiFFrGWPXFYmCVG4QeA0Jjcac7pvF4qgQXC5DYyiO0DBdeYOgjexLbTkp9GNbXZT0nQz7c1a9NOqOIzNqBUR9oBcBQJBvoTEA+FUEU6uxSqNPq2BkzC9lSZ1bAyZgQmRSipW2yowASeV6LawPaykzaSElJgTBlWg9tGSBqiLgNVXRXYq4Xs9sg5SUkAWXykK4E3F4FilXlWut5SRYwYtcVQcCqa8O0UMUop8VyKJEmxSp0UqiikintrKTIrkUoqlFIXlK3gRfxp6cWd4BqGKUVEOQcE510q104VHU7WHUqSlJISJJAsBz4VFFRWI0CbFKKdFG7KwgWuVqCW0ArdWbBKE3USd1qF72saXO0Hz4N1TnhjS52gUG0sarB4XO2CcViiWcOkd5INlu301AB5jdNWnR3ZicIwhhN4upQ++s95XwHICs/srEHG4tzHrENJ+xwqD91CZGaNx181K4CjelW2Cwwcn61w9W0BrmVaR4C/jHGslWm95FD+omXf7tA3wYLH/AFFxGy4lM5w7F1dIsP8ATt5ut5RxV1gNqNPFZbCj1a1NZyEgKKe9kIJJTJibTWZ+lHaWTCBod55YEb8qCFH+bJ61a7FwYw7DbIjsJAJ4qN1HzUTWJ6UY5Du1GG1rCW2lNglXckkLVJ3A9lJJsI4UzA0GnFh7QS1ku5nLp6mLePCUGMDqeFh5hzoB4SdfICfdejbKaDLLTU2bbSkn91IBPrJrz3b/AE+YcZdYw+FKA6CCtSkJPaMqJbQm5PEqOtbx6FpI6xEKBGZLre8RIOaKyI+jrCf3y/8AqsUOC+mYS/EAkyCImJ1JNxN9JkKsZTL8rKNRoEQe0PDnsivomcjBuf8APV/9bVa9G1U9aWQVZ0oS4bAJyqKkiFTJMp4cL1hvouX/ALM4MyB9se8tCT3EblEcKtMQoJ2iycyJdw7jdloI7Cgu5BgWnWpjKGbF1RvLj6Cf0Uw1SkMNT7Q2Go3MITpzieoxmCxu5JLazfuTceJS45WxxrSXW1trulaShXgoQYrK9P8ACFzBLMpJbKVjtoJsYNgeCjRnRPaJewjKzqE5FeKOz7YB86CrSzYam8atJb/9N9JKZSa36l9MEEOAda/Jwt5IjontBSmSy6Zew6upX+0E/q13vCkjXeUmgdovDAY0YoicNifscWncCdHfHfPJW9VSnArTjfrDZTkcbyPJJIJKe4sCIJFh4Txo/aGHQ+0tpwSlYg8uBHMGCOYoZY2rmjsvHaA56geDhmZw7JRHCPfRNM95p7J8ND6WPNBbVwBZcKZkapVuUk91Q+dxoSKk6LPqeYXgXb4rBfq+LrAiAOOUERyKedIJG/StrCbsd3hrz4OHJwv6jaB0cHievpye8LOHAo9hlSW4iCYVcKgiDv0EgpE8zzoggrF4Maa2EAEgCJTM3ruKRBReezCbpsLxmubgbx61Lh0Xkx6g0IvdA51syFxClqKYJGotA1OgUb6RrTMZmW2M1ylSlTmJIFgZ3HQX19aNxTcRGs20+OlC4ZvMoykDsE2E/JueekcKjoaJ4K2uEA8Pn6qmilFSKFcinLYSmRSqSKVRVKdFKKkilFUqlMy0stSRXYqKpRmC2l1bS2+rSoLzdokhQlMWi3OgCmpxh1Rmyqy+Bj10pmWqAAJIS2Ma1znDfXxAjy+WUWWj9rYAPYP6s1immlOkF5RDhJTqGxlGk6+fGh8tLLQuaSQQSIM7ajSQQQYN/FLxFFtdmRxIHJLCYLENNpbRi9n5UJCR/s70wBF73NA43YGIeeafVjsFma7ieqdDcneUxc6fwijstdy0DaRaSQ699mb6/wBO8rGejKZEF74/3KTqcb/vmzf+g5+VYTph0McYaXjXMUy51jsAIC5WtRKiEyNAJPlW4Q3JA48dPOm9OejruKVhxh8RgwwwkQlx0gqcJlalpSkgiw/m41dCr9LWbDoB17I0AmLNm5gctdlix+BAaAHPcSdyXRxMfJXl+0CWkJJbb6xW7ILXvbzSPWn49KWQO5mjQtg58uXyTeZrT4roDjHHEuHE4CUxADq4sZ06uiH+hGLWDmd2YSRElxcjw7FahjaIDJdxLtfIXboAtzsQ1xqhsjQMlpOguTBBzOPhaNpWXYwacqJSicgOgvZJ4U1GHRB7CLLKCSka58o9iknyrUp6GYwBA+sbP7Agfaufhy37HCuHoVjMrifrGzu2ST9qu2YAW7FtKWcYwm9T/lxPLgZWn6rCjRrtPyHgOXGyyuMwAUlaUpAUm4ypKSoTbX/EPFNWX0d4HHOh44PEMtpbylxL57BzZoVlUhQ+4RmsRV7/AGOx2YK+sbNmI/Wr7X8nzmNW/QHou7g33lPYnB9U80tCktukkEwUkBSRYdrfvqn9INGGezMCbESC7cSO02NLjaVy+kqdGrVZVoBwIseyRbUHxGh3Nlw4ba399sj+NP5VxWG2xud2T4hbXxqcpqbDYNbhhCcx4DWsxpFuuX/0H7rS/o9rRJqvH9xWZw3RXaiMYnG/WcGp4KCifrDYCgAE5cqQAElIywItWv29g0pWHEZYcElKVJVkV95Nt3A+NAFHKlFOd1j3te4iwizYEcNdtuF+JTMJgW4Z5cxxvrO6PUglCXAZUMqZkECRFwdDAAiN00sITB4AzMHnefLTf5ULhXy2SRv1HHhpfW/lRasUg37STpa5IHdFzECBu30JzCwWhzSLR84J6u1YZSSbAXN40jduvQy0kJJOgMCNDYzrbenSdPE0/E4kWCbxB5fukcAm2utBOEqMmPIAD0FQAnVRrSh8tLLU+WuZKctGZQZa7UuSlUQyuxXctH4nZLzYlbagOIhQHiUzFBhNUhD2uEtMpsVqOj3RbrQlx1YSmxyRJUOZnsg1nEprY4fH9hMSAoDlaiaWgy5YekK1VjB1Zid+Hy/otQlUDKlPYAiwtHhWa2r0RbVmW05lUb5CBl8BF0+2jk7SgFMxpHCPjQ316CTOtPfUY4X/AIXBoGtRdNMx+visOtopJBEEGCOYpuWtM9sRb5W4hSZJnKbTaLGqd3BLSrKpBB4R8azxaV6OliqdQWInccEDlruWiMldyUJKfmUGWlkqYJ4U7q6gdIkIRUB0UGWuZaOw2FK1pQNVED1MVoVbAZ07c8ZHuirlJq4tlIgO3WRyUslaR7o7+BweCgR7RPurmA2WlC/tIURuF0+fHwoXPgSUBx1LLIM8t1T4PZLzv6ttRHHQepo7+yuI35B/i/pW7wroCRFJ1ynZREyuY/pasXdkADzK85xGxHkaokfswfdehEApMgkEbxIIr0R4iqjaOzkOX0VxA9/Gs5flN1po9Il1qg9P2+eCx5RT04ZREhJI4gEj1q9Q0yjROY8VX/l099WOCxBUQJqCo3QJr8dlHZb62WP6ul1dW+2GAl5YAgTPqAT7TTGNmOrEpQY4mAPInWmLUK4yBxMSqvq651dWzuynUiSgxyhXuoXq6kqxVDrgoPq6XV0Z1dT4fZ7i+6gkcdB6m1RQ1QBJKrerpVc/oR78P8yPzpUMhB9Qz8w9QtNn4e2qTbOxCrtsogk9pNkg8xMRzFXYTlEm/tj+tc6zjry+NKDoK49Ko6m7Mz+VSbH2Lllx5Nxomyh+8YmeQqzxrXWCCYIuP60Tn9edcU2SJFuO6fXfUN1KlV1R2Z/+FWdU4LRNcXh1QSRoJIGuk1YqbVwMesVGlzTw90fmak8VM3AKmRtTL3Qr1AqHE7RWsRp7TTMS0AtQGgUR7aYEU2F0hSpiCAoQiuhFTBFSBFWmZ1H2cgSEgGSSeNMCKsWdmuq0bUfKPfTXcGtFlJI8RVAQLJTajBZp+4Q+DVkWlX4VA+hmtwGATmFxwrHt4cqMAE+FaHZr6kpANimx8Nx+eBpjCN1hx4zQRqPb+Ua9hwd1ZzFJUl1UjU1p04pJ1prrTa9YPjVV6AqjskSsFOqWahVeGxlqlViamXsxG730z6gBvpWSsBED1RSwmUOpRNPaw+81P1UbxQmIdSO8snkLCqLIu5WDNgoBhkKUYA19alaZQ0c2/cPjVdiNrAWQIpuHWpckms7qjZDW6rQKbiisLhgpRdWJKjKQfYY3nhVgV8deVQFUGNwHuge6utoURpbWdJ8/ndTyZUecxk+Xgpes4a86C2hs4ODOhMK36AK+E899GoaIubjhrHO1OK+NUJF1TXlrpbqqjA7IMy4mw0TIM+MHSrXP/oN35eFIr+TpXYzDgePwmrJlXUe55lyWeu1H1Z4fzf1rtChsmh7gfWnZCoSBHHdPrQqHAkSbndy/M104onU+nxocw3R5DNkTlyiSPjHM1GXydfTfUQxPP1rjiwbix38/61CZ0UDTN1P1vP1rpTmuBce3l40M0oaq04H3+FOOKJ+A3/0qZuKmQ7KsdwLslRQbkkxf3VGwxmUBxIHqa2Wz8IAJXc891EPYZlWqE+MAH1FdBuHcRJshPSMWjzCx2JabSY7Q8wfZFF4BlKYVrOkiKn2g6ltZCxroobxz513D4pChGZJ8bGsMkktJuNlHVXuZvBViziQKA21i5FS9Sk/eA8KnwuHbnsjMeJ0pxFR4yCBKzDK05lzB4aUgkRImBYCp0bPzEEKgAQbayZow4ckXIjgKY49ltW1tFoHaCT1rieyoHNk/hXfmKqcaXGT2tNxGhq3GLmpMVgutSUqUIPKaXUw7XtPV2P280Tapae3os2NpGmubTUKu/qy2B2QCkcBfzFSNYpLohaQocwDWX6R+hqQfD/P6J3Wt1DZHj/hZR/aZ41V4rGk6mtLtro2D2mTlvdJ0HMflVtsjAMsABCQVb1kAqPnuHIUhuBql8PMc9fT4E76im1uZonloqjotsdJSHnUyTdKToBuJG8mtWVpiIEcLVFiMQnSJJ4VAMKvWU+BJ/KuvSpii3IweKwVHGo7M5D4zCpnOhJJ4ASPGKBcWQYVIPCL+2r1OJy2UIPsNceWhYhQkfOnCk1MMHSWmPZMZWIs4SqHruB9fhUg7YkCDv5/1qJ9oNqMmRqmfeajVieJ8q512m62xN2ooNkXIkcNfPwqNT86/1/pUH1ngfWnKdCxOit/PkedTMFeQ6lS9Z4+z8q5QnWV2hzIurUL+7y91MTv8fgK7SpZ1Wgd1dPxHxpydT8/dFKlRt1QuSe7o+d9SYH9anxT7zXaVRnfHiFHfhu8D+i1A0qM0qVdwrhhUnS/uN+KvhWZwmtKlXCx347vL/iF08N+EPP3KtUaVe7B7p8aVKi6O/G8j+iXiO4rw6VXq74pUq7r9lz2bp2O3URhtKVKqHeU/pCe9oaz+C7x8T767SpdbUJlLQq5PdoNquUqt+oUZoVxr9Z5VZjSlSqU9Cqeq3aGg8aa3SpUB7yId0IHa/eR5f+Qqt4+XxpUq5uI/FPkunh/wx5+5XF6envpzPePl8aVKkHvJ50TqVKlVql//2Q=="));
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ImageView imageView = findViewById(R.id.imageView13);
                imageView.setImageBitmap(bitmap);
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }


        // Tạo Intent
        Intent intent = new Intent(this, ManageFilm.class);

        btnThemFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    e = findViewById(R.id.imageView13);
                    Phim phim = new Phim();
                    phim.setTenPhim(etTenPhim.getText().toString());
                    phim.setMoTa(etMoTa.getText().toString());
                    phim.setTheLoai(etTheLoai.getText().toString());
                    phim.setThoiLuong(Integer.parseInt(etThoiLuong.getText().toString()));
                    java.sql.Date date = java.sql.Date.valueOf(etNgayPhatHanh.getText().toString());
                    phim.setNgayPhatHanh(date);
                    phim.setDaoDien(etDaoDien.getText().toString());
                    phim.setLinkTrailer(etLinkTrailer.getText().toString());
                    phim.setUserUpdate(Integer.parseInt(user_id));
                    phim.setHinhAnh(urlString);
                boolean b = dbHelper.addFilm(phim);
                if (b) {
                    Toast.makeText(ManageAddFilm.this, "Thêm phim thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ManageAddFilm.this, "Thêm phim Thất bại", Toast.LENGTH_LONG).show();
                }
                }catch (Exception e){
                    Toast.makeText(ManageAddFilm.this, "Chưa chọn hình ảnh", Toast.LENGTH_LONG).show();
                }

            }
        });
        //Xoá
        btnXoaFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phim phim = new Phim();
                phim.setId(itemId);
                String idd= String.valueOf(itemId);
                phim.setUserUpdate(Integer.parseInt(user_id));
                boolean b = dbHelper.deleteFilm(phim,idd);
                if (b) {
                    Toast.makeText(ManageAddFilm.this, "Xoá phim thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ManageAddFilm.this, "Xoá phim Thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Sửa thông tin phim
        btnSuaFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemId == -1) {
                    Toast.makeText(ManageAddFilm.this, "Vui lòng quay lại chọn phim cần sửa", Toast.LENGTH_LONG).show();
                }else{
                    Phim phim = new Phim();
                    phim.setTenPhim(etTenPhim.getText().toString());
                    phim.setMoTa(etMoTa.getText().toString());
                    phim.setTheLoai(etTheLoai.getText().toString());
                    phim.setThoiLuong(Integer.parseInt(etThoiLuong.getText().toString()));
                    java.sql.Date date = java.sql.Date.valueOf(etNgayPhatHanh.getText().toString());
                    phim.setNgayPhatHanh(date);
                    phim.setDaoDien(etDaoDien.getText().toString());
                    phim.setLinkTrailer(etLinkTrailer.getText().toString());
                    String idphim = String.valueOf(itemId);
                    phim.setUserUpdate(Integer.parseInt(user_id));

                    boolean b = dbHelper.updateFilm(phim,idphim);
                    if (b) {
                        Toast.makeText(ManageAddFilm.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ManageAddFilm.this, "Cập nhật phim Thất bại", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // Quay về quản lí phim
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                startActivity(intent);
            }
        });

        // nút thêm hình ảnh
        btnHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
//                Log.d("hinh anh",urlString);
                startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            e = findViewById(R.id.imageView13);
            e.setImageURI(imageUri);

        }

        // Chuyển đổi ảnh sang byte array
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imagefilm);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();

        urlString = String.valueOf(imageBytes);
        Log.d("d ",String.valueOf(imageBytes));

    }

}