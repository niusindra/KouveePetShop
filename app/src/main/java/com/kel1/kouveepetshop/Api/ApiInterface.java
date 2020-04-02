package com.kel1.kouveepetshop.Api;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.Respon.cudCustomer;
import com.kel1.kouveepetshop.Respon.readCustomer;

public interface ApiInterface {
//    =============================READ========================================================
    @GET("customer")
    Call<readCustomer> getCustomer();
    @GET("produk")
    Call<readCustomer> getProduk();
    @GET("layanan")
    Call<readCustomer> getLayanan();
    @GET("hewan")
    Call<readCustomer> getHewan();
    @GET("jenishewan")
    Call<readCustomer> getJenisHewan();
    @GET("ukuranhewan")
    Call<readCustomer> getUkuranHewan();
    @GET("supplier")
    Call<readCustomer> getSupplier();


//    =============================SEARCH========================================================
    @GET("customer/{id}")
    Call<customerDAO> getCustomer(@Path("id")int id);
    @GET("produk/{id}")
    Call<customerDAO> getProduk(@Path("id")int id);
    @GET("layanan/{id}")
    Call<customerDAO> getLayanan(@Path("id")int id);
    @GET("hewan/{id}")
    Call<customerDAO> getHewan(@Path("id")int id);
    @GET("jenishewan/{id}")
    Call<customerDAO> getJenisHewan(@Path("id")int id);
    @GET("ukuranhewan/{id}")
    Call<customerDAO> getUkuranHewan(@Path("id")int id);
    @GET("supplier/{id}")
    Call<customerDAO> getSupplier(@Path("id")int id);

//    =============================CREATE========================================================
    @POST("customer/")
    @FormUrlEncoded
    Call<cudCustomer> addCustomer(@Field("nama_customer")String nama_customer,
                                  @Field("alamat_customer")String alamat_customer,
                                  @Field("tgllahir_customer")String tgllahir_customer,
                                  @Field("telp_customer")String telp_customer,
                                  @Field("cust_created_by")String cust_created_by);

    @POST("produk/")
    @FormUrlEncoded
    Call<String> addProduk(@Field("id_supplier")int id_supplier,
                           @Field("nama_produk")String nama_produk,
                           @Field("foto_produk")String foto_produk,
                           @Field("harga_beli_produk")String harga_beli_produk,
                           @Field("harga_jual_produk")String harga_jual_produk,
                           @Field("stok")String stok,
                           @Field("min_stok")String min_stok);

    @POST("layanan/")
    @FormUrlEncoded
    Call<String> addLayanan(@Field("nama_layanan")String nama_layanan);

    @POST("hewan/")
    @FormUrlEncoded
    Call<String> addHewan(@Field("id_customer")int id_customer,
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
    Call<cudCustomer> editCustomer(@Path("id")int id,
                              @Field("nama_customer")String nama_customer,
                              @Field("alamat_customer")String alamat_customer,
                              @Field("tgllahir_customer")String tgllahir_customer,
                              @Field("telp_customer")String telp_customer,
                              @Field("cust_edited_by")String cust_edited_by);

    @POST("produk/{id}")
    @FormUrlEncoded
    Call<String> addProduk(@Path("id")int id,
                           @Field("id_supplier")int id_supplier,
                           @Field("nama_produk")String nama_produk,
                           @Field("foto_produk")String foto_produk,
                           @Field("harga_beli_produk")int harga_beli_produk,
                           @Field("harga_jual_produk")int harga_jual_produk,
                           @Field("stok")int stok,
                           @Field("min_stok")int min_stok);

    @POST("layanan/{id}")
    @FormUrlEncoded
    Call<String> addLayanan(@Path("id")int id,
                            @Field("nama_layanan")String nama_layanan);

    @POST("hewan/{id}")
    @FormUrlEncoded
    Call<String> addHewan(@Path("id")int id,
                          @Field("id_customer")int id_customer,
                          @Field("nama_hewan")String nama_hewan,
                          @Field("tgl_lahir_hewan")String tgl_lahir_hewan,
                          @Field("hwn_edited_by")String hwn_edited_by);

    @POST("jenishewan/{id}")
    @FormUrlEncoded
    Call<String> addJenisHewan(@Path("id")int id,
                               @Field("jenis")String jenis);

    @POST("ukuranhewan/{id}")
    @FormUrlEncoded
    Call<String> addUkuranHewan(@Path("id")int id,
                                @Field("ukuran")String ukuran);

    @POST("Supplier/{id}")
    @FormUrlEncoded
    Call<String> addSupplier(@Path("id")int id,
                             @Field("nama_supplier")String nama_customer,
                             @Field("alamat_supplier")String alamat_customer,
                             @Field("telp_supplier")String telp_customer);

//    =============================DELETE========================================================
    @POST("customer/delete/{id}")
    @FormUrlEncoded
    Call<cudCustomer> deleteCustomer(@Path("id") int id,
                                @Field("cust_deleted_by")String cust_deleted_by);
    @POST("produk/delete/{id}")
    @FormUrlEncoded
    Call<customerDAO> deleteProduk(@Path("id")int id);
    @POST("layanan/delete/{id}")
    @FormUrlEncoded
    Call<customerDAO> deleteLayanan(@Path("id")int id);
    @POST("hewan/delete/{id}")
    @FormUrlEncoded
    Call<customerDAO> deleteHewan(@Path("id")int id,
                                  @Field("hwn_deleted_by")String hwn_deleted_by);
    @POST("jenishewan/delete/{id}")
    @FormUrlEncoded
    Call<customerDAO> deleteJenisHewan(@Path("id")int id);
    @POST("ukuranhewan/delete/{id}")
    @FormUrlEncoded
    Call<customerDAO> deleteUkuranHewan(@Path("id")int id);
    @POST("supplier/delete/{id}")
    @FormUrlEncoded
    Call<customerDAO> deleteSupplier(@Path("id")int id);
}
