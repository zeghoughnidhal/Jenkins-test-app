package com.cloudwatt.example.api.rest;

import com.cloudwatt.example.service.JenkinsService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/api")
@Api()
public class MetricsController extends AbstractController {

    @Autowired
    private JenkinsService folderService;


    /*-------------------------------------------------------------------------------------*/
    // /forView/metrics
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/metrics/**", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get jobs (with environments list).", notes = "")
    public @ResponseBody
    Map<String, Object> getMetrics(
            HttpServletRequest request,
            @RequestParam(value = "recursive", defaultValue = "false") boolean recursiveMode) throws ExecutionException {

        String folderPath = getJenkinsPathFromRequest(request, 3);


        if (recursiveMode) {
            return this.folderService.getJobsForMetricsRecursiveMode(folderPath);
        }

        return this.folderService.getJobsForMetrics(folderPath);
    }


    /*-------------------------------------------------------------------------------------*/
    // /results   (suite test results)
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/results/**", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    ObjectNode getTestResult(
            HttpServletRequest request) throws ExecutionException {

        List<String> pathElements = extractPathElementsFromUrlFromIndex(request, 3);

        // build is the last part of the request url
        String buildId = pathElements.get(pathElements.size() - 1);
        // remove buildId from path
        pathElements.remove(pathElements.size() - 1);
        String folderPath = getJenkinsFolderPath(pathElements) + buildId;

        return folderService.getBuildTestsReportFromUrl(folderPath);
    }

}
