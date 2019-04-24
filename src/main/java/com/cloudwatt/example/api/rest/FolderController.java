package com.cloudwatt.example.api.rest;

import com.cloudwatt.example.domain.jenkins.Folder;
import com.cloudwatt.example.service.FolderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/api/v1/folders")
@Api(tags = {"Folder"})
public class FolderController extends AbstractRestHandler {

    @Autowired
    public FolderService folderService;

    public String getJenkinsFolderPath(String... folders) {
        String path = "";
        for (String folder : folders) {
            path += "/job/" + folder;
        }
        return path;
    }


//    @RequestMapping(
//            value = "/{projectName}",
//            method = RequestMethod.GET,
//            produces = {"application/json"}
//    )
//    @ResponseStatus(HttpStatus.OK)
//    public Folder getFolder(
//            @PathVariable("projectName") String projectName,
//            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
//    ) {
//        return this.folderService.getFolder(projectName);
//    }

    @RequestMapping(value = "/{projectName}",
            method = RequestMethod.GET,
            produces = {"application/json"})
/*    @RequestMapping(value = "/{project1Name}/{project2Name}/{project3Name}",
            method = RequestMethod.GET,
            produces = {"application/json"})*/
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
        // Folder getFolderByName(@PathVariable("projectName") String projectName, @PathVariable("projectName") String projectName ,@PathVariable("projectName") String projectName) {
    Folder getFolderLevel1(@PathVariable("projectName") String projectName) {
        // TODO découper l'url des jobs en 3 parties projectName -> project1Name (Functional-tests) / project2Name (Tempest) / project3Name (
        Folder folder = this.folderService.getFolder(getJenkinsFolderPath(projectName));
        return folder;
    }

    @RequestMapping(value = "/{projectName}/{projectName2}",
            method = RequestMethod.GET,
            produces = {"application/json"})
/*    @RequestMapping(value = "/{project1Name}/{project2Name}/{project3Name}",
            method = RequestMethod.GET,
            produces = {"application/json"})*/
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
        // Folder getFolderByName(@PathVariable("projectName") String projectName, @PathVariable("projectName") String projectName ,@PathVariable("projectName") String projectName) {
    Folder getFolderLevel2(@PathVariable("projectName") String projectName, @PathVariable("projectName2") String projectName2) {
        // TODO découper l'url des jobs en 3 parties projectName -> project1Name (Functional-tests) / project2Name (Tempest) / project3Name (
        Folder folder = this.folderService.getFolder(getJenkinsFolderPath(projectName, projectName2));
        return folder;
    }

    @RequestMapping(value = "/{projectName}/{projectName2}/{projectName3}",
            method = RequestMethod.GET,
            produces = {"application/json"})
/*    @RequestMapping(value = "/{project1Name}/{project2Name}/{project3Name}",
            method = RequestMethod.GET,
            produces = {"application/json"})*/
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
        // Folder getFolderByName(@PathVariable("projectName") String projectName, @PathVariable("projectName") String projectName ,@PathVariable("projectName") String projectName) {
    Folder getFolderLevel3(@PathVariable("projectName") String projectName, @PathVariable("projectName2") String projectName2, @PathVariable("projectName3") String projectName3) {
        // TODO découper l'url des jobs en 3 parties projectName -> project1Name (Functional-tests) / project2Name (Tempest) / project3Name (
        Folder folder = this.folderService.getFolder(getJenkinsFolderPath(projectName, projectName2, projectName3));
        return folder;
    }
}
