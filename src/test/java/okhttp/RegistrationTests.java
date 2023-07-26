package okhttp;
import dto.ProviderData;
import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test(dataProvider = "userRegistrationDtoCSV", dataProviderClass = ProviderData.class)
    public void registration(AuthRequestDTO requestDTO) throws IOException {

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/registration/usernamepassword")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(String.format("Credentials: login: %s, password: %s", requestDTO.getUsername(), requestDTO.getPassword()));

        if(response.isSuccessful()){

            AuthResponseDTO responseDTO = gson.fromJson(response.body().string(), AuthResponseDTO.class);
            System.out.println(responseDTO.getToken());
            System.out.println("Response code is: " + response.code());
            Assert.assertTrue(response.isSuccessful());

        }else{
            System.out.println("Response code is: " + response.code());
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);

            System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
            Assert.assertTrue(!response.isSuccessful());
        }
        System.out.println("================================================");
    }


}
