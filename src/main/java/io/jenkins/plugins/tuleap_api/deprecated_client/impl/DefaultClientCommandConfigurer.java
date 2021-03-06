package io.jenkins.plugins.tuleap_api.deprecated_client.impl;

import hudson.Extension;
import hudson.model.TaskListener;
import io.jenkins.plugins.tuleap_credentials.TuleapAccessToken;
import io.jenkins.plugins.tuleap_api.deprecated_client.TuleapClientCommandConfigurer;
import io.jenkins.plugins.tuleap_api.deprecated_client.TuleapClientRawCmd;
import io.jenkins.plugins.tuleap_server_configuration.TuleapConfiguration;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.defaultString;

@Extension
public class DefaultClientCommandConfigurer<T> implements TuleapClientCommandConfigurer {

    private String apiUrl;
    private String gitUrl;
    private TuleapClientRawCmd.Command command;
    private TuleapAccessToken credentials;
    private TaskListener listener;


    public DefaultClientCommandConfigurer() {
    }

    private DefaultClientCommandConfigurer(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public final boolean isMatch(String apiUrl) {
        // FIXME now just returns true :(
        return apiUrl.equals(TuleapConfiguration.get().getApiBaseUrl());
    }

    @Override
    public final DefaultClientCommandConfigurer<T> create(String apiUrl) {
        return new DefaultClientCommandConfigurer<>(apiUrl);
    }

    @Override
    public final DefaultClientCommandConfigurer<T> withCommand(TuleapClientRawCmd.Command command) {
        this.command = command;
        return this;
    }

    @Override
    public final DefaultClientCommandConfigurer<T> withCredentials(TuleapAccessToken credentials) {
        this.credentials = credentials;
        return this;
    }

    @Override
    public final DefaultClientCommandConfigurer<T> withGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
        return this;
    }

    @Override
    public TuleapClientCommandConfigurer withListener(TaskListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public final TuleapClientRawCmd.Command<T> configure() {
        DefaultClient client = new DefaultClient(Optional.ofNullable(credentials), defaultString(apiUrl),
                                                 defaultString(gitUrl), Optional.ofNullable(listener));
        ((TuleapClientRawCmd) command).setClient(client);
        return command;
    }
}
