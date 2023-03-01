package cloudcode.profilebuilder.web;

import cloudcode.profilebuilder.interfaces.DatastoreService;
import cloudcode.profilebuilder.model.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** Defines a controller to handle HTTP requests */
@Controller
@RequestMapping(value = "/")
public class ProfileBuilderController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileBuilderController.class);
    private DatastoreService dataService;

    public ProfileBuilderController(DatastoreService datastoreService){

        dataService = datastoreService;
    }

    @GetMapping("/{profileId}/attributes.html")
    public String getAttributes(@PathVariable("profileId") int profileId, ModelMap map) {
        try {
            List<Attribute> list = dataService.getAttributes(profileId);
            map.addAttribute("list", list);
            map.addAttribute("profileid", profileId);

        } catch(Exception ex){
            logger.error("Whoopsie" + ex);
            map.addAttribute("message", ex.getMessage());
        }

        return "attributes";
    }

    @GetMapping("/{profileId}/ship.html")
    public String getShip(@PathVariable("profileId") int profileId, ModelMap map) {
        try {
            map.addAttribute("profileid", profileId);
        } catch(Exception ex){
            logger.error("Whoopsie" + ex);
            map.addAttribute("message", ex.getMessage());
        }

        return "ship";
    }

    @GetMapping("/{profileId}/team/{id}.html")
    public String viewTeam(@PathVariable("profileId") int profileId, @PathVariable("id") int id, ModelMap map) {
        try {
            Team team = dataService.getTeam(profileId, id);
            map.addAttribute("team", team);
            map.addAttribute("profileid", profileId);
        } catch(Exception ex){
            logger.error("Whoopsie" + ex);
            map.addAttribute("message", ex.getMessage());
        }

        return "/fragments/team";
    }

    @RequestMapping(value="/{profileId}/team-sentiment/{id}.json", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String getTeamSentiment(@PathVariable("profileId") int profileId, @PathVariable("id") int id) {
        try {
            SentimentChart chart = dataService.getTeamSentiment(profileId, id);
            Gson gson = new Gson();
            return gson.toJson(chart);
        } catch(Exception ex){
            logger.error("Whoopsie" + ex);
            // Implement error handling here
        }
        return "";
    }

    @GetMapping("/{profileId}/demographics.html")
    public String viewDemographics(@PathVariable("profileId") int profileId, ModelMap map) {
        try {
            map.addAttribute("profileid", profileId);
        } catch(Exception ex){
            logger.error("Whoopsie" + ex);
            map.addAttribute("message", ex.getMessage());
        }

        return "/fragments/demographics";
    }

    @RequestMapping(value="/{profileId}/demographic-data.json", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String getDemographicData(@PathVariable("profileId") int profileId) {
        try {
            Demographics demographics = dataService.getDemographics(profileId);
            Gson gson = new Gson();
            return gson.toJson(demographics);
        } catch(Exception ex){
            logger.error("Whoopsie" + ex);
            // Implement error handling here
        }
        return "";
    }

    @GetMapping("/credits.html")
    public String viewCredits(ModelMap map) {
        return "/credits";
    }
}
