package com.cloudwatt.example.api.rest;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AbstractController extends AbstractRestHandler {


    protected List<String> extractPathElementsFromUrlFromIndex(HttpServletRequest request, int fromIndex) {
        // split full URI into an Array
        List<String> pathList = Splitter.on("/").splitToList(request.getRequestURI());

        // get all array entries excepted to 2 firsts (/api/results/) and map the rest into a empty collection
        List<String> extractedPath = Lists.newArrayList();

        for (int i = fromIndex; i <= pathList.size() - 1; i++) {
            extractedPath.add(pathList.get(i));
        }

        if (extractedPath.size() == 1 && extractedPath.get(0).equals("")) {
            return Lists.newArrayList();
        }
        return extractedPath;
    }


    protected String getJenkinsFolderPath(List<String> folders) {
        String path = "";
        for (String folder : folders) {
            path += "/job/" + folder;
        }
        return path + "/";
    }


    protected String getJenkinsPathFromRequest(HttpServletRequest request, int fromIndex) {
        List<String> pathElements = extractPathElementsFromUrlFromIndex(request, fromIndex);
        return getJenkinsFolderPath(pathElements);
    }
}
