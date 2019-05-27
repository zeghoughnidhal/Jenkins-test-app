package com.cloudwatt.example.api.rest;

import com.cloudwatt.example.domain.jenkins.HudsonFolder;
import com.cloudwatt.example.domain.jenkins.HudsonJob;
import com.cloudwatt.example.service.FolderService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/api")
@Api()
public class FolderController extends AbstractRestHandler {

    @Autowired
    private FolderService folderService;

    /*-------------------------------------------------------------------------------------*/
    // /folders
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
    // /jobs
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/jobs/{projectName}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of  hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<HudsonJob> getJobsLevel1(
            @PathVariable("projectName") String projectName) throws ExecutionException {
        return this.folderService.getJobsRecursiveModeFrom(getJenkinsFolderPath(projectName));
    }


    @RequestMapping(value = "/jobs/{projectName}/{projectName2}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of  hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<HudsonJob> getJobsLevel2(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2) throws ExecutionException {
        return this.folderService.getJobsRecursiveModeFrom(getJenkinsFolderPath(projectName, projectName2));
    }


    @RequestMapping(value = "/jobs/{projectName}/{projectName2}/{projectName3}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<HudsonJob> getJobsLevel3(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2,
            @PathVariable("projectName3") String projectName3) throws ExecutionException {

        return this.folderService.getJobsRecursiveModeFrom(getJenkinsFolderPath(projectName, projectName2, projectName3));
    }

    /*-------------------------------------------------------------------------------------*/
    // /forView/folders
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
    // /forView/jobs
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/forView/jobs", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, List<HudsonJob>> getJobsForViewRoot() throws ExecutionException {
        return this.folderService.getJobsByEnvRecursiveModeForMatrixViewFrom("");
    }

    @RequestMapping(value = "/forView/jobs/{projectName}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, List<HudsonJob>> getJobsForViewLevel1(
            @PathVariable("projectName") String projectName) throws ExecutionException {
        return this.folderService.getJobsByEnvRecursiveModeForMatrixViewFrom(getJenkinsFolderPath(projectName));
    }

    @RequestMapping(value = "/forView/jobs/{projectName}/{projectName2}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, List<HudsonJob>> getJobsForViewLevel2(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2) throws ExecutionException {
        return this.folderService.getJobsByEnvRecursiveModeForMatrixViewFrom(getJenkinsFolderPath(projectName, projectName2));
    }

    @RequestMapping(value = "/forView/jobs/{projectName}/{projectName2}/{projectName3}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, List<HudsonJob>> getJobsForViewLevel3(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2,
            @PathVariable("projectName3") String projectName3) throws ExecutionException {
        return this.folderService.getJobsByEnvRecursiveModeForMatrixViewFrom(getJenkinsFolderPath(projectName, projectName2, projectName3));
    }

    /*-------------------------------------------------------------------------------------*/
    // /forView/metrics
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/forView/metrics/{projectName}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, Object> getMetricsForViewLevel1(
            @PathVariable("projectName") String projectName) throws ExecutionException {
        return this.folderService.getJobsRecursiveModeForMatrixViewFrom(getJenkinsFolderPath(projectName));
    }

    @RequestMapping(value = "/forView/metrics/{projectName}/{projectName2}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, Object> getMetricsForViewLevel2(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2) throws ExecutionException {
        return this.folderService.getJobsRecursiveModeForMatrixViewFrom(getJenkinsFolderPath(projectName, projectName2));
    }

    @RequestMapping(value = "/forView/metrics/{projectName}/{projectName2}/{projectName3}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Map<String, Object> getMetricsForViewLevel3(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2,
            @PathVariable("projectName3") String projectName3) throws ExecutionException {
        return this.folderService.getJobsRecursiveModeForMatrixViewFrom(getJenkinsFolderPath(projectName, projectName2, projectName3));
    }


    @RequestMapping(value = "/results/**", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody List<ObjectNode> getTestResult(
            HttpServletRequest request) throws ExecutionException {

        // split full URI into an Array
        List<String> pathList = Splitter.on("/").splitToList(request.getRequestURI());

        // get all array entries excepted to 2 firsts (/api/results/) and map the rest into a empty collection
        List<String> extractedPath = Lists.newArrayList();

        for (int i = 3; i <= pathList.size() - 2; i++) {
            extractedPath.add(pathList.get(i));
        }

        String buildPath = getJenkinsFolderPath(extractedPath.toArray(new String[0])) + "/" + pathList.get(pathList.size() - 1);

        return folderService.getBuildTestsReportFromUrl(buildPath);
    }





    // SubFolders

    @RequestMapping(value = "/subfolders", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<String> getSubFolderRoot() throws ExecutionException {
        return this.folderService.getSubFolders("");
    }


    @RequestMapping(value = "/subfolders/{projectName}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<String> getSubFolderLevel1(
            @PathVariable("projectName") String projectName,
            @RequestParam(value = "depth", required = false, defaultValue = "1") Integer depth) throws ExecutionException {

        return this.folderService.getSubFolders(getJenkinsFolderPath(projectName));
    }

    @RequestMapping(value = "/subfolders/{projectName}/{projectName2}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<String> getSubFolderLevel2(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2) throws ExecutionException {
        return this.folderService.getSubFolders(getJenkinsFolderPath(projectName, projectName2));
    }


    @RequestMapping(value = "/subfolders/{projectName}/{projectName2}/{projectName3}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<String> getSubFolderLevel3(
            @PathVariable("projectName") String projectName,
            @PathVariable("projectName2") String projectName2,
            @PathVariable("projectName3") String projectName3) throws ExecutionException {

        return this.folderService.getSubFolders(getJenkinsFolderPath(projectName, projectName2, projectName3));
    }


    /*-------------------------------------------------------------------------------------*/
    // Methods
    /*-------------------------------------------------------------------------------------*/

    private String getJenkinsFolderPath(String... folders) {
        String path = "";
        for (String folder : folders) {
            path += "/job/" + folder;
        }
        return path + "/";
    }



}
