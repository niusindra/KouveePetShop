package com.kel1.kouveepetshop.Api;

import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.Respon.cudCustomer;
import com.kel1.kouveepetshop.Respon.readCustomer;
import com.kel1.kouveepetshop.Respon.readHewan;
import com.kel1.kouveepetshop.Respon.readJenisHewan;
import com.kel1.kouveepetshop.Respon.readLayanan;
import com.kel1.kouveepetshop.Respon.readProduk;
import com.kel1.kouveepetshop.Respon.readSupplier;
import com.kel1.kouveepetshop.Respon.readUkuranHewan;
import com.kel1.kouveepetshop.Respon.verifyPegawai;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
//    =============================READ========================================================
    @GET("customer")
    Call<readCustomer> getCustomer();
    @GET("produk")
    Call<readProduk> getProduk();
    @GET("layanan")
    Call<readLayanan> getLayanan();
    @GET("hewan")
    Call<readHewan> getHewan();
    @GET("jenishewan")
    Call<readJenisHewan> getJenisHewan();
    @GET("ukuranhewan")
    Call<readUkuranHewan> getUkuranHewan();
    @GET("supplier")
    Call<readSupplier> getSupplier();

    @GET("customer/log")
    Call<readCustomer> getCustomerLog();
    @GET("produk/log")
    Call<readProduk> getProdukLog();
    @GET("layanan/log")
    Call<readLayanan> getLayananLog();
    @GET("hewan/log")
    Call<readHewan> getHewanLog();
    @GET("jenishewan/log")
    Call<readJenisHewan> getJenisHewanLog();
    @GET("ukuranhewan/log")
    Call<readUkuranHewan> getUkuranHewanLog();
    @GET("supplier/log")
    Call<readSupplier> getSupplierLog();

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
    @POST("pegawai/verify")
    @FormUrlEncoded
    Call<verifyPegawai> verifyPegawai(@Field("username")String username,
                                  @Field("password")String password);
    @POST("customer/")
    @FormUrlEncoded
    Call<cudCustomer> addCustomer(@Field("nama_customer")String nama_customer,
                                  @Field("alamat_customer")String alamat_customer,
                                  @Field("tgllahir_customer")String tgllahir_customer,
                                  @Field("telp_customer")String telp_customer,
                                  @Field("cust_created_by")String cust_created_by);

    @POST("produk/")
    @FormUrlEncoded
    Call<cudCustomer> addProduk(@Field("id_supplier")int id_supplier,
                              @Field("nama_produk")String nama_produk,
                              @Field("foto_produk")String foto_produk,
                              @Field("harga_beli_produk")String harga_beli_produk,
                              @Field("harga_jual_produk")String harga_jual_produk,
                              @Field("stok")String stok,
                              @Field("min_stok")String min_stok);

    @POST("layanan/")
    @FormUrlEncoded
    Call<cudCustomer> addLayanan(@Field("nama_layanan")String nama_layanan);

    @POST("hewan/")
    @FormUrlEncoded
    Call<cudCustomer> addHewan(@Field("id_customer")int id_customer,
                            @Field("nama_hewan")String nama_hewan,
                            @Field("tgl_lahir_hewan")String tgl_lahir_hewan,
                            @Field("hwn_created_by")String hwn_created_by);

    @POST("jenishewan/")
    @FormUrlEncoded
    Call<cudCustomer> addJenisHewan(@Field("jenis")String jenis);

    @POST("ukuranhewan/")
    @FormUrlEncoded
    Call<cudCustomer> addUkuranHewan(@Field("ukuran")String ukuran);

    @POST("supplier/")
    @FormUrlEncoded
    Call<cudCustomer> addSupplier(@Field("nama_supplier")String nama_supplier,
                                  @Field("alamat_supplier")String alamat_supplier,
                                  @Field("telp_supplier")String telp_supplier);

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
    Call<cudCustomer> editProduk(@Path("id")int id,
                           @Field("id_supplier")int id_supplier,
                           @Field("nama_produk")String nama_produk,
                           @Field("foto_produk")String foto_produk,
                           @Field("harga_beli_produk")int harga_beli_produk,
                           @Field("harga_jual_produk")int harga_jual_produk,
                           @Field("stok")int stok,
                           @Field("min_stok")int min_stok);

    @POST("layanan/{id}")
    @FormUrlEncoded
    Call<cudCustomer> editLayanan(@Path("id")int id,
                                   @Field("nama_layanan")String nama_layanan);
    @POST("hewan/{id}")
    @FormUrlEncoded
    Call<cudCustomer> editHewan(@Path("id")int id,
                          @Field("id_customer")int id_customer,
                          @Field("nama_hewan")String nama_hewan,
                          @Field("tgl_lahir_hewan")String tgl_lahir_hewan,
                          @Field("hwn_edited_by")String hwn_edited_by);

    @POST("jenishewan/{id}")
    @FormUrlEncoded
    Call<cudCustomer> editJenisHewan(@Path("id")int id,
                               @Field("jenis")String jenis);

    @POST("ukuranhewan/{id}")
    @FormUrlEncoded
    Call<cudCustomer> editUkuranHewan(@Path("id")int id,
                                         @Field("ukuran")String ukuran);

    @POST("supplier/{id}")
    @FormUrlEncoded
    Call<cudCustomer> editSupplier(@Path("id")int id,
                             @Field("nama_supplier")String nama_supplier,
                             @Field("alamat_supplier")String alamat_supplier,
                             @Field("telp_supplier")String telp_supplier);

//    =============================DELETE========================================================
    @POST("customer/delete/{id}")
    @FormUrlEncoded
    Call<cudCustomer> deleteCustomer(@Path("id") int id,
                                @Field("cust_deleted_by")String cust_deleted_by);
    @POST("produk/delete/{id}")
    @FormUrlEncoded
    Call<cudCustomer> deleteProduk(@Path("id")int id);
    @POST("layanan/delete/{id}")
    @FormUrlEncoded
    Call<cudCustomer> deleteLayanan(@Path("id")int id);
    @POST("hewan/delete/{id}")
    @FormUrlEncoded
    Call<cudCustomer> deleteHewan(@Path("id")int id,
                                  @Field("hwn_deleted_by")String hwn_deleted_by);
    @POST("jenishewan/delete/{id}")
    @FormUrlEncoded
    Call<cudCustomer> deleteJenisHewan(@Path("id")int id);
    @POST("ukuranhewan/delete/{id}")
    @FormUrlEncoded
    Call<cudCustomer> deleteUkuranHewan(@Path("id")int id);
    @POST("supplier/delete/{id}")
    @FormUrlEncoded
    Call<cudCustomer> deleteSupplier(@Path("id")int id);
}
