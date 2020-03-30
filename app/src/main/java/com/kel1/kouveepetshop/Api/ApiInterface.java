package com.kel1.kouveepetshop.Api;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.kel1.kouveepetshop.DAO.customerDAO;

public interface ApiInterface {
//    =============================READ========================================================
    @GET("customer")
    Call<Respon> getCustomer();
    @GET("produk")
    Call<Respon> getProduk();
    @GET("layanan")
    Call<Respon> getLayanan();
    @GET("hewan")
    Call<Respon> getHewan();
    @GET("jenishewan")
    Call<Respon> getJenisHewan();
    @GET("ukuranhewan")
    Call<Respon> getUkuranHewan();
    @GET("supplier")
    Call<Respon> getSupplier();


//    =============================SEARCH========================================================
    @GET("customer/{id}")
    Call<customerDAO> getCustomer(@Path("id")String id);
    @GET("produk/{id}")
    Call<customerDAO> getProduk(@Path("id")String id);
    @GET("layanan/{id}")
    Call<customerDAO> getLayanan(@Path("id")String id);
    @GET("hewan/{id}")
    Call<customerDAO> getHewan(@Path("id")String id);
    @GET("jenishewan/{id}")
    Call<customerDAO> getJenisHewan(@Path("id")String id);
    @GET("ukuranhewan/{id}")
    Call<customerDAO> getUkuranHewan(@Path("id")String id);
    @GET("supplier/{id}")
    Call<customerDAO> getSupplier(@Path("id")String id);

//    =============================CREATE========================================================
    @POST("customer/")
    @FormUrlEncoded
    Call<String> addCustomer(@Field("nama_customer")String nama_customer,
                             @Field("alamat_customer")String alamat_customer,
                             @Field("tgllahir_customer")String tgllahir_customer,
                             @Field("telp_customer")String telp_customer,
                             @Field("cust_created_by")String cust_created_by);

    @POST("produk/")
    @FormUrlEncoded
    Call<String> addProduk(@Field("id_supplier")String id_supplier,
                           @Field("nama_produk")String nama_produk,
                           @Field("harga_beli_produk")String harga_beli_produk,
                           @Field("harga_jual_produk")String harga_jual_produk,
                           @Field("stok")String stok,
                           @Field("min_stok")String min_stok);

    @POST("layanan/")
    @FormUrlEncoded
    Call<String> addLayanan(@Field("nama_layanan")String nama_layanan);

    @POST("hewan/")
    @FormUrlEncoded
    Call<String> addHewan(@Field("id_customer")String id_customer,
                          @Field("nama_hewan")String nama_hewan,
                          @Field("tgl_lahir_hewan")String tgl_lahir_hewan,
                          @Field("hwn_created_by")String hwn_created_by);

    @POST("jenishewan/")
    @FormUrlEncoded
    Call<String> addJenisHewan(@Field("jenis")String jenis);

    @POST("ukuranhewan/")
    @FormUrlEncoded
    Call<String> addUkuranHewan(@Field("ukuran")String ukuran);

    @POST("Supplier/")
    @FormUrlEncoded
    Call<String> addSupplier(@Field("nama_supplier")String nama_customer,
                             @Field("alamat_supplier")String alamat_customer,
                             @Field("telp_supplier")String telp_customer);

//    =============================UPDATE========================================================
    @POST("customer/{id}")
    @FormUrlEncoded
    Call<String> editCustomer(@Path("id")String id,
                              @Field("nama_customer")String nama_customer,
                              @Field("alamat_customer")String alamat_customer,
                              @Field("tgllahir_customer")String tgllahir_customer,
                              @Field("telp_customer")String telp_customer,
                              @Field("cust_edited_by")String cust_edited_by);

    @POST("produk/{id}")
    @FormUrlEncoded
    Call<String> addProduk(@Path("id")String id,
                           @Field("id_supplier")String id_supplier,
                           @Field("nama_produk")String nama_produk,
                           @Field("harga_beli_produk")String harga_beli_produk,
                           @Field("harga_jual_produk")String harga_jual_produk,
                           @Field("stok")String stok,
                           @Field("min_stok")String min_stok);

    @POST("layanan/{id}")
    @FormUrlEncoded
    Call<String> addLayanan(@Path("id")String id,
                            @Field("nama_layanan")String nama_layanan);

    @POST("hewan/{id}")
    @FormUrlEncoded
    Call<String> addHewan(@Path("id")String id,
                          @Field("id_customer")String id_customer,
                          @Field("nama_hewan")String nama_hewan,
                          @Field("tgl_lahir_hewan")String tgl_lahir_hewan,
                          @Field("hwn_edited_by")String hwn_edited_by);

    @POST("jenishewan/{id}")
    @FormUrlEncoded
    Call<String> addJenisHewan(@Path("id")String id,
                               @Field("jenis")String jenis);

    @POST("ukuranhewan/{id}")
    @FormUrlEncoded
    Call<String> addUkuranHewan(@Path("id")String id,
                                @Field("ukuran")String ukuran);

    @POST("Supplier/{id}")
    @FormUrlEncoded
    Call<String> addSupplier(@Path("id")String id,
                             @Field("nama_supplier")String nama_customer,
                             @Field("alamat_supplier")String alamat_customer,
                             @Field("telp_supplier")String telp_customer);

//    =============================DELETE========================================================
    @DELETE("customer/{id}")
    Call<String> deleteCustomer(@Path("id") String id,
                                @Field("cust_deleted_by")String cust_deleted_by);
    @DELETE("produk/{id}")
    Call<customerDAO> deleteProduk(@Path("id")String id);
    @DELETE("layanan/{id}")
    Call<customerDAO> deleteLayanan(@Path("id")String id);
    @DELETE("hewan/{id}")
    Call<customerDAO> deleteHewan(@Path("id")String id,
                                  @Field("hwn_deleted_by")String hwn_deleted_by);
    @DELETE("jenishewan/{id}")
    Call<customerDAO> deleteJenisHewan(@Path("id")String id);
    @DELETE("ukuranhewan/{id}")
    Call<customerDAO> deleteUkuranHewan(@Path("id")String id);
    @DELETE("supplier/{id}")
    Call<customerDAO> deleteSupplier(@Path("id")String id);
}
