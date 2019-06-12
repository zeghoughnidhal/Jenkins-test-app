package com.cloudwatt.example.api.rest;

import com.cloudwatt.example.domain.jenkins.HudsonFolder;
import com.cloudwatt.example.respository.FolderRepository;
import com.cloudwatt.example.respository.SubFolderRepository;
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/api")
@Api()
public class FolderController extends AbstractController {

    @Autowired
    private JenkinsService folderService;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private SubFolderRepository subFolderRepository;

    /*-------------------------------------------------------------------------------------*/
    // /folders
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/folders/**", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a folder from its path", notes = "")
    public @ResponseBody
    HudsonFolder getFolder(
            HttpServletRequest request,
            @RequestParam(value = "depth", required = false, defaultValue = "1") Integer depth) throws ExecutionException {
        String folderPath = getJenkinsPathFromRequest(request, 3);
        return this.folderService.getFolder(folderPath, depth);
    }


    /*-------------------------------------------------------------------------------------*/
    // Sub-Folders
    /*-------------------------------------------------------------------------------------*/

    @RequestMapping(value = "/subfolders/**", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all hotels.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    List<String> getSubFolder(HttpServletRequest request) throws ExecutionException {
        String folderPath = getJenkinsPathFromRequest(request, 3);
        return this.folderService.getSubFolders(folderPath);
    }


}
