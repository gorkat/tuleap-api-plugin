package io.jenkins.plugins.tuleap_api.client;

import io.jenkins.plugins.tuleap_api.client.authentication.AccessToken;
import io.jenkins.plugins.tuleap_api.client.exceptions.ProjectNotFoundException;

import java.util.List;

public interface ProjectApi {

    String PROJECT_API = "/projects";
    String PROJECT_GROUPS = "/user_groups";

    Project getProjectByShortname(String shortname, AccessToken token) throws ProjectNotFoundException;
    List<UserGroup> getProjectUserGroups(Integer projectId, AccessToken token);
}
