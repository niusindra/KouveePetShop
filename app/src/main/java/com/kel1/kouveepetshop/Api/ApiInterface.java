package com.kel1.kouveepetshop.Api;

import com.kel1.kouveepetshop.DAO.customerDAO;
import com.kel1.kouveepetshop.DAO.hargalayananDAO;
import com.kel1.kouveepetshop.Respon.cudDataMaster;
import com.kel1.kouveepetshop.Respon.readCustomer;
import com.kel1.kouveepetshop.Respon.readDetailPengadaan;
import com.kel1.kouveepetshop.Respon.readDetailTransLay;
import com.kel1.kouveepetshop.Respon.readDetailTransPro;
import com.kel1.kouveepetshop.Respon.readHargaLayanan;
import com.kel1.kouveepetshop.Respon.readHewan;
import com.kel1.kouveepetshop.Respon.readJenisHewan;
import com.kel1.kouveepetshop.Respon.readLayanan;
import com.kel1.kouveepetshop.Respon.readPengadaan;
import com.kel1.kouveepetshop.Respon.readProduk;
import com.kel1.kouveepetshop.Respon.readSupplier;
import com.kel1.kouveepetshop.Respon.readTransLay;
import com.kel1.kouveepetshop.Respon.readTransPro;
import com.kel1.kouveepetshop.Respon.readUkuranHewan;
import com.kel1.kouveepetshop.Respon.verifyPegawai;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {
//    =============================READ========================================================
    @GET("customer")
    Call<readCustomer> getCustomer();
    @GET("produk")
    Call<readProduk> getProduk();
    @GET("layanan")
    Call<readLayanan> getLayanan();
    @GET("hargalayanan")
    Call<readHargaLayanan> getHargaLayanan();
    @GET("hewan")
    Call<readHewan> getHewan();
    @GET("jenishewan")
    Call<readJenisHewan> getJenisHewan();
    @GET("ukuranhewan")
    Call<readUkuranHewan> getUkuranHewan();
    @GET("supplier")
    Call<readSupplier> getSupplier();
    @GET("pengadaan")
    Call<readPengadaan> getPengadaan();
    @GET("transaksilayanan/belumselesai")
    Call<readTransLay> getTransLayBelumSelesai();
    @GET("transaksilayanan/selesai")
    Call<readTransLay> getTransLaySelesai();
    @GET("transaksiproduk")
    Call<readTransPro> getTransPro();
    @GET("detailpengadaan/{id}")
    Call<readDetailPengadaan> getDetailPengadaan(@Path("id")String id);
    @GET("detailtl/{id}")
    Call<readDetailTransLay> getDetailLayanan(@Path("id")String id);
    @GET("detailtp/{id}")
    Call<readDetailTransPro> getDetailProduk(@Path("id")String id);
    @GET("produk/bysupplier/{id}")
    Call<readProduk> getProdukbySupplier(@Path("id")int id);

    @GET("customer/log")
    Call<readCustomer> getCustomerLog();
    @GET("produk/log")
    Call<readProduk> getProdukLog();
    @GET("layanan/log")
    Call<readLayanan> getLayananLog();
    @GET("hargalayanan/log")
    Call<readHargaLayanan> getHargaLayananLog();
    @GET("hewan/log")
    Call<readHewan> getHewanLog();
    @GET("jenishewan/log")
    Call<readJenisHewan> getJenisHewanLog();
    @GET("ukuranhewan/log")
    Call<readUkuranHewan> getUkuranHewanLog();
    @GET("supplier/log")
    Call<readSupplier> getSupplierLog();
    @GET("pengadaan/log")
    Call<readPengadaan> getPengadaanLog();
    @GET("transaksilayanan/log")
    Call<readTransLay> getTransLayLog();
    @GET("transaksiproduk/log")
    Call<readTransPro> getTransProLog();


    @GET("transaksilayanan/konfirmasiselesai/{id}")
    Call<cudDataMaster> konfirmasiSelesai(@Path("id")String id);

//    =============================SEARCH========================================================
    @GET("customer/{id}")
    Call<customerDAO> getCustomer(@Path("id")int id);
    @GET("produk/{id}")
    Call<customerDAO> getProduk(@Path("id")int id);
    @GET("layanan/{id}")
    Call<customerDAO> getLayanan(@Path("id")int id);
    @GET("harga_layanan/{id}")
    Call<hargalayananDAO> getHargaLayanan(@Path("id")int id);
    @GET("hewan/{id}")
    Call<customerDAO> getHewan(@Path("id")int id);
    @GET("jenishewan/{id}")
    Call<customerDAO> getJenisHewan(@Path("id")int id);
    @GET("ukuranhewan/{id}")
    Call<customerDAO> getUkuranHewan(@Path("id")int id);
    @GET("supplier/{id}")
    Call<customerDAO> getSupplier(@Path("id")int id);
    @GET("transaksiproduk/{id}")
    Call<readTransPro> getTransPro(@Path("id")String id);
    @GET("transaksilayanan/{id}")
    Call<readTransLay> getTransLay(@Path("id")String id);

//    =============================CREATE========================================================
    @POST("pegawai/verify")
    @FormUrlEncoded
    Call<verifyPegawai> verifyPegawai(@Field("username")String username,
                                  @Field("password")String password);
    @POST("customer/")
    @FormUrlEncoded
    Call<cudDataMaster> addCustomer(@Field("nama_customer")String nama_customer,
                                    @Field("alamat_customer")String alamat_customer,
                                    @Field("tgllahir_customer")String tgllahir_customer,
                                    @Field("telp_customer")String telp_customer,
                                    @Field("cust_created_by")String cust_created_by);

    @POST("produk/")
    @Multipart
    Call<cudDataMaster> addProduk(@Part ("id_supplier") Integer id_supplier,
                                  @Part ("nama_produk") RequestBody nama_produk,
                                  @Part MultipartBody.Part foto_produk,
                                  @Part ("harga_beli_produk") Integer harga_beli_produk,
                                  @Part ("harga_jual_produk") Integer harga_jual_produk,
                                  @Part ("stok") Integer stok,
                                  @Part ("min_stok") Integer min_stok);
    @POST("hargalayanan/")
    @FormUrlEncoded
    Call<cudDataMaster> addHargaLayanan(@Field ("id_layanan") int id_layanan,
                                  @Field ("id_jenis") int id_jenis,
                                  @Field ("id_ukuran") int id_ukuran,
                                  @Field ("harga_layanan") int harga_layanan);


    @POST("layanan/")
    @FormUrlEncoded
    Call<cudDataMaster> addLayanan(@Field("nama_layanan")String nama_layanan);

    @POST("hewan/")
    @FormUrlEncoded
    Call<cudDataMaster> addHewan(@Field("id_customer")int id_customer,
                                 @Field("nama_hewan")String nama_hewan,
                                 @Field("tgl_lahir_hewan")String tgl_lahir_hewan,
                                 @Field("hwn_created_by")String hwn_created_by);

    @POST("jenishewan/")
    @FormUrlEncoded
    Call<cudDataMaster> addJenisHewan(@Field("jenis")String jenis);

    @POST("ukuranhewan/")
    @FormUrlEncoded
    Call<cudDataMaster> addUkuranHewan(@Field("ukuran")String ukuran);

    @POST("supplier/")
    @FormUrlEncoded
    Call<cudDataMaster> addSupplier(@Field("nama_supplier")String nama_supplier,
                                    @Field("alamat_supplier")String alamat_supplier,
                                    @Field("telp_supplier")String telp_supplier);

    @POST("pengadaan/")
    @FormUrlEncoded
    Call<cudDataMaster> addPengadaan(@Field("status_pengadaan")String status_pengadaan);

    @POST("transaksilayanan/")
    @FormUrlEncoded
    Call<cudDataMaster> addTransLay(@Field("id_pegawai")int id_pegawai,
                                    @Field("id_hewan")int id_hewan,
                                    @Field("status_layanan")String status_layanan,
                                    @Field("translay_created_by")String translay_created_by);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @POST("transaksiproduk/")
    @FormUrlEncoded
    Call<cudDataMaster> addTransPro(@Field("id_pegawai")int id_pegawai,
                                    @Field("id_hewan")int id_hewan,
                                    @Field("status_penjualan_produk")String status_penjualan_produk,
                                    @Field("transproduk_created_by")String transproduk_created_by);

    @POST("detailpengadaan/")
    @FormUrlEncoded
    Call<cudDataMaster> addDetailPengadaan(@Field("id_pengadaan")String id_pengadaan,
                                           @Field("id_produk")int id_produk,
                                           @Field("satuan")String satuan,
                                           @Field("jml_pengadaan_produk")int jml_pengadaan_produk);

    @POST("detailtp/")
    @FormUrlEncoded
    Call<cudDataMaster> addDetailTransPro(@Field("id_trans_produk")String id_trans_produk,
                                          @Field("id_produk")int id_produk,
                                          @Field("jumlah_beli_produk")int jumlah_beli_produk);

    @POST("detailtl/")
    @FormUrlEncoded
    Call<cudDataMaster> addDetailTransLay(@Field("id_trans_layanan")String id_trans_layanan,
                                          @Field("id_harga_layanan")int id_harga_layanan,
                                          @Field("jumlah_beli_layanan")int jumlah_beli_layanan);

    @POST("device/")
    @FormUrlEncoded
    Call<cudDataMaster> addDevice(@Field("role")String role,
                                  @Field("token")String token);

//    =============================UPDATE========================================================
    @POST("customer/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editCustomer(@Path("id")int id,
                                     @Field("id_layanan")String nama_customer,
                                     @Field("alamat_customer")String alamat_customer,
                                     @Field("tgllahir_customer")String tgllahir_customer,
                                     @Field("telp_customer")String telp_customer,
                                     @Field("cust_edited_by")String cust_edited_by);
    @POST("hargalayanan/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editHargaLayanan(@Path("id")int id,
                                     @Field("id_layanan")int id_layanan,
                                     @Field("id_jenis")int id_jenis,
                                     @Field("id_ukuran")int id_ukuran,
                                     @Field("harga_layanan")int harga_layanan);

    @POST("produk/{id}")
    @Multipart
    Call<cudDataMaster> editProduk(@Path ("id") Integer id,
                                   @Part ("id_supplier") Integer id_supplier,
                                  @Part ("nama_produk") RequestBody nama_produk,
                                  @Part MultipartBody.Part foto_produk,
                                  @Part ("harga_beli_produk") Integer harga_beli_produk,
                                  @Part ("harga_jual_produk") Integer harga_jual_produk,
                                  @Part ("stok") Integer stok,
                                  @Part ("min_stok") Integer min_stok);

    @POST("produk/pengadaanproduk/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> pengadaanProduk(@Path ("id") Integer id,
                                   @Field ("stok") Integer stok);

    @POST("layanan/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editLayanan(@Path("id")int id,
                                    @Field("nama_layanan")String nama_layanan);
    @POST("hewan/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editHewan(@Path("id")int id,
                                  @Field("id_customer")int id_customer,
                                  @Field("nama_hewan")String nama_hewan,
                                  @Field("tgl_lahir_hewan")String tgl_lahir_hewan,
                                  @Field("hwn_edited_by")String hwn_edited_by);

    @POST("jenishewan/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editJenisHewan(@Path("id")int id,
                                       @Field("jenis")String jenis);

    @POST("ukuranhewan/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editUkuranHewan(@Path("id")int id,
                                        @Field("ukuran")String ukuran);

    @POST("supplier/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editSupplier(@Path("id")int id,
                                     @Field("nama_supplier")String nama_supplier,
                                     @Field("alamat_supplier")String alamat_supplier,
                                     @Field("telp_supplier")String telp_supplier);

    @POST("pengadaan/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editPengadaan(@Path("id")String id,
                                      @Field("status_pengadaan")String status_pengadaan);

    @POST("transaksiproduk/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editTransPro(@Path("id")String id,
                                     @Field("id_hewan")int id_hewan,
                                     @Field("status_penjualan_produk")String status_penjualan_produk,
                                     @Field("transproduk_edited_by")String transproduk_edited_by);

    @POST("detailpengadaan/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editDetailPengadaan(@Path("id")int id,
                                            @Field("id_produk")int id_produk,
                                            @Field("satuan")String satuan,
                                           @Field("jml_pengadaan_produk")int jml_pengadaan_produk);

    @POST("detailtp/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> editDetailTransPro(@Path("id")int id,
                                           @Field("id_produk")int id_produk,
                                           @Field("jumlah_beli_produk")int jumlah_beli_produk);

//    =============================DELETE========================================================
    @POST("customer/delete/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> deleteCustomer(@Path("id") int id,
                                       @Field("cust_deleted_by")String cust_deleted_by);
    @POST("produk/delete/{id}")
    Call<cudDataMaster> deleteProduk(@Path("id")int id);
    @POST("layanan/delete/{id}")
    Call<cudDataMaster> deleteLayanan(@Path("id")int id);
    @POST("hargalayanan/delete/{id}")
    Call<cudDataMaster> deleteHargaLayanan(@Path("id")int id);
    @POST("hewan/delete/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> deleteHewan(@Path("id")int id,
                                    @Field("hwn_deleted_by")String hwn_deleted_by);
    @POST("jenishewan/delete/{id}")
    Call<cudDataMaster> deleteJenisHewan(@Path("id")int id);
    @POST("ukuranhewan/delete/{id}")
    Call<cudDataMaster> deleteUkuranHewan(@Path("id")int id);
    @POST("supplier/delete/{id}")
    Call<cudDataMaster> deleteSupplier(@Path("id")int id);
    @POST("pengadaan/delete/{id}")
    Call<cudDataMaster> deletePengadaan(@Path("id")String id);
    @POST("transaksiproduk/delete/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> deleteTransPro(@Path("id")String id,
                                       @Field("transproduk_deleted_by")String transproduk_deleted_by);
    @POST("transaksilayanan/delete/{id}")
    @FormUrlEncoded
    Call<cudDataMaster> deleteTransLay(@Path("id")String id,
                                       @Field("translay_deleted_by")String translay_deleted_by);
    @DELETE("detailpengadaan/{id}")
    Call<cudDataMaster> deleteDetailPengadaan(@Path("id")int id);
    @DELETE("detailtp/{id}")
    Call<cudDataMaster> deleteDetailPTransPro(@Path("id")int id);
    @DELETE("detailtl/{id}")
    Call<cudDataMaster> deleteDetailPTransLay(@Path("id")int id);
}
