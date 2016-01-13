package org.arthan.desktop.tetris;

import com.google.gson.Gson;
import spark.ResponseTransformer;

import java.util.Map;

import static spark.Spark.get;

/**
 * Created by ashamsiev on 13.01.2016
 */
public class SparkService {

    private static class GsonTransformer implements ResponseTransformer {

        private Gson gson = new Gson();

        @Override
        public String render(Object model) throws Exception {
            return gson.toJson(model);
        }
    }

    public static void main(String[] args) {

        get("/one", ((request, response) -> {
            Person person = new Person();
            person.setName("Акакий");
            person.setLastName("Шинель");
            person.setZip(332234);

            String name = request.params("name");
            String number = request.queryParams("number");

            return person;
        }), new GsonTransformer());
    }

    private static class Person {
        private String name;
        private String lastName;
        private int zip;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getZip() {
            return zip;
        }

        public void setZip(int zip) {
            this.zip = zip;
        }
    }
}
