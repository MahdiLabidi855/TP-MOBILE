package com.example.tp_qr_code;

import retrofit2.*;
import retrofit2.http.*;

public interface RepoServiceAPI {
    String BASE_URL = "http://192.168.1.16:8787/";

    @PUT("/banque/retrait/{id}/{mnt}")
    Call<Compte> Retrait(@Path("id") long id, @Path("mnt") double mnt);

}
