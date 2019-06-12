package com.cloudwatt.example.api.rest;

import com.cloudwatt.example.domain.jenkins.HudsonJob;
import com.cloudwatt.example.respository.BuildRepository;
import com.cloudwatt.example.respository.JobRepository;
import com.cloudwatt.example.service.JenkinsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ExecutionException;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/api")
@Api()
public class JobController extends AbstractController {

    @Autowired
    private JenkinsService folderService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private BuildRepository buildRepository;

    /*-------------------------------------------------------------------------------------*/
    // /jobs
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/jobs/**", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get jobs of from folder path", notes = "The list is composed by HudsonJob elements")
    public @ResponseBody
    List<HudsonJob> getJobs(
            HttpServletRequest request,
            @RequestParam(value = "recursive", defaultValue = "false") boolean recursiveMode)
            throws ExecutionException {

        String folderPath = getJenkinsPathFromRequest(request, 3);

        if (recursiveMode) {
            return folderService.getJobsRecursiveModeFrom(folderPath);
        }
        return folderService.getJobsFrom(folderPath);
    }


}
