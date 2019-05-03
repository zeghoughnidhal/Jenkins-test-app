package com.cloudwatt.example.api.rest;

import com.cloudwatt.example.domain.jenkins.HudsonFolder;
import com.cloudwatt.example.domain.jenkins.HudsonJob;
import com.cloudwatt.example.service.FolderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/api")
@Api(tags = {"HudsonFolder"})
public class FolderController extends AbstractRestHandler {

    @Autowired
    private FolderService folderService;

    /*-------------------------------------------------------------------------------------*/
    // Folders
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/folders/{projectName}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    HudsonFolder getFolderLevel1(
            @PathVariable("projectName") String projectName,
            @RequestParam(value = "depth", required = false, defaultValue = "1") Integer depth) {

        return this.folderService.getFolder(getJenkinsFolderPath(projectName), depth);
    }

    @RequestMapping(value = "/folders/{projectName}/{projectName2}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    HudsonFolder getFolderLevel2(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2,
            @RequestParam(value = "depth", required = false, defaultValue = "1") Integer depth) {
        return this.folderService.getFolder(getJenkinsFolderPath(projectName, projectName2), depth);
    }


    @RequestMapping(value = "/folders/{projectName}/{projectName2}/{projectName3}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    HudsonFolder getFolderLevel3(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2,
            @PathVariable("projectName3") String projectName3,
            @RequestParam(value = "depth", required = false, defaultValue = "1") Integer depth) {

        return this.folderService.getFolder(getJenkinsFolderPath(projectName, projectName2, projectName3), depth);
    }

    /*-------------------------------------------------------------------------------------*/
    // Jobs
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/jobs/{projectName}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of  hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<HudsonJob> getJobsLevel1(
            @PathVariable("projectName") String projectName) throws ExecutionException {
        return this.folderService.getJobsFrom(getJenkinsFolderPath(projectName));
    }


    @RequestMapping(value = "/jobs/{projectName}/{projectName2}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of  hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<HudsonJob> getJobsLevel2(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2) throws ExecutionException {
        return this.folderService.getJobsFrom(getJenkinsFolderPath(projectName, projectName2));
    }


    @RequestMapping(value = "/jobs/{projectName}/{projectName2}/{projectName3}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<HudsonJob> getJobsLevel3(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2,
            @PathVariable("projectName3") String projectName3) throws ExecutionException {

        return this.folderService.getJobsFrom(getJenkinsFolderPath(projectName, projectName2, projectName3));
    }

    /*-------------------------------------------------------------------------------------*/
    // Folders (for view)
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/forView/folders", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, Object> getFolderForViewRoot() throws ExecutionException {
        return this.folderService.getFolderForView("");
    }

    @RequestMapping(value = "/forView/folders/{projectName}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, Object> getFolderForViewLevel1(
            @PathVariable("projectName") String projectName) throws ExecutionException {

        return this.folderService.getFolderForView(getJenkinsFolderPath(projectName));
    }

    @RequestMapping(value = "/forView/folders/{projectName}/{projectName2}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, Object> getFolderForViewLevel2(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2) throws ExecutionException {
        return this.folderService.getFolderForView(getJenkinsFolderPath(projectName, projectName2));
    }


    @RequestMapping(value = "/forView/folders/{projectName}/{projectName2}/{projectName3}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, Object> getFolderForViewLevel3(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2,
            @PathVariable("projectName3") String projectName3) throws ExecutionException {
        return this.folderService.getFolderForView(getJenkinsFolderPath(projectName, projectName2, projectName3));
    }

    /*-------------------------------------------------------------------------------------*/
    // Methods
    /*-------------------------------------------------------------------------------------*/

    private String getJenkinsFolderPath(String... folders) {
        String path = "";
        for (String folder : folders) {
            path += "/job/" + folder;
        }
        return path;
    }
}
