package io.jenkins.plugins.tuleap_api.deprecated_client;

import hudson.ExtensionList;
import hudson.model.TaskListener;
import io.jenkins.plugins.tuleap_credentials.TuleapAccessToken;

public interface TuleapClientCommandConfigurer<T> {

    static <T> TuleapClientCommandConfigurer<T> newInstance(String serverUrl) {
        for (TuleapClientCommandConfigurer factory : ExtensionList.lookup(TuleapClientCommandConfigurer.class)) {
            if (factory.isMatch(serverUrl)) {
                return factory.create(serverUrl);
            }
        }
        throw new IllegalArgumentException("Unsupported Tuleap server URL: " + serverUrl);
    }

    boolean isMatch(String serverUrl);

    TuleapClientCommandConfigurer<T> create(String apiUrl);

    TuleapClientCommandConfigurer<T> withCommand(TuleapClientRawCmd.Command<T> command);

    TuleapClientCommandConfigurer<T> withCredentials(TuleapAccessToken credentials);

    TuleapClientCommandConfigurer<T> withGitUrl(final String gitUrl);

    TuleapClientCommandConfigurer<T> withListener(final TaskListener listener);

    TuleapClientRawCmd.Command<T> configure();
}
