import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cities", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/cities.vtl");
      model.put("cities", City.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cities/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/city-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cities", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/cities.vtl");

      String cityName = request.queryParams("name");
      City city = new City(cityName);
      model.put("cities", City.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cities/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/city.vtl");
      City city = City.find(Integer.parseInt(request.params(":id")));
      model.put("city", city);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cities/:id/jobs/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/job-form.vtl");
      City city = City.find(Integer.parseInt(request.params(":id")));
      model.put("city", city);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cities/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/city.vtl");
      City city = City.find(Integer.parseInt(request.queryParams("cityId")));
      model.put("city", city);
      String jobName = request.queryParams("name");
      Job job = new Job(jobName);
      city.addJob(job);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
