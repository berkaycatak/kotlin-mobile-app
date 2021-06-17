import com.google.gson.annotations.SerializedName

data class AdsModelItem(
    @SerializedName("baslik")
    val baslik: String,
    @SerializedName("detay1")
    val detay1: String,
    @SerializedName("detay2")
    val detay2: String,
    @SerializedName("detay3")
    val detay3: String,
    @SerializedName("detay4")
    val detay4: String,
    @SerializedName("icerik")
    val icerik: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("kategoriad")
    val kategoriad: String,
    @SerializedName("kategoriid")
    val kategoriid: String,
    @SerializedName("konum")
    val konum: String,
    @SerializedName("kullaniciid")
    val kullaniciid: String,
    @SerializedName("kullanicikadi")
    val kullanicikadi: String,
    @SerializedName("kullanicipp")
    val kullanicipp: String,
    @SerializedName("kullanicitamad")
    val kullanicitamad: String,
    @SerializedName("postres")
    val postres: String,
    @SerializedName("tarih")
    val tarih: String
)