package org.jenkinsci.plugins.customizebuildnow;

import static org.junit.Assert.*;

import hudson.model.ParameterDefinition;
import hudson.model.ParametersDefinitionProperty;
import hudson.model.StringParameterDefinition;
import java.util.ArrayList;
import java.util.List;
import jenkins.model.Messages;
import hudson.model.FreeStyleProject;
import org.htmlunit.html.HtmlPage;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

/**
 * Test Build Now Label Change
 */
public class AlternateBuildNowTest {
    @Rule
    public JenkinsRule rule = new JenkinsRule();

    @Test
    public void testBuildNowChange() throws Exception {
        FreeStyleProject p = rule.createFreeStyleProject();
        Labels labels = new Labels();
        labels.setAlternateBuildNow("Deploy Now");
        p.addProperty(new BuildNowTextProperty(labels));

        JenkinsRule.WebClient wc = rule.createWebClient();
        HtmlPage html = wc.getPage(p);
        assertNotNull(html.getAnchorByText("Deploy Now"));

        FreeStyleProject p2 = rule.configRoundtrip(p);

        BuildNowTextProperty textProperty = p2.getProperty(BuildNowTextProperty.class);
        assertNotNull(textProperty);
        assertEquals(textProperty.getLabels().getAlternateBuildNow(), "Deploy Now");
    }

    @Test
    public void testBuildWithParamsChange() throws Exception {
        FreeStyleProject p = rule.createFreeStyleProject();
        List<ParameterDefinition> parameterDefinitions = new ArrayList<>();
        parameterDefinitions.add(new StringParameterDefinition("test", ""));
        ParametersDefinitionProperty paramsProp = new ParametersDefinitionProperty(parameterDefinitions);
        Labels labels = new Labels();
        labels.setAlternateBuildNow("Run Now");
        labels.setAlternateBuildWithParams("Run with Parameters");
        p.addProperty(new BuildNowTextProperty(labels));
        p.addProperty(paramsProp);

        JenkinsRule.WebClient wc = rule.createWebClient();
        HtmlPage html = wc.getPage(p);
        assertNotNull(html.getAnchorByText("Run with Parameters"));

        FreeStyleProject p2 = rule.configRoundtrip(p);

        BuildNowTextProperty textProperty = p2.getProperty(BuildNowTextProperty.class);
        assertNotNull(textProperty);
        assertEquals(textProperty.getLabels().getAlternateBuildWithParams(), "Run with Parameters");
    }

    @Test
    public void testBuildNowUnChanged() throws Exception {
        FreeStyleProject p = rule.createFreeStyleProject();

        JenkinsRule.WebClient wc = rule.createWebClient();
        HtmlPage html = wc.getPage(p);
        assertNotNull(html.getAnchorByText("Build Now"));

        FreeStyleProject p2 = rule.configRoundtrip(p);
        BuildNowTextProperty textProperty = p2.getProperty(BuildNowTextProperty.class);
        assertNull(textProperty);
    }

    @Test
    public void workflow() throws Exception {
        WorkflowJob p = rule.jenkins.createProject(WorkflowJob.class, "p");
        assertEquals(Messages.ParameterizedJobMixIn_build_now(), p.getBuildNowText());
        Labels labels = new Labels();
        labels.setAlternateBuildNow("Deploy Now");
        p.addProperty(new BuildNowTextProperty(labels));
        assertEquals("Deploy Now", p.getBuildNowText());
        JenkinsRule.WebClient wc = rule.createWebClient();
        HtmlPage html = wc.getPage(p);
        assertNotNull(html.getAnchorByText("Deploy Now"));
        rule.configRoundtrip(p);
        assertNotNull(p.getProperty(BuildNowTextProperty.class));
   }
}