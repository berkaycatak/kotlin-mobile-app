import com.google.gson.annotations.SerializedName

data class UsersModelItem(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("pp")
    val pp: String,
    @SerializedName("username")
    val username: String
)