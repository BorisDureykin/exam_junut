package objects.steps.rick_and_morty_api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import objects.steps.request_respone_api.ResponseAllTests;
import org.json.JSONObject;

public class GetEpisode extends ResponseAllTests {

    @Step("Получение данных эпизода с id : \"{id}\"")
    public static String getEpisode(String id, RequestSpecification request) {

        String endpoint = "/episode/" + id;

        String pathSchema = "rick_and_morti/schemaGetEpisode.json";

        Response response = responseGet(request, null, endpoint, "GET", "200", pathSchema);

        String responseBody = response.getBody().asString();

        JSONObject jsonObject = new JSONObject(responseBody);

        String lastEpisodeUrl = jsonObject.getJSONArray("characters").getString(jsonObject.getJSONArray("characters").length() - 1);

        return lastEpisodeUrl.substring(lastEpisodeUrl.lastIndexOf("/") + 1);
    }
}