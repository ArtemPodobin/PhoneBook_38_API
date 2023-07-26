package okhttp;

import com.google.gson.Gson;
import dto.*;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContact {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test(dataProvider = "addNewContactDtoCSV", dataProviderClass = ProviderData.class)
    public void addNewContact(ContactDTO contact) throws IOException {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoi77u_cm9iZXJ0cGF0N0BtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjkxMDA4MzI5LCJpYXQiOjE2OTA0MDgzMjl9.woXE7LsUAoSUojpkv6osbWYIxjlqaYS1w5u1QDAXoTE";

        RequestBody requestBody = RequestBody.create(gson.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()){

            ContactResponseDTO responseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
            System.out.println(responseDTO.getMassages());
            System.out.println("Created contact with email: " + contact.getEmail());
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

    @Test(dataProvider = "addNewContactDtoCSV", dataProviderClass = ProviderData.class)
    public void addNewContactUnauthorized(ContactDTO contact) throws IOException {
        String token = "bzdfb";

        RequestBody requestBody = RequestBody.create(gson.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()){

            ContactResponseDTO responseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
            System.out.println(responseDTO.getMassages());
            System.out.println("Created contact with email: " + contact.getEmail());
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
