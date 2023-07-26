package dto;

import dto.AuthRequestDTO;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ProviderData {
    @DataProvider
    public Iterator<Object[]> userRegistrationDtoCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/regDTo.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{ AuthRequestDTO.builder()
                    .username(split[0])
                    .password(split[1])
                    .build()
            });
            line = reader.readLine();
        }
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> addNewContactDtoCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/addConractDTo.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{ ContactDTO.builder()
                    .name(split[0])
                    .lastName(split[1])
                    .email(split[2])
                    .phone(split[3])
                    .address(split[4])
                    .build()
            });
            line = reader.readLine();
        }
        return list.iterator();
    }
}
